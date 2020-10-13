/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.core.dao;

import mose.CommonConfiguration;
import mose.network.modal.Grid;
import mose.rail.core.modal.Bureau;
import mose.rail.core.modal.TrainlineDepot;
import mose.rail.core.vo.TrainlineDepotSearchVo;
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
        CommonConfiguration.class, TestTrainlineDepotDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail.core.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauDao.class,
                TrainlineDepotDao.class
        })
})
public class TestTrainlineDepotDao {
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private TrainlineDepotDao trainlineDepotDao;
    private TrainlineDepot trainlineDepot;

    @Before
    public void before() {
        trainlineDepot = new TrainlineDepot();
        trainlineDepot.setGridGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        trainlineDepot.setBasePointString("b");
        trainlineDepot.setAnchorPointsString("a");
        trainlineDepot.setJurisdictionBureauId(2);
        trainlineDepot.setName("n");
        trainlineDepot.setNameInitialPinyin("nip");
        trainlineDepot.setNamePinyin("np");
        trainlineDepot.setTelegraphCode("tc");
        trainlineDepot.setCreatorId(2);
        trainlineDepot.setCreatorRealName("crn");
        trainlineDepot.setLastEditorId(3);
        trainlineDepot.setLastEditorRealName("lrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        Bureau bureau = new Bureau();
        bureau.setName("b");
        int bureauId = bureauDao.addOne(bureau);
        trainlineDepot.setJurisdictionBureauId(bureauId);
        trainlineDepot.setJurisdictionBureauName(bureau.getName());

        int id = trainlineDepotDao.addOne(trainlineDepot);
        trainlineDepot.setId(id);
        TrainlineDepotSearchVo trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(id);
        TrainlineDepot getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainlineDepot.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(trainlineDepot.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(trainlineDepot.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(trainlineDepot.getName(), getOne.getName());
        Assert.assertEquals(trainlineDepot.getJurisdictionBureauId(), getOne.getJurisdictionBureauId());
        Assert.assertEquals(trainlineDepot.getJurisdictionBureauName(), getOne.getJurisdictionBureauName());
        Assert.assertEquals(trainlineDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainlineDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainlineDepot.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(trainlineDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(trainlineDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(trainlineDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(trainlineDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(id);
        getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertTrue(trainlineDepot.equals(getOne));

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setNameEqual(trainlineDepot.getName());
        getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertTrue(trainlineDepot.equals(getOne));

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setNameLike(trainlineDepot.getName());
        List<TrainlineDepot> getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (TrainlineDepot one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainlineDepot.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(trainlineDepot.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(trainlineDepot.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(trainlineDepot.getName(), getOne.getName());
        Assert.assertEquals(trainlineDepot.getJurisdictionBureauId(), getOne.getJurisdictionBureauId());
        Assert.assertEquals(trainlineDepot.getJurisdictionBureauName(), getOne.getJurisdictionBureauName());
        Assert.assertEquals(trainlineDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainlineDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainlineDepot.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(trainlineDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(trainlineDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(trainlineDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(trainlineDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setJurisdictionBureauIdEqual(trainlineDepot.getJurisdictionBureauId());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setTelegraphCodeLike(trainlineDepot.getTelegraphCode());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setTelegraphCodeEqual(trainlineDepot.getTelegraphCode());
        getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertTrue(trainlineDepot.equals(getOne));


        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setPinyinLike(trainlineDepot.getNamePinyin());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        Assert.assertEquals(1, trainlineDepotDao.getCount(trainlineDepotSearchVo));
        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, trainlineDepotDao.getCount(trainlineDepotSearchVo));

        bureau = new Bureau();
        bureau.setId(trainlineDepot.getJurisdictionBureauId());
        getMany = trainlineDepotDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotDao.updateOne(this.trainlineDepot);
        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(id);
        getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(this.trainlineDepot.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(this.trainlineDepot.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(this.trainlineDepot.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(this.trainlineDepot.getName(), getOne.getName());
        Assert.assertEquals(this.trainlineDepot.getJurisdictionBureauId(), getOne.getJurisdictionBureauId());
        Assert.assertEquals(this.trainlineDepot.getJurisdictionBureauName(), getOne.getJurisdictionBureauName());
        Assert.assertEquals(this.trainlineDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(this.trainlineDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(this.trainlineDepot.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(this.trainlineDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(this.trainlineDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());
        Assert.assertEquals(this.trainlineDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(this.trainlineDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainlineDepotDao.deleteOne(this.trainlineDepot);
        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(id);
        getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertNull(getOne);
    }
}
