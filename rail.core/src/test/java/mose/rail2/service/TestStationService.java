/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.service;

import mose.CommonConfiguration;
import mose.core.time.DateUtil;
import mose.network.modal.Grid;
import mose.network.service.GridService;
import mose.rail2.dao.BureauDao;
import mose.rail2.dao.LinkDao;
import mose.rail2.dao.StationDao;
import mose.rail2.dao.TrainlineDepotDao;
import mose.rail2.modal.AdjoinStations;
import mose.rail2.modal.Bureau;
import mose.rail2.modal.BureauPartingStation;
import mose.rail2.modal.Link;
import mose.rail2.modal.Station;
import mose.rail2.modal.TrainlineDepot;
import mose.rail2.modal.Yard;
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
 * @author 靳磊 created on 2019/9/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestStationService.class
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
public class TestStationService {
    @Autowired
    private BureauService bureauService;
    @Autowired
    private TrainlineDepotService trainlineDepotService;
    @Autowired
    private StationService stationService;
    @Autowired
    private LinkService linkService;

    @Test
    @Transactional
    @Rollback
    public void testStationsAdjoin() {
        //点形车站
        Station stationA = new Station();
        stationA.setAnchorPointsString("0@0");
        Station stationB = new Station();
        stationB.setAnchorPointsString("100@100");
        Station stationC = new Station();
        stationC.setAnchorPointsString("30@40");
        //多边形车站
        Station stationD = new Station();
        stationD.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationD.setAnchorPointsString("0@0;0@20;10@20");

        Link link = new Link();
        link.setAnchorPointsString("0@0;100@100");
        linkService.addOne(link);
        link = new Link();
        link.setAnchorPointsString("20@20;30@30");
        linkService.addOne(link);
        link = new Link();
        link.setAnchorPointsString("200@200;300@300;400@400;23@53");
        linkService.addOne(link);
        for (int index = 0; index < 1000; index++) {
            link = new Link();
            link.setAnchorPointsString("200@200;300@300;400@400;23@53");
            linkService.addOne(link);
        }


        //车站A、B邻接
        System.out.println(DateUtil.getSystemTime());
        boolean result = stationService.adjoin(stationA, stationB);
        Assert.assertTrue(result);
        System.out.println(DateUtil.getSystemTime());

        //车站A、D邻接
        System.out.println(DateUtil.getSystemTime());
        result = stationService.adjoin(stationA, stationD);
        Assert.assertTrue(result);
        System.out.println(DateUtil.getSystemTime());

        //车站A、C不邻接
        System.out.println(DateUtil.getSystemTime());
        result = stationService.adjoin(stationA, stationC);
        Assert.assertFalse(result);
        System.out.println(DateUtil.getSystemTime());
    }

    @Test
    public void testStationJurisdictionYard() {
        //多边形车站
        Station station = new Station();
        station.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        station.setAnchorPointsString("0@0;0@20;20@20;20@0");

        // station管辖车场A
        Yard yardA = new Yard();
        yardA.setAnchorPointsString("5@5");
        Assert.assertTrue(stationService.jurisdiction(station, yardA));

        // station不管辖车场B
        Yard yardB = new Yard();
        yardB.setAnchorPointsString("5@50");
        Assert.assertFalse(stationService.jurisdiction(station, yardB));
    }

    @Test
    public void testGetMany() {
        //委托StationDao
    }

    @Test
    public void testGetOne() {
        //委托StationDao
    }

    @Test
    public void testGetAll() {
        //委托StationDao
    }

