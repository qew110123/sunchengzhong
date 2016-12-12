package com.artsoft.download.TVPlay.HuaXu.dandanduyunxing;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_TVPLAY_TIDBITS;
import com.artsoft.demo.imag.Image1;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Iqiyi_huaxu {

	private static void IqiyiBranch(String name, String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);

		String id = HtmlAnalyze.getTagText(strHtml, "albumId:", ",");

		/**
		 * 片花
		 * http://cache.video.qiyi.com/jp/othlist/204147201/4/desc/?idType=album
		 * &callback=window.Q.__callbacks__.cbtp9wpe
		 */
		String urlBranchjson = "http://cache.video.qiyi.com/jp/othlist/" + id
				+ "/4/desc/?idType=album&callback=window.Q.__callbacks__.cbtp9wpe";
		int page1 = 1;
		Down(urlBranchjson, name, urlBranch, 1, page1);

		/**
		 * 预告片
		 * http://cache.video.qiyi.com/jp/othlist/204147201/1/desc/?idType=album
		 * &callback=window.Q.__callbacks__.cbe91tgh
		 */
		urlBranchjson = "http://cache.video.qiyi.com/jp/othlist/" + id
				+ "/1/desc/?idType=album&callback=window.Q.__callbacks__.cbe91tgh";

		int page2 = 1;
		Down(urlBranchjson, name, urlBranch, 2, page2);

	}

	private static void Down(String urlBranchjson, String name, String urlBranch, int DATA_TYPE, int page1) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlBranchjson, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranchjson, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		String strHtmlarry = HtmlAnalyze.getTagText(strHtml, "\"list\":", "},\"code\"");
		String IMG_SMALL_URL = "";
		String IMG_SMALL_NAME = "";
		String PLAY_URL="";
		String TITLE_NAME="";
		JSONArray dataarray = JSONArray.fromObject(strHtmlarry);
		int iii=0;
		for (Object object : dataarray) {
			JSONObject objectobjects = JSONObject.fromObject(object);
			System.out.println(IMG_SMALL_URL = (String) objectobjects.get("vpic"));
			System.out.println(PLAY_URL=(String) objectobjects.get("vurl"));
			System.out.println(TITLE_NAME=(String) objectobjects.get("vn"));

			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlBranch);
			tidbits.setSOURCE(2);
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			IMG_SMALL_NAME = Image1.downloadimg_no_jpg(IMG_SMALL_URL);
			tidbits.setImgSmallName(IMG_SMALL_NAME);
			tidbits.setPlayUrl(PLAY_URL);
			tidbits.setTitleName(TITLE_NAME);
			tidbits.setOrderNo(iii);
			
			tidbits.setDataType(DATA_TYPE);	
			
			
			tidbits.setSOURCE(2);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			iii+=1;
		}

	}

	/**
	 * 2016年5月27日16:09:57 进行整体数据的更细
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray = OracleNetwork.selectTVplayorder(date_date, "2");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			// System.out.println(listTemp.get(0));
			try {
				IqiyiBranch(listTemp.get(0), listTemp.get(1));
				// youkuBranch(listTemp.get(0),listTemp.get(1));
				// youkuhuaxu(listTemp.get(0), listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	private static void runstatic() {
		// TODO Auto-generated method stub
//		for (int i = 0; i < 500; i=i+20) {
//			//http://v.qq.com/x/teleplaylist/?sort=4&offset=400&itype=837&iarea=-1
//			String url = "http://v.qq.com/x/teleplaylist/?sort=4&offset=" + i + "&itype=837&iarea=-1";
//			System.out.println(url);
//			youkuMaim(url);
//			
//			
//		}
		youkuMaim("http://list.iqiyi.com/www/10/1006--29927-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29928-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29929-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29930-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29931-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29932-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29933-----------11-1-2--1-.html");
		youkuMaim("http://list.iqiyi.com/www/10/1006--29934-----------11-1-2--1-.html");
//		youkuMaim("http://list.iqiyi.com/www/10/1006--29927-----------11-1-2--1-.html");
//		youkuMaim("http://list.iqiyi.com/www/10/1006--29927-----------11-1-2--1-.html");
		
		
		
	}

	private static void youkuMaim(String url) {
		// TODO Auto-generated method stub
		
		mainurl(url);
		url = url.replaceAll("1-1-iqiyi--.html", "");
		for (int i = 1; i < 30; i++) {
			mainurl(url + i + "-1-iqiyi--.html");
		}
		
	}

	private static void mainurl(String urlMain) {
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
			String strmainurl = "";
			System.out.println(strmainurl = link.select("a.site-piclist_pic_link").attr("href"));
			// System.out.println(link.select("a.site-piclist_pic_link").attr("title"));
			// iQiYiBranch(strmainurl);
			String name = "";
			System.out.println(name = link.select("a").attr("title"));
			String score = "";
			System.out.println(score = link.select("span.score").text());
			try {
				IqiyiBranch(name, strmainurl);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
//			String html=DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
//			if (html.contains("大头图信息区")) {
//			}
//			if (html.contains("小头图信息区")) {
//				System.out.println("小头图信息区");
////				iQiYiSmallBranch(strmainurl, name, score);
//				IqiyiBranch(name, strmainurl);
//				
//			}
//			else{
//				System.out.println("大头图信息区");
////				iQiYibigBranch(strmainurl, name, score);
//				IqiyiBranch(name, strmainurl);
//			}
//			iQiYiBranch(strmainurl, name, score);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 openordor();
		runstatic();
		 
//		Down("http://cache.video.qiyi.com/jp/othlist/204147201/4/desc/?idType=album&callback=window.Q.__callbacks__.cbtp9wpe",
//				"", "", 1, 1);
		// youkuBranch("匹夫英雄",
		// "http://www.youku.com/show_page/id_z1b8c5c54e79a11e2a705.html");
		// youkuhuaxu("匹夫英雄",
		// "http://www.youku.com/show_page/id_z1b8c5c54e79a11e2a705.html");
	}

	
}
