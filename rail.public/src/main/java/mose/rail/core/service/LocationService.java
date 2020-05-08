/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail.core.service;

import mose.core.cache.EhCacheUtil;
import mose.core.json.JsonUtil;
import mose.rail.core.vo.StationLocationVo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * what:    位置服务. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/22
 */
@Service
public class LocationService {
    /**
     * 铁路位置信息缓存
     */
    public static final String RAIL_LOCATION_CACHE = "railLocationCache";

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

    /**
     * what:    加载位置数据. <br/>
     * when:    (这里描述这个方法适用时机 – 可选).<br/>
     * how:     (这里描述这个方法的执行流程或使用方法 – 可选).<br/>
     * warning: (这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param
     *
     * @return
     *
     * @author 靳磊 created on 2020/4/22
     */
    @PostConstruct
    public void initData() throws IOException {
        Resource data = new ClassPathResource("mose/rail/core/railLocation.json");
        RailLocationFile railLocationFile = JsonUtil.toObject(new String(FileCopyUtils.copyToByteArray(data.getFile()), "UTF-8"), RailLocationFile.class);
        for (int index = 0; index < railLocationFile.getRailLocationJsons().size(); index++) {
            RailLocationJson railLocationJson = railLocationFile.getRailLocationJsons().get(index);
            if (railLocationJson.getStationName().isEmpty()) {
                continue;
            }
            if (railLocationJson.getLatLngString().isEmpty()) {
                continue;
            }
            EhCacheUtil.put(RAIL_LOCATION_CACHE, railLocationJson.getStationName(), railLocationJson.getLatLng());
        }
    }

    /**
     * what:    根据给定的车站名，返回对应车站位置信息. <br/>
     * when:    (这里描述这个方法适用时机 – 可选).<br/>
     * how:     (这里描述这个方法的执行流程或使用方法 – 可选).<br/>
     * warning: (这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param stationName 车站名
     *
     * @return
     *
     * @author 靳磊 created on 2020/4/23
     */
    public StationLocationVo getStationLocationVo(String stationName) {
        double[] latLng = EhCacheUtil.get(RAIL_LOCATION_CACHE, stationName);
        if (latLng != null) {
            StationLocationVo vo = new StationLocationVo();
            vo.setTdmsStationName(stationName);
            vo.setLatLng(latLng);
            return vo;
        } else {
            return null;
        }
    }

    /**
     * what:    通过腾讯地图获取地点集合. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/4/30
     */
    public List getLocations(String keyword) {
        StringBuffer url = new StringBuffer();
        url.append("https://apis.map.qq.com/ws/place/v1/search?");
        url.append("boundary=region(全国)");
//        url.append("&filter=category=火车站");
        url.append("&page_size=20");
        url.append("&page_index=1");
        url.append("&orderby=_distance");
        url.append("&key=SJ3BZ-QX3LF-3AKJA-NRZCQ-ILP4J-H4FKN");
        url.append("&keyword=");
        url.append(keyword);
        RestTemplate restTemplate = new RestTemplate();
        Map result = restTemplate.getForObject(url.substring(0), Map.class);
        if (result.get("status").equals(0)) {
            List<Map> data = (List<Map>) result.get("data");
            for (int index = 0; index < data.size(); index++) {
                Map<String, Object> entry = (Map) data.get(index);
                entry.remove("id");
                entry.remove("address");
                entry.remove("tel");
                entry.remove("category");
                entry.remove("ad_info");
                entry.remove("pano");
                entry.remove("type");
                entry.remove("type");
                entry.put("locationName", entry.get("title"));
                entry.remove("title");
                Map<String, Double> location = (Map<String, Double>) entry.get("location");
                entry.put("latLng", new double[]{location.get("lat"), location.get("lng")});
                entry.remove("location");
            }
            return data;
        } else {
            return Collections.emptyList();
        }
    }
}
