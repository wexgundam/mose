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
import java.util.List;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/7/27
 */
public class CreateBureauPolygonFileByLineView {
    @Test
    public void doCreate() throws IOException {
        List<BureauPolygonSuperMapJson> bureauPolygonJsons = new ArrayList<>();
        /////////
        InputStream inputStream = this.getClass().getResourceAsStream("bureauPolygon.json");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);

//        Map<Integer,List<BureauPolygonJson>>
//        for (BureauPolygonJson bureauPolygonJson : JsonUtil.toObject(json, BureauPolygonJson[].class)) {
//
//        }
////            nodeViews.put(bureauPolygonJson.getX() + "-" + bureauPolygonJson.getY(), bureauPolygonJson);
//            BureauPolygonSuperMapJson superMapJson = new BureauPolygonSuperMapJson();
//            //            LineJson lineJson = new LineJson();
//            superMapJson.setFeature(bureauPolygonJson.getId());
////            lineJson.setSourceNodeName(railLocationSource.getStationName());
////            lineJson.setTargetNodeName(railLocationTarget.getStationName());
//            superMapJson.getGeometry().setRegion(new double[][][]{
//                    {
//                            {bureauPolygonJson.getLng(), bureauPolygonJson.getLat()},
//                            {railLocationTarget.getLatLng()[1], railLocationTarget.getLatLng()[0]}
//                    }
//            });
//            bureauPolygonJsons.add(lineJson);
//        }

//        /////////
//        inputStream = this.getClass().getResourceAsStream("lineViews.json");
//        bytes = new byte[inputStream.available()];
//        inputStream.read(bytes);
//        json = new String(bytes);
//        int index = 0;
//        for (LineView lineView : JsonUtil.toObject(json, LineView[].class)) {
//            NodeView nodeViewSource = nodeViews.get(lineView.getSourceX() + "-" + lineView.getSourceY());
//            NodeView nodeViewTarget = nodeViews.get(lineView.getTargetX() + "-" + lineView.getTargetY());
//            if (nodeViewSource == null || nodeViewTarget == null) {
//                continue;
//            }
//            BureauPolygonJson railLocationSource = railLocations.get(nodeViewSource.getName());
//            BureauPolygonJson railLocationTarget = railLocations.get(nodeViewTarget.getName());
//            if (railLocationSource == null || railLocationTarget == null) {
//                continue;
//            }
//
//            LineJson lineJson = new LineJson();
//            lineJson.setFeature(index++);
////            lineJson.setSourceNodeName(railLocationSource.getStationName());
////            lineJson.setTargetNodeName(railLocationTarget.getStationName());
//            lineJson.getGeometry().setLine(new double[][][]{
//                    {
//                            {railLocationSource.getLatLng()[1], railLocationSource.getLatLng()[0]},
//                            {railLocationTarget.getLatLng()[1], railLocationTarget.getLatLng()[0]}
//                    }
//            });
//            bureauPolygonJsons.add(lineJson);
//        }
        json = JsonUtil.toString(bureauPolygonJsons);
        FileOutputStream outputStream = new FileOutputStream("D://bureauPolygonSupermap.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
        System.out.println();
    }

    private static class RailLocationFile {
        private List<BureauPolygonJson> bureauPolygonJsons = null;

        public List<BureauPolygonJson> getBureauPolygonJsons() {
            return bureauPolygonJsons;
        }

        public void setBureauPolygonJsons(List<BureauPolygonJson> bureauPolygonJsons) {
            this.bureauPolygonJsons = bureauPolygonJsons;
        }
    }

    private static class BureauPolygonJson {
        private int bureauCode;
        private int latLngIndex;
        private double lng;
        private double lat;
        private int id;

        public int getBureauCode() {
            return bureauCode;
        }

        public void setBureauCode(int bureauCode) {
            this.bureauCode = bureauCode;
        }

        public int getLatLngIndex() {
            return latLngIndex;
        }

        public void setLatLngIndex(int latLngIndex) {
            this.latLngIndex = latLngIndex;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    private static class BureauPolygonSuperMapJson {
        private int feature;
        private int[] attributes = new int[]{0};
        private RegionGeometry geometry = new RegionGeometry();

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

        public RegionGeometry getGeometry() {
            return geometry;
        }

        public void setGeometry(RegionGeometry geometry) {
            this.geometry = geometry;
        }
    }

    private static class RegionGeometry {
        @JsonProperty("Region")
        private double[][][] region = null;

        public double[][][] getRegion() {
            return region;
        }

        public void setRegion(double[][][] region) {
            this.region = region;
        }
    }
}
