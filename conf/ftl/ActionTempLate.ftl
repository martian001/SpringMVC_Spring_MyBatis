package com.et.web.action.${packageModule};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseAction;
import com.et.bean.${packageModule}.${beanName};
import com.et.service.${packageModule}.${beanName}Service;

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
public class ${beanName}Action extends BaseAction {
   @Resource
   private ${beanName}Service ${lowercaseBeanName}Service;

   @RequestMapping("/to_${lowercaseBeanName}Action_list.do")
   public String to_list() {
      return "${lowercaseBeanName}Action/list";
   }

   @RequestMapping("/${lowercaseBeanName}Action_list.do")
   public void list(${beanName} query, HttpServletResponse response) {
      Map<String, Object> resultMap = new HashMap<String, Object>();
      List<${beanName}> ${lowercaseBeanName}List = ${lowercaseBeanName}Service.findPage(query);
      int total = ${lowercaseBeanName}Service.getTotal(query);
      // 输出
      resultMap.put("rows", ${lowercaseBeanName}List);
      resultMap.put("total", total);
      outputJson(resultMap, response);
   }

   @RequestMapping("/${lowercaseBeanName}Action_delete.do")
   public String delete(String id) {
      ${lowercaseBeanName}Service.deleteById(id);
      return "forward:to_${lowercaseBeanName}Action_list.do";
   }

   @RequestMapping("/${lowercaseBeanName}Action_addUI.do")
   public String addUI() {
      return "${lowercaseBeanName}Action/addUI";
   }

   @RequestMapping("/${lowercaseBeanName}Action_editUI.do")
   public String editUI(ModelMap map, String id) {
      ${beanName} ${lowercaseBeanName} = ${lowercaseBeanName}Service.getById(id);
      map.put("${lowercaseBeanName}", ${lowercaseBeanName});
      return "${lowercaseBeanName}Action/editUI";
   }

   @RequestMapping("/${lowercaseBeanName}Action_add.do")
   public String add(${beanName} ${lowercaseBeanName}) {
      ${lowercaseBeanName}Service.insert(${lowercaseBeanName});
      return "forward:to_${lowercaseBeanName}Action_list.do";
   }

   @RequestMapping("/${lowercaseBeanName}Action_update.do")
   public String update(${beanName} ${lowercaseBeanName}) {
      ${lowercaseBeanName}Service.update(${lowercaseBeanName});
      return "forward:to_${lowercaseBeanName}Action_list.do";
   }
}
