package com.ssw.service;

import com.ssw.dto.TableDataDTO;
import com.ssw.vo.MyPage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author ssw
 * @date 2023/11/29 14:42
 */
public interface TableDataService {

    ResponseEntity<Object> getTableData(MyPage<Map<String, Object>, TableDataDTO, Object> page);
}
