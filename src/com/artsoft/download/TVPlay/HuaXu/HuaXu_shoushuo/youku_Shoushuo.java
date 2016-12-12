package com.artsoft.download.TVPlay.HuaXu.HuaXu_shoushuo;

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

public class youku_Shoushuo {
	
	

	/**
	 * 进行优酷网的详细类表页数据的采集
	 * 预告片
	 * @param urlBranch
	 */
	public static void youkuBranch_yugaopiao(String id,String name,String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);

		
		
//		Document doc = Jsoup.parse(htmldate);
		Elements links = doc.select("div.sk_result div.v");
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
		int DATA_TYPE = 2;
		int i=1;
		for (Element link : links) {
//			System.out.println(link);
			TEM_TVPLAY_TIDBITS tidbits= new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setTvplayId(id);;
			tidbits.setDetailUrl(urlBranch);
			tidbits.setSOURCE(1);
//			tidbits.setPlayPlatform("优酷");
			
			System.out.println(IMG_SMALL_URL = link.select("img").attr("src"));
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			
			 IMG_SMALL_NAME =Image1.downloadimg(IMG_SMALL_URL);
			 tidbits.setImgSmallName(IMG_SMALL_NAME);
			
			System.out.println(TIME_LONGS = link.select("li.v_time  span.num").text());
			tidbits.setTimeLongs(TIME_LONGS);
			try {
				String PLAY_URL_url="";
				System.out.println(PLAY_URL_url = link.select("div.v-link a").attr("href"));
				String strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
				if (strHtmlPLAY_URL == null || strHtmlPLAY_URL.equals("")) {
					strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
				}
				if (strHtmlPLAY_URL == null || strHtmlPLAY_URL.equals("")) {
					strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
				}
				String link4=Jsoup.parse(strHtmlPLAY_URL).getElementById("link4").toString();
				System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link4, "src=&quot;", "&quot;"));
				if (PLAY_URL.equals("")) {
					System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link4, "src='", "'"));
				}
				tidbits.setPlayUrl(PLAY_URL);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			System.out.println(TITLE_NAME = link.select("div.v-link a").attr("title"));
			tidbits.setTitleName(TITLE_NAME);
			System.out.println(PLAY_AMOUNT = link.select("span.pub").text());
			tidbits.setPlayAmount(Integer.valueOf(PLAY_AMOUNT.replace(",", "")));
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(1);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS_andid(tidbits);
			i=i+1;
			
		}
	
		
		
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
				String url="http://www.soku.com/search_video/q_"+java.net.URLEncoder.encode(listTemp.get(1), "utf-8")+"+%E7%89%87%E8%8A%B1_orderby_1_limitdate_0?site=14&_lg=10&cateid=97&spm=a2h0k.8191407.0.0";
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				youkuBranch_yugaopiao(listTemp.get(0),listTemp.get(1),url);
//				youkuhuaxu(listTemp.get(0), listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		openordor();
//		http://www.soku.com/search_video/q_%E9%BA%BB%E9%9B%80_orderby_1_limitdate_0?site=14&_lg=10&cateid=97&spm=a2h0k.8191407.0.0
//		youkuBranch_yugaopiao("龙珠传奇","http://www.soku.com/search_video/q_%E9%BE%99%E7%8F%A0%E4%BC%A0%E5%A5%87+%E7%89%87%E8%8A%B1_orderby_1_limitdate_0?site=14&_lg=10&cateid=97&spm=a2h0k.8191407.0.0");
		openordor();

	}

}
