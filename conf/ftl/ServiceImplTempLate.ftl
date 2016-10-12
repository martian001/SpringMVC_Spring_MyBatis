package com.et.service.${packageModule}.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.${packageModule}.${beanName};
import com.et.mapper.${packageModule}.${beanName}Mapper;
import com.et.service.${packageModule}.${beanName}Service;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：${dateTime} <br>
 * ★☆ @version：  ${version}<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： ${classAnnotation}<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Service
public class ${beanName}ServiceImpl extends BaseServiceImpl<${beanName}> implements ${beanName}Service {
   @Resource
   private ${beanName}Mapper ${lowercaseBeanName}Mapper;

   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(${beanName}Mapper);
   }
}
