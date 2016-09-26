package com.artsoft.download.TVPlay.HuaXu;

import java.io.IOException;

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

public class HuaXu_mgtv {
	
	private static void mainmore(String url) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}

		// System.out.println(strHtml);
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("p.img-box a");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		System.out.println(links.size());
		for (Element link : links) {
			String name="";
			 System.out.println(name=link.select("a").attr("title"));
			String urlstr = "";
			System.out.println(urlstr = link.select("a").attr("href"));
//			mainurl(urlstr);
			mainfalseurl(name,urlstr,1);
		}
	}
	
	private static void mainfalseurl(String name, String urlstr,int DATA_TYPE) {
		// TODO Auto-generated method stub
//		System.out.println(name);
//		System.out.println(urlstr);
		String urlser_nothtml=urlstr.replace(".html", "");
		//http://rc.mpp.hunantv.com/pc/cms/3582862?channel=2&callback=jQuery182031653768270502924_1474613293893&_=1474613294489
//		String 
		String cms_id="";
		String[] namelist = urlser_nothtml.split("/");
		cms_id = namelist[namelist.length - 1] ;
		String urljson="http://rc.mpp.hunantv.com/pc/cms/"+cms_id+"?channel=2&callback=jQuery182031653768270502924_1474613293893&_=1474613294489";
		
		System.out.println(urljson);
		
		String strHtml = "";
		boolean bb = true;
		while (bb) {
//			strHtml = DownloadUtil.getHtmlText(urljson, 1000 * 30, "UTF-8", null, null);
//			strHtml = Jsoup.
			Document docJsoup = null;
			try {
				docJsoup = Jsoup.connect(urljson).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			strHtml = docJsoup.toString();
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		String urljsonhtml= HtmlAnalyze.getTagText(strHtml.toString(), "templateData\":", ",\"isCached\"");
		
		String IMG_SMALL_URL = "";
		String IMG_SMALL_NAME = "";
		String PLAY_URL="";
		String TITLE_NAME="";
		String TIME_LONGS="";
		JSONArray dataarray = JSONArray.fromObject(urljsonhtml);
		int iii=0;
		for (Object object : dataarray) {
			JSONObject objectobjects = JSONObject.fromObject(object);
			System.out.println(IMG_SMALL_URL = (String) objectobjects.get("img"));
			System.out.println(PLAY_URL=(String) objectobjects.get("url"));
			System.out.println(TITLE_NAME=(String) objectobjects.get("title"));
			System.out.println(TIME_LONGS=(String) objectobjects.get("releasedate"));

			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlstr);
//			tidbits.setSOURCE(2);
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			IMG_SMALL_NAME = Image1.downloadimg_no_jpg(IMG_SMALL_URL);
			tidbits.setImgSmallName(IMG_SMALL_NAME);
			tidbits.setPlayUrl(PLAY_URL);
			tidbits.setTitleName(TITLE_NAME);
			tidbits.setOrderNo(iii);
			
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setTimeLongs(TIME_LONGS);
			
			
			tidbits.setSOURCE(7);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			iii+=1;
		}
		
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":¿ª Ê¼");

		for (int i = 1; i <= 22; i++) {
			String url = "http://list.hunantv.com/2/----------" + i + "---.html";
			System.out.println(url);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
			mainmore(url);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":½á Êø");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
	}

}
