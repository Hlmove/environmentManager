package com.briup.server;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/26-09-26-09:47
 * @Description：线程池工具类
 */
public class TestExecutors {
    public static void main(String[] args) {
        new ThreadPoolExecutor(5,10,0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        // 定长线程池 核心线程数和最大线程数相同 无限队列
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        // 缓存线程池 核心默认是0 最大无限制(Integer最大值)
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 单例线程 核心1 最大1 无限队列
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 定时线程池 核心指定 最大无限制
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        // 5秒后 按每秒1次执行线程任务
        scheduledExecutorService.scheduleAtFixedRate(()->{

                    System.out.println(Thread.currentThread().getName() + ":" + new Date());
                },
                5000,1000, TimeUnit.MILLISECONDS);
    }
}
