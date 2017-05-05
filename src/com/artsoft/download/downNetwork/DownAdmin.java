package com.artsoft.download.downNetwork;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class DownAdmin {

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		
		CommonUtil.setLog("type:��ʼ||date:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + "||logs:����粥���� ����ʼ;");
		try {

			DownYoukuNetwork.main(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			DownSohuNwtword.main(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			DownqqNetword.main(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			DownkankanNetwork.main(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			DownIqiyiNetword.main(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// DownDoubanNetword.main(null);//runstatic();
		// DownDoubanChaXunNetword.main(null);
		CommonUtil.setLog("type:����||date:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + "||logs:����粥���� �������;");
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}

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
		}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(00, 00, 00);
		//�����
		// runstatic();
	}

}
