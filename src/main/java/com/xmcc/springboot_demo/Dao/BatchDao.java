package com.xmcc.springboot_demo.Dao;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BatchDao<T> {

    //批量插入接口
    void batchInsert(List<T> list);
}
