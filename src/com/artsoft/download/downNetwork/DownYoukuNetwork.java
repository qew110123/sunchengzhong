package com.artsoft.download.downNetwork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownYoukuNetwork {

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		String strurl = DownYoukuNetwork
				.youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		
		DownYoukuNetwork
		.youkuMaim("http://list.youku.com/category/show/c_97_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81_s_1_d_1_p_2.html");
		
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
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
			Elements links = doc.select("div.box-series div.p-thumb");
			for (Element link : links) {

				String strmainurl = "";
				System.out.println(strmainurl = link.select("div.p-thumb a").attr("href"));
//				System.out.println(link.select("div.p-meta-title a").attr("title"));
//				System.out.println(link.select("span.p-actor").text());
//				System.out.println(link.select("span.p-num").text());
//				System.out.println(link.select("span.p-status").text());
				String strmainurlHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document strmainurlHtmldoc = Jsoup.parse(strmainurlHtml);
				
				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				
				DownYoukuNetwork.youkuBranch(strmainxiangxiurl);
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
		
		
//		String score = ""; // 集数
//		score = HtmlAnalyze.getTagText(strHtml, "<label>评分:</label>", "<style type=\"text/css\">");
//		System.out.println(score);
		

		OracleOpreater.intoReputation(name, "1", Amount, "0", "", urlBranch, "1", "0");
		OracleOpreater.intoReputation(name, "1", score, "0", "", urlBranch, "1", "1");
		OracleOpreater.intoReputation(name, "1", comment, "0", "", urlBranch, "1", "2");

	}

	/**
	 * 网络剧
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();

	}

}
