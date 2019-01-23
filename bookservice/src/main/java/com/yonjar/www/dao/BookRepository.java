package com.yonjar.www.dao;

import com.yonjar.www.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LuoYJ on 2019/1/10.
 * JpaRepository< , > 的第二个参数类型作用：可以根据它获取唯一的数据；第二个参数类型需和你实体类的ID参数类型相同
 * 在启动类中需扫描指定Repository包  @EnableJpaRepositories
 *
 * 如果我们要做复杂一些的查询，多条件分页，spring data jpa为我们提供了JpaSpecificationExecutor接口
 * 只要简单实现toPredicate方法就可以实现复杂的查询
 */

public interface BookRepository extends JpaRepository<BookEntity,String>{

    //可以自定义查询方法，遵从驼峰命名法
    BookEntity findByBookName(String bookName);

    void deleteByBookName(String bookName);
}
