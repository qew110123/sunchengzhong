package com.artsoft.download.BaiDu;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaiDuPeoplemingxingguanxitupianDownload2 {
//	public static void peopleurl(String urlMain,String id){
//		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		// String strHtml = DownloadUtil.readHtml(strUrl, 1000 * 30,"UTF-8",
//		// cookstr, null);
//		// StringBuffer strHtml = DownloadUtil.getContent(urlstr);
//		// System.out.println(strHtml);
//		if (strHtml == null || strHtml.equals("")) {
//			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		}
//		if (strHtml == null || strHtml.equals("")) {
//			// return;
//			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		}
//		Document doc = Jsoup.parse(strHtml);
////		Elements links = doc.select("div.yk-col3");
//		 Element content = doc.getElementById("slider_relations");
//	 if (content==null	) {
//			return;
//		}
//		 Elements links = content.getElementsByTag("li");
//		 String PERSON_NAME="";
//		 String PERSON_URL="";
//		 String IMG_URL="";
//		for (Element link : links) {
////			System.out.println(link);
//			System.out.println(PERSON_URL=link.select("a").first().attr("href"));
//			System.out.println(IMG_URL=link.select("img").first().attr("src"));
//			System.out.println(PERSON_NAME=link.select("div.name").first().attr("title"));
//			
//			OracleBaidu.intoTEM_PERSON_IMG_baidu(id,PERSON_NAME, PERSON_URL, IMG_URL,"百度图片");
//		}
//	}
	
	public static void peopleurl(String urlMain,String id,String baidu_name) {
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
		// System.out.println(strHtml);
		// Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.yk-col3");
		// Element content = doc.getElementById("pullimages");
		// if (content == null) {
		// return;
		// }
		// Element linkss = doc.select("div.list").first();
		// Elements links = linkss.select("div.img");
		// String PERSON_NAME = "";
		// String PERSON_URL = "";
		// String IMG_URL = "";
		// for (Element link : links) {
		// // System.out.println(link);
		// System.out.println(PERSON_URL =
		// link.select("a").first().attr("href"));
		// System.out.println(IMG_URL = link.select("img").first().attr("src"));
		// System.out.println(PERSON_NAME =
		// link.select("div.name").first().attr("title"));
		//
		// // OracleBaidu.intoTEM_PERSON_IMG(PERSON_NAME, PERSON_URL, IMG_URL);
		// }
		//

		String strjson = HtmlAnalyze.getTagText(strHtml, "imgtotal:1000,", " headPic:{");
		// System.out.println(strjson);
		int xx = 0;
		boolean bb = false;
		if (strjson != null) {
			if (strjson.length() < 8) {
				return;
			}
			strjson = strjson.substring(6, strjson.length() - 2);
			// System.out.println(strjson);
			String[] jsonlist = strjson.split(",                                ");
			// JSONObject obj = JSONObject.fromObject(strjson);
			// System.out.println(obj);

			for (String string : jsonlist) {
				JSONObject objstring = JSONObject.fromObject(string);
				System.out.println(objstring);
				// System.out.println(objstring.get("thumbURL"));
				System.out.println(objstring.get("hoverURL"));
				// System.out.println(objstring.get("hoverURL"));
				peopleIMG_URL(objstring.get("hoverURL").toString(), urlMain,id, baidu_name);
				if (++xx > 4) {
					bb = true;
					return;
				}
			}
		}
		if (bb == false) {
			// strjson= HtmlAnalyze.getTagText(strHtml, "var skinData = ","var
			// nowServerTime");
			System.out.println(strjson = strHtml);
			// if (strjson.length()<8) {
			// return;
			// }
			// strjson= strjson.substring(6, strjson.length()-2);
			// System.out.println(strjson);
			// String[] jsonlist=strjson.split(", ");
			JSONObject obj = JSONObject.fromObject(strjson);
			// System.out.println(obj.get("data"));

			JSONArray jsonArray = (JSONArray) obj.get("data");
			try {
				for (Object object : jsonArray) {
					// System.out.println(object);
					JSONObject jsons = JSONObject.fromObject(object);
					
					System.out.println(jsons.get("thumbURL"));
					peopleIMG_URL(jsons.get("thumbURL").toString(), urlMain,id, baidu_name);
					if (++xx > 3) {
						bb = true;
						return;
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		
		if (bb == false) {
			// strjson= HtmlAnalyze.getTagText(strHtml, "var skinData = ","var
			// nowServerTime");
			System.out.println(strjson = strHtml);
			// if (strjson.length()<8) {
			// return;
			// }
			// strjson= strjson.substring(6, strjson.length()-2);
			// System.out.println(strjson);
			// String[] jsonlist=strjson.split(", ");
			JSONObject obj = JSONObject.fromObject(strjson);
			// System.out.println(obj.get("data"));

			JSONArray jsonArray = (JSONArray) obj.get("imgs");
			try {
				for (Object object : jsonArray) {
					// System.out.println(object);
					JSONObject jsons = JSONObject.fromObject(object);
					
					System.out.println(jsons.get("thumbURL"));
					peopleIMG_URL(jsons.get("thumbURL").toString(), urlMain,id, baidu_name);
					if (++xx > 3) {
						bb = true;
						return;
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
	
	public static void peopleIMG_URL(String IMG_URL,String PERSON_URL,String id,String PERSON_NAME) {
		OracleBaidu.intoTEM_PERSON_IMG_baidu(id,PERSON_NAME, PERSON_URL, IMG_URL,"百度图片");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		peopleurl("http://baike.baidu.com/view/302258.htm");
		
		
		List<String> listArray = OracleBaidu.selectBaiduitupian("0", "20000");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null&&listTemp.get(1) != null) {
				// mainmore(listTemp.get(0), listTemp.get(1));
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
//				peopleurl(listTemp.get(0));
				String name=listTemp.get(1)+"头像";
				try {
					name=java.net.URLEncoder.encode(name, "utf-8");
//					System.out.println("http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord="+name+"&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&word="+name+"&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&pn=0&rn=60&gsm=3c&1449630567582=");
					String urls="http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord="+name+"&cl=2&lm=0&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=1&ic=&word="+name+"&s=3&se=&tab=&width=0&height=0&face=0&istype=&qc=&nc=&fr=ala&pn=0&rn=10&gsm=3c&1449631513444=";
					System.out.println(urls);
					peopleurl(urls,listTemp.get(0),listTemp.get(1));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
