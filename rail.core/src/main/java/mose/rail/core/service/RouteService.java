/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.rail.core.service;

import mose.network.modal.Grid;
import mose.network.service.GridService;
import mose.rail.core.dao.LinkDao;
import mose.rail.core.modal.Link;
import mose.rail.core.modal.Site;
import mose.rail.core.modal.Station;
import mose.rail.core.modal.Yard;
import mose.rail.core.vo.LinkSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * what: 径路服务<br/>
 * 径路——广义节点间. <br/>
 * 径路表示有一个位置到另一个位置的线路，其本质是节点间，但扩展了节点间的概念<br/>
 * 一组连续的节点间亦可视为一个节点间，即径路.<br/>
 * 一个节点间亦可视为径路。<br/>
 * 径路是路网模型中最重要的概念，是核心元素。理解了径路即理解了全部<br/>
 * <p>
 * # 检测给定的两个径路是否存在重叠（不含交叉、相切）. <br/>
 * # 检测给定径路是否经由给定位置. <br/>
 * # 检测给定径路是否经由给定路局. <br/>
 * # 检测给定径路是否经由给定行调台. <br/>
 * # 检测给定径路是否经由给定车站. <br/>
 * # 检测给定径路是否经由给定车场. <br/>
 * # 检测给定径路是否经由给定节点间. <br/>
 * # 按条件查询一组径路. <br/>
 * # 按条件查询一个径路. <br/>
 * # 按Id查询一个径路. <br/>
 * # 获得给定的两个径路的重叠部分. <br/>
 * # 获得给定径路经由的路局. <br/>
 * # 获得给定径路经由的行调台. <br/>
 * # 获得给定径路经由的车站. <br/>
 * # 获得给定径路经由的车场. <br/>
 * # 获得给定径路经由的节点间. <br/>
 * # 获得所有径路. <br/>
 * # 获取数量. <br/>
 * # 以后：获取给定两个路网元素间的最短径路. <br/>
 * # 以后：获取给定两个路网元素间的所有径路. <br/>
 * # 新增径路. <br/>
 * # 更新径路. <br/>
 * # 删除径路. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/12/12
 */
@Service
public class RouteService {
    /**
     * 定义日志输出位置
     */
    private Logger logger = LoggerFactory.getLogger("serviceLog");
    /**
     * 网格服务
     */
    @Autowired
    private GridService gridService;
    /**
     * 路网元素服务
     */
    @Autowired
    private NetworkElementService networkElementService;
    /**
     * 位置服务
     */
    @Autowired
    private SiteService siteService;
    /**
     * 数据获取Dao
     */
    @Autowired
    private LinkDao linkDao;

