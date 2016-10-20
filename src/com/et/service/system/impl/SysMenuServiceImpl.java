package com.et.service.system.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.system.SysMenu;
import com.et.bean.system.SysPermission;
import com.et.bean.system.SysRolePermission;
import com.et.mapper.system.SysMenuMapper;
import com.et.mapper.system.SysPermissionMapper;
import com.et.mapper.system.SysRolePermissionMapper;
import com.et.service.system.SysMenuService;
import com.et.util.Constants;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-06-22 16:07:35 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统菜单<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @PostConstruct
    public void init() {
        System.out.println("初始化");
        setBaseDao(sysMenuMapper);
    }

    @Override
    public int insert(SysMenu sysMenu) {
        super.insert(sysMenu);
        sysPermissionMapper.insert(convert(sysMenu));
        return 1;
    }

    @Override
    public void deleteById(String id) {
        SysPermission parameter = new SysPermission();
        parameter.setMenuId(id);
        List<SysPermission> list = sysPermissionMapper.query(parameter);
        if (list!=null&&!list.isEmpty()) {
            SysPermission delPermission = list.get(0);
            String permisId = delPermission.getId();
            //删除角色和权限关联
            SysRolePermission sysRolePermissionQuery=new SysRolePermission();
            sysRolePermissionQuery.setPermisId(permisId);
            List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.query(sysRolePermissionQuery);
            if (sysRolePermissions!=null&&!sysRolePermissions.isEmpty()) {
                for (SysRolePermission sysRolePermission : sysRolePermissions) {
                    sysRolePermissionMapper.deleteById(sysRolePermission.getId());
                }
            }
            //删除权限
            sysPermissionMapper.deleteById(permisId);
        }
        //删除菜单
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        for (String id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void update(SysMenu sysMenu) {
        // 修改权限信息 （根据菜单ID）
        sysPermissionMapper.updateByMenuId(convert(sysMenu));
        super.update(sysMenu);
    }

    private SysPermission convert(SysMenu sysMenu) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setPermisCode("MNU");
        sysPermission.setStatus(Constants.STATUS_ENABLED);
        sysPermission.setPermisType(Constants.PERMIS_TYPE_2);
        sysPermission.setPermisDesc(sysMenu.getMenuName());
        sysPermission.setPermisName(sysMenu.getMenuName());
        sysPermission.setMenuId(sysMenu.getId());
        return sysPermission;
    }
}
