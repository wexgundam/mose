/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.controller;

import mose.core.json.JsonUtil;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.web.WebUtil;
import mose.rail2.service.NetworkMapService;
import mose.tile.vo.FeaturesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RequestMapping("/rail/networkmap")
@Controller
public class NetworkMapController {
    /**
     * 配置信息
     */
    @Autowired
    private PubConfig pubConfig;

    /**
     * 铁路路网示意图服务
     */
    @Autowired
    private NetworkMapService networkMapService;


    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String url = pubConfig.getDynamicServer() + "/rail/networkmap/index.htm";
        // 设置返回url
        BackUrlUtil.createBackUrl(mv, request, url);
        // 跳转至指定页面
        mv.setViewName("/rail/networkmap/index");
        return mv;
    }

    /**
     * what:    根据用户请求，返回对应的图形对象. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/10/23
     */
    @RequestMapping(value = "/features")
    public void getNodeViewFeatures(@RequestParam(value = "zoom-level") Integer zoomLevel,
                                    @RequestParam(value = "view-width") Double viewWidth,
                                    @RequestParam(value = "view-height") Double viewHeight,
                                    @RequestParam(value = "figure-center-view-coordinate-delta-x") Double figureCenterViewCoordinateDeltaX,
                                    @RequestParam(value = "figure-center-view-coordinate-delta-y") Double figureCenterViewCoordinateDeltaY,
                                    @RequestParam(value = "locked-view-coordinate-delta-x") Double lockedViewCoordinateDeltaX,
                                    @RequestParam(value = "locked-view-coordinate-delta-y") Double lockedViewCoordinateDeltaY,
                                    @RequestParam(value = "previous-zoom-level", required = false) Integer previousZoomLevel,
                                    HttpServletResponse response) {
        FeaturesVo featuresVo = networkMapService.getFeaturesVo(zoomLevel, viewWidth, viewHeight, figureCenterViewCoordinateDeltaX, figureCenterViewCoordinateDeltaY, lockedViewCoordinateDeltaX, lockedViewCoordinateDeltaY, previousZoomLevel);

        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", featuresVo);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }
}
