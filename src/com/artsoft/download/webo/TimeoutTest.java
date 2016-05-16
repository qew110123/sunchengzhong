package com.artsoft.download.webo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutTest {
	public static String runing(String keyword) {
		String retuenString ="";
		final ExecutorService service = Executors.newFixedThreadPool(1);

		TaskThread taskThread = new TaskThread(keyword);
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

	public static void main(String[] args) {
		System.out.println(runing("http://data.weibo.com/index/ajax/contrast?key2=%25E5%25AD%2594%25E7%2590%25B3&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071"));
	}

}

class TaskThread implements Callable<Object> {
	private String t;

	public TaskThread(String temp) {
		this.t = temp;
	}

	public Object call() throws Exception {
		String result = "空结果";
		try {
			result=WeBoDajiewang.Weiboall(t);
			
			// System.out.println("任务开始....");
			// Thread.sleep(50000);
			// System.out.println(t);
//			 Search.xiaoUrl(t, "");
			 
			
			 
			// String numno=ConfigManager.getInstance().getConfigValue("NONUM");
			// System.out.println(numno+"8888888888888888888888888");
			// if ("0".equals(numno)) {
			// System.out.println("1111111111111");
			// Search.xiaoUrlbaidu4haoshixianshi(t, "");
			// Search.xiaoUrlbaidu5(t, "");
//			 Search.baidu(t, "");
			
			
			// 进行真实浏览器
			//2016年1月9日14:53:53
//			Websdrver.runbaidu(t);
			
			
			// 进行代理数据
			//2016年1月9日21:40:15
//			Iphtml.mainip(t);
			
			
			//进行代理手机百度的数据
		//2016年1月10日20:24:32
//			Iphtmlbaidushouji.mainip(t);
			
			// System.out.println();
//			 Search.xiaoUrlsogou(t, "");
			//进行代理s搜狗数据
			//2016年1月20日22:34:06
//			Iphtml.mainip(t);
			
			
			//2016年3月31日21:44:20
			//蚂蚁数据
//			 Search.mayi(t, "");
			 
			//2016年3月31日23:12:11
				//更改打开数据 手机百度
//			 Search.xiaoUrlbaidu20160331(t, "");
			 
			 
			 //2016年4月17日23:09:36
			//内自浏览器
//			 Search.HtmlUnitDriverbaidu(t, "");
			 
			
			// }else{
			// Search.xiaoUrlsogou(t, "");
			// }
			// Search.xiaoUrlsogou(t, "");
			// result = "正确结果";
			// System.out.println("任务结束....");
		} catch (Exception e) {
//			Websdrver.shuaxin();
//			System.out.println("Task is interrupted! ");
//			Websdrver.shuaxin();
//			Websdrver.WebDriverBranch();
		}
		return result;
	}

}