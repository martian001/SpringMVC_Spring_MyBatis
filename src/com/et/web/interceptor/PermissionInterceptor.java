package com.et.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.et.annotation.PermissionAnno;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年6月28日下午2:52:00 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 通过实现的方式同时拦截请求<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class PermissionInterceptor implements HandlerInterceptor {

   /**
    * 通过实现接口在执行完请求执行该方法afterCompletion
    */
   @Override
   public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception exception) throws Exception {
   }

   /**
    * 通过实现接口在执行完action执行该方法postHandle
    */
   @Override
   public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView modelAndView) throws Exception {
   }

   /**
    * 通过实现接口在执行action之前执行该方法preHandle
    */
   @Override
   public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      System.out.println(handlerMethod.getBean());
      PermissionAnno permissionAnno = handlerMethod.getMethod().getAnnotation(PermissionAnno.class);
      if (permissionAnno != null) {
         System.out.println(permissionAnno.methodId());
      }
      System.out.println("通过实现接口在执行action之前执行该方法preHandle" + handler);
      return true;// 是否执行action
   }

}
