package com.xmcc.springboot_demo.repository;

import com.xmcc.springboot_demo.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpaRepository就提供了常用的增删改查方法//第一个参数 是实体类名称  第二个参数是主键类型
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


}
