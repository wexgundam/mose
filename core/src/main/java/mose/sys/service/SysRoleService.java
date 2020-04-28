package mose.sys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mose.core.cache.EhCacheUtil;
import mose.core.model.ComboboxVO;
import mose.core.pub.PubConfig;
import mose.core.session.SessionUtil;
import mose.core.session.UserSession;
import mose.core.string.StringUtil;
import mose.sys.dao.SysResourceDao;
import mose.sys.dao.SysRoleDao;
import mose.sys.dao.SysRoleResourceDao;
import mose.sys.model.SysResource;
import mose.sys.model.SysRole;
import mose.sys.model.SysRoleResource;
import mose.sys.vo.SysRoleSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * what: 系统角色处理Service how: 系统的所有权限控制都在该类中处理，共有三个cache
 * 1、系统左侧菜单，通过RoleId来生成，cache格式为roleMenu_+roleId
 * 2、系统所有按钮权限，页面显示按钮时使用，cache格式为roleFunctions_+roleId
 * 3、系统能访问资源权限，包括所有资源的url，hashmap数据类型，key为url，value为0/1，,1具有权限，0不具有权限，cache格式为roleResources_+roleId
 * 当修改模块或新增删除模块时，清空所有cache，当角色修改时，清空上述三个对应roleId的cache
 *
 * @author mose created on 2017年11月6日
 */
@Service
public class SysRoleService {
    /**
     * 角色Dao
     */
    @Autowired
    private SysRoleDao sysRoleDao;
    /**
     * 资源Dao
     */
    @Autowired
    private SysResourceDao sysResourceDao;
    /**
     * 角色资源Dao
     */
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;
    /**
     * 全局参数配置
     */
    @Autowired
    private PubConfig pubConfig;

    /**
     * what: 新增角色，同时新增对应的权限
     *
     * @param sysRole     sysRole
     * @param moduleArr   moduleArr
     * @param functionArr functionArr
     *
     * @return flag 0、失败，1、成功，2、角色名称已经存在
     *
     * @author mose created on 2017年11月6日
     */
    public int add(SysRole sysRole, String moduleArr, String functionArr) {
        int flag = 0;
        int count = sysRoleDao.getNameNum(sysRole);
        if (count > 0) {
            flag = 2;
        } else {
            int roleId = sysRoleDao.add(sysRole);
            sysRoleResourceDao.deleteRoleResource(roleId);
            String[] moduleSplit = moduleArr.split("@@");
            for (int i = 0; i < moduleSplit.length; i++) {
                if (StringUtil.isNotNullOrEmpty(moduleSplit[i])) {
                    sysRoleResourceDao.addRoleResource(roleId, Integer.parseInt(moduleSplit[i]));
                }
            }
            String[] functionSplit = functionArr.split("@@");
            for (int i = 0; i < functionSplit.length; i++) {
                if (StringUtil.isNotNullOrEmpty(functionSplit[i])) {
                    sysRoleResourceDao.addRoleResource(roleId, Integer.parseInt(functionSplit[i]));
                }
            }
            flag = 1;
        }
        return flag;
    }

    /**
     * what: 修改角色，同时新增对应的权限
     *
     * @param sysRole     sysRole
     * @param moduleArr   moduleArr
     * @param functionArr functionArr
     *
     * @return flag 0、失败，1、成功，2、角色名称已经存在
     *
     * @author mose created on 2017年11月6日
     */
    public int update(SysRole sysRole, String moduleArr, String functionArr) {
        int flag = 0;
        int count = sysRoleDao.getNameNum(sysRole);
        if (count > 0) {
            flag = 2;
        } else {
            sysRoleDao.update(sysRole);
            sysRoleResourceDao.deleteRoleResource(sysRole.getId());
            String[] moduleSplit = moduleArr.split("@@");
            for (int i = 0; i < moduleSplit.length; i++) {
                if (StringUtil.isNotNullOrEmpty(moduleSplit[i])) {
                    sysRoleResourceDao.addRoleResource(sysRole.getId(), Integer.parseInt(moduleSplit[i]));
                }
            }
            String[] functionSplit = functionArr.split("@@");
            for (int i = 0; i < functionSplit.length; i++) {
                if (StringUtil.isNotNullOrEmpty(functionSplit[i])) {
                    sysRoleResourceDao.addRoleResource(sysRole.getId(), Integer.parseInt(functionSplit[i]));
                }
            }
//            EhCacheUtil.remove("sysCache", "roleFunctions_" + sysRole.getId());
//            EhCacheUtil.remove("sysCache", "roleResources_" + sysRole.getId());
//            EhCacheUtil.remove("sysCache", "roleMenu_" + sysRole.getId());
            EhCacheUtil.removeAll("sysCache");
            flag = 1;
        }
        return flag;
    }