    /**
     * what:    检测给定的两个径路是否存在重叠（不含交叉、相切）. <br/>
     * 通过GridService判断网格间关系. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean intersects(Link routeA, Link routeB) {
        // 判断elementA是否已生成网格
        networkElementService.checkGrid(routeA);
        // 判断elementB是否已生成网格
        networkElementService.checkGrid(routeB);
        // 通过网格服务判断是否邻接
        return gridService.intersects(routeA.getGrid(), routeB.getGrid());
    }

    /**
     * what:    检测给定的两个径路是否存在重叠（不含交叉、相切）. <br/>
     * 通过GridService判断网格间关系. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean intersects(String routeAAnchorPointsString, String routeBAnchorPointsString) {
        //根据径路坐标的字符串形式创建径路对象
        Link routeA = new Link();
        routeA.setAnchorPointsString(routeAAnchorPointsString);
        Link routeB = new Link();
        routeB.setAnchorPointsString(routeBAnchorPointsString);

        // 判断elementA是否已生成网格
        networkElementService.checkGrid(routeA);
        // 判断elementB是否已生成网格
        networkElementService.checkGrid(routeB);
        // 通过网格服务判断是否邻接
        return gridService.intersects(routeA.getGrid(), routeB.getGrid());
    }

    /**
     * what:    检测给定的径路是否经由给定的位置. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean route(Link route, Site site) {
        // 判断elementA是否已生成网格
        networkElementService.checkGrid(route);
        // 判断elementB是否已生成网格
        networkElementService.checkGrid(site);
        // 通过网格服务判断是否邻接
        return gridService.contains(route.getGrid(), site.getGrid()) || gridService.touches(route.getGrid(), site.getGrid());
    }

    /**
     * what:    检测给定的径路是否经由给定的位置. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean route(List<Link> routes, String pointString) {
        //根据字符串形式的坐标生成位置对象
        Site site = siteService.getSite(pointString);
        //检测径路集合是否经过位置
        for (Link route : routes) {
            if (route(route, site)) {
                return true;
            }
        }
        return false;
    }

    /**
     * what:    检测给定的径路是否经由给定的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean route(Link route, Link link) {
        // 判断elementA是否已生成网格
        networkElementService.checkGrid(route);
        // 判断elementB是否已生成网格
        networkElementService.checkGrid(link);
        // 通过网格服务判断是否包含
        return gridService.contains(route.getGrid(), link.getGrid());
    }

    /**
     * what:    检测给定的径路是否经由给定的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean route(List<Link> routes, Link link) {
        for (Link route : routes) {
            if (route(route, link)) {
                return true;
            }
        }
        return false;
    }

    /**
     * what:    检测是否存在与给定车站和给定车场邻接的节点间. <br/>
     * 只要存在一个节点间连接给定的车站和车场，即视为这两个车站邻接. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean adjoin(Station station, Yard yard) {
        //获得车站A的邻接节点间
        for (Link link : getMany(station)) {
            //如果某节点间与车场邻接，返回true
            if (adjoin(yard, link)) {
                return true;
            }
        }
        return false;
    }


    /**
     * what:    检测是否存在与给定的两个车场均邻接的节点间. <br/>
     * 只要存在一个节点间连接给定的两个车场，即视为这两个车站邻接. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean adjoin(Yard yardA, Yard yardB) {
        //获得车站A的邻接节点间
        for (Link link : getMany(yardA)) {
            //如果某节点间与车场B邻接，返回true
            if (adjoin(yardB, link)) {
                return true;
            }
        }
        return false;
    }

    /**
     * what:    检测给定车站是否邻接给定节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/16
     */
    public boolean adjoin(Station station, Link link) {
        return networkElementService.adjoin(station, link);
    }

