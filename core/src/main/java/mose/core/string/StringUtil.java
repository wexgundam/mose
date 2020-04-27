package mose.core.string;

import net.sourceforge.pinyin4j.PinyinHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户字符串操作，这里面包括字符串的decode、encode、substrac等等操作
 *
 * @author mose
 * @date 2017-05-23
 */
public class StringUtil {

    /**
     * 把前台传过来的含中文的url字符串转换成标准中文，比如%25E5%258C%2597%25E4%25BA%25AC转换成北京
     *
     * @param url url字符串
     * @return string
     */
    public static String decodeUrl(String url) {
        if (url == null)
            return "";
        String str = "";
        try {
            str = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 把比如北京转换成%25E5%258C%2597%25E4%25BA%25AC
     *
     * @param url url字符串
     * @return string
     */
    public static String encodeUrl(String url) {
        if (url == null)
            return "";
        String str = "";
        try {
            str = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 取字符除最后一位的子串，比如 aaa,bbb, 返回aaa,bbb，一般用在多个字段进行拼接，要去除最后一位
     *
     * @param str 字符串
     * @return string
     */
    public static String subTract(String str) {
        if (str.length() == 0)
            return "";
        else
            return str.substring(0, str.length() - 1);
    }

    /**
     * 判断字符串是null或空，null或""都返回true
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.equals(""))
            return true;
        else
            return false;
    }


    /**
     * 判断字符串不是null或空
     *
     * @param str 字符串
     * @return
     */
    public static boolean isNotNullOrEmpty(String str) {
        if (str != null && !str.equals(""))
            return true;
        else
            return false;
    }


    /**
     * 判断是否是ajax请求，用于进行权限控制或异常处理时，得判断是否是ajax请求
     *
     * @param request
     * @return
     */
    public static boolean checkAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else
            return false;
    }


    /**
     * 获取客户端请求的ip地址，可以跳过代理等直接获取
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String szClientIP = request.getHeader("x-forwarded-for");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getHeader("Proxy-Client-IP");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getHeader("WL-Proxy-Client-IP");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getRemoteAddr();
        return szClientIP;
    }


    /**
     * 过滤表情，在移动开发中，有些字符是表情等特殊字符，数据库不识别，需要过滤掉， 替换为*
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtil.isNotNullOrEmpty(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }

    /**
     * 获取url地址，整个请求地址，不包含?后面的参数信息，如果最后一位后缀为#，去掉
     *
     * @param request 请求
     * @return url地址
     */
    public static String getUrlPath(HttpServletRequest request) {
        String url;
        if (request.getServerPort() == 80) {
            url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI();
        } else {
            url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
        }
        if (url.contains("#")) {
            url = url.substring(0, url.indexOf("#"));
        }
        return url;
    }

    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher m = p.matcher(mobile);
        boolean b = m.matches();
        return b;
    }

    /**
     * 获取Request的参数，并将其"Key=Value&Key=Value"的格式返回
     *
     * @param request 请求
     * @return "Key=Value&Key=Value"格式的字符串
     */
    public static String getOperaParams(HttpServletRequest request) {
        String parameters = "";// 定义所有参数值
        Map<String, String[]> map = request.getParameterMap();
        // /取得所有参数值，用&号组装起来
        Object[] obj = null;
        obj = map.keySet().toArray();
        for (int i = 0; i < obj.length; i++) {
            parameters += obj[i].toString() + "=" + request.getParameter(obj[i].toString()) + "&";
        }
        return parameters;
    }

    /**
     * 去掉小数点后面的0，导入数据的时候用到
     *
     * @param val
     * @return
     */
    public static String formatNumber(String val) {
        val = val.replaceAll("0+?$", "");// 去掉后面无用的零
        val = val.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
        return val;
    }

    /**
     * 生成给定字符串的拼音首字母大写形式
     *
     * 例如：北京——BJ
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadUpCase(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        String convert = "";
        for (char word : str.toCharArray()) {
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        convert = convert.trim();
        return convert.toUpperCase();
    }
    /**
     * 
     * what:     将汉字转换为拼音 （不考虑多音字）
     * how:     例如：北京--->beijing
     * warning: 多音字场景下不适用
     * 
     * @param str  需要转换的汉字
     * @return
     *
     * @author 刘艳超 created on 2017年10月26日
     */
    public static String getPinYinAll(String str){
    	if (isNullOrEmpty(str)) {
    		return "";
		}
    	String convert ="";
    	for (char word : str.toCharArray()) {
			//提取汉字的拼音
    		String[] pinYinArray = PinyinHelper.toHanyuPinyinStringArray(word);
    		if (pinYinArray!=null) {
    			String pinYin = pinYinArray[0];
    			convert += pinYin.substring(0, pinYin.length()-1)+" ";
			}else{
				convert += word+" ";
			}
		}
    	convert = convert.trim();
    	return convert;
    }
}
