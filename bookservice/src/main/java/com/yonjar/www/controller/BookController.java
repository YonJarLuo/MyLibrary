package com.yonjar.www.controller;

import com.yonjar.www.dtos.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by LuoYJ on 2019/1/3.
 */
@RestController
public class BookController {

    @GetMapping(value = "/querybook/{bookName}")
    public Book findBookByName(@PathVariable String bookName){

        Book book = new Book();
        book.setAuthor("yonjar");
        book.setBookName("SpringCloud");
        book.setBookId(9527);
        book.setPrice(100.0);
        book.setPublicationDate(new Date());

        return book;
    }
}
