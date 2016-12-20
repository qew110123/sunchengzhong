package com.artsoft.download.TVPlay;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.TVPlay.Downloadpool.DownloadYoukupool;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownloadYouku {

	static ThreadPool pool = new ThreadPool(20);
	static int i = 0;

	/**
	 * ���������ݵĲɼ� �����ſ��������ҳ�������ݶβ�ѯ
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
//			Elements links = doc.select("div.yk-col3");
//			for (Element link : links) {
//				System.out.println("�������ݵĲɼ�" + (++i));
//				if (i <= 0) {
//					continue;
//				}
//				String strmainurl = "";
//				System.out.println(strmainurl = link.select("div.p-meta-title a").attr("href"));
////				System.out.println(link.select("div.p-meta-title a").attr("title"));
////				System.out.println(link.select("span.p-actor").text());
////				System.out.println(link.select("span.p-num").text());
//
//				while (pool.getPoolNum() > 20) {
//					try {
//						System.out.println("�߳���������10���ȴ�5s");
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				// DownloadYouku.youkuBranch(strmainurl);
//				System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
//				pool.performTask(new DownloadYoukupool(strmainurl));
//
//			}
			
			
			Elements links = doc.select("div.box-series div.p-thumb");
			for (Element link : links) {

//				String strmainurl = "";
//				System.out.println(strmainurl = link.select("a").attr("href"));
////				System.out.println(link.select("div.p-meta-title a").attr("title"));
////				System.out.println(link.select("span.p-actor").text());
////				System.out.println(link.select("span.p-num").text());
////				System.out.println(link.select("span.p-status").text());
//				DownYoukuMovie.youkuBranch1(strmainurl);
				System.out.println("�������ݵĲɼ�" + (++i));
//				if (i <= 0) {
//					continue;
//				}
//				String strmainurl = "";
//				System.out.println(strmainurl = link.select("div.p-thumb a").attr("href"));
//				System.out.println(link.select("div.p-meta-title a").attr("title"));
//				System.out.println(link.select("span.p-actor").text());
//				System.out.println(link.select("span.p-num").text());

//				while (pool.getPoolNum() > 20) {
//					try {
//						System.out.println("�߳���������10���ȴ�5s");
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				
				// DownloadYouku.youkuBranch(strmainurl);
//				System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
//				pool.performTask(new DownloadYoukupool(strmainurl));
				
//				String strmainurlHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
//				Document strmainurlHtmldoc = Jsoup.parse(strmainurlHtml);
//				
//				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				
				String strmainurl = "";
				System.out.println(strmainurl = link.select("a").attr("href"));
				if (!strmainurl.contains("http://")) {
					strmainurl=strmainurl.replace("//", "http://");
				}
				
				Document strmainurlHtmldoc = Jsoup.connect(strmainurl).get();
				
				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				if (strmainxiangxiurl==null||strmainxiangxiurl.equals("")||strmainxiangxiurl.equals("http://tv.youku.com/")||strmainxiangxiurl.equals("//tv.youku.com/")) {
//					System.out.println(strmainurlHtmldoc);
					strmainxiangxiurl=HtmlAnalyze.getTagText(strmainurlHtmldoc.toString(), "desc-link\" href=\"","\"");
				}
				if (!strmainxiangxiurl.contains("http://")) {
					strmainxiangxiurl=strmainxiangxiurl.replace("//", "http://");
				}
				
				
				DownloadYouku.youkuBranch(strmainxiangxiurl);
				
			}

			// ������һҳ���ݵ��ж�

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"��һҳ\"><a href=\"",
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

	/**
	 * �����ſ�������ϸ���ҳ���ݵĲɼ�
	 * 
	 * @param urlBranch
	 */
	public static void youkuBranch(String urlBranch) {
		
		if (!urlBranch.contains("http://")) {
			urlBranch=urlBranch.replace("//", "http://");
		}
		
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
		 * �ܲ���: ����: ��:
		 */

		String name = "";// ����
		name = doc.select("span.name").text();
		if (name.equals("")) {
			name=HtmlAnalyze.getTagText(strHtml, "<a title=\"", "\"");
		}
		String Amount = "";// ������
		Amount = doc.select("span.play").text();
		if (Amount.equals("")) {
			Amount=HtmlAnalyze.getTagText(strHtml, "�ܲ�������", "<");
		}
		Amount = Amount.replaceAll("�ܲ���:", "").replaceAll(",", "");
		

		String comment = ""; // ����
		comment = doc.select("span.comment").text();
		if (comment.equals("")) {
			comment=HtmlAnalyze.getTagText(strHtml, "���ۣ�", "<");
		}
		comment = comment.replaceAll("����:", "").replaceAll(",", "");

		String answer = ""; // ��
		answer = doc.select("span.increm").text();
		if (answer.equals("")) {
			answer=HtmlAnalyze.getTagText(strHtml, "����", "<");
		}
		answer = answer.replaceAll("��:", "").replaceAll(",", "");

		String score = ""; // ����
		score = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "<style type=\"text/css\">");
		if (score.equals("")) {
			score=HtmlAnalyze.getTagText(strHtml, "class=\"star-num\">", "<");
		}
		System.out.println(score);

		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "0", "0", urlBranch);
		// "2015��10��23��10:43:46",urlBranch, "0", "1");
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "0", "1", urlBranch);
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "0", "2", urlBranch);
		// OracleOpreater.intoDemo(tyPlayName, source, dataAmount, vodeoType,
		// upDatedate, playUrl, tvType, dataType);

		//
		/**
		 * ��ϸÿ���б������
		 * 
		 * ʱ��2015��11��16��16:52:33 ������ϸ���ݾ��Ĳɼ�
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
		// * ��Ԥ��Ƭ ���� ��������Ʒ mv ���� ��5�� һ��20��
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
	 * �����ſ�������ϸ���ҳ���ݵĲɼ�
	 * 2016��5��25��10:13:23
	 * @param urlBranch
	 */
	public static void youkuBranch1(String urlBranch) {
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
//		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//		if (strHtml == null || strHtml.equals("")) {
//			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//		}
//		if (strHtml == null || strHtml.equals("")) {
//			return;
//		}
//		Document doc = Jsoup.parse(strHtml);
//		// Elements links = doc.select("div.yk-col3");
//
//		/**
//		 * �ܲ���: ����: ��:
//		 */
//
//		String name = "";// ����
//		System.out.println(name = doc.select("span.name").text());
//		String Amount = "";// ������
//		System.out.println(Amount = doc.select("span.play").text());
//		Amount = Amount.replaceAll("�ܲ���:", "").replaceAll(",", "");
//
//		String comment = ""; // ����
//		System.out.println(comment = doc.select("span.comment").text());
//		comment = comment.replaceAll("����:", "").replaceAll(",", "");
//
//		String answer = ""; // ��
//		System.out.println(answer = doc.select("span.increm").text());
//		answer = answer.replaceAll("��:", "").replaceAll(",", "");
//
//		String score = ""; // ����
//		score = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "<style type=\"text/css\">");
//		System.out.println(score);
//		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "0", "0", urlBranch);
//		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "0", "1", urlBranch);
//		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "0", "2", urlBranch);
		
		
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
		 * �ܲ���: ����: ��:
		 */

		String name = "";// ����
//				System.out.println(name = doc.select("span.name").text());
		System.out.println(name=HtmlAnalyze.getTagText(strHtml, "<meta name=\"title\" content=\"", "\">"));
		
		
		///http://v.youku.com/QVideo/~ajax/getVideoPlayInfo?__rt=1&__ro=&id=391497703&sid=283994&type=vv%2Cpermission&catid=96
		
		String strNo = HtmlAnalyze.getTagText(strHtml, "var videoId = '", "'");
		System.out.println(strNo);
		if (strNo == null || strNo.equals("")) {
			return;
		}
		
		String sid= HtmlAnalyze.getTagText(strHtml, "var showid=\"", "\";");
		System.out.println(sid);
		if (sid == null || sid.equals("")) {
			return;
		}
		
		String catid= HtmlAnalyze.getTagText(strHtml, "var catId=\"", "\";");
		System.out.println(catid);
		if (catid == null || catid.equals("")) {
			return;
		}
		
		String video_owner = HtmlAnalyze.getTagText(strHtml, "var video_owner = '", "';");
		System.out.println(video_owner );
		if (video_owner  == null || video_owner .equals("")) {
			return;
		}
		
		String urlnew = "http://v.youku.com/v_vpactionInfo/id/" + strNo + "/pm/3?__rt=1&__ro=info_stat";
		
		urlnew="http://v.youku.com/QVideo/~ajax/getVideoPlayInfo?__rt=1&__ro=&id="+strNo+"&sid="+sid+"&type=vv&catid="+catid;
//				System.out.println(urlnew);
		
		String strHtmlurlnew = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);

		if (strHtmlurlnew == null || strHtmlurlnew.equals("")) {
			strHtmlurlnew = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtmlurlnew == null || strHtmlurlnew.equals("")) {
			return;
		}

		String Amount = "";// ������
		System.out.println(
				Amount = HtmlAnalyze.getTagText(strHtmlurlnew, "{\"vv\":", ","));
		Amount = Amount.replaceAll(",", "");
		
