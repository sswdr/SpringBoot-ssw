package com.ssw.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ssw
 * @date 2023/11/29 15:50
 */
@Getter
@ToString
public enum OrderType implements Serializable {

    ASC(0, "asc", "升序"),
    DESC(1, "desc", "降序"),
    OTHER(-1, "other", "不排序");

    private static final long serialVersionUID = 1L;

    @EnumValue
    private final int code;
    @JsonValue
    private final String name;
    private final String displayText;

    OrderType(int code, String name, String displayText) {
        this.code = code;
        this.name = name;
        this.displayText = displayText;
    }
}