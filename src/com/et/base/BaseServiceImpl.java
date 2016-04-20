package com.et.base;

import java.util.List;

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

   /**
    *@author:liangyanjun
    *@time:2016年4月20日下午4:50:55
    */
   @Override
   public List<T> findPage(T parameter) {
      return baseDao.findPage(parameter);
   }

   /**
    *@author:liangyanjun
    *@time:2016年4月20日下午4:50:55
    */
   @Override
   public int getTotal(T parameter) {
      return baseDao.getTotal(parameter);
   }

}
