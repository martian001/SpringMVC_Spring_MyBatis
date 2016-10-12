package com.et.service.system.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.system.SysUser;
import com.et.mapper.system.SysUserMapper;
import com.et.service.system.SysUserService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午9:58:18 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Service("sysUserServiceImpl")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
   @Resource
   private SysUserMapper sysUserMapper;


   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(sysUserMapper);
   }


   @Override
   public SysUser getSysUserByPhone(String phone) {
      return sysUserMapper.getSysUserByPhone(phone);
   }


   @Override
   public SysUser getSysUserByUserName(String userName) {
      return sysUserMapper.getSysUserByUserName(userName);
   }


    /** @author:liangyanjun
     * @time:2016年10月8日上午10:04:31 */
    @Override
    public boolean checkUserNameIsExist(String userName) {
        SysUser sysUser = sysUserMapper.getSysUserByUserName(userName);
        return sysUser != null;
    }

    /** @author:liangyanjun
     * @time:2016年10月8日上午10:04:31 */
    @Override
    public boolean checkPhoneIsExist(String phone) {
        SysUser sysUser = sysUserMapper.getSysUserByPhone(phone);
        return sysUser != null;
    }


    /**
     *@author:liangyanjun
     *@time:2016年10月12日上午11:32:19
     *
     */
    @Override
    public List<SysUser> findRoleUserListPage(SysUser query) {
        return sysUserMapper.findRoleUserListPage(query);
    }


    /**
     *@author:liangyanjun
     *@time:2016年10月12日上午11:32:19
     *
     */
    @Override
    public int getRoleUserListTotal(SysUser query) {
        return sysUserMapper.getRoleUserListTotal(query);
    }
}
