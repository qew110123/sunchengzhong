package com.artsoft.download.BaiDu;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;

public class BaiDuPeoplemingxingguanxitupianDownload {
	public static void peopleurl(String urlMain){
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
		Document doc = Jsoup.parse(strHtml);
//		Elements links = doc.select("div.yk-col3");
		 Element content = doc.getElementById("slider_relations");
	 if (content==null	) {
			return;
		}
		 Elements links = content.getElementsByTag("li");
		 String PERSON_NAME="";
		 String PERSON_URL="";
		 String IMG_URL="";
		for (Element link : links) {
//			System.out.println(link);
			System.out.println(PERSON_URL=link.select("a").first().attr("href"));
			System.out.println(IMG_URL=link.select("img").first().attr("src"));
			System.out.println(PERSON_NAME=link.select("div.name").first().attr("title"));
			
			OracleBaidu.intoTEM_PERSON_IMG(PERSON_NAME, PERSON_URL, IMG_URL);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		peopleurl("http://baike.baidu.com/view/302258.htm");
		
		
		List<String> listArray = OracleBaidu.selectBaiduiInformation("0", "15000");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null) {
				// mainmore(listTemp.get(0), listTemp.get(1));
				System.out.println(listTemp.get(0));
				peopleurl(listTemp.get(0));
			}
		}
	}

}
