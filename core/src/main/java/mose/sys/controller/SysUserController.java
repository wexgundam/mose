package mose.sys.controller;

import mose.core.code.GlobalCode;
import mose.core.time.DateUtil;
import mose.core.json.JsonUtil;
import mose.core.page.PageNavigate;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.session.UserSession;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysUser;
import mose.sys.model.SysUserLogin;
import mose.sys.service.SysDepartmentService;
import mose.sys.service.SysDicService;
import mose.sys.service.SysRoleService;
import mose.sys.service.SysUserLoginService;
import mose.sys.service.SysUserService;
import mose.sys.vo.SysUserLoginSearchVO;
import mose.sys.vo.SysUserSearchVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * what:    系统用户管理Controller
 *
 * @author mose created on 2017年7月26日
 */
@RequestMapping("/sys/user")
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserLoginService sysUserLoginService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private PubConfig pubConfig;
    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private SysDicService sysDicService;

    /**
     * what:    进入用户管理页面
     * when:    菜单点击系统用户时进入
     *
     * @param request
     * @param response
     * @param sysUserSearchVO
     *
     * @return
     *
     * @author mose created on 2017年7月26日
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        ModelAndView mv = new ModelAndView();
        sysUserSearchVO.setCurrentUser(SessionUtil.getUserSession(request).getUsername());// 获取当前用户
        int recordCount = sysUserService.count(sysUserSearchVO);// 获取查询总数
        String url = createUrl(sysUserSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, sysUserSearchVO.getPageIndex(), recordCount);// 定义分页对象
        List<SysUser> list = sysUserService.list(sysUserSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.addObject("listRole", sysRoleService.listCombo());// 角色列表
        String ztree = sysDepartmentService.createZtreeByModule();// 机构列表
        mv.addObject("ztree", ztree);
        mv.setViewName("/sys/user/index");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    /**
     * what:    设置分页url，一般有查询条件的才会用到
     * when:    一般有查询条件的才会用到
     *
     * @param sysUserSearchVO
     *
     * @return
     *
     * @author mose created on 2017/11/8
     */
    private String createUrl(SysUserSearchVO sysUserSearchVO) {
        String url = pubConfig.getDynamicServer() + "/sys/user/index.htm?";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername()))
            url += "&username=" + sysUserSearchVO.getUsername();
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealName()))// 如果为模糊查询，要把该字段encode
            url += "&realname=" + sysUserSearchVO.getRealName();
        if (sysUserSearchVO.getStatus() != null)
            url += "&status=" + sysUserSearchVO.getStatus();
        if (sysUserSearchVO.getRoleId() != null)
            url += "&roleId=" + sysUserSearchVO.getRoleId();
        return url;
    }


    /**
     * what:    进入添加用户界面
     * when:    用户页面点击新增
     *
     * @param request
     * @param response
     *
     * @return
     *
     * @author mose created on 2017年7月26日
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("listRole", sysRoleService.listCombo());// 角色列表
        mv.addObject("listJobTitle", sysDicService.getByCategory("JOB_TITLE"));// 职务
        mv.addObject("listPost", sysDicService.getByCategory("POST"));// 职称
        mv.addObject("listPolitical", sysDicService.getByCategory("POLITICAL"));// 政治面貌
        mv.addObject("listEducation", sysDicService.getByCategory("EDUCATION"));// 教育程度
        mv.addObject("listNation", sysDicService.getByCategory("NATION"));// 民族
        String ztree = sysDepartmentService.createZtreeByModule();// 机构列表
        mv.addObject("ztree", ztree);
        SysUser sysUser = new SysUser();
        mv.addObject("sysUser", sysUser);
        mv.setViewName("/sys/user/add");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }


    /**
     * what:    进入修改用户界面
     *
     * @param request
     * @param response
     * @param id
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("listRole", sysRoleService.listCombo());// 角色列表
        mv.addObject("listJobTitle", sysDicService.getByCategory("JOB_TITLE"));// 职务
        mv.addObject("listPost", sysDicService.getByCategory("POST"));// 职称
        mv.addObject("listPolitical", sysDicService.getByCategory("POLITICAL"));// 政治面貌
        mv.addObject("listEducation", sysDicService.getByCategory("EDUCATION"));// 教育程度
        mv.addObject("listNation", sysDicService.getByCategory("NATION"));// 民族
        String ztree = sysDepartmentService.createZtreeByModule();// 机构列表
        mv.addObject("ztree", ztree);
        if (id == 0)
            id = SessionUtil.getUserSession(request).getUserId();
        SysUser sysUser = sysUserService.get(id);
        mv.addObject("sysUser", sysUser);
        mv.setViewName("/sys/user/update");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/getPinYin")
    @ResponseBody
    public String getPinYin(String realname) {
        String pinyin = StringUtil.toInitialPinyin(realname);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("pinyin", pinyin);
        return jsonObject.toString();
    }

    /**
     * what:    查询部门Ztree的方法
     * when:    ajax请求部门ztree
     *
     * @param request
     * @param response
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/departmentTree")
    @ResponseBody
    public String departmentTree(HttpServletRequest request, HttpServletResponse response) {
        String ztree = sysDepartmentService.createZtreeByModule();// 机构列表
        JSONObject jsonObject = new JSONObject();
        if ("".equals(ztree) || ztree == null) {
            jsonObject.put("success", "false");
        } else {
            jsonObject.put("success", "true");
            jsonObject.put("ztree", ztree);
        }
        return jsonObject.toString();
    }


    /**
     * what:    进入查看详情页
     *
     * @param request
     * @param response
     * @param id
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysUser sysUser = sysUserService.get(id);
        mv.addObject("sysUser", sysUser);
        mv.setViewName("/sys/user/detail");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }


    /**
     * what:    新增用户
     *
     * @param request
     * @param response
     * @param sysUser
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        sysUser.setStatus(1);
        sysUser.setCreatorId(SessionUtil.getUserId(request));
        sysUser.setCreatorRealName(SessionUtil.getUserRealName(request));
        sysUser.setLastEditorId(SessionUtil.getUserId(request));
        sysUser.setLastEditorRealName(SessionUtil.getUserRealName(request));
        int flag = sysUserService.add(sysUser);
        if (flag == 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;// 用户信息新增失败;
        else if (flag == 2)
            return "forward:/error.htm?resultCode=20104";// 用户账号已存在;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;// 用户信息新增成功;
    }


    /**
     * what:    修改用户
     *
     * @param request
     * @param response
     * @param sysUser
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/update")
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response, SysUser sysUser, @RequestParam
            (value = "file", required = false) MultipartFile file) throws IOException {
        if (file != null) {
            String path = uploadAvatar(file);
            sysUser.setAvatar(path);
        }
        int flag = sysUserService.update(sysUser);
        if (flag == 0) {

            WebUtil.out(response, JsonUtil.createOperaString(false, "/error.htm?resultCode=" + GlobalCode
                    .OPERA_FAILURE));
        } else {
            if (SessionUtil.getUserRealName(request).equals(sysUser.getRealName())) {
                UserSession userSession = SessionUtil.getUserSession(request);
                userSession.setAvatar(sysUser.getAvatar());
                request.getSession().setAttribute("userSession", userSession);
            }
            WebUtil.out(response, JsonUtil.createOperaString(true, "/success.htm?resultCode=" + GlobalCode
                    .SAVE_SUCCESS));
        }

    }


    /**
     * what:    上传头像
     * when:    保存修改用户信息的时候上传头像及avatar字段存入数据库
     *
     * @return String 数据库存放的avatar字段
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/uploadAvatar")
    public String uploadAvatar(MultipartFile file) throws IOException {
        //实际上传文件路径
        String dir = File.separator + "sys/user/avatar" + File.separator + DateUtil.dateToString(new Date(),
                "yyyyMMdd");
        //数据库保存路径
        String saveDir = "/sys/user/avatar/" + DateUtil.dateToString(new Date(), "yyyyMMdd");//保存上传文件路径
        String fileName = DateUtil.getShortSystemTime() + "" + new Random().nextInt(10000) + ".jpg";
        File f = new File(pubConfig.getImageUploadPath() + dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        file.transferTo(new File(pubConfig.getImageUploadPath() + dir + "/" + fileName));
        return saveDir + "/" + fileName;
    }

    /**
     * what:    删除用户
     *
     * @param request
     * @param response
     * @param id       用户id
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;// 用户删除失败;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.DELETE_SUCCESS;// 用户删除成功;
    }

    /**
     * what:    重置密码
     *
     * @param request
     * @param response
     * @param id       用户id
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/saveResetPass")
    public String saveResetPass(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.saveResetPass(id);
        if (flag == 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;// 用户重置密码失败;
        else
            return "forward:/success.htm?resultCode=10101";// 用户重置密码成功，重置后密码变为123456;
    }


    /**
     * what:    用户加锁，状态由1变成2
     *
     * @param request
     * @param response
     * @param id       用户id
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/saveLock")
    public String saveLock(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.updateStatus(id, 2);
        if (flag == 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;// 用户锁定失败;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;// 用户锁定成功");
    }


    /**
     * what:    用户解锁，状态由2变为1
     *
     * @param request
     * @param response
     * @param id       用户id
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/saveUnlock")
    public String saveUnlock(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.updateStatus(id, 1);
        if (flag == 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;// 用户解锁失败;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;// 用户解锁成功;
    }

    /**
     * what:    验证用户代码是否存在
     * when:    新增用户时异步校验用户是否存在
     *
     * @param request
     * @param response
     * @param username 用户名
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/checkUserExist")
    public void checkUserExist(HttpServletRequest request, HttpServletResponse response, String username) {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null)
            WebUtil.out(response, "true");
        else
            WebUtil.out(response, "false");
    }

    /**
     * what:    验证手机号是否存在
     * when:    新增、修改用户时异步校验用户手机号是否存在
     *
     * @param request
     * @param response
     * @param mobile   手机号
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/checkMobileExist")
    public void checkMobileExist(HttpServletRequest request, HttpServletResponse response, String mobile) {
        SysUser sysUser = sysUserService.getByMobile(mobile);
        if (sysUser == null)
            WebUtil.out(response, "true");
        else
            WebUtil.out(response, "false");
    }


    /**
     * what:    根据用户id，获取用户登录信息
     *
     * @param request
     * @param response
     * @param sysUserloginSearchVO 用户登录信息
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/searchUserLogin")
    public void searchUserLogin(HttpServletRequest request, HttpServletResponse response, SysUserLoginSearchVO sysUserloginSearchVO) {
        int recordCount = sysUserLoginService.count(sysUserloginSearchVO);// 获取查询总数
        List<SysUserLogin> list = sysUserLoginService.list(sysUserloginSearchVO);
        WebUtil.out(response, JsonUtil.createDataTablePageJson(sysUserloginSearchVO.getPageIndex(), recordCount, JsonUtil.toString(list)));
    }

    /**
     * what: 根据用户输入的查询条件进行数据导出
     *
     * @param request
     * @param response
     * @param sysUserSearchVO 导出数据的查询条件
     *
     * @author mose created on 2017年10月30日
     */
    @RequestMapping("/exportUserExcel")
    public void exportUserExcel(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        sysUserService.exportList(request, response, sysUserSearchVO);
    }

    /**
     * what: 跳转到导入用户页面
     *
     * @return
     *
     * @author mose created on 2017年10月30日
     */
    @RequestMapping("/toImportUser")
    public String toImportUser() {
        return "sys/user/importUser";
    }

    /**
     * what: 对数据进行导入操作
     *
     * @param file     需要导入的文件
     * @param request
     * @param response
     * @param listJson 包含校验结果的集合
     *
     * @author mose created on 2017年10月30日
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/importUser")
    public void importUser(@RequestParam("file1") MultipartFile file, HttpServletRequest request, HttpServletResponse response, String listJson) {
        List<String> list = JsonUtil.toObject(listJson, List.class);
        List<String> resultList = sysUserService.importUser(request, file, list);
        Map<String, Object> jsonMap = new HashMap<>();
        if (resultList == null) {
            jsonMap.put("status", false);
            jsonMap.put("msg", "表为空");
        } else {
            jsonMap.put("status", true);
            jsonMap.put("list", resultList);
            jsonMap.put("successSum", request.getAttribute("successSum"));
            jsonMap.put("failSum", request.getAttribute("failSum"));
            jsonMap.put("waringSum", request.getAttribute("waringSum"));
            jsonMap.put("sumDataLine", request.getAttribute("sumDataLine"));
        }
        WebUtil.out(response, JsonUtil.toString(jsonMap));
    }

    /**
     * what: 验证导入用户信息
     *
     * @param file     需要导入的文件
     * @param request
     * @param response
     *
     * @author mose created on 2017年10月30日
     */
    @RequestMapping("/validationImportUser")
    public void validationImportUser(@RequestParam("file1") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        List<String> list = sysUserService.validationUser(request, file);
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("list", list);
        map.put("listJson", JsonUtil.toString(list));
        map.put("sumDataLine", request.getAttribute("sumDataLine"));
        map.put("sumWaringLine", request.getAttribute("sumWaringLine"));
        map.put("sumErrorLine", request.getAttribute("sumErrorLine"));
        WebUtil.out(response, JsonUtil.toString(map));
    }


    /**
     * what:   进入个人信息页
     *
     * @param request
     * @param response
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("listJobTitle", sysDicService.getByCategory("JOB_TITLE"));// 职务
        mv.addObject("listPost", sysDicService.getByCategory("POST"));// 职称
        mv.addObject("listPolitical", sysDicService.getByCategory("POLITICAL"));// 政治面貌
        mv.addObject("listEducation", sysDicService.getByCategory("EDUCATION"));//教育程度
        mv.addObject("listNation", sysDicService.getByCategory("NATION"));//民族
        SysUser sysUser = sysUserService.get(SessionUtil.getUserSession(request).getUserId());
        mv.addObject("sysUser", sysUser);
        mv.setViewName("sys/user/view");
        mv.addObject("backUrl", pubConfig.getDynamicServer() + "/index.htm");
        return mv;
    }

//    /**
//     * what:    修改头像
//     *
//     * @param avatarFile
//     * @param avatarData
//     * @param request
//     * @param response
//     * @param userId
//     * @return
//     * @author mose created on 2017年11月6日
//     */
//    @RequestMapping("/uploadAvatar")
//    public void uploadAvatar(MultipartFile avatarFile, String avatarData, HttpServletRequest request,
//     String dir = File.separator + "sys/user/avatar" + File.separator + DateUtil.dateToString(new Date(),
//                "yyyyMMdd");//实际上传文件路径                         HttpServletResponse response, int userId) {
//
//        String saveDir = "/sys/user/avatar/" + DateUtil.dateToString(new Date(), "yyyyMMdd");//保存上传文件路径
//        String oldFileName = avatarFile.getOriginalFilename();
//        String fileName = new Date().getTime() + "" + new Random().nextInt(10000) + oldFileName.substring(oldFileName.lastIndexOf('.'));//定义上传文件名
//
//        //开始上传
//        try {
//            File targetFile = new File(pubConfig.getImageUploadPath() + dir, fileName);
//            File targetDir = new File(pubConfig.getImageUploadPath() + dir);
//            if (!targetDir.exists()) {
//                targetDir.mkdirs();
//            }
//            InputStream is = avatarFile.getInputStream();
//
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            WebUtil.out(response, "{\"result\":" + false + ",\"message\":" + "\"传输失败\"}");
//        }
//        String url = pubConfig.getImageServer() + saveDir + "/" + fileName;
//        //修改session中的头像值
//        UserSession userSession = SessionUtil.getUserSession(request);
//        userSession.setAvatar(saveDir + "/" + fileName);
//        request.getSession().setAttribute("userSession", userSession);
//        WebUtil.out(response, "{\"result\":" + true + ",\"message\":\"" + "上传成功" + "\",\"url\":\"" + url + "\"}");
//    }
//

    /**
     * what:    进入密码修改页
     *
     * @param request
     * @param response
     * @param sysUser  用户信息
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/toUpdatePass")
    public ModelAndView updatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/user/updatePass");
        mv.addObject("backUrl", pubConfig.getDynamicServer() + "/index.htm");
        return mv;
    }


    /**
     * what:    修改密码
     *
     * @param request
     * @param response
     * @param sysUser  用户信息
     *
     * @return
     *
     * @author mose created on 2017年11月6日
     */
    @RequestMapping("/saveUpdatePass")
    public String saveUpdatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        String oldPass = WebUtil.getSafeStr(request.getParameter("oldPass"));
        String newPass = WebUtil.getSafeStr(request.getParameter("newPass"));
        UserSession userSession = SessionUtil.getUserSession(request);
        int flag = sysUserService.updatePass(userSession.getUserId(), oldPass, newPass);
        if (flag == 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;//密码修改失败;
        else if (flag == 2)
            return "forward:/error.htm?resultCode=20105";//原密码输入错误;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;//密码修改成功;
    }

    /**
     * what:    计算完整度
     *
     * @param request
     * @param response
     * @param sysUser
     *
     * @return
     *
     * @author mose created on 2017年11月15日
     */
    @RequestMapping("/completionCalculate")
    @ResponseBody
    public String completionCalculate(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        Integer completion = sysUserService.completionCalculate(sysUser);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("completion", completion);
        return jsonObject.toString();
    }

}
