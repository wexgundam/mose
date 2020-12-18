/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.service;

import mose.sys.dao.SysLogDao;
import mose.sys.model.SysLog;
import mose.sys.model.SysResource;
import mose.sys.vo.SysLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * what: 系统日志service
 * 
 *
 * @author mose created on 2017年6月13日
 */
@Service
@EnableAsync
public class SysLogService {
	/**
	 * 系统日志dao
	 */
    @Autowired
    private SysLogDao sysLogDao;
    /**
     * 系统资源service
     */
    @Autowired
    private SysResourceService sysResourceService;

   /**
    * 
    * what:分页日志列表
    * 
    * @param sysLogSearchVO 日志搜索VO
    * @return list
    *
    * @author mose created on 2017年11月10日
    */
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        List<SysLog> list = sysLogDao.list(sysLogSearchVO);
        return list;
    }

    /**
     * 
     * what:不分页日志列表
     * 
     * @param sysLogSearchVO 日志搜索VO
     * @return list
     *
     * @author mose created on 2017年11月10日
     */
    public List<SysLog> listAll(SysLogSearchVO sysLogSearchVO) {
        List<SysLog> list = sysLogDao.listAll(sysLogSearchVO);
        return list;
    }

   /**
    * 
    * what: 数据总条数
    * when：分页
    * 
    * @param sysLogSearchVO 日志搜索VO
    * @return int
    *
    * @author mose created on 2017年11月10日
    */
    public int count(SysLogSearchVO sysLogSearchVO) {
        return sysLogDao.count(sysLogSearchVO);
    }


    /**
     * 
     * what:    异步记录操作日志
     * 
     * @param userId 用户id
     * @param url    操作URL
     * @param parameters 操作参数
     * @param operaIp    操作ip
     *
     * @author mose created on 2017年6月13日
     */
    @Async
    public void addLog(int userId, String url, String parameters, String operaIp) {
    	//获取所有资源
        HashMap<String, SysResource> hashMap = sysResourceService.getAllResource();

        SysLog sysLog = new SysLog();
        sysLog.setUserId(userId);
        sysLog.setOperaUrl(url);
        if (parameters.length() > 500) {
            parameters = parameters.substring(0, 500);
        }
        sysLog.setOperaParams(parameters);
        sysLog.setOperaDate(new Date());
        sysLog.setOperaIp(operaIp);

        SysResource sysResource = hashMap.get(url);
        if (sysResource != null) {
            sysLog.setModuleName(sysResource.getParentName());
            sysLog.setOperaName(sysResource.getName());
        }
        sysLogDao.add(sysLog);
    }
    /**
     * 
     * what: 根据ID获取系统日志
     * 
     * @param id id
     * @return SysLog
     *
     * @author mose created on 2017年10月13日
     */
	public SysLog getById(String id) {
		return sysLogDao.getById(id);
	}

}
