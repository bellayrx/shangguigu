package com.example.crud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "ID")
    private Long id;
    @TableField(value = "姓名")
    private String name;

    private Integer age;

    private String email;
}
