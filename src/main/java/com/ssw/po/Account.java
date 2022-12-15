package com.ssw.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * @author ssw
 * @date 2022/8/22 15:30
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @TableId(type = IdType.ASSIGN_ID)
    String userId;
    String userData;
    Integer ckSign;
    Integer ckVersion;
}
