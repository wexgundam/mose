/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.tdms.service;

import mose.tdms.dao.BureauDao;
import mose.tdms.modal.Bureau;
import mose.tdms.vo.BureauSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * what:    调度视角的铁路局服务. <br/>
 * # 查询所有路局. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
@Service
public class BureauService {
    /**
     * 路局数据获取对象
     */
    @Autowired
    private BureauDao bureauDao;

    /**
     * what:    检测两个路局是否邻接. <br/>
     * 通过GridService判断网格间关系. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Bureau> getAll() {
        return bureauDao.getAll();
    }

    /**
     * 返回总数
     *
     * @return
     */
    public int getCount() {
        return bureauDao.getCount(new BureauSearchVo());
    }
}
