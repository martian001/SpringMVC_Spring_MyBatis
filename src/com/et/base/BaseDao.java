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

   void deleteById(Long id);

   void deleteByIds(Long[] ids);

   void update(T parameter);

   T getById(Long id);

   List<T> getByIds(Long[] ids);

   List<T> getByPage(int first, int MaxResults);

   List<T> getByPageNum(int pageNumber, int pageResults);

   int selectCount(T parameter);

   List<T> getAll();

   T selectOne(Object parameter);

   List<T> selectList(Object parameter, int pageSize, int pageIndex);

   void executeSql(String sql);

   List<T> findPage(T parameter);

   int getTotal(T parameter);
}
