package com.artsoft.download.maoyanandpiaofang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class zhongguopioafangwang_diqu {
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
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
		TimingTime(6, 59, 59);

	}

}
