/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.service;

import mose.CommonConfiguration;
import mose.network.service.GridService;
import mose.rail.dao.LinkDao;
import mose.rail.modal.INetworkElement;
import mose.rail.modal.Trainline;
import mose.rail.modal.TrainlineItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestTrainlineService.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                GridService.class,
                NetworkElementService.class,
                TrainlineService.class,
                RouteService.class,
                SiteService.class,
                LinkDao.class
        })
})
public class TestTrainlineService {
    @Autowired
    private TrainlineService trainlineService;

    @Test
    public void testGetTrainlineItem() {

        Trainline trainline = new Trainline();
        trainline.setId(1);
        trainline.setName("G1");
        trainline.setRouteAnchorPointsString("0@0;50@50;100@100;150@150");

        List<TrainlineItem> items = new ArrayList<>();
        TrainlineItem item = new TrainlineItem();
        item.setId(1);
        item.setOrder(0);
        item.setNetworkElementType(INetworkElement.TYPE_STATION);
        item.setNetworkElementId(0);
        item.setNetworkElementName("A-1");
        item.setNetworkElementBasePointString("0@0");
        items.add(item);

        item = new TrainlineItem();
        item.setId(2);
        item.setOrder(1);
        item.setNetworkElementType(INetworkElement.TYPE_STATION);
        item.setNetworkElementId(1);
        item.setNetworkElementName("B-1");
        item.setNetworkElementBasePointString("50@50");
        items.add(item);

        item = new TrainlineItem();
        item.setId(3);
        item.setOrder(2);
        item.setNetworkElementType(INetworkElement.TYPE_YARD);
        item.setNetworkElementId(2);
        item.setNetworkElementName("C-1");
        item.setNetworkElementBasePointString("100@100");
        items.add(item);

        item = new TrainlineItem();
        item.setId(4);
        item.setOrder(3);
        item.setNetworkElementType(INetworkElement.TYPE_YARD);
        item.setNetworkElementId(3);
        item.setNetworkElementName("D-1");
        item.setNetworkElementBasePointString("150@150");
        items.add(item);

        trainline.setItems(items);

        TrainlineItem trainlineItem = trainlineService.getTrainlineItem(trainline, INetworkElement.TYPE_STATION, 0);
        Assert.assertNotNull(trainlineItem);
        Assert.assertEquals(trainline.getItems().get(0), trainlineItem);
        trainlineItem = trainlineService.getTrainlineItem(trainline, INetworkElement.TYPE_YARD, 2);
        Assert.assertNotNull(trainlineItem);
        Assert.assertEquals(trainline.getItems().get(2), trainlineItem);
        trainlineItem = trainlineService.getTrainlineItem(trainline, INetworkElement.TYPE_YARD, 1);
        Assert.assertNull(trainlineItem);
    }

}
