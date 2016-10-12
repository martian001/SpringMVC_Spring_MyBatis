package com.et.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.et.base.BaseController;
import com.et.bean.system.SysUser;
import com.et.service.system.SysUserService;
import com.et.util.Constants;
import com.et.util.CryptographyUtil;
import com.et.util.StringUtil;

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
@RequestMapping("/sysUserController")
public class SysUserController extends BaseController {
   @Resource
   private SysUserService sysUserService;

   @RequestMapping("/toList.do")
   public String toList() {
      return "system/sysUserList";
   }

   @RequestMapping("/list.do")
   public void list(SysUser query, HttpServletResponse response) {
      List<SysUser> list = sysUserService.findPage(query);
      int total = sysUserService.getTotal(query);
      // 输出
      outputPage(query.getRows(), response, list, total);
   }
   @RequestMapping("/roleUserList.do")
   public void roleUserList(SysUser query, HttpServletResponse response) {
       List<SysUser> list = sysUserService.findRoleUserListPage(query);
       int total = sysUserService.getRoleUserListTotal(query);
       // 输出
       outputPage(query.getRows(), response, list, total);
   }

   @RequestMapping("/toAddOrUpdate.do")
   public String toAddOrUpdate(ModelMap map, String id) {
      if (!StringUtil.isBlank(id)) {
          SysUser sysUser = sysUserService.getById(id);
          map.put("sysUser", sysUser);
       }
      return "system/sysUserAddOrUpdate";
   }

   @RequestMapping("/addOrUpdate.do")
   public void addOrUpdate(SysUser sysUser,HttpServletRequest req, HttpServletResponse resp) {
       String id = sysUser.getId();
       String userName = sysUser.getUserName();// 用户名
       String phone = sysUser.getPhone();// 手机号码
       String realName = sysUser.getRealName();// 真实姓名
       String memberId = sysUser.getMemberId();// 工号
       int status = sysUser.getStatus();// 状态,1表示启用,2表示禁用
       String deptName = sysUser.getDeptName();// 部门
       String mail = sysUser.getMail();// 邮箱
       String pwd = sysUser.getPwd();// 密码
       String remark = sysUser.getRemark();// 备注
       if (StringUtil.isBlank(userName, phone, realName, memberId, deptName, mail)) {
          fillReturnJson(resp, false, "参数不合法，请输入必填项");
          return;
       }

       if (StringUtil.isBlank(id)) {
          if (StringUtil.isBlank(pwd)) {
             fillReturnJson(resp, false, "参数不合法，请输入必填项");
             return;
          }
          // 检查数据是否存在
          if (sysUserService.checkUserNameIsExist(userName)) {
             fillReturnJson(resp, false, "该登录名已存在！");
             return;
          }
          if (sysUserService.checkPhoneIsExist(phone)) {
             fillReturnJson(resp, false, "该手机号码已存在！");
             return;
          }
       }
       SysUser loginUser = getLoginUser(req);
       // 有id为更新操作，否则为新增操作
       if (!StringUtil.isBlank(id)) {
          // 更新信息
          SysUser updatesysUser = sysUserService.getById(id);
          updatesysUser.setRealName(realName);
          updatesysUser.setMemberId(memberId);
          updatesysUser.setStatus(status);
          updatesysUser.setDeptName(deptName);
          updatesysUser.setRemark(remark);
          updatesysUser.setMail(mail);
          updatesysUser.setUpdateId(loginUser.getId());
          sysUserService.update(updatesysUser);
       } else {
          // 添加
          sysUser.setPwd(CryptographyUtil.encBase64(pwd));
          sysUser.setUpdateId(loginUser.getId());
          sysUser.setCreatorId(loginUser.getId());
          sysUserService.insert(sysUser);
       }
       fillReturnJson(resp, true, "提交成功");
   }
   /**
    * 检查用户名是否已存在
    * 存在返回true，不存在返回false
    *@author:liangyanjun
    *@time:2016年7月8日下午3:26:40
    *@param userName
    *@param Id
    *@param response
    * @throws TException 
    */
   @RequestMapping(value="/checkUserNameIsExist" , method=RequestMethod.POST)
   public void checkUserNameIsExist(String userName, String Id, HttpServletResponse resp){
      if (StringUtil.isBlank(userName)) {
         fillReturnJson(resp, false, "参数不合法");
         return;
      }
      boolean flag = false;
      SysUser sysUser = sysUserService.getSysUserByUserName(userName);
      if (sysUser!=null&&!StringUtil.isBlank(sysUser.getId())) {
         flag = true;
         if (!StringUtil.isBlank(Id) && Id.equals(sysUser.getId())) {
            flag=false;
         }
      }
      // 输出
      fillReturnJson(resp, flag, "查询成功");
   }
   
   /**
    * 检查手机号码是否已存在
    * 存在返回true，不存在返回false
    *@author:liangyanjun
    *@time:2016年7月8日下午3:30:03
    *@param phone
    *@param resp
    * @param Id 
    * @throws TException 
    */
   @RequestMapping(value="/checkPhoneIsExist" , method=RequestMethod.POST)
   public void checkPhoneIsExist(String phone , String Id, HttpServletResponse resp){
      if (StringUtil.isBlank(phone)) {
         fillReturnJson(resp, false, "参数不合法");
         return;
      }
      boolean flag = false;
      SysUser sysUser = sysUserService.getSysUserByPhone(phone);
      if (sysUser!=null&&!StringUtil.isBlank(sysUser.getId())) {
          flag = true;
          if (!StringUtil.isBlank(Id) && Id.equals(sysUser.getId())) {
             flag=false;
          }
       }
      // 输出
      fillReturnJson(resp, flag, "查询成功");
   }
}
