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
	 * 进行优酷网的类表页进行数据段查询
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
				System.out.println("进行数据的采集" + (++i));
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

			// 进行下一页数据的判断

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"下一页\"><a href=\"",
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
	 * 进行优酷网的详细类表页数据的采集
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
		
		String times = ""; // 上映:
		times = HtmlAnalyze.getTagText(strHtml, "上映:</label>", "</span>");
		System.out.println(times=times.replaceAll("-", ""));
		
		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtml, "<label>类型:</label>", "导演");
		System.out.println(classstr=classstr.replaceAll(" 					", ""));

		String peopleste = ""; // ren:
		peopleste = HtmlAnalyze.getTagText(strHtml, "<label>主演:</label>", "总播放:");
		System.out.println(peopleste=peopleste.replaceAll(" 					", ""));
		
//		OracleOpreater。intoTEM_NETWORK_TVPLAY_AMOUNT
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
