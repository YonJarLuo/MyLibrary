package com.yonjar.www.controller;

import com.yonjar.www.service.gateway.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/1/8.
 */
@RestController
public class TestFeignController {

    @Autowired
    private TestFeign testFeign;

    @GetMapping("/getHello")
    public String getHello(){
        String result = testFeign.getHello();
        System.out.println("通过Fegin请求返回的 ："+result);
        return result;
    }
}
