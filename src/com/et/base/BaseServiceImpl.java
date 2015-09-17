package com.et.base;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Resource
    private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int insert(T parameter) {
        return baseDao.insert(parameter);
    }

    @Override
    public void deleteById(Long id) {
        baseDao.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        baseDao.deleteByIds(ids);
    }
    @Transactional
    @Override
    public void update(T parameter) {
        baseDao.update(parameter);
    }

    @Override
    public T getById(Long id) {
        return baseDao.getById(id);
    }

    @Override
    public List<T> getByIds(Long[] ids) {
        return baseDao.getByIds(ids);
    }

    @Override
    public List<T> getByPage(int first, int MaxResults) {
        return baseDao.getByPage(first, MaxResults);
    }

    @Override
    public List<T> getByPageNum(int pageNumber, int pageResults) {
        return baseDao.getByPageNum(pageNumber, pageResults);
    }

    @Override
    public int selectCount(T parameter) {
        return baseDao.selectCount(parameter);
    }

    @Override
    public List<T> getAll() {
        return baseDao.getAll();
    }

    @Override
    public T selectOne(Object parameter) {
        return baseDao.selectOne(parameter);
    }

    @Override
    public List<T> selectList(Object parameter, int pageSize, int pageIndex) {
        return baseDao.selectList(parameter, pageSize, pageIndex);
    }

    @Override
    public void executeSql(String sql) {
        baseDao.executeSql(sql);
    }

}
