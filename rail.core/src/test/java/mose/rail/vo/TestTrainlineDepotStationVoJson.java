/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose.rail.vo;

import mose.core.gis.GisUtil;
import mose.core.json.JsonUtil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what:    行调台所属站点. <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/21
 */
public class TestTrainlineDepotStationVoJson {
    @Test
    public void testTrainlineDepotStations() throws IOException {
        //基于给定地图图片的墨卡托投影转换坐标
        //左上
        double[] topLeftMercator = Parameters.topLeftMercator;
        //右上
        double[] topRightMercator = Parameters.topRightMercator;
        //左下
        double[] bottomLeftMercator = Parameters.bottomLeftMercator;
        //图片尺寸
        double viewSize = Parameters.viewSize;

        Map<String, Object> data = new HashMap<>();
        List<List<TrainlineDepotStationVo>> trainlineDepotStationVos = new ArrayList<>();

        //获取路局多边形腾讯Gis坐标
        double[][][] trainlineDepots = getTrainlineDepots();
        for (int trainlineDepotIndex = 0; trainlineDepotIndex < trainlineDepots.length; trainlineDepotIndex++) {
            double[][] trainlineDepot = trainlineDepots[trainlineDepotIndex];
            List<TrainlineDepotStationVo> stationVos = new ArrayList<>();
            for (int trainlineDepotStationIndex = 0; trainlineDepotStationIndex < trainlineDepot.length; trainlineDepotStationIndex++) {
                double[] trainlineDepotStation = trainlineDepot[trainlineDepotStationIndex];
                TrainlineDepotStationVo trainlineDepotStationVo = new TrainlineDepotStationVo();
                trainlineDepotStationVo.setId("trainlineDepotStationVo" + (trainlineDepotIndex + 1) + "-" + (trainlineDepotStationIndex + 1));
                //将腾讯Gis坐标转为墨卡托投影
                double[] mercator = GisUtil.INSTANCE.lngLat2Mercator(new double[]{trainlineDepotStation[1], trainlineDepotStation[0]});
                //将墨卡托投影转为视图坐标
                trainlineDepotStationVo.setX((mercator[0] - topLeftMercator[0]) / (topRightMercator[0] - topLeftMercator[0]) * viewSize);
                trainlineDepotStationVo.setY((topLeftMercator[1] - mercator[1]) / (topRightMercator[1] - bottomLeftMercator[1]) * viewSize);
                stationVos.add(trainlineDepotStationVo);
            }
            trainlineDepotStationVos.add(stationVos);
        }
        data.put("trainlineDepotStationVos", trainlineDepotStationVos);
        String json = JsonUtil.toString(data);
        FileOutputStream outputStream = new FileOutputStream("D://develop//workspace//upload//file//trainlineDepotStationVos.json");
        outputStream.write(json.getBytes("utf-8"));
        outputStream.close();
    }

