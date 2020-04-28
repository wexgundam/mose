/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.controller;

import mose.core.image.VerifyCodeUtil;
import mose.core.json.JsonUtil;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.session.UserSession;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysUser;
import mose.sys.model.SysUserLogin;
import mose.sys.service.SysLoginService;
import mose.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * what:    系统登录校验，及首页显示
 *
 * @author mose created on 2017年6月13日
 */
@RequestMapping("/")
@Controller
public class SysLoginController {
    /**
     * 系统用户service
     */
    @Autowired
    private SysUserService sysUserService;
    /**
     * 用户登录service
     */
    @Autowired
    private SysLoginService userLoginService;
    /**
     * 全局配置
     */
    @Autowired
    private PubConfig pubConfig;
    /**
     * redis操作template
     */
//    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * what:    进入系统登录界面
     *
     * @param request  request
     * @param response response
     *
     * @return login
     *
     * @author mose created on 2017年6月13日
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login");
        return mv;
    }

    /**
     * what:    用户登录校验
     * when:    用户登录系统时
     *
     * @param request  request
     * @param response response
     * @param username 用户名
     * @param password 密码
     *
     * @author mose created on 2017年11月2日
     */
    @RequestMapping("/checkLogin")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        // 校验账号和密码是否都为空
        if (StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password)) {
            WebUtil.out(response, JsonUtil.createOperaStr(false, "用户名或密码为空"));
            return;
        }
