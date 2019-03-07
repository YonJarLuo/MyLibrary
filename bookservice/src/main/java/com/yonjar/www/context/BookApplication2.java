package com.yonjar.www.context;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Created by LuoYJ on 2019/3/6.
 */
@ComponentScan("com.yonjar.www.**")                     //添加需要扫描的controller
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.yonjar.www.service.gateway")   //此处为了测试Feign  使用@EnableFeignClients扫描指定包，不然无法启动
@EnableJpaRepositories("com.yonjar.www.dao")    //添加Repository扫描
@EntityScan("com.yonjar.www.domain")           //添加Entity扫描
@EnableCircuitBreaker                           //打开断路器
public class BookApplication2 {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BookApplication2.class).properties("server.port=8764").run(args);
    }
}
