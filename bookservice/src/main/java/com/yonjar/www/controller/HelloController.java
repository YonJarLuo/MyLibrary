package com.yonjar.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/1/4.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("....进入到HelloController...");
        return "Hello";
    }
}
