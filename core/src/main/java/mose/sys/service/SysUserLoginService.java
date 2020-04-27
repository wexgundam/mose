/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.sys.service;

import mose.sys.dao.SysUserLoginDao;
import mose.sys.model.SysUserLogin;
import mose.sys.vo.SysUserLoginSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统登录Service
 *
 * @author mose
 * @date 2017-06-13
 */
@Service
public class SysUserLoginService {
    @Autowired
    private SysUserLoginDao sysUserLoginDao;

    /**
     * 
     * what: 登录时新增登录信息
     * 
     * @param sysUserLogin
     *
     * @author mose created on 2017年6月13日
     */
    @Async
    public void add(SysUserLogin sysUserLogin) {
        sysUserLoginDao.add(sysUserLogin);
    }

   /**
    * 
    * what: 取得最后登录信息
    * 
    * @param user_id
    * @return
    *
    * @author mose created on 2017年6月13日
    */
    public SysUserLogin getLastLogin(int user_id) {
        return sysUserLoginDao.getLastLogin(user_id);
    }

    public List<SysUserLogin> list(SysUserLoginSearchVO sysUserloginSearchVO) {
        return sysUserLoginDao.list(sysUserloginSearchVO);
    }

   /**
    * 
    * what: 查询用户登录总数
    * 
    * @param sysUserloginSearchVO 用户登录信息搜索VO
    * @return int
    *
    * @author mose created on 2017年6月13日
    */
    public int count(SysUserLoginSearchVO sysUserloginSearchVO) {
        return sysUserLoginDao.count(sysUserloginSearchVO);
    }

}
