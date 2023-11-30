package com.ssw.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ssw.dto.TableDataDTO;
import com.ssw.enums.OrderType;
import com.ssw.po.Table;
import com.ssw.service.TableDataService;
import com.ssw.vo.MyPage;
import io.swagger.models.auth.In;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ssw
 * @date 2023/11/29 14:43
 */
@Service
public class TableDataServiceImpl implements TableDataService {

    @Override
    public ResponseEntity<Object> getTableData(MyPage<Map<String, Object>, TableDataDTO, Object> page) {
        if (false) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("xxx error");
        }

        /*
         * 模拟dao获取表格数据
         *  Row id   Column 1    Column 2    Column 3
         *  Row 1    Value 1     Value 2     Value 3
         *  Row 2    Value 4     Value 5     Value 6
         *  Row 3    Value 7     Value 8     Value 9
         */
        List<Table> daoData = new ArrayList<>();
        daoData.add(Table.builder().id(1).row("Row 1").column("Column 1").value(1).columnOrder(1).build());
        daoData.add(Table.builder().id(2).row("Row 1").column("Column 2").value(2).columnOrder(2).build());
        daoData.add(Table.builder().id(3).row("Row 1").column("Column 3").value(null).columnOrder(3).build());
        daoData.add(Table.builder().id(4).row("Row 2").column("Column 1").value(4).columnOrder(1).build());
        daoData.add(Table.builder().id(5).row("Row 2").column("Column 2").value(5).columnOrder(2).build());
        daoData.add(Table.builder().id(6).row("Row 2").column("Column 3").value(null).columnOrder(3).build());
        daoData.add(Table.builder().id(7).row("Row 3").column("Column 1").value(7).columnOrder(1).build());
        daoData.add(Table.builder().id(8).row("Row 3").column("Column 2").value(8).columnOrder(2).build());
        daoData.add(Table.builder().id(9).row("Row 3").column("Column 3").value(9).columnOrder(3).build());

        // 表头
        LinkedHashMap<String, String> headerMapTmp = daoData.stream().sorted(Comparator.comparing(Table::getColumnOrder)).collect(Collectors.toMap(Table::getColumn, Table::getColumn, (a, b) -> a, LinkedHashMap::new));
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Row id", "Row id");
        headerMap.putAll(headerMapTmp);

        // 表体
        Set<String> rowSet = daoData.stream().distinct().map(Table::getRow).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        List<Map<String, Object>> rowList = new LinkedList<>();
        rowSet.forEach(row -> {
            LinkedHashMap<String, Object> rowMap = new LinkedHashMap<>();
            headerMap.forEach((key, value) -> {
                if ("Row id".equals(value)) {
                    rowMap.put(value, row);
                } else {
                    rowMap.put(value, null);
                    daoData.forEach(data -> {
                        if (data.getRow().equals(row) && data.getColumn().equals(value)) {
                            rowMap.put(value, data.getValue());
                        }
                    });
                }
            });
            rowList.add(rowMap);
        });

        // 排序
        OrderType orderType = page.getParam().getOrderType();
        String sortName = page.getParam().getSortName();
        if (headerMap.containsKey(sortName) && ObjectUtil.isNotEmpty(orderType)) {
            if (OrderType.DESC.equals(orderType)) {
                rowList.sort(Comparator.comparing(m -> (m.containsKey(sortName) && m.get(sortName)
                        != null ? (Integer) m.get(sortName) : Integer.MAX_VALUE), Comparator.reverseOrder()));
            } else if (OrderType.ASC.equals(orderType)) {
                rowList.sort(Comparator.comparing(m -> (m.containsKey(sortName) && m.get(sortName)
                        != null ? (Integer) m.get(sortName) : Integer.MAX_VALUE)));
            }
        }

        // 内存分页
        List<Map<String, Object>> items = ListUtils.partition(rowList, (int) page.getSize()).get((int) (page.getCurrent() - 1));
        page.setTotal(rowList.size());
        page.setRecords(items);
        page.setExtendObj(headerMap);
        return ResponseEntity.ok(page);
    }
}
