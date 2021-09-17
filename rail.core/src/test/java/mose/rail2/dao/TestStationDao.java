/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail2.dao;

import mose.CommonConfiguration;
import mose.rail2.modal.Bureau;
import mose.rail2.modal.Station;
import mose.rail2.modal.TrainlineDepot;
import mose.rail2.vo.StationSearchVo;
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
        CommonConfiguration.class, TestStationDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail2.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {StationDao.class, BureauDao.class, TrainlineDepotDao.class})
})
public class TestStationDao {
    @Autowired
    private StationDao stationDao;
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private TrainlineDepotDao trainlineDepotDao;
    private Station station;

    @Before
    public void before() {
        station = new Station();
        station.setBasePointString("b");
        station.setAnchorPointsString("a");
        station.setJurisdictionBureauId(2);
        station.setJurisdictionTdId(1);
        station.setBureauParting(true);
        station.setName("n");
        station.setNameInitialPinyin("nip");
        station.setNamePinyin("np");
        station.setTelegraphCode("tc");
        station.setCreatorId(2);
        station.setCreatorRealName("crn");
        station.setLastEditorId(3);
        station.setLastEditorRealName("lrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        Bureau bureau = new Bureau();
        bureau.setName("b");
        int bureauId = bureauDao.addOne(bureau);
        station.setJurisdictionBureauId(bureauId);
        station.setJurisdictionBureauName(bureau.getName());

        TrainlineDepot trainlineDepot = new TrainlineDepot();
        trainlineDepot.setName("td");
        int trainlineDepotId = trainlineDepotDao.addOne(trainlineDepot);
        station.setJurisdictionTdId(trainlineDepotId);
        station.setJurisdictionTdName(trainlineDepot.getName());

        int id = stationDao.addOne(station);
        station.setId(id);
        StationSearchVo stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(id);
        Station getOne = stationDao.getOne(stationSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(station.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(station.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(station.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(station.getName(), getOne.getName());
        Assert.assertEquals(station.getJurisdictionBureauId(), getOne.getJurisdictionBureauId());
        Assert.assertEquals(station.getJurisdictionBureauName(), getOne.getJurisdictionBureauName());
        Assert.assertEquals(station.getJurisdictionTdId(), getOne.getJurisdictionTdId());
        Assert.assertEquals(station.getJurisdictionTdName(), getOne.getJurisdictionTdName());
        Assert.assertEquals(station.isBureauParting(), getOne.isBureauParting());
        Assert.assertEquals(station.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(station.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(station.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(station.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(station.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(station.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(station.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(id);
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertTrue(station.equals(getOne));

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setNameEqual(station.getName());
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertTrue(station.equals(getOne));

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setNameLike(station.getName());
        List<Station> getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        getOne = null;
        for (Station one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(station.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(station.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(station.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(station.getName(), getOne.getName());
        Assert.assertEquals(station.getJurisdictionBureauId(), getOne.getJurisdictionBureauId());
        Assert.assertEquals(station.getJurisdictionBureauName(), getOne.getJurisdictionBureauName());
        Assert.assertEquals(station.getJurisdictionTdId(), getOne.getJurisdictionTdId());
        Assert.assertEquals(station.getJurisdictionTdName(), getOne.getJurisdictionTdName());
        Assert.assertEquals(station.isBureauParting(), getOne.isBureauParting());
        Assert.assertEquals(station.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(station.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(station.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(station.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(station.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(station.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(station.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setJurisdictionBureauIdEqual(station.getJurisdictionBureauId());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setJurisdictionTdIdEqual(station.getJurisdictionTdId());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setBureauPartingEqual(station.isBureauParting());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setTelegraphCodeLike(station.getTelegraphCode());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setTelegraphCodeEqual(station.getTelegraphCode());
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertTrue(station.equals(getOne));

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setPinyinLike(station.getNamePinyin());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setBureauPartingEqual(true);
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        Assert.assertEquals(1, stationDao.getCount(stationSearchVo));
        stationSearchVo = new StationSearchVo();
        stationSearchVo.setBureauPartingEqual(true);
        Assert.assertEquals(1, stationDao.getCount(stationSearchVo));
        stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, stationDao.getCount(stationSearchVo));

        bureau = new Bureau();
        bureau.setId(station.getJurisdictionBureauId());
        getMany = stationDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepot = new TrainlineDepot();
        trainlineDepot.setId(station.getJurisdictionTdId());
        getMany = stationDao.getMany(trainlineDepot);
        Assert.assertTrue(getMany.size() > 0);

        stationDao.updateOne(station);
        stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(id);
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(station.getGridGeometryType(), getOne.getGridGeometryType());
        Assert.assertEquals(station.getBasePointString(), getOne.getBasePointString());
        Assert.assertEquals(station.getAnchorPointsString(), getOne.getAnchorPointsString());
        Assert.assertEquals(station.getName(), getOne.getName());
        Assert.assertEquals(station.getJurisdictionBureauId(), getOne.getJurisdictionBureauId());
        Assert.assertEquals(station.getJurisdictionBureauName(), getOne.getJurisdictionBureauName());
        Assert.assertEquals(station.getJurisdictionTdId(), getOne.getJurisdictionTdId());
        Assert.assertEquals(station.getJurisdictionTdName(), getOne.getJurisdictionTdName());
        Assert.assertEquals(station.isBureauParting(), getOne.isBureauParting());
        Assert.assertEquals(station.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(station.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(station.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(station.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(station.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());
        Assert.assertEquals(station.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(station.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        stationDao.deleteOne(station);
        stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(id);
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertNull(getOne);
    }
}
