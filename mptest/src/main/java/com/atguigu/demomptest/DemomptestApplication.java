package com.atguigu.demomptest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

/*
*爲什麽加mapperscan
* 接口動態實現他的實現類對象，動態生成對象找不到，所以要加mapperscan
* */
public class DemomptestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemomptestApplication.class, args);
    }

}
