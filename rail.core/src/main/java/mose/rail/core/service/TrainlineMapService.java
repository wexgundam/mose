/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.core.service;

import mose.core.time.DateUtil;
import mose.rail.core.modal.Link;
import mose.rail.core.modal.Trainline;
import mose.rail.core.modal.TrainlineItem;
import mose.rail.core.modal.TrainlineMap;
import mose.rail.core.modal.TrainlineMapTableLeftHeaderItem;
import mose.rail.core.vo.TrainlineMapTrainlineVo;
import mose.rail.core.vo.TrainlineMapVo;
import mose.tile.vo.LineStringVo;
import mose.tile.vo.TextVo;
import org.apache.commons.lang3.ArrayUtils;
import org.locationtech.jts.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * what:    铁路列车运行图服务. <br/>
 * # 检测给定运行图与给定运行线是否相容. <br/>
 * # 检测给定运行图与给定径路是否存在重叠. <br/>
 * # 检测给定运行图是否经由给定位置. <br/>
 * # 获取给定运行图的图形包. <br/>
 * # 获取给定运行线对应给定运行图的图形包. <br/>
 * # 获取给定运行图经由的路局集合. <br/>
 * # 获取给定运行图的坐标X对应的时间. <br/>
 * # 获取给定运行图的时间对应的坐标X. <br/>
 * # 获取给定运行图的左表头项对应的坐标Y. <br/>
 * # 获取给定运行图的坐标Y对应的左表头项. <br/>
 * # 获取给定运行图的左表头项相对第一个左表头项的运行时分. <br/>
 * # 按条件查询一组径路. <br/>
 * # 按条件查询一个径路. <br/>
 * # 按Id查询一个径路	按Id查询一个径路. <br/>
 * # 获得所有径路	获得所有径路. <br/>
 * # 获取数量. <br/>
 * # 新增径路. <br/>
 * # 更新径路. <br/>
 * # 删除径路. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/5
 */
@Service
public class TrainlineMapService {
    /**
     * 定义日志输出位置
     */
    private Logger logger = LoggerFactory.getLogger("serviceLog");
    /**
     * 径路服务
     */
    @Autowired
    private RouteService routeService;
    /**
     * 运行线服务
     */
    @Autowired
    private TrainlineService trainlineService;


