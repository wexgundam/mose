package mose.sys.controller;

import mose.core.code.GlobalCode;
import mose.core.page.PageNavigate;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysResource;
import mose.sys.model.SysRole;
import mose.sys.service.SysResourceService;
import mose.sys.service.SysRoleService;
import mose.sys.vo.SysRoleSearchVO;
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
 * what: 系统角色处理Controller
 * 
 *
 * @author mose created on 2017年11月6日
 */
@RequestMapping("/sys/role")
@Controller
public class SysRoleController {
	/**
	 * 角色Service
	 */
	@Autowired
	private SysRoleService sysRoleService;
	/**
	 * 全局参数配置
	 */
	@Autowired
	private PubConfig pubConfig;
	/**
	 * 资源Service
	 */
	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * 
	 * what: 进入角色维护界面
	 * 
	 * @param request request
	 * @param response response
	 * @param sysRoleSearchVO 查询条件
	 * @return 到角色首页
	 *
	 * @author mose created on 2017年11月6日
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SysRoleSearchVO sysRoleSearchVO) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sys/role/index");
		// 获取查询总数
		int recordCount = sysRoleService.count(sysRoleSearchVO);
		String url = createUrl(sysRoleSearchVO);
		// 定义分页对象
		PageNavigate pageNavigate = new PageNavigate(url, sysRoleSearchVO.getPageIndex(), recordCount);
		List<SysRole> list = sysRoleService.list(sysRoleSearchVO);
		// 设置分页的变量
		mv.addObject("pageNavigate", pageNavigate);
		mv.addObject("list", list);
		// 设置返回url
		BackUrlUtil.createBackUrl(mv, request, url);
		return mv;
	}

	/**
	 * 
	 * what: 设置分页url，一般有查询条件的才会用到
	 * 
	 * @param sysRoleSearchVO 查询条件
	 * @return url
	 *
	 * @author mose created on 2017年11月6日
	 */
	private String createUrl(SysRoleSearchVO sysRoleSearchVO) {
		String url = pubConfig.getDynamicServer() + "/sys/role/index.htm?";
		// 如果为模糊查询，要把该字段encode
		if (StringUtil.isNotNullOrEmpty(sysRoleSearchVO.getName())) {
			url += "&name=" + sysRoleSearchVO.getName();
		}
		return url;
	}

	/**
	 * 
	 * what: 新增角色
	 * 
	 * @param request request
	 * @param response response
	 * @return 到角色新增界面
	 *
	 * @author mose created on 2017年11月6日
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sys/role/add");
		// 设置资源父节点
		int parentId = 0;
		// 所有菜单
		List<SysResource> listResource = sysResourceService.listByIsbuildin(parentId);
		mv.addObject("listModule", listResource);
		// 所有功能按钮
		List<SysResource> listFunction = sysResourceService.listByType(2);
		mv.addObject("listFunction", listFunction);
		// 设置返回的url
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 修改角色
	 * 
	 * @param request request
	 * @param response response
	 * @param id 角色id
	 * @return 到角色修改页面
	 *
	 * @author mose created on 2017年11月6日
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
		ModelAndView mv = new ModelAndView();
		SysRole sysRole = sysRoleService.get(id);
		mv.addObject("sysRole", sysRole);
		mv.setViewName("/sys/role/update");
		// 所有模块
		List<SysResource> listResource = sysResourceService.listByIsbuildin(0);
		mv.addObject("listModule", listResource);
		// 所有功能按钮
		List<SysResource> listFunction = sysResourceService.listByType(2);
		mv.addObject("listFunction", listFunction);
		String checkButton = sysRoleService.checkResourceAndFunction(sysRole.getId());
		mv.addObject("checkButton", checkButton);
		// 设置返回的url
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 新增角色
	 * 
	 * @param request request
	 * @param response response
	 * @param sysRole sysRole
	 * @return 到操作提示页面
	 *
	 * @author mose created on 2017年11月6日
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response, @Valid SysRole sysRole) {
		// 创建人
		sysRole.setCreatorId(SessionUtil.getUserId(request));
		sysRole.setCreatorRealName(SessionUtil.getUserRealName(request));
		// 修改人
		sysRole.setLastEditorId(SessionUtil.getUserId(request));
		sysRole.setLastEditorRealName(SessionUtil.getUserRealName(request));
		String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
		String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
		// 创建人的id
		sysRole.setCreateUserId(SessionUtil.getUserSession(request).getUserId());
		// 是否可删除
		sysRole.setDeletable(1);
		int flag = sysRoleService.add(sysRole, moduleArr, functionArr);
		if (flag == 0) {
			// 角色新增失败
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else if (flag == 2) {
			// msg=" + StringUtil.encodeUrl("角色名称已存在！");
			return "forward:/error.htm?resultCode=20004";
		} else {
			// 角色新增成功
			return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;
		}
	}

	/**
	 * 
	 * what: 修改角色
	 * 
	 * @param request request
	 * @param response response
	 * @param sysRole sysRole
	 * @return 到操作提示页面
	 *
	 * @author mose created on 2017年11月6日
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @Valid SysRole sysRole) {
		// 修改人
		sysRole.setLastEditorId(SessionUtil.getUserId(request));
		sysRole.setLastEditorRealName(SessionUtil.getUserRealName(request));

		String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
		String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
		int flag = sysRoleService.update(sysRole, moduleArr, functionArr);
		if (flag == 0) {
			// 角色修改失败
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else if (flag == 2) {
			// 存在相同的角色名称，不能修改
			return "forward:/error.htm?resultCode=20004";
		} else {
			// 角色修改成功
			return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;
		}
	}

	/**
	 * 
	 * what: 删除角色
	 * 
	 * @param request request
	 * @param response response
	 * @param id 角色id
	 * @return 到操作提示页面
	 *
	 * @author mose created on 2017年11月6日
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
		int flag = sysRoleService.delete(id);
		if (flag == 0) {
			// 角色删除失败
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else if (flag == 2) {
			// 存在用户，不能删除
			return "forward:/error.htm?resultCode=20108";
		}
		else {
			// 角色删除成功
			return "forward:/success.htm?resultCode=" + GlobalCode.DELETE_SUCCESS;
		}
	}
}
