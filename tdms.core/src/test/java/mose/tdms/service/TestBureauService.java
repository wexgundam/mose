/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import mose.core.json.JsonUtil;
import mose.tdms.CommonConfiguration;
import mose.tdms.dao.BureauDao;
import mose.tdms.dao.TestBureauDao;
import mose.tdms.modal.Bureau;
import mose.tdms.modal.Feature;
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
        CommonConfiguration.class, TestBureauService.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauService.class,
                BureauDao.class})
})
public class TestBureauService {
    @Autowired
    private BureauService bureauService;
    @Autowired
    private BureauDao bureauDao;

    @Test
    @Transactional
    @Rollback
    public void getAll() {
        Bureau bureau = new Bureau();
        bureauDao.addOne(bureau);
        bureau = new Bureau();
        bureauDao.addOne(bureau);

        List<Bureau> bureaus = bureauService.getAll();
        Assert.assertNotNull(bureaus);
        Assert.assertFalse(bureaus.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void getCount() {
        Bureau bureau = new Bureau();
        bureauDao.addOne(bureau);
        bureau = new Bureau();
        bureauDao.addOne(bureau);

        int count = bureauService.getCount();
        Assert.assertTrue(count > 0);
    }
}
