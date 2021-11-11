package com.example.crud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.crud.entity.User;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.crypto.Data;
import java.util.Date;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
    public static void main(String[] args) {
        Date data = new Date();
        Date dat2 = new Date();
        String s = "456" ;
        if (data != null && data != null && !s.isEmpty()) {
            System.out.println(0);
        }

    }
}
