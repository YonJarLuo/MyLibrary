package com.yonjar.www.controller;

import com.yonjar.www.service.gateway.ESFeign;
import com.yonjar.www.service.gateway.TestFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/1/8.
 */
@RestController
@Api(description = "测试Feign接口", tags = "TestFeignController")
public class TestFeignController {

    @Autowired
    private TestFeign testFeign;

    @Autowired
    private ESFeign esFeign;

    @GetMapping("/getHello")
    public String getHello(){
        String result = testFeign.getHello();
        System.out.println("通过Fegin请求返回的 ："+result);
        return result;
    }

    /**
     * 0t_ovmwBiiUuzdgwOrvE
     * @param id
     * @return
     */
    @GetMapping("/getAccountById")
    public Object getAccountById(@RequestParam("id") String id){
        System.out.println("es----controller");
        return esFeign.getAccountById(id);
    }

    @GetMapping("/getAccountById2")
    public Object getAccountById2(){
        System.out.println("es--2--controller");
        return esFeign.getAccountById2();
    }
}
