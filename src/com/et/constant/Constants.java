package com.et.constant;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年6月23日下午3:47:31 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 常用常量接口<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public interface Constants {
   public static final String LOGIN_USER = "login_user";
   public static final String SEPARATOR = "/";
   public static final int STATUS_ENABLED = 1;// 有效状态
   public static final int STATUS_DISABLED = 2;// 无效效状态

   public static final String PERMIS_TYPE_1 = "1";// 权限类型（功能权限=1/菜单权限=2）
   public static final String PERMIS_TYPE_2 = "2";// 权限类型（功能权限=1/菜单权限=2）

   public static final String USERNAME_IS_NULL = "用户名为空!";
   public static final String LOGIN_IS_EXIST = "该用户已登录!";
   public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";
   public static final String UNKNOWN_ACCOUNT_EXCEPTION = "账号错误!";
   public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";
   public static final String UNKNOWN_PWD_ACCOUNT_EXCEPTION = "账号或密码错误!";
   public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";
   public static final String DISABLED_ACCOUNT_EXCEPTION = "账号已失效，请与系统管理员联系!";
   public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";
   public static final String AUTHENTICATION_EXCEPTION = "您没有授权!";
   public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";
}
