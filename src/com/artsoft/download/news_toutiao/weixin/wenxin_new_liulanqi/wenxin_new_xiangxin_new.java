package com.artsoft.download.news_toutiao.weixin.wenxin_new_liulanqi;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.TimeTest;

public class wenxin_new_xiangxin_new {
	
	/**
	 * 2017��3��14��14:43:08
	 * �������ݵ�
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TimingTime(1, 59, 59);

	}
	
	// �ж����ݿ�ʼʱ��
			public static void TimingTime(int hh, int mm, int ss) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
				calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
				calendar.set(Calendar.SECOND, ss); // ������

				Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						System.out.println("-------�趨Ҫָ������--------");
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
				}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
			}


}
