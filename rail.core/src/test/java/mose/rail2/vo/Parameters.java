/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail2.vo;

import mose.core.gis.GisUtil;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/6/9
 */
public class Parameters {
    //基于给定地图图片的墨卡托投影转换坐标
    //左上
    public static final double[] topLeftMercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{70.488281, 55.128649});
    //右上
    public static final double[] topRightMercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{137.285156, 55.128649});
    //左下
    public static final double[] bottomLeftMercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{70.488281, 0.351560});
    //图片尺寸
    public static final double viewSize = 256;
}
