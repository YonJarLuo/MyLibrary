package com.yonjar.www.context;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by LuoYJ on 2019/1/3.
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
    public static void main(String[] args) {
        //使用另一种启动方式，指定端口
        new SpringApplicationBuilder(ZuulApplication.class).properties("server.port=9000").run(args);
    }
}
