package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class zhongguopioafangwang_diqu {
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}
	
	
	
	
	private static void openstatic() {
		// TODO Auto-generated method stub
		zhongguopiaofangwang.quanguopaipian("http://www.cbooo.cn/Screen/getScreenData?days=0",TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"),
				 0).toString().replace("-", ""));
		zhongguopiaofangwang.quanguopaipian("http://www.cbooo.cn/Screen/getScreenData?days=1",TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"),
				 1).toString().replace("-", ""));
		zhongguopiaofangwang.quanguopaipian("http://www.cbooo.cn/Screen/getScreenData?days=2",TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"),
				 2).toString().replace("-", ""));

//				 System.out.println(TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"),
//				 -0).toString().replace("-", ""));

		zhongguopiaofangwang.numxiantvcity("http://www.cbooo.cn/Screen/getCityData?days=0&cityType=1");
		zhongguopiaofangwang.numxiantvcity("http://www.cbooo.cn/Screen/getCityData?days=0&cityType=2");
		zhongguopiaofangwang.numxiantvcity("http://www.cbooo.cn/Screen/getCityData?days=0&cityType=3");
				// http://www.cbooo.cn/Screen/getAreaScreenData?days=1
		
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
			}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
		}

		public static void runstaticshijian(){
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
			System.out.println(TimeTest.getNowTime("HH"));
			if (TimeTest.getNowTime("HH").equals("12") || TimeTest.getNowTime("HH").equals("09")) {
				runstatic();
			}
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
		}
		
		
		 /* 测试时间
		 * 2016年8月17日17:30:00
		 * @param args
		 */
		public static void rundingshitime(int hh , int mm ,int ss) {  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
	        calendar.set(Calendar.MINUTE, mm);       // 控制分  
	        calendar.set(Calendar.SECOND, ss);       // 控制秒  
	  
	        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
	  
	        Timer timer = new Timer();  
	        timer.scheduleAtFixedRate(new TimerTask() {  
	            public void run() {  
	                System.out.println("-------设定要指定任务--------");  
	                try {
						
	                	runstaticshijian();
					} catch (Exception e) {
						// TODO: handle exception
					}
	            } 
	        }, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行  
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 TimingTime(1, 00, 00);
		 rundingshitime(1, 10, 00);
//		runstatic();
	}

}
