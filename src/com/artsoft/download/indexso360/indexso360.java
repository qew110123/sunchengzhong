package com.artsoft.download.indexso360;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.downloadThreadpool.HaoSouWordAdmin;
import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.downloadThreadpool.MyHaoSoutask;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class indexso360 {

	static ThreadPool pool = new ThreadPool(30);

	public static String Htmlurl(String urlMain) {
		// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		// "UTF-8", null, null);

		String strHtml = HaoSouWordAdmin.urlreturnHtml(urlMain);
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return strHtml;

	}

	public static void tem_person_keyword_distrib(String person_id, String data_date, String keyword, String urlMain,int data_type) {
		// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		// "UTF-8", null, null);
		// if (strHtml == null || strHtml.equals("")) {
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }
		// if (strHtml == null || strHtml.equals("")) {
		// // return;
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }

		String strHtml = Htmlurl(urlMain);

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);
		JSONObject data = (JSONObject) people.get("data");
		System.out.println(data);
		list = (JSONArray) data.get("list");
		int search_index = 0;
		String trend = "";
		for (Object object : list) {
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(keyword = (String) objectobject.get("query"));
			System.out.println(search_index = (int) objectobject.get("power"));
			System.out.println(trend = (String) objectobject.get("trend"));
			OracleHaoSou.intotem_person_keyword_distrib(data_date, person_id, keyword, search_index, trend, urlMain,data_type);

		}
	}

	private static void tem_person_relevant_keyword(String person_id, String data_date, String keyword,
			String urlMain,int data_type) {
		// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		// "UTF-8", null, null);
		// if (strHtml == null || strHtml.equals("")) {
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }
		// if (strHtml == null || strHtml.equals("")) {
		// // return;
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }

		String strHtml = Htmlurl(urlMain);

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONArray datalist = (JSONArray) people.get("data");

		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			String recreason = (String) objectobject.get("recreason");
			Object liststring = (Object) objectobject.get("list");
			// for (Object object2 : listlist) {
			// System.out.println(object2);
			// }
			String trend = "";
			// System.out.println(liststring);
			String[] listkeyword = liststring.toString().split(",");
			for (int i = 0; i < listkeyword.length; i++) {
				// System.out.println(listkeyword[i]);
				System.out.println(keyword = HtmlAnalyze.getTagText(listkeyword[i], "\"", "\":\""));
				System.out.println(trend = HtmlAnalyze.getTagText(listkeyword[i], "\":\"", "\""));
				String utf8keyword = "";
				try {
					utf8keyword = java.net.URLEncoder.encode(keyword, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String keyword_url = "http://www.so.com/s?ie=utf-8&src=360zhishu&q=" + utf8keyword + "&from=360zhishu";
				// http://www.so.com/s?ie=utf-8&src=360zhishu&q=%E5%94%90%E5%AB%A3&from=360zhishu
				OracleHaoSou.intotem_person_relevant_keyword(data_date, person_id, keyword, keyword_url, recreason,
						trend, urlMain,data_type);
			}

		}
	}

	private static void tem_person_relevant_news(String person_id, String data_date, String keyword, String urlMain,int data_type) {
		// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		// "UTF-8", null, null);
		// if (strHtml == null || strHtml.equals("")) {
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }
		// if (strHtml == null || strHtml.equals("")) {
		// // return;
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }

		String strHtml = Htmlurl(urlMain);

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONObject data = (JSONObject) people.get("data");

		JSONArray datalist = (JSONArray) data.get("list");

		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			String news_title = (String) objectobject.get("title");
			System.out.println(news_title);
			String news_url = (String) objectobject.get("url");
			String news_sitename = (String) objectobject.get("sitename");
			String news_date = (String) objectobject.get("pdate");

			OracleHaoSou.intotem_person_relevant_news(data_date, person_id, news_date, news_sitename, news_title,
					news_url, urlMain,data_type);
		}
	}

	private static void tem_person_relevant_weibo(String person_id, String data_date, String keyword, String urlMain,int data_type) {
		// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		// "UTF-8", null, null);
		// if (strHtml == null || strHtml.equals("")) {
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }
		// if (strHtml == null || strHtml.equals("")) {
		// // return;
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }

		String strHtml = Htmlurl(urlMain);

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONObject data = (JSONObject) people.get("data");

		JSONArray datalist = (JSONArray) data.get("list");

		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// String news_title=(String) objectobject.get("title");
			// System.out.println(news_title);
			String comments_num = (String) objectobject.get("forwardsnum");
			String forwards_num = (String) objectobject.get("commentsnum");
			String timestamp = (String) objectobject.get("timestamp");
			String comments_url = (String) objectobject.get("link_forwardsnum");
			String forwards_url = (String) objectobject.get("link_commentsnum");
			String weibo_url = (String) objectobject.get("url");
			String text = (String) objectobject.get("text");
			OracleHaoSou.intotem_person_relevant_weibo(data_date, person_id, comments_num, forwards_num, comments_url,
					forwards_url, text, timestamp, weibo_url, urlMain,data_type);

		}
	}

	private static void tem_person_keyword_up(String person_id, String data_date, String keyword, String urlMain,int data_type) {
		// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		// "UTF-8", null, null);
		// if (strHtml == null || strHtml.equals("")) {
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }
		// if (strHtml == null || strHtml.equals("")) {
		// // return;
		// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
		// null);
		// }

		String strHtml = Htmlurl(urlMain);

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONObject data = (JSONObject) people.get("data");
		JSONArray datalist = (JSONArray) data.get("list");

		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// String news_title=(String) objectobject.get("title");
			// System.out.println(news_title);
			keyword = (String) objectobject.get("word");
			String up_rate = (String) objectobject.get("ratio");
			OracleHaoSou.intotem_person_keyword_up(data_date, person_id, keyword, up_rate, urlMain,data_type);
		}
	}
	
	/**
	 *2016年7月5日10:37:03
	 *进行360指数地区分布
	 *
	 * @param person_id
	 * @param data_date
	 * @param keyword
	 * @param urlMain
	 * http://index.so.com/index.php?a=drawAreaJson&t=30&q=%E6%AC%A2%E4%B9%90%E9%A2%82
	 * @param data_type
	 */
	public static void TEM_360_WORD_AREA(String person_id, String data_date, String keyword, String urlMain,int data_type) {


		String strHtml = Htmlurl(urlMain);
		
//		 String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
//				 "UTF-8", null, null);
//				 if (strHtml == null || strHtml.equals("")) {
//				 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
//				 null);
//				 }
//				 if (strHtml == null || strHtml.equals("")) {
//				 // return;
//				 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
//				 null);
//				 }

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONObject data = (JSONObject) people.get("data");
		JSONArray datalist = (JSONArray) data.get("list");

		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// String news_title=(String) objectobject.get("title");
			// System.out.println(news_title);
//			keyword = (String) objectobject.get("word");
//			String up_rate = (String) objectobject.get("ratio");
			String AREA_TOP= (String) objectobject.getString("rank");
			String AREA_NAME_CN= (String) objectobject.getString("province");
			String AREA_RATE= (String) objectobject.getString("perctent");
			OracleHaoSou.intoTEM_360_WORD_AREA(data_date, person_id, keyword, AREA_TOP,AREA_NAME_CN,AREA_RATE, urlMain,data_type);
		}
	}
	
	
	/**
	 *2016年7月5日10:47:26
	 *360关注人物性别比例年龄分布
	 *http://index.so.com/index.php?a=portrayalJson&t=30&q=%E6%AC%A2%E4%B9%90%E9%A2%82
	 */
	public static void TEM_360_WORD_AGE_SEX(String person_id, String data_date, String keyword, String urlMain,int data_type) {


		String strHtml = Htmlurl(urlMain);
		
//		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
//				 "UTF-8", null, null);
//				 if (strHtml == null || strHtml.equals("")) {
//				 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
//				 null);
//				 }
//				 if (strHtml == null || strHtml.equals("")) {
//				 // return;
//				 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
//				 null);
//				 }

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONObject data = (JSONObject) people.get("data");
		String MAN_RATE=data.getString("male");
		String WOMAN_RATE=data.getString("female");
		String age=data.getString("age");
		String RATE_19="";
		String RATE_29="";
		String RATE_39="";
		String RATE_49="";
		String RATE_50="";
		try {
//			String ageString =data.getString(age);
			String [] rate=age.split(",");
			RATE_19=rate[0];
			RATE_29=rate[1];
			RATE_39=rate[2];
			RATE_49=rate[3];
			RATE_50=rate[4];
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		OracleHaoSou.intoTEM_360_WORD_AGE_SEX(data_date, person_id, keyword, MAN_RATE, WOMAN_RATE, RATE_19, RATE_29, RATE_39, RATE_49, RATE_50, urlMain, data_type);
		
	}
	
	/**
	 * 2016年7月5日11:20:12
	 * 360关键字标签
	 * TEM_360_WORD_TAG
	 * http://index.so.com/index.php?a=portrayalJson&t=30&q=%E6%AC%A2%E4%B9%90%E9%A2%82
	 */
	private static void TEM_360_WORD_TAG(String person_id, String data_date, String keyword, String urlMain,int data_type) {

		String strHtml = Htmlurl(urlMain);
		
//		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
//				 "UTF-8", null, null);
//				 if (strHtml == null || strHtml.equals("")) {
//				 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
//				 null);
//				 }
//				 if (strHtml == null || strHtml.equals("")) {
//				 // return;
//				 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
//				 null);
//				 }

		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		System.out.println(people);

		JSONObject data = (JSONObject) people.get("data");
		JSONArray datalist = (JSONArray) data.get("tags");

		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// String news_title=(String) objectobject.get("title");
			// System.out.println(news_title);
			String LABEL_NAME = (String) objectobject.get("tag");
			String LABEL_NUM = (String) objectobject.get("power");
			OracleHaoSou.intoTEM_360_WORD_TAG(data_date, person_id, keyword, LABEL_NAME, LABEL_NUM, urlMain,data_type);
		}
	}
	
	
	
	/**
	 * 
	 * @param person_id
	 * @param data_date
	 * @param keyword
	 * @param krywordutf8
	 * @param data_type
	 */

	public static void my360run(String person_id, String data_date, String keyword, String krywordutf8,int data_type) {
//		try {
//			tem_person_keyword_distrib(person_id, data_date, keyword,
//					"http://index.so.com/index.php?a=radarJson&t=30&q=" + krywordutf8, data_type);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		try {
//			tem_person_relevant_keyword(person_id, data_date, keyword,
//					"http://index.so.com/index.php?a=nlpJson&t=30&q=" + krywordutf8, data_type);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		try {
//			tem_person_relevant_news(person_id, data_date, keyword,
//					"http://index.so.com/index.php?a=relNewsJson&q=" + krywordutf8, data_type);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		try {
//			tem_person_relevant_weibo(person_id, data_date, keyword,
//					"http://index.so.com/index.php?a=relWeiboJson&q=" + krywordutf8, data_type);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		try {
//			tem_person_keyword_up(person_id, data_date, keyword,
//					"http://index.so.com/index.php?a=surgeWordsJson&t=7&q=" + krywordutf8, data_type);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
//		try {
//			TEM_360_WORD_AREA(person_id, data_date, keyword, "http://index.so.com/index.php?a=drawAreaJson&t=30&q=" + krywordutf8, data_type);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
//		try {
//			TEM_360_WORD_AGE_SEX(person_id, data_date, keyword, "http://index.so.com/index.php?a=portrayalJson&t=30&q=" + krywordutf8, data_type);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		try {
			TEM_360_WORD_TAG(person_id, data_date, keyword, "http://index.so.com/index.php?a=portrayalJson&t=30&q=" + krywordutf8, data_type);
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(person_id);
	}

	public static void openstatic() {

		String returnNumPeople = OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
		IpFilter.mainip("http://index.haosou.com/");
		CommonUtil.setLog("ip代理时间" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println("需要采集的人名字数为" + returnNumPeople);

		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			List<String> listArray = OracleHaoSou.selectname(Integer.toString(i), Integer.toString(i + 1000));
			for (Object Objstring : listArray) {
				System.out.println(Objstring);
				List<String> listTemp = (List<String>) Objstring;
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
					// if (Integer.parseInt(listTemp.get(0))>2961) {
					String person_id = listTemp.get(0);
					String keyword = listTemp.get(1);
					String data_date = TimeTest.getNowTime("yyyyMMdd");
					String krywordutf8 = "";
					try {
						krywordutf8 = java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					while (pool.getPoolNum() > 30) {
						try {
							System.out.println("线程数量大于30，等待5s");
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("当前启动线程thread:" + pool.getPoolNum());
					pool.performTask(new index360pool(person_id, data_date, keyword, krywordutf8,1));

					// }

				}
			}
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// runstatic();
//		 openstatic();
		TimingTime(00, 59, 59);
//		String krywordutf8="";
//		try {
//			krywordutf8 = java.net.URLEncoder.encode("你好", "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		my360run("0", "0", "0", krywordutf8, 0);
		
	}

}
