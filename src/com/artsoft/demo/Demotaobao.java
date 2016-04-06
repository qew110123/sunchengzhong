package com.artsoft.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.artsoft.util.DownloadUtil;

public class Demotaobao {

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

	public static void taobao() throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		String newUrl = "https://lu.taobao.com/api/cat1_list?f=jp&_ksTS=1459851090558_231&callback=jsonp232";
		HttpGet get = new HttpGet(newUrl);
		get.addHeader(new BasicHeader("Cookie",
				"cna=mck7DbFn9QYCAXzBnKpmX97e; miid=7170281725360010232; thw=cn; v=0; _tb_token_=56ab533355b3e; uc3=sg2=Aibh7NhN5qjcFqN7jNLLo5pnxxXPNifxy7y8RXS0pcw%3D&nk2=Aia0dWqgBnkDdw%3D%3D&id2=W80oUshsbGll&vt3=F8dASm7ZEx%2FBrOYn0Pw%3D&lg2=UIHiLt3xD8xYTw%3D%3D; existShop=MTQ1OTg1MDI4Nw%3D%3D; uss=BxNYK1OzK1c9NQoSj2dZntGTxazvCujI4dBl8mS%2FkCKMGgb%2BStNUp%2FBUeko%3D; lgc=a764295333; tracknick=a764295333; sg=35e; skt=930ca304a40c99d9; _cc_=Vq8l%2BKCLiw%3D%3D; tg=0; _l_g_=Ug%3D%3D; cookie2=19944232286f7f575919d2202dc534b6; cookie1=U7Go5RsZr6C%2FHThHAzcB16DmK9YHiHKx5mt3TeDJCpI%3D; unb=840208315; t=d3c4c1140f319d5c0aa4edd1f59e2122; _nk_=a764295333; cookie17=W80oUshsbGll; mt=ci=16_1&cyk=3_0; uc1=cart_m=0&cookie14=UoWyh5x%2BuLOX7w%3D%3D&existShop=false&cookie16=UtASsssmPlP%2Ff1IHDsDaPRu%2BPw%3D%3D&cookie21=URm48syIYn73&tag=2&cookie15=UIHiLt3xD8xYTw%3D%3D&pas=0; swfstore=7027; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; whl=-1%260%260%261459851085992; l=AktLmyzAX9//DHC9dDLAuV5eW-E1yV9i"));
		get.addHeader(":authority", "lu.taobao.com");
		get.addHeader(":method", "GET");
		get.addHeader(":path", "/api/cat1_list?f=jp&_ksTS=1459851090558_231&callback=jsonp232");
		get.addHeader(":scheme", "https");
		get.addHeader("accept",
				"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
		get.addHeader("accept-encoding", "gzip, deflate, sdch");
		get.addHeader("accept-language", "zh-CN,zh;q=0.8");
		get.addHeader("referer", "https://lu.taobao.com/newMyPath.htm?spm=a21bo.50862.1997525045.3.W0174U");
		get.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
		get.addHeader("x-requested-with", "XMLHttpRequest");
		// get.addHeader(":scheme", "https");
		// get.addHeader(":scheme", "https");
		// get.addHeader(":scheme", "https");
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();

	}

	public static void taobao1() throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		String newUrl = "https://cart.taobao.com/cart.htm?spm=a1z02.1.a2109.d1000367.Rnw7oR";
		HttpGet get = new HttpGet(newUrl);
		get.addHeader(new BasicHeader("Cookie",
				"cna=mck7DbFn9QYCAXzBnKpmX97e; miid=7170281725360010232; thw=cn; v=0; uc3=sg2=Aibh7NhN5qjcFqN7jNLLo5pnxxXPNifxy7y8RXS0pcw%3D&nk2=Aia0dWqgBnkDdw%3D%3D&id2=W80oUshsbGll&vt3=F8dASm7ZEx%2FBrOYn0Pw%3D&lg2=UIHiLt3xD8xYTw%3D%3D; existShop=MTQ1OTg1MDI4Nw%3D%3D; uss=BxNYK1OzK1c9NQoSj2dZntGTxazvCujI4dBl8mS%2FkCKMGgb%2BStNUp%2FBUeko%3D; lgc=a764295333; tracknick=a764295333; sg=35e; skt=930ca304a40c99d9; _cc_=Vq8l%2BKCLiw%3D%3D; tg=0; _l_g_=Ug%3D%3D; cookie2=19944232286f7f575919d2202dc534b6; cookie1=U7Go5RsZr6C%2FHThHAzcB16DmK9YHiHKx5mt3TeDJCpI%3D; unb=840208315; t=d3c4c1140f319d5c0aa4edd1f59e2122; _nk_=a764295333; cookie17=W80oUshsbGll; swfstore=124676; _tb_token_=56ab533355b3e; ubn=p; ucn=unsz; mt=ci=16_1&cyk=3_0; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; whl=-1%260%260%261459852555480; uc1=cart_m=0&cookie14=UoWyh5x%2Bu6TTVQ%3D%3D&existShop=false&cookie16=VFC%2FuZ9az08KUQ56dCrZDlbNdA%3D%3D&cookie21=W5iHLLyFe3xm&tag=2&cookie15=W5iHLLyFOGW7aA%3D%3D&pas=0; l=Ag8PUMvjQ8vbkDSByAZMLeAwH60ZbWNX"));
		get.addHeader(":host", "cart.taobao.com");
		get.addHeader(":method", "GET");
		get.addHeader(":path", "/cart.htm?spm=a1z02.1.a2109.d1000367.Rnw7oR");
		get.addHeader(":scheme", "https");
		get.addHeader(":version", "HTTP/1.1");

		get.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("accept-encoding", "gzip, deflate, sdch");
		get.addHeader("accept-language", "zh-CN,zh;q=0.8");
		get.addHeader("referer",
				"https://i.taobao.com/my_taobao.htm?spm=a1z2k.6997417.754894437.3.YjNdbH&ad_id=&am_id=&cm_id=&pm_id=1501036000a02c5c3739");
		get.addHeader("upgrade-insecure-requests", "1");
		get.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
		// get.addHeader(":scheme", "https");
		// get.addHeader(":scheme", "https");
		// get.addHeader(":scheme", "https");
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		System.out.println(responseString);
		get.releaseConnection();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// String strHtml =
		// DownloadUtil.getHtmlText("http://lu.taobao.com/api/cat1_list?f=jp&_ksTS=1459851090558_231&callback=jsonp232",
		// 1000 * 30, "UTF-8", null, null);
		// System.out.println(strHtml);
		try {
			taobao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			taobao1();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
