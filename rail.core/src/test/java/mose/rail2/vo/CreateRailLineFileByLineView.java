/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import mose.core.json.JsonUtil;
import org.junit.Test;

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
public class CreateRailLineFileByLineView {
    @Test
    public void doCreate() throws IOException {
        List<LineJson> lineJsons = new ArrayList<>();
        /////////
        Map<String, NodeView> nodeViews = new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("nodeViews.json");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);

        for (NodeView nodeView : JsonUtil.toObject(json, NodeView[].class)) {
            nodeViews.put(nodeView.getX() + "-" + nodeView.getY(), nodeView);
        }

        /////////
        Map<String, RailLocationJson> railLocations = new HashMap<>();
        inputStream = this.getClass().getResourceAsStream("../railLocation.json");
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        json = new String(bytes);
        RailLocationFile railLocationFile = JsonUtil.toObject(json, RailLocationFile.class);
        for (RailLocationJson railLocationJson : railLocationFile.getRailLocationJsons()) {
            if (railLocationJson.getStationName().isEmpty()) {
                continue;
            }
            if (railLocationJson.getLatLngString().isEmpty()) {
                continue;
            }
            railLocations.put(railLocationJson.getStationName(), railLocationJson);
        }

        /////////
        inputStream = this.getClass().getResourceAsStream("lineViews.json");
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        json = new String(bytes);
        int index = 0;
        for (LineView lineView : JsonUtil.toObject(json, LineView[].class)) {
            NodeView nodeViewSource = nodeViews.get(lineView.getSourceX() + "-" + lineView.getSourceY());
            NodeView nodeViewTarget = nodeViews.get(lineView.getTargetX() + "-" + lineView.getTargetY());
            if (nodeViewSource == null || nodeViewTarget == null) {
                continue;
            }
            RailLocationJson railLocationSource = railLocations.get(nodeViewSource.getName());
            RailLocationJson railLocationTarget = railLocations.get(nodeViewTarget.getName());
            if (railLocationSource == null || railLocationTarget == null) {
                continue;
            }

            LineJson lineJson = new LineJson();
            lineJson.setFeature(index++);
//            lineJson.setSourceNodeName(railLocationSource.getStationName());
//            lineJson.setTargetNodeName(railLocationTarget.getStationName());
            lineJson.getGeometry().setLine(new double[][][]{
                    {
                            {railLocationSource.getLatLng()[1], railLocationSource.getLatLng()[0]},
                            {railLocationTarget.getLatLng()[1], railLocationTarget.getLatLng()[0]}
                    }
            });
            lineJsons.add(lineJson);
        }
        json = JsonUtil.toString(lineJsons);
        FileOutputStream outputStream = new FileOutputStream("D://test.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
        System.out.println();
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
        private String tencentText;

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
}
