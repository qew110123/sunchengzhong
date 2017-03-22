package com.artsoft.download.news_toutiao.weixin.wenxin_new_liulanqi;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.TimeTest;

public class wenxin_new_xiangxin_new {
	
	/**
	 * 2017年3月14日14:43:08
	 * 进行数据的
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TimingTime(1, 59, 59);

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
						// runstatic();
						try {

//							runnewMain();
//							runnewMainOracleTanchu(TimeTest.getNowTime("yyyy-MM-dd"));
							
							wenxin_new.runnewMainOracle(TimeTest.getNowTime("yyyy-MM-dd"));
	//
//							DBManager dbm = DBManager.instance();
//							dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
			}


}
