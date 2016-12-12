package com.artsoft.download.TVPlay.HuaXu;

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
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HuaXuIqiyi {

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

		String id = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		/**
		 * Æ¬»¨
		 * http://cache.video.qiyi.com/jp/othlist/204147201/4/desc/?idType=album
		 * &callback=window.Q.__callbacks__.cbtp9wpe
		 */
		String urlBranchjson = "http://cache.video.qiyi.com/jp/othlist/" + id
				+ "/4/desc/?idType=album&callback=window.Q.__callbacks__.cbtp9wpe";
		int page1 = 1;
		Down(urlBranchjson, name, urlBranch, 1, page1);

		/**
		 * Ô¤¸æÆ¬
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
			
			String  amount=iQiYiBranch(PLAY_URL);
			tidbits.setPlayAmount(Integer.valueOf(amount));
			
			tidbits.setDataType(DATA_TYPE);	
			
			
			tidbits.setSOURCE(2);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			iii+=1;
		}

	}
	
	public static String  iQiYiBranch(String urlBranch) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}

//		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" />");
//		if (name.equals("")||name.equals("null")||name==null) {
//			//data-shareplattrigger-videoname="
//			name=HtmlAnalyze.getTagText(strHtml, "data-shareplattrigger-videoname=\"", "\"");
//		}
//		System.out.println(name);
		String tvId = HtmlAnalyze.getTagText(strHtml, "tvId:", ",");
		//http://mixer.video.iqiyi.com/jp/mixin/videos/408898800
		String albumurl = "http://mixer.video.iqiyi.com/jp/mixin/videos/" + tvId ;
		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "playCount\":", ","));
		return numhtml;
		
//		try {
//
//			OracleOpreater.intoReputationAndDETAIL_URL(name, "2", numhtml, "0", "", urlBranch, "0", "0",urlBranch);
//
//			String videourl = "http://up.video.iqiyi.com/ugc-updown/quud.do?dataid=" + videoId
//					+ "&type=1&userid=&flashuid=e42dc42f8825edf5580806cde99606ce&appID=21&callback=window.Q.__callbacks__.cb1953yg";
//
//			String feishuhtml = DownloadUtil.getHtmlText(videourl, 1000 * 30, "UTF-8", null, null);
//			String feishu = "";
//			System.out.println(feishu = HtmlAnalyze.getTagText(feishuhtml, "score\":", ",\""));
//
//			OracleOpreater.intoReputationAndDETAIL_URL(name, "2", feishu, "0", "", urlBranch, "0", "1",urlBranch);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
	
	
	

	/**
	 * 2016Äê5ÔÂ27ÈÕ16:09:57 ½øÐÐÕûÌåÊý¾ÝµÄ¸üÏ¸
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
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
					
					IqiyiBranch(listTemp.get(0), listTemp.get(1));
				// youkuBranch(listTemp.get(0),listTemp.get(1));
				// youkuhuaxu(listTemp.get(0), listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	private static void Iqiyishousuo(String name, String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		strHtml= HtmlAnalyze.getTagText(strHtml, "Ô¤¸æÆ¬ -->", "<!--",true,0);
		
		Document doc = Jsoup.parse(strHtml);

		String IMG_SMALL_URL = "";
		String IMG_SMALL_NAME = "";
		String PLAY_URL="";
		String TITLE_NAME="";
//		JSONArray dataarray = JSONArray.fromObject(strHtmlarry);
		Elements links = doc.select("li");
		int iii=0;
		for (Element element : links) {
			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlBranch);
			tidbits.setSOURCE(2);
//			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			
			
			
			IMG_SMALL_URL = element.select("img").attr("src");
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			IMG_SMALL_NAME = Image1.downloadimg_no_jpg(IMG_SMALL_URL);
			tidbits.setImgSmallName(IMG_SMALL_NAME);
			System.out.println(PLAY_URL=element.select("a").first().attr("href"));
			
			tidbits.setPlayUrl(PLAY_URL);
			System.out.println(TITLE_NAME=element.select("a").first().attr("title"));
			tidbits.setTitleName(TITLE_NAME);
			tidbits.setOrderNo(iii);
			String  amount=iQiYiBranch(PLAY_URL);
			tidbits.setPlayAmount(Integer.valueOf(amount));
			tidbits.setDataType(2);	
			tidbits.setSOURCE(2);
			String TIME_LONGS=element.select("span.mod-listTitle_right").first().text();
			tidbits.setTimeLongs(TIME_LONGS);
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			iii+=1;
		}
//		for (Object object : links) {
//			JSONObject objectobjects = JSONObject.fromObject(object);
//			System.out.println(IMG_SMALL_URL = (String) objectobjects.get("vpic"));
//			System.out.println(PLAY_URL=(String) objectobjects.get("vurl"));
//			System.out.println(TITLE_NAME=(String) objectobjects.get("vn"));
//
//			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
//			tidbits.setTvplayName(name);
//			tidbits.setDetailUrl(urlBranch);
//			tidbits.setSOURCE(2);
//			tidbits.setImgSmallUrl(IMG_SMALL_URL);
//			IMG_SMALL_NAME = Image1.downloadimg_no_jpg(IMG_SMALL_URL);
//			tidbits.setImgSmallName(IMG_SMALL_NAME);
//			tidbits.setPlayUrl(PLAY_URL);
//			tidbits.setTitleName(TITLE_NAME);
//			tidbits.setOrderNo(iii);
//			
//			String  amount=iQiYiBranch(PLAY_URL);
//			tidbits.setPlayAmount(Integer.valueOf(amount));
//			
//			tidbits.setDataType(DATA_TYPE);	
//			
//			
//			tidbits.setSOURCE(2);
//			
//			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
//			iii+=1;
//		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Iqiyishousuo("¹í´µµÆÖ®ÄÁÒ°¹îÊÂ", "http://www.iqiyi.com/lib/m_211070614.html");
//		Iqiyishousuo("ÀÏ¾ÅÃÅ", "http://www.iqiyi.com/lib/m_208624914.html?src=search");
//		Iqiyishousuo("µÁÄ¹±Ê¼Ç", "http://www.iqiyi.com/lib/m_208834314.html?src=search");
//		Iqiyishousuo("Ñ°Áú¾÷", "http://www.iqiyi.com/lib/m_205360614.html?src=search");
//		Iqiyishousuo("¾Å²ãÑýËþ", "http://www.iqiyi.com/lib/m_207374614.html?src=search");
//		Iqiyishousuo("¹í´µµÆÖ®¾«¾ø¹Å³Ç", "http://www.iqiyi.com/lib/m_209969914.html");
		
		 openordor();
		
		
//		Down("http://cache.video.qiyi.com/jp/othlist/204147201/4/desc/?idType=album&callback=window.Q.__callbacks__.cbtp9wpe",
//				"", "", 1, 1);
		// youkuBranch("Æ¥·òÓ¢ÐÛ",
		// "http://www.youku.com/show_page/id_z1b8c5c54e79a11e2a705.html");
		// youkuhuaxu("Æ¥·òÓ¢ÐÛ",
		// "http://www.youku.com/show_page/id_z1b8c5c54e79a11e2a705.html");
	}

	
}
