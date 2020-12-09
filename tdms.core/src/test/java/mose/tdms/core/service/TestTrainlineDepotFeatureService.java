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
import mose.tdms.core.modal.TrainlineDepot;

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
        CommonConfiguration.class, TestTrainlineDepotFeatureService.class
})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
})
@PropertySource({"classpath:application-supermap.properties"})
@ComponentScan(basePackages = "mose", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                TrainlineDepotFeatureService.class,
                RestfulTemplate.class
        })
})
public class TestTrainlineDepotFeatureService {
    @Autowired
    private TrainlineDepotFeatureService trainlineDepotFeatureService;
    private TrainlineDepot trainlineDepot;

    @Before
    public void getBeforeTest() {
        trainlineDepot = new TrainlineDepot();
        trainlineDepot.setId(-100);
        trainlineDepot.setName("测试");
        trainlineDepot.setBureauId(11);
        trainlineDepot.setLatitude(31.28);
        trainlineDepot.setLongitude(121.60);
    }

    @Test
    public void testCreateFeature() {
        Feature feature = trainlineDepotFeatureService.toFeature(trainlineDepot);
        String json = JsonUtil.toString(feature);
        System.out.println(json);
    }

    @Test
    public void testCRUD() {
        Assert.assertTrue(trainlineDepotFeatureService.addOne(trainlineDepot));

        int smId = trainlineDepotFeatureService.getSmId(trainlineDepot.getId());
        Assert.assertNotEquals(-1, smId);

        trainlineDepot.setName("测试 update");
        Assert.assertTrue(trainlineDepotFeatureService.updateOne(trainlineDepot));

        Assert.assertTrue(trainlineDepotFeatureService.deleteOne(trainlineDepot));

        trainlineDepot.setId(-100);
        Assert.assertFalse(trainlineDepotFeatureService.deleteOne(trainlineDepot));
    }

}
