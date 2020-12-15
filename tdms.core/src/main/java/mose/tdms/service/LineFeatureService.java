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
import mose.tdms.modal.Line;

/**
 * what:    地物——车站关系. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/1
 */
@Service
public class LineFeatureService extends AbstractFeatureService {
    /**
     * 数据集
     */
    @Value("${supermap.dataset.line}")
    protected String dataset;
    /**
     * 数据新增服务地址
     */
    @Value("${supermap.data.line.add.url}")
    private String addUrl;
    /**
     * 数据更新服务地址
     */
    @Value("${supermap.data.line.update.url}")
    private String updateUrl;
    /**
     * 数据删除服务地址
     */
    @Value("${supermap.data.line.delete.url}")
    private String deleteUrl;

    @Override
    public int getId(Object modal) {
        Line line = (Line) modal;
        return line.getId();
    }

    @Override
    public Feature toFeature(Object modal) {
        Line line = (Line) modal;

        Geometry geometry = new Geometry();
        geometry.setType(Geometry.TYPE_LINE);
        geometry.addPoint(line.getStationALongitude(), line.getStationALatitude());
        geometry.addPoint(line.getStationBLongitude(), line.getStationBLatitude());
        geometry.setId(getSmId(line.getId()));

        Feature feature = new Feature();
        feature.addFieldNameValue(Feature.FIELD_SM_USER_ID, line.getId());
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
