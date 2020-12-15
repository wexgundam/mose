package mose.tdms.controller; /**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */

import mose.core.restful.RestfulUtil;
import mose.tdms.service.BureauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * what:    路局控制器. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/11
 */
@RestController
@RequestMapping("/tdms/bureau")
public class BureauController {
    @Autowired
    private BureauService bureauService;

    @RequestMapping(value = "/entries.json", method = RequestMethod.GET)
    public ModelMap query() {
        ModelMap map = new ModelMap();
        RestfulUtil.INSTANCE.setupModelMap(map, bureauService.getAll(), bureauService.getCount());
        return map;
    }
}
