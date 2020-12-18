package mose.tdms.controller; /**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */

import mose.core.json.JsonUtil;
import mose.core.web.WebUtil;
import mose.tdms.modal.Station;
import mose.tdms.modal.TrainoperationDepot;
import mose.tdms.modal.TrainoperationDepot;
import mose.tdms.service.TrainoperationDepotService;
import mose.tdms.vo.StationSearchVo;
import mose.tdms.vo.TrainoperationDepotSearchVo;
import mose.tdms.vo.TrainoperationDepotSearchVo;
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
 * what:    车务段控制器. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RestController
@RequestMapping("/tdms/trainoperationdepot")
public class TrainoperationDepotController {
    @Autowired
    private TrainoperationDepotService trainoperationDepotService;

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
        TrainoperationDepotSearchVo searchVo = new TrainoperationDepotSearchVo();
        searchVo.setTextLike(textLike);
        searchVo.setBureauIdEqual(bureauIdEqual);
        searchVo.setVerified(verified);
        List<TrainoperationDepot> depots = trainoperationDepotService.getMany(searchVo);
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
    public void addOne(@RequestBody TrainoperationDepot one, HttpServletRequest request, HttpServletResponse response) {
        TrainoperationDepot addedOne = trainoperationDepotService.addOne(one);
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
    public void updateOne(@RequestBody TrainoperationDepot one, HttpServletRequest request, HttpServletResponse response) {
        TrainoperationDepot updatedOne = trainoperationDepotService.updateOne(one);
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
    public void verifyOne(@RequestBody TrainoperationDepot one, HttpServletRequest request, HttpServletResponse response) {
        TrainoperationDepot updatedOne = trainoperationDepotService.verifyOne(one);
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
    public void deleteOne(@RequestBody TrainoperationDepot one, HttpServletRequest request, HttpServletResponse response) {
        trainoperationDepotService.deleteOne(one);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        String json = JsonUtil.toString(modelMap);
        WebUtil.out(response, json);
    }
}
