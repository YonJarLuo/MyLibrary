package com.yonjar.www.service.gateway;

import com.yonjar.www.dtos.Book;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Created by LuoYJ on 2019/1/3.
 * 此处非Spring-cloud微服务集群里面的项目，使用Feign调用微服务网关Zuul，进而调用里面所提供的服务
 * 如果是spring-cloud微服务集群内部使用，@FeignClient里面可以直接写 注册到Eureka的服务名serviceId：@FeignClient("book-service")
 */
@FeignClient(name = "book01",url = "${book-server-api.url}")
public interface BookClient {

    @GetMapping("/querybook/{bookName}")
    Book querybook(@PathVariable("bookName") String bookName);

    /*@GetMapping(value = "/hello",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getHello();*/
}
