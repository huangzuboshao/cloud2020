package com.liao.springcloud.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/5 9:46
 */
public class MaximumTest {
    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<DateFormat> df1 = ThreadLocal.withInitial(SimpleDateFormat::new);


    /**
     * 3个数的最大值
     *
     * @param x
     * @param y
     * @param z
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
