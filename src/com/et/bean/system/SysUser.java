package com.et.bean.system;

import java.util.List;

import com.et.base.BaseBean;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-06-23 14:46:30 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统用户<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class SysUser extends BaseBean {
   private String id;
   private String userName;
   private String realName;
   private String memberId;
   private int status;
   private String jobTitle;
   private String mail;
   private String pwd;
   private String photoUrl;
   private String token;
   private String personalQQ;
   private String enterpriseQQ;
   private String phone;
   private String workPhone;
   private String extension;
   private String superiorId;
   private String superiorName;
   private String deptName;
   private String deviceToken;
   private String remark;
   private List<SysRole> roleList;
   
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getRealName() {
      return realName;
   }

   public void setRealName(String realName) {
      this.realName = realName;
   }

   public String getMemberId() {
      return memberId;
   }

   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public String getJobTitle() {
      return jobTitle;
   }

   public void setJobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
   }

   public String getMail() {
      return mail;
   }

   public void setMail(String mail) {
      this.mail = mail;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   public String getPhotoUrl() {
      return photoUrl;
   }

   public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getPersonalQQ() {
      return personalQQ;
   }

   public void setPersonalQQ(String personalQQ) {
      this.personalQQ = personalQQ;
   }

   public String getEnterpriseQQ() {
      return enterpriseQQ;
   }

   public void setEnterpriseQQ(String enterpriseQQ) {
      this.enterpriseQQ = enterpriseQQ;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public void setWorkPhone(String workPhone) {
      this.workPhone = workPhone;
   }

   public String getExtension() {
      return extension;
   }

   public void setExtension(String extension) {
      this.extension = extension;
   }

   public String getSuperiorId() {
      return superiorId;
   }

   public void setSuperiorId(String superiorId) {
      this.superiorId = superiorId;
   }

   public String getSuperiorName() {
      return superiorName;
   }

   public void setSuperiorName(String superiorName) {
      this.superiorName = superiorName;
   }

   public String getDeptName() {
      return deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeviceToken() {
      return deviceToken;
   }

   public void setDeviceToken(String deviceToken) {
      this.deviceToken = deviceToken;
   }

   public String getRemark() {
    return remark;
}

public void setRemark(String remark) {
    this.remark = remark;
}

@Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
      result = prime * result + ((deviceToken == null) ? 0 : deviceToken.hashCode());
      result = prime * result + ((enterpriseQQ == null) ? 0 : enterpriseQQ.hashCode());
      result = prime * result + ((extension == null) ? 0 : extension.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
      result = prime * result + ((mail == null) ? 0 : mail.hashCode());
      result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
      result = prime * result + ((personalQQ == null) ? 0 : personalQQ.hashCode());
      result = prime * result + ((phone == null) ? 0 : phone.hashCode());
      result = prime * result + ((photoUrl == null) ? 0 : photoUrl.hashCode());
      result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
      result = prime * result + ((realName == null) ? 0 : realName.hashCode());
      result = prime * result + status;
      result = prime * result + ((superiorId == null) ? 0 : superiorId.hashCode());
      result = prime * result + ((superiorName == null) ? 0 : superiorName.hashCode());
      result = prime * result + ((token == null) ? 0 : token.hashCode());
      result = prime * result + ((userName == null) ? 0 : userName.hashCode());
      result = prime * result + ((workPhone == null) ? 0 : workPhone.hashCode());
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
      SysUser other = (SysUser) obj;
      if (deptName == null) {
         if (other.deptName != null)
            return false;
      } else if (!deptName.equals(other.deptName))
         return false;
      if (deviceToken == null) {
         if (other.deviceToken != null)
            return false;
      } else if (!deviceToken.equals(other.deviceToken))
         return false;
      if (enterpriseQQ == null) {
         if (other.enterpriseQQ != null)
            return false;
      } else if (!enterpriseQQ.equals(other.enterpriseQQ))
         return false;
      if (extension == null) {
         if (other.extension != null)
            return false;
      } else if (!extension.equals(other.extension))
         return false;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      if (jobTitle == null) {
         if (other.jobTitle != null)
            return false;
      } else if (!jobTitle.equals(other.jobTitle))
         return false;
      if (mail == null) {
         if (other.mail != null)
            return false;
      } else if (!mail.equals(other.mail))
         return false;
      if (memberId == null) {
         if (other.memberId != null)
            return false;
      } else if (!memberId.equals(other.memberId))
         return false;
      if (personalQQ == null) {
         if (other.personalQQ != null)
            return false;
      } else if (!personalQQ.equals(other.personalQQ))
         return false;
      if (phone == null) {
         if (other.phone != null)
            return false;
      } else if (!phone.equals(other.phone))
         return false;
      if (photoUrl == null) {
         if (other.photoUrl != null)
            return false;
      } else if (!photoUrl.equals(other.photoUrl))
         return false;
      if (pwd == null) {
         if (other.pwd != null)
            return false;
      } else if (!pwd.equals(other.pwd))
         return false;
      if (realName == null) {
         if (other.realName != null)
            return false;
      } else if (!realName.equals(other.realName))
         return false;
      if (status != other.status)
         return false;
      if (superiorId != other.superiorId)
         return false;
      if (superiorName == null) {
         if (other.superiorName != null)
            return false;
      } else if (!superiorName.equals(other.superiorName))
         return false;
      if (token == null) {
         if (other.token != null)
            return false;
      } else if (!token.equals(other.token))
         return false;
      if (userName == null) {
         if (other.userName != null)
            return false;
      } else if (!userName.equals(other.userName))
         return false;
      if (workPhone == null) {
         if (other.workPhone != null)
            return false;
      } else if (!workPhone.equals(other.workPhone))
         return false;
      return true;
   }

    @Override
    public String toString() {
        return "SysUser [id=" + id + ", userName=" + userName + ", realName=" + realName + ", memberId=" + memberId + ", status=" + status
                + ", jobTitle=" + jobTitle + ", mail=" + mail + ", pwd=" + pwd + ", photoUrl=" + photoUrl + ", token=" + token
                + ", personalQQ=" + personalQQ + ", enterpriseQQ=" + enterpriseQQ + ", phone=" + phone + ", workPhone=" + workPhone
                + ", extension=" + extension + ", superiorId=" + superiorId + ", superiorName=" + superiorName + ", deptName=" + deptName
                + ", deviceToken=" + deviceToken + ", remark=" + remark + "]";
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
  

}
