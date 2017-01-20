package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.io.IOException;
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
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;
import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_key.maoyan_key;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_xunzhe.zhongguopiaofangwang_xunzhe;


public class maoyan_shishipiaofang {
	
	
	
	private static final List<String> Objstring = null;

	public static  String Stringhtml_int(String keturlString,String htmls,String url){
		String htmls_num=htmls;
		boolean bb=true;
		int iii=0;
		while (bb) {
			List<List> listArray1 =Oracle.selectmayao_key(keturlString);
			
			
			if (listArray1!=null&&listArray1.size()>0) {
				for (List listArray : listArray1) {
				
				
//				List<String> listArray =listArray.get(0);
				System.out.println(listArray.toString());
				System.out.println(listArray.get(0));
				System.out.println(listArray.get(10));
				String[] numstaticnum={listArray.get(10).toString(),listArray.get(1).toString(),listArray.get(2).toString(),listArray.get(3).toString(),
						listArray.get(4).toString(),listArray.get(5).toString(),listArray.get(6).toString(),listArray.get(7).toString(),listArray.get(8).toString(),listArray.get(9).toString()};
				String[] numstaticnum1={"0","1","2","3","4","5","6","7","8","9"};
				
				for (int i = 0; i < 10; i++) {
					htmls_num=htmls_num.replace(numstaticnum[i], numstaticnum1[i]);
	//				System.out.println(numstaticnum[i]);
	//				System.out.println(numstaticnum1[i]);
	//				System.out.println(htmls_num);
				}
				}
				bb=false;
				
			}else{
				
				maoyan_key.openkey();
				
				if (iii%5==4) {
					String strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
					
//					String keturlString ="";
					System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
					
					iii=0;
				}
				iii=iii+1;
				
			}
		}
		
		return htmls_num;
	}
	
	
	
	
	
