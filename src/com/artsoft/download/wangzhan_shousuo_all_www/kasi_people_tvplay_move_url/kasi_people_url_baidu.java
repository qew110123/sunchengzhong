package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_tvplay_move_url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_PERSON_URL_WORKS;
import com.artsoft.bean.tem_person_works;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class kasi_people_url_baidu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id="1892";
		String name="张丹露";
		String url= "http://www.baidu.com/link?url=EzF9_o1jh3Qg4urCp1SNkn0-fCFtCNXt5MHOlaXAcNueqBhc3qERn_MxGdW3a3zIJhiCBzp2l3YDwLUarvz7LI1NGqDfFI0I7f-yjXf45B32O-tfJ-XQtry2MEMW1tQm";
		
		mainmore(id,name, url);

	}
	
	
	public static void mainmore(String id,String name, String url) {
		
		
		
		String strHtml = "";
		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		Document doc = Jsoup.parse(strHtml);
		
		
		// 电视剧
		tvplay(id,name, url, strHtml, doc);
		// 电影
		moves(id,name, url, strHtml, doc);
	}
	
	
	
	
	/**
	 * 参演电视剧 2016年5月10日14:18:20
	 * 
	 * @param strId
	 * @param url
	 */
	public static void tvplay(String strId,String name, String url, String strHtml, Document doc) {
//		tem_person_works personwork = new tem_person_works();
		TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//		personwork.setPersonId(Integer.parseInt(strId));
		work.setPersonId(strId);
//		personwork.setPersonUrl(url);
		work.setPersonName(name);
		work.setPersonUrl(url);
		// String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
		// null);
		// Document doc = Jsoup.parse(strHtml);
		
//		CommonUtil.setLog(strHtml);

		/**
		 * 参演电视剧 2016年5月6日16:53:08
		 *
		 */

		String tvplay = HtmlAnalyze.getTagText(strHtml, "</span>参演电视剧</h3>", "<div class=\"pageBtns\">", true, 0);
		// System.out.println(moves);
		
		if (tvplay.equals("")) {
			if (strHtml.contains("</span>参演电视剧</h3>")) {
				tvplay = HtmlAnalyze.getTagText(strHtml, "</span>参演电视剧</h3>", "</li>\r\n</ul>\r\n</ul>\r\n</div>\r\n</div>", true, 0);
			}
		}
		

		Document doctvplay = Jsoup.parse(tvplay);
		Elements linksdoctvplay = doctvplay.select("div.starMovieAndTvplay ul.starWorksList li.listItem div.info");
		for (Element element : linksdoctvplay) {
//			String name = "";
			String name_URL = "";
			String PRODUCED_TIME = "";
			String role_name = "";
			String DIRECTOR = "";
			String MAJOR_ACTORS = "";
			String Singer = "";
			String remarks = "";
			String text_value = "";
			String data_type = "";
			System.out.println(name = element.select("b.title").text());
//			personwork.setName(name);
			work.setDateName(name);
//			if ("导火线".equals(name)) {
//				System.out.println("命运呼叫转移");
//			}
			System.out.println(name_URL = element.select("b.title a").attr("href"));
			if (!name_URL.equals("")) {
				System.out.println(name_URL = "http://baike.baidu.com" + element.select("b.title a").attr("href"));
			}
//			personwork.setName_URL(name_URL);
			work.setDateUrl(name_URL);

			System.out.println(PRODUCED_TIME = element.select("b").get(1).text());
//			personwork.setProducedTime(PRODUCED_TIME);
			work.setDateTime(PRODUCED_TIME);
			// System.out.println(element.select("dl"));
			Elements linksdocmovesdl = element.select("dl dt");
			int i = 0;
			String urlrole = "";
			for (Element element2 : linksdocmovesdl) {
				if (element2.text().equals("饰演")) {
					if (element.select("dl dd").get(i).select("a").size() > 0) {
						Elements elementa = element.select("dl dd").get(i).select("a");
						for (Element element3 : elementa) {
							System.out.println(urlrole = element3.attr("href"));
							if (!urlrole.equals("")) {
								urlrole = "http://baike.baidu.com" + urlrole;
							}
							// System.out.println(element3.text());
							role_name = role_name + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(role_name = element.select("dl dd").get(i).text());
					}
//					personwork.setRoleName(role_name);
//					work.setdate
				}
				if (element2.text().equals("导演")) {

					if (element.select("dl dd").get(i).select("a").size() > 0) {
						Elements elementa = element.select("dl dd").get(i).select("a");
						for (Element element3 : elementa) {
							urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.baidu.com" + urlrole;
							}
							// System.out.println(element3.text());
							DIRECTOR = DIRECTOR + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(DIRECTOR = element.select("dl dd").get(i).text());
					}
//					personwork.setDirector(DIRECTOR);
					work.setDateDirector(DIRECTOR);
					
				}
				if (element2.text().equals("主演")) {

					if (element.select("dl dd").get(i).select("a").size() > 0) {
						Elements elementa = element.select("dl dd").get(i).select("a");
						for (Element element3 : elementa) {
							urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.baidu.com" + urlrole;
							}
							// System.out.println(element3.text());
							MAJOR_ACTORS = MAJOR_ACTORS + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(MAJOR_ACTORS = element.select("dl dd").get(i).text());
					}
//					personwork.setMajorActors(MAJOR_ACTORS);
					work.setDateMajorActors(MAJOR_ACTORS);
				}

				i += 1;
			}
			// System.out.println(role_name+DIRECTOR+MAJOR_ACTORS);
			// System.out.println(element.text());
//			personwork.setTextValue(element.text());
//			personwork.setDataType(2);
			work.setSource(1);
			work.setDataType(1);
//			OracleHaoSou.intotem_person_works(personwork);
			
			Oracle.InsertTEM_PERSON_URL_WORKS(work);
		}

	}
	
	
	
	
	/**
	 * 参演电影 2016年5月10日14:16:15
	 * 
	 * @param strId
	 * @param url
	 */
	public static void moves(String strId,String name, String url, String strHtml, Document doc) {
//		tem_person_works personwork = new tem_person_works();
//		personwork.setPersonId(Integer.parseInt(strId));
//		personwork.setPersonUrl(url);
		TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//		personwork.setPersonId(Integer.parseInt(strId));
		work.setPersonId(strId);
//		personwork.setPersonUrl(url);
		work.setPersonName(name);
		work.setPersonUrl(url);
		// String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
		// null);
		// Document doc = Jsoup.parse(strHtml);
		/**
		 * 参演电影 2016年5月6日16:53:08
		 *
		 */

		String moves = HtmlAnalyze.getTagText(strHtml, "</span>参演电影</h3>", "<div class=\"pageBtns\">", true, 0);
		if (moves.contains("title-text")) {
			moves = HtmlAnalyze.getTagText("###" + moves, "###", "<h3 class=\"title-text\">", true, 0);
		}

		if (moves.equals("")) {
			moves = HtmlAnalyze.getTagText(strHtml, "</span>参演电影</h3>", "</table>", true, 0);
		}
		
		if (moves.equals("")) {
			if (moves.contains("</span>参演电影</h3>")) {
				moves = HtmlAnalyze.getTagText(strHtml, "</span>参演电影</h3>", "</li>\r\n</ul>\r\n</ul>\r\n</div>\r\n</div>", true, 0);
			}
		}
		
		System.out.println(moves);
		// System.out.println(moves);

		Document docmoves = Jsoup.parse(moves);
		Elements linksdocmoves = docmoves.select("div.starMovieAndTvplay ul.starWorksList li.listItem div.info");
		for (Element element : linksdocmoves) {
//			String name = "";
			String name_URL = "";
			String PRODUCED_TIME = "";
			String role_name = "";
			String DIRECTOR = "";
			String MAJOR_ACTORS = "";
			String Singer = "";
			String remarks = "";
			String text_value = "";
			String data_type = "";
			System.out.println(name = element.select("b.title").text());
//			personwork.setName(name);
			work.setDateName(name);
			System.out.println(name_URL = element.select("b.title a").attr("href"));
			if (!name_URL.equals("")) {
				System.out.println(name_URL = "http://baike.baidu.com" + element.select("b.title a").attr("href"));
			}
//			personwork.setName_URL(name_URL);
			work.setDateUrl(name_URL);

			System.out.println(PRODUCED_TIME = element.select("b").get(1).text());
//			personwork.setProducedTime(PRODUCED_TIME);
			work.setDateTime(PRODUCED_TIME);

			// System.out.println(element.select("dl"));
			Elements linksdocmovesdl = element.select("dl dt");
			int i = 0;
			String urlrole = "";
			for (Element element2 : linksdocmovesdl) {
				if (element2.text().equals("饰演")) {
					if (element.select("dl dd").get(i).select("a").size() > 0) {
						Elements elementa = element.select("dl dd").get(i).select("a");
						for (Element element3 : elementa) {
							System.out.println(urlrole = element3.attr("href"));
							if (!urlrole.equals("")) {
								urlrole = "http://baike.baidu.com" + urlrole;
							}
							// System.out.println(element3.text());
							role_name = role_name + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(role_name = element.select("dl dd").get(i).text());
					}
//					personwork.setRoleName(role_name);
				}
				if (element2.text().equals("导演")) {

					if (element.select("dl dd").get(i).select("a").size() > 0) {
						Elements elementa = element.select("dl dd").get(i).select("a");
						for (Element element3 : elementa) {
							urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.baidu.com" + urlrole;
							}
							// System.out.println(element3.text());
							DIRECTOR = DIRECTOR + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(DIRECTOR = element.select("dl dd").get(i).text());
					}
//					personwork.setDirector(DIRECTOR);
					work.setDateDirector(DIRECTOR);
				}
				if (element2.text().equals("主演")) {

					if (element.select("dl dd").get(i).select("a").size() > 0) {
						Elements elementa = element.select("dl dd").get(i).select("a");
						for (Element element3 : elementa) {
							urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.baidu.com" + urlrole;
							}
							// System.out.println(element3.text());
							MAJOR_ACTORS = MAJOR_ACTORS + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(MAJOR_ACTORS = element.select("dl dd").get(i).text());
					}
//					personwork.setMajorActors(MAJOR_ACTORS);
					work.setDateMajorActors(MAJOR_ACTORS);
				}

				i += 1;
			}
			// System.out.println(role_name+DIRECTOR+MAJOR_ACTORS);
			// System.out.println(element.text());
//			personwork.setTextValue(element.text());
//			personwork.setDataType(1);
//			OracleHaoSou.intotem_person_works(personwork);
			
			work.setSource(1);
			work.setDataType(2);
//			OracleHaoSou.intotem_person_works(personwork);
			
			Oracle.InsertTEM_PERSON_URL_WORKS(work);

		}

	}
	
	

}
