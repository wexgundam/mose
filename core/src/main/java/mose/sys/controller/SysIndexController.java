package mose.sys.controller;

import mose.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * what: 跳转主页
 *
 * @author mose created on 2017年7月6日
 */
@RequestMapping("/")
@Controller
public class SysIndexController {
	/**
	 * 系统用户管理Service
	 */
    @Autowired
    private SysUserService sysUserService;
    /**
     * 进入用户管理界面
     *
     * @return
     */
    /*@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/index");// 跳转至Index页面
        return mv;
    }*/


}
