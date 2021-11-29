/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:
 */
package mose.rail.service;

import mose.rail.dao.BureauDao;
import mose.rail.modal.Bureau;
import mose.rail.vo.BureauSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * what:    调度视角的铁路局服务. <br/>
 * # 检测两个路局是否邻接. <br/>
 * # 检测给定路局是否是管辖给定行车调度台. <br/>
 * # 检测给定路局是否是管辖给定车站. <br/>
 * # 检车给定路局是否是给定区段的管辖局之一. <br/>
 * # 按条件查询一组路局. <br/>
 * # 按条件查询一个路局. <br/>
 * # 按Id查询一个路局. <br/>
 * # 获取全路路局. <br/>
 * # 获取给定路局的邻接路局. <br/>
 * # 获取给定行车调度台的管辖局. <br/>
 * # 获取给定车站的管辖局. <br/>
 * # 获取给定区段的所有管辖局. <br/>
 * # 获取数量. <br/>
 * # 新增路局. <br/>
 * # 更新路局. <br/>
 * # 删除路局. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/11
 */
@Service
public class BureauService {
    @Autowired
    private BureauDao bureauDao;

    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public Bureau getOne(Integer id) {
        BureauSearchVo bureauSearchVo = new BureauSearchVo();
        bureauSearchVo.setIdEqual(id);
        return bureauDao.getOne(bureauSearchVo);
    }
}
