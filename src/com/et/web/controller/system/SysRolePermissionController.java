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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.base.BaseController;
import com.et.bean.system.SysRolePermission;
import com.et.bean.system.SysUser;
import com.et.bean.system.SysRolePermission;
import com.et.service.system.SysRolePermissionService;
import com.et.util.Constants;
import com.et.util.ExceptionUtil;
import com.et.util.StringUtil;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-12 15:19:50 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 角色-权限关系表<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Controller
@RequestMapping("/sysRolePermissionController")
public class SysRolePermissionController extends BaseController {
    private String path = "system";
    @Resource
    private SysRolePermissionService sysUserRoleService;
    private Logger logger = LoggerFactory.getLogger(SysRolePermissionController.class);

    /** 
     * 角色关联权限
     * @author:liangyanjun
     * @time:2016年10月12日下午4:20:37
     * @param roleId
     * @param permisStrs
     * @param req
     * @param resp */
    @RequestMapping("/addSysRolePermission.do")
    public void addSysRolePermission(String roleId, String permisStrs, HttpServletRequest req, HttpServletResponse resp) {
        if (StringUtil.isBlank(roleId, permisStrs)) {
            fillReturnJson(resp, false, "参数不合法，请重新操作");
            return;
        }
        String[] permisIds = permisStrs.split(",");
        SysUser loginUser = getLoginUser(req);
        String loginUserId = loginUser.getId();
        for (String permisId : permisIds) {
            SysRolePermission updatePermisRole = sysUserRoleService.getByPermiAndRoleId(permisId, roleId);
            if (updatePermisRole == null) {
                SysRolePermission sysUserRole = new SysRolePermission();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setPermisId(permisId);
                sysUserRole.setStatus(Constants.STATUS_ENABLED);
                sysUserRole.setCreatorId(loginUserId);
                sysUserRole.setUpdateId(loginUserId);
                sysUserRoleService.insert(sysUserRole);
            } else if (updatePermisRole.getStatus() == Constants.STATUS_DISABLED) {
                updatePermisRole.setStatus(Constants.STATUS_ENABLED);
                updatePermisRole.setUpdateId(loginUserId);
                sysUserRoleService.update(updatePermisRole);
            }
        }
        fillReturnJson(resp, true, "操作成功");
    }

    /** 角色移除权限
     * 
     * @author:liangyanjun
     * @time:2016年10月12日下午2:25:04
     * @param roleId
     * @param userIds
     * @param req
     * @param resp */
    @RequestMapping("/removeSysRolePermission.do")
    public void removeSysRolePermission(String roleId, String permisStrs, HttpServletRequest req, HttpServletResponse resp) {
        if (StringUtil.isBlank(roleId, permisStrs)) {
            fillReturnJson(resp, false, "参数不合法，请重新操作");
            return;
        }
        String[] permisIds = permisStrs.split(",");
        SysUser loginUser = getLoginUser(req);
        String loginUserId = loginUser.getId();
        for (String permisId : permisIds) {
            SysRolePermission updatePermisRole = sysUserRoleService.getByPermiAndRoleId(permisId, roleId);
            if (updatePermisRole != null && updatePermisRole.getStatus() == Constants.STATUS_ENABLED) {
                updatePermisRole.setStatus(Constants.STATUS_DISABLED);
                updatePermisRole.setUpdateId(loginUserId);
                sysUserRoleService.update(updatePermisRole);
            }
        }
        fillReturnJson(resp, true, "操作成功");
    }

}
