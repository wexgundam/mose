/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail.core.vo;

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
 * what:    路局分界口. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/21
 */
public class TestBureauAdjoinPointVoJson {
    @Test
    public void testBureauAdjoinPoints() throws IOException {
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
        List<BureauAdjoinPointVo> bureauAdjoinPointVos = new ArrayList<>();

        //获取路局多边形腾讯Gis坐标
        double[][] adjoinPoints = getBureauAdjoinPoints();
        for (int adjoinPointIndex = 0; adjoinPointIndex < adjoinPoints.length; adjoinPointIndex++) {
            double[] adjoinPoint = adjoinPoints[adjoinPointIndex];

            BureauAdjoinPointVo bureauAdjoinPointVo = new BureauAdjoinPointVo();
            bureauAdjoinPointVo.setId("bureauAdjoinPoint" + (adjoinPointIndex + 1));
            //将腾讯Gis坐标转为墨卡托投影
            double[] mercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{adjoinPoint[1], adjoinPoint[0]});
            //将墨卡托投影转为视图坐标
            bureauAdjoinPointVo.setX((mercator[0] - topLeftMercator[0]) / (topRightMercator[0] - topLeftMercator[0]) * viewSize);
            bureauAdjoinPointVo.setY((topLeftMercator[1] - mercator[1]) / (topRightMercator[1] - bottomLeftMercator[1]) * viewSize);
            bureauAdjoinPointVos.add(bureauAdjoinPointVo);
        }
        data.put("bureauAdjoinPointVos", bureauAdjoinPointVos);
        String json = JsonUtil.toStr(data);
        FileOutputStream outputStream = new FileOutputStream("D://develop//workspace//upload//file//bureauAdjoinPointVos.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
    }

    //获得路局adjoin point的Gis坐标
    //Gis坐标采用腾讯地图坐标格式(lat,lng)
    private double[][] getBureauAdjoinPoints() {
        double[][] bureauAdjoinPoints = new double[][]{
                {46.392010, 123.411740},
                {47.283700, 119.809250},
                {44.922120, 127.143690},
                {45.220630, 126.189160},
                {45.656620, 126.781470},
                {43.876250, 129.289220},
                {40.000128, 119.767594},
                {43.619770, 122.203060},
                {38.939543, 121.312119},
                {38.939543, 121.312119},
                {41.328650, 117.751370},
                {39.757736, 118.705857},
                {39.986850, 117.175120},
                {39.785660, 118.064240},
                {40.720360, 114.551150},
                {37.877720, 113.506860},
                {37.082681, 113.379372},
                {37.449380, 116.288730},
                {36.488560, 115.782350},
                {41.098140, 116.924240},
                {40.012560, 117.325010},
                {39.942440, 119.644110},
                {39.461620, 114.221260},
                {37.885364, 112.586891},
                {39.147090, 118.455610},
                {40.173869, 113.305915},
                {34.631650, 110.311200},
                {34.973860, 110.923380},
                {35.576490, 110.535490},
                {39.039710, 110.445810},
                {40.861480, 114.047090},
                {38.544880, 108.875900},
                {39.277080, 109.840580},
                {36.104730, 114.340610},
                {36.308830, 113.125310},
                {36.513420, 112.926030},
                {36.034130, 112.888890},
                {35.586370, 112.522490},
                {33.271160, 111.512070},
                {33.250410, 111.589360},
                {33.855090, 113.046940},
                {34.501890, 110.313000},
                {34.632250, 115.602730},
                {33.627160, 114.016680},
                {32.493660, 112.085510},
                {32.165274, 112.147986},
                {32.316320, 113.734930},
                {32.807454, 110.217093},
                {32.501480, 115.412090},
                {29.880770, 115.795130},
                {31.009370, 114.021820},
                {27.809800, 114.939290},
                {29.698910, 113.877410},
                {37.481060, 110.666620},
                {33.530995, 110.881807},
                {35.234770, 106.808850},
                {35.222600, 115.451460},
                {35.963880, 115.863440},
                {36.841981, 115.737604},
                {36.488560, 115.782350},
                {34.407050, 115.864410},
                {33.750730, 115.483550},
                {29.460817, 117.591460},
                {31.834140, 115.937130},
                {32.970080, 115.869250},
                {34.537230, 117.329240},
                {34.380279, 118.345757},
                {30.067190, 116.873720},
                {22.720140, 111.636140},
                {27.527887, 120.412338},
                {29.911730, 115.898330},
                {24.784500, 115.027890},
                {27.105340, 115.020870},
                {22.591640, 109.334810},
                {27.624130, 113.676590},
                {25.189880, 114.359810},
                {29.398510, 113.159220},
                {26.423740, 111.544720},
                {26.131110, 109.623390},
                {21.680900, 110.847990},
                {22.556710, 110.178110},
                {32.465760, 105.823980},
                {31.198550, 107.453160},
                {28.444940, 109.016172},
                {25.319790, 107.460180},
                {26.543380, 101.851380},
                {26.589448, 104.852829},
                {26.265530, 104.155710},
                {25.280270, 104.742730},
                {34.565909, 105.898950},
                {35.313860, 107.752410},
                {37.561970, 107.577680},
                {39.262627, 106.727972},
                {32.594550, 105.779900},
                {38.863622, 93.885997},
                {40.792850, 96.189090},
                {36.341029, 102.864979}
        };
        return bureauAdjoinPoints;
    }

    private static class BureauAdjoinPointVo {
        private String id;
        private String type = "rect";
        private double x = 0;
        private double y = 0;
        private double size = 0.3;
        private Map<String, Object> style;

        public BureauAdjoinPointVo() {
            style = new HashMap<>();
            style.put("stroke", "green");
            style.put("lineWidth", 0.3);
            style.put("fill", "green");
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
