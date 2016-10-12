package com.et.StudentService;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.et.bean.system.SysUser;
import com.et.service.system.SysUserService;

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
public class StudentServiceImpl{

   @Test
   public void test_getAll() {
      try {
          ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"applicationContext-spring.xml"});
          System.out.println(ctx);
          SysUserService service = (SysUserService) ctx.getBean("sysUserServiceImpl");
          List<SysUser> all = service.getAll();
          for (SysUser sysUser : all) {
             System.out.println(sysUser);
          }
      } catch (BeansException e) {
         e.printStackTrace();
      }
   }
   @Test
   public void test_getAll2() {
      System.out.println(2+2.5+7.5+2+2+2.5+2+2);
   }
}
