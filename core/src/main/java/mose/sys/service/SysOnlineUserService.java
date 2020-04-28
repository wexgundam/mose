package mose.sys.service;

import mose.core.pub.PubConfig;
import mose.core.session.UserSession;
import mose.core.string.StringUtil;
import mose.sys.vo.SysUserLoginSearchVO;
import mose.sys.vo.SysUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * what:    在线用户service
 *
 *
 * @author mose created on 2017年10月31日
 */
@Service
public class SysOnlineUserService {
	/**
	 * 系统配置类
	 */
	@Autowired
	private PubConfig pubConfig;

	/**
	 *
	 * what:    设置分页url
	 * when:    一般有查询条件的才会用到
	 *
	 * @param sysUserSearchVO 查询实体类
	 * @return String
	 *
	 * @author mose created on 2017年10月31日
	 */

	public  String createUrl(SysUserSearchVO sysUserSearchVO) {
		String url = pubConfig.getDynamicServer() + "/sys/onlineuser/index.htm?";
		if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername())) {
			url += "&username=" + sysUserSearchVO.getUsername();
		}
		//如果为模糊查询，要把该字段encode
		if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealName())) {
			url += "&realname=" + sysUserSearchVO.getRealName();
		}

		if (sysUserSearchVO.getRoleId() != null) {
			url += "&roleId=" + sysUserSearchVO.getRoleId();
		}

		return url;
	}


	/**
	 *
	 * what:    条件查询，获取符合条件的session
	 * how:     按照单个条件，两个条件，三个条件查询
	 *
	 * @param userName 账号
	 * @param realName 姓名
	 * @param roleName 角色
	 * @param sessionlist 在线用户list
	 * @return ArrayList
	 *
	 * @author mose created on 2017年9月29日
	 */
	public  ArrayList<UserSession> conditionSearch(String userName, String realName, String roleName, ArrayList<UserSession> sessionlist) {
		boolean tag1 =  StringUtil.isNotNullOrEmpty(userName);
		boolean tag2 =  StringUtil.isNotNullOrEmpty(realName);
		boolean tag3 =  StringUtil.isNotNullOrEmpty(roleName);
		ArrayList<UserSession> list = new ArrayList<UserSession>();
		for (UserSession userSession:sessionlist) {
			if (userSession != null) {
				String tempUserName = userSession.getUsername();
				String tempRealName = userSession.getRealName();
				String tempRoleName = String.valueOf(userSession.getRoleId());
				if (tag1 && tag2 && tag3) {    //三个条件都不为空
					if ((tempUserName.equals(userName) && tempRealName.equals(realName) && tempRoleName.equals(roleName))) {
						list.add(userSession);
					}
				} else if (tag1 && !tag2 && !tag3) {
					//查询userName
					if (tempUserName.equals(userName)) {
						list.add(userSession);
					}
				} else if (tag2 && !tag1 && !tag3) {
					//查询realName
					if (tempRealName.equals(realName)) {
						list.add(userSession);
					}
				} else if (tag3 && !tag1 && !tag2) {
					//查询roleName
					if (tempRoleName.equals(roleName)) {
						list.add(userSession);
					}
				} else if (tag1 && tag2 && !tag3) {
					//查询userName,realName
					if (tempUserName.equals(userName) && tempRealName.equals(realName)) {
						list.add(userSession);
					}
				} else if (tag1 && tag3 && !tag2) {
					//查询userName,roleName
					if (tempUserName.equals(userName) && tempRoleName.equals(roleName)) {
						list.add(userSession);
					}
				} else if (tag2 && tag3 && !tag1) {
					//查询realName,roleName
					if (tempRealName.equals(realName) && tempRoleName.equals(roleName)) {
						list.add(userSession);
					}
				}

			}
		}

		return list;
	}

	/**
	 *
	 * what:    获取redis中所有存在的用户session
	 *
	 * @param redisTemplate redisTemplate
	 * @return ArrayList
	 *
	 * @author mose created on 2017年10月31日
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<UserSession> getUserSession(RedisTemplate redisTemplate){
		//取出redis中所有session的key
		Set<String> keys = redisTemplate.keys("spring:session:sessions:*");
		ArrayList<UserSession> userList = new ArrayList<UserSession>();

		for (String key : keys) {
			if (key.indexOf("expires") == -1 && key.indexOf("l") == -1) {
				if ((UserSession) redisTemplate.opsForHash().get(key, "sessionAttr:userSession") != null) {
					//一条条取出session
					userList.add((UserSession) redisTemplate.opsForHash().get(key, "sessionAttr:userSession"));
				}

			}
		}
		return userList;
	}

	/**
	 *
	 * what:  生成登录历史分页 url
	 *
	 * @param sysUserLoginSearchVO 查询实体类
	 * @return String
	 *
	 * @author mose created on 2017年11月8日
	 */
	public String createUrl2(SysUserLoginSearchVO sysUserLoginSearchVO) {
		String url = pubConfig.getDynamicServer() + "/sys/onlineuser/toLoginHistory.htm?";
		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getUsername())) {
			url += "&username=" + sysUserLoginSearchVO.getUsername();
		}

		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getLoginIp())) {
			url += "&loginIp=" + sysUserLoginSearchVO.getLoginIp();
		}

		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getStartDate())) {
			url += "&startDate=" + sysUserLoginSearchVO.getStartDate();
		}

		if (StringUtil.isNotNullOrEmpty(sysUserLoginSearchVO.getEndDate())) {
			url += "&endDate=" + sysUserLoginSearchVO.getEndDate();
		}

		return url;
	}


}
