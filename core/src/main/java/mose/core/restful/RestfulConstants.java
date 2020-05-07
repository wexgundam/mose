package mose.core.restful;

/**
 * 使用正则表达式可以比配特殊字符，下面的例子可以匹配dot‘.’
 * @RequestMapping(value = "/trainlines/trainline-{id:.+}", method = RequestMethod.GET)
 *
 * Created by Administrator on 2014/6/17.
 */
public class RestfulConstants {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String ITEM = "item";
    public static final String ITEMS = "items";
    public static final String RESULT = "result";
    public static final String SOURCE = "source";
    public static final String ID = "id";

    public static final String ACTION_SORTED = "sorted";

    public static final String METHOD_TYPE = "methodType";
    public static final String METHOD_TYPE_GET = "GET";
    public static final String METHOD_TYPE_POST = "POST";
    public static final String METHOD_TYPE_PUT = "PUT";
    public static final String METHOD_TYPE_DELETE = "DELETE";

    public static final RestfulConstants INSTANCE = new RestfulConstants();

    private RestfulConstants() {
    }
}
