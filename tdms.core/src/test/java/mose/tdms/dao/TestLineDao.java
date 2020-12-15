/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.dao;

import mose.tdms.CommonConfiguration;
import mose.tdms.modal.Bureau;
import mose.tdms.modal.Line;
import mose.tdms.modal.Station;
import mose.tdms.vo.LineSearchVo;
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
        CommonConfiguration.class, TestLineDao.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                BureauDao.class,
                StationDao.class,
                LineDao.class
        })
})
public class TestLineDao {
    @Autowired
    private BureauDao bureauDao;
    @Autowired
    private StationDao stationDao;
    @Autowired
    private LineDao lineDao;
    private Line line;

    @Before
    public void before() {
        line = new Line();
        line.setCreatorId(2);
        line.setCreatorRealName("crn");
        line.setLastEditorId(3);
        line.setLastEditorRealName("lerrn");
        line.setLastVerifierId(4);
        line.setLastVerifierRealName("lvrn");
    }

    @Test
    @Transactional
    @Rollback
    public void testCRUD() {
        Bureau bureauA = new Bureau();
        bureauA.setId(bureauDao.addOne(bureauA));
        Bureau bureauB = new Bureau();
        bureauB.setId(bureauDao.addOne(bureauB));
        Station stationA = new Station();
        stationA.setBureauId(bureauA.getId());
        stationA.setLatitude(1d);
        stationA.setLongitude(2d);
        stationA.setId(stationDao.addOne(stationA));
        Station stationB = new Station();
        stationB.setBureauId(bureauB.getId());
        stationB.setLatitude(3d);
        stationB.setLongitude(4d);
        stationB.setId(stationDao.addOne(stationB));

        line.setStationAId(stationA.getId());
        line.setStationBId(stationB.getId());

        int id = lineDao.addOne(line);
        line.setId(id);
        LineSearchVo lineSearchVo = new LineSearchVo();
        lineSearchVo.setIdEqual(id);
        Line getOne = lineDao.getOne(lineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(line.getStationAId(), getOne.getStationAId());
        Assert.assertEquals(stationA.getName(), getOne.getStationAName());
        Assert.assertEquals(stationA.getLatitude(), getOne.getStationALatitude());
        Assert.assertEquals(stationA.getLongitude(), getOne.getStationALongitude());
        Assert.assertEquals(line.getStationBId(), getOne.getStationBId());
        Assert.assertEquals(stationB.getName(), getOne.getStationBName());
        Assert.assertEquals(stationB.getLatitude(), getOne.getStationBLatitude());
        Assert.assertEquals(stationB.getLongitude(), getOne.getStationBLongitude());
        Assert.assertEquals(line.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(line.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(line.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(line.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        lineSearchVo = new LineSearchVo();
        lineSearchVo.setIdEqual(id);
        getOne = lineDao.getOne(lineSearchVo);
        Assert.assertTrue(line.equals(getOne));

        lineSearchVo = new LineSearchVo();
        lineSearchVo.setStationIdEqual(line.getStationAId());
        List<Line> getMany = lineDao.getMany(lineSearchVo);
        Assert.assertTrue(getMany.size() > 0);

        lineSearchVo = new LineSearchVo();
        lineSearchVo.setStationsIdEqual(new Integer[]{line.getStationAId(), line.getStationBId()});
        getMany = lineDao.getMany(lineSearchVo);
        Assert.assertTrue(getMany.size() > 0);


        getOne = null;
        for (Line one : getMany) {
            if (one.getId() == id) {
                getOne = one;
                break;
            }
        }
        Assert.assertNotNull(getOne);
        Assert.assertEquals(line.getStationAId(), getOne.getStationAId());
        Assert.assertEquals(stationA.getName(), getOne.getStationAName());
        Assert.assertEquals(stationA.getLatitude(), getOne.getStationALatitude());
        Assert.assertEquals(stationA.getLongitude(), getOne.getStationALongitude());
        Assert.assertEquals(line.getStationBId(), getOne.getStationBId());
        Assert.assertEquals(stationB.getName(), getOne.getStationBName());
        Assert.assertEquals(stationB.getLatitude(), getOne.getStationBLatitude());
        Assert.assertEquals(stationB.getLongitude(), getOne.getStationBLongitude());
        ;
        Assert.assertEquals(line.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(line.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(line.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(line.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        lineDao.updateOne(line);
        lineSearchVo = new LineSearchVo();
        lineSearchVo.setIdEqual(id);
        getOne = lineDao.getOne(lineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(line.getStationAId(), getOne.getStationAId());
        Assert.assertEquals(stationA.getName(), getOne.getStationAName());
        Assert.assertEquals(stationA.getLatitude(), getOne.getStationALatitude());
        Assert.assertEquals(stationA.getLongitude(), getOne.getStationALongitude());
        Assert.assertEquals(line.getStationBId(), getOne.getStationBId());
        Assert.assertEquals(stationB.getName(), getOne.getStationBName());
        Assert.assertEquals(stationB.getLatitude(), getOne.getStationBLatitude());
        Assert.assertEquals(stationB.getLongitude(), getOne.getStationBLongitude());
        Assert.assertEquals(line.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(line.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(line.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(line.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());

        lineDao.verifyOne(line);
        lineSearchVo = new LineSearchVo();
        lineSearchVo.setIdEqual(id);
        lineSearchVo.setVerified(true);
        getOne = lineDao.getOne(lineSearchVo);
        Assert.assertNotNull(getOne);
        Assert.assertEquals(line.getStationAId(), getOne.getStationAId());
        Assert.assertEquals(stationA.getName(), getOne.getStationAName());
        Assert.assertEquals(stationA.getLatitude(), getOne.getStationALatitude());
        Assert.assertEquals(stationA.getLongitude(), getOne.getStationALongitude());
        Assert.assertEquals(line.getStationBId(), getOne.getStationBId());
        Assert.assertEquals(stationB.getName(), getOne.getStationBName());
        Assert.assertEquals(stationB.getLatitude(), getOne.getStationBLatitude());
        Assert.assertEquals(stationB.getLongitude(), getOne.getStationBLongitude());
        Assert.assertEquals(line.getCreatorId(), getOne.getCreatorId());
        Assert.assertEquals(line.getCreatorRealName(), getOne.getCreatorRealName());
        Assert.assertNotNull(getOne.getCreatedAt());
        Assert.assertEquals(line.getLastEditorId(), getOne.getLastEditorId());
        Assert.assertEquals(line.getLastEditorRealName(), getOne.getLastEditorRealName());
        Assert.assertNotNull(getOne.getLastEditedAt());
        Assert.assertEquals(line.getLastVerifierId(), getOne.getLastVerifierId());
        Assert.assertEquals(line.getLastVerifierRealName(), getOne.getLastVerifierRealName());
        Assert.assertNotNull(getOne.getLastVerifiedAt());

        lineDao.deleteOne(line);
        lineSearchVo = new LineSearchVo();
        lineSearchVo.setIdEqual(id);
        getOne = lineDao.getOne(lineSearchVo);
        Assert.assertNull(getOne);
    }
}
