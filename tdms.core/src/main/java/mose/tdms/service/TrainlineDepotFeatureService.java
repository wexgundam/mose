/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import mose.tdms.modal.Feature;
import mose.tdms.modal.Geometry;
import mose.tdms.modal.TrainlineDepot;

/**
 * what:    地物——行调台. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/1
 */
@Service
public class TrainlineDepotFeatureService extends AbstractFeatureService {
    /**
     * 字段 名称
     */
    public static final String FIELAD_NAME = "NAME";
    /**
     * 字段 局代码
     */
    public static final String FIELAD_BUREAU_ID = "BUREAU_ID";
    /**
     * 数据集
     */
    @Value("${supermap.dataset.trainlinedepot}")
    protected String dataset;
    /**
     * 数据新增服务地址
     */
    @Value("${supermap.data.trainlinedepot.add.url}")
    private String addUrl;
    /**
     * 数据更新服务地址
     */
    @Value("${supermap.data.trainlinedepot.update.url}")
    private String updateUrl;
    /**
     * 数据删除服务地址
     */
    @Value("${supermap.data.trainlinedepot.delete.url}")
    private String deleteUrl;

    @Override
    public int getId(Object modal) {
        TrainlineDepot trainlineDepot = (TrainlineDepot) modal;
        return trainlineDepot.getId();
    }

    @Override
    public Feature toFeature(Object modal) {
        TrainlineDepot trainlineDepot = (TrainlineDepot) modal;

        Geometry geometry = new Geometry();
        geometry.setType(Geometry.TYPE_POINT);
        geometry.addPoint(trainlineDepot.getLongitude(), trainlineDepot.getLatitude());
        geometry.setId(getSmId(trainlineDepot.getId()));

        Feature feature = new Feature();
        feature.addFieldNameValue(Feature.FIELD_SM_USER_ID, trainlineDepot.getId());
        feature.addFieldNameValue(TrainlineDepotFeatureService.FIELAD_BUREAU_ID, trainlineDepot.getBureauId());
        feature.addFieldNameValue(TrainlineDepotFeatureService.FIELAD_NAME, trainlineDepot.getName());
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
