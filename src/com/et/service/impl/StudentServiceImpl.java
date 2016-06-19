package com.et.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.Student;
import com.et.mapper.system.StudentMapper;
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
   private StudentMapper studentMapper;

   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(studentMapper);
   }

   public StudentServiceImpl() {
      System.out.println("StudentServiceImpl.StudentServiceImpl()");
   }

   public StudentMapper getStudentMapper() {
      return studentMapper;
   }

   public void setStudentMapper(StudentMapper studentMapper) {
      this.studentMapper = studentMapper;
   }

   @Override
   public boolean nameIsExist(String name) {
      return studentMapper.nameIsExist(name);
   }
}
