package com.yonjar.www.service.serviceImpl;

import com.yonjar.www.dao.BookRepository;
import com.yonjar.www.domain.BookEntity;
import com.yonjar.www.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LuoYJ on 2019/1/29.
 * 增加事务管理
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

//    @Transactional(readOnly = false)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addBook(BookEntity bookEntity) {
        BookEntity entity = bookRepository.saveAndFlush(bookEntity);
        return entity==null?false:true;
    }

    @Transactional(readOnly = true)
    @Override
    public BookEntity findByBookName(String bookName) {
        BookEntity entity = bookRepository.findByBookName(bookName);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> all = bookRepository.findAll();
        return all;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        bookRepository.delete(id);
    }

    /**
     * JPA的修改有多种方式（反射、JPQL）
     * @param bookEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateBook(BookEntity bookEntity) {
        String id = bookEntity.getId();
        BookEntity oldEntity = bookRepository.findOne(id);
        //设置可变化的数据
        oldEntity.setPublicationDate(bookEntity.getPublicationDate());
        oldEntity.setPrice(bookEntity.getPrice());
        oldEntity.setAuthor(bookEntity.getAuthor());
        oldEntity.setUpdateBy(bookEntity.getUpdateBy());
        oldEntity.setUpdateTime(bookEntity.getUpdateTime());
        BookEntity entity = bookRepository.saveAndFlush(oldEntity);
        return entity==null?false:true;
    }
}
