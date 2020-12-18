/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        mose.network.modal.TestGrid.class,
        mose.network.modal.TestPointVector.class,
        mose.network.service.TestExecutorService.class,
        mose.network.service.TestGridService.class,
        mose.network.service.TestJts.class,
        mose.rail.dao.TestBureauDao.class,
        mose.rail.dao.TestLinkDao.class,
        mose.rail.dao.TestStationDao.class,
        mose.rail.dao.TestTrainlineDepotDao.class,
        mose.rail.dao.TestYardDao.class,
        mose.rail.modal.TestVector.class,
        mose.rail.service.TestBureauService.class,
        mose.rail.service.TestLinkService.class,
        mose.rail.service.TestNetworkElementService.class,
        mose.rail.service.TestRouteService.class,
        mose.rail.service.TestSiteService.class,
        mose.rail.service.TestStationService.class,
        mose.rail.service.TestTrainlineDepotService.class,
        mose.rail.service.TestTrainlineService.class,
        mose.rail.service.TestYardService.class,
        mose.tile.modal.TestTileBounds.class
})
public class TestAll {

}
