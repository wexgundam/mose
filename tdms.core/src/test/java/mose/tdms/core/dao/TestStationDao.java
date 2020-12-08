/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.core.dao;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import mose.core.json.JsonUtil;
import mose.core.string.StringUtil;
import mose.tdms.CommonConfiguration;
import mose.tdms.core.modal.Bureau;
import mose.tdms.core.modal.Station;
import mose.tdms.core.modal.TrainlineDepot;
import mose.tdms.core.modal.TrainoperationDepot;
import mose.tdms.core.vo.BureauSearchVo;
import mose.tdms.core.vo.StationSearchVo;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@ComponentScan(basePackages = "mose.tdms.core.dao", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauDao.class,
                TrainlineDepotDao.class,
                StationDao.class
        })
})
public class TestStationDao {
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private TrainlineDepotDao trainlineDepotDao;
    @Autowired
    private StationDao stationDao;
    private Station station;

    @Before
    public void before() {
        station = new Station();
        station.setBureauId(2);
        station.setTrainlineDepotId(3);
        station.setName("n");
        station.setNameInitialPinyin("NIP");
        station.setNamePinyin("NP");
        station.setTelegraphCode("tc");
        station.setLatitude(52.11111);
        station.setLongitude(132.11111);
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
        station.setBureauId(bureauId);
        station.setBureauName(bureau.getName());

        TrainlineDepot trainlineDepot = new TrainlineDepot();
        trainlineDepot.setName("b");
        int trainlineDepotId = trainlineDepotDao.addOne(trainlineDepot);
        station.setTrainlineDepotId(trainlineDepotId);
        station.setTrainlineDepotName(trainlineDepot.getName());

        int id = stationDao.addOne(station);
        station.setId(id);
        StationSearchVo stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(id);
        Station getOne = stationDao.getOne(stationSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(station.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(station.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(station.getTrainlineDepotId(), getOne.getTrainlineDepotId());
        Assert.assertEquals(station.getTrainlineDepotName(), getOne.getTrainlineDepotName());
        Assert.assertEquals(station.getName(), getOne.getName());
        Assert.assertEquals(station.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(station.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(station.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(station.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(station.getLongitude(), getOne.getLongitude(), 0);
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
        stationSearchVo.setTextLike(station.getName());
        List<Station> getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setTelegraphCodeEqual(station.getTelegraphCode());
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertTrue(station.equals(getOne));

        getOne = null;
        for (Station one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(station.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(station.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(station.getTrainlineDepotId(), getOne.getTrainlineDepotId());
        Assert.assertEquals(station.getTrainlineDepotName(), getOne.getTrainlineDepotName());
        Assert.assertEquals(station.getName(), getOne.getName());
        Assert.assertEquals(station.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(station.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(station.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(station.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(station.getLongitude(), getOne.getLongitude(), 0);
        Assert.assertEquals(station.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(station.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(station.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(station.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setBureauIdEqual(station.getBureauId());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setTelegraphCodeEqual(station.getTelegraphCode());
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertTrue(station.equals(getOne));

        stationSearchVo = new StationSearchVo();
        Assert.assertTrue(stationDao.getCount(stationSearchVo) > 0);
        stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(-1);
        Assert.assertEquals(0, stationDao.getCount(stationSearchVo));

        stationSearchVo = new StationSearchVo();
        stationSearchVo.setTextLike(station.getNamePinyin());
        getMany = stationDao.getMany(stationSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        bureau = new Bureau();
        bureau.setId(station.getBureauId());
        getMany = stationDao.getMany(bureau);
        Assert.assertTrue(getMany.size() > 0);

        trainlineDepot = new TrainlineDepot();
        trainlineDepot.setId(station.getTrainlineDepotId());
        getMany = stationDao.getMany(trainlineDepot);
        Assert.assertTrue(getMany.size() > 0);

        stationDao.updateOne(this.station);
        stationSearchVo = new StationSearchVo();
        stationSearchVo.setIdEqual(id);
        getOne = stationDao.getOne(stationSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(station.getBureauId(), getOne.getBureauId());
        Assert.assertEquals(station.getBureauName(), getOne.getBureauName());
        Assert.assertEquals(station.getTrainlineDepotId(), getOne.getTrainlineDepotId());
        Assert.assertEquals(station.getTrainlineDepotName(), getOne.getTrainlineDepotName());
        Assert.assertEquals(station.getName(), getOne.getName());
        Assert.assertEquals(station.getNamePinyin(), getOne.getNamePinyin());
        Assert.assertEquals(station.getNameInitialPinyin(), getOne.getNameInitialPinyin());
        Assert.assertEquals(station.getTelegraphCode(), getOne.getTelegraphCode());
        Assert.assertEquals(station.getLatitude(), getOne.getLatitude(), 0);
        Assert.assertEquals(station.getLongitude(), getOne.getLongitude(), 0);
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

    //    @Test
    public void updateTrainlineDepot() throws IOException {
        Map<String, TrainlineDepot> depots = new HashMap<>();
        for (TrainlineDepot depot : trainlineDepotDao.getAll()) {
            String key = depot.getBureauId() + Integer.toString(depot.getDdtId());
            depots.put(key, depot);
        }

        Map<String, Station> stations = new HashMap<>();
        for (Station station : stationDao.getAll()) {
            String key = station.getBureauId() + station.getName();
            stations.put(key, station);
        }

        InputStream inputStream = this.getClass().getResourceAsStream("trainlineDepotEntries.json");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);
        TrainlineDepotEntry[] trainlineDepotEntriesJson = JsonUtil.toObject(json, TrainlineDepotEntry[].class);

        for (int index = 0; index < trainlineDepotEntriesJson.length; index++) {
            TrainlineDepotEntry trainlineDepotEntry = trainlineDepotEntriesJson[index];
            String key1 = trainlineDepotEntry.getBureauCode() + trainlineDepotEntry.getEntryText();
            String key2 = trainlineDepotEntry.getBureauCode() + Integer.toString(trainlineDepotEntry.getDdtId());
            if (stations.containsKey(key1) && depots.containsKey(key2)) {
                Station station = stations.get(key1);
                station.setTrainlineDepotId(depots.get(key2).getId());
                stationDao.updateOne(station);
            }
        }
    }

    private static class TrainlineDepotEntry {
        private int bureauCode;
        private int ddtId;
        private int queueIndex;
        private int entryIndex;
        private String entryText;

        public int getBureauCode() {
            return bureauCode;
        }

        public void setBureauCode(int bureauCode) {
            this.bureauCode = bureauCode;
        }

        public int getDdtId() {
            return ddtId;
        }

        public void setDdtId(int ddtId) {
            this.ddtId = ddtId;
        }

        public int getQueueIndex() {
            return queueIndex;
        }

        public void setQueueIndex(int queueIndex) {
            this.queueIndex = queueIndex;
        }

        public int getEntryIndex() {
            return entryIndex;
        }

        public void setEntryIndex(int entryIndex) {
            this.entryIndex = entryIndex;
        }

        public String getEntryText() {
            return entryText;
        }

        public void setEntryText(String entryText) {
            this.entryText = entryText;
        }
    }
}
