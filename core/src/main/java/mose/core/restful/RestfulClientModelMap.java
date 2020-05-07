package mose.core.restful;

import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/18.
 */
public class RestfulClientModelMap<T> {
    /**
     * 服务是否调用成功
     */
    private boolean success;
    /**
     * 本次服务调用可获得数据的总数
     */
    private int totalCount;
    /**
     * 本次服务返回多条记录时，数据存储在该列表中
     */
    private List<T> items;
    /**
     * 本次服务返回单条记录时，数据存储在该属性中
     */
    private T item;
    /**
     * 服务调用失败时，服务端返回的错误信息
     */
    private String error;

    public void setupModelMap(ModelMap map) {
        map.put(RestfulConstants.SUCCESS, success);
        map.put(RestfulConstants.TOTAL_COUNT, totalCount);
        map.put(RestfulConstants.ITEMS, items);
        map.put(RestfulConstants.ITEM, item);
        map.put(RestfulConstants.ERROR, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        if (items == null) {
            items = new ArrayList<T>();
        }
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
