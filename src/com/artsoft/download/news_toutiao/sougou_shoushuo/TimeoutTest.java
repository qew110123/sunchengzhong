package com.artsoft.download.news_toutiao.sougou_shoushuo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutTest {
	public static String runing(String name, int num,String id) {
		String retuenString ="";
		final ExecutorService service = Executors.newFixedThreadPool(1);

		TaskThread taskThread = new TaskThread(name,num,id);
		// System.out.println("提交任务...begin");
		Future<Object> taskFuture = service.submit(taskThread);
		// System.out.println("提交任务...end");
		try {
			Object re = taskFuture.get(5000, TimeUnit.MILLISECONDS);// 超时设置，100s
			// System.out.println(re);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			// System.out.println("超时 取消任务");
			taskFuture.cancel(true);
			// System.out.println("超时 取消任务OK");
		} finally {
			service.shutdown();
		}
		try {
			retuenString=taskFuture.get().toString();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retuenString;

	}

//	public static void main(String[] args) {
//		System.out.println(runing("http://data.weibo.com/index/ajax/contrast?key2=%25E5%25AD%2594%25E7%2590%25B3&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071"));
//	}

}

class TaskThread implements Callable<Object> {
	private String name="";
	private int num=0;
	private String id="";

	public TaskThread(String name,int num,String id) {
		this.name = name;
		this.num = num;
		this.id = id;
	}

	public Object call() throws Exception {
		String result = "空结果";
		try {
//			result=WeBoDajiewang.Weiboall(t);
			sougou_shoushuo.runnewMain(name, 2, id);
			
		} catch (Exception e) {
		}
		return result;
	}

}