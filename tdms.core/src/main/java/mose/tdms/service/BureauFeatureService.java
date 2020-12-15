/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import mose.tdms.modal.Bureau;
import mose.tdms.modal.Feature;
import mose.tdms.modal.Geometry;

/**
 * what:    地物——行调台. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/1
 */
@Service
public class BureauFeatureService extends AbstractFeatureService {
    /**
     * 字段 简称
     */
    public static final String FIELAD_SHORT_NAME = "SHORT_NAME";
    /**
     * 数据集
     */
    @Value("${supermap.dataset.bureau}")
    protected String dataset;
    /**
     * 数据新增服务地址
     */
    @Value("${supermap.data.bureau.add.url}")
    private String addUrl;
    /**
     * 数据更新服务地址
     */
    @Value("${supermap.data.bureau.update.url}")
    private String updateUrl;
    /**
     * 数据删除服务地址
     */
    @Value("${supermap.data.bureau.delete.url}")
    private String deleteUrl;

    @Override
    public int getId(Object modal) {
        Bureau bureau = (Bureau) modal;
        return bureau.getId();
    }

    @Override
    public Feature toFeature(Object modal) {
        Bureau bureau = (Bureau) modal;

        Geometry geometry = new Geometry();
        geometry.setType(Geometry.TYPE_POINT);
        geometry.addPoint(bureau.getLongitude(), bureau.getLatitude());
        geometry.setId(getSmId(bureau.getId()));

        Feature feature = new Feature();
        feature.addFieldNameValue(Feature.FIELD_SM_USER_ID, bureau.getId());
        feature.addFieldNameValue(BureauFeatureService.FIELAD_SHORT_NAME, bureau.getShortName());
        feature.setGeometry(geometry);
        return feature;
    }

    @Override
    public String getDataset() {
        return dataset;
    }

    @Override
    public String getAddUrl() {
        return addUrl;
    }

    @Override
    public String getUpdateUrl() {
        return updateUrl;
    }

    @Override
    public String getDeleteUrl() {
        return deleteUrl;
    }
}
