/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.service;

import mose.CommonConfiguration;
import mose.rail.dao.BureauDao;
import mose.rail.dao.IntervalLineDao;
import mose.rail.dao.NodeDao;
import mose.rail.dao.RailwayLineDao;
import mose.rail.modal.IntervalLine;
import mose.rail.modal.OldIntervalLine;
import mose.rail.modal.RailwayLine;
import mose.rail.vo.IntervalLineSearchVo;
import mose.rail.vo.RailwayLineSearchVo;
import org.junit.Assert;
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
        CommonConfiguration.class, RectorFromOldData.class
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
public class RectorFromOldData {
    @Autowired
    private IntervalLineDao intervalLineDao;
    @Autowired
    private RailwayLineDao railwayLineDao;
//    private String railwayLineName = "卫安";
//    private String railwayLineName = "南环";
//    private String railwayLineName = "清潞";
//    private String railwayLineName = "三万";//站顺有问题
    private String railwayLineName = "三万";//站顺有问题

    @Test
    public void testCore() {
        RailwayLineSearchVo railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setNameEqual(railwayLineName);
        RailwayLine railwayLine = railwayLineDao.getOne(railwayLineSearchVo);
        List<OldIntervalLine> oldMany = intervalLineDao.getOldIntervalLines(railwayLine.getName());

        List<IntervalLine> intervalLines = new ArrayList<>();
        //算法是从第一条记录向下寻找，所以不用计算最后一条记录
        for (OldIntervalLine currentOldOne : oldMany.subList(0, oldMany.size() - 1)) {
            intervalLines.addAll(createFromOld(railwayLine, oldMany, currentOldOne));
        }
        List<String> keys = new ArrayList<>();
        for (IntervalLine intervalLine : intervalLines) {
            String key = intervalLine.getNodeAId() + intervalLine.getDirection();
            if (!keys.contains(key)) {
                intervalLineDao.addOne(intervalLine);
                keys.add(key);
            }
        }
    }

    private List<IntervalLine> createFromOld(RailwayLine railwayLine, List<OldIntervalLine> oldMany, OldIntervalLine currentOldOne) {
        OldIntervalLine getNextOldOne = getNextOldOne(oldMany, currentOldOne);

        if (getNextOldOne == null) {
            System.out.println(currentOldOne);
        }

        List<IntervalLine> intervalLines = createIntervalLines(railwayLine, currentOldOne, getNextOldOne);

        return intervalLines;
    }

    private OldIntervalLine getNextOldOne(List<OldIntervalLine> oldMany, OldIntervalLine currentOldOne) {
        for (int index = oldMany.indexOf(currentOldOne) + 1; index < oldMany.size(); index++) {
            OldIntervalLine nextOldOne = oldMany.get(index);
            if (!currentOldOne.getNodeId().equals(nextOldOne.getNodeId())) {
                return nextOldOne;
            }
        }
        return null;
    }

    private List<IntervalLine> createIntervalLines(RailwayLine railwayLine, OldIntervalLine currentOldOne, OldIntervalLine nextOldOne) {
        List<IntervalLine> intervalLines = new ArrayList<>();
        if ("双".equals(currentOldOne.getrD())) {
            intervalLines.add(createIntervalLine(railwayLine, currentOldOne, nextOldOne, "双上"));
            intervalLines.add(createIntervalLine(railwayLine, currentOldOne, nextOldOne, "双下"));
        } else if ("上".equals(currentOldOne.getrD())) {
            intervalLines.add(createIntervalLine(railwayLine, currentOldOne, nextOldOne, "上"));
        } else if ("下".equals(currentOldOne.getrD())) {
            intervalLines.add(createIntervalLine(railwayLine, currentOldOne, nextOldOne, "下"));
        } else if ("单".equals(currentOldOne.getrD())) {
            intervalLines.add(createIntervalLine(railwayLine, currentOldOne, nextOldOne, "单"));
        } else {//三、四、五...等线路
            intervalLines.add(createIntervalLine(railwayLine, currentOldOne, nextOldOne, currentOldOne.getrD()));
        }
        return intervalLines;
    }

    private IntervalLine createIntervalLine(RailwayLine railwayLine, OldIntervalLine currentOldOne, OldIntervalLine nextOldOne, String direction) {
        IntervalLine intervalLine = new IntervalLine();
        intervalLine.setRailwayLineId(railwayLine.getId());
        intervalLine.setNodeAId(currentOldOne.getNodeId());
        intervalLine.setNodeAMileage(currentOldOne.getrSck());
        intervalLine.setNodeBId(nextOldOne.getNodeId());
        intervalLine.setNodeBMileage(nextOldOne.getrSck());
        intervalLine.setDirection(direction);
        return intervalLine;
    }
}
