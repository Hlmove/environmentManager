package com.briup.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 2000, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5));
		
		for(int i=0;i<16;i++){
			MyRun myTask = new MyRun();
			//把要执行的任务交给线程池即可
			executor.execute(myTask);
			System.out.println("线程池中线程数目: "+executor.getPoolSize()+
								",队列中等待执行的任务数目: "+executor.getQueue().size()+
								",已执行完的任务数目: "+executor.getCompletedTaskCount());
		}
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//关闭线程池
		executor.shutdown();
	}
	
	static class MyRun implements Runnable{

		public void run() {
			for(int i=0;i<100;i++){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("任务结束");
		}
	}
}

