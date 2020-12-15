/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.modal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    查询参数对象. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/3
 */
public class Query {
    /**
     * 查询模式 通过sql查询
     */
    public static final String QUERY_MODE_SQLQUERY = "SqlQuery";
    /**
     * 查询模式 查询最近地物
     */
    public static final String QUERY_MODE_FINDNEAREST = "FindNearest";
    /**
     * 查询参数 key sql过滤
     */
    public static final String QUERY_PARAM = "queryParams";
    /**
     * 查询参数 key sql过滤
     */
    public static final String QUERY_PARAM_KEY_SQL = "attributeFilter";
    /**
     * 查询参数 key 数据集
     */
    public static final String QUERY_PARAM_KEY_DATASET = "name";
    /**
     * 查询模式
     */
    private String queryMode;
    /**
     * 查询参数
     */
    private Map<String, Object> queryParameters;
    /**
     * 几何图形参数
     */
    private Geometry geometry;
    /**
     * 距离参数
     */
    private double distance;

    /**
     * 添加QueryParameter
     *
     * @param key
     * @param value
     */
    public void addQueryParameter(String key, Object value) {
        getQueryParameters().put(key, value);
    }

    /**
     * 添加QueryParam
     *
     * @param queryParam
     */
    public void addQueryParam( Object queryParam) {
        ((List) getQueryParameters().get(Query.QUERY_PARAM)).add(queryParam);
    }

    public String getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(String queryMode) {
        this.queryMode = queryMode;
    }

    public Map<String, Object> getQueryParameters() {
        if (queryParameters == null) {
            queryParameters = new HashMap<>();
            queryParameters.put(Query.QUERY_PARAM, new ArrayList<>());
        }
        return queryParameters;
    }

    public void setQueryParameters(Map<String, Object> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}