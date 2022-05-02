package com.jzz.springCloud.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.jzz.springCloud.admin.mapper")
@EnableSwagger2
@EnableFeignClients
public class AdminApplication9528 {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication9528.class, args);
    }

}
