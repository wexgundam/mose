/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail2.vo;

import mose.core.gis.GisUtil;
import mose.core.json.JsonUtil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    路局key point. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/21
 */
public class TestBureauKeyPointVoJson {
    @Test
    public void testBureauKeyPoints() throws IOException {
        //基于给定地图图片的墨卡托投影转换坐标
        //左上
        double[] topLeftMercator = Parameters.topLeftMercator;
        //右上
        double[] topRightMercator = Parameters.topRightMercator;
        //左下
        double[] bottomLeftMercator = Parameters.bottomLeftMercator;
        //图片尺寸
        double viewSize = Parameters.viewSize;

        Map<String, Object> data = new HashMap<>();
        List<BureauKeyPointVo> bureauKeyPointVos = new ArrayList<>();

        //获取路局多边形腾讯Gis坐标
        double[][] keyPoints = getBureauKeyPoints();
        for (int bureauIndex = 0; bureauIndex < keyPoints.length; bureauIndex++) {
            double[] keyPoint = keyPoints[bureauIndex];

            BureauKeyPointVo bureauKeyPointVo = new BureauKeyPointVo();
            bureauKeyPointVo.setId("bureauKeyPoint" + (bureauIndex + 1));
            //将腾讯Gis坐标转为墨卡托投影
            double[] mercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{keyPoint[1], keyPoint[0]});
            //将墨卡托投影转为视图坐标
            bureauKeyPointVo.setX((mercator[0] - topLeftMercator[0]) / (topRightMercator[0] - topLeftMercator[0]) * viewSize);
            bureauKeyPointVo.setY((topLeftMercator[1] - mercator[1]) / (topRightMercator[1] - bottomLeftMercator[1]) * viewSize);
            bureauKeyPointVos.add(bureauKeyPointVo);
        }
        data.put("bureauKeyPointVos", bureauKeyPointVos);
        String json = JsonUtil.toString(data);
        FileOutputStream outputStream = new FileOutputStream("D://develop//workspace//upload//file//bureauKeyPointVos.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
    }

    //获得路局key point的Gis坐标
    //Gis坐标采用腾讯地图坐标格式(lat,lng)
    private double[][] getBureauKeyPoints() {
        double[][] bureauKeyPoints = new double[][]{
                {45.751390, 126.635060},
                {41.801406, 123.407629},
                {39.906550, 116.332040},
                {37.878990, 112.584060},
                {40.825310, 111.667059},
                {34.736331, 113.655668},
                {30.543690, 114.337760},
                {34.241856, 108.977666},
                {36.669297, 116.994995},
                {31.250430, 121.478220},
                {28.661830, 115.916420},
                {23.124780, 113.300550},
                {22.834578, 108.407605},
                {30.686344, 104.071428},
                {25.030220, 102.717440},
                {36.042144, 103.839953},
                {43.870994, 87.560003},
                {36.615380, 101.808610}
        };
        return bureauKeyPoints;
    }

    private static class BureauKeyPointVo {
        private String id;
        private String type = "circle";
        private double x = 0;
        private double y = 0;
        private double size = 2;
        private Map<String, Object> style;

        public BureauKeyPointVo() {
            style = new HashMap<>();
            style.put("stroke", "red");
            style.put("fill", "red");
            style.put("fillOpacity", 1);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public Map<String, Object> getStyle() {
            return style;
        }

        public void setStyle(Map<String, Object> style) {
            this.style = style;
        }
    }
}
