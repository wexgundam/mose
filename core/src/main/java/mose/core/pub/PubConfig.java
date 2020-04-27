/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.pub;

import org.springframework.stereotype.Component;

/**
 * 
 * what: 全局参数配置，用于在后端获取参数
 *
 * @author mose created on 2017年6月13日
 */
@Component
public class PubConfig {
	/**
	 * 图片显示服务器地址
	 */
    private String imageServer;
    /**
     * 图片路径
     */
    private String imageUploadPath;
    /**
     * 静态地址
     */
    private String staticServer;
    /**
     * 动态地址
     */
    private String dynamicServer;
    /**
     * 文件上传路径
     */
    private String fileUploadPath;
    
    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public String getImageUploadPath() {
        return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath) {
        this.imageUploadPath = imageUploadPath;
    }

    public String getStaticServer() {
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }

    public String getDynamicServer() {
        return dynamicServer;
    }

    public void setDynamicServer(String dynamicServer) {
        this.dynamicServer = dynamicServer;
    }


	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	@Override
	public String toString() {
		return "PubConfig [imageServer=" + imageServer + ", imageUploadPath=" + imageUploadPath + ", staticServer=" + staticServer + ", dynamicServer=" + dynamicServer + ", fileUploadPath="
				+ fileUploadPath + "]";
	}

	
}