//				System.out.println(Amount = doc.select("span.play").text());
//				Amount = Amount.replaceAll("�ܲ���:", "").replaceAll(",", "");

		String comment = ""; // ����
		
		//http://comments.youku.com/comments/~ajax/getStatus.html?__ap=%7B%22videoid%22%3A%22391497703%22%2C%22userid%22%3A%2297454045%22%2C%22oldSid%22%3A-1%2C%22showid%22%3A%22283994%22%2C%22episode%22%3A%22%22%7D&__ai=&__callback=commentInfoCallback
		System.out.println(comment = doc.select("span.comment").text());
		comment = comment.replaceAll("����:", "").replaceAll(",", "");
		String urlcomment="http://comments.youku.com/comments/~ajax/getStatus.html?__ap=%7B%22videoid%22%3A%22"+strNo+"%22%2C%22userid%22%3A%22"+video_owner+"%22%2C%22oldSid%22%3A-1%2C%22showid%22%3A%22"+sid+"%22%2C%22episode%22%3A%22%22%7D&__ai=&__callback=commentInfoCallback";
		String strHtmlurlcomment = DownloadUtil.getHtmlText(urlcomment, 1000 * 30, "UTF-8", null, null);
		System.out.println(
				comment = HtmlAnalyze.getTagText(strHtmlurlcomment, "total\":\"", "\""));
		comment=comment.replaceAll(",", "");

