package com.artsoft.download;

import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.artsoft.config.ConfigManager;
import com.artsoft.demo.DemoTime;
import com.artsoft.mythread.MyThread;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.Stirngstr;
import com.artsoft.util.TimeTest;
import com.artsoft.util.DealProxy;

public class HaoSouPeopleThread implements Runnable {
	
//	static ThreadPool pool = new ThreadPool(10);
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		String [] sstre=Thread.currentThread().getName().split("##");
		System.out.println(sstre[0]+sstre[1]+"*******"+sstre[2]+sstre[3]);
		HaosouPeoPleBranch(sstre[0], sstre[1], sstre[2], sstre[3]);
		
	}
	private static Proxy proxy = null;
	

	// 行电视剧数据
	public static void HaosouBranch(String urlBranch, String tvplayId, String tyPlayName, String DataType) {
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
					Thread.sleep(2000);
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

				for (int i = sourceStrArray.length; i > sourceStrArray.length - 7; i--) {
					System.out.println(sourceStrArray[i - 1]);
					String palydate = DemoTime.getBeforeAfterDate(starttime, i).toString();
					System.out.println(palydate = palydate.replaceAll("-", ""));

//					OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName, sourceStrArray[i - 1], "0", palydate, urlBranch,
//							"0", DataType, "");
					if (palydate.equals("20151111")) {
						return;
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("好搜电视剧分析数据日期 ，入库报错,运行报错，等待5分钟");
			try {
				Thread.sleep(1000 * 60 * 5);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

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

//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				// for (int i = 0; i < sourceStrArray.length; i++) {
				// // System.out.println(sourceStrArray[i] +
				// // DemoTime.getBeforeAfterDate(starttime, i));
				// OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i],
				// DemoTime.getBeforeAfterDate(starttime,
				// i).toString().replaceAll("-", ""), "", urlBranch,
				// DataType);
				// }

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
			System.out.println("人分析数据和入库报错,运行报错，等待5分钟");
			try {
				Thread.sleep(1000 * 60 * 5);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static void mainProgram(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.select(Integer.toString(statnum), Integer.toString(endnum));
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				// 指数
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")
							+ "&area=%E5%85%A8%E5%9B%BD";
					HaosouBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("运行报错，等待5分钟" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				// 媒体关注度
				try {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8");
					HaosouBranch(urlBranch, listTemp.get(0), listTemp.get(1), "4");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("运行报错，等待5分钟" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");
		}
	}

	/**
	 * 人的数据的采集
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
				// 指数
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
//					HaosouPeoPleBranch(urlBranch, listTemp.get(0), listTemp.get(1), "2");
					HaoSouPeopleThread mt = new HaoSouPeopleThread();
					Thread thread1 = new Thread(mt, urlBranch+"##"+listTemp.get(0)+"##"+listTemp.get(1)+"##2");
					thread1.start();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("运行报错，等待5分钟" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				// 媒体关注度
				try {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
//					HaosouPeoPleBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
					HaoSouPeopleThread mt = new HaoSouPeopleThread();
					Thread thread1 = new Thread(mt, urlBranch+"##"+listTemp.get(0)+"##"+listTemp.get(1)+"##3");
					thread1.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("运行报错，等待5分钟" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
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
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
			if (i > 10) {
				bb = false;
			}
			i += 1;
		}
		return strHtml;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
//		// ConfigManager config = ConfigManager.getInstance();
//		// driver = config.getConfigValue("driver");
//		String xx = ConfigManager.getInstance().getConfigValue("IDpeople");
//		// mainPeoPle();
//		int xxnum = Integer.parseInt(xx);
//		for (int i = xxnum; i < 16871; i = i + 1000) {
//			// i=15780;
//			mainPeoPle(i, i + 1000);
//		}
//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
//		System.out.println(urlreturnHtml("http://index.haosou.com/"));
		
		

	}

}
