package com.ssw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ssw
 * @date 2022/11/22 11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {

    @NotEmpty(message = "name不能为空")
    @Length(min = 5, max = 17, message = "name长度不对")
    private String name;

    @NotNull(message = "age不能为空")
    private Integer age;

//    1
}
