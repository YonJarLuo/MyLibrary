package com.yonjar.www.controller;

import com.yonjar.www.dao.BookRepository;
import com.yonjar.www.domain.BookEntity;
import com.yonjar.www.dtos.Book;
import com.yonjar.www.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by LuoYJ on 2019/1/3.
 * @Api swagger注解
 * @ApiOperation 接口方法说明
 * @ApiImplicitParam 具体参数说明。paramType的值有：
 *      header：请求参数放置于Request Header，使用@RequestHeader获取
 *      query：请求参数放置于请求地址，使用@RequestParam获取         http://localhost:8765/findBookByName/bookName="远方"
 *      path：（用于restful接口）-->请求参数的获取：@PathVariable   http://localhost:8765/findBookByName/远方
 *      body：（不常用）
 *      form（不常用）
 * 启动项目访问：http://localhost:8765/swagger-ui.html  注意：在页面填写json的日期数据时，格式要按照：yyyy-MM-dd HH:mm:ss （不然转换异常）
 */

@RestController
@Api(value = "bookservice服务的RESTFUL API接口",description = "图书管理接口", tags = "BookController")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "获取书信息",notes = "根据书名获取书信息")
    @ApiImplicitParam(paramType = "path", name = "bookName", value = "书名", required = true, dataType = "String")  //注意name值为参数 paramType = "path"对应@PathVariable
    @GetMapping(value = "/findBookByName/{bookName}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)  //设置返回状态
    public BookEntity findBookByName(@PathVariable String bookName){

        System.out.println("....进入到BookController的findBookByName方法....");
        BookEntity bookEntity = bookService.findByBookName(bookName);  //bookEntity返回的对象如果不是都是所需的，则可以通过转换对应的dto来接收
        System.out.println(bookEntity.toString());
        return bookEntity;
    }

    @ApiOperation(value = "查询所有书籍", notes = "查询所有书籍详情信息")
    @GetMapping("/findAllBook")
    @ResponseStatus(HttpStatus.OK)
    public List<BookEntity> findAllBook(){
        List<BookEntity> all = bookService.findAll();
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
        return bookService.addBook(bookEntity);
    }

    @ApiOperation(value = "删除书籍" , notes = "根据 id 删除书籍")
    @ApiImplicitParam(paramType = "query",name = "id",value = "书id",required = true,dataType = "String")  //此处paramType:query  对应 @RequestParam
    @DeleteMapping("/deletBook")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@RequestParam("id") String id){  //注意这里使用的是 @RequestParam注解  与@PathVariable不同
        //后面会添加service层
        bookService.deleteById(id);
    }

    @ApiOperation(value = "修改书籍" , notes = "修改书籍信息")
//    @ApiImplicitParam(name = "bookEntity",value = "修改数据",required = true,dataType = "BookEntity")
    @PutMapping("/updateBook")
    @ResponseStatus(HttpStatus.OK)
    public Boolean updateBook(@RequestBody BookEntity bookEntity){
        return bookService.updateBook(bookEntity);
    }
}
