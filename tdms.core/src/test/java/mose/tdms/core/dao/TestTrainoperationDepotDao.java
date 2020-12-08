/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.tdms.CommonConfiguration;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.TrainoperationDepot;
import mose.tdms.core.vo.TrainoperationDepotSearchVo;
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
        CommonConfiguration.class, TestTrainoperationDepotDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.tdms.core.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauDao.class,
                TrainoperationDepotDao.class
        })
})
public class TestTrainoperationDepotDao {
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private TrainoperationDepotDao trainoperationDepotDao;
    private TrainoperationDepot trainoperationDepot;

    @Before
    public void before() {
        trainoperationDepot = new TrainoperationDepot();
        trainoperationDepot.setBureauId(2);
        trainoperationDepot.setName("n");
        trainoperationDepot.setNameInitialPinyin("NIP");
        trainoperationDepot.setNamePinyin("NP");
        trainoperationDepot.setLatitude(52.11111);
        trainoperationDepot.setLongitude(132.11111);
        trainoperationDepot.setCreatorId(2);
        trainoperationDepot.setCreatorRealName("crn");
        trainoperationDepot.setLastEditorId(3);
        trainoperationDepot.setLastEditorRealName("lrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        Bureau bureau = new Bureau();
        bureau.setName("b");
        int bureauId = bureauDao.addOne(bureau);
        trainoperationDepot.setBureauId(bureauId);
        trainoperationDepot.setBureauName(bureau.getName());

        int id = trainoperationDepotDao.addOne(trainoperationDepot);
        trainoperationDepot.setId(id);
        TrainoperationDepotSearchVo trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setIdEqual(id);
        TrainoperationDepot getOne = trainoperationDepotDao.getOne(trainoperationDepotSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainoperationDepot.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(trainoperationDepot.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(trainoperationDepot.getName(), getOne.getName());
        Assert.assertEquals(trainoperationDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainoperationDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainoperationDepot.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(trainoperationDepot.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(trainoperationDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(trainoperationDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(trainoperationDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(trainoperationDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setIdEqual(id);
        getOne = trainoperationDepotDao.getOne(trainoperationDepotSearchVo);
        Assert.assertTrue(trainoperationDepot.equals(getOne));

        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setTextLike(trainoperationDepot.getName());
        List<TrainoperationDepot> getMany = trainoperationDepotDao.getMany(trainoperationDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (TrainoperationDepot one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainoperationDepot.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(trainoperationDepot.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(trainoperationDepot.getName(), getOne.getName());
        Assert.assertEquals(trainoperationDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainoperationDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainoperationDepot.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(trainoperationDepot.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(trainoperationDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(trainoperationDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(trainoperationDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(trainoperationDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setBureauIdEqual(trainoperationDepot.getBureauId());
        getMany = trainoperationDepotDao.getMany(trainoperationDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        Assert.assertTrue(trainoperationDepotDao.getCount(trainoperationDepotSearchVo) > 0);
        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, trainoperationDepotDao.getCount(trainoperationDepotSearchVo));

        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setTextLike(trainoperationDepot.getNamePinyin());
        getMany = trainoperationDepotDao.getMany(trainoperationDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        bureau = new Bureau();
        bureau.setId(trainoperationDepot.getBureauId());
        getMany = trainoperationDepotDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        trainoperationDepotDao.updateOne(this.trainoperationDepot);
        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setIdEqual(id);
        getOne = trainoperationDepotDao.getOne(trainoperationDepotSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainoperationDepot.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(trainoperationDepot.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(trainoperationDepot.getName(), getOne.getName());
        Assert.assertEquals(trainoperationDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainoperationDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainoperationDepot.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(trainoperationDepot.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(this.trainoperationDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(this.trainoperationDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());
        Assert.assertEquals(this.trainoperationDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(this.trainoperationDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainoperationDepotDao.deleteOne(this.trainoperationDepot);
        trainoperationDepotSearchVo = new TrainoperationDepotSearchVo();
        trainoperationDepotSearchVo.setIdEqual(id);
        getOne = trainoperationDepotDao.getOne(trainoperationDepotSearchVo);
        Assert.assertNull(getOne);
    }
}
