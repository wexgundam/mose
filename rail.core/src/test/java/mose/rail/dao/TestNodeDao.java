/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.dao;

import mose.CommonConfiguration;
import mose.rail.modal.Bureau;
import mose.rail.modal.Node;
import mose.rail.vo.NodeSearchVo;
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
        CommonConfiguration.class, TestNodeDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {NodeDao.class, BureauDao.class})
})
public class TestNodeDao {
    @Autowired
    private NodeDao nodeDao;
    @Autowired
    private BureauDao bureauDao;
    private Node node;

    @Before
    public void before() {
        node = new Node();
        node.setBureauId(2);
//        node.setXingDiaoId(1);
        node.setLabels("labes");
        node.setName("n");
        node.setInitialPinyin("nip");
        node.setPinyin("np");
        node.setTelegraphCode("tc");
        node.setEpsg4326("43");
        node.setRadius(300);
        node.setAlias("a");
        node.setGeoSpatial("geo");
        node.setCreatorId(2);
        node.setCreatorRealName("crn");
        node.setLastEditorId(3);
        node.setLastEditorRealName("lrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        Bureau bureau = new Bureau();
        bureau.setName("b");
        int bureauId = bureauDao.addOne(bureau);
        node.setBureauId(bureauId);
        node.setBureauName(bureau.getName());

        int id = nodeDao.addOne(node);
        node.setId(id);
        NodeSearchVo nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setIdEqual(id);
        Node getOne = nodeDao.getOne(nodeSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(node.getName(), getOne.getName());
        Assert.assertEquals(node.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(node.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(node.getLabels(), getOne.getLabels());
        Assert.assertEquals(node.getPinyin(), getOne.getPinyin());
        Assert.assertEquals(node.getInitialPinyin(), getOne.getInitialPinyin());
        Assert.assertEquals(node.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(node.getEpsg4326(), getOne.getEpsg4326());
        Assert.assertEquals(node.getRadius(), getOne.getRadius(),0);
        Assert.assertEquals(node.getAlias(), getOne.getAlias());
        Assert.assertEquals(node.getGeoSpatial(), getOne.getGeoSpatial());
        Assert.assertEquals(node.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(node.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(node.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(node.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setIdEqual(id);
        getOne = nodeDao.getOne(nodeSearchVo);
        Assert.assertTrue(node.equals(getOne));

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setNameEqual(node.getName());
        getOne = nodeDao.getOne(nodeSearchVo);
        Assert.assertTrue(node.equals(getOne));

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setNameLike(node.getName());
        List<Node> getMany = nodeDao.getMany(nodeSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (Node one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(node.getName(), getOne.getName());
        Assert.assertEquals(node.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(node.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(node.getLabels(), getOne.getLabels());
        Assert.assertEquals(node.getPinyin(), getOne.getPinyin());
        Assert.assertEquals(node.getInitialPinyin(), getOne.getInitialPinyin());
        Assert.assertEquals(node.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(node.getEpsg4326(), getOne.getEpsg4326());
        Assert.assertEquals(node.getRadius(), getOne.getRadius(),0);
        Assert.assertEquals(node.getAlias(), getOne.getAlias());
        Assert.assertEquals(node.getGeoSpatial(), getOne.getGeoSpatial());
        Assert.assertEquals(node.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(node.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(node.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(node.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setBureauIdEqual(node.getBureauId());
        getMany = nodeDao.getMany(nodeSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setTelegraphCodeLike(node.getTelegraphCode());
        getMany = nodeDao.getMany(nodeSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setTelegraphCodeEqual(node.getTelegraphCode());
        getOne = nodeDao.getOne(nodeSearchVo);
        Assert.assertTrue(node.equals(getOne));

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setPinyinLike(node.getPinyin());
        getMany = nodeDao.getMany(nodeSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, nodeDao.getCount(nodeSearchVo));

        bureau = new Bureau();
        bureau.setId(node.getBureauId());
        getMany = nodeDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        nodeDao.updateOne(node);
        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setIdEqual(id);
        getOne = nodeDao.getOne(nodeSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(node.getName(), getOne.getName());
        Assert.assertEquals(node.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(node.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(node.getLabels(), getOne.getLabels());
        Assert.assertEquals(node.getPinyin(), getOne.getPinyin());
        Assert.assertEquals(node.getInitialPinyin(), getOne.getInitialPinyin());
        Assert.assertEquals(node.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(node.getEpsg4326(), getOne.getEpsg4326());
        Assert.assertEquals(node.getRadius(), getOne.getRadius(),0);
        Assert.assertEquals(node.getAlias(), getOne.getAlias());
        Assert.assertEquals(node.getGeoSpatial(), getOne.getGeoSpatial());
        Assert.assertEquals(node.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(node.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(node.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(node.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        nodeDao.deleteOne(node);
        nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setIdEqual(id);
        getOne = nodeDao.getOne(nodeSearchVo);
        Assert.assertNull(getOne);
    }
}
