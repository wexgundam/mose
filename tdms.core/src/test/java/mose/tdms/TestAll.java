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
        //mose.tdms.core.dao
        mose.tdms.core.dao.TestBureauDao.class,
        mose.tdms.core.dao.TestFenjiekouDao.class,
        mose.tdms.core.dao.TestLineDao.class,
        mose.tdms.core.dao.TestStationDao.class,
        mose.tdms.core.dao.TestTrainlineDepotDao.class,
        mose.tdms.core.dao.TestTrainoperationDepotDao.class,
        //mose.tdms.core.service
        mose.tdms.core.service.TestBureauFeatureService.class,
        mose.tdms.core.service.TestFenjiekouFeatureService.class,
        mose.tdms.core.service.TestLineFeatureService.class,
        mose.tdms.core.service.TestStationFeatureService.class,
        mose.tdms.core.service.TestTrainlineDepotFeatureService.class,
        mose.tdms.core.service.TestTrainoperationDepotFeatureService.class
})
public class TestAll {

}
