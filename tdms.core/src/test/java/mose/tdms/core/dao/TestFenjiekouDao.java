/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import mose.tdms.CommonConfiguration;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.Fenjiekou;
import mose.tdms.core.modal.TrainlineDepot;
import mose.tdms.core.vo.FenjiekouSearchVo;
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
        CommonConfiguration.class, TestFenjiekouDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.tdms.core.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauDao.class,
                TrainlineDepotDao.class,
                FenjiekouDao.class
        })
})
public class TestFenjiekouDao {
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private TrainlineDepotDao trainlineDepotDao;
    @Autowired
    private FenjiekouDao fenjiekouDao;
    private Fenjiekou fenjiekou;

    @Before
    public void before() {
        fenjiekou = new Fenjiekou();
        fenjiekou.setBureauId(2);
        fenjiekou.setTrainlineDepotId(3);
        fenjiekou.setTargetBureauId(2);
        fenjiekou.setTargetTrainlineDepotId(3);
        fenjiekou.setName("n");
        fenjiekou.setNameInitialPinyin("NIP");
        fenjiekou.setNamePinyin("NP");
        fenjiekou.setTelegraphCode("tc");
        fenjiekou.setLatitude(52.11111);
        fenjiekou.setLongitude(132.11111);
        fenjiekou.setCode(1);
        fenjiekou.setCreatorId(2);
        fenjiekou.setCreatorRealName("crn");
        fenjiekou.setLastEditorId(3);
        fenjiekou.setLastEditorRealName("lrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        Bureau bureau = new Bureau();
        bureau.setName("b");
        int bureauId = bureauDao.addOne(bureau);
        fenjiekou.setBureauId(bureauId);
        fenjiekou.setBureauName(bureau.getName());

        TrainlineDepot trainlineDepot = new TrainlineDepot();
        trainlineDepot.setName("t");
        int trainlineDepotId = trainlineDepotDao.addOne(trainlineDepot);
        fenjiekou.setTrainlineDepotId(trainlineDepotId);
        fenjiekou.setTrainlineDepotName(trainlineDepot.getName());

        Bureau targetBureau = new Bureau();
        targetBureau.setName("tb");
        int targetBureauId = bureauDao.addOne(targetBureau);
        fenjiekou.setTargetBureauId(targetBureauId);
        fenjiekou.setTargetBureauName(targetBureau.getName());

        TrainlineDepot targetTrainlineDepot = new TrainlineDepot();
        targetTrainlineDepot.setName("tt");
        int targetTrainlineDepotId = trainlineDepotDao.addOne(targetTrainlineDepot);
        fenjiekou.setTargetTrainlineDepotId(targetTrainlineDepotId);
        fenjiekou.setTargetTrainlineDepotName(targetTrainlineDepot.getName());

        int id = fenjiekouDao.addOne(fenjiekou);
        fenjiekou.setId(id);
        FenjiekouSearchVo fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setIdEqual(id);
        Fenjiekou getOne = fenjiekouDao.getOne(fenjiekouSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(fenjiekou.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(fenjiekou.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(fenjiekou.getTrainlineDepotId(), getOne.getTrainlineDepotId());
        Assert.assertEquals(fenjiekou.getTrainlineDepotName(), getOne.getTrainlineDepotName());
        Assert.assertEquals(fenjiekou.getTargetBureauId(), getOne.getTargetBureauId());
        Assert.assertEquals(fenjiekou.getTargetBureauName(), getOne.getTargetBureauName());
        Assert.assertEquals(fenjiekou.getTargetTrainlineDepotId(), getOne.getTargetTrainlineDepotId());
        Assert.assertEquals(fenjiekou.getTargetTrainlineDepotName(), getOne.getTargetTrainlineDepotName());
        Assert.assertEquals(fenjiekou.getName(), getOne.getName());
        Assert.assertEquals(fenjiekou.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(fenjiekou.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(fenjiekou.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(fenjiekou.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(fenjiekou.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(fenjiekou.getCode(), getOne.getCode());
        Assert.assertEquals(fenjiekou.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(fenjiekou.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(fenjiekou.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(fenjiekou.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setIdEqual(id);
        getOne = fenjiekouDao.getOne(fenjiekouSearchVo);
        Assert.assertTrue(fenjiekou.equals(getOne));

        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setTextLike(fenjiekou.getName());
        List<Fenjiekou> getMany = fenjiekouDao.getMany(fenjiekouSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setTelegraphCodeEqual(fenjiekou.getTelegraphCode());
        getOne = fenjiekouDao.getOne(fenjiekouSearchVo);
        Assert.assertTrue(fenjiekou.equals(getOne));

        getOne = null;
        for (Fenjiekou one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(fenjiekou.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(fenjiekou.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(fenjiekou.getTrainlineDepotId(), getOne.getTrainlineDepotId());
        Assert.assertEquals(fenjiekou.getTrainlineDepotName(), getOne.getTrainlineDepotName());
        Assert.assertEquals(fenjiekou.getTargetBureauId(), getOne.getTargetBureauId());
        Assert.assertEquals(fenjiekou.getTargetBureauName(), getOne.getTargetBureauName());
        Assert.assertEquals(fenjiekou.getTargetTrainlineDepotId(), getOne.getTargetTrainlineDepotId());
        Assert.assertEquals(fenjiekou.getTargetTrainlineDepotName(), getOne.getTargetTrainlineDepotName());
        Assert.assertEquals(fenjiekou.getName(), getOne.getName());
        Assert.assertEquals(fenjiekou.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(fenjiekou.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(fenjiekou.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(fenjiekou.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(fenjiekou.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(fenjiekou.getCode(), getOne.getCode());
        Assert.assertEquals(fenjiekou.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(fenjiekou.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(fenjiekou.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(fenjiekou.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setBureauIdEqual(fenjiekou.getBureauId());
        getMany = fenjiekouDao.getMany(fenjiekouSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setTelegraphCodeEqual(fenjiekou.getTelegraphCode());
        getOne = fenjiekouDao.getOne(fenjiekouSearchVo);
        Assert.assertTrue(fenjiekou.equals(getOne));

        fenjiekouSearchVo = new FenjiekouSearchVo();
        Assert.assertTrue(fenjiekouDao.getCount(fenjiekouSearchVo) > 0);
        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, fenjiekouDao.getCount(fenjiekouSearchVo));

        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setTextLike(fenjiekou.getNamePinyin());
        getMany = fenjiekouDao.getMany(fenjiekouSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        bureau = new Bureau();
        bureau.setId(fenjiekou.getBureauId());
        getMany = fenjiekouDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        fenjiekouDao.updateOne(this.fenjiekou);
        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setIdEqual(id);
        getOne = fenjiekouDao.getOne(fenjiekouSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(fenjiekou.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(fenjiekou.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(fenjiekou.getTrainlineDepotId(), getOne.getTrainlineDepotId());
        Assert.assertEquals(fenjiekou.getTrainlineDepotName(), getOne.getTrainlineDepotName());
        Assert.assertEquals(fenjiekou.getTargetBureauId(), getOne.getTargetBureauId());
        Assert.assertEquals(fenjiekou.getTargetBureauName(), getOne.getTargetBureauName());
        Assert.assertEquals(fenjiekou.getTargetTrainlineDepotId(), getOne.getTargetTrainlineDepotId());
        Assert.assertEquals(fenjiekou.getTargetTrainlineDepotName(), getOne.getTargetTrainlineDepotName());
        Assert.assertEquals(fenjiekou.getName(), getOne.getName());
        Assert.assertEquals(fenjiekou.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(fenjiekou.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(fenjiekou.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(fenjiekou.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(fenjiekou.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(fenjiekou.getCode(), getOne.getCode());
        Assert.assertEquals(fenjiekou.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(fenjiekou.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());
        Assert.assertEquals(fenjiekou.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(fenjiekou.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        fenjiekouDao.deleteOne(fenjiekou);
        fenjiekouSearchVo = new FenjiekouSearchVo();
        fenjiekouSearchVo.setIdEqual(id);
        getOne = fenjiekouDao.getOne(fenjiekouSearchVo);
        Assert.assertNull(getOne);
    }
}
