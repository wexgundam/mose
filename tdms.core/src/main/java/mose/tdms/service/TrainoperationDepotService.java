/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package mose.tdms.service;

import org.springframework.stereotype.Service;

/**
 * what:    调度视角的车务段服务. <br/>
 * # 检测两个行车调度台是否邻接. <br/>
 * # 检测给定行车调度台是否管辖给定车站. <br/>
 * # 检测给定行车调度台是否邻接给定节点间. <br/>
 * # 检测给定行车调度台是否管辖给定车场. <br/>
 * # 按条件查询一组行车调度台. <br/>
 * # 按条件查询一个行车调度台. <br/>
 * # 按Id查询一个行车调度台. <br/>
 * # 获取全路行车调度台. <br/>
 * # 获取两个路局的邻接行车调度台. <br/>
 * # 获取给定路局所辖行车调度台. <br/>
 * # 获取给定行车调度台的邻接行车调度台. <br/>
 * # 获取给定车站的管辖行车调度台. <br/>
 * # 获取给定节点的管辖行车调度台. <br/>
 * # 获取给定车场的管辖行车调度台. <br/>
 * # 获取数量. <br/>
 * # 设置给定行车调度台的管辖单位. <br/>
 * # 新增行车调度台. <br/>
 * # 更新行车调度台. <br/>
 * # 删除行车调度台. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/9/12
 */
