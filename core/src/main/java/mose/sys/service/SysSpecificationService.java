/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mose.core.file.FileUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

/**
 * what: 技术规范Service 
 *
 * @author mose created on 2017年11月6日
 */
@Service
public class SysSpecificationService {

	/**
	 * 
	 * what:获取pdf文件的页数
	 * 
	 * @param filePath 文件路径
	 * @return int 页数
	 * @throws Exception
	 *
	 * @author mose created on 2017年11月8日
	 */
	public int getPage(String filePath) throws Exception {
		String fullFileName = FileUtil.class.getResource(filePath).getPath();
		File file = new File(fullFileName);
		PDDocument doc = PDDocument.load(file);
		int pageCount = doc.getNumberOfPages();
		doc.close();
		return pageCount;
	}

	/**
	 * 
	 * what:pdf转换到图片
	 * 
	 * @param pdfPath pdf路径
	 * @param savePath 保存的路径
	 * @param imgType 图片的格式（jpg或者png）
	 * @throws Exception
	 *
	 * @author mose created on 2017年11月8日
	 */
	public void pdfToimg(String pdfPath, String savePath, String imgType) throws Exception {
		String fileName = pdfPath.substring(pdfPath.lastIndexOf("/") + 1, pdfPath.length());
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(pdfPath);
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 196); // 分辨率
				String saveFileName = savePath + "\\" + fileName + "page_" + i + "." + imgType;
				ImageIO.write(image, imgType, new File(saveFileName));
			}
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
