/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.vo;

import mose.core.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/10/12
 */
public class WriteJsonFile {
    @Test
    public void testWrite() throws IOException {
        NodeView nodeView = new NodeView();
        nodeView.setId("test");
        String json = JsonUtil.toString(nodeView);
        FileOutputStream outputStream = new FileOutputStream("D://test.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
        Assert.assertNotNull(nodeView);
    }
}