    /**
     * what: 删除角色
     *
     * @param id 角色id
     *
     * @return flag 0、失败，1、成功，2、存在系统用户不能删除
     *
     * @author mose created on 2017年11月6日
     */
    public int delete(int id) {
        int flag = 0;
        int count = sysRoleDao.countUserNum(id);
        if (count > 0) {
            flag = 2;
        } else {
            flag = sysRoleDao.delete(id);
            if (flag == 1) {
                sysRoleResourceDao.deleteRoleResource(id);
            }
//            EhCacheUtil.remove("sysCache", "roleFunctions_" + id);
//            EhCacheUtil.remove("sysCache", "roleResources_" + id);
//            EhCacheUtil.remove("sysCache", "roleMenu_" + id);
            EhCacheUtil.removeAll("sysCache");
        }
        return flag;
    }

    /**
     * what: 通过js来设置选中的模块和按钮
     *
     * @param roleId 角色id
     *
     * @return checkButton
     *
     * @author mose created on 2017年11月6日
     */
    public String checkResourceAndFunction(int roleId) {
        // 角色模块列表
        List<SysRoleResource> listRoleResource = sysRoleResourceDao.listRoleResourceByType(roleId, 1);
        // 角色对应功能
        List<SysRoleResource> listRoleFunction = sysRoleResourceDao.listRoleResourceByType(roleId, 2);
        StringBuilder sb = new StringBuilder();
        for (SysRoleResource sysRoleResource : listRoleResource) {
            sb.append("$('#mod_" + sysRoleResource.getResourceId() + "').prop('checked',true);\r\n");
        }
        for (SysRoleResource sysRoleFunction : listRoleFunction) {
            sb.append("$('#function_" + sysRoleFunction.getResourceId() + "').prop('checked',true);\r\n");
        }
        return sb.toString();
    }

    /**
     * what: 根据id获取
     *
     * @param id 角色id
     *
     * @return 根据id查询的角色对象
     *
     * @author mose created on 2017年11月6日
     */
    public SysRole get(int id) {
        return sysRoleDao.get(id);
    }

    /**
     * what: 获取列表
     *
     * @param sysRoleSearchVO 角色查询VO
     *
     * @return 查询的list
     *
     * @author mose created on 2017年11月6日
     */
    public List<SysRole> list(SysRoleSearchVO sysRoleSearchVO) {
        return sysRoleDao.list(sysRoleSearchVO);
    }

    /**
     * what: 角色列表总数
     *
     * @param sysRoleSearchVO 角色查询VO
     *
     * @return 角色列表总数
     *
     * @author mose created on 2017年11月6日
     */
    public int count(SysRoleSearchVO sysRoleSearchVO) {
        return sysRoleDao.count(sysRoleSearchVO);
    }

    /**
     * what: 角色下拉框
     *
     * @return 角色list
     *
     * @author mose created on 2017年11月13日
     */
    public List<ComboboxVO> listCombo() {
        return sysRoleDao.listCombo();
    }

    /**
     * what: 根据roleId来获取该角色具有的功能按钮
     *
     * @param roleId 角色id
     *
     * @return hashmap
     *
     * @author mose created on 2017年11月6日
     */
    public HashMap<String, String> getRoleFunctions(int roleId) {
        HashMap<String, String> hashFunctions = EhCacheUtil.get("sysCache", "roleFunctions" + roleId);
        if (hashFunctions == null) {
            hashFunctions = new HashMap<>();
            List<SysRoleResource> listRoleResource = sysRoleResourceDao.listRoleResourceByType(roleId, 2);
            for (SysRoleResource sysRoleResource : listRoleResource) {
                hashFunctions.put(sysRoleResource.getResourceCode(), sysRoleResource.getUrl());
            }
        }
        return hashFunctions;
    }

    /**
     * what: 根据roleId来获取所有的资源，返回hashmap，key为url，value为0/1,0不具备该权限，1具备
     *
     * @param roleId 角色id
     *
     * @return hashmap
     *
     * @author mose created on 2017年11月6日
     */
    public HashMap<String, Integer> getRoleResources(int roleId) {
        HashMap<String, Integer> hashRoleResources = EhCacheUtil.get("sysCache", "roleResources_" + roleId);
        if (hashRoleResources == null) {
            hashRoleResources = new HashMap<>();
            List<SysRoleResource> listRoleResource = sysRoleResourceDao.listRoleResource(roleId);
            for (SysRoleResource sysRoleResource : listRoleResource) {
                hashRoleResources.put(sysRoleResource.getUrl(), sysRoleResource.getResourceId() == 0 ? 0 : 1);
            }
            EhCacheUtil.put("sysCache", "roleResources_" + roleId, hashRoleResources);
        }
        return hashRoleResources;
    }

