/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.core.service;

import mose.CommonConfiguration;
import mose.network.modal.Grid;
import mose.network.service.GridService;
import mose.rail.core.dao.BureauDao;
import mose.rail.core.modal.Bureau;
import mose.rail.core.modal.Station;
import mose.rail.core.modal.TrainlineDepot;
import org.junit.Assert;
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
 * @author 靳磊 created on 2019/9/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestBureauService.class
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
                BureauService.class,
                BureauDao.class
        })
})
public class TestBureauService {
    @Autowired
    private BureauService bureauService;

    @Test
    @Transactional
    @Rollback
    public void testAdjoin() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        bureauService.addOne(bureauB);
        Bureau bureauC = new Bureau();
        bureauC.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        bureauService.addOne(bureauC);

        boolean result = bureauService.adjoin(bureauA, bureauB);
        Assert.assertTrue(result);
        result = bureauService.adjoin(bureauA, bureauC);
        Assert.assertTrue(result);
        result = bureauService.adjoin(bureauB, bureauC);
        Assert.assertFalse(result);
    }

    @Test
    @Transactional
    @Rollback
    public void testJurisdiction() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        bureauService.addOne(bureauB);

        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("10@10;10@20;20@20;20@10");

        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("70@70;70@80;80@80;80@70");

        boolean result = bureauService.jurisdiction(bureauA, trainlineDepotA);
        Assert.assertTrue(result);
        result = bureauService.jurisdiction(bureauA, trainlineDepotB);
        Assert.assertTrue(result);
        result = bureauService.jurisdiction(bureauB, trainlineDepotA);
        Assert.assertFalse(result);
        result = bureauService.jurisdiction(bureauB, trainlineDepotB);
        Assert.assertFalse(result);

        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("10@10;10@20;20@20;20@10");

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("70@70;70@80;80@80;80@70");
        stationB.setBureauParting(true);

        Station stationC = new Station();
        stationC.setAnchorPointsString("110@110");

        result = bureauService.jurisdiction(bureauA, stationA);
        Assert.assertTrue(result);
        result = bureauService.jurisdiction(bureauA, stationB);
        Assert.assertTrue(result);
        result = bureauService.jurisdiction(bureauA, stationC);
        Assert.assertFalse(result);
        result = bureauService.jurisdiction(bureauB, stationA);
        Assert.assertFalse(result);
        result = bureauService.jurisdiction(bureauB, stationB);
        Assert.assertFalse(result);
        result = bureauService.jurisdiction(bureauB, stationC);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetMany() {
        //委托Dao
    }

    @Test
    public void testGetOne() {
        //委托Dao
    }

    @Test
    public void testGetAll() {
        //委托Dao
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAdjoinBureauses() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("80@80;80@100;100@100;100@80");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@150;150@150;150@100");
        bureauService.addOne(bureauB);
        Bureau bureauC = new Bureau();
        bureauC.setAnchorPointsString("40@40;40@80;80@80;80@40");
        bureauService.addOne(bureauC);
        Bureau bureauD = new Bureau();
        bureauD.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        bureauService.addOne(bureauD);

        List<Bureau> adjoinBureaus = bureauService.getAdjoinBureaus(bureauA);
        Assert.assertEquals(2, adjoinBureaus.size());
        if (bureauB.equals(adjoinBureaus.get(0))) {
            Assert.assertEquals(bureauC, adjoinBureaus.get(1));
        } else {
            Assert.assertEquals(bureauC, adjoinBureaus.get(0));
        }

        adjoinBureaus = bureauService.getAdjoinBureaus(bureauB);
        Assert.assertEquals(1, adjoinBureaus.size());
        Assert.assertEquals(bureauA, adjoinBureaus.get(0));

        adjoinBureaus = bureauService.getAdjoinBureaus(bureauC);
        Assert.assertEquals(1, adjoinBureaus.size());
        Assert.assertEquals(bureauA, adjoinBureaus.get(0));

        adjoinBureaus = bureauService.getAdjoinBureaus(bureauD);
        Assert.assertTrue(adjoinBureaus.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetJurisdiction() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("80@80;80@100;100@100;100@80");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@150;150@150;150@100");
        bureauService.addOne(bureauB);
        Bureau bureauC = new Bureau();
        bureauC.setAnchorPointsString("40@40;40@80;80@80;80@40");
        bureauService.addOne(bureauC);
        Bureau bureauD = new Bureau();
        bureauD.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        bureauService.addOne(bureauD);

        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("90@90;90@95;95@95;95@90");
        Bureau jurisdiction = bureauService.getJurisdiction(trainlineDepotA);
        Assert.assertNotNull(jurisdiction);
        Assert.assertEquals(bureauA, jurisdiction);

        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("130@130;130@140;140@140;140@130");
        jurisdiction = bureauService.getJurisdiction(trainlineDepotB);
        Assert.assertNotNull(jurisdiction);
        Assert.assertEquals(bureauB, jurisdiction);

        TrainlineDepot trainlineDepotC = new TrainlineDepot();
        trainlineDepotC.setAnchorPointsString("-500@-500;-400@-500;-400@-400;-500@-400");
        jurisdiction = bureauService.getJurisdiction(trainlineDepotC);
        Assert.assertNull(jurisdiction);

        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("90@90;90@95;95@95;95@90");
        jurisdiction = bureauService.getJurisdiction(stationA);
        Assert.assertNotNull(jurisdiction);
        Assert.assertEquals(bureauA, jurisdiction);

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("130@130;130@140;140@140;140@130");
        jurisdiction = bureauService.getJurisdiction(stationB);
        Assert.assertNotNull(jurisdiction);
        Assert.assertEquals(bureauB, jurisdiction);

        Station stationC = new Station();
        stationC.setAnchorPointsString("-500@-500");
        jurisdiction = bureauService.getJurisdiction(stationC);
        Assert.assertNull(jurisdiction);
    }

    @Test
    public void testGetCount() {
        //委托Dao
    }
}
