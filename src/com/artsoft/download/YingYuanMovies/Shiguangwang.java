package com.artsoft.download.YingYuanMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

//import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Shiguangwang {
	public static void mainUrl(String urlmain){
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String hotplaySvList  = HtmlAnalyze.getTagText(strHtml.toString(), "var hotplaySvList = ", ";");
		System.out.println(hotplaySvList);
		
		JSONObject author = new JSONObject();
//		JSONArray arraylist = new JSONArray();
		
		try {
			JSONArray arraylist = new JSONArray(hotplaySvList);
			for (int i = 0; i < arraylist.length(); i++) {
				org.json.JSONObject iObj = arraylist.getJSONObject(i);
//				System.out.println(iObj);
				String url="";
				String id="";
				String title="";
				System.out.println(url=(String) iObj.get("Url"));
				System.out.println(id=""+ iObj.get("Id"));
				System.out.println(title=(String) iObj.get("Title"));
				Url(url, title,id);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		arraylist= hotplaySvList;
	}
	
	
	public static void Url(String url,String title,String id){
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
//		Elements links = doc.select("a.result-title");
		
		String pinggfei= HtmlAnalyze.getTagText(strHtml.toString(), "class=\"db_point ml6\">", "</span>");
		System.out.println(pinggfei);
		
		String daoyan= HtmlAnalyze.getTagText(strHtml.toString(), "导演：", "/dd>");
		System.out.println(daoyan);
		String bianji=HtmlAnalyze.getTagText(strHtml.toString(), "编剧：", "/dd>");
		System.out.println(bianji);
		
		String guojiadiqu=HtmlAnalyze.getTagText(strHtml.toString(), "国家地区：", "/dd>");
		System.out.println(guojiadiqu);
		
		String faxingggongshi=HtmlAnalyze.getTagText(strHtml.toString(), "发行公司：", "/dd>");
		System.out.println(faxingggongshi);
		
		String mosttitle=HtmlAnalyze.getTagText(strHtml.toString(), "更多片名：", "/dd>");
		System.out.println(mosttitle);
		
		String juqing=HtmlAnalyze.getTagText(strHtml.toString(), "剧情：", "</p>");
		System.out.println(juqing);
		
		Elements links = doc.select("dl.main_actor");
		for (Element element : links) {
			System.out.println(element.text());
		}
		
		//票房数据的js
//		String strHtml = "";
		String jsurl="http://service.library.mtime.com/Movie.api?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Library.Services&Ajax_CallBackMethod=GetMovieOverviewRating&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2F209180%2F&t=201622917372586847&Ajax_CallBackArgument0="+id;
		
		 bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(jsurl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		String TotalBoxOffice=HtmlAnalyze.getTagText(strHtml.toString(), "TotalBoxOffice\":\"", "\"");
		String danwei=HtmlAnalyze.getTagText(strHtml.toString(), "TotalBoxOfficeUnit\":\"", "\"");
		System.out.println(TotalBoxOffice+danwei);
		
		
	}
	

	//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//http://theater.mtime.com/China_Beijing/
		mainUrl("http://theater.mtime.com/China_Beijing/");
		
	}

}
