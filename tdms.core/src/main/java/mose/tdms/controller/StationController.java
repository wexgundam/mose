package mose.tdms.controller; /**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */

import mose.core.json.JsonUtil;
import mose.core.web.WebUtil;
import mose.tdms.modal.Bureau;
import mose.tdms.modal.Station;
import mose.tdms.modal.Station;
import mose.tdms.service.BureauService;
import mose.tdms.service.StationService;
import mose.tdms.vo.StationSearchVo;
import mose.tdms.vo.StationSearchVo;
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
 * what:    车站控制器. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RestController
@RequestMapping("/tdms/station")
public class StationController {
    @Autowired
    private StationService stationService;

    /**
     * what:    查询. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    @RequestMapping(value = "/many")
    public void getMany(@RequestParam(value = "textlike", required = false) String textLike,
                        @RequestParam(value = "bureauidequal", required = false) Integer bureauIdEqual,
                        @RequestParam(value = "verified", required = false) Boolean verified,
                        HttpServletRequest request, HttpServletResponse response) {
        StationSearchVo searchVo = new StationSearchVo();
        searchVo.setTextLike(textLike);
        searchVo.setBureauIdEqual(bureauIdEqual);
        searchVo.setVerified(verified);
        List<Station> depots = stationService.getMany(searchVo);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", depots);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }


    /**
     * what:    根据输入查询车站，名字、拼音模糊匹配方式. <br/>
     * parameter方式
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2020/12/17
     */
    //    @RequestMapping(value = "/many")
    public void getMany(StationSearchVo stationSearchVo, HttpServletRequest request, HttpServletResponse response) {
        List<Station> stations = stationService.getMany(stationSearchVo);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("data", stations);
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
    public void addOne(@RequestBody Station one, HttpServletRequest request, HttpServletResponse response) {
        Station addedOne = stationService.addOne(one);
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
    public void updateOne(@RequestBody Station one, HttpServletRequest request, HttpServletResponse response) {
        Station updatedOne = stationService.updateOne(one);
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
    public void verifyOne(@RequestBody Station one, HttpServletRequest request, HttpServletResponse response) {
        Station updatedOne = stationService.verifyOne(one);
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
    public void deleteOne(@RequestBody Station one, HttpServletRequest request, HttpServletResponse response) {
        stationService.deleteOne(one);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }
}
