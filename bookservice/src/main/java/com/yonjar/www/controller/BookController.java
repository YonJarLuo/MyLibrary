package com.yonjar.www.controller;

import com.yonjar.www.dao.BookRepository;
import com.yonjar.www.domain.BookEntity;
import com.yonjar.www.dtos.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by LuoYJ on 2019/1/3.
 * @Api swagger注解
 * @ApiOperation 接口方法说明
 * @ApiImplicitParam 具体参数说明。paramType的值有：
 *      header：请求参数放置于Request Header，使用@RequestHeader获取
 *      query：请求参数放置于请求地址，使用@RequestParam获取
 *      path：（用于restful接口）-->请求参数的获取：@PathVariable
 *      body：（不常用）
 *      form（不常用）
 * 启动项目访问：http://localhost:8765/swagger-ui.html
 */

@RestController
@Api(value = "bookservice服务的RESTFUL API接口",description = "图书管理接口", tags = "BookController")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "获取书信息",notes = "根据书名获取书信息")
    @ApiImplicitParam(paramType = "path", name = "bookName", value = "书名", required = true, dataType = "String")  //注意name值为参数
    @GetMapping(value = "/findBookByName/{bookName}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)  //设置返回状态
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

    @ApiOperation(value = "查询所有书籍", notes = "查询所有书籍详情信息")
    @GetMapping("/findAllBook")
    @ResponseStatus(HttpStatus.OK)
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
    @ApiOperation(value = "新增书籍" , notes = "新增书籍")
    @ApiImplicitParam(name = "bookEntity",value = "书籍数据", required = true,dataType = "BookEntity")  //省略 paramType ,注意dataType类型
    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.CREATED)     //这里状态为创建
    public Boolean addBook(@RequestBody BookEntity bookEntity){
    /*@GetMapping("/addBook")
    public Boolean addBook(){
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthor("xinxixue");
        bookEntity1.setBookName("weifen");
        bookEntity1.setPrice(84.00);
        bookEntity1.setPublicationDate(new Date());
        BookEntity entity = bookRepository.saveAndFlush(bookEntity1);*/
        BookEntity entity = bookRepository.saveAndFlush(bookEntity);
        return entity==null?false:true;
    }

    @ApiOperation(value = "删除书籍" , notes = "根据书名删除书籍")
    @ApiImplicitParam(paramType = "query",name = "bookName",value = "书名",required = true,dataType = "String")
    @DeleteMapping("/deletBook")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@RequestParam("bookName") String bookName){  //注意这里使用的是 @RequestParam注解  与@PathVariable不同
        bookRepository.deleteByBookName(bookName);
    }
}
