/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.dao;

import mose.core.model.ComboboxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * what: 基础DAO，用于封装dao操作
 *
 * @param <T> model
 * @param <S> VO
 *
 * @author mose created on 2017年6月13日
 */
@Component
public class BaseDao<T, S> {
    /**
     * 数据库操作template
     */
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    /**
     * 使用参数进行数据库操作
     */
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * what: 新增
     *
     * @param sql sql
     * @param t   model
     *
     * @return 受影响行数
     *
     * @author mose created on 2017年6月13日
     */
    protected int insert(String sql, T t) {
        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t));
    }

    /**
     * what: 新增并返回主键
     *
     * @param sql     sql
     * @param t       model
     * @param pkField 主键
     *
     * @return 主键
     *
     * @author mose created on 2017年6月13日
     */
    protected int insertForId(String sql, T t, String pkField) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), keyHolder, new String[]{pkField});
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    /**
     * what: 修改
     *
     * @param sql sql
     * @param t   model
     *
     * @return 受影响行数
     *
     * @author mose created on 2017年6月13日
     */
    protected int update(String sql, T t) {
        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t));
    }

    /**
     * what:  按照参数修改
     *
     * @param sql     sql
     * @param objects 指定参数
     *
     * @return 受影响行数
     *
     * @author mose created on 2017年6月13日
     */
    protected int update(String sql, Object... objects) {
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * what: 按参数删除
     *
     * @param sql     sql
     * @param objects 指定参数
     *
     * @return 受影响行数
     *
     * @author mose created on 2017年6月13日
     */
    protected int delete(String sql, Object... objects) {
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * what: 根据参数获取model
     *
     * @param sql     sql
     * @param objects 指定参数
     *
     * @return model
     *
     * @author mose created on 2017年6月13日
     */
    protected T get(String sql, Object... objects) {
        List<T> list = jdbcTemplate.query(sql, objects, BeanPropertyRowMapper.newInstance(getClazz()));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * what: 取得当前泛型的实际class名
     *
     * @return Class
     *
     * @author mose created on 2017年6月13日
     */
    @SuppressWarnings("unchecked")
    private Class<T> getClazz() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * what: 直接根据sql获取列表
     *
     * @param sql sql
     *
     * @return List<model>
     *
     * @author mose created on 2017年6月13日
     */
    protected List<T> list(String sql) {
        List<T> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    /**
     * what: 根据sql和多参数获取列表
     *
     * @param sql     sql
     * @param objects 指定参数
     *
     * @return List<model>
     *
     * @author mose created on 2017年6月13日
     */
    protected List<T> list(String sql, Object... objects) {
        List<T> list = jdbcTemplate.query(sql, objects, BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    /**
     * what:  根据查询条件获取列表
     *
     * @param sql sql
     * @param s   VO
     *
     * @return List<model>
     *
     * @author mose  created on 2017年6月13日
     */
    protected List<T> list(String sql, S s) {
        List<T> list = namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(s), BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    /**
     * what:  查询总数，无参数
     *
     * @param sql sql
     *
     * @return 总条数
     *
     * @author mose created on 2017年6月13日
     */
    protected int count(String sql) {
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * what:  查询总数，带参数
     *
     * @param sql     sql
     * @param objects 指定参数
     *
     * @return 符合条件总数
     *
     * @author mose created on 2017年6月13日
     */
    protected int count(String sql, Object... objects) {
        return jdbcTemplate.queryForObject(sql, objects, Integer.class);
    }

    /**
     * what:  查询总数，带查询对象
     *
     * @param sql sql
     * @param s   VO
     *
     * @return 符合条件总数
     *
     * @author mose created on 2017年6月13日
     */
    protected int count(String sql, S s) {
        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(s), Integer.class);
    }

    /**
     * what: 下拉框列表
     *
     * @param sql sql
     *
     * @return List<ComboboxVO>
     *
     * @author mose created on 2017年6月13日
     */
    protected List<ComboboxVO> listCombobox(String sql) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }

    /**
     * what: 下拉框列表
     *
     * @param sql     sql
     * @param objects 指定参数
     *
     * @return List<ComboboxVO>
     *
     * @author mose created on 2017年6月13日
     */
    protected List<ComboboxVO> listCombobox(String sql, Object... objects) {
        return jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }
}
