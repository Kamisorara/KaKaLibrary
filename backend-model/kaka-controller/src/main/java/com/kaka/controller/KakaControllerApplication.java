package com.kaka.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.kaka.common",
                "com.kaka.controller",
                "com.kaka.entity",
                "com.kaka.framework"
        }
)
@MapperScan("com.kaka.service.mapper")
public class KakaControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaControllerApplication.class, args);
    }

}
