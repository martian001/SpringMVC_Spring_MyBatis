package com.et.service.system;

import java.util.List;

import com.et.base.BaseService;
import com.et.bean.system.SysPermission;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-10 11:15:18 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统功能权限<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */

public interface SysPermissionService extends BaseService<SysPermission> {

    List<SysPermission> findRolePermissionListPage(SysPermission query);

    int getRolePermissionListTotal(SysPermission query);
    
    List<SysPermission> findNotGrantRolePermissionListPage(SysPermission query);
    
    int getNotGrantRolePermissionListTotal(SysPermission query);

}
