package mose.network.service;

import org.junit.Assert;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/9
 */
public class TestJts {
    GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    public void testDistance() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pointA = geometryFactory.createPoint(new Coordinate(0, 0));
        Point pointB = geometryFactory.createPoint(new Coordinate(1, 1));
        System.out.println(pointA.distance(pointB));
    }

    @Test
    public void testEmptyPolygon() {
        Polygon polygon = geometryFactory.createPolygon(new Coordinate[0]);
        Assert.assertTrue(polygon.isValid());
        Assert.assertTrue(polygon.isEmpty());
    }

    /**
     * 1个点构造Polygon-->异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidatePolygon1() {
        geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0)});
    }

    /**
     * 2个点构造Polygon-->异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidatePolygon2() {
        geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 0)});
    }

    /**
     * 3个点构造Polygon-->异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidatePolygon3() {
        geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0)});
    }

    /**
     * 4个点构造Polygon-->无异常
     */
    @Test
    public void testValidatePolygon4() {
        geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0)});
    }

    @Test
    public void testPredicates() {
        // PolygonA是一个（0,0），（0,100），（100,100），（100,0），（0,0）的多边形
        Polygon polygonA = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 100), new Coordinate(100, 100), new Coordinate(100, 0), new Coordinate(0, 0)});

        // 自我判定
        Assert.assertTrue(polygonA.contains(polygonA));
        Assert.assertTrue(polygonA.intersects(polygonA));
        Assert.assertFalse(polygonA.touches(polygonA));
        Assert.assertFalse(polygonA.disjoint(polygonA));

        // PolygonB是一个empty polygon
        Polygon polygonB = geometryFactory.createPolygon(new Coordinate[0]);
        Assert.assertFalse(polygonA.contains(polygonB));
        Assert.assertFalse(polygonA.intersects(polygonB));
        Assert.assertFalse(polygonA.touches(polygonB));
        Assert.assertTrue(polygonA.disjoint(polygonB));

        // PolygonC是一个点（10,10）
        // 测试多边形包含一点
        Polygon polygonC = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(10, 10), new Coordinate(10, 10), new Coordinate(10, 10), new Coordinate(10, 10)});
        Assert.assertTrue(polygonA.contains(polygonC));
        Assert.assertTrue(polygonA.intersects(polygonC));
        Assert.assertFalse(polygonA.touches(polygonC));
        Assert.assertFalse(polygonA.disjoint(polygonC));

        // PolygonD是一个点（0,0）,（0,10），（10,10），（10,0），（0,0）的多边形
        // 测试多变内切多边形
        Polygon polygonD = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 10), new Coordinate(10, 10), new Coordinate(10, 0), new Coordinate(0, 0)});
        Assert.assertTrue(polygonA.contains(polygonD));
        Assert.assertTrue(polygonA.intersects(polygonD));
        Assert.assertFalse(polygonA.touches(polygonD));
        Assert.assertFalse(polygonA.disjoint(polygonD));

        // PolygonE是一个点（10,10），（10,20），（20,20），（20,10），（10,10）的多边形
        // 测试多变内含多边形
        Polygon polygonE = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(10, 10), new Coordinate(10, 20), new Coordinate(20, 20), new Coordinate(20, 10), new Coordinate(10, 10)});
        Assert.assertTrue(polygonA.contains(polygonE));
        Assert.assertTrue(polygonA.intersects(polygonE));
        Assert.assertFalse(polygonA.touches(polygonE));
        Assert.assertFalse(polygonA.disjoint(polygonE));

        // PolygonF是一个点（100,100），（100,110），（110,110），（110,100），（100,100）的多边形
        // 测试多边形外切多边形与一点
        Polygon polygonF = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(100, 100), new Coordinate(100, 110), new Coordinate(110, 110), new Coordinate(110, 100), new Coordinate(100, 100)});
        Assert.assertFalse(polygonA.contains(polygonF));
        Assert.assertTrue(polygonA.intersects(polygonF));
        Assert.assertTrue(polygonA.touches(polygonF));
        Assert.assertFalse(polygonA.disjoint(polygonF));

        // PolygonF是一个点（0,100），（0,110），（110,110），（110,100），（0,100）的多边形
        // 测试多边形外切多边形与一边
        Polygon polygonG = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(100, 100), new Coordinate(100, 110), new Coordinate(110, 110), new Coordinate(110, 100), new Coordinate(100, 100)});
        Assert.assertFalse(polygonA.contains(polygonG));
        Assert.assertTrue(polygonA.intersects(polygonG));
        Assert.assertTrue(polygonA.touches(polygonG));
        Assert.assertFalse(polygonA.disjoint(polygonG));

        // PolygonG是一个点（110,110），（110,120），（120,120），（120,110），（110,110）的多边形
        // 测试2个多边形无关联
        Polygon polygonH = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(110, 110), new Coordinate(110, 120), new Coordinate(120, 120), new Coordinate(120, 110), new Coordinate(110, 110)});
        Assert.assertFalse(polygonA.contains(polygonH));
        Assert.assertFalse(polygonA.intersects(polygonH));
        Assert.assertFalse(polygonA.touches(polygonH));
        Assert.assertTrue(polygonA.disjoint(polygonH));
    }

    @Test
    public void testTouch() {
        // 点和线互切于1点
        Point point = geometryFactory.createPoint(new Coordinate(0, 0));
        LineString lineString = geometryFactory.createLineString(new Coordinate[]{new Coordinate(0, 0), new Coordinate(100, 100)});
        LineString lineString2 = geometryFactory.createLineString(new Coordinate[]{new Coordinate(100, 100), new Coordinate(200, 200)});
        Assert.assertTrue(lineString.touches(point));
        Assert.assertTrue(point.touches(lineString));

        // 点和多边形互切于1点
        Polygon polygon = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0)});
        Assert.assertTrue(polygon.touches(point));
        Assert.assertTrue(point.touches(polygon));

        // 线和多边形互切于1点
        Assert.assertTrue(polygon.touches(lineString));
        Assert.assertTrue(lineString.touches(polygon));

        // 缩成一点的polygon和缩成一条线的polygon不相切
        Polygon polygonB = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(0, 0), new Coordinate(100, 100), new Coordinate(0, 0), new Coordinate(0, 0)});
        Assert.assertFalse(polygon.touches(polygonB));
    }

    @Test
    public void testLineString() {
        //线与线包含
        LineString lineStringA = geometryFactory.createLineString(new Coordinate[]{new Coordinate(0, 0), new Coordinate(100, 0), new Coordinate(200, 0)});
        LineString lineStringB = geometryFactory.createLineString(new Coordinate[]{new Coordinate(0, 0), new Coordinate(100, 0)});
        LineString lineStringC = geometryFactory.createLineString(new Coordinate[]{new Coordinate(0, 100), new Coordinate(100, 0), new Coordinate(0, -100)});
        LineString lineStringD = geometryFactory.createLineString(new Coordinate[]{new Coordinate(0, 200), new Coordinate(100, 200), new Coordinate(200, 200)});
        Assert.assertTrue(lineStringA.contains(lineStringB));
        Assert.assertFalse(lineStringA.contains(lineStringC));
        Assert.assertFalse(lineStringA.contains(lineStringD));
        Assert.assertFalse(lineStringB.contains(lineStringC));
        Assert.assertFalse(lineStringB.contains(lineStringD));
        Assert.assertFalse(lineStringC.contains(lineStringD));

        //线与线交叉
        Assert.assertFalse(lineStringA.crosses(lineStringB));
        Assert.assertTrue(lineStringA.crosses(lineStringC));
        Assert.assertFalse(lineStringA.crosses(lineStringD));
        Assert.assertFalse(lineStringB.crosses(lineStringC));
        Assert.assertFalse(lineStringB.crosses(lineStringD));
        Assert.assertFalse(lineStringC.crosses(lineStringC));

        //线与线相切
        Assert.assertFalse(lineStringA.touches(lineStringB));
        Assert.assertFalse(lineStringA.touches(lineStringC));
        Assert.assertFalse(lineStringA.touches(lineStringD));
        Assert.assertTrue(lineStringB.touches(lineStringC));
        Assert.assertFalse(lineStringB.touches(lineStringD));
        Assert.assertFalse(lineStringC.touches(lineStringD));

        //线覆盖
        Assert.assertTrue(lineStringA.covers(lineStringB));
        Assert.assertFalse(lineStringA.covers(lineStringC));
        Assert.assertFalse(lineStringA.covers(lineStringD));
        Assert.assertFalse(lineStringB.covers(lineStringC));
        Assert.assertFalse(lineStringB.covers(lineStringD));
        Assert.assertFalse(lineStringC.covers(lineStringD));

        //重叠，含交叉
        Assert.assertTrue(lineStringA.intersects(lineStringB));
        Assert.assertTrue(lineStringA.intersects(lineStringC));
        Assert.assertFalse(lineStringA.intersects(lineStringD));
        Assert.assertTrue(lineStringB.intersects(lineStringC));
        Assert.assertFalse(lineStringB.intersects(lineStringD));
        Assert.assertFalse(lineStringC.intersects(lineStringD));

        //重叠内容
        Assert.assertEquals(LineString.class.getSimpleName(), lineStringA.intersection(lineStringB).getGeometryType());
        Assert.assertEquals(Point.class.getSimpleName(), lineStringA.intersection(lineStringC).getGeometryType());
        Assert.assertEquals(Point.class.getSimpleName(), lineStringB.intersection(lineStringC).getGeometryType());
    }
}
