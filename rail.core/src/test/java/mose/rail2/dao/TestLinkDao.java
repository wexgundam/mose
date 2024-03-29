/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.dao;

import mose.CommonConfiguration;
import mose.rail2.modal.Link;
import mose.rail2.vo.LinkSearchVo;
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
        CommonConfiguration.class, TestLinkDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail2.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {LinkDao.class})
})
public class TestLinkDao {
    @Autowired
    private LinkDao linkDao;
    private Link link;

    @Before
    public void before() {
        link = new Link();
        link.setBasePointString("b");
        link.setAnchorPointsString("a");
        link.setCreatorId(2);
        link.setCreatorRealName("crn");
        link.setLastEditorId(3);
        link.setLastEditorRealName("lrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        int id = linkDao.addOne(link);
        link.setId(id);
        LinkSearchVo linkSearchVo = new LinkSearchVo();
        linkSearchVo.setIdEqual(id);
        Link getOne = linkDao.getOne(linkSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(link.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(link.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(link.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(link.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(link.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(link.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(link.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        linkSearchVo = new LinkSearchVo();
        linkSearchVo.setIdEqual(id);
        getOne = linkDao.getOne(linkSearchVo);
        Assert.assertTrue(link.equals(getOne));

        List<Link> getMany = linkDao.getAll();
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (Link one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(link.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(link.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(link.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(link.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(link.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(link.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(link.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        linkDao.updateOne(link);
        linkSearchVo = new LinkSearchVo();
        linkSearchVo.setIdEqual(id);
        getOne = linkDao.getOne(linkSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(link.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(link.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(link.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(link.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(link.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(link.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(link.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        linkDao.deleteOne(link);
        linkSearchVo = new LinkSearchVo();
        linkSearchVo.setIdEqual(id);
        getOne = linkDao.getOne(linkSearchVo);
        Assert.assertNull(getOne);
    }
}
