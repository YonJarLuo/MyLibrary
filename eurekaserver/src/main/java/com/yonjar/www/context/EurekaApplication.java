package com.yonjar.www.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by LuoYJ on 2019/1/3.
 * @SpringBootApplication注解具有：@SpringBootConfiguration,@EnableAutoConfiguration,@ComponentScan等注解功能
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);

//        new SpringApplicationBuilder(EurekaApplication.class).run(args);

        //指定启动哪个服务
//        new SpringApplicationBuilder(EurekaApplication.class).profiles("slave1").run(args);  //application.yml 的spring配置要打开
        //使用另一种启动方式，指定端口
        //new SpringApplicationBuilder(ZuulApplication.class).properties("server.port=8761").run(args);
    }
}
