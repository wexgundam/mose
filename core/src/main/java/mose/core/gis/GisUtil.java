/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.core.gis;

import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;

/**
 * what:    gis工具. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/6/4
 */
public class GisUtil {
    public static final GisUtil INSTANCE = new GisUtil();

    private GisUtil() {
    }

    /**
     * what:    将经纬度转为墨卡托投影. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/6/4
     */
    public double[] lngLat2Mercator(double[] lngLat) {
        double[] mercator = new double[2];
        double x = lngLat[0] * 20037508.34 / 180;
        double y = Math.log(Math.tan((90 + lngLat[1]) * Math.PI / 360)) / (Math.PI / 180);
        y = y * 20037508.34 / 180;
        mercator[0] = x;
        mercator[1] = y;
        return mercator;
    }

    /**
     * what:    将墨卡托投影转为经纬度. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/6/4
     */
    public double[] mercator2lonLat(double[] mercator) {
        double[] lngLat = new double[2];
        double x = mercator[0] / 20037508.34 * 180;
        double y = mercator[1] / 20037508.34 * 180;
        y = 180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2);
        lngLat[0] = x;
        lngLat[1] = y;
        return lngLat;
    }

    /**
     * what:    EPSG4326转EPSG3857. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public double[] epsg4326ToEpsg3857(double lng, double lat) {
        CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem sourceCrs = factory.createFromName("EPSG:4326"); // Use "EPSG:4326" here.
        CoordinateReferenceSystem targetCrs = factory.createFromName("EPSG:3857"); // Use "EPSG:3857" here.
        CoordinateTransform transform = new CoordinateTransformFactory().createTransform(sourceCrs, targetCrs);

        ProjCoordinate sourceCoordinate = new ProjCoordinate(lng, lat);
        ProjCoordinate targetCoordinate = new ProjCoordinate();
        transform.transform(sourceCoordinate, targetCoordinate);
        return new double[]{targetCoordinate.x, targetCoordinate.y};
    }

    /**
     * what:    EPSG4326转EPSG3857. <br/>
     * EPSG4326由"lng,lat"字符串表示
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public double[] epsg4326ToEpsg3857(String epsg4326) {
        String[] split = epsg4326.split(",");
        return GisUtil.INSTANCE.epsg4326ToEpsg3857(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
    }
}
