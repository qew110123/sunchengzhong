package com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.shouhuyule;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class shouhuyule_2 {

	
	
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
//				runstatic();
				try {
					
//					runnewMain("http://yule.sohu.com/tv.shtml");
//					
//					DBManager dbm = DBManager.instance();
//					dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}

			
		}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
	}
	
	public static void main(String[] args) {
	//TimingTime(1, 59, 59);
		shouhuyule_1.runnewMain("http://yule.sohu.com/movie.shtml",3);
	}

}
