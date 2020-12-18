/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:rail
 */
package mose.tdms.service;

import mose.tdms.dao.FenjiekouDao;
import mose.tdms.modal.Fenjiekou;
import mose.tdms.vo.FenjiekouSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * what:    调度视角的分界口服务. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/12
 */
@Service
public class FenjiekouService {
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
    private FenjiekouDao fenjiekouDao;

    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Fenjiekou getOne(int id) {
        FenjiekouSearchVo searchVo = new FenjiekouSearchVo();
        searchVo.setIdEqual(id);
        return fenjiekouDao.getOne(searchVo);
    }

    /**
     * what:    查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public List<Fenjiekou> getMany(FenjiekouSearchVo searchVo) {
        return fenjiekouDao.getMany(searchVo);
    }


    /**
     * what:    增加. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Fenjiekou addOne(Fenjiekou one) {
        int id = fenjiekouDao.addOne(one);
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
    public Fenjiekou updateOne(Fenjiekou one) {
        fenjiekouDao.updateOne(one);
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
    public Fenjiekou verifyOne(Fenjiekou one) {
        fenjiekouDao.verifyOne(one);
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
    public void deleteOne(Fenjiekou one) {
        fenjiekouDao.deleteOne(one);
    }
}
