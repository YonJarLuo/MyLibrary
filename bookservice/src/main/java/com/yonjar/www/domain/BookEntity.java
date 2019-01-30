package com.yonjar.www.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LuoYJ on 2019/1/10.
 * 在父类的pom.xml中，引入了lombok依赖，且IDEA安装了lombok插件，即可使用lombok的注解
 * @Entity 声明实体类
 * @Data 包含了set,get,toString,hashCode...等方法
 * @NoArgsConstructor 空参构造方法
 * @GeneratorValue    JPA通用策略生成器
 * @GenericGenerator  自定义主键生成策略
 */
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class BookEntity extends BaseEntity{

    @Id
    @Column(name = "id",columnDefinition = "varchar(50)")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    private String id;

    @Column(name = "book_name",columnDefinition = "varchar(12) default null comment '书名' ")
    private String bookName;

    @Column(name = "author",columnDefinition = "varchar(10) default null comment '作者' ")
    private String author;

    @Column(name = "price",columnDefinition = "double(5,2) default null comment '价格' ")
    private double price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //如果是 String转Date 使用@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "publication_date",columnDefinition = "datetime default null comment '出版日期' ")
    private Date publicationDate;
}
