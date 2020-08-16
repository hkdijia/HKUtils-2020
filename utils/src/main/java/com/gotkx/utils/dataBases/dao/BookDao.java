package com.gotkx.utils.dataBases.dao;



import com.gotkx.utils.dataBases.pojo.Book;

import java.util.List;

public class BookDao {

    public void save(List<Book> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
        System.out.println("黄凯测试");
    }

}
