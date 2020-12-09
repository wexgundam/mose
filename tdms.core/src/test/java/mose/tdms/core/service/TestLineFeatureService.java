/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.service;

import mose.tdms.CommonConfiguration;
import mose.core.json.JsonUtil;
import mose.core.restful.RestfulTemplate;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import mose.tdms.core.modal.Feature;
import mose.tdms.core.modal.Line;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestLineFeatureService.class
})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
})
@PropertySource({"classpath:application-supermap.properties"})
@ComponentScan(basePackages = "mose", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                LineFeatureService.class,
                RestfulTemplate.class
        })
})
public class TestLineFeatureService {
    @Autowired
    private LineFeatureService lineFeatureService;
    private Line line;

    @Before
    public void getBeforeTest() {
        line = new Line();
        line.setId(-100);
        line.setStationALatitude(31.28);
        line.setStationALongitude(121.60);
        line.setStationBLatitude(31.28);
        line.setStationBLongitude(121.70);
    }

    @Test
    public void testCreateFeature() {
        Feature feature = lineFeatureService.toFeature(line);
        String json = JsonUtil.toString(feature);
        System.out.println(json);
    }

    @Test
    public void testCRUD() {
        Assert.assertTrue(lineFeatureService.addOne(line));

        int smId = lineFeatureService.getSmId(line.getId());
        Assert.assertNotEquals(-1, smId);

        line.setStationBLatitude(31.08);
        Assert.assertTrue(lineFeatureService.updateOne(line));

        Assert.assertTrue(lineFeatureService.deleteOne(line));

        line.setId(-100);
        Assert.assertFalse(lineFeatureService.deleteOne(line));
    }

}
