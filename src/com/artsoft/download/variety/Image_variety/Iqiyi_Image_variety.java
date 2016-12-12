package com.artsoft.download.variety.Image_variety;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.downNetwork.DownIqiyiNetword;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;

public class Iqiyi_Image_variety {
	
	
	
	private static void runstatic() {
		String url = "";
		String[] diqu = { "151", "152", "153", "154", "1113" };
		String[] leixing = { "155", "156", "157", "158", "159", "160", "163", "292", "193", "1002",
				"1003", "2117", "2118", "2119", "2120",
				"2121", "2122", "2224", "161"};
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				
				try {
					for (int i = 1; i < 30; i++) {
						url = "http://list.iqiyi.com/www/6/" + diqutxt
								+ "-" + leixingtxt + "------------11-" + i + "-1-iqiyi--.html";
						System.out.println(url);
//						String urlnext = DownYoukuMovie.youkuMaim(url);
						youkuMaim(url);
//						if (urlnext.equals("") || urlnext == "" || urlnext == null) {
//							break;
//						}
					}

				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}

		}
	}
	
	private static void youkuMaim(String urlMain) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		// String strHtml = DownloadUtil.readHtml(strUrl, 1000 * 30,"UTF-8",
		// cookstr, null);
		// StringBuffer strHtml = DownloadUtil.getContent(urlstr);
		// System.out.println(strHtml);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			return;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.site-piclist_pic");
		for (Element link : links) {
			String name="";
			String strmainurl = "";
			String imgurl="";
			System.out.println(strmainurl = link.select("a.site-piclist_pic_link").attr("href"));
			System.out.println(name=link.select("a.site-piclist_pic_link").attr("title"));
			
			System.out.println(imgurl= link.select("img").attr("src"));
			
			try {
				DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\variety\\Iqiyi\\");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println();
//			iQiYiBranch(name,strmainurl);
		}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 runstatic();

	}
	
}
