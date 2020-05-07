package mose.core.restful;

import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2014/7/15.
 */
public class RestfulUtil {
    public static final RestfulUtil INSTANCE = new RestfulUtil();

    private RestfulUtil() {
    }

    /**
     * 可以修改Tomcat的server.xml配置文件，强制要求tomcat用utf-8编码
     * <p/>
     * 搜索：tomcat json乱码
     *
     * @param param
     *
     * @return
     *
     * @throws UnsupportedEncodingException
     */
    public String decode(String param) throws UnsupportedEncodingException {
        if (param == null) {
            return null;
        }
        return new String(param.getBytes("iso8859-1"), "UTF-8");
    }

    public void setupModelMap(ModelMap map, boolean success) {
        map.put(RestfulConstants.SUCCESS, success);
    }

    public void setupModelMap(ModelMap map, Object item) {
        map.put(RestfulConstants.SUCCESS, true);
        map.put(RestfulConstants.ITEM, item);
    }

    public void setupModelMap(ModelMap map, List<?> items, Integer totalCount) {
        map.put(RestfulConstants.SUCCESS, true);
        map.put(RestfulConstants.TOTAL_COUNT, totalCount);
        map.put(RestfulConstants.ITEMS, items);
    }

    public void setupModelMap(ModelMap map, Exception e) {
        map.put(RestfulConstants.SUCCESS, false);
        map.put(RestfulConstants.ERROR, e.getLocalizedMessage());
    }

    public void setupModelMap(ModelMap map, List<?> items, Long totalCount) {
        setupModelMap(map, items);
        map.put(RestfulConstants.TOTAL_COUNT, totalCount);
    }
}
