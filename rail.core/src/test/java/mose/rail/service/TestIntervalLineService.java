/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.service;

import mose.CommonConfiguration;
import mose.rail.dao.BureauDao;
import mose.rail.dao.IntervalLineDao;
import mose.rail.dao.NodeDao;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestIntervalLineService.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BureauDao.class, IntervalLineDao.class, NodeDao.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BureauService.class, IntervalLineService.class, NodeService.class})
})
public class TestIntervalLineService {
    @Autowired
    private IntervalLineDao intervalLineDao;
    @Autowired
    private IntervalLineService intervalLineService;
    private IntervalLine intervalLine;

    @Before
    public void before() {
        intervalLine = new IntervalLine();
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        int id = intervalLineDao.addOne(intervalLine);
        IntervalLine one = intervalLineService.getOne(id);
        Assert.assertNotNull(one);
    }

    @Test
    public void testUpdateGeoSpatail() {
        Node nodeA = new Node();
        nodeA.setId(0);
        Node nodeB = new Node();
        nodeB.setId(1);

        //EPSG4326 经纬度坐标转为web墨卡托投影坐标
        double[] nodeAEpsg3857 = new double[]{0, 0};
        double[] nodeBEpsg3857 = new double[]{0, 100};

        IntervalLine intervalLine = new IntervalLine();
        intervalLine.setId(0);
        intervalLine.setNodeAId(nodeA.getId());
        intervalLine.setNodeBId(nodeB.getId());

        IntervalLineService.FirstIntervalLine firstIntervalLine = new IntervalLineService.FirstIntervalLine();
        firstIntervalLine.setNodeAId(nodeA.getId());
        firstIntervalLine.setNodeBId(nodeB.getId());
        firstIntervalLine.setNodeACoordinates(nodeAEpsg3857);
        firstIntervalLine.setNodeBCoordinates(nodeBEpsg3857);

        List<IntervalLine> intervalLines = new ArrayList<>();
        intervalLines.add(intervalLine);

        //////////////////////////////////////////////////////////
        intervalLine = new IntervalLine();
        intervalLine.setId(intervalLines.size());
        intervalLine.setNodeAId(nodeA.getId());
        intervalLine.setNodeBId(nodeB.getId());
        intervalLines.add(intervalLine);
        //////////////////////////////////////////////////////////

        intervalLineService.updateGeoSpatial(intervalLines, firstIntervalLine);
        System.out.println(intervalLine);
    }
}
