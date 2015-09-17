package com.et.base;

import java.util.List;
import java.util.Map;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年11月20日 下午9:02:47      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public interface BaseService<T> {
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
}
