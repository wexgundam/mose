/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.controller;

import mose.core.date.DateUtil;
import mose.core.json.JsonUtil;
import mose.core.page.PageNavigate;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysLog;
import mose.sys.service.SysLogService;
import mose.sys.service.SysUserService;
import mose.sys.vo.SysLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * what: 系统日志查询Controller
 * 
 *
 * @author 孔垂云 created on 2017年6月13日
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	/**
	 * 日志service
	 */
	@Autowired
	private SysLogService sysLogService;
	/**
	 * 系统用户service
	 */
	@Autowired
	private SysUserService sysUserService;
	/**
	 * 全局参数配置
	 */
	@Autowired
	private PubConfig pubConfig;

	/**
	 * 
	 * what:    进入日志查看界面
	 * 
	 * @param request request
	 * @param sysLogSearchVO 日志查询VO
	 * @return index
	 *
	 * @author 杨超凡 created on 2017年11月2日
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, SysLogSearchVO sysLogSearchVO) {
		// 设置查询起始日期
		if (StringUtil.isNullOrEmpty(sysLogSearchVO.getStartDate())) {
			sysLogSearchVO.setStartDate(DateUtil.getOperaDate(DateUtil.getSystemDate(), -30));
		}
		// 设置截止日期
		if (StringUtil.isNullOrEmpty(sysLogSearchVO.getEndDate())) {
			sysLogSearchVO.setEndDate(DateUtil.getSystemDate()); 
		}
		ModelAndView mv = new ModelAndView(); 
		// 获取查询总数
		int recordCount = sysLogService.count(sysLogSearchVO); 
		String url = createUrl(sysLogSearchVO); 
		PageNavigate pageNavigate = new PageNavigate(url, sysLogSearchVO.getPageIndex(), recordCount);
		List<SysLog> list = sysLogService.list(sysLogSearchVO); 
		// 设置分页的变量 
		mv.addObject("pageNavigate", pageNavigate); 
		// 把获取的记录放到mv里面 
		mv.addObject("list", list); 
		// 用户列表 
		mv.addObject("listUser", sysUserService.listAll()); 
		// 跳转至指定页面 
		mv.setViewName("/sys/log/index"); 
		// 设置返回url 
		BackUrlUtil.createBackUrl(mv, request, url);
		return mv; 
	}

	/**
	 * 
	 * what:    异步加载操作参数
	 * 
	 * @param response response
	 * @param id 操作记录id
	 *
	 * @author 杨超凡 created on 2017年11月6日
	 */
	@RequestMapping("/operaparams")
	public void ajaxParam(HttpServletResponse response, String id) {
		SysLog sysLog = sysLogService.getById(id);
		WebUtil.out(response, sysLog.getOperaParams());
	}

	/**
	 * 
	 * what:    自动补全账号数据列表
	 * 
	 * @param response response
	 *
	 * @author 杨超凡 created on 2017年11月6日
	 */
	@RequestMapping("/getAutoComplteList")
	public void getAutoComplteList(HttpServletResponse response) {
		WebUtil.out(response, JsonUtil.toStr(sysUserService.listAll()));
	}

	/**
	 * 
	 * what: 异步加载用户操作日志
	 * 
	 * @param request request
	 * @param response response
	 * @param sysLogSearchVO 日志查询VO
	 *
	 * @author 杨超凡 created on 2017年11月3日
	 */
	@RequestMapping("/searchUserOper")
	public void searchUserLogin(HttpServletRequest request, HttpServletResponse response, SysLogSearchVO sysLogSearchVO) {
		// 获取查询总数
		int recordCount = sysLogService.count(sysLogSearchVO); 
		List<SysLog> list = sysLogService.list(sysLogSearchVO); 
		WebUtil.out(response, JsonUtil.createDataTablePageJson(sysLogSearchVO.getPageIndex(), recordCount, JsonUtil.toStr(list))); 
	}

	/**
	 * 
	 * what:    设置分页url
	 * 
	 * @param sysLogSearchVO 日志查询VO
	 * @return backUrl
	 *
	 * @author孔垂云 created on 2017年6月13日
	 */
	private String createUrl(SysLogSearchVO sysLogSearchVO) {
		String url = pubConfig.getDynamicServer() + "/sys/log/index.htm?";
		if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getUsername())) {
			url += "&username=" + sysLogSearchVO.getUsername();
		}
		if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getStartDate())) {
			url += "&startDate=" + sysLogSearchVO.getStartDate();
		}
		if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getEndDate())) {
			url += "&endDate=" + sysLogSearchVO.getEndDate();
		}
		return url;
	}

}
