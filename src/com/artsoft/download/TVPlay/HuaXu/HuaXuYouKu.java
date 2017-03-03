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
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;
import com.artsoft.util.ftp.FavFTPUtil;

public class HuaXuYouKu {

	/**
	 * 进行优酷网的详细类表页数据的采集
	 * 预告片
	 * @param urlBranch
	 */
	public static void youkuBranch(String name,String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);
		/**
		 * 2016年9月6日11:11:51
		 * 预告片
		 */
		String strHtmlyugaopian=HtmlAnalyze.getTagText(strHtml, "<h3 class=\"title\">预告片</h3>", "<!--body end-->",true,0);
		Document docyugaopian = Jsoup.parse(strHtmlyugaopian);
		
		Elements links = docyugaopian.select("div.collgrid4w");
		//预告片
//		int page1=youkudown(links.toString(), name, urlBranch, 2,1);
		int page1=1;
		//http://www.youku.com/show_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87_id_z1b8c5c54e79a11e2a705.html?dt=json&tab_num=5&__rt=1&__ro=reload_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87
		//http://www.youku.com/show_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87.html?id=z1b8c5c54e79a11e2a705&page=1&dt=json&tab_num=5&__rt=1&__ro=around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87_more1
		//http://www.youku.com/show_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87.html?id=z1b8c5c54e79a11e2a705&page=2&dt=json&tab_num=5&__rt=1&__ro=around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87_more2
		String tab_num=HtmlAnalyze.getTagText(strHtml, "%E9%A2%84%E5%91%8A%E7%89%87\" tab_num=\"", "\"");
		String id=HtmlAnalyze.getTagText(urlBranch, "id_", ".html");
		
		String urlBranchjson="http://www.youku.com/show_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87_id_"+id+".html?dt=json&tab_num="+tab_num+"&__rt=1&__ro=reload_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87";
		strHtml = DownloadUtil.getHtmlText(urlBranchjson, 1000 * 30, "UTF-8", null, null);
		
		page1=youkudown(strHtml.toString(), name, urlBranch, 2,page1);
		
		
		for (int i = 1; i < 10; i++) {
			String urlmore="http://www.youku.com/show_around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87.html?id="+id+"&page="+i+"&dt=json&tab_num="+tab_num+"&__rt=1&__ro=around_type_2_title_%E9%A2%84%E5%91%8A%E7%89%87_more"+i;
			strHtml = DownloadUtil.getHtmlText(urlBranchjson, 1000 * 30, "UTF-8", null, null);
			
			page1=youkudown(strHtml.toString(), name, urlBranch, 2,page1);
		}
		
		
	}
	
	
	/**
	 * 进行优酷网的详细类表页数据的采集
	 * 花絮
	 * @param urlBranch
	 */
	public static void youkuhuaxu(String name,String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);
		
		/**
		 * 2016年9月6日11:11:51
		 */
		String strHtmlyugaopian=HtmlAnalyze.getTagText(strHtml, "class=\"title\">花絮</h3>", "<!--body end-->",true,0);
		Document docyugaopian = Jsoup.parse(strHtmlyugaopian);
		
		//花絮
		Elements links = docyugaopian.select("div.collgrid4w");//.select("ul.v");
