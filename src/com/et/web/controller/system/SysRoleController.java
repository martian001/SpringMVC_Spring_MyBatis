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
import com.et.bean.system.SysRole;
import com.et.bean.system.SysUser;
import com.et.service.system.SysRoleService;
import com.et.util.ExceptionUtil;
import com.et.util.StringUtil;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-08 16:21:06 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 角色<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Controller
@RequestMapping("/sysRoleController")
public class SysRoleController extends BaseController {
    private String path = "system";
    @Resource
    private SysRoleService sysRoleService;
    private Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    /** 全局异常处理
     *
     * @author:liangyanjun
     * @time:2016年10月8日下午6:11:54
     * @param request
     * @param response
     * @param e
     * @return */
    @ExceptionHandler
    public String exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error(ExceptionUtil.getExceptionMessage(e), e);
        fillReturnJson(response, false, "出现未知异常,请与系统管理员联系!");
        return null;
    }

    @RequestMapping("/toList.do")
    public String to_list() {
        return path + "/sysRoleList";
    }

    @RequestMapping("/list.do")
    public void list(SysRole query, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRole> sysRoleList = sysRoleService.findPage(query);
        int total = sysRoleService.getTotal(query);
        // 输出
        resultMap.put("rows", sysRoleList);
        resultMap.put("total", total);
        outputJson(resultMap, response);
    }

    @RequestMapping("/delete.do")
    public String delete(String id) {
        sysRoleService.deleteById(id);
        return "forward:to_sysRolelist.do";
    }

    /** 新增或修改角色
     * 
     * @author:liangyanjun
     * @time:2016年10月8日下午6:09:37
     * @param sysRole
     * @param req
     * @param resp */
    @RequestMapping("/addOrUpdate.do")
    public void addOrUpdate(SysRole sysRole, HttpServletRequest req, HttpServletResponse resp) {
        String id = sysRole.getId();
        String roleName = sysRole.getRoleName();
        String roleCode = sysRole.getRoleCode();
        String roleDesc = sysRole.getRoleDesc();
        Integer status = sysRole.getStatus();
        if (StringUtil.isBlank(roleName, roleCode) || status <= 0) {
            fillReturnJson(resp, false, "参数不合法，请输入必填项");
            return;
        }
        SysUser loginUser = getLoginUser(req);
        String loginUserId = loginUser.getId();
        sysRole.setUpdateId(loginUserId);

        if (StringUtil.isBlank(id)) {//新增
            sysRole.setCreatorId(loginUserId);
            sysRoleService.insert(sysRole);
        } else {//修改
            SysRole updateSysRole = sysRoleService.getById(id);
            updateSysRole.setRoleName(roleName);
            updateSysRole.setRoleCode(roleCode);
            updateSysRole.setRoleDesc(roleDesc);
            updateSysRole.setStatus(status);
            sysRoleService.update(updateSysRole);
        }
        fillReturnJson(resp, true, "提交成功");
    }
}
