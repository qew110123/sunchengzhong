package com.artsoft.download.webo;

import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Dajiewang {

	public static void daJeWang() throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		String newUrl = "http://www.dajie.com/home";
		HttpGet get = new HttpGet(newUrl);
		get.addHeader(new BasicHeader("Cookie",
				"DJ_UVID=MTQ0Njc3NTY0MTU1Mzc3MDc2; DJ_RF=empty; DJ_EU=http%3A%2F%2Fwww.dajie.com%2Fhome; login_email=764295333%40qq.com; dj_auth_v3=MW_qOtlnwl_JWoggzLsiIygjegD07-zT0hRU1DpC7Nwrsyf3qxtw-s9uPFHeds4*; uchome_loginuser=23860580; dj_cap=623eefeadd1d35d8d524c3a4c11e428f; USER_ACTION=request^AProfessional^ANORMAL^A-^A-; login_email=764295333%40qq.comHost:www.dajie.com"));
		get.addHeader("Content-Type", "text/html;charset=UTF-8");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		get.addHeader("Host", "www.dajie.com");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();

	}

	public static String Weibo(String newUrl) throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		// String newUrl =
		// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
		HttpGet get = new HttpGet(newUrl);
		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
		// get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
		// rv:26.0) Gecko/20100101 Firefox/26.0");
		// get.addHeader("Host", "data.weibo.com");
		// get.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		get.addHeader(new BasicHeader("Cookie",
				"SINAGLOBAL=2972848266363.144.1448332311328; DATA=usrmdinst_14; PHPSESSID=jkeoolcth20l8c8bq3avh0dfc0; WBStore=5955be0e3d5411da|undefined; _s_tentry=-; Apache=817587687633.9316.1450254524241; ULV=1450254524257:2:1:1:817587687633.9316.1450254524241:1448332311344"));

		get.addHeader("Host", "data.weibo.com");
		get.addHeader("Referer", "http://data.weibo.com/index/hotword");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");

		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();
		return responseString;
	}

	public static void people(String names) throws Exception {
		// String strHtml =
		// DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword?wname=范冰冰",
		// 1000 * 30, "UTF-8",
		// null, null);、
		// http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108

		// DownloadUtil.getHtmlText("http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108",
		// 1000 * 30, "UTF-8",
		// null, null);
		String strHtml = DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword", 1000 * 30, "UTF-8", null,
				null);
		String timeDiff = HtmlAnalyze.getTagText(strHtml, "server_time': '", "'");
		System.out.println(new Date());
		// System.out.println(Weibo("http://data.weibo.com/index/ajax/contrast?key2=%25E6%259D%258E%25E6%2599%25A8&key3=&key4=&key5=&key6=&_t=0&__rnd=1450260534657"));
		System.out.println(timeDiff);

		Date date = new Date(System.currentTimeMillis());
		int s = 0;
		System.out.println(s = (int) (date.getTime() - Integer.parseInt(timeDiff)));

		// System.out.println(Integer.parseInt(timeDiff));
		// System.out.println(new Date()- new Date(Integer.parseInt(timeDiff));
		String hostnum = Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s);
		// Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1449471097626");
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss").format(new Date(1446912627104l)));
		// Weibo("http://data.weibo.com/index/ajax/isexsitofinput?__rnd="+s );
		System.out.println(hostnum = DownloadUtil.decodeUnicode(hostnum));
		JSONObject objectobject = JSONObject.fromObject(hostnum);
		// objectobject=JSONObject.fromObject(object);
		// System.out.println( objectobject.get("keyword"));
		String keywordnames = HtmlAnalyze.getTagText(hostnum, "keyword\":[\"", "\"]}");

		if (!names.equals(keywordnames)) {
			return;
		}
		System.out.println(objectobject.get("data"));
		JSONArray authors = new JSONArray();
		authors = (JSONArray) objectobject.get("data");
		for (Object object : authors) {
			JSONObject objectobjects = JSONObject.fromObject(object);
			// objectobject=JSONObject.fromObject(object);
			// String ANIMATION_NAME="";
			// System.out.println(ANIMATION_NAME=(String)
			// objectobjects.get("name"));
			JSONArray arrayjsonname = new JSONArray();
			arrayjsonname = (JSONArray) objectobjects.get("zt");
			for (Object object2 : arrayjsonname) {
				System.out.println(object2);
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(objectobjects2.get("day_key"));
				System.out.println(objectobjects2.get("wid"));
				System.out.println(objectobjects2.get("value"));

			}

			JSONArray arrayjsonnameyd = new JSONArray();
			arrayjsonnameyd = (JSONArray) objectobjects.get("yd");
			for (Object object2 : arrayjsonnameyd) {
				System.out.println(object2);
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(objectobjects2.get("daykey"));
				System.out.println(objectobjects2.get("pc"));
				System.out.println(objectobjects2.get("mobile"));
			}

		}
		String wid = HtmlAnalyze.getTagText(hostnum, "\"wid\":\"", "\"");
		System.out.println("地理位置");
		String keywordzone = Weibo(
				"http://data.weibo.com/index/ajax/keywordzone?type=notdefault&wid=" + wid + "&__rnd=" + s);
		
		keywordzone = DownloadUtil.decodeUnicode(keywordzone);
		
		
		Weibo("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=" + s);
		

	}

	/**
	 * 人的处理
	 */

	public static void main(String[] args) throws Exception {
		String name = "邓超";
		String URLEncodername = "";
		System.out.println(
				URLEncodername = java.net.URLEncoder.encode(java.net.URLEncoder.encode(name, "utf-8"), "utf-8"));
		System.out.println(Weibo("http://data.weibo.com/index/ajax/contrast?key2=" + URLEncodername
				+ "&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071"));
		people(name);
	}
}
