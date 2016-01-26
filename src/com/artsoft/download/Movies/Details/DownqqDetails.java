package com.artsoft.download.Movies.Details;

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

public class DownqqDetails {
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

			// System.out.println(score = link.select("span.mod_score").text());
			// try {
			// OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain,
			// "3", "1");
			// } catch (Exception e) {
			// // TODO: handle exception
			// }
			TvPlay playtv = new TvPlay();
			playtv.setTvplay_url(strmainurl);
			playtv.setTvplay_name(name);
			
			try {
				playtv =  downBranch(strmainurl, name, urlMain, playtv);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (playtv.getSubject().equals("")) {
					String strmainurl1 = strmainurl.replaceAll("http://v.qq.com", "http://film.qq.com");
					playtv =downBranch(strmainurl1, name, urlMain, playtv);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
//				downBranch(strmainurl2, name, urlMain, playtv);
				if (playtv.getSubject().equals("")) {
					String strmainurl2 = strmainurl.replaceAll("cover", "prev");
					playtv =downBranch(strmainurl2, name, urlMain, playtv);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
//				downBranch(strmainurl2, name, urlMain, playtv);
				OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();

		try {
			if (xxx > Integer.parseInt(tt)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;

	}

	public static TvPlay downBranch(String urlBranch, String nameBranch, String urlMain ,TvPlay playtv  ) {
		// http: // v.qq.com/cover/e/e7hi6lep1yc51ca.html
//		TvPlay playtv = new TvPlay();
//		playtv.setTvplay_url(urlBranch);
//		playtv.setTvplay_name(nameBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}

		String classstr = ""; // 类型:
		String daoyanAlltext = HtmlAnalyze.getTagText(strHtml, "class=\"tag_list\">", "</ul>", true, 0);
		String[] daoyanlists = daoyanAlltext.split("</li> <li>");
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
		System.out.println(classstr);
		playtv.setSubject(classstr);

		String daoyan = "";// 导演
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "导演：", "</span>", true, 0);
		String[] daoyanlist = daoyanAll.split("</span>/");
		i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
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
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：</span>", "class=\"list_item", true, 0);
		String[] yanyuanlist = yanyuanAll.split("/span>");
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
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "desc_cut\":\"", "\",\"");
		System.out.println(detail);
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		playtv.setBasic_info(detail);

		playtv.setClassnum(3);

//		OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
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
		String url = "";
		String[] diqu = { "100024", "100025", "100026", "100027", "100028", "100029", "100030", "100031", "100032",
				"100033" };
		String[] nianfen = { "100063", "100034", "100035", "100036", "100037", "100038", "100039", "100040" };
		for (String diqutxt : diqu) {
			for (String nianfentxt : nianfen) {
				System.out.println(diqutxt + nianfentxt);

				try {
					for (int i = 0; i < 5000; i = i + 20) {
						url = "http://v.qq.com/x/movielist/?cate=-1&offset=" + i + "&sort=5&pay=-1&area=" + diqutxt
								+ "&year=" + nianfentxt + "";
						System.out.println(url);
						boolean bb = downMain(url, i);
						// String urlnext = DownYoukuMovie.youkuMaim(url);
						if (!bb) {
							break;
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TimingTime(23, 59, 59);
		openstatic();

	}

}
