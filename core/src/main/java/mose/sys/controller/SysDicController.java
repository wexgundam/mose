/**
 * Copyright 2017 弘远技术研发中心. 
 *  All rights reserved
 * Project Name:cdpf_v1
 * Module Name:cdpf_core_web
 */
package mose.sys.controller;

import mose.core.code.GlobalCode;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysDic;
import mose.sys.service.SysDicService;
import mose.sys.vo.SysDicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 
 * 字典信息 Controller
 * @author: 刘正荣
 * @date: 2017年10月19日
 */

/**
 * 
 * what: 字典信息 Controller. <br/>
 * when: (这里描述这个类的适用时机 – 可选).<br/>
 * how: (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 * 
 *
 * @author 刘正荣 created on 2017年10月31日
 */
@Controller
@RequestMapping("/sys/dic")
public class SysDicController {

	/**
	 * 字典 Service
	 */
	@Autowired
	private SysDicService sysDicService;

	/**
	 * 配置信息
	 */
	@Autowired
	private PubConfig pubConfig;

	/**
	 * 
	 * what: 跳转到字典信息首页. <br/>
	 * 
	 * @param request request
	 * @return 跳转到字典信息首页
	 *
	 * @author 刘正荣 created on 2017年10月31日
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<SysDicVO> list = sysDicService.listAll();
		String url = pubConfig.getDynamicServer() + "/sys/dic/index.htm";
		// 把获取的记录放到mv里面
		mv.addObject("list", list);
		// 跳转至指定页面
		mv.setViewName("/sys/dic/index");
		// 设置返回url
		BackUrlUtil.createBackUrl(mv, request, url);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到添加字典页面. <br/>
	 * 
	 * @param request request
	 * @return 跳转到添加字典页面
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sysDic", new SysDic());
		mv.setViewName("/sys/dic/add");
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到添加类别页面. <br/>
	 * 
	 * @param request request
	 * @return 跳转到添加字典类别页面
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/toAddCategory")
	public ModelAndView toAddCategory(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sysDic", new SysDic());
		mv.setViewName("/sys/dic/addCategory");
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到更新字典页面. <br/>
	 * 
	 * @param request request
	 * @param id 要更新的字典信息的id
	 * @return 跳转到更新字典页面
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView();
		SysDic sysDic = sysDicService.get(id);
		mv.setViewName("/sys/dic/update");
		mv.addObject("sysDic", sysDic);
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到更新类别页面. <br/>
	 * 
	 * @param request request
	 * @param id 需要修改的字典类别id
	 * @return 跳转到修改字典类别页面
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/toUpdateCategory")
	public ModelAndView toUpdateCategory(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView();
		SysDic sysDic = sysDicService.get(id);
		mv.setViewName("/sys/dic/updateCategory");
		mv.addObject("sysDic", sysDic);
		BackUrlUtil.setBackUrl(mv, request);
		return mv;
	}

	/**
	 * 
	 * what: 跳转到字典明细. <br/>
	 * 
	 * @param request request
	 * @param category 字典类别
	 * @return 跳转到字典明细
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/toMaintenance")
	public ModelAndView toMaintenance(HttpServletRequest request, String category) {
		ModelAndView mv = new ModelAndView();
		List<SysDic> sysDicList = sysDicService.getAllCategory(category);
		mv.setViewName("/sys/dic/maintenance");
		BackUrlUtil.setBackUrl(mv, request);
		mv.addObject("sysDicList", sysDicList);
		return mv;
	}

	/**
	 * 
	 * what: 添加字典. <br/>
	 * 
	 * @param sysDic 添加的字典对象
	 * @return 0表示添加失败，1表示添加成功
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/add")
	public String add(@Valid SysDic sysDic) {
		int flag = sysDicService.add(sysDic);

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
	 * what: 处理字典维护请求. <br/>
	 * 
	 * @param sysDicList 需要维护的字典信息，其中sysDicList中分别存储新增的字典信息，修改的字典信息，删除的字典信息
	 * @param response response
	 * @param request request
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/maintenanceSysDic")
	@ResponseBody
	public void maintenanceSysDic(@RequestBody List<List<SysDic>> sysDicList, HttpServletResponse response,
			HttpServletRequest request) {
		String msg = null;
		int flag = sysDicService.maintenanceSysDic(sysDicList);
		if (flag == 1) {
			msg = GlobalCode.OPERA_SUCCESS;
			WebUtil.out(response, "{\"result\":" + true + ",\"message\":\"" + msg + "\"}");
		} else {
			msg = GlobalCode.OPERA_FAILURE;
			WebUtil.out(response, "{\"result\":" + false + ",\"message\":\"" + msg + "\"}");
		}
	}

	/**
	 * 
	 * what: 删除字典信息. <br/>
	 * 
	 * @param category 需要删除的字典类别
	 * @return 0表示删除成功，1表示删除失败
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/delete")
	public String delete(String category) {
		int flag = sysDicService.deleteCategory(category);
		if (flag == 0) {
			return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
		} else {
			return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
		}
	}

	/**
	 * 
	 * what: 用于判断字典类别或字典类别信息是否唯一. <br/>
	 * 
	 * @param response response
	 * @param param 有四个值：category,categoryName,code,name
	 * @param prev 修改前的值
	 * @param value category,categoryName,code,name修改后的值
	 * @param category 字典信息的类别
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/checkDicExist")
	public void checkDicExist(HttpServletResponse response, String param, String prev, String value, String category) {
		int flag = sysDicService.checkCategoryExist(param, prev, value, category);

		if (flag == 0) {
			WebUtil.out(response, "{\"result\":" + 0 + ",\"message\":\"" + "与修改前相同" + "\"}");
		} else if (flag == 1) {
			WebUtil.out(response, "{\"result\":" + 1 + ",\"message\":\"" + "数据库中不存在" + "\"}");
		} else {
			WebUtil.out(response, "{\"result\":" + 2 + ",\"message\":\"" + "数据库中已存在" + "\"}");
		}
	}

	/**
	 * 
	 * what: 用于判断类别下是否存在字典信息. <br/>
	 * when: 删除时使用 .<br/>
	 * 
	 * @param response response
	 * @param category 字典类别
	 *
	 * @author 刘正荣 created on 2017年11月1日
	 */
	@RequestMapping("/checkCateoryExistDic")
	public void checkCateoryExistDic(HttpServletResponse response, String category) {
		boolean flag = sysDicService.checkCateoryExistDic(category);

		if (flag) {
			WebUtil.out(response, "{\"result\":" + true + ",\"message\":\"" + "可以删除" + "\"}");
		} else {
			WebUtil.out(response, "{\"result\":" + false + ",\"message\":\"" + "不能删除" + "\"}");
		}
	}

}
