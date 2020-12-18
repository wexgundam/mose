/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.modal;


import mose.network.modal.Grid;

/**
 * what:    铁路路网元素，路网元素具有以下属性. <br/>
 * 1.主键.<br/>
 * 2.网格.<br/>
 * 3.网格空间几何类型.<br/>
 * 3.基点坐标的字符串形式.<br/>
 * 4.锚点坐标的字符串形式.<br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/6
 */
public interface INetworkElement extends IAudit {
    /**
     * 路网元素类型——路局
     */
    static final int TYPE_BUREAU = 0;
    /**
     * 路网元素类型——行车调度台
     */
    static final int TYPE_TRAINLINE_DEPOT = 1;
    /**
     * 路网元素类型——车站
     */
    static final int TYPE_STATION = 2;
    /**
     * 路网元素类型——车场
     */
    static final int TYPE_YARD = 3;

    /**
     * what:    获得主键Id. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    int getId();

    /**
     * what:    设置主键Id. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    void setId(int id);

    /**
     * what:    获得对应网格. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    Grid getGrid();

    /**
     * what:    设置对应网格. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    void setGrid(Grid grid);

    /**
     * what:    获得对应网格的空间几何类型. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    int getGridGeometryType();

    /**
     * what:    设置对应网格的空间几何类型. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    void setGridGeometryType(int gridGeometryType);

    /**
     * what:    获得对应网格的字符串形式的基点坐标. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    String getBasePointString();

    /**
     * what:    设置对应网格的字符串形式的基点坐标. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    void setBasePointString(String basePointString);

    /**
     * what:    获得对应网格的字符串形式的锚点坐标. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    String getAnchorPointsString();

    /**
     * what:    设置对应网格的字符串形式的锚点坐标. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    void setAnchorPointsString(String anchorPointsString);
}
