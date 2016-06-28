package com.et.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.annotation.PermissionAnno;
import com.et.base.BaseAction;
import com.et.bean.system.SysMenu;
import com.et.bean.system.SysUser;
import com.et.service.system.SysMenuService;
import com.et.service.system.SysUserService;
import com.et.util.Constants;

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
   @Resource
   private SysUserService sysUserService;

   @PermissionAnno(methodId="to_index")
   @RequestMapping("/to_index.do")
   public String toIndex(ModelMap model) {
      fillMenus(model);
      return "/index";
   }

   // 填充菜单
   private void fillMenus(ModelMap model) {
      List<SysMenu> sysMenus = sysMenuService.getAll();
      for (SysMenu sysMenu : sysMenus) {
         System.out.println(sysMenu.getMenuName());
         for (SysMenu sysMenu2 : sysMenu.getChildrenList()) {
            System.out.println(sysMenu2.getMenuName());
            
         }
      }
      model.put("sysMenus", sysMenus);
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

   /**
    * 用户登录
    *@author:liangyanjun
    *@time:2016年6月23日下午5:29:26
    *@param model
    *@param userName
    *@param password
    *@param captcha
    *@param req
    *@param resp
    *@return
    */
   @PermissionAnno(methodId="login")
   @RequestMapping("/login.do")
   public String login(ModelMap model,String userName, String password, String captcha, HttpServletRequest req,HttpServletResponse resp) {
      SysUser sysUser =sysUserService.getSysUserByUserName(userName);
      if (sysUser == null) {
         model.put("msg", "用户或密码错误!");
         return "/login";
      }
      if (!sysUser.getPwd().equals(password)) {
         model.put("msg", "用户或密码错误!");
         return "/login";
      }
      req.getSession().setAttribute(Constants.LOGIN_USER, sysUser);
      fillMenus(model);
      return "redirect:/to_index.do";
   }
   /**
    * 退出
    *@author:liangyanjun
    *@time:2016年6月23日下午5:35:37
    *@param req
    *@return
    */
   @RequestMapping("/logout.do")
   public String logout(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      if(session != null){
         session.removeAttribute(Constants.LOGIN_USER);
         session.invalidate();
      }
      req.getSession(true);
      return "redirect:/login";
   }

}
