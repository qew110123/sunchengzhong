package com.artsoft.downloadThreadpool.people;

import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.admin.Thread_Main;
import com.artsoft.demo.DemoTime;
import com.artsoft.download.HaoSouPeopleThread;
import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.downloadThreadpool.MyHaoSoutask;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class HaoSouWordAdmin {
	static ThreadPool pool = new ThreadPool(100);
	private static Proxy proxy = null;

	public static void HaosouPeoPleBranch(String urlBranch, String tvplayId, String tyPlayName, String DataType) {
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
		
//		 String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30,
//				 "UTF-8", null, null);
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

//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

//				 for (int i = 0; i < sourceStrArray.length; i++) {
//				 // System.out.println(sourceStrArray[i] +
//				 // DemoTime.getBeforeAfterDate(starttime, i));
//				 OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i],
//				 DemoTime.getBeforeAfterDate(starttime,
//				 i).toString().replaceAll("-", ""), "", urlBranch,
//				 DataType);
//				 }

				// OracleHaoSou.returnMaxdianpeople();
//				for (int i = sourceStrArray.length; i > sourceStrArray.length - 7; i--) {
//					System.out.println(sourceStrArray[i - 1]);
//					String palydate = DemoTime.getBeforeAfterDate(starttime, i).toString();
//					System.out.println(palydate = palydate.replaceAll("-", ""));
//					// System.out.println(sourceStrArray[i-1] +
//					// DemoTime.getBeforeAfterDate(starttime, i-1));
//					OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i - 1], palydate, "", urlBranch, DataType);
//					if (palydate.equals("20151027")) {
//						return;
//					}
//				}
				
				String starttimeto=HtmlAnalyze.getTagText(starttime+"#", "to\":\"","#");
//				String palydateto=starttimeto.replaceAll("-", "");
				for (int i = 0; i < 7; i++) {
					String palydate = DemoTime.getBeforeAfterDate(starttimeto, 0-i).toString();
//					System.out.println(palydateto=starttimeto.replaceAll("-", ""));
					System.out.println(palydate = palydate.replaceAll("-", ""));
//					OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName, sourceStrArray[sourceStrArray.length -1- i], "0", palydate, urlBranch,
//							TV_TYPE, DataType, "");
					OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[sourceStrArray.length -1- i], palydate, "", urlBranch, DataType);
					
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�˷������ݺ���ⱨ��,");
			System.out.println("���б���url:" + urlBranch);
			try {
				Thread.sleep(1000 * 60 * 5);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	public static void runadminpool(String urlBranch, String tvplayId, String tyPlayName, String DataType) {
//		System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
		try {

			urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
					+ java.net.URLEncoder.encode(tyPlayName, "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
//			pool.performTask(new MyHaoSoutask(urlBranch, tvplayId, tyPlayName, "2"));
			HaosouPeoPleBranch(urlBranch, tvplayId, tyPlayName, "2");
		} catch (Exception e) {
			// TODO: handle exception
		}

		// ý���ע��
		try {
			urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
					+ java.net.URLEncoder.encode(tyPlayName, "utf-8");

//			System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
//			pool.performTask(new MyHaoSoutask(urlBranch, tvplayId, tyPlayName, "3"));
			HaosouPeoPleBranch(urlBranch, tvplayId, tyPlayName, "3");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * �˵����ݵĲɼ�
	 */

	private static void mainPeoPle(int statnum, int endnum) {
		List<String> listArray = OracleHaoSou.selectname360admin(Integer.toString(statnum), Integer.toString(endnum));
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				// ָ��
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					// HaosouPeoPleBranch(urlBranch, listTemp.get(0),
					// listTemp.get(1), "2");
					while (pool.getPoolNum() > 100) {
						try {
							System.out.println("�߳���������40���ȴ�5s");
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//���������ϵ�һ��
					//2016��4��5��14:00:55
					try {

//						urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
//								+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
						System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
						pool.performTask(new MyHaoSoutask("", listTemp.get(0), listTemp.get(1), ""));
					} catch (Exception e) {
						// TODO: handle exception
					}
//
//					// ý���ע��
//					try {
//						urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
//								+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
//
//						System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
//						pool.performTask(new MyHaoSoutask(urlBranch, listTemp.get(0), listTemp.get(1), "3"));
//					} catch (Exception e) {
//						// TODO: handle exception
//					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// System.out.println("���б����ȴ�5����" + urlBranch);
					System.out.println("���б���url:" + urlBranch);
					
//					try {
//						Thread.sleep(1000 * 60 * 5);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
					e.printStackTrace();
				}
				// ý���ע��
				// try {
				// urlBranch =
				// "http://index.haosou.com/index.php?a=soMediaJson&q="
				// + java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
				// // HaosouPeoPleBranch(urlBranch, listTemp.get(0),
				// // listTemp.get(1), "3");
				// // HaoSouPeopleThread mt = new HaoSouPeopleThread();
				// // Thread thread1 = new Thread(mt,
				// //
				// urlBranch+"##"+listTemp.get(0)+"##"+listTemp.get(1)+"##3");
				// // thread1.start();
				// while (pool.getPoolNum() > 10) {
				// try {
				// System.out.println("�߳���������10���ȴ�5s");
				// Thread.sleep(5000);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// }
				// System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
				// pool.performTask(new MyHaoSoutask(urlBranch, listTemp.get(0),
				// listTemp.get(1), "3"));
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				//// System.out.println("���б����ȴ�5����" + urlBranch);
				// System.out.println("���б���url:" + urlBranch);
				// try {
				// Thread.sleep(1000 * 60 * 5);
				// } catch (InterruptedException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				// e.printStackTrace();
				// }

			}
		}

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
					System.out.println("ip �������");
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
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
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
		String returnNumPeople = OracleHaoSou.returnNumPeople();
		System.out.println("��Ҫ�ɼ�����������Ϊ" + returnNumPeople);

		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			mainPeoPle(i, i + 1000);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}
	
	
	
	

	
	
	public static void runstaticshijian(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		System.out.println(TimeTest.getNowTime("HH"));
		if (Integer.valueOf(TimeTest.getNowTime("HH"))>=9) {
			runstatic();
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}
	
	// �ж����ݿ�ʼʱ��
		public static void TimingTime(int hh, int mm, int ss) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
			calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
			calendar.set(Calendar.SECOND, ss); // ������

			Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					System.out.println("-------�趨Ҫָ������--------");
//					runstatic();
					
					runstaticshijian();
				}
			}, time, 1000 * 60 * 60 * 1);// �����趨����ʱÿ��̶�ִ��
		}
		
		
		
		
		// �ж����ݿ�ʼʱ��
		public static void TimingTimeip(int hh, int mm, int ss) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
			calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
			calendar.set(Calendar.SECOND, ss); // ������

			Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					System.out.println("-------�趨Ҫָ������--------");
//					runstatic();
					IpFilter.mainip("http://index.haosou.com/");
					
					
				}
			}, time, 1000 * 60 * 30 * 1);// �����趨����ʱÿ��̶�ִ��
		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		
		
		new Thread(new haoSou_thread_admin("adminSou")).start();
		new Thread(new haoSou_thread_admin("ip")).start();

//		TimingTime(9, 00, 01);
//		// runstatic();
//		System.out.println("��������");
		
//		String urlBranch="http://index.so.com/index.php?a=soIndexJson&q=%E6%AC%A2%E4%B9%90%E9%A2%82&area=%E5%85%A8%E5%9B%BD";
//		String tvplayId="0";
//		String tyPlayName="������";
//		String DataType="3";
//		int TV_TYPE=0;
//		HaosouPeoPleBranch(urlBranch, tvplayId, tyPlayName, DataType);

	}

}
