/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.core.controller;

import mose.core.json.JsonUtil;
import mose.core.pub.PubConfig;
import mose.core.string.BackUrlUtil;
import mose.core.time.TimeUtil;
import mose.core.web.WebUtil;
import mose.rail.core.modal.Trainline;
import mose.rail.core.modal.TrainlineItem;
import mose.rail.core.modal.TrainlineMap;
import mose.rail.core.modal.TrainlineMapTableLeftHeaderItem;
import mose.rail.core.service.TrainlineMapService;
import mose.rail.core.vo.TrainlineMapTrainlineVo;
import mose.rail.core.vo.TrainlineMapVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RequestMapping("/rail/trainlinemap")
@Controller
public class TrainlineMapController {
    /**
     * 配置信息
     */
    @Autowired
    private PubConfig pubConfig;

    /**
     * 铁路路网示意图服务
     */
    @Autowired
    private TrainlineMapService trainlineMapService;


    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String url = pubConfig.getDynamicServer() + "/rail/trainlinemap/index.htm";
        // 设置返回url
        BackUrlUtil.createBackUrl(mv, request, url);
        // 跳转至指定页面
        mv.setViewName("/rail/trainlinemap/index");
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
    public void getFeatures(@RequestParam(value = "zoom-level") Integer zoomLevel,
                            @RequestParam(value = "view-width") Double viewWidth,
                            @RequestParam(value = "view-height") Double viewHeight,
                            @RequestParam(value = "figure-center-view-coordinate-delta-x") Double figureCenterViewCoordinateDeltaX,
                            @RequestParam(value = "figure-center-view-coordinate-delta-y") Double figureCenterViewCoordinateDeltaY,
                            @RequestParam(value = "locked-view-coordinate-delta-x") Double lockedViewCoordinateDeltaX,
                            @RequestParam(value = "locked-view-coordinate-delta-y") Double lockedViewCoordinateDeltaY,
                            @RequestParam(value = "previous-zoom-level", required = false) Integer previousZoomLevel,
                            HttpServletResponse response) {
//        TrainlineMapVo trainlineMapVo = trainlineMapService.getFeaturesVo(0, 1, date, 9,
//                zoomLevel, viewWidth, viewHeight, figureCenterViewCoordinateDeltaX, figureCenterViewCoordinateDeltaY, lockedViewCoordinateDeltaX, lockedViewCoordinateDeltaY, previousZoomLevel);

        //缩放即调整运行图绘图参数

        TrainlineMap trainlineMap = getTrainlineMap();

        TrainlineMapVo trainlineMapVo = trainlineMapService.getFeaturesVo(trainlineMap);

        for (int step = 0; step < trainlineMap.getTimeAxisHourCount() * 6; step++) {
            Date time = TimeUtil.INSTANCE.addMinutes(trainlineMap.getTimeAxisStartTime(), 10 * step);
            Trainline trainline = getTrainline(time);
            TrainlineMapTrainlineVo trainlineMapTrainlineVo = trainlineMapService.getFeaturesVo(trainlineMap, trainline);
            trainlineMapVo.getTrainlines().add(trainlineMapTrainlineVo);
        }

        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", trainlineMapVo);
        String json = JsonUtil.toStr(modelMap);
        WebUtil.out(response, json);
    }


