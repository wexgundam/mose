package mose.sys.controller;


import mose.core.json.JsonUtil;
import mose.core.page.PageNavigate;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.session.UserSession;
import mose.core.string.StringUtil;
import mose.core.web.WebUtil;
import mose.sys.model.SysUserLogin;
import mose.sys.service.SysOnlineUserService;
import mose.sys.service.SysRoleService;
import mose.sys.service.SysUserLoginService;
import mose.sys.vo.SysUserLoginSearchVO;
import mose.sys.vo.SysUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * what:    在线用户实时查看
 *
 * @author 郭飞 created on 2017年7月26日
 */

@RequestMapping("/sys/onlineuser")
@Controller
public class SysOnlineUserController {
    /**
     * 在线用户service
     */
    @Autowired
    private SysOnlineUserService sysOnlineUserService;
    /**
     * redis操作工具
     */
    @SuppressWarnings("rawtypes")
//    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 系统角色service
     */
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 系统用户登录service
     */
    @Autowired
    private SysUserLoginService sysUserLoginService;
    /**
     * 全局配置类
     */
    @SuppressWarnings("unused")
    @Autowired
    private PubConfig pubConfig;

    /**
     * what:    进入用户查看首页
     *
     * @param request         request
     * @param response        response
     * @param sysUserSearchVO 查询实体
     *
     * @return ModelAndView ModelAndView
     *
     * @throws SerializationException       SerializationException
     * @throws UnsupportedEncodingException
     * @author 郭飞 created on 2017年9月26日
     */
    @SuppressWarnings("unused")
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) throws SerializationException, UnsupportedEncodingException {

        //在线用户数量
        int onlineNum = 0;
        /* int pageIndex = 0; //页码初始值
		 int pageSize = 10; //设置每页显示多少条
        */
        int recordNum = 0;
        //条件参数列表
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //传到页面的session List
        ArrayList<UserSession> realUserList = new ArrayList<UserSession>();
        //获取当前用户
        sysUserSearchVO.setCurrentUser(SessionUtil.getUserSession(request).getUsername());
        //获取redis中所有session
        ArrayList<UserSession> userList = sysOnlineUserService.getUserSession(redisTemplate);

        String userName = (String) request.getParameter("username");
        String realName = (String) request.getParameter("realname");
        String roleName = (String) request.getParameter("rolename");
        boolean tag1 = StringUtil.isNotNullOrEmpty(userName);
        boolean tag2 = StringUtil.isNotNullOrEmpty(realName);
        boolean tag3 = StringUtil.isNotNullOrEmpty(roleName);

        //有条件的查询
        if (tag1 || tag2 || tag3) {
            realUserList = sysOnlineUserService.conditionSearch(userName, realName, roleName, userList);
            recordNum = realUserList.size();
            paramMap.put("userName", userName);
            paramMap.put("realName", realName);
            paramMap.put("roleName", roleName);
        } else {
            //空条件查询
            realUserList = userList;
            recordNum = realUserList.size();
        }
        onlineNum = userList.size();

		/* boolean flag = StringUtil.isNullOrEmpty(request.getParameter("pageIndex"));
		 if(flag){  //判断是否为第一次访问，初次访问设置起始页码为1,如果不是则动态设值
			 pageIndex=1;
		 }else{
			 pageIndex = Integer.parseInt( request.getParameter("pageIndex")  );
		 }*/


        String url = sysOnlineUserService.createUrl(sysUserSearchVO);
        // PageNavigate pageNavigate = new PageNavigate(url, pageIndex,pageSize, recordNum);//定义分页对象

        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", realUserList);
//		 mv.addObject("pageIndex", pageIndex);
//		 mv.addObject("pageSize", pageSize);
        mv.addObject("onlineNum", onlineNum);
        mv.addObject("paramMap", paramMap);
        // 设置分页的变量
        // mv.addObject("pageNavigate", pageNavigate);
        // 角色列表
        mv.addObject("listRole", sysRoleService.listCombo());
        // 跳转至指定页面
        mv.setViewName("/sys/onlineuser/index");
        return mv;
    }

    /**
     * what: 查看登录历史
     *
     * @param request  request
     * @param response Response
     *
     * @return ModelAndView ModelAndView
     *
     * @author 郭飞 created on 2017年11月8日
     */

    @RequestMapping("/toLoginHistory")
    public ModelAndView tologinhistory(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        SysUserLoginSearchVO sysUserloginSearchVO = new SysUserLoginSearchVO();
        String id = request.getParameter("id");
        String pageIndex = request.getParameter("pageIndex");
        if (StringUtil.isNotNullOrEmpty(id)) {
            sysUserloginSearchVO.setUserId(Integer.parseInt(id));
        }
        if (StringUtil.isNotNullOrEmpty(pageIndex)) {
            sysUserloginSearchVO.setPageIndex(Integer.parseInt(pageIndex));
            ;
        }

        List<SysUserLogin> list = sysUserLoginService.list(sysUserloginSearchVO);
        // 获取查询总数
        int recordCount = sysUserLoginService.count(sysUserloginSearchVO);
        String url = sysOnlineUserService.createUrl2(sysUserloginSearchVO);
        //定义分页对象
        PageNavigate pageNavigate = new PageNavigate(url, sysUserloginSearchVO.getPageIndex(), recordCount);
        mv.addObject("list", list);
        mv.addObject("pageNavigate", pageNavigate);
        mv.addObject("list", list);
        mv.setViewName("/sys/onlineuser/userlogin");
        return mv;

    }

    /**
     * what:    输入框自动补全
     *
     * @param request  request
     * @param response response
     *
     * @author 郭飞 created on 2017年11月9日
     */
    @RequestMapping("/getAutoComplteList")
    public void getAutoComplteList(HttpServletRequest request, HttpServletResponse response) {
        //获取redis中所有session
        ArrayList<UserSession> userList = sysOnlineUserService.getUserSession(redisTemplate);
        WebUtil.out(response, JsonUtil.toStr(userList));
    }


}
