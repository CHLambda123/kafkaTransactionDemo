package com.lambda.bigtask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lambda.bigtask.mapper")
public class BigtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigtaskApplication.class, args);
    }

}
