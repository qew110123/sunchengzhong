package com.artsoft.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpclientHeaders {

	/**
	 * httpClient的get请求方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, String charset) throws Exception {
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

		// 设置http头
		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header("Accept", "*/*"));
//		headers.add(new Header("Accept-Encoding", "gzip, deflate, sdch"));
		headers.add(new Header("Accept-Language", "zh-CN,zh;q=0.8"));
		headers.add(new Header("Connection", "keep-alive"));
		headers.add(new Header("Content-Type", "application/x-www-form-urlencoded"));

		headers.add(new Header("Cookie",
				"SINAGLOBAL=8858606864232.57.1447918197910; WEB3_PHP-FPM_BX=c6bf40b75e3394cc69125b6a8186b749; SUHB=0FQrI-caFr9Ekl; PHPSESSID=ksodvda9k8m5ut7n4853uis190; _s_tentry=login.sina.com.cn; Apache=4404089951422.065.1465977164977; ULV=1465977164986:3:1:1:4404089951422.065.1465977164977:1462960442581; myuid=3923882226; SUB=_2AkMgPYX4dcNhrABYmvsdxGngbotH-jzEiebBAn7uJhMyAxh77g00qSWE_hzUQtOQW953imvgXCU9NlbN4A..; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WFLv95IJ-mdsv8MBKZwnWZl5JpVhGUydJURUfvDUFR3Zntt; UOR=,,login.sina.com.cn; WBStore=8ca40a3ef06ad7b2|undefined"));

		headers.add(new Header("Host", "data.weibo.com"));
		headers.add(new Header("Referer", "http://data.weibo.com/index/zone"));

		headers.add(new Header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36"));
		headers.add(new Header("X-Requested-With", "XMLHttpRequest"));
//		
		
		// headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE
		// 7.0; Windows NT 5.1)"));
		
		
		httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);

		// getMethod

		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("请求出错: " + getMethod.getStatusLine());
			}

			// /* 5 处理 HTTP 响应内容 */
			// // HTTP响应头部信息，这里简单打印
			// Header[] headers = getMethod.getResponseHeaders();
			// for (Header h : headers)
			// System.out.println(h.getName() + "------------ " + h.getValue());

			Header[] headerss = getMethod.getResponseHeaders();
			for (Header h : headerss)
				System.out.println(h.getName() + "------------ " + h.getValue());

			// MethodRetryHandler Footers = getMethod.getMethodRetryHandler();
			// System.out.println(Footers);

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
	
	
	
	public static String getDoGetURL2(String url, String charset)
            throws Exception {
        // 创建httpclinet对象，进行http请求
        HttpClient httpClient = new HttpClient();
        // 设置超时连接时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
                5000);
        // 使用get方法访问url
        GetMethod getMethod = new GetMethod(url);
        // 设置get请求超时连接
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 应该是重新发送get请求时的处理对象
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        // 存储服务器端响应数据
        String response = "";
        try {
            // get请求过后的状态码，像服务器端有404或者200等等
            int statusCode = httpClient.executeMethod(getMethod);
            // 如果状态码不为SC_OK,输出错误信息
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            // 拿到get请求头部信息
            Header[] headers = getMethod.getResponseHeaders();
            for (Header h : headers)
                System.out
                        .println(h.getName() + "------------ " + h.getValue());
            // 拿到响应数据
            byte[] responseBody = getMethod.getResponseBody(); // 读取为字节数组
            // 对响应数据进行字符集编码
            response = new String(responseBody, charset);
            System.out.println("----------response:" + response);
        } catch (HttpException e) {
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 最后释放http连接
            getMethod.releaseConnection();
        }
        // 返回响应数据
        return response;
    }

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try {
			doGet("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=1465910360625", "utf-8");
			
//			getDoGetURL2("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=1465910360625", "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
