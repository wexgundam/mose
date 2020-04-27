/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/spring/applicationContext-common.xml",
        "/spring/applicationContext-package.xml",
        "/spring/applicationContext-database.xml",
})
public class TestConfiguration {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {

    }

    @Test
    public void test() {
        jdbcTemplate.execute("select count(*) from user_users");
    }
}
