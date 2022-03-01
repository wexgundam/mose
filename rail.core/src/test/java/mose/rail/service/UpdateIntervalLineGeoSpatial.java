/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.service;

import mose.CommonConfiguration;
import mose.core.file.FileUtil;
import mose.core.gis.GisUtil;
import mose.core.json.JsonUtil;
import mose.rail.dao.BureauDao;
import mose.rail.dao.IntervalLineDao;
import mose.rail.dao.NodeDao;
import mose.rail.dao.RailwayLineDao;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.Node;
import mose.rail.modal.RailwayLine;
import mose.rail.vo.IntervalLineGeoSpatialVo;
import mose.rail.vo.IntervalLineSearchVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, UpdateIntervalLineGeoSpatial.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.rail", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BureauDao.class, IntervalLineDao.class, NodeDao.class, RailwayLineDao.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BureauService.class, IntervalLineService.class, NodeService.class, RailwayLineService.class})
})
public class UpdateIntervalLineGeoSpatial {
    @Autowired
    private RailwayLineService railwayLineService;
    @Autowired
    private RailwayLineDao railwayLineDao;
    @Autowired
    private IntervalLineService intervalLineService;
    @Autowired
    private IntervalLineDao intervalLineDao;
    @Autowired
    private NodeDao nodeDao;
    int railwayLineId = 813;

    /**
     * what:    每次处理一条铁路线. <br/>
     * 获取铁路线的所有Interval Lines
     * 仅限该铁路线内计算相同node间多条线路算法
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    @Test
    public void updateData() {
        //获取铁路线
        RailwayLine railwayLine = railwayLineService.getOne(railwayLineId);

        //获取铁路线包含的IntervalLines
        IntervalLineSearchVo intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setRailwayLineIdEqual(railwayLine.getId());
        List<IntervalLine> many = intervalLineDao.getMany(intervalLineSearchVo);

        //分组，相同node间的interval line为一组
        Map<IntervalLine, List<IntervalLine>> map = toMap(many);

        for (List<IntervalLine> intervalLines : map.values()) {
            //获取排序第一的IntervalLine的必要信息
            IntervalLineService.FirstIntervalLine firstIntervalLine = intervalLineService.toFirstIntervalLine(intervalLines.get(0));

            //计算各个IntervalLine对应的地理信息
            intervalLineService.updateGeoSpatial(intervalLines, firstIntervalLine);
        }

        //临时json对象
        RailwayLineJson railwayLineJson = new RailwayLineJson();
        Map<Integer, Node> nodeMap = getNodeMap();
        Map<Integer, NodeJson> nodeJsons = new HashMap<>();
        for (IntervalLine one : many) {
            IntervalLineGeoSpatialVo geoSpatialVo = JsonUtil.toObject(one.getGeoSpatial(), IntervalLineGeoSpatialVo.class);
            //获取interval line地理信息
            railwayLineJson.getLineStrings().add(geoSpatialVo.getCoordinates());

            if (!nodeJsons.containsKey(one.getNodeAId())) {
                NodeJson nodeJson = createNodeJson(nodeMap.get(one.getNodeAId()));
                nodeJsons.put(nodeJson.getId(), nodeJson);
            }
            if (!nodeJsons.containsKey(one.getNodeBId())) {
                NodeJson nodeJson = createNodeJson(nodeMap.get(one.getNodeBId()));
                nodeJsons.put(nodeJson.getId(), nodeJson);
            }

            /**
             *更新到数据库
             */
//            intervalLineDao.updateOne(one);
        }


        //railwayLine
        String railwayLineFilePath = "D:\\develop\\workspace\\upload\\file\\railwayline.json";
        FileUtil.delete(railwayLineFilePath);
        FileUtil.writeFile(railwayLineFilePath, JsonUtil.toString(railwayLineJson));
        //node
        String nodeFilePath = "D:\\develop\\workspace\\upload\\file\\nodes.json";
        FileUtil.delete(nodeFilePath);
        FileUtil.writeFile(nodeFilePath, JsonUtil.toString(nodeJsons.values()));
    }

    private Map<IntervalLine, List<IntervalLine>> toMap(List<IntervalLine> many) {
        Map<IntervalLine, List<IntervalLine>> map = new HashMap<>();
        Map<String, List<IntervalLine>> abMap = new HashMap<>();
        for (IntervalLine intervalLine : many) {
            String abId = Integer.toString(intervalLine.getNodeAId()) + Integer.toString(intervalLine.getNodeBId());
            String baId = Integer.toString(intervalLine.getNodeBId()) + Integer.toString(intervalLine.getNodeAId());
            List<IntervalLine> intervalLines = null;
            if (abMap.containsKey(abId)) {
                intervalLines = abMap.get(abId);
            } else if (abMap.containsKey(baId)) {
                intervalLines = abMap.get(baId);
            } else {
                intervalLines = new ArrayList<>();

                abMap.put(abId, intervalLines);
                abMap.put(baId, intervalLines);

                map.put(intervalLine, intervalLines);
            }
            intervalLines.add(intervalLine);
        }
        return map;
    }

    private class RailwayLineJson {
        private List<double[][]> lineStrings;

        public List<double[][]> getLineStrings() {
            lineStrings = lineStrings == null ? new ArrayList<>() : lineStrings;
            return lineStrings;
        }
    }

    private Map<Integer, Node> getNodeMap() {
        Map<Integer, Node> nodes = new HashMap<>();
        for (Node node : nodeDao.getAll()) {
            nodes.put(node.getId(), node);
        }
        return nodes;
    }

    private NodeJson createNodeJson(Node node) {
        NodeJson nodeJson = new NodeJson();
        nodeJson.setId(node.getId());
        nodeJson.setName(node.getName());
        nodeJson.setCoordinates(GisUtil.INSTANCE.epsg4326ToEpsg3857(node.getEpsg4326()));
        nodeJson.setRadius(node.getRadius());
        return nodeJson;
    }

    private class NodeJson {
        private int id;
        private String name;
        private double[] coordinates;
        private double radius;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCoordinates(double[] coordinates) {
            this.coordinates = coordinates;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double[] getCoordinates() {
            return coordinates;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }
    }

    public int getRailwayLineId() {
        return railwayLineId;
    }

    public void setRailwayLineId(int railwayLineId) {
        this.railwayLineId = railwayLineId;
    }
}