    @Test
    public void testGetBureauPartingStations() {
        Bureau bureauA = new Bureau();
        bureauA.setAnchorPointsString("0@0;0@100;100@100;100@0");
        bureauService.addOne(bureauA);
        Bureau bureauB = new Bureau();
        bureauB.setAnchorPointsString("100@100;100@200;200@200;200@100");
        bureauService.addOne(bureauB);

        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("10@10;10@20;20@20;20@10");
        stationService.setJurisdiction(stationA);
        stationService.addOne(stationA);

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("70@70;70@80;80@80;80@70");
        stationB.setBureauParting(true);
        stationService.setJurisdiction(stationB);
        stationService.addOne(stationB);

        Station stationC = new Station();
        stationC.setAnchorPointsString("110@110");
        stationService.setJurisdiction(stationC);
        stationService.addOne(stationC);

        Station stationD = new Station();
        stationD.setAnchorPointsString("180@180");
        stationService.setJurisdiction(stationD);
        stationService.addOne(stationD);

        Link linkA = new Link();
        linkA.setAnchorPointsString("80@80;90@93;102@105;110@110");
        linkService.addOne(linkA);
        Link linkB = new Link();
        linkB.setAnchorPointsString("20@20;70@70");
        linkService.addOne(linkB);

        try {
            List<BureauPartingStation> bureauPartingStations = stationService.getBureauPartingStations();
            Assert.assertNotNull(bureauPartingStations);
            Assert.assertEquals(1, bureauPartingStations.size());
            Assert.assertNotNull(bureauPartingStations.get(0).getBureauPartingStation());
            Assert.assertTrue(bureauPartingStations.get(0).getBureauPartingStation().equals(stationB));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinStation());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinStation().equals(stationC));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinLink());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinLink().equals(linkA));
            Assert.assertNotNull(bureauPartingStations.get(0).getJurisdictionBureau());
            Assert.assertTrue(bureauPartingStations.get(0).getJurisdictionBureau().equals(bureauA));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinBureau());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinBureau().equals(bureauB));

            bureauPartingStations = stationService.getBureauPartingStations(bureauA);
            Assert.assertNotNull(bureauPartingStations);
            Assert.assertEquals(1, bureauPartingStations.size());
            Assert.assertNotNull(bureauPartingStations.get(0).getBureauPartingStation());
            Assert.assertTrue(bureauPartingStations.get(0).getBureauPartingStation().equals(stationB));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinStation());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinStation().equals(stationC));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinLink());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinLink().equals(linkA));
            Assert.assertNotNull(bureauPartingStations.get(0).getJurisdictionBureau());
            Assert.assertTrue(bureauPartingStations.get(0).getJurisdictionBureau().equals(bureauA));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinBureau());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinBureau().equals(bureauB));

            bureauPartingStations = stationService.getBureauPartingStations(bureauA, bureauB);
            Assert.assertNotNull(bureauPartingStations);
            Assert.assertEquals(1, bureauPartingStations.size());
            Assert.assertNotNull(bureauPartingStations.get(0).getBureauPartingStation());
            Assert.assertTrue(bureauPartingStations.get(0).getBureauPartingStation().equals(stationB));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinStation());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinStation().equals(stationC));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinLink());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinLink().equals(linkA));
            Assert.assertNotNull(bureauPartingStations.get(0).getJurisdictionBureau());
            Assert.assertTrue(bureauPartingStations.get(0).getJurisdictionBureau().equals(bureauA));
            Assert.assertNotNull(bureauPartingStations.get(0).getAdjoinBureau());
            Assert.assertTrue(bureauPartingStations.get(0).getAdjoinBureau().equals(bureauB));
        } finally {
            bureauService.deleteOne(bureauA);
            bureauService.deleteOne(bureauB);

            stationService.deleteOne(stationA);
            stationService.deleteOne(stationB);
            stationService.deleteOne(stationC);
            stationService.deleteOne(stationD);

            linkService.deleteOne(linkA);
            linkService.deleteOne(linkB);
        }
    }


    @Test
    @Transactional
    @Rollback
    public void testGetAdjoinStationses() {
        Station stationA = new Station();
        stationA.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationA.setAnchorPointsString("10@10;10@20;20@20;20@10");
        stationService.addOne(stationA);

        Station stationB = new Station();
        stationB.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationB.setAnchorPointsString("70@70;70@80;80@80;80@70");
        stationB.setBureauParting(true);
        stationService.addOne(stationB);

        Station stationC = new Station();
        stationC.setAnchorPointsString("110@110");
        stationService.addOne(stationC);

        Station stationD = new Station();
        stationD.setAnchorPointsString("180@180");
        stationService.addOne(stationD);

        Link linkA = new Link();
        linkA.setAnchorPointsString("80@80;90@93;102@105;110@110");
        linkService.addOne(linkA);
        Link linkB = new Link();
        linkB.setAnchorPointsString("32@54;123@231");
        linkService.addOne(linkB);

        List<AdjoinStations> adjoinStationses = stationService.getAdjoinStationses(stationA);
        Assert.assertNotNull(adjoinStationses);
        Assert.assertEquals(0, adjoinStationses.size());

        adjoinStationses = stationService.getAdjoinStationses(stationB);
        Assert.assertNotNull(adjoinStationses);
        Assert.assertEquals(1, adjoinStationses.size());
        Assert.assertNotNull(adjoinStationses.get(0).getStationA());
        Assert.assertTrue(adjoinStationses.get(0).getStationA().equals(stationB));
        Assert.assertNotNull(adjoinStationses.get(0).getLink());
        Assert.assertTrue(adjoinStationses.get(0).getLink().equals(linkA));
        Assert.assertNotNull(adjoinStationses.get(0).getStationB());
        Assert.assertTrue(adjoinStationses.get(0).getStationB().equals(stationC));

        adjoinStationses = stationService.getAdjoinStationses(stationC);
        Assert.assertNotNull(adjoinStationses);
        Assert.assertEquals(1, adjoinStationses.size());
        Assert.assertNotNull(adjoinStationses.get(0).getStationA());
        Assert.assertTrue(adjoinStationses.get(0).getStationA().equals(stationC));
        Assert.assertNotNull(adjoinStationses.get(0).getLink());
        Assert.assertTrue(adjoinStationses.get(0).getLink().equals(linkA));
        Assert.assertNotNull(adjoinStationses.get(0).getStationB());
        Assert.assertTrue(adjoinStationses.get(0).getStationB().equals(stationB));

        adjoinStationses = stationService.getAdjoinStationses(stationD);
        Assert.assertNotNull(adjoinStationses);
        Assert.assertEquals(0, adjoinStationses.size());

        AdjoinStations adjoinStations = stationService.getAdjoinStations(linkA);
        Assert.assertNotNull(adjoinStations);
        Assert.assertNotNull(adjoinStations.getStationA());
        if (stationB.equals(adjoinStations.getStationA())) {
            Assert.assertNotNull(adjoinStations.getStationB());
            Assert.assertTrue(adjoinStations.getStationB().equals(stationC));
        }
        Assert.assertNotNull(adjoinStations.getLink());
        Assert.assertTrue(adjoinStations.getLink().equals(linkA));
        if (stationC.equals(adjoinStations.getStationA())) {
            Assert.assertNotNull(adjoinStations.getStationB());
            Assert.assertTrue(adjoinStations.getStationB().equals(stationB));
        }

        adjoinStations = stationService.getAdjoinStations(linkB);
        Assert.assertNull(adjoinStations);
    }


    @Test
    @Transactional
    @Rollback
    public void testGetJurisdiction() {
        Station stationA = new Station();
        stationA.setAnchorPointsString("10@10");
        stationService.setJurisdiction(stationA);
        stationService.addOne(stationA);

        Station stationB = new Station();
        stationB.setAnchorPointsString("110@110");
        stationService.setJurisdiction(stationB);
        stationService.addOne(stationA);

        Station stationC = new Station();
        stationC.setAnchorPointsString("300@300");
        stationService.setJurisdiction(stationC);
        stationService.addOne(stationA);

        Station stationD = new Station();
        stationD.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        stationD.setAnchorPointsString("130@130;130@140;140@140;140@130");
        stationService.setJurisdiction(stationD);
        stationService.addOne(stationD);

        Yard yard = new Yard();
        yard.setAnchorPointsString("135@135");
        Station jurisdiction = stationService.getJurisdiction(yard);
        Assert.assertNotNull(jurisdiction);
        Assert.assertEquals(stationD, jurisdiction);

        yard = new Yard();
        yard.setAnchorPointsString("1350@1350");
        jurisdiction = stationService.getJurisdiction(yard);
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
        trainlineDepotA.setAnchorPointsString("0@0;0@30;30@30;30@0");
        trainlineDepotService.addOne(trainlineDepotA);
        TrainlineDepot trainlineDepotB = new TrainlineDepot();
        trainlineDepotB.setAnchorPointsString("105@105;105@150;150@150;150@105");
        trainlineDepotService.addOne(trainlineDepotB);

        Station stationA = new Station();
        stationA.setAnchorPointsString("10@10");
        stationService.setJurisdiction(stationA);
        Assert.assertEquals(bureauA.getId(), stationA.getJurisdictionBureauId());
        Assert.assertEquals(trainlineDepotA.getId(), stationA.getJurisdictionTdId());

        Station stationB = new Station();
        stationB.setAnchorPointsString("110@110");
        stationService.setJurisdiction(stationB);
        Assert.assertEquals(bureauB.getId(), stationB.getJurisdictionBureauId());
        Assert.assertEquals(trainlineDepotB.getId(), stationB.getJurisdictionTdId());

        Station stationC = new Station();
        stationC.setAnchorPointsString("300@300");
        stationService.setJurisdiction(stationC);
        Assert.assertNotEquals(bureauA.getId(), stationC.getJurisdictionBureauId());
        Assert.assertNotEquals(bureauB.getId(), stationC.getJurisdictionBureauId());
        Assert.assertNotEquals(trainlineDepotA.getId(), stationC.getJurisdictionTdId());
        Assert.assertNotEquals(trainlineDepotB.getId(), stationC.getJurisdictionTdId());
    }

    @Test
    public void testAddOne() {
        //委托Dao
    }

    @Test
    public void testUpdateOne() {
        //委托Dao
    }

    @Test
    public void testDeleteOne() {
        //委托Dao
    }
}
