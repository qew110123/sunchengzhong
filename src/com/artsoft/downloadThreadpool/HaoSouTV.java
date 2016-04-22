package com.artsoft.downloadThreadpool;

import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.demo.DemoTime;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class HaoSouTV {
	static ThreadPool pool = new ThreadPool(10);
	private static Proxy proxy = null;

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
					HaosouBranch1(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("运行报错，url:" + urlBranch);
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
					HaosouBranch1(urlBranch, listTemp.get(0), listTemp.get(1), "4");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("运行报错，url:" + urlBranch);
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
							"0", DataType, "");
					if (palydate.equals("20151111")) {
						return;
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("好搜电视剧分析数据日期 ，入库报错,运行报错");
//			System.out.println("运行报错，url:" + urlBranch);
			try {
				Thread.sleep(1000 );
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	private static void HaosouBranch1(String urlBranch, String string, String string2, String string3) {
		// TODO Auto-generated method stub
		while (pool.getPoolNum() > 10) {
			try {
				System.out.println("线程数量大于10，等待5s");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("当前启动线程thread:" + pool.getPoolNum());
		pool.performTask(new HaoSouTVtask(urlBranch, string, string2, string3));

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
				if (strHtml.contains("360指数_访问异常出错")) {
					bb = true;
					System.out.println(Thread.currentThread().getName());
					System.out.println("ip 代理出错" + proxy);
				}
			} else {
				System.out.println("打开出错" + i + "次,链接：" + urlBranch);

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
	    * 得到二个日期间的间隔天数
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
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		IpFilter.mainip("http://index.haosou.com/");
		CommonUtil.setLog("ip代理时间" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		String returnNumTVle=OracleHaoSou.returnNumPeople("edw.dim_tvplay");
		System.out.println("需要采集的人名字数为"+returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
			mainProgram(i, i + 1000);
		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		 TimingTime(1, 00, 00);
		// IpFilter ipxi=new IpFilter;

//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
//		IpFilter.mainip("http://index.haosou.com/");
//		CommonUtil.setLog("ip代理时间" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
//		String returnNumTVle=OracleHaoSou.returnNumPeople("edw.dim_tvplay");
//		System.out.println("需要采集的人名字数为"+returnNumTVle);
//		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
//			// i=15780;
//			mainProgram(i, i + 1000);
//		}
//
//		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

}
