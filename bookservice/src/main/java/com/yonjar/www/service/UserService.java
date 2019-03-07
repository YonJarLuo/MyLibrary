package com.yonjar.www.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by LuoYJ on 2019/3/6.
 */
@Service
public class UserService {

    @Value("${server.port}")
    private String port;

    @Autowired
    RestTemplate restTemplate;

    //测试Hystrix  有基于方法，类以及全局的  此处演示基于方法上
    @HystrixCommand(fallbackMethod = "userFallBack")
    public String findUser(){
        System.out.println("-----findUser----");
        //使用RestTemplate模拟调用其它服务接口   但user服务并未开启
        String user = restTemplate.getForObject("http://localhost:8766/findUser", String.class);
        return user;
    }

    //提供回退方法  fallBack方法需要和helloWorld的返回参数类型一致
    public String userFallBack(){
        return "服务器："+port+" 发生错误，回退";
    }

}
