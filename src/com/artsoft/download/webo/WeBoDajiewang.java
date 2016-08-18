package com.artsoft.download.webo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.artsoft.bean.Tem_weibo_word_age_sex;
import com.artsoft.bean.Tem_weibo_word_area;
import com.artsoft.bean.Tem_weibo_word_tag;
import com.artsoft.bean.tem_weibo_word_num;
import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.downloadThreadpool.MyHaoSoutask;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeBoDajiewang {
	
	/**
	 * 微博基本数据
	 * **/

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
	
	public static String Weiboall(String newUrl) throws Exception {
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
		
		//get.addHeader("ContentType", "text/html; charset=utf-8"));
		
		
		
		get.addHeader(new BasicHeader("Cookie",
				"SINAGLOBAL=8858606864232.57.1447918197910; WEB3_PHP-FPM_BX=c6bf40b75e3394cc69125b6a8186b749; SUHB=0FQrI-caFr9Ekl; PHPSESSID=ksodvda9k8m5ut7n4853uis190; _s_tentry=login.sina.com.cn; Apache=4404089951422.065.1465977164977; ULV=1465977164986:3:1:1:4404089951422.065.1465977164977:1462960442581; myuid=3923882226; SUB=_2AkMgPYX4dcNhrABYmvsdxGngbotH-jzEiebBAn7uJhMyAxh77g00qSWE_hzUQtOQW953imvgXCU9NlbN4A..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFLv95IJ-mdsv8MBKZwnWZl5JpVhGUydJURUfvDUFR3Zntt; UOR=,,login.sina.com.cn; WBStore=8ca40a3ef06ad7b2|undefined"));
		
		get.addHeader("Accept", "*/*");
//		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		//get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		//get.addHeader("Cookie",
		//		"SINAGLOBAL=8549726845230.907.1445398578667; _s_tentry=fun.youth.cn; Apache=9864564227371.924.1464687037301; ULV=1464687037601:31:5:1:9864564227371.924.1464687037301:1463024037240; appkey=; un=764295333@qq.com; ULOGIN_IMG=14656991891358; SUHB=0WGNMFnY-fd5yZ; WEB3_PHP-FPM_BX=b4e45307e57611d895592857e89c154f; PHPSESSID=vm64vrd03jsilm939p4lkoa9g6; VARNISH-bx=9913ee32e2fe6443b57de6e7558564bf; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmc=31150890; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WBStore=8ca40a3ef06ad7b2|undefined; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com");

		get.addHeader("Host", "data.weibo.com");
		get.addHeader("Referer", "http://data.weibo.com/index/zone");
//		get.addHeader("Upgrade-Insecure-Requests", "1");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//		get.addHeader("Content-Type", "text/html;charset=UTF-8");
//		get.addHeader("charset=utf-8");
//		get.connect();
//		get.add
//		get.setHeader("Content-Type", "text/html; charset=utf-8");
		
//		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		
		HttpResponse httpResponse = new DefaultHttpClient().execute(get);
		
//		System.out.println(httpResponse.getStatusLine().getStatusCode());
		
		
//		  HttpEntity httpEntity = httpResponse.getEntity();  
//        String   result = EntityUtils.toString(httpEntity);//取出应答字符串  
//		
//		System.out.println(result.replaceAll("\r", ""));
		
		
//		System.out.println(httpResponse.wait(get));
//		response.setCharacterEncoding("UTF-8");
//		httpResponse
//		httpResponse.setHeader("Content-Type", "text/html; charset=utf-8");
//		System.out.println(httpResponse);
		
//		httpResponse.
//		String responseString = EntityUtils.toString(httpResponse.getEntity());
//		System.out.println(httpResponse.getEntity());
//		String responseString = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
//		textview.setText(responseString);
		
	
		
		
		
//		System.out.println(responseString.wait(timeout););
//		System.out.println( java.net.URLDecoder.decode(responseString,"GBK"));
		System.out.println(responseString);
		
		get.releaseConnection();
		
		return responseString;
//		return Test.weoborun(newUrl);
	}
	
	/** 
	    * @Title: methodPost  
	    * @Description: httpclient方法中post提交数据的使用  
	    * @param @return 
	    * @param @throws Exception    
	    * @return String     
	    * @throws 
	     */  
	    public static String methodPost() throws Exception {  
	        DefaultHttpClient httpclient = new DefaultHttpClient();  
	        // // 代理的设置  
	        // HttpHost proxy = new HttpHost("10.60.8.20", 8080);  
	        // httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,  
	        // proxy);  
	  
	        // 目标地址  
	        HttpPost httppost = new HttpPost(  
	                "http://data.weibo.com/index/ajax/contrast?key2=%25E5%25AD%2594%25E7%2590%25B3&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071");  
	        System.out.println("请求: " + httppost.getRequestLine());  
	   
	        // post 参数 传递  
	        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
	        nvps.add(new BasicNameValuePair("key2", "%E8%8C%83%E5%86%B0%E5%86%B0")); // 参数  
	        nvps.add(new BasicNameValuePair("_t", "0")); // 参数  
	        nvps.add(new BasicNameValuePair("__rnd", "1465958748340")); // 参数  
//	        nvps.add(new BasicNameValuePair("Accept-Encoding", "gzip, deflate, sdch")); // 参数  
//	        nvps.add(new BasicNameValuePair("Accept-Language", "zh-CN,zh;q=0.8")); // 参数  
//	        nvps.add(new BasicNameValuePair("Connection", "keep-alive")); // 参数  
//	        nvps.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded")); // 参数  
//	        nvps.add(new BasicNameValuePair("Cookie", "SINAGLOBAL=8549726845230.907.1445398578667; un=764295333@qq.com; SUHB=0WGNMFnY-fd5yZ; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WEB3_PHP-FPM_GD=c2fabe0d8b25ece0332132ca379fe277; _s_tentry=www.baidu.com; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; Apache=7013302213083.097.1465958451637; ULV=1465958451641:32:1:1:7013302213083.097.1465958451637:1464687037601; WBStore=8ca40a3ef06ad7b2|undefined; PHPSESSID=ecuqj6vsd7ee9jbbmt649nn1j7")); // 参数  
//	        nvps.add(new BasicNameValuePair("Host", "data.weibo.com")); // 参数  
//	        nvps.add(new BasicNameValuePair("Referer", "http://data.weibo.com/index/zone")); // 参数  
//	        nvps.add(new BasicNameValuePair("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36X-Requested-With:XMLHttpRequest")); // 参数  
	        httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post  
	  
	        // 执行  
	        HttpResponse response = httpclient.execute(httppost);  
	        HttpEntity entity = response.getEntity();  
	        System.out.println(response.getStatusLine());  
	        if (entity != null) {  
	            System.out.println("Response content length: "  
	                    + entity.getContentLength());  
	        }  
	        // 显示结果  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                entity.getContent(), "UTF-8"));  
	  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {  
	            System.out.println(line);  
	        }  
	        if (entity != null) {  
	            entity.consumeContent();  
	        }  
	        return null;  
	  
	    }  
	    
	    
	    /** 
	     * @Title: methodGet  
	     * @Description:  以get方法提交数的使用  
	     * @param @return 
	     * @param @throws Exception    
	     * @return String     
	     * @throws 
	      */  
	     public static  String methodGet() throws  Exception {   
	            DefaultHttpClient httpclient = new DefaultHttpClient();     
//	                // 代理的设置     
//	                 HttpHost proxy = new HttpHost("10.60.8.20", 8080);     
//	                 httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);     
	   
	   
	             // 目标地址     
	              HttpPost httpGet = new HttpPost("http://data.weibo.com/index/ajax/contrast");     
	                
	              // 构造最简单的字符串数据     
	               StringEntity reqEntity = new StringEntity("key2=%25E5%25AD%2594%25E7%2590%25B3&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071");     
	              // 设置类型     
//	               reqEntity.setContentType("application/x-www-form-urlencoded");     
	               
//	               reqEntity.set
	              // 设置请求的数据     
//	               httpGet.setEntity(reqEntity);
	               
//	               httpGet.setHeader(name, value);
	               
//	               httpGet.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
//	               httpGet.setHeader("Connection", "close");
//	               httpGet.setHeader("Content-Encoding", "gzip");
//	               
//	               httpGet.setHeader("Content-Type", "text/html; charset=utf-8");
//	               httpGet.setHeader("DPOOL_ARGS_MARK", "F");
//	               httpGet.setHeader("DPOOL_HEADER", "hathor178");
//	               httpGet.setHeader("DPOOL_LB7_HEADER", "hathor114");
//	               httpGet.setHeader("DPOOL_LU", "web3");
//	               httpGet.setHeader("DPOOL_URI_MARK", "F");
//	               httpGet.setHeader("Server", "Sina");
//	               httpGet.setHeader("Transfer-Encoding", "chunked");
//	               httpGet.setHeader("Vary", "Accept-Encoding");
	               
	               httpGet.addHeader("Accept", "*/*");
	               httpGet.addHeader("Accept-Encoding", "gzip, deflate, sdch");
	               httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
	       		//get.addHeader("Cache-Control", "max-age=0");
	               httpGet.addHeader("Connection", "keep-alive");
	               httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
	               httpGet.addHeader(new BasicHeader("Cookie",
	       				"SINAGLOBAL=8549726845230.907.1445398578667; un=764295333@qq.com; SUHB=0WGNMFnY-fd5yZ; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WEB3_PHP-FPM_GD=c2fabe0d8b25ece0332132ca379fe277; _s_tentry=www.baidu.com; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; Apache=7013302213083.097.1465958451637; ULV=1465958451641:32:1:1:7013302213083.097.1465958451637:1464687037601; WBStore=8ca40a3ef06ad7b2|undefined; PHPSESSID=ecuqj6vsd7ee9jbbmt649nn1j7"));
	       		//get.addHeader("Cookie",
	       		//		"SINAGLOBAL=8549726845230.907.1445398578667; _s_tentry=fun.youth.cn; Apache=9864564227371.924.1464687037301; ULV=1464687037601:31:5:1:9864564227371.924.1464687037301:1463024037240; appkey=; un=764295333@qq.com; ULOGIN_IMG=14656991891358; SUHB=0WGNMFnY-fd5yZ; WEB3_PHP-FPM_BX=b4e45307e57611d895592857e89c154f; PHPSESSID=vm64vrd03jsilm939p4lkoa9g6; VARNISH-bx=9913ee32e2fe6443b57de6e7558564bf; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmc=31150890; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WBStore=8ca40a3ef06ad7b2|undefined; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com");

	               httpGet.addHeader("Host", "data.weibo.com");
	               httpGet.addHeader("Referer", "http://data.weibo.com/index/zone");
//	       		get.addHeader("Upgrade-Insecure-Requests", "1");
	               httpGet.addHeader("User-Agent",
	       				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
	               httpGet.addHeader("X-Requested-With", "XMLHttpRequest");
	             
	              // 执行     
	              HttpResponse response = httpclient.execute(httpGet);     
	              HttpEntity entity = response.getEntity();     
	              System.out.println(response.getStatusLine());     
	                
	             if (entity != null) {     
	                System.out.println("Response content length: " + entity.getContentLength());  //得到返回数据的长度    
	              }     
	              // 显示结果     
	              BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));   
	                
	              String line = null;     
	             while ((line = reader.readLine()) != null) {     
	                  System.out.println(line);     
	              }     
	             if (entity != null) {     
	                   entity.consumeContent();     
	              }     
	             return null;   
	   
	         }  	    
	    
	
	     
	     
	     public static String doGet()  
	     {  
	         String uriAPI = "http://data.weibo.com/index/ajax/contrast?key2=%25E6%259D%25A8%25E6%25A1%2582%25E6%25A2%2585&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071";  
	         String result= "";  
//	       HttpGet httpRequst = new HttpGet(URI uri);  
//	       HttpGet httpRequst = new HttpGet(String uri);  
//	       创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。  
	         HttpGet httpRequst = new HttpGet(uriAPI);  
	         
	         httpRequst.addHeader(new BasicHeader("Cookie",
	 				"SINAGLOBAL=8549726845230.907.1445398578667; un=764295333@qq.com; SUHB=0WGNMFnY-fd5yZ; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WEB3_PHP-FPM_GD=c2fabe0d8b25ece0332132ca379fe277; _s_tentry=www.baidu.com; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; Apache=7013302213083.097.1465958451637; ULV=1465958451641:32:1:1:7013302213083.097.1465958451637:1464687037601; WBStore=8ca40a3ef06ad7b2|undefined; PHPSESSID=ecuqj6vsd7ee9jbbmt649nn1j7"));
	 		
	         httpRequst.addHeader("Accept", "*/*");
	         httpRequst.addHeader("Accept-Encoding", "gzip, deflate, sdch");
	         httpRequst.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
	 		//get.addHeader("Cache-Control", "max-age=0");
	         httpRequst.addHeader("Connection", "keep-alive");
	         httpRequst.addHeader("Content-Type", "application/x-www-form-urlencoded");
//	         httpRequst.addHeader("Cookie",
//	 				"SINAGLOBAL=8549726845230.907.1445398578667; _s_tentry=fun.youth.cn; Apache=9864564227371.924.1464687037301; ULV=1464687037601:31:5:1:9864564227371.924.1464687037301:1463024037240; appkey=; un=764295333@qq.com; ULOGIN_IMG=14656991891358; SUHB=0WGNMFnY-fd5yZ; WEB3_PHP-FPM_BX=b4e45307e57611d895592857e89c154f; PHPSESSID=vm64vrd03jsilm939p4lkoa9g6; VARNISH-bx=9913ee32e2fe6443b57de6e7558564bf; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmc=31150890; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WBStore=8ca40a3ef06ad7b2|undefined; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com");

	         httpRequst.addHeader("Host", "data.weibo.com");
	         httpRequst.addHeader("Referer", "http://data.weibo.com/index/zone");
//	 		get.addHeader("Upgrade-Insecure-Requests", "1");
	         httpRequst.addHeader("User-Agent",
	 				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
	         httpRequst.addHeader("X-Requested-With", "XMLHttpRequest");
	   
//	       new DefaultHttpClient().execute(HttpUriRequst requst);  
	         try {  
	    //使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。  
	             HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类  
	             if(httpResponse.getStatusLine().getStatusCode() == 200)  
	             {  
	                 HttpEntity httpEntity = httpResponse.getEntity();  
	                 result = EntityUtils.toString(httpEntity);//取出应答字符串  
	             // 一般来说都要删除多余的字符   
	                 result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
	                 System.out.println(result);
	             }  
	                    else   
	                         httpRequst.abort();  
	            } catch (ClientProtocolException e) {  
	             // TODO Auto-generated catch block  
	             e.printStackTrace();  
	             result = e.getMessage().toString();  
	         } catch (IOException e) {  
	             // TODO Auto-generated catch block  
	             e.printStackTrace();  
	             result = e.getMessage().toString();  
	         }  
	         return result;  
	     }  
	     
	     
	     
	     /**
	     httpClient的get请求方式2
	     * @return
	     * @throws Exception
	     */
	    public static String doGet(String url, String charset)
	        throws Exception {
	      /*
	       * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
	       * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get
	       * 方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
	       */
	      /* 1 生成 HttpClinet 对象并设置参数 */
	      HttpClient httpClient = new HttpClient();
	      // 设置 Http 连接超时为5秒
	      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
	      /* 2 生成 GetMethod 对象并设置参数 */
	      GetMethod getMethod = new GetMethod(url);
	      
	      
	      
	    //设置http头  
	        List<Header> headers = new ArrayList<Header>();  
	        headers.add(new Header("Accept", "*/*"));  
	        headers.add(new Header("Accept-Encoding", "gzip, deflate, sdch"));  
	        headers.add(new Header("Accept-Language", "zh-CN,zh;q=0.8"));  
	        headers.add(new Header("Connection", "keep-alive"));  
	        headers.add(new Header("Content-Type", "application/x-www-form-urlencoded"));  
	        
	        headers.add(new Header("Cookie", "MSINAGLOBAL=8549726845230.907.1445398578667; un=764295333@qq.com; SUHB=0WGNMFnY-fd5yZ; __utma=31150890.1895517594.1465899683.1465899683.1465899683.1; __utmz=31150890.1465899683.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); myuid=2805706924; SUB=_2AkMgPI5cdcNhrAFZmP0SzG3rbolH-jzEiebBAn7uJhMyAxh77k4hqSWwEaD6lLDhD8ViOPBOFfUs5eIiQQ..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; WEB3_PHP-FPM_GD=c2fabe0d8b25ece0332132ca379fe277; _s_tentry=www.baidu.com; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; Apache=7013302213083.097.1465958451637; ULV=1465958451641:32:1:1:7013302213083.097.1465958451637:1464687037601; WBStore=8ca40a3ef06ad7b2|undefined; PHPSESSID=ecuqj6vsd7ee9jbbmt649nn1j7; WEB3_PHP-FPM_BX=6fb2ead3c5f48fa5c2e869be7b88f9fc"));  
	        
	        headers.add(new Header("Host", "data.weibo.com"));  
	        headers.add(new Header("Referer", "http://data.weibo.com/index/zone"));  
	        
	        
	        headers.add(new Header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36"));  
	        headers.add(new Header("X-Requested-With", "XMLHttpRequest"));  
//	        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));  
	        
	        
	        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
	      
//	      getMethod
	      
	      // 设置 get 请求超时为 5 秒
	      getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
	      // 设置请求重试处理，用的是默认的重试处理：请求三次
	      getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,	new DefaultHttpMethodRetryHandler());
	      
	      
	      
	      String response = "";
	      /* 3 执行 HTTP GET 请求 */
	      try {
	        int statusCode = httpClient.executeMethod(getMethod);
	        /* 4 判断访问的状态码 */
	        if (statusCode != HttpStatus.SC_OK) {
	          System.err.println("请求出错: "+ getMethod.getStatusLine());
	        }
	        
	        
//	        /* 5 处理 HTTP 响应内容 */
//	        // HTTP响应头部信息，这里简单打印
//	        Header[] headers = getMethod.getResponseHeaders();
//	        for (Header h : headers)
//	          System.out.println(h.getName() + "------------ " + h.getValue());
	        
	        
	        Header[] headerss = getMethod.getResponseHeaders();
	        for (Header h : headerss)
	          System.out.println(h.getName() + "------------ " + h.getValue());
	        
//	        MethodRetryHandler Footers =  getMethod.getMethodRetryHandler();
//	        System.out.println(Footers);
	        
	        // 读取 HTTP 响应内容，这里简单打印网页内容
	        byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
	        	        
	        response = new String(responseBody, charset);
	        System.out.println("----------response:" + response);
	        // 读取为 InputStream，在网页内容数据量大时候推荐使用
	        // InputStream response = getMethod.getResponseBodyAsStream();
	      } catch (HttpException e) {
	        // 发生致命的异常，可能是协议不对或者返回的内容有问题
	        System.out.println("请检查输入的URL!");
	        e.printStackTrace();
	      } catch (IOException e) {
	        // 发生网络异常
	        System.out.println("发生网络异常!");
	        e.printStackTrace();
	      } finally {
	        /* 6 .释放连接 */
	        getMethod.releaseConnection();
	      }
	      return response;
	    }
	

	public static String Weibo(String newUrl) throws Exception {
//		// TODO Auto-generated method stub
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpResponse response = null;
//		System.out.println("******************************页面转向******************************");
//		// String newUrl =
//		// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
//		HttpGet get = new HttpGet(newUrl);
//		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
//		// get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
//		// rv:26.0) Gecko/20100101 Firefox/26.0");
//		// get.addHeader("Host", "data.weibo.com");
//		// get.addHeader("Accept",
//		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		get.addHeader("Accept", "*/*");
//		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
//		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		get.addHeader("Cache-Control", "max-age=0");
//		get.addHeader("Connection", "keep-alive");
//		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
//		get.addHeader(new BasicHeader("Cookie",
//				"SINAGLOBAL=8549726845230.907.1445398578667; SUHB=0sqQ0pK3WBV2gN; SUB=_2AkMhLpLRdcNhrAFZmP0SzG3rbolXzQ7wu9_0M03fZ2JCMnoQgT5nqiRotBF_DN7Orke6kXahkJdZP3wN_xXCDM1NAU5yAAw.; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFVId20mkyG_N-5ejfVKF0s5JpV2hMcShz4SKe0eXWpMC4odcXt; DATA=usrmdinst_0; WBStore=8ca40a3ef06ad7b2|undefined; _s_tentry=www.baidu.com; Apache=3636198288224.295.1463024037215; ULV=1463024037240:30:4:3:3636198288224.295.1463024037215:1462874953123; UOR=picture.youth.cn,widget.weibo.com,www.baidu.com; PHPSESSID=m5v11jk24squ83afqf6m94grp7"));
//
//		get.addHeader("Host", "data.weibo.com");
//		get.addHeader("Referer", "http://data.weibo.com/index/hotword?wid=1091324230349&wname=%E6%AC%A2%E4%B9%90%E9%A2%82");
////		get.addHeader("Upgrade-Insecure-Requests", "1");
//		get.addHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
//		get.addHeader("X-Requested-With", "XMLHttpRequest");
//		// get.addHeader("Accept-Language",
//		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//
//		HttpResponse httpResponse = client.execute(get);
//		String responseString = EntityUtils.toString(httpResponse.getEntity());
//		// 登录后首页的内容
//		System.out.println(responseString);
//		get.releaseConnection();
//		return responseString;
		return TimeoutTest.runing(newUrl);
	}

	public static void people(String names, int data_type, String  data_id) throws Exception {
		// String strHtml =
		// DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword?wname=范冰冰",
		// 1000 * 30, "UTF-8",
		// null, null);、
		// http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108

		// DownloadUtil.getHtmlText("http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108",
		// 1000 * 30, "UTF-8",
		// null, null);
		String strHtml = DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword?wid=1091324101491&wname=%E4%BD%A0%E5%A5%BD", 1000 * 30, "UTF-8", null,
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
		String url="http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s;
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
			String NEWS_NUM="";
			String DATA_DATE="";
			for (Object object2 : arrayjsonname) {
				tem_weibo_word_num tem_weibo_word_num= new tem_weibo_word_num();
				tem_weibo_word_num.setWord(keywordnames);
				tem_weibo_word_num.setDataId(data_id);
				System.out.println(object2);
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(DATA_DATE=(String) objectobjects2.get("day_key"));
				if (DATA_DATE!=null&&!DATA_DATE.equals("")) {
					DATA_DATE=DATA_DATE.replace("-", "");
					tem_weibo_word_num.setDataDate(DATA_DATE);
					System.out.println(objectobjects2.get("wid"));
					System.out.println(NEWS_NUM=(String) objectobjects2.get("value"));
					tem_weibo_word_num.setNewsNum(Integer.valueOf(NEWS_NUM));
					tem_weibo_word_num.setDimensionType(1);
					tem_weibo_word_num.setDataType(data_type);
					tem_weibo_word_num.setUrl(url);
					Oracle.InsertCompany(tem_weibo_word_num);
				}
				
			}

			JSONArray arrayjsonnameyd = new JSONArray();
			arrayjsonnameyd = (JSONArray) objectobjects.get("yd");
			for (Object object2 : arrayjsonnameyd) {
				tem_weibo_word_num tem_weibo_word_num= new tem_weibo_word_num();
				tem_weibo_word_num.setWord(keywordnames);
//				System.out.println(object2);
				String pc="";
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(DATA_DATE=(String)objectobjects2.get("daykey").toString().replace("-", ""));
				tem_weibo_word_num.setDataDate(DATA_DATE);
				System.out.println(pc=(String) objectobjects2.get("pc"));
				tem_weibo_word_num.setDataType(data_type);
//				tem_weibo_word_num.setDimensionType(1);
				tem_weibo_word_num.setDimensionType(2);
				tem_weibo_word_num.setNewsNum(Integer.valueOf(pc));
				tem_weibo_word_num.setDataId(data_id);
				tem_weibo_word_num.setUrl(url);
				Oracle.InsertCompany(tem_weibo_word_num);
				String mobile="";
				System.out.println(mobile=(String) objectobjects2.get("mobile"));
				tem_weibo_word_num.setDimensionType(3);
				tem_weibo_word_num.setNewsNum(Integer.valueOf(mobile));
				Oracle.InsertCompany(tem_weibo_word_num);
			}

		}
		String wid = HtmlAnalyze.getTagText(hostnum, "\"wid\":\"", "\"");
		System.out.println("地理位置");
		String keywordzone = Weibo(
				"http://data.weibo.com/index/ajax/keywordzone?type=notdefault&wid=" + wid + "&__rnd=" + s);
		String urlair="http://data.weibo.com/index/ajax/keywordzone?type=notdefault&wid=" + wid + "&__rnd=" + s;
		keywordzone = DownloadUtil.decodeUnicode(keywordzone);
		
//		JSONArray authors = new JSONArray();
//		authors = (JSONArray) objectobject.get("data");
		JSONObject objectkeywordzones = JSONObject.fromObject(keywordzone);
		
//		System.out.println(objectkeywordzones.get("zone"));
		JSONObject objectkeywordzone=JSONObject.fromObject(objectkeywordzones.get("zone"));
//		System.out.println(objectkeywordzone.get("jiangsu"));
		//地域热议度
		keywordzone(objectkeywordzone,  data_type,   data_id,urlair);
		
		JSONObject objectkeyworduser=JSONObject.fromObject(objectkeywordzones.get("user"));
		//用户热议度
		keywordzoneuser(objectkeyworduser,  data_type,   data_id,urlair);
		
		String getdefaultattributealldata=Weibo("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=" + s);
		url="http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=" + s;
		getdefaultattributealldata = DownloadUtil.decodeUnicode(getdefaultattributealldata);
		System.out.println(getdefaultattributealldata);
		
		Tem_weibo_word_age_sex tem_weibo_word_age_sex=new Tem_weibo_word_age_sex();
		tem_weibo_word_age_sex.setUrl(url);
		tem_weibo_word_age_sex.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
		tem_weibo_word_age_sex.setDataId(data_id);
		tem_weibo_word_age_sex.setWord(keywordnames);
		
		JSONObject objectkeyworduserda=JSONObject.fromObject(getdefaultattributealldata);
		JSONObject objectkeyworduserdata=JSONObject.fromObject(objectkeyworduserda.get("data"));
		
		String sexMan="";
		String sexwoman="";
		JSONObject objectkeyworduserdatasex=JSONObject.fromObject(objectkeyworduserdata.get("sex"));
		JSONObject objectkeyworduserdatasexkey2=JSONObject.fromObject(objectkeyworduserdatasex.get("key2"));
		System.out.println(objectkeyworduserdatasexkey2.get("man"));
//		JSONObject objectkeyworduserdatasexkey2man=JSONObject.fromObject(objectkeyworduserdatasexkey2.get("man"));
//		JSONObject objectkeyworduserdatasexkey2woman=JSONObject.fromObject(objectkeyworduserdatasexkey2.get("woman"));
//		JSONObject objectkeyworduserdatasexkey2word=JSONObject.fromObject(objectkeyworduserdatasexkey2.get("word"));
		sexMan=(String) objectkeyworduserdatasexkey2.get("man");
		tem_weibo_word_age_sex.setManRate(sexMan);
		sexwoman=(String) objectkeyworduserdatasexkey2.get("woman");
		tem_weibo_word_age_sex.setWomanRate(sexwoman);
		tem_weibo_word_age_sex.setDataType(data_type);
		String age0_12="";
		String age12_18="";
		String age19_24="";
		String age25_34="";
		String age35_50="";
		String ageother="";
		JSONObject objectkeyworduserdataage=JSONObject.fromObject(objectkeyworduserdata.get("age"));
		JSONObject objectkeyworduserdataagekey2=JSONObject.fromObject(objectkeyworduserdataage.get("key2"));
		JSONObject objectkeyworduserdataagekey2_0=JSONObject.fromObject(objectkeyworduserdataagekey2.get("0"));
//		JSONObject objectkeyworduserdataagekey2_0_0_12=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("0-12"));
//		JSONObject objectkeyworduserdataagekey2_0_12_18=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("12-18"));
//		JSONObject objectkeyworduserdataagekey2_0_19_24=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("19-24"));
//		JSONObject objectkeyworduserdataagekey2_0_25_34=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("25-34"));
//		JSONObject objectkeyworduserdataagekey2_0_35_50=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("35-50"));
//		JSONObject objectkeyworduserdataagekey2_0_ageother=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("other"));
		 age0_12=objectkeyworduserdataagekey2_0.get("0-12").toString();
		 age12_18=objectkeyworduserdataagekey2_0.get("12-18").toString();
		 age19_24=objectkeyworduserdataagekey2_0.get("19-24").toString();
		 age25_34=objectkeyworduserdataagekey2_0.get("25-34").toString();
		 age35_50=objectkeyworduserdataagekey2_0.get("35-50").toString();
		 ageother=objectkeyworduserdataagekey2_0.get("other").toString();
		 tem_weibo_word_age_sex.setRate12(age0_12);
		 tem_weibo_word_age_sex.setRate18v(age12_18);
		 tem_weibo_word_age_sex.setRate24(age19_24);
		 tem_weibo_word_age_sex.setRate34(age25_34);
		 tem_weibo_word_age_sex.setRate50(age35_50);
		 tem_weibo_word_age_sex.setOtherRate(ageother);
		 
//		JSONObject objectkeyworduserdataagekey2_0_0_12=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("0-12"));
		 Oracle.InsertCompany(tem_weibo_word_age_sex);
		 
		 
		 Tem_weibo_word_tag tem_weibo_word_tag=new Tem_weibo_word_tag();
		 tem_weibo_word_tag.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
		 tem_weibo_word_tag.setDataId(data_id);
		 tem_weibo_word_tag.setWord(keywordnames);
		 tem_weibo_word_tag.setDataType(data_type);
		 tem_weibo_word_tag.setDimensionType(1);
		 tem_weibo_word_tag.setUrl(url);
		String meishi="";
		String luyou="";
		String mingrenmingxing="";
		String lule="";
		String gaoxiaoyoumo="";
		JSONObject objectkeyworduserdatatag=JSONObject.fromObject(objectkeyworduserdata.get("tag"));
		JSONObject objectkeyworduserdatatagkey2=JSONObject.fromObject(objectkeyworduserdatatag.get("key2"));
		JSONObject objectkeyworduserdatatagkey2_0=JSONObject.fromObject(objectkeyworduserdatatagkey2.get("0"));
//		JSONObject objectkeyworduserdatatagkey2_0meishi=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("美食"));
//		JSONObject objectkeyworduserdatatagkey2_0luyou=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("旅游"));
//		JSONObject objectkeyworduserdatatagkey2_0mingrenmingxing=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("名人明星"));
//		JSONObject objectkeyworduserdatatagkey2_0lule=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("娱乐"));
//		JSONObject objectkeyworduserdatatagkey2_0gaoxiaoyoumo=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("搞笑幽默"));
		 meishi=(String) objectkeyworduserdatatagkey2_0.get("美食");
		 tem_weibo_word_tag.setLabelName("美食");
		 tem_weibo_word_tag.setLabelRate(meishi);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 luyou=(String) objectkeyworduserdatatagkey2_0.get("旅游");
		 tem_weibo_word_tag.setLabelName("旅游");
		 tem_weibo_word_tag.setLabelRate(luyou);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 mingrenmingxing=(String) objectkeyworduserdatatagkey2_0.get("名人明星");
		 tem_weibo_word_tag.setLabelName("名人明星");
		 tem_weibo_word_tag.setLabelRate(mingrenmingxing);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 lule=(String) objectkeyworduserdatatagkey2_0.get("娱乐");
		 tem_weibo_word_tag.setLabelName("娱乐");
		 tem_weibo_word_tag.setLabelRate(lule);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 gaoxiaoyoumo=(String) objectkeyworduserdatatagkey2_0.get("搞笑幽默");
		 tem_weibo_word_tag.setLabelName("搞笑幽默");
		 tem_weibo_word_tag.setLabelRate(gaoxiaoyoumo);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		String moxie="";
		String shuiping="";
		String shuangyu="";
		String baiyang="";
		String jinniu="";
		String shuangzi="";
		String juxie="";
		String shizi="";
		String chunv="";
		String tianping="";
		String 	tianxie="";
		String sheshou="";
		tem_weibo_word_tag.setDimensionType(2);
		JSONObject objectkeyworduserdatastar=JSONObject.fromObject(objectkeyworduserdata.get("star"));
		JSONObject objectkeyworduserdatastarkey2=JSONObject.fromObject(objectkeyworduserdatastar.get("key2"));
		JSONObject objectkeyworduserdatastarkey2_0=JSONObject.fromObject(objectkeyworduserdatastarkey2.get("0"));
//		JSONObject objectkeyworduserdatastarkey2_0moxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("摩羯座"));
//		JSONObject objectkeyworduserdatastarkey2_0shuiping=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("水瓶座"));
//		JSONObject objectkeyworduserdatastarkey2_0shuangyu=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("双鱼座"));
//		JSONObject objectkeyworduserdatastarkey2_0baiyang=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("白羊座"));
//		JSONObject objectkeyworduserdatastarkey2_0jinniu=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("金牛座"));
//		JSONObject objectkeyworduserdatastarkey2_0shuangzi=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("双子座"));
//		JSONObject objectkeyworduserdatastarkey2_0juxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("巨蟹座"));
//		JSONObject objectkeyworduserdatastarkey2_0shizi=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("狮子座"));
//		JSONObject objectkeyworduserdatastarkey2_0chunv=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("处女座"));
//		JSONObject objectkeyworduserdatastarkey2_0tianping=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("天秤座"));
//		JSONObject objectkeyworduserdatastarkey2_0tianxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("天蝎座"));
//		JSONObject objectkeyworduserdatastarkey2_0sheshou=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("射手座"));
//		JSONObject objectkeyworduserdatastarkey2_0moxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("摩羯座"));
		
		 moxie=objectkeyworduserdatastarkey2_0.get("摩羯座").toString();
		 tem_weibo_word_tag.setLabelName("摩羯座");
		 tem_weibo_word_tag.setLabelRate(moxie);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 shuiping=objectkeyworduserdatastarkey2_0.get("水瓶座").toString();
		 tem_weibo_word_tag.setLabelName("水瓶座");
		 tem_weibo_word_tag.setLabelRate(shuiping);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 shuangyu=objectkeyworduserdatastarkey2_0.get("双鱼座").toString();
		 tem_weibo_word_tag.setLabelName("双鱼座");
		 tem_weibo_word_tag.setLabelRate(shuangyu);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 baiyang=objectkeyworduserdatastarkey2_0.get("白羊座").toString();
		 tem_weibo_word_tag.setLabelName("白羊座");
		 tem_weibo_word_tag.setLabelRate(baiyang);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 jinniu=objectkeyworduserdatastarkey2_0.get("金牛座").toString();
		 tem_weibo_word_tag.setLabelName("金牛座");
		 tem_weibo_word_tag.setLabelRate(jinniu);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 shuangzi=objectkeyworduserdatastarkey2_0.get("双子座").toString();
		 tem_weibo_word_tag.setLabelName("双子座");
		 tem_weibo_word_tag.setLabelRate(shuangzi);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 juxie=objectkeyworduserdatastarkey2_0.get("巨蟹座").toString();
		 tem_weibo_word_tag.setLabelName("巨蟹座");
		 tem_weibo_word_tag.setLabelRate(juxie);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 shizi=objectkeyworduserdatastarkey2_0.get("狮子座").toString();
		 tem_weibo_word_tag.setLabelName("狮子座");
		 tem_weibo_word_tag.setLabelRate(shizi);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 chunv=objectkeyworduserdatastarkey2_0.get("处女座").toString();
		 tem_weibo_word_tag.setLabelName("处女座");
		 tem_weibo_word_tag.setLabelRate(chunv);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 tianping=objectkeyworduserdatastarkey2_0.get("天秤座").toString();
		 tem_weibo_word_tag.setLabelName("天秤座");
		 tem_weibo_word_tag.setLabelRate(tianping);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 tianxie=objectkeyworduserdatastarkey2_0.get("天蝎座").toString();
		 tem_weibo_word_tag.setLabelName("天蝎座");
		 tem_weibo_word_tag.setLabelRate(tianxie);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		 sheshou=objectkeyworduserdatastarkey2_0.get("射手座").toString();
		 tem_weibo_word_tag.setLabelName("射手座");
		 tem_weibo_word_tag.setLabelRate(sheshou);
		 Oracle.InsertCompany(tem_weibo_word_tag);
		
		
		
	}

	/**
	 * 地域热议度
	 * @param objectkeywordzone
	 */
	public static void keywordzone(JSONObject objectkeywordzone, int data_type, String data_id,String urlair){
		String pingyin="";
		String address="";
		String value="";
		String ct="";
		String index="";
		String wname="";
		String stateInitColor="";
		
		String[][] numsix={{"chongqing","重庆"},{"zhejiang","浙江"},{"yunnan","云南"},{"xinjiang","新疆"},{"hongkong","香港"},{"xizang","西藏"},{"tianjin","天津"},{"taiwan","台湾"},{"sichuan","四川"},{"shanghai","上海"},{"shaanxi","陕西"},{"shanxi","山西"},{"shandong","山东"},{"qinghai","青海"},{"ningxia","宁夏"},{"neimongol","内蒙古"},{"liaoning","辽宁"},{"jiangxi","江西"},{"jiangsu","江苏"},{"jilin","吉林"},{"hunan","湖南"},{"hubei","湖北"},{"heilongjiang","黑龙江"},{"henan","河南"},{"hebei","河北"},{"hainan","海南"},{"guizhou","贵州"},{"guangxi","广西"},{"guangdong","广东"},{"gansu","甘肃"},{"fujian","福建"},{"beijing","北京"},{"macau","澳门"},{"anhui","安徽"}};
//		System.out.println(objectkeywordzone.get("jiangsu"));
		for (int i = 0; i < numsix.length; i++) {
			pingyin=numsix[i][0];
			address=numsix[i][1];
			System.out.println(pingyin+address);
			try {
				Tem_weibo_word_area tem_weibo_word_area=new Tem_weibo_word_area();
				JSONObject objectkeywordzones=JSONObject.fromObject(objectkeywordzone.get(pingyin));
				//{"value":"9.16%","ct":"7055","index":"2","wname":"邓超","stateInitColor":"61B6FD"}
				tem_weibo_word_area.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
				tem_weibo_word_area.setDataType(data_type);
				tem_weibo_word_area.setDataId(data_id);
				tem_weibo_word_area.setAreaNameEn(pingyin);
				tem_weibo_word_area.setAreaNameCn(address);
				tem_weibo_word_area.setDimensionType(1);
				value=(String) objectkeywordzones.get("value");
				//area_rate
				tem_weibo_word_area.setAreaRate(value);
				ct=(String) objectkeywordzones.get("ct");
				tem_weibo_word_area.setAreaNum(ct);
				index=(String) objectkeywordzones.get("index");
				tem_weibo_word_area.setAreaTop(index);
				wname=(String) objectkeywordzones.get("wname");
				tem_weibo_word_area.setWord(wname);
				tem_weibo_word_area.setUrl(urlair);
				stateInitColor=(String) objectkeywordzones.get("stateInitColor");
				
				System.out.println(value+ct+index+wname+stateInitColor);
				
				Oracle.InsertCompany(tem_weibo_word_area);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	////用户热议度
	public static void keywordzoneuser(JSONObject objectkeywordzone, int data_type, String data_id,String urlair){
		String pingyin="";
		String address="";
		String value="";
		String num="";
		String index="";
		String wname="";
		String stateInitColor="";
		
		String[][] numsix={{"chongqing","重庆"},{"zhejiang","浙江"},{"yunnan","云南"},{"xinjiang","新疆"},{"hongkong","香港"},{"xizang","西藏"},{"tianjin","天津"},{"taiwan","台湾"},{"sichuan","四川"},{"shanghai","上海"},{"shaanxi","陕西"},{"shanxi","山西"},{"shandong","山东"},{"qinghai","青海"},{"ningxia","宁夏"},{"neimongol","内蒙古"},{"liaoning","辽宁"},{"jiangxi","江西"},{"jiangsu","江苏"},{"jilin","吉林"},{"hunan","湖南"},{"hubei","湖北"},{"heilongjiang","黑龙江"},{"henan","河南"},{"hebei","河北"},{"hainan","海南"},{"guizhou","贵州"},{"guangxi","广西"},{"guangdong","广东"},{"gansu","甘肃"},{"fujian","福建"},{"beijing","北京"},{"macau","澳门"},{"anhui","安徽"}};
//		System.out.println(objectkeywordzone.get("jiangsu"));
		for (int i = 0; i < numsix.length; i++) {
			pingyin=numsix[i][0];
			address=numsix[i][1];
			System.out.println(pingyin+address);
			try {
				Tem_weibo_word_area tem_weibo_word_area=new Tem_weibo_word_area();
				tem_weibo_word_area.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
				tem_weibo_word_area.setDataType(data_type);
				tem_weibo_word_area.setDataId(data_id);
				tem_weibo_word_area.setAreaNameEn(pingyin);
				tem_weibo_word_area.setAreaNameCn(address);
				tem_weibo_word_area.setDimensionType(2);
				JSONObject objectkeywordzones=JSONObject.fromObject(objectkeywordzone.get(pingyin));
				//{"value":"9.16%","ct":"7055","index":"2","wname":"邓超","stateInitColor":"61B6FD"}
				value=(String) objectkeywordzones.get("value");
				tem_weibo_word_area.setAreaRate(value);
				num=(String) objectkeywordzones.get("num");
				tem_weibo_word_area.setAreaNum(num);
				index=(String) objectkeywordzones.get("index");
				tem_weibo_word_area.setAreaTop(index);
				wname=(String) objectkeywordzones.get("wname");
				tem_weibo_word_area.setWord(wname);
				tem_weibo_word_area.setUrl(urlair);
				stateInitColor=(String) objectkeywordzones.get("stateInitColor");
				System.out.println(pingyin+pingyin+value+num+index+wname+stateInitColor);
				
				Oracle.InsertCompany(tem_weibo_word_area);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	public static void webopeople(String name ,int data_type,String  data_id){
//		String name = "范冰冰";
		String URLEncodername = "";
		String codeString ="";
		try {
			System.out.println(
					URLEncodername = java.net.URLEncoder.encode(java.net.URLEncoder.encode(name, "utf-8"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(codeString=Weibo("http://data.weibo.com/index/ajax/contrast?key2=" + URLEncodername
					+ "&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JSONObject objectobject = JSONObject.fromObject(codeString);
			String codeshuju=objectobject.getString("code");
			if (codeshuju.equals("100000")) {
				System.out.println("含有关键字");
				people(name, data_type,data_id);
			}else{
				if (codeshuju.equals("100001")) {
					System.out.println("没有关键词");
				}
				else{
					System.out.println("出现问题");
					CommonUtil.setLog( "出现问题"+TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + "name："+name+"id:"+data_id);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 人的数据的采集
	 */

	private static void mainPeoPle(int statnum, int endnum) {
		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			/**
			 * 名称  
			 * data_type 类型 1 人 2 电视剧 3 电影
			 * data_id  人物id
			 */
			try {
				webopeople(listTemp.get(1),1,listTemp.get(0));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		String returnNumPeople = OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
		System.out.println("需要采集的人名字数为" + returnNumPeople);
		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			mainPeoPle(i, i + 1000);
		}
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
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
			}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
		}
	

	public static void main(String[] args) throws Exception {
		
		
//		Weiboall("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=1465910360625");
		TimingTime(00, 00, 01);
		
//		 runstatic();
		/**
		 * 名称  
		 * data_type 类型 1 人 2 电视剧 3 电影
		 * data_id  人物id
		 */
//		webopeople("王宝强",1,"2346");
//		daJeWang();
//		System.out.println("运行网吧");
//		methodPost();
//		 methodGet() ;
		
//		doGet();
		
//		doGet("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=1465910360625", "utf-8");
		
		
		
	}
}
