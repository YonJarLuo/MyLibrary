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

    @GetMapping("/querybook/{bookName}")
    public Book findBookByName(@PathVariable String bookName){

        System.out.println("进入到BookController的findBookByName方法");
        Book book = new Book();
        book.setAuthor("yonjar");
        book.setBookName(bookName);
        book.setBookId(9527);
        book.setPrice(100.0);
        book.setPublicationDate(new Date());

        return book;
    }
}
