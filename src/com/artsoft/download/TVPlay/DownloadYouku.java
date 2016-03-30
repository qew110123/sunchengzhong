package com.artsoft.download.TVPlay;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

public class DownloadYouku {
	static int i = 0;

	/**
	 * 播放量数据的采集
	 * 进行优酷网的类表页进行数据段查询
	 * 
	 * @param urlMain
	 */
	public static String youkuMaim(String urlMain) {
		try {

			// String cookstr="BAIDUID=3AADC6F35D3F3088991AA4460A697FE2:FG=1;
			// BIDUPSID=3AADC6F35D3F3088991AA4460A697FE2; PSTM=1445389865;
			// BDSFRCVID=YxIsJeCCxG3ICov459p-VZx1qNDZSXG-d5OR3J;
			// H_BDCLCKID_SF=tRk8oItMJCvBfJuk-4QEbbQH-UnLqhcOW67Z0lOnMp05OqOv568heJOD3R6OJRow3NQghp0E5I5cVCO_e4bK-TrXDGuetx5;
			// BDUSS=BnYVFaZEhifmlxYzhYaUg0cEdOUHpOcVYtU0NkZXZQc3R5bEZBT2JhVDVjMDVXQVFBQUFBJCQAAAAAAAAAAAEAAAChkbEONzY0Mjk1MzMzAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPnmJlb55iZWVl;
			// locale=zh;
			// H_PS_PSSID=12897_1430_17619_12826_17001_17470_17073_15669_12073_17421_17050";
			// String
			// strUrl="http://www.youku.com/v_olist/c_97_a_%E5%A4%A7%E9%99%86_s_1_d_1.html";
			// strUrl="http://www.youku.com/v_olist/c_97_a_%E5%A4%A7%E9%99%86_s_1_d_1.html";
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			// String strHtml = DownloadUtil.readHtml(strUrl, 1000 * 30,"UTF-8",
			// cookstr, null);
			// StringBuffer strHtml = DownloadUtil.getContent(urlstr);
			// System.out.println(strHtml);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				// return;
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.yk-col3");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			for (Element link : links) {
				System.out.println("进行数据的采集" + (++i));
				if (i <= 0) {
					continue;
				}
				String strmainurl = "";
				System.out.println(strmainurl = link.select("div.p-meta-title a").attr("href"));
				System.out.println(link.select("div.p-meta-title a").attr("title"));
				System.out.println(link.select("span.p-actor").text());
				System.out.println(link.select("span.p-num").text());
				DownloadYouku.youkuBranch(strmainurl);
			}

			// 进行下一页数据的判断

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"下一页\"><a href=\"",
					"\"  charset=");
			if (strnexturl != null && !"".equals(strnexturl)&& !"http://www.youku.com".equals(strnexturl)) {
				strnexturl = "http://www.youku.com" + strnexturl;
				return strnexturl;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * 进行优酷网的详细类表页数据的采集
	 * 
	 * @param urlBranch
	 */
	public static void youkuBranch(String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.yk-col3");

		/**
		 * 总播放: 评论: 顶:
		 */

		String name = "";// 名称
		System.out.println(name = doc.select("span.name").text());
		String Amount = "";// 播放量
		System.out.println(Amount = doc.select("span.play").text());
		Amount = Amount.replaceAll("总播放:", "").replaceAll(",", "");

		String comment = ""; // 评论
		System.out.println(comment = doc.select("span.comment").text());
		comment = comment.replaceAll("评论:", "").replaceAll(",", "");

		String answer = ""; // 顶
		System.out.println(answer = doc.select("span.increm").text());
		answer = answer.replaceAll("顶:", "").replaceAll(",", "");

		String score = ""; // 评分
		score = HtmlAnalyze.getTagText(strHtml, "<label>评分:</label>", "<style type=\"text/css\">");
		System.out.println(score);
		// System.out.println(score=HtmlAnalyze.getTagText(score, "<em
		// class=\"num\">", "</em>"));
		// answer=answer.replaceAll(",","");

		// Double.parseDouble(answer)

		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "0", "0",urlBranch);
		// OracleOpreater.intoReputation(name, "1", Amount, "0",
		// "2015年10月23日10:43:46",urlBranch, "0", "1");
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "0", "1",urlBranch);
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "0", "2",urlBranch);
		// OracleOpreater.intoDemo(tyPlayName, source, dataAmount, vodeoType,
		// upDatedate, playUrl, tvType, dataType);

		//
		/**
		 * 详细每集列表的数据
		 * 
		 * 时间2015年11月16日16:52:33
		 * 进行详细数据决的采集
		 */

