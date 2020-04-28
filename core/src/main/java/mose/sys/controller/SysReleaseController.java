/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:cdpf_core_web
 */
package mose.sys.controller;

import mose.core.code.GlobalCode;
import mose.core.page.PageNavigate;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysRelease;
import mose.sys.service.SysReleaseService;
import mose.sys.vo.SysReleaseSearchVO;
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
 * what: 系统升级 Controller. <br/>
 *
 * 
 * @author mose created on 2017年10月31日
 */
@Controller
@RequestMapping("/sys/release")
public class SysReleaseController {

	/**
	 * 系统升级说明 service
	 */
	@Autowired
	private SysReleaseService sysReleaseService;

	/**
	 * 配置信息
	 */
	@Autowired
	private PubConfig pubConfig;

	/**
	 * 
	 * what: 跳转到添加系统说明主页. <br/>
	 * 
	 * @param request request
	 * @param sysReleaseSearchVO sysReleaseSearchVo查询条件
	 * @return 跳转到系统升级说明主页
	 *
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, SysReleaseSearchVO sysReleaseSearchVO) {
		ModelAndView mv = new ModelAndView();
		String url = createUrl(sysReleaseSearchVO);
		List<SysRelease> list = sysReleaseService.list(sysReleaseSearchVO);

		// 获取查询总数
		int recordCount = sysReleaseService.count(sysReleaseSearchVO);
		// 定义分页对象
		PageNavigate pageNavigate = new PageNavigate(url, sysReleaseSearchVO.getPageIndex(), recordCount);
		// 把获取的记录放到mv里面
		mv.addObject("list", list);
		// 跳转至指定页面
		mv.setViewName("/sys/release/index");
		// 设置返回url
		BackUrlUtil.createBackUrl(mv, request, url);
		// 设置分页的变量
		mv.addObject("pageNavigate", pageNavigate);
		// 设置返回url
		BackUrlUtil.createBackUrl(mv, request, url);
		return mv;
	}

	/**
	 * 
	 * what: 设置返回的url. <br/>
	 * 
	 * @param sysReleaseSearchVO sysRlease查询条件
	 * @return 返回拼接的url
	 *
	 * @author mose created on 2017年10月31日
	 */
	private String createUrl(SysReleaseSearchVO sysReleaseSearchVO) {
		String url = pubConfig.getDynamicServer() + "/sys/release/index.htm?";
		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getName())) {
			url += "&name=" + sysReleaseSearchVO.getName();
		}

		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getVersion())) {
			url += "&version=" + sysReleaseSearchVO.getVersion();
		}

		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getReleaseBy())) {
			url += "&releaseBy=" + sysReleaseSearchVO.getReleaseBy();
		}

		if (StringUtil.isNotNullOrEmpty(sysReleaseSearchVO.getReleaseDate())) {
			url += "&releaseDate=" + sysReleaseSearchVO.getReleaseDate();
		}
		return url;
	}

	/**
	 * 
	 * what: 跳转到添加系统说明. <br/>
	 * 
	 * @param request request
	 * @return 跳转到添加系统说明页面
	 *
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sys/release/add");

		// 设置返回的url
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到系统升级信息更新页面. <br/>
	 * when: 点击修改时.<br/>
	 * 
	 * @param request request
	 * @param id 需要修改的系统说明信息的id
	 * @return 跳转到系统修改系统说明页面
	 *
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView();
		SysRelease sysRelease = sysReleaseService.get(id);
		mv.setViewName("/sys/release/update");
		mv.addObject("sysRelease", sysRelease);
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到查看升级信息详情页面. <br/>
	 * 
	 * @param request request
	 * @param id 将要查看的系统说明的id
	 * @return 跳转到查看详情页面
	 *
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/toDetail")
	public ModelAndView toDetail(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView();
		SysRelease sysRelease = sysReleaseService.get(id);
		mv.setViewName("/sys/release/detail");
		mv.addObject("sysRelease", sysRelease);
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 添加系统升级信息. <br/>
	 * when: 添加系统升级信息时，点击保存.<br/>
	 * 
	 * @param request request
	 * @param sysRelease 添加的系统升级说明信息
	 * @param content 系统升级说明
	 * @return 返回操作结果，0表示新增失败,1表示成功
	 *
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request, @Valid SysRelease sysRelease, String content) {
		String basePath = pubConfig.getFileUploadPath();
		int flag = sysReleaseService.add(sysRelease, basePath, content);

		if (flag == 0) {
			// 字典信息新增失败;
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else {
			// 字典信息新增成功;
			return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;
		}
	}

	/**
	 * 
	 * what: 更新系统升级信息. <br/>
	 * when: 修改页面中，点击保存.<br/>
	 * 
	 * @param request request
	 * @param response response
	 * @param sysRelease 修改后的信息
	 * @param content 发布内容
	 * @return 返回处理结果
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @Valid SysRelease sysRelease,
			String content) {
		String basePath = pubConfig.getFileUploadPath();
		int flag = sysReleaseService.update(sysRelease, basePath, content);
		if (flag == 0) {
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else {
			return "forward:/success.htm?resultCode=" + GlobalCode.SAVE_SUCCESS;
		}
	}

	/**
	 * 
	 * what: 删除系统升级信息. <br/>
	 * when: 点击删除.<br/>
	 * 
	 * @param id 需要删除的系统说明的id
	 * @return 0表示失败，1表示成功
	 *
	 * @author mose created on 2017年10月31日
	 */
	@RequestMapping("/delete")
	public String delete(int id) {
		int flag = sysReleaseService.delete(id);
		if (flag == 0) {
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else {
			return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
		}
	}

	/**
	 * 
	 * what: 从指定路径中读取文件. <br/>
	 * when: 添加，修改，查看系统详情时使用 .<br/>
	 * 
	 * @param request request
	 * @param response response
	 * @param path 文件路径
	 * 
	 * @author mose created on 2017年10月30日
	 */
	@RequestMapping("/readFile")
	public void readFile(HttpServletRequest request, HttpServletResponse response, String path) {
		WebUtil.out(response, sysReleaseService.readFile(pubConfig.getFileUploadPath(), path, request));
	}

	/**
	 * 
	 * what: 检查系统版本号是否唯一. <br/>
	 * 
	 * @param request request
	 * @param response response
	 * @param name 版本名称
	 * @param version 版本号
	 *
	 * @author mose created on 2017年10月30日
	 */

	/**
	 * 
	 * what: 检查系统版本号是否唯一. <br/>
	 * 
	 * @param response response
	 * @param preName 之前的版本名称
	 * @param preVersion 之前的版本名称
	 * @param name 版本名称
	 * @param version 版本号
	 *
	 * @author mose created on 2017年11月16日
	 */
	@RequestMapping("/checkVersionExist")
	public void checkVersionExist(HttpServletResponse response, String preName, String preVersion, String name,
			String version) {

		int flag = sysReleaseService.checkVersionExist(preName, preVersion, name, version);

		if (flag == 0) {
			WebUtil.out(response, "{\"result\":" + 0 + ",\"message\":\"" + "与修改前相同" + "\"}");
		} else if (flag == 1) {
			WebUtil.out(response, "{\"result\":" + 1 + ",\"message\":\"" + "数据库中不存在" + "\"}");
		} else {
			WebUtil.out(response, "{\"result\":" + 2 + ",\"message\":\"" + "数据库中已存在" + "\"}");
		}
	}

}