//		youkudown(links.toString(), name, urlBranch, 1,1);
		int page1=1;
		//http://www.youku.com/show_around_type_3_title_%E8%8A%B1%E7%B5%AE_id_zfacfcb0cec2511e583e8.html?dt=json&tab_num=6&__rt=1&__ro=reload_around_type_3_title_%E8%8A%B1%E7%B5%AE
		//http://www.youku.com/show_around_type_3_title_%E8%8A%B1%E7%B5%AE.html?id=zfacfcb0cec2511e583e8&page=1&dt=json&tab_num=6&__rt=1&__ro=around_type_3_title_%E8%8A%B1%E7%B5%AE_more1
		//http://www.youku.com/show_around_type_3_title_%E8%8A%B1%E7%B5%AE.html?id=zfacfcb0cec2511e583e8&page=2&dt=json&tab_num=6&__rt=1&__ro=around_type_3_title_%E8%8A%B1%E7%B5%AE_more2
		
		String tab_num=HtmlAnalyze.getTagText(strHtml, "%E8%8A%B1%E7%B5%AE\" tab_num=\"", "\"");
		String id=HtmlAnalyze.getTagText(urlBranch, "id_", ".html");
		
		String urlBranchjson="http://www.youku.com/show_around_type_3_title_%E8%8A%B1%E7%B5%AE_id_"+id+".html?dt=json&tab_num="+tab_num+"&__rt=1&__ro=reload_around_type_3_title_%E8%8A%B1%E7%B5%AE";
		strHtml = DownloadUtil.getHtmlText(urlBranchjson, 1000 * 30, "UTF-8", null, null);
		
		page1=youkudown(strHtml.toString(), name, urlBranch, 1,page1);
		
		for (int i = 1; i < 10; i++) {
			String urlmore="http://www.youku.com/show_around_type_3_title_%E8%8A%B1%E7%B5%AE.html?id="+id+"&page="+i+"&dt=json&tab_num="+tab_num+"&__rt=1&__ro=around_type_3_title_%E8%8A%B1%E7%B5%AE_more"+i;
			strHtml = DownloadUtil.getHtmlText(urlBranchjson, 1000 * 30, "UTF-8", null, null);
			
			page1=youkudown(strHtml.toString(), name, urlBranch, 1,page1);
		}
		
	}
	
	
	public static int youkudown(String htmldate,String name ,String urlBranch,int DATA_TYPE,int i){
		try {
			
		
			Document doc = Jsoup.parse(htmldate);
			Elements links = doc.select("ul.v");
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
	//		int DATA_TYPE = 2;
	//		int i=1;
			for (Element link : links) {
				TEM_TVPLAY_TIDBITS tidbits= new TEM_TVPLAY_TIDBITS();
				tidbits.setTvplayName(name);
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
					System.out.println(PLAY_URL_url = link.select("li.v_title a").attr("href"));
					String strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
					if (strHtmlPLAY_URL == null || strHtmlPLAY_URL.equals("")) {
						strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
					}
					if (strHtmlPLAY_URL == null || strHtmlPLAY_URL.equals("")) {
						strHtmlPLAY_URL = DownloadUtil.getHtmlText(PLAY_URL_url, 1000 * 30, "UTF-8", null, null);
					}
					String link4=Jsoup.parse(strHtmlPLAY_URL).getElementById("link4").toString();
					System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link4, "src=&quot;", "&quot;"));
					tidbits.setPlayUrl(PLAY_URL);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				System.out.println(TITLE_NAME = link.select("li.v_title a").text());
				tidbits.setTitleName(TITLE_NAME);
				System.out.println(PLAY_AMOUNT = link.select("li.v_stat span.num").text());
				tidbits.setPlayAmount(Integer.valueOf(PLAY_AMOUNT.replace(",", "")));
				
				tidbits.setOrderNo(i);
				tidbits.setDataType(DATA_TYPE);	
				
				tidbits.setSOURCE(1);
				
				Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
				i=i+1;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}
	
	
	static String hostname = "192.168.1.18";
 	static int port = 21;
 	static String username = "shareuser";
 	static String password = "shareuser18";
	
 	/**
 	 * ftp weixin  列表微信数据
 	 * 2016年8月29日17:07:52
 	 */
	public static void leibiaoFavFTPUtil(String filename) {
//		String hostname = "192.168.1.18";
//		int port = 21;
//		String username = "shareuser";
//		String password = "shareuser18";
		String pathname = "/img/tvplay/tidbits";
//		String filename = "1.jpg";
		String originfilename = "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\huaxu\\"+filename;
		FavFTPUtil.uploadFileFromProduction(hostname, port, username, password, pathname, filename, originfilename);
		System.out.println("tidbits上传成功");
		
		
	}
	
	
	

	/**
	 * 2016年5月27日16:09:57 进行整体数据的更细
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray = OracleNetwork.selectyoukuTVplay(date_date);
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
			try {

				youkuBranch(listTemp.get(0),listTemp.get(1));
				youkuhuaxu(listTemp.get(0), listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		openordor();
//		youkuBranch("极品家丁", "http://list.youku.com/show/id_z6a4d7c5aab1d11e6b9bb.html");
//		youkuhuaxu("匹夫英雄", "http://www.youku.com/show_page/id_z1b8c5c54e79a11e2a705.html");
		
//		youkuhuaxu("极品家丁", "http://list.youku.com/show/id_z6a4d7c5aab1d11e6b9bb.html");
	}

}
