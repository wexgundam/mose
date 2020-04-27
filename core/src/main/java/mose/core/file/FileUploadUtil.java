package mose.core.file;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * 
 * what:    文件上传工具
 * 
 *
 * @author mose created on 2017年11月10日
 */
public class FileUploadUtil {
	/**
	 * 
	 * what:   文件上传（支持所有文件格式）
	 * 
	 * @param request  request
	 * @param response response
	 * @param path     文件上传路径
	 * @throws IllegalStateException IllegalStateException
	 * @throws IOException           IOException
	 *
	 * @author mose created on 2017年11月10日
	 */
public static void upload(HttpServletRequest request, HttpServletResponse response,String path) throws IllegalStateException, IOException {
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					File f = new File(path);
					if (!f.exists())
						f.mkdirs();
					// 上传
					file.transferTo(new File(path));
				}
			}
		}
	}
}
