package com.artsoft.download.downNetwork.Details;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownSohuNwtwordDetails {

	public static void sohuBranch(String urlBranch) {
		TvPlay playtv = new TvPlay();
		playtv.setTvplay_url(urlBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		// <h2>���Ӿ磺<span class="vname">
		String name = HtmlAnalyze.getTagText(strHtml, "<span class=\"vname\">", "</span>");
		System.out.println(name);
		playtv.setTvplay_name(name);

		// <span>������</span>
		String ALIAS_CN = HtmlAnalyze.getTagText(strHtml, "<span>������</span>", "</li>");
		if (ALIAS_CN!=null) {
			ALIAS_CN=ALIAS_CN.replace(" ", "");
		}
		playtv.setAlias_cn(ALIAS_CN);

		String times = ""; // ��ӳ:
		times = HtmlAnalyze.getTagText(strHtml, "��ݣ�", "</span>");
		// System.out.println(times=times.replaceAll("-", ""));
		if (times.equals("")) {

			times = HtmlAnalyze.getTagText(strHtml, "<span>��ӳʱ�䣺</span>", "</li>");
		}
		playtv.setShow_date(times);

		String classstr = ""; // ����:
		classstr = HtmlAnalyze.getTagText(strHtml, "���ͣ�", "</li>");
		System.out.println(classstr = classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);

		String shichang = ""; // ʱ��
		shichang = HtmlAnalyze.getTagText(strHtml, "ʱ����", "</span>");
		playtv.setTime_length(shichang);

		String diqu = ""; // ����
		diqu = HtmlAnalyze.getTagText(strHtml, "������", "</span>");
		if (diqu.equals("") || diqu == null || diqu.equals("null")) {
			diqu = HtmlAnalyze.getTagText(strHtml, "������", "</li>");
		}
		playtv.setProduction_area(diqu);

		String daoyan = "";// ����
//		daoyan = HtmlAnalyze.getTagText(strHtml, "���ݣ�", "</span>");
		if (daoyan.equals("") || daoyan == null || daoyan.equals("null")) {
//			String daoyan = "";// ����
			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "���ݣ�</span>", "</li>", true, 0);
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
			
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);

		String yanyuan = "";// ��Ա
//		yanyuan = HtmlAnalyze.getTagText(strHtml, "���ݣ�", "</p>");
//		yanyuan = yanyuan.replaceAll("\r\n\t", "").replaceAll("\t", "").replaceAll("/", "|,");
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "���ݣ�</span>", "</li>", true, 0);
		String[] yanyuanlist = yanyuanAll.split("class=\"mLR6\">/</span>");
		int i = 0;
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
		detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"all_info\">", "</p>");
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"full_intro \" style=\"display:none\">", "</span>");
		}
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short_intro hide\">", "</span>");
		}
		if (detail!=null) {
			detail=detail.replace("&nbsp;", "");
		}
		System.out.println(detail);
		playtv.setBasic_info(detail);

		playtv.setClassnum(4);
		playtv.setSOURCE(4);

		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		playtv.setDATA_TYPE(1);
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);

		// String score = HtmlAnalyze.getTagText(strHtml, "</em><strong
		// class=\"score\">", "</strong> ��");
		// �������ֵĲɼ�
		// try {
		// OracleOpreater.intoReputation(name, "4", score, "0", "", urlBranch,
		// "3", "1");
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.general li");

		// sohuDetailedfirst(urlBranch, name);

		// System.out.println(links.size());
		// for (Element link : links) {
		// // System.out.println(link);
		// String strmainurl = "";
		// String strmaintitle = "";
		// System.out.println(strmainurl = link.select("div.pic
		// a").attr("href"));
		// System.out.println(strmaintitle = link.select("div.pic
		// a").attr("title").replaceAll(name, ""));
		// sohuDetailed(strmainurl, strmaintitle);
		//
		// }

	}

	/**
	 * ���з������ǵĲɼ�
	 * 
	 * @param urlerer
	 * @param name
	 */
	public static void sohuDetailedfirst(String urlerer, String name) {
		String strHtmls = DownloadUtil.getHtmlText(urlerer, 1000 * 30, "UTF-8", null, null);
		String strvid = HtmlAnalyze.getTagText(strHtmls, "var vid=\"", "\"");
		String strvplaylistId = HtmlAnalyze.getTagText(strHtmls, "var playlistId=\"", "\"");
		System.out.println(strvid);
		if (strvid == null || strvid.equals("")) {
			return;
		}
		String urlnew = "http://count.vrs.sohu.com/count/queryext.action?vids=" + strvid + "&plids=" + strvplaylistId
				+ "&callback=playCountVrs";
		System.out.println(urlnew);
		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		String Amount = HtmlAnalyze.getTagText(strHtml, "{\"" + strvid + "\":{\"total\":", ",\"");
		try {
			OracleOpreater.intoReputation(name, "4", Amount, "0", "", urlerer, "1", "0");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void sohuDetailed(String urlerer, String title) {
		String strHtmls = DownloadUtil.getHtmlText(urlerer, 1000 * 30, "GBK", null, null);
		String strvid = HtmlAnalyze.getTagText(strHtmls, "var vid=\"", "\"");
		String strvplaylistId = HtmlAnalyze.getTagText(strHtmls, "var playlistId=\"", "\"");
		System.out.println(strvid);
		if (strvid == null || strvid.equals("")) {
			return;
		}
		String urlnew = "http://count.vrs.sohu.com/count/queryext.action?vids=" + strvid + "&plids=" + strvplaylistId
				+ "&callback=playCountVrs";
		System.out.println(urlnew);

		String tyPlayName;
		String serNumber = title.replaceAll("��", "").replaceAll("��", "");
		String source = "4";
		String playAmount;
		String vodeoType = "0";
		String palydate = "";
		String playUrl = urlnew;
		String tvType = "0";
		String realUrl = urlerer;
		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtmls, "<meta name=\"album\" content=\"", "\" /> "));

		System.out.println(
				playAmount = HtmlAnalyze.getTagText(strHtml, "{\"" + strvplaylistId + "\":{\"total\":", ",\""));
		try {

//			OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, playUrl,
//					tvType, realUrl);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// http://count.vrs.sohu.com/count/queryext.action?vids=2604452&plids=6457518&callback=playCountVrs
	}

	private static void sohuMain(String sohuMainUrl) {
		// TODO Auto-generated method stub
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + sohuMainUrl);
		String strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		}
		try {

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.st-pic");
			for (Element link : links) {
				String texthtml = link.select("a").attr("title");
				String texturl = link.select("a").attr("href");
				// System.out.println(texthtml);
				if (texthtml != null && !"".equals(texthtml) && texturl != null && !"".equals(texturl)) {
					System.out.println(texturl);
					sohuBranch(texturl);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		for (int i = 1; i <= 72; i++) {
			sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10" + i + "_p11_p12_p13.html");

		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
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
		// sohuBranch("http://tv.sohu.com/item/MTE3NzM3NA==.html");
		// sohuDetailed("http://tv.sohu.com/20150919/n421602862.shtml","��1��");

		// sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10_p11_p12_p13.html");

//		while (true) {
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
//			for (int i = 1; i <= 72; i++) {
//				sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10" + i + "_p11_p12_p13.html");
//
//			}
//			try {
//				Thread.sleep(1000 * 60 * 60 * 22);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
//		}
		
			sohuMain("http://so.tv.sohu.com/list_p1101_p20_p3_p40_p5_p6_p77_p80_p9_2d1_p10_p11_p122_p13.html");
//		 TimingTime(23, 59, 59);
	}

}
