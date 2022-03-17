/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.traindiagram.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    列车运行线绘图vo Spatial Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
public class TrainSpatialVo {
    /**
     * id
     */
    private int id;
    /**
     * 绘制列车运行线 为Multi line string
     */
    private List<double[][]> trainLineStrings;
    /**
     * 绘制运text，包括车次、运行时分等
     */
    private List<TrainText> trainTexts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<double[][]> getTrainLineStrings() {
        trainLineStrings = trainLineStrings == null ? new ArrayList<>() : trainLineStrings;
        return trainLineStrings;
    }

    public void setTrainLineStrings(List<double[][]> trainLineStrings) {
        this.trainLineStrings = trainLineStrings;
    }

    public List<TrainText> getTrainTexts() {
        trainTexts = trainTexts == null ? new ArrayList<>() : trainTexts;
        return trainTexts;
    }

    public void setTrainTexts(List<TrainText> trainTexts) {
        this.trainTexts = trainTexts;
    }

    /**
     * what:    列车运文本，包括车次、运行时分等. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    public static class TrainText {
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
