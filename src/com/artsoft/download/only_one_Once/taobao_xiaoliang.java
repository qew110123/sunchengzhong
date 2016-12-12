package com.artsoft.download.only_one_Once;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class taobao_xiaoliang {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		taobaoxiaoliangshuju("https://s.taobao.com/search?q=%E9%AC%BC%E5%90%B9%E7%81%AF&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20161202&ie=utf8&bcoffset=0&p4ppushleft=%2C44&sort=sale-desc");
//https://s.taobao.com/search?q=%E9%AC%BC%E5%90%B9%E7%81%AF&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20161202&ie=utf8&bcoffset=0&p4ppushleft=%2C44&sort=sale-desc&s=44
		for (int i = 0; i <= 4312; i=i+44) {
			taobaoxiaoliangshuju("https://s.taobao.com/search?q=%E7%9B%97%E5%A2%93%E7%AC%94%E8%AE%B0&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20161202&ie=utf8&bcoffset=0&ntoffset=0&p4ppushleft=%2C44&sort=sale-desc&s="+i);
		}
	}

	private static void taobaoxiaoliangshuju(String urlMain) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		
		strHtml=HtmlAnalyze.getTagText(strHtml.toString(), "auctions\":", ",\"recommendAuctions\"");
//		System.out.println(strHtml);
		
		JSONObject objects = new JSONObject();
		JSONArray list = new JSONArray();

//		objects = JSONObject.fromObject(strHtml);
//		System.out.println(objects);
//		System.out.println(objects.get("data"));
//		JSONObject data = (JSONObject) objects.get("data");
//		System.out.println(data);
//		list = (JSONArray) objects.get("data");
		list=JSONArray.fromObject(strHtml);
		int search_index = 0;
		String trend = "";
		String title="";
		String xiangxi_url="";
		for (Object object : list) {
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println( objectobject.getString("view_sales"));
			title=objectobject.getString("title");
			String view_price=objectobject.getString("view_price");
			String item_loc=objectobject.getString("item_loc");
			String detail_url=objectobject.getString("detail_url");
			
			CommonUtil.setLog(objectobject.getString("view_sales"));
		}
		
		
		
	}

}
