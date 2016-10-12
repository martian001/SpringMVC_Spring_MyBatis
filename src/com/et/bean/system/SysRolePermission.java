package com.et.bean.system;

import com.et.base.BaseBean;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-12 15:19:50 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 角色-权限关系表<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
public class SysRolePermission extends BaseBean {
    private String id; //
    private String roleId; //
    private String permisId; //
    private Integer status; //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermisId() {
        return permisId;
    }

    public void setPermisId(String permisId) {
        this.permisId = permisId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysRolePermission [id=" + id + ", roleId=" + roleId + ", permisId=" + permisId + ", status=" + status + "]";
    }

}
