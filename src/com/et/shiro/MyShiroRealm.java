package com.et.shiro;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.et.bean.system.SysUser;
import com.et.constant.Constants;
import com.et.service.system.SysUserService;
import com.et.util.StringUtil;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年9月29日下午5:24:36 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    /**
     * 为当限前登录的用户授予角色和权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
        String id = shiroUser.getId();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        Set<String> roleCodes = sysUserService.getRoleCodes(id);
        System.out.println("roleCodes:"+roleCodes);
        authorizationInfo.setRoles(roleCodes);
        Set<String> permissionCodes = sysUserService.getPermissionCodes(id);
        System.out.println("permissionCodes:"+permissionCodes);
        authorizationInfo.setStringPermissions(permissionCodes);
        return authorizationInfo;
    }

    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;

        // 通过表单接收的用户名
        String userName = token.getUsername();
        if (StringUtil.isBlank(userName)) {
            return null;
        }
        doCaptchaValidate(token);
        try {
            // 从数据库中取出user
            SysUser sysuser = sysUserService.getSysUserByUserName(userName);
            if (sysuser == null) {
                return null;
            }
            //检查用户状态是否有效
//            if (sysuser.getStatus() == Constants.STATUS_DISABLED) {
//                throw new DisabledAccountException("账户已禁用!");
//            }
            Subject subject = SecurityUtils.getSubject();
            ShiroUser shiroUser = new ShiroUser(sysuser.getId(), sysuser.getUserName(), sysuser.getRealName(), null);
            subject.getSession().setAttribute(Constants.LOGIN_USER, sysuser);
            return new SimpleAuthenticationInfo(shiroUser, sysuser.getPwd(), getName());
        } catch (Exception e) {
            throw new AuthenticationException("服务器请求失败!");
        }
    }

    /** 更新用户授权信息缓存. */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /** 清除所有用户授权信息缓存. */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    // 验证码校验
    protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) {
        /* HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
         if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
             throw new IncorrectCaptchaException("验证码错误！");
         }*/
        return true;
    }

}
