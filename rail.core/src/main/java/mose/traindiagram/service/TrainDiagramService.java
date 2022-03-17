/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.traindiagram.service;

import mose.core.json.JsonUtil;
import mose.core.time.DateUtil;
import mose.core.time.TimeUtil;
import mose.traindiagram.modal.IntervalLineMapping;
import mose.traindiagram.modal.NodeMapping;
import mose.traindiagram.modal.TrainDiagram;
import mose.traindiagram.vo.TrainDiagramSpatialVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    运行图服务. <br/>
 * # 获取运行图包含的节点集合. <br/>
 * # 更新运行图绘图信息. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
@Service
public class TrainDiagramService {
    /**
     * what:    更新运行图绘图信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public void updateSpatial(TrainDiagram trainDiagram) {
        //绘图vo
        TrainDiagramSpatialVo spatialVo = new TrainDiagramSpatialVo();

        //获取给定运行图包含的nodes
        List<NodeMapping> nodeMappings = trainDiagram.getNodeMappings();

        //时间轴坐标 水平线
        double timeBarStartCoordinateX = trainDiagram.getTimeBarStartCoordinateX();
        double timeBarCoordinateY = trainDiagram.getTimeBarStartCoordinateY();
        double timeBarEndCoordinateX = timeBarStartCoordinateX + trainDiagram.getHours() * 60 * 60 * trainDiagram.getSecondRatio();
        //区间域总高度
        double intervalLineAreaHeight = trainDiagram.getIntervlLineAreaHeight();

        /**计算绘图坐标**/
        //time bar绘图信息 垂直线
        List<double[][]> timeBarLineStrings = new ArrayList<>();
        timeBarLineStrings.add(new double[][]{{timeBarStartCoordinateX, timeBarCoordinateY}, {timeBarEndCoordinateX, timeBarCoordinateY}});
        spatialVo.getTimeLineLineStrings().addAll(timeBarLineStrings);

        //time line绘图信息 垂直线
        List<double[][]> timeLineLineStrings = new ArrayList<>();
        List<TrainDiagramSpatialVo.TimeLineText> timeLineTexts = new ArrayList<>();
        for (int index = 0; index < trainDiagram.getTimeLineCount(); index++) {
            //分钟增量
            int deltaMinutes = index * trainDiagram.getMinutes();
            //当前time line对应分钟（1~59）还是小时
            boolean isHour = (deltaMinutes) % 60 == 0;
            double deltaX = index * trainDiagram.getTimeLineInterval();
            double deltaY = isHour ? trainDiagram.getHourLineHeight() : trainDiagram.getMinuteLineHeight();

            //时间轴对应的时间线 lineString
            double timeLineCoordinateX = timeBarStartCoordinateX + deltaX;
            double timeLineStartCoordinateY = timeBarCoordinateY;
            double timeLineEndCoordinateY = timeBarCoordinateY + deltaY;
            timeLineLineStrings.add(new double[][]{
                    {timeLineCoordinateX, timeLineStartCoordinateY},
                    {timeLineCoordinateX, timeLineEndCoordinateY}
            });

            //时间在区间线路区域的垂直线 lineString
            double intervalLineAreaTimeLineStartCoordinateY = timeBarCoordinateY - trainDiagram.getTimeBar2IntervalLineArea();
            double intervalLineAreaTimeLineEndCoordinateY = intervalLineAreaTimeLineStartCoordinateY - intervalLineAreaHeight;
            timeLineLineStrings.add(new double[][]{
                    {timeLineCoordinateX, intervalLineAreaTimeLineStartCoordinateY},
                    {timeLineCoordinateX, intervalLineAreaTimeLineEndCoordinateY}
            });

            //时间线Text
            double timeLineTextCoordinateY = timeLineEndCoordinateY;
            String text = Integer.toString((new Double((isHour ? ((trainDiagram.getStartHour() + Math.floor(deltaMinutes / 60)) % 24) : (deltaMinutes % 60)))).intValue());
            TrainDiagramSpatialVo.TimeLineText timeLineText = new TrainDiagramSpatialVo.TimeLineText();
            timeLineText.setText(text);
            timeLineText.setCoordinate(new double[]{timeLineCoordinateX, timeLineTextCoordinateY});
            timeLineTexts.add(timeLineText);
        }

        //节点text
        List<TrainDiagramSpatialVo.NodeText> nodeTexts = new ArrayList<>();
        //节点线
        List<double[][]> nodeLineLineStrings = new ArrayList<>();
        //节点域横向坐标
        double nodeTextCoordinateX = timeBarStartCoordinateX - trainDiagram.getNodeArea2IntervalLineArea();
        //节点域纵向坐标集合
        Map<Integer, Double> nodeMappingCoordinateYs = getNodeMappingCoordinateYs(trainDiagram);
        for (NodeMapping mapping : nodeMappings) {
            //节点对应纵向坐标
            double nodeCoordinateY = nodeMappingCoordinateYs.get(mapping.getId());
            //节点text
            TrainDiagramSpatialVo.NodeText nodeText = new TrainDiagramSpatialVo.NodeText();
            nodeText.setCoordinate(new double[]{nodeTextCoordinateX, nodeCoordinateY});
            nodeText.setText(mapping.getText());
            nodeTexts.add(nodeText);

            //节点在区间线路区域的水平线
            nodeLineLineStrings.add(new double[][]{
                    {timeBarStartCoordinateX, nodeCoordinateY},
                    {timeBarEndCoordinateX, nodeCoordinateY}
            });
        }

        spatialVo.getTimeLineLineStrings().addAll(timeLineLineStrings);
        spatialVo.getTimeLineTexts().addAll(timeLineTexts);
        spatialVo.getNodeTexts().addAll(nodeTexts);
        spatialVo.getNodeLineLineStrings().addAll(nodeLineLineStrings);

        trainDiagram.setSpatial(JsonUtil.toString(spatialVo));
    }

    /**
     * what:    获取给定运行图NodeMapping纵坐标Y的集合. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    public Map<Integer, Double> getNodeMappingCoordinateYs(TrainDiagram trainDiagram) {
        Map<Integer, Double> coordinateYs = new HashMap<>();
        double interval = 0;
        double nodeMappingAreaStartCoordinateY = trainDiagram.getTimeBarStartCoordinateY() - trainDiagram.getTimeBar2IntervalLineArea();
        for (NodeMapping mapping : trainDiagram.getNodeMappings()) {
            interval += mapping.getInterval();
            double nodeCoordinateY = nodeMappingAreaStartCoordinateY - interval;

            coordinateYs.put(mapping.getId(), nodeCoordinateY);
        }
        return coordinateYs;
    }

    /**
     * what:    根据给定条件获取对应额横向坐标X. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    public double getCoordinateX(TrainDiagram trainDiagram, Date trainDiagramStartTime, Date time) {
        long seconds = (time.getTime() - trainDiagramStartTime.getTime()) / 1000;
        double coordinateX = trainDiagram.getTimeBarStartCoordinateX() + seconds * trainDiagram.getSecondRatio();
        return coordinateX;
    }
}
