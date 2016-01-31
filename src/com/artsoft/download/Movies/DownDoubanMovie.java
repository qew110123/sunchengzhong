package com.artsoft.download.Movies;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.Doc;

public class DownDoubanMovie {

	public static void iQiYiBranch(String urlBranch) {
		Proxy proxy = DealProxy.getInstance().getPoxxy();
		// strHtml = DownloadUtil.getHtmlText(url, 1000 * 10, "UTF-8", null,
		// proxy);
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
		if (strHtml == null || strHtml.equals("")) {
			proxy = DealProxy.getInstance().getPoxxy();
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");

		System.out.println(name);
		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/?callback=window.Q.__callbacks__.cbrymman";
		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "\":", "}"));
		try {

			OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch, "1", "0");

			String videourl = "http://up.video.iqiyi.com/ugc-updown/quud.do?dataid=" + videoId
					+ "&type=1&userid=&flashuid=e42dc42f8825edf5580806cde99606ce&appID=21&callback=window.Q.__callbacks__.cb1953yg";

			String feishuhtml = DownloadUtil.getHtmlText(videourl, 1000 * 30, "UTF-8", null, null);
			String feishu = "";
			System.out.println(feishu = HtmlAnalyze.getTagText(feishuhtml, "score\":", ",\""));

			OracleOpreater.intoReputation(name, "2", feishu, "0", "", urlBranch, "1", "1");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void mainurl(String urlMain) {
		// TODO Auto-generated method stub
		try {

			Proxy proxy = DealProxy.getInstance().getPoxxy();
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, proxy);
			int i = 0;
			while ((strHtml == null || strHtml.equals("")) && i < 15) {
				System.out.println("访问" + i + "次");
				proxy = DealProxy.getInstance().getPoxxy();
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, proxy);
				i += 1;
			}
			JSONObject htmljson = new JSONObject();
			JSONArray htmljsonArray = new JSONArray();
			htmljson = JSONObject.fromObject(strHtml);

