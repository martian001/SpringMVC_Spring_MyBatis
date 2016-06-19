package com.et.service.checkingIn.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.checkingIn.CheckingInIndex;
import com.et.mapper.checkingIn.CheckingInMapper;
import com.et.service.checkingIn.CheckingInService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午10:00:06 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Service
public class CheckingInServiceImpl extends BaseServiceImpl<CheckingIn> implements CheckingInService {
   @Resource
   private CheckingInMapper checkingInDao;

   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(checkingInDao);
   }

   /**
    *@author:liangyanjun
    *@time:2016年5月25日下午3:46:49
    */
   @Override
   public List<CheckingInIndex> queryCheckingInIndex(CheckingInIndex checkingInIndex) {
      return checkingInDao.queryCheckingInIndex(checkingInIndex);
   }

   /**
    *@author:liangyanjun
    *@time:2016年5月25日下午3:46:49
    */
   @Override
   public int getCheckingInIndexTotal(CheckingInIndex checkingInIndex) {
      return checkingInDao.getCheckingInIndexTotal(checkingInIndex);
   }

   @Override
   public List<CheckingInIndex> queryHandleCheckingIn(CheckingInIndex checkingInIndex) {
      return checkingInDao.queryHandleCheckingIn(checkingInIndex);
   }
}
