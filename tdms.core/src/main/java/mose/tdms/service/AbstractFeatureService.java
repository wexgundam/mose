/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import mose.core.restful.RestfulTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import mose.tdms.modal.Feature;
import mose.tdms.modal.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    抽象FeatureService. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/3
 */
public abstract class AbstractFeatureService {

    /**
     * 查询结果字段 总数
     */
    public static final String QUERY_RESULT_TOTAL_COUNT = "totalCount";
    /**
     * 查询结果字段 记录集
     */
    public static final String QUERY_RESULT_RECORDSETS = "recordsets";
    /**
     * 查询结果字段 feature集
     */
    public static final String QUERY_RESULT_FEATURES = "features";
    /**
     * 查询结果字段 feature sm id
     */
    public static final String QUERY_RESULT_FEATURE_SMID = "ID";
    /**
     * restful通用模板
     */
    @Autowired
    protected RestfulTemplate restfulTemplate;
    /**
     * 数据查询服务地址
     */
    @Value("${supermap.query.url}")
    protected String queryUrl;

    /**
     * 转为feature
     *
     * @return
     */
    public abstract Feature toFeature(Object modal);

    /**
     * 获取Model Id
     *
     * @param modal
     *
     * @return
     */
    public abstract int getId(Object modal);

    /**
     * 根据id查询超图数据的smid
     *
     * @param id
     *
     * @return
     */
    public int getSmId(int id) {
        Map<String, Object> sqlQueryParams = new HashMap<>();
        sqlQueryParams.put(Query.QUERY_PARAM_KEY_SQL, "smuserid=" + id);
        sqlQueryParams.put(Query.QUERY_PARAM_KEY_DATASET, getDataset());

        Query query = new Query();
        query.setQueryMode(Query.QUERY_MODE_SQLQUERY);
        query.addQueryParam(sqlQueryParams);

        Map result = restfulTemplate.postForEntity(queryUrl, query, Map.class).getBody();
        return getSmId(result);
    }

    /**
     * 获取smid
     *
     * @param result
     *
     * @return
     */
    private int getSmId(Map result) {
        if ((int) result.get(QUERY_RESULT_TOTAL_COUNT) == 0) {
            return -1;
        }
        return (int) ((Map) ((List) ((Map) ((List) result.get(QUERY_RESULT_RECORDSETS)).get(0)).get(QUERY_RESULT_FEATURES)).get(0)).get(QUERY_RESULT_FEATURE_SMID);
    }

    /**
     * 向Gis服务器发送新增Feature请求
     *
     * @param modal
     *
     * @return
     */
    public boolean addOne(Object modal) {
        Feature feature = toFeature(modal);

        List<Feature> features = new ArrayList<>();
        features.add(feature);

        Map result = restfulTemplate.postForEntity(getAddUrl(), features, Map.class).getBody();
        return (boolean) result.get("succeed");
    }

    /**
     * 向Gis服务器发送更新Feature请求
     *
     * @param modal
     *
     * @return
     */
    public boolean updateOne(Object modal) {
        Feature feature = toFeature(modal);

        List<Feature> features = new ArrayList<>();
        features.add(feature);

        Map result = restfulTemplate.postForEntity(getUpdateUrl(), features, Map.class).getBody();
        return (boolean) result.get("succeed");
    }

    /**
     * 向Gis服务器发送删除Feature请求
     *
     * @param modal
     *
     * @return
     */
    public boolean deleteOne(Object modal) {
        int smId = getSmId(getId(modal));

        if (smId == -1) {
            return false;
        }

        List<Integer> smIds = new ArrayList<>();
        smIds.add(smId);

        Map result = restfulTemplate.postForEntity(getDeleteUrl(), smIds, Map.class).getBody();
        return (boolean) result.get("succeed");
    }

    /**
     * 获取数据集
     *
     * @return
     */
    public abstract String getDataset();

    /**
     * 获取新增url
     *
     * @return
     */
    public abstract String getAddUrl();

    /**
     * 获取更新url
     *
     * @return
     */
    public abstract String getUpdateUrl();

    /**
     * 获取删除url
     *
     * @return
     */
    public abstract String getDeleteUrl();
}
