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
public class CreateRailStationLocationByTrainlineDepotEntry {
    @Test
    public void doCreate() throws IOException {
        /////////
        Map<String, RailLocationJson> railLocationJsonWithLocationMap = new HashMap<>();
        Map<String, RailLocationJson> railLocationJsonWithoutLocationMap = new HashMap<>();
        List<RailLocationJson> railLocationJsonWithoutLocationList = new ArrayList<>();
//        String filePath="../railLocation.json";
//        InputStream inputStream = this.getClass().getResourceAsStream(filePath);
        String filePath = "D://railLocation.json";
        InputStream inputStream = new FileInputStream(filePath);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);
        RailLocationFile railLocationFile = JsonUtil.toObject(json, RailLocationFile.class);
        for (RailLocationJson railLocationJson : railLocationFile.getRailLocationJsons()) {
            if (railLocationJson.getStationName().isEmpty()) {
                continue;
            }
            if (railLocationJson.getLatLngString().isEmpty()) {
                railLocationJsonWithoutLocationMap.put(railLocationJson.getBureauCode() + "-" + railLocationJson.getStationName(), railLocationJson);
                railLocationJsonWithoutLocationList.add(railLocationJson);
            } else {
                railLocationJsonWithLocationMap.put(railLocationJson.getBureauCode() + "-" + railLocationJson.getStationName(), railLocationJson);
            }
        }

        System.out.println(railLocationJsonWithoutLocationList.size());

        Map<RailLocationJson, List<TrainlineDepotEntry>> connectedRaillocations = new HashMap<>();

        Map<String, List<TrainlineDepotEntry>> trainlineDepotEntries = new HashMap<>();
        inputStream = this.getClass().getResourceAsStream("trainlineDepotEntries.json");
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        json = new String(bytes);

        TrainlineDepotEntry[] trainlineDepotEntriesJson = JsonUtil.toObject(json, TrainlineDepotEntry[].class);
        for (int index = 0; index < trainlineDepotEntriesJson.length; index++) {
            TrainlineDepotEntry trainlineDepotEntry = trainlineDepotEntriesJson[index];
            String key = trainlineDepotEntry.getBureauCode() + "-" + trainlineDepotEntry.getDdtId() + "-" + trainlineDepotEntry.getQueueIndex();
            if (!trainlineDepotEntries.containsKey(key)) {
                trainlineDepotEntries.put(key, new ArrayList<>());
            }
            trainlineDepotEntries.get(key).add(trainlineDepotEntry);


            RailLocationJson railLocationJsonWithoutLocation = railLocationJsonWithoutLocationMap.get(trainlineDepotEntry.getBureauCode() + "-" + trainlineDepotEntry.getEntryText());
            if (railLocationJsonWithoutLocation == null) {
                continue;
            }

            if (!connectedRaillocations.containsKey(railLocationJsonWithoutLocation)) {
                connectedRaillocations.put(railLocationJsonWithoutLocation, new ArrayList<>());
            }

            boolean getPrevious = false;
            boolean getNext = false;
            if (index > 0) {
                TrainlineDepotEntry previousEntry = trainlineDepotEntriesJson[index - 1];
                getPrevious = previousEntry.getBureauCode() == trainlineDepotEntry.getBureauCode()
                        && previousEntry.getDdtId() == trainlineDepotEntry.getDdtId()
                        && previousEntry.getQueueIndex() == trainlineDepotEntry.getQueueIndex();
            }
            if (index < trainlineDepotEntriesJson.length - 1) {
                TrainlineDepotEntry nextEntry = trainlineDepotEntriesJson[index + 1];
                getNext = nextEntry.getBureauCode() == trainlineDepotEntry.getBureauCode()
                        && nextEntry.getDdtId() == trainlineDepotEntry.getDdtId()
                        && nextEntry.getQueueIndex() == trainlineDepotEntry.getQueueIndex();

            }
            if (getPrevious) {
                connectedRaillocations.get(railLocationJsonWithoutLocation).add(trainlineDepotEntriesJson[index - 1]);
            }
            if (getNext) {
                connectedRaillocations.get(railLocationJsonWithoutLocation).add(trainlineDepotEntriesJson[index + 1]);
            }
        }

        for (RailLocationJson railLocationJson : railLocationJsonWithoutLocationList) {
            double lat = 0;
            double lng = 0;
            int count = 0;
            for (TrainlineDepotEntry entry : connectedRaillocations.get(railLocationJson)) {
                String key = entry.getBureauCode() + "-" + entry.getEntryText();
                if (railLocationJsonWithLocationMap.containsKey(key)) {
                    double[] latLng = railLocationJsonWithLocationMap.get(key).getLatLng();
                    lat += latLng[0];
                    lng += latLng[1];
                    count++;
                }
            }
            if (count > 1) {
                railLocationJson.setLatLngString(lat / count + "," + lng / count);
            }
            if (count == 1) {
                railLocationJson.setLatLngString((lat - 0.05) + "," + (lng - 0.02));
            }
        }

        railLocationFile.getRailLocationJsons().clear();
        railLocationFile.getRailLocationJsons().addAll(railLocationJsonWithLocationMap.values());
        railLocationFile.getRailLocationJsons().addAll(railLocationJsonWithoutLocationMap.values());
        json = JsonUtil.toString(railLocationFile);
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
    }

    private static class RailLocationFile {
        private List<RailLocationJson> railLocationJsons = null;

        public List<RailLocationJson> getRailLocationJsons() {
            return railLocationJsons;
        }

        public void setRailLocationJsons(List<RailLocationJson> railLocationJsons) {
            this.railLocationJsons = railLocationJsons;
        }
    }

    private static class RailLocationJson {
        private int bureauCode;
        private String stationName;
        private String latLngString;
        String tencentText;

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

    private static class TrainlineDepotEntry {
        private int bureauCode;
        private int ddtId;
        private int queueIndex;
        private int entryIndex;
        private String entryText;

        public int getBureauCode() {
            return bureauCode;
        }

        public void setBureauCode(int bureauCode) {
            this.bureauCode = bureauCode;
        }

        public int getDdtId() {
            return ddtId;
        }

        public void setDdtId(int ddtId) {
            this.ddtId = ddtId;
        }

        public int getQueueIndex() {
            return queueIndex;
        }

        public void setQueueIndex(int queueIndex) {
            this.queueIndex = queueIndex;
        }

        public int getEntryIndex() {
            return entryIndex;
        }

        public void setEntryIndex(int entryIndex) {
            this.entryIndex = entryIndex;
        }

        public String getEntryText() {
            return entryText;
        }

        public void setEntryText(String entryText) {
            this.entryText = entryText;
        }
    }
}
