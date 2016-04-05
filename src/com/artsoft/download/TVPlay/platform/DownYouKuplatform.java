package com.artsoft.download.TVPlay.platform;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.TVPlay.DownloadIqiyi;
import com.artsoft.download.TVPlay.DownloadLetv;
import com.artsoft.download.TVPlay.DownloadSohu;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.download.TVPlay.Downloadkankan;
import com.artsoft.download.TVPlay.Downloadpptv;
import com.artsoft.download.TVPlay.Downloadqq;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import oracle.net.aso.p;

public class DownYouKuplatform {
	static int i = 0;

	/**
	 * �����ſ��������ҳ�������ݶβ�ѯ
	 * 
	 * @param urlMain
	 *            ////////////////////////////////////
	 */
	public static String youkuMaim(String urlMain) {
		try {
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.yk-col3");
			for (Element link : links) {
				System.out.println("�������ݵĲɼ�" + (++i));
				if (i <= 0) {
					continue;
				}
				String strmainurl = "";
				System.out.println(strmainurl = link.select("div.p-meta-title a").attr("href"));
				System.out.println(link.select("div.p-meta-title a").attr("title"));
				System.out.println(link.select("span.p-actor").text());
				System.out.println(link.select("span.p-num").text());
				DownYouKuplatform.youkuBranch(strmainurl);
			}

			// ������һҳ���ݵ��ж�

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"��һҳ\"><a href=\"",
					"\"  charset=");
			if (strnexturl != null || "".equals(strnexturl)) {
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
	 *            ///////////////////////////////
	 */
	public static void youkuBranch(String urlBranch) {
		TvPlay playtv = new TvPlay();
		playtv.setTvplay_url(urlBranch);
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
		playtv.setTvplay_name(name);
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

		String bieming = "";// ����

		bieming = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "</span>");
		System.out.println(bieming = bieming.replace("	", ""));
		playtv.setAlias_en(bieming);
		String shichang = ""; // ʱ��
		shichang = HtmlAnalyze.getTagText(strHtml, "<label>ʱ��:</label>", "</span>");
		playtv.setTime_length(shichang);

		String times = ""; // ��ӳ:
		times = HtmlAnalyze.getTagText(strHtml, "��ӳ:</label>", "</span>");
		// System.out.println(times=times.replaceAll("-", ""));
		playtv.setShow_date(times);

		String niandai = ""; // ���
		niandai = HtmlAnalyze.getTagText(strHtml, "class=\"pub\">", "</span>");
		playtv.setShoot_time(niandai);

		String diqu = ""; // ����
		diqu = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "</span>");
		playtv.setProduction_area(diqu);

		String classstr = ""; // ����:
		classstr = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "����");
		System.out.println(classstr = classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);

		String peopleste = ""; // ren:
		peopleste = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "�ܲ���:");
		System.out.println(peopleste = peopleste.replaceAll(" 					", ""));

		String daoyan = "";// ����
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "</span>", true, 0);
		String[] daoyanlist = daoyanAll.split(" /");
		int i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			daoyan = daoyan + textss + "|" + urlss;
			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
				daoyan = daoyan + ",";
			}

			i += 1;
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);

		String yanyuan = "";// ��Ա
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "</span>", true, 0);
		String[] yanyuanlist = yanyuanAll.split(" /");
		i = 0;
		for (String stringtext : yanyuanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			yanyuan = yanyuan + textss + "|" + urlss;
			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
				yanyuan = yanyuan + ",";
			}

			i += 1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"long\" style=\"display:none;\">", "</span>");
		System.out.println(detail);
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		playtv.setBasic_info(detail);

		// playtv.setClassnum(1);
		playtv.setSOURCE(1);
		// basic_info

		// OracleOpreater.intoTEM_NETWORK_TVPLAY_AMOUNT(name, 1,
		// Integer.parseInt(Amount), times, urlBranch, classstr, peopleste,
		// Integer.parseInt(comment));
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
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
						url = "http://www.youku.com/v_olist/c_97_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_1_p_" + i + ".html";
						System.out.println(url);
						String urlnext = DownYouKuplatform.youkuMaim(url);
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

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		openstatic();
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
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
		}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
	}

	public static void main(String[] args) {
		// openstatic();
		TimingTime(4, 00, 00);
	}

}
