package com.example.page.Entity;


import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;

    private Date createTime;//駝峰

    private Date updateTime;


    private Integer version;

    private Integer deleted;
}
