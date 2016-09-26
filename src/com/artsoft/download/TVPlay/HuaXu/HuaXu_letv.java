package com.artsoft.download.TVPlay.HuaXu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_TVPLAY_TIDBITS;
import com.artsoft.demo.imag.Image1;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HuaXu_letv {
	
	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// System.out.println(strHtml);
		JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
		JSONArray album_lists = new JSONArray();
		album_lists=(JSONArray) objectobjectmain.get("album_list");
		try {
			
		
			for (Object object : album_lists) {
//				TvPlay playtv=new TvPlay();
				JSONObject objectobject = JSONObject.fromObject(object);
				
				String url=(String) objectobject.get("vids");
				System.out.println(url="http://www.letv.com/ptv/vplay/"+url.split(",")[0]+".html");
//				playtv.setTvplay_url(url);
				
				String name = (String) objectobject.get("name");
				System.out.println(name);
				//http://www.le.com/tv/10029212.html
				String aid = (String) objectobject.get("aid");
				String url_aid="";
				System.out.println(url_aid="http://www.le.com/tv/"+aid+".html");
				
				letvdownurl(url_aid ,name,2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void letvdownurl(String url_aid, String name,int DATA_TYPE) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url_aid, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		String yugaopiao = HtmlAnalyze.getTagText(strHtml, "<!-- 花絮 预告 start -->", "<!-- 花絮 预告 end -->", true, 0);
		// System.out.println(yugaopiao);

		Document docyugaopiao = Jsoup.parse(yugaopiao);
		Elements docsyugaopiao = docyugaopiao.select("dl.w120");
		int i=0;
		for (Element link : docsyugaopiao) {
			System.out.println(link);
			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(url_aid);
//			tidbits.setSOURCE(4);
			String IMG_SMALL_URL = "";
			System.out.println(IMG_SMALL_URL = link.select("img").attr("src"));
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			String IMG_SMALL_NAME = Image1.downloadimg(IMG_SMALL_URL);
			tidbits.setImgSmallName(IMG_SMALL_NAME);
			String TIME_LONGS = "";
			System.out.println(TIME_LONGS = HtmlAnalyze.getTagText(link.toString(), "class=\"time\">", "</span>"));
			tidbits.setTimeLongs(TIME_LONGS);
			String PLAY_URL="";
			System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link.toString(), "href=\"", "\""));
			tidbits.setPlayUrl(PLAY_URL);
			String TITLE_NAME="";
			System.out.println(TITLE_NAME = link.select("a").first().attr("title"));
			tidbits.setTitleName(TITLE_NAME);
//			String PLAY_AMOUNT="";
//			System.out.println(PLAY_AMOUNT = link.select("li.v_stat span.num").text());
//			tidbits.setPlayAmount(Integer.valueOf(PLAY_AMOUNT.replace(",", "")));
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(5);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			i=i+1;
		}
		
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 0; i < 30; i++) {
			String mainUrl = "http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=" + i + "&s=1";
			mainurl(mainUrl);
		}
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
	}

}
