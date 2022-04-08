package com.jzz.springCloud.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.jzz.springCloud.admin.mapper")
@EnableSwagger2
public class AdminApplication9528 {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication9528.class, args);
    }

}
