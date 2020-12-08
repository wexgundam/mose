/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.tdms.core.modal;


import java.util.ArrayList;
import java.util.List;

/**
 * what:    地物的Json格式. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/12/1
 */
public class Feature {
    /**
     * 超图数据字段名——sm user id
     */
    public static final String FIELD_SM_USER_ID = "SMUSERID";

    /**
     * 字段名称集合
     */
    private List<String> fieldNames;
    /**
     * 字段值集合，顺序对应字段名称集合
     */
    private List<Object> fieldValues;
    /**
     * 集合图形
     */
    private Geometry geometry;

    /**
     * 添加字段名称及对应的值
     * 字段值当前仅支持字符串类型
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addFieldNameValue(String fieldName, Object fieldValue) {
        getFieldNames().add(fieldName);
        getFieldValues().add(fieldValue);
    }

    public List<String> getFieldNames() {
        fieldNames = fieldNames == null ? new ArrayList<>() : fieldNames;
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<Object> getFieldValues() {
        fieldValues = fieldValues == null ? new ArrayList<>() : fieldValues;
        return fieldValues;
    }

    public void setFieldValues(List<Object> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
