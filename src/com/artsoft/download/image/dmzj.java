package com.artsoft.download.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * http://www.dmzj.com/category/1-0-0-6-0-0-1.html 动漫之家
 * 
 * @author Administrator
 *
 */
public class dmzj {
	static String html = "";

	@Test
	public static String requestByGetMethod(String url) {

		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = getHttpClient();
		try {
			// 用get方法发送http请求
			HttpGet get = new HttpGet(url);
			System.out.println("执行get请求:...." + get.getURI());
			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpClient.execute(get);
			try {
				// response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					System.out.println("响应状态码:" + httpResponse.getStatusLine());
					System.out.println("-------------------------------------------------");
//					System.out.println(entity);
//					System.out.println("响应内容:" + EntityUtils.toString(entity, "utf-8"));
//					System.out.println(EntityUtils.toString(entity));
					// html=EntityUtils.toString(entity)+"";
					html = EntityUtils.toString(entity, "utf-8");
//					html="1111";
					// html = ConvertStreamToString(instreams);
					System.out.println("-------------------------------------------------");
					// Thread.sleep(1000);
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return html;

	}

	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
	

	public static String ConvertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();

		String line = null;

		try {

			while ((line = reader.readLine()) != null) {

				sb.append(line + "\n");

			}

		} catch (IOException e) {

			System.out.println("Error=" + e.toString());

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				System.out.println("Error=" + e.toString());

			}

		}

		return sb.toString();

	}

	public static void mainurllist(String mainUrl, String AREA, String SOURCE) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		// boolean bb = true;
		// while (bb) {
		// strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "utf-8", null,
		// null);
		// if (strHtml != null && !"".equals(strHtml)) {
		// bb = false;
		// }
		// }
		// dmzj bb=new dmzj();
		strHtml = requestByGetMethod(mainUrl);
		// requestByPostMethod
//		System.out.println(strHtml);
		String ANIMATION_NAME = ""; // 动漫名称
		String ANIMATION_URL = ""; // 网站url
		String AUTHOR = ""; // 作者
		// String AREA = ""; // 地区
		String STATE = ""; // 状态
		String POPULARITY = "0"; // 人气
		String SUBJECT_NAME = ""; // 题材名称
		String ANIMATION_CATEGORY = ""; // 分类
		String IN_DATE = ""; // 收录日期
		// String SOURCE = ""; // 漫画来源 ： 网站名称
		String CLICK_NUM = "";// 点击量
		String SCORE = "";// 评分
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.list_con_li li");
		// Elements links = linksul.select("ul.list_con_li").first();
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			System.out.println(link);
			System.out.println(ANIMATION_NAME = link.select("h3").first().text());
			System.out.println(ANIMATION_URL = link.select("a").first().attr("href"));
			System.out.println(AUTHOR = HtmlAnalyze.getTagText(link.toString(), "作者：", "</p>"));
			System.out.println(SUBJECT_NAME= HtmlAnalyze.getTagText(link.toString(), "类型：", "</p>"));
			System.out.println(STATE = HtmlAnalyze.getTagText(link.toString(), "状态：", "</p>"));
			String comic_id = "";

			String comic_idstrHtml = DownloadUtil.getHtmlText(ANIMATION_URL, 1000 * 30, "utf-8", null, null);
			comic_id = HtmlAnalyze.getTagText(comic_idstrHtml, "var comic_id = '", "'");
			// http://www.dmzj.com/static/hits/17049.json
			if (comic_id != null) {
				String POPULARITYjson = DownloadUtil.getHtmlText(
						"http://www.dmzj.com/static/hits/" + comic_id + ".json", 1000 * 30, "utf-8", null, null);
				if (POPULARITYjson!=null) {
					
					POPULARITY = HtmlAnalyze.getTagText(POPULARITYjson, "hot_hits\":\"", "\"").replace("\\u2103", "");
					CLICK_NUM = HtmlAnalyze.getTagText(POPULARITYjson, "\"hits\":", ",");
				}
				String SCOREjson = DownloadUtil.getHtmlText(
						"http://i.dmzj.com/ajax/getScoreInfo?callback=success_jsonpCallback_201509221731&comic_id="
								+ comic_id + "",
						1000 * 30, "utf-8", null, null);
				if (SCOREjson!=null) {
					
					SCORE = HtmlAnalyze.getTagText(SCOREjson, "show_points\\\":\\\"" , "\\\"");
				}
			}
			OracleSarFtGov.intotem_animation(ANIMATION_NAME, ANIMATION_URL, AUTHOR, AREA, STATE, POPULARITY,
					SUBJECT_NAME, ANIMATION_CATEGORY, IN_DATE, SOURCE, CLICK_NUM, SCORE);
					// OracleSarFtGov.intotem_animation(ANIMATION_NAME,
					// ANIMATION_URL, AUTHOR, AREA, STATE, POPULARITY,
					// SUBJECT_NAME, ANIMATION_CATEGORY, IN_DATE, SOURCE,"","");

			// strHtml=strHtml.replaceAll("search.renderResult(", "");
			// strHtml=strHtml.substring(0, strHtml.length()-1);
			// System.out.println(strHtml);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for (int i = 1; i <= 1505; i++) {

		// mainurllist(
		// "http://s.acg.178.com/mh/index.php?c=category&m=doSearch&status=0&reader_group=0&zone=2304&initial=all&type=9&_order=h&p=1&callback=search.renderResult&_=1449670088287",
		// "日本","动漫之家");
		// }
		// for (int i = 1; i <= 9; i++) {
		//
		//// mainurllist("http://yy.8fkd.com/YanYuanKu/Search.aspx?key=&gj=&xx=&sex=2&xz=&zm=&page="
		//// + i, "女");
		// mainurllist(
		// "http://s.acg.178.com/mh/index.php?c=category&m=doSearch&status=0&reader_group=0&zone=2304&initial=all&type=9&_order=h&p="+i+"&callback=search.renderResult&_=1449670088287",
		// "日本","动漫之家");
		// }
		 for (int i = 1; i <= 362; i++) {
			 mainurllist(
			 "http://www.dmzj.com/category/1-0-0-6-0-0-"+i+".html","","dmzj");
		
		 }
//		 mainurllist(
//		 "http://www.dmzj.com/category/1-0-0-6-0-0-2.html","","dmzj");
		// dmzj ss= new dmzj();
//		System.out.println("**" + requestByGetMethod("http://www.dmzj.com/category/1-0-0-6-0-0-1.html"));

		// System.out.println(DownloadUtil.readHtml("http://www.dmzj.com/category/1-0-0-6-0-0-1.html",
		// 1000 * 30, "UTF-8", null, null));\
		// URL url = null;
		// try {
		// url = new URL("http://www.dmzj.com/category/1-0-0-6-0-0-1.html");
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(DownloadUtil.getContent(url));
	}

}
