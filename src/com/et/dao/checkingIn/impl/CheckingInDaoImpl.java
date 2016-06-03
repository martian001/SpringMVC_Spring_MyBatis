package com.et.dao.checkingIn.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.et.base.BaseDaoImpl;
import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.checkingIn.CheckingInIndex;
import com.et.dao.checkingIn.CheckingInDao;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午10:04:47 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Repository
public class CheckingInDaoImpl extends BaseDaoImpl<CheckingIn> implements CheckingInDao{

   @Override
   public List<CheckingInIndex> queryCheckingInIndex(CheckingInIndex checkingInIndex) {
      return getSqlSession().selectList(className + ".queryCheckingInIndex",checkingInIndex);
   }

   @Override
   public int getCheckingInIndexTotal(CheckingInIndex checkingInIndex) {
      return (int) getSqlSession().selectOne(className + ".getCheckingInIndexTotal",checkingInIndex);
   }

   /**
    *@author:liangyanjun
    *@time:2016年6月3日下午3:05:16
    */
   @Override
   public List<CheckingInIndex> queryHandleCheckingIn(CheckingInIndex checkingInIndex) {
      return getSqlSession().selectList(className + ".queryHandleCheckingIn",checkingInIndex);
   }

}
