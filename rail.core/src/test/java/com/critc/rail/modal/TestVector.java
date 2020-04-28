/**
 * Copyright 2019 靳磊. All rights reserved
 * Project Name:rail
 * Module Name:TODO:Module
 */
package com.critc.rail.modal;

import com.critc.util.date.DateUtil;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2019/11/13
 */
public class TestVector {
    @Test
    public void testTime() {
        System.out.println(DateUtil.getSystemTime());
        int count = 1000000;
        int total = 0;
        for (int index = 0; index < count; index++) {
            total += index * index;
        }
        System.out.println("vector total is " + total);
        System.out.println(DateUtil.getSystemTime());
    }

    @Test
    public void testArrayRealVetor() {
        System.out.println(DateUtil.getSystemTime());
        for (int time = 0; time < 20000; time++) {
            int count = 20000;
            ArrayRealVector vector = new ArrayRealVector(count);
//        vector.set(0);
            for (int index = 0; index < 2000; index++) {
                vector.setEntry(index, 1);
            }
            ArrayRealVector vector2 = new ArrayRealVector(count);
            vector2.set(1);
            double value = vector.dotProduct(vector2);
            System.out.println(value);
        }

        System.out.println(DateUtil.getSystemTime());
    }
}
