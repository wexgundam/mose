package mose.rail.core.controller; /**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */

import mose.core.json.JsonUtil;
import mose.core.web.WebUtil;
import mose.rail.core.service.LocationService;
import mose.rail.core.vo.StationLocationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * what:    位置服务控制器. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RequestMapping("/rail/location")
@Controller
public class LocationServiceController {
    @Autowired
    private LocationService locationService;

    @RequestMapping("/query")
    public void query(@RequestParam(value = "station-name") String stationName,
                      HttpServletResponse response) {
        ModelMap modelMap = new ModelMap();
        StationLocationVo stationLocationVo = locationService.getStationLocationVo(stationName);
        if (stationLocationVo != null) {
            modelMap.put("success", true);
            modelMap.put("data", stationLocationVo);
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "no such station whose name is [" + stationName + "].");
            modelMap.put("data", locationService.getLocations(stationName));
        }
        String json = JsonUtil.toStr(modelMap);
        WebUtil.out(response, json);
    }
}
