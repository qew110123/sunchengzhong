package com.artsoft.download.variety.Image_variety;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Lesi_Image_variety {
	
	
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
		// String str = strHtml;
		// String[] strarray = str.split("\"actor\":");
		// for (int i = 0; i < strarray.length; i++) {
		// String urlBranch = "";
		// String Amount = "";
		// String name = "";
		// String score = "";
		// String aid="";
		// String DETAIL_URL="";
		// // System.out.println(strarray[i]);
		// System.out.println(Amount = HtmlAnalyze.getTagText(strarray[i],
		// "playCount\":\"", "\""));
		// System.out.println(score = HtmlAnalyze.getTagText(strarray[i],
		// "rating\":\"", "\""));
		// System.out.println(name = HtmlAnalyze.getTagText(strarray[i],
		// "\"name\":\"", "\""));
		// System.out.println(aid = HtmlAnalyze.getTagText(strarray[i],
		// "\"aid\":\"", "\""));
		// if (aid != "") {
		// DETAIL_URL="http://www.le.com/tv/"+aid+".html";
		// }
		// if (urlBranch != null || Amount != null || name != null || score !=
		// null || urlBranch != "" || Amount != ""
		// || name != "" || score != "") {
		// try {
		// if (name != null && Amount != null && mainUrl != null) {
		// OracleOpreater.intoReputationAndDETAIL_URL(name, "5", Amount, "0",
		// "", mainUrl, "0", "0",DETAIL_URL);
		// }
		// if (name != null && score != null && mainUrl != null) {
		// OracleOpreater.intoReputationAndDETAIL_URL(name, "5", score, "0", "",
		// mainUrl, "0", "1",DETAIL_URL);
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// }
		// }

		JSONObject letvjson = new JSONObject();
		JSONArray letvjsonArray = new JSONArray();
		System.out.println(strHtml);
		letvjson = JSONObject.fromObject(strHtml);
		letvjsonArray = (JSONArray) letvjson.get("video_list");
		String urlBranch = "";
		String Amount = "";
		String name = "";
		String score = "";
		String aid = "";
		String DETAIL_URL = "";
		if (letvjsonArray == null) {
			return;
		}
		try {

			for (Object object : letvjsonArray) {
				JSONObject objectobject = JSONObject.fromObject(object);
				Amount = (String) objectobject.get("playCount");
				score = (String) objectobject.get("rating");
				name = (String) objectobject.get("name");
				name= (String) objectobject.get("albumName");
				aid = (String) objectobject.get("aid");
				// System.out.println(Amount = (String)
				// objectobject.get("playCount"));
				// System.out.println(score = (String)
				// objectobject.get("rating"));
				// System.out.println(name = (String) objectobject.get("name"));
				// System.out.println(aid = (String) objectobject.get("aid"));
				if (aid != "") {
					DETAIL_URL = "http://www.le.com/tv/" + aid + ".html";
				}

				System.out.println(Amount + "score" + score);
				
				JSONObject objectobjectimgurl=(JSONObject) objectobject.get("images");
				String url300=(String) objectobjectimgurl.get("300*300");
				
				System.out.println(url300);
				
				try {
					DownloadImage.download(url300, name+".jpg", "D:\\Image\\variety\\lesi\\");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":¿ª Ê¼");

		for (int i = 0; i < 30; i++) {
			//http://list.le.com/apin/chandata.json?c=11&d=1&md=&o=3&p=2&s=3
			String mainUrl = "http://list.le.com/apin/chandata.json?c=11&d=2&md=&o=1&p=" + i + "&s=1";
			mainurl(mainUrl);
		}
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":½á Êø");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
	}

}
