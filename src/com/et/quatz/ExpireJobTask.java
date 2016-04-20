package com.et.quatz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2015年1月10日 下午6:19:54      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class ExpireJobTask {
   private Logger logger = LoggerFactory.getLogger(ExpireJobTask.class);

   public ExpireJobTask() {
      System.out.println("ExpireJobTask.ExpireJobTask()");
   }

   public void doBiz() {
      logger.info("我是定时任务，开始工作");
      // System.out.println("我是定时任务，开始工作");
   }
}
