package com.artsoft.download.TVPlay.platform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownloadIqiyi {

	public static void iQiYiBranch(String urlBranch, String name, String score) {

		TvPlay playtv = new TvPlay();
		playtv.setTvplay_url(urlBranch);
		System.out.println(name);
		playtv.setTvplay_name(name);
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		// String name = HtmlAnalyze.getTagText(strHtml, "<meta
		// name=\"keywords\" content=\"", "\" /> ");
		
		String PRODUCTION_AREA="";
		PRODUCTION_AREA=HtmlAnalyze.getTagText(strHtml, "������", "</p>");
		playtv.setProduction_area(PRODUCTION_AREA);
		
		String SUBJECT="";
		SUBJECT=HtmlAnalyze.getTagText(strHtml, "���ͣ�", "</p>");
		SUBJECT=SUBJECT.replace("\r\n", "").replace(" ", "");
		playtv.setSubject(SUBJECT);
		
		
//		String daoyan = "";// ����
//		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "id=\"datainfo-director-list\">", "</p>", true, 0);
//		String[] daoyanlist = daoyanAll.split("</span>/");
//		int i = 0;
//		for (String stringtext : daoyanlist) {
//			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
//			// daoyan=daoyan+textss+"|"+urlss;
//			if (!(urlss.equals("") && textss.equals(""))) {
//				daoyan = daoyan + textss + "|" + urlss;
//			}
//			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
//				daoyan = daoyan + ",";
//			}
//
//			i += 1;
//		}
//		System.out.println(daoyan);
//		playtv.setDirector(daoyan);
		
		String DIRECTOR="";
		DIRECTOR=HtmlAnalyze.getTagText(strHtml, "���ݣ�", "</p>");
		playtv.setDirector(DIRECTOR);
		
		

		String yanyuan = "";// ��Ա
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "<p class=\"li-large\">���ݣ�", "</p>", true, 0);
		String[] yanyuanlist = yanyuanAll.split("/a>");
		int i = 0;
		for (String stringtext : yanyuanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			if (!(urlss.equals("") && textss.equals(""))) {
				yanyuan = yanyuan + textss + "|" + urlss;
			}
			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
				yanyuan = yanyuan + ",";
			}

			i += 1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId:", ",");
		//
		// String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId +
		// "/";
		// String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30,
		// "UTF-8", null, null);
		// System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, ":",
		// "}"));
		// try {
		// OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch,
		// "3", "0");
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "style=\"display: none;\"><span class=\"c-999\">��飺</span>", "</span>");
		System.out.println(detail);
		
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"c-999\">��飺</span>", "</span>");
		}
		playtv.setBasic_info(detail);

		// playtv.setClassnum(2);
		playtv.setSOURCE(2);
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);

	}

	private static void mainurl(String urlMain) {
		// TODO Auto-generated method stub

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
		if (strHtml == null || strHtml.equals("")) {
			// return;
			return;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.site-piclist_pic");
		for (Element link : links) {
			String strmainurl = "";
			System.out.println(strmainurl = link.select("a.site-piclist_pic_link").attr("href"));
			// System.out.println(link.select("a.site-piclist_pic_link").attr("title"));
			// iQiYiBranch(strmainurl);
			String name = "";
			System.out.println(name = link.select("a").attr("title"));
			String score = "";
			System.out.println(score = link.select("span.score").text());
			iQiYiBranch(strmainurl, name, score);
		}
	}

	/**
	 * http://list.iqiyi.com/www/2/-100001------------4-1--iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-20------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-21------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-23------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-24------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-27------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-29------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-30------------4-1-1-iqiyi--.html
	 * 
	 * @param url
	 * @return
	 */

	private static String youkuMaim(String url) {
		mainurl(url);
		url = url.replaceAll("1-1-iqiyi--.html", "");
		for (int i = 1; i < 30; i++) {
			mainurl(url + i + "-1-iqiyi--.html");
		}

		return null;
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

		String strkey = ReadTxtFile.getKeyWordFromFile("keywordiqiyi.txt");
		String[] keys = strkey.split("\n");
		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i]);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + keys[i]);
			String url = keys[i];
			System.out.println(url);
			boolean bb = true;
			DownloadIqiyi.youkuMaim(url);
		}
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
		// TODO Auto-generated method stub

		// TimingTime(23, 59, 59);

		 runstatic();

		iQiYiBranch("http://www.iqiyi.com/a_19rrhaj295.html#vfrm=2-4-0-1", "������İٴ�", "6.8");
	}
}
