package com.briup.server;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/26-09-26-09:08
 * @Description：阻塞队列
 */
public class TestBlockingQueue {
    public static void main(String[] args) throws Exception {
//        new TestBlockingQueue().testQueue();
        new TestBlockingQueue().testBlockingQuque();
    }
    // 阻塞队列 先进先出
    // 队列是空 再取会阻塞
    // 队列是满 再放入会阻塞
    public void testBlockingQuque() throws Exception {
        // 数组实现的阻塞队列 指定队列长度
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
        // 存值 put
        for (int i = 0; i < 4; i++) {
            queue.put(i);
        }
        // 取值
//        System.out.println(queue.take());// 取值 wait
//        System.out.println(queue.take());// 取值 wait
    }


    // 队列 先进先出
    public void testQueue(){
        Queue queue = new ArrayDeque();
        queue.offer("hello");
        queue.offer("world111");
        queue.offer("world"); // 存值
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll()); // null 没有值取出空值
//        System.out.println(queue.element()); // 取出元素头
        System.out.println("queue = " + queue);
    }
}
