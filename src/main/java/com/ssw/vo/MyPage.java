package com.ssw.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssw.enums.OrderType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssw
 * @date 2023/11/29 15:18
 */
@Data
@NoArgsConstructor
public class MyPage<T, P, E> extends Page<T> {

    private static final long serialVersionUID = 1L;

    // 请求参数
    private P param;

    // 扩展属性
    private E extendObj;

    // 排序字段
    private String sortName;

    // 排序类型
    private OrderType orderType;
}
