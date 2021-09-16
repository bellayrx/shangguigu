package com.atguigu.demomptest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)//自動填充
    private Date createTime;//駝峰
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    @Version//添加版本號
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
