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
   private int first = 1;

   public int getRows() {
      return rows;
   }

   public void setRows(int rows) {
      this.rows = rows;
   }

   public int getFirst() {
      return first;
   }

   public void setFirst(int first) {
      this.first = first;
   }

   public int getPage() {
      return page;
   }

   // (#{page}-1)*#{row},#{row}
   public void setPage(int page) {
      first = (page - 1) * rows;
      this.page = page;
   }

   @Override
   public String toString() {
      return "BaseBean [page=" + page + ", row=" + rows + ", first=" + first + "]";
   }

}
