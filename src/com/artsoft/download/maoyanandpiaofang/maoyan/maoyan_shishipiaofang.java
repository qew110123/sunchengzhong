package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.io.IOException;
import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_FILM_BOXOFFICE_REALTIME;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.oracle2.DBManager;
import com.artsoft.oracle2.DateUtil;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;
import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_key.maoyan_key;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_xunzhe.zhongguopiaofangwang_xunzhe;
import com.artsoft.downloadThreadpool.people.haoSou_thread_admin;

public class maoyan_shishipiaofang {

	private static final List<String> Objstring = null;

	public static String Stringhtml_int(String keturlString, String htmls, String url) {
		String htmls_num = htmls;
		boolean bb = true;
		int iii = 0;
		while (bb) {
			List<List> listArray1 = Oracle.selectmayao_key(keturlString);

			if (listArray1 != null && listArray1.size() > 0) {
				for (List listArray : listArray1) {

					// List<String> listArray =listArray.get(0);
					System.out.println(listArray.toString());
					System.out.println(listArray.get(0));
					System.out.println(listArray.get(10));
					String[] numstaticnum = { listArray.get(10).toString(), listArray.get(1).toString(),
							listArray.get(2).toString(), listArray.get(3).toString(), listArray.get(4).toString(),
							listArray.get(5).toString(), listArray.get(6).toString(), listArray.get(7).toString(),
							listArray.get(8).toString(), listArray.get(9).toString() };
					String[] numstaticnum1 = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

					for (int i = 0; i < 10; i++) {
						htmls_num = htmls_num.replace(numstaticnum[i], numstaticnum1[i]);
						// System.out.println(numstaticnum[i]);
						// System.out.println(numstaticnum1[i]);
						// System.out.println(htmls_num);
					}
				}
				bb = false;

			} else {

//				try {
//					
//					boolean truebb = maoyan_key.openkey();
//
//					if (truebb) {
//
//						if (iii % 5 == 4) {
//							try {
//								
//								String strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//
//								// String keturlString ="";
//								System.out.println(keturlString = HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
//
//								iii = 0;
//								
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//							
//						}
//						iii = iii + 1;
//					} else {
//						bb = false;
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
					bb = false;
//				}

			}
		}

		return htmls_num;
	}

