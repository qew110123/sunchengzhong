package com.artsoft.download.BaiDu;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_DIM_FILM;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;



/**
 * 电影数据只要是带电影数据的就进行详细数据的采集
 * 2016年6月7日11:29:27
 * @author Administrator
 *
 */

public class BaiDuMovesDownload {
	
	
	public static TEM_DIM_FILM mainmore(String strId, String url, String strUrlname) {
		TEM_DIM_FILM movesfilm = new TEM_DIM_FILM();
		// TODO Auto-generated method stub
		movesfilm.setFilmId(strId);
		movesfilm.setFilmUrl(url);
//		movesfilm.setFilmName(strUrlname);
		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		if (strHtml==null) {
			return movesfilm;
		}	
		try {
			
		

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.basic-info");
		for (Element link : links) {
			String[] sourceStrArray = link.toString().split("d>");

			for (int i = 0; i < sourceStrArray.length; i++) {
				String baseInfoName = "";
				String baseInfoValue = "";
				baseInfoName = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item name\">", "</");
				baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item value\">", "</d");

				if (baseInfoName != null && baseInfoValue != null) {
					baseInfoName = baseInfoName.replace("&nbsp;", "");
					System.out.println(baseInfoName);
					
					if (contains(baseInfoName)) {
						Document doclite = Jsoup.parse(sourceStrArray[i].toString());
						Elements linkss = doclite.select("a");
						// System.out.println(baseInfoValue.replace("&nbsp;",
						// ""));##展开##
						// if (linkss.size()==0) {
						//
						// }
						// System.out.println(linkss);
						// System.out.println(linkss.size()==0);
						if (linkss.size() == 0) {
							// baseInfoValue = baseInfoValue.replace(" ",
							// "").replace("\n", "").replace("展开", "");
							/**
							 * 2016年4月21日11:26:14 因发现没有链接时出现 空格 顿号则进行数据的整体up
							 */
							baseInfoValue = baseInfoValue.replace("展开", "");
							System.out.println(baseInfoValue);
						} else {
							baseInfoValue = "";
							for (Element element : linkss) {
								// System.out.println(linkss.attr("href"));
								// System.out.println(linkss.text());
								if (!element.attr("class").equals("sup-anchor")) {
									baseInfoValue = baseInfoValue + element.text() + "|http://baike.baidu.com"
											+ element.attr("href") + ",";
								}

							}
							
							
							System.out.println(baseInfoValue);
						}
					} else {
						/**
						 * 2016年4月21日14:03:39
						 * 进行制片人单独拿出，进行数据的替换
						 */
						if (baseInfoName.equals("制片人")) {
							
//							baseInfoValue = baseInfoValue.replace("展开", "");
							System.out.println(baseInfoValue);
							
						}else{
							baseInfoValue = baseInfoValue.replace(" ", "").replace("\n", "").replace("展开", "");
							System.out.println(baseInfoValue);
						}
					}
					
					
					

				}
				movesfilm = buildTvPlay(baseInfoName, baseInfoValue, movesfilm);

			}
			String stills_url = "";
			Elements linkstills_url = doc.select("div.summary-pic img");
			// stills_url=HtmlAnalyze.getTagText(strHtml.toString(), "
			// target=\"_blank\">\n<img src=\"", "\"");
			System.out.println(stills_url = HtmlAnalyze.getTagText(linkstills_url.toString(), "src=\"", "\""));
			if (stills_url == null || stills_url.equals(null)) {
				stills_url = "";
			}
			movesfilm.setFilmImgUrl(stills_url);

			Elements linksss = doc.select("div.lemma-summary div.para");
			String information = "";
			int u = 0;
			for (Element element : linksss) {
				// System.out.println("111"+element);
				if (u == 0) {
					information = element.text();
					u += 1;
				} else {
					information = information + "||" + element.text();
				}
			}
			if (information != null && !"".equals(information)) {
				movesfilm.setDescription(information);
			}

			// tvplay.setBasic_info("");
//			movesfilm.print();
			// OracleHaoSou.InsertTVplay(tvplay);

		}
//		try {
//
//			// 进行数据的另一个表数据 的添加
//			Elements linkli = doc.select("li.roleIntroduction-item");
//			String personname = "";
//			String personurl = "";
//			String rolename = "";
//			String personstillsurl = "";
//			String dubbingname = "";
//			String dubbingurl = "";
//			String roleintro = "";
//			String PERSON_BIG_URL="";
//			String PERSON_BIG_URLall="";
//			for (Element elementli : linkli) {
//				System.out.println(personstillsurl = HtmlAnalyze.getTagText(elementli.toString(), "<img src=\"", "\""));
//				
//				System.out.println(PERSON_BIG_URLall = elementli.select("a.roleIntrodcution-picture").attr("href"));
//				System.out.println(
//						rolename = HtmlAnalyze.getTagText(elementli.toString(), "class=\"item-value\">", "</span>"));
//				System.out.println(personname = elementli.select("div.role-actor span.item-value").text());
//				System.out.println(personurl = elementli.select("div.role-actor span.item-value a ").attr("href"));
//				// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-actor
//				// span.item-value").toString(), "href=\"", "\""));
//				System.out.println(dubbingname = elementli.select("div.role-voice span.item-value").text());
//				System.out.println(dubbingurl = elementli.select("div.role-voice span.item-value a").attr("href"));
//				// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-voice
//				// span.item-value").toString(), "href=\"", "\""));
//				
//				
//				System.out.println(
//						roleintro = HtmlAnalyze.getTagText(elementli.toString(), "role-description\">", "</dd>"));
//				if (personurl != "") {
//					personurl = "http://baike.baidu.com" + personurl;
//				}
//				if (dubbingurl != "") {
//					dubbingurl = "http://baike.baidu.com" + dubbingurl;
//				}
//				
//				if (PERSON_BIG_URLall != "") {
//					PERSON_BIG_URLall = "http://baike.baidu.com" + PERSON_BIG_URLall;
//					String strHtmlPERSON_BIG_URLall = DownloadUtil.getHtmlText(PERSON_BIG_URLall, 1000 * 30, "UTF-8", null, null);
//					Document docPERSON_BIG_URLall = Jsoup.parse(strHtmlPERSON_BIG_URLall);
//					PERSON_BIG_URL=docPERSON_BIG_URLall.getElementById("imgPicture").attr("src");
//				}
//				
//				OracleHaoSou.intotemtvplay(strId, strUrlname, url, "", personname, personurl, rolename, personstillsurl,
//						dubbingname, dubbingurl, roleintro,PERSON_BIG_URL);
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return movesfilm;

	}
	
	
	
	/**
	 * 进行判断是否有我需要的值
	 * 
	 * @param baseInfoName
	 * @param baseInfoValue
	 * @param tvplay
	 * @return
	 */
	public static TEM_DIM_FILM buildTvPlay(String baseInfoName, String baseInfoValue, TEM_DIM_FILM movesfilm) {
		if (baseInfoValue != null && !baseInfoValue.equals("")) {
			baseInfoValue = baseInfoValue.replace("	", "");
		}
		//
		if ("中文名".equals(baseInfoName)) {
//			movesfilm.setAliasName(baseInfoValue);
			movesfilm.setFilmName(baseInfoValue);
		}
		if ("外文名".equals(baseInfoName)) {
			movesfilm.setEnglishName(baseInfoValue);
		}
		if ("别名".equals(baseInfoName) || "其它译名".equals(baseInfoName)) {
			movesfilm.setAliasName(baseInfoValue);
		}
		if ("出品时间".equals(baseInfoName)) {
			movesfilm.setProduceDate(baseInfoValue);
		}
		if ("出品公司".equals(baseInfoName)) {
			movesfilm.setProduceCompany(baseInfoValue);
		}
		if ("制片地区".equals(baseInfoName)) {
			movesfilm.setProduceArea(baseInfoValue);
		}
		if ("拍摄地点".equals(baseInfoName)) {
			movesfilm.setProduceArea(baseInfoValue);
		}
		if ("发行公司".equals(baseInfoName)) {
			movesfilm.setIssueOrganization(baseInfoValue);
		}
		if ("首播时间".equals(baseInfoName)) {
			movesfilm.setPremiereDate(baseInfoValue);
		}
		
		if ("导演".equals(baseInfoName)) {
			movesfilm.setDirector(baseInfoValue);
		}
		if ("编剧".equals(baseInfoName)) {
			movesfilm.setScreenwriter(baseInfoValue);
		}
		if ("主演".equals(baseInfoName)) {
			movesfilm.setActors(baseInfoValue);
		}
//		if ("集数".equals(baseInfoName)) {
//			tvplay.setPages(baseInfoValue);
//		}
//		if ("每集长度".equals(baseInfoName)) {
//			tvplay.setTime_length(baseInfoValue);
//		}
//		if ("类型".equals(baseInfoName)) {
//			tvplay.setSubject(baseInfoValue);
//		}
		if ("制片人".equals(baseInfoName)) {
			movesfilm.setProducer(baseInfoValue);
		}
		if ("在线播放平台".equals(baseInfoName)) {
			movesfilm.setPlayPlatform(baseInfoValue);
		}
		if ("开机时间".equals(baseInfoName)) {
			movesfilm.setShootingDate(baseInfoValue);
		}
//		if ("杀青时间".equals(baseInfoName) || "关机时间".equals(baseInfoName)) {
//			tvplay.setClose_time(baseInfoValue);
//		}
		if ("首播平台".equals(baseInfoName)) {
//			tvplay.setPremiere_platform(baseInfoValue);
			movesfilm.setPlayPlatform(baseInfoValue);
		}
//		if ("首播平台".equals(baseInfoName)) {
//			tvplay.setPremiere_platform(baseInfoValue);
//		}
//		if ("接档".equals(baseInfoName)) {
//			tvplay.setBefore_eleplay(baseInfoValue);
//		}
//		if ("被接档".equals(baseInfoName)) {
//			tvplay.setNext_teleplay(baseInfoValue);
//		}
		if ("主要奖项".equals(baseInfoName)) {
			movesfilm.setAward(baseInfoValue);
		}

		if ("上映日期".equals(baseInfoName) || "上映时间".equals(baseInfoName)) {
			movesfilm.setShowDate(baseInfoValue);
		}
		
		if ("制作年代".equals(baseInfoName)) {
			movesfilm.setYears(baseInfoValue);
		}
		
		if ("制作成本".equals(baseInfoName)) {
			movesfilm.setProduceCost(baseInfoValue);
		}
		
		if ("出品日期".equals(baseInfoName)) {
			movesfilm.setProduceDate(baseInfoValue);
		}
		
		if ("上映日期".equals(baseInfoName)) {
			movesfilm.setShowDate(baseInfoValue);
		}
		if ("表现形式".equals(baseInfoName)) {
			movesfilm.setPerformanceForm(baseInfoValue);
		}
		
		if ("时代背景".equals(baseInfoName)) {
			movesfilm.setHistoricalBackground(baseInfoValue);
		}
		if ("发行机构".equals(baseInfoName)) {
			movesfilm.setIssueOrganization(baseInfoValue);
		}
		
		if ("类型".equals(baseInfoName)) {
			movesfilm.setSubjectNameOne(baseInfoValue);
		}
		
		if ("批准证号".equals(baseInfoName)) {
			movesfilm.setIssuingLicense(baseInfoValue);
		}
		
		if ("获奖记录".equals(baseInfoName)) {
			movesfilm.setAward(baseInfoValue);
		}
		if ("拍摄地".equals(baseInfoName)) {
			movesfilm.setShootingLocation(baseInfoValue);
		}
		
		if ("拍摄日期".equals(baseInfoName)) {
			movesfilm.setShootingDate(baseInfoValue);
		}
		
		if ("片长".equals(baseInfoName)) {
			movesfilm.setTimeLong(baseInfoValue);
		}
		
		if ("语言".equals(baseInfoName)) {
			movesfilm.setLanguages(baseInfoValue);
		}
		
		if ("imdb编码".equals(baseInfoName)) {
			movesfilm.setImdbCode(baseInfoValue);
		}
		
		if ("原著".equals(baseInfoName)) {
			movesfilm.setOriginal(baseInfoValue);
		}
		
		return movesfilm;
	}
	
	
	
	/**
	 * 判断数据是否出现要分开
	 * 
	 * @param strtext
	 * @return
	 */
	public static boolean contains(String strtext) {
		List<String> list = new ArrayList<String>();
		list.add("主演"); // 向列表中添加数据
		list.add("编剧"); // 向列表中添加数据
		list.add("导演"); // 向列表中添加数据
//		list.add("制片人");
		if (list.contains(strtext)) {
			return true;
		}
		return false;

	}
	
	
	
	
	
	
	/**
	 * 进行数据的搜索
	 * 2016年6月3日16:02:59
	 * @param urlmain
	 * @param id
	 * @param strname
	 */
	public static void mainUrlall(String urlmain, String id, String strname) {
		// TODO Auto-generated method stub
		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		try {
			
		
		Document doc = Jsoup.parse(strHtml);
		
		
		
//		// Element linkmain = doc.getElementById("fluxes_static");
//		Elements links = doc.select("a.result-title");
//		// Element content = doc.getElementById("content");
//		// Elements links = content.getElementsByTag("a");
//		System.out.println(links.size());
//		 for (Element link : links) {
//			 String idnum = "";
//			 String strVolumes = "";
//			 System.out.println(strVolumes = link.text());
//			 System.out.println(idnum = link.attr("href"));
//			 
//			 
//		 }
		
		
//		if (links.size() > 0) {
//			String strUrl = "";
//			String strUrlname = "";
//			System.out.println(strUrlname = links.first().text());
//			System.out.println(strUrl = links.attr("href"));
//			// mainmore(id, strUrl,strUrlname);
//			if (strUrl != null && !"".equals(strUrl)) {
//
//				if (!strUrl.contains("http://baike.baidu.com")) {
//					strUrl = "http://baike.baidu.com" + strUrl;
//				}
//				System.out.println(strUrl);
//				// mainmore(id, strUrl);
//				TEM_DIM_FILM movesfilm = mainmore(id, strUrl, strname);
//				movesfilm.setBaikefilmname(strUrlname);
//				
//				OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// 添加操作
//				
//				// OracleHaoSou.UpdateTVplay(tvplay);//修改操作
//			}
//		}
		
		Elements links = doc.select("dl.search-list dd");
		for (Element element : links) {
			String strUrl = "";
			String strUrlname = "";
			String strallname="";
			System.out.println(strUrlname = element.select("a.result-title").first().text());
			System.out.println(strallname = element.select("p.result-summary").first().text());
			System.out.println(strUrl = element.select("a.result-title").first().attr("href"));
//			if (strUrlname.contains("电影")||strallname.contains("电影")) {
				if (strUrl!=null) {
					System.out.println("成功");
					TEM_DIM_FILM movesfilm = mainmore(id, strUrl, strname);
					movesfilm.setBaikefilmname(strUrlname);
					
					OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// 添加操作
					
				}
//			}
			
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
//	public static void runnewMain(){
//		List<String> listArray = OracleBaidu.selectmove();
//		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0)) && listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
//				String urlBranch = "";
//				try {
//					urlBranch = "http://baike.baidu.com/search?word="
//							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
//					mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
//					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
//					
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TEM_DIM_FILM movesfilm =mainmore("0", "http://baike.baidu.com/link?url=lvbgoPAi-XZIClr2KlJVootafA3egGcYQIDof3AnAoM1SoPWXIxJ5UnTV48rNdSAVVm4VxAY_QTmTIU7Mzy2LrOT9jclkmMwoLtIgS_81FS", "功夫熊猫");
//		OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);
//		runnewMain();
		
	}
	
	

}
