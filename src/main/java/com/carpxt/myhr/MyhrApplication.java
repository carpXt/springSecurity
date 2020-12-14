package com.carpxt.myhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.carpxt.myhr.mapper")
public class MyhrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhrApplication.class, args);
    }

}
