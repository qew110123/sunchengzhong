package com.artsoft.download.variety.Each;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Each_variety_Iqiyi {
	
	
	public static void IqiyiBranch(String urlall){
		if (urlall.equals("")) {
			return;
		}
		//http://www.iqiyi.com/a_19rrhb2g1t.html#vfrm=2-4-0-1
		//http://cache.video.qiyi.com/jp/sdvlst/6/202742701/?categoryId=6&sourceId=202742701&callback=window.Q.__callbacks__.cbu8so7h
		String html=DownloadUtil.getHtmlText(urlall, 30000, "UTF-8", null, null);
		Document doc=Jsoup.parse(html);
		String albumId=HtmlAnalyze.getTagText(html, "albumId: ", ",");
		String cid=HtmlAnalyze.getTagText(html, "cid: ", ",");
		
		String playUrl="http://cache.video.qiyi.com/jp/sdvlst/"+cid+"/"+albumId+"/?categoryId="+cid+"&sourceId="+albumId+"&callback=window.Q.__callbacks__.cbu8so7h";
		String realhtml=DownloadUtil.getHtmlText(playUrl, 30000, "UTF-8", null, null);
		realhtml=HtmlAnalyze.getTagText(realhtml, "(", ");}");
		JSONObject Iqiyijson = new JSONObject();
		Iqiyijson = JSONObject.fromObject(realhtml);
		JSONArray IqiyijsonArray = new JSONArray();
		IqiyijsonArray= (JSONArray) Iqiyijson.get("data");
		for (Object object : IqiyijsonArray) {
			String tyPlayName="";
			String source="2";
			String playAmount="";
			String vodeoType="0";
			String palydate="";
			String tvType="2";
			String realUrl="";
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(tyPlayName=(String) objectobject.get("videoName"));
			System.out.println(realUrl=(String)  objectobject.get("vUrl"));
			System.out.println( objectobject.get("disCnt"));
			System.out.println(playAmount = ""+objectobject.get("disCnt"));
			OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, playUrl, tvType, realUrl);
		}
	}
	
	/**
	 * 2016��5��31��18:03:17
	 * �����������ݵĸ�ϸ
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuvariety(date_date,"2");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				IqiyiBranch(listTemp.get(0));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog("�ſ�����" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		openordor();
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
			}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(1, 59, 59);
//		openordor();
//		IqiyiBranch("http://www.iqiyi.com/a_19rrgiarlt.html#vfrm=2-4-0-1");
	}

}
