/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.core.gis;

import org.junit.Assert;
import org.junit.Test;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;

import java.math.BigDecimal;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/21
 */
public class TestGisUtil {
    @Test
    public void test() {
        //北京故宫博物院
        double[] mercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{116.397026, 39.918060});
        System.out.println("x:" + mercator[0]);
        System.out.println("y:" + mercator[1]);

        //基于给定地图图片的墨卡托投影转换坐标
        //左上
        double[] topLeftMercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{70.488281, 55.128649});
        System.out.println("topLeftMercator[:" + topLeftMercator[0] + "][" + topLeftMercator[1] + "]");
        //右上
        double[] topRightMercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{137.285156, 55.128649});
        System.out.println("topLeftMercator[:" + topRightMercator[0] + "][" + topRightMercator[1] + "]");
        //左下
        double[] bottomLeftMercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{70.488281, 0.351560});
        System.out.println("topLeftMercator[:" + bottomLeftMercator[0] + "][" + bottomLeftMercator[1] + "]");
        //北京故宫博物院
        System.out.println("x:" + (mercator[0] - topLeftMercator[0]) / (topRightMercator[0] - topLeftMercator[0]) * 400);
        System.out.println("y:" + (topLeftMercator[1] - mercator[1]) / (topRightMercator[1] - bottomLeftMercator[1]) * 400);
    }

    @Test
    public void testEpsg4326ToEpsg3857() {
        //北京故宫博物院
        double[] targetCoordinates = GisUtil.INSTANCE.epsg4326ToEpsg3857(116.397026, 39.918060);
        System.out.println("x:" + targetCoordinates[0]);
        System.out.println("y:" + targetCoordinates[1]);

        //北京故宫博物院
        targetCoordinates = GisUtil.INSTANCE.epsg4326ToEpsg3857("116.397026,39.918060");
        System.out.println("x:" + targetCoordinates[0]);
        System.out.println("y:" + targetCoordinates[1]);
    }

    @Test
    public void testProjection() {
        double[] c = new double[]{116.291213, 39.836643};
        CoordinateTransformFactory transformFactory = new CoordinateTransformFactory();
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem source = crsFactory.createFromName("EPSG:4326");
        CoordinateReferenceSystem target = crsFactory.createFromName("EPSG:3857");

        CoordinateTransform transform = transformFactory.createTransform(source, target);
        ProjCoordinate minCoordinate = new ProjCoordinate(c[0], c[1]);
        transform.transform(minCoordinate, minCoordinate);
        System.out.println(new BigDecimal(minCoordinate.x));
        Assert.assertFalse(false);
    }
}