			htmljsonArray = (JSONArray) htmljson.get("subjects");
			for (Object objecthtml : htmljsonArray) {
				JSONObject jsonsubject = (JSONObject) objecthtml;
				String name = "";
				System.out.println(name = (String) jsonsubject.get("title"));
				String urls = "";
				System.out.println(urls = (String) jsonsubject.get("url"));
				String feishu = "";
				System.out.println(feishu = (String) jsonsubject.get("rate"));
				String pinglun = "";
				// OracleOpreater.intoReputation(name, "1", Amount, "0", "",
				// urlBranch, "1", "0");
				// OracleOpreater.intoReputation(name, "1", score, "0", "",
				// urlBranch, "1", "1");
				// OracleOpreater.intoReputation(name, "1", comment, "0", "",
				// urlBranch, "1", "2");
				// OracleOpreater.intoReputation(name, "2", feishu, "0", "",
				// urlBranch, "1", "1");
				// OracleOpreater.intoReputation(name, "2", numhtml, "0", "",
				// urlBranch, "1", "0");

				proxy = DealProxy.getInstance().getPoxxy();
				String strHtmlurl = DownloadUtil.getHtmlText(urls, 1000 * 30, "UTF-8", null, proxy);
				i = 0;
				while ((strHtmlurl == null || strHtmlurl.equals("")) && i < 15) {
					System.out.println("访问" + i + "次");
					proxy = DealProxy.getInstance().getPoxxy();
					System.out.println(proxy);
					strHtmlurl = DownloadUtil.getHtmlText(urls, 1000 * 30, "UTF-8", null, proxy);
					i += 1;
				}
				System.out.println(pinglun = HtmlAnalyze.getTagText(strHtmlurl, "votes\">", "</span"));
				try {
					OracleOpreater.intoReputationAndDETAIL_URL(name, "9", feishu, "0", "", urls, "3", "1", urls);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					OracleOpreater.intoReputationAndDETAIL_URL(name, "9", pinglun, "0", "", urls, "3", "2", urls);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * @param url
	 * @return
	 */

	private static String youkuMaim(String url) {
		mainurl(url);
		url = url.replaceAll("1-1-iqiyi--.html", "");
		for (int i = 1; i < 5; i++) {
			mainurl(url + i + "-1-iqiyi--.html");
		}

		return null;
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		// //热门
		// for (int i = 0; i <=500; i=i+20) {
		//// System.out.println(i);
		// DownDoubanMovie.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
		// }
		//
		//
		// //最新
		// for (int i = 0; i <=500; i=i+20) {
		//// System.out.println(i);
		// DownDoubanMovie.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=%E6%9C%80%E6%96%B0&page_limit=20&page_start="+i);
		// }

		String[] list = { "热门", "最新", "经典", "可播放", "豆瓣高分", "冷门佳片", "华语", "欧美", "韩国 日本", "动作", "喜剧", "爱情", "科幻", "悬疑",
				"恐怖", "文艺" };
		for (String stringtext : list) {
			for (int i = 0; i <= 500; i = i + 20) {
				// System.out.println(i);
				// try {
				// DownDoubanMovie.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag="
				// + java.net.URLEncoder.encode(stringtext, "utf-8")
				// +"&page_limit=20&page_start="+i);
				// } catch (UnsupportedEncodingException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				try {

					DownDoubanMovie.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=" + stringtext
							+ "&page_limit=20&page_start=" + i);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

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
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TimingTime(23, 59, 59);
		// for (int j = 0; j < 4; j++) {
		//
		// DownDoubanNetword.mainurl("http://list.iqiyi.com/www/2/-24065------------4-"+j+"-1-iqiyi--.html");
		// }

		// for (int i = 0; i <=500; i=i+20) {
		//// System.out.println(i);
		// DownDoubanNetword.mainurl("http://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
		// }

		// http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=20

		// for (int i = 0; i <=500; i=i+20) {
		//// System.out.println(i);
		// DownDoubanMovie.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
		// }
		// TimingTime(23, 59, 59);
		 IpFilter.mainip("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=20");
		runstatic();

		// String
		// url="http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=0";
		//
		//// String strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8",
		// null, null);
		// String strHtml ="";
		//// Proxy proxy = DealProxy.getInstance().getPoxxy();
		//// System.out.println(proxy);
		//
		//
		// Proxy proxy = DealProxy.getInstance().getPoxxy();
		// strHtml = DownloadUtil.getHtmlText(url, 1000 * 10, "UTF-8", null,
		// proxy);
		//// strHtml=DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
		// proxy);
		// try {
		//// String strHtml =htmlstring(url);
		// System.out.println(strHtml);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//// try {
		//// Document texthtml = Jsoup.connect(url).get();
		//// System.out.println(texthtml.toString());
		//// } catch (IOException e) {
		//// // TODO Auto-generated catch block
		//// e.printStackTrace();
		//// }

	}

	// public static String htmlstring(String newUrl) throws Exception {
	// // TODO Auto-generated method stub
	// DefaultHttpClient client = new DefaultHttpClient();
	// HttpResponse response = null;
	// System.out.println("******************************页面转向******************************");
	// // String newUrl =
	// //
	// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
	// HttpGet get = new HttpGet(newUrl);
	// // get.addHeader("Content-Type", "text/html;charset=UTF-8");
	// // get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
	// // rv:26.0) Gecko/20100101 Firefox/26.0");
	// // get.addHeader("Host", "data.weibo.com");
	// // get.addHeader("Accept",
	// // "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	// get.addHeader("Accept:",
	// "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
	// get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
	// get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
	// get.addHeader("Cache-Control", "max-age=0");
	// get.addHeader("Connection", "keep-alive");
	//// get.addHeader("Content-Type", "application/x-www-form-urlencoded");
	// get.addHeader(new BasicHeader("Cookie",
	// "bid=\"C0m6kfvGtWg\"; ll=\"108288\";
	// gr_user_id=a9310766-95d9-441c-9bc9-72779eee8448; ct=y; ps=y;
	// _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1453888640%2C%22http%3A%2F%2Fwww.douban.com%2F%22%5D;
	// ap=1; __utma=30149280.2012357565.1446608496.1453811528.1453888640.29;
	// __utmb=30149280.0.10.1453888640; __utmc=30149280;
	// __utmz=30149280.1453806305.27.17.utmcsr=baidu|utmccn=(organic)|utmcmd=organic;
	// __utma=223695111.1543114860.1451274290.1453888640.1453890115.16;
	// __utmb=223695111.5.10.1453890115; __utmc=223695111;
	// __utmz=223695111.1453890115.16.7.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E8%B1%86%E7%93%A3;
	// _pk_id.100001.4cf6=d86ff6aae8c2d873.1451274289.14.1453891711.1453811528.;
	// _pk_ses.100001.4cf6=*"));
	//
	// get.addHeader("Host", "movie.douban.com");
	//// get.addHeader("Referer", "http://movie.douban.com/");
	// get.addHeader("Upgrade-Insecure-Requests", "1");
	// get.addHeader("User-Agent",
	// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like
	// Gecko) Chrome/46.0.2490.86 Safari/537.36");
	//// get.addHeader("X-Requested-With", "XMLHttpRequest");
	// // get.addHeader("Accept-Language",
	// // "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
	//
	// HttpResponse httpResponse = client.execute(get);
	// String responseString = EntityUtils.toString(httpResponse.getEntity());
	// // 登录后首页的内容
	// System.out.println(responseString);
	// get.releaseConnection();
	// return responseString;
	// }
}
