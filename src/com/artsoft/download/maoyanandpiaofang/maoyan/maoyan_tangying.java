package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_FILM_CINEMA;
import com.artsoft.bean.TEM_FILM_CINEMA_DATE;
import com.artsoft.bean.TEM_FILM_SCHEDULE;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class maoyan_tangying {
	
	
	
	
	
	
	
	
	public static void openstatic() {
		String datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"), -1).toString();
//		String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
//		openstatPaipian(urlMain, datetext);
		for (int i = -1; i > -7; i--) {
			datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"), i).toString();
			try {
				openstaticPaipianShuJuquanguo("", datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				openstaticPaipianShuJu("", datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	
	/**
	 * 2016年7月12日17:26:36
	 * @param urlMain
	 * @param datetext
	 * 进行数据的整体排片各个城市
	 */
	public static void openstaticPaipianShuJuquanguo(String urlMain, String datetext) {
		String url="http://pf.maoyan.com/company/cinema";
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
//			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

//		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
//		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.getElementById("area").select("ul.table li a.react");

		for (Element link : links) {
			String City="";
			String cityid="";
			System.out.println(City=link.text());
			System.out.println(cityid=link.attr("data-cityid"));
			String Cityutf="";
			
			try {
				Cityutf = java.net.URLEncoder.encode(City, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//http://pf.maoyan.com/show?showDate=2016-07-11&periodType=0&cityType=0&cityName=%E9%9E%8D%E5%B1%B1&showType=2
			//http://pf.maoyan.com/company/cinema?typeId=0&date=2016-07-17&webCityId=151&cityTier=0&page=1&noSum=0&cityName=%25E9%259E%258D%25E5%25B1%25B1
			//http://pf.maoyan.com/company/cinema?typeId=0&date=2016-07-14&webCityId=341&cityTier=0&page=1&noSum=0&cityName=%25E5%25A4%25A7%25E7%2590%2586
			for (int i = 1; i < 2; i++) {
				
				//http://pf.maoyan.com/company/invest?date=2016-07-18&webCityId=197&cityTier=0&page=1&cityName=%E5%AE%89%E5%BA%86
				String cityurl="http://pf.maoyan.com/company/invest?date="+datetext+"&webCityId="+cityid+"&cityTier=0&page="+i+"&noSum=0&cityName="+Cityutf+"";
				String strcityHtml = "";
				if (strcityHtml == null || strcityHtml.equals("")) {
	//				urlMain = "http://piaofang.maoyan.com/";
					strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
				}
				if (strcityHtml == null || strcityHtml.equals("")) {
					strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
				}
				
				Document docciy = Jsoup.parse(strcityHtml);
	
				Elements linkscty = docciy.getElementById("cinema-tbody").select("tr");
				for (Element element : linkscty) {
					String linkId=HtmlAnalyze.getTagText(element.toString(), "data-linkid=\"", "\"");
					TEM_FILM_CINEMA CINEMA =new TEM_FILM_CINEMA();
					CINEMA.setUrl(cityurl);
					CINEMA.setDataDate(datetext.replace("-", ""));
					CINEMA.setFid(linkId);
					String paming=element.select("td").get(0).text();
					CINEMA.setTop(paming);
					String name=element.select("td").get(1).text();
					CINEMA.setTitle(name);
					String piaofang=Stringnum(element.select("td").get(2).text());
					CINEMA.setTotalBoxoffice(piaofang);
					String renchi=Stringnum(element.select("td").get(3).text());
					CINEMA.setFieldPnum(renchi);
					System.out.println(element.select("td").get(4));
					System.out.println(element.select("td").get(4).text());
					String renjunchangchi=Stringnum(element.select("td").get(4).text());
					CINEMA.setFieldAveragePnum(renjunchangchi);
					String danzhuangpiaofang=Stringnum(element.select("td").get(5).text());
					CINEMA.setRealTimeBoxoffice(danzhuangpiaofang);
					String COLLECTION_URL="http://pf.maoyan.com/company/cinema/"+linkId;
					CINEMA.setCITY_NAME(City);
					CINEMA.setDATA_TYPE(2);
					OracleMovePiaoFang.intoTEM_FILM_CINEMA(CINEMA);
					
					if (!linkId.equals("")&&!linkId.equals("null")) {
						//http://pf.maoyan.com/company/invest/7/opdata?cityTier=0&webCityId=10&cityName=%E4%B8%8A%E6%B5%B7&dateType=0
//						String linkIdurl="http://pf.maoyan.com/company/cinema/"+linkId;
						String linkIdurl="";
						linkIdurl="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=0";
						
						String linkIdurlhtml = DownloadUtil.getHtmlText(linkIdurl, 1000 * 30, "UTF-8", null, null);
						
//						String linkIdurlhtml_zhou = DownloadUtil.getHtmlText(linkIdurl_zhou, 1000 * 30, "UTF-8", null, null);
						 linkIdurlhtml=HtmlAnalyze.getTagText(linkIdurlhtml.toString(), "tabHtml\":\"", "\"}",true,0);
						
						Document doclinkId = Jsoup.parse(linkIdurlhtml);
//						System.out.println(doclinkId.getElementById("tab-cont"));
						Elements ultheatre = doclinkId.select("ul");
						
						for (Element element2 : ultheatre) {
//							System.out.println(element2);
							if (element2.toString().contains("content")) {
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi=element2.select("strong").text();
								CINEMA_DATE.setDataDate(riqi);
								String zouqi=element2.select("span").text();
								CINEMA_DATE.setCycle(zouqi);
								String piaofang_ri=Stringnum(element2.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_ri);
								String changjuhnrenshu_ri=Stringnum(element2.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_ri);
								String danripiaofang_ri=Stringnum(element2.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_ri);
								CINEMA_DATE.setDataType(1);
								CINEMA_DATE.setCollectionUrl(linkIdurl);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
								
							}
						}
						
						//周
						//http://pf.maoyan.com/company/cinema/87/opdata?dateType=1
//						String linkIdurl_zhou="http://pf.maoyan.com/company/cinema/"+linkId+"/opdata?dateType=1";
						String linkIdurl_zhou="";
						linkIdurl_zhou="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=1";
						String linkIdurlhtml_zhou = DownloadUtil.getHtmlText(linkIdurl_zhou, 1000 * 30, "UTF-8", null, null);
						String linkIdurlhtml_zhou_html=HtmlAnalyze.getTagText(linkIdurlhtml_zhou.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId_zhou = Jsoup.parse(linkIdurlhtml_zhou_html);
						
						Elements ultheatre_zhou = doclinkId_zhou.select("ul");
						
						for (Element element2_zhou : ultheatre_zhou) {
//							System.out.println(element2_zhou);
							if (element2_zhou.toString().contains("content")) {
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi_zhou=element2_zhou.select("strong").text();
								CINEMA_DATE.setDataDate(riqi_zhou);
								String zouqi_zhou=element2_zhou.select("span").text();
								CINEMA_DATE.setDataDate(zouqi_zhou);
								String piaofang_zhou=Stringnum(element2_zhou.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_zhou);
								String changjuhnrenshu_zhou=Stringnum(element2_zhou.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_zhou);
								String danripiaofang_zhou=Stringnum(element2_zhou.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_zhou);
								CINEMA_DATE.setDataType(2);
								CINEMA_DATE.setCollectionUrl(linkIdurl_zhou);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
							}
						}
						
						//yue
						//http://pf.maoyan.com/company/cinema/87/opdata?dateType=2
//						String linkIdurl_yue="http://pf.maoyan.com/company/cinema/"+linkId+"/opdata?dateType=2";
						String linkIdurl_yue="";
						linkIdurl_yue="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=2";
						String linkIdurlhtml_yue = DownloadUtil.getHtmlText(linkIdurl_yue, 1000 * 30, "UTF-8", null, null);
						linkIdurlhtml_yue=HtmlAnalyze.getTagText(linkIdurlhtml_yue.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId_yue = Jsoup.parse(linkIdurlhtml_yue);
						
						Elements ultheatre_yue = doclinkId_yue.select("ul");
						
						for (Element element2_yue : ultheatre_yue) {
//							System.out.println(element2_yue);
							if (element2_yue.toString().contains("content")) {
//								String riqi_yue=element2_yue.select("strong").text();
//								String zouqi_yue=element2_yue.select("span").text();
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi_yu=element2_yue.select("li").get(0).text().replace("\\n", "").replace(" ", "");
								CINEMA_DATE.setDataDate(riqi_yu);
								String piaofang_yue=Stringnum(element2_yue.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_yue);
								String changjuhnrenshu_yue=Stringnum(element2_yue.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_yue);
								String danripiaofang_yue=Stringnum(element2_yue.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_yue);
								CINEMA_DATE.setDataType(3);
								CINEMA_DATE.setCollectionUrl(linkIdurl_yue);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
							}
						}
						
						
						//nian
						//http://pf.maoyan.com/company/cinema/87/opdata?dateType=3
//						String linkIdurl_nian="http://pf.maoyan.com/company/cinema/"+linkId+"/opdata?dateType=3";
						String linkIdurl_nian="";
						linkIdurl_nian="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=3";
						String linkIdurlhtml_nian = DownloadUtil.getHtmlText(linkIdurl_nian, 1000 * 30, "UTF-8", null, null);
						linkIdurlhtml_nian=HtmlAnalyze.getTagText(linkIdurlhtml_nian.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId_nian = Jsoup.parse(linkIdurlhtml_nian);
						
						Elements ultheatre_nian = doclinkId_nian.select("ul");
						
						for (Element element2_nian : ultheatre_nian) {
//							System.out.println(element2_nian);
							if (element2_nian.toString().contains("content")) {
//								String riqi_yue=element2_yue.select("strong").text();
//								String zouqi_yue=element2_yue.select("span").text();
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi_nian=element2_nian.select("li").get(0).text().replace("\\n", "").replace(" ", "");
								CINEMA_DATE.setDataDate(riqi_nian);
								String piaofang_nian=Stringnum(element2_nian.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_nian);
								String changjuhnrenshu_nian=Stringnum(element2_nian.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_nian);
								String danripiaofang_nian=Stringnum(element2_nian.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_nian);
								CINEMA_DATE.setDataType(4);
								CINEMA_DATE.setCollectionUrl(linkIdurl_nian);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
							}
						}
					}
					
					
					
				}
			}
			
//			playPlan_table
			
			
		}

	}
	
	
	
	
	/**
	 * 2016年7月12日17:26:36
	 * @param urlMain
	 * @param datetext
	 * 进行数据的整体排片各个城市
	 */
	public static void openstaticPaipianShuJu(String urlMain, String datetext) {
		String url="http://pf.maoyan.com/company/invest";
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
//			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

//		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
//		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.select("div.abc ul.table li a.react");

		for (Element link : links) {
			String City="";
			String cityid="";
			System.out.println(City=link.text());
			System.out.println(cityid=link.attr("data-cityid"));
			String Cityutf="";
			
			try {
				Cityutf = java.net.URLEncoder.encode(City, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//http://pf.maoyan.com/show?showDate=2016-07-11&periodType=0&cityType=0&cityName=%E9%9E%8D%E5%B1%B1&showType=2
			//http://pf.maoyan.com/company/cinema?typeId=0&date=2016-07-17&webCityId=151&cityTier=0&page=1&noSum=0&cityName=%25E9%259E%258D%25E5%25B1%25B1
			//http://pf.maoyan.com/company/cinema?typeId=0&date=2016-07-14&webCityId=341&cityTier=0&page=1&noSum=0&cityName=%25E5%25A4%25A7%25E7%2590%2586
			for (int i = 1; i < 2; i++) {
				
				//http://pf.maoyan.com/company/invest?date=2016-07-18&webCityId=197&cityTier=0&page=1&cityName=%E5%AE%89%E5%BA%86
				String cityurl="http://pf.maoyan.com/company/invest?date="+datetext+"&webCityId="+cityid+"&cityTier=0&page="+i+"&noSum=0&cityName="+Cityutf+"";
				String strcityHtml = "";
				if (strcityHtml == null || strcityHtml.equals("")) {
	//				urlMain = "http://piaofang.maoyan.com/";
					strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
				}
				if (strcityHtml == null || strcityHtml.equals("")) {
					strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
				}
				
				Document docciy = Jsoup.parse(strcityHtml);
	
				Elements linkscty = docciy.getElementById("cinema-tbody").select("tr");
				for (Element element : linkscty) {
					String linkId=HtmlAnalyze.getTagText(element.toString(), "data-linkid=\"", "\"");
					TEM_FILM_CINEMA CINEMA =new TEM_FILM_CINEMA();
					CINEMA.setUrl(cityurl);
					CINEMA.setDataDate(datetext.replace("-", ""));
					CINEMA.setFid(linkId);
					String paming=element.select("td").get(0).text();
					CINEMA.setTop(paming);
					String name=element.select("td").get(1).text();
					CINEMA.setTitle(name);
					String piaofang=Stringnum(element.select("td").get(2).text());
					CINEMA.setTotalBoxoffice(piaofang);
					String renchi=Stringnum(element.select("td").get(3).text());
					CINEMA.setFieldPnum(renchi);
					String renjunchangchi=Stringnum(element.select("td").get(4).text());
					CINEMA.setFieldAveragePnum(renjunchangchi);
					String danzhuangpiaofang=Stringnum(element.select("td").get(5).text());
					CINEMA.setRealTimeBoxoffice(danzhuangpiaofang);
					String COLLECTION_URL="http://pf.maoyan.com/company/cinema/"+linkId;
					CINEMA.setCITY_NAME(City);
					CINEMA.setDATA_TYPE(2);
					OracleMovePiaoFang.intoTEM_FILM_CINEMA(CINEMA);
					
					if (!linkId.equals("")&&!linkId.equals("null")) {
						//http://pf.maoyan.com/company/invest/7/opdata?cityTier=0&webCityId=10&cityName=%E4%B8%8A%E6%B5%B7&dateType=0
//						String linkIdurl="http://pf.maoyan.com/company/cinema/"+linkId;
						String linkIdurl="";
						linkIdurl="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=0";
						String linkIdurlurl = DownloadUtil.getHtmlText(linkIdurl, 1000 * 30, "UTF-8", null, null);
//						String linkIdurlhtml_zhou = DownloadUtil.getHtmlText(linkIdurl_zhou, 1000 * 30, "UTF-8", null, null);
						String linkIdurlhtml=HtmlAnalyze.getTagText(linkIdurlurl.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId = Jsoup.parse(linkIdurlhtml);
//						System.out.println(doclinkId.getElementById("tab-cont"));
						Elements ultheatre = doclinkId.select("ul");
						
						for (Element element2 : ultheatre) {
//							System.out.println(element2);
							if (element2.toString().contains("content")) {
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi=element2.select("strong").text();
								CINEMA_DATE.setDataDate(riqi);
								String zouqi=element2.select("span").text();
								CINEMA_DATE.setCycle(zouqi);
								String piaofang_ri=Stringnum(element2.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_ri);
								String changjuhnrenshu_ri=Stringnum(element2.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_ri);
								String danripiaofang_ri=Stringnum(element2.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_ri);
								CINEMA_DATE.setDataType(1);
								CINEMA_DATE.setCollectionUrl(linkIdurl);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
								
							}
						}
						
						//周
						//http://pf.maoyan.com/company/cinema/87/opdata?dateType=1
//						String linkIdurl_zhou="http://pf.maoyan.com/company/cinema/"+linkId+"/opdata?dateType=1";
						String linkIdurl_zhou="";
						linkIdurl_zhou="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=1";
						String linkIdurlhtml_zhou = DownloadUtil.getHtmlText(linkIdurl_zhou, 1000 * 30, "UTF-8", null, null);
						String linkIdurlhtml_zhou_html=HtmlAnalyze.getTagText(linkIdurlhtml_zhou.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId_zhou = Jsoup.parse(linkIdurlhtml_zhou_html);
						
						Elements ultheatre_zhou = doclinkId_zhou.select("ul");
						
						for (Element element2_zhou : ultheatre_zhou) {
//							System.out.println(element2_zhou);
							if (element2_zhou.toString().contains("content")) {
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi_zhou=element2_zhou.select("strong").text();
								CINEMA_DATE.setDataDate(riqi_zhou);
								String zouqi_zhou=element2_zhou.select("span").text();
								CINEMA_DATE.setDataDate(zouqi_zhou);
								String piaofang_zhou=Stringnum(element2_zhou.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_zhou);
								String changjuhnrenshu_zhou=Stringnum(element2_zhou.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_zhou);
								String danripiaofang_zhou=Stringnum(element2_zhou.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_zhou);
								CINEMA_DATE.setDataType(2);
								CINEMA_DATE.setCollectionUrl(linkIdurl_zhou);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
							}
						}
						
						//yue
						//http://pf.maoyan.com/company/cinema/87/opdata?dateType=2
//						String linkIdurl_yue="http://pf.maoyan.com/company/cinema/"+linkId+"/opdata?dateType=2";
						String linkIdurl_yue="";
						linkIdurl_yue="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=2";
						String linkIdurlhtml_yue = DownloadUtil.getHtmlText(linkIdurl_yue, 1000 * 30, "UTF-8", null, null);
						linkIdurlhtml_yue=HtmlAnalyze.getTagText(linkIdurlhtml_yue.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId_yue = Jsoup.parse(linkIdurlhtml_yue);
						
						Elements ultheatre_yue = doclinkId_yue.select("ul");
						
						for (Element element2_yue : ultheatre_yue) {
//							System.out.println(element2_yue);
							if (element2_yue.toString().contains("content")) {
//								String riqi_yue=element2_yue.select("strong").text();
//								String zouqi_yue=element2_yue.select("span").text();
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi_yu=element2_yue.select("li").get(0).text().replace("\\n", "").replace(" ", "");
								CINEMA_DATE.setDataDate(riqi_yu);
								String piaofang_yue=Stringnum(element2_yue.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_yue);
								String changjuhnrenshu_yue=Stringnum(element2_yue.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_yue);
								String danripiaofang_yue=Stringnum(element2_yue.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_yue);
								CINEMA_DATE.setDataType(3);
								CINEMA_DATE.setCollectionUrl(linkIdurl_yue);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
							}
						}
						
						
						//nian
						//http://pf.maoyan.com/company/cinema/87/opdata?dateType=3
//						String linkIdurl_nian="http://pf.maoyan.com/company/cinema/"+linkId+"/opdata?dateType=3";
						String linkIdurl_nian="";
						linkIdurl_nian="http://pf.maoyan.com/company/invest/"+linkId+"/opdata?cityTier=0&webCityId="+cityid+"&cityName="+Cityutf+"&dateType=3";
						String linkIdurlhtml_nian = DownloadUtil.getHtmlText(linkIdurl_nian, 1000 * 30, "UTF-8", null, null);
						linkIdurlhtml_nian=HtmlAnalyze.getTagText(linkIdurlhtml_nian.toString(), "tabHtml\":\"", "\"}",true,0);
						Document doclinkId_nian = Jsoup.parse(linkIdurlhtml_nian);
						
						Elements ultheatre_nian = doclinkId_nian.select("ul");
						
						for (Element element2_nian : ultheatre_nian) {
//							System.out.println(element2_nian);
							if (element2_nian.toString().contains("content")) {
//								String riqi_yue=element2_yue.select("strong").text();
//								String zouqi_yue=element2_yue.select("span").text();
								TEM_FILM_CINEMA_DATE CINEMA_DATE =new TEM_FILM_CINEMA_DATE(); 
								CINEMA_DATE.setTitle(name);
								CINEMA_DATE.setUrl(linkIdurl);
								String riqi_nian=element2_nian.select("li").get(0).text().replace("\\n", "").replace(" ", "");
								CINEMA_DATE.setDataDate(riqi_nian);
								String piaofang_nian=Stringnum(element2_nian.select("li").get(1).text());
								CINEMA_DATE.setTotalBoxoffice(piaofang_nian);
								String changjuhnrenshu_nian=Stringnum(element2_nian.select("li").get(2).text());
								CINEMA_DATE.setFieldAveragePnum(changjuhnrenshu_nian);
								String danripiaofang_nian=Stringnum(element2_nian.select("li").get(3).text());
								CINEMA_DATE.setRealTimeBoxoffice(danripiaofang_nian);
								CINEMA_DATE.setDataType(4);
								CINEMA_DATE.setCollectionUrl(linkIdurl_nian);
								CINEMA_DATE.setFid(linkId);
								CINEMA_DATE.setCITY_NAME(City);
								CINEMA_DATE.setTYPE(2);
								OracleMovePiaoFang.intoTEM_FILM_CINEMA_DATE(CINEMA_DATE);
							}
						}
					}
					
					
					
				}
			}
			
//			playPlan_table
			
			
		}

	}
	
	
	public static String  Stringnum( String numString){
		int numIn=0;
		if (numString==null||numString.equals("")) {
			numString="-1";
			numIn=0;
		}
		if (numString.contains("亿")) {
			numIn=(int) ((Double.parseDouble(numString.replace("亿", "")))*100000000);
		}else{
			
		
			if (numString.contains("万")) {
				numIn=(int) (Double.parseDouble(numString.replace("万", ""))*10000);
			}else{
				try {
					numIn=(int) Double.parseDouble(numString);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
//				numIn
			}
		}
		
		
		return ""+numIn;
		
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}
	public static void main(String[] args) {
//		TimingTime(1, 59, 59);
//		openstaticPaipianShuJuquanguo("", "2016-07-11");
		openstatic();
	//	openstatic();
//		runstatic();
	}
	

}
