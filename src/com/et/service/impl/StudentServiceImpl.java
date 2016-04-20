package com.et.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.Student;
import com.et.dao.StudentDao;
import com.et.service.StudentService;

/**
 * 
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： liangyanjun                                       ☆★
★☆            @time：2015年9月16日下午4:28:05    ☆★
★☆            @version：                                                                          ☆★
★☆            @lastMotifyTime：                                                   ☆★
★☆            @ClassAnnotation：                                                ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
   @Resource
   private StudentDao studentDao;

   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(studentDao);
   }

   public StudentServiceImpl() {
      System.out.println("StudentServiceImpl.StudentServiceImpl()");
   }

   public StudentDao getStudentDao() {
      return studentDao;
   }

   public void setStudentDao(StudentDao studentDao) {
      this.studentDao = studentDao;
      System.out.println("StudentServiceImpl.setStudentDao()");
   }

   @Override
   public boolean nameIsExist(String name) {
      return studentDao.nameIsExist(name);
   }
}