    private TrainlineMap getTrainlineMap() {
        Calendar calendar = TimeUtil.INSTANCE.truncate(Calendar.getInstance(), Calendar.DATE);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        Date startTime = calendar.getTime();

        TrainlineMap trainlineMap = new TrainlineMap();
        trainlineMap.setId(1);
        trainlineMap.setName("测试");
        trainlineMap.setTimeAxisStartTime(startTime);
        trainlineMap.setTimeAxisHourCount(7);
        trainlineMap.setRouteAnchorPointsString("0@0;50@50;100@100;150@150");

        List<TrainlineMapTableLeftHeaderItem> elements = new ArrayList<>();
        TrainlineMapTableLeftHeaderItem item = new TrainlineMapTableLeftHeaderItem();
        item.setId(1);
        item.setDetailVisible(false);
        item.setRelativeRuntime(0);
        item.setVisible(true);
        item.setOrder(0);
        item.setNetworkElementId(0);
        item.setNetworkElementName("A-1");
        item.setNetworkElementBasePointString("0@0");
        elements.add(item);
        item = new TrainlineMapTableLeftHeaderItem();
        item.setId(2);
        item.setDetailVisible(false);
        item.setRelativeRuntime(30);
        item.setVisible(true);
        item.setOrder(1);
        item.setNetworkElementId(1);
        item.setNetworkElementName("B-1");
        item.setNetworkElementBasePointString("50@50");
        elements.add(item);
        item = new TrainlineMapTableLeftHeaderItem();
        item.setId(3);
        item.setDetailVisible(false);
        item.setRelativeRuntime(80);
        item.setVisible(true);
        item.setOrder(2);
        item.setNetworkElementId(2);
        item.setNetworkElementName("C-1");
        item.setNetworkElementBasePointString("100@100");
        elements.add(item);
        item = new TrainlineMapTableLeftHeaderItem();
        item.setId(4);
        item.setDetailVisible(false);
        item.setRelativeRuntime(120);
        item.setVisible(true);
        item.setOrder(3);
        item.setNetworkElementId(3);
        item.setNetworkElementName("D-1");
        item.setNetworkElementBasePointString("150@150");
        elements.add(item);

        trainlineMap.setRuntimeCount(120);
        trainlineMap.setTableLeftHeaderItems(elements);
        return trainlineMap;
    }

    private Trainline getTrainline(Date time) {
        Trainline trainline = new Trainline();
        trainline.setId(1);
        trainline.setName("G1");
        trainline.setRouteAnchorPointsString("0@0;50@50;100@100;150@150");

        List<TrainlineItem> items = new ArrayList<>();
        TrainlineItem item = new TrainlineItem();
        item.setId(1);
        item.setOrder(0);
        item.setNetworkElementId(0);
        item.setNetworkElementName("A-1");
        item.setNetworkElementBasePointString("0@0");
        item.setTime(time);
        items.add(item);

        item = new TrainlineItem();
        item.setId(2);
        item.setOrder(1);
        item.setNetworkElementId(1);
        item.setNetworkElementName("B-1");
        item.setNetworkElementBasePointString("50@50");
        item.setTime(TimeUtil.INSTANCE.addMinutes(time, 20));
        items.add(item);

        item = new TrainlineItem();
        item.setId(3);
        item.setOrder(2);
        item.setNetworkElementId(2);
        item.setNetworkElementName("C-1");
        item.setNetworkElementBasePointString("100@100");
        item.setTime(TimeUtil.INSTANCE.addMinutes(time, 40));
        items.add(item);

        item = new TrainlineItem();
        item.setId(4);
        item.setOrder(3);
        item.setNetworkElementId(3);
        item.setNetworkElementName("D-1");
        item.setNetworkElementBasePointString("150@150");
        item.setTime(TimeUtil.INSTANCE.addMinutes(time, 60));
        items.add(item);

        trainline.setItems(items);
        return trainline;
    }

//    public TrainlineMapVo getFeaturesVo(Integer trainlineMapId,
//                                        Integer trainlineMapType,
//                                        Date trainlineMapStartTime,
//                                        Integer trainlineMapTotalHours,
//                                        Integer zoomLevel,
//                                        Double viewWidth,
//                                        Double viewHeight,
//                                        Double figureCenterViewCoordinateDeltaX,
//                                        Double figureCenterViewCoordinateDeltaY,
//                                        Double lockedViewCoordinateDeltaX,
//                                        Double lockedViewCoordinateDeltaY,
//                                        Integer previousZoomLevel) {}
}
