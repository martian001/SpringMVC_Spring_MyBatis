package com.et.web.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseController;
import com.et.bean.system.SysLogInfo;
import com.et.service.system.SysLogInfoService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-23 11:32:33 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统操作日志<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Controller
@RequestMapping("/sysLogInfoController")
public class SysLogInfoController extends BaseController {
   private String path = "system";
   @Resource
   private SysLogInfoService sysLogInfoService;
   private Logger logger = LoggerFactory.getLogger(SysLogInfoController.class);

   @RequestMapping("/toList.do")
   public String to_list() {
      return path + "/sysLogList";
   }

   @RequestMapping("/list.do")
   public void list(SysLogInfo query, HttpServletResponse response) {
      List<SysLogInfo> list = sysLogInfoService.findPage(query);
      int total = sysLogInfoService.getTotal(query);
      // 输出
      outputPage(query.getRows(), response, list, total);
   }
}
