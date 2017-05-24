package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_key.maoyan_key;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import com.artsoft.bean.TEM_FILM_DAILY_BOXOFFICE_ALL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class adminmaoyan {

	public static void tem_dim_film_boxoffice(String id, String title, String urlMain) {
		String strHtml = "";
//		if (strHtml == null || strHtml.equals("")) {
//			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		}
//		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			
			maoyan_key.openKet();
			
			String keturlString ="";
			System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src:url(", ")"));
			
			if (!keturlString.equals("")) {
				strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
				
			}
			
			
			
//		}

		Document doc = Jsoup.parse(strHtml);
		String score="";
		
		try {
			score = doc.select("p.score-num  i").first().text();
			System.out.println(score);
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		
		String score_num="";
		try {
			
		

		 score_num = doc.select("span.wish-num i").first().text();// 想看人数
		System.out.println(score_num = HtmlAnalyze.getTagText(score_num, "(", "人评分)"));
//		System.out.println(score_num = "");
		} catch (Exception e) {
			// TODO: handle exception
		}

		String want_see_num = doc.select("span.wish-num i").first().text();// 想看人数
		System.out.println(want_see_num = HtmlAnalyze.getTagText(want_see_num, "(", "人评分)"));
		System.out.println(want_see_num);

		String type = HtmlAnalyze.getTagText(strHtml, "info-category\">", "</p>");
		System.out.println(type);

		String duration = HtmlAnalyze.getTagText(strHtml, "时长：", "</p>");
		System.out.println(duration);
		duration="";

		String standard = HtmlAnalyze.getTagText(strHtml, "制式：", "</p>");
		System.out.println(standard);
		standard="";

		String released_date = HtmlAnalyze.getTagText(strHtml, "info-release\">", "</p>");
		System.out.println(released_date);

		String total_boxoffice = HtmlAnalyze.getTagText(strHtml, "累计综合票房", "</span>");
		System.out.println(total_boxoffice);

		String first_week_boxoffice = HtmlAnalyze.getTagText(strHtml, "首周综合票房", "</span>");
		System.out.println(first_week_boxoffice);

		String director = HtmlAnalyze.getTagText(strHtml, "导演", "<div class=\"category\">",false,0);
		director = HtmlAnalyze.getTagText(strHtml, "class=\"items\">", "<div class=\"category\">");
		System.out.println(director);

		String actors = HtmlAnalyze.getTagText(strHtml, "演员", "<div class=\"category\">",false,0);
		
		actors = HtmlAnalyze.getTagText(actors, "class=\"items\">", "<div class=\"category\">");
		actors=actors.replace(" ", "").replace("\r", "").replace("\n", "");
		System.out.println(actors);

		String PRODUCE_COMPANY = HtmlAnalyze.getTagText(strHtml, "出品", "<div class=\"category\">",false,0);
		
		PRODUCE_COMPANY = HtmlAnalyze.getTagText(PRODUCE_COMPANY, "class=\"items\">", "<div class=\"category\">");
		PRODUCE_COMPANY = PRODUCE_COMPANY.replace("<br>", ",").replace(" ", "").replace("\r", "").replace("\n", "");
		System.out.println(PRODUCE_COMPANY);

		String ISSUE_company = HtmlAnalyze.getTagText(strHtml, "发行", "<div class=\"category\">",false,0);
		
		ISSUE_company = HtmlAnalyze.getTagText(ISSUE_company, "class=\"items\">", "<div class=\"category\">");
		ISSUE_company = ISSUE_company.replace("<br>", ",").replace("<br>", ",").replace(" ", "").replace("\r", "").replace("\n", "");
		System.out.println(ISSUE_company);

		String union_ISSUE_company = HtmlAnalyze.getTagText(strHtml, "联合发行公司</div>", "</div>");
		union_ISSUE_company = union_ISSUE_company.replace("<br>", ",").replace(" ", "").replace("\r", "").replace("\n", "");
		System.out.println(union_ISSUE_company);

		String times_length = HtmlAnalyze.getTagText(strHtml, "时长：", "\r\n");
		System.out.println(times_length);
		times_length="";

		String Color = HtmlAnalyze.getTagText(strHtml, "颜色（Color）：", "<br />");
		System.out.println(Color);
		Color="";
		String Aspect_Ratio = HtmlAnalyze.getTagText(strHtml, "幕幅（Aspect Ratio）：", "<br />");
		System.out.println(Aspect_Ratio);
		Aspect_Ratio="";

		String DESCRIPTION = HtmlAnalyze.getTagText(strHtml, "影片简介</div>", "</div>");
		if (DESCRIPTION.length()>1000) {
			DESCRIPTION=DESCRIPTION.substring(0,1000);
		}
		System.out.println(DESCRIPTION);

		OracleMovePiaoFang.intotem_dim_film_boxoffice(id, title, score, score_num, want_see_num, type, duration,
				standard, released_date, total_boxoffice, first_week_boxoffice, director, actors, PRODUCE_COMPANY,
				ISSUE_company, union_ISSUE_company, times_length, Color, Aspect_Ratio, DESCRIPTION, urlMain, 1);

	}
	
	
	/**
	 * 进行数据采集
	 * @param id
	 * @param title
	 * @param urlMain
	 * 2017年4月27日11:19:45
	 */
	
	public static void TEM_FILM_DAILY_BOXOFFICE_ALL(String uid, String text, String urlMain){
		String strHtml = "";
		
		urlMain="http://piaofang.maoyan.com/movie/"+uid+"/boxshow";

		strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);

		maoyan_key.openKet();

		String keturlString = "";
		System.out.println(keturlString = HtmlAnalyze.getTagText(strHtml, "src:url(", ")"));

		if (!keturlString.equals("")) {
			strHtml = maoyan_shishipiaofang.Stringhtml_int(keturlString, strHtml, urlMain);
		}
		
		
		//日期
		Document doc = Jsoup.parse(strHtml);

		Elements links = doc.select("div.t-table div.t-main-col span");
		
//		ArrayList<ArrayList> Listlistdate = new ArrayList<ArrayList>();
		
		HashMap<String , String> map = new HashMap<String , String>();   

		for (Element link : links) {
//			ArrayList Listdate = new ArrayList();
			String BOXOFFICE_TYPE=link.select("em").text();
			
			if (!BOXOFFICE_TYPE.equals("上映首日")) {
				if (BOXOFFICE_TYPE.contains("上映")) {
					BOXOFFICE_TYPE = "正式";
				}
				if (BOXOFFICE_TYPE.equals("")) {
					BOXOFFICE_TYPE = "正式";
				}
			}
//			Listdate.add(BOXOFFICE_TYPE);
			
			System.out.println(link.select("b").text());
//			Listdate.add(link.select("b").text());
//			Listlistdate.add(Listdate);
			map.put(link.select("b").text(), BOXOFFICE_TYPE);
			
			
		}
		
		
		
		//票房 座位 综合【票房---
		Elements linksheader = doc.select("div.t-table div.t-header div.t-col");
		
//		ArrayList<ArrayList> Listlistdate = new ArrayList<ArrayList>();
		
		ArrayList Listdateheader = new ArrayList();

		for (Element link : linksheader) {
			String header=link.select("div").first().text();
			
//			if (!BOXOFFICE_TYPE.equals("上映首日")) {
//				if (BOXOFFICE_TYPE.contains("上映")) {
//					BOXOFFICE_TYPE = "正式";
//				}
//				if (BOXOFFICE_TYPE.equals("")) {
//					BOXOFFICE_TYPE = "正式";
//				}
//			}
			Listdateheader.add(header);
			
//			System.out.println(link.select("b").text());
//			Listdate.add(link.select("b").text());
//			Listlistdate.add(Listdate);
			
		}
		
		
//		Elements linksRow = doc.select("div.t-table div.t-change div.t-row");
//		ArrayList ListRow = new ArrayList();
//		
//		if (linksRow.size()!=links.size()) {
//			return;
//		}
//		
//		int bbb=0;
//		for (Element link : linksRow) {
//			System.out.println(link);
////			Listdate.add(link.text());
//			Elements linksRowList=link.select("div.t-col");
//			int bbi=0;
//			
//			String REAL_TIME_BOXOFFICE=""; //综合单日票房
//			String BOXOFFICE_RATE=""; //票房占比
//			String SLICE_RATE="";//拍片占比
//			String ATTENDANCE_RATE="";//上座率
//			String FIELD_AVERAGE_PNUM="";//场均人次
//			String FIELD_AVERAGE_PRICE="";//平均票价
//			
//			for (Element element : linksRowList) {
//				System.out.println(element);
//				
//				if (bbi==2) {
//					REAL_TIME_BOXOFFICE=element.text();
//				}
//				
//				if (bbi==3) {
//					BOXOFFICE_RATE=element.text();
//				}
//				
//				if (bbi==7) {
//					SLICE_RATE=element.text();
//				}
//				
//				if (bbi==8) {
//					FIELD_AVERAGE_PNUM=element.text();
//				}
//				
//				if (bbi==4) {
//					FIELD_AVERAGE_PRICE=element.text();
//				}
//				
//				
//				
//				
//				
//				bbi=bbi+1;
//			}
//			
//			String BOXOFFICE_TYPE=(String) Listlistdate.get(bbb).get(0);
//			
//			String data_date = (String) Listlistdate.get(bbb).get(1);
//			System.out.println(data_date = data_date.replace("-", ""));
//			
//			
//			
//			
//			
//			bbb=bbb+1;
//			
//		}
		
		
		String json= HtmlAnalyze.getTagText(strHtml, "var boxData = ",	"var chartData");
		
		JSONObject jsonboxdata = new JSONObject();
		JSONArray list = new JSONArray();
		
		jsonboxdata = JSONObject.fromObject(json);
		
		
//		String keyword="";
		list=jsonboxdata.getJSONArray("data");
		for (Object object : list) {
			
			TEM_FILM_DAILY_BOXOFFICE_ALL boxoffice=new TEM_FILM_DAILY_BOXOFFICE_ALL();
			
			
			String real_time_boxoffice=""; //综合单日票房
			String boxoffice_rate=""; //票房占比
			String slice_rate="";//拍片占比
			String ATTENDANCE_RATE="";//上座率
			String FIELD_AVERAGE_PNUM="";//场均人次
			String FIELD_AVERAGE_PRICE="";//平均票价
			String DATA_DATE="";
			String BOXOFFICE_TYPE="";
			
			String BILL_BOXOFFICE="";//分账票房
			String BILL_BOXOFFICE_RATE="";//分账票房占比
			String BILL_BOXOFFICE_PRICE="";//分账票房票价
			String FIELD_AVG_PNUM="";//场均人次
			String NET_SALE_RATE="";//网售占比
			String GOLD_FIELD_RATE="";//黄金场占比
			String FIELD_NUM="";//场次
			String SEAT_RATE="";//排座占比
			String SEAT_NUM="";//座位数
//			String BOXOFFICE_TYPE="";//票房类型 点映 零点场 上映首日 正式 预售
//			String WOMINDEX="";//口碑指数
//			String COLLECTION_URL="";//采集链接
			
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(real_time_boxoffice = (String) objectobject.get("boxInfo"));
			
			System.out.println(boxoffice_rate = (String) objectobject.get("boxRate"));
			System.out.println(slice_rate = (String) objectobject.get("showRate"));
			System.out.println(FIELD_AVERAGE_PNUM = (String) objectobject.get("avgShowView"));
			System.out.println(FIELD_AVERAGE_PRICE = (String) objectobject.get("avgViewBox"));
			BOXOFFICE_TYPE=map.get(objectobject.getString("showDate"));
			System.out.println(DATA_DATE=objectobject.getString("showDate").replace("-", ""));
			
			BILL_BOXOFFICE=objectobject.getString("splitBoxInfo");
			BILL_BOXOFFICE_RATE=objectobject.getString("splitBoxRate");
			BILL_BOXOFFICE_PRICE=objectobject.getString("avgViewSplitBox");
			FIELD_AVG_PNUM=objectobject.getString("avgShowView");
			NET_SALE_RATE=objectobject.getString("onlineBoxRate");
			GOLD_FIELD_RATE=objectobject.getString("primeShowRate");
			FIELD_NUM=objectobject.getString("showInfo");
			SEAT_RATE=objectobject.getString("seatRate");
			SEAT_NUM=objectobject.getString("seatInfo");
//			WOMINDEX=objectobject.getString("seatInfo");
			
			boxoffice.setDataDate(DATA_DATE);
			boxoffice.setTitle(text);
			boxoffice.setUrl(urlMain);
			boxoffice.setShowDays("");
			boxoffice.setTotalBoxoffice("");
			boxoffice.setSliceRate(slice_rate);
			boxoffice.setDailyBoxoffice(real_time_boxoffice);
			boxoffice.setDailyBoxofficeRate(boxoffice_rate);
			boxoffice.setBoxofficePrice(FIELD_AVERAGE_PRICE);
			boxoffice.setBillBoxoffice(BILL_BOXOFFICE);
			boxoffice.setBillBoxofficeRate(BILL_BOXOFFICE_RATE);
			boxoffice.setBillBoxofficePrice(BILL_BOXOFFICE_PRICE);
			boxoffice.setFieldAvgPnum(FIELD_AVG_PNUM);
			boxoffice.setNetSaleRate(NET_SALE_RATE);
			boxoffice.setGoldFieldRate(GOLD_FIELD_RATE);
			boxoffice.setFieldNum(FIELD_NUM);
			boxoffice.setSeatRate(SEAT_RATE);
			boxoffice.setSeatNum(SEAT_NUM);
			boxoffice.setWomindex("");
			boxoffice.setCollectionUrl(urlMain);
			boxoffice.setFid(uid);
			
			OracleMovePiaoFang.intotem_film_daily_boxoffice(boxoffice);
			
			
//			try {
//				
//				OracleMovePiaoFang.intotem_film_daily_boxoffice(DATA_DATE, text, urlMain, uid, "",
//						"", real_time_boxoffice, boxoffice_rate, slice_rate, "", 1, "", urlMain,
//						BOXOFFICE_TYPE,FIELD_AVERAGE_PRICE,FIELD_AVERAGE_PNUM,"");
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			
		}

		
	}

	// 日票房明细

	public static void tem_film_daily_boxoffice_other(String id, String title, String urlMain) {

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		if (strHtml == null || strHtml.equals("")) {
//			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		}
//		if (strHtml == null || strHtml.equals("")) {
//			// return;
//			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		}
		
		
		String keturlString ="";
		System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
		
		if (!keturlString.equals("")) {
			maoyan_key.openkey();
			strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
			
		}

		Document doc = Jsoup.parse(strHtml);

		try {

			Elements links = doc.getElementById("ticket_tbody").select("ul");

			for (Element link : links) {
				String data_date = "";
				System.out.println(data_date = link.select("li.c1 b").text());
				System.out.println(data_date = data_date.replace("-", ""));

				String day_boxoffice = "";
				System.out.println(day_boxoffice = link.select("li.c2").text());

				String boxoffice_rate = "";
				System.out.println(boxoffice_rate = link.select("li.c3").text());

				String slice_rate = "";
				System.out.println(slice_rate = link.select("li.c4").text());

				String field_average_pnum = "";
				System.out.println(field_average_pnum = link.select("li.c5").text());

				String BOXOFFICE_TYPE = "";
				// System.out.println(BOXOFFICE_TYPE =
				// link.select("li.c5").text());
				// try {
				BOXOFFICE_TYPE = link.select("li.c1 em").text();
				// } catch (Exception e) {
				// // TODO: handle exception
				// }
				// if (BOXOFFICE_TYPE) {
				//
				// }
				if (BOXOFFICE_TYPE.equals("")) {
					BOXOFFICE_TYPE = "正式";
				}
				System.out.println(BOXOFFICE_TYPE);
				OracleMovePiaoFang.intotem_film_daily_boxoffice_other(id, title, data_date, day_boxoffice,
						boxoffice_rate, slice_rate, field_average_pnum, urlMain, BOXOFFICE_TYPE);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 日排片

	public static void tem_daily_film_slice(String id, String title, String urlMain, String datetext) {
		// http://piaofang.maoyan.com/movie/246286/dayShow?releaseDate=2016-03-04
		TimeTest tt = new TimeTest();
		// urlMain = "http://piaofang.maoyan.com/movie/" + id +
		// "/dayShow?releaseDate=" + tt.getNowTime("yyyy-MM-dd");

		urlMain = "http://piaofang.maoyan.com/movie/" + id + "/dayShow?releaseDate=" + datetext;

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

		// String score = doc.select("hgroup.score-wrap article.score
		// i").first().text();
		// System.out.println(score);
		//
		// String score_num=doc.select("hgroup.score-wrap
		// article.score").first().text();//想看人数
		// System.out.println(score_num= HtmlAnalyze.getTagText(score_num, "(",
		// "人评分)"));

		Elements links = doc.select("div.n-table-wrapper tbody tr");

		for (Element link : links) {
			String data_date = "";
			System.out.println(data_date = link.select("td b").text());

			Elements linkstd = link.select("td");
			int i = 0;
			String field_num = "";
			String slice_rate = "";
			String seat_num = "";
			String seat_rate = "";
			String gold_field_rate = "";
			for (Element element : linkstd) {
				if (i == 0) {
					System.out.println(data_date = element.select("b").text());
					System.out.println(data_date = data_date.replace("-", ""));
				}

				if (i == 1) {
					System.out.println(field_num = element.text());

				}

				if (i == 2) {
					System.out.println(slice_rate = element.text());

				}
				if (i == 3) {
					System.out.println(seat_num = element.text());
				}
				if (i == 4) {
					System.out.println(seat_rate = element.text());
				}
				if (i == 5) {
					System.out.println(gold_field_rate = element.text());
				}
				i += 1;
			}

			OracleMovePiaoFang.intotem_daily_film_slice(id, title, data_date, field_num, slice_rate, seat_num,
					seat_rate, gold_field_rate, urlMain);

		}

	}

	// 城市明细
	public static void tem_film_city(String id, String title, String urlMain, String datetext) {
		// http://piaofang.maoyan.com/movie/246286/cityBox?date=2016-03-10
		TimeTest tt = new TimeTest();
		// String data = tt.getBeforeAfterDate(tt.getNowTime("yyyy-MM-dd"),
		// -1).toString();
		String data = tt.getBeforeAfterDate(datetext, -1).toString();
		String data_date = data.replace("-", "");
		urlMain = "http://piaofang.maoyan.com/movie/" + id + "/cityBox?date=" + data;

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
		
		String keturlString ="";
		System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
		
		if (!keturlString.equals("")) {
			maoyan_key.openkey();
			strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
			
		}

		Document doc = Jsoup.parse(strHtml);

		// String score = doc.select("hgroup.score-wrap article.score
		// i").first().text();
		// System.out.println(score);
		//
		// String score_num=doc.select("hgroup.score-wrap
		// article.score").first().text();//想看人数
		// System.out.println(score_num= HtmlAnalyze.getTagText(score_num, "(",
		// "人评分)"));

		Elements links = doc.select("div.n-table-wrapper tbody tr");

		for (Element link : links) {
			// String data_date="";
			// System.out.println(data_date=link.select("td b").text());

			Elements linkstd = link.select("td");
			int i = 0;
			String city_name = "";
			String daily_boxoffice = "";
			String boxoffice_rate = "";
			String slice_rate = "";
			String total_boxoffice = "";
			String seat_rate = "";
			String gold_field_rate = "";
			String field_average_pnum = "";
			String pnum = "";
			String field_num = "";
			for (Element element : linkstd) {
				if (i == 0) {
					System.out.println(city_name = element.text());
				}
				if (i == 1) {
					System.out.println(daily_boxoffice = element.text());
				}
				if (i == 2) {
					System.out.println(boxoffice_rate = element.text());
				}
				if (i == 3) {
					System.out.println(slice_rate = element.text());
				}
				if (i == 4) {
					System.out.println(total_boxoffice = element.text());
				}
				if (i == 5) {
					System.out.println(seat_rate = element.text());
				}
				if (i == 6) {
					System.out.println(gold_field_rate = element.text());
				}
				if (i == 7) {
					System.out.println(field_average_pnum = element.text());
				}
				if (i == 8) {
					System.out.println(pnum = element.text());
				}
				if (i == 9) {
					System.out.println(field_num = element.text());
				}
				i += 1;
			}

			OracleMovePiaoFang.intotem_film_city(id, title, data_date, city_name, daily_boxoffice, boxoffice_rate,
					slice_rate, total_boxoffice, seat_rate, gold_field_rate, field_average_pnum, pnum, field_num,
					urlMain, "", 1);

		}

	}

	// 影投明细表
	public static void tem_film_company(String id, String title, String urlMain, String datetext) {
		// http://piaofang.maoyan.com/movie/246286/dayShow?releaseDate=2016-03-04
		// http://piaofang.maoyan.com/movie/246286/shadowBox?date=2016-03-10
		TimeTest tt = new TimeTest();
		// String data = tt.getBeforeAfterDate(tt.getNowTime("yyyy-MM-dd"),
		// -1).toString();
		String data = tt.getBeforeAfterDate(datetext, -1).toString();
		String data_date = data.replace("-", "");
		urlMain = "http://piaofang.maoyan.com/movie/" + id + "/shadowBox?date=" + data;

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

		Elements links = doc.select("div.n-table-wrapper tbody tr");

		for (Element link : links) {

			Elements linkstd = link.select("td");
			int i = 0;
			String film_company = "";
			String boxoffice = "";
			String boxoffice_rate = "";
			String slice_rate = "";
			String total_boxoffice = "";
			String seat_rate = "";
			String gold_field_rate = "";
			String field_average_pnum = "";
			String pnum = "";
			String field_num = "";
			for (Element element : linkstd) {
				if (i == 0) {
					System.out.println(film_company = element.text());
				}
				if (i == 1) {
					System.out.println(boxoffice = element.text());
				}
				if (i == 2) {
					System.out.println(boxoffice_rate = element.text());
				}
				if (i == 3) {
					System.out.println(slice_rate = element.text());
				}
				if (i == 4) {
					System.out.println(total_boxoffice = element.text());
				}
				if (i == 5) {
					System.out.println(seat_rate = element.text());
				}
				if (i == 6) {
					System.out.println(gold_field_rate = element.text());
				}
				if (i == 7) {
					System.out.println(field_average_pnum = element.text());
				}
				if (i == 8) {
					System.out.println(pnum = element.text());
				}
				if (i == 9) {
					System.out.println(field_num = element.text());
				}

				i += 1;
			}

			OracleMovePiaoFang.intotem_film_company(id, title, data_date, film_company, boxoffice, boxoffice_rate,
					slice_rate, total_boxoffice, seat_rate, gold_field_rate, field_average_pnum, pnum, field_num,
					urlMain);
			// intotem_film_company

		}

	}

	// 受众人群
	// http://piaofang.maoyan.com/movie/246286/aud

	public static void tem_audience_crowd(String id, String title, String urlMain, String datetext) {
		// http://piaofang.maoyan.com/movie/246286/dayShow?releaseDate=2016-03-04
		// http://piaofang.maoyan.com/movie/246286/shadowBox?date=2016-03-10
		TimeTest tt = new TimeTest();
		String data = datetext;

		String data_date = data.replace("-", "");
		urlMain = "http://piaofang.maoyan.com/movie/" + id + "/aud";

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

		String sex_name = "男";
		try {
			
			String sex_rate = doc.getElementById("bar-left").attr("data-content");
//		Element docbarleft= doc.getElementById("bar-left");
//		String sex_rate = docbarleft.attr("data-content");
			System.out.println(sex_rate);
			OracleMovePiaoFang.intotem_audience_crowd(data_date, id, title, "", "", sex_name, sex_rate, urlMain, 1);
			sex_name = "女";
			sex_rate = doc.getElementById("bar-right").attr("data-content");
			System.out.println(sex_rate);
			OracleMovePiaoFang.intotem_audience_crowd(data_date, id, title, "", "", sex_name, sex_rate, urlMain, 1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(strHtml);
			String sex_rate = HtmlAnalyze.getTagText(strHtml, "bar-left\\\" data-content=\\\"", "\\\"");
//			Element docbarleft= doc.getElementById("bar-left");
//			String sex_rate = docbarleft.attr("data-content");
			System.out.println(sex_rate);
			OracleMovePiaoFang.intotem_audience_crowd(data_date, id, title, "", "", sex_name, sex_rate, urlMain, 1);
			sex_name = "女";
			sex_rate = HtmlAnalyze.getTagText(strHtml, "bar-right\\\" data-content=\\\"", "\\\"");
			System.out.println(sex_rate);
			OracleMovePiaoFang.intotem_audience_crowd(data_date, id, title, "", "", sex_name, sex_rate, urlMain, 1);
			
			
			
		}
		
		
		
		
		String audChartData = HtmlAnalyze.getTagText(strHtml, "<script>", "</script>");
		String[] audChartDatalist = audChartData.split("\"},");
		// System.out.println(audChartDatalist.length);
		String age_name = "";
		String age_rate = "";
		for (int i = 0; i < audChartDatalist.length; i++) {
			age_rate = HtmlAnalyze.getTagText(audChartDatalist[i].toString(), "\"value\":", ",\"");
			if (i == 0) {
				age_name = "11-15岁";
			}
			if (i == 1) {
				age_name = "16-20岁";
			}
			if (i == 2) {
				age_name = "21-25岁";
			}
			if (i == 3) {
				age_name = "26-30岁";
			}
			if (i == 4) {
				age_name = "31-35岁";
			}
			if (i == 5) {
				age_name = "36-40岁";
			}
			if (i == 6) {
				age_name = "40-50岁";
			}
			System.out.println(age_name + "****" + age_rate);
			try {

				OracleMovePiaoFang.intotem_audience_crowd(data_date, id, title, age_name, age_rate, "", "", urlMain, 2);
			} catch (Exception e) {
				// TODO: handle exception

			}
		}

	}

	// 想看指数
	public static void tem_want_see_index(String id, String title, String urlMain) {

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

		String ChartData = HtmlAnalyze.getTagText(strHtml, "\"data\":[", "],");
		String[] audChartDatalist = ChartData.split(",");

		String openChartdate = HtmlAnalyze.getTagText(strHtml, "\"data\":[", "</script>");
		String Chartdate = HtmlAnalyze.getTagText(openChartdate, "date\":[\"", "\"]");
		String[] audChartDataldate = Chartdate.split("\",\"");

		for (int i = 0; i < audChartDatalist.length; i++) {
			String data_date = audChartDatalist[i];
			String new_person_num = audChartDataldate[i];
			System.out.println(data_date + "****" + new_person_num);
			OracleMovePiaoFang.intotem_want_see_index(data_date, id, title, new_person_num, urlMain, 1, "", 1, 1, "");
		}

	}

	// 想看人数城市排行
	public static void tem_want_see_city(String id, String title, String urlMain, String datetext) {
		// http://piaofang.maoyan.com/movie/246286/dayShow?releaseDate=2016-03-04
		// http://piaofang.maoyan.com/movie/246286/shadowBox?date=2016-03-10

		TimeTest tt = new TimeTest();
		String data = datetext;
		String data_date = data.replace("-", "");

		// urlMain = "http://piaofang.maoyan.com/movie/" + id + "/aud" ;

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);
		try {

			Elements links = doc.getElementById("wantCity").select("ul");

			String city_name = "";
			String city_rate = "";

			for (Element element : links) {
				city_name = element.select("li.c2").text();
				city_rate = element.select("li.c3 div.percentBar").attr("style").replace("width:", "");
				System.out.println(city_name + "******" + city_rate);
				OracleMovePiaoFang.intotem_want_see_city(data_date, id, title, city_name, city_rate, urlMain);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void openstatic() {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/";
		// openstaticLiShiShuJu(urlMain);
		// TimeTest.getBeforeAfterDate("2013-01-11", -1019);1535

		 for (int i = -1; i > -4; i--) {
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

	/**
	 * 历史数据 2016年4月29日15:52:53
	 * 
	 */
	public static void historyopenstatic() {
		CommonUtil.setLog("type:开始||date:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + "||logs:猫眼单日票房 任务开始;");
		for (int i = 0; i > -3; i--) {
			String datetext = TimeTest.getBeforeAfterDate("2017-04-21", i).toString();
			String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
			//http://piaofang.maoyan.com/dayoffice?date=2017-04-20&cnt=10
			urlMain="http://piaofang.maoyan.com/dayoffice?date="+datetext+"&cnt=10";
			System.out.println(urlMain);
			//老版数据的运行，程序改变了
			//openstaticLiShiShuJu(urlMain, datetext);
			
			//单独数据的运行
			//2017年4月21日15:20:17
			openstaticLiShiShuJuNew(urlMain, datetext);
		}
		
		CommonUtil.setLog("type:结束||date:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + "||logs:猫眼单日票房 任务结束;");
	}

	private static void openstaticLiShiShuJuNew(String urlMain, String datetext) {
		// TODO Auto-generated method stub
		
		String strHtml = "";
		
//		urlMain="http://piaofang.maoyan.com/movie/"+uid+"/boxshow";

		strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);

		maoyan_key.openKet();

		String keturlString = "";
		System.out.println(keturlString = HtmlAnalyze.getTagText(strHtml, "src:url(", ")"));

		if (!keturlString.equals("")) {
			strHtml = maoyan_shishipiaofang.Stringhtml_int(keturlString, strHtml, urlMain);
		}
		
		strHtml=HtmlAnalyze.getTagText(strHtml, "ticketList\":\"", "\",",true,0);
		strHtml=strHtml.replace("\\\"", "\"");
		strHtml=strHtml.replace("\\n", "");
		
		Document doc = Jsoup.parse(strHtml);
		
//		System.out.println(doc);
		String data_date="";
		System.out.println(data_date = datetext.replace("-", ""));
		
		Elements links = doc.select("ul.canTouch");
		for (Element link : links) {
			System.out.println(link);
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
			if (!real_time_boxoffice.equals("")) {
				real_time_boxoffice=real_time_boxoffice.replace(" ", "").replace("\n", "");
			}

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
				BOXOFFICE_TYPE = link.select("li.c1 i.font-orange").first().text();

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
			try {
				
				OracleMovePiaoFang.intotem_film_daily_boxoffice(data_date, text, strmainurl, uid, released_days,
						total_boxoffice, real_time_boxoffice, boxoffice_rate, slice_rate, attendance_rate, 1, "", urlMain,
						BOXOFFICE_TYPE);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TEM_FILM_DAILY_BOXOFFICE_ALL(uid, text, urlMain);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}

	/**
	 * //http://piaofang.maoyan.com/?date=2016-03-14 历史数据 日期数据的输入
	 */

	public static void openstaticLiShiShuJu(String urlMain, String datetext) {
		// http://piaofang.maoyan.com/?date=2016-03-15
		// String urlMain = "http://piaofang.maoyan.com/?date="+timeText;
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			
			
			String keturlString ="";
			System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
			
			if (!keturlString.equals("")) {
				maoyan_key.openkey();
				strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
				maoyan_key.openkey();
				
//				String keturlString ="";
				System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
				
				if (!keturlString.equals("")) {
					strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
					
				}
			}
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			
			
			String keturlString ="";
			System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
			
			if (!keturlString.equals("")) {
				maoyan_key.openkey();
				strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
				maoyan_key.openkey();
				
//				String keturlString ="";
				System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
				
				if (!keturlString.equals("")) {
					strHtml=maoyan_shishipiaofang.Stringhtml_int(keturlString,strHtml,urlMain);
					
				}
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
			try {
				
				OracleMovePiaoFang.intotem_film_daily_boxoffice(data_date, text, strmainurl, uid, released_days,
						total_boxoffice, real_time_boxoffice, boxoffice_rate, slice_rate, attendance_rate, 1, "", urlMain,
						BOXOFFICE_TYPE);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				tem_dim_film_boxoffice(uid, text, strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				tem_film_daily_boxoffice_other(uid, text, strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				tem_daily_film_slice(uid, text, strmainurl, datetext);//
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				tem_film_city(uid, text, strmainurl, datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				tem_film_company(uid, text, strmainurl, datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				tem_audience_crowd(uid, text, strmainurl, datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				
				tem_want_see_index(uid, text, strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				
				tem_want_see_city(uid, text, strmainurl, datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			

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
	
	
	
	
	public static void runstaticshijian(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		
		System.out.println(TimeTest.getNowTime("HH"));
		if (TimeTest.getNowTime("HH").equals("07") || TimeTest.getNowTime("HH").equals("09") || TimeTest.getNowTime("HH").equals("10")|| TimeTest.getNowTime("HH").equals("12")) {

			//老数据
			
			//			runstatic();
			historyopenstatic();
		}
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
	
	 /* 测试时间
	 * 2016年8月17日17:30:00
	 * @param args
	 */
	public static void rundingshitime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
        Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                try {
					
                	runstaticshijian();
				} catch (Exception e) {
					// TODO: handle exception
				}
            } 
        }, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行  
    }
	
	
	
	public static void runnewMain(){
		List<String> listArray = OracleBaidu.selectmaoyanpiaofang();
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String name="";
			String url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			System.out.println(url=listTemp.get(2));
			if (id != null && !"".equals(id)&&name != null && !"".equals(name)) {
				
				try {
					TEM_FILM_DAILY_BOXOFFICE_ALL(id, name, url);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 runstatic();
		// openstatic();

		//
		// openstaticLiShiShuJu("http://piaofang.maoyan.com/?date=2016-04-08",
		// "2016-04-08");
		// historyopenstatic();
		 
//		TimingTime(11, 59, 59);
		
//		TimingTime(1, 59, 59);
		
		
//		openstatic();
		// historyopenstatic();
		// tem_film_daily_boxoffice_other("", "",
		// "http://piaofang.maoyan.com/movie/246970?_v_=yes");
		
		
		
//		runstatic();
//		
//		rundingshitime(1, 00, 00);
		
		
		//2017年4月27日10:45:47
		
		
//		tem_dim_film_boxoffice("344451", "绑架者", "http://piaofang.maoyan.com/movie/344451?_v_=yes");
		
		
//		TEM_FILM_DAILY_BOXOFFICE_ALL("248700", "数据与激情8", "http://piaofang.maoyan.com/movie/248700/boxshow");
		
//		runnewMain();
		
//		historyopenstatic();
		
		
//		openstaticLiShiShuJuNew("http://piaofang.maoyan.com/dayoffice?date=2017-03-02&cnt=10", "2017-03-02");
		
		//单日排片率和座位比
		rundingshitime(1, 00, 00);
		
//		historyopenstatic();
		

	}

}
