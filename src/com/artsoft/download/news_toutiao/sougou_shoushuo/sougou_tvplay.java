package com.artsoft.download.news_toutiao.sougou_shoushuo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.oracle.Oracle;
import com.artsoft.oracle2.DBManager;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;
import com.artsoft.download.news_toutiao.sougou_shoushuo.sougou_shoushuo;


public class sougou_tvplay {
	
	
	public static void runstatic( ) {
		// TODO Auto-generated method stub
		List<String> listArray = Oracle.selecttvplay();
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String name="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				try {
					
					
//					sougou_shoushuo.runnewMain(name, 2, id);
					
					TimeoutTest.runing(name, 2, id);
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
			}
				
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
				timer.schedule(new TimerTask() {
					public void run() {
						System.out.println("-------设定要指定任务--------");
//						runstatic();
						try {
							
//							runnewMain();
							
//							DBManager dbm = DBManager.instance();
//							dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
							CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
							runstatic();
							CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
				}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
			}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runstatic();
		TimingTime(1, 59, 59);
		

	}

}