	public static void openstaticLiShiShuJu(String urlMain, String datetext) {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/?date="+timeText;
		String strHtml = "";
		// Document doc = null ;
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://piaofang.maoyan.com/";

			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {

			// Proxy proxy = null;
			//
			// proxy = DealProxy.getInstance().getPoxxy();
			// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 10, "UTF-8",
			// null, proxy);

			strHtml = urlreturnHtml(urlMain);

			// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8",
			// null, null);

			String keturlString = "";
			System.out.println(keturlString = HtmlAnalyze.getTagText(strHtml, "src:url(", ")"));

			if (!keturlString.equals("")) {
				maoyan_key.openkey();
				strHtml = Stringhtml_int(keturlString, strHtml, urlMain);

			}
			// System.out.println(strHtml);
			// strHtml=Jsoup.connect(urlMain);
			// doc =(Document) Jsoup.connect(urlMain);
			// try {
			// doc = Jsoup.connect(urlMain).get();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}

		String REAL_DATE = HtmlAnalyze.getTagText(strHtml, "上次更新", "</div>");

		Document doc = Jsoup.parse(strHtml);

		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.select("ul.canTouch");

		for (Element link : links) {

			TEM_FILM_BOXOFFICE_REALTIME realitme = new TEM_FILM_BOXOFFICE_REALTIME();
			// realitme

			String strmainurl = "";
			System.out.println(strmainurl = link.attr("data-com"));
			System.out.println(strmainurl = "http://piaofang.maoyan.com"
					+ strmainurl.replace("hrefTo,href:", "").replace("'", ""));
			String text = "";
			System.out.println(text = link.select("li.c1 b").text());

			String uid = "";
			System.out.println(uid = HtmlAnalyze.getTagText(strmainurl, "movie/", "?"));
			String released_days = "";
			System.out.println(released_days = HtmlAnalyze.getTagText(link.select("li.c1").text(), "上映", "天"));
			if (released_days.equals("")) {
				System.out.println(released_days = "1");
			}

			String total_boxoffice = "";//
			// System.out.println(link.select("li.c1").toString());
			System.out.println(total_boxoffice = HtmlAnalyze.getTagText(link.select("li.c1").toString(), "1rem\">",
					"</em>", true, 0));

			if (!total_boxoffice.equals("") && total_boxoffice.contains("class")) {
				total_boxoffice = HtmlAnalyze.getTagText(total_boxoffice.toString(), "\">", "</i>");
			}

			String real_time_boxoffice = "";
			System.out.println(real_time_boxoffice = link.select("li.c2").text());

			String boxoffice_rate = "";
			System.out.println(boxoffice_rate = link.select("li.c3").text());

			String slice_rate = "";
			System.out.println(slice_rate = link.select("li.c4").text());

			String attendance_rate = "";
			System.out.println(attendance_rate = link.select("li.c5").text());
			// intotem_film_total_boxoffice

			String BOXOFFICE_TYPE = "";
			// System.out.println(BOXOFFICE_TYPE = link.select("li.c5").text());
			// try {
			// BOXOFFICE_TYPE = link.select("li.c1 em").text();
			try {
				BOXOFFICE_TYPE = link.select("li.c1 i").first().text();

			} catch (Exception e) {
				// TODO: handle exception
			}
			if (BOXOFFICE_TYPE.equals("")) {
				BOXOFFICE_TYPE = link.select("li.c1 em").first().text();
			}
			// } catch (Exception e) {
			// // TODO: handle exception
			// }
			// if (BOXOFFICE_TYPE) {
			//
			// }
			if (!BOXOFFICE_TYPE.equals("上映首日")) {
				if (BOXOFFICE_TYPE.contains("上映")) {
					BOXOFFICE_TYPE = "正式";
				}
				if (BOXOFFICE_TYPE.equals("")) {
					BOXOFFICE_TYPE = "正式";
				}
			}

			System.out.println(BOXOFFICE_TYPE);

			// OracleMovePiaoFang.intotem_film_daily_boxoffice(data_date, text,
			// strmainurl, uid, released_days,
			// total_boxoffice, real_time_boxoffice, boxoffice_rate, slice_rate,
			// attendance_rate, 1, "", urlMain,
			// BOXOFFICE_TYPE);
			realitme.setDataDate(data_date);
			realitme.setTitle(text);
			realitme.setUrl(strmainurl);
			realitme.setFid(uid);
			realitme.setReleasedDays(released_days);
			realitme.setRealTimeBoxoffice(real_time_boxoffice);
			realitme.setBoxofficeRate(boxoffice_rate);
			realitme.setTotalBoxoffice(total_boxoffice);

			realitme.setREAL_DATE(REAL_DATE);

			realitme.setCollectionUrl(urlMain);
			realitme.setDATA_TYPE(0);
			;

			realitme.setSOURCE(1);
			String SHOW_DATE = "";

			try {
				String strHtmlxiangxi = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document docstrHtmlxiangxi = Jsoup.parse(strHtmlxiangxi);
				// SHOW_DATE =

				String atrxt = docstrHtmlxiangxi.select("p.info-release").first().text();
				String regEx = "[^0-9]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(atrxt);
				System.out.println(SHOW_DATE = m.replaceAll("").trim());
				realitme.setSHOW_DATE(SHOW_DATE);

				Thread.sleep(1000);

			} catch (Exception e) {
				// TODO: handle exception
			}

			OracleMovePiaoFang.intoTEM_FILM_BOXOFFICE_REALTIME(realitme);

			try {
				zhongguopiaofangwang_xunzhe.shoushuozhongguopiaofangwang(text);
			} catch (Exception e) {
				// TODO: handle exception
			}

			// tem_dim_film_boxoffice(uid, text, strmainurl);
			// tem_film_daily_boxoffice_other(uid, text, strmainurl);
			// tem_daily_film_slice(uid, text, strmainurl, datetext);//
			// tem_film_city(uid, text, strmainurl, datetext);
			// tem_film_company(uid, text, strmainurl, datetext);
			// tem_audience_crowd(uid, text, strmainurl, datetext);
			// tem_want_see_index(uid, text, strmainurl);
			// tem_want_see_city(uid, text, strmainurl, datetext);

			// TimeTest tt = new TimeTest();
			// String datetext=tt.getNowTime("yyyy-MM-dd");
			// System.out.println(link.select("div.p-meta-title
			// a").attr("title"));
			// System.out.println(link.select("span.p-actor").text());
			// System.out.println(link.select("span.p-num").text());
			// System.out.println(link.select("span.p-status").text());
			//// DownYoukuMovie.youkuBranch(strmainurl);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static Proxy proxy = null;

	private static String urlreturnHtml(String urlMain) {
		String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
		// null, proxy);
		// new Thread(new haoSou_thread_admin("ip")).start();
		boolean bb = true;
		int i = 0;
		while (bb) {
			proxy = DealProxy.getInstance().getPoxxy();
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 10, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
				if (strHtml.contains("360指数_访问异常出错")) {
					bb = true;
					System.out.println(Thread.currentThread().getName());
					System.out.println("ip 代理出错");
				}
			} else {
				System.out.println("打开出错" + i + "次,链接：" + urlMain);

			}
			if (i > 10) {
				bb = false;
			}

			i += 1;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strHtml;

	}

	public static void openstatic() {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/";
		// openstaticLiShiShuJu(urlMain);
		// TimeTest.getBeforeAfterDate("2013-01-11", -1019);1535

		// for (int i = -79; i > -1536; i--) {
		// String datetext = TimeTest.getBeforeAfterDate("2016-03-15",
		// i).toString();
		// String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
		// System.out.println(urlMain);
		// openstaticLiShiShuJu(urlMain, datetext);
		// }

		// String datetext=TimeTest.getBeforeAfterDate("2016-03-15",
		// 0).toString();
		// String datetext = TimeTest.getNowTime("yyyy-MM-dd");
		String datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"), 0).toString();
		String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
		// urlMain = "";
		openstaticLiShiShuJu(urlMain, datetext);
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		try {
			
			openstatic();

			if ((Integer.valueOf(TimeTest.getNowTime("HH")) == 23 && Integer.valueOf(TimeTest.getNowTime("mm")) > 30)
					|| (Integer.valueOf(TimeTest.getNowTime("HH")) == 0
							&& Integer.valueOf(TimeTest.getNowTime("mm")) < 30)) {
				System.out.println("存储过程运行不在运行时间内 ");
			} else {
				DBManager dbm = DBManager.instance();
				dbm.executeCall("call sp_f_film_boxoffice_realtime('" + DateUtil.getAfterDayDate(-1) + "') ");
				dbm.executeCall("call dwetl.MART_F_FILM_INDEX('" + DateUtil.getAfterDayDate(-1) + "') ");
				System.out.println("存储过程运行完毕 ");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
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
		}, time, 1000 * 60 * 30 * 1);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// runstatic();
		// openstatic();
		TimingTime(01, 00, 59);

	}

}
