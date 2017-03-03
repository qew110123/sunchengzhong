package com.artsoft.download.Movies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DownqqMovie {
	
	public static void downBranchpingfen(String urlBranch, String nameBranch, String urlMain){
		String htmlpinglunhtml=DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		String htmlidhtml=HtmlAnalyze.getTagText(htmlpinglunhtml, "<!--\"create_at\":","-->");
		if (!htmlidhtml.equals("")) {
			
			String urlpinglun="http://coral.qq.com/article/1407496658/commentnum?callback=jQuery19105714774770759592_1464171533134&low_login=1&_="+htmlidhtml;
			String commentnum=DownloadUtil.getHtmlText(urlpinglun, 1000 * 30, "UTF-8", null, null);
			String comment=HtmlAnalyze.getTagText(commentnum, "commentnum\":\"","\"}");
			
			
//			OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", comment, "0", "", urlBranch, "2", "2", urlBranch);
			OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", comment, "0", "", urlpinglun, "3", "2",urlBranch);
//			OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", numstring, "0", "", urlMain, "3", "0",DETAIL_URL);
//			OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "3", "1",strmainurl);
		}
//		else{
//			String comment=HtmlAnalyze.getTagText(htmlpinglunhtml, "class=\"video_score\">","</span>");
//			if (!comment.equals("")) {
//				
//				OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", comment, "0", "", urlBranch, "3", "2",urlBranch);
//			}
//		}
	}
	
	public static boolean downMain(String urlMain ,int xxx) {
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
			System.out.println(name = link.select("strong.figure_title").attr("title"));
			
			if (name.equals("")) {
				System.out.println(name = link.select("strong.figure_title").text());
			}
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			score = link.select("span.mod_score").text();
			if (score.equals("")) {
				score = link.select("div.figure_score").text().replace(" ", "");
			}
			System.out.println(score);
			
			try {
				//评分
				OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "3", "1",strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				//总播放量
				downBranch(strmainurl, name, urlMain);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			try {
				//评论量
				downBranchpingfen(strmainurl, name, urlMain);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		String tt = doc.select("span.txt_01").select("em.strong").first().text();
//		
//		
//		try {
//			if (xxx<Integer.parseInt(tt)) {
//				return true;
//			}
//			else{
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		return true;
		

	}

	public static void downBranch(String urlBranch, String nameBranch, String urlMain) {
		// http: // v.qq.com/cover/e/e7hi6lep1yc51ca.html
		String DETAIL_URL=urlBranch;
		
		if (urlBranch.contains("cover")) {
			urlBranch =  HtmlAnalyze.getTagText(urlBranch, "cover/",".");
		}
		else if (urlBranch.contains("http://v.qq.com/cover/")) {
			urlBranch = urlBranch.replaceAll("http://v.qq.com/cover/", "").replaceAll("html", "");
		}else if (urlBranch.contains("https://v.qq.com/x/cover/")) {
			urlBranch = urlBranch.replaceAll("https://v.qq.com/x/cover/", "").replaceAll("html", "");
			
		}else if (urlBranch.contains("v.qq.com/x/cover/")) {
			urlBranch = urlBranch.replaceAll("https://v.qq.com/x/cover/", "").replaceAll("html", "");
			
		}
		
//		urlBranch = HtmlAnalyze.getTagText(urlBranch, "/", ".");
//		urlBranch = urlBranch.replaceAll("http://v.qq.com/cover/", "").replaceAll("html", "");
//		urlBranch = HtmlAnalyze.getTagText(urlBranch, "/", ".");
		String idlist=urlBranch;
		// http://sns.video.qq.com/tvideo/fcgi-bin/batchgetplaymount?callback=jQuery191043746947194449604_1445913614627&low_login=1&id=e7hi6lep1yc51ca&otype=json&_=1445913614628
		System.out.println(urlBranch);
		System.out.println(urlBranch);
		if (urlBranch == null || "".equals(urlBranch)) {
			return;
		}
		urlBranch = "http://sns.video.qq.com/tvideo/fcgi-bin/batchgetplaymount?callback=jQuery191043746947194449604_1445913614627&low_login=1&id="
				+ urlBranch + "&otype=json&_=1445913614628";
		
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		String numstring = HtmlAnalyze.getTagText(strHtml, "{\"all\":", ",\"");
		
		if (numstring.equals("")) {
//			System.out.println("为空");
			urlBranch="http://data.video.qq.com/fcgi-bin/data?tid=70&&appid=10001007&appkey=e075742beb866145&callback=jQuery19102410269394301756_1461233515175&low_login=1&idlist="+idlist+"&otype=json&_=1461233515181";
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(strHtml, 1000 * 30, "UTF-8", null, null);
			}
			numstring = HtmlAnalyze.getTagText(strHtml, "\"allnumc\":",",");
		}
		
		if (numstring.equals("")) {
			//http://data.video.qq.com/fcgi-bin/data?tid=70&&appid=10001007&appkey=e075742beb866145&callback=jQuery191007682828863116642_1461740744468&low_login=1&idlist=7rie0b46llp982g&otype=json&_=1461740744469
			urlBranch="http://data.video.qq.com/fcgi-bin/data?tid=70&&appid=10001007&appkey=e075742beb866145&callback=jQuery191007682828863116642_1461740744468&low_login=1&idlist="+idlist+"&otype=json&_=1461740744469";
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(strHtml, 1000 * 30, "UTF-8", null, null);
			}
			numstring = HtmlAnalyze.getTagText(strHtml, "\"allnumc\":",",");
		}
		System.out.println(numstring);
		
		if (numstring.equals("")) {
			
		}
		
		
		try {
			//总播放量
			OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", numstring, "0", "", urlMain, "3", "0",DETAIL_URL);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}
	
	/**
	 * 获得分手，不能得到播放量的数据进行重新运行
	 */
	public static void Again(){
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectqqMovie(date_date);
		String strmainurl="";
		String name="";
		String urlMain="";
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(strmainurl=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			System.out.println(urlMain=listTemp.get(2));
			downBranch(strmainurl, name, urlMain);
		}
		
//		downBranch("http://v.qq.com/cover/e/erzyxpn1exuszy4.html", "33分钟侦探", "http://v.qq.com/x/teleplaylist/?sort=4&offset=440&itype=-1&iarea=819&iyear=-1&ipay=-1");
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		openstatic();
		Again();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
	
	public static void openstatic() {
		String url = "";
		String[] diqu = { "100024", "100025", "100026", "100027", "100028", "100029", "100030", "100031", "100032", "100033" };
		String[] nianfen = {"2016", "100063", "100034", "100035", "100036", "100037", "100038", "100039", "100040"};
		for (String diqutxt : diqu) {
			for (String nianfentxt : nianfen) {
				System.out.println(diqutxt + nianfentxt);
				
				try {
					for (int i = 0; i < 5000; i=i+30) {
						url = "http://v.qq.com/x/movielist/?cate=-1&offset="+i+"&sort=5&pay=-1&area=" + diqutxt
								+ "&year=" + nianfentxt + "";
						System.out.println(url);
						boolean bb=downMain(url,i);
//						String urlnext = DownYoukuMovie.youkuMaim(url);
						if (!bb) {
							break;
						}
					}

				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TimingTime(1, 59, 59);
//		openstatic();
//		Again();
//		TimingTime(21, 59, 59);

	}

}
