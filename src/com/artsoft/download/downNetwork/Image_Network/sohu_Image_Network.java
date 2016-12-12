package com.artsoft.download.downNetwork.Image_Network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

public class sohu_Image_Network {
	
	
	
	private static void sohuMain(String sohuMainUrl) {
		// TODO Auto-generated method stub
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + sohuMainUrl);
		String strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		}
		try {

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select(".st-list").select(".cfix").select("li");
			for (Element link : links) {
				String name = link.select("strong a").attr("title");
				System.out.println(name);
				String texturl = link.select("a").attr("href");
				System.out.println(texturl);
				String imgurl="";
				System.out.println(imgurl= link.select("img").attr("src"));
				DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\downNetwork\\sohu\\");
				// System.out.println(texthtml);
//				if (texthtml != null && !"".equals(texthtml) && texturl != null && !"".equals(texturl)) {
//					System.out.println(texturl);
////					sohuBranch(texturl);
//				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		
			sohuMain("http://so.tv.sohu.com/list_p1101_p20_p3_p40_p5_p6_p77_p80_p9_2d1_p10_p11_p122_p13.html");
//		 TimingTime(23, 59, 59);
	}

}
