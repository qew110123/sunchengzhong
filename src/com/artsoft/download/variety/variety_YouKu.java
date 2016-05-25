package com.artsoft.download.variety;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.TVPlay.DownloadYouku_always;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class variety_YouKu {
	static int i = 0;
	
	
	
	
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

		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "2", "0", urlBranch);
//		// "2015年10月23日10:43:46",urlBranch, "0", "1");
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "2", "1", urlBranch);
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "2", "2", urlBranch);
		// OracleOpreater.intoDemo(tyPlayName, source, dataAmount, vodeoType,
		// upDatedate, playUrl, tvType, dataType);

		//
		/**
		 * 详细每集列表的数据
		 * 
		 * 时间2015年11月16日16:52:33 进行详细数据决的采集
		 */

		// try {
		// Element content = doc.getElementById("zy_bd");
		// Elements links = null;
		// links = content.getElementsByTag("li");
		// if (links == null) {
		// return;
		// }
		// Element link = links.first();
		// String urlDetailedfirst = "";
		// System.out.println(urlDetailedfirst = link.select("a").attr("href"));
		// youkuOneDetailed(urlDetailedfirst);
		// } catch (Exception e) {
		// }

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
	 * 进行优酷网的类表页进行数据段查询
	 * 
	 * @param urlMain
	 */
	public static String youkuMaim(String urlMain) {
		try {
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				// return;
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.box-series div.p-thumb");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			for (Element link : links) {
				System.out.println("进行数据的采集" + (++i));
				if (i <= 0) {
					continue;
				}
				String strmainurl = "";
				System.out.println(strmainurl = link.select("div.p-thumb a").attr("href"));
				
				String strmainurlHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document strmainurlHtmldoc = Jsoup.parse(strmainurlHtml);
				
				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				youkuBranch(strmainxiangxiurl);
			}
			
			
			

			// 进行下一页数据的判断

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"下一页\"><a href=\"",
					"\"  charset=");
			if (strnexturl != null && !"".equals(strnexturl) && !"http://www.youku.com".equals(strnexturl)) {
				strnexturl = "http://www.youku.com" + strnexturl;
				return strnexturl;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		String url = "";
		String[] diqu = { "大陆", "香港", "台湾", "韩国", "日本" , "美国", "澳大利亚" };
		String[] leixing = { "优酷出品", "优酷牛人", "脱口秀", "真人秀", "选秀", "美食", "旅游", "汽车", "访谈", "纪实", "搞笑", "时尚", "晚会", "理财", "演唱会", "曲艺", "益智", "音乐", "舞蹈", "体育娱乐", "游戏", "生活" };
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				// try {
				for (int i = 1; i < 30; i++) {
					try {
						url = "http://list.youku.com/category/show/c_85_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_" + i + ".html";
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(url);
					String urlnext = youkuMaim(url);
					if (urlnext.equals("") || urlnext == "" || urlnext == null) {
						break;
					}
				}

			}

		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
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
			}, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(0, 29, 59);

	}

}
