/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail2.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mose.core.json.JsonUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/7/27
 */
public class CreateRailLineByLkj {
    @Test
    public void doCreate() throws IOException {
        List<LineJson> lineJsons = new ArrayList<>();

        InputStream inputStream = new FileInputStream("D://lkj.json");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);


        LkjJson lkjJson = JsonUtil.toObject(json, LkjJson.class);
        List<Lkj> lkjs = lkjJson.getLkjs();

        List<String> addes = new ArrayList<>();

        for (int index = 1; index < lkjs.size(); index++) {
            Lkj previousLkj = lkjs.get(index - 1);
            Lkj lkj = lkjs.get(index);
            String key1 = getKey1(previousLkj, lkj);
            String key2 = getKey2(previousLkj, lkj);
            if (addes.contains(key1) || addes.contains(key2)) {
                continue;
            }
            if (lkj.getRailwaylineName().equals(previousLkj.getRailwaylineName())) {
                double[] previousLkjLatLng = previousLkj.getLatLng();
                double[] lkjLatLng = lkj.getLatLng();
                LineJson lineJson = new LineJson();
                lineJson.setFeature(index);
                lineJson.getGeometry().setLine(new double[][][]{
                        {
                                {previousLkjLatLng[1], previousLkjLatLng[0]},
                                {lkjLatLng[1], lkjLatLng[0]}
                        }
                });
                lineJsons.add(lineJson);

                addes.add(key1);
                addes.add(key2);
            }
        }

        System.out.println(lineJsons.size());
        json = JsonUtil.toString(lineJsons);
        FileOutputStream outputStream = new FileOutputStream("D://line_lkj.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
    }

    private String getKey1(Lkj previousLkj, Lkj lkj) {
        return previousLkj.getBureauCode() + previousLkj.getStationName() + "@" + lkj.getBureauCode() + lkj.getStationName();
    }

    private String getKey2(Lkj previousLkj, Lkj lkj) {
        return lkj.getBureauCode() + lkj.getStationName() + "@" + previousLkj.getBureauCode() + previousLkj.getStationName();
    }

    private static class LineJson {
        private int feature;
        private int[] attributes = new int[]{0};
        private LineGeometry geometry = new LineGeometry();

        public int getFeature() {
            return feature;
        }

        public void setFeature(int feature) {
            this.feature = feature;
        }

        public int[] getAttributes() {
            return attributes;
        }

        public void setAttributes(int[] attributes) {
            this.attributes = attributes;
        }

        public LineGeometry getGeometry() {
            return geometry;
        }

        public void setGeometry(LineGeometry geometry) {
            this.geometry = geometry;
        }
    }

    private static class LineGeometry {
        @JsonProperty("Line")
        private double[][][] line = null;

        public double[][][] getLine() {
            return line;
        }

        public void setLine(double[][][] line) {
            this.line = line;
        }
    }

    private static class LkjJson {
        private List<Lkj> lkjs;

        public List<Lkj> getLkjs() {
            lkjs = lkjs == null ? new ArrayList<>() : lkjs;
            return lkjs;
        }

        public void setLkjs(List<Lkj> lkjs) {
            this.lkjs = lkjs;
        }
    }

    private static class Lkj {
        private String railwaylineName;
        private int bureauCode;
        private String stationName;
        private String stationLatLng;

        @JsonIgnore
        public double[] getLatLng() {
            if (stationLatLng.isEmpty()) {
                return null;
            } else {
                String[] split = stationLatLng.split(",");
                return new double[]{Double.parseDouble(split[0]), Double.parseDouble(split[1])};
            }
        }

        public String getStationLatLng() {
            return stationLatLng;
        }

        public void setStationLatLng(String stationLatLng) {
            this.stationLatLng = stationLatLng;
        }

        public String getRailwaylineName() {
            return railwaylineName;
        }

        public void setRailwaylineName(String railwaylineName) {
            this.railwaylineName = railwaylineName;
        }

        public int getBureauCode() {
            return bureauCode;
        }

        public void setBureauCode(int bureauCode) {
            this.bureauCode = bureauCode;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }
    }
}
