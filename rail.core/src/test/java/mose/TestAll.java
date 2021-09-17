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
        mose.rail2.dao.TestBureauDao.class,
        mose.rail2.dao.TestLinkDao.class,
        mose.rail2.dao.TestStationDao.class,
        mose.rail2.dao.TestTrainlineDepotDao.class,
        mose.rail2.dao.TestYardDao.class,
        mose.rail2.modal.TestVector.class,
        mose.rail2.service.TestBureauService.class,
        mose.rail2.service.TestLinkService.class,
        mose.rail2.service.TestNetworkElementService.class,
        mose.rail2.service.TestRouteService.class,
        mose.rail2.service.TestSiteService.class,
        mose.rail2.service.TestStationService.class,
        mose.rail2.service.TestTrainlineDepotService.class,
        mose.rail2.service.TestTrainlineService.class,
        mose.rail2.service.TestYardService.class,
        mose.tile.modal.TestTileBounds.class
})
public class TestAll {

}
