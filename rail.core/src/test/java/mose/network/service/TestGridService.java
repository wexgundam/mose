/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.network.service;

import mose.CommonConfiguration;
import mose.core.time.DateUtil;
import mose.network.modal.Grid;
import mose.rail.core.service.LocationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    测试PointVector. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        CommonConfiguration.class,
        TestGridService.class
})
@Configuration
@ComponentScan(basePackages = "mose.network.service", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {GridService.class})
})
public class TestGridService {
    @Autowired
    GridService gridService;

    @Test
    public void testTouches() {
        // 点类型网格
        Grid gridPointA = new Grid();
        gridPointA.setGeometryType(Grid.GEOMETRY_TYPE_POINT);
        gridPointA.addAnchorPointVector(0, 0);
        Grid gridPointB = new Grid();
        gridPointB.setGeometryType(Grid.GEOMETRY_TYPE_POINT);
        gridPointB.addAnchorPointVector(0, 0);
        gridPointB.addAnchorPointVector(0, 100);
        gridPointB.addAnchorPointVector(100, 100);
        gridPointB.addAnchorPointVector(100, 0);

        //点和点不相切
        boolean result = gridService.touches(gridPointA, gridPointB);
        Assert.assertFalse(result);
        result = gridService.touches(gridPointB, gridPointA);
        Assert.assertFalse(result);

        // 线类型网格
        Grid gridLineStringA = new Grid();
        gridLineStringA.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringA.addAnchorPointVector(0, 0);
        gridLineStringA.addAnchorPointVector(10, 20);
        gridLineStringA.addAnchorPointVector(20, 20);
        gridLineStringA.addAnchorPointVector(20, 10);
        Grid gridLineStringB = new Grid();
        gridLineStringB.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringB.addAnchorPointVector(20, 10);
        gridLineStringB.addAnchorPointVector(30, 10);

        //点和线相切
        result = gridService.touches(gridPointA, gridLineStringA);
        Assert.assertTrue(result);
        result = gridService.touches(gridLineStringA, gridPointA);
        Assert.assertTrue(result);
        result = gridService.touches(gridPointB, gridLineStringB);
        Assert.assertFalse(result);
        result = gridService.touches(gridLineStringB, gridPointB);
        Assert.assertFalse(result);

        //线和线相切
        result = gridService.touches(gridLineStringA, gridLineStringB);
        Assert.assertTrue(result);
        result = gridService.touches(gridLineStringB, gridLineStringA);
        Assert.assertTrue(result);

        // 多边形类型网格
        Grid gridPolygonA = new Grid();
        gridPolygonA.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        gridPolygonA.addAnchorPointVector(0, 0);
        gridPolygonA.addAnchorPointVector(-10, -20);
        gridPolygonA.addAnchorPointVector(-20, -20);
        Grid gridPolygonB = new Grid();
        gridPolygonB.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        gridPolygonB.addAnchorPointVector(-20, -20);
        gridPolygonB.addAnchorPointVector(-100, -200);
        gridPolygonB.addAnchorPointVector(-200, -30);

        //点和多边形相切
        result = gridService.touches(gridPointA, gridPolygonA);
        Assert.assertTrue(result);
        result = gridService.touches(gridPolygonA, gridPointA);
        Assert.assertTrue(result);

        //线和多边形相切
        result = gridService.touches(gridLineStringA, gridPolygonA);
        Assert.assertTrue(result);
        result = gridService.touches(gridPolygonA, gridLineStringA);
        Assert.assertTrue(result);

        //多边形和多边形相切
        result = gridService.touches(gridPolygonA, gridPolygonB);
        Assert.assertTrue(result);
        result = gridService.touches(gridPolygonA, gridPolygonB);
        Assert.assertTrue(result);
    }

