/**
 * Copyright 2021 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail.service;

import mose.rail.dao.NodeDao;
import mose.rail.modal.Node;
import mose.rail.vo.NodeSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * what:    节点service. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2021/9/22
 */
@Service
public class NodeService {
    @Autowired
    private NodeDao nodeDao;

    /**
     * what:    根据Id查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2021/9/22
     */
    public Node getOne(Integer id) {
        NodeSearchVo nodeSearchVo = new NodeSearchVo();
        nodeSearchVo.setIdEqual(id);
        return nodeDao.getOne(nodeSearchVo);
    }
}
