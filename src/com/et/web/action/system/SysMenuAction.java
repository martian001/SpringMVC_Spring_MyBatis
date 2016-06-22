package com.et.web.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseAction;
import com.et.bean.system.SysMenu;
import com.et.service.system.SysMenuService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-06-22 16:07:35 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统菜单<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Controller
public class SysMenuAction extends BaseAction {
   @Resource
   private SysMenuService sysMenuService;

   @RequestMapping("/to_sysMenuAction_list.do")
   public String to_list() {
      return "sysMenuAction/list";
   }

   @RequestMapping("/sysMenuAction_list.do")
   public void list(SysMenu query, HttpServletResponse response) {
      Map<String, Object> resultMap = new HashMap<String, Object>();
      List<SysMenu> sysMenuList = sysMenuService.findPage(query);
      int total = sysMenuService.getTotal(query);
      // 输出
      resultMap.put("rows", sysMenuList);
      resultMap.put("total", total);
      outputJson(resultMap, response);
   }

   @RequestMapping("/sysMenuAction_delete.do")
   public String delete(String id) {
      sysMenuService.deleteById(id);
      return "forward:to_sysMenuAction_list.do";
   }

   @RequestMapping("/sysMenuAction_addUI.do")
   public String addUI() {
      return "sysMenuAction/addUI";
   }

   @RequestMapping("/sysMenuAction_editUI.do")
   public String editUI(ModelMap map, String id) {
      SysMenu sysMenu = sysMenuService.getById(id);
      map.put("sysMenu", sysMenu);
      return "sysMenuAction/editUI";
   }

   @RequestMapping("/sysMenuAction_add.do")
   public String add(SysMenu sysMenu) {
      sysMenuService.insert(sysMenu);
      return "forward:to_sysMenuAction_list.do";
   }

   @RequestMapping("/sysMenuAction_update.do")
   public String update(SysMenu sysMenu) {
      sysMenuService.update(sysMenu);
      return "forward:to_sysMenuAction_list.do";
   }
}
