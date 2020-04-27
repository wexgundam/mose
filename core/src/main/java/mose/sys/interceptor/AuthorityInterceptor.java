/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.interceptor;

import mose.core.code.GlobalCode;
import mose.core.date.DateUtil;
import mose.core.json.JsonUtil;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.session.UserSession;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.service.SysLogService;
import mose.sys.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * what: 系统权限控制拦截器，访问每个操作时先进行权限校验,同时记录日志
 * 
 *
 * @author 孔垂云 created on 2017年6月13日
 */
public class AuthorityInterceptor implements HandlerInterceptor {
	/**
	 * 系统角色处理service
	 */
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 系统日志service
     */
    @Autowired
    private SysLogService sysLogService;
    /**
     * 全局参数配置
     */
    @Autowired
    private PubConfig pubConfig;
	/**
	 * 定义日志输出位置
	 */
    private static Logger logger = LoggerFactory.getLogger("operationLog");

    /**
     * 
     * why:     拦截处理
     * how:     校验权限，如果无权限，提示权限不足
     * @see HandlerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)
     *
     * @param request request
     * @param response response
     * @param handler 处理器
     * @return boolean
     * @throws Exception Exception
     *
     * @author 孔垂云 created on 2017年6月13日
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserSession userSession = SessionUtil.getUserSession(request);
        //校验权限
        String path = request.getServletPath();
        path = path.substring(1, path.length());
        String operaMethod = path.substring(path.lastIndexOf("/"));
        operaMethod = operaMethod.substring(1, operaMethod.length());
        String parameters = StringUtil.getOperaParams(request);
        //记操作日志
        logOperation(path, parameters, userSession);
        //目前只校验add/update/delete/save/import开头的方法，其余不校验
        boolean checked = sysRoleService.checkAuthority(userSession.getRoleId(), path);
        if (checked) {
            if (checkUrl(operaMethod)) {
                //记录数据库日志
                sysLogService.addLog(userSession.getUserId(), path, parameters, userSession.getUserIp());
            }
            return true;
        } else {
            boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);
            if (isAjaxRequest) {
                WebUtil.out(response, JsonUtil.createOperaStr(false, "权限不足"));
            } else {
//                String location = pubConfig.getDynamicServer() + "/error.htm?msg=" + StringUtil.encodeUrl("权限不足");
                String location = pubConfig.getDynamicServer() + "/error.htm?resultCode=" + GlobalCode.NO_AUTH;
                String str = "<script>location.href='" + location + "';</script>";
                WebUtil.out(response, str);
            }
            return false;
        }
    }

    /**
     *
     * what: 记录文本日志
     *
     * @param path 路径
     * @param parameters 参数
     * @param us 用户session
     *
     * @author 孔垂云 created on 2017年6月13日
     */
    public void logOperation(String path, String parameters, UserSession us) {
        String log = "";
        log = "[OPERALOG]" + "-[" + us.getUserIp() + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[" + us.getUsername() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }
	/**
	 *
	 * why:继承接口实现类
	 * @see HandlerInterceptor#postHandle(HttpServletRequest,
	 * HttpServletResponse, Object, ModelAndView)
	 *
	 * @param request request
	 * @param response response
	 * @param handler handler
	 * @param modelAndView modelAndView
	 * @throws Exception Exception
	 *
	 * @author 孔垂云 created on 2017年11月14日
	 */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
	/**
	 *
	 * why: 继承接口实现类
	 * @see HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
	 *
	 * @param request request
	 * @param response response
	 * @param handler handler
	 * @param ex ex
	 * @throws Exception Exception
	 *
	 * @author 孔垂云 created on 2017年6月13日
	 */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    /**
     * 
     * what: 检查请求方法是否包含add|update|delete|save|import
     * 
     * @param url url
     * @return boolean
     *
     * @author 孔垂云 created on 2017年6月13日
     */
    private static boolean checkUrl(String url) {
        Pattern pattern = Pattern.compile("^(add|update|delete|save|import).*");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
    /**
     * 
     * what:  测试方法
     * 
     * @param args 参数组
     *
     * @author 孔垂云 created on 2017年6月13日
     */
    public static void main(String[] args) {
        //当条件满足时，将返回true，否则返回false
        System.out.println(AuthorityInterceptor.checkUrl("aupdate.htm"));
    }
}
