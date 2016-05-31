package com.artsoft.download.variety;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class variety_Douban {
	
	
	public static void mainUrlall(String urlBranch) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("tr.item");
		for (Element element : links) {
//			if (element.text().contains("���Ӿ�")) {
				String name=element.select("a").first().attr("title");
				String urls = "";
				System.out.println(urls = element.select("a.nbg").first().attr("href"));
				String feishu = "";
				System.out.println(feishu = element.select("span.rating_nums").text());
				String pinglun = "";
				System.out.println(pinglun = HtmlAnalyze.getTagText(element.toString(), "class=\"pl\">(", "������"));
				try {
					if (feishu != null && !feishu.equals("")) {
//						OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "1", "1");
						OracleOpreater.intoReputationAndDETAIL_URL(name, "9", feishu, "0", "", urlBranch, "2", "1", urlBranch);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if (pinglun != null &&! pinglun.equals("")) {
						pinglun = pinglun.replaceAll("\\D+", "");
						if (pinglun != null && !pinglun.equals("")) {
//							OracleOpreater.intoReputation(name, "9", pinglun, "0", "", urls, "1", "2");
							OracleOpreater.intoReputationAndDETAIL_URL(name, "9", pinglun, "0", "", urlBranch, "2", "2", urlBranch);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//		}

	}
	
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		
		for (int i = 0; i <= 240; i=i+20) {
//			https://movie.douban.com/tag/%E7%BB%BC%E8%89%BA%E8%8A%82%E7%9B%AE?start=20&type=T
			mainUrlall("https://movie.douban.com/tag/%E7%BB%BC%E8%89%BA%E8%8A%82%E7%9B%AE?start="+i+"&type=T");
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
		TimingTime(0, 29, 59);
//		runstatic();
	}

}
