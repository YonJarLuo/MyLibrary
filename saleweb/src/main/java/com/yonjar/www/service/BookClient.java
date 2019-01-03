package com.yonjar.www.service;

import com.yonjar.www.dtos.Book;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Created by LuoYJ on 2019/1/3.
 */
@FeignClient(name = "book",url = "http://zuulgateway:9000/book")
public interface BookClient {

    @GetMapping(value = "/querybook/{bookName}")
    Book querybook(@PathVariable String bookName);
}
