package com.et.service.system;

import java.util.List;

import com.et.base.BaseService;
import com.et.bean.system.SysUser;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午9:57:47 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */

public interface SysUserService extends BaseService<SysUser> {

   /**
    *@author:liangyanjun
    *@time:2016年6月23日下午4:28:32
    *@param userName
    *@return
    */
   SysUser getSysUserByPhone(String phone);

   /**
    *@author:liangyanjun
    *@time:2016年6月23日下午5:12:55
    *@param userName
    *@return
    */
   SysUser getSysUserByUserName(String userName);

   boolean checkUserNameIsExist(String userName);

   boolean checkPhoneIsExist(String phone);

    List<SysUser> findRoleUserListPage(SysUser query);

    int getRoleUserListTotal(SysUser query);

}
