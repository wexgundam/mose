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
import mose.rail.core.dao.LinkDao;
import mose.rail.core.dao.StationDao;
import mose.rail.core.dao.TrainlineDepotDao;
import mose.rail.core.modal.AdjoinTrainlineDepots;
import mose.rail.core.modal.Bureau;
import mose.rail.core.modal.Link;
import mose.rail.core.modal.Station;
import mose.rail.core.modal.TrainlineDepot;
import mose.rail.core.modal.Yard;
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
        CommonConfiguration.class, TestTrainlineDepotService.class
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
                BureauDao.class,
                TrainlineDepotService.class,
                TrainlineDepotDao.class,
                StationService.class,
                StationDao.class,
                LinkService.class,
                LinkDao.class
        })
})
public class TestTrainlineDepotService {
    @Autowired
    private BureauService bureauService;
    @Autowired
    private TrainlineDepotService trainlineDepotService;
    @Autowired
    private StationService stationService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private LinkDao linkDao;

    @Test
    @Transactional
    @Rollback
    public void testAdjoin() {
        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        trainlineDepotService.addOne(trainlineDepotA);
        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        trainlineDepotService.addOne(trainlineDepotB);
        TrainlineDepot trainlineDepotC = new TrainlineDepot();
        trainlineDepotC.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        trainlineDepotService.addOne(trainlineDepotC);

        boolean result = trainlineDepotService.adjoin(trainlineDepotA, trainlineDepotB);
        Assert.assertTrue(result);
        result = trainlineDepotService.adjoin(trainlineDepotA, trainlineDepotC);
        Assert.assertTrue(result);
        result = trainlineDepotService.adjoin(trainlineDepotB, trainlineDepotC);
        Assert.assertFalse(result);
    }

