package com.artsoft.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

	public static void iQiYiBranch(String urlBranch) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");

		System.out.println(name);
		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/?callback=window.Q.__callbacks__.cbrymman";
		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "\":", "}"));
		try {

			OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch, "0", "0");

			String videourl = "http://up.video.iqiyi.com/ugc-updown/quud.do?dataid=" + videoId
					+ "&type=1&userid=&flashuid=e42dc42f8825edf5580806cde99606ce&appID=21&callback=window.Q.__callbacks__.cb1953yg";

			String feishuhtml = DownloadUtil.getHtmlText(videourl, 1000 * 30, "UTF-8", null, null);
			String feishu = "";
			System.out.println(feishu = HtmlAnalyze.getTagText(feishuhtml, "score\":", ",\""));

			OracleOpreater.intoReputation(name, "2", feishu, "0", "", urlBranch, "0", "1");
		} catch (Exception e) {
			// TODO: handle exception
		}
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
			System.out.println(link.select("a.site-piclist_pic_link").attr("title"));
			iQiYiBranch(strmainurl);
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
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

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
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

//判断数据开始时间
	public static void TimingTime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                runstatic();
            } 
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行  
    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// youkuBranch("http://www.iqiyi.com/a_19rrhbc6p5.html#vfrm=2-4-0-1");
		// http://list.iqiyi.com/www/2/-------------4-1-1-iqiyi--.html
		// while (true) {
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":开始");
		// String strkey = ReadTxtFile.getKeyWordFromFile("keywordiqiyi.txt");
		// String[] keys = strkey.split("\n");
		// for (int i = 0; i < keys.length; i++) {
		// System.out.println(keys[i]);
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// keys[i]);
		// String url = keys[i];
		// System.out.println(url);
		// boolean bb = true;
		// DownloadIqiyi.youkuMaim(url);
		// }
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":结束");
		// try {
		// Thread.sleep(1000 * 60 * 60 * 21);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		
		TimingTime(23, 59, 59);

	}
}
