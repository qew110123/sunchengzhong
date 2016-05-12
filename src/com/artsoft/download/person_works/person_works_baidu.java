package com.artsoft.download.person_works;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.tem_person_works;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class person_works_baidu {

	public static String getTopDomainWithoutSubdomain(String url) throws MalformedURLException {

		String host = new URL(url).getHost().toLowerCase();// 此处获取值转换为小写
		Pattern pattern = Pattern.compile(
				"[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
		Matcher matcher = pattern.matcher(host);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * 2016年5月10日14:10:10 曾任大使
	 */
	public static void chengRenDaShi(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);
		/**
		 * 时间2016年5月9日16:02:11 曾任大使
		 */
		String dosVariety = HtmlAnalyze.getTagText(strHtml, "<caption><b>曾任大使</b></caption>", "</table>", true, 0);

		String[] dosVarietylist = dosVariety.split("</tr><tr>");
		for (int i = 0; i < dosVarietylist.length; i++)

		{
			// System.out.println(dosVarietylist[i]);
			Document VarietyJsoup = Jsoup.parse(dosVarietylist[i]);
			System.out.println(VarietyJsoup);
			if (!VarietyJsoup.text().equals("时间称号")) {
				personwork.setName(VarietyJsoup.text());
				personwork.setTextValue(VarietyJsoup.text());
				personwork.setDataType(6);
				if (!personwork.getTextValue().equals("") && personwork.getTextValue() != null) {
					OracleHaoSou.intotem_person_works(personwork);
				}
			}
		}

	}

	/**
	 * 音乐 2016年5月10日14:13:59
	 * 
	 * @param strId
	 * @param url
	 */
	public static void yinyue(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);
		// /**
		// * 时间2016年5月9日14:01:57
		// * 音乐
		// */
		//// Document docmoves = Jsoup.parse(moves);
		// Elements musicHot = doc.select("div.musicHot-wrapper td.item-name
		// a");
		// for (Element element : musicHot) {
		//// System.out.println(element);
		// System.out.println(element.text());
		// String NAME_URL="";
		// System.out.println(NAME_URL=element.attr("href"));
		// personwork.setName(element.text());
		// if (!NAME_URL.equals("")) {
		// personwork.setTextValue("http://baike.baidu.com"+NAME_URL);
		// }
		// personwork.setDataType(3);
		// OracleHaoSou.intotem_person_works(personwork);
		// }

		/**
		 * 时间2016-5-9 14:23:04 音乐 单曲
		 */
		String dosmusic = HtmlAnalyze.getTagText(strHtml, "<h3>单曲</h3>",
				"<div class=\"musicSingle-pager\"></div></div>", true, 0);
		Document music = Jsoup.parse(dosmusic);
		Elements musicHot = music.select("tr");
		for (Element element : musicHot) {
			// System.out.println(element);
			// System.out.println(element.text());
			String name = element.select("td.with-padding span.title a").text();
			personwork.setName(name);
			String NAME_URL = "";
			NAME_URL = element.select("td.with-padding span.title a").attr("href");
			System.out.println(NAME_URL);
			if (!NAME_URL.equals("")) {
				personwork.setName_URL("http://baike.baidu.com" + NAME_URL);
			}
			String remarks = "";
			if ("color:#999999;".equals(element.select("td.with-padding span").attr("style"))) {
				System.out.println(remarks = element.select("td.with-padding span").text());
				personwork.setRemarks(remarks);
			}
			String PRODUCED_TIME = "";
			System.out.println(PRODUCED_TIME = element.select("td.align-center").text());
			personwork.setProducedTime(PRODUCED_TIME);

			personwork.setTextValue(element.text());

			personwork.setDataType(3);
			if (!"歌曲名称（歌曲说明） 发行时间".equals(element.text()) && !"".equals(name) && name != null) {
				OracleHaoSou.intotem_person_works(personwork);
			}
		}

	}

	/**
	 * 2016年5月10日14:14:07 mv
	 */
	public static void mvyinyue(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);
		/**
		 * 时间2016年5月9日14:59:00 MV作品
		 */
		String dosmv = HtmlAnalyze.getTagText(strHtml, "span>MV作品</h3>", "</table>", true, 0);
		if (dosmv.equals("")) {
			 dosmv = HtmlAnalyze.getTagText(strHtml, "</span>参演MV</h3>", "</table>", true, 0);
		}
		Document mv = Jsoup.parse(dosmv);
		Elements mvtr = mv.select("tr");
		for (Element element : mvtr)

		{
			// System.out.println(element);
			Elements mvtrtd = element.select("td");
			String name = "";
			String NAME_URL = "";
			String producedTime = "";
			String SINGER = "";
//			for (Element element2 : mvtrtd) {
//				if ("187".equals(element2.attr("width"))) {
//					System.out.println(producedTime = element2.text());
//					personwork.setProducedTime(producedTime);
//				}
//				if ("285".equals(element2.attr("width"))) {
//					System.out.println(name = element2.select("a").text());
//					personwork.setName(name);
//					NAME_URL = element2.select("a").attr("href");
//					System.out.println(NAME_URL);
//					if (!NAME_URL.equals("")) {
//						personwork.setName_URL("http://baike.baidu.com" + NAME_URL);
//					}
//				}
//				if ("185".equals(element2.attr("width"))) {
//					System.out.println(SINGER = element2.text());
//					personwork.setSinger(SINGER);
//				}
//			}
			
			personwork.setTextValue(mvtrtd.text());
			int i=0;
			for (Element element2 : mvtrtd) {
			if (i%3==0) {
				System.out.println(producedTime = element2.text());
				personwork.setProducedTime(producedTime);
			}
			if (i%3==1) {
				name = element2.select("a").text();
				if (name.equals("")) {
					name=element2.text();
				}
				personwork.setName(name);
				NAME_URL = element2.select("a").attr("href");
				System.out.println(NAME_URL);
				if (!NAME_URL.equals("")) {
					personwork.setName_URL("http://baike.baidu.com" + NAME_URL);
				}
			}
			if (i%3==2) {
				System.out.println(SINGER = element2.text());
				personwork.setSinger(SINGER);
			}
			i+=1;
		}
			personwork.setDataType(4);
			if (!name.equals("")) {
				OracleHaoSou.intotem_person_works(personwork);
			}

		}
		
		

	}

	/**
	 * 综艺节目 2016年5月10日14:15:25
	 * 
	 * @param strId
	 * @param url
	 */
	public static void zhongyijiemu(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);
		/**
		 * 时间2016年5月9日15:49:32 音乐 综艺节目
		 */
		String dosVariety = HtmlAnalyze.getTagText(strHtml, "span>综艺节目</h3>", "</table>", true, 0);
		Document Variety = Jsoup.parse(dosVariety);
		Elements Varietys = Variety.select("tbody tr");
		for (Element element : Varietys)

		{
			System.out.println(element);
			Elements mvtrtd = element.select("td");
			String name = "";
			String NAME_URL = "";
			String producedTime = "";
			String Remarks = "";
			int i = 0;
			for (Element element2 : mvtrtd) {
				if (i % 3 == 0) {
					System.out.println(producedTime = element2.text());
					personwork.setProducedTime(producedTime);
				}
				if (i % 3 == 1) {
					System.out.println(name = element2.text());
					personwork.setName(name);
					// NAME_URL = element2.select("a").attr("href");
					// System.out.println(NAME_URL);
					// if (!NAME_URL.equals("")) {
					// personwork.setName_URL("http://baike.baidu.com" +
					// NAME_URL);
					// }
				}
				if (i % 3 == 2) {
					System.out.println(Remarks = element2.text());
					personwork.setRemarks(Remarks);
				}
				i += 1;
			}
			personwork.setDataType(5);

			personwork.setTextValue(mvtrtd.text());
			if (!name.equals("")) {
				OracleHaoSou.intotem_person_works(personwork);
			}
		}

	}

	/**
	 * 参演电影 2016年5月10日14:16:15
	 * 
	 * @param strId
	 * @param url
	 */
	public static void moves(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);
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
		System.out.println(moves);
		// System.out.println(moves);

		Document docmoves = Jsoup.parse(moves);
		Elements linksdocmoves = docmoves.select("div.starMovieAndTvplay ul.starWorksList li.listItem div.info");
		for (Element element : linksdocmoves) {
			String name = "";
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
			personwork.setName(name);
			System.out.println(name_URL = element.select("b.title a").attr("href"));
			if (!name_URL.equals("")) {
				System.out.println(name_URL = "http://baike.baidu.com" + element.select("b.title a").attr("href"));
			}
			personwork.setName_URL(name_URL);

			System.out.println(PRODUCED_TIME = element.select("b").get(1).text());
			personwork.setProducedTime(PRODUCED_TIME);

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
					personwork.setRoleName(role_name);
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
					personwork.setDirector(DIRECTOR);
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
					personwork.setMajorActors(MAJOR_ACTORS);
				}

				i += 1;
			}
			// System.out.println(role_name+DIRECTOR+MAJOR_ACTORS);
			// System.out.println(element.text());
			personwork.setTextValue(element.text());
			personwork.setDataType(1);
			OracleHaoSou.intotem_person_works(personwork);

		}

	}

	/**
	 * 参演电视剧 2016年5月10日14:18:20
	 * 
	 * @param strId
	 * @param url
	 */
	public static void tvplay(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);

		/**
		 * 参演电视剧 2016年5月6日16:53:08
		 *
		 */

		String tvplay = HtmlAnalyze.getTagText(strHtml, "</span>参演电视剧</h3>", "<div class=\"pageBtns\">", true, 0);
		// System.out.println(moves);

		Document doctvplay = Jsoup.parse(tvplay);
		Elements linksdoctvplay = doctvplay.select("div.starMovieAndTvplay ul.starWorksList li.listItem div.info");
		for (Element element : linksdoctvplay) {
			String name = "";
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
			personwork.setName(name);
			if ("导火线".equals(name)) {
				System.out.println("命运呼叫转移");
			}
			System.out.println(name_URL = element.select("b.title a").attr("href"));
			if (!name_URL.equals("")) {
				System.out.println(name_URL = "http://baike.baidu.com" + element.select("b.title a").attr("href"));
			}
			personwork.setName_URL(name_URL);

			System.out.println(PRODUCED_TIME = element.select("b").get(1).text());
			personwork.setProducedTime(PRODUCED_TIME);
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
					personwork.setRoleName(role_name);
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
					personwork.setDirector(DIRECTOR);
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
					personwork.setMajorActors(MAJOR_ACTORS);
				}

				i += 1;
			}
			// System.out.println(role_name+DIRECTOR+MAJOR_ACTORS);
			// System.out.println(element.text());
			personwork.setTextValue(element.text());
			personwork.setDataType(2);
			OracleHaoSou.intotem_person_works(personwork);
		}

	}
	
	
	/**
	 *杂志写真
	 *2016年5月10日15:19:35
	 * 
	 * @param strId
	 * @param url
	 */
	public static void zhazhixiezheng(String strId, String url,String strHtml ,Document doc) {
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
//		String strHtml = "";
//		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//		Document doc = Jsoup.parse(strHtml);


		String tvplay = HtmlAnalyze.getTagText(strHtml, "</span>杂志写真</h3>", "</table>", true, 0);
		// System.out.println(moves);

		Document doctvplay = Jsoup.parse(tvplay);
		Elements linksdoctvplay = doctvplay.select("tbody tr");
		for (Element element : linksdoctvplay) {
			
			System.out.println(element);
			Elements mvtrtd = element.select("td");
			String name = "";
			String NAME_URL = "";
			String producedTime = "";
			String Remarks = "";
			int i = 0;
			for (Element element2 : mvtrtd) {
				if (i%3==0) {
					System.out.println(producedTime = element2.text());
					personwork.setProducedTime(producedTime);
				}
				if (i%3==1) {
					name = element2.select("a").text();
					if (name.equals("")) {
						name=element2.text();
					}
					personwork.setName(name);
					NAME_URL = element2.select("a").attr("href");
					System.out.println(NAME_URL);
					if (!NAME_URL.equals("")) {
						personwork.setName_URL("http://baike.baidu.com" + NAME_URL);
					}
				}
				if (i%3==2) {
					String remarks="";
					System.out.println(remarks = element2.text());
					personwork.setRemarks(remarks);
				}
				i += 1;
			}
			personwork.setDataType(7);

			personwork.setTextValue(mvtrtd.text());
			if (!name.equals("")) {
				OracleHaoSou.intotem_person_works(personwork);
			}
		}

	}
	
	

	public static void mainmore(String strId, String url) {
		
		tem_person_works personwork = new tem_person_works();
		personwork.setPersonId(Integer.parseInt(strId));
		personwork.setPersonUrl(url);
		String strHtml = "";
		strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		Document doc = Jsoup.parse(strHtml);
		// 曾任大使
		chengRenDaShi(strId, url,strHtml,doc);
		// 电视剧
		tvplay(strId, url,strHtml,doc);
		// 电影
		moves(strId, url,strHtml,doc);
		// 综艺节目
		zhongyijiemu(strId, url,strHtml,doc);
//		 mv
		mvyinyue(strId, url,strHtml,doc);
//		 音乐
		yinyue(strId, url,strHtml,doc);
		//杂志写真
		zhazhixiezheng(strId, url,strHtml,doc);
		
	}

	public static void runnewMain() {
		List<String> listArray = OracleBaidu.selectpeople();
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			try {
//				if (Integer.valueOf(listTemp.get(0))>4000) {
					
					mainmore(listTemp.get(0), listTemp.get(1));
//				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		mainmore("0",
//				"http://baike.baidu.com/link?url=j4mYxsV49miG1eafx2XaFuzXMAbeo_ZcsBk2-RJbJQnpCyIjyITsN7cxVsoZb6DGTjKRfCE2acMb7kSis3Rpia");
				
//				 mainmore("0",
//				 "http://baike.baidu.com/link?url=khFDXc_ofWRcs2EuxZyCchpQWA-tCXk6L4gXIBE7RKABR6l7ynhHlgIoax0DUGexMHu2wbuKmUyDmOs189VZl_");

		 runnewMain();
	}

}
