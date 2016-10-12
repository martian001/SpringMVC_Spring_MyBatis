package com.et.service.system.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.system.SysRolePermission;
import com.et.mapper.system.SysRolePermissionMapper;
import com.et.service.system.SysRolePermissionService;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-12 15:19:50 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 角色-权限关系表<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements SysRolePermissionService {
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @PostConstruct
    public void init() {
        System.out.println("初始化");
        setBaseDao(sysRolePermissionMapper);
    }

    /** @author:liangyanjun
     * @time:2016年10月12日下午4:11:42 */
    @Override
    public SysRolePermission getByPermiAndRoleId(String permisId, String roleId) {
        return sysRolePermissionMapper.getByPermiAndRoleId(permisId,roleId);
    }
}
