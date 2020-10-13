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
        mose.rail.core.dao.TestBureauDao.class,
        mose.rail.core.dao.TestLinkDao.class,
        mose.rail.core.dao.TestStationDao.class,
        mose.rail.core.dao.TestTrainlineDepotDao.class,
        mose.rail.core.dao.TestYardDao.class,
        mose.rail.core.modal.TestVector.class,
        mose.rail.core.service.TestBureauService.class,
        mose.rail.core.service.TestLinkService.class,
        mose.rail.core.service.TestLocationService.class,
        mose.rail.core.service.TestNetworkElementService.class,
        mose.rail.core.service.TestRouteService.class,
        mose.rail.core.service.TestSiteService.class,
        mose.rail.core.service.TestStationService.class,
        mose.rail.core.service.TestTrainlineDepotService.class,
        mose.rail.core.service.TestTrainlineService.class,
        mose.rail.core.service.TestYardService.class,
        mose.tile.modal.TestTileBounds.class
})
public class TestAll {

}
