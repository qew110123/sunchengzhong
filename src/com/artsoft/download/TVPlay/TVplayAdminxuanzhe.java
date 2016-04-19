package com.artsoft.download.TVPlay;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class TVplayAdminxuanzhe {
	public static void runstatic(int numi) {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		switch (numi) {
		case 1:
			DownloadYouku.runstatic();
			break;
		case 2:
			DownloadIqiyi.runstatic();
			break;
		case 3:
			Downloadqq.runstatic();
			break;
		case 4:
			DownloadSohu.runstatic();
			break;
		case 5:
			DownloadLetv.runstatic();
			break;
		case 6:
			Downloadpptv.runstatic();
			break;
		case 7:
			DownloadHunantv.runstatic();
			break;
		case 8:
			Downloadkankan.runstatic();
			break;

		default:
			System.out.println("出错");
			break;
		}
		// DownloadYouku.runstatic();
		// DownloadIqiyi.runstatic();
		// Downloadqq.runstatic();
		// DownloadSohu.runstatic();
		// DownloadLetv.runstatic();
		//// Downloadpptv.runstatic();
		//// DownloadHunantv.runstatic();
		// Downloadkankan.runstatic();
		// System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println(numi);
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss, final int numi) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic(numi);
			}
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TimingTime(04, 00, 00);
		// runstatic();

		Scanner in = new Scanner(System.in);
		System.out.println("请输入	" + "1DownloadYouku.runstatic();\n" + "2.DownloadIqiyi.runstatic();\n"
				+ "3.Downloadqq.runstatic();\n" + "4.DownloadSohu.runstatic();\n" + "5.DownloadLetv.runstatic();//\n"
				+ "6.Downloadpptv.runstatic();//\n" + "7.DownloadHunantv.runstatic();\n"
				+ "8.Downloadkankan.runstatic();");
		String numi = in.next();
		System.out.println(numi);
		TimingTime(04, 00, 00, Integer.parseInt(numi));

	}

}