    /**
     * what:根据角色id生成该角色对应的菜单，可以支持生成多级菜单
     *
     * @param role_id       角色id
     * @param resourceLevel 资源等级，0、应用系统1、平台管理
     * @param themeStyle    themeStyle
     *
     * @return 菜单对应的HTML
     *
     * @Author: 靳磊 modified on 2017/10/11 15:29
     */
    public String createMenuStr(int role_id, int resourceLevel, int themeStyle) {
        String key = "roleMenu_" + role_id + "@resourceLevel" + resourceLevel;
        String menu = EhCacheUtil.get("sysCache", key);

        if (menu == null) {
            StringBuffer sb = new StringBuffer();
            // 模块列表
            List<SysResource> listResource = sysResourceDao.listByType(1);
            // 角色模块列表
            List<SysRoleResource> listRoleResource = sysRoleResourceDao.listRoleResourceByType(role_id, 1);
            List<Integer> displayResourceIdList = new ArrayList<>();
            for (SysRoleResource sysRoleResource : listRoleResource) {
                displayResourceIdList.add(sysRoleResource.getResourceId());
            }
            for (SysResource sysResource : getResourceTreeRootNodes(listResource, displayResourceIdList)) {
                if (themeStyle == 0) {
                    // 0代表横向菜单主题
                    sb.append(createMenuStr3(listResource, displayResourceIdList, sysResource, resourceLevel));
                } else if (themeStyle == 1) {
                    // 1代表纵向菜单主题
                    sb.append(createMenuStr(listResource, displayResourceIdList, sysResource, resourceLevel));
                }
            }
            menu = sb.toString();
            EhCacheUtil.put("sysCache", key, menu);
        }
        return menu;
    }

    /**
     * what:使用递归算法生成给定模块对应的菜单内容
     *
     * warning:如果模块包含子模块，则同时生成子模块对应的菜单内容
     *
     * @param listResource          所有模块集合
     * @param displayResourceIdList 用户可见模块集合
     * @param sysResource           给定的模块
     * @param resourceLevel         资源等级，0、应用系统1、平台管理
     *
     * @return 菜单对应的HTML
     *
     * @Author: 靳磊 created on 2017/10/11 14:26
     */
    private String createMenuStr(List<SysResource> listResource, List<Integer> displayResourceIdList,
                                 SysResource sysResource, int resourceLevel) {
        StringBuffer sb = new StringBuffer();
        // 如果模块不可见，则不生成菜单内容
        if (!displayResourceIdList.contains(sysResource.getId())) {
            return sb.substring(0);
        }

        // 获取子模块
        List<SysResource> children = findChildren(listResource, sysResource);
        // 如果模块为顶级模块或者包含子模块，则生成带有子菜单的菜单
        if (sysResource.getResourceLevel() == resourceLevel) {

            if (children != null) {
                sb.append("<li class=\"nav-item start \">\n"
                        + " <a href=\"javascript:;\" class=\"nav-link nav-toggle\">\n" + " <i class=\"fa "
                        + sysResource.getIconImg() + "\"></i>\n" + " <span class=\"title\">" + sysResource.getName()
                        + "</span>\n" + " <span class=\"arrow\"></span>\n" + " </a>\n" + " <ul class=\"sub-menu\">");
                if (children != null) {
                    // 如果包含子模块，则生成子模块对应的菜单，并且包含在本菜单内
                    for (SysResource child : children) {
                        if (child.getResourceLevel() == resourceLevel) {
                            sb.append(createMenuStr(listResource, displayResourceIdList, child, resourceLevel));
                        }
                    }
                }
                sb.append("</ul></li>");
            } else { // 生成叶子菜单
                sb.append("<li id=\"module_" + sysResource.getId() + "\" class=\"nav-item start \">\n" + " <a href=\""
                        + pubConfig.getDynamicServer() + "/" + sysResource.getUrl() + "\" class=\"nav-link\" target=\""
                        + sysResource.getTarget() + "\">\n" + " <i class=\"fa " + sysResource.getIconImg() + "\"></i>\n"
                        + " <span class=\"title\">" + sysResource.getName() + "</span>\n" + " </a>\n" + " </li>");
            }
        }

        return sb.substring(0);
    }

