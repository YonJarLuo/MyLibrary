package com.yonjar.www.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by LuoYJ on 2019/1/3.
 * 注意：需要在启动类里面添加需要扫描的controller，不然请求不到
 */
@ComponentScan("com.yonjar.www.**")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.yonjar.www.service.gateway")   //此处为了测试Feign  使用@EnableFeignClients扫描指定包，不然无法启动
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class);
    }
}
