package com.artsoft.download.only_one_Once;

import java.awt.print.Book;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.artsoft.bean.TEM_MEIHUA;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * 2017年4月28日10:53:49 梅花网
 * 
 */
public class meihua {

	public static void main(String[] args) {
		// String strHtml =
		// DownloadUtil.getHtmlText("http://adm.meihua.info/actionpage/Response.aspx?bdType=2&ps=25&st=1&sa=1&onlytvcresearch=0&sDate=2016-11-17&eDate=2017-4-17",
		// 1000 * 30, "UTF-8", null, null);
		//
		// System.out.println(strHtml);
		// http://adm.meihua.info/ListView.aspx?bdType=0&keyStr=文章###
		// http://adm.meihua.info/ListView.aspx?bdType=0&keyStr=%E6%96%87%E7%AB%A0###

		runstatic();

	}

	// yunxing
	public static void runstatic() {

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

//		mainurl("http://adm.meihua.info/actionpage/QuerySearch.aspx?keyStr=%E5%A7%9C%E6%98%86","姜昆");
		baidunew("姜昆");

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
	private static void baidunew(String name) {
		// TODO Auto-generated method stub
		String utf8name = "";
		try {
			utf8name = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainurl("http://adm.meihua.info/actionpage/QuerySearch.aspx?keyStr="+utf8name, name);
		
	}
	
	
	

	private static void mainurl(String urls,String people) {
		// TODO Auto-generated method stub
		try {
			// daJeWang("http://adm.meihua.info/ListView.aspx?bdType=0&keyStr=%E8%91%9B%E4%BC%98###");
			// DownloadUtil.getHtmlText("http://adm.meihua.info/actionpage/QuerySearch.aspx?keyStr=%E7%99%BD%E7%99%BE%E5%90%88",
			// 1000 * 30, "UTF-8", null, null);
			// https://www.baidu.com/s?wd=%E5%A7%9C%E6%98%86&rsv_spt=1&rsv_iqid=0x96dbd30e00000a52&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=13&rsv_sug1=33&rsv_sug7=101
//			daJeWang("http://adm.meihua.info/actionpage/QuerySearch.aspx?keyStr=%E5%A7%9C%E6%98%86");
			daJeWang(urls);
			String html = "";
			html = daJeWang(
					"http://adm.meihua.info/actionpage/Response.aspx?bdType=2&ps=50&st=1&sa=1&onlytvcresearch=0&sDate=2016-11-28&eDate=2017-4-28&rax=0.16533998902956037");
			System.out.println(html);
			
			
//			String resp = "<?xml version='1.0' encoding='UTF-8'?><bookstore><book id='1'><name>冰与火之歌</name><author>乔治马丁</author><year>2014</year><price>89</price></book><book id='2'><name>安徒生童话</name><author>安徒生</author><year>2014</year><price>69</price></book></bookstore>";
			String resp=html;
			List<Book> books = new ArrayList<Book>();
	        Book kBook = null;
	        try {
	            Document doc = DocumentHelper.parseText(resp);
	            //指向根节点
	            Element root = doc.getRootElement();
//	            Element book = XMLString2.getChildElement(root, "book");
	            //获取book的属性值
//	            String id = book.attributeValue("id");
//	            kBook.setId(id);
	            List<Element> elements = root.elements("Item");
	            String CATCHLINE="";//标语
	            String BRANDNAME="";//品牌名称
	            String PRODUCTNAME="";//产品名称
	            String CHANNELNAME="";//采集频道
	            String categoryname="";//产品类目
	            String language="";//	广告语种
	            String DESCRIPTION="";//描述
	            String graphicsmall="";//图片
	            String 	videotime="";//时长
	            String recordtime="";//发布日期
	            String PEOPLES="";//';//拍摄卡斯"
	            for (Element element : elements) {
	            	TEM_MEIHUA TEMMEIHUA= new TEM_MEIHUA();
	            	
	            	System.out.println(CATCHLINE=element.element("CatchLine").getStringValue());
	            	System.out.println(BRANDNAME=element.element("BrandName").getStringValue());
	            	System.out.println(PRODUCTNAME=element.element("ProductName").getStringValue());
	            	System.out.println(CHANNELNAME=element.element("ChannelName").getStringValue());
	            	System.out.println(categoryname=element.element("CategoryName").getStringValue());
	            	System.out.println(language=element.element("Language").getStringValue());
	            	System.out.println(DESCRIPTION=element.element("Description").getStringValue());
	            	System.out.println(graphicsmall=element.element("GraphicSmall").getStringValue());
	            	System.out.println(videotime=element.element("VideoTime").getStringValue());
	            	System.out.println(recordtime=element.element("RecordTime").getStringValue());
	            	
	            	TEMMEIHUA.setCatchline(CATCHLINE);
	            	TEMMEIHUA.setBrandname(BRANDNAME);
	            	TEMMEIHUA.setProductname(PRODUCTNAME);
	            	TEMMEIHUA.setChannelname(CHANNELNAME);
	            	TEMMEIHUA.setCategoryname(categoryname);
	            	TEMMEIHUA.setLanguage(language);
	            	TEMMEIHUA.setDescription(DESCRIPTION);
	            	TEMMEIHUA.setGraphicsmall(graphicsmall);
	            	TEMMEIHUA.setVideotime(videotime);
	            	TEMMEIHUA.setRecordtime(recordtime);
	            	TEMMEIHUA.setPeoples(people);
	            	
	            	
	            	Oracle.InsertTEMMEIHUA(TEMMEIHUA);
	            	
	            	
	            }
	            
	            
	        } catch (DocumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
//	        for (Book book : books) {
//	            System.out.println(book);
//	        }
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String daJeWang(String url) throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		String newUrl = "http://adm.meihua.info/actionpage/Response.aspx?bdType=2&ps=25&st=1&sa=1&onlytvcresearch=0&sDate=2016-11-17&eDate=2017-4-17";
		newUrl = url;
		// http://adm.meihua.info/actionpage/Response.aspx?bdType=2&ps=25&st=1&sa=1&onlytvcresearch=0&sDate=2016-11-17&eDate=2017-4-17&rax=0.9771383311935087
		HttpGet get = new HttpGet(newUrl);
		get.addHeader(new BasicHeader("Cookie",
				// "gr_user_id=223a2a24-8fad-4ed9-9964-4a00f04025f7;
				// gr_session_id_3d82bb7757134159ae903485602ee950=7100c5f6-c08a-4ddc-abdb-1a86c9273879;
				// Hm_lvt_a233702fe6ac2ef6beca467d8254ba22=1491467407;
				// Hm_lpvt_a233702fe6ac2ef6beca467d8254ba22=1492424480;
				// ASP.NET_SessionId=3ylfahm1fpdvjwypvqmxq1q2; ViewType_6=1;
				// ViewType_3=1;
				// mhauthorization=b69ad7f4a887edec836ad42b2f9c1a6389a913eb88b22c9e2782edb1338a91d4f95117943a4c8150;
				// gi_static=\"{'user_id':'386204','user_name':'1069339155@qq.com','user_email':'1069339155@qq.com'}\";
				// LastSearch_1069339155@qq.com=actionpage%2fQuerySearch.aspx%3fkeyStr%3d%25u8303%25u51b0%25u51b0%2c%e8%8c%83%e5%86%b0%e5%86%b0@actionpage%2fQuerySearch.aspx%3fkeyStr%3d%25u6587%25u7ae0%2c%e6%96%87%e7%ab%a0;
				// ViewType_1=1;
				// DownBasketForPost=%7C231564%2C229530%2C229622%2C228634%2C228558%2C229607%2C228388%2C228354%2C229595%2C228097%2C229582%2C229564%2C229557%2C227542%2C227449%2C227448%2C227372%2C227133%2C227109%2C227024%2C227001%2C226563%2C226182%2C225918%2C225689%7C%7C%7C%7C%7C;
				// DownBasket_2=; referrer=; ViewType_2=1;
				// _ga=GA1.2.227902082.1491467407; ListViewTab=2"));
				"gr_user_id=223a2a24-8fad-4ed9-9964-4a00f04025f7; gr_session_id_3d82bb7757134159ae903485602ee950=7100c5f6-c08a-4ddc-abdb-1a86c9273879; Hm_lvt_a233702fe6ac2ef6beca467d8254ba22=1491467407; Hm_lpvt_a233702fe6ac2ef6beca467d8254ba22=1492424480; ASP.NET_SessionId=3ylfahm1fpdvjwypvqmxq1q2; ViewType_6=1; ViewType_3=1; mhauthorization=b69ad7f4a887edec836ad42b2f9c1a6389a913eb88b22c9e2782edb1338a91d4f95117943a4c8150; gi_static=\"{'user_id':'386204','user_name':'1069339155@qq.com','user_email':'1069339155@qq.com'}\"; DownBasketForPost=%7C231564%2C229530%2C229622%2C228634%2C228558%2C229607%2C228388%2C228354%2C229595%2C228097%2C229582%2C229564%2C229557%2C227542%2C227449%2C227448%2C227372%2C227133%2C227109%2C227024%2C227001%2C226563%2C226182%2C225918%2C225689%7C%7C%7C%7C%7C; DownBasket_2=; _gat=1; ViewType_2=1; LastSearch_1069339155@qq.com=actionpage%2fQuerySearch.aspx%3fkeyStr%3d%25u845b%25u4f18%2c%e8%91%9b%e4%bc%98@actionpage%2fQuerySearch.aspx%3fkeyStr%3d%25u8303%25u51b0%25u51b0%2c%e8%8c%83%e5%86%b0%e5%86%b0@actionpage%2fQuerySearch.aspx%3fkeyStr%3d%25u6587%25u7ae0%2c%e6%96%87%e7%ab%a0; referrer=http%3A%2F%2Fadm.meihua.info%2FListView.aspx%3FbdType%3D0%26keyStr%3D%25E6%2596%2587%25E7%25AB%25A0; _ga=GA1.2.227902082.1491467407; ViewType_1=1; ListViewTab=2"));
		get.addHeader("Content-Type", "text/html;charset=UTF-8");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		get.addHeader("Host", "adm.meihua.info");
		get.addHeader("Referer", "http://adm.meihua.info/ListView.aspx?bdType=0&keyStr=%E6%96%87%E7%AB%A0");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
		// System.out.println(responseString);
		get.releaseConnection();

		return responseString;

	}

}
