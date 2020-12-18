/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import mose.tdms.CommonConfiguration;
import mose.tdms.dao.TrainlineDepotDao;
import mose.tdms.modal.TrainlineDepot;
import mose.tdms.vo.TrainlineDepotSearchVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
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
 * @author 靳磊 created on 2019/11/13
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
@ComponentScan(basePackages = "mose.tdms", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
                Repository.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauService.class,
                TrainlineDepotService.class})
})
public class TestTrainlineDepotService {
    @Autowired
    private TrainlineDepotService trainlineDepotService;
    @Autowired
    private TrainlineDepotDao trainlineDepotDao;

    @Test
    @Transactional
    @Rollback
    public void testGetOne() {
        //委托dao
    }

    @Test
    @Transactional
    @Rollback
    public void testGetMany() {
        //委托dao
    }

    @Test
    @Transactional
    @Rollback
    public void testAddOne() {
        //委托dao
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateOne() {
        //委托dao
    }

    @Test
    @Transactional
    @Rollback
    public void testVerifyOne() {
        //委托dao
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteOne() {
        //委托dao
    }
}
