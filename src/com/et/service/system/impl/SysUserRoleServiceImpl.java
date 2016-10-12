package com.et.service.system.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.system.SysUserRole;
import com.et.mapper.system.SysUserRoleMapper;
import com.et.service.system.SysUserRoleService;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-12 11:44:56 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 用户角色关系表<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @PostConstruct
    public void init() {
        System.out.println("初始化");
        setBaseDao(sysUserRoleMapper);
    }

    /** @author:liangyanjun
     * @time:2016年10月12日下午2:14:36 */
    @Override
    public SysUserRole getByUserAndRoleId(String userId, String roleId) {
        return sysUserRoleMapper.getByUserAndRoleId(userId,roleId);
    }
}