    @Test
    public void testContains() {
        // 点类型网格
        Grid gridPointA = new Grid();
        gridPointA.setGeometryType(Grid.GEOMETRY_TYPE_POINT);
        gridPointA.addAnchorPointVector(0, 0);
        Grid gridPointB = new Grid();
        gridPointB.setGeometryType(Grid.GEOMETRY_TYPE_POINT);
        gridPointB.addAnchorPointVector(5, 5);
        // 线类型网格
        Grid gridLineString = new Grid();
        gridLineString.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineString.addAnchorPointVector(0, 0);
        gridLineString.addAnchorPointVector(10, 10);
        gridLineString.addAnchorPointVector(20, 30);
        gridLineString.addAnchorPointVector(20, 10);
        // 多边形类型网格
        Grid gridPolygonA = new Grid();
        gridPolygonA.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        gridPolygonA.addAnchorPointVector(0, 0);
        gridPolygonA.addAnchorPointVector(0, 100);
        gridPolygonA.addAnchorPointVector(100, 100);
        gridPolygonA.addAnchorPointVector(100, 0);
        Grid gridPolygonB = new Grid();
        gridPolygonB.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        gridPolygonB.addAnchorPointVector(0, 0);
        gridPolygonB.addAnchorPointVector(0, 10);
        gridPolygonB.addAnchorPointVector(20, 20);
        Grid gridPolygonC = new Grid();
        gridPolygonC.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        gridPolygonC.addAnchorPointVector(0, 0);
        gridPolygonC.addAnchorPointVector(-10, -10);
        gridPolygonC.addAnchorPointVector(20, 20);

        //点包含点
        boolean result = gridService.contains(gridPointA, gridPointA);
        Assert.assertTrue(result);
        //线不包含边界点
        result = gridService.contains(gridLineString, gridPointA);
        Assert.assertFalse(result);
        //线包含中间点
        result = gridService.contains(gridLineString, gridPointB);
        Assert.assertTrue(result);
        //多边形不包含边界点
        result = gridService.contains(gridPolygonA, gridPointA);
        Assert.assertFalse(result);
        //多边形包含内部点
        result = gridService.contains(gridPolygonA, gridPointB);
        Assert.assertTrue(result);
        //多边形包含线
        result = gridService.contains(gridPolygonA, gridLineString);
        Assert.assertTrue(result);
        //多边形包含多边形
        result = gridService.contains(gridPolygonA, gridPolygonB);
        Assert.assertTrue(result);
        //多边形不包含多边形
        result = gridService.contains(gridPolygonA, gridPolygonC);
        Assert.assertFalse(result);
    }

    @Test
    public void testIntersects() {
        // 线类型网格
        Grid gridLineStringA = new Grid();
        gridLineStringA.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringA.addAnchorPointVector(0, 0);
        gridLineStringA.addAnchorPointVector(10, 10);
        gridLineStringA.addAnchorPointVector(20, 30);
        gridLineStringA.addAnchorPointVector(20, 10);
        Grid gridLineStringB = new Grid();
        gridLineStringB.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringB.addAnchorPointVector(20, 30);
        gridLineStringB.addAnchorPointVector(20, 10);
        gridLineStringB.addAnchorPointVector(50, 70);
        gridLineStringB.addAnchorPointVector(90, 90);
        Grid gridLineStringC = new Grid();
        gridLineStringC.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringC.addAnchorPointVector(20, 30);
        gridLineStringC.addAnchorPointVector(30, 50);
        Grid gridLineStringD = new Grid();
        gridLineStringD.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringD.addAnchorPointVector(10, 20);
        gridLineStringD.addAnchorPointVector(20, 30);
        gridLineStringD.addAnchorPointVector(60, 50);

        //重叠
        Assert.assertTrue(gridService.intersects(gridLineStringA, gridLineStringB));
        //相切
        Assert.assertFalse(gridService.intersects(gridLineStringA, gridLineStringC));
        //相相交
        Assert.assertFalse(gridService.intersects(gridLineStringA, gridLineStringD));
    }

