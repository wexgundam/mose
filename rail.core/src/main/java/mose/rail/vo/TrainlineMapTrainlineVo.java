/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.vo;


import mose.tile.vo.LineStringVo;

import java.util.ArrayList;
import java.util.List;

/**
 * what:    列车运行线对应列车运行图的图形包Vo. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/6
 */
public class TrainlineMapTrainlineVo {
    /**
     * 线图形
     */
    private List<LineStringVo> lineStrings;

    public List<LineStringVo> getLineStrings() {
        lineStrings = lineStrings == null ? new ArrayList<>() : lineStrings;
        return lineStrings;
    }

    public void setLineStrings(List<LineStringVo> lineStrings) {
        this.lineStrings = lineStrings;
    }
}
