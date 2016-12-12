package com.artsoft.download.variety.Image_variety;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

public class pptv_Image_variety {
	
	
	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		// System.out.println(strHtml);
			try {
				
			
				Document doc = Jsoup.parse(strHtml);
				String DETAIL_URL="";
				Elements linksa = doc.select("a.ui-list-ct");
				for (Element elementa : linksa) {
					System.out.println(DETAIL_URL=elementa.attr("href"));
					Elements links = elementa.select("p.ui-txt");
					// Element content = doc.getElementById("content");
					// Elements links = content.getElementsByTag("a");
					for (Element link : links) {
						String name = "";
						String score = "";
						System.out.println(name = link.select("span").text());
						System.out.println(score = link.select("em").text());
		//				System.out.println(DETAIL_URL= link.select("em").text());
						// Download.youkuBranch(strmainurl);
//						try {
//							OracleOpreater.intoReputationAndDETAIL_URL(name, "6", score, "0", "", mainUrl, "2", "1",DETAIL_URL);
//							
//						} catch (Exception e) {
//							// TODO: handle exception
//						}
						String imgurl="";
						System.out.println(imgurl= elementa.select("img").attr("data-src2"));
						try {
							DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\variety\\pptv\\");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		

	}
	
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":¿ª Ê¼");

		for (int i = 1; i < 80; i++) {
			//http://list.pptv.com/channel_list.html?page=2&type=4&sort=1
			String mainUrl = "http://list.pptv.com/channel_list.html?page=" + i + "&type=4&sort=1";
			mainurl(mainUrl);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":½á Êø");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
	}

}
