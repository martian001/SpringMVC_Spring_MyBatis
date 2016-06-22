package com.et.bean.system;

import java.util.List;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-06-22 16:07:35 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统菜单<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class SysMenu extends BaseBean {
   private String id;
   private String parentId;
   private String menuName;
   private String iconCls;
   private String menuUrl;
   private int status;
   private int menuIndex;
   private List<SysMenu> childrenList;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getParentId() {
      return parentId;
   }

   public void setParentId(String parentId) {
      this.parentId = parentId;
   }

   public String getMenuName() {
      return menuName;
   }

   public void setMenuName(String menuName) {
      this.menuName = menuName;
   }

   public String getIconCls() {
      return iconCls;
   }

   public void setIconCls(String iconCls) {
      this.iconCls = iconCls;
   }

   public String getMenuUrl() {
      return menuUrl;
   }

   public void setMenuUrl(String menuUrl) {
      this.menuUrl = menuUrl;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public int getMenuIndex() {
      return menuIndex;
   }

   public void setMenuIndex(int menuIndex) {
      this.menuIndex = menuIndex;
   }

   public List<SysMenu> getChildrenList() {
      return childrenList;
   }

   public void setChildrenList(List<SysMenu> childrenList) {
      this.childrenList = childrenList;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((iconCls == null) ? 0 : iconCls.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + menuIndex;
      result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
      result = prime * result + ((menuUrl == null) ? 0 : menuUrl.hashCode());
      result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
      result = prime * result + status;
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
      SysMenu other = (SysMenu) obj;
      if (iconCls == null) {
         if (other.iconCls != null)
            return false;
      } else if (!iconCls.equals(other.iconCls))
         return false;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      if (menuIndex != other.menuIndex)
         return false;
      if (menuName == null) {
         if (other.menuName != null)
            return false;
      } else if (!menuName.equals(other.menuName))
         return false;
      if (menuUrl == null) {
         if (other.menuUrl != null)
            return false;
      } else if (!menuUrl.equals(other.menuUrl))
         return false;
      if (parentId == null) {
         if (other.parentId != null)
            return false;
      } else if (!parentId.equals(other.parentId))
         return false;
      if (status != other.status)
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "SysMenu [id=" + id + ", parentId=" + parentId + ", menuName=" + menuName + ", iconCls=" + iconCls + ", menuUrl=" + menuUrl + ", status=" + status
            + ", menuIndex=" + menuIndex + ", childrenList=" + childrenList + "]";
   }

}
