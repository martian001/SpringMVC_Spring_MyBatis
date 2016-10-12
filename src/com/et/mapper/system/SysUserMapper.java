package com.et.mapper.system;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.mybatis.mapper.BaseMapper;
import com.et.base.BaseDao;
import com.et.bean.system.SysUser;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午10:01:36 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@MapperScan("sysUserMapper")
public interface SysUserMapper<T, PK> extends BaseMapper<T, PK>,BaseDao<T>{

   /**
    *@author:liangyanjun
    *@time:2016年6月23日下午4:29:07
    *@param phone
    *@return
    */
   SysUser getSysUserByPhone(String phone);

   /**
    *@author:liangyanjun
    *@time:2016年6月23日下午5:13:55
    *@param userName
    *@return
    */
   SysUser getSysUserByUserName(String userName);

    List<SysUser> findRoleUserListPage(SysUser query);

    int getRoleUserListTotal(SysUser query);

}
