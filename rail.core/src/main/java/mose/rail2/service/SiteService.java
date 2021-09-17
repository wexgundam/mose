/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.service;

import mose.network.service.GridService;
import mose.rail2.modal.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * what: 位置服务<br/>
 * <p>
 * # 获取给定字符串形式的坐标对应的位置. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/12/12
 */
@Service
public class SiteService {
    /**
     * 定义日志输出位置
     */
    private Logger logger = LoggerFactory.getLogger("serviceLog");
    /**
     * 网格服务
     */
    @Autowired
    private GridService gridService;
    /**
     * 路网元素服务
     */
    @Autowired
    private NetworkElementService networkElementService;

    /**
     * what:    获取给定字符串形式的坐标对应的位置. <br/>
     * 通过GridService判断网格间关系. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Site getSite(String pointString) {
        //根据坐标的字符串形式构件位置对象
        Site site = new Site();
        site.setBasePointString(pointString);
        site.setAnchorPointsString(pointString);
        networkElementService.checkGrid(site);

        return site;
    }
}
