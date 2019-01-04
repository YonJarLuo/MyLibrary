package com.yonjar.www.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by LuoYJ on 2019/1/4.
 * 使用默认端口8080
 */
@SpringBootApplication
@ComponentScan("com.yonjar.www.**")
//@EnableCircuitBreaker
//@ServletComponentScan
//@EnableFeignClients
public class SaleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class);
    }
}
