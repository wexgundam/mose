/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import mose.tdms.dao.LineDao;
import mose.tdms.modal.Line;
import mose.tdms.vo.LineSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * what:    连线服务. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/18
 */
@Service
public class LineService {
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
     * 车站服务
     */
    @Autowired
    private StationService stationService;
    /**
     * 车站数据获取对象
     */
    @Autowired
    private LineDao lineDao;

    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Line getOne(int id) {
        LineSearchVo searchVo = new LineSearchVo();
        searchVo.setIdEqual(id);
        return lineDao.getOne(searchVo);
    }

    /**
     * what:    查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public List<Line> getMany(LineSearchVo searchVo) {
        return lineDao.getMany(searchVo);
    }


    /**
     * what:    增加. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    public Line addOne(Line one) {
        int id = lineDao.addOne(one);
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
    public Line updateOne(Line one) {
        lineDao.updateOne(one);
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
    public Line verifyOne(Line one) {
        lineDao.verifyOne(one);
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
    public void deleteOne(Line one) {
        lineDao.deleteOne(one);
    }
}
