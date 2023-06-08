package com.kaka.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.kaka.common",
                "com.kaka.controller",
                "com.kaka.entity",
                "com.kaka.framework"
        }
)
public class KakaControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaControllerApplication.class, args);
    }

}
