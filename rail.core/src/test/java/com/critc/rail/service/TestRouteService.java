/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package com.critc.rail.service;

import com.critc.rail.modal.Link;
import com.critc.rail.modal.Site;
import com.critc.rail.modal.Station;
import com.critc.rail.modal.Yard;
import com.critc.util.date.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@ContextConfiguration("/spring/applicationContext-database.xml")
public class TestRouteService {
    @Autowired
    private RouteService routeService;

    @Test
    public void testIntersects1() {
        Link routeA = new Link();
        routeA.setAnchorPointsString("0@0;50@50;100@50;100@100;200@200");
        Link routeB = new Link();
        routeB.setAnchorPointsString("120@120;150@150");
        Link routeC = new Link();
        routeC.setAnchorPointsString("50@50;100@50");
        Link routeD = new Link();
        routeD.setAnchorPointsString("0@0;50@50;50@100;100@100;200@200");

        Assert.assertTrue(routeService.intersects(routeA, routeB));
        Assert.assertTrue(routeService.intersects(routeB, routeA));
        Assert.assertTrue(routeService.intersects(routeA, routeC));
        Assert.assertFalse(routeService.intersects(routeB, routeC));
        Assert.assertTrue(routeService.intersects(routeA, routeD));
    }


    @Test
    public void testIntersects2() {
        String routeAString = "0@0;50@50;100@50;100@100;200@200";
        String routeBString = "120@120;150@150";
        String routeCString = "50@50;100@50";
        String routeDString = "0@0;50@50;50@100;100@100;200@200";

        Assert.assertTrue(routeService.intersects(routeAString, routeBString));
        Assert.assertTrue(routeService.intersects(routeBString, routeAString));
        Assert.assertTrue(routeService.intersects(routeAString, routeCString));
        Assert.assertFalse(routeService.intersects(routeBString, routeCString));
        Assert.assertTrue(routeService.intersects(routeAString, routeDString));
    }

    @Test
    public void testGetIntersections1() {
        Link routeA = new Link();
        routeA.setAnchorPointsString("0@0;50@50;100@50;100@100;200@200");
        Link routeB = new Link();
        routeB.setAnchorPointsString("120@120;150@150");
        Link routeC = new Link();
        routeC.setAnchorPointsString("50@50;100@50");
        Link routeD = new Link();
        routeD.setAnchorPointsString("0@0;50@50;50@100;100@100;200@200");

        List<Link> intersectionABs = routeService.getIntersection(routeA, routeB);
        Assert.assertNotNull(intersectionABs);
        Assert.assertFalse(intersectionABs.isEmpty());
        Assert.assertEquals(1, intersectionABs.size());
        Assert.assertEquals("120.0@120.0;150.0@150.0", intersectionABs.get(0).getAnchorPointsString());

        List<Link> intersectionBAs = routeService.getIntersection(routeB, routeA);
        Assert.assertNotNull(intersectionBAs);
        Assert.assertFalse(intersectionBAs.isEmpty());
        Assert.assertEquals(1, intersectionBAs.size());
        Assert.assertEquals("120.0@120.0;150.0@150.0", intersectionBAs.get(0).getAnchorPointsString());
        Assert.assertEquals(intersectionABs.get(0), intersectionBAs.get(0));

        List<Link> intersectionACs = routeService.getIntersection(routeA, routeC);
        Assert.assertNotNull(intersectionACs);
        Assert.assertFalse(intersectionACs.isEmpty());
        Assert.assertEquals(1, intersectionACs.size());
        Assert.assertEquals("50.0@50.0;100.0@50.0", intersectionACs.get(0).getAnchorPointsString());

        List<Link> intersectionADs = routeService.getIntersection(routeA, routeD);
        Assert.assertNotNull(intersectionADs);
        Assert.assertFalse(intersectionADs.isEmpty());
        Assert.assertEquals(2, intersectionADs.size());
        Assert.assertEquals("0.0@0.0;50.0@50.0", intersectionADs.get(0).getAnchorPointsString());
        Assert.assertEquals("100.0@100.0;200.0@200.0", intersectionADs.get(1).getAnchorPointsString());
    }

    @Test
    public void testGetIntersections2() {
        String routeAString = "0@0;50@50;100@50;100@100;200@200";
        String routeBString = "120@120;150@150";
        String routeCString = "50@50;100@50";
        String routeDString = "0@0;50@50;50@100;100@100;200@200";

        List<Link> intersectionABs = routeService.getIntersection(routeAString, routeBString);
        Assert.assertNotNull(intersectionABs);
        Assert.assertFalse(intersectionABs.isEmpty());
        Assert.assertEquals(1, intersectionABs.size());
        Assert.assertEquals("120.0@120.0;150.0@150.0", intersectionABs.get(0).getAnchorPointsString());

        List<Link> intersectionBAs = routeService.getIntersection(routeBString, routeAString);
        Assert.assertNotNull(intersectionBAs);
        Assert.assertFalse(intersectionBAs.isEmpty());
        Assert.assertEquals(1, intersectionBAs.size());
        Assert.assertEquals("120.0@120.0;150.0@150.0", intersectionBAs.get(0).getAnchorPointsString());
        Assert.assertEquals(intersectionABs.get(0), intersectionBAs.get(0));

        List<Link> intersectionACs = routeService.getIntersection(routeAString, routeCString);
        Assert.assertNotNull(intersectionACs);
        Assert.assertFalse(intersectionACs.isEmpty());
        Assert.assertEquals(1, intersectionACs.size());
        Assert.assertEquals("50.0@50.0;100.0@50.0", intersectionACs.get(0).getAnchorPointsString());

        List<Link> intersectionADs = routeService.getIntersection(routeAString, routeDString);
        Assert.assertNotNull(intersectionADs);
        Assert.assertFalse(intersectionADs.isEmpty());
        Assert.assertEquals(2, intersectionADs.size());
        Assert.assertEquals("0.0@0.0;50.0@50.0", intersectionADs.get(0).getAnchorPointsString());
        Assert.assertEquals("100.0@100.0;200.0@200.0", intersectionADs.get(1).getAnchorPointsString());
    }

    @Test
    public void testRouteSite() {
        Link route = new Link();
        route.setAnchorPointsString("0@0;50@50;100@50;100@100;200@200");
        Site siteA = new Site();
        siteA.setAnchorPointsString("120@120");
        Site siteB = new Site();
        siteB.setAnchorPointsString("100@120");
        Site siteC = new Site();
        siteC.setAnchorPointsString("100@100");

        boolean routed = routeService.route(route, siteA);
        Assert.assertTrue(routed);
        routed = routeService.route(route, siteB);
        Assert.assertFalse(routed);
        routed = routeService.route(route, siteC);
        Assert.assertTrue(routed);
    }


    @Test
    public void testRouteLink() {
        Link route = new Link();
        route.setAnchorPointsString("0@0;50@50;100@50;100@100;200@200");
        Link linkA = new Link();
        linkA.setAnchorPointsString("120@120;150@150");
        Link linkB = new Link();
        linkB.setAnchorPointsString("50@50;100@50");
        Link linkC = new Link();
        linkC.setAnchorPointsString("0@0;50@50;50@100;100@100;200@200");

        boolean routed = routeService.route(route, linkA);
        Assert.assertTrue(routed);
        routed = routeService.route(route, linkB);
        Assert.assertTrue(routed);
        routed = routeService.route(route, linkC);
        Assert.assertFalse(routed);
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
