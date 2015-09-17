package com.et.dao.impl;

import org.springframework.stereotype.Repository;

import com.et.base.BaseDaoImpl;
import com.et.bean.Student;
import com.et.dao.StudentDao;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： liangyanjun                                       ☆★
★☆            @time：2015年9月16日下午4:34:05    ☆★
★☆            @version：                                                                          ☆★
★☆            @lastMotifyTime：                                                   ☆★
★☆            @ClassAnnotation：                                                ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

    public StudentDaoImpl() {
        System.out.println("UserDaoImpl实例化");
    }

    @Override
    public boolean nameIsExist(String name) {
        return true;
    }

    public static void main(String[] args) {
        StudentDao dao = new StudentDaoImpl();
    }

}
