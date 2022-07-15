package com.xln.sping.spingbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@TableName
public class User {
    @TableId             // 等同于 @TableId(type = IdType.ASSIGN_ID)，默认采用雪花算法实现主键自增长，无需数据库设置。
    private Long id;     // ID类型在对象中必须使用Long类型，在数据库表中必须使用BigInt类型，为了兼容雪花算法。
    @TableField          // 驼峰匹配规则：如果表字段名user_name，则对象属性可用userName，且可省略@TableField注解
    private String name;
    private Integer age;
    private String email;
}
