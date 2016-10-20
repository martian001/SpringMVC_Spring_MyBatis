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

   void deleteByIds(List<String> ids);

   void update(T parameter);

   T getById(String id);

   List<T> getByIds(List<String> ids);

   List<T> getAll();
   
   List<T> query(T parameter);

   T selectOne(Object parameter);

   void executeSql(String sql);

   List<T> findPage(T parameter);

   int getTotal(T parameter);
}
