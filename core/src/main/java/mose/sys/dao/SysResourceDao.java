package mose.sys.dao;

import mose.core.dao.BaseDao;
import mose.sys.model.SysResource;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * what:    系统资源管理Dao
 *
 *
 * @author mose created on 2017年06月13日
 */
@Repository
public class SysResourceDao extends BaseDao<SysResource, SysResource> {


    /**
     *
     * what:    新增
     * @param sysResource 资源实体类
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int add(SysResource sysResource) {
        String sql = "insert into t_sys_resource(id,name,code,parent_id,url,target,iconImg,display_order,type,description,resource_level)" +
                " values(seq_t_sys_resource.nextval,:name,:code,:parentId,:url,:target,:iconImg,:displayOrder,:type,:description,:resourceLevel)";
        return insert(sql, sysResource);
    }


    /**
     *
     * what:   修改
     *
     * @param sysResource  资源实体类
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int update(SysResource sysResource) {
        String sql = "update t_sys_resource set name=:name,code=:code,url=:url,parent_id=:parentId,target=:target,iconImg=:iconImg," +
                "display_order=:displayOrder,description=:description,resource_level=:resourceLevel where id=:id";
        return update(sql, sysResource);
    }

    /**
     *
     * what: 删除
     *
     * @param id 资源id
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int delete(int id) {
        String sql = "delete from t_sys_resource where id=?";
        return delete(sql, id);
    }


    /**
     *
     * what:    按上级id删除，删除对应的功能
     *
     * @param id 资源 id
     * @return  int
     *
     * @author mose created on 2017年06月13日
     */
    public int deleteByParentId(int id) {
        String sql = "delete from t_sys_resource where parent_id=?";
        return delete(sql, id);
    }


    /**
     *
     * what:   根据id获取
     *
     * @param id 资源id
     * @return SysResource
     *
     * @author mose created on 2017年0613日
     */
    public SysResource get(int id) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,t.resource_level,(select name from t_sys_resource where id=t.parent_id)"
                + " parent_name from t_sys_resource t where id=?";
        return get(sql, id);
    }


    /**
     *
     * what:   获取所有资源
     *
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> list() {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,(select count(*) "
                + " from t_sys_resource where parent_id=t.id) cnt,(select name from t_sys_resource where id=t.parent_id)parentName" +
                " from t_sys_resource t order by parent_id, display_order";
        return list(sql);
    }

    /**
     *
     * what:  获取所有资源
     *
     * @param type 资源类型
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> listByType(int type) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,t.resource_level,(select count(*)"
                + " from t_sys_resource where parent_id=t.id) cnt from t_sys_resource t where type =? order by parent_id, display_order";
        return list(sql, type);
    }

    /**
     *
     * what:  按照资源等级获取资源
     *
     * @param type 资源类型
     * @param resourcelevel  资源等级
     * @return List
     *
     * @author 郭飞 created on 2017年11月14日
     */
    public List<SysResource> listByTypeAndResourcelevel(int type, int resourcelevel) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,t.resource_level,(select count(*)"
                + " from t_sys_resource where parent_id=t.id) cnt from t_sys_resource t where type =? and resource_level = ?  order by parent_id, display_order";
        return list(sql, type, resourcelevel);
    }

    /**
     *
     * what:    获取下级节点总数
     *
     * @param id 资源id
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int getChildCount(int id) {
        String sql = "select count(*) from t_sys_resource where parent_id=? and type=1";
        return count(sql, id);
    }


    /**
     *
     * what:   根据parentId获取下面的所有功能
     *
     * @param parentId 父节点
     * @param type 资源类型
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> listByParentId(int parentId, int type) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,(select count(*)"
                + " from t_sys_resource where parent_id=t.id) cnt from t_sys_resource t where parent_id=? and type=? order by display_order";
        return list(sql, parentId, type);
    }


    /**
     *
     * what:    根据角色id获取模块
     *
     * @param roleId 角色id
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> listByRoleId(int roleId) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description from t_sys_resource ";
        sql += " where id in (select  module_id from t_sys_rolemodule where role_id =?)";
        sql += " order by parent_id, display_order";
        return list(sql, roleId);
    }


    /**
     *
     * what:   根据模块代码获取模块信息
     *
     * @param code  code
     * @return SysResource
     *
     * @author mose created on 2017年06月13日
     */
    public SysResource getByModuleCode(String code) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description from t_sys_resource t where code=?";
        return get(sql, code);
    }



}