    @Test
    @Transactional
    @Rollback
    public void testJurisdiction() {
        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        trainlineDepotService.addOne(trainlineDepotA);
        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        trainlineDepotService.addOne(trainlineDepotB);

        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("10@10;10@20;20@20;20@10");

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("70@70;70@80;80@80;80@70");
        stationB.setBureauParting(true);

        Station stationC = new Station();
        stationC.setAnchorPointsString("110@110");

        boolean result = trainlineDepotService.jurisdiction(trainlineDepotA, stationA);
        Assert.assertTrue(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotA, stationB);
        Assert.assertTrue(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotA, stationC);
        Assert.assertFalse(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, stationA);
        Assert.assertFalse(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, stationB);
        Assert.assertFalse(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, stationC);
        Assert.assertTrue(result);

        Link linkA = new Link();
        linkA.setAnchorPointsString("0@0;100@100");
        Link linkB = new Link();
        linkB.setAnchorPointsString("20@20;30@30");
        Link linkC = new Link();
        linkC.setAnchorPointsString("200@200;300@300;400@400;23@53");

        result = trainlineDepotService.jurisdiction(trainlineDepotA, linkA);
        Assert.assertTrue(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotA, linkB);
        Assert.assertTrue(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotA, linkC);
        Assert.assertFalse(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, linkA);
        Assert.assertFalse(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, linkB);
        Assert.assertFalse(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, linkC);
        Assert.assertFalse(result);

        Yard yard = new Yard();
        yard.setAnchorPointsString("5@5");

        result = trainlineDepotService.jurisdiction(trainlineDepotA, yard);
        Assert.assertTrue(result);
        result = trainlineDepotService.jurisdiction(trainlineDepotB, yard);
        Assert.assertFalse(result);
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
    public void testGetAdjoinTrainlineDepotses() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        bureauService.addOne(bureauB);

        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("80@80;80@100;100@100;100@80");
        trainlineDepotService.setJurisdiction(trainlineDepotA);
        trainlineDepotService.addOne(trainlineDepotA);
        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("100@100;100@150;150@150;150@100");
        trainlineDepotService.setJurisdiction(trainlineDepotB);
        trainlineDepotService.addOne(trainlineDepotB);
        TrainlineDepot trainlineDepotC = new TrainlineDepot();
        trainlineDepotC.setAnchorPointsString("40@40;40@80;80@80;80@40");
        trainlineDepotService.setJurisdiction(trainlineDepotC);
        trainlineDepotService.addOne(trainlineDepotC);
        TrainlineDepot trainlineDepotD = new TrainlineDepot();
        trainlineDepotD.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        trainlineDepotService.setJurisdiction(trainlineDepotD);
        trainlineDepotService.addOne(trainlineDepotD);

        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("90@90;90@95;95@95;95@90");
        stationService.setJurisdiction(stationA);
        stationService.addOne(stationA);

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("130@130;130@140;140@140;140@130");
        stationService.setJurisdiction(stationB);
        stationService.addOne(stationB);

        Station stationC = new Station();
        stationC.setAnchorPointsString("50@50");
        stationService.setJurisdiction(stationC);
        stationService.addOne(stationC);

        Link linkA = new Link();
        linkA.setAnchorPointsString("95@95;130@130");
        linkService.addOne(linkA);
        Link linkB = new Link();
        linkB.setAnchorPointsString("20@20;30@30");
        linkService.addOne(linkB);
        Link linkC = new Link();
        linkC.setAnchorPointsString("50@50;90@90");
        linkService.addOne(linkC);

        try {
            List<AdjoinTrainlineDepots> adjoinTrainlineDepots = trainlineDepotService.getAdjoinTrainlineDepotses(bureauA, bureauB);
            Assert.assertNotNull(adjoinTrainlineDepots);
            Assert.assertEquals(1, adjoinTrainlineDepots.size());
            Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(0).getTrainlineDepotA());
            Assert.assertEquals(stationA, adjoinTrainlineDepots.get(0).getStationA());
            Assert.assertEquals(linkA, adjoinTrainlineDepots.get(0).getLink());
            Assert.assertEquals(stationB, adjoinTrainlineDepots.get(0).getStationB());
            Assert.assertEquals(trainlineDepotB, adjoinTrainlineDepots.get(0).getTrainlineDepotB());

            adjoinTrainlineDepots = trainlineDepotService.getAdjoinTrainlineDepotses(trainlineDepotA);
            Assert.assertNotNull(adjoinTrainlineDepots);
            Assert.assertEquals(2, adjoinTrainlineDepots.size());
            if (linkA.equals(adjoinTrainlineDepots.get(0).getLink())) {
                Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(0).getTrainlineDepotA());
                Assert.assertEquals(stationA, adjoinTrainlineDepots.get(0).getStationA());
                Assert.assertEquals(linkA, adjoinTrainlineDepots.get(0).getLink());
                Assert.assertEquals(stationB, adjoinTrainlineDepots.get(0).getStationB());
                Assert.assertEquals(trainlineDepotB, adjoinTrainlineDepots.get(0).getTrainlineDepotB());

                Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(1).getTrainlineDepotA());
                Assert.assertEquals(stationA, adjoinTrainlineDepots.get(1).getStationA());
                Assert.assertEquals(linkC, adjoinTrainlineDepots.get(1).getLink());
                Assert.assertEquals(stationC, adjoinTrainlineDepots.get(1).getStationB());
                Assert.assertEquals(trainlineDepotC, adjoinTrainlineDepots.get(1).getTrainlineDepotB());
            } else if (linkA.equals(adjoinTrainlineDepots.get(1).getLink())) {
                Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(0).getTrainlineDepotA());
                Assert.assertEquals(stationA, adjoinTrainlineDepots.get(0).getStationA());
                Assert.assertEquals(linkC, adjoinTrainlineDepots.get(0).getLink());
                Assert.assertEquals(stationC, adjoinTrainlineDepots.get(0).getStationB());
                Assert.assertEquals(trainlineDepotC, adjoinTrainlineDepots.get(0).getTrainlineDepotB());

                Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(1).getTrainlineDepotA());
                Assert.assertEquals(stationA, adjoinTrainlineDepots.get(1).getStationA());
                Assert.assertEquals(linkA, adjoinTrainlineDepots.get(1).getLink());
                Assert.assertEquals(stationB, adjoinTrainlineDepots.get(1).getStationB());
                Assert.assertEquals(trainlineDepotB, adjoinTrainlineDepots.get(1).getTrainlineDepotB());
            }

            adjoinTrainlineDepots = trainlineDepotService.getAdjoinTrainlineDepotses(trainlineDepotB);
            Assert.assertNotNull(adjoinTrainlineDepots);
            Assert.assertEquals(1, adjoinTrainlineDepots.size());
            Assert.assertEquals(stationB, adjoinTrainlineDepots.get(0).getStationA());
            Assert.assertEquals(trainlineDepotB, adjoinTrainlineDepots.get(0).getTrainlineDepotA());
            Assert.assertEquals(linkA, adjoinTrainlineDepots.get(0).getLink());
            Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(0).getTrainlineDepotB());
            Assert.assertEquals(stationA, adjoinTrainlineDepots.get(0).getStationB());

            adjoinTrainlineDepots = trainlineDepotService.getAdjoinTrainlineDepotses(trainlineDepotC);
            Assert.assertNotNull(adjoinTrainlineDepots);
            Assert.assertEquals(1, adjoinTrainlineDepots.size());
            Assert.assertEquals(stationA, adjoinTrainlineDepots.get(0).getStationB());
            Assert.assertEquals(trainlineDepotA, adjoinTrainlineDepots.get(0).getTrainlineDepotB());
            Assert.assertEquals(linkC, adjoinTrainlineDepots.get(0).getLink());
            Assert.assertEquals(trainlineDepotC, adjoinTrainlineDepots.get(0).getTrainlineDepotA());
            Assert.assertEquals(stationC, adjoinTrainlineDepots.get(0).getStationA());
        } finally {
            linkService.deleteOne(linkA);
            linkService.deleteOne(linkB);
            linkService.deleteOne(linkC);
            stationService.deleteOne(stationA);
            stationService.deleteOne(stationB);
            stationService.deleteOne(stationC);
            trainlineDepotService.deleteOne(trainlineDepotA);
            trainlineDepotService.deleteOne(trainlineDepotB);
            trainlineDepotService.deleteOne(trainlineDepotC);
            trainlineDepotService.deleteOne(trainlineDepotD);
            bureauService.deleteOne(bureauA);
            bureauService.deleteOne(bureauB);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void testGetJurisdiction() {
        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("80@80;80@100;100@100;100@80");
        trainlineDepotService.setJurisdiction(trainlineDepotA);
        trainlineDepotService.addOne(trainlineDepotA);
        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("100@100;100@150;150@150;150@100");
        trainlineDepotService.setJurisdiction(trainlineDepotB);
        trainlineDepotService.addOne(trainlineDepotB);
        TrainlineDepot trainlineDepotC = new TrainlineDepot();
        trainlineDepotC.setAnchorPointsString("40@40;40@80;80@80;80@40");
        trainlineDepotService.setJurisdiction(trainlineDepotC);
        trainlineDepotService.addOne(trainlineDepotC);
        TrainlineDepot trainlineDepotD = new TrainlineDepot();
        trainlineDepotD.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        trainlineDepotService.setJurisdiction(trainlineDepotD);
        trainlineDepotService.addOne(trainlineDepotD);

        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("90@90;90@95;95@95;95@90");
        stationService.setJurisdiction(stationA);
        stationService.addOne(stationA);

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("130@130;130@140;140@140;140@130");
        stationService.setJurisdiction(stationB);
        stationService.addOne(stationB);

        Station stationC = new Station();
        stationC.setAnchorPointsString("-500@-500");
        stationService.setJurisdiction(stationC);
        stationService.addOne(stationC);

        TrainlineDepot jurisdiction = trainlineDepotService.getJurisdiction(stationA);
        Assert.assertNotNull(jurisdiction);
        Assert.assertEquals(trainlineDepotA, jurisdiction);
        jurisdiction = trainlineDepotService.getJurisdiction(stationC);
        Assert.assertNull(jurisdiction);
    }

    @Test
    @Transactional
    @Rollback
    public void testSetJurisdiction() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        bureauService.addOne(bureauB);

        TrainlineDepot trainlineDepotA = new TrainlineDepot();
        trainlineDepotA.setAnchorPointsString("80@80;80@100;100@100;100@80");
        trainlineDepotService.setJurisdiction(trainlineDepotA);
        Assert.assertEquals(bureauA.getId(), trainlineDepotA.getJurisdictionBureauId());
        trainlineDepotService.addOne(trainlineDepotA);
        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("100@100;100@150;150@150;150@100");
        trainlineDepotService.setJurisdiction(trainlineDepotB);
        Assert.assertEquals(bureauB.getId(), trainlineDepotB.getJurisdictionBureauId());
        TrainlineDepot trainlineDepotC = new TrainlineDepot();
        trainlineDepotC.setAnchorPointsString("40@40;40@80;80@80;80@40");
        trainlineDepotService.setJurisdiction(trainlineDepotC);
        Assert.assertEquals(bureauA.getId(), trainlineDepotC.getJurisdictionBureauId());
        TrainlineDepot trainlineDepotD = new TrainlineDepot();
        trainlineDepotD.setAnchorPointsString("-100@-100;-100@0;0@0;0@-100");
        trainlineDepotService.setJurisdiction(trainlineDepotD);
        Assert.assertNotEquals(bureauA.getId(), trainlineDepotD.getJurisdictionBureauId());
        Assert.assertNotEquals(bureauB.getId(), trainlineDepotD.getJurisdictionBureauId());
    }
}