    //获得路局adjoin point的Gis坐标
    //Gis坐标采用腾讯地图坐标格式(lat,lng)
    private double[][][] getTrainlineDepots() {
        double[][][] trainlineDepots = new double[][][]{
                //哈局
                {
                        {47.34063, 123.99609},
                        {47.41355, 124.07158},
                        {47.46514, 124.13184},
                        {47.55242, 124.23331},
                        {47.68018, 124.382013},
                        {47.79733, 124.46065},
                        {47.86254, 124.50104},
                        {47.961114, 124.546882},
                        {48.10377, 124.61689},
                        {48.20552, 124.63061},
                        {48.2797, 124.635328},
                        {48.35077, 124.69738},
                        {48.40917, 124.7865},
                        {48.48135, 124.84837},
                        {48.56174, 124.96366},
                        {48.65098, 125.00642},
                        {48.80758, 125.13612},
                        {48.881831, 125.256614},
                        {48.90271, 125.27259},
                        {49.01576, 125.24242},
                        {49.06912, 125.28252},
                        {49.107998, 125.254816},
                        {49.17795, 125.24987},
                        {49.30222, 125.21666},
                        {49.42164, 125.15532},
                        {49.51356, 124.98218},
                        {49.56732, 124.89396},
                        {49.66596, 124.78928},
                        {49.71214, 124.66093},
                        {49.748792, 124.609543},
                        {49.78974, 124.49806},
                        {49.889119, 124.374837},
                        {50.02592, 124.3291},
                        {50.15425, 124.25113},
                        {50.24729, 124.19497},
                        {50.32429, 124.16335},
                        {50.420295, 124.126412}
                },
                {
                        {47.79733, 124.46065},
                        {47.86613, 124.86509},
                        {47.87446, 125.28667},
                        {47.961733, 125.680463},
                        {48.01624, 125.85076},
                        {48.16059, 126.21137},
                        {48.23531, 126.51147},
                        {46.64459, 127.0155},
                        {46.75894, 127.01079},
                        {46.798833, 127.0028},
                        {46.92264, 126.99665},
                        {47.06236, 127.0054},
                        {47.24905, 127.08273},
                        {47.410848, 127.112603},
                        {47.467142, 126.994274},
                        {47.59411, 126.94133},
                        {47.67005, 126.87794},
                        {47.76795, 126.76066},
                        {47.93929, 126.75494},
                        {48.06228, 126.73204},
                        {48.23531, 126.51147},
                        {48.356626, 126.528074},
                        {48.49005, 126.64143},
                        {48.62941, 126.61767},
                        {48.68628, 126.71612}
                },
                {
                        {45.29255, 130.27019},
                        {45.26077, 130.34253},
                        {45.21519, 130.35988},
                        {45.22863, 130.40824},
                        {45.213346, 130.480267},
                        {45.20541, 130.54023},
                        {45.21638, 130.589},
                        {45.24761, 130.58650},
                        {45.27744, 130.62657},
                        {45.29714, 130.70542},
                        {45.34086, 130.77417},
                        {45.34618, 130.83288},
                        {45.30474, 130.97814},
                        {45.30474, 130.97814},
                        {45.21959, 130.92962},
                        {45.30474, 130.97814},
                        {45.30474, 130.97814},
                        {45.25302, 131.15215},
                        {45.33987, 131.47905},
                        {45.40921, 131.6247},
                        {45.45096, 131.67627},
                        {45.5151, 131.82378},
                        {45.55144, 131.8738},
                        {45.63774, 131.90543},
                        {45.70228, 132.06412},
                        {45.71656, 132.28687},
                        {45.72363, 132.41609},
                        {45.74582, 132.80612},
                        {45.7647, 132.97725},
                        {46.042443, 132.951419},
                        {46.20028, 133.08414}
                },
                {
                        {44.590847, 129.612842},
                        {44.645069, 129.658569},
                        {44.67767, 129.67508},
                        {44.76739, 129.6871},
                        {44.87782, 129.85863},
                        {45.02877, 129.97799},
                        {45.19029, 130.08967},
                        {45.29255, 130.27019},
                        {45.35486, 130.29472},
                        {45.39771, 130.43136},
                        {45.44628, 130.56909},
                        {45.51592, 130.61551},
                        {45.569288, 130.622741},
                        {45.62773, 130.57644},
                        {45.71189, 130.54666},
                        {45.756, 130.55958},
                        {45.79682, 130.68438},
                        {45.79001, 130.83692},
                        {45.81145, 130.90213},
                        {45.756, 130.55958},
                        {45.84392, 130.58203},
                        {45.92253, 130.56388},
                        {46.00473, 130.53027},
                        {46.24393, 130.58458},
                        {46.422526, 130.660362},
                        {46.59744, 130.65765},
                        {46.73603, 130.48188},
                        {46.79093, 130.43704},
                        {46.80471, 130.38412}
                },
                {
                        {50.420295, 124.126412},
                        {50.580416, 123.730431},
                        {50.56263, 123.44212},
                        {50.58875, 123.20929},
                        {50.629778, 123.057932},
                        {50.65166, 122.4939},
                        {50.701467, 122.055427},
                        {50.62848, 121.55211},
                        {51.61062, 124.15938},
                        {51.73328, 123.57794},
                        {52.03548, 123.60583},
                        {52.11799, 123.66638},
                        {50.420295, 124.126412},
                        {50.52949, 124.24465},
                        {50.6133, 124.27518},
                        {50.79539, 124.28675},
                        {50.93577, 124.31494},
                        {51.00509, 124.20043},
                        {51.0672, 124.18886},
                        {51.21203, 124.215},
                        {51.32641, 124.32456},
                        {51.451529, 124.289905},
                        {51.68072, 124.41326},
                        {51.79083, 124.53664},
                        {51.9496, 124.61441},
                        {52.04354, 124.69409},
                        {52.24271, 124.72297},
                        {52.33308, 124.69971},
                        {52.33308, 124.69971},
                        {52.49433, 124.606392},
                        {52.59191, 124.54176},
                        {52.64796, 124.2246},
                        {52.70391, 123.87939},
                        {52.76092, 123.56952},
                        {52.86901, 123.33718},
                        {52.8625, 123.16516},
                        {52.93235, 122.79269},
                        {52.96479, 122.63071},
                        {52.98848, 122.50777},
                        {52.94687, 122.36133},
                        {52.33308, 124.69971},
                        {52.43719, 125.40884},
                        {52.07121, 125.75056}
                },
                {
                        {45.22063, 126.18916},
                        {45.397356, 126.293256},
                        {45.54478, 126.39297},
                        {45.671137, 126.54481},
                        {45.675695, 126.619724},
                        {45.675695, 126.619724},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.671137, 126.54481},
                        {45.85445, 126.47676},
                        {45.88549, 126.46375},
                        {44.92212, 127.14369},
                        {45.1697, 126.99758},
                        {45.229725, 126.89702},
                        {45.3507, 126.73683},
                        {45.46176, 126.672938},
                        {45.585945, 126.675728},
                        {45.64455, 126.68562},
                        {45.675695, 126.619724},
                        {45.675695, 126.619724},
                        {45.69562, 126.68295}
                },
                {
                        {45.416669, 127.162524},
                        {45.33457, 127.31891},
                        {45.32069, 127.406812},
                        {45.276872, 127.522563},
                        {45.22245, 127.85116},
                        {45.208095, 127.961752},
                        {45.062705, 128.079023},
                        {45.00081, 128.26903},
                        {44.952142, 128.380373},
                        {44.793852, 128.487208},
                        {44.92859, 128.615092},
                        {44.93745, 128.7702},
                        {44.93183, 128.89999},
                        {44.91438, 128.96821},
                        {44.81452, 129.07375},
                        {44.63954, 129.06863},
                        {44.57917, 129.10159},
                        {44.5321, 129.26871},
                        {44.56718, 129.38811},
                        {44.56547, 129.49791},
                        {44.590847, 129.612842},
                        {44.5321, 129.26871},
                        {44.46717, 128.93511}
                },
                {
                        {47.13342, 129.28038},
                        {47.17477, 129.33661},
                        {47.42339, 129.42332},
                        {47.47909, 129.30947},
                        {47.56723, 129.26425},
                        {47.63692, 129.14111},
                        {47.72953, 128.92623},
                        {47.83134, 128.8338},
                        {47.858815, 128.841828},
                        {47.98307, 129.02645},
                        {48.11202, 129.23755},
                        {48.23599, 129.38521},
                        {48.29162, 129.52688},
                        {48.45545, 129.58119},
                        {48.59225, 129.43436},
                        {46.80471, 130.38412},
                        {46.79093, 130.43704},
                        {46.77446, 130.55228},
                        {46.77529, 130.62714},
                        {46.7763, 130.72462},
                        {46.80897, 130.89173},
                        {46.78762, 131.00493},
                        {46.72217, 131.13432},
                        {46.64536, 131.15181},
                        {46.72217, 131.13432},
                        {46.73884, 131.21738},
                        {46.72678, 131.37458},
                        {46.725655, 131.595261},
                        {46.76618, 131.81507},
                        {46.9199, 131.95362},
                        {47.09495, 131.87358},
                        {47.23751, 132.04697},
                        {47.30822, 132.33716},
                        {47.30967, 132.49792},
                        {47.24659, 132.63645},
                        {47.36203, 133.06351},
                        {47.54417, 133.12455},
                        {47.59232, 133.51638},
                        {47.61647, 133.88536},
                        {47.686488, 134.122425},
                        {47.97119, 134.15775},
                        {48.25759, 134.50086},
                        {48.34597, 134.40216},
                        {46.76618, 131.81507},
                        {46.353672, 132.202279},
                        {47.30822, 132.33716},
                        {47.62898, 132.50733}
                },
                {
                        {49.22976, 119.74375},
                        {49.21222, 119.60817},
                        {49.15447, 119.32942},
                        {49.18685, 118.89733},
                        {49.20186, 118.4665},
                        {49.25645, 118.09969},
                        {49.443806, 117.769344},
                        {49.47199, 117.69526},
                        {49.549168, 117.546971},
                        {49.58513, 117.4504},
                        {49.58513, 117.4504},
                        {49.58513, 117.4504},
                        {49.24078, 120.86559},
                        {49.27941, 120.73767},
                        {49.29166, 120.83121},
                        {49.4087, 121.13219},
                        {49.47965, 121.31975},
                        {49.56614, 121.38501},
                        {49.71077, 121.24745},
                        {49.886663, 121.39512},
                        {50.027911, 121.629363},
                        {50.211223, 121.785926},
                        {50.307473, 121.760966},
                        {50.48332, 121.67432},
                        {50.62848, 121.55211},
                        {50.62848, 121.55211},
                        {50.680432, 121.518983},
                        {50.776935, 121.509846},
                        {50.71733, 121.995152},
                        {51.33482, 121.49876},
                        {51.6879, 121.87961},
                        {52.03674, 122.07474},
                        {50.71733, 121.995152},
                        {50.99318, 121.07843},
                        {51.23413, 120.80998}
                },
                {
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.16169, 123.81447},
                        {47.21072, 123.64633},
                        {47.24684, 123.54979},
                        {47.285, 123.27395},
                        {47.32965, 123.17891},
                        {47.44976, 122.98831},
                        {47.51552, 122.88327},
                        {47.588703, 122.874382},
                        {47.74862, 122.83933},
                        {47.93687, 122.80187},
                        {48.01777, 122.74401},
                        {48.080834, 122.692351},
                        {48.11638, 122.42548},
                        {48.178562, 122.366971},
                        {48.31574, 122.33349},
                        {48.38641, 122.26119},
                        {48.54997, 122.14406},
                        {48.68013, 122.02648},
                        {48.01777, 122.74401}
                },
                {
                        {45.90966, 126.64352},
                        {45.99833, 126.6475},
                        {46.09743, 126.77066},
                        {46.19663, 126.8173},
                        {46.2992, 126.89623},
                        {46.37111, 127.00418},
                        {46.44393, 127.12062},
                        {46.53594, 127.12258},
                        {46.64459, 127.0155},
                        {46.73985, 127.216},
                        {46.88218, 127.38815},
                        {46.88768, 127.5034},
                        {46.974024, 127.76095},
                        {46.96976, 127.92547},
                        {46.97443, 128.05371},
                        {46.9252, 128.17957},
                        {46.93648, 128.32392},
                        {46.94297, 128.42772},
                        {47.00074, 128.51331},
                        {47.01648, 128.65265},
                        {46.959203, 128.878111},
                        {47.02348, 129.02092},
                        {47.09719, 129.11921},
                        {47.13342, 129.28038}
                },
                {
                        {45.93917, 124.5867},
                        {45.99461, 124.57087},
                        {46.14815, 124.63159},
                        {46.21091, 124.68092},
                        {46.34373, 124.76301},
                        {46.49119, 124.85422},
                        {46.57319, 124.85016},
                        {46.64197, 124.84895},
                        {46.65705, 124.88645},
                        {46.64197, 124.84895},
                        {46.659139, 124.884619},
                        {46.39201, 123.41174},
                        {46.60312, 123.54703},
                        {46.7712, 123.68502},
                        {46.876758, 123.750492},
                        {46.9873, 123.77428},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372}
                },
                {
                        {45.88549, 126.46375},
                        {45.931386, 126.344789},
                        {45.966534, 126.216858},
                        {46.01176, 126.09797},
                        {46.07762, 125.99874},
                        {46.07762, 125.99874},
                        {46.12737, 125.87728},
                        {46.22168, 125.64087},
                        {46.33587, 125.42372},
                        {46.40272, 125.32167},
                        {46.46967, 125.21794},
                        {46.533894, 125.124365},
                        {46.59868, 125.01187},
                        {46.65705, 124.88645},
                        {46.659139, 124.884619},
                        {46.659139, 124.884619},
                        {46.70807, 124.78008},
                        {46.81627, 124.56137},
                        {46.870471, 124.447508},
                        {47.00388, 124.16235}
                },
                {
                        {45.671137, 126.54481},
                        {45.85445, 126.47676},
                        {45.88549, 126.46375},
                        {45.671137, 126.54481},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.761089, 126.631908},
                        {45.850749, 126.535947},
                        {45.850749, 126.535947},
                        {45.88549, 126.46375},
                        {45.90966, 126.64352},
                        {45.87694, 126.63777},
                        {45.83956, 126.6679},
                        {45.788051, 126.712183},
                        {45.79217, 127.15007},
                        {45.794935, 126.687503},
                        {45.761089, 126.631908},
                        {45.738869, 126.905616},
                        {45.794935, 126.687503},
                        {45.761089, 126.631908},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.850749, 126.535947}
                },
                {
                        {45.761089, 126.631908},
                        {45.73032, 126.65026},
                        {45.72127, 126.67951},
                        {45.675695, 126.619724},
                        {45.675695, 126.619724},
                        {45.675695, 126.619724},
                        {45.675695, 126.619724},
                        {45.69562, 126.68295},
                        {45.72127, 126.67951},
                        {45.788051, 126.712183},
                        {45.71179, 126.76783},
                        {45.69977, 126.83657},
                        {45.55124, 126.99634},
                        {45.482597, 127.076409},
                        {45.416669, 127.162524},
                        {45.33457, 127.31891},
                        {45.69977, 126.83657},
                        {45.70619, 126.89367},
                        {45.71985, 126.99464}
                },
                {
                        {47.98665, 121.19277},
                        {48.33608, 121.35854},
                        {48.54619, 121.57705},
                        {48.68013, 122.02648},
                        {48.75425, 121.9187},
                        {48.820919, 121.672366},
                        {48.84586, 121.59017},
                        {48.84887, 121.48087},
                        {48.85881, 121.24881},
                        {48.918, 121.23354},
                        {49.09347, 121.05992},
                        {49.24078, 120.86559},
                        {49.27941, 120.73767},
                        {49.27815, 120.63929},
                        {49.23224, 120.55222},
                        {49.21734, 120.43194},
                        {49.19182, 120.08305},
                        {49.190375, 120.076292},
                        {49.25655, 119.82917},
                        {49.22976, 119.74375},
                        {49.25655, 119.82917},
                        {49.18523, 119.89299},
                        {48.65095, 119.78881},
                        {47.83055, 119.47755},
                        {47.2837, 119.80925}
                },
                {
                        {45.00862, 126.00262},
                        {45.425972, 126.27189},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.706914, 126.577897}
                },
                {
                        {43.87625, 129.28922},
                        {43.94118, 129.26133},
                        {44.020762, 129.205347},
                        {44.11804, 129.22531},
                        {44.1777, 129.33729},
                        {44.26746, 129.41204},
                        {44.35982, 129.46263},
                        {44.43541, 129.50163},
                        {44.54056, 129.55577},
                        {44.55307, 129.56553},
                        {44.590847, 129.612842},
                        {44.590847, 129.612842},
                        {44.58086, 129.69943},
                        {44.54072, 130.0193},
                        {44.59993, 130.35341},
                        {44.61306, 130.36073},
                        {44.671641, 130.448107},
                        {44.92666, 130.54219},
                        {45.08249, 130.68782},
                        {45.161162, 130.696087},
                        {45.23615, 130.78323},
                        {45.30474, 130.97814},
                        {45.30474, 130.97814},
                        {44.427743, 130.900126},
                        {44.406, 131.14083},
                        {44.406, 131.14083}
                },
                {
                        {47.13342, 129.28038},
                        {47.11519, 129.38033},
                        {46.97528, 129.48999},
                        {46.72831, 129.6388},
                        {46.65237, 129.75819},
                        {46.74728, 129.92618},
                        {46.85838, 130.16521},
                        {46.90149, 130.32004},
                        {46.873827, 130.322607},
                        {46.774104, 130.264436},
                        {46.80471, 130.38412},
                        {46.79093, 130.43704},
                        {46.90149, 130.32004},
                        {46.94774, 130.31659},
                        {47.05286, 130.29202},
                        {47.19691, 130.2919},
                        {47.29201, 130.28967},
                        {47.34053, 130.284676},
                        {47.43023, 130.52175},
                        {47.54151, 130.47021}
                },
                {
                        {47.00388, 124.16235},
                        {47.1169, 123.91405},
                        {47.15517, 123.89592},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.16169, 123.81447},
                        {47.21072, 123.64633},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.09832, 123.84372},
                        {47.16169, 123.81447},
                        {47.15517, 123.89592},
                        {47.25621, 123.96243},
                        {47.284527, 123.972052},
                        {47.34063, 123.99609},
                        {47.34063, 123.99609}
                },
                {
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.850749, 126.535947},
                        {45.850749, 126.535947},
                        {46.07762, 125.99874},
                        {46.40272, 125.32167},
                        {46.533894, 125.124365},
                        {46.65705, 124.88645},
                        {46.870471, 124.447508},
                        {47.08749, 123.98719},
                        {47.284527, 123.972052},
                        {47.34063, 123.99609},
                        {47.34063, 123.99609},
                        {47.284527, 123.972052},
                        {47.284527, 123.972052}
                },
                {
                        {44.590847, 129.612842},
                        {44.58086, 129.69943},
                        {44.56205, 129.86868},
                        {44.61306, 130.36073},
                        {44.671641, 130.448107},
                        {44.493, 130.75827},
                        {44.427743, 130.900126},
                        {44.406, 131.14083},
                        {44.406, 131.14083}},
                {
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.794935, 126.687503},
                        {45.79217, 127.15007},
                        {45.798376, 127.746365},
                        {45.788956, 127.993705},
                        {45.83792, 128.23005},
                        {45.82997, 128.49774},
                        {45.86757, 128.80708},
                        {45.87771, 129.03343},
                        {45.98874, 129.27452},
                        {46.14856, 129.46523},
                        {46.27888, 129.60776},
                        {46.51723, 129.85907},
                        {46.774104, 130.264436},
                        {46.80471, 130.38412}
                },
                {
                        {45.706914, 126.577897},
                        {45.706914, 126.577897},
                        {45.761089, 126.631908},
                        {45.794935, 126.687503},
                        {45.738869, 126.905616},
                        {45.589434, 127.023475},
                        {45.28023, 127.47794},
                        {45.19574, 127.95282},
                        {45.08543, 128.086177},
                        {44.93392, 128.36421},
                        {44.91055, 128.58623},
                        {44.79076, 129.10765},
                        {44.61102, 129.37342},
                        {44.590847, 129.612842}
                }
        };
        return trainlineDepots;
    }

    private static class TrainlineDepotStationVo {
        private String id;
        private String type = "circle";
        private double x = 0;
        private double y = 0;
        private double size = 0.3;
        private Map<String, Object> style;

        public TrainlineDepotStationVo() {
            style = new HashMap<>();
            style.put("stroke", "red");
            style.put("lineWidth", size);
            style.put("fill", "red");
            style.put("fillOpacity", 1);
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public Map<String, Object> getStyle() {
            return style;
        }

        public void setStyle(Map<String, Object> style) {
            this.style = style;
        }
    }
}