//				String answer = ""; // ��
//				System.out.println(answer = doc.select("span.increm").text());
//				answer = answer.replaceAll("��:", "").replaceAll(",", "");

		String score = ""; // ����

		//<span class="score_yk">
		score = HtmlAnalyze.getTagText(strHtml, "class=\"score_yk\">", "</strong>");
		// Pattern pattern = Pattern.compile("[0-9]*");
		// Matcher isNum = pattern.matcher(score);
		// score=doc.select("span.ratingstar").text();
		if (score.contains("\r")) {
			score = HtmlAnalyze.getTagText("#" + score, "#", "\r");
		}
		// if (!isNum.matches()) {
		// score=doc.select("span.ratingstar").text().replace("����:", "");
		// }
		System.out.println(score);

		// String score = ""; // ����
		// score = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "<style
		// type=\"text/css\">");
		// System.out.println(score);
		// ������
		try {

//					OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "3", "0", urlBranch);
			OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "0", "0", urlBranch);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// ���
		try {

//					OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "3", "1", urlBranch);
			OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "0", "1", urlBranch);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// ����
		try {
//					OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "3", "2", urlBranch);
			OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "0", "2", urlBranch);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * ���������ݵ���ϸ����
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
	 * �����ſ�������ϸ���ҳ���ݵ��첽ˢ�� ���в����б�ķ�ҳ
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
	 * ������ϸ��ÿһ�����ݵ��ж����ݵ�����
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
		String serNumber = strVolumes.replaceAll("��", "").replaceAll("��", "");
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
				playAmount = HtmlAnalyze.getTagText(strHtml, "�ܲ�����:</label><span class=\"num\">", "</span></li>"));
		playAmount = playAmount.replaceAll(",", "");
		System.out.println(HtmlAnalyze.getTagText(strHtml, "�� / ��:</label><span class=\"num\">", "</span></li>"));
		System.out.println(HtmlAnalyze.getTagText(strHtml, "�ղ�:</label><span class=\"num\">", "</span></li>"));
		System.out.println(HtmlAnalyze.getTagText(strHtml, "����:</label><span id=\"totalComment2\" class=\"num\">",
				"</span></li>"));
		System.out.println(HtmlAnalyze.getTagText(strHtml, "<label>����ָ��:</label><a href=\"", "\" class=\"vrnum\""));
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
	 * ������ϸ��ÿһ�����ݵ��ж����ݵ�����
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
	
	
	

	public static void runstatic() {
		CommonUtil.setLog("�ſ�����" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		openstatic();
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		openordor();
//		Thread.sleep(millis);
//		try {
////			System.out.println("�ȴ�2Сʱ");
//			CommonUtil.setLog("��ǰʱ��:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			Thread.sleep(1000 * 60 * 60 * 2);
//			openstatic();
//			CommonUtil.setLog("�ſ�ȴ�2Сʱ" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			System.out.println("�ȴ�2Сʱ");
//			CommonUtil.setLog("��ǰʱ��:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			Thread.sleep(1000 * 60 * 60 * 3);
//			openstatic();
//			CommonUtil.setLog("�ſ�ȴ�3Сʱ" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
	}
	
	/**
	 * 
	 * @param datestr
	 *            �����ַ���
	 * @param day
	 *            ���������Ϊ������ʾ֮��Ϊ������ʾ֮ǰ
	 * @return ָ�������ַ���n��֮ǰ����֮�������
	 */
	public static String getBeforeAfterDate(String datestr, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		java.sql.Date olddate = null;
		try {
			df.setLenient(false);
			olddate = new java.sql.Date(df.parse(datestr).getTime());
		} catch (ParseException e) {
			throw new RuntimeException("����ת������");
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewDay = Day + day;

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);
		// System.out.println(df.format(cal.getTimeInMillis()));

		return df.format(cal.getTimeInMillis());
	}
	/**
	 * 2016��5��27��16:09:57
	 * �����������ݵĸ�ϸ
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuTVplay(date_date);
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				
				DownloadYouku.youkuBranch(listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	private static void openstatic() {
		// TODO Auto-generated method stub
		String url = "";
		String[] diqu = { "��½", "���", "̨��", "����", "�ձ�", "����", "Ӣ��", "̩��", "�¼���" };
		String[] leixing = { "��װ", "����", "����", "����", "��", "�ƻ�", "����", "��ʷ", "��ͯ", "ũ��", "����", "��ͥ", "��Ц", "ż��", "����", "ʱװ", "�ſ��Ʒ" };
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				// http://www.youku.com/v_olist/c_96_g_%E6%81%90%E6%80%96_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_4.html
				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_3.html
				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_1.html
				// try {
				for (int i = 1; i < 30; i++) {
					try {
						url = "http://www.youku.com/v_olist/c_97_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_1_p_" + i + ".html";
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(url);
					String urlnext = DownloadYouku.youkuMaim(url);
					if (urlnext.equals("") || urlnext == "" || urlnext == null) {
						break;
					}
				}

				// } catch (UnsupportedEncodingException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			}

		}

	}

	// �ж����ݿ�ʼʱ��
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
		calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
		calendar.set(Calendar.SECOND, ss); // ������

		Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------�趨Ҫָ������--------");
				runstatic();
				
			}
		}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
	}
	
	
	/**
	 * 
	 * �ſᲥ����
	 * 2016��11��25��10:41:55
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		openordor();
//		openstatic();
//		runstatic();
		
		
		TimingTime(1, 59, 59);
		
//		openstatic();
	}

}
