package com.et.base;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年4月20日下午4:33:28 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class BaseBean {
   private int page = 1;
   private int rows = 10;
   private int limit;
   private int offset;
   private String creatorId;
   private String creatorDate;
   private String updateId;
   private String updateDate;

   public int getRows() {
      rows=getOffset()+limit;
      return rows;
   }

   public void setRows(int rows) {
      this.rows = rows;
   }

   public int getPage() {
      return page;
   }

   public void setPage(int page) {
      this.page = page;
   }

   public String getCreatorId() {
      return creatorId;
   }

   public void setCreatorId(String creatorId) {
      this.creatorId = creatorId;
   }

   public String getCreatorDate() {
      return creatorDate;
   }

   public void setCreatorDate(String creatorDate) {
      this.creatorDate = creatorDate;
   }

   public String getUpdateId() {
      return updateId;
   }

   public void setUpdateId(String updateId) {
      this.updateId = updateId;
   }

   public String getUpdateDate() {
      return updateDate;
   }

   public void setUpdateDate(String updateDate) {
      this.updateDate = updateDate;
   }

   public int getLimit() {
      return limit;
   }

   public void setLimit(int limit) {
      this.limit = limit;
   }

   public int getOffset() {
      return offset;
   }

   public void setOffset(int offset) {
      this.offset = offset;
   }

   @Override
   public String toString() {
      return "BaseBean [page=" + page + ", rows=" + rows + ", limit=" + limit + ", offset=" + offset + ", creatorId=" + creatorId + ", creatorDate=" + creatorDate
            + ", updateId=" + updateId + ", updateDate=" + updateDate + "]";
   }



}
