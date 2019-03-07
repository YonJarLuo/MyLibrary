package com.yonjar.www.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by LuoYJ on 2019/3/6.
 * 启动Zuul，浏览器访问http://localhost:9000/book/hello
 * 测试Hystrix
 */
@RestController
@Api(description = "HelloWorld接口", tags = "HelloController")
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println(port+"....进入到HelloController...");
        return port+"：Hello";
    }

}