    @Test
    public void testGetIntersection() {
        // 线类型网格
        Grid gridLineStringA = new Grid();
        gridLineStringA.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringA.addAnchorPointVector(0, 0);
        gridLineStringA.addAnchorPointVector(10, 10);
        gridLineStringA.addAnchorPointVector(20, 30);
        gridLineStringA.addAnchorPointVector(20, 10);
        Grid gridLineStringB = new Grid();
        gridLineStringB.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringB.addAnchorPointVector(20, 30);
        gridLineStringB.addAnchorPointVector(20, 10);
        gridLineStringB.addAnchorPointVector(50, 70);
        gridLineStringB.addAnchorPointVector(90, 90);
        Grid gridLineStringC = new Grid();
        gridLineStringC.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringC.addAnchorPointVector(20, 30);
        gridLineStringC.addAnchorPointVector(30, 50);
        Grid gridLineStringD = new Grid();
        gridLineStringD.setGeometryType(Grid.GEOMETRY_TYPE_LINE_STRING);
        gridLineStringD.addAnchorPointVector(10, 20);
        gridLineStringD.addAnchorPointVector(20, 30);
        gridLineStringD.addAnchorPointVector(60, 50);

        //重叠
        List<Grid> intersectionGrids = gridService.getIntersection(gridLineStringA, gridLineStringB);
        Assert.assertNotNull(intersectionGrids);
        Assert.assertFalse(intersectionGrids.isEmpty());
        Assert.assertEquals(1, intersectionGrids.size());
        Assert.assertEquals(Grid.GEOMETRY_TYPE_LINE_STRING, intersectionGrids.get(0).getGeometryType());
        //相切
        intersectionGrids = gridService.getIntersection(gridLineStringA, gridLineStringC);
        Assert.assertNotNull(intersectionGrids);
        Assert.assertTrue(intersectionGrids.isEmpty());
        //相相交
        intersectionGrids = gridService.getIntersection(gridLineStringA, gridLineStringD);
        Assert.assertNotNull(intersectionGrids);
        Assert.assertTrue(intersectionGrids.isEmpty());
    }

    @Test
    public void testGetGridContainsTargetGrid() {
        // 模拟Grid集合
        List<Grid> grids = new ArrayList<>();
        for (int index = 0; index < 1000; index++) {
            // 网格B完全内含与网格A，网格A包含网格B
            Grid grid = new Grid();
            grid.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grids.add(grid);
        }
        // 模拟目标Grid，理论存在
        Grid targetGrid = new Grid();
        targetGrid.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        targetGrid.addAnchorPointVector(10, 10);
        targetGrid.addAnchorPointVector(10, 20);
        targetGrid.addAnchorPointVector(20, 20);
        targetGrid.addAnchorPointVector(20, 10);

        System.out.println(DateUtil.getSystemTime());
        System.out.println(gridService.getGridContainsTargetGrid(grids, targetGrid));
        System.out.println(DateUtil.getSystemTime());

        // 模拟目标Grid，肯定不存在
        targetGrid = new Grid();
        targetGrid.addAnchorPointVector(-10, -10);
        targetGrid.addAnchorPointVector(-10, -20);
        targetGrid.addAnchorPointVector(-20, -20);
        targetGrid.addAnchorPointVector(-20, -10);

        System.out.println(DateUtil.getSystemTime());
        System.out.println(gridService.getGridContainsTargetGrid(grids, targetGrid));
        System.out.println(DateUtil.getSystemTime());
    }

    @Test
    public void testGetGridTouchesTargetGrid() {
        // 模拟Grid集合
        List<Grid> grids = new ArrayList<>();
        for (int index = 0; index < 1000; index++) {
            // 网格B完全内含与网格A，网格A包含网格B
            Grid grid = new Grid();
            grid.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grid.addAnchorPointVector(100 * Math.random(), 100 * Math.random());
            grids.add(grid);
        }
        // 与模拟目标Grid相切的Grid
        Grid grid = new Grid();
        grid.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        grid.addAnchorPointVector(20, 20);
        grid.addAnchorPointVector(20, 30);
        grid.addAnchorPointVector(30, 30);
        grid.addAnchorPointVector(30, 20);
        grids.add(grid);

        // 模拟目标Grid，一定存在
        Grid targetGrid = new Grid();
        targetGrid.setGeometryType(Grid.GEOMETRY_TYPE_POLYGON);
        targetGrid.addAnchorPointVector(10, 10);
        targetGrid.addAnchorPointVector(10, 20);
        targetGrid.addAnchorPointVector(20, 20);
        targetGrid.addAnchorPointVector(20, 10);

        System.out.println(DateUtil.getSystemTime());
        System.out.println(gridService.getGridTouchesTargetGrid(grids, targetGrid));
        System.out.println(DateUtil.getSystemTime());

        // 模拟目标Grid，肯定不存在
        targetGrid = new Grid();
        targetGrid.addAnchorPointVector(-10, -10);
        targetGrid.addAnchorPointVector(-10, -20);
        targetGrid.addAnchorPointVector(-20, -20);
        targetGrid.addAnchorPointVector(-20, -10);

        System.out.println(DateUtil.getSystemTime());
        System.out.println(gridService.getGridContainsTargetGrid(grids, targetGrid));
        System.out.println(DateUtil.getSystemTime());
    }
}