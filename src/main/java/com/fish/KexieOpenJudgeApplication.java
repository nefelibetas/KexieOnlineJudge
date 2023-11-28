package com.fish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fish.mapper")
public class KexieOpenJudgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(KexieOpenJudgeApplication.class, args);
    }
}
