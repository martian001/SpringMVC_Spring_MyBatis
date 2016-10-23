package com.et.web.controller.system;

import java.util.List;

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
import com.et.bean.system.SysUser;
import com.et.bean.system.SysUserRole;
import com.et.constant.Constants;
import com.et.service.system.SysUserRoleService;
import com.et.util.ExceptionUtil;
import com.et.util.StringUtil;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-12 11:44:56 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 用户角色关系表<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Controller
@RequestMapping("/sysUserRoleController")
public class SysUserRoleController extends BaseController {
    private String path = "system";
    @Resource
    private SysUserRoleService sysUserRoleService;
    private Logger logger = LoggerFactory.getLogger(SysUserRoleController.class);

    @RequestMapping("/list.do")
    public void list(SysUserRole query, HttpServletResponse response) {
        List<SysUserRole> list = sysUserRoleService.findPage(query);
        int total = sysUserRoleService.getTotal(query);
        // 输出
        outputPage(query.getRows(), response, list, total);
    }

    

    /** 角色加入用户
     * 
     * @author:liangyanjun
     * @time:2016年10月12日下午2:23:18
     * @param roleId
     * @param userIds
     * @param req
     * @param resp */
    @RequestMapping("/saveSysUserRole.do")
    public void saveSysUserRole(String roleId, String userIdStrs, HttpServletRequest req, HttpServletResponse resp) {
        if (StringUtil.isBlank(roleId, userIdStrs)) {
            fillReturnJson(resp, false, "参数不合法，请重新操作");
            return;
        }
        String[] userIds = userIdStrs.split(",");
        SysUser loginUser = getLoginUser(req);
        String loginUserId = loginUser.getId();
        for (String userId : userIds) {
            SysUserRole updateUserRole = sysUserRoleService.getByUserAndRoleId(userId, roleId);
            if (updateUserRole == null) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                sysUserRole.setStatus(Constants.STATUS_ENABLED);
                sysUserRole.setCreatorId(loginUserId);
                sysUserRole.setUpdateId(loginUserId);
                sysUserRoleService.insert(sysUserRole);
            } else if (updateUserRole.getStatus() == Constants.STATUS_DISABLED) {
                updateUserRole.setStatus(Constants.STATUS_ENABLED);
                updateUserRole.setUpdateId(loginUserId);
                sysUserRoleService.update(updateUserRole);
            }
        }
        fillReturnJson(resp, true, "操作成功");
    }

    /** 用户移除角色
     * 
     * @author:liangyanjun
     * @time:2016年10月12日下午2:25:04
     * @param roleId
     * @param userIds
     * @param req
     * @param resp */
    @RequestMapping("/removeSysUserRole.do")
    public void removeSysUserRole(String roleId, String userIdStrs, HttpServletRequest req, HttpServletResponse resp) {
        if (StringUtil.isBlank(roleId, userIdStrs)) {
            fillReturnJson(resp, false, "参数不合法，请重新操作");
            return;
        }
        String[] userIds = userIdStrs.split(",");
        SysUser loginUser = getLoginUser(req);
        String loginUserId = loginUser.getId();
        for (String userId : userIds) {
            SysUserRole updateUserRole = sysUserRoleService.getByUserAndRoleId(userId, roleId);
            if (updateUserRole != null && updateUserRole.getStatus() == Constants.STATUS_ENABLED) {
                updateUserRole.setStatus(Constants.STATUS_DISABLED);
                updateUserRole.setUpdateId(loginUserId);
                sysUserRoleService.update(updateUserRole);
            }
        }
        fillReturnJson(resp, true, "操作成功");
    }
}
