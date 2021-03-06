package com.et.web.controller.${packageModule};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseController;
import com.et.bean.${packageModule}.${beanName};
import com.et.service.${packageModule}.${beanName}Service;
import com.et.util.ExceptionUtil;
/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：${dateTime} <br>
 * ★☆ @version：  ${version}<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： ${classAnnotation}<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Controller
@RequestMapping("/${lowercaseBeanName}Controller")
public class ${beanName}Controller extends BaseController {
   private String path="${packageModule}";
   @Resource
   private ${beanName}Service ${lowercaseBeanName}Service;
   private Logger logger = LoggerFactory.getLogger(${beanName}Controller.class);
   
   @RequestMapping("/toList.do")
   public String to_list() {
      return path+"/list";
   }

   @RequestMapping("/list.do")
   public void list(${beanName} query, HttpServletResponse response) {
      List<${beanName}> list = ${lowercaseBeanName}Service.findPage(query);
      int total = ${lowercaseBeanName}Service.getTotal(query);
      // 输出
      outputPage(query.getRows(), response, list, total);
   }

   @RequestMapping("/delete.do")
   public String delete(String id) {
      ${lowercaseBeanName}Service.deleteById(id);
      return "forward:to_${lowercaseBeanName}list.do";
   }

   @RequestMapping("/addUI.do")
   public String addUI() {
      return path+"/addUI";
   }

   @RequestMapping("/editUI.do")
   public String editUI(ModelMap map, String id) {
      ${beanName} ${lowercaseBeanName} = ${lowercaseBeanName}Service.getById(id);
      map.put("${lowercaseBeanName}", ${lowercaseBeanName});
      return path+"/editUI";
   }

   @RequestMapping("/add.do")
   public String add(${beanName} ${lowercaseBeanName}) {
      ${lowercaseBeanName}Service.insert(${lowercaseBeanName});
      return "forward:to_${lowercaseBeanName}list.do";
   }

   @RequestMapping("/update.do")
   public String update(${beanName} ${lowercaseBeanName}) {
      ${lowercaseBeanName}Service.update(${lowercaseBeanName});
      return "forward:to_${lowercaseBeanName}list.do";
   }
}
