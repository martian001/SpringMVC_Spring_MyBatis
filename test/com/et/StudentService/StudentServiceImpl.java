package com.et.StudentService;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeansException;

import com.et.base.TestBase;
import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.system.SysMenu;
import com.et.bean.system.SysUser;
import com.et.service.checkingIn.CheckingInService;
import com.et.service.system.SysMenuService;
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
public class StudentServiceImpl extends TestBase {

   @Test
   public void test_getAll() {
      try {
         test_getAll2();
         
      } catch (BeansException e) {
         e.printStackTrace();
      }
   }
   public void test_getAll2() {
      try {
         SysMenuService service = (SysMenuService) ctx.getBean("sysMenuServiceImpl");
         List<SysMenu> all = service.getAll();
         for (SysMenu obj : all) {
            System.out.println(obj);
         }
         
      } catch (BeansException e) {
         e.printStackTrace();
      }
   }
   @Test
   public void test_getAll1() {
      try {
         SysUserService service = (SysUserService) ctx.getBean("sysUserServiceImpl");
         // SysUser sysUser=new SysUser();
         // sysUser.setUserName("lyj");
         // sysUser.setRealName("梁衍君");
         // sysUser.setPassword("123");
         // sysUser.setMemberId("321");
         // service.insert(sysUser);
         List<SysUser> all = service.getAll();
         for (SysUser sysUser : all) {
            System.out.println(sysUser);
         }
         CheckingInService checkingInService = (CheckingInService) ctx.getBean("checkingInServiceImpl");
         CheckingIn checkingIn=new CheckingIn();
         checkingIn.setHour(1.3);
         checkingIn.setStartDate(new Date());
         checkingIn.setEndDate(new Date());
         checkingIn.setMonth(10);
         checkingIn.setStatus(2);
         //checkingInService.insert(checkingIn);
         List<CheckingIn> all2 = checkingInService.getAll();
         for (CheckingIn c : all2) {
            System.out.println(c);
         }
         // StudentService studentService = (StudentService) ctx.getBean("studentServiceImpl");
         // List<Student> list = studentService.getAll();
         // for (Student student : list) {
         // System.out.println(student);
         // }
      } catch (BeansException e) {
         e.printStackTrace();
      }
   }
}
