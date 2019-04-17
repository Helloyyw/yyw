package com.xmcc.springboot_demo.Dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
public class BatchImpl<T> implements BatchDao<T>{
    //实现批量插入方法 :其思想就是先批量存在缓存区中然后一次性插入

    @PersistenceContext
    protected EntityManager em;
    @Transactional
    public void batchInsert(List<T> list) {
    //遍历结合存入缓冲区然后写入数据库
        for(int i =0;i<list.size();i++){
            em.persist(list.get(i));
            if(i%100 == 0 || list.size()-1 == i ){
                //每100条执行一次写入数据库操作
             em.flush();
             em.clear();
            }

        }





    }
}
