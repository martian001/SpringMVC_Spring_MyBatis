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
   private int page;
   private int rows;//一页显示条数
   private int limit;//一页显示条数
   private int offset;//开始索引
   private String creatorId;
   private String creatorDate;
   private String updateId;
   private String updateDate;

   public int getRows() {
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
      if (offset>0) {
         limit = offset + limit;
      }
      if (page > 0) {
         offset = (page - 1) * rows;
         limit = page * rows;
      }
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
