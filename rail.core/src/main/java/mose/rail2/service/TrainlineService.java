/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.service;

import mose.rail2.modal.Trainline;
import mose.rail2.modal.TrainlineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * what:    列车运行线服务. <br/>
 * # 获取给定运行线中给定路网元素对应的时刻. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/5
 */
@Service
public class TrainlineService {
    /**
     * 定义日志输出位置
     */
    private Logger logger = LoggerFactory.getLogger("serviceLog");
    /**
     * 径路服务
     */
    @Autowired
    private RouteService routeService;


    /**
     * what:    获取给定运行线中给定路网元素对应的时刻. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/12/26
     */
    public TrainlineItem getTrainlineItem(Trainline trainline, int networkElementType, int networkElementId) {
        for (TrainlineItem trainlineItem : trainline.getItems()) {
            if (trainlineItem.getNetworkElementType() == networkElementType
                    && trainlineItem.getNetworkElementId() == networkElementId) {
                return trainlineItem;
            }
        }
        return null;
    }
}
