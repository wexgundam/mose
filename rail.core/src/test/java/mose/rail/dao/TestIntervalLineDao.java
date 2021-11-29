/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.dao;

import mose.CommonConfiguration;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.OldIntervalLine;
import mose.rail.modal.RailwayLine;
import mose.rail.vo.IntervalLineSearchVo;
import mose.rail.vo.RailwayLineSearchVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.math.BigDecimal;
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
        CommonConfiguration.class, TestIntervalLineDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {IntervalLineDao.class, RailwayLineDao.class})
})
public class TestIntervalLineDao {
    @Autowired
    private IntervalLineDao intervalLineDao;
    @Autowired
    private RailwayLineDao railwayLineDao;
    private IntervalLine intervalLine;
    /**
     * 定义日志输出位置
     */
    private static Logger logger = LoggerFactory.getLogger(TestIntervalLineDao.class);

    @Before
    public void before() {
        intervalLine = new IntervalLine();
        intervalLine.setRailwayLineId(111);
        intervalLine.setNodeAId(1);
        intervalLine.setNodeAMileage(2d);
        intervalLine.setNodeBId(11);
        intervalLine.setNodeBMileage(22d);
        intervalLine.setDirection("123");
        intervalLine.setAlias("a");
        intervalLine.setGeoSpatial("geo");
        intervalLine.setCreatorId(2);
        intervalLine.setCreatorRealName("crn");
        intervalLine.setLastEditorId(3);
        intervalLine.setLastEditorRealName("lrn");
    }


    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        int id = intervalLineDao.addOne(intervalLine);
        intervalLine.setId(id);
        IntervalLineSearchVo intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setIdEqual(id);
        IntervalLine getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(intervalLine.getRailwayLineId(), getOne.getRailwayLineId());
        Assert.assertEquals(intervalLine.getNodeAId(), getOne.getNodeAId());
        Assert.assertEquals(intervalLine.getNodeAMileage(), getOne.getNodeAMileage(), 0);
        Assert.assertEquals(intervalLine.getNodeBId(), getOne.getNodeBId());
        Assert.assertEquals(intervalLine.getNodeBMileage(), getOne.getNodeBMileage(), 0);
        Assert.assertEquals(intervalLine.getDirection(), getOne.getDirection());
        Assert.assertEquals(intervalLine.getAlias(), getOne.getAlias());
        Assert.assertEquals(intervalLine.getGeoSpatial(), getOne.getGeoSpatial());
        Assert.assertEquals(intervalLine.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(intervalLine.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(intervalLine.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(intervalLine.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setRailwayLineIdEqual(intervalLine.getRailwayLineId());
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertTrue(intervalLine.equals(getOne));

        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setNodeIdEqual(intervalLine.getNodeAId());
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertTrue(intervalLine.equals(getOne));
        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setNodeIdEqual(intervalLine.getNodeBId());
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertTrue(intervalLine.equals(getOne));

        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setNodesIdEqual(new Integer[]{intervalLine.getNodeAId(), intervalLine.getNodeBId()});
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertTrue(intervalLine.equals(getOne));
        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setNodesIdEqual(new Integer[]{intervalLine.getNodeBId(), intervalLine.getNodeAId()});
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertTrue(intervalLine.equals(getOne));

        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setDirectionEqual(intervalLine.getDirection());
        List<IntervalLine> getMany = intervalLineDao.getMany(intervalLineSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setNodeIdEqual(intervalLine.getNodeAId());
        getMany = intervalLineDao.getMany(intervalLineSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        intervalLineDao.updateOne(intervalLine);
        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setIdEqual(id);
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(intervalLine.getRailwayLineId(), getOne.getRailwayLineId());
        Assert.assertEquals(intervalLine.getNodeAId(), getOne.getNodeAId());
        Assert.assertEquals(intervalLine.getNodeAMileage(), getOne.getNodeAMileage(), 0);
        Assert.assertEquals(intervalLine.getNodeBId(), getOne.getNodeBId());
        Assert.assertEquals(intervalLine.getNodeBMileage(), getOne.getNodeBMileage(), 0);
        Assert.assertEquals(intervalLine.getDirection(), getOne.getDirection());
        Assert.assertEquals(intervalLine.getAlias(), getOne.getAlias());
        Assert.assertEquals(intervalLine.getGeoSpatial(), getOne.getGeoSpatial());
        Assert.assertEquals(intervalLine.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(intervalLine.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(intervalLine.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(intervalLine.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        intervalLineDao.deleteOne(intervalLine);
        intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setIdEqual(id);
        getOne = intervalLineDao.getOne(intervalLineSearchVo);
        Assert.assertNull(getOne);
    }
}
