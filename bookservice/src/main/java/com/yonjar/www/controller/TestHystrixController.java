package com.yonjar.www.controller;

import com.yonjar.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/3/6.
 * 测试Hystrix的断路器开启与回退方法
 * http://localhost:9000/book/user
 */
@RestController
public class TestHystrixController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String findUser(){
        String user = userService.findUser();
        return user;
    }
}
