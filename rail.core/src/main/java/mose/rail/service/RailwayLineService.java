/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.rail.service;

import mose.rail.dao.RailwayLineDao;
import mose.rail.modal.RailwayLine;
import mose.rail.vo.IntervalLineSearchVo;
import mose.rail.vo.RailwayLineSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * what:    铁路线服务. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
@Service
public class RailwayLineService {
    @Autowired
    private RailwayLineDao railwayLineDao;
    @Autowired
    private IntervalLineService intervalLineService;

    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public RailwayLine getOne(int id) {
        RailwayLineSearchVo railwayLineSearchVo = new RailwayLineSearchVo();
        railwayLineSearchVo.setIdEqual(id);
        return railwayLineDao.getOne(railwayLineSearchVo);
    }

    /**
     * what:    更新给定铁路线的地理空间信息. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public void updateGeoSpacial(RailwayLine railwayLine) {
        //查询给定railwayLine包含的所有IntervalLine
        IntervalLineSearchVo intervalLineSearchVo = new IntervalLineSearchVo();
        intervalLineSearchVo.setRailwayLineIdEqual(railwayLine.getId());
        //TODO 可以使用多线程
//        for (IntervalLine intervalLine : intervalLineDao.getMany(intervalLineSearchVo)) {
//            intervalLineService.updateGeoSpatial(intervalLine);
//        }
    }
}
