package com.artsoft.download.variety;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.TVPlay.DownloadYouku_always;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class variety_YouKu {
	static int i = 0;
	
	
	
	
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
		System.out.println(score);

		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", Amount, "0", "", urlBranch, "2", "0", urlBranch);
//		// "2015��10��23��10:43:46",urlBranch, "0", "1");
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", score, "0", "", urlBranch, "2", "1", urlBranch);
		OracleOpreater.intoReputationAndDETAIL_URL(name, "1", comment, "0", "", urlBranch, "2", "2", urlBranch);
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
	 * �����ſ��������ҳ�������ݶβ�ѯ
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
				
				String strmainurlHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document strmainurlHtmldoc = Jsoup.parse(strmainurlHtml);
				
				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				youkuBranch(strmainxiangxiurl);
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
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

		String url = "";
		String[] diqu = { "��½", "���", "̨��", "����", "�ձ�" , "����", "�Ĵ�����" };
		String[] leixing = { "�ſ��Ʒ", "�ſ�ţ��", "�ѿ���", "������", "ѡ��", "��ʳ", "����", "����", "��̸", "��ʵ", "��Ц", "ʱ��", "���", "���", "�ݳ���", "����", "����", "����", "�赸", "��������", "��Ϸ", "����" };
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				// try {
				for (int i = 1; i < 30; i++) {
					try {
						url = "http://list.youku.com/category/show/c_85_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_" + i + ".html";
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(url);
					String urlnext = youkuMaim(url);
					if (urlnext.equals("") || urlnext == "" || urlnext == null) {
						break;
					}
				}

			}

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
			}, time, 1000 * 60 * 60 * 12);// �����趨����ʱÿ��̶�ִ��
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(0, 29, 59);

	}

}
