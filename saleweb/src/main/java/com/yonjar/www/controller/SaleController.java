package com.yonjar.www.controller;

import com.google.gson.Gson;
import com.yonjar.www.dtos.Book;
import com.yonjar.www.service.getway.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LuoYJ on 2019/1/3.
 * http://localhost:8080/sale-web/findBook/123
 */
@RestController
public class SaleController {

    @Autowired
    private BookClient bookClient;

    @GetMapping("/sale-web/findBook/{bookName}")
    public Book findBook(@PathVariable String bookName){
        System.out.println("进入到SaleController的findBook方法");

        Book book = bookClient.querybook(bookName);
        /*Gson gson = new Gson();
        String result = gson.toJson(book);
        System.out.println(result);*/
        System.out.println(book.toString());
        return book;
    }
}
