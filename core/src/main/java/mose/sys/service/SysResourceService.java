package mose.sys.service;

import mose.core.cache.EhCacheUtil;
import mose.core.string.StringUtil;
import mose.sys.dao.SysResourceDao;
import mose.sys.model.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * what:    系统资源Service
 *
 *
 * @author mose created on 2017年06月13日
 */
@Service
public class SysResourceService {
    /**
     * 系统资源dao
     */
    @Autowired
    private SysResourceDao sysResourceDao;


    /**
     *
     * what:新增前判断代码是否唯一
     *
     * @param sysResource 系统资源实体类
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int add(SysResource sysResource) {
        //判断代码是否一致
        SysResource exist = sysResourceDao.getByModuleCode(sysResource.getCode());
        if (exist != null) {
            return 2;
        } else {
            return sysResourceDao.add(sysResource);
        }

    }
    /**
     *
     * what:    更新sysResource
     *
     * @param sysResource 系统资源实体类
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int update(SysResource sysResource) {
        return sysResourceDao.update(sysResource);
    }


    /**
     *
     * what:   删除资源，先判断是否有下级资源，有的话提示不允许删除,删除时同时删除该资源对应的功能
     * @param id 资源id
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int delete(int id) {
        if (sysResourceDao.getChildCount(id) > 0) {
            return 2;
        } else {
            sysResourceDao.deleteByParentId(id);
            return sysResourceDao.delete(id);
        }
    }

    /**
     *
     * what: 获取资源
     *
     * @param id 资源id
     * @return SysResource
     *
     * @author mose created on 2017年06月13日
     */
    public SysResource get(int id) {
        return sysResourceDao.get(id);
    }


    /**
     *
     * what:   根据类型来获取所有资源
     * @param type 资源类型
     * @return List
     *
     * @author mose  created on 2017年11月13日
     */
    public List<SysResource> listByType(int type) {
        return sysResourceDao.listByType(type);
    }


    /**
     *
     * what:    模块列表，递归生成，用于显示treeGrid
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> list() {
        //获取所有模块
        List<SysResource> list = sysResourceDao.listByType(1);
        List<SysResource> listRet = new ArrayList<>();
        listRet = createModuleList(list, listRet, 0);
        return listRet;
    }


    /**
     *
     * what:   模块列表，角色设置时用到
     * @param parentId 父级节点
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> listByIsbuildin(int parentId) {
        //获取所有模块
        List<SysResource> list = sysResourceDao.listByType(1);
        List<SysResource> listRet = new ArrayList<>();
        listRet = createModuleList(list, listRet, parentId);
        return listRet;
    }
    /**
     *
     * what:  创建模块列表
     *
     * @param list 系统资源list
     * @param listRet listRet
     * @param parentId 父节点
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    private List<SysResource> createModuleList(List<SysResource> list, List<SysResource> listRet, int parentId) {
        for (SysResource sysModule : list) {
            if (sysModule.getParentId() == parentId) {
                listRet.add(sysModule);
                if (sysModule.getCnt() > 0) {
                    listRet = createModuleList(list, listRet, sysModule.getId());
                }
            }
        }
        return listRet;
    }

    /**
     *
     * what:  获取子节点
     *
     * @param id 资源id
     * @return int
     *
     * @author mose created on 2017年06月13日
     */
    public int getChildCount(int id) {
        return sysResourceDao.getChildCount(id);
    }


    /**
     *
     * what:   模块列表，递归生成，用于显示treeGrid
     *
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> listByParentId() {
        //获取所有模块
        List<SysResource> list = sysResourceDao.listByType(1);
        List<SysResource> listRet = new ArrayList<>();
        listRet = createModuleList(list, listRet, 1);
        return listRet;
    }


    /**
     *
     * what:     生成Ztree的树节点,新增模块时使用，只有模块上下级
     *
     * @return String
     *
     * @author mose created on 2017年06月13日
     */
    public String createZtreeByModule() {
        // 模块列表
        List<SysResource> listModule = sysResourceDao.listByType(1);
        StringBuilder sb = new StringBuilder();
        for (SysResource sysModule : listModule) {
            sb.append("{id : \"" + sysModule.getId() + "\",pId :\"" + sysModule.getParentId() + "\",name :\"" + sysModule.getName() + "\",open : false");
            sb.append("},");
        }
        return StringUtil.subTract(sb.toString());
    }

    /**
     *
     * what:     生成Ztree的树节点,新增模块时使用，只有模块上下级
     *
     * @return String
     *
     * @author mose created on 2017年11月14日
     */
    public String createZtreeByResourceLevel(int resourceLevel) {
        // 模块列表
        List<SysResource> listModule = sysResourceDao.listByTypeAndResourcelevel(1, resourceLevel);
        StringBuilder sb = new StringBuilder();
        for (SysResource sysModule : listModule) {
            sb.append("{\"id\":\"" + sysModule.getId() + "\","+"\"pId\":\"" + sysModule.getParentId() + "\","+"\"name\""+":\"" + sysModule.getName() + "\","+"\"open\""+":\"false\"},");

        }
        return StringUtil.subTract(sb.toString());
    }


    /**
     *
     * what:    根据parentId获取下面的所有功能
     *
     * @param parentId 父节点
     * @param type
     * @return List
     *
     * @author mose created on 2017年06月13日
     */
    public List<SysResource> listByParentId(int parentId, int type) {
        return sysResourceDao.listByParentId(parentId, type);
    }


    /**
     *
     * what:   从缓存中获取所有资源
     *
     * @return HashMap
     *
     * @author mose created on 2017年06月13日
     */
    public HashMap<String, SysResource> getAllResource() {
        HashMap<String, SysResource> hashMap = EhCacheUtil.get("sysCache", "sysAllResource");
        if (hashMap == null) {
            hashMap = new HashMap<>();
            // 资源列表
            List<SysResource> listResource = sysResourceDao.list();
            for (SysResource sysResource : listResource) {
                if (!sysResource.getUrl().equals("#")) {
                    hashMap.put(sysResource.getUrl(), sysResource);
                }
            }
            EhCacheUtil.put("sysCache", "sysAllResource", hashMap);
        }
        return hashMap;
    }

}
