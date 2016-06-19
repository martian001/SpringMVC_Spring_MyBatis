package com.et.bean.checkingIn;

import java.util.Date;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午9:37:59 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class CheckingInRecord extends BaseBean{
   private String id;
   private String userId;
   private Date checkingInDate;
   private Integer month;
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public Date getCheckingInDate() {
      return checkingInDate;
   }
   public void setCheckingInDate(Date checkingInDate) {
      this.checkingInDate = checkingInDate;
   }
   public Integer getMonth() {
      return month;
   }
   public void setMonth(Integer month) {
      this.month = month;
   }
   @Override
   public String toString() {
      return "CheckingInRecord [id=" + id + ", userId=" + userId + ", checkingInDate=" + checkingInDate + ", month=" + month + "]";
   }

}

