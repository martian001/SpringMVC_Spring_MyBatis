package com.et.bean.checkingIn;

import java.util.Date;

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
public class CheckingIn {
   private Integer Id;
   private Integer userId;
   private Double hour;
   private Date startDate;
   private Date endDate;
   private int month;
   private int status;
   private int overtime;
   public int getOvertime() {
      return overtime;
   }
   public void setOvertime(int overtime) {
      this.overtime = overtime;
   }
   public double getOverHour() {
      return overHour;
   }
   public void setOverHour(double overHour) {
      this.overHour = overHour;
   }
   private double overHour;
   public Integer getId() {
      return Id;
   }
   public void setId(Integer id) {
      Id = id;
   }
   public Integer getUserId() {
      return userId;
   }
   public void setUserId(Integer userId) {
      this.userId = userId;
   }
   public Double getHour() {
      return hour;
   }
   public void setHour(Double hour) {
      this.hour = hour;
   }
   public Date getStartDate() {
      return startDate;
   }
   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }
   public Date getEndDate() {
      return endDate;
   }
   public void setEndDate(Date endDate) {
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
      return "CheckingIn [Id=" + Id + ", userId=" + userId + ", hour=" + hour + ", startDate=" + startDate + ", endDate=" + endDate + ", month=" + month + ", status="
            + status + ", overtime=" + overtime + ", overHour=" + overHour + "]";
   }
   


}
