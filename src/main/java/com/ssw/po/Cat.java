package com.ssw.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author ssw
 * @date 2022/8/2 10:38
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("cat")
public class Cat {
    Integer id;
    String name;
    String account;
    String password;
}
