package com.et.base;

import java.util.List;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： liangyanjun                                       ☆★
★☆            @time：2015年9月16日下午4:32:44    ☆★
★☆            @version：                                                                          ☆★
★☆            @lastMotifyTime：                                                   ☆★
★☆            @ClassAnnotation：                                                ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public interface BaseDao<T> {
   int insert(T parameter);

   void deleteById(String id);

   void deleteByIds(List ids);

   void update(T parameter);

   T getById(String id);

   List<T> getByIds(List ids);

   List<T> getAll();

   T selectOne(Object parameter);

   void executeSql(String sql);

   List<T> findPage(T parameter);

   int getTotal(T parameter);
}
