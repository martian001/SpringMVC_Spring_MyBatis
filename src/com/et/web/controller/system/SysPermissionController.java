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
import com.et.bean.system.SysPermission;
import com.et.bean.system.SysUser;
import com.et.service.system.SysPermissionService;
import com.et.util.ExceptionUtil;
import com.et.util.StringUtil;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-10 11:15:18 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统功能权限<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Controller
@RequestMapping("/sysPermissionController")
public class SysPermissionController extends BaseController {
    private String path = "system";
    @Resource
    private SysPermissionService sysPermissionService;
    private Logger logger = LoggerFactory.getLogger(SysPermissionController.class);

    @ExceptionHandler
    public String exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error(ExceptionUtil.getExceptionMessage(e), e);
        fillReturnJson(response, false, "出现未知异常,请与系统管理员联系!");
        return null;
    }

    @RequestMapping("/toSysPermissionList.do")
    public String to_list() {
        return path + "/sysPermissionList";
    }

    @RequestMapping("/list.do")
    public void list(SysPermission query, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysPermission> sysPermissionList = sysPermissionService.findPage(query);
        int total = sysPermissionService.getTotal(query);
        // 输出
        resultMap.put("rows", sysPermissionList);
        resultMap.put("total", total);
        outputJson(resultMap, response);
    }

    /** 新增或修改角色
     * 
     * @author:liangyanjun
     * @time:2016年10月8日下午6:09:37
     * @param sysPermission
     * @param req
     * @param resp */
    @RequestMapping("/addOrUpdate.do")
    public void addOrUpdate(SysPermission sysPermission, HttpServletRequest req, HttpServletResponse resp) {
        String id = sysPermission.getId();
        String permisName = sysPermission.getPermisName();
        String permisCode = sysPermission.getPermisCode();
        String permisDesc = sysPermission.getPermisDesc();
        Integer status = sysPermission.getStatus();
        String permisType = sysPermission.getPermisType();
        if (StringUtil.isBlank(permisName, permisCode, permisType) || status <= 0) {
            fillReturnJson(resp, false, "参数不合法，请输入必填项");
            return;
        }
        SysUser loginUser = getLoginUser(req);
        String loginUserId = loginUser.getId();
        sysPermission.setUpdateId(loginUserId);

        if (StringUtil.isBlank(id)) {//新增
            sysPermission.setCreatorId(loginUserId);
            sysPermissionService.insert(sysPermission);
        } else {//修改
            SysPermission updatesysPermission = sysPermissionService.getById(id);
            updatesysPermission.setPermisName(permisName);
            updatesysPermission.setPermisCode(permisCode);
            updatesysPermission.setPermisDesc(permisDesc);
            updatesysPermission.setStatus(status);
            updatesysPermission.setPermisType(permisType);
            sysPermissionService.update(updatesysPermission);
        }
        fillReturnJson(resp, true, "提交成功");
    }
}
