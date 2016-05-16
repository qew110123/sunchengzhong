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
		// System.out.println("�ύ����...begin");
		Future<Object> taskFuture = service.submit(taskThread);
		// System.out.println("�ύ����...end");
		try {
			Object re = taskFuture.get(5000, TimeUnit.MILLISECONDS);// ��ʱ���ã�100s
			// System.out.println(re);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			// System.out.println("��ʱ ȡ������");
			taskFuture.cancel(true);
			// System.out.println("��ʱ ȡ������OK");
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
		String result = "�ս��";
		try {
			result=WeBoDajiewang.Weiboall(t);
			
			// System.out.println("����ʼ....");
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
			
			
			// ������ʵ�����
			//2016��1��9��14:53:53
//			Websdrver.runbaidu(t);
			
			
			// ���д�������
			//2016��1��9��21:40:15
//			Iphtml.mainip(t);
			
			
			//���д����ֻ��ٶȵ�����
		//2016��1��10��20:24:32
//			Iphtmlbaidushouji.mainip(t);
			
			// System.out.println();
//			 Search.xiaoUrlsogou(t, "");
			//���д���s�ѹ�����
			//2016��1��20��22:34:06
//			Iphtml.mainip(t);
			
			
			//2016��3��31��21:44:20
			//��������
//			 Search.mayi(t, "");
			 
			//2016��3��31��23:12:11
				//���Ĵ����� �ֻ��ٶ�
//			 Search.xiaoUrlbaidu20160331(t, "");
			 
			 
			 //2016��4��17��23:09:36
			//���������
//			 Search.HtmlUnitDriverbaidu(t, "");
			 
			
			// }else{
			// Search.xiaoUrlsogou(t, "");
			// }
			// Search.xiaoUrlsogou(t, "");
			// result = "��ȷ���";
			// System.out.println("�������....");
		} catch (Exception e) {
//			Websdrver.shuaxin();
//			System.out.println("Task is interrupted! ");
//			Websdrver.shuaxin();
//			Websdrver.WebDriverBranch();
		}
		return result;
	}

}