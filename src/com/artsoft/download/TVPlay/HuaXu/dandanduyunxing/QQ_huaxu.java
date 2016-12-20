package com.artsoft.download.TVPlay.HuaXu.dandanduyunxing;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_TVPLAY_TIDBITS;
import com.artsoft.demo.imag.Image1;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.download.variety.variety_sohu;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class QQ_huaxu {
	
	
	
	private static void qqbranch(String name, String urlBranch,int DATA_TYPE) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml.contains("正在跳转")) {
			urlBranch=HtmlAnalyze.getTagText(strHtml, ";url='", "'");
		}
		strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		
		Document doc = Jsoup.parse(strHtml);
		Elements docs=doc.select("div.mod_playlist li.list_item");
		int i=1;
		
		for (Element element : docs) {
			
			TEM_TVPLAY_TIDBITS tidbits= new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlBranch);
			tidbits.setSOURCE(3);
			
			String namexiangxi="";
			String shijian="";
			String bofanglianjie="";
			String imgurl="";
			System.out.println(namexiangxi=HtmlAnalyze.getTagText(element.toString(), "alt=\"", "\""));
			tidbits.setTitleName(namexiangxi);
			
			System.out.println(bofanglianjie=HtmlAnalyze.getTagText(element.toString(), "href=\"", "\""));
			if (!bofanglianjie.equals("")) {
				bofanglianjie="http://v.qq.com"+bofanglianjie;
			}
			
			tidbits.setPlayUrl(bofanglianjie);
			
			imgurl=HtmlAnalyze.getTagText(element.toString(), "src=\"", "\"");
			if (imgurl.equals("")) {
				System.out.println(imgurl=HtmlAnalyze.getTagText(element.toString(), "r-lazyload=\"", "\""));
			}
			if (!imgurl.equals("")) {
				imgurl="http:"+imgurl;
			}
			System.out.println(imgurl);
			
			tidbits.setImgSmallUrl(imgurl);
			
			String IMG_SMALL_NAME =Image1.downloadimg(imgurl);
			 tidbits.setImgSmallName(IMG_SMALL_NAME);
			 
			System.out.println(shijian=element.select("span.figure_info").first().text());
			tidbits.setTimeLongs(shijian);
			
			
			String TIME_LONGS = "";
			System.out.println(TIME_LONGS=HtmlAnalyze.getTagText(element.toString(), "ideo_playnum\">", "</"));
			int numcishu=variety_sohu.Stringnum(TIME_LONGS);
			tidbits.setPlayAmount(numcishu);
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(3);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			
			i=i+1;
			
			
		}
		
		
		
	}
	
	
	/**
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray = OracleNetwork.selectTVplayorder_tv_type(date_date, "3");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			 System.out.println(listTemp.get(0));
			 System.out.println(listTemp.get(1));
			try {
//				IqiyiBranch(listTemp.get(0), listTemp.get(1));
				// youkuBranch(listTemp.get(0),listTemp.get(1));
				// youkuhuaxu(listTemp.get(0), listTemp.get(1));
				qqbranch(listTemp.get(0), listTemp.get(1),1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	public static boolean downMain(String urlMain, int xxx) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return false;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.figures_list li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String strmainurl = "";
			String score = "";
			String name = "";
			System.out.println(strmainurl = link.select("strong.figure_title a").attr("href"));
			// System.out.println(strmainurl = link.select("a").attr("id"));
			System.out.println(name = link.select("strong.figure_title a").attr("title"));
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			System.out.println(score = link.select("span.mod_score").text());
			try {
				// OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score,
				// "0", "", urlMain, "3", "1",strmainurl);
//				OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "0", "1", strmainurl);
//				OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain, "1", "1");
				
				qqbranch(name, strmainurl,1);
				String id = strmainurl.replaceAll("http://v.qq.com/cover/", "").replaceAll("html", "");
				id = HtmlAnalyze.getTagText(id, "/", ".");
				//http://v.qq.com/x/cover/vtxb95np45a6ooz.html
				String newString ="http://v.qq.com/x/cover/"+id+".html";
				qqbranch(name, newString,1);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
//			downBranch(strmainurl, name, urlMain);
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();

		try {
			if (xxx < Integer.parseInt(tt)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;

	}

	public static void runstatic() {
		String url = "";
//		String[] diqu = { "814", "815", "816", "817", "818", "819" };
//		for (String diqutxt : diqu) {
			for (int i = 0; i < 5000; i = i + 20) {
				url = "http://v.qq.com/x/teleplaylist/?sort=4&offset=" + i + "&itype=837&iarea=-1";
				System.out.println(url);
				boolean bb = downMain(url, i);
				// String urlnext = DownYoukuMovie.youkuMaim(url);
				if (!bb) {
					break;
				}
			}

//		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		runstatic();
//		openordor();
//		qqbranch("山海经之赤影传说", "http://v.qq.com/cover/1/1hnc62lteegcjhu.html",1);
	}

}
