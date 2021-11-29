/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail;


import mose.rail.dao.TestBureauDao;
import mose.rail.dao.TestIntervalLineDao;
import mose.rail.dao.TestNodeDao;
import mose.rail.dao.TestRailwayLineDao;
import mose.rail.service.TestBureauService;
import mose.rail.service.TestIntervalLineService;
import mose.rail.service.TestNodeService;
import mose.rail.service.TestRailwayLineService;
import mose.rail.vo.TestIntervalGeospatialVo;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //dao
        TestBureauDao.class,
        TestIntervalLineDao.class,
        TestNodeDao.class,
        TestRailwayLineDao.class,
        //service
        TestBureauService.class,
        TestIntervalLineService.class,
        TestNodeService.class,
        TestRailwayLineService.class,
        //vo
        TestIntervalGeospatialVo.class
})
public class TestAll {

}
