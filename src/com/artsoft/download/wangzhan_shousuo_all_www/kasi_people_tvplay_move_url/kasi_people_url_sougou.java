package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_tvplay_move_url;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_PERSON_URL_WORKS;
import com.artsoft.bean.TVPLAY_IQIYI_INDEX;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class kasi_people_url_sougou {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String id="0";
		String name="刘涛";
		String url= "http://baike.sogou.com/v74151.htm?fromTitle=%E5%88%98%E6%B6%9B";
		
		mainmore(id,name, url);

	}
	
	
	
	public static void mainmore(String id,String name, String url) {
		
		
		
		String strHtml = "";
		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		Document doc = Jsoup.parse(strHtml);
		
		String lemmaId=HtmlAnalyze.getTagText(strHtml, "lemmaId=", ";");
		
		if (!lemmaId.equals("")&&lemmaId!=null) {
			// 电视剧
			tvplay(id,name, url, strHtml, doc,lemmaId);
			// 电影
			moves(id,name, url, strHtml, doc,lemmaId);
		}
		
		
		
		
	}
	
	/**
	 * 参演电视剧 2016年5月10日14:18:20
	 * 
	 * @param strId
	 * @param url
	 */
	public static void tvplay(String strId,String name, String url, String strHtml, Document doc,String lemmaId) {
//		tem_person_works personwork = new tem_person_works();
		TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//		personwork.setPersonId(Integer.parseInt(strId));
		work.setPersonId(strId);
//		personwork.setPersonUrl(url);
		work.setPersonName(name);
		work.setPersonUrl(url);
		// String strHtml = "";
		//http://baike.sogou.com/getPersonOpusByType.v?lemmaId=74151&type=1&start=0&num=12
		//http://baike.sogou.com/getPersonOpusByType.v?lemmaId=56289&type=2&start=0&num=12
		String url_lemmaId="http://baike.sogou.com/getPersonOpusByType.v?lemmaId="+lemmaId+"&type=1&start=0&num=12";
		
		strHtml = DownloadUtil.getHtmlText(url_lemmaId, 1000 * 30, "UTF-8", null,null);
//		 doc = Jsoup.parse(strHtml);
		
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		
//		JSONObject data = (JSONObject) people.get(name);
		System.out.println(people.toString());
		
		
		list=people.getJSONArray("movies");
		int total=people.getInt("total");
		
		
		
		
		
		int yeshu=1;
		
		for (int i = 0; i < total; i=i+12) {
			
			url_lemmaId="http://baike.sogou.com/getPersonOpusByType.v?lemmaId="+lemmaId+"&type="+yeshu+"&start=0&num=12";
			yeshu=yeshu+1;
			
			strHtml = DownloadUtil.getHtmlText(url_lemmaId, 1000 * 30, "UTF-8", null,null);
//			 doc = Jsoup.parse(strHtml);
			
			
			 people = new JSONObject();
			 list = new JSONArray();

			people = JSONObject.fromObject(strHtml);
			
//			JSONObject data = (JSONObject) people.get(name);
			System.out.println(people.toString());
			
			
			list=people.getJSONArray("movies");
//			int total=people.getInt("total");
			
			for (Object object : list) {
				String date="";
				String nametitle="";
				String sourceLinkUrl="";
				JSONObject objectobject = JSONObject.fromObject(object);
				System.out.println(date = objectobject.getString("date"));
				System.out.println(nametitle = objectobject.getString("name"));
				System.out.println(sourceLinkUrl = objectobject.getString("sourceLinkUrl"));
				
				work.setDateName(nametitle);
				work.setDateUrl(sourceLinkUrl);
				work.setDateTime(date);
				
				work.setSource(2);
				work.setDataType(1);
//				OracleHaoSou.intotem_person_works(personwork);
				Oracle.InsertTEM_PERSON_URL_WORKS(work);
				
			}
			
			
		}
		
	}
	
	
	
	/**
	 * 参演电影
	 * 
	 * @param strId
	 * @param url
	 */
	public static void moves(String strId,String name, String url, String strHtml, Document doc,String lemmaId) {
//		tem_person_works personwork = new tem_person_works();
		TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//		personwork.setPersonId(Integer.parseInt(strId));
		work.setPersonId(strId);
//		personwork.setPersonUrl(url);
		work.setPersonName(name);
		work.setPersonUrl(url);
		// String strHtml = "";
		//http://baike.sogou.com/getPersonOpusByType.v?lemmaId=74151&type=1&start=0&num=12
		//http://baike.sogou.com/getPersonOpusByType.v?lemmaId=56289&type=1&start=0&num=12
		String url_lemmaId="http://baike.sogou.com/getPersonOpusByType.v?lemmaId="+lemmaId+"&type=1&start=0&num=12";
		
		strHtml = DownloadUtil.getHtmlText(url_lemmaId, 1000 * 30, "UTF-8", null,null);
//		 doc = Jsoup.parse(strHtml);
		
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtml);
		
//		JSONObject data = (JSONObject) people.get(name);
		System.out.println(people.toString());
		
		
		list=people.getJSONArray("TvPlays");
		int total=people.getInt("total");
		
		
		
		
		
		int yeshu=1;
		
		for (int i = 0; i < total; i=i+12) {
			
			url_lemmaId="http://baike.sogou.com/getPersonOpusByType.v?lemmaId="+lemmaId+"&type="+yeshu+"&start=0&num=12";
			yeshu=yeshu+1;
			
			strHtml = DownloadUtil.getHtmlText(url_lemmaId, 1000 * 30, "UTF-8", null,null);
//			 doc = Jsoup.parse(strHtml);
			
			
			 people = new JSONObject();
			 list = new JSONArray();

			people = JSONObject.fromObject(strHtml);
			
//			JSONObject data = (JSONObject) people.get(name);
			System.out.println(people.toString());
			
			
			list=people.getJSONArray("movies");
//			int total=people.getInt("total");
			
			for (Object object : list) {
				String date="";
				String nametitle="";
				String sourceLinkUrl="";
				JSONObject objectobject = JSONObject.fromObject(object);
				System.out.println(date = objectobject.getString("date"));
				System.out.println(nametitle = objectobject.getString("name"));
				System.out.println(sourceLinkUrl = objectobject.getString("sourceLinkUrl"));
				
				work.setDateName(nametitle);
				work.setDateUrl(sourceLinkUrl);
				work.setDateTime(date);
				
				work.setSource(2);
				work.setDataType(2);
//				OracleHaoSou.intotem_person_works(personwork);
				Oracle.InsertTEM_PERSON_URL_WORKS(work);
				
			}
			
			
		}
		
	}

}
