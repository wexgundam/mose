/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.aop;

import mose.core.json.JsonUtil;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * what: 处理所有请求的异常
 * 
 *
 * @author mose created on 2017年6月13日
 */
@ControllerAdvice
public class PublicControllerAdvice {
	/**
	 * 定义日志输出位置
	 */
	private static Logger logger = LoggerFactory.getLogger("controllerLog");

	/**
	 * 
	 * what: 系统异常处理，比如：404,500
	 * 
	 * @param request request
	 * @param response response
	 * @param e 异常对象
	 * @return mv
	 * @throws Exception Exception
	 *
	 * @author mose created on 2017年6月13日
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		e.printStackTrace();
		logger.error("请求地址：" + request.getServletPath());
		logger.error("请求参数：" + StringUtil.getOperaParams(request));
		logger.error("异常：" + e.getMessage());
		// 判断是否是Ajax请求
		boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);
		// 获取异常的详细信息
		if (isAjaxRequest) {
			String msg = "{\"flag\":false,\"msg\":" + e.getMessage() + "}";
			WebUtil.out(response, JsonUtil.toStr(msg));
			return null;
		} else {
			// URL请求处理
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/serverError.htm");
			mv.addObject("message", e.getMessage());
			return mv;
		}
	}

	/**
	 * 
	 * what: 拦截@Valid请求参数验证不通过的异常
	 * 
	 * @param request request
	 * @param response response
	 * @param e 不通过的异常
	 * @return mv
	 *
	 * @author mose created on 2017年6月13日
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Order(0)
	public ModelAndView handleMethodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException e) {
		logger.info("请求的参数不正确", e);
		logger.error("请求路径：" + request.getServletPath());
		logger.error("请求参数：" + request.getParameterMap().toString());
		String validation_message;
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult != null && bindingResult.getFieldError() != null) {
			validation_message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			validation_message = e.getMessage();
		}
		logger.info("参数错误信息：" + validation_message);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/common/exception");
		mv.addObject("message", e.getMessage());
		return mv;
	}

	/**
	 * 
	 * what: 拦截@Valid请求参数验证不通过的异常
	 * 
	 * @param request request
	 * @param response response
	 * @param e 不通过的异常
	 * @return mv
	 *
	 * @author mose created on 2017年11月13日
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Order(0)
	public ModelAndView handleBindException(HttpServletRequest request, HttpServletResponse response, BindException e) {
		logger.info("请求的参数不正确", e);
		logger.error("请求路径：" + request.getServletPath());
		logger.error("请求参数：" + request.getParameterMap().toString());
		String validation_message;
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult != null && bindingResult.getFieldError() != null) {
			validation_message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			validation_message = e.getMessage();
		}
		logger.info("参数错误信息：" + validation_message);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/common/exception");
		mv.addObject("message", e.getMessage());
		return mv;
	}
}
