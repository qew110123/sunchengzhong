package com.artsoft.download.qqindex;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.artsoft.util.DownloadUtil;

public class qqindex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		runstatic();

	}

	private static void runstatic() {
		// TODO Auto-generated method stub

		String name = "琅琊榜";
		String krywordutf8 = "";
		try {
			krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qqIndex(name, krywordutf8);

	}

	private static void qqIndex(String name, String krywordutf8) {
		// TODO Auto-generated method stub
		String qq_index = "http://tbi.tencent.com/tbi/queryTagIndex?end=20170321&start=20170220&tag=315&tagId=&type=0";

		qq_shoushuo_index(name, qq_index);

	}

	private static void qq_shoushuo_index(String name, String qq_index) {
		// TODO Auto-generated method stub
		String strHtml = "";
		try {
			strHtml = QQall(qq_index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(strHtml);

	}

	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);

		int i = 0;
		while (i < 15 && strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;

		}

		return strHtml;

	}

	public static String QQall(String newUrl) throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println(newUrl);
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
		// get.addHeader("ContentType", "text/html; charset=utf-8"));
		// get.addHeader("Accept", "application/json, text/plain, */*");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Accept-Encoding", "gzip");
		get.addHeader("Content-Type", "application/json;charset=UTF-8");
//		get.addHeader("ETag", "W/\"2e68-eqGPmj3RBu2wN86wjGMvMA\"");
		get.addHeader("Server", "nginx");
		get.addHeader("Set-Cookie", "XSRF-TOKEN=RMzL9xbY-yFOAY0tvaSnQDBDGv6CpQFC4gBc; Path=/");
		get.addHeader("Transfer-Encoding", "chunked");
		get.addHeader("Vary", "Accept-Encoding");
		
		
//		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		get.addHeader("Cache-Control", "max-age=0");
		
		// get.addHeader("Cookie",
		// "SINAGLOBAL=8549726845230.907.1445398578667; _s_tentry=fun.youth.cn;
		// Apache=9864564227371.924.1464687037301;
		// ULV=1464687037601:31:5:1:9864564227371.924.1464687037301:1463024037240;
		// appkey=; un=764295333@qq.com; ULOGIN_IMG=14656991891358;
		// SUHB=0WGNMFnY-fd5yZ;
		// WEB3_PHP-FPM_BX=b4e45307e57611d895592857e89c154f;
		// PHPSESSID=vm64vrd03jsilm939p4lkoa9g6;
		// VARNISH-bx=9913ee32e2fe6443b57de6e7558564bf;
		// __utma=31150890.1895517594.1465899683.1465899683.1465899683.1;
		// __utmc=31150890;
		// __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);
		// myuid=2805706924;
		// SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..;
		// SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt;
		// WBStore=8ca40a3ef06ad7b2|undefined;
		// UOR=picture.youth.cn,widget.weibo.com,www.baidu.com");
		
		
		get.addHeader("Accept", "application/json, text/plain, */*");
		get.addHeader("Accept-Encoding", "gzip, deflate");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Content-Length", "72");
		get.addHeader("Content-Type", "application/json;charset=UTF-8");
		get.addHeader(new BasicHeader("Cookie",
				"pgv_pvi=2984485888; Hm_lvt_407473d433e871de861cf818aa1405a1=1488521619,1488521622; _csrf=5ROstcwvYhvCg9sKUGgE02vr; pgv_si=s8261459968; ptui_loginuin=764295333@qq.com; ptisp=cnc; ptcz=ae348d5d158bf9e63106941022b04d700d030bafd07f41cf612eb9fde73e33aa; pt2gguin=o0764295333; uin=o0764295333; skey=@bqyRso9Gn; p_uin=o0764295333; p_skey=aasmMnWK0SdxElpmVwoF1evfn2zh-xPDaXWkxzayp-4_; pt4_token=qpa8lK8PWEnUsKWxhHVLLHhdwg**Z1K4PL6kkVThTYY_; XSRF-TOKEN=JtX5bVxY-IIA1KoKayAOHtEE4Bu4M2PdRqxY"));
		
		get.addHeader("Host", "tbi.tencent.com");
		get.addHeader("Origin", "http://tbi.tencent.com");
		get.addHeader("Referer", "http://tbi.tencent.com/index?word=%E5%AF%B9%E8%AF%9D&date=1");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
		get.addHeader("X-XSRF-TOKEN", "JtX5bVxY-IIA1KoKayAOHtEE4Bu4M2PdRqxY");
		///////////////////////////////JtX5bVxY-IIA1KoKayAOHtEE4Bu4M2PdRqxY
//		get.addHeader("ETag", "W/\"2e68-eqGPmj3RBu2wN86wjGMvMA\"");
		
		

		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
		// get.addHeader("charset=utf-8");
		// get.connect();
		// get.add
		// get.setHeader("Content-Type", "text/html; charset=utf-8");
		// client.getParams().setParameter("http.protocol.content-charset",
		// "UTF-8");

		HttpResponse httpResponse = new DefaultHttpClient().execute(get);

		// System.out.println(httpResponse.getStatusLine().getStatusCode());
		// HttpEntity httpEntity = httpResponse.getEntity();
		// String result = EntityUtils.toString(httpEntity);//取出应答字符串
		// System.out.println(result.replaceAll("\r", ""));
		// System.out.println(httpResponse.wait(get));
		// response.setCharacterEncoding("UTF-8");
		// httpResponse
		// httpResponse.setHeader("Content-Type", "text/html; charset=utf-8");
		// System.out.println(httpResponse);
		// httpResponse.
		// String responseString =
		// EntityUtils.toString(httpResponse.getEntity());
		// System.out.println(httpResponse.getEntity());
		// String responseString =
		// EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		// textview.setText(responseString);
		// System.out.println(responseString.wait(timeout););
		// System.out.println(
		// java.net.URLDecoder.decode(responseString,"GBK"));
		System.out.println(responseString);

		get.releaseConnection();

		return responseString;
		// return Test.weoborun(newUrl);
	}

}
