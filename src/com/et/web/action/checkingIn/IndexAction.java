package com.et.web.action.checkingIn;

import java.util.List;

import javax.annotation.Resource;

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
public class IndexAction extends BaseAction {
   @Resource
   private SysMenuService sysMenuService;

   @RequestMapping("/to_index.do")
   public String toIndex(ModelMap model) {
      List<SysMenu> sysMenus = sysMenuService.getAll();
      for (SysMenu sysMenu : sysMenus) {
         System.out.println(sysMenu.getMenuName());
         for (SysMenu sysMenu2 : sysMenu.getChildrenList()) {
            System.out.println(sysMenu2.getMenuName());
            
         }
      }
      model.put("sysMenus", sysMenus);
      return "/index";
   }

   @RequestMapping("/to_index_content.do")
   public String toIndexContent(ModelMap model) {
      return "/index_content";
   }

   @RequestMapping("/to_form_avatar.do")
   public String toFormAvatar(ModelMap model) {
      return "/form_avatar";
   }

   @RequestMapping("/to_profile.do")
   public String toProfile(ModelMap model) {
      return "/profile";
   }

   @RequestMapping("/to_login.do")
   public String toLogin(ModelMap model) {
      return "/login";
   }

}
