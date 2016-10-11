package com.artsoft.downloadThreadpool.moves;

import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.demo.DemoTime;
import com.artsoft.downloadThreadpool.HaoSouTVtask;
import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class MovesHaoSo_dianying {
	static ThreadPool pool = new ThreadPool(30);
	private static Proxy proxy = null;

	public static void mainProgram(int statnum, int endnum,int TV_TYPE) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectdim_film(Integer.toString(statnum), Integer.toString(endnum));
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
					if (listTemp.get(1).length()<=2) {
						urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")+"%e7%94%b5%e5%bd%b1"
								+ "&area=%E5%85%A8%E5%9B%BD";
					}else{
						urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")
								+ "&area=%E5%85%A8%E5%9B%BD";
					}
					
					HaosouBranch1(urlBranch, listTemp.get(0), listTemp.get(1), "5",TV_TYPE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("���б�����url:" + urlBranch);
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
					if (listTemp.get(1).length()<=2) {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")+"%e7%94%b5%e5%bd%b1";
					}
					else{
						urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
								+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8");
					}
					HaosouBranch1(urlBranch, listTemp.get(0), listTemp.get(1), "6",TV_TYPE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("���б�����url:" + urlBranch);
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

	// �е��Ӿ�����
	public static void HaosouBranch(String urlBranch, String tvplayId, String tyPlayName, String DataType,int TV_TYPE) {
		// proxy=DealProxy.getInstance().getPoxxy();
		// String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30,
		// "UTF-8", null, proxy);
		// if (strHtml == null || strHtml.equals("")) {
		// proxy=DealProxy.getInstance().getPoxxy();
		// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
		// null, proxy);
		// }
		// if (strHtml == null || strHtml.equals("")) {
		// return;
		// }
		String strHtml = urlreturnHtml(urlBranch);
//		System.out.println(strHtml);

		String strtext = HtmlAnalyze.getTagText(strHtml, "\":\"", "\"}");
		String starttime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");
		String endtime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");

//		System.out.println(strtext);
//		System.out.println(starttime);

		try {

			String[] sourceStrArray = strtext.toString().split("\\|");
			System.out.println(sourceStrArray.length);
			if (starttime != null && !"".equals(starttime)) {

				 try {
				 Thread.sleep(1000);
				 } catch (InterruptedException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 }

				// for (int i = 0; i < sourceStrArray.length; i++) {
				// // System.out.println(sourceStrArray[i]
				// // + DemoTime.getBeforeAfterDate(starttime, i).toString());
				// OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName,
				// sourceStrArray[i], "0",
				// DemoTime.getBeforeAfterDate(starttime,
				// i).toString().replaceAll("-", ""), urlBranch, "0",
				// DataType, "");
				//
				// }
				// System.out.println(OracleHaoSou.returnMaxdianshijudata());
				
				for (int i = sourceStrArray.length; i > sourceStrArray.length - 7; i--) {
					System.out.println(sourceStrArray[i - 1]);
					String palydate = DemoTime.getBeforeAfterDate(starttime, i).toString();
					System.out.println(palydate = palydate.replaceAll("-", ""));

					OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName, sourceStrArray[i - 1], "0", palydate, urlBranch,
							TV_TYPE, DataType, "");
					if (palydate.equals("20151111")) {
						return;
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���ѵ��Ӿ������������ ����ⱨ��,���б���");
//			System.out.println("���б�����url:" + urlBranch);
			try {
				Thread.sleep(1000 );
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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

	public static String urlreturnHtml(String urlBranch) {
		String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
		// null, proxy);
		boolean bb = true;
		int i = 0;
		while (bb) {
			proxy = DealProxy.getInstance().getPoxxy();
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 10, "UTF-8", null, proxy);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
				if (strHtml.contains("360ָ��_�����쳣����")) {
					bb = true;
					System.out.println(Thread.currentThread().getName());
					System.out.println("ip ��������" + proxy);
				}
			} else {
				System.out.println("�򿪳���" + i + "��,���ӣ�" + urlBranch);

			}
			if (i > 10) {
				bb = false;
			}

			i += 1;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strHtml;

	}
	
	/**
	    * �õ��������ڼ�ļ������
	    */
	public static String getTwoDay(String sj1, String sj2) {
	    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
	    long day = 0;
	    try {
	     java.util.Date date = myFormatter.parse(sj1);
	     java.util.Date mydate = myFormatter.parse(sj2);
	     day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	    } catch (Exception e) {
	     return "";
	    }
	    return day + "";
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		IpFilter.mainip("http://index.haosou.com/");
		CommonUtil.setLog("ip����ʱ��" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		//select t.film_id,t.film_name from ods.dim_film t
		String returnNumTVle=OracleHaoSou.returnNummove();
		System.out.println("��Ҫ�ɼ�����������Ϊ"+returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
			int TV_TYPE=3;
			mainProgram(i, i + 1000,TV_TYPE);
		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}

	// �ж����ݿ�ʼʱ��
//	public static void TimingTime(int hh, int mm, int ss) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
//		calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
//		calendar.set(Calendar.SECOND, ss); // ������
//
//		Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00
//
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			public void run() {
//				System.out.println("-------�趨Ҫָ������--------");
//				runstatic();
//			}
//		}, time, 1000 * 60 * 60 * 12);// �����趨����ʱÿ��̶�ִ��
//	}
	
	
	public static void runstaticshijian(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

		if (TimeTest.getNowTime("HH").equals("07") || TimeTest.getNowTime("HH").equals("09") || TimeTest.getNowTime("HH").equals("13")) {
//			CountNum.runCount();
			runstatic();
		}
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}
	
	
	/**
	 * ����ʱ��
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
//		runstatic();
//		 TimingTime(1, 00, 00);
		 rundingshitime(1, 00, 00);
		 
//		 runstatic();
		// IpFilter ipxi=new IpFilter;

//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
//		IpFilter.mainip("http://index.haosou.com/");
//		CommonUtil.setLog("ip����ʱ��" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
//		String returnNumTVle=OracleHaoSou.returnNumPeople("edw.dim_tvplay");
//		System.out.println("��Ҫ�ɼ�����������Ϊ"+returnNumTVle);
//		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
//			// i=15780;
//			mainProgram(i, i + 1000);
//		}
//
//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}

}