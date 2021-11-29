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
import mose.rail.dao.RailwayLineDao;
import mose.rail.modal.RailwayLine;
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
        CommonConfiguration.class, TestRailwayLineService.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BureauDao.class, IntervalLineDao.class, NodeDao.class, RailwayLineDao.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BureauService.class, IntervalLineService.class, NodeService.class, RailwayLineService.class})
})
public class TestRailwayLineService {
    @Autowired
    private RailwayLineDao railwayLineDao;
    @Autowired
    private RailwayLineService railwayLineService;
    private RailwayLine railwayLine;

    @Before
    public void before() {
        railwayLine = new RailwayLine();
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        int id = railwayLineDao.addOne(railwayLine);
        RailwayLine one = railwayLineService.getOne(id);
        Assert.assertNotNull(one);
    }
}
