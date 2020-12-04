/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:web
 */
package mose.sys.controller;

import mose.core.time.DateUtil;
import mose.core.json.JsonUtil;
import mose.core.page.PageNavigate;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysUserLogin;
import mose.sys.service.SysUserLoginService;
import mose.sys.service.SysUserService;
import mose.sys.vo.SysUserLoginSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * what:   用户登录记录查询Controller
 * 
 *
 * @author mose created on 2017年10月19日
 */
@Controller
@RequestMapping("/sys/userlogin")
public class SysUserLoginController {
	/**
	 * 用户登录记录service
	 */
	@Autowired
	private SysUserLoginService sysUserLoginService;
	/**
	 * 全局参数配置
	 */
	@Autowired
	private PubConfig pubConfig;
	/**
	 * 系统用户service
	 */
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 
	 * what:    进入用户登录记录主页
	 * 
	 * @param request request
	 * @param sysUserLoginSearchVO 用户登录记录查询VO
	 * @return index
	 *
	 * @author mose created on 2017年10月19日
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, SysUserLoginSearchVO sysUserLoginSearchVO) {
		// 设置查询起始日期
		if (StringUtil.isNullOrEmpty(sysUserLoginSearchVO.getStartDate())) {
			sysUserLoginSearchVO.setStartDate(DateUtil.getOperaDate(DateUtil.getSystemDate(), -30));
		}
		// 设置截止日期
		if (StringUtil.isNullOrEmpty(sysUserLoginSearchVO.getEndDate())) {
			sysUserLoginSearchVO.setEndDate(DateUtil.getSystemDate());
			}
		ModelAndView mv = new ModelAndView();
		// 获取查询总数
		int recordCount = sysUserLoginService.count(sysUserLoginSearchVO);
		String url = createUrl(sysUserLoginSearchVO);
		PageNavigate pageNavigate = new PageNavigate(url, sysUserLoginSearchVO.getPageIndex(), recordCount);
		List<SysUserLogin> list = sysUserLoginService.list(sysUserLoginSearchVO);
		// 设置分页的变量
		mv.addObject("pageNavigate", pageNavigate);
		// 把获取的记录放到mv里面
		mv.addObject("list", list);
		// 跳转至指定页面
		mv.setViewName("/sys/userlogin/index");
		// 设置返回url
		BackUrlUtil.createBackUrl(mv, request, url);
		return mv;
	}
	/**
	 * 
	 * what:    异步请求用户登录信息
	 * 
	 * @param request request
	 * @param response response
	 * @param sysUserloginSearchVO 用户登陆记录查询VO
	 *
	 * @author mose created on 2017年11月3日
	 */
	@RequestMapping("/searchUserLogin")
	public void searchUserLogin(HttpServletRequest request, HttpServletResponse response, SysUserLoginSearchVO sysUserloginSearchVO) {
		// 获取查询总数
		int recordCount = sysUserLoginService.count(sysUserloginSearchVO);
		List<SysUserLogin> list = sysUserLoginService.list(sysUserloginSearchVO);
		WebUtil.out(response, JsonUtil.createDataTablePageJson(sysUserloginSearchVO.getPageIndex(), recordCount, JsonUtil.toString(list)));
	}

	/**
	 * 
	 * what:    自动补全账号数据列表
	 * 
	 * @param response response
	 *
	 * @author mose created on 2017年10月19日
	 */
	@RequestMapping("/getAutoComplteList")
	public void getAutoComplteList(HttpServletResponse response) {
		WebUtil.out(response, JsonUtil.toString(sysUserService.listAll()));
	}

	/**
	 * 
	 * what:    设置分页url
	 * 
	 * @param sysUserLoginSearchVO 用户登陆记录查询VO
	 * @return backUrl
	 *
	 * @authormose created on 2017年6月13日
	 */
	private String createUrl(SysUserLoginSearchVO sysUserLoginSearchVO) {
		String url = pubConfig.getDynamicServer() + "/sys/userlogin/index.htm?";
		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getUsername())) {
			url += "&username=" + sysUserLoginSearchVO.getUsername();
		}
		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getLoginIp())) {
			url += "&loginIp=" + sysUserLoginSearchVO.getLoginIp();
		}
		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getStartDate())) {
			url += "&startDate=" + sysUserLoginSearchVO.getStartDate();
		}
		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getEndDate())) {
			url += "&endDate=" + sysUserLoginSearchVO.getEndDate();
		}
		return url;
	}

}
