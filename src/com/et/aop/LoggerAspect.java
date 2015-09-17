package com.et.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2015年9月17日上午9:46:44 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class LoggerAspect {
   private static Logger logger = Logger.getLogger(ClassName.class);

   /**
    * 定义环绕通知的处理方法
    * 
    * @param pjd
    * @return
    */
   public Object aroundMethod(ProceedingJoinPoint pjd) {
      Object result = null;
      // 得到被拦截参数的名 称
      String classAndMethodName = pjd.toString();// execution(List com.et.base.BaseService.getAll())
      classAndMethodName = classAndMethodName.substring(classAndMethodName.indexOf(" "), classAndMethodName.length() - 1);// 获取类名和方法名称
      // String methodName = pjd.getSignature().getName();
      try {
         //
         logger.info(" Start :The method " + classAndMethodName + " 参数：" + Arrays.asList(pjd.getArgs()));
         // 执行目标方法
         result = pjd.proceed();
         // 返回通知
         logger.info(" End : The method " + classAndMethodName + " 结果：" + result);
      } catch (Throwable e) {
         logger.error(" End : The method " + classAndMethodName + " occurs exception:" + e);
         throw new RuntimeException(e);
      }
      return result;
   }

   /**
    * 处理异常通知的方法,当拦截的代码发生异常,那么就会调用当前方法
    * 
    * @param joinPoint
    * @param e
    */
   public void afterThrowing(Method m, Object[] os, Object target, Throwable throwable) {
      logger.error("The method " + m.getName() + ",params:" + Arrays.toString(os) + " occurs excetion:" + throwable);
   }
}
