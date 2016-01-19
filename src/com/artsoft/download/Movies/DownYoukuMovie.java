package com.artsoft.download.Movies;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownYoukuMovie {

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
	}

	public static void openstatic() {
		String url = "";
		String[] diqu = { "��½", "���", "̨��", "����", "����", "����", "Ӣ��", "�¹�", "�����", "���ô�", "ӡ��", "����˹", "̩��", "����" };
		String[] leixing = { "����", "����", "����", "�ƻ�", "ս��", "�ֲ�", "���", "��¼Ƭ", "����", "Ϸ��", "����", "���", "ð��", "����", "��ʷ",
				"����", "����", "����", "��ͯ", "ϲ��", "����", "����", "�˶�", "��Ƭ", "�ſ��Ʒ" };
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				// http://www.youku.com/v_olist/c_96_g_%E6%81%90%E6%80%96_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_4.html
				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_3.html
				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_1.html
				try {
					for (int i = 1; i < 30; i++) {
						url = "http://www.youku.com/v_olist/c_96_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_1_p_" + i + ".html";
						System.out.println(url);
						String urlnext = DownYoukuMovie.youkuMaim(url);
						if (urlnext.equals("") || urlnext == "" || urlnext == null) {
							break;
						}
					}

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private static String youkuMaim(String urlMain) {
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
			Elements links = doc.select("div.yk-col3");
			for (Element link : links) {

				String strmainurl = "";
				System.out.println(strmainurl = link.select("div.p-meta-title a").attr("href"));
				System.out.println(link.select("div.p-meta-title a").attr("title"));
				System.out.println(link.select("span.p-actor").text());
				System.out.println(link.select("span.p-num").text());
				System.out.println(link.select("span.p-status").text());
				DownYoukuMovie.youkuBranch(strmainurl);
				// }

				// ������һҳ���ݵ��ж�

				String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"��һҳ\"><a href=\"",
						"\"  charset=");
				if (strnexturl != null || "".equals(strnexturl)) {
					strnexturl = "http://www.youku.com" + strnexturl;
					return strnexturl;
				}
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

		String name = "";// ����
		System.out.println(name = doc.select("span.name").text());
		String Amount = "";// ������
		System.out.println(Amount = doc.select("span.play").text());
		Amount = Amount.replaceAll("�ܲ���:", "").replaceAll(",", "");

		String comment = ""; // ����
		System.out.println(comment = doc.select("span.comment").text());
		comment = comment.replaceAll("����:", "").replaceAll(",", "");

		String answer = ""; // ��
		System.out.println(answer = doc.select("span.increm").text());
		answer = answer.replaceAll("��:", "").replaceAll(",", "");
		
		

		String score = ""; // ����
		
		score = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "<style type=\"text/css\">");
//		Pattern pattern = Pattern.compile("[0-9]*");
//		Matcher isNum = pattern.matcher(score);
		//score=doc.select("span.ratingstar").text();
		if (score.contains("\r")) {
			score=HtmlAnalyze.getTagText("#"+score, "#", "\r");
		}
//		if (!isNum.matches()) {
//			score=doc.select("span.ratingstar").text().replace("����:", "");
//		}
		System.out.println(score);

		// String score = ""; // ����
		// score = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "<style
		// type=\"text/css\">");
		// System.out.println(score);
		//������
		try {
			
			OracleOpreater.intoReputation(name, "1", Amount, "0", "", urlBranch, "3", "0");
		} catch (Exception e) {
			// TODO: handle exception
		}
		//���
		try {
			
			OracleOpreater.intoReputation(name, "1", score, "0", "", urlBranch, "3", "1");
		} catch (Exception e) {
			// TODO: handle exception
		}
		//����
		try {
			OracleOpreater.intoReputation(name, "1", comment, "0", "", urlBranch, "3", "2");
			
		} catch (Exception e) {
			// TODO: handle exception
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
				}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
			}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runstatic();
		
		
		TimingTime(23, 59, 59);

	}

}
