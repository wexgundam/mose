package mose.sys.web;


import mose.core.model.ComboboxVO;
import mose.core.spring.SpringContextHolder;
import mose.sys.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成下拉框的列表
 *
 * @autho mose
 * @date 2017/7/17.
 */
public class ComboboxUtil {

    /**
     * 生成下拉框列表
     *
     * @param comboType
     *
     * @return
     */
    public static List<ComboboxVO> createComboboxList(String comboType) {
        List<ComboboxVO> list = new ArrayList<>();//定义下拉框
        switch (comboType) {
            case "sysUser"://系统用户，日志查询用到
                SysUserService sysUserService = SpringContextHolder.getBean("sysUserService");
                list.addAll(sysUserService.listAllUser());
                break;
        }
        return list;
    }

}
