package com.artsoft.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;
import com.artsoft.util.DownloadUtil;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Text {
	private static void writerTxt(String text) {
		BufferedWriter fw = null;
		try {
			File file = new File("D://text.txt");
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // ָ�������ʽ�������ȡʱ�����ַ��쳣
			fw.append(text);
			// fw.newLine();
			// fw.append("����д�������");
			fw.flush(); // ȫ��д�뻺���е�����
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	int a;
//	Integer b;
//	public Text(){
//		System.out.println(a);
//		System.out.println(b);
//	}
	
//	public Text(){
//		String name="123";
//		change(name);
//		System.out.println(name);
//	}
//
//	private void change(String name) {
//	// TODO Auto-generated method stub
//		name="abc";
//	
//}

	public Text(){
		List<String> listOfString = new ArrayList<>();
		listOfString.add("123");
		listOfString.add("456");
		change(listOfString);
		System.out.println(listOfString);
				
	}
	
	private void change(List<String> listOfString) {
	// TODO Auto-generated method stub
		listOfString.set(1, "abc");
	
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//// writerTxt("111");
		// String strHtml = "";
		// String url="http://www.open-open.com/jsoup/set-html.htm";
		// strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
		//// null);
		//
		// System.out.println(strHtml);
		//
		// System.out.println("****************************************");
		//
		// Document doc = Jsoup.parse(strHtml);
		//
		// Element div = doc.select("div").first(); // <div></div>
		// div.html("<p>lorem ipsum</p>"); // <div><p>lorem ipsum</p></div>
		// div.prepend("<p>First</p>");//��divǰ���html����
		// div.append("<p>Last</p>");//��div֮�����html����
		// // �����Ľ��: <div><p>First</p><p>lorem ipsum</p><p>Last</p></div>
		//
		//// Element span = doc.select("span").first(); // <span>One</span>
		//// span.wrap("<li><a href='http://example.com/'></a></li>");
		// // �����Ľ��: <li><a href="http://example.com"><span>One</span></a></li>
		//
		//
		// System.out.println(doc);

//		try {
//			// ���ظ����ַ��������� Class ����
//			// �������� Class ��������ʾ�����һ����ʵ��
//			Object object = Class.forName("com.artsoft.demo.Quizzee").newInstance();
//			// ���ط�����Ϊ��testMethod����һ�� Method ���󣬺�������Ǹ÷�������
//			Method method = object.getClass().getMethod("testMethod", new Class[] { String.class, Integer.class });
//			// ִ�и÷���
//			method.invoke(object, new Object[] { new String("����1"), 520 });
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			// ���ظ����ַ��������� Class ����
//			// �������� Class ��������ʾ�����һ����ʵ��
//			Object object = Class.forName("com.artsoft.downloadThreadpool.people.HaoSouWordAdmin").newInstance();
//			// ���ط�����Ϊ��testMethod����һ�� Method ���󣬺�������Ǹ÷�������
//			Method method = object.getClass().getMethod("main" , new Class[] {    });
//			// ִ�и÷���
//			method.invoke(object , null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
//		 HaoSouWordAdmin.main(null);
		 
//			String strHtml = "";
//		 String url="http://zhishu.sogou.com/index/media/wechat?kwdNamesStr=%E6%96%87%E7%AB%A0&timePeriodType=MONTH&dataType=MEDIA_WECHAT&queryType=INPUT";
//		 strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
//		 null);
//		 
//		 System.out.println(strHtml);
		 
		
//		new Text();
		
		
		try {
			
			HtmlPage page = null;
//			WebClient client = BoxofficeUtil.getClient(60 * 1000 * 20);
			WebClient client = new WebClient();
			WebRequest request;
			//��	ֵ
//			����	POST /srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc HTTP/1.1
			request = new WebRequest(new URL("http://www.gjdypw.cn/srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc"), HttpMethod.POST);
//			BoxofficeUtil.setBasicRequestHeader(client);
			client.addRequestHeader("Referer", "http://www.gjdypw.cn/index.html");
//			client.addRequestHeader("Accept", "application/json, text/plain, */*");
			client.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
//			client.addRequestHeader("Accept-Language", "zh-CN");
			client.addRequestHeader("Accept-Encoding", "gzip, deflate");
//			client.addRequestHeader("Content-Type", "application/json;charset=utf-8");
			client.addRequestHeader("Cookie", "SESSION=919325af-5a64-4e97-a506-55c090e344f3; curentUserName=aimankeji; remember=false; currentNickname=aimankeji");
			
			client.addRequestHeader("Content-Type", "text/html;charset=UTF-8");  
			client.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
			client.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"); 
			
			//��	ֵ
//			Content-Length	0
//			client.addRequestHeader("Content-Length","0");
			
			//��	ֵ
//			Transfer-Encoding	chunked
			
//			client.addRequestHeader("Transfer-Encoding","chunked");
			//��	ֵ
//			Connection	keep-alive
			//��	ֵ		Connection	Keep-Alive
			client.addRequestHeader("Connection","Keep-Alive");
			client.addRequestHeader("Cache-Control","no-cache");
			client.addRequestHeader("Host","www.gjdypw.cn");
			
//			client.removeRequestHeader("Content-Length");
//			//��	ֵ
////			X-Application-Context	gateway:pord:58000
//			client.addRequestHeader("X-Application-Context","gateway:pord:58000");
//			//��	ֵ
////			X-Content-Type-Options	nosniff
//			client.addRequestHeader("X-Content-Type-Options","nosniff");
//			//��	ֵ
////			X-XSS-Protection	1; mode=block
//			client.addRequestHeader("X-XSS-Protection","1; mode=block");
//			//��	ֵ
//			//Cache-Control	no-cache, no-store, max-age=0, must-revalidate
//			client.addRequestHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
//			//��	ֵ
//			//Pragma	no-cache
//			client.addRequestHeader("Pragma","no-cache");
//			//��	ֵ
////			Expires	0
//			client.addRequestHeader("Expires","0");
//			System.out.println(client.getPage(request).toString());
			
//			page = client.getPage(request);
			
//			 HtmlPage page1=client.getPage(request);
//			System.out.println(page1.asText());
			System.out.println(page);
			System.out.println(client.getPage(new WebRequest(new URL("http://www.gjdypw.cn/srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc"), HttpMethod.POST)));
			System.out.println(client.getPage("http://www.gjdypw.cn/srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc"));
			
//			page = client.getPage("http://www.gjdypw.cn/srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc");  
//			
			page=client.getPage(new WebRequest(new URL("http://www.gjdypw.cn/srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc"), HttpMethod.POST));
			System.out.println(page);
			
//			page = client.getPage("http://www.gjdypw.cn/srfs/w/cinemaStatisticsInquiry/serviceForDec/s?page=0&size=10&s_fromDay=1420041600000&s_toDay=1514735999999&s_containZero=false&s_flag=false&sort=total_boxoffice,desc");  
//			System.out.println(page.asText());
//			client.waitForBackgroundJavaScript(10000);  
			    // ��ҳ����  
//			System.out.println(page.asXml());  
			
			client.closeAllWindows();
			
			} catch (FailingHttpStatusCodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
