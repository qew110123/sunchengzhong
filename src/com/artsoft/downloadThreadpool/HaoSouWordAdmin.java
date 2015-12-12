package com.artsoft.downloadThreadpool;

import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.demo.DemoTime;
import com.artsoft.download.HaoSouPeopleThread;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class HaoSouWordAdmin {
	static ThreadPool pool = new ThreadPool(10);
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
		String strHtml = urlreturnHtml(urlBranch);
		System.out.println(strHtml);

		String strtext = HtmlAnalyze.getTagText(strHtml, "\":\"", "\"}");
		String starttime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");
		String endtime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");

		System.out.println(strtext);
		System.out.println(starttime);

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
				// // System.out.println(sourceStrArray[i] +
				// // DemoTime.getBeforeAfterDate(starttime, i));
				// OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i],
				// DemoTime.getBeforeAfterDate(starttime,
				// i).toString().replaceAll("-", ""), "", urlBranch,
				// DataType);
				// }

				// OracleHaoSou.returnMaxdianpeople();
				for (int i = sourceStrArray.length; i > sourceStrArray.length - 7; i--) {
					System.out.println(sourceStrArray[i - 1]);
					String palydate = DemoTime.getBeforeAfterDate(starttime, i).toString();
					System.out.println(palydate = palydate.replaceAll("-", ""));
					// System.out.println(sourceStrArray[i-1] +
					// DemoTime.getBeforeAfterDate(starttime, i-1));
					OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i - 1], palydate, "", urlBranch, DataType);
					if (palydate.equals("20151027")) {
						return;
					}
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

	/**
	 * �˵����ݵĲɼ�
	 */

	private static void mainPeoPle(int statnum, int endnum) {
		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
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
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
					// HaosouPeoPleBranch(urlBranch, listTemp.get(0),
					// listTemp.get(1), "2");
					while (pool.getPoolNum() > 10) {
						try {
							System.out.println("�߳���������10���ȴ�5s");
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
					pool.performTask(new MyHaoSoutask(urlBranch, listTemp.get(0), listTemp.get(1), "2"));

				} catch (Exception e) {
					// TODO Auto-generated catch block
//					System.out.println("���б����ȴ�5����" + urlBranch);
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
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					// HaosouPeoPleBranch(urlBranch, listTemp.get(0),
					// listTemp.get(1), "3");
					// HaoSouPeopleThread mt = new HaoSouPeopleThread();
					// Thread thread1 = new Thread(mt,
					// urlBranch+"##"+listTemp.get(0)+"##"+listTemp.get(1)+"##3");
					// thread1.start();
					while (pool.getPoolNum() > 10) {
						try {
							System.out.println("�߳���������10���ȴ�5s");
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
					pool.performTask(new MyHaoSoutask(urlBranch, listTemp.get(0), listTemp.get(1), "3"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					System.out.println("���б����ȴ�5����" + urlBranch);
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
//		IpFilter.mainip("http://index.haosou.com/");
		CommonUtil.setLog("ip����ʱ��" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		String returnNumPeople=OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
		System.out.println("��Ҫ�ɼ�����������Ϊ"+returnNumPeople);
		
		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			mainPeoPle(i, i + 1000);
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
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
	}
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 TimingTime(23, 59, 59);
//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
//		IpFilter.mainip("http://index.haosou.com/");
//		CommonUtil.setLog("ip����ʱ��" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
//		String returnNumPeople=OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
//		System.out.println("��Ҫ�ɼ�����������Ϊ"+returnNumPeople);
//		
//		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
//			mainPeoPle(i, i + 1000);
//		}
//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");

	}

}
