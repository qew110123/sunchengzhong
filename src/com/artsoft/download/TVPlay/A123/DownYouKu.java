package com.artsoft.download.TVPlay.A123;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class DownYouKu {
	static int i = 0;

	/**
	 * �����ſ��������ҳ�������ݶβ�ѯ
	 * 
	 * @param urlMain
	 ////////////////////////////////////
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
				DownYouKu.youkuBranch(strmainurl);
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
	 ///////////////////////////////
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
		System.out.println(score);
		
		String times = ""; // ��ӳ:
		times = HtmlAnalyze.getTagText(strHtml, "��ӳ:</label>", "</span>");
		System.out.println(times=times.replaceAll("-", ""));
		
		String classstr = ""; // ����:
		classstr = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "����");
		System.out.println(classstr=classstr.replaceAll(" 					", ""));

		String peopleste = ""; // ren:
		peopleste = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "�ܲ���:");
		System.out.println(peopleste=peopleste.replaceAll(" 					", ""));
		
//		OracleOpreater��intoTEM_NETWORK_TVPLAY_AMOUNT
		OracleOpreater.intoTEM_NETWORK_TVPLAY_AMOUNT(name, 1, Integer.parseInt(Amount), times, urlBranch, classstr, peopleste, Integer.parseInt(comment));
	}

	

	///////////////////////
	public static void main(String[] args) {
//		youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_r_2015.html");
		
		//http://www.youku.com/v_olist/c_97_g__a__sg__mt__lg__q__s_1_r_2015_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_2.html
		//http://www.youku.com/v_olist/c_97_g__a__sg__mt__lg__q__s_1_r_2015_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_3.html
		for (int i = 1; i < 10; i++) {
			youkuMaim("http://www.youku.com/v_olist/c_97_g__a__sg__mt__lg__q__s_1_r_2015_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_"+i+".html");
		}
	}

}
