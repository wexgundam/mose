/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.traindiagram.service;

import mose.CommonConfiguration;
import mose.core.file.FileUtil;
import mose.core.json.JsonUtil;
import mose.core.time.TimeUtil;
import mose.traindiagram.modal.IntervalLineMapping;
import mose.traindiagram.modal.NodeMapping;
import mose.traindiagram.modal.TrainDiagram;
import mose.traindiagram.vo.TrainSpatialVo;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * what:    测试运行图服务. <br/>
 * # 获取运行图包含的节点集合. <br/>
 * # 更新运行图绘图坐标. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = {
        CommonConfiguration.class, TestTrainDiagramService.class
})
@PropertySource({"classpath:application-database.properties"})
@ImportResource({
        "classpath:/spring/applicationContext-common.xml",
//        "classpath:/spring/applicationContext-database.xml"
})
@ComponentScan(basePackages = "mose.traindiagram", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {TrainDiagramService.class})
})
public class TestTrainDiagramService {
    @Autowired
    private TrainDiagramService trainDiagramService;

    /**
     * what:    更新运行图地理信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    @Test
    public void testUpdateGeoSpatial() {
        //模拟运行图数据 京沪高铁
        TrainDiagram trainDiagram = new TrainDiagram();
        //运行图
        //获取给定运行图包含的nodes
        trainDiagram.setNodeMappings(getNodeMappings());
        //获取给定运行图包含的IntervalLines
        trainDiagram.setIntervalLineMappings(getIntervalLineMappings());
        //更新绘图信息
        trainDiagramService.updateSpatial(trainDiagram);

        //写文件
        String trainDiagramFilePath = "D:\\develop\\workspace\\upload\\file\\traindiagram.json";
        FileUtil.delete(trainDiagramFilePath);
        FileUtil.writeFile(trainDiagramFilePath, trainDiagram.getSpatial());


        //开始时间
        Calendar calendar = TimeUtil.INSTANCE.truncate(Calendar.getInstance(), Calendar.DATE);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        Date trainDiagramStartTime = calendar.getTime();

        //运行线
        List<TrainSpatialVo> trainSpatialVos = getTrainSpatialVos(trainDiagram, trainDiagramStartTime);

        //写文件
        String trainDiagramTrainFilePath = "D:\\develop\\workspace\\upload\\file\\traindiagramtrain.json";
        FileUtil.delete(trainDiagramTrainFilePath);
        FileUtil.writeFile(trainDiagramTrainFilePath, JsonUtil.toString(trainSpatialVos));
    }

    private List<IntervalLineMapping> getIntervalLineMappings() {
        List<IntervalLineMapping> intervalLineMappings = new ArrayList<>();
        return intervalLineMappings;
    }

    private List<NodeMapping> getNodeMappings() {
        List<NodeMapping> nodeMappings = new ArrayList<>();
        int order = 0;
        double interval = 200d;
        NodeMapping nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("北京南高速场");
        nodeMapping.setInterval(0d);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("廊坊");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("京津线路所");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("津沪线路所");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("天津南");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("沧州西");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("德州东京沪场");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("济南西");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("崔马庄线路所");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("泰安");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("曲阜东");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("滕州东");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("枣庄");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("徐州东");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("宿州东");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("蚌埠南");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("定远");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("滁州");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("扬州线路所");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("南京南京沪场");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("秦淮河线路所");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("镇江南");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("丹阳北");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("常州北");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("无锡东");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("苏州北");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("昆山南京沪场");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("黄渡线路所");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        nodeMapping = new NodeMapping();
        nodeMapping.setId(order);
        nodeMapping.setOrder(order++);
        nodeMapping.setNodeId(nodeMapping.getId());
        nodeMapping.setText("上海虹桥高速场");
        nodeMapping.setInterval(interval);
        nodeMappings.add(nodeMapping);
        return nodeMappings;
    }

    /**
     * what:    重点测试运行图性能. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    private List<TrainSpatialVo> getTrainSpatialVos(TrainDiagram trainDiagram, Date trainDiagramStartTime) {
        List<TrainSpatialVo> trainSpatialVos = new ArrayList<>();

        for (int index = 0; index < 196; index++) {
            //每列车间隔10分钟发车
            Date trainStartTime = TimeUtil.INSTANCE.addMinutes(trainDiagramStartTime, index * 10);
            trainSpatialVos.add(getTrainSpatialVo(trainDiagram, trainDiagramStartTime, trainStartTime, true));
            trainSpatialVos.add(getTrainSpatialVo(trainDiagram, trainDiagramStartTime, trainStartTime, false));
        }

        return trainSpatialVos;
    }


    /**
     * what:    获取运行线绘图信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2022/3/16
     */
    private TrainSpatialVo getTrainSpatialVo(TrainDiagram trainDiagram, Date trainDiagramStartTime, Date trainStartTime, boolean isDown) {
        TrainSpatialVo trainSpatialVo = new TrainSpatialVo();

        Map<Integer, Double> nodeMappingCoordinateYs = trainDiagramService.getNodeMappingCoordinateYs(trainDiagram);

        List<double[][]> trainLineStrings = new ArrayList<>();
        List<TrainSpatialVo.TrainText> trainTexts = new ArrayList<>();

        int firstIndex = isDown ? 0 : getNodeMappings().size() - 1;
        int lastIndex = isDown ? getNodeMappings().size() - 1 : 0;
        int step = isDown ? 1 : -1;

        NodeMapping firstNodeMapping = trainDiagram.getNodeMappings().get(firstIndex);
        Date previousTime = trainStartTime;
        double previousCoordinateX = trainDiagramService.getCoordinateX(trainDiagram, trainDiagramStartTime, trainStartTime);
        double previousCoordinateY = nodeMappingCoordinateYs.get(firstNodeMapping.getId());

        //始发标志
        double delta = isDown ? 20 : -20;
        trainLineStrings.add(new double[][]{{previousCoordinateX, previousCoordinateY}, {previousCoordinateX, previousCoordinateY + delta}});
        trainLineStrings.add(new double[][]{{previousCoordinateX - 10, previousCoordinateY + delta}, {previousCoordinateX + 10, previousCoordinateY + delta}});

        //车次
        delta = isDown ? 30 : -30;
        TrainSpatialVo.TrainText trainNumberText = new TrainSpatialVo.TrainText();
        trainNumberText.setCoordinate(new double[]{previousCoordinateX, previousCoordinateY + delta});
        trainNumberText.setText("GXX");
        trainTexts.add(trainNumberText);

        int deltaMinute = 5;
        for (int index = firstIndex + step; isLast(isDown, lastIndex, index); index += step) {
            NodeMapping nodeMapping = trainDiagram.getNodeMappings().get(index);
            //模拟区间运行时间逐级增加
            Date time = TimeUtil.INSTANCE.addMinutes(previousTime, deltaMinute);

            //当前坐标
            double coordinateX = trainDiagramService.getCoordinateX(trainDiagram, trainDiagramStartTime, time);
            double coordinateY = nodeMappingCoordinateYs.get(nodeMapping.getId());
            trainLineStrings.add(new double[][]{{previousCoordinateX, previousCoordinateY}, {coordinateX, coordinateY}});

            //运行时分 暂时不加
            //TrainSpatialVo.TrainText text = new TrainSpatialVo.TrainText();
            //text.setText();

            previousCoordinateX = coordinateX;
            previousCoordinateY = coordinateY;

            previousTime = time;
            deltaMinute = (deltaMinute % 10 == 0) ? 5 : ++deltaMinute;
        }

        //终到标志
        delta = isDown ? -20 : 20;
        int delta2 = isDown ? -30 : 30;
        trainLineStrings.add(new double[][]{{previousCoordinateX, previousCoordinateY}, {previousCoordinateX, previousCoordinateY + delta}});
        trainLineStrings.add(new double[][]{{previousCoordinateX - 10, previousCoordinateY + delta}, {previousCoordinateX + 10, previousCoordinateY + delta}});
        trainLineStrings.add(new double[][]{{previousCoordinateX, previousCoordinateY + delta2}, {previousCoordinateX - 10, previousCoordinateY + delta}});
        trainLineStrings.add(new double[][]{{previousCoordinateX, previousCoordinateY + delta2}, {previousCoordinateX + 10, previousCoordinateY + delta}});

        trainSpatialVo.getTrainTexts().addAll(trainTexts);
        trainSpatialVo.getTrainLineStrings().addAll(trainLineStrings);

        return trainSpatialVo;
    }

