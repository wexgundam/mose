/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail2.vo;

import mose.core.json.JsonUtil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * @author mose created on 2020/5/27
 */
public class CreateNetworkDataFile {
    @Test
    public void testDo() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("nodeViews.json");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);
        NodeView[] nodeViews = JsonUtil.toObject(json, NodeView[].class);

        G6Data g6Data = createData(nodeViews);
        json = JsonUtil.toString(g6Data);
        FileOutputStream outputStream = new FileOutputStream("D://G6Data.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
    }

    private G6Data createData(NodeView[] nodeViews) {
//        double minX = 0;
//        double maxX = 0;
//        double minY = 0;
//        double maxY = 0;
//        for (NodeView nodeView : nodeViews) {
//            minX = Math.min(nodeView.getX(), minX);
//            maxX = Math.max(nodeView.getX(), maxX);
//            minY = Math.min(nodeView.getY(), minY);
//            maxY = Math.max(nodeView.getY(), maxY);
//        }
        double centerX = 2949.083333292;
        double centerY = 6238.8697917151;
        G6Data g6Data = new G6Data();
        for (NodeView nodeView : nodeViews) {
            nodeView.setX(nodeView.getX() - centerX);
            nodeView.setY(-(nodeView.getY() - centerY));
            g6Data.getNodes().add(createNode(nodeView));
        }
        return g6Data;
    }

    private Object createNode(NodeView nodeView) {
        Map<String, Object> node = new HashMap<>();
        node.put("id", nodeView.getId());
        node.put("x", nodeView.getX());
        node.put("y", nodeView.getY());
        node.put("label", nodeView.getName());
        node.put("size", 10);

//        Map<String, Object> style = new HashMap<>();
//        style.put("stroke", "black");
//        style.put("fill", "blue");
//        style.put("fillOpacity", 0.3);
//        node.put("style", style);
//
//        Map<String, Object> stateStyles = new HashMap<>();
//        Map<String, Object> hover = new HashMap<>();
//        hover.put("fill", "lightsteelblue");
//        stateStyles.put("hover", hover);
//        Map<String, Object> selected = new HashMap<>();
//        selected.put("stroke", "green");
//        selected.put("lineWidth", 3);
//        stateStyles.put("selected", selected);
//        node.put("stateStyles", stateStyles);
        return node;
    }

    private static class G6Data {
        private List<Object> nodes;
        private List<Object> edges;

        public List<Object> getNodes() {
            nodes = nodes == null ? new ArrayList<>() : nodes;
            return nodes;
        }

        public void setNodes(List<Object> nodes) {
            this.nodes = nodes;
        }

        public List<Object> getEdges() {
            edges = edges == null ? new ArrayList<>() : edges;
            return edges;
        }

        public void setEdges(List<Object> edges) {
            this.edges = edges;
        }
    }
}
