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

public class kasi_people_url_360 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id="0";
		String name="刘涛";
		String url= "http://baike.so.com/doc/5328985-5564157.html";
		
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
		 * 参演电视剧
			2017年2月22日16:31:31
		 *
		 */

		String tvplay = HtmlAnalyze.getTagText(strHtml, ">参演电视剧</b>", "</tbody>", true, 0);
		// System.out.println(moves);
		
		if (tvplay.equals("")) {
			if (strHtml.contains("</span>参演电视剧</h3>")) {
				tvplay = HtmlAnalyze.getTagText(strHtml, "</span>参演电视剧</h3>", "</li>\r\n</ul>\r\n</ul>\r\n</div>\r\n</div>", true, 0);
			}
		}
		

		Document doctvplay = Jsoup.parse(tvplay);
		Elements linksdoctvplay = doctvplay.select("tr");
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
			
			Elements tdlinks=element.select("td");
			
			if (tdlinks.size()==0) {
				continue;
			}
			
			int i=0;
			
			for (Element element2 : tdlinks) {
				
//				System.out.println(element2.text().toString());
				if (i==0) {
					PRODUCED_TIME=element2.text().toString();
					work.setDateTime(PRODUCED_TIME);
				}
				
				if (i==1) {
					name=element2.text().toString();
					work.setDateName(name);
					
					System.out.println(name_URL = element2.select("a").attr("href"));
					if (!name_URL.equals("")) {
						System.out.println(name_URL = "http://baike.so.com" +name_URL);
					}
					work.setDateUrl(name_URL);
				}
				
				
				if (i==3) {
					if (element2.select("a").size() > 0) {
						Elements elementa = element2.select("a");
						for (Element element3 : elementa) {
							String urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.so.com" + urlrole;
							}
							// System.out.println(element3.text());
							DIRECTOR = DIRECTOR + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(DIRECTOR = element2.text());
					}
//				personwork.setDirector(DIRECTOR);
				work.setDateDirector(DIRECTOR);
				}
				
				
				
				if (i==4) {
					
					if (element2.select("a").size() > 0) {
						Elements elementa = element2.select("a");
						for (Element element3 : elementa) {
							String urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.so.com" + urlrole;
							}
							// System.out.println(element3.text());
							MAJOR_ACTORS = MAJOR_ACTORS + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(MAJOR_ACTORS = element2.text());
					}
//					personwork.setMajorActors(MAJOR_ACTORS);
					work.setDateMajorActors(MAJOR_ACTORS);
					
					
				}
				
				
				
				i+=1;
			}
			
			System.out.println(work);
			
			work.setSource(3);
			work.setDataType(1);

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

		String moves = HtmlAnalyze.getTagText(strHtml, ">参演电影</b>", "</tbody>", true, 0);
		// System.out.println(moves);
		
		if (moves.equals("")) {
			if (strHtml.contains("</span>参演电影</h3>")) {
				moves = HtmlAnalyze.getTagText(strHtml, "</span>参演电影</h3>", "</li>\r\n</ul>\r\n</ul>\r\n</div>\r\n</div>", true, 0);
			}
		}
		System.out.println(moves);
		// System.out.println(moves);

		Document docmoves = Jsoup.parse(moves);
		Elements linksdocmoves = docmoves.select("tr");
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
			
			Elements tdlinks=element.select("td");
			
			if (tdlinks.size()==0) {
				continue;
			}
			
			int i=0;
			
			for (Element element2 : tdlinks) {
				
//				System.out.println(element2.text().toString());
				if (i==0) {
					PRODUCED_TIME=element2.text().toString();
					work.setDateTime(PRODUCED_TIME);
				}
				
				if (i==1) {
					name=element2.text().toString();
					work.setDateName(name);
					
					System.out.println(name_URL = element2.select("a").attr("href"));
					if (!name_URL.equals("")) {
						System.out.println(name_URL = "http://baike.so.com" +name_URL);
					}
					work.setDateUrl(name_URL);
				}
				
				
				if (i==3) {
					if (element2.select("a").size() > 0) {
						Elements elementa = element2.select("a");
						for (Element element3 : elementa) {
							String urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.so.com" + urlrole;
							}
							// System.out.println(element3.text());
							DIRECTOR = DIRECTOR + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(DIRECTOR = element2.text());
					}
//				personwork.setDirector(DIRECTOR);
				work.setDateDirector(DIRECTOR);
				}
				
				
				
				if (i==4) {
					
					if (element2.select("a").size() > 0) {
						Elements elementa = element2.select("a");
						for (Element element3 : elementa) {
							String urlrole = element3.attr("href");
							if (!urlrole.equals("")) {
								urlrole = "http://baike.so.com" + urlrole;
							}
							// System.out.println(element3.text());
							MAJOR_ACTORS = MAJOR_ACTORS + element3.text() + "|" + urlrole + ",";
						}

					} else {
						System.out.println(MAJOR_ACTORS = element2.text());
					}
//					personwork.setMajorActors(MAJOR_ACTORS);
					work.setDateMajorActors(MAJOR_ACTORS);
					
					
				}
				
				
				
				i+=1;
			}
			

			
			work.setSource(3);
			work.setDataType(2);
//			OracleHaoSou.intotem_person_works(personwork);
			
			Oracle.InsertTEM_PERSON_URL_WORKS(work);

		}

	}
	
	

}
