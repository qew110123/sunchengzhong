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
 * ��Ӱ����ֻҪ�Ǵ���Ӱ���ݵľͽ�����ϸ���ݵĲɼ�
 * 2016��6��7��11:29:27
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
						// ""));##չ��##
						// if (linkss.size()==0) {
						//
						// }
						// System.out.println(linkss);
						// System.out.println(linkss.size()==0);
						if (linkss.size() == 0) {
							// baseInfoValue = baseInfoValue.replace(" ",
							// "").replace("\n", "").replace("չ��", "");
							/**
							 * 2016��4��21��11:26:14 ����û������ʱ���� �ո� �ٺ���������ݵ�����up
							 */
							baseInfoValue = baseInfoValue.replace("չ��", "");
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
						 * 2016��4��21��14:03:39
						 * ������Ƭ�˵����ó����������ݵ��滻
						 */
						if (baseInfoName.equals("��Ƭ��")) {
							
//							baseInfoValue = baseInfoValue.replace("չ��", "");
							System.out.println(baseInfoValue);
							
						}else{
							baseInfoValue = baseInfoValue.replace(" ", "").replace("\n", "").replace("չ��", "");
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
//			// �������ݵ���һ�������� ������
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
	 * �����ж��Ƿ�������Ҫ��ֵ
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
		if ("������".equals(baseInfoName)) {
//			movesfilm.setAliasName(baseInfoValue);
			movesfilm.setFilmName(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			movesfilm.setEnglishName(baseInfoValue);
		}
		if ("����".equals(baseInfoName) || "��������".equals(baseInfoName)) {
			movesfilm.setAliasName(baseInfoValue);
		}
		if ("��Ʒʱ��".equals(baseInfoName)) {
			movesfilm.setProduceDate(baseInfoValue);
		}
		if ("��Ʒ��˾".equals(baseInfoName)) {
			movesfilm.setProduceCompany(baseInfoValue);
		}
		if ("��Ƭ����".equals(baseInfoName)) {
			movesfilm.setProduceArea(baseInfoValue);
		}
		if ("����ص�".equals(baseInfoName)) {
			movesfilm.setProduceArea(baseInfoValue);
		}
		if ("���й�˾".equals(baseInfoName)) {
			movesfilm.setIssueOrganization(baseInfoValue);
		}
		if ("�ײ�ʱ��".equals(baseInfoName)) {
			movesfilm.setPremiereDate(baseInfoValue);
		}
		
		if ("����".equals(baseInfoName)) {
			movesfilm.setDirector(baseInfoValue);
		}
		if ("���".equals(baseInfoName)) {
			movesfilm.setScreenwriter(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			movesfilm.setActors(baseInfoValue);
		}
//		if ("����".equals(baseInfoName)) {
//			tvplay.setPages(baseInfoValue);
//		}
//		if ("ÿ������".equals(baseInfoName)) {
//			tvplay.setTime_length(baseInfoValue);
//		}
//		if ("����".equals(baseInfoName)) {
//			tvplay.setSubject(baseInfoValue);
//		}
		if ("��Ƭ��".equals(baseInfoName)) {
			movesfilm.setProducer(baseInfoValue);
		}
		if ("���߲���ƽ̨".equals(baseInfoName)) {
			movesfilm.setPlayPlatform(baseInfoValue);
		}
		if ("����ʱ��".equals(baseInfoName)) {
			movesfilm.setShootingDate(baseInfoValue);
		}
//		if ("ɱ��ʱ��".equals(baseInfoName) || "�ػ�ʱ��".equals(baseInfoName)) {
//			tvplay.setClose_time(baseInfoValue);
//		}
		if ("�ײ�ƽ̨".equals(baseInfoName)) {
//			tvplay.setPremiere_platform(baseInfoValue);
			movesfilm.setPlayPlatform(baseInfoValue);
		}
//		if ("�ײ�ƽ̨".equals(baseInfoName)) {
//			tvplay.setPremiere_platform(baseInfoValue);
//		}
//		if ("�ӵ�".equals(baseInfoName)) {
//			tvplay.setBefore_eleplay(baseInfoValue);
//		}
//		if ("���ӵ�".equals(baseInfoName)) {
//			tvplay.setNext_teleplay(baseInfoValue);
//		}
		if ("��Ҫ����".equals(baseInfoName)) {
			movesfilm.setAward(baseInfoValue);
		}

		if ("��ӳ����".equals(baseInfoName) || "��ӳʱ��".equals(baseInfoName)) {
			movesfilm.setShowDate(baseInfoValue);
		}
		
		if ("�������".equals(baseInfoName)) {
			movesfilm.setYears(baseInfoValue);
		}
		
		if ("�����ɱ�".equals(baseInfoName)) {
			movesfilm.setProduceCost(baseInfoValue);
		}
		
		if ("��Ʒ����".equals(baseInfoName)) {
			movesfilm.setProduceDate(baseInfoValue);
		}
		
		if ("��ӳ����".equals(baseInfoName)) {
			movesfilm.setShowDate(baseInfoValue);
		}
		if ("������ʽ".equals(baseInfoName)) {
			movesfilm.setPerformanceForm(baseInfoValue);
		}
		
		if ("ʱ������".equals(baseInfoName)) {
			movesfilm.setHistoricalBackground(baseInfoValue);
		}
		if ("���л���".equals(baseInfoName)) {
			movesfilm.setIssueOrganization(baseInfoValue);
		}
		
		if ("����".equals(baseInfoName)) {
			movesfilm.setSubjectNameOne(baseInfoValue);
		}
		
		if ("��׼֤��".equals(baseInfoName)) {
			movesfilm.setIssuingLicense(baseInfoValue);
		}
		
		if ("�񽱼�¼".equals(baseInfoName)) {
			movesfilm.setAward(baseInfoValue);
		}
		if ("�����".equals(baseInfoName)) {
			movesfilm.setShootingLocation(baseInfoValue);
		}
		
		if ("��������".equals(baseInfoName)) {
			movesfilm.setShootingDate(baseInfoValue);
		}
		
		if ("Ƭ��".equals(baseInfoName)) {
			movesfilm.setTimeLong(baseInfoValue);
		}
		
		if ("����".equals(baseInfoName)) {
			movesfilm.setLanguages(baseInfoValue);
		}
		
		if ("imdb����".equals(baseInfoName)) {
			movesfilm.setImdbCode(baseInfoValue);
		}
		
		if ("ԭ��".equals(baseInfoName)) {
			movesfilm.setOriginal(baseInfoValue);
		}
		
		return movesfilm;
	}
	
	
	
	/**
	 * �ж������Ƿ����Ҫ�ֿ�
	 * 
	 * @param strtext
	 * @return
	 */
	public static boolean contains(String strtext) {
		List<String> list = new ArrayList<String>();
		list.add("����"); // ���б�����������
		list.add("���"); // ���б�����������
		list.add("����"); // ���б�����������
//		list.add("��Ƭ��");
		if (list.contains(strtext)) {
			return true;
		}
		return false;

	}
	
	
	
	
	
	
	/**
	 * �������ݵ�����
	 * 2016��6��3��16:02:59
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
//				OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// ���Ӳ���
//				
//				// OracleHaoSou.UpdateTVplay(tvplay);//�޸Ĳ���
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
//			if (strUrlname.contains("��Ӱ")||strallname.contains("��Ӱ")) {
				if (strUrl!=null) {
					System.out.println("�ɹ�");
					TEM_DIM_FILM movesfilm = mainmore(id, strUrl, strname);
					movesfilm.setBaikefilmname(strUrlname);
					
					OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// ���Ӳ���
					
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
//		TEM_DIM_FILM movesfilm =mainmore("0", "http://baike.baidu.com/link?url=lvbgoPAi-XZIClr2KlJVootafA3egGcYQIDof3AnAoM1SoPWXIxJ5UnTV48rNdSAVVm4VxAY_QTmTIU7Mzy2LrOT9jclkmMwoLtIgS_81FS", "������è");
//		OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);
//		runnewMain();
		
	}
	
	

}