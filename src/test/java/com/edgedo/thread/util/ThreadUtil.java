package com.edgedo.thread.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Qiutianzhu
 * @Date 2022-02-09 14:36
 * @Description: 线程工具使用模板
 */
public class ThreadUtil {

    /** 获取当前终端cpu个数 **/
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /** 定义线程数，最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目 **/
    private static final int THREAD_COUNT = CPU_COUNT * 2;

    /** 定义线程池 **/
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    /**
     * @Author:Qiutianzhu
     * @Description: 此业务循环方法，适合用于基于一个方法或者一个全局对象使用时控制先入先用的原则
     * @Date: 2022/2/9 17:18
     **/
    public static synchronized void makeLoad() {

        for (int taskCount = 0; taskCount < THREAD_COUNT; ++taskCount) {
            executorService.submit(() -> {
                /**1.excel，数据文件，获取数据写入文本，
                 * 就好比 40000字，分一个人写(单线程，利用for、foreach)，出现内存溢出(gc)，性能慢
                 * 如果分10个人去写(多线程，利用thread)，线程安全问题，写入覆盖，内容顺序还需要考虑写入无序问题
                 * 锁synchronized去解决先入先写的原则，但是这样的写入就变成了一个单线程 = n个单线程，
                 * 这就涉及到集合写入控制因为不可能全怼到一个集合会出现内存溢出，而分出去多线程看上去不会内存溢出
                 * 核心工作就是 2 = 1 + 1 ，
                 *
                 */

            });
        }
    }

    /**
     * 用一次关一次，也可以控制一直存在在springboot这类项目中，停止服务时可以在关闭
     */
    public static void stopThread(){
        //3.关闭线程
        try {
            executorService.shutdown();
            // (所有的任务都结束的时候，返回TRUE)
            if(!executorService.awaitTermination(1200000, TimeUnit.MILLISECONDS)){
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            executorService.shutdownNow();
        }
    }
}
