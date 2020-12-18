/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.vo;


import mose.tile.vo.LineStringVo;
import mose.tile.vo.TextVo;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    列车运行图的图形包Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/6
 */
public class TrainlineMapVo {
    /**
     * 时间轴轴线图形
     */
    private List<LineStringVo> timeAxisLineStrings;
    /**
     * 时间轴其它分钟线图形
     */
    private List<LineStringVo> timeAxisMinuteLineStrings;
    /**
     * 时间轴10分钟线图形
     */
    private List<LineStringVo> timeAxis10MinuteLineStrings;
    /**
     * 时间轴30分钟线图形
     */
    private List<LineStringVo> timeAxis30MinuteLineStrings;
    /**
     * 时间轴60分钟线图形
     */
    private List<LineStringVo> timeAxis60MinuteLineStrings;
    /**
     * 时间轴日期文本图形
     */
    private List<TextVo> timeAxisDayTexts;
    /**
     * 时间轴小时文本图形
     */
    private List<TextVo> timeAxis60MinuteTexts;
    /**
     * 表格时间轴线图形
     */
    private List<LineStringVo> tableTimeAxisLineStrings;
    /**
     * 左表头文本图形
     */
    private List<TextVo> tableLeftHeaderTexts;
    /**
     * 运行线图形包
     */
    private List<TrainlineMapTrainlineVo> trainlines;

    public List<TextVo> getTableLeftHeaderTexts() {
        tableLeftHeaderTexts = tableLeftHeaderTexts == null ? new ArrayList<>() : tableLeftHeaderTexts;
        return tableLeftHeaderTexts;
    }

    public void setTableLeftHeaderTexts(List<TextVo> tableLeftHeaderTexts) {
        this.tableLeftHeaderTexts = tableLeftHeaderTexts;
    }

    public List<LineStringVo> getTableTimeAxisLineStrings() {
        tableTimeAxisLineStrings = tableTimeAxisLineStrings == null ? new ArrayList<>() : tableTimeAxisLineStrings;
        return tableTimeAxisLineStrings;
    }

    public void setTableTimeAxisLineStrings(List<LineStringVo> tableTimeAxisLineStrings) {
        this.tableTimeAxisLineStrings = tableTimeAxisLineStrings;
    }

    public List<LineStringVo> getTimeAxisLineStrings() {
        timeAxisLineStrings = timeAxisLineStrings == null ? new ArrayList<>() : timeAxisLineStrings;
        return timeAxisLineStrings;
    }

    public void setTimeAxisLineStrings(List<LineStringVo> timeAxisLineStrings) {
        this.timeAxisLineStrings = timeAxisLineStrings;
    }

    public List<LineStringVo> getTimeAxis60MinuteLineStrings() {
        timeAxis60MinuteLineStrings = timeAxis60MinuteLineStrings == null ? new ArrayList<>() : timeAxis60MinuteLineStrings;
        return timeAxis60MinuteLineStrings;
    }

    public void setTimeAxis60MinuteLineStrings(List<LineStringVo> timeAxis60MinuteLineStrings) {
        this.timeAxis60MinuteLineStrings = timeAxis60MinuteLineStrings;
    }

    public List<LineStringVo> getTimeAxis10MinuteLineStrings() {
        timeAxis10MinuteLineStrings = timeAxis10MinuteLineStrings == null ? new ArrayList<>() : timeAxis10MinuteLineStrings;
        return timeAxis10MinuteLineStrings;
    }

    public void setTimeAxis10MinuteLineStrings(List<LineStringVo> timeAxis10MinuteLineStrings) {
        this.timeAxis10MinuteLineStrings = timeAxis10MinuteLineStrings;
    }

    public List<LineStringVo> getTimeAxis30MinuteLineStrings() {
        timeAxis30MinuteLineStrings = timeAxis30MinuteLineStrings == null ? new ArrayList<>() : timeAxis30MinuteLineStrings;
        return timeAxis30MinuteLineStrings;
    }

    public void setTimeAxis30MinuteLineStrings(List<LineStringVo> timeAxis30MinuteLineStrings) {
        this.timeAxis30MinuteLineStrings = timeAxis30MinuteLineStrings;
    }

    public List<LineStringVo> getTimeAxisMinuteLineStrings() {
        timeAxisMinuteLineStrings = timeAxisMinuteLineStrings == null ? new ArrayList<>() : timeAxisMinuteLineStrings;
        return timeAxisMinuteLineStrings;
    }

    public void setTimeAxisMinuteLineStrings(List<LineStringVo> timeAxisMinuteLineStrings) {
        this.timeAxisMinuteLineStrings = timeAxisMinuteLineStrings;
    }

    public List<TextVo> getTimeAxisDayTexts() {
        timeAxisDayTexts = timeAxisDayTexts == null ? new ArrayList<>() : timeAxisDayTexts;
        return timeAxisDayTexts;
    }

    public void setTimeAxisDayTexts(List<TextVo> timeAxisDayTexts) {
        this.timeAxisDayTexts = timeAxisDayTexts;
    }

    public List<TextVo> getTimeAxis60MinuteTexts() {
        timeAxis60MinuteTexts = timeAxis60MinuteTexts == null ? new ArrayList<>() : timeAxis60MinuteTexts;
        return timeAxis60MinuteTexts;
    }

    public void setTimeAxis60MinuteTexts(List<TextVo> timeAxis60MinuteTexts) {
        this.timeAxis60MinuteTexts = timeAxis60MinuteTexts;
    }

    public List<TrainlineMapTrainlineVo> getTrainlines() {
        trainlines = trainlines == null ? new ArrayList<>() : trainlines;
        return trainlines;
    }

    public void setTrainlines(List<TrainlineMapTrainlineVo> trainlines) {
        this.trainlines = trainlines;
    }
}
