package mose.tdms.controller; /**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */

import mose.core.json.JsonUtil;
import mose.core.web.WebUtil;
import mose.tdms.modal.Line;
import mose.tdms.service.LineService;
import mose.tdms.vo.LineSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * what:    分界口控制器. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RestController
@RequestMapping("/tdms/line")
public class LineController {
    @Autowired
    private LineService lineService;

    /**
     * what:    查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    @RequestMapping(value = "/many")
    public void getMany(@RequestParam(value = "stationid", required = false) Integer stationId,
                        @RequestParam(value = "stationsid", required = false) Integer[] stationsId,
                        @RequestParam(value = "verified", required = false) Boolean verified,
                        HttpServletRequest request, HttpServletResponse response) {
        LineSearchVo searchVo = new LineSearchVo();
        searchVo.setStationIdEqual(stationId);
        searchVo.setStationsIdEqual(stationsId);
        searchVo.setVerified(verified);
        List<Line> depots = lineService.getMany(searchVo);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", depots);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }

    /**
     * what:    增加. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    @RequestMapping(value = "/one", method = RequestMethod.POST)
    public void addOne(@RequestBody Line one, HttpServletRequest request, HttpServletResponse response) {
        Line addedOne = lineService.addOne(one);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", addedOne);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }

    /**
     * what:    更新. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    @RequestMapping(value = "/one", method = RequestMethod.PUT)
    public void updateOne(@RequestBody Line one, HttpServletRequest request, HttpServletResponse response) {
        Line updatedOne = lineService.updateOne(one);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", updatedOne);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }


    /**
     * what:    核对. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    @RequestMapping(value = "/one", method = RequestMethod.PATCH)
    public void verifyOne(@RequestBody Line one, HttpServletRequest request, HttpServletResponse response) {
        Line updatedOne = lineService.verifyOne(one);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", updatedOne);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }

    /**
     * what:    删除. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    @RequestMapping(value = "/one", method = RequestMethod.DELETE)
    public void deleteOne(@RequestBody Line one, HttpServletRequest request, HttpServletResponse response) {
        lineService.deleteOne(one);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }
}
