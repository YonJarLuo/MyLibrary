package com.yonjar.www.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/1/4.
 */
@RestController
@Api(description = "HelloWorld接口", tags = "HelloController")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("....进入到HelloController...");
        return "Hello";
    }
}