    /**
     * what:    获取给定运行图的图形包. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/12/26
     */
    public TrainlineMapVo getFeaturesVo(TrainlineMap trainlineMap) {
        List<LineStringVo> timeAxisLineStrings = new ArrayList<>();
        List<LineStringVo> timeAxisMinuteLineStrings = new ArrayList<>();
        List<LineStringVo> timeAxis10MinuteLineStrings = new ArrayList<>();
        List<LineStringVo> timeAxis30MinuteLineStrings = new ArrayList<>();
        List<LineStringVo> timeAxis60MinuteLineStrings = new ArrayList<>();
        List<TextVo> timeAxis60MinuteTexts = new ArrayList<>();
        List<TextVo> timeAxisDayTexts = new ArrayList<>();
        List<LineStringVo> tableTimeAxisLineStrings = new ArrayList<>();
        List<TextVo> tableLeftHeaderTexts = new ArrayList<>();

        trainlineMap.setTimeAxisMillisecondsSpace(0.00003);

        //时间轴长度
        double timeAxisLength = trainlineMap.getTimeAxisHourCount() * 60 * 60 * 1000 * trainlineMap.getTimeAxisMillisecondsSpace();
        //时间轴每分钟间隔
        double timeAxisMinutesSpace = 60 * 1000 * trainlineMap.getTimeAxisMillisecondsSpace();
        //表格Y坐标
        double tableTopY = trainlineMap.getTableLeftHeaderStartY();
        double tableBottomY = tableTopY + trainlineMap.getRuntimeCount() * trainlineMap.getRuntimesSpace();

        //左表头
        for (TrainlineMapTableLeftHeaderItem item : trainlineMap.getTableLeftHeaderItems()) {
            double y = item.getRelativeRuntime() * trainlineMap.getRuntimesSpace() + tableTopY;

            //左表头文本图形
            TextVo tableLeftHeaderItemTextVo = new TextVo();
            tableLeftHeaderItemTextVo.setText(item.getNetworkElementName());
            tableLeftHeaderItemTextVo.setX(trainlineMap.getTableLeftHeaderStartX());
            tableLeftHeaderItemTextVo.setY(y);
            tableLeftHeaderTexts.add(tableLeftHeaderItemTextVo);

            //绘制表格时间轴水平线图形
            LineStringVo tableTimeAxisLineString = new LineStringVo();
            tableTimeAxisLineString.setXys(new double[]{
                    trainlineMap.getTimeAxisStartX(),
                    y,
                    trainlineMap.getTimeAxisStartX() + timeAxisLength,
                    y});
            tableTimeAxisLineStrings.add(tableTimeAxisLineString);
        }

        //时间轴竖线步长
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(trainlineMap.getTimeAxisStartTime());
        int timeAxisStartHour = calendar.get(Calendar.HOUR_OF_DAY);

        int minuteStep = 0;
        switch (trainlineMap.getTimeAxisType()) {
            case 0:
                minuteStep = 2;
                break;
            case 1:
                minuteStep = 10;
                break;
            case 2:
                minuteStep = 30;
                break;
            case 3:
                minuteStep = 60;
                break;
        }

        //时间轴
        for (int hour = 0; hour < trainlineMap.getTimeAxisHourCount(); hour++) {
            //时间轴水平线，每小时一段
            LineStringVo timeAxisLineString = new LineStringVo();
            timeAxisLineString.setXys(new double[]{
                    trainlineMap.getTimeAxisStartX() + hour * 60 * timeAxisMinutesSpace,
                    trainlineMap.getTimeAxisStartY(),
                    trainlineMap.getTimeAxisStartX() + (hour + 1) * 60 * timeAxisMinutesSpace,
                    trainlineMap.getTimeAxisStartY()});
            timeAxisLineStrings.add(timeAxisLineString);

            for (int minute = 0; minute < 60; minute += minuteStep) {
                if (Math.floorMod(minute, 60) == 0) {
                    //时间轴分钟竖线
                    LineStringVo timeAxis60MinuteLineString = new LineStringVo();
                    timeAxis60MinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxis60MinuteLineStringHeight(),
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY()});
                    timeAxis60MinuteLineStrings.add(timeAxis60MinuteLineString);

                    //时间文本
                    TextVo timeAxis60MinuteTextVo = new TextVo();
                    timeAxis60MinuteTextVo.setText(Integer.toString(Math.floorMod(timeAxisStartHour + hour, 24)));
                    timeAxis60MinuteTextVo.setX(trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace);
                    timeAxis60MinuteTextVo.setY(trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxis60MinuteTextHeight());
                    timeAxis60MinuteTexts.add(timeAxis60MinuteTextVo);

                    //表格竖线
                    LineStringVo table60MinuteLineString = new LineStringVo();
                    table60MinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableTopY,
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableBottomY});
                    timeAxis60MinuteLineStrings.add(table60MinuteLineString);
                } else if (Math.floorMod(minute, 30) == 0) {
                    //时间轴分钟竖线
                    LineStringVo timeAxis30MinuteLineString = new LineStringVo();
                    timeAxis30MinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxis30MinuteLineStringHeight(),
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY()});
                    timeAxis30MinuteLineStrings.add(timeAxis30MinuteLineString);

                    //表格竖线
                    LineStringVo table30MinuteLineString = new LineStringVo();
                    table30MinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableTopY,
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableBottomY});
                    timeAxis30MinuteLineStrings.add(table30MinuteLineString);
                } else if (Math.floorMod(minute, 10) == 0) {
                    //时间轴分钟竖线
                    LineStringVo timeAxis10MinuteLineString = new LineStringVo();
                    timeAxis10MinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxis10MinuteLineStringHeight(),
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY()});
                    timeAxis10MinuteLineStrings.add(timeAxis10MinuteLineString);

                    //表格竖线
                    LineStringVo table10MinuteLineString = new LineStringVo();
                    table10MinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableTopY,
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableBottomY});
                    timeAxis10MinuteLineStrings.add(table10MinuteLineString);
                } else {
                    //时间轴分钟竖线
                    LineStringVo timeAxisMinuteLineString = new LineStringVo();
                    timeAxisMinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxisMinuteLineStringHeight(),
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            trainlineMap.getTimeAxisStartY()});
                    timeAxisMinuteLineStrings.add(timeAxisMinuteLineString);

                    //表格竖线
                    LineStringVo tableMinuteLineString = new LineStringVo();
                    tableMinuteLineString.setXys(new double[]{
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableTopY,
                            trainlineMap.getTimeAxisStartX() + (hour * 60 + minute) * timeAxisMinutesSpace,
                            tableBottomY});
                    timeAxisMinuteLineStrings.add(tableMinuteLineString);
                }
            }

            //日期文本
            if (Math.floorMod(timeAxisStartHour + hour, 6) == 0) {
                calendar.set(Calendar.HOUR_OF_DAY, timeAxisStartHour + hour);
                TextVo timeAxisDayTextVo = new TextVo();
                timeAxisDayTextVo.setText(DateUtil.dateToString(calendar.getTime(), "yyyy-MM-dd"));
                timeAxisDayTextVo.setX(trainlineMap.getTimeAxisStartX() + hour * 60 * timeAxisMinutesSpace);
                timeAxisDayTextVo.setY(trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxisDayTextHeight());
                timeAxisDayTexts.add(timeAxisDayTextVo);
            }
        }

        //时间轴最后面的60分钟线
        LineStringVo timeAxis60MinuteLineString = new LineStringVo();
        timeAxis60MinuteLineString.setXys(new double[]{
                trainlineMap.getTimeAxisStartX() + timeAxisLength,
                trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxis60MinuteLineStringHeight(),
                trainlineMap.getTimeAxisStartX() + timeAxisLength,
                trainlineMap.getTimeAxisStartY()});
        timeAxis60MinuteLineStrings.add(timeAxis60MinuteLineString);

        TextVo timeAxis60MinuteTextVo = new TextVo();
        timeAxis60MinuteTextVo.setText(Integer.toString(Math.floorMod(timeAxisStartHour + trainlineMap.getTimeAxisHourCount(), 24)));
        timeAxis60MinuteTextVo.setX(trainlineMap.getTimeAxisStartX() + timeAxisLength);
        timeAxis60MinuteTextVo.setY(trainlineMap.getTimeAxisStartY() + trainlineMap.getTimeAxis60MinuteTextHeight());
        timeAxis60MinuteTexts.add(timeAxis60MinuteTextVo);


        //时间轴最后面的60分钟线 表格竖线
        LineStringVo table60MinuteLineString = new LineStringVo();
        table60MinuteLineString.setXys(new double[]{
                trainlineMap.getTimeAxisStartX() + timeAxisLength,
                tableTopY,
                trainlineMap.getTimeAxisStartX() + timeAxisLength,
                tableBottomY});
        timeAxis60MinuteLineStrings.add(table60MinuteLineString);

        //图形包
        TrainlineMapVo trainlineMapVo = new TrainlineMapVo();
        trainlineMapVo.setTimeAxisLineStrings(timeAxisLineStrings);
        trainlineMapVo.setTimeAxisMinuteLineStrings(timeAxisMinuteLineStrings);
        trainlineMapVo.setTimeAxis10MinuteLineStrings(timeAxis10MinuteLineStrings);
        trainlineMapVo.setTimeAxis30MinuteLineStrings(timeAxis30MinuteLineStrings);
        trainlineMapVo.setTimeAxis60MinuteLineStrings(timeAxis60MinuteLineStrings);
        trainlineMapVo.setTimeAxis60MinuteTexts(timeAxis60MinuteTexts);
        trainlineMapVo.setTimeAxisDayTexts(timeAxisDayTexts);
        trainlineMapVo.setTableTimeAxisLineStrings(tableTimeAxisLineStrings);
        trainlineMapVo.setTableTimeAxisLineStrings(tableTimeAxisLineStrings);
        trainlineMapVo.setTableLeftHeaderTexts(tableLeftHeaderTexts);

        return trainlineMapVo;
    }

    /**
     * what:    获取给定运行线对应给定运行图的图形包. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/12/26
     */
    public TrainlineMapTrainlineVo getFeaturesVo(TrainlineMap trainlineMap, Trainline trainline) {
        Vector<Double> vector = new Vector<>();

        //计算运行图与运行线径路重叠部分
        List<Link> intersection = routeService.getIntersection(trainlineMap.getRouteAnchorPointsString(), trainline.getRouteAnchorPointsString());

        //通常情况下，运行图的左表头项数量要小于运行线时刻数量
        //所以采用遍历运行图左表头项，如果在径路重叠区域内，再查询列车运行线时刻
        //根据以上信息创建图形对象
        for (TrainlineMapTableLeftHeaderItem trainlineMapTableLeftHeaderItem : trainlineMap.getTableLeftHeaderItems()) {
            if (routeService.route(intersection, trainlineMapTableLeftHeaderItem.getNetworkElementBasePointString())) {
                //获取给定运行线中给定路网元素对应的时刻
                TrainlineItem trainlineItem = trainlineService.getTrainlineItem(trainline, trainlineMapTableLeftHeaderItem.getNetworkElementType(), trainlineMapTableLeftHeaderItem.getNetworkElementId());
                if (trainlineItem != null) {
                    //根据时刻获取横坐标
                    double x = getX(trainlineMap, trainlineItem.getTime());
                    //根据位置获取纵坐标
                    double y = getY(trainlineMap, trainlineMapTableLeftHeaderItem);
                    vector.add(x);
                    vector.add(y);
                }
            }
        }

        LineStringVo lineStringVo = new LineStringVo();
        double[] xys = ArrayUtils.toPrimitive(vector.toArray(new Double[0]));
        lineStringVo.setXys(xys);
        List<LineStringVo> lineStringVos = new ArrayList<>();
        lineStringVos.add(lineStringVo);
        TrainlineMapTrainlineVo vo = new TrainlineMapTrainlineVo();
        vo.setLineStrings(lineStringVos);
        return vo;
    }

    /**
     * what:    获取给定时间的起始坐标X. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/11/8
     */

    public double getX(TrainlineMap trainlineMap, Date time) {
        //给定时间与运行图开始时间间隔毫秒数
        long interval = time.getTime() - trainlineMap.getTimeAxisStartTime().getTime();
        //根据毫米数据计算x
        double x = trainlineMap.getTimeAxisStartX() + interval * trainlineMap.getTimeAxisMillisecondsSpace();
        return x;
    }

    /**
     * what:    获取给定运行图元素的起始坐标Y. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/11/8
     */
    public double getY(TrainlineMap trainlineMap, TrainlineMapTableLeftHeaderItem item) {
        double y = trainlineMap.getTableLeftHeaderStartY() + item.getRelativeRuntime() * trainlineMap.getRuntimesSpace();
        return y;
    }
}
