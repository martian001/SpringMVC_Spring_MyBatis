package com.et.mapper.system;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.mybatis.mapper.BaseMapper;
import com.et.base.BaseDao;
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
@MapperScan
public interface SysPermissionMapper<T, PK> extends BaseMapper<T, PK>,BaseDao<T>{

    List<SysPermission> findRolePermissionListPage(SysPermission query);

    int getRolePermissionListTotal(SysPermission query);

    List<SysPermission> findNotGrantRolePermissionListPage(SysPermission query);

    int getNotGrantRolePermissionListTotal(SysPermission query);

    void updateByMenuId(SysPermission sysPermission);

}
