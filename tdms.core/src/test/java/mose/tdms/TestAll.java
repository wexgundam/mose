/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //mose.tdms.dao
        mose.tdms.dao.TestBureauDao.class,
        mose.tdms.dao.TestFenjiekouDao.class,
        mose.tdms.dao.TestLineDao.class,
        mose.tdms.dao.TestStationDao.class,
        mose.tdms.dao.TestTrainlineDepotDao.class,
        mose.tdms.dao.TestTrainoperationDepotDao.class,
        //mose.tdms.service
        mose.tdms.service.TestBureauService.class,
        mose.tdms.service.TestBureauFeatureService.class,
        mose.tdms.service.TestFenjiekouService.class,
        mose.tdms.service.TestFenjiekouFeatureService.class,
        mose.tdms.service.TestLineService.class,
        mose.tdms.service.TestLineFeatureService.class,
        mose.tdms.service.TestStationService.class,
        mose.tdms.service.TestStationFeatureService.class,
        mose.tdms.service.TestTrainlineDepotService.class,
        mose.tdms.service.TestTrainlineDepotFeatureService.class,
        mose.tdms.service.TestTrainoperationDepotService.class,
        mose.tdms.service.TestTrainoperationDepotFeatureService.class
})
public class TestAll {

}
