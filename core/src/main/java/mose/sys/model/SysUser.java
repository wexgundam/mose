package mose.sys.model;

import mose.core.model.BaseModel;
import mose.core.string.RegexUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * 系统用户
 *
 * @author 马丽静
 * @date 2017-10-19
 */
public class SysUser extends BaseModel {

    private int id;//用户id
    private Integer roleId;//所属角色
    /**
     * 是否能够删除，0:不可以被删除；1：可以被删除。默认为1
     */
    private int deletable = 1;
    private String roleName;//角色描述
    @NotEmpty
    private String username;//登录账号
    private String password;//登录密码
    private String randomcode;//随机数
    private int status;//账号状态
    private String realName;//姓名
    @Pattern(regexp = RegexUtil.MOBILE_REG)
    private String mobile;//手机号
    @Pattern(regexp = RegexUtil.EMAIL_REG)
    private String email;//邮件
    private String avatar;//头像

    private Integer departmentId;//部门id
    private String departmentName;//部门名称
    private Integer isAdmin;//是否是admin用户
    private String pinyin;//姓名拼音
    private String gender;//性别
    private String nation;//民族（字段中取）
    private String political;//政治面貌（字典中取）
    private String education;//教育程度（字典中取）
    private String graduatedSchool;//毕业学校
    private String major;//专业
    private String idcard;//身份证号
    private String telephone;//座机
    private String post;//职务（从字典取）
    private String jobTitle;//职称（从字典取）
    private Integer displayOrder;//排序
    private String lastLoginDate;//最后登录时间
    private Integer completion;//完整度

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", deletable=" + deletable +
                ", roleName='" + roleName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", randomcode='" + randomcode + '\'' +
                ", status=" + status +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", isAdmin=" + isAdmin +
                ", pinyin='" + pinyin + '\'' +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", political='" + political + '\'' +
                ", education='" + education + '\'' +
                ", graduatedSchool='" + graduatedSchool + '\'' +
                ", major='" + major + '\'' +
                ", idcard='" + idcard + '\'' +
                ", telephone='" + telephone + '\'' +
                ", post='" + post + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", displayOrder=" + displayOrder +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", completion=" + completion +
                '}';
    }

    public int getDeletable() {
        return deletable;
    }

    public void setDeletable(int deletable) {
        this.deletable = deletable;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomcode() {
        return randomcode;
    }

    public void setRandomcode(String randomcode) {
        this.randomcode = randomcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
