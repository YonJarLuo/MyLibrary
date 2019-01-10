package com.yonjar.www.controller;

import com.yonjar.www.dao.BookRepository;
import com.yonjar.www.domain.BookEntity;
import com.yonjar.www.dtos.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by LuoYJ on 2019/1/3.
 */
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/querybook/{bookName}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Book findBookByName(@PathVariable String bookName){

        System.out.println("进入到BookController的findBookByName方法");
        Book book = new Book();
        book.setAuthor("yonjar");
        book.setBookName(bookName);
        book.setBookId(9527);
        book.setPrice(100.0);
        book.setPublicationDate(new Date());

        System.out.println(book.toString());
        return book;
    }

    @GetMapping("/findAllBook")
    public List<Book> findAllBook(){
        List<BookEntity> all = bookRepository.findAll();
        System.out.println(all.toString());
        return null;
    }
}
