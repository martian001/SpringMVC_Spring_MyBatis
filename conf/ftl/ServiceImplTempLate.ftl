package com.et.service.checkingIn.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.checkingIn.${beanName};
import com.et.dao.checkingIn.${beanName}Dao;
import com.et.service.checkingIn.${beanName}Service;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：${dateTime} <br>
 * ★☆ @version：${version} <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation：${classAnnotation} <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Service
public class ${beanName}ServiceImpl extends BaseServiceImpl<${beanName}> implements ${beanName}Service {
   @Resource
   private ${beanName}Dao dao;

   @PostConstruct
   public void init() {
      setBaseDao(dao);
   }
}
