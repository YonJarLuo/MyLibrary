package com.yonjar.www.service.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by LuoYJ on 2019/1/8.
 * 如果是spring-cloud微服务集群内部使用，@FeignClient里面可以直接写 注册到Eureka的服务名serviceId：@FeignClient("book-service")
 * 如果指定了url,Feign不走Ribbon负载均衡
 */
@FeignClient(name = "testFeign" ,url = "http://localhost:8765")
public interface TestFeign {

    @GetMapping(value = "/hello",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getHello();
}