@Service
public class TrainoperationDepotService {
//    /**
//     * 定义日志输出位置
//     */
//    private Logger logger = LoggerFactory.getLogger("serviceLog");
//    /**
//     * 路网元素服务
//     */
//    @Autowired
//    private NetworkElementService networkElementService;
//    /**
//     * 路局服务
//     */
//    @Autowired
//    private BureauService bureauService;
//    /**
//     * 行车调度台数据获取服务
//     */
//    @Autowired
//    private TrainlineDepotDao trainlineDepotDao;
//    /**
//     * 车站服务
//     */
//    @Autowired
//    private StationService stationService;
//
//    /**
//     * what:    检测两个行车调度台是否邻接. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public boolean adjoin(TrainlineDepot trainlineDepotA, TrainlineDepot trainlineDepotB) {
//        return networkElementService.adjoin(trainlineDepotA, trainlineDepotB);
//    }
//
//    /**
//     * what:    检测给定行车调度台是否管辖给定车站. <br/>
//     * 通过GridService判断网格间关系. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public boolean jurisdiction(TrainlineDepot trainlineDepot, Station station) {
//        return networkElementService.jurisdiction(trainlineDepot, station);
//    }
//
//    /**
//     * what:    检测给定行车调度台是否管辖给定节点间. <br/>
//     * 通过GridService判断网格间关系. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public boolean jurisdiction(TrainlineDepot trainlineDepot, Link link) {
//        return networkElementService.jurisdiction(trainlineDepot, link);
//    }
//
//    /**
//     * what:    检测给定行车调度台是否管辖给定车场. <br/>
//     * 通过GridService判断网格间关系. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public boolean jurisdiction(TrainlineDepot trainlineDepot, Yard yard) {
//        return networkElementService.jurisdiction(trainlineDepot, yard);
//    }
//
//    /**
//     * what:    根据查询条件查询一组车站. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public List<TrainlineDepot> getMany(TrainlineDepotSearchVo trainlineDepotSearchVo) {
//        return trainlineDepotDao.getMany(trainlineDepotSearchVo);
//    }
//
//    /**
//     * what:    根据查询条件查询一个行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public TrainlineDepot getOne(TrainlineDepotSearchVo trainlineDepotSearchVo) {
//        return trainlineDepotDao.getOne(trainlineDepotSearchVo);
//    }
//
//    /**
//     * what:    根据id查询一个行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public TrainlineDepot getOne(int id) {
//        TrainlineDepotSearchVo trainlineDepotSearchVo = new TrainlineDepotSearchVo();
//        trainlineDepotSearchVo.setIdEqual(id);
//        return getOne(trainlineDepotSearchVo);
//    }
//
//
//    /**
//     * what:    查询所有行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public List<TrainlineDepot> getAll() {
//        return trainlineDepotDao.getAll();
//    }
//
//    /**
//     * what:    获取给定行车调度台的邻接行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public List<AdjoinTrainlineDepots> getAdjoinTrainlineDepotses(Bureau bureauA, Bureau bureauB) {
//        // 获取bureauA管辖的行调台
//        List<TrainlineDepot> bureauATrainlineDepots = getMany(bureauA);
//        // 获取bureauB管辖的行调台
//        List<TrainlineDepot> bureauBTrainlineDepots = getMany(bureauB);
//
//        //台邻接的行车调度台集合
//        List<AdjoinTrainlineDepots> adjoinTrainlineDepots = new ArrayList<>();
//        // 声明固定线程池。后期根据性能优化
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        // 任务集合
//        List<FutureTask<AdjoinTrainlineDepots>> futureTasks = new ArrayList<>();
//
//        for (TrainlineDepot bureauATrainlineDepot : bureauATrainlineDepots) {
//            // 声明任务
//            Callable<AdjoinTrainlineDepots> callable = () -> {
//                for (TrainlineDepot bureauBTrainlineDepot : bureauBTrainlineDepots) {
//                    if (adjoin(bureauATrainlineDepot, bureauBTrainlineDepot)) {
//                        return getAdjoinTrainlineDepots(bureauATrainlineDepot, bureauBTrainlineDepot);
//                    }
//                }
//                return null;
//            };
//            // 创建FutureTask
//            FutureTask<AdjoinTrainlineDepots> futureTask = new FutureTask<>(callable);
//            // 添加到任务集合
//            futureTasks.add(futureTask);
//            // 将任务提交到线程池
//            executorService.submit(futureTask);
//        }
//
//        // 软性关闭线程池，开始执行线程
//        executorService.shutdown();
//
//        // 返回结果
//        for (FutureTask<AdjoinTrainlineDepots> task : futureTasks) {
//            try {
//                if (task.get() != null) {
//                    adjoinTrainlineDepots.add(task.get());
//                }
//            } catch (InterruptedException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getAdjoinTrainlineDepotses(Bureau bureauA, Bureau bureauB)");
//                logger.error("异常信息: " + e.getMessage());
//            } catch (ExecutionException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getAdjoinTrainlineDepotses(Bureau bureauA, Bureau bureauB)");
//                logger.error("异常信息: " + e.getMessage());
//            }
//        }
//
//        return adjoinTrainlineDepots;
//    }
//
//    /**
//     * what:    获取两个行车调度的邻接信息. <br/>
//     * 如果邻接返回邻接对象，否则返回null. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/25
//     */
//    private AdjoinTrainlineDepots getAdjoinTrainlineDepots(TrainlineDepot trainlineDepotA, TrainlineDepot trainlineDepotB) {
//        //生成邻接信息
//        AdjoinTrainlineDepots adjoinTrainlineDepots = new AdjoinTrainlineDepots();
//        adjoinTrainlineDepots.setTrainlineDepotA(trainlineDepotA);
//        adjoinTrainlineDepots.setTrainlineDepotB(trainlineDepotB);
//
//        // 声明固定线程池。后期根据性能优化
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        // 任务集合
//        List<FutureTask> futureTasks = new ArrayList<>();
//
//        //获取两个行调台管辖的车站集合
//        List<Station> trainlineDepotAStations = stationService.getMany(trainlineDepotA);
//        List<Station> trainlineDepotBStations = stationService.getMany(trainlineDepotB);
//
//        //遍历行调台A管辖的车站集合
//        for (Station trainlineDepotAStation : trainlineDepotAStations) {
//            // 声明任务
//            Runnable runnable = () -> {
//                //遍历给定车站的邻接车站集合
//                for (AdjoinStations adjoinStations : stationService.getAdjoinStationses(trainlineDepotAStation)) {
//                    //如果行调台B管辖的车站集合包含邻接车站，则补充邻接行调台信息
//                    if (trainlineDepotBStations.contains(adjoinStations.getStationB())) {
//                        adjoinTrainlineDepots.setStationA(adjoinStations.getStationA());
//                        adjoinTrainlineDepots.setLink(adjoinStations.getLink());
//                        adjoinTrainlineDepots.setStationB(adjoinStations.getStationB());
//                        return;
//                    }
//                }
//            };
//            // 创建FutureTask
//            FutureTask futureTask = new FutureTask<>(runnable, null);
//            // 添加到任务集合
//            futureTasks.add(futureTask);
//            // 将任务提交到线程池
//            executorService.submit(futureTask);
//        }
//
//        // 软性关闭线程池，开始执行线程
//        executorService.shutdown();
//
//        // 返回结果
//        for (FutureTask task : futureTasks) {
//            try {
//                task.get();
//            } catch (InterruptedException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getAdjoinTrainlineDepots(TrainlineDepot trainlineDepotA, TrainlineDepot trainlineDepotB)");
//                logger.error("异常信息: " + e.getMessage());
//            } catch (ExecutionException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getAdjoinTrainlineDepots(TrainlineDepot trainlineDepotA, TrainlineDepot trainlineDepotB)");
//                logger.error("异常信息: " + e.getMessage());
//            }
//        }
//
//        return adjoinTrainlineDepots;
//    }
//
//    /**
//     * what:    获取给定路局管辖的行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public List<TrainlineDepot> getMany(Bureau bureau) {
//        TrainlineDepotSearchVo trainlineDepotSearchVo = new TrainlineDepotSearchVo();
//        trainlineDepotSearchVo.setJurisdictionBureauIdEqual(bureau.getId());
//        return getMany(trainlineDepotSearchVo);
//    }
//
//    /**
//     * what:    获取给定行车调度台的邻接行车调度台. <br/>
//     * 遍历所有行车调度台，如果邻接，则记录该行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public List<AdjoinTrainlineDepots> getAdjoinTrainlineDepotses(TrainlineDepot targetTrainlineDepot) {
//        //与给定行车调度台邻接的行车调度台集合
//        List<AdjoinTrainlineDepots> adjoinTrainlineDepots = new ArrayList<>();
//        // 声明固定线程池。后期根据性能优化
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        // 任务集合
//        List<FutureTask<AdjoinTrainlineDepots>> futureTasks = new ArrayList<>();
//
//        for (TrainlineDepot trainlineDepot : getAll()) {
//            // 声明任务
//            Callable<AdjoinTrainlineDepots> callable = () -> {
//                if (adjoin(targetTrainlineDepot, trainlineDepot)) {
//                    return getAdjoinTrainlineDepots(targetTrainlineDepot, trainlineDepot);
//                }
//                return null;
//            };
//            // 创建FutureTask
//            FutureTask<AdjoinTrainlineDepots> futureTask = new FutureTask<>(callable);
//            // 添加到任务集合
//            futureTasks.add(futureTask);
//            // 将任务提交到线程池
//            executorService.submit(futureTask);
//        }
//
//        // 软性关闭线程池，开始执行线程
//        executorService.shutdown();
//
//        // 返回结果
//        for (FutureTask<AdjoinTrainlineDepots> task : futureTasks) {
//            try {
//                if (task.get() != null) {
//                    adjoinTrainlineDepots.add(task.get());
//                }
//            } catch (InterruptedException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getAdjoinTrainlineDepotses(TrainlineDepot targetTrainlineDepot)");
//                logger.error("异常信息: " + e.getMessage());
//            } catch (ExecutionException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getAdjoinTrainlineDepotses(TrainlineDepot targetTrainlineDepot)");
//                logger.error("异常信息: " + e.getMessage());
//            }
//        }
//
//        return adjoinTrainlineDepots;
//
//    }
//
//    /**
//     * what:    获取给定车站的管辖行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public TrainlineDepot getJurisdiction(Station station) {
//        // 声明固定线程池。后期根据性能优化
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        // 任务集合
//        List<FutureTask<TrainlineDepot>> futureTasks = new ArrayList<>();
//
//        for (TrainlineDepot trainlineDepot : getAll()) {
//            // 声明任务
//            //查询给定路局的分界口
//            Callable<TrainlineDepot> callable = () -> jurisdiction(trainlineDepot, station) ? trainlineDepot : null;
//            // 创建FutureTask
//            FutureTask<TrainlineDepot> futureTask = new FutureTask<>(callable);
//            // 添加到任务集合
//            futureTasks.add(futureTask);
//            // 将任务提交到线程池
//            executorService.submit(futureTask);
//        }
//
//        // 软性关闭线程池，开始执行线程
//        executorService.shutdown();
//
//        // 返回结果
//        for (FutureTask<TrainlineDepot> task : futureTasks) {
//            try {
//                TrainlineDepot trainlineDepot = task.get();
//                if (trainlineDepot != null) {
//                    return trainlineDepot;
//                }
//            } catch (InterruptedException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getJurisdiction");
//                logger.error("异常信息: " + e.getMessage());
//            } catch (ExecutionException e) {
//                // 异常日志
//                logger.error("任务类: " + getClass().getSimpleName());
//                logger.error("任务方法: getJurisdiction");
//                logger.error("异常信息: " + e.getMessage());
//            }
//        }
//
//        return null;
//    }
//
//
//    /**
//     * what:    设置给定行调台的管辖局. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/11
//     */
//    public void setJurisdiction(TrainlineDepot trainlineDepot) {
//        Bureau jurisdictionBureau = bureauService.getJurisdiction(trainlineDepot);
//        if (jurisdictionBureau != null) {
//            trainlineDepot.setJurisdictionBureauId(jurisdictionBureau.getId());
//        }
//    }
//
//    /**
//     * what:    新增行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/25
//     */
//    public void addOne(TrainlineDepot trainlineDepot) {
//        int id = trainlineDepotDao.addOne(trainlineDepot);
//        trainlineDepot.setId(id);
//    }
//
//    /**
//     * what:    更新行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/25
//     */
//    public void updateOne(TrainlineDepot trainlineDepot) {
//        trainlineDepotDao.updateOne(trainlineDepot);
//    }
//
//
//    /**
//     * what:    删除行车调度台. <br/>
//     * when:    (这里描述这个类的适用时机 – 可选).<br/>
//     * how:     (这里描述这个类的使用方法 – 可选).<br/>
//     * warning: (这里描述这个类的注意事项 – 可选).<br/>
//     *
//     * @author 靳磊 created on 2019/9/25
//     */
//    public void deleteOne(TrainlineDepot trainlineDepot) {
//        trainlineDepotDao.deleteOne(trainlineDepot);
//    }
}
