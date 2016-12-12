package com.artsoft.download.downNetwork.Image_Network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.downNetwork.DownIqiyiNetword;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;

public class QQ_Image_Network {
	
	
	
	private static void runstatic() {
		// TODO Auto-generated method stub
		String url = "";
		// String[] diqu = { "814", "815", "816", "817", "818", "819" };
		// for (String diqutxt : diqu) {
		for (int i = 0; i < 5000; i = i + 20) {
			url = "http://v.qq.com/x/teleplaylist/?sort=4&offset="+i+"&itype=844&iarea=-1&iyear=-1&ipay=-1";
			System.out.println(url);
			boolean bb = downMain(url, i);
			// String urlnext = DownYoukuMovie.youkuMaim(url);
			if (!bb) {
				break;
			}
		}
	}
	
	public static boolean downMain(String urlMain ,int xxx) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return false;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.figures_list li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String strmainurl = "";
			String score = "";
			String name = "";
			System.out.println(strmainurl = link.select("strong.figure_title a").attr("href"));
			// System.out.println(strmainurl = link.select("a").attr("id"));
			System.out.println(name = link.select("strong.figure_title a").attr("title"));
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			System.out.println(score = link.select("span.mod_score").text());
			String imgurl="";
			System.out.println(imgurl= link.select("img").attr("r-lazyload"));
			try {
				DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\downNetwork\\QQ\\");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();
		
		
		try {
			if (xxx<Integer.parseInt(tt)) {
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return true;
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 runstatic();

	}
	
}
