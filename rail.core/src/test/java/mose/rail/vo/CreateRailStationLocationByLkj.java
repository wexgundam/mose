/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mose.core.json.JsonUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/7/27
 */
public class CreateRailStationLocationByLkj {
    @Test
    public void all() throws IOException {
        int previousCount = -1;
        int count = 0;
        while (count != previousCount) {
            previousCount = count;
            count = find();
        }
    }

    @Test
    public void doCreate() throws IOException {
        find();
    }

    private int find() throws IOException {
        /////////
        Map<String, RailLocationJson> railLocationJsonWithLocationMap = new HashMap<>();
        Map<String, RailLocationJson> railLocationJsonWithoutLocationMap = new HashMap<>();
        List<RailLocationJson> railLocationJsonWithoutLocationList = new ArrayList<>();
        String filePath = "D://railLocation.json";
        InputStream inputStream = new FileInputStream(filePath);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);
        RailLocationFile railLocationFile = JsonUtil.toObject(json, RailLocationFile.class);
        for (RailLocationJson railLocationJson : railLocationFile.getRailLocations()) {
            if (railLocationJson.getLkjStationName().isEmpty()) {
                continue;
            }
            String key = getKey(railLocationJson);
            if (railLocationJson.getLatLngString().isEmpty()) {
                railLocationJsonWithoutLocationMap.put(key, railLocationJson);
                railLocationJsonWithoutLocationList.add(railLocationJson);
            } else {
                railLocationJsonWithLocationMap.put(key, railLocationJson);
            }
        }

        System.out.println(railLocationJsonWithoutLocationList.size());

        inputStream = new FileInputStream("D://lkj.json");
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        json = new String(bytes);

        LkjJson lkjJson = JsonUtil.toObject(json, LkjJson.class);

        //某station关联的所有lkjMap
        Map<String, List<Lkj>> stationLkjMap = new HashMap<>();
        List<Lkj> lkjs = lkjJson.getLkjs();
        for (int index = 0; index < lkjs.size(); index++) {
            Lkj lkj = lkjs.get(index);
            String key = getKey(lkj);
            if (!stationLkjMap.containsKey(key)) {
                stationLkjMap.put(key, new ArrayList<>());
            }

            List<Lkj> connectedLkjs = stationLkjMap.get(key);
            if (index > 0) {
                Lkj previousLkj = lkjs.get(index - 1);
                if (previousLkj.getRailwaylineName().equals(lkj.getRailwaylineName())) {
                    connectedLkjs.add(previousLkj);
                }
            }
            if (index < lkjs.size() - 2) {
                Lkj nextLkj = lkjs.get(index + 1);
                if (nextLkj.getRailwaylineName().equals(lkj.getRailwaylineName())) {
                    connectedLkjs.add(nextLkj);
                }
            }
        }

        //遍历没有latLng的station
        for (RailLocationJson railLocationJson : railLocationJsonWithoutLocationList) {
            String key = getKey(railLocationJson);
            if (!stationLkjMap.containsKey(key)) {
                continue;
            }

            double lng = 0d;
            double lat = 0d;
            int count = 0;
            for (Lkj lkj : stationLkjMap.get(key)) {
                String connectedLkjKey = getKey(lkj);
                if (railLocationJsonWithLocationMap.containsKey(connectedLkjKey)) {
                    RailLocationJson connectedRailLocation = railLocationJsonWithLocationMap.get(connectedLkjKey);
                    lat += connectedRailLocation.getLatLng()[0];
                    lng += connectedRailLocation.getLatLng()[1];
                    count++;
                }
            }

            if (count > 1) {
                railLocationJson.setLatLngString(lat / count + "," + lng / count);
                System.out.println(getKey(railLocationJson));
            } else if (count == 1) {
                railLocationJson.setLatLngString(lat + "," + (lng + 0.02));
                System.out.println(getKey(railLocationJson));
            }
        }

        railLocationFile.getRailLocations().clear();
        railLocationFile.getRailLocations().addAll(railLocationJsonWithLocationMap.values());
        railLocationFile.getRailLocations().addAll(railLocationJsonWithoutLocationMap.values());
        json = JsonUtil.toString(railLocationFile);
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();

        return railLocationJsonWithoutLocationList.size();
    }

    private String getKey(Lkj lkj) {
        return lkj.getBureauCode() + "@" + lkj.getStationName();
    }

    private String getKey(RailLocationJson railLocationJson) {
        return railLocationJson.getBureauCode() + "@" + railLocationJson.getLkjStationName();
    }

    private static class RailLocationFile {
        private List<RailLocationJson> railLocations = null;

        public List<RailLocationJson> getRailLocations() {
            return railLocations;
        }

        public void setRailLocations(List<RailLocationJson> railLocations) {
            this.railLocations = railLocations;
        }
    }

    private static class RailLocationJson {
        private int bureauCode;
        private String stationName;
        private String latLngString;
        private String tencentText;
        private String lkjStationName;

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

        @JsonIgnore
        public double[] getLatLng() {
            if (latLngString.isEmpty()) {
                return null;
            } else {
                String[] split = latLngString.split(",");
                return new double[]{Double.parseDouble(split[0]), Double.parseDouble(split[1])};
            }
        }

        public String getLatLngString() {
            return latLngString;
        }

        public void setLatLngString(String latLngString) {
            this.latLngString = latLngString;
        }

        public String getTencentText() {
            return tencentText;
        }

        public void setTencentText(String tencentText) {
            this.tencentText = tencentText;
        }

        public String getLkjStationName() {
            return lkjStationName;
        }

        public void setLkjStationName(String lkjStationName) {
            this.lkjStationName = lkjStationName;
        }
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
