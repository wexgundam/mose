package mose.core.session;

import javax.servlet.http.HttpServletRequest;

/**
 * session工具类，用于获取用户session
 *
 * @author mose
 * @date 2017-05-23
 */
public class SessionUtil {
    /**
     * 功能描述:获取用户session
     *
     * @param request
     *
     * @return UserSession
     *
     * @version 1.0.0
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("userSession") != null)
            return (UserSession) request.getSession().getAttribute("userSession");
        else {
            return null;
        }
    }

    /**
     * 获取当前操作人Id
     *
     * @param request
     *
     * @return
     */
    public static int getUserId(HttpServletRequest request) {
        return getUserSession(request).getUserId();
    }

    /**
     * 获取当前操作人真是姓名
     *
     * @param request
     *
     * @return
     */
    public static String getUserRealName(HttpServletRequest request) {
        return getUserSession(request).getRealName();
    }
}