//		try {
//			Element content = doc.getElementById("zy_bd");
//			Elements links = null;
//			links = content.getElementsByTag("li");
//			if (links == null) {
//				return;
//			}
//			Element link = links.first();
//			String urlDetailedfirst = "";
//			System.out.println(urlDetailedfirst = link.select("a").attr("href"));
//			youkuOneDetailed(urlDetailedfirst);
//		} catch (Exception e) {
//		}

		//
		// /**
		// * 在预告片 花絮 饭子制作品 mv 独家 共5个 一共20个
		// */
		//// content = doc.getElementById("zy_bd");
		// links = doc.select("div.collgrid4w").select("ul.v");
		// for (Element link : links) {
		//// System.out.println(link.select("div.p-meta-title a").attr("href"));
		//// System.out.println(link.select("div.p-meta-title
		// a").attr("title"));
		//// System.out.println(link.text());
		// System.out.println(link.select("li.v_title a").attr("href"));
		// System.out.println(link.select("li.v_titLle a").text());
		// System.out.println(link.select("li.v_stat span.num").text());
		// }

	}

	/**
	 * 进行数数据的详细教研
	 * 
	 * @param urlBranch
	 */
	public static void youkuBranchmore(String urlBranch) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		String videoId = HtmlAnalyze.getTagText(strHtml, "var videoId = '", "'");
		String showid = HtmlAnalyze.getTagText(strHtml, "var showid=\"", "'");
		System.out.println(videoId + showid);
		if (videoId == null || videoId.equals("")) {
			return;
		}

		String urlnew = "http://v.youku.com/v_vpofficiallistv5/id_" + videoId + "_showid_" + showid
				+ "_page_2?__rt=1&__ro=listitem_page2";
		System.out.println(urlnew);
		youkuBranchAsynchronous(urlnew);

	}

	/**
	 * 进行优酷网的详细类表页数据的异步刷新 进行播放列表的分页
	 * 
	 * @param urlBranch
	 */
	public static void youkuBranchAsynchronous(String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.yk-col3");
		// Element content = doc.getElementById("zy_bd");
		Elements links = doc.getElementsByTag("li");
		for (Element link : links) {
			// System.out.println(link.select("div.p-meta-title
			// a").attr("href"));
			// System.out.println(link.select("div.p-meta-title
			// a").attr("title"));
			// System.out.println(link.select("span.p-actor").text());
			System.out.println(link.select("a").attr("href"));
			System.out.println(link.select("a").attr("title"));
		}
	}

	/**
	 * 进行详细到每一集数据的判断数据的整理
	 * 
	 * @param urlBranch
	 */
	public static void youkuDetailed(String urlDetailed, String strVolumes) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
		String strHtmls = DownloadUtil.getHtmlText(urlDetailed, 1000 * 30, "UTF-8", null, null);

		String strNo = HtmlAnalyze.getTagText(strHtmls, "var videoId = '", "'");
		System.out.println(strNo);
		if (strNo == null || strNo.equals("")) {
			return;
		}

		String urlnew = "http://v.youku.com/v_vpactionInfo/id/" + strNo + "/pm/3?__rt=1&__ro=info_stat";
		System.out.println(urlnew);

		String tyPlayName;
		String serNumber = strVolumes.replaceAll("第", "").replaceAll("集", "");
		String source = "1";
		String playAmount;
		String vodeoType = "0";
		String palydate = "";
		String playUrl = urlnew;
		String tvType = "0";
		String realUrl = urlDetailed;

		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);

		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		System.out.println(
				playAmount = HtmlAnalyze.getTagText(strHtml, "总播放数:</label><span class=\"num\">", "</span></li>"));
		playAmount = playAmount.replaceAll(",", "");
		System.out.println(HtmlAnalyze.getTagText(strHtml, "顶 / 踩:</label><span class=\"num\">", "</span></li>"));
		System.out.println(HtmlAnalyze.getTagText(strHtml, "收藏:</label><span class=\"num\">", "</span></li>"));
		System.out.println(HtmlAnalyze.getTagText(strHtml, "评论:</label><span id=\"totalComment2\" class=\"num\">",
				"</span></li>"));
		System.out.println(HtmlAnalyze.getTagText(strHtml, "<label>播放指数:</label><a href=\"", "\" class=\"vrnum\""));
		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml, "\"  target=\"_blank\">", "</a>"));

		OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, playUrl, tvType,
				realUrl);

		// Document doc = Jsoup.parse(strHtml);
		//// Elements links = doc.select("div.yk-col3");
		//// Element content = doc.getElementById("zy_bd");
		// Elements links = doc.getElementsByTag("li");
		// for (Element link : links) {
		//// System.out.println(link.select("div.p-meta-title a").attr("href"));
		//// System.out.println(link.select("div.p-meta-title
		// a").attr("title"));
		//// System.out.println(link.select("span.p-actor").text());
		// System.out.println(link.select("a").attr("href"));
		// System.out.println(link.select("a").attr("title"));
		// }
	}

	/**
	 * 进行详细到每一集数据的判断数据的整理
	 * 
	 * @param urlBranch
	 */
	public static void youkuOneDetailed(String urlDetailed) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
		String strHtmls = DownloadUtil.getHtmlText(urlDetailed, 1000 * 30, "UTF-8", null, null);

		String strhtml = CommonUtil.getTagInfo(strHtmls, "<div class=\"items\">", "<div class=\"clear\"></div>");

		// System.out.println(strhtml);

		Document doc = Jsoup.parse(strhtml);
		// Elements links = doc.select("div.yk-col3");
		// Element content = doc.getElementById("zy_bd");
		Elements links = doc.select("div.item");
		for (Element link : links) {
			// System.out.println(link.select("div.p-meta-title
			// a").attr("href"));
			// System.out.println(link.select("div.p-meta-title
			// a").attr("title"));
			// System.out.println(link.select("span.p-actor").text());
			String urlever = "";
			String title = "";
			System.out.println(urlever = link.select("a").attr("href"));
			System.out.println(title = link.attr("title"));
			if (!urlever.equals("") && urlever != null && !title.equals("") && title != null) {
				try {
					youkuDetailed(urlever, title);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	public static void runstatic(){
		CommonUtil.setLog("优酷总数"+TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		String strkey = ReadTxtFile.getKeyWordFromFile("keyword.txt");
		String[] keys = strkey.split("\n");
		for (int i = 0; i < keys.length; i++) {
			System.out.println(i);
			System.out.println(keys[i]);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + keys[i]);
			// ConfigManager config = ConfigManager.getInstance();
			String url = keys[i];
			System.out.println(url);
			boolean bb = true;
			while (bb) {
				String strurl = DownloadYouku.youkuMaim(url);
				System.out.println("strurl" + strurl);
				// System.out.println(strurl!=null&&!"".equals(strurl));
				if (strurl != null && !"".equals(strurl)&&!"".equals("http://www.youku.com")) {
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + strurl);
					url = strurl;
				} else {
					bb = false;
				}

			}
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}
	
	//判断数据开始时间
	public static void TimingTime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                runstatic();
            } 
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行  
    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TimingTime(23, 59, 59);
		runstatic();
	}

}