    /**
     * what:    检测给定车场是否邻接给定节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public boolean adjoin(Yard yard, Link link) {
        return networkElementService.adjoin(yard, link);
    }

    /**
     * what:    根据查询条件查询一组节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getMany(LinkSearchVo linkSearchVo) {
        return linkDao.getMany(linkSearchVo);
    }

    /**
     * what:    根据查询条件查询一个节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Link getOne(LinkSearchVo linkSearchVo) {
        return linkDao.getOne(linkSearchVo);
    }

    /**
     * what:    根据id查询一个节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public Link getOne(int id) {
        LinkSearchVo linkSearchVo = new LinkSearchVo();
        linkSearchVo.setIdEqual(id);
        return getOne(linkSearchVo);
    }

    /**
     * what:    获得所有节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getAll() {
        LinkSearchVo linkSearchVo = new LinkSearchVo();
        return getMany(linkSearchVo);
    }

    /**
     * what:    获得给定的两个径路的重叠部分. <br/>
     * 径路重叠部分不连续时，将被拆分成多个径路
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getIntersection(Link routeA, Link routeB) {
        List<Link> interSectionRoutes = new ArrayList<>();
        // 判断elementA是否已生成网格
        networkElementService.checkGrid(routeA);
        // 判断elementB是否已生成网格
        networkElementService.checkGrid(routeB);
        // 获得重叠网格集合
        List<Grid> intersectionGrids = gridService.getIntersection(routeA.getGrid(), routeB.getGrid());
        for (Grid intersectionGrid : intersectionGrids) {
            // 创建Route
            Link interSectionRoute = new Link();
            interSectionRoute.setGrid(intersectionGrid);
            networkElementService.updateElementByGrid(interSectionRoute);
            interSectionRoutes.add(interSectionRoute);
        }
        return interSectionRoutes;
    }

    /**
     * what:    获得给定的两个径路的重叠部分. <br/>
     * 径路重叠部分不连续时，将被拆分成多个径路
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getIntersection(String routeAString, String routeBString) {
        //根据径路坐标的字符串形式创建径路对象
        Link routeA = new Link();
        routeA.setAnchorPointsString(routeAString);
        Link routeB = new Link();
        routeB.setAnchorPointsString(routeBString);

        List<Link> interSectionRoutes = new ArrayList<>();
        // 判断elementA是否已生成网格
        networkElementService.checkGrid(routeA);
        // 判断elementB是否已生成网格
        networkElementService.checkGrid(routeB);
        // 获得重叠网格集合
        List<Grid> intersectionGrids = gridService.getIntersection(routeA.getGrid(), routeB.getGrid());
        for (Grid intersectionGrid : intersectionGrids) {
            // 创建Route
            Link interSectionRoute = new Link();
            interSectionRoute.setGrid(intersectionGrid);
            networkElementService.updateElementByGrid(interSectionRoute);
            interSectionRoutes.add(interSectionRoute);
        }
        return interSectionRoutes;
    }

    /**
     * what:    获得与两个给定车站邻接的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getMany(Station stationA, Station stationB) {
        // 与两个车站均邻接的节点间集合
        List<Link> links = new ArrayList<>();
        // 声明固定线程池。后期根据性能优化
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        // 任务集合
        List<FutureTask<Link>> futureTasks = new ArrayList<>();

        //获得与车站A邻接的节点间
        for (Link link : getMany(stationA)) {
            // 声明任务
            Callable<Link> callable = () -> {
                //检测车站B是否与节点间邻接，如果邻接，返回节点间
                return adjoin(stationB, link) ? link : null;
            };
            // 创建FutureTask
            FutureTask<Link> futureTask = new FutureTask<>(callable);
            // 添加到任务集合
            futureTasks.add(futureTask);
            // 将任务提交到线程池
            executorService.submit(futureTask);
        }

        // 软性关闭线程池，开始执行线程
        executorService.shutdown();

        // 返回结果
        for (FutureTask<Link> task : futureTasks) {
            try {
                Link link = task.get();
                if (link != null) {
                    links.add(link);
                }
            } catch (InterruptedException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法: getMany(Station stationA, Station stationB)");
                logger.error("异常信息: " + e.getMessage());
            } catch (ExecutionException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法: getMany(Station stationA, Station stationB)");
                logger.error("异常信息: " + e.getMessage());
            }
        }

        return links;
    }

    /**
     * what:    获得与给定车站和给定车场邻接的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getMany(Station station, Yard yard) {
        // 与车站车场均邻接的节点间集合
        List<Link> links = new ArrayList<>();
        // 声明固定线程池。后期根据性能优化
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        // 任务集合
        List<FutureTask<Link>> futureTasks = new ArrayList<>();

        //获得与车站邻接的节点间
        for (Link link : getMany(station)) {
            // 声明任务
            Callable<Link> callable = () -> {
                //检测车场是否与节点间邻接，如果邻接，返回节点间
                return adjoin(yard, link) ? link : null;
            };
            // 创建FutureTask
            FutureTask<Link> futureTask = new FutureTask<>(callable);
            // 添加到任务集合
            futureTasks.add(futureTask);
            // 将任务提交到线程池
            executorService.submit(futureTask);
        }

        // 软性关闭线程池，开始执行线程
        executorService.shutdown();

        // 返回结果
        for (FutureTask<Link> task : futureTasks) {
            try {
                Link link = task.get();
                if (link != null) {
                    links.add(link);
                }
            } catch (InterruptedException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法:  getMany(Station station, Yard yard)");
                logger.error("异常信息: " + e.getMessage());
            } catch (ExecutionException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法:  getMany(Station station, Yard yard)");
                logger.error("异常信息: " + e.getMessage());
            }
        }

        return links;
    }

    /**
     * what:    获得与两个给定车场邻接的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getMany(Yard yardA, Yard yardB) {
        // 与两个车场均邻接的节点间集合
        List<Link> links = new ArrayList<>();
        // 声明固定线程池。后期根据性能优化
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        // 任务集合
        List<FutureTask<Link>> futureTasks = new ArrayList<>();

        //获得与车场A邻接的节点间
        for (Link link : getMany(yardA)) {
            // 声明任务
            Callable<Link> callable = () -> {
                //检测车场B是否与节点间邻接，如果邻接，返回节点间
                return adjoin(yardB, link) ? link : null;
            };
            // 创建FutureTask
            FutureTask<Link> futureTask = new FutureTask<>(callable);
            // 添加到任务集合
            futureTasks.add(futureTask);
            // 将任务提交到线程池
            executorService.submit(futureTask);
        }

        // 软性关闭线程池，开始执行线程
        executorService.shutdown();

        // 返回结果
        for (FutureTask<Link> task : futureTasks) {
            try {
                Link link = task.get();
                if (link != null) {
                    links.add(link);
                }
            } catch (InterruptedException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法:  getMany(Yard yardA, Yard yardB)");
                logger.error("异常信息: " + e.getMessage());
            } catch (ExecutionException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法:  getMany(Yard yardA, Yard yardB)");
                logger.error("异常信息: " + e.getMessage());
            }
        }

        return links;
    }

    /**
     * what:    获得与给定车站邻接的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getMany(Station station) {
        //与给定车站邻接的节点间集合
        List<Link> links = new ArrayList<>();
        // 声明固定线程池。后期根据性能优化
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        // 任务集合
        List<FutureTask<Link>> futureTasks = new ArrayList<>();

        for (Link link : getAll()) {
            // 声明任务
            Callable<Link> callable = () -> adjoin(station, link) ? link : null;
            // 创建FutureTask
            FutureTask<Link> futureTask = new FutureTask<>(callable);
            // 添加到任务集合
            futureTasks.add(futureTask);
            // 将任务提交到线程池
            executorService.submit(futureTask);
        }

        // 软性关闭线程池，开始执行线程
        executorService.shutdown();

        // 返回结果
        for (FutureTask<Link> task : futureTasks) {
            try {
                if (task.get() != null) {
                    links.add(task.get());
                }
            } catch (InterruptedException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法: getMany(Station station)");
                logger.error("异常信息: " + e.getMessage());
            } catch (ExecutionException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法: getMany(Station station)");
                logger.error("异常信息: " + e.getMessage());
            }
        }

        return links;
    }

    /**
     * what:    获得与给定车场邻接的节点间. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public List<Link> getMany(Yard yard) {
        //与给定车场邻接的节点间集合
        List<Link> links = new ArrayList<>();
        // 声明固定线程池。后期根据性能优化
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        // 任务集合
        List<FutureTask<Link>> futureTasks = new ArrayList<>();

        for (Link link : getAll()) {
            // 声明任务
            Callable<Link> callable = () -> adjoin(yard, link) ? link : null;
            // 创建FutureTask
            FutureTask<Link> futureTask = new FutureTask<>(callable);
            // 添加到任务集合
            futureTasks.add(futureTask);
            // 将任务提交到线程池
            executorService.submit(futureTask);
        }

        // 软性关闭线程池，开始执行线程
        executorService.shutdown();

        // 返回结果
        for (FutureTask<Link> task : futureTasks) {
            try {
                if (task.get() != null) {
                    links.add(task.get());
                }
            } catch (InterruptedException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法: getMany(Yard yard)");
                logger.error("异常信息: " + e.getMessage());
            } catch (ExecutionException e) {
                // 异常日志
                logger.error("任务类: " + getClass().getSimpleName());
                logger.error("任务方法: getMany(Yard yard)");
                logger.error("异常信息: " + e.getMessage());
            }
        }

        return links;
    }

    /**
     * what:    新增径路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void addOne(Link link) {
        int id = linkDao.addOne(link);
        link.setId(id);
    }

    /**
     * what:    更新径路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void updateOne(Link link) {
        linkDao.updateOne(link);
    }

    /**
     * what:    删除径路. <br/>
     * when:    (这里描述这个类的适用时机 – 可选).<br/>
     * how:     (这里描述这个类的使用方法 – 可选).<br/>
     * warning: (这里描述这个类的注意事项 – 可选).<br/>
     *
     * @author 靳磊 created on 2019/9/11
     */
    public void deleteOne(Link link) {
        linkDao.deleteOne(link);
    }

}
