package com.ssw.dto;

import com.ssw.enums.OrderType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableDataDTO {

    @ApiModelProperty(name = "当前页, 默认1", example = "1")
    private int curPage = 1;

    @ApiModelProperty(name = "分页大小, 默认10", example = "10")
    private int pageSize = 10;

    @ApiModelProperty(name = "排序方式, 默认不排序-1", example = "-1")
    private OrderType orderType = OrderType.OTHER;

    @ApiModelProperty(name = "排序字段", example = "")
    private String sortName = "";


    @ApiModelProperty(name = "请求参数id", example = "")
    @NotNull(message = "请求参数id不能为空")
    private String id;

    @ApiModelProperty(name = "开始时间", example = "1")
    @NotNull(message = "开始时间[startTime]不能为空")
    @Min(value = 1, message = "开始时间[startTime]不能小于1")
    private long startTime;

    @ApiModelProperty(name = "结束时间", example = "1")
    @NotNull(message = "结束时间[endTime]不能为空")
    @Min(value = 1, message = "结束时间[endTime]不能小于1")
    private long endTime;
}
