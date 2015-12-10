package com.artsoft.download.image;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class manhua {

	public static void mainurllist(String mainUrl,String AREA,String SOURCE) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "utf-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		strHtml = DownloadUtil.decodeUnicode(strHtml);
		// System.out.println(strHtml);
		strHtml = strHtml.replace("search.renderResult(", "");
		// strHtml = strHtml.substring(0, strHtml.length() - 1);
		// String bbs=strHtml.substring(strHtml.length() - 5, strHtml.length() -
		// 3);
		// System.out.println(bbs);
		// System.out.println(strHtml);
		strHtml = strHtml.substring(0, strHtml.length() - 4);
		// System.out.println(strHtml);
		strHtml = strHtml.replaceAll("class=\"red1_font12\">", "class='red1_font12'>");
		JSONObject author = new JSONObject();
		JSONArray authors = new JSONArray();
		author = JSONObject.fromObject(strHtml);
		System.out.println(author);

		authors = (JSONArray) author.get("result");
		String ANIMATION_NAME = ""; // 动漫名称
		String ANIMATION_URL = ""; // 网站url
		String AUTHOR = ""; // 作者
//		String AREA = ""; // 地区
		String STATE = ""; // 状态
		String POPULARITY = ""; // 人气
		String SUBJECT_NAME = ""; // 题材名称
		String ANIMATION_CATEGORY = ""; // 分类
		String IN_DATE = ""; // 收录日期
//		String SOURCE = ""; // 漫画来源 ： 网站名称

		for (Object object : authors) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// objectobject=JSONObject.fromObject(object);
			System.out.println(ANIMATION_NAME=(String) objectobject.get("name"));
			System.out.println(ANIMATION_URL="http://manhua.dmzj.com"+(String) objectobject.get("comic_url"));
			System.out.println(SUBJECT_NAME=(String) objectobject.get("type"));
			System.out.println(objectobject.get("last_update_date"));
			String jsonid="";
			System.out.println(jsonid=(String) objectobject.get("id"));
			System.out.println(AUTHOR=(String) objectobject.get("author"));
			System.out.println(IN_DATE=(String) objectobject.get("last_update_date"));
			String STATEs="";
			System.out.println(STATEs=(String) objectobject.get("status"));
			if (!STATEs.equals("")) {
				STATE="完";
			}else{
				STATE="连载中";
			}
			
			//http://manhua.dmzj.com/hits/4201.json
			String POPULARITYurl="http://manhua.dmzj.com/hits/"+jsonid+".json";
			String strHtmljson = DownloadUtil.getHtmlText(POPULARITYurl, 1000 * 30, "utf-8", null, null);
			String strHtmljsongetTag = HtmlAnalyze.getTagText(strHtmljson, "hot_hits\":\"", "\"");
			if (strHtmljsongetTag!=null) {
				POPULARITY=strHtmljsongetTag;
				POPULARITY=POPULARITY.replace("\\u2103", "").replaceAll("℃", "");
			}
			
			try {
				String strHtmlss = DownloadUtil.getHtmlText(ANIMATION_URL, 1000 * 30, "utf-8", null, null);
				ANIMATION_CATEGORY=HtmlAnalyze.getTagText(strHtmlss, "<th>分类：</th><td><a title='", "'");
				if (ANIMATION_CATEGORY==null) {
					ANIMATION_CATEGORY="";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OracleSarFtGov.intotem_animation(ANIMATION_NAME, ANIMATION_URL, AUTHOR, AREA, STATE, POPULARITY, SUBJECT_NAME, ANIMATION_CATEGORY, IN_DATE, SOURCE);
			
//			AREA=
		}

		// strHtml=strHtml.replaceAll("search.renderResult(", "");
		// strHtml=strHtml.substring(0, strHtml.length()-1);
		// System.out.println(strHtml);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for (int i = 1; i <= 1505; i++) {

//		mainurllist(
//				"http://s.acg.178.com/mh/index.php?c=category&m=doSearch&status=0&reader_group=0&zone=2304&initial=all&type=9&_order=h&p=1&callback=search.renderResult&_=1449670088287",
//				"日本","动漫之家");
		// }
//		 for (int i = 1; i <= 9; i++) {
//		
////		 mainurllist("http://yy.8fkd.com/YanYuanKu/Search.aspx?key=&gj=&xx=&sex=2&xz=&zm=&page="
////		 + i, "女");
//		 mainurllist(
//					"http://s.acg.178.com/mh/index.php?c=category&m=doSearch&status=0&reader_group=0&zone=2304&initial=all&type=9&_order=h&p="+i+"&callback=search.renderResult&_=1449670088287",
//					"日本","动漫之家");
//		 }
		 for (int i = 1; i <= 10; i++) {
				
//			 mainurllist("http://yy.8fkd.com/YanYuanKu/Search.aspx?key=&gj=&xx=&sex=2&xz=&zm=&page="
//			 + i, "女");
			 mainurllist(
		 "http://s.acg.178.com/mh/index.php?c=category&m=doSearch&status=0&reader_group=0&zone=2304&initial=all&type=3245&_order=h&p="+i+"&&callback=search.renderResult&_=1449715754094","日本","动漫之家");
		 }
	}

}