//        Object obj = redisTemplate.opsForValue().get("spring:session:sessions:login:" + username);
        Object obj = null;
        Integer loginNum = null;
        //是否存在限制session
        if (obj != null) {
            loginNum = Integer.valueOf(String.valueOf(obj));
            //存在，判断是否大于5
            if (loginNum >= 5) {
                //锁定
                String json = "{\"success\":" + false + ",\"msgText\":\"1\"}";
                WebUtil.out(response, json);
            } else {
                //判断用户名密码是否正确
                SysUser sysUser = sysUserService.getByUsername(username);
                if (sysUser == null || !sysUserService.checkPass(sysUser, password)) {
                    //错误，限制session+1
                    redisTemplate.boundValueOps("spring:session:sessions:login:" + username).increment(1);
                    loginNum++;
                    if (loginNum == 5) {
                        //锁定
                        String json = "{\"success\":" + false + ",\"msgText\":\"1\"}";
                        WebUtil.out(response, json);
                    } else {
                        //判断错误次数是否大于3，如果大于，下次输入，前台需要输入验证码
                        if (loginNum >= 3) {
                            String json = "{\"success\":" + false + ",\"msgText\":\"2\"}";
                            WebUtil.out(response, json);
                        } else {
                            //输入错误3次以内
                            String json = "{\"success\":" + false + ",\"msgText\":\"用户名或密码错误\"}";
                            WebUtil.out(response, json);
                        }
                    }
                } else {
                    //用户状态是否锁定
                    if (sysUser.getStatus() == 2) {
                        WebUtil.out(response, JsonUtil.createOperaStr(false, "该用户已锁定"));
                    } else {
                        //正确，进入系统
                        loginSuccess(request, response, sysUser);
                    }
                }
            }
        } else {
            //不存在限制session的情况
            SysUser sysUser = sysUserService.getByUsername(username);
            if (sysUser == null || !sysUserService.checkPass(sysUser, password)) {
                //错误，设置限制session
//                redisTemplate.opsForValue().set("spring:session:sessions:login:" + username, "1");
//                redisTemplate.expire("spring:session:sessions:login:" + username, 10, TimeUnit.MINUTES);
                String json = "{\"success\":" + false + ",\"msgText\":\"用户名或密码错误\"}";
                WebUtil.out(response, json);
            } else {
                //用户状态是否锁定
                if (sysUser.getStatus() == 2) {
                    WebUtil.out(response, JsonUtil.createOperaStr(false, "该用户已锁定"));
                } else {
                    //正确，进入系统
                    loginSuccess(request, response, sysUser);
                }
            }
        }
    }

    /**
     * what:    进入首页面
     *
     * @param request  request
     * @param response response
     *
     * @return index
     *
     * @author mose created on 2017年6月13日
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        request.getSession().setAttribute("resourceLevel", 0);
        mv.setViewName("/index");
        return mv;
    }

    /**
     * what:    切换菜单为平台管理
     * when:    在系统管理员登陆的时候起作用
     *
     * @param request  request
     * @param response response
     *
     * @return index
     *
     * @author mose created on 2017年10月27日
     */
    @RequestMapping("/sys/index")
    public ModelAndView sysIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        request.getSession().setAttribute("resourceLevel", 1);
        mv.setViewName("/sys/index");
        return mv;
    }

    /**
     * what:    切换为横向菜单
     *
     * @param request request
     *
     * @return index
     *
     * @author mose created on 2017年11月3日
     */
    @RequestMapping("/themeOfHorizontal")
    public ModelAndView themeOfHorizontal(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int themeStyle = (int) request.getSession().getAttribute("themeStyle");
        if (themeStyle == 1) {
            request.getSession().setAttribute("themeStyle", 0);
        }
        mv.setViewName("/index");
        return mv;
    }

    /**
     * what:    切换为纵向菜单
     *
     * @param request request
     *
     * @return index
     *
     * @author mose created on 2017年11月3日
     */
    @RequestMapping("/themeOfVertical")
    public ModelAndView themeOfVertical(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int themeStyle = (int) request.getSession().getAttribute("themeStyle");
        if (themeStyle == 0) {
            request.getSession().setAttribute("themeStyle", 1);
        }
        mv.setViewName("/index");
        return mv;
    }

    /**
     * what:    用户注销
     *
     * @param request  request
     * @param response response
     *
     * @return login
     *
     * @author mose created on 2017年6月13日
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/login.htm";
    }

    /**
     * 进入修改密码界面
     *
     * @param request  request
     * @param response response
     * @param sysUser  系统用户model
     *
     * @return index
     */
    @RequestMapping("/toUpdatePass")
    public ModelAndView toUpdatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/user/updatePass");
        mv.addObject("backUrl", pubConfig.getDynamicServer() + "/index.htm");
        return mv;
    }

    /**
     * what:   修改个人信息，登陆界面
     *
     * @param request  request
     * @param response response
     * @param sysUser  系统用户model
     *
     * @return 成功或错误界面
     *
     * @author mose created on 2017年6月13日
     */
    @RequestMapping("/updatePass")
    public String updatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        String oldPass = WebUtil.getSafeStr(request.getParameter("oldPass"));
        String newPass = WebUtil.getSafeStr(request.getParameter("newPass"));
        UserSession userSession = SessionUtil.getUserSession(request);
        int flag = sysUserService.updatePass(userSession.getUserId(), oldPass, newPass);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("密码修改失败");
        } else if (flag == 2) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("原密码输入错误");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("密码修改成功");
        }
    }

    /**
     * what:    生成验证码图片
     * when:    前台需要显示或更换验证码图片
     *
     * @param request  request
     * @param response response
     * @param para     生成验证码类型参数
     *
     * @throws IOException IOException
     * @author mose created on 2017年11月2日
     */
    @RequestMapping("/authImage")
    public void authImage(HttpServletRequest request, HttpServletResponse response, Integer para) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4, para);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verCode");
        if (para == 5) {
            session.setAttribute("verCode", VerifyCodeUtil.getSumCode(verifyCode));
        } else {
            session.setAttribute("verCode", verifyCode.toLowerCase());
        }
        //生成图片s
        int w = 300;
        int h = 80;
        VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /**
     * what:    前台验证码异步校验
     * when:    用户输入验证码时
     *
     * @param session  session
     * @param response response
     * @param authCode 验证码
     *
     * @author mose created on 2017年11月2日
     */
    @RequestMapping("/authPicCode")
    public void authCode(HttpSession session, HttpServletResponse response, String authCode) {
        WebUtil.out(response, (authCode.toLowerCase().equals(session.getAttribute("verCode")) ? "1" : "0"));
    }

    /**
     * what:    登录成功的后续操作（插入登录日志等）
     * when:    登录成功时
     *
     * @param request  request
     * @param response response
     * @param sysUser  系统用户
     *
     * @author mose created on 2017年11月2日
     */
    private void loginSuccess(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        String ip = StringUtil.getIp(request);
        UserSession userSession = new UserSession();
        //userId
        userSession.setUserId(sysUser.getId());
        //username
        userSession.setUsername(sysUser.getUsername());
        //IP地址
        userSession.setUserIp(ip);
        // 角色
        userSession.setRoleId(sysUser.getRoleId());
        userSession.setRoleName(sysUser.getRoleName());
        userSession.setRealName(sysUser.getRealName());
        //头像
        userSession.setAvatar(sysUser.getAvatar());
        request.getSession().setAttribute("userSession", userSession);
        // 设置过期时间30分钟
        request.getSession().setMaxInactiveInterval(60 * 30);
        //切换菜单配置 0代表应用系统，1代表平台管理
        request.getSession().setAttribute("resourceLevel", 0);
        //切换主题菜单，0代表横向菜单，1代表纵向菜单
        request.getSession().setAttribute("themeStyle", 1);
        // 插入登录日志
        SysUserLogin sysUserLogin = new SysUserLogin();
        sysUserLogin.setUserId(sysUser.getId());
        sysUserLogin.setLoginDate(new Date());
        sysUserLogin.setLoginIp(ip);
        sysUserLogin.setTerminal(WebUtil.getSafeStr(request.getParameter("terminal")));
        sysUserLogin.setExplorerType(WebUtil.getSafeStr(request.getParameter("explorerType")));
        sysUserLogin.setExplorerVersion(WebUtil.getSafeStr(request.getParameter("explorerVersion")));
        userLoginService.add(sysUserLogin);
        WebUtil.out(response, JsonUtil.createOperaStr(true, "登录成功"));
    }
}
