package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_key.maoyan_key;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class maoyan_jibenxinxi_zhongpiaofang {
	
	
	
	public static void openstaticLiShiShuJu(String urlMain, String datetext) {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/?date="+timeText;
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			
			
			String keturlString ="";
			System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
			if (keturlString.equals("")) {
				maoyan_key.openkey();
				strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
				System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src:url(", ")"));
				
			}
			
			if (!keturlString.equals("")) {
				maoyan_key.openkey();
				strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
				
			}
		}

		Document doc = Jsoup.parse(strHtml);

		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.select("ul.canTouch");

		for (Element link : links) {

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

//			OracleMovePiaoFang.intotem_film_daily_boxoffice(data_date, text, strmainurl, uid, released_days,
//					total_boxoffice, real_time_boxoffice, boxoffice_rate, slice_rate, attendance_rate, 1, "", urlMain,
//					BOXOFFICE_TYPE);
			try {
				
				adminmaoyan.tem_dim_film_boxoffice(uid, text, strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			tem_film_daily_boxoffice_other(uid, text, strmainurl);
//			tem_daily_film_slice(uid, text, strmainurl, datetext);//
//			tem_film_city(uid, text, strmainurl, datetext);
//			tem_film_company(uid, text, strmainurl, datetext);
//			tem_audience_crowd(uid, text, strmainurl, datetext);
//			tem_want_see_index(uid, text, strmainurl);
//			tem_want_see_city(uid, text, strmainurl, datetext);

			// TimeTest tt = new TimeTest();
			// String datetext=tt.getNowTime("yyyy-MM-dd");
			// System.out.println(link.select("div.p-meta-title
			// a").attr("title"));
			// System.out.println(link.select("span.p-actor").text());
			// System.out.println(link.select("span.p-num").text());
			// System.out.println(link.select("span.p-status").text());
			//// DownYoukuMovie.youkuBranch(strmainurl);
		}

	}
	
	
	public static void openstatic() {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/";
		// openstaticLiShiShuJu(urlMain);
		// TimeTest.getBeforeAfterDate("2013-01-11", -1019);1535

		 for (int i = -1; i > -1535; i--) {
		 String datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"),
		 i).toString();
		 String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
		 System.out.println(urlMain);
		 try {
			
			 openstaticLiShiShuJu(urlMain, datetext);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 }
		 
		 

		// String datetext=TimeTest.getBeforeAfterDate("2016-03-15",
		// 0).toString();
		// String datetext = TimeTest.getNowTime("yyyy-MM-dd");
		 
		 
		 
		 /**
		  * 每日运行
		  * 2016年8月15日11:07:31
		  */
//		String datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"), -1).toString();
//		String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
//		// urlMain = "";
//		openstaticLiShiShuJu(urlMain, datetext);
		
		
	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
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
			}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(10, 59, 59);
//		runstatic();
	}

}
