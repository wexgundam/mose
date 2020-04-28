/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:web
 */
package mose.sys.controller;

import mose.core.file.FileUtil;
import mose.core.pub.PubConfig;
import mose.sys.service.SysSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

/**
 * what: 研发规范Controller
 *
 * @author mose created on 2017年11月6日
 */
@Controller
@RequestMapping("sys/specification")
public class SysSpecificationController {
	@Autowired
	private SysSpecificationService sysSpecificationService;
	@Autowired
	private PubConfig pubConfig;

	/**
	 * 
	 * what:跳转到研发规范首页
	 * 
	 * @return mv
	 *
	 * @author mose created on 2017年11月8日
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		String technicalSelectionPdfNamePath = "/file/sys/specification/应用软件开发技术选型规范V0.1.pdf";
		String technicalGuidePdfNamePath = "/file/sys/specification/应用软件开发技术指南V0.8.pdf";
		String databaseDesignPdfNamePath = "/file/sys/specification/数据库设计文档示例V0.1.pdf";
		String interfaceDesignPdfNamePath = "/file/sys/specification/接口设计文档示例V0.1.pdf";
		ModelAndView mv = new ModelAndView();
		try {
			// 先判断是否存在文件路径，不存在则创建并将pdf文件转换成图片
			String savePath = pubConfig.getImageUploadPath() + "\\sys\\specification";
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			if (file.listFiles().length == 0) {
				String pdfPath = FileUtil.class.getResource(technicalSelectionPdfNamePath).getPath();
				sysSpecificationService.pdfToimg(pdfPath, savePath, "jpg");
				pdfPath = FileUtil.class.getResource(technicalGuidePdfNamePath).getPath();
				sysSpecificationService.pdfToimg(pdfPath, savePath, "jpg");
				pdfPath = FileUtil.class.getResource(databaseDesignPdfNamePath).getPath();
				sysSpecificationService.pdfToimg(pdfPath, savePath, "jpg");
				pdfPath = FileUtil.class.getResource(interfaceDesignPdfNamePath).getPath();
				sysSpecificationService.pdfToimg(pdfPath, savePath, "jpg");
			}
			// 获取每个文件的页数，并将页数和文件名返回到页面中
			int technicalSelectionPage = sysSpecificationService.getPage(technicalSelectionPdfNamePath);
			int technicalGuidePage = sysSpecificationService.getPage(technicalGuidePdfNamePath);
			int databaseDesignPage = sysSpecificationService.getPage(databaseDesignPdfNamePath);
			int interfaceDesignPage = sysSpecificationService.getPage(interfaceDesignPdfNamePath);
			mv.addObject("technicalSelectionPage", technicalSelectionPage);
			mv.addObject("technicalSelectionPdfName", technicalSelectionPdfNamePath.substring(technicalSelectionPdfNamePath.lastIndexOf("/") + 1,
					technicalSelectionPdfNamePath.lastIndexOf(".")));
			mv.addObject("technicalGuidePage", technicalGuidePage);
			mv.addObject("technicalGuidePdfName",
					technicalGuidePdfNamePath.substring(technicalGuidePdfNamePath.lastIndexOf("/") + 1, technicalGuidePdfNamePath.lastIndexOf(".")));
			mv.addObject("databaseDesignPage", databaseDesignPage);
			mv.addObject("databaseDesignPdfName",
					databaseDesignPdfNamePath.substring(databaseDesignPdfNamePath.lastIndexOf("/") + 1, databaseDesignPdfNamePath.lastIndexOf(".")));
			mv.addObject("interfaceDesignPage", interfaceDesignPage);
			mv.addObject("interfaceDesignPdfName", interfaceDesignPdfNamePath.substring(interfaceDesignPdfNamePath.lastIndexOf("/") + 1,
					interfaceDesignPdfNamePath.lastIndexOf(".")));
			mv.setViewName("/sys/specification/index");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("redirect:/toServerError.htm");
		}
		return mv;
	}

}
