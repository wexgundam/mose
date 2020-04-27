/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail.core.service;

import mose.core.cache.EhCacheUtil;
import mose.rail.core.vo.StationLocationVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/spring/applicationContext-common.xml",
        "/spring/applicationContext-package.xml"
})
public class TestLocationService {
    @Autowired
    private LocationService locationService;

    @Before
    public void before() throws IOException {
    }

    @Test
    public void testInitData() {
        String stationName = "北京";
        double[] latLng = EhCacheUtil.get("railLocationCache", stationName);
        Assert.assertNotNull(latLng);
        Assert.assertEquals(39.902802, latLng[0], 0.0001);
        Assert.assertEquals(116.427048, latLng[1], 0.0001);
    }

    @Test
    public void testGetLatLng() {
        String stationName = "北京";
        StationLocationVo stationLocationVo = locationService.getStationLocationVo(stationName);
        Assert.assertNotNull(stationLocationVo);
        Assert.assertEquals(39.902802, stationLocationVo.getLatLng()[0], 0.0001);
        Assert.assertEquals(116.427048, stationLocationVo.getLatLng()[1], 0.0001);
    }
}
