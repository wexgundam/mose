package mose.sys.controller;

import mose.core.code.GlobalCode;
import mose.core.json.JsonUtil;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysDepartment;
import mose.sys.model.SysUser;
import mose.sys.service.SysDepartmentService;
import mose.sys.service.SysDicService;
import mose.sys.service.SysUserService;
import mose.sys.vo.SysUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * what: 部门Controller
 *
 * @author 李红 created on 2017年10月30日
 */
@Controller
@RequestMapping("/sys/department")
public class SysDepartmentController {
    /**
     * 部门Service
     */
    @Autowired
    private SysDepartmentService sysDepartmentService;
    /**
     * 用户Service
     */
    @Autowired
    private SysUserService sysUserService;
    /**
     * 字典Sevice
     */
    @Autowired
    private SysDicService sysDicService;
    /**
     * 全局参数配置
     */
    @Autowired
    private PubConfig pubConfig;

    /**
     * what: 显示所有部门列表，treeGrid显示
     *
     * @param request request
     *
     * @return 到部门列表首页
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/department/index");
        List<SysDepartment> list = sysDepartmentService.list();
        // 把获取的记录放到mv里面
        mv.addObject("list", list);
        String url = pubConfig.getDynamicServer() + "/sys/department/index.htm?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

    /**
     * what: 到新增部门页面
     *
     * @param request  request
     * @param response response
     *
     * @return 到新增部门页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // 部门列表
        String ztree = sysDepartmentService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("listType", sysDicService.getByCategory("DEPARTMENT_TYPE"));
        mv.setViewName("/sys/department/add");
        // 设置返回的url
        SysDepartment sysDepartment = new SysDepartment();
        mv.addObject("sysDepartment", sysDepartment);
        BackUrlUtil.setBackUrl(mv, request);
        return mv;
    }

    /**
     * what: 到修改部门页面
     *
     * @param request  request
     * @param response response
     * @param id       部门id
     *
     * @return 到修改部门页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysDepartment sysDepartment = sysDepartmentService.get(id);
        String ztree = sysDepartmentService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("sysDepartment", sysDepartment);
        mv.addObject("listType", sysDicService.getByCategory("DEPARTMENT_TYPE"));
        mv.setViewName("/sys/department/update");
        // 设置返回的url
        BackUrlUtil.setBackUrl(mv, request);
        return mv;
    }

    /**
     * what: 新增部门
     *
     * @param request       request
     * @param response      response
     * @param sysDepartment sysDepartment
     *
     * @return 到操作提示页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, @Valid SysDepartment sysDepartment) {
        if (sysDepartment.getParentId() == null) {
            sysDepartment.setParentId(0);
        }
        // 创建人
        sysDepartment.setCreatorId(SessionUtil.getUserId(request));
        sysDepartment.setCreatorRealName(SessionUtil.getUserRealName(request));
        // 修改人
        sysDepartment.setLastEditorId(SessionUtil.getUserId(request));
        sysDepartment.setLastEditorRealName(SessionUtil.getUserRealName(request));
        int flag = sysDepartmentService.add(sysDepartment);
        if (flag == 0) {
            // msg=" + StringUtil.encodeUrl("部门新增失败");
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        } else if (flag == 2) {
            // msg=" + StringUtil.encodeUrl("部门名称已存在！");
            return "forward:/error.htm?resultCode=20003";
        } else {
            // msg=" + StringUtil.encodeUrl("部门新增成功");
            return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;
        }
    }

    /**
     * what: 修改部门
     *
     * @param request       request
     * @param response      response
     * @param sysDepartment sysDepartment
     *
     * @return 到操作提示页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @Valid SysDepartment sysDepartment) {
        if (sysDepartment.getParentId() == null) {
            sysDepartment.setParentId(0);
        }
        if (sysDepartment.getId() == sysDepartment.getParentId()) {
            // 不能和上级部门一样
            return "forward:/error.htm?resultCode=20106";
        } else {
            // 修改人
            sysDepartment.setLastEditorId(SessionUtil.getUserId(request));
            sysDepartment.setLastEditorRealName(SessionUtil.getUserRealName(request));
            int flag = sysDepartmentService.update(sysDepartment);
            if (flag == 0) {
                // 部门修改失败
                return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
            } else if (flag == 2) {
                // 存在相同的部门名称，不能修改
                return "forward:/error.htm?resultCode=20003";
            } else {
                // 部门修改成功
                return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;
            }
        }
    }

    /**
     * what: 删除部门
     *
     * @param request  request
     * @param response response
     * @param id       部门id
     *
     * @return 到操作提示页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysDepartmentService.delete(id);
        if (flag == 0) {
            // 删除失败
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        } else if (flag == 2) {
            // 还有下级部门，不能删除
            return "forward:/error.htm?resultCode=20107";
        } else {
            // 删除成功
            return "forward:/success.htm?resultCode=" + GlobalCode.DELETE_SUCCESS;
        }
    }

    /**
     * what: 查询系统用户
     *
     * @param request         request
     * @param response        response
     * @param sysUserSearchVO sysUserSearchVO
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/searchUser")
    public void searchUser(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        int recordCount = sysUserService.countUserByDepartmentId(sysUserSearchVO.getDepartmentId());
        List<SysUser> list = sysUserService.listUserByDepartmentId(sysUserSearchVO.getDepartmentId());
        WebUtil.out(response, JsonUtil.createDataTablePageJson(sysUserSearchVO.getPageIndex(), recordCount, JsonUtil.toStr(list)));
    }
}
