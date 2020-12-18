/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:rail
 */
package mose.tdms.service;

import mose.tdms.dao.StationDao;
import mose.tdms.modal.Station;
import mose.tdms.modal.Station;
import mose.tdms.vo.StationSearchVo;
import mose.tdms.vo.StationSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * what:    调度视角的车站服务. <br/>
 * # 检测两个车站是否邻接. <br/>
 * # 检测给定车站是否管辖给定车场. <br/>
 * # 按条件查询一组车站. <br/>
 * # 按条件查询一个车站. <br/>
 * # 按Id查询一个车站. <br/>
 * # 获取全路车站. <br/>
 * # 获取全路的路局分界口车站. <br/>
 * # 获取两个路局的路局分界口车站. <br/>
 * # 获取给定路局所辖车站. <br/>
 * # 获取给定路局所辖路局分界口车站. <br/>
 * # 获取给定行车调度台所辖车站. <br/>
 * # 获取给定车站的邻接车站. <br/>
 * # 获得给定节点间的邻接车站. <br/>
 * # 获取给定车场的邻接车站. <br/>
 * # 获得给定车场的管辖站. <br/>
 * # 获得数量. <br/>
 * # 设置给定车站的管辖信息. <br/>
 * # 新增车站. <br/>
 * # 更新车站. <br/>
 * # 删除车站. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/12
 */
@Service
public class StationService {
    /**
     * 定义日志输出位置
     */
    private Logger logger = LoggerFactory.getLogger("serviceLog");
    /**
     * 路局服务
     */
    @Autowired
    private BureauService bureauService;
    /**
     * 车务段服务
     */
    @Autowired
    private TrainoperationDepotService trainoperationDepotService;
    /**
     * 行车调度台服务
     */
    @Autowired
    private TrainlineDepotService trainlineDepotService;
    /**
     * 车站数据获取对象
     */
    @Autowired
    private StationDao stationDao;

    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Station getOne(int id) {
        StationSearchVo searchVo = new StationSearchVo();
        searchVo.setIdEqual(id);
        return stationDao.getOne(searchVo);
    }

    /**
     * what:    查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public List<Station> getMany(StationSearchVo searchVo) {
        return stationDao.getMany(searchVo);
    }


    /**
     * what:    增加. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Station addOne(Station one) {
        int id = stationDao.addOne(one);
        return getOne(id);
    }

    /**
     * what:    更新. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Station updateOne(Station one) {
        stationDao.updateOne(one);
        return getOne(one.getId());
    }

    /**
     * what:    核对. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Station verifyOne(Station one) {
        stationDao.verifyOne(one);
        return getOne(one.getId());
    }

    /**
     * what:    删除. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public void deleteOne(Station one) {
        stationDao.deleteOne(one);
    }
}
