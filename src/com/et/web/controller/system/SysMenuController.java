package com.et.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseController;
import com.et.bean.system.SysMenu;
import com.et.bean.system.SysUser;
import com.et.constant.ModuelConstant;
import com.et.constant.SysLogTypeConstant;
import com.et.service.system.SysMenuService;
import com.et.util.ExceptionUtil;
import com.et.util.StringUtil;

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
@RequestMapping("/sysMenuController")
public class SysMenuController extends BaseController {
   private String path = "system";
   @Resource
   private SysMenuService sysMenuService;
   private Logger logger = LoggerFactory.getLogger(SysRoleController.class);
   
   @RequestMapping("/toSysMenuList.do")
   public String to_list() {
      return path+"/sysMenuList";
   }

   @RequestMapping("/list.do")
   public void list(SysMenu query, HttpServletResponse response) {
      List<SysMenu> list = sysMenuService.findPage(query);
      int total = sysMenuService.getTotal(query);
      // 输出
      outputPage(query.getRows(), response, list, total);
   }

   @RequestMapping("/delete.do")
   public void delete(String id, HttpServletRequest req,HttpServletResponse resp) {
      if (StringUtil.isBlank(id)) {
           fillReturnJson(resp, false, "缺少参数，请重新操作");
           return;
      }
      sysMenuService.deleteById(id);
      fillReturnJson(resp, true, "提交成功");
      recordLog(ModuelConstant.MODUEL_SYSTEM, SysLogTypeConstant.LOG_TYPE_DELETE, "删除系统菜单,参数：id="+id, req);
   }
   /** 新增或修改菜单
    * 
    * @author:liangyanjun
    * @time:2016年10月8日下午6:09:37
    * @param sysMenu
    * @param req
    * @param resp */
   @RequestMapping("/addOrUpdate.do")
   public void addOrUpdate(SysMenu sysMenu, HttpServletRequest req, HttpServletResponse resp) {
       String id = sysMenu.getId();
       String menuName = sysMenu.getMenuName();
       String parentId = sysMenu.getParentId();
       String iconCls = sysMenu.getIconCls();
       Integer status = sysMenu.getStatus();
       String menuUrl = sysMenu.getMenuUrl();
       int menuIndex = sysMenu.getMenuIndex();
       if (StringUtil.isBlank(menuName, menuUrl) || status <= 0) {
           fillReturnJson(resp, false, "参数不合法，请输入必填项");
           return;
       }
       SysUser loginUser = getLoginUser(req);
       String loginUserId = loginUser.getId();
       sysMenu.setUpdateId(loginUserId);

       if (StringUtil.isBlank(id)) {//新增
           sysMenu.setCreatorId(loginUserId);
           sysMenuService.insert(sysMenu);
           recordLog(ModuelConstant.MODUEL_SYSTEM, SysLogTypeConstant.LOG_TYPE_ADD, "增加系统菜单,参数：sysMenu="+sysMenu, req);
       } else {//修改
           SysMenu updatesysMenu = sysMenuService.getById(id);
           updatesysMenu.setMenuName(menuName);
           updatesysMenu.setParentId(parentId);
           updatesysMenu.setIconCls(iconCls);
           updatesysMenu.setStatus(status);
           updatesysMenu.setMenuUrl(menuUrl);
           updatesysMenu.setMenuIndex(menuIndex);
           sysMenuService.update(updatesysMenu);
           recordLog(ModuelConstant.MODUEL_SYSTEM, SysLogTypeConstant.LOG_TYPE_UPDATE, "修改系统菜单,参数：sysMenu="+sysMenu+",updatesysMenu="+updatesysMenu, req);
       }
       fillReturnJson(resp, true, "提交成功");
   }
}
