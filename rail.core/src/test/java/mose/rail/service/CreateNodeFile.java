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
import mose.rail.modal.Node;
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
import java.util.List;

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
        CommonConfiguration.class, CreateNodeFile.class
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
public class CreateNodeFile {
    @Autowired
    private NodeDao nodeDao;

    /**
     * what:    生成node json文件. <br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    @Test
    public void createData() {
        //获取nodes
        List<Node> all = nodeDao.getAll();

        //临时json对象
        List<NodeJson> jsons = new ArrayList<>();
        for (Node one : all) {
            System.out.println(one);
            NodeJson nodeJson = createNodeJson(one);
            jsons.add(nodeJson);
        }

        //node file
        String nodeFilePath = "D:\\develop\\workspace\\upload\\file\\nodes.json";
        FileUtil.delete(nodeFilePath);
        FileUtil.writeFile(nodeFilePath, JsonUtil.toString(jsons));
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


        @Override
        public String toString() {
            return "NodeJson{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + Arrays.toString(coordinates) +
                    ", radius=" + radius +
                    '}';
        }

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
}
