package com.et.mapper.system;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.mybatis.mapper.BaseMapper;
import com.et.base.BaseDao;
import com.et.bean.system.SysUserRole;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-12 11:44:56 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 用户角色关系表<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
@MapperScan
public interface SysUserRoleMapper<T, PK> extends BaseMapper<T, PK>, BaseDao<T> {

    SysUserRole getByUserAndRoleId(@Param(value = "userId") String userId, @Param(value = "roleId") String roleId);

}
