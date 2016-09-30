package com.et.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseController;
import com.et.bean.system.SysUser;
import com.et.service.system.SysUserService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-06-23 15:11:41 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统用户<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Controller
public class SysUserController extends BaseController {
   @Resource
   private SysUserService sysUserService;

   @RequestMapping("/to_sysUserAction_list.do")
   public String to_list() {
      return "sysUserAction/list";
   }

   @RequestMapping("/sysUserAction_list.do")
   public void list(SysUser query, HttpServletResponse response) {
      Map<String, Object> resultMap = new HashMap<String, Object>();
      List<SysUser> sysUserList = sysUserService.findPage(query);
      int total = sysUserService.getTotal(query);
      // 输出
      resultMap.put("rows", sysUserList);
      resultMap.put("total", total);
      outputJson(resultMap, response);
   }

   @RequestMapping("/sysUserAction_delete.do")
   public String delete(String id) {
      sysUserService.deleteById(id);
      return "forward:to_sysUserAction_list.do";
   }

   @RequestMapping("/sysUserAction_addUI.do")
   public String addUI() {
      return "sysUserAction/addUI";
   }

   @RequestMapping("/sysUserAction_editUI.do")
   public String editUI(ModelMap map, String id) {
      SysUser sysUser = sysUserService.getById(id);
      map.put("sysUser", sysUser);
      return "sysUserAction/editUI";
   }

   @RequestMapping("/sysUserAction_add.do")
   public String add(SysUser sysUser) {
      sysUserService.insert(sysUser);
      return "forward:to_sysUserAction_list.do";
   }

   @RequestMapping("/sysUserAction_update.do")
   public String update(SysUser sysUser) {
      sysUserService.update(sysUser);
      return "forward:to_sysUserAction_list.do";
   }
}
