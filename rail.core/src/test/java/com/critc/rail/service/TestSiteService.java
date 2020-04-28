/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package com.critc.rail.service;

import mose.network.modal.Grid;
import com.critc.rail.modal.Link;
import com.critc.rail.modal.Site;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class TestSiteService {
    @Autowired
    private SiteService siteService;

    @Test
    public void testGetSite() {
        String pointString = "100@120";
        Site site = siteService.getSite(pointString);
        Assert.assertNotNull(site);
        Assert.assertEquals(pointString, site.getBasePointString());
        Assert.assertEquals(pointString, site.getAnchorPointsString());
        Assert.assertNotNull(site.getGrid());
        Assert.assertEquals(Grid.GEOMETRY_TYPE_POINT, site.getGrid().getGeometryType());
        Assert.assertNotNull(site.getGrid().getBasePointVector());
        Assert.assertEquals(100, site.getGrid().getBasePointVector().getPointX(), 0);
        Assert.assertEquals(120, site.getGrid().getBasePointVector().getPointY(), 0);
        Assert.assertNotNull(site.getGrid().getAnchorPointVectors());
        Assert.assertEquals(1, site.getGrid().getAnchorPointVectors().size());
        Assert.assertNotNull(site.getGrid().getAnchorPointVector(0));
        Assert.assertEquals(100, site.getGrid().getAnchorPointVector(0).getPointX(), 0);
        Assert.assertEquals(120, site.getGrid().getAnchorPointVector(0).getPointY(), 0);
    }

}
