package com.artsoft.download.downNetwork.DanJiBoFangLiang;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.downNetwork.DownYoukuNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

public class DownloadYouku_always_DanJiBoFangLiang {
	static int i = 0;

	/**
	 * �����ſ��������ҳ�������ݶβ�ѯ
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
			Elements links = doc.select("div.box-series div.p-thumb");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			for (Element link : links) {
				System.out.println("�������ݵĲɼ�" + (++i));
				if (i <= 0) {
					continue;
				}
				String strmainurl = "";
				System.out.println(strmainurl = link.select("div.p-thumb a").attr("href"));
//				System.out.println(link.select("div.p-meta-title a").attr("title"));
//				System.out.println(link.select("span.p-actor").text());
//				System.out.println(link.select("span.p-num").text());
				
				String strmainurlHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document strmainurlHtmldoc = Jsoup.parse(strmainurlHtml);
				
				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				DownloadYouku_always_DanJiBoFangLiang.youkuBranch(strmainxiangxiurl);
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
		System.out.println(HtmlAnalyze.getTagText(strHtml, "<meta name=\"title\" content=\"", "\">"));
		
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
		// System.out.println(score=HtmlAnalyze.getTagText(score, "<em
		// class=\"num\">", "</em>"));
		// answer=answer.replaceAll(",","");

		// Double.parseDouble(answer)
		// OracleOpreater.intoReputation(name, "1", Amount, "0",
		// "2015��10��23��10:43:46",urlBranch, "0", "1");
		// OracleOpreater.intoDemo(tyPlayName, source, dataAmount, vodeoType,
		// upDatedate, playUrl, tvType, dataType);

		// OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0",
		// "", urlBranch, "0", "0",urlBranch);
		// OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "",
		// urlBranch, "0", "1",urlBranch);
		// OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0",
		// "", urlBranch, "0", "2",urlBranch);

		//
		/**
		 * ��ϸÿ���б�������
		 */

		try {
			Element content = doc.getElementById("zy_bd");
			// Elements links = content.getElementsByTag("li");
			// for (Element link : links) {
			//// System.out.println(link.select("div.p-meta-title
			// a").attr("href"));
			//// System.out.println(link.select("div.p-meta-title
			// a").attr("title"));
			//// System.out.println(link.select("span.p-actor").text());
			//
			// System.out.println(link.select("a").attr("href"));
			// System.out.println(link.select("a").attr("title"));
			// }
			Elements links = null;
			links = content.getElementsByTag("li");

			if (links == null) {
				return;
			}
			Element link = links.first();
			String urlDetailedfirst = "";
			// String nameDetailedfirst = "";
			System.out.println(urlDetailedfirst = link.select("a").attr("href"));
			// System.out.println(nameDetailedfirst =
			// link.select("a").attr("title"));

			youkuOneDetailed(urlDetailedfirst);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//
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
	 * �����ſ�������ϸ���ҳ���ݵ��첽ˢ�� ���в����б��ķ�ҳ
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
		
		String sid= HtmlAnalyze.getTagText(strHtmls, "var showid=\"", "\";");
		System.out.println(sid);
		if (sid == null || sid.equals("")) {
			return;
		}
		
		String catid= HtmlAnalyze.getTagText(strHtmls, "var catId=\"", "\";");
		System.out.println(catid);
		if (catid == null || catid.equals("")) {
			return;
		}
		
		String urlnew = "http://v.youku.com/v_vpactionInfo/id/" + strNo + "/pm/3?__rt=1&__ro=info_stat";
		
		urlnew="http://v.youku.com/QVideo/~ajax/getVideoPlayInfo?__rt=1&__ro=&id="+strNo+"&sid="+sid+"&type=vv&catid="+catid;
		System.out.println(urlnew);

		String tyPlayName;
		String serNumber = strVolumes.replaceAll("��", "").replaceAll("��", "");
		String source = "1";
		String playAmount;
		String vodeoType = "0";
		String palydate = "";
		String playUrl = urlnew;
		String tvType = "1";
		String realUrl = urlDetailed;

		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);

		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		System.out.println(
				playAmount = HtmlAnalyze.getTagText(strHtml, "{\"vv\":", ","));
		playAmount = playAmount.replaceAll(",", "");
//		System.out.println(HtmlAnalyze.getTagText(strHtml, "�� / ��:</label><span class=\"num\">", "</span></li>"));
//		System.out.println(HtmlAnalyze.getTagText(strHtml, "�ղ�:</label><span class=\"num\">", "</span></li>"));
//		System.out.println(HtmlAnalyze.getTagText(strHtml, "����:</label><span id=\"totalComment2\" class=\"num\">",
//				"</span></li>"));
//		System.out.println(HtmlAnalyze.getTagText(strHtml, "<label>����ָ��:</label><a href=\"", "\" class=\"vrnum\""));
		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtmls, "<meta name=\"title\" content=\"", "\">"));

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
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

//		String strkey = ReadTxtFile.getKeyWordFromFile("keyword.txt");
//		String[] keys = strkey.split("\n");
//		for (int i = 0; i < keys.length; i++) {
//			// System.out.println(i);
//			// System.out.println(keys[i]);
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + keys[i]);
//			// ConfigManager config = ConfigManager.getInstance();
//			String url = keys[i];
//			System.out.println(url);
//			boolean bb = true;
//			while (bb) {
//				String strurl = DownloadYouku_always.youkuMaim(url);
//				System.out.println("strurl" + strurl);
//				// System.out.println(strurl!=null&&!"".equals(strurl));
//				if (strurl != null && !"".equals(strurl) && !"".equals("http://www.youku.com")) {
//					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + strurl);
//					url = strurl;
//				} else {
//					bb = false;
//				}
//
//			}
//		}
		
		
//		String url = "";
//		String[] diqu = { "��½", "���", "̨��", "����", "�ձ�", "����", "Ӣ��", "̩��", "�¼���" };
//		String[] leixing = { "��װ", "����", "����", "����", "��", "�ƻ�", "����", "��ʷ", "��ͯ", "ũ��", "����", "��ͥ", "��Ц", "ż��", "����", "ʱװ", "�ſ��Ʒ" };
//		for (String diqutxt : diqu) {
//			for (String leixingtxt : leixing) {
//				System.out.println(diqutxt + leixingtxt);
//				// http://www.youku.com/v_olist/c_96_g_%E6%81%90%E6%80%96_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_4.html
//				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_3.html
//				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_1.html
//				// try {
//				for (int i = 1; i < 30; i++) {
//					try {
//						url = "http://list.youku.com/category/show/c_97_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
//								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_1_p_" + i + ".html";
//					} catch (UnsupportedEncodingException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					System.out.println(url);
//					String urlnext = youkuMaim(url);
//					if (urlnext.equals("") || urlnext == "" || urlnext == null) {
//						break;
//					}
//				}
//
//				// } catch (UnsupportedEncodingException e) {
//				// // TODO Auto-generated catch block
//				// e.printStackTrace();
//				// }
//			}
//
//		}
		
		String strurl = youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		
		youkuMaim("http://list.youku.com/category/show/c_97_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81_s_1_d_1_p_2.html");

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(0, 59, 59);
//		String url = "http://list.youku.com/category/show/c_97_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81_a_%E6%96%B0%E5%8A%A0%E5%9D%A1_s_1_d_1.html";
//		youkuMaim(url);
		// runstatic();
	}

}