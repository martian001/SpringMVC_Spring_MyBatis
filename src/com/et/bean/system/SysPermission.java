package com.et.bean.system;

import java.util.List;

import com.et.base.BaseBean;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-10-10 11:15:18 <br>
 * ★☆ @version： 1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统功能权限<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
public class SysPermission extends BaseBean {
    private String id; //
    private String permisType; //权限类型（功能权限=1/数据权限=2）
    private String permisName; //权限名称
    private String permisDesc; //权限描述
    private String permisCode; //权限代码，系统唯一
    private String menuId; //菜单ID
    private Integer status; //状态
    private List<SysRole> roleList;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermisType() {
        return permisType;
    }

    public void setPermisType(String permisType) {
        this.permisType = permisType;
    }

    public String getPermisName() {
        return permisName;
    }

    public void setPermisName(String permisName) {
        this.permisName = permisName;
    }

    public String getPermisDesc() {
        return permisDesc;
    }

    public void setPermisDesc(String permisDesc) {
        this.permisDesc = permisDesc;
    }

    public String getPermisCode() {
        return permisCode;
    }

    public void setPermisCode(String permisCode) {
        this.permisCode = permisCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysPermission [id=" + id + ", permisType=" + permisType + ", permisName=" + permisName + ", permisDesc=" + permisDesc
                + ", permisCode=" + permisCode + ", menuId=" + menuId + ", status=" + status + "]";
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

}
