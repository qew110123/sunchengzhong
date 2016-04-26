package com.artsoft.download.TVPlay.platform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Downqqplatform {
	// public static boolean downMain(String urlMain) {
	// String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8",
	// null, null);
	// if (strHtml == null || strHtml.equals("")) {
	// strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null,
	// null);
	// }
	// if (strHtml == null || strHtml.equals("")) {
	// return false;
	// }
	//
	// Document doc = Jsoup.parse(strHtml);
	// Elements links = doc.select("ul.mod_list_pic_130 li");
	// // Element content = doc.getElementById("content");
	// // Elements links = content.getElementsByTag("a");
	// for (Element link : links) {
	// String strmainurl = "";
	// String score = "";
	// String name = "";
	// System.out.println(strmainurl = link.select("a").attr("href"));
	// // System.out.println(strmainurl = link.select("a").attr("id"));
	// System.out.println(name = link.select("a").attr("title"));
	// // System.out.println(link.select("a").text());
	// // System.out.println(link.text());
	//
	// // System.out.println(score = link.select("span.mod_score").text());
	// // try {
	// // OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain,
	// // "3", "1");
	// // } catch (Exception e) {
	// // // TODO: handle exception
	// // }
	// TvPlay playtv = new TvPlay();
	// playtv.setTvplay_url(strmainurl);
	// playtv.setTvplay_name(name);
	//
	// try {
	// playtv = downBranch(strmainurl, name, urlMain, playtv);
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// try {
	// if (playtv.getSubject().equals("")) {
	// String strmainurl1 = strmainurl.replaceAll("http://v.qq.com",
	// "http://film.qq.com");
	// playtv =downBranch(strmainurl1, name, urlMain, playtv);
	//
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// try {
	//// downBranch(strmainurl2, name, urlMain, playtv);
	// if (playtv.getSubject().equals("")) {
	// String strmainurl2 = strmainurl.replaceAll("cover", "prev");
	// playtv =downBranch(strmainurl2, name, urlMain, playtv);
	//
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// try {
	//// downBranch(strmainurl2, name, urlMain, playtv);
	//// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
	// OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	//// String tt =
	// doc.select("span.txt_01").select("em.strong").first().text();
	//
	//// try {
	//// if (xxx > Integer.parseInt(tt)) {
	//// return true;
	//// } else {
	//// return false;
	//// }
	//// } catch (Exception e) {
	//// // TODO: handle exception
	//// }
	//
	// return true;
	//
	// }

	public static boolean downMain(String urlMain, int xxx) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return false;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.figures_list li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String strmainurl = "";
			String score = "";
			String name = "";
			System.out.println(strmainurl = link.select("strong.figure_title a").attr("href"));
			// System.out.println(strmainurl = link.select("a").attr("id"));
			System.out.println(name = link.select("strong.figure_title a").attr("title"));
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			System.out.println(score = link.select("span.mod_score").text());
			// try {
			// // OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score,
			// // "0", "", urlMain, "3", "1",strmainurl);
			// OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0",
			// "", urlMain, "0", "1", strmainurl);
			//// OracleOpreater.intoReputation(name, "3", score, "0", "",
			// urlMain, "1", "1");
			// } catch (Exception e) {
			// // TODO: handle exception
			// }
			TvPlay playtv = new TvPlay();
			playtv.setTvplay_url(strmainurl);
			playtv.setTvplay_name(name);

			try {
				playtv = downBranch(strmainurl, name, urlMain, playtv);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (playtv.getSubject().equals("")) {
					String strmainurl1 = strmainurl.replaceAll("http://v.qq.com", "http://film.qq.com");
					playtv = downBranch(strmainurl1, name, urlMain, playtv);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// downBranch(strmainurl2, name, urlMain, playtv);
				if (playtv.getSubject().equals("")) {
					String strmainurl2 = strmainurl.replaceAll("cover", "prev");
					playtv = downBranch(strmainurl2, name, urlMain, playtv);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// downBranch(strmainurl2, name, urlMain, playtv);
				// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
				OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
			} catch (Exception e) {
				// TODO: handle exception
			}

			// }
			// downBranch(strmainurl1, name, urlMain, playtv);
			// downBranch(strmainurl, name, urlMain);
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();

		try {
			if (xxx < Integer.parseInt(tt)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;

	}

	public static TvPlay downBranch(String urlBranch, String nameBranch, String urlMain, TvPlay playtv) {
		// http: // v.qq.com/cover/e/e7hi6lep1yc51ca.html
		// TvPlay playtv = new TvPlay();
		// playtv.setTvplay_url(urlBranch);
		// playtv.setTvplay_name(nameBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}

		String classstr = ""; // 类型:
		String daoyanAlltext = HtmlAnalyze.getTagText(strHtml, "class=\"tag_list\">", "</ul>", true, 0);
		String[] daoyanlists = daoyanAlltext.split("</li><li");
		int i = 0;
		for (String stringtext : daoyanlists) {
			// String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "tag_inner\">", "<");
			if (!(textss.equals(""))) {
				classstr = classstr + textss;
			}
			if (daoyanlists.length != 1 && i + 1 < daoyanlists.length) {
				classstr = classstr + "/";
			}

			i += 1;
		}
		if (classstr.equals("")) {
			daoyanAlltext = HtmlAnalyze.getTagText(strHtml, "类型：", "</span>", true, 0);
			daoyanlists = daoyanAlltext.split("/a>/<a");
			i = 0;
			for (String stringtext : daoyanlists) {
				// String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"",
				// "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "cover.bread_2\">", "<");
				if (!(textss.equals(""))) {
					classstr = classstr + textss;
				}
				if (daoyanlists.length != 1 && i + 1 < daoyanlists.length) {
					classstr = classstr + "/";
				}

				i += 1;
			}
		}

		System.out.println(classstr);

		playtv.setSubject(classstr);

		String daoyan = "";// 导演
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "导演：", "</ul>", true, 0);
		String[] daoyanlist = daoyanAll.split("</span>/");
		i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "itemprop=\"name\" >", "<");
			if (textss.equals("")) {

				textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\"");
			}
			// daoyan=daoyan+textss+"|"+urlss;
			if (!(urlss.equals("") && textss.equals(""))) {
				daoyan = daoyan + textss + "|" + urlss;
			}
			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
				daoyan = daoyan + ",";
			}

			i += 1;
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);

		String niandai = ""; // 年代
		niandai = HtmlAnalyze.getTagText(strHtml, "年份：", "</span>");
		playtv.setShoot_time(niandai);

		String diqu = ""; // 地区
		diqu = HtmlAnalyze.getTagText(strHtml, "地区：", "</span>");
		playtv.setProduction_area(diqu);

		String yanyuan = "";// 演员
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：</span>", "</ul>", true, 0);
		String[] yanyuanlist = yanyuanAll.split("</li><li");
		i = 0;
		for (String stringtext : yanyuanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "itemprop=\"name\" >", "<");
			if (!(urlss.equals("") && textss.equals(""))) {
				yanyuan = yanyuan + textss + "|" + urlss;
			}
			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
				yanyuan = yanyuan + ",";
			}

			i += 1;
		}
		if (yanyuan.equals("")) {
			yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：", "</dd>", true, 0);
			yanyuanlist = yanyuanAll.split("/a>/<a");
			i = 0;
			for (String stringtext : yanyuanlist) {
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\">");
				if (!(urlss.equals("") && textss.equals(""))) {
					yanyuan = yanyuan + textss + "|" + urlss;
				}
				if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
					yanyuan = yanyuan + ",";
				}

				i += 1;
			}

		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"intro_full\">", "</p>");
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "itemprop=\"abstract\">", "</p>");
		}

		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"detail_all\">", "</p>");
		}

		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"detail\">", "</p>");
		}
		System.out.println(detail);

		playtv.setBasic_info(detail);

		// playtv.setClassnum(3);

		playtv.setSOURCE(3);
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		return playtv;

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
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	public static void openstatic() {

		// for (int i = 0; i <= 241; i++) {
		// String url = "http://v.qq.com/list/2_-1_-1_-1_1_0_" + i +
		// "_20_-1_-1_0_-1.html";
		// System.out.println(url);
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// url);
		// downMain(url);
		// }

		String url = "";
		String[] diqu = { "814", "815", "816", "817", "818", "819" };
		for (String diqutxt : diqu) {
			for (int i = 0; i < 5000; i = i + 20) {
				url = "http://v.qq.com/x/teleplaylist/?sort=4&offset=" + i + "&itype=-1&iarea=" + diqutxt
						+ "&iyear=-1&ipay=-1";
				System.out.println(url);
				boolean bb = downMain(url, i);
				// String urlnext = DownYoukuMovie.youkuMaim(url);
				if (!bb) {
					break;
				}
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		TimingTime(23, 59, 59);
//		 openstatic();
		 TimingTime(3, 59, 59);
		// TvPlay playtv = new TvPlay();
		// playtv.setTvplay_url("http://film.qq.com/cover/z/zagd2l04q320z04.html");
		// playtv.setTvplay_name("爱的阶梯");
		//
		// downBranch("http://film.qq.com/cover/z/zagd2l04q320z04.html", "爱的阶梯",
		// "", playtv);
	}

}
