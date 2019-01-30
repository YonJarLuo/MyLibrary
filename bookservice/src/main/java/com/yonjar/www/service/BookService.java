package com.yonjar.www.service;

import com.yonjar.www.domain.BookEntity;

import java.util.List;

/**
 * Created by LuoYJ on 2019/1/29.
 */
public interface BookService {

    Boolean addBook(BookEntity bookEntity);

    BookEntity findByBookName(String bookName);

    /**
     * 后续会修改为分页查询
     * @return
     */
    List<BookEntity> findAll();

    /**
     * 单条记录删除 后续会增加多条记录删除
     * @param id
     */
    void deleteById(String id);

    Boolean updateBook(BookEntity bookEntity);
}
