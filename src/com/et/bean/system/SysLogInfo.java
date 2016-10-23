package com.et.bean.system;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-23 11:32:33 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统操作日志<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class SysLogInfo extends BaseBean {
   private String id; //
   private Integer type; // 操作类型,1表示添加,2表示删除,3表示修改,4表示登录,5表示登出,6表示下载文件,7表示上传文件
   private Integer moduel; // 日志模块：系统模块=1
   private String content; // 操作内容
   private String ipAddress; // IP地址
   private String operatorId; // 操作人ID
   private String operatorName; //
   private String creatorDateEnd; //

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public Integer getModuel() {
      return moduel;
   }

   public void setModuel(Integer moduel) {
      this.moduel = moduel;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getIpAddress() {
      return ipAddress;
   }

   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public String getOperatorId() {
      return operatorId;
   }

   public void setOperatorId(String operatorId) {
      this.operatorId = operatorId;
   }

   public String getOperatorName() {
      return operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getCreatorDateEnd() {
      return creatorDateEnd;
   }

   public void setCreatorDateEnd(String creatorDateEnd) {
      this.creatorDateEnd = creatorDateEnd;
   }

   @Override
   public String toString() {
      return "SysLogInfo [id=" + id + ", type=" + type + ", moduel=" + moduel + ", content=" + content + ", ipAddress=" + ipAddress + ", operatorId=" + operatorId
            + ", operatorName=" + operatorName + ", creatorDateEnd=" + creatorDateEnd + "]";
   }

}
