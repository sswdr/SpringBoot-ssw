package com.ssw.po;

import lombok.*;

/**
 * @author ssw
 * @date 2023/11/30 13:44
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Table {
    private Integer id;
    // 行
    private String row;
    // 列
    private String column;
    // 值
    private Integer value;
    // 列序号
    private Integer columnOrder;
}
