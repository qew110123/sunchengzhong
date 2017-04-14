package com.artsoft.download.TVPlay;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownloadDoubanChaXun {
	
	
	public static void mainUrlall(String urlBranch, String name) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.result-list div.result");
		for (Element element : links) {
			if (element.text().contains("电视剧")) {
				String urls = "";
				System.out.println(urls = element.select("a.nbg").first().attr("href"));
				String names = "";
				System.out.println(names = element.select("a").first().attr("title"));
				if (!names.equals(name)) {
					continue;
				}
				
				String feishu = "";
				System.out.println(feishu = element.select("span.rating_nums").text());
				String pinglun = "";
				System.out.println(pinglun = HtmlAnalyze.getTagText(element.toString(), "<span>(", "评价"));
				
				
				
				try {
					if (feishu != null && !feishu.equals("")) {
						OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "0", "1");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if (pinglun != null &&! pinglun.equals("")) {
						pinglun = pinglun.replaceAll("\\D+", "");
						if (pinglun != null && !pinglun.equals("")) {
							OracleOpreater.intoReputation(name, "9", pinglun, "0", "", urls, "0", "2");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleNetwork.selectdouban();
		
		boolean bb=false;
//
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "https://www.douban.com/search?cat=1002&q="
							+ java.net.URLEncoder.encode(listTemp.get(0), "utf-8") + "";
					
//					if (listTemp.get(0).equals("狐影")) {
//						bb=true;
//					}
//					if (bb) {
						mainUrlall(urlBranch, listTemp.get(0));
						
						try {
							Thread.sleep(3 * 1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
//					}

					CommonUtil.setLog(
							TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(0));

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
	}

}
