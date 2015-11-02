package com.artsoft.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.DownloadUtil;

public class Downloadpptv {
	
	public static void mainurl(String mainUrl){

//		String mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		//        ParseJson(BuildJson());
    	String strHtml ="";
    	boolean bb=true;
    	while (bb) {
    		strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml!=null&&!"".equals(strHtml)) {
				bb=false;
			}
		}
//    	System.out.println(strHtml);
    	Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("p.ui-txt");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String name = "";
			String score = "";
			System.out.println(name=link.select("span").text());
			System.out.println(score=link.select("em").text());
//			Download.youkuBranch(strmainurl);
			try {
				OracleOpreater.intoReputation(name, "6", score, "0", "", mainUrl, "0", "1");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
    	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < 25; i++) {
//			String mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p="+i+"&s=1";
//			mainurl(mainUrl);
//		}
//		String mainUrl="http://list.pptv.com/channel_list.html?page=1&type=2&sort=1";
//		mainurl(mainUrl);
		
		for (int i = 1; i < 70; i++) {
			String mainUrl="http://list.pptv.com/channel_list.html?page="+i+"&type=2&sort=1";
			mainurl(mainUrl);
		}
	}

}
