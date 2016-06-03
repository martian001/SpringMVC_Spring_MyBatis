package com.et.SystemService;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeansException;

import com.et.base.TestBase;
import com.et.bean.system.SysUser;
import com.et.service.system.SysUserService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午10:41:34 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */

public class SysUserServiceTest extends TestBase {
   @Test
   public void test_getAll() {
      try {
         String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
         System.out.println(beanDefinitionNames);
         SysUserService service = (SysUserService) ctx.getBean("sysUserServiceImpl");
         List<SysUser> all = service.getAll();
         for (SysUser sysUser : all) {
            System.out.println(sysUser);
         }
      } catch (BeansException e) {
         e.printStackTrace();
      }
   }
}
