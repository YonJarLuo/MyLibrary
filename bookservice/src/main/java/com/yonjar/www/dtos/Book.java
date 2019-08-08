package com.yonjar.www.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LuoYJ on 2019/1/3.
 */
@Data
public class Book implements Serializable{
    @ApiModelProperty(value = "主键ID")
    private String id;
    @ApiModelProperty(value = "书名")
    private String bookName;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty("价格")
    private double price;


}
