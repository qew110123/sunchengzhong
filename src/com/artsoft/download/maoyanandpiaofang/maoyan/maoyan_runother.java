package com.artsoft.download.maoyanandpiaofang.maoyan;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class maoyan_runother {
	
	
	// �ж����ݿ�ʼʱ��
		public static void TimingTime(int hh, int mm, int ss) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
			calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
			calendar.set(Calendar.SECOND, ss); // ������

			Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					System.out.println("-------�趨Ҫָ������--------");
					runstatic();
				}
			}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
		}

	protected static void runstatic() {
			// TODO Auto-generated method stub
		//��Ƭ
		try {
			
			maoyan_paipian.runstatic();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//ӰԺ
		try {
			
			maoyan_tangying.runstatic();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//ӰͶ
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
