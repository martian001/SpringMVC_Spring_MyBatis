package com.et.service.system.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.system.SysPermission;
import com.et.mapper.system.SysPermissionMapper;
import com.et.service.system.SysPermissionService;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-10 11:15:18 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统功能权限<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @PostConstruct
    public void init() {
        System.out.println("初始化");
        setBaseDao(sysPermissionMapper);
    }

    /** @author:liangyanjun
     * @time:2016年10月12日下午3:26:04 */
    @Override
    public List<SysPermission> findRolePermissionListPage(SysPermission query) {
        return sysPermissionMapper.findRolePermissionListPage(query);
    }

    /** @author:liangyanjun
     * @time:2016年10月12日下午3:26:04 */
    @Override
    public int getRolePermissionListTotal(SysPermission query) {
        return sysPermissionMapper.getRolePermissionListTotal(query);
    }

    /**
     *@author:liangyanjun
     *@time:2016年10月12日下午3:53:51
     *
     */
    @Override
    public List<SysPermission> findNotGrantRolePermissionListPage(SysPermission query) {
        return sysPermissionMapper.findNotGrantRolePermissionListPage(query);
    }

    /**
     *@author:liangyanjun
     *@time:2016年10月12日下午3:53:51
     *
     */
    @Override
    public int getNotGrantRolePermissionListTotal(SysPermission query) {
        return sysPermissionMapper.getNotGrantRolePermissionListTotal(query);
    }
}
