package mose.sys.service;

import mose.core.code.RandomCodeUtil;
import mose.core.time.DateUtil;
import mose.core.encrypt.Md5SaltUtil;
import mose.core.excel.Excel2007Util;
import mose.core.file.FileUtil;
import mose.core.model.ComboboxVO;
import mose.core.session.UserSession;
import mose.core.string.RegexUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.dao.SysDepartmentDao;
import mose.sys.dao.SysDicDao;
import mose.sys.dao.SysRoleDao;
import mose.sys.dao.SysUserDao;
import mose.sys.model.SysDepartment;
import mose.sys.model.SysDic;
import mose.sys.model.SysUser;
import mose.sys.vo.SysUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 系统用户管理Service
 *
 * @author mose
 * @date 2017-06-13
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysDicDao sysDicDao;
    @Autowired
    private SysDepartmentDao sysDepartmentDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    /**
     * 用户新增，先判断用户名是否存在 返回2，账号已存在，返回1操作成功
     *
     * @param sysUser
     *
     * @return
     */
    public int add(SysUser sysUser) {
        int flag = 0;
        SysUser exist = sysUserDao.getByUsername(sysUser.getUsername());
        if (exist != null)
            flag = 2;
        else {
            // 设置密码
            String password = "123456";
            String randomcode = RandomCodeUtil.createRandomCode(6);
            Md5SaltUtil md5SaltUtil = new Md5SaltUtil(randomcode);
            String md5Pass = md5SaltUtil.encode(password);
            sysUser.setPassword(md5Pass);
            sysUser.setRandomcode(randomcode);
            // 根据性别设置默认头像
            if ("女".equals(sysUser.getGender())) {
                sysUser.setAvatar("/sys/user/avatar/female.jpg");
            } else {
                sysUser.setAvatar("/sys/user/avatar/male.jpg");
            }
            // 设置姓名拼音
            sysUser.setPinyin(StringUtil.toInitialPinyin(sysUser.getRealName()));// 姓名拼音
            sysUser.setCompletion(completionCalculate(sysUser));
            flag = sysUserDao.add(sysUser);
        }
        return flag;
    }

    public int update(SysUser sysUser) {
        int flag = 0;
        sysUser.setCompletion(completionCalculate(sysUser));
        flag = sysUserDao.update(sysUser);
        return flag;
    }

    public int delete(int id) {
        return sysUserDao.delete(id);
    }

    public SysUser get(int id) {
        return sysUserDao.get(id);
    }

    /**
     * 根据username获取用户
     *
     * @param username
     *
     * @return
     */
    public SysUser getByUsername(String username) {
        return sysUserDao.getByUsername(username);
    }

    /**
     * 根据手机号获取用户
     *
     * @param mobile
     *
     * @return
     */
    public SysUser getByMobile(String mobile) {
        return sysUserDao.getByMobile(mobile);
    }

    /**
     * 用户列表
     *
     * @param sysUserSearchVO
     *
     * @return
     */
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO) {
        List<SysUser> list = sysUserDao.list(sysUserSearchVO);
        return list;
    }

    /**
     * 用户列表总数
     *
     * @param sysUserSearchVO
     *
     * @return
     */
    public int count(SysUserSearchVO sysUserSearchVO) {
        return sysUserDao.count(sysUserSearchVO);
    }

    /**
     * 修改密码
     *
     * @param id
     * @param oldPass
     * @param newPass
     *
     * @return
     */
    public int updatePass(int id, String oldPass, String newPass) {
        int flag = 0;
        SysUser getUser = sysUserDao.get(id);
        // 判断原密码是否为空，不为空则修改新密码
        if (StringUtil.isNotNullOrEmpty(oldPass)) {
            Md5SaltUtil md5SaltUtil = new Md5SaltUtil(getUser.getRandomcode());
            if (md5SaltUtil.isPasswordValid(getUser.getPassword(), oldPass)) {
                String newRandomcode = RandomCodeUtil.createRandomCode(6);
                Md5SaltUtil md5SaltUtil12 = new Md5SaltUtil(newRandomcode);
                String md5Pass = md5SaltUtil12.encode(newPass);
                sysUserDao.updatePass(getUser.getId(), md5Pass, newRandomcode);
                flag = 1;
            } else {
                flag = 2;
            }
        }
        return flag;
    }

    /**
     * 校验密码是否正确
     *
     * @param sysUser
     * @param password
     *
     * @return
     */
    public boolean checkPass(SysUser sysUser, String password) {
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(sysUser.getRandomcode());
        return md5SaltUtil.isPasswordValid(sysUser.getPassword(), password);
    }

    /**
     * 重置密码
     *
     * @param id
     *
     * @return
     */
    public int saveResetPass(int id) {
        int flag = 0;
        String password = "123456";
        String randomcode = RandomCodeUtil.createRandomCode(6);
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(randomcode);
        String md5Pass = md5SaltUtil.encode(password);
        flag = sysUserDao.updatePass(id, md5Pass, randomcode);
        return flag;
    }

    /**
     * 修改状态，锁定解锁用户时使用
     *
     * @param id
     * @param status
     *
     * @return
     */
    public int updateStatus(int id, int status) {
        return sysUserDao.updateStatus(id, status);
    }

    /**
     * 修改头像
     *
     * @param id
     * @param avatar 头像
     *
     * @return
     */
    public int updateAvatar(int id, String avatar) {
        return sysUserDao.updateAvatar(id, avatar);
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    public List<ComboboxVO> listAllUser() {
        return sysUserDao.listAllUser();
    }

    /**
     * 用户列表
     *
     * @return
     */
    public List<SysUser> listAll() {
        List<SysUser> list = sysUserDao.listAll();
        return list;
    }

    /**
     * 根据部门ID查询系统用户
     *
     * @return
     */
    public List<SysUser> listUserByDepartmentId(int departmentId) {
        return sysUserDao.listUserByDepartmentId(departmentId);
    }

    /**
     * 根据部门ID查询系统用户数量
     *
     * @return
     */
    public int countUserByDepartmentId(int departmentId) {
        return sysUserDao.countUserByDepartmentId(departmentId);
    }

    /**
     * what: 导出用户数据
     *
     * @param request
     * @param response
     * @param sysUserSearchVO 导出时依据的查询条件
     *
     * @author mose created on 2017年10月30日
     */
    public void exportList(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        List<SysUser> list = sysUserDao.list(sysUserSearchVO);

        String[][] datas = new String[list.size() + 1][16];
        datas[0][0] = "账号";
        datas[0][1] = "手机号";
        datas[0][2] = "部门名称";
        datas[0][3] = "角色名称";
        datas[0][4] = "真实姓名";
        datas[0][5] = "邮箱";
        datas[0][6] = "性别";
        datas[0][7] = "民族";
        datas[0][8] = "政治面貌";
        datas[0][9] = "教育程度";
        datas[0][10] = "毕业院校";
        datas[0][11] = "专业";
        datas[0][12] = "身份证号";
        datas[0][13] = "座机";
        datas[0][14] = "职务";
        datas[0][15] = "职称";
        int i = 1;
        for (SysUser user : list) {
            datas[i][0] = WebUtil.getSafeStr(user.getUsername());// "账号";
            datas[i][1] = WebUtil.getSafeStr(user.getMobile());// "手机号";
            datas[i][2] = WebUtil.getSafeStr(user.getDepartmentName());// "部门名称";
            datas[i][3] = WebUtil.getSafeStr(user.getRoleName());// "角色名称";
            datas[i][4] = WebUtil.getSafeStr(user.getRealName());// "真实姓名";
            datas[i][5] = WebUtil.getSafeStr(user.getEmail());// "邮箱";
            datas[i][6] = WebUtil.getSafeStr(user.getGender());// "性别";
            datas[i][7] = WebUtil.getSafeStr(user.getNation());// "民族";
            datas[i][8] = WebUtil.getSafeStr(user.getPolitical());// "政治面貌";
            datas[i][9] = WebUtil.getSafeStr(user.getEducation());// "教育程度";
            datas[i][10] = WebUtil.getSafeStr(user.getGraduatedSchool());// "毕业院校";
            datas[i][11] = WebUtil.getSafeStr(user.getMajor());// "专业";
            datas[i][12] = WebUtil.getSafeStr(user.getIdcard());// "身份证号";
            datas[i][13] = WebUtil.getSafeStr(user.getTelephone());// "座机";
            datas[i][14] = WebUtil.getSafeStr(user.getPost());// "职务";
            datas[i][15] = WebUtil.getSafeStr(user.getJobTitle());// "职称";
            i++;
        }
        String fileName = DateUtil.getSystemDate() + "系统用户信息导出";
        Excel2007Util.writeExcel(datas, fileName, response);
    }

    /**
     * what: 对传过来的数据进行验证，并拼接对应的结果
     * warning:返回的数据格式：x-x-XXX表示：行号-对应的检验结果-对应的错误描述,行号为此Excel中对应的数据行的行号；
     * 检验结果取值为0/1分别表示检验为不可导入，可导入待完善；
     * 对应的错误描述表示行号对应的数据行所存在的错误;在页面显示时，根据‘-’将字符串分割，将对应的检验结果转换成文字，将对应的行号错误描述显示到表格中。
     * 当行号为0时，表示意义有：文件为空，传入的文件格式不是Excel，传入的文件表头和模板表头不一致。此类错误提示为文件错误，页面根据判断行号来进行错误提示。
     *
     * @param request request
     * @param file    上传的文件
     *
     * @return List 包含有问题的数据的校验结果
     *
     * @author mose created on 2017年10月30日
     */
    public List<String> validationUser(HttpServletRequest request, MultipartFile file) {
        // 获取民族，政治面貌，教育程度，职务，职称,部门，角色字典表数据
        // 民族
        List<SysDic> nation = sysDicDao.getAllCategory("NATION");
        Map<String, String> nationMap = new HashMap<>();
        for (SysDic sysDic : nation) {
            nationMap.put(sysDic.getName(), sysDic.getCode());
        }
        // 政治面貌
        Map<String, String> politicalMap = new HashMap<>();
        List<SysDic> political = sysDicDao.getAllCategory("POLITICAL");
        for (SysDic sysDic : political) {
            politicalMap.put(sysDic.getName(), sysDic.getCode());
        }
        // 教育程度
        Map<String, String> educationMap = new HashMap<>();
        List<SysDic> education = sysDicDao.getAllCategory("EDUCATION");
        for (SysDic sysDic : education) {
            educationMap.put(sysDic.getName(), sysDic.getCode());
        }
        // 职务
        Map<String, String> postMap = new HashMap<>();
        List<SysDic> post = sysDicDao.getAllCategory("POST");
        for (SysDic sysDic : post) {
            postMap.put(sysDic.getName(), sysDic.getCode());
        }
        // 职称
        Map<String, String> jobTitleMap = new HashMap<>();
        List<SysDic> jobTitle = sysDicDao.getAllCategory("JOB_TITLE");
        for (SysDic sysDic : jobTitle) {
            jobTitleMap.put(sysDic.getName(), sysDic.getCode());
        }
        // 部门
        Map<String, String> departmentMap = new HashMap<>();
        List<SysDepartment> department = sysDepartmentDao.list();
        for (SysDepartment depart : department) {
            departmentMap.put(depart.getName(), Integer.toString(depart.getId()));
        }
        // 角色
        Map<String, String> roleMap = new HashMap<>();
        List<ComboboxVO> role = sysRoleDao.listCombo();
        for (ComboboxVO r : role) {
            roleMap.put(r.getContent(), r.getValue());
        }
        // end

        List<String> list = new ArrayList<>();
        String[] fileTitle = null;
        if (file.getOriginalFilename().lastIndexOf(".xlsx") < 0 && file.getOriginalFilename().lastIndexOf(".xls") < 0) {
            // 0-0-0特殊标记，因Excel数据行其实行为1，不存在0行，返回到页面时，根据返回的结果，如果是0则表示文件错误，在页面进行错误提示
            String string = "0-0-0";
            list.add(string);
            return list;
        }
        fileTitle = Excel2007Util.readExcelTitle(file, 1);
        if (fileTitle == null) {
            // 0-0-0特殊标记，因Excel数据行其实行为1，不存在0行，返回到页面时，根据返回的结果，如果是0则表示文件错误，在页面进行错误提示
            String string = "0-0-0";
            list.add(string);
            return list;
        }
        // 获取上传文件表头
        // 定义模板表头
        String[] modelTitle = new String[]{"账号", "手机号", "部门名称", "角色名称", "真实姓名", "邮箱", "性别", "民族", "政治面貌", "教育程度", "毕业院校", "专业", "身份证号", "座机", "职务",
                "职称"};
        // 定义数据行从哪一行开始
        int dataStart = 2;
        // 总数据条数
        int sumDataLine = 0;
        // 不可导入条数
        int sumErrorLine = 0;
        // 待完善数据条数
        int sumWaringLine = 0;
        // 校验表头
        if (!Arrays.equals(fileTitle, modelTitle)) {
            String result = "0-0-表头与模板不一致";// 行号-结果-描述
            list.add(result);
            return list;
        } else {
            List<String[]> dataList = Excel2007Util.readExcelContent(file, dataStart);// 读excel数据
            if (dataList == null || dataList.size() == 0) {
                // 0-0-0特殊标记，因Excel数据行其实行为1，不存在0行，返回到页面时，根据返回的结果，如果是0则表示文件错误，在页面进行错误提示
                String result = "0-0-表为空";
                list.add(result);
                return list;
            }
            sumDataLine = dataList.size();
            int row = dataStart;// 定义数据行号
            for (String[] str : dataList) {
                // 返回的处理结果
                String result = validateUserResult(str, roleMap, departmentMap, jobTitleMap, postMap, educationMap, politicalMap, nationMap);
                if (StringUtil.isNotNullOrEmpty(result)) {
                    String string = row + "-";
                    if (result.contains("-1")) {
                        // 处理结果中包含-1表示出现不可导入错误，则将不可导入结果表示0和处理结果描述与行号拼接成指定格式
                        string += "0-" + (result.replaceAll("-1", "")).replaceAll("0", "");
                        sumErrorLine++;
                    } else if (result.contains("0")) {
                        // 处理结果中包含0表示出现可导入待完善错误，则将可导入待完善结果表示1和处理结果描述与行号拼接成指定格式
                        string += "1-" + result.replaceAll("0", "");
                        sumWaringLine++;
                    } else {
                        // 未出现错误表示-1/0，则进行下一次循环验证下一条数据
                        continue;
                    }
                    list.add(string);
                }
                row++;
            }
            // 总的数据行
            request.setAttribute("sumDataLine", sumDataLine);
            // 可导入待完善的数据行数
            request.setAttribute("sumWaringLine", sumWaringLine);
            // 不可导入的数据行数
            request.setAttribute("sumErrorLine", sumErrorLine);
            return list;
        }
    }

    /**
     * what: 校验每一条数据是否可以入库（在调用这个方法之前已经确定和模板或者导出的文件的表头一致）
     *
     * @param data       每一行Excel数据
     * @param role       需要进行数据准确性校验的字典类型角色和对应的值
     * @param department 需要进行数据准确性校验的字典类型和对应的值
     * @param jobTitle   需要进行数据准确性校验的字典类型和对应的值
     * @param post       需要进行数据准确性校验的字典类型和对应的值
     * @param education  需要进行数据准确性校验的字典类型和对应的值
     * @param political  需要进行数据准确性校验的字典类型和对应的值
     * @param nation     需要进行数据准确性校验的字典类型和对应的值
     *
     * @return String 返回这一行的校验结果(返回结果包含-1则表示此行出现账号未填写、格式不正确、数据不存在等不可导入错误，包含0表示出现某些字段为空等可导入待完善错误)
     *
     * @author mose created on 2017年10月30日
     */
    private String validateUserResult(String[] data, Map<String, String> role, Map<String, String> department, Map<String, String> jobTitle,
                                      Map<String, String> post, Map<String, String> education, Map<String, String> political, Map<String, String> nation) {
        String result = "";
        // 判断账号是否为空
        if (StringUtil.isNullOrEmpty(data[0])) {// 账号
            // 账号为空
            result += "-1/账号不能为空";
        } else {
            // 账号不为空，判断账号格式是否正确
            if (data[0].length() > 20) {
                // 账号格式不正确
                result += "-1/账号过长";
            }
        }
        // 判断账号是否为空
        if (StringUtil.isNullOrEmpty(data[0])) {
            return result;
        }

        // 表示账号都不为空，可以进行格式校验

        // 验证必填字段：姓名，性别，手机，部门，角色是否必填
        // 验证姓名
        if (StringUtil.isNullOrEmpty(data[4])) {
            // 姓名为空
            result += "0/姓名为空";

        } else if (data[4].length() > 20) {
            // 姓名不为空，判断姓名的长度是否符合
            result += "-1/姓名过长";
        }
        // 验证性别
        if (!"男".equals(data[6]) && !"女".equals(data[6]) && StringUtil.isNotNullOrEmpty(data[6])) {
            result += "-1/性别格式不正确";
        } else if (StringUtil.isNullOrEmpty(data[6])) {
            result += "0/性别为空";
        }
        // 验证手机号
        if (StringUtil.isNotNullOrEmpty(data[1])) {
            // 手机号是否过长
            if (data[1].length() > 11) {
                result += "-1/手机号过长";
            } else if (data[1].length() < 11) {// 手机号是否过短
                result += "-1/手机号过短";
            } else {
                // 判断是否符合手机号格式
                boolean b = RegexUtil.validateMobile(data[1]);
                if (!b) {
                    result += "-1/手机号格式不正确";
                }
            }
        } else if (StringUtil.isNullOrEmpty(data[1])) {
            // 手机号为空
            result += "0/手机号为空";
        }
        // 验证部门
        if (StringUtil.isNotNullOrEmpty(data[2])) {
            // 部门不为空
            // 验证部门是否存在
            if (!department.containsKey(data[2])) {
                result += "-1/部门不存在";
            }
        } else if (StringUtil.isNullOrEmpty(data[2])) {
            // 部门为空
            result += "0/部门名称为空";
        }
        // 验证角色
        if (StringUtil.isNotNullOrEmpty(data[3])) {
            // 角色不为空
            // 验证角色是否存在
            if (!role.containsKey(data[3])) {
                result += "-1/角色不存在";
            }
        } else if (StringUtil.isNullOrEmpty(data[3])) {
            // 角色为空
            result += "0/角色名称为空";
        }
        // 验证邮箱
        if (StringUtil.isNotNullOrEmpty(data[5])) {
            if (!RegexUtil.validateEmail(data[5])) {
                result += "-1/邮箱格式不正确";
            }
            if (data[5].length() > 40) {
                result += "-1/邮箱过长";
            }
        }
        // 验证身份证号
        if (StringUtil.isNotNullOrEmpty(data[12])) {
            if (data[12].length() < 18) {
                result += "-1/身份证号过短";
            } else if (data[12].length() > 18) {
                result += "-1/身份证号过长";
            } else {
                boolean b = RegexUtil.validateIdCard(data[12]);
                if (!b) {
                    result += "-1/身份证号格式不正确";
                }
            }
        }
        // 验证民族
        if (StringUtil.isNotNullOrEmpty(data[7]) && data[7].length() > 20) {
            result += "-1/民族字段过长";
        } else if (StringUtil.isNotNullOrEmpty(data[7]) && data[7].length() <= 20) {
            if (!nation.containsKey(data[7])) {
                result += "-1/输入的民族不存在";
            }
        }
        // 验证政治面貌
        if (StringUtil.isNotNullOrEmpty(data[8]) && data[8].length() > 20) {
            result += "-1/政治面貌字段过长";
        } else if (StringUtil.isNotNullOrEmpty(data[8]) && data[8].length() <= 20) {
            if (!political.containsKey(data[8])) {
                result += "-1/输入的政治面貌不存在";
            }
        }
        // 验证教育程度
        if (StringUtil.isNotNullOrEmpty(data[9]) && data[9].length() > 20) {
            result += "-1/教育程度字段过长";
        } else if (StringUtil.isNotNullOrEmpty(data[9]) && data[9].length() <= 20) {
            if (!education.containsKey(data[9])) {
                result += "-1/输入的教育程度不存在";
            }
        }
        // 验证毕业院校
        if (StringUtil.isNotNullOrEmpty(data[10]) && data[10].length() > 40) {
            result += "-1/毕业院校字段过长";
        }
        // 验证专业
        if (StringUtil.isNotNullOrEmpty(data[11]) && data[11].length() > 40) {
            result += "-1/专业字段过长";
        }
        // 验正座机
        if (StringUtil.isNotNullOrEmpty(data[13]) && data[13].length() > 20) {
            result += "-1/座机字段过长";
        }
        // 验正职务
        if (StringUtil.isNotNullOrEmpty(data[14]) && data[14].length() > 20) {
            result += "-1/职务字段过长";
        } else if (StringUtil.isNotNullOrEmpty(data[14]) && data[14].length() <= 20) {
            if (!post.containsKey(data[14])) {
                result += "-1/输入的职务不存在";
            }
        }
        // 验正职称
        if (StringUtil.isNotNullOrEmpty(data[15]) && data[15].length() > 20) {
            result += "-1/职称字段过长";
        } else if (StringUtil.isNotNullOrEmpty(data[15]) && data[15].length() <= 20) {
            if (!jobTitle.containsKey(data[15])) {
                result += "-1/输入的职称不存在";
            }
        }
        // 拼接的数据格式为x/xxx，表示：错误代码/错误描述
        return result;
    }

    /**
     * what: 对数据进行导入操作
     *
     * @param request
     * @param file           需要导入的文件
     * @param valicationList 校验之后获得的，有问题的数据集合
     *
     * @return List 返回的导入结果
     *
     * @author mose created on 2017年10月30日
     */
    public List<String> importUser(HttpServletRequest request, MultipartFile file, List<String> valicationList) {
        // 获取民族，政治面貌，教育程度，职务，职称,部门，角色字典表数据
        Map<String, Map<String, String>> dicMap = new HashMap<>();
        Map<String, String> tempMap = new HashMap<>();
        List<SysDepartment> department = sysDepartmentDao.list();// 部门
        for (SysDepartment depart : department) {
            tempMap.put(depart.getName(), Integer.toString(depart.getId()));
        }
        dicMap.put("department", tempMap);

        List<ComboboxVO> role = sysRoleDao.listCombo();// 角色
        for (ComboboxVO r : role) {
            tempMap.put(r.getContent(), r.getValue());
        }
        dicMap.put("role", tempMap);

        // end
        List<String> list = new ArrayList<>();
        int userId = ((UserSession) request.getSession().getAttribute("userSession")).getUserId();
        String userName = ((UserSession) request.getSession().getAttribute("userSession")).getUsername();
        String rowErrorNum = "";// 存放不可导入的行号
        String rowFailNum = "";// 失败条数
        int rowWaringNum = 0;// 待完善条数
        int sumDataLine = 0;// 总记录条数
        List<String[]> dataList = null;
        dataList = Excel2007Util.readExcelContent(file, 2);// 读excel数据

        if (dataList == null) {
            return null;
        }
        sumDataLine = dataList.size();
        for (String str : valicationList) {
            // 对校验之后的结果进行分割，将不可导入的行号与待完善的行号分离，
            String[] s = str.split("-");
            if ("0".equals(s[1])) {
                // 为0表示不可导入，加入到失败条数包含的行号中
                rowErrorNum += s[0] + ",";
            }
            if ("1".equals(s[1])) {
                // 为1表示不可导入，加入到待完善条数包含的行号中
                rowWaringNum++;
            }
        }
        int successSum = 0;// 成功的条数

        int dataLine = 2;// 数据起始行
        SysUser user = null;// 中间变量
        for (String[] data : dataList) {
            // 判断此行号是否在不可导入的行号之内，在的话直接略过这一行数据
            if (rowErrorNum.contains(Integer.toString(dataLine))) {
                dataLine++;
                continue;
            }
            user = new SysUser();
            user.setUsername(data[0]);// 账号
            user.setMobile(data[1]);// 手机号
            user.setDepartmentId("".equals(data[2]) ? null : Integer.valueOf(dicMap.get("department").get(data[2])));// 部门id
            user.setRoleId("".equals(data[3]) ? null : Integer.valueOf(dicMap.get("role").get(data[3])));// 角色id
            user.setRealName(data[4]);// 真实姓名
            user.setEmail(data[5]);// 邮箱
            user.setGender(data[6]);// 性别
            user.setNation(data[7]);// 民族
            user.setPolitical(data[8]);// 政治面貌
            user.setEducation(data[9]);// 教育程度
            user.setGraduatedSchool(data[10]);// 毕业院校
            user.setMajor(data[11]);// 专业
            user.setIdcard(data[12]);// 身份证号
            user.setTelephone(data[13]);// 座机
            user.setPost(data[14]);// 职务
            user.setJobTitle(data[15]);// 职称
            user.setPinyin(StringUtil.toInitialPinyin(data[4]));// 姓名拼音
            user.setStatus(1);// 账号状态
            user.setIsAdmin(0);// 是否管理员
            // user.setIsCheck(1);//是否审核，保留字段
            user.setAvatar("");// 头像
            user.setCreatorId(userId);
            user.setCreatorRealName(userName);
            int flagAdd = add(user);// 添加用户时返回的标记
            int flagUpdate = 0;// 更新用户时返回的标记
            if (flagAdd == 2) {
                SysUser existedUser = getByUsername(data[0]);
                user.setId(existedUser.getId());
                user.setCreatorId(existedUser.getCreatorId());
                user.setCreatorRealName(existedUser.getCreatorRealName());
                user.setLastEditorId(userId);
                user.setLastEditorRealName(userName);
                user.setDisplayOrder(existedUser.getDisplayOrder());
                flagUpdate = update(user);
            }
            if (flagAdd == 0 && flagUpdate == 0) {
                // 如果添加和更新标记都为0表示操作失败，将此行号作为入库失败行号保存
                rowFailNum += dataLine + ",";
            }
            dataLine++;
            successSum++;
        }
        request.setAttribute("successSum", successSum);// 成功条数
        request.setAttribute("failSum", "".equals(rowErrorNum + rowFailNum) ? 0 : (rowErrorNum + rowFailNum).split(",").length);// 失败条数
        request.setAttribute("waringSum", rowWaringNum);// 待完善条数
        request.setAttribute("sumDataLine", sumDataLine);// 总记录条数
        for (String str : valicationList) {
            String[] s = str.split("-");
            // 对导入后的数据进行判断是否入库成功，如果检验的结果中的行号存在于失败行号中，则将表示入库失败的状态0拼接到对应的处理结果中，在页面中进行对应的图标显示
            if (rowFailNum.contains(s[0])) {
                str += "-0";
            } else {
                if ("0".equals(s[1])) {
                    // 对导入后的数据进行判断是否入库成功，如果检验的结果为不可导入，即导入结果标记为0时，则将表示入库失败的状态0拼接到对应的处理结果中，在页面中进行对应的图标显示
                    str += "-0";
                } else {
                    // 对导入成功的数据拼接入库标志位1，表示入库成功
                    str += "-1";
                }
            }

            list.add(str);
        }
        return list;
    }

    /**
     * what: 下载文件模板业务处理
     *
     * @param response
     * @param request
     *
     * @throws Exception
     * @author mose created on 2017年11月3日
     */
    public void exportUserTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取目标文件的绝对路径
        String fullFileName = FileUtil.class.getResource("/file/sys/user/sys_userExcelTemplate.xlsx").getPath();
        // 设置文件MIME类型
        response.setContentType(request.getServletContext().getMimeType(fullFileName));
        // 设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + fullFileName.substring(fullFileName.lastIndexOf("/") + 1));
        try {
            // 读取文件
            InputStream in = new FileInputStream(fullFileName);
            // 读取目标文件，通过response将目标文件写到客户端
            OutputStream out = response.getOutputStream();

            // 写文件
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("系统用户模板下载错误");
        }
    }

    /**
     * what:    计算信息完整度
     *
     * @param sysUser
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    public Integer completionCalculate(SysUser sysUser) {
        int num = 0;
        //计算规则，必填字段只要有一没有值则完整度为49
        if (StringUtil.isNullOrEmpty(sysUser.getRealName()) || StringUtil.isNullOrEmpty(sysUser.getGender())
                || StringUtil.isNullOrEmpty(sysUser.getMobile()) || sysUser.getDepartmentId() == null || sysUser
                .getRoleId() == null) {
            num = 49;
            return num;
        } else {
            num = 50;
        }
        //计算规则，必填字段都填了以后得到基础分50.其他选
        if (StringUtil.isNotNullOrEmpty(sysUser.getEmail())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getPolitical())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getPost())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getEducation())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getJobTitle())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getIdcard())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getGraduatedSchool())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getTelephone())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getMajor())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getNation())) {
            num += 4;
        }
        if (StringUtil.isNotNullOrEmpty(sysUser.getAvatar()) && !"/sys/user/avatar/male.jpg".equals(sysUser.getAvatar()
        ) && !"/sys/user/avatar/female.jpg".equals(sysUser
                .getAvatar())) {
            num += 10;
        }
        return num;
    }
}
