/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import mose.tdms.dao.TrainoperationDepotDao;
import mose.tdms.modal.TrainoperationDepot;
import mose.tdms.modal.TrainoperationDepot;
import mose.tdms.vo.TrainoperationDepotSearchVo;
import mose.tdms.vo.TrainoperationDepotSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * what:    调度视角的车务段服务. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/12
 */
@Service
public class TrainoperationDepotService {
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
     * 车务段数据对象
     */
    @Autowired
    private TrainoperationDepotDao trainoperationDepotDao;


    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public TrainoperationDepot getOne(int id) {
        TrainoperationDepotSearchVo searchVo = new TrainoperationDepotSearchVo();
        searchVo.setIdEqual(id);
        return trainoperationDepotDao.getOne(searchVo);
    }

    /**
     * what:    查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public List<TrainoperationDepot> getMany(TrainoperationDepotSearchVo searchVo) {
        return trainoperationDepotDao.getMany(searchVo);
    }


    /**
     * what:    增加. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public TrainoperationDepot addOne(TrainoperationDepot one) {
        int id = trainoperationDepotDao.addOne(one);
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
    public TrainoperationDepot updateOne(TrainoperationDepot one) {
        trainoperationDepotDao.updateOne(one);
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
    public TrainoperationDepot verifyOne(TrainoperationDepot one) {
        trainoperationDepotDao.verifyOne(one);
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
    public void deleteOne(TrainoperationDepot one) {
        trainoperationDepotDao.deleteOne(one);
    }
}
