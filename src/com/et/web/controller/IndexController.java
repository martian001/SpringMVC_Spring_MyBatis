package com.et.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseController;
import com.et.bean.system.SysMenu;
import com.et.service.system.SysMenuService;
import com.et.service.system.SysUserService;
import com.et.shiro.CaptchaUsernamePasswordToken;
import com.et.shiro.IncorrectCaptchaException;
import com.et.util.Constants;
import com.et.util.ExceptionUtil;
import com.et.util.LogTypeConstant;

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
public class IndexController extends BaseController {
   @Resource
   private SysMenuService sysMenuService;
   @Resource
   private SysUserService sysUserService;
   
   private Logger logger = LoggerFactory.getLogger(IndexController.class);

   @RequestMapping("/index.do")
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

   @RequestMapping("/ignore/to_login.do")
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
   @RequestMapping("/ignore/login.do")
   public void login(ModelMap model,String userName, String password, String captcha, HttpServletRequest req,HttpServletResponse resp) {
       HashMap<String, Object> result = new HashMap<String, Object>();
       Subject subject = SecurityUtils.getSubject();
       CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
       token.setCaptcha(captcha);
       token.setRememberMe(true);
       try {
           token.setPassword(password.toCharArray());
           token.setUsername(userName);
           subject.login(token);
       } catch (UnknownSessionException use) {
           subject = new Subject.Builder().buildSubject();
           subject.login(token);
           result.put("msg", Constants.UNKNOWN_SESSION_EXCEPTION);
           logger.error(ExceptionUtil.getExceptionMessage(use));
       } catch (UnknownAccountException | IncorrectCredentialsException ex) {
           result.put("msg", Constants.UNKNOWN_PWD_ACCOUNT_EXCEPTION);
       } catch (LockedAccountException lae) {
           result.put("msg", Constants.LOCKED_ACCOUNT_EXCEPTION);
       } catch (DisabledAccountException lae) {
           result.put("msg", Constants.DISABLED_ACCOUNT_EXCEPTION);
       } catch (IncorrectCaptchaException e) {
           result.put("msg", Constants.INCORRECT_CAPTCHA_EXCEPTION);
       } catch (Exception e) {
           result.put("msg", Constants.UNKNOWN_EXCEPTION);
           logger.error(ExceptionUtil.getExceptionMessage(e));
       }
       if (result.get("msg") != null) {
           result.put("success", false);
       } else {
           result.put("success", true);
       }
       outputJson(result, resp);
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
       Subject subject = SecurityUtils.getSubject();
       if (subject.isAuthenticated()) {
          subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
       }
      return "redirect:/ignore/to_login.do";
   }

}
