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
import java.util.Arrays;
import java.util.Collections;
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
        CommonConfiguration.class, CreateIntervalLineFile.class
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
public class CreateIntervalLineFile {
    @Autowired
    private IntervalLineService intervalLineService;
    @Autowired
    private IntervalLineDao intervalLineDao;
    @Autowired
    private NodeDao nodeDao;

    /**
     * what:    生成Interval Line数据文件. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    @Test
    public void createData() {
        //获取所有IntervalLines
        List<IntervalLine> all = intervalLineDao.getAll();

        //分组，相同node间的interval line为一组
        Map<IntervalLine, List<IntervalLine>> map = toMap(all);

        //拓扑区间
        createToplogic(all, map);

        //逻辑区间
        createLogic(map);
    }

    private void createToplogic(List<IntervalLine> all, Map<IntervalLine, List<IntervalLine>> map) {
        for (IntervalLine intervalLine : map.keySet()) {
            //获取排序第一的IntervalLine的必要信息
            IntervalLineService.FirstIntervalLine firstIntervalLine = intervalLineService.toFirstIntervalLine(intervalLine);

            //计算各个IntervalLine对应的地理信息
            List<IntervalLine> intervalLines = map.get(intervalLine);
            intervalLineService.updateGeoSpatial(intervalLines, firstIntervalLine);
        }

        //临时json对象
        RailwayLineJson railwayLineJson = new RailwayLineJson();
        for (IntervalLine one : all) {
            System.out.println(one.getId());
            IntervalLineGeoSpatialVo geoSpatialVo = JsonUtil.toObject(one.getGeoSpatial(), IntervalLineGeoSpatialVo.class);
            //获取interval line地理信息
            railwayLineJson.getLineStrings().add(geoSpatialVo.getCoordinates());
        }

        //railwayLine
        String railwayLineFilePath = "D:\\develop\\workspace\\upload\\file\\railwayline.json";
        FileUtil.delete(railwayLineFilePath);
        FileUtil.writeFile(railwayLineFilePath, JsonUtil.toString(railwayLineJson));
    }

    /**
     * 逻辑区间
     *
     * @param map
     */
    private void createLogic(Map<IntervalLine, List<IntervalLine>> map) {
        //临时json对象
        RailwayLineJson railwayLineJson = new RailwayLineJson();
        for (IntervalLine one : map.keySet()) {
            System.out.println(one.getId());
            //            //获取排序第一的IntervalLine的必要信息
            IntervalLineService.FirstIntervalLine firstIntervalLine = intervalLineService.toFirstIntervalLine(one);

            //计算IntervalLine对应的地理信息
            //计算连线坐标，该坐标可生成一个包含4组坐标的LineString。
            List<double[]> coordinates = new ArrayList<>();
            coordinates.add(firstIntervalLine.getNodeACoordinates());
            coordinates.add(firstIntervalLine.getNodeBCoordinates());

            //生成geo spatial json
            IntervalLineGeoSpatialVo geoSpatialVo = new IntervalLineGeoSpatialVo();
            geoSpatialVo.setId(one.getId());
            geoSpatialVo.setNodeAId(one.getNodeAId());
            geoSpatialVo.setNodeBId(one.getNodeBId());
            geoSpatialVo.setCoordinates(coordinates.toArray(new double[][]{}));

            one.setGeoSpatial(JsonUtil.toString(geoSpatialVo));

            //获取interval line地理信息
            railwayLineJson.getLineStrings().add(geoSpatialVo.getCoordinates());
        }

        //railwayLine
        String intervalLineFilePath = "D:\\develop\\workspace\\upload\\file\\intervallines.json";
        FileUtil.delete(intervalLineFilePath);
        FileUtil.writeFile(intervalLineFilePath, JsonUtil.toString(railwayLineJson));
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
}
