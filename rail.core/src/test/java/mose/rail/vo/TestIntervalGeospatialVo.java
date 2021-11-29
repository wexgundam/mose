/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.vo;

import mose.CommonConfiguration;
import mose.core.json.JsonUtil;
import mose.rail.dao.IntervalLineDao;
import mose.rail.dao.RailwayLineDao;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.RailwayLine;
import mose.rail.service.IntervalLineService;
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
        CommonConfiguration.class, TestIntervalGeospatialVo.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {})
})
public class TestIntervalGeospatialVo {
    private IntervalLine intervalLine;

    @Before
    public void before() {
        intervalLine = new IntervalLine();
        intervalLine.setRailwayLineId(1);
        intervalLine.setDirection("双");
        intervalLine.setNodeAId(2);
        intervalLine.setNodeBId(3);
        intervalLine.setAlias("alias");
        intervalLine.setGeoSpatial("geoSpatial");
    }


    @Test
    public void testToJson() {
    }
}
