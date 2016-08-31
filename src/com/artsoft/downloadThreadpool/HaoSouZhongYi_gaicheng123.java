package com.artsoft.downloadThreadpool;

import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class HaoSouZhongYi_gaicheng123 {
	
	static ThreadPool pool = new ThreadPool(30);
	private static Proxy proxy = null;

	public static void mainProgram(int statnum, int endnum,int TV_TYPE) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectdim_film_zhongyi_hanziban(Integer.toString(statnum), Integer.toString(endnum));
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				String urlBranch = "";
				// ָ��
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
//					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
//							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")
//							+ "&area=%E5%85%A8%E5%9B%BD";
					
					if (listTemp.get(1).length()<=2) {
						urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")
								+ "%E7%BB%BC%E8%89%BA&area=%E5%85%A8%E5%9B%BD";
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					}else{
						urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")
								+ "&area=%E5%85%A8%E5%9B%BD";
						
					}
					HaosouBranch1(urlBranch, listTemp.get(0), listTemp.get(1), "3",TV_TYPE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("���б���url:" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				// ý���ע��
				try {
//					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
//							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8");
					
					if (listTemp.get(1).length()<=2) {
						urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")+"%E7%BB%BC%E8%89%BA";
					}else{
						
						urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8");
					}
					HaosouBranch1(urlBranch, listTemp.get(0), listTemp.get(1), "4",TV_TYPE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("���б���url:" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "���Ӿ�", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");
		}
	}
	
	private static void HaosouBranch1(String urlBranch, String string, String string2, String string3,int TV_TYPE) {
		// TODO Auto-generated method stub
		while (pool.getPoolNum() > 30) {
			try {
				System.out.println("�߳���������10���ȴ�5s");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
		pool.performTask(new HaoSouTVtask(urlBranch, string, string2, string3,TV_TYPE));

	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		IpFilter.mainip("http://index.haosou.com/");
		CommonUtil.setLog("ip����ʱ��" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		//select t.film_id,t.film_name from ods.dim_film t
		String returnNumTVle=OracleHaoSou.returnNumPeople("ODS.DIM_VARIETY");
		
		System.out.println("��Ҫ�ɼ���������Ϊ"+returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
			
			//0 ���Ӿ� 1 ����� 2 ���� 3 ��Ӱ
			int TV_TYPE=2;
			mainProgram(i, i + 1000,TV_TYPE);
		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}
	
	// �ж����ݿ�ʼʱ��
//		public static void TimingTime(int hh, int mm, int ss) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
//			calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
//			calendar.set(Calendar.SECOND, ss); // ������
//
//			Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00
//
//			Timer timer = new Timer();
//			timer.scheduleAtFixedRate(new TimerTask() {
//				public void run() {
//					System.out.println("-------�趨Ҫָ������--------");
//					runstatic();
//				}
//			}, time, 1000 * 60 * 60 * 12);// �����趨����ʱÿ��̶�ִ��
//		}

		
		public static void runstaticshijian(){
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
			System.out.println(TimeTest.getNowTime("HH"));
			if (TimeTest.getNowTime("HH").equals("11") || TimeTest.getNowTime("HH").equals("09") || TimeTest.getNowTime("HH").equals("13")) {
				runstatic();
			}
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
		}
		
		
		 /* ����ʱ��
		 * 2016��8��17��17:30:00
		 * @param args
		 */
		public static void rundingshitime(int hh , int mm ,int ss) {  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ  
	        calendar.set(Calendar.MINUTE, mm);       // ���Ʒ�  
	        calendar.set(Calendar.SECOND, ss);       // ������  
	  
	        Date time = calendar.getTime();         // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00  
	  
	        Timer timer = new Timer();  
	        timer.schedule(new TimerTask() {  
	            public void run() {  
	                System.out.println("-------�趨Ҫָ������--------");  
	                try {
						
	                	runstaticshijian();
					} catch (Exception e) {
						// TODO: handle exception
					}
	            } 
	        }, time, 1000 * 60 * 60 * 1);// �����趨����ʱÿ��̶�ִ��  
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 TimingTime(1, 00, 00);
		 rundingshitime(1, 00, 00);
//		runstatic();
	}

}
