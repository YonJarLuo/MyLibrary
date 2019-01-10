package com.yonjar.www.dao;

import com.yonjar.www.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LuoYJ on 2019/1/10.
 * JpaRepository< , > 的第二个参数类型作用：可以根据它获取唯一的数据
 */
public interface BookRepository extends JpaRepository<BookEntity,String>{


}
