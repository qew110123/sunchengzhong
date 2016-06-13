package com.artsoft.download.maoyanandpiaofang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.bean.TEM_FILM_BOXOFFICE_REALTIME;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class zhonggoupiaofangwang_shisipiaofangwang {

	
	
	public static void numxiantvcity(String urlMain) {
		// TODO Auto-generated method stub
		
		
		String htmlss= DownloadUtil.getHtmlText("http://www.cbooo.cn/realtime", 1000 * 30, "UTF-8", null, null);
		
		String REAL_DATE= HtmlAnalyze.getTagText(htmlss, "class=\"fl\" style=\"margin-left:16px;\">北京时间", "（30分钟更新一次）");
		
		

		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
//			urlMain = "http://www.cbooo.cn/Screen/getCityData?days=0&cityType=1";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
		
		

		String DATA_DATE = "";
		DATA_DATE = TimeTest.getNowTime("yyyyMMdd");
		
		
		JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
		
//		JSONArray objectobjectmain = JSONArray.fromObject(strHtml);
		
		JSONArray album_lists = new JSONArray();
		
		

		album_lists = (JSONArray) objectobjectmain.get("data2");
		String BoxOffice = "";
		String title = "";
		String boxPer = "";
		String mId = "";
		String movieDay = "";
		String sumBoxOffice="";
		String url="";
		for (Object object : album_lists) {
			TEM_FILM_BOXOFFICE_REALTIME  realitme=new TEM_FILM_BOXOFFICE_REALTIME();
			realitme.setREAL_DATE(REAL_DATE);
			realitme.setDataDate(DATA_DATE);
			
			JSONObject objectobject = JSONObject.fromObject(object);
			BoxOffice = (String) objectobject.get("BoxOffice");
			realitme.setRealTimeBoxoffice(BoxOffice);
			title = (String) objectobject.get("MovieName");
			realitme.setTitle(title);
//			field_num = (String) objectobject.get("num_pro");
			boxPer = (String) objectobject.get("boxPer");
			realitme.setBoxofficeRate(boxPer);
			mId = (String) objectobject.get("mId");
			realitme.setFid(mId);
			movieDay = (String) objectobject.get("movieDay");
			realitme.setReleasedDays(movieDay);
			sumBoxOffice = (String) objectobject.get("sumBoxOffice");
			realitme.setTotalBoxoffice(sumBoxOffice);
			//http://www.cbooo.cn/m/642020
			url="http://www.cbooo.cn/m/"+mId;
			realitme.setUrl(url);
			realitme.setCollectionUrl(urlMain);
			realitme.setDATA_TYPE(0);;
			OracleMovePiaoFang.intoTEM_FILM_BOXOFFICE_REALTIME(realitme);
			// }

		}

	}
	
	
	
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
		try {
			
			numxiantvcity("http://www.cbooo.cn/boxOffice/GetHourBoxOffice");
		} catch (Exception e) {
			// TODO: handle exception
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
		}, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(0, 59, 59);
//		openstatic();
	}

}
