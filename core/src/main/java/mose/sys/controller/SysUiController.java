package mose.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * what:    系统界面Controller
 *
 * @author mose created on 2017年11月1日
 */
@Controller
@RequestMapping("/sys/ui")
public class SysUiController {
	/**
	 * 
	 * what:    进入系统界面首页
	 * 
	 * @return
	 *
	 * @author mose created on 2017年11月1日
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/ui/index");
		return mv;
	}
}