	public static void openstaticLiShiShuJu(String urlMain, String datetext) {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/?date="+timeText;
		String strHtml = "";
//		Document  doc = null ;
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			
			
			String keturlString ="";
			System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
			
			if (!keturlString.equals("")) {
				maoyan_key.openkey();
				strHtml=Stringhtml_int(keturlString,strHtml,urlMain);
				
			}
//			System.out.println(strHtml);
//			strHtml=Jsoup.connect(urlMain);
//			 doc =(Document) Jsoup.connect(urlMain);
//			  try {
//				doc = Jsoup.connect(urlMain).get();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		String REAL_DATE= HtmlAnalyze.getTagText(strHtml, "上次更新", "</div>");
		

		Document doc = Jsoup.parse(strHtml);

		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.select("ul.canTouch");

		for (Element link : links) {
			
			
			
			TEM_FILM_BOXOFFICE_REALTIME  realitme=new TEM_FILM_BOXOFFICE_REALTIME();
//			realitme
			

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
			
			if (!total_boxoffice.equals("")&&total_boxoffice.contains("class")) {
				total_boxoffice= HtmlAnalyze.getTagText(total_boxoffice.toString(), "\">",
						"</i>");
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
			

//			OracleMovePiaoFang.intotem_film_daily_boxoffice(data_date, text, strmainurl, uid, released_days,
//					total_boxoffice, real_time_boxoffice, boxoffice_rate, slice_rate, attendance_rate, 1, "", urlMain,
//					BOXOFFICE_TYPE);
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
			realitme.setDATA_TYPE(0);;
			
			realitme.setSOURCE(1);
			String SHOW_DATE="";
			
			try {
				String strHtmlxiangxi = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document docstrHtmlxiangxi = Jsoup.parse(strHtmlxiangxi);
//				 SHOW_DATE = 
				 
				 String atrxt=docstrHtmlxiangxi.select("p.info-release").first().text();
				 String regEx="[^0-9]";   
				 Pattern   p   =   Pattern.compile(regEx);      
				 Matcher   m   =   p.matcher(atrxt);      
				 System.out.println(  SHOW_DATE= m.replaceAll("").trim());
				 realitme.setSHOW_DATE(SHOW_DATE);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			OracleMovePiaoFang.intoTEM_FILM_BOXOFFICE_REALTIME(realitme);
			
			
			try {
				zhongguopiaofangwang_xunzhe.shoushuozhongguopiaofangwang(text);
			} catch (Exception e) {
				// TODO: handle exception
			}
			

//			tem_dim_film_boxoffice(uid, text, strmainurl);
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
			
			if ((Integer.valueOf(TimeTest.getNowTime("HH"))==23&&Integer.valueOf(TimeTest.getNowTime("mm"))>30)||(Integer.valueOf(TimeTest.getNowTime("HH"))==0&&Integer.valueOf(TimeTest.getNowTime("mm"))<30)) {
				System.out.println("存储过程运行不在运行时间内 ");
			}else{
				DBManager dbm = DBManager.instance();
				dbm.executeCall("call sp_f_film_boxoffice_realtime('"+DateUtil.getAfterDayDate(-1)+"') ");
				dbm.executeCall("call dwetl.MART_F_FILM_INDEX('"+DateUtil.getAfterDayDate(-1)+"') ");
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
		
		
//		String String1="<i class=\"cs gsBlur\">&#xed68;.&#xe85b;%</i>";
//		String String2="&#xed68;";
////		System.out.println(String1.replace(String2, "1"));
//		System.out.println(Stringhtml_int(String1));
		
//		String html="var isDev=false;"
//+"    var sRev=('{&quot;biz/box-letters-detail.js&quot;:&quot;448d81e9&quot;,&quot;biz/box-letters.js&quot;:&quot;f3509e44&quot;,&quot;biz/calendar-nav.js&quot;:&quot;7cf3a59d&quot;,&quot;biz/company.js&quot;:&quot;40d761b0&quot;,&quot;biz/connect/components.js&quot;:&quot;b52102d2&quot;,&quot;biz/connect/demand_detail.js&quot;:&quot;bcd7f194&quot;,&quot;biz/connect/link_action.js&quot;:&quot;07d43b62&quot;,&quot;biz/connect/project_detail.js&quot;:&quot;4307e01f&quot;,&quot;biz/connect/report.js&quot;:&quot;23a88f00&quot;,&quot;biz/detail.js&quot;:&quot;fac4e2f0&quot;,&quot;biz/download_app.js&quot;:&quot;6bbdbe80&quot;,&quot;biz/ds.js&quot;:&quot;3ad9f5c6&quot;,&quot;biz/errors.js&quot;:&quot;95394174&quot;,&quot;biz/expandable-container.js&quot;:&quot;fa391226&quot;,&quot;biz/forecast.js&quot;:&quot;034c706d&quot;,&quot;biz/guess.js&quot;:&quot;e6a999ce&quot;,&quot;biz/monthly.js&quot;:&quot;b56cc770&quot;,&quot;biz/months.js&quot;:&quot;2aff7e45&quot;,&quot;biz/movie.js&quot;:&quot;baf0aed4&quot;,&quot;biz/rankings.js&quot;:&quot;579413e1&quot;,&quot;biz/search.js&quot;:&quot;b68d732a&quot;,&quot;biz/seat.js&quot;:&quot;efc873a3&quot;,&quot;biz/show.js&quot;:&quot;b761a5c2&quot;,&quot;biz/ssz.js&quot;:&quot;5f62f97f&quot;,&quot;biz/table-view.js&quot;:&quot;8416886f&quot;,&quot;biz/theatre.js&quot;:&quot;4a434a6d&quot;,&quot;biz/ticket.js&quot;:&quot;3b2630ab&quot;,&quot;lib/count.js&quot;:&quot;90e1e54b&quot;,&quot;lib/declareable.js&quot;:&quot;ac3ea433&quot;,&quot;lib/eruda.js&quot;:&quot;3b56e273&quot;,&quot;lib/history.js&quot;:&quot;592b03f7&quot;,&quot;lib/ichart.js&quot;:&quot;e4336755&quot;,&quot;lib/mtnb.js&quot;:&quot;3d652478&quot;,&quot;lib/sharekit.js&quot;:&quot;37e27424&quot;,&quot;lib/solarlunar.js&quot;:&quot;1ce10dc9&quot;,&quot;lib/swipe.js&quot;:&quot;b058c3d8&quot;,&quot;lib/wechat.js&quot;:&quot;2044fc41&quot;,&quot;lib/zepto.js&quot;:&quot;65f8ba92&quot;,&quot;truck.js&quot;:&quot;818ff290&quot;,&quot;util/alphaCatWelcome.js&quot;:&quot;f50b998d&quot;,&quot;util/calendar.js&quot;:&quot;b70edff0&quot;,&quot;util/canTouch.js&quot;:&quot;998c9c42&quot;,&quot;util/cityBar.js&quot;:&quot;f32c71d4&quot;,&quot;util/colors.js&quot;:&quot;4a81b7a4&quot;,&quot;util/cookie.js&quot;:&quot;495e1b76&quot;,&quot;util/dateBar.js&quot;:&quot;905ee2eb&quot;,&quot;util/el-sticky.js&quot;:&quot;7dd1f252&quot;,&quot;util/env.js&quot;:&quot;8bb29f97&quot;,&quot;util/error-view.js&quot;:&quot;46b553eb&quot;,&quot;util/gaConfig.js&quot;:&quot;08103446&quot;,&quot;util/gotoTop.js&quot;:&quot;edf416a1&quot;,&quot;util/hisBack.js&quot;:&quot;7c9a21a0&quot;,&quot;util/hrefTo.js&quot;:&quot;cb7d28ae&quot;,&quot;util/itouch.js&quot;:&quot;42526fe9&quot;,&quot;util/lazyload.js&quot;:&quot;5e3f5beb&quot;,&quot;util/nAjax.js&quot;:&quot;9feaa0ce&quot;,&quot;util/nuwa.js&quot;:&quot;f3da34ad&quot;,&quot;util/pull-refresh.js&quot;:&quot;c1fc50ab&quot;,&quot;util/query.js&quot;:&quot;838d8c3c&quot;,&quot;util/raf.js&quot;:&quot;e8c3592a&quot;,&quot;util/remember.js&quot;:&quot;46dadfd0&quot;,&quot;util/searchInput.js&quot;:&quot;81322d4a&quot;,&quot;util/slider.js&quot;:&quot;10fcfe14&quot;,&quot;util/tab.js&quot;:&quot;6699acd9&quot;,&quot;util/tools.js&quot;:&quot;dac235c7&quot;,&quot;util/weeklist.js&quot;:&quot;7c24ddbd&quot;,&quot;util/xnb.js&quot;:&quot;263a018d&quot;}').replace(/&quot;/g,'\"');"
//+"    var sDep=('{&quot;js/biz/box-letters-detail.448d81e9.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/xnb.js&quot;,&quot;util/el-sticky.js&quot;],&quot;js/biz/box-letters.f3509e44.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/pull-refresh.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/xnb.js&quot;],&quot;js/biz/calendar-nav.7cf3a59d.js&quot;:[&quot;util/tools.js&quot;],&quot;js/biz/company.40d761b0.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/remember.js&quot;,&quot;biz/calendar-nav.js&quot;],&quot;js/biz/connect/components.b52102d2.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/biz/connect/demand_detail.bcd7f194.js&quot;:[&quot;lib/zepto.js&quot;,&quot;biz/connect/components.js&quot;,&quot;util/xnb.js&quot;,&quot;biz/connect/report.js&quot;,&quot;biz/connect/link_action.js&quot;],&quot;js/biz/connect/link_action.07d43b62.js&quot;:[&quot;biz/connect/components.js&quot;],&quot;js/biz/connect/project_detail.4307e01f.js&quot;:[&quot;lib/zepto.js&quot;,&quot;biz/connect/components.js&quot;,&quot;util/xnb.js&quot;,&quot;biz/connect/report.js&quot;,&quot;biz/download_app.js&quot;,&quot;biz/connect/link_action.js&quot;],&quot;js/biz/connect/report.23a88f00.js&quot;:[&quot;lib/zepto.js&quot;,&quot;biz/connect/components.js&quot;],&quot;js/biz/detail.fac4e2f0.js&quot;:[&quot;lib/ichart.js&quot;],&quot;js/biz/download_app.6bbdbe80.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/biz/ds.3ad9f5c6.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;lib/wechat.js&quot;,&quot;lib/mtnb.js&quot;,&quot;lib/sharekit.js&quot;,&quot;util/itouch.js&quot;,&quot;util/cookie.js&quot;,&quot;util/nAjax.js&quot;],&quot;js/biz/errors.95394174.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/biz/expandable-container.fa391226.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/biz/forecast.034c706d.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;util/tools.js&quot;,&quot;util/remember.js&quot;,&quot;biz/table-view.js&quot;,&quot;util/nAjax.js&quot;,&quot;biz/calendar-nav.js&quot;],&quot;js/biz/guess.e6a999ce.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;lib/wechat.js&quot;,&quot;lib/mtnb.js&quot;,&quot;lib/sharekit.js&quot;,&quot;util/itouch.js&quot;,&quot;util/cookie.js&quot;,&quot;util/nAjax.js&quot;],&quot;js/biz/monthly.b56cc770.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;],&quot;js/biz/months.2aff7e45.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/biz/movie.baf0aed4.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;util/tools.js&quot;,&quot;biz/table-view.js&quot;,&quot;util/nAjax.js&quot;,&quot;biz/calendar-nav.js&quot;,&quot;biz/expandable-container.js&quot;,&quot;util/el-sticky.js&quot;,&quot;util/xnb.js&quot;],&quot;js/biz/rankings.579413e1.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/nAjax.js&quot;],&quot;js/biz/search.b68d732a.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/query.js&quot;],&quot;js/biz/seat.efc873a3.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/history.js&quot;,&quot;util/nAjax.js&quot;],&quot;js/biz/show.b761a5c2.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;util/itouch.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/colors.js&quot;,&quot;util/tools.js&quot;],&quot;js/biz/ssz.5f62f97f.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;lib/wechat.js&quot;,&quot;lib/mtnb.js&quot;,&quot;lib/sharekit.js&quot;,&quot;util/itouch.js&quot;,&quot;util/cookie.js&quot;,&quot;util/nAjax.js&quot;],&quot;js/biz/table-view.8416886f.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/biz/theatre.4a434a6d.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/ichart.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/remember.js&quot;,&quot;biz/calendar-nav.js&quot;],&quot;js/biz/ticket.3b2630ab.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/tools.js&quot;,&quot;biz/calendar-nav.js&quot;,&quot;util/error-view.js&quot;],&quot;js/lib/count.90e1e54b.js&quot;:[],&quot;js/lib/declareable.ac3ea433.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/lib/eruda.3b56e273.js&quot;:[],&quot;js/lib/history.592b03f7.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/lib/ichart.e4336755.js&quot;:[],&quot;js/lib/mtnb.3d652478.js&quot;:[],&quot;js/lib/sharekit.37e27424.js&quot;:[],&quot;js/lib/solarlunar.1ce10dc9.js&quot;:[],&quot;js/lib/swipe.b058c3d8.js&quot;:[],&quot;js/lib/wechat.2044fc41.js&quot;:[],&quot;js/lib/zepto.65f8ba92.js&quot;:[],&quot;js/truck.818ff290.js&quot;:[],&quot;js/util/alphaCatWelcome.f50b998d.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/calendar.b70edff0.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/solarlunar.js&quot;,&quot;util/weeklist.js&quot;],&quot;js/util/canTouch.998c9c42.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/cityBar.f32c71d4.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/tools.js&quot;],&quot;js/util/colors.4a81b7a4.js&quot;:[],&quot;js/util/cookie.495e1b76.js&quot;:[],&quot;js/util/dateBar.905ee2eb.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/calendar.js&quot;,&quot;util/tools.js&quot;],&quot;js/util/el-sticky.7dd1f252.js&quot;:[&quot;util/raf.js&quot;],&quot;js/util/env.8bb29f97.js&quot;:[],&quot;js/util/error-view.46b553eb.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/gaConfig.08103446.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/gotoTop.edf416a1.js&quot;:[],&quot;js/util/hisBack.7c9a21a0.js&quot;:[],&quot;js/util/hrefTo.cb7d28ae.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/itouch.42526fe9.js&quot;:[],&quot;js/util/lazyload.5e3f5beb.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/nAjax.9feaa0ce.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/tools.js&quot;],&quot;js/util/nuwa.f3da34ad.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/pull-refresh.c1fc50ab.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/raf.js&quot;],&quot;js/util/query.838d8c3c.js&quot;:[],&quot;js/util/raf.e8c3592a.js&quot;:[],&quot;js/util/remember.46dadfd0.js&quot;:[&quot;lib/zepto.js&quot;,&quot;util/query.js&quot;],&quot;js/util/searchInput.81322d4a.js&quot;:[],&quot;js/util/slider.10fcfe14.js&quot;:[&quot;lib/zepto.js&quot;,&quot;lib/swipe.js&quot;,&quot;util/lazyload.js&quot;],&quot;js/util/tab.6699acd9.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/tools.dac235c7.js&quot;:[&quot;lib/zepto.js&quot;],&quot;js/util/weeklist.7c24ddbd.js&quot;:[],&quot;js/util/xnb.263a018d.js&quot;:[&quot;lib/mtnb.js&quot;,&quot;lib/wechat.js&quot;,&quot;util/nAjax.js&quot;,&quot;util/env.js&quot;]}').replace(/&quot;/g,'\"');"
//+"    var requirejs = {};"
//+"    requirejs.__requirejsConfig = {"
//+"        baseUrl:'http://ms0.meituan.net/',"
//+"        revision:JSON.parse(sRev),"
//+"        prefix: 'piaofang/js/',"
//+"        cachePrefix: 'n~', "
//+"        combo: {"
//+"            url: 'http://mc.meituan.net/combo/?f=',"
//+"            deps:JSON.parse(sDep)"
//+"        }"
//+"    }";
//		System.out.println(html);
		
		
		
//		runstatic();
//		openstatic();
		TimingTime(01, 00, 59);
		
//		DBManager dbm = DBManager.instance();
//		dbm.executeCall("call sp_f_film_boxoffice_realtime('"+DateUtil.getAfterDayDate(-1)+"') ");
//		dbm.executeCall("call dwetl.MART_F_FILM_INDEX('"+DateUtil.getAfterDayDate(-1)+"') ");
//		System.out.println("存储过程运行完毕 ");
		
	}

}
