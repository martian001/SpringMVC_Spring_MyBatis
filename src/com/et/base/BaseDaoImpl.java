package com.et.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
*/@SuppressWarnings("unchecked")
@Repository
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    protected Class<T> clazz;
    protected String className;
    @Autowired
    protected SqlSessionFactory sqlSessionFactory;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public BaseDaoImpl() {
        // 通过反射技术得到T的真是类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();// 获取当前new对象 的泛型的 父类类型
        clazz = (Class<T>) pt.getActualTypeArguments()[0];// 获取第一个类型参数的真实类型
        className = clazz.getName();
    }

    @Override
    public int insert(T parameter) {
        return getSqlSession().insert(className + ".insert", parameter);
    }

    @Override
    public void deleteById(Long id) {
        getSqlSession().delete(className + ".deleteById", id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(T parameter) {
        getSqlSession().update(className + ".updateById", parameter);
    }

    @Override
    public T getById(Long id) {
        return (T) getSqlSession().selectOne(className + ".getById", id);
    }

    @Override
    public List<T> getByIds(Long[] ids) {
        //  return getSqlSession().selectList(className+".getByIds", ids);
        return null;
    }

    @Override
    public List<T> getByPage(int first, int MaxResults) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> getByPageNum(int pageNumber, int pageResults) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int selectCount(T parameter) {
        return (int) getSqlSession().selectOne(className + ".selectCount", parameter);
    }

    @Override
    public List<T> getAll() {
        return getSqlSession().selectList(className + ".getAll");
    }

    @Override
    public T selectOne(Object parameter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> selectList(Object parameter, int pageSize, int pageIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void executeSql(String sql) {
        // TODO Auto-generated method stub

    }

}
