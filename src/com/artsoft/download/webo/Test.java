package com.artsoft.download.webo;

import java.util.concurrent.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 * Java线程：有返回值的线程
 * 
 * @author Administrator 2009-11-5 0:41:50
 */
public class Test {
	

	public static void main(String[] args) throws ExecutionException, InterruptedException {
//		// 创建一个线程池
//		// 创建两个有返回值的任务
//		Callable c1 = new MyCallable("A");
//		Callable c2 = new MyCallable("B");
//		// 执行任务并获取Future对象
//		Future f1 = pool.submit(c1);
//		Future f2 = pool.submit(c2);
//		try {
//			f1.get(3000, TimeUnit.SECONDS);
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			
//		}
//		// 从Future对象上获取任务的返回值，并输出到控制台
//		System.out.println(">>>" + f1.get().toString());
//		System.out.println(">>>" + f2.get().toString());
//		// 关闭线程池
//		// pool.shutdown();
		System.out.println("1111"+weoborun("http://data.weibo.com/index/ajax/contrast?key2=%25E5%25AD%2594%25E7%2590%25B3&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071"));
//		pool.shutdown();
//		try {
//			Weibo("http://data.weibo.com/index/ajax/contrast?key2=%25E5%25AD%2594%25E7%2590%25B3&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static String Weibo(String newUrl) throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		// String newUrl =
		// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
		HttpGet get = new HttpGet(newUrl);
		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
		// get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
		// rv:26.0) Gecko/20100101 Firefox/26.0");
		// get.addHeader("Host", "data.weibo.com");
		// get.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept", "*/*");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		get.addHeader(new BasicHeader("Cookie",
				"SINAGLOBAL=8549726845230.907.1445398578667; SUHB=0sqQ0pK3WBV2gN; SUB=_2AkMhLpLRdcNhrAFZmP0SzG3rbolXzQ7wu9_0M03fZ2JCMnoQgT5nqiRotBF_DN7Orke6kXahkJdZP3wN_xXCDM1NAU5yAAw.; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; DATA=usrmdinst_0; WBStore=8ca40a3ef06ad7b2|undefined; _s_tentry=www.baidu.com; Apache=3636198288224.295.1463024037215; ULV=1463024037240:30:4:3:3636198288224.295.1463024037215:1462874953123; PHPSESSID=m5v11jk24squ83afqf6m94grp7; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com"));

		get.addHeader("Host", "data.weibo.com");
		get.addHeader("Referer", "http://data.weibo.com/index/zone");
//		get.addHeader("Upgrade-Insecure-Requests", "1");
//		get.addHeader("Host", "data.weibo.com");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");

		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();
		return responseString;
	}
	
	
	
	public static String weoborun(String newUrl) throws ExecutionException, InterruptedException{
//		System.out.println(newUrl);
		 ExecutorService pool = Executors.newFixedThreadPool(100);
		Callable c1 = new MyCallable(newUrl);
		Future f1 = pool.submit(c1);
		try {
			f1.get(5, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			pool.shutdown();
			return "";
		}
//		pool.shutdown();
		return  f1.get().toString();
	}
}



class MyCallable implements Callable {
	private String newUrl;

	MyCallable(String newUrl) {
		this.newUrl = newUrl;
	}

	@Override
	public Object call() throws Exception {
		
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		System.out.println(newUrl);
		// String newUrl =
		// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
		HttpGet get = new HttpGet(newUrl);
		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
		// get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
		// rv:26.0) Gecko/20100101 Firefox/26.0");
		// get.addHeader("Host", "data.weibo.com");
		// get.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept", "*/*");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		get.addHeader(new BasicHeader("Cookie",
				"SINAGLOBAL=8549726845230.907.1445398578667; SUHB=0sqQ0pK3WBV2gN; SUB=_2AkMhLpLRdcNhrAFZmP0SzG3rbolXzQ7wu9_0M03fZ2JCMnoQgT5nqiRotBF_DN7Orke6kXahkJdZP3wN_xXCDM1NAU5yAAw.; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; DATA=usrmdinst_0; WBStore=8ca40a3ef06ad7b2|undefined; _s_tentry=www.baidu.com; Apache=3636198288224.295.1463024037215; ULV=1463024037240:30:4:3:3636198288224.295.1463024037215:1462874953123; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; PHPSESSID=m5v11jk24squ83afqf6m94grp7"));

		get.addHeader("Host", "data.weibo.com");
		get.addHeader("Referer",
				"http://data.weibo.com/index/hotword?wid=1091324230349&wname=%E6%AC%A2%E4%B9%90%E9%A2%82");
		// get.addHeader("Upgrade-Insecure-Requests", "1");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");

		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();
		return responseString;
	}
}
