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
	 * httpClient��get����ʽ
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, String charset) throws Exception {
		/*
		 * ʹ�� GetMethod ������һ�� URL ��Ӧ����ҳ,ʵ�ֲ���: 1:����һ�� HttpClinet ����������Ӧ�Ĳ�����
		 * 2:����һ�� GetMethod ����������Ӧ�Ĳ����� 3:�� HttpClinet ���ɵĶ�����ִ�� GetMethod ���ɵ�Get
		 * ������ 4:������Ӧ״̬�롣 5:����Ӧ���������� HTTP ��Ӧ���ݡ� 6:�ͷ����ӡ�
		 */
		/* 1 ���� HttpClinet �������ò��� */
		HttpClient httpClient = new HttpClient();
		// ���� Http ���ӳ�ʱΪ5��
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		/* 2 ���� GetMethod �������ò��� */
		GetMethod getMethod = new GetMethod(url);

		// ����httpͷ
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

		// ���� get ����ʱΪ 5 ��
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// �����������Դ����õ���Ĭ�ϵ����Դ�����������
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		String response = "";
		/* 3 ִ�� HTTP GET ���� */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 �жϷ��ʵ�״̬�� */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("�������: " + getMethod.getStatusLine());
			}

			// /* 5 ���� HTTP ��Ӧ���� */
			// // HTTP��Ӧͷ����Ϣ������򵥴�ӡ
			// Header[] headers = getMethod.getResponseHeaders();
			// for (Header h : headers)
			// System.out.println(h.getName() + "------------ " + h.getValue());

			Header[] headerss = getMethod.getResponseHeaders();
			for (Header h : headerss)
				System.out.println(h.getName() + "------------ " + h.getValue());

			// MethodRetryHandler Footers = getMethod.getMethodRetryHandler();
			// System.out.println(Footers);

			// ��ȡ HTTP ��Ӧ���ݣ�����򵥴�ӡ��ҳ����
			byte[] responseBody = getMethod.getResponseBody();// ��ȡΪ�ֽ�����

			response = new String(responseBody, charset);
			System.out.println("----------response:" + response);
			// ��ȡΪ InputStream������ҳ������������ʱ���Ƽ�ʹ��
			// InputStream response = getMethod.getResponseBodyAsStream();
		} catch (HttpException e) {
			// �����������쳣��������Э�鲻�Ի��߷��ص�����������
			System.out.println("���������URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// ���������쳣
			System.out.println("���������쳣!");
			e.printStackTrace();
		} finally {
			/* 6 .�ͷ����� */
			getMethod.releaseConnection();
		}
		return response;
	}
	
	
	
	public static String getDoGetURL2(String url, String charset)
            throws Exception {
        // ����httpclinet���󣬽���http����
        HttpClient httpClient = new HttpClient();
        // ���ó�ʱ����ʱ��
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
                5000);
        // ʹ��get��������url
        GetMethod getMethod = new GetMethod(url);
        // ����get����ʱ����
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // Ӧ�������·���get����ʱ�Ĵ������
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        // �洢����������Ӧ����
        String response = "";
        try {
            // get��������״̬�룬�����������404����200�ȵ�
            int statusCode = httpClient.executeMethod(getMethod);
            // ���״̬�벻ΪSC_OK,���������Ϣ
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            // �õ�get����ͷ����Ϣ
            Header[] headers = getMethod.getResponseHeaders();
            for (Header h : headers)
                System.out
                        .println(h.getName() + "------------ " + h.getValue());
            // �õ���Ӧ����
            byte[] responseBody = getMethod.getResponseBody(); // ��ȡΪ�ֽ�����
            // ����Ӧ���ݽ����ַ�������
            response = new String(responseBody, charset);
            System.out.println("----------response:" + response);
        } catch (HttpException e) {
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // ����ͷ�http����
            getMethod.releaseConnection();
        }
        // ������Ӧ����
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
