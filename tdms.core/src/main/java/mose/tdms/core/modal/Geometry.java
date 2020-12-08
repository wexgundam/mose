/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.core.modal;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    几何图形集合. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/1
 */
public class Geometry {
    public static final String TYPE_POINT = "POINT";
    public static final String TYPE_LINE = "LINE";

    /**
     * 超图的Id
     */
    private int id;
    /**
     * 类型
     */
    private String type;
    /**
     * 图形点集
     */
    private List<Point> points;

    /**
     * 添加坐标点
     *
     * @param point
     */
    public void addPoint(Point point) {
        getPoints().add(point);
    }

    /**
     * 添加坐标点
     *
     * @param x
     * @param y
     */
    public void addPoint(double x, double y) {
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        getPoints().add(point);
    }

    /**
     * 添加一组坐标点
     *
     * @param points
     */
    public void addPoints(List<Point> points) {
        getPoints().addAll(points);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Point> getPoints() {
        points = points == null ? new ArrayList<>() : points;
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
