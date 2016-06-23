package com.et.bean.system;

import java.sql.Date;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年4月19日下午4:43:38 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class Mail extends BaseBean {
   private String id;
   private String title;
   private String content;
   private String sendId;
   private Date sendDate;
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getSendId() {
      return sendId;
   }
   public void setSendId(String sendId) {
      this.sendId = sendId;
   }
   public Date getSendDate() {
      return sendDate;
   }
   public void setSendDate(Date sendDate) {
      this.sendDate = sendDate;
   }
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((content == null) ? 0 : content.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((sendDate == null) ? 0 : sendDate.hashCode());
      result = prime * result + ((sendId == null) ? 0 : sendId.hashCode());
      result = prime * result + ((title == null) ? 0 : title.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Mail other = (Mail) obj;
      if (content == null) {
         if (other.content != null)
            return false;
      } else if (!content.equals(other.content))
         return false;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      if (sendDate == null) {
         if (other.sendDate != null)
            return false;
      } else if (!sendDate.equals(other.sendDate))
         return false;
      if (sendId == null) {
         if (other.sendId != null)
            return false;
      } else if (!sendId.equals(other.sendId))
         return false;
      if (title == null) {
         if (other.title != null)
            return false;
      } else if (!title.equals(other.title))
         return false;
      return true;
   }
   @Override
   public String toString() {
      return "Mail [id=" + id + ", title=" + title + ", content=" + content + ", sendId=" + sendId + ", sendDate=" + sendDate + "]";
   }

   

}
