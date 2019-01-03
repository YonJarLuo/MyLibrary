package com.yonjar.www.controller;

import com.yonjar.www.dtos.Book;
import com.yonjar.www.service.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/1/3.
 * @EnableFeignClients 开启Feign开关
 */
@RestController("/sale-web")
@Configuration
public class SaleController {

    @Autowired
    private BookClient bookservice;

    @GetMapping("/findBook/{bookName}")
    public String findBook(@PathVariable String bookName){
        Book book = bookservice.querybook(bookName);
        System.out.println(book.toString());
        return book.toString();
    }
}
