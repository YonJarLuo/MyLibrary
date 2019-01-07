package com.yonjar.www.service.getway;

import com.yonjar.www.dtos.Book;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Created by LuoYJ on 2019/1/3.
 */
@FeignClient(name = "book01",url = "http://localhost:9000/book")
public interface BookClient {

    @GetMapping(value = "/querybook/{bookName}")
//    @RequestLine("GET /querybook/{bookName}")  //Feign的注解
    Book querybook(@PathVariable("bookName") String bookName);
}
