/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.modal;

import java.util.Date;

/**
 * what:    审计. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/12/11
 */
public interface IAudit {
    /**
     * what:    获取创建人Id，只在保存新增数据时填写，以后不可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public int getCreatorId();

    /**
     * what:    设置创建人Id，只在保存新增数据时填写，以后不可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public void setCreatorId(int creatorId);

    /**
     * what:    获取创建人实名，只在保存新增数据时填写，以后不可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public String getCreatorRealName();

    /**
     * what:    设置创建人实名，只在保存新增数据时填写，以后不可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public void setCreatorRealName(String creatorRealName);

    /**
     * what:    获取创建时间，只在保存新增数据时填写，以后不可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public Date getCreatedAt();

    /**
     * what:    设置创建时间，只在保存新增数据时填写，以后不可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public void setCreatedAt(Date createdAt);

    /**
     * what:    获取最后修改人Id，在新增和更新数据时填写，修改后保存时可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public int getLastEditorId();

    /**
     * what:    设置最后修改人Id，在新增和更新数据时填写，修改后保存时可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public void setLastEditorId(int lastEditorId);

    /**
     * what:    获取最后修改人实名，在新增和更新数据时填写，修改后保存时可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public String getLastEditorRealName();

    /**
     * what:    设置最后修改人实名，在新增和更新数据时填写，修改后保存时可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public void setLastEditorRealName(String lastEditorRealName);

    /**
     * what:    获取最后修改时间，在新增和更新数据时填写，修改后保存时可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public Date getLastEditedAt();

    /**
     * what:    设置最后修改时间，在新增和更新数据时填写，修改后保存时可更改. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/3
     */
    public void setLastEditedAt(Date lastEditedAt);
}
