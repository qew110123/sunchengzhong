package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_url;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.util.DownloadUtil;

public class kasi_people_url_360 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id ="";
		String name = "刘涛";
		String zhuopin = "欢乐颂,琅琊榜";
		shoushuo(id ,name, zhuopin);
		
//		kasi_people_url_360 url360=new kasi_people_url_360();
//		try {
//			url360.url("");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			daJeWang1();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	
	public String  url(String url) throws IOException{
		// 创建URL对象
		URL myURL = new URL("https://www.sun.com");

		// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		HttpsURLConnection httpsConn = (HttpsURLConnection) myURL
		.openConnection();

		// 取得该连接的输入流，以读取响应内容
		InputStreamReader insr = new InputStreamReader(httpsConn
		.getInputStream());

		// 读取服务器的响应内容并显示
		int respInt = insr.read();
		while (respInt != -1) {
		System.out.print((char) respInt);
		respInt = insr.read();
		}
		
		
		return "";
	}
	
	
	private static void shoushuo(String id,String name, String zhuopin) {
		// TODO Auto-generated method stub
		// https://www.baidu.com/s?wd=%E5%88%98%E6%B6%9B&pn=10&oq=%E5%88%98%E6%B6%9B
		// http://www.baidu.com/s?wd=刘涛&pn=10&oq=刘涛

		// String utf8name="";
		String krywordutf8 = "";
		try {
			krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "http://www.so.com/s?q=" + krywordutf8 + "" 	;
		String strHtml = Htmlurl(url);
		
		
//		url="http://www.so.com/s?q=%E5%88%98%E6%B6%9B&src=srp&psid=17a7edeabeae7976bdc09dcaea053f6a";
//		String strHtml="";
//		try {
//			strHtml = Jsoup.connect(url).get().toString();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		Document doc = Jsoup.parse(strHtml);
		Elements linkli = doc.select("h3");
		for (Element element : linkli) {

			if (element.text().contains("360百科")) {
				System.out.println(element);
				System.out.println(element.text());
				String urlxiangxi = "";
				System.out.println(urlxiangxi = element.select("a").attr("href"));

//				chaxunall(id,name, zhuopin, urlxiangxi);
			}

		}

	}
	
	
	/**
	 * 2017年1月5日14:58:15 统一打开方式
	 * 
	 * @param urlMain
	 * @return
	 */
	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		int i = 0;
		while (i < 15 && strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;
		}
		return strHtml;
	}
	
	
	
	public static String httpsurl(String url){
		
		
		
		return url;
		
	}
	public static void daJeWang1() throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		String newUrl = "https://www.so.com/s?q=%E5%88%98%E6%B6%9B";
		HttpGet get = new HttpGet(newUrl);
		get.addHeader(new BasicHeader("Cookie",
				"QiHooGUID=9005CF9080F848C87CA75FC319F6FCC1.1483607109510; tso_Anoyid=11148360710911119819; __guid=15484592.4293868065840577500.1483607111971.779; dpr=1; webp=1; userexp=1; count=7; __huid=103XYUPvogtJnL3nNjIuDLLWXQUUOrPGL026NfazJA2jo%3D; gtHuid=1"));
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch, br");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
//		get.addHeader("Content-Type", "text/html;charset=UTF-8");
//		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		get.addHeader("Host", "www.so.com");
		get.addHeader("Upgrade-Insecure-Requests", "1");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();

	}
	
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
	
	

}
