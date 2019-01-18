package com.yonjar.www.controller;

import com.yonjar.www.dao.BookRepository;
import com.yonjar.www.domain.BookEntity;
import com.yonjar.www.dtos.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by LuoYJ on 2019/1/3.
 */
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/findBookByName/{bookName}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BookEntity findBookByName(@PathVariable String bookName){

        System.out.println("....进入到BookController的findBookByName方法....");
        /*Book book = new Book();
        book.setAuthor("yonjar");
        book.setBookName(bookName);
        book.setId("9527");
        book.setPrice(100.0);*/
        /*System.out.println(book.toString());
        return book;*/
        BookEntity bookEntity = bookRepository.findByBookName(bookName);  //bookEntity返回的对象如果不是都是所需的，则可以通过转换对应的dto来接收
        System.out.println(bookEntity.toString());
        return bookEntity;
    }

    @GetMapping("/findAllBook")
    public List<BookEntity> findAllBook(){
        List<BookEntity> all = bookRepository.findAll();
        /*BookEntity bookEntity = all.get(0);
        Date createTime = bookEntity.getCreateTime();
        System.out.println(createTime);*/
        System.out.println(all.toString());
        return all;
    }

    /**
     * @param bookEntity
     * @RequestBody 利用对象去获取前端传来的数据
     * @return
     */
    /*@PostMapping("/addBook")
    public Boolean addBook(@RequestBody BookEntity bookEntity){*/
    @GetMapping("/addBook")
    public Boolean addBook(){
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthor("xinxixue");
        bookEntity1.setBookName("weifen");
        bookEntity1.setPrice(84.00);
        bookEntity1.setPublicationDate(new Date());
        BookEntity entity = bookRepository.saveAndFlush(bookEntity1);
        return true;
    }
}
