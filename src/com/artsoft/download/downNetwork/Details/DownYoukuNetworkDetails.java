package com.artsoft.download.downNetwork.Details;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.Movies.Details.DownYouKuDetails;
import com.artsoft.download.TVPlay.platform.DownYouKuplatform;
import com.artsoft.download.downNetwork.DownYoukuNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownYoukuNetworkDetails {

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		String strurl = youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		
		youkuMaim("http://list.youku.com/category/show/c_97_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81_s_1_d_1_p_2.html");
		
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
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
				
				youkuBranch(strmainxiangxiurl);
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
		playtv.setDATA_TYPE(1);
		
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
	
	
	}

	/**
	 * �����
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();

	}

}
