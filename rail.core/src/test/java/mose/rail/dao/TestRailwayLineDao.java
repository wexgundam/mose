/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.dao;

import mose.CommonConfiguration;
import mose.rail.modal.RailwayLine;
import mose.rail.vo.RailwayLineSearchVo;
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
 * @author 靳磊 created on 2019/9/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestRailwayLineDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {RailwayLineDao.class})
})
public class TestRailwayLineDao {
    @Autowired
    private RailwayLineDao railwayLineDao;
    private RailwayLine railwayLine;

    @Before
    public void before() {
        railwayLine = new RailwayLine();
        railwayLine.setName("n");
        railwayLine.setCode(99);
        railwayLine.setAlias("a");
        railwayLine.setCreatorId(2);
        railwayLine.setCreatorRealName("crn");
        railwayLine.setLastEditorId(3);
        railwayLine.setLastEditorRealName("lrn");
    }


    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        int id = railwayLineDao.addOne(railwayLine);
        railwayLine.setId(id);
        RailwayLineSearchVo railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setIdEqual(id);
        RailwayLine getOne = railwayLineDao.getOne(railwayLineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(railwayLine.getName(), getOne.getName());
        Assert.assertEquals(railwayLine.getCode(), getOne.getCode());
        Assert.assertEquals(railwayLine.getAlias(), getOne.getAlias());
        Assert.assertEquals(railwayLine.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(railwayLine.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(railwayLine.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(railwayLine.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setIdEqual(id);
        getOne = railwayLineDao.getOne(railwayLineSearchVo);
        Assert.assertTrue(railwayLine.equals(getOne));

        railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setNameEqual(railwayLine.getName());
        getOne = railwayLineDao.getOne(railwayLineSearchVo);
        Assert.assertTrue(railwayLine.equals(getOne));

        railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setNameLike(railwayLine.getName());
        List<RailwayLine> getMany = railwayLineDao.getMany(railwayLineSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (RailwayLine one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(railwayLine.getName(), getOne.getName());
        Assert.assertEquals(railwayLine.getCode(), getOne.getCode());
        Assert.assertEquals(railwayLine.getAlias(), getOne.getAlias());
        Assert.assertEquals(railwayLine.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(railwayLine.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(railwayLine.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(railwayLine.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        railwayLineDao.updateOne(railwayLine);
        railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setIdEqual(id);
        getOne = railwayLineDao.getOne(railwayLineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(railwayLine.getName(), getOne.getName());
        Assert.assertEquals(railwayLine.getCode(), getOne.getCode());
        Assert.assertEquals(railwayLine.getAlias(), getOne.getAlias());
        Assert.assertEquals(railwayLine.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(railwayLine.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(railwayLine.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(railwayLine.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        railwayLineDao.deleteOne(railwayLine);
        railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setIdEqual(id);
        getOne = railwayLineDao.getOne(railwayLineSearchVo);
        Assert.assertNull(getOne);
    }
}
