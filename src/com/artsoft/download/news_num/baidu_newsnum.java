package com.artsoft.download.news_num;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class baidu_newsnum {

	public static void baidunews(String id, String url, int data_type, int source) {
		System.out.println(url);
		String strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		int news_num = 0;
		String newsnumstring = HtmlAnalyze.getTagText(strHtml, "�ҵ��������", "ƪ");
		System.out.println(newsnumstring);
		if (!newsnumstring.equals("")) {
			news_num = Integer.parseInt(newsnumstring.replace(",", "").replace("Լ", ""));
			String data_date = TimeTest.getNowTime("yyyyMMdd");
			OracleBaidu.intotem_news_num(data_date, id, news_num, url, data_type, source);

		}
	}

	public static void peoplenews() {
		String returnNumPeople = OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
		System.out.println("��Ҫ�ɼ�����������Ϊ" + returnNumPeople);
		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			List<String> listArray = OracleHaoSou.selectname(Integer.toString(i), Integer.toString(i + 1000));
			for (Object Objstring : listArray) {
				System.out.println(Objstring);
				List<String> listTemp = (List<String>) Objstring;
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
					String person_id = listTemp.get(0);
					String keyword = listTemp.get(1);
					// String data_date = TimeTest.getNowTime("yyyyMMdd");
					String krywordutf8 = "";
					try {
						krywordutf8 = java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String url = "http://news.baidu.com/ns?ct=1&rn=20&ie=utf-8&bs=" + krywordutf8
							+ "&rsv_bp=1&sr=0&cl=2&f=8&prevct=no&tn=newstitle&word=" + krywordutf8
							+ "&qq-pf-to=pcqq.c2c";
					try {
						
						baidunews(person_id, url, 1, 1);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}
		}
	}

	public static void tvplaynews() {

		String returnNumTVle = OracleHaoSou.returnNumPeople("edw.dim_tvplay");
		System.out.println("��Ҫ�ɼ��ĵ��Ӿ���Ϊ" + returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
			List<String> listArray = OracleHaoSou.select(Integer.toString(i), Integer.toString(i + 1000));
			System.out.println(listArray.size());
			for (Object Objstring : listArray) {
				System.out.println(Objstring);
				List<String> listTemp = (List<String>) Objstring;
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
					String person_id = listTemp.get(0);
					String keyword = listTemp.get(1);
					// String data_date = TimeTest.getNowTime("yyyyMMdd");
					String krywordutf8 = "";
					try {
						krywordutf8 = java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String url = "http://news.baidu.com/ns?ct=1&rn=20&ie=utf-8&bs=" + krywordutf8
							+ "&rsv_bp=1&sr=0&cl=2&f=8&prevct=no&tn=newstitle&word=" + krywordutf8
							+ "&qq-pf-to=pcqq.c2c";
					try {
						
						baidunews(person_id, url, 2, 1);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}
		}
	}

	public static void openstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ��");
		peoplenews();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ���Ӿ�");
		tvplaynews();
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
		}, time, 1000 * 60 * 60 * 12);// �����趨����ʱÿ��̶�ִ��
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 openstatic();
		TimingTime(1, 59, 59);
		// baidunews("",
		// "http://news.baidu.com/ns?ct=1&rn=20&ie=utf-8&bs=%E5%94%90%E5%AB%A3&rsv_bp=1&sr=0&cl=2&f=8&prevct=no&tn=newstitle&word=%E5%94%90%E5%AB%A3&qq-pf-to=pcqq.c2c");
	}

}
