package com.artsoft.download.variety.Each;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

public class Each_variety_qq {
	
	
	public static boolean openstatic(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return false;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.figures_list li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String strmainurl = "";
			String score = "";
			String name = "";
			System.out.println(strmainurl = link.select("strong.figure_title a").attr("href"));
			// System.out.println(strmainurl = link.select("a").attr("id"));
			System.out.println(name = link.select("strong.figure_title a").attr("title"));
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			System.out.println(score = link.select("span.mod_score").text());
			try {
				
				danjibofangliang(strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();

//		try {
//			if (xxx < Integer.parseInt(tt)) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		return true;

	}
	
	
	
	private static void danjibofangliang(String strmainurl) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return ;
		}

		Document doc = Jsoup.parse(strHtml);
		Element linksid= doc.getElementById("column_follow");
		
		Elements links = linksid.select("li.list_item");
		for (Element element : links) {
			String tyPlayName="";
			String source="3";
			String playAmount="";
			String vodeoType="0";
			String palydate="";
			String playUrl="";
			String tvType="2";
			String realUrl=strmainurl;
			String playAmountString="";
			System.out.println(tyPlayName=element.select("strong  a").text());
			System.out.println(playUrl=element.select("strong  a").attr("href"));
			System.out.println(playAmountString=element.select("div.mod_playnum").text());
			playAmount=String.valueOf(Stringnum(playAmountString));
			
			OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, playUrl, tvType, realUrl);
			
			
		}
		
		
		
		
		
	}
	
	public static int  Stringnum( String numString){
		int numIn=0;
		if (numString==null||numString.equals("")) {
			numString="-1";
			numIn=0;
		}
		if (numString.contains("��")) {
			numIn=(int) ((Double.parseDouble(numString.replace("��", "")))*100000000);
		}else{
			
		
			if (numString.contains("��")) {
				numIn=(int) (Double.parseDouble(numString.replace("��", ""))*10000);
			}else{
				numIn=(int) Double.parseDouble(numString);
			}
		}
		
		
		return numIn;
		
	}



	private static void openordor() {
		// TODO Auto-generated method stub
		String url="";
		for (int i = 0; i <= 1160; i = i + 20) {
			url = "http://v.qq.com/x/varietylist/?itype=-1&offset=" + i + "&isource=-1&sort=4";
			
			openstatic(url);
		}
		
	}
	
	public static void runstatic() {
		CommonUtil.setLog("�ſ�����" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		openordor();

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
				}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
			}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(1, 59, 59);
//		openordor();
//		danjibofangliang("http://v.qq.com/cover/b/bkj3856wxukmjcj.html");
	}

}
