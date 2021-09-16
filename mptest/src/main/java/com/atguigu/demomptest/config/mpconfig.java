package com.atguigu.demomptest.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atguigu.demomptest.mapper")
public class mpconfig {
    /*
    * 樂觀鎖的插件
    * */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /*
    * 分頁
    * */
    @Bean
        public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
