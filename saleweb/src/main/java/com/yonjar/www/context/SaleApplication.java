package com.yonjar.www.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by LuoYJ on 2019/1/4.
 * @EnableFeignClients 开启Feign开关
 * 使用默认端口8080
 * 在@EnableFeignClients注解里面需要指定扫描的 Feign包，不然项目无法启动
 * 因为Feign接口需要在启动类package或者子package之下。
 */
@SpringBootApplication
@ComponentScan("com.yonjar.www.**")
@EnableFeignClients("com.yonjar.www.service.gateway")
public class SaleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class);
    }
}
