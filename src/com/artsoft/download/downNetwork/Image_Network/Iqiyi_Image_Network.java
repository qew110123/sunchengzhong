package com.artsoft.download.downNetwork.Image_Network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.downNetwork.DownIqiyiNetword;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;

public class Iqiyi_Image_Network {
	
	
	
	private static void runstatic() {
		// TODO Auto-generated method stub
		for (int j = 0; j < 6; j++) {
			try {
				
//				DownIqiyiNetwordDetails.mainurl("http://list.iqiyi.com/www/2/-24065------------4-"+j+"-1-iqiyi--.html");
				
				youkuMaim("http://list.iqiyi.com/www/2/-24065------------4-"+j+"-1-iqiyi--.html");
			} catch (Exception e) {
				// TODO: handle exception
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
				DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\downNetwork\\Iqiyi\\");
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
