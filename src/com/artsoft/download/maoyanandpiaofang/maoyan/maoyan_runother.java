package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class maoyan_runother {
	
	
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
			}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
		}

	protected static void runstatic() {
			// TODO Auto-generated method stub
		//排片
		try {
			
			maoyan_paipian.runstatic();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//影院
		try {
			
			maoyan_tangying.runstatic();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//影投
		try {
			
			maoyan_yingyuan.runstatic();
		} catch (Exception e) {
			// TODO: handle exception
		}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(1, 59, 59);
	}

}
