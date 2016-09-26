package com.artsoft.download.TVPlay.HuaXu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_TVPLAY_TIDBITS;
import com.artsoft.bean.TvPlay;
import com.artsoft.demo.imag.Image1;
import com.artsoft.download.variety.variety_sohu;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class HuaXu_xunleikankan {
	
	private static void hunanBranch(String urlBranch, String name ,int DATA_TYPE) {
		// TODO Auto-generated method stub

		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "GBK", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		Elements docs=doc.getElementById("preview_sub_list").select("li ");
		int i=1;
		
		for (Element element : docs) {
			
			TEM_TVPLAY_TIDBITS tidbits= new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlBranch);
			
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
//			if (!imgurl.equals("")) {
//				imgurl="http:"+imgurl;
//			}
			System.out.println(imgurl);
			
			tidbits.setImgSmallUrl(imgurl);
			
			String IMG_SMALL_NAME =Image1.downloadimg(imgurl);
			 tidbits.setImgSmallName(IMG_SMALL_NAME);
			 
			System.out.println(shijian=element.select("span.mask").first().text());
			tidbits.setTimeLongs(shijian);
			
			
//			String TIME_LONGS = "";
//			System.out.println(TIME_LONGS=HtmlAnalyze.getTagText(element.toString(), "ideo_playnum\">", "</"));
//			int numcishu=variety_sohu.Stringnum(TIME_LONGS);
//			tidbits.setPlayAmount(numcishu);
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(8);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			
			i=i+1;
			
			
		}

	}
	
	public static void mainurl(String mainUrl) {

		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// String tyPlayName = "";
		// System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml,
		// "cname: \"", "\""));
		try {

			// System.out.println(strHtml);
			Document doc = Jsoup.parse(strHtml);
			Element linkmain = doc.getElementById("movie_list");
			Elements links = linkmain.select("li");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			System.out.println(links.size());
			for (Element link : links) {
				String url = "";
				String title = "";
				// System.out.println(strVolumes = link.select("a.pic").text());
				System.out.println(url = link.select("a.pic").attr("href"));
				System.out.println(title = link.select("a.pic").attr("title"));
				hunanBranch(url, title,1);

			}
		} catch (Exception e) {
			// TODO: handle exception
			// /程序报错
			System.out.println("出错");
		}
	}
	
	

	
	public static void openordor(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 1; i <= 50; i++) {
			String url = "http://movie.kankan.com/type,order/teleplay,update/page" + i + "/";
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
			mainurl(url);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		openordor();
//		qqbranch("山海经之赤影传说", "http://v.qq.com/cover/1/1hnc62lteegcjhu.html",1);
	}

}
