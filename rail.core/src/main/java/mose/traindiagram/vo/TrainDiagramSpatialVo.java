/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.traindiagram.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    列车运行图绘图vo Spatial Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class TrainDiagramSpatialVo {
    /**
     * id
     */
    private int id;
    /**
     * 绘制时间线 为Multi line string
     */
    private List<double[][]> timeLineLineStrings;
    /**
     * 绘制时间线text
     */
    private List<TimeLineText> timeLineTexts;
    /**
     * 绘制节点text
     */
    private List<NodeText> nodeTexts;
    /**
     * 绘制j节点线 为Multi line string
     */
    private List<double[][]> nodeLineLineStrings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<double[][]> getTimeLineLineStrings() {
        timeLineLineStrings = timeLineLineStrings == null ? new ArrayList<>() : timeLineLineStrings;
        return timeLineLineStrings;
    }

    public void setTimeLineLineStrings(List<double[][]> timeLineLineStrings) {
        this.timeLineLineStrings = timeLineLineStrings;
    }

    public List<TimeLineText> getTimeLineTexts() {
        timeLineTexts = timeLineTexts == null ? new ArrayList<>() : timeLineTexts;
        return timeLineTexts;
    }

    public void setTimeLineTexts(List<TimeLineText> timeLineTexts) {
        this.timeLineTexts = timeLineTexts;
    }

    public List<double[][]> getNodeLineLineStrings() {
        nodeLineLineStrings = nodeLineLineStrings == null ? new ArrayList<>() : nodeLineLineStrings;
        return nodeLineLineStrings;
    }

    public void setNodeLineLineStrings(List<double[][]> nodeLineLineStrings) {
        this.nodeLineLineStrings = nodeLineLineStrings;
    }

    public List<NodeText> getNodeTexts() {
        nodeTexts = nodeTexts == null ? new ArrayList<>() : nodeTexts;
        return nodeTexts;
    }

    public void setNodeTexts(List<NodeText> nodeTexts) {
        this.nodeTexts = nodeTexts;
    }

    /**
     * what:    时间线文本. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    public static class TimeLineText {
        private double[] coordinate;
        private String text;

        public double[] getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(double[] coordinate) {
            this.coordinate = coordinate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * what:    节点文本. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    public static class NodeText {
        private double[] coordinate;
        private String text;

        public double[] getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(double[] coordinate) {
            this.coordinate = coordinate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
