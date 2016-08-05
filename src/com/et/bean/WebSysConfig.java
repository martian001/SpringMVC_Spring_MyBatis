package com.et.bean;

public class WebSysConfig {
   // //////////////WEB应用配置////////////
   // 文件根目录
   private String uploadFileRoot;
   private int maxFileSize;
   private int maxRequestSize;
   private String fileType;

   public String[] getFileType() {
      return fileType.split(",");
   }

   public void setFileType(String fileType) {
      this.fileType = fileType;
   }

   public int getMaxFileSize() {
      return maxFileSize;
   }

   public void setMaxFileSize(int maxFileSize) {
      this.maxFileSize = maxFileSize;
   }

   public int getMaxRequestSize() {
      return maxRequestSize;
   }

   public void setMaxRequestSize(int maxRequestSize) {
      this.maxRequestSize = maxRequestSize;
   }

   public String getUploadFileRoot() {
      return uploadFileRoot;
   }

   public void setUploadFileRoot(String uploadFileRoot) {
      this.uploadFileRoot = uploadFileRoot;
   }
}
