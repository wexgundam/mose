/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.core.string.StringUtil;
import mose.tdms.CommonConfiguration;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.TrainlineDepot;
import mose.tdms.core.vo.TrainlineDepotSearchVo;
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
@ComponentScan(basePackages = "mose.tdms.core.dao", useDefaultFilters = false, includeFilters = {
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
        trainlineDepot.setBureauId(2);
        trainlineDepot.setName("n");
        trainlineDepot.setNameInitialPinyin("NIP");
        trainlineDepot.setNamePinyin("NP");
        trainlineDepot.setDdtId(1);
        trainlineDepot.setLatitude(52.11111);
        trainlineDepot.setLongitude(132.11111);
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
        trainlineDepot.setBureauId(bureauId);
        trainlineDepot.setBureauName(bureau.getName());

        int id = trainlineDepotDao.addOne(trainlineDepot);
        trainlineDepot.setId(id);
        TrainlineDepotSearchVo trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(id);
        TrainlineDepot getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainlineDepot.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(trainlineDepot.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(trainlineDepot.getName(), getOne.getName());
        Assert.assertEquals(trainlineDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainlineDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainlineDepot.getDdtId(), getOne.getDdtId());
        Assert.assertEquals(trainlineDepot.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(trainlineDepot.getLongitude(), getOne.getLongitude(), 0);
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
        trainlineDepotSearchVo.setTextLike(trainlineDepot.getName());
        List<TrainlineDepot> getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setDdtIdEqual(trainlineDepot.getDdtId());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (TrainlineDepot one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainlineDepot.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(trainlineDepot.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(trainlineDepot.getName(), getOne.getName());
        Assert.assertEquals(trainlineDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(trainlineDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainlineDepot.getDdtId(), getOne.getDdtId());
        Assert.assertEquals(trainlineDepot.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(trainlineDepot.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(trainlineDepot.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(trainlineDepot.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(trainlineDepot.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(trainlineDepot.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setBureauIdEqual(trainlineDepot.getBureauId());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setDdtIdEqual(trainlineDepot.getDdtId());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        Assert.assertTrue(trainlineDepotDao.getCount(trainlineDepotSearchVo) > 0);
        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, trainlineDepotDao.getCount(trainlineDepotSearchVo));

        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setTextLike(trainlineDepot.getNamePinyin());
        getMany = trainlineDepotDao.getMany(trainlineDepotSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        bureau = new Bureau();
        bureau.setId(trainlineDepot.getBureauId());
        getMany = trainlineDepotDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepotDao.updateOne(this.trainlineDepot);
        trainlineDepotSearchVo = new TrainlineDepotSearchVo();
        trainlineDepotSearchVo.setIdEqual(id);
        getOne = trainlineDepotDao.getOne(trainlineDepotSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(trainlineDepot.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(trainlineDepot.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(trainlineDepot.getName(), getOne.getName());
        Assert.assertEquals(this.trainlineDepot.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(this.trainlineDepot.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(trainlineDepot.getDdtId(), getOne.getDdtId());
        Assert.assertEquals(trainlineDepot.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(trainlineDepot.getLongitude(), getOne.getLongitude(), 0);
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

    @Test
    public void testUpdatePinyin() {
        for (TrainlineDepot depot : trainlineDepotDao.getAll()) {
            depot.setNamePinyin(StringUtil.toPinyin(depot.getName()));
            depot.setNameInitialPinyin(StringUtil.toInitialPinyin(depot.getName()));
            trainlineDepotDao.updateOne(depot);
        }
    }
}
