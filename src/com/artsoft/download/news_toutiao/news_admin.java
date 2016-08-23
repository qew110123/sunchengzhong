package com.artsoft.download.news_toutiao;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.download.TVPlay.DownloadIqiyi;
import com.artsoft.download.TVPlay.DownloadLetv;
import com.artsoft.download.TVPlay.DownloadSohu;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.download.TVPlay.Downloadkankan;
import com.artsoft.download.TVPlay.Downloadpptv;
import com.artsoft.download.TVPlay.Downloadqq;
import com.artsoft.download.news_toutiao.weixin.weixin;
import com.artsoft.oracle2.DBManager;
import com.artsoft.oracle2.DateUtil;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class news_admin {
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		try {
			baidubaijia.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			tengxunyule_1.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			tengxunyule_2.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			tengxunyule_3.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			tengxunyule_4.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wangyiyule_1.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			wangyiyule_2.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			shouhuyule_1.runnewMain("http://yule.sohu.com/tv.shtml");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			shouhuyule_1.runnewMain("http://yule.sohu.com/movie.shtml");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("调用存储过程");
		
		DBManager dbm = DBManager.instance();
//		dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
		dbm.executeCall("call sp_f_movies_news('"+TimeTest.getNowTime("yyyyMMdd")+"') ");
		
		// DownloadHunantv.runstatic();

		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
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
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(04, 00, 00);
		// runstatic();
	}

}
