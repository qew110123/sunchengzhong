package com.artsoft.download.downNetwork.Image_Network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.downNetwork.DownYoukuNetwork;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class YouKu_Image_Network {
	
	private static String youkuMaim(String urlMain) {
		try {
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				// return;
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.box-series div.p-thumb");
			for (Element link : links) {
				String name="";
				String strmainurl = "";
				String imgurl="";
				System.out.println(strmainurl = link.select("div.p-thumb a").attr("href"));
				System.out.println(name= link.select("div.p-thumb a").attr("title"));
				System.out.println(imgurl= link.select("img").attr("src"));
				DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\downNetwork\\youku\\");
				
				
			}

			// 进行下一页数据的判断

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"下一页\"><a href=\"",
					"\"  charset=");
			if (strnexturl != null || "".equals(strnexturl)) {
				strnexturl = "http://www.youku.com" + strnexturl;
				return strnexturl;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		
		youkuMaim("http://list.youku.com/category/show/c_97_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81_s_1_d_1_p_2.html");
		
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
	}

}
