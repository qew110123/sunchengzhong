package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class zhongguopiaofangwang_danri {
	
	
	public static void openstatic() {
		// String datetext = TimeTest.getNowTime("yyyy-MM-dd");
		// String datetext = TimeTest.getBeforeAfterDate("2016-04-29",
		// -1).toString();
		for (int i = 0; i > -9; i--) {
			//http://www.cbooo.cn/boxOffice/GetDayBoxOffice?num=-2
		
			//String urlMain = "http://www.cbooo.cn/boxOffice/GetDayBoxOffice?num=" + i;
			// urlMain = "";
			//opensUrl(urlMain);

		}
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
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
			}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(11, 59, 59);
	}

}
