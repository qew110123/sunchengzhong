package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_FILM_BOXOFFICE_REALTIME;
import com.artsoft.bean.TEM_FILM_DAILY_CINEMA;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class zhongguopiaofangwang_yingyuandanripiaofang {
	
	

	public static void openstatic() {
		// String datetext = TimeTest.getNowTime("yyyy-MM-dd");
		// String datetext = TimeTest.getBeforeAfterDate("2016-04-29",
		// -1).toString();
//		for (int i = 0; i > -9; i--) {
//			String urlMain = "http://www.cbooo.cn/boxOffice/GetDayBoxOffice?num=" + i;
//			// urlMain = "";
//			opensUrl(urlMain);
//
//		}
//		try {
			
			numxian("http://www.cbooo.cn/cinemaday");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
	
	
	private static void numxian(String urlMain) {
		// TODO Auto-generated method stub
		
		
		String htmlss= DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		
		Element jsouphtmlss=Jsoup.parse(htmlss);
		
		Element docidselTime=jsouphtmlss.getElementById("selTime");
		Elements docoption =docidselTime.select("option");
		for (Element element : docoption) {
//			System.out.println(element);
			String data="";
			System.out.println(data=element.attr("value"));
			String urlMain1="http://www.cbooo.cn/boxOffice/getCBD?pIndex=1&dt="+data;
			danripiaofang(urlMain1,data);
			
		}
		
		
		
	}

	private static void danripiaofang(String urlMain,String data) {
		// TODO Auto-generated method stub
//		String REAL_DATE= HtmlAnalyze.getTagText(htmlss, "class=\"fl\" style=\"margin-left:16px;\">北京时间", "（30分钟更新一次）");
//		String urlMain="http://www.cbooo.cn/boxOffice/getCBD?pIndex=1&dt="+data;
		for (int i = 0; i <= 10; i++) {
			urlMain="http://www.cbooo.cn/boxOffice/getCBD?pIndex="+i+"&dt="+data;
			String strHtml = "";
			if (urlMain == null || urlMain.equals("")) {
	//			urlMain = "http://www.cbooo.cn/Screen/getCityData?days=0&cityType=1";
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			
			
	
			String DATA_DATE = "";
	//		DATA_DATE = TimeTest.getNowTime("yyyyMMdd");
			DATA_DATE=data.replace("/", "");
			
			
			JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
			
	//		JSONArray objectobjectmain = JSONArray.fromObject(strHtml);
			
			JSONArray album_lists = new JSONArray();
			
			try {
				
				album_lists = (JSONArray) objectobjectmain.get("data1");
				String title = "";
				String BoxOffice = "";
				String TodayShowCount = "";
				String AvgPeople = "";
				String price = "";
				String Attendance="";
				String url="";
				for (Object object : album_lists) {
					TEM_FILM_DAILY_CINEMA  dailycinema=new TEM_FILM_DAILY_CINEMA();
					dailycinema.setDataDate(DATA_DATE);
					
					JSONObject objectobject = JSONObject.fromObject(object);
					title = (String) objectobject.get("CinemaName");
					dailycinema.setTitle(title);
					//			realitme.setRealTimeBoxoffice(BoxOffice);
					BoxOffice = (String) objectobject.get("TodayBox");
					dailycinema.setRealTimeBoxoffice(BoxOffice);
					//			field_num = (String) objectobject.get("num_pro");
					TodayShowCount = (String) objectobject.get("TodayShowCount");
					dailycinema.setFieldNum(TodayShowCount);
					AvgPeople = (String) objectobject.get("AvgPeople");
					dailycinema.setFieldAveragePnum(AvgPeople);
					price = (String) objectobject.get("price");
					dailycinema.setAveragePrice(price);
					Attendance = (String) objectobject.get("Attendance");
					dailycinema.setAttendanceRate(Attendance);
					//http://www.cbooo.cn/m/642020
					//			url="http://www.cbooo.cn/m/"+mId;
					dailycinema.setCollectionUrl(urlMain);
					//			realitme.setCollectionUrl(urlMain);
					
					OracleMovePiaoFang.intoTEM_FILM_DAILY_CINEMA(dailycinema);
					// }
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	
		}
	}



	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
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
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行
	}
	/**
	 * 单日票房 
	 * 地区
	 * 2016年6月12日16:44:06
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(0, 59, 59);
//		openstatic();
	}

}
