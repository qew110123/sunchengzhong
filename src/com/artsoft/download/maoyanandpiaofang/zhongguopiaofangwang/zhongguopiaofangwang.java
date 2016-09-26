package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class zhongguopiaofangwang {

	private static void opensUrl(String urlMain) {
		// TODO Auto-generated method stub
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://www.cbooo.cn/boxOffice/GetDayBoxOffice?num=0";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
		JSONArray album_lists = new JSONArray();

		JSONArray data2 = (JSONArray) objectobjectmain.get("data2");
		String DATA_DATE = "";
		for (Object object : data2) {
			JSONObject objectobject = JSONObject.fromObject(object);
			DATA_DATE = (String) objectobject.get("BoxDate");
			DATA_DATE = DATA_DATE.replace("-", "");
		}

		album_lists = (JSONArray) objectobjectmain.get("data1");
		String TITLE = "";
		String URL = "";
		String RELEASED_DAYS = "";
		String TOTAL_BOXOFFICE = "";
		String REAL_TIME_BOXOFFICE = "";
		String BOXOFFICE_RATE = "";
		String SLICE_RATE = "";
		String ATTENDANCE_RATE = "";
		String FID = "";
		// String BOXOFFICE_TYPE="";
		String FIELD_AVERAGE_PRICE = "";
		String FIELD_AVERAGE_PNUM = "";
		String BOXOFFICE_UP = "";
		String WOMINDEX = "";
		String MOVIEIMG = "";
		for (Object object : album_lists) {
			JSONObject objectobject = JSONObject.fromObject(object);
			TITLE = (String) objectobject.get("MovieName");
			FID = (String) objectobject.get("MovieID");
			if (!FID.equals("")) {
				URL = "http://www.cbooo.cn/m/" + FID;
			}
			RELEASED_DAYS = (String) objectobject.get("MovieDay");
			TOTAL_BOXOFFICE = (String) objectobject.get("SumBoxOffice");
			if (!TOTAL_BOXOFFICE.equals("")) {
				TOTAL_BOXOFFICE = TOTAL_BOXOFFICE + "��";
			}
			REAL_TIME_BOXOFFICE = (String) objectobject.get("BoxOffice");
			if (!REAL_TIME_BOXOFFICE.equals("")) {
				REAL_TIME_BOXOFFICE = REAL_TIME_BOXOFFICE + "��";
			}
			FIELD_AVERAGE_PRICE = (String) objectobject.get("AvgPrice");
			FIELD_AVERAGE_PNUM = (String) objectobject.get("AvpPeoPle");
			// FIELD_AVERAGE_PNUM=(String) objectobject.get("AvpPeoPle");
			BOXOFFICE_UP = (String) objectobject.get("BoxOffice_Up");
			WOMINDEX = (String) objectobject.get("WomIndex");
			MOVIEIMG = (String) objectobject.get("MovieImg");
			if (!MOVIEIMG.equals("")) {
				MOVIEIMG = "http://www.cbooo.cn" + MOVIEIMG;
			}
			OracleMovePiaoFang.intoTEM_FILM_DAILY_BOXOFFICE(DATA_DATE, TITLE, URL, FID, RELEASED_DAYS, TOTAL_BOXOFFICE,
					REAL_TIME_BOXOFFICE, "", "", "", 4, "", URL, "", FIELD_AVERAGE_PRICE, FIELD_AVERAGE_PNUM,
					BOXOFFICE_UP, WOMINDEX, MOVIEIMG);

		}

	}

	/**
	 * private String fid; private String title; private String dataDate;
	 * private String cityName; private String dailyBoxoffice; private String
	 * boxofficeRate; private String sliceRate; private String totalBoxoffice;
	 * private String seatRate; private String goldFieldRate; private String
	 * fieldAveragePnum; private String pnum; private String fieldNum; private
	 * Date intoDate; private Date updateDate; private String collectionUrl;
	 */

	public  static void quanguopaipian(String urlMain, String data_date) {
		// TODO Auto-generated method stub
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://www.cbooo.cn/Screen/getScreenData?days=0";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		// String DATA_DATE = "";
		// DATA_DATE = TimeTest.getNowTime("yyyyMMdd");

		JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
		JSONArray album_lists = new JSONArray();
		try {
			
		
		JSONArray data3 = (JSONArray) objectobjectmain.get("data3");
		String TOTAL_FIELD_NUM = "";
		for (Object object : data3) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// DATA_DATE=(String) objectobject.get("BoxDate");
			// DATA_DATE=DATA_DATE.replace("-", "");
			// fid = (String) objectobject.get("id");
			// title = (String) objectobject.get("name");
			// daily_boxoffice = (String) objectobject.get("citynum");
			TOTAL_FIELD_NUM = (String) objectobject.get("countrynum");
			// city_name=(String) objectobject.get("cityname");
		}

		album_lists = (JSONArray) objectobjectmain.get("data1");
		String fid = "";
		String title = "";
		String FIELD_NUM = "";
		String boxoffice_rate = "";
		for (Object object : album_lists) {
			JSONObject objectobject = JSONObject.fromObject(object);
			fid = (String) objectobject.get("movieid");
			title = (String) objectobject.get("moviename");
			FIELD_NUM = (String) objectobject.get("movienum");
			boxoffice_rate = (String) objectobject.get("Per_num");

			OracleMovePiaoFang.intotem_film_city(fid, title, data_date, "ȫ��", "", "", "", "", "", "", "", "", FIELD_NUM,
					urlMain, TOTAL_FIELD_NUM, 4);

		}

		String citynum = "";
		String city_name = "";
		JSONArray data2 = (JSONArray) objectobjectmain.get("data2");
		for (Object object : data2) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// DATA_DATE=(String) objectobject.get("BoxDate");
			// DATA_DATE=DATA_DATE.replace("-", "");
			fid = (String) objectobject.get("movieid");
			title = (String) objectobject.get("cnName");
			FIELD_NUM = (String) objectobject.get("sumnum");
			TOTAL_FIELD_NUM = (String) objectobject.get("citynum");
			city_name = (String) objectobject.get("cityname");
			OracleMovePiaoFang.intotem_film_city(fid, title, data_date, city_name, "", "", "", "", "", "", "", "",
					FIELD_NUM, urlMain, TOTAL_FIELD_NUM, 4);
		}

		// ��ָ֪��
		String MovieID = "";
//		String MovieName = "";
		String RenZhiIndex = "";
		String InsertDate = "";
		String days = "";
		JSONArray data4 = (JSONArray) objectobjectmain.get("data4");
		for (Object object : data4) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// DATA_DATE=(String) objectobject.get("BoxDate");
			// DATA_DATE=DATA_DATE.replace("-", "");
			MovieID = (String) objectobject.get("MovieID");
			title = (String) objectobject.get("MovieName");
			RenZhiIndex = (String) objectobject.get("RenZhiIndex");
			InsertDate = (String) objectobject.get("InsertDate");
			days = (String) objectobject.get("days");

			int data_typeint = 1;
			if (!data_date.equals(TimeTest.getNowTime("yyyyMMdd"))) {
				data_typeint = 2;
			}

			OracleMovePiaoFang.intotem_want_see_index(data_date, MovieID, title, RenZhiIndex, urlMain, 4, days, 2,
					data_typeint, InsertDate);

		}

		// ��Ʊָ��
		String BuyTicketIndex = "";
		JSONArray data5 = (JSONArray) objectobjectmain.get("data5");
		for (Object object : data5) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// DATA_DATE=(String) objectobject.get("BoxDate");
			// DATA_DATE=DATA_DATE.replace("-", "");
			MovieID = (String) objectobject.get("MovieID");
			title = (String) objectobject.get("MovieName");
			BuyTicketIndex = (String) objectobject.get("BuyTicketIndex");
			InsertDate = (String) objectobject.get("InsertDate");
			days = (String) objectobject.get("days");

			int data_typeint = 1;
			if (!data_date.equals(TimeTest.getNowTime("yyyyMMdd"))) {
				data_typeint = 2;
			}

			OracleMovePiaoFang.intotem_want_see_index(data_date, MovieID, title, BuyTicketIndex, urlMain, 4, days, 3,
					data_typeint, InsertDate);

		}
		// �ڱ�ָ��
		String RapIndex = "";
		JSONArray data6 = (JSONArray) objectobjectmain.get("data6");
		for (Object object : data6) {
			JSONObject objectobject = JSONObject.fromObject(object);
			// DATA_DATE=(String) objectobject.get("BoxDate");
			// DATA_DATE=DATA_DATE.replace("-", "");
			MovieID = (String) objectobject.get("MovieID");
			title = (String) objectobject.get("MovieName");
			RapIndex = (String) objectobject.get("RapIndex");
			InsertDate = (String) objectobject.get("InsertDate");
			days = (String) objectobject.get("days");

			int data_typeint = 1;
			if (!data_date.equals(TimeTest.getNowTime("yyyyMMdd"))) {
				data_typeint = 2;
			}

			OracleMovePiaoFang.intotem_want_see_index(data_date, MovieID, title, RapIndex, urlMain, 4, days, 4,
					data_typeint, InsertDate);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void diyupaipian(String urlMain) {
		// TODO Auto-generated method stub

		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://www.cbooo.cn/Screen/getAreaScreenData?days=0";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		String DATA_DATE = "";
		DATA_DATE = TimeTest.getNowTime("yyyyMMdd");

		JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
		JSONArray album_lists = new JSONArray();

		album_lists = (JSONArray) objectobjectmain.get("data1");
		String Level = "";
		String fid = "";
		String title = "";
		String field_num = "";
		// String boxoffice_rate = "";
		for (Object object : album_lists) {
			JSONObject objectobject = JSONObject.fromObject(object);
			Level = (String) objectobject.get("Level");
			fid = (String) objectobject.get("movieid");
			title = (String) objectobject.get("cnName");
			field_num = (String) objectobject.get("sunnum");
			// boxoffice_rate = (String) objectobject.get("Per_num");
			// OracleMovePiaoFang.intoTEM_FILM_DAILY_BOXOFFICE(DATA_DATE, TITLE,
			// URL, FID, RELEASED_DAYS, TOTAL_BOXOFFICE,
			// REAL_TIME_BOXOFFICE, "", "", "", 4, "", URL, "",
			// FIELD_AVERAGE_PRICE, FIELD_AVERAGE_PNUM, BOXOFFICE_UP, WOMINDEX,
			// MOVIEIMG);
			String city_name = "";
			if (Level.equals("4") || Level.equals("5")) {
				if (Level.equals("4")) {
					city_name = "���߳���";
				}
				if (Level.equals("5")) {
					city_name = "���߳���";
				}
				OracleMovePiaoFang.intotem_film_city(fid, title, DATA_DATE, city_name, "", "", "", "", "", "", "", "",
						field_num, urlMain, "", 4);
			}

		}

	}

	public static void numxiantvcity(String urlMain) {
		// TODO Auto-generated method stub

		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
			urlMain = "http://www.cbooo.cn/Screen/getCityData?days=0&cityType=1";
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		String DATA_DATE = "";
		DATA_DATE = TimeTest.getNowTime("yyyyMMdd");

		JSONArray objectobjectmain = JSONArray.fromObject(strHtml);
		JSONArray album_lists = new JSONArray();

		album_lists = objectobjectmain;
		String Level = "";
		String fid = "";
		String title = "";
		String field_num = "";
		 String total_field_num = "";
		 try {
			
			 for (Object object : album_lists) {
				 JSONObject objectobject = JSONObject.fromObject(object);
				 Level = (String) objectobject.get("Level");
				 fid = (String) objectobject.get("movieid");
				 title = (String) objectobject.get("cnName");
//			field_num = (String) objectobject.get("num_pro");
				 field_num = (String) objectobject.get("sunnum");
				 total_field_num = (String) objectobject.get("tolNum");
				 // boxoffice_rate = (String) objectobject.get("Per_num");
				 // OracleMovePiaoFang.intoTEM_FILM_DAILY_BOXOFFICE(DATA_DATE, TITLE,
				 // URL, FID, RELEASED_DAYS, TOTAL_BOXOFFICE,
				 // REAL_TIME_BOXOFFICE, "", "", "", 4, "", URL, "",
				 // FIELD_AVERAGE_PRICE, FIELD_AVERAGE_PNUM, BOXOFFICE_UP, WOMINDEX,
				 // MOVIEIMG);
				 String city_name = "";
				 // if (Level.equals("4")||Level.equals("5")) {
				 if (Level.equals("1")) {
					 city_name = "һ�߳���";
				 }
				 if (Level.equals("2")) {
					 city_name = "���߳���";
				 }
				 if (Level.equals("3")) {
					 city_name = "���߳���";
				 }
				 
				 if (Level.equals("4")) {
					 city_name = "���߳���";
				 }
				 if (Level.equals("5")) {
					 city_name = "���߳���";
				 }
				 OracleMovePiaoFang.intotem_film_city(fid, title, DATA_DATE, city_name, "", "", "", "", "", "", "", "",
						 field_num, urlMain, total_field_num, 4);
				 // }
				 
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void openstatic() {
		// String datetext = TimeTest.getNowTime("yyyy-MM-dd");
		// String datetext = TimeTest.getBeforeAfterDate("2016-04-29",
		// -1).toString();
		for (int i = 0; i > -9; i--) {
			String urlMain = "http://www.cbooo.cn/boxOffice/GetDayBoxOffice?num=" + i;
			// urlMain = "";
			opensUrl(urlMain);

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
	
	
	
	

	public static void runstaticshijian(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		System.out.println(TimeTest.getNowTime("HH"));
		if (TimeTest.getNowTime("HH").equals("12") || TimeTest.getNowTime("HH").equals("09")) {
			runstatic();
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}
	
	
	 /* ����ʱ��
	 * 2016��8��17��17:30:00
	 * @param args
	 */
	public static void rundingshitime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ  
        calendar.set(Calendar.MINUTE, mm);       // ���Ʒ�  
        calendar.set(Calendar.SECOND, ss);       // ������  
  
        Date time = calendar.getTime();         // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00  
  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                System.out.println("-------�趨Ҫָ������--------");  
                try {
					
                	runstaticshijian();
				} catch (Exception e) {
					// TODO: handle exception
				}
            } 
        }, time, 1000 * 60 * 60 * 1);// �����趨����ʱÿ��̶�ִ��  
    }
public static void main(String[] args) {
	// TODO Auto-generated method stub
//	 TimingTime(1, 00, 00);
	 rundingshitime(1, 10, 00);
//	runstatic();
}

}
