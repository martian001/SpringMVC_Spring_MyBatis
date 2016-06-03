package com.et.bean.system;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午9:30:37 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class SysUser extends BaseBean{
   private Integer id;
   private String userName;
   private String realName;
   private String password;
   private String deptName;
   private String memberId;
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getRealName() {
      return realName;
   }
   public void setRealName(String realName) {
      this.realName = realName;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getDeptName() {
      return deptName;
   }
   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }
   public String getMemberId() {
      return memberId;
   }
   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }
   @Override
   public String toString() {
      return "SysUser [id=" + id + ", userName=" + userName + ", realName=" + realName + ", password=" + password + ", deptName=" + deptName + ", memberId=" + memberId
            + "]";
   }
   

  

}