    private boolean isLast(boolean isDown, int lastIndex, int index) {
        return isDown ? index < (lastIndex + 1) : index > lastIndex - 1;
    }

//    G1	1	北京南高速场	-1.0000	9.0000	XII	1
//    G1	1	廊坊	9.1730	9.1730	Ⅰ	2
//    G1	1	京津线路所	9.2605	9.2605	Ⅰ
//    G1	1	津沪线路所	9.2730	9.2730	Ⅰ
//    G1	1	天津南	9.2840	9.2840	Ⅰ	5
//    G1	1	沧州西	9.4430	9.4430	I	6
//    G1	1	德州东京沪场	10.0240	10.0240	Ⅴ	7
//    G1	1	济南西	10.2250	10.2450	6	8
//    G1	1	崔马庄线路所	10.3025	10.3025	 	9
//    G1	1	泰安	10.3835	10.3835	Ⅰ	10
//    G1	1	曲阜东	10.5100	10.5100	Ⅰ	11
//    G1	1	滕州东	11.0055	11.0055	Ⅰ	12
//    G1	1	枣庄	11.0720	11.0720	Ⅰ	13
//    G1	1	徐州东	11.1835	11.1835	Ⅰ	14
//    G1	1	宿州东	11.3020	11.3020	Ⅰ	15
//    G1	1	蚌埠南	11.4555	11.4555	Ⅰ	16
//    G1	1	定远	11.5540	11.5540	Ⅰ	17
//    G1	1	滁州	12.0700	12.0700	Ⅰ	18
//    G1	1	扬州线路所	12.1050	12.1050	Ⅰ	19
//    G1	1	南京南京沪场	12.2355	12.2555	7	20
//    G1	1	秦淮河线路所	12.2845	12.2845	Ⅰ	21
//    G1	1	镇江南	12.4110	12.4110	Ⅰ	22
//    G1	1	丹阳北	12.4620	12.4620	Ⅰ	23
//    G1	1	常州北	12.5205	12.5205	Ⅰ	24
//    G1	1	无锡东	13.0210	13.0210	Ⅰ	25
//    G1	1	苏州北	13.0655	13.0655	Ⅰ	26
//    G1	1	昆山南京沪场	13.1340	13.1340	Ⅰ	27
//    G1	1	黄渡线路所	13.2040	13.2040	Ⅰ	28
//    G1	1	上海虹桥高速场	13.2800	-1.0000	11	29


//    G2	2	上海虹桥高速场	-1.0000	9.0000	 	12
//    G2	2	黄渡线路所	9.0620	9.0620	 	13
//    G2	2	昆山南京沪场	9.1220	9.1220	Ⅱ	14
//    G2	2	苏州北	9.1805	9.1805	Ⅱ	15
//    G2	2	无锡东	9.2250	9.2250	 	16
//    G2	2	常州北	9.3255	9.3255	Ⅱ	17
//    G2	2	丹阳北	9.3840	9.3840	Ⅱ	18
//    G2	2	镇江南	9.4350	9.4350	Ⅱ	19
//    G2	2	秦淮河线路所	9.5715	9.5715	 	20
//    G2	2	南京南京沪场	10.0005	10.0205	 	21
//    G2	2	扬州线路所	10.1340	10.1340	 	22
//    G2	2	滁州	10.1700	10.1700	Ⅱ	23
//    G2	2	定远	10.2820	10.2820	Ⅱ	24
//    G2	2	蚌埠南	10.3805	10.3805	Ⅱ	25
//    G2	2	宿州东	10.5340	10.5340	 	26
//    G2	2	徐州东	11.0525	11.0525	Ⅱ	27
//    G2	2	枣庄	11.1640	11.1640	Ⅱ	28
//    G2	2	滕州东	11.2305	11.2305	Ⅱ	29
//    G2	2	曲阜东	11.3300	11.3300	 	30
//    G2	2	泰安	11.4525	11.4525	 	31
//    G2	2	崔马庄线路所	11.5335	11.5335	 	32
//    G2	2	济南西	11.5910	12.0110	13	33
//    G2	2	德州东京沪场	12.2120	12.2120	Ⅳ	34
//    G2	2	沧州西	12.3930	12.3930	II	35
//    G2	2	天津南	12.5620	12.5620	Ⅱ	36
//    G2	2	津沪线路所	12.5730	12.5730	Ⅱ	37
//    G2	2	京津线路所	12.5855	12.5855	Ⅱ	38
//    G2	2	廊坊	13.0830	13.0830	Ⅱ	39
//    G2	2	北京南高速场	13.2800	-1.0000	XV	40
}
