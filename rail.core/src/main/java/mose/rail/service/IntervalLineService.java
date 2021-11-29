/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.rail.service;

import mose.core.gis.GisUtil;
import mose.core.json.JsonUtil;
import mose.rail.dao.IntervalLineDao;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.Node;
import mose.rail.vo.IntervalLineGeoSpatialVo;
import mose.rail.vo.IntervalLineSearchVo;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * what:    区间线路服务. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
@Service
public class IntervalLineService {
    @Autowired
    private IntervalLineDao intervalLineDao;
    @Autowired
    private NodeService nodeService;

    /**
     * 相同node间连线间距，默认100m
     */
    private double geoSpatialInterval = 100;


    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public IntervalLine getOne(Integer id) {
        IntervalLineSearchVo intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setIdEqual(id);
        return intervalLineDao.getOne(intervalLineSearchVo);
    }

    /**
     * what:    更新给定IntervalLine的GeoSpatial. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public void updateGeoSpatial(IntervalLine intervalLine) {
        //获取所有同NodeA和NodeB的IntervalLines
        IntervalLineSearchVo intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setNodesIdEqual(intervalLine.getNodesId());
        List<IntervalLine> many = intervalLineDao.getMany(intervalLineSearchVo);

        //获取排序第一的IntervalLine的必要信息
        FirstIntervalLine firstIntervalLine = toFirstIntervalLine(many.get(0));

        //计算各个IntervalLine对应的地理信息
        updateGeoSpatial(many, firstIntervalLine);
        //更新数据库
        for (IntervalLine one : many) {
            intervalLineDao.updateOne(one);
        }
    }

    /**
     * what:    获取第一个IntervalLine的必要信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    protected FirstIntervalLine toFirstIntervalLine(IntervalLine firstOne) {
        //获取关联的节点
        Node nodeA = nodeService.getOne(firstOne.getNodeAId());
        Node nodeB = nodeService.getOne(firstOne.getNodeBId());

        //EPSG4326 经纬度坐标转为web墨卡托投影坐标
        double[] nodeAEpsg3857 = GisUtil.INSTANCE.epsg4326ToEpsg3857(nodeA.getEpsg4326());
        double[] nodeBEpsg3857 = GisUtil.INSTANCE.epsg4326ToEpsg3857(nodeB.getEpsg4326());

        if (nodeAEpsg3857[0] == nodeBEpsg3857[0] && nodeAEpsg3857[1] == nodeBEpsg3857[1]) {
            System.out.println("error intervalLine:" + nodeA.getId() + "-" + nodeA.getName());
        }

        FirstIntervalLine firstIntervalLine = new FirstIntervalLine();
        firstIntervalLine.setNodeAId(nodeA.getId());
        firstIntervalLine.setNodeBId(nodeB.getId());
        firstIntervalLine.setNodeACoordinates(nodeAEpsg3857);
        firstIntervalLine.setNodeBCoordinates(nodeBEpsg3857);

        return firstIntervalLine;
    }

    /**
     * what:    更新一组IntervalLine的地理信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     核心算法.<br/>
     * <p>
     * 在EPSG3857坐标系下计算，即web墨卡托投影
     * 生成两个node之间的连线坐标
     * 所有连线围绕node中心平均分布
     * nodeA、nodeB间线路条数为奇数时，nodes中心有连线
     * nodeA、nodeB间线路条数为偶数时，nodes中心无连线
     * 多条线的连线按照nodeB围绕nodeA顺时针旋转在前的顺序排列
     * 连线间隔为10m
     * <p>
     * 生成的结果：
     * 两个node间的连线用1个LineString数组表示
     * 每个数组代表一个LineString
     * 每个LineString包含4个point坐标
     * LineString的第一个坐标一定是nodeA的center，最后一个坐标一定是nodeB的center
     * <p>
     * ***************************************************************************
     * * 由于连线平均分布和顺序排列规则，每次调用该函数,相同节点间的连线坐标都会发生修改 *
     * ***************************************************************************
     * <p>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    protected void updateGeoSpatial(List<IntervalLine> many, FirstIntervalLine firstIntervalLine) {
        //计算两点间间距
        double distance = distanceBetween(firstIntervalLine.getNodeACoordinates(), firstIntervalLine.getNodeBCoordinates());

        //根据间隔计算增量xy
        double deltaX = this.geoSpatialInterval / distance * (firstIntervalLine.getNodeBCoordinates()[1] - firstIntervalLine.getNodeACoordinates()[1]);
        double deltaY = this.geoSpatialInterval / distance * (firstIntervalLine.getNodeACoordinates()[0] - firstIntervalLine.getNodeBCoordinates()[0]);

        //根据增量和intervalLines的数量，分别生产LineString
        for (IntervalLine one : many) {
            //根据连线总数计算连线位置
            double location = (many.size() - 1) / 2d - many.indexOf(one);
            //计算连线坐标，该坐标可生成一个包含4组坐标的LineString。
            List<double[]> coordinates = new ArrayList<>();
            coordinates.add(firstIntervalLine.getNodeACoordinates());
            coordinates.add(new double[]{firstIntervalLine.getNodeACoordinates()[0] + deltaX * location, firstIntervalLine.getNodeACoordinates()[1] + deltaY * location});
            coordinates.add(new double[]{firstIntervalLine.getNodeBCoordinates()[0] + deltaX * location, firstIntervalLine.getNodeBCoordinates()[1] + deltaY * location});
            coordinates.add(firstIntervalLine.getNodeBCoordinates());

            //如果one和第一个Interval方向相反，坐标反转
            if (one.getNodeAId() == firstIntervalLine.getNodeBId() && one.getNodeBId() == firstIntervalLine.getNodeAId()) {
                Collections.reverse(coordinates);
            }

            //生成geo spatial json
            IntervalLineGeoSpatialVo geoSpatialVo = new IntervalLineGeoSpatialVo();
            geoSpatialVo.setId(one.getId());
            geoSpatialVo.setNodeAId(one.getNodeAId());
            geoSpatialVo.setNodeBId(one.getNodeBId());
            geoSpatialVo.setCoordinates(coordinates.toArray(new double[][]{}));

            one.setGeoSpatial(JsonUtil.toString(geoSpatialVo));
            //暂不更新到数据库
//            intervalLineDao.updateOne(one);
        }
    }

    /**
     * what:    获取平面两点间距离. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    private double distanceBetween(double[] nodeAEpsg3857, double[] nodeBEpsg3857) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point pointA = geometryFactory.createPoint(new Coordinate(nodeAEpsg3857[0], nodeAEpsg3857[1]));
        Point pointB = geometryFactory.createPoint(new Coordinate(nodeBEpsg3857[0], nodeBEpsg3857[1]));
        return pointA.distance(pointB);
    }

    /**
     * what:    NodeA和NodeB之间第一个IntervalLine中，用于计算Geo spatial的必要信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    protected static class FirstIntervalLine {
        /**
         * 第一个intervalLine的nodeA Id
         */
        private int nodeAId;
        /**
         * 第一个intervalLine的nodeB Id
         */
        private int nodeBId;

        /**
         * 第一个intervalLine的nodeA 坐标
         */
        private double[] nodeACoordinates;
        /**
         * 第一个intervalLine的nodeB 坐标
         */
        private double[] nodeBCoordinates;

        public int getNodeAId() {
            return nodeAId;
        }

        public void setNodeAId(int nodeAId) {
            this.nodeAId = nodeAId;
        }

        public int getNodeBId() {
            return nodeBId;
        }

        public void setNodeBId(int nodeBId) {
            this.nodeBId = nodeBId;
        }

        public double[] getNodeACoordinates() {
            return nodeACoordinates;
        }

        public void setNodeACoordinates(double[] nodeACoordinates) {
            this.nodeACoordinates = nodeACoordinates;
        }

        public double[] getNodeBCoordinates() {
            return nodeBCoordinates;
        }

        public void setNodeBCoordinates(double[] nodeBCoordinates) {
            this.nodeBCoordinates = nodeBCoordinates;
        }
    }
}
