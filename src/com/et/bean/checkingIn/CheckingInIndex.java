package com.et.bean.checkingIn;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午9:29:26 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class CheckingInIndex extends BaseBean {
   private String Id;
   private String userId;
   private Double hour;
   private Double overTime;
   private String startDate;
   private String endDate;
   private String week;
   private int month;
   private int status;
   private String realName;
   private String deptName;

   public String getRealName() {
      return realName;
   }

   public void setRealName(String realName) {
      this.realName = realName;
   }

   public String getDeptName() {
      return deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getId() {
      return Id;
   }

   public void setId(String id) {
      Id = id;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public Double getHour() {
      return hour;
   }

   public void setHour(Double hour) {
      this.hour = hour;
   }

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public int getMonth() {
      return month;
   }

   public void setMonth(int month) {
      this.month = month;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   @Override
   public String toString() {
      return "CheckingInIndex [Id=" + Id + ", userId=" + userId + ", hour=" + hour + ", startDate=" + startDate + ", endDate=" + endDate + ", month=" + month
            + ", status=" + status + ", realName=" + realName + ", deptName=" + deptName + "]";
   }

   public Double getOverTime() {
      return overTime;
   }

   public void setOverTime(Double overTime) {
      this.overTime = overTime;
   }

   public String getWeek() {
      return week;
   }

   public void setWeek(String week) {
      this.week = week;
   }

}
