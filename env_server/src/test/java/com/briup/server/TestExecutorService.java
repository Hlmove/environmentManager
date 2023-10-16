package com.briup.server;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/22-09-22-17:13
 * @Description：线程池实现类
 */
public class TestExecutorService {
    @Test
    public void test1(){
        // 20 任务
            // 核心线程 5个任务
            // 放阻塞队列 队列5任务
            // 根据最大线程数量创建10 5个任务
            // 拒绝策略
        // corePoolSize 核心线程数 池中初始化线程 6个任务
        // maxinumPoolSize 最大线程数 池子最多
        // KeepAliveTime 线程存活时间
        // 时间单位 1秒
        // 阻塞队列  生产消费队列 线程+数据结构
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

//        Executors.newFixedThreadPool(5);
    }
}
