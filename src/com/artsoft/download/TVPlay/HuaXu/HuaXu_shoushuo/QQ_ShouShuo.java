package com.artsoft.download.TVPlay.HuaXu.HuaXu_shoushuo;

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
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class QQ_ShouShuo {
	
	
	private static void qqbranch(String id,String name, String urlBranch,int DATA_TYPE) {
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
			tidbits.setTvplayId(id);
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
	
	
	private static void qqbranch_allmain(String id, String name, String urlBranch, int DATA_TYPE) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);

		
		
//		Document doc = Jsoup.parse(htmldate);
		Elements linkall=doc.select("div.wrapper_main");
//		System.out.println(linkall);
		Elements links = linkall.select("div.result_item_h");
		String TVPLAY_NAME = "";
		String TITLE_NAME = "";
		String DETAIL_URL = "";
		String PLAY_URL = "";
		String PUT_DATE = "";
		String PLAY_AMOUNT = "";
		String PLAY_PLATFORM = "";
		String TIME_LONGS = "";
		String ORDER_NO = "";
		String IMG_SMALL_URL = "";
		String IMG_SMALL_NAME = "";
//		int DATA_TYPE = 2;
		int i=1;
		for (Element link : links) {
//			System.out.println(link);
			TEM_TVPLAY_TIDBITS tidbits= new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setTvplayId(id);;
			tidbits.setDetailUrl(urlBranch);
			tidbits.setSOURCE(3);
			
			System.out.println(IMG_SMALL_URL = link.select("img").attr("src"));
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			if (!IMG_SMALL_URL.contains("http")) {
				IMG_SMALL_URL="http:"+IMG_SMALL_URL;
			}
			
			 IMG_SMALL_NAME =Image1.downloadimg_return1(IMG_SMALL_URL);
			 tidbits.setImgSmallName(IMG_SMALL_NAME);
			
			System.out.println(TIME_LONGS = link.select("li.v_time  span.num").text());
			tidbits.setTimeLongs(TIME_LONGS);
			try {
				String PLAY_URL_url="";
				System.out.println(PLAY_URL_url = link.select("a").attr("href"));
//				String strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
//				if (strHtmlPLAY_URL == null || strHtmlPLAY_URL.equals("")) {
//					strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
//				}
//				if (strHtmlPLAY_URL == null || strHtmlPLAY_URL.equals("")) {
//					strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
//				}
//				String link4=Jsoup.parse(strHtmlPLAY_URL).getElementById("link4").toString();
//				System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link4, "src=&quot;", "&quot;"));
//				if (PLAY_URL.equals("")) {
//					System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link4, "src='", "'"));
//				}
				tidbits.setPlayUrl(PLAY_URL_url);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			System.out.println(TITLE_NAME = link.select("img").attr("alt"));
			tidbits.setTitleName(TITLE_NAME);
			System.out.println(PLAY_AMOUNT = HtmlAnalyze.getTagText(link.toString(), "播放量：", "</div>"));
//			PLAY_AMOUNT=;
			tidbits.setPlayAmount(Stringnum(PLAY_AMOUNT));
			
//			String PUT_DATE="";
			System.out.println(PUT_DATE = HtmlAnalyze.getTagText(link.toString(), "时　间：", "</div>"));
			tidbits.setPutDate(PUT_DATE);
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(1);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS_andid(tidbits);
			i=i+1;
			
		}
		
		
	}
	
	
	public static int  Stringnum( String numString){
		int numIn=0;
		if (numString==null||numString.equals("")) {
			numString="-1";
			numIn=0;
		}
		if (numString.contains("亿")) {
			numIn=(int) ((Double.parseDouble(numString.replace("亿", "")))*100000000);
		}else{
			
		
			if (numString.contains("万")) {
				numIn=(int) (Double.parseDouble(numString.replace("万", ""))*10000);
			}else{
				numIn=(int) Double.parseDouble(numString);
			}
		}
		
		
		return numIn;
		
	}
	
	
	
	/**
	 * 2016年5月27日16:09:57 进行整体数据的更细
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
//		TimeTest tt = new TimeTest();
//		String newtime = tt.getNowTime("yyyyMMdd");
//		System.out.println(newtime);
//		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray = OracleNetwork.selectyoukuTVplay_pianhua();
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
			try {
				//http://www.soku.com/search_video/q_%E9%BE%99%E7%8F%A0%E4%BC%A0%E5%A5%87+%E7%89%87%E8%8A%B1_orderby_1_limitdate_0?site=14&_lg=10&cateid=97&spm=a2h0k.8191407.0.0
				String name_utf_8=java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
				String url="http://v.qq.com/x/search/?ses=qid%3D7xQAoVybVwYmhu44CkMivhVazOqlxi4RRoxSPorJdnVpStZ3g7lgTw%26last_query%3D"+name_utf_8+"+%20%E7%89%87%E8%8A%B1%26tabid_list%3D0%7C2%7C1%7C4%7C6%7C12%7C5%7C17%7C8%7C7%26tabname_list%3D%E5%85%A8%E9%83%A8%7C%E7%94%B5%E8%A7%86%E5%89%A7%7C%E7%94%B5%E5%BD%B1%7C%E5%8A%A8%E6%BC%AB%7C%E7%BA%AA%E5%BD%95%E7%89%87%7C%E5%A8%B1%E4%B9%90%7C%E9%9F%B3%E4%B9%90%7C%E6%B8%B8%E6%88%8F%7C%E5%8E%9F%E5%88%9B%7C%E5%85%B6%E4%BB%96&q="+name_utf_8+"%20%E7%89%87%E8%8A%B1&stag=4&filter=sort%3D0%26pubfilter%3D0%26duration%3D0%26tabid%3D2#!filtering=1";
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				qqbranch_allmain(listTemp.get(0),listTemp.get(1),url,1);
//				youkuhuaxu(listTemp.get(0), listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		openordor();
		openordor();
//		qqbranch_allmain("0","龙珠传奇", "http://v.qq.com/x/search/?ses=qid%3D7xQAoVybVwYmhu44CkMivhVazOqlxi4RRoxSPorJdnVpStZ3g7lgTw%26last_query%3D%E9%BE%99%E7%8F%A0%E4%BC%A0%E5%A5%87%20%E7%89%87%E8%8A%B1%26tabid_list%3D0%7C2%7C1%7C4%7C6%7C12%7C5%7C17%7C8%7C7%26tabname_list%3D%E5%85%A8%E9%83%A8%7C%E7%94%B5%E8%A7%86%E5%89%A7%7C%E7%94%B5%E5%BD%B1%7C%E5%8A%A8%E6%BC%AB%7C%E7%BA%AA%E5%BD%95%E7%89%87%7C%E5%A8%B1%E4%B9%90%7C%E9%9F%B3%E4%B9%90%7C%E6%B8%B8%E6%88%8F%7C%E5%8E%9F%E5%88%9B%7C%E5%85%B6%E4%BB%96&q=%E9%BE%99%E7%8F%A0%E4%BC%A0%E5%A5%87%20%E7%89%87%E8%8A%B1&stag=4&filter=sort%3D0%26pubfilter%3D0%26duration%3D0%26tabid%3D2#!filtering=1",1);
	}
}
