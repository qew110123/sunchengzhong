package com.artsoft.download.webo;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.artsoft.util.DownloadUtil;

public class weiboshouji {

	public static void Weibo(String newUrl, String cookie) throws Exception {
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
		get.addHeader("Content-Type", "text/html; charset=utf-8");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader(new BasicHeader("Cookie",cookie));
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");

		get.addHeader("Host", "weibo.cn");
		get.addHeader("Referer", "http://weibo.cn/search/?pos=search&vt=4");
		
		get.addHeader("Upgrade-Insecure-Requests", "1");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
//		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");

		System.out.println(get);
//		System.out.println(URLEncoder.encode(newUrl,"UTF-8"));
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
//		String charset = "UTF-8";  
//        if (null != params && params.length >= 1) {  
//            charset = params[0];  
//        }  
//		System.out.println(EntityUtils.toString(httpResponse.getEntity(), "ISO-8859-1"));
//		System.out.println(httpResponse.getEntity().toString());
//		System.out.println(new StringEntity(httpResponse.getEntity().toString(), "GBK"));
		System.out.println(responseString);
		get.releaseConnection();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String urlMain="http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1449471097626";
//		String strHtml=	 DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", "SINAGLOBAL=8549726845230.907.1445398578667; SUHB=0sqQ0pK3WBV2gN; SUB=_2AkMhD0eLdcNhrAFZmP0SzG3rbolXzQ7wu9_0M03fZ2JCMnoQgT5nqiRotBF_DN7Dt0e6al7NzPhNs71jebD5Fh4XHuaWFWw.; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; _s_tentry=www.china.com.cn; Apache=1754437831696.1228.1449036929163; ULV=1449036929207:14:1:1:1754437831696.1228.1449036929163:1448331434952; DATA=usrmdinst_16; WBStore=5955be0e3d5411da|undefined; open_div=close; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; PHPSESSID=luoft8me2rj25ejpsdf0nv9u94", null);
//		System.out.println(strHtml);
		try {
			Weibo("http://weibo.cn/3952070245/profile?vt=4", "SUHB=04W_eBxMk2M3Zi; _T_WM=f4e4f7e3fb4a5c19490609c9b47733b5; SUB=_2A257YTugDeTxGeRG61cW8CjFyTiIHXVYqkXorDV6PUJbrdANLXb-kW0hYvWY4Fe_rc9WkLRO111DH98HEw..; gsid_CTandWM=4uVfb7ae118uReJsJrvR7bLTkeU");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	http:// weibo.cn/fbb0916?f=search_0&vt=4

}
