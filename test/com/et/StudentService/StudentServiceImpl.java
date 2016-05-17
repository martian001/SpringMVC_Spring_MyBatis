package com.et.StudentService;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.et.base.TestBase;
import com.et.bean.Student;
import com.et.service.StudentService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月17日下午11:02:10 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class StudentServiceImpl extends TestBase {

   @Test
   public void test_getAll() {
      try {
         StudentService studentService = (StudentService) ctx.getBean("studentServiceImpl");
         List<Student> list = studentService.getAll();
         for (Student student : list) {
            System.out.println(student);
         }
      } catch (BeansException e) {
         e.printStackTrace();
      }
   }
}
