package com.yonjar.www.context;

import com.yonjar.www.router.DiscoveryRouteLocator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LuoYJ on 2019/1/3.
 * 配置Ribbon路由跳转时，serviceId是Spring.application.name,不是eureka的hostname
 * 经过测试简单跳转没有问题：http:localhost:8080/test/hell
 */
@ComponentScan("com.yonjar.www.**")
@SpringBootApplication
//@EnableDiscoveryClient
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApplication {
    public static void main(String[] args) {


        //使用另一种启动方式，指定端口
        new SpringApplicationBuilder(ZuulApplication.class).properties("server.port=9000").run(args);
    }

}
