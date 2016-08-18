package com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_shoushuo;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.maoyanandpiaofang.maoyan.adminmaoyan;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class maoyan_shoushuo {
	
	public static void runshoushuo(String id ,String urlmain){
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		try {
			
		
			Document doc = Jsoup.parse(strHtml);
			
			Elements links= doc.getElementById("search-list").select("article");
			for (Element element : links) {
				System.out.println(element);
				
				
				String uid = "";
				System.out.println(uid = HtmlAnalyze.getTagText(element.attr("data-url")+"#", "movie/", "#"));
				String text=element.select("div.title").first().text();
				String strmainurl="http://piaofang.maoyan.com"+element.attr("data-url");
				
				try {
					
					adminmaoyan.tem_dim_film_boxoffice(uid, text, strmainurl);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	public static void openstatic() {
		List<String> listArray = Oracle.selectmaoyanshijuTmove();
		
		
		boolean bb=false;
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
//			if (listTemp.get(0).equals("382991")) {
//				bb=true;
//			}
//			if (bb) {
				
			
				if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
					String urlBranch = "";
					try {
						//http://piaofang.maoyan.com/search?key=%E9%B2%A8%E9%B1%BC%E6%95%85%E4%BA%8B
						urlBranch = "http://piaofang.maoyan.com/search?key="
								+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "";
	//					BaiDuTeleplayDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
						runshoushuo(listTemp.get(0), urlBranch);
						CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
						
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//			}
		}
	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":¿ªÊ¼");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":½áÊø");
	}
	
	public static void main(String[] args) {
		runstatic();
	}
}

