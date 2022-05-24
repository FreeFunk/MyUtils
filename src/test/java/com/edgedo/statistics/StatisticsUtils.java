package com.edgedo.statistics;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:Qiutianzhu
 * @Date 2022-05-24 11:21
 * @Description: 常规平均值，方差等计算工具
 */
public class StatisticsUtils {


    public static void main(String[] args){
        List<Double> doubles = Arrays.asList(new Double[]{25d,45d,90d,21d});
        //平均值
        getAverage(doubles);
        //方差
        getVariance(doubles);
        //最大值
        getMax(doubles);
        //最小值
        getMin(doubles);
        //总和
        getSum(doubles);



    }

    private static void getSum(List<Double> doubles) {
        double sum = doubles.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("总和：" + sum);
    }

    private static void getMin(List<Double> doubles) {
        double min = doubles.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
        System.out.println("最小值：" + min);
    }

    private static void getMax(List<Double> doubles) {
        double max = doubles.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
        System.out.println("最大值：" + max);
    }

    private static void getVariance(List<Double> doubles) {
        double avg = doubles.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
        double variance = doubles.stream().mapToDouble(i -> Math.pow(i - avg, 2)).average().getAsDouble();
        System.out.println("方差：" + variance);
    }

    private static void getAverage(List<Double> doubles) {
        double valueDouble = doubles.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
        System.out.println("平均值：" + valueDouble);
    }

}
