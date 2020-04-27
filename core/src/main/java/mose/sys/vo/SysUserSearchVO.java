package mose.sys.vo;


import mose.core.page.PageSearchVO;

/**
 * 用户查询VO
 *
 * @author 马丽静
 * @date 2017-10-25
 */
public class SysUserSearchVO extends PageSearchVO {
    private String username;//username
    private Integer status;//状态
    private Integer roleId;//角色
    private String realName;//姓名
    private String currentUser;//当前用户，如果为system，可以看全部的，如果不为system，不能看system用户
    private Integer  departmentId;//部门ID
    private String  departmentName;//部门名称
    private String  isCompletion;//部门名称


    public String getIsCompletion() {
        return isCompletion;
    }

    public void setIsCompletion(String isCompletion) {
        this.isCompletion = isCompletion;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    //姓名模糊查询
    public String getRealnameStr() {
        return "%" + realName + "%";
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "SysUserSearchVO{" +
                "username='" + username + '\'' +
                ", status=" + status +
                ", roleId=" + roleId +
                ", realName='" + realName + '\'' +
                ", currentUser='" + currentUser + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}