    /**
     * what: 该方法生成layout3的 横向菜单栏 warning:如果模块包含子模块，则同时生成子模块对应的菜单内容
     *
     * @param listResource          所有模块集合
     * @param displayResourceIdList 用户可见模块集合
     * @param sysResource           给定的模块
     * @param resourceLevel         资源等级，0、应用系统1、平台管理
     *
     * @return 菜单对应的HTML
     *
     * @author mose created on 2017年10月30日
     */
    private String createMenuStr3(List<SysResource> listResource, List<Integer> displayResourceIdList,
                                  SysResource sysResource, int resourceLevel) {
        StringBuffer sb = new StringBuffer();
        // 如果模块不可见，则不生成菜单内容
        if (!displayResourceIdList.contains(sysResource.getId())) {
            return sb.substring(0);
        }
        // 获取子模块
        List<SysResource> children = findChildren(listResource, sysResource);
        // 如果模块为顶级模块或者包含子模块，则生成带有子菜单的菜单
        if (sysResource.getResourceLevel() == resourceLevel) {
            if (sysResource.getParentId() == 0 || children != null) {
                String className = " ";
                if (children != null) {
                    className = "dropdown-submenu ";
                }
                if (sysResource.getParentId() == 0) {
                    className = "menu-dropdown classic-menu-dropdown ";
                }
                sb.append("<li aria-haspopup=\"true\" class=\"" + className + "\">\n" + " <a href=\"javascript:;\" >\n"
                        + " <i class=\"fa " + sysResource.getIconImg() + "\"></i>" + sysResource.getName() + "\n"
                        + " <span class=\"arrow\"></span>\n" + " </a>\n" + " <ul class=\"dropdown-menu \">");
                if (children != null) {
                    // 如果包含子模块，则生成子模块对应的菜单，并且包含在本菜单内
                    for (SysResource child : children) {
                        if (child.getResourceLevel() == resourceLevel) {
                            sb.append(createMenuStr3(listResource, displayResourceIdList, child, resourceLevel));
                        }
                    }
                }
                sb.append("</ul></li>");
            } else { // 生成叶子菜单
                sb.append("<li id=\"module_" + sysResource.getId() + "\" aria-haspopup=\"true\" class=\"\">\n"
                        + " <a href=\"" + pubConfig.getDynamicServer() + "/" + sysResource.getUrl()
                        + "\" class=\"nav-link \" target=\"" + sysResource.getTarget() + "\">\n" + " <i class=\"fa "
                        + sysResource.getIconImg() + "\"></i>" + sysResource.getName() + "\n" + " </a>\n" + " </li>");
            }
        }

        return sb.substring(0);
    }

    /**
     * what:获得所有顶级模块集合，顶级模块：没有父模块的模块
     *
     * @param allSysResources       所有模块集合
     * @param displayResourceIdList 用户可见模块集合
     *
     * @return 顶级菜单列表
     *
     * @Author: 靳磊 created on 2017/10/11 14:25
     */
    private List<SysResource> getResourceTreeRootNodes(List<SysResource> allSysResources,
                                                       List<Integer> displayResourceIdList) {
        List<SysResource> sysResources = new ArrayList<>();
        for (SysResource sysResource : allSysResources) {
            if (!displayResourceIdList.contains(sysResource.getId())) {
                continue;
            }

            if (sysResource.getParentId() == 0) {
                sysResources.add(sysResource);
            }
        }
        Collections.sort(sysResources, Comparator.comparingInt(SysResource::getDisplayOrder));
        return sysResources;
    }

    /**
     * what:获得给定模块的子模块集合 how:使用递归算法
     *
     * @param sysResources 所有模块集合
     * @param sysResource  给定的模块
     *
     * @return 子模块集合
     *
     * @Author: 靳磊 created on 2017/10/11 14:24
     */
    private List<SysResource> findChildren(List<SysResource> sysResources, SysResource sysResource) {
        List<SysResource> children = new ArrayList<>();
        for (SysResource child : sysResources) {
            if (sysResource.getId() == child.getParentId()) {
                findChildren(sysResources, child);
                children.add(child);
            }
        }
        Collections.sort(children, Comparator.comparingInt(SysResource::getDisplayOrder));
        return children.isEmpty() ? null : children;
    }

    /**
     * what: 校验所有权限，防止不通过浏览器提交
     *
     * @param roleId 角色id
     * @param path   路径
     *
     * @return boolean
     *
     * @author mose created on 2017年11月6日
     */
    public boolean checkAuthority(int roleId, String path) {
        HashMap<String, Integer> hashRoleResources = getRoleResources(roleId);
        return (!hashRoleResources.containsKey(path) || hashRoleResources.get(path) == 1);

    }

    /**
     * what: 判断按钮是否在角色中
     *
     * @param buttonCode buttonCode
     *
     * @return boolean
     *
     * @author mose created on 2017年11月6日
     */
    public boolean checkBtnPrivilege(String buttonCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        UserSession userSession = SessionUtil.getUserSession(request);
        HashMap<String, String> hashRoleFunction = getRoleFunctions(userSession.getRoleId());
        return hashRoleFunction.containsKey(buttonCode);
    }
}
