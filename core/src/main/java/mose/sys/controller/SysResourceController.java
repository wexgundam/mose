package mose.sys.controller;

import mose.core.cache.EhCacheUtil;
import mose.core.code.GlobalCode;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysResource;
import mose.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 *
 * what:   系统资源管理Controller
 *
 * @author 孔垂云 created on 2017年06月13日
 */
@Controller
@RequestMapping("/sys/resource")
public class SysResourceController  {
    /**
     * 系统资源service
     */
    @Autowired
    private SysResourceService sysResourceService;
    /**
     * 系统配置类
     */
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入资源维护界面
     * 显示所有模块列表，treeGrid显示
     * @param request request
     * @return ModelAndView
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/resource/index");
        List<SysResource> list = sysResourceService.list();
        // 把获取的记录放到mv里面
        mv.addObject("list", list);
        String url = pubConfig.getDynamicServer() + "/sys/resource/index.htm?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }


    /**
     *
     * what:    跳转至新增模块页面
     *
     * @param request request
     * @param response response
     * @param sysResource 系统资源实体类
     * @return ModelAndView
     *
     * @author 郭飞 created on 2017年10月25日
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysResource sysResource) {
        ModelAndView mv = new ModelAndView();
        //初始化模块列表，首先查询应用系统级别的资源
        String ztree = sysResourceService.createZtreeByResourceLevel(0);
        mv.addObject("ztree", ztree);
        if (sysResource.getParentId() != null && sysResource.getParentId() != 0) {
            SysResource parent = sysResourceService.get(sysResource.getParentId());
            sysResource.setParentName(parent.getName());
            //设置为增加功能界面
            sysResource.setType(2);
        } else {
            sysResource.setType(1);
        }

        mv.addObject("sysResource", sysResource);
        mv.setViewName("/sys/resource/add");
        // 设置返回的url
        BackUrlUtil.setBackUrl(mv, request);
        return mv;
    }

    /**
     *  ajax初始化资源等级树
     * @param request request
     * @param response response
     * @author 郭飞 created on 2017年11月14日
     */
    @RequestMapping("/initTree")
    public void    initTree(HttpServletRequest request, HttpServletResponse response){
        int resourceLevel = Integer.parseInt(request.getParameter("resourceLevel"));
        String ztree = sysResourceService.createZtreeByResourceLevel(resourceLevel);
        WebUtil.out(response,"["+ztree+"]");
    }
    /**
     *
     * what:    跳转到新增功能页面
     *
     * @param request request
     * @param response response
     * @param sysResource 系统资源实体类
     * @return ModelAndView
     *
     * @author 郭飞 created on 2017年10月25日
     */
    @RequestMapping("/toFunctionAdd")
    public ModelAndView toFunctionAdd(HttpServletRequest request, HttpServletResponse response, SysResource sysResource) {
        ModelAndView mv = new ModelAndView();
        //模块列表
        String ztree = sysResourceService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        if (sysResource.getParentId() != null && sysResource.getParentId() != 0) {
            SysResource parent = sysResourceService.get(sysResource.getParentId());
            sysResource.setParentName(parent.getName());
            //设置为增加功能界面
            sysResource.setType(2);
        } else {
            sysResource.setType(1);
        }
        mv.addObject("sysResource", sysResource);
        mv.setViewName("/sys/resource/functionAdd");
        // 设置返回的url
        BackUrlUtil.setBackUrl(mv, request);
        return mv;
    }

