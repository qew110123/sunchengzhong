package com.artsoft.download.iqiyi_youku_index.Iqiyi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.artsoft.bean.TEM_IQIYI_AND_YOUKU_WORD_INDEX;
import com.artsoft.bean.TVPLAY_IQIYI_INDEX;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Iqiyi_index {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		runstatic();

	}
	
	
	
	
	

	private static void runstatic() {
		// TODO Auto-generated method stub
		List<String> listArray = Oracle.selec_tvplay();
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				
				String name=listTemp.get(0);
				String krywordutf8 = "";
				try {
					krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iqiyiIndex(name,krywordutf8);
				
			}
		}
	}






	private static void iqiyiIndex(String name,String krywordutf8) {
		// TODO Auto-generated method stub
		//播放指数趋势全部内容的url
		//http://uaa.iqiyi.com/video_index/v1/get_index_trend?album_name=%E8%9C%82%E9%B8%9F%E7%89%B9%E6%94%BB&time_window=-1&callback=window.Q.__callbacks__.cbjlo7zo
		String url_bofang_index="http://uaa.iqiyi.com/video_index/v1/get_index_trend?album_name="+krywordutf8+"&time_window=-1&callback=window.Q.__callbacks__.cbjlo7zo";
		
		Iqiyi_bofang_index(name,url_bofang_index);
		
		
		
		//人物画像
		//http://uaa.iqiyi.com/video_index/v1/get_user_profile?album_name=%E8%9C%82%E9%B8%9F%E7%89%B9%E6%94%BB&callback=window.Q.__callbacks__.cbs271uf
		String url_people="http://uaa.iqiyi.com/video_index/v1/get_user_profile?album_name="+krywordutf8+"&callback=window.Q.__callbacks__.cbs271uf";
		
//		Iqiyi_people(name,url_people);
		
		
		
		//地域划分
		//http://uaa.iqiyi.com/video_index/v1/get_province_distribution?album_name=%E8%9C%82%E9%B8%9F%E7%89%B9%E6%94%BB&callback=window.Q.__callbacks__.cbkit375
		String url_diqi="http://uaa.iqiyi.com/video_index/v1/get_province_distribution?album_name="+krywordutf8+"&callback=window.Q.__callbacks__.cbkit375";
		
//		Iqiyi_diqi(name,url_diqi);
		
		
		
		//pc和手机划分
		//http://index.iqiyi.com/q/?name=%E7%BA%A2%E8%89%B2%E9%80%9A%E9%81%93
		String url_pc="http://index.iqiyi.com/q/?name="+krywordutf8+"";
		
		Iqiyi_pc_m(name,url_pc);
		
		
		
	}
	
	
	private static void Iqiyi_pc_m(String name, String url_pc) {
		// TODO Auto-generated method stub
		String strHtml = Htmlurl(url_pc);
		String strHtmlString = HtmlAnalyze.getTagText(strHtml, "videoPlatformStat", "};");
		System.out.println(strHtmlString);
		if (strHtmlString.equals("")) {
			return;
		}
		String pc=HtmlAnalyze.getTagText(strHtmlString, "p\":", ",");
		String Mobile=HtmlAnalyze.getTagText(strHtmlString, "m\":", "}");
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("pc");
		Iqiyi.setLabelRate(pc.toString());
		Iqiyi.setDimensionType(7);
		Iqiyi.setSource(1);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		 Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("Mobile");
		Iqiyi.setLabelRate(Mobile.toString());
		Iqiyi.setDimensionType(7);
		Iqiyi.setSource(1);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		

	}






	private static void Iqiyi_diqi(String name, String url_diqi) {
		// TODO Auto-generated method stub
		String strHtml = Htmlurl(url_diqi);
		String strHtmlString = HtmlAnalyze.getTagText(strHtml, "data\":", "})}catch(e)");
		System.out.println(strHtmlString);
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		if (strHtmlString.equals("")) {
			return;
		}

		people = JSONObject.fromObject(strHtmlString);
//		System.out.println(people);

//		JSONObject data = (JSONObject) people.get(name);
		
		//人数的性别
		
//		JSONObject genderLabels=data.getJSONObject("genderLabels");
		JSONArray provinceNameslist=people.getJSONArray("provinceNames");
		ArrayList List_provinceNames_name = new ArrayList(); 
		for (Object object : provinceNameslist) {
//			System.out.println(object);
			List_provinceNames_name.add(object);
		}
		try {
			
		
			JSONArray jsonpeople=people.getJSONObject("details").getJSONArray(name);
	//		JSONArray jsongender=jsonpeople.getJSONArray("gender");
			int i=0;
			for (Object object : jsonpeople) {
				System.out.println(object);
				System.out.println(List_provinceNames_name.get(i));
				
				TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
				Iqiyi.setWord(name);
				Iqiyi.setLabelName(List_provinceNames_name.get(i).toString());
				Iqiyi.setLabelRate(object.toString());
				Iqiyi.setDimensionType(3);
				Iqiyi.setSource(1);
				Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
				i+=1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void Iqiyi_people(String name, String url_people) {
		// TODO Auto-generated method stub
		String strHtml = Htmlurl(url_people);
		String strHtmlString = HtmlAnalyze.getTagText(strHtml, "data\":", "})}catch(e)");
		System.out.println(strHtmlString);
		if (strHtmlString.equals("")) {
			return;
		}
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtmlString);
//		System.out.println(people);

//		JSONObject data = (JSONObject) people.get(name);
		
		//人数的性别
		
//		JSONObject genderLabels=data.getJSONObject("genderLabels");
		JSONArray genderLabelslist=people.getJSONArray("genderLabels");
		ArrayList List_xingbei_name = new ArrayList(); 
		for (Object object : genderLabelslist) {
//			System.out.println(object);
			List_xingbei_name.add(object);
		}
		try {
			JSONObject jsonpeople=people.getJSONObject("details").getJSONObject(name);
			JSONArray jsongender=jsonpeople.getJSONArray("gender");
			int i=0;
			for (Object object : jsongender) {
				System.out.println(object);
				System.out.println(List_xingbei_name.get(i));
				TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
				Iqiyi.setWord(name);
				Iqiyi.setLabelName(List_xingbei_name.get(i).toString());
				Iqiyi.setLabelRate(object.toString());
				Iqiyi.setDimensionType(1);
				Iqiyi.setSource(1);
//				Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
				i+=1;
			}
		
		
		
		
		//年龄
		
		
		
			JSONArray ageLabelslist=people.getJSONArray("ageLabels");
			ArrayList ageLabels_name = new ArrayList(); 
			for (Object object : ageLabelslist) {
	//			System.out.println(object);
				ageLabels_name.add(object);
			}
			
	//		JSONObject jsonpeople=people.getJSONObject("details").getJSONObject(name);
			JSONArray jsonage=jsonpeople.getJSONArray("age");
			i=0;
			for (Object object : jsonage) {
				System.out.println(ageLabels_name.get(i));
				System.out.println(object);
				
				TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
				Iqiyi.setWord(name);
				Iqiyi.setLabelName(ageLabels_name.get(i).toString());
				Iqiyi.setLabelRate(object.toString());
				Iqiyi.setDimensionType(2);
				Iqiyi.setSource(1);
//				Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
				
				i+=1;
			}
			
			
			
			
			//星座
			JSONArray constellationLabelslist=people.getJSONArray("constellationLabels");
			ArrayList constellationLabels_name = new ArrayList(); 
			for (Object object : constellationLabelslist) {
	//			System.out.println(object);
				constellationLabels_name.add(object);
			}
			
	//		JSONObject jsonpeople=people.getJSONObject("details").getJSONObject(name);
			JSONArray jsonconstellation=jsonpeople.getJSONArray("constellation");
			i=0;
			for (Object object : jsonconstellation) {
				System.out.println(constellationLabels_name.get(i));
				System.out.println(object);
				
				TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
				Iqiyi.setWord(name);
				Iqiyi.setLabelName(constellationLabels_name.get(i).toString());
				Iqiyi.setLabelRate(object.toString());
				Iqiyi.setDimensionType(4);
				Iqiyi.setSource(1);
//				Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
				
				i+=1;
			}
			
			
			
			//学历
			JSONArray educationLabelslist=people.getJSONArray("educationLabels");
			ArrayList educationLabels_name = new ArrayList(); 
			for (Object object : educationLabelslist) {
	//			System.out.println(object);
				educationLabels_name.add(object);
			}
			
	//		JSONObject jsonpeople=people.getJSONObject("details").getJSONObject(name);
			JSONArray jsoneducation=jsonpeople.getJSONArray("education");
			i=0;
			for (Object object : jsoneducation) {
				System.out.println(educationLabels_name.get(i));
				System.out.println(object);
				
				TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
				Iqiyi.setWord(name);
				Iqiyi.setLabelName(educationLabels_name.get(i).toString());
				Iqiyi.setLabelRate(object.toString());
				Iqiyi.setDimensionType(5);
				Iqiyi.setSource(1);
//				Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
				
				i+=1;
			}
			
			
			
			
			//兴趣
			JSONArray interestLabelslist=people.getJSONArray("interestLabels");
			ArrayList interestLabels_name = new ArrayList(); 
			for (Object object : interestLabelslist) {
	//			System.out.println(object);
				interestLabels_name.add(object);
			}
			
	//		JSONObject jsonpeople=people.getJSONObject("details").getJSONObject(name);
			JSONArray jsoninterest=jsonpeople.getJSONArray("interest");
			i=0;
			for (Object object : jsoninterest) {
				System.out.println(interestLabels_name.get(i));
				System.out.println(object);
				
				TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
				Iqiyi.setWord(name);
				Iqiyi.setLabelName(interestLabels_name.get(i).toString());
				Iqiyi.setLabelRate(object.toString());
				Iqiyi.setDimensionType(6);
				Iqiyi.setSource(1);
//				Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
				
				i+=1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

//		JSONArray datalist = (JSONArray) data.get("list");
//
//		for (Object object : datalist) {
//			JSONObject objectobject = JSONObject.fromObject(object);
//			String news_title = (String) objectobject.get("title");
//			System.out.println(news_title);
//			String news_url = (String) objectobject.get("url");
//			String news_sitename = (String) objectobject.get("sitename");
//			String news_date = (String) objectobject.get("pdate");
//
////			OracleHaoSou.intotem_person_relevant_news(data_date, person_id, news_date, news_sitename, news_title,
////					news_url, urlMain,data_type);
//		}
		 
	}

	public static String Htmlurl(String urlMain) {
		 String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
		 "UTF-8", null, null);

		 int i=0;
		 while (i<15 && strHtml.equals("")) {
			 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
					 "UTF-8", null, null);
			 i=i+1;
			
		}
//		 if (strHtml.equals("")) {
//			 
//			 strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30,
//					 "UTF-8", null, null);
//			 
//			
//		}
//		String strHtml = HaoSouWordAdmin.urlreturnHtml(urlMain);
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return strHtml;

	}

	private static void Iqiyi_bofang_index(String name,String url_bofang_index) {
		// TODO Auto-generated method stub
		String strHtml = Htmlurl(url_bofang_index);
		String strHtmlString = HtmlAnalyze.getTagText(strHtml, "data\":", "})}catch(e)");
		
		if (strHtmlString.equals("")) {
			return;
		}
		
//		System.out.println(strHtmlString);
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();

		people = JSONObject.fromObject(strHtmlString);
//		System.out.println(people);
		try {
			JSONObject data = (JSONObject) people.get(name);
			System.out.println(data.toString());
		
			 for (Iterator iter = data.keys(); iter.hasNext();) { 
			        String key = (String)iter.next();
			        System.out.println(key);
			        System.out.println(data.getString(key));
			        TVPLAY_IQIYI_INDEX tvplayindex= new TVPLAY_IQIYI_INDEX();
			        tvplayindex.setDataUrl(url_bofang_index);
			        tvplayindex.setTvplayName(name);
			        tvplayindex.setVCount(data.getString(key));
			        tvplayindex.setDataDate(key);
//			        tvplayindex.set
			        
			        Oracle.InsertTVPLAY_IQIYI_INDEX(tvplayindex);
			        
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
