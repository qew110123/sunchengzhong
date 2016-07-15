package com.artsoft.download.variety.xiangxijibenxinxi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_DIM_PLATFORM;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class YouKuxiangxixinxi {
	
	
	public static void youkuBranch(String urlall){
		String url=urlall;
		String tvplayNamehtml=DownloadUtil.getHtmlText(urlall, 30000, "UTF-8", null, null);
		String tvplayName="";
		tvplayName= HtmlAnalyze.getTagText(tvplayNamehtml, "class=\"name\">", "</span>");
		
		if (urlall.equals("")) {
			return;
		}
		//http://www.youku.com/show_page/id_z427435942dbc11df97c0.html
		//http://www.youku.com/show_point_id_z9cfbe28619ec11e29498.html?dt=json&tab_num=4&__rt=1&__ro=reload_point
		//http://www.youku.com/show_point/id_z2554dd90b91811e59e2a.html?dt=json&divid=point_reload_201512&tab=0&__rt=1&__ro=point_reload_201512
		String caijiurl=urlall.replace("show_page/", "show_point_")+"?dt=json&tab_num=4&__rt=1&__ro=reload_point";
		String html=DownloadUtil.getHtmlText(caijiurl, 30000, "UTF-8", null, null);
		Document doc=Jsoup.parse(html);
		Element linkid=doc.getElementById("zySeriesTab");
		
		
//		String tvplayName=
		
//		System.out.println(linkid);
		if (linkid!=null) {
			Elements links=linkid.select("li");
			for (Element element : links) {
				//http://www.youku.com/show_page/id_z427435942dbc11df97c0.html
				//http://www.youku.com/show_episode/id_z427435942dbc11df97c0.html?dt=json&divid=reload_200806&__rt=1&__ro=reload_200806
				String reload="";
				System.out.println(reload=element.attr("data"));
				caijiurl=urlall.replace("show_page", "show_point")+"?dt=json&divid="+reload+"&tab=0&__rt=1&__ro="+reload;
				System.out.println(caijiurl);
				String REAL_URLhtml=DownloadUtil.getHtmlText(caijiurl, 30000, "UTF-8", null, null);
				Document REAL_URLdoc=Jsoup.parse(REAL_URLhtml);
				Elements linksREAL_UR=REAL_URLdoc.select("div.item");
				for (Element element2 : linksREAL_UR) {
					TEM_DIM_PLATFORM platform=new TEM_DIM_PLATFORM();
					String tyPlayName="";
//					String source="1";
					platform.setSource(1);
					
					String playAmount="";
//					String vodeoType="0";
//					String palydate="";
					String playUrl="";
//					String tvType="2";
//					String realUrl=caijiurl;
					System.out.println(tyPlayName=element2.select("div.title a").text());
//					platform.setTvplayName(tyPlayName);
					platform.setTvplayNameSmall(tyPlayName);
					System.out.println(playUrl=element2.select("div.title a").attr("href"));
					platform.setTvplayUrl(playUrl);
					
					System.out.println(playAmount=element2.select("div.stat span.num").text());
					if (!playAmount.equals("")) {
						playAmount=playAmount.replace(",", "");
					}
					platform.setPlayAmount(playAmount);
					String BASIC_INFO="";
					System.out.println(BASIC_INFO=element2.select("div.desc").text());
					platform.setBasicInfo(BASIC_INFO);
					
					String TIME_INFO="";
//					System.out.println(TIME_INFO=element2.select("div.desc").text());
					Elements ElementTIME_INFO= element2.select("div.keylist li");
					for (Element element3 : ElementTIME_INFO) {
						String ktime=element3.select("em").first().text();
						String aString = element3.select("a").first().text();
						TIME_INFO=TIME_INFO+ktime+"/"+aString+";";
					}
					System.out.println(TIME_INFO);
					platform.setTimeInfo(TIME_INFO);
					
					String MAJOR_ACTORS="";
//					System.out.println(TIME_INFO=element2.select("div.desc").text());
					Elements MAJOR_ACTORSElements= element2.select("div.guestlist li");
					for (Element element3 : MAJOR_ACTORSElements) {
//						String ktime=element3.select("em").first().text();
//						String aString = element3.select("a").first().text();
//						TIME_INFO=ktime+"/"+aString+";";
						MAJOR_ACTORS=MAJOR_ACTORS+element3.select("a").first().text()+";";
					}
					System.out.println(MAJOR_ACTORS);
					platform.setMajorActors(MAJOR_ACTORS);
					platform.setDataType(2);
					platform.setTvplayName(tvplayName);
					platform.setUrl(url);
					OracleOpreater.intoPLATFORM(platform);
				}
			}
		}else{
//			Elements linksREAL_UR2=doc.select("div.item");
//			for (Element element2 : linksREAL_UR2) {
//				String tyPlayName="";
//				String source="1";
//				String playAmount="";
//				String vodeoType="0";
//				String palydate="";
//				String playUrl="";
//				String tvType="2";
//				String realUrl=caijiurl;
//				System.out.println(tyPlayName=element2.select("div.title a").text());
//				System.out.println(playUrl=element2.select("div.title a").attr("href"));
//				
//				System.out.println(playAmount=element2.select("div.stat span.num").text());
//				if (!playAmount.equals("")) {
//					playAmount=playAmount.replace(",", "");
//				}
//				OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, playUrl, tvType, realUrl);
//			}
			
		}
		
	}
	
	
	
	/**
	 * 2016年5月30日17:36:49
	 * 进行整体数据的更细
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuvariety(date_date,"1");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				youkuBranch(listTemp.get(0));
//				DownloadYouku.youkuBranch(listTemp.get(0));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void runstatic() {
		CommonUtil.setLog("" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
//		openstatic();
//		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		openordor();
//		Thread.sleep(millis);
//		try {
////			System.out.println("等待2小时");
//			CommonUtil.setLog("当前时间:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			Thread.sleep(1000 * 60 * 60 * 2);
//			openstatic();
//			CommonUtil.setLog("优酷等待2小时" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			System.out.println("等待2小时");
//			CommonUtil.setLog("当前时间:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			Thread.sleep(1000 * 60 * 60 * 3);
//			openstatic();
//			CommonUtil.setLog("优酷等待3小时" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TimingTime(1, 59, 59);
//		youkuBranch("http://www.youku.com/show_page/id_z234cc978f0e411e583e8.html");
		runstatic();
	}

}