    /**
     *
     * what:    跳转到修改模块页面
     *
     * @param request request
     * @param response response
     * @param id 资源id
     * @return ModelAndView
     *
     * @author 郭飞 created on 2017年10月25日
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysResource sysResource = sysResourceService.get(id);
        String ztree = sysResourceService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("sysResource", sysResource);
        mv.setViewName("/sys/resource/update");
        // 设置返回的url
        BackUrlUtil.setBackUrl(mv, request);
        return mv;
    }

    /**
     *
     * what:    修改功能
     *
     * @param request request
     * @param response response
     * @param id 资源id
     * @return ModelAndView
     *
     * @author 郭飞 created on 2017年10月25日
     */
    @RequestMapping("/toFunctionUpdate")
    public ModelAndView toFunctionUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysResource sysResource = sysResourceService.get(id);
        String ztree = sysResourceService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("sysResource", sysResource);
        mv.setViewName("/sys/resource/functionUpdate");
        // 设置返回的url
        BackUrlUtil.setBackUrl(mv, request);
        return mv;
    }
    /**
     *
     * what:   新增系统资源
     *
     * @param request request
     * @param response response
     * @param sysResource 资源实体类
     * @return String
     *
     * @author 孔垂云  created on 2017年11月13日
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, @Valid SysResource sysResource) {
        if (sysResource.getParentId() == null) {
            sysResource.setParentId(0);
        }

        int flag = sysResourceService.add(sysResource);
        if (flag == 0) {
            //msg=" + StringUtil.encodeUrl("资源新增失败");
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        } else if (flag == 2) {
            //msg=" + StringUtil.encodeUrl("资源代码已存在");
            return "forward:/error.htm?resultCode=" + GlobalCode.CODEEXIST_FAILURE;
        } else {
            int tab = sysResource.getResourceLevel();
            String backUrl = request.getParameter("backUrl") + "&tab=" + tab;
            //清空缓存
            EhCacheUtil.removeAll("sysCache");
            // 资源修改成功
            return "forward:/success.htm?backUrl=" + StringUtil.encodeUrl(backUrl) + "&resultCode=" + GlobalCode.SAVE_SUCCESS;
        }
    }

    /**
     *
     * what:   修改系统资源
     *
     * @param request request
     * @param response response
     * @param sysResource 资源实体类
     * @return String
     *
     * @author 孔垂云  created on 2017年11月13日
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @Valid SysResource sysResource) {

        if (sysResource.getParentId() == null) {
            sysResource.setParentId(0);
        }
        if (sysResource.getId() == sysResource.getParentId()) {
            //不能和上级节点一样
            return "forward:/error.htm?resultCode=20101";
        } else {
            int flag = sysResourceService.update(sysResource);
            if (flag == 0) {
                //资源修改失败
                return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
            } else if (flag == 2) {
                //上级节点不存在
                return "forward:/error.htm?resultCode=20102";
            } else {
                //清空缓存
                EhCacheUtil.removeAll("sysCache");
                int tab = sysResource.getResourceLevel();
                String backUrl = request.getParameter("backUrl") + "&tab=" + tab;
                // 资源修改成功
                return "forward:/success.htm?backUrl=" + StringUtil.encodeUrl(backUrl) + "&resultCode=" + GlobalCode.SAVE_SUCCESS;
            }
        }
    }

    /**
     *
     * what:  删除系统资源
     *
     * @param request request
     * @param response response
     * @param id 资源id
     * @return String
     *
     * @author 孔垂云 created on 2017年11月13日
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysResourceService.delete(id);
        if (flag == 0) {
            //删除失败
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        } else if (flag == 2) {
            //还有下级节点，不能删除
            return "forward:/error.htm?resultCode=20103";
        } else {
            //清空缓存
            EhCacheUtil.removeAll("sysCache");
            //删除成功
            return "forward:/success.htm?resultCode=" + GlobalCode.DELETE_SUCCESS;
        }

    }



    /**
     *
     * what: 进入功能维护列表
     *
     * @param request request
     * @param parentId 上级节点
     * @param resourceName 资源名称
     * @return ModelAndView
     *
     * @author 孔垂云  created on 2017年11月13日
     */
    @RequestMapping("/functionIndex")
    public ModelAndView functionIndex(HttpServletRequest request, int parentId, String resourceName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/resource/functionIndex");
        List<SysResource> list = sysResourceService.listByParentId(parentId, 2);
        // 把获取的记录放到mv里面
        mv.addObject("list", list);
        //取得父节点放入mv
        mv.addObject("resource", sysResourceService.get(parentId));
        mv.addObject("resourceName", resourceName);
        String url = pubConfig.getDynamicServer() + "/sys/resource/functionIndex.htm?parentId=" + parentId;
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

}
