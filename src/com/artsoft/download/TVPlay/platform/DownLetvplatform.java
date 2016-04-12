package com.artsoft.download.TVPlay.platform;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownLetvplatform {

	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// System.out.println(strHtml);
		JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
		JSONArray album_lists = new JSONArray();
		album_lists=(JSONArray) objectobjectmain.get("album_list");
		for (Object object : album_lists) {
			TvPlay playtv=new TvPlay();
			JSONObject objectobject = JSONObject.fromObject(object);
			
			String url=(String) objectobject.get("vids");
			System.out.println(url="http://www.letv.com/ptv/vplay/"+url.split(",")[0]+".html");
			playtv.setTvplay_url(url);
			
			String name = (String) objectobject.get("name");
			System.out.println(name);
			playtv.setTvplay_name(name);
			
			String diqu=""; //����
			diqu = (String) objectobject.get("areaName");
			playtv.setProduction_area(diqu);
			
			String niandai=""; //���
			niandai=(String) objectobject.get("releaseDate");
			Date   now   =   new   Date((long) Double.parseDouble(niandai));   
			SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//���Է�����޸����ڸ�ʽ   
			String  hehe  = dateFormat.format(now);   
			System.out.println(hehe);
			playtv.setShoot_time(hehe);
			
			String classstr = ""; // ����:
			classstr = (String) objectobject.get("subCategoryName");
			System.out.println(classstr=classstr.replaceAll(",", "/"));
			playtv.setSubject(classstr);
			
			String yanyuan="";// ��Ա
			Object yanyuanAll= (Object) objectobject.get("starring");
			String[] yanyuanlist=yanyuanAll.toString().split(",");
			int i=0;
			for (String stringtext : yanyuanlist) {
				String urlss="";
				String textss=HtmlAnalyze.getTagText(stringtext, "\":\"", "\"");
				yanyuan=yanyuan+textss+"|"+urlss;
				if (yanyuanlist.length!=1&&i+1<yanyuanlist.length) {
					yanyuan=yanyuan+",";
				}
				
				i+=1;
			}
			System.out.println(yanyuan);
			playtv.setMajor_actors(yanyuan);

			
			
			String yuyan="";
			Object objectyuyan=objectobject.get("lgName");
			if (!objectyuyan.equals(null)) {
				yuyan= (String) objectyuyan;
				playtv.setLgName(yuyan);
				
			}
			
			String daoyan="";// ����
			Object daoyanAll= (Object) objectobject.get("directory");
			daoyan=HtmlAnalyze.getTagText(daoyanAll.toString(), "\":\"", "\"");
			System.out.println(daoyan);
			playtv.setDirector(daoyan);
			
			
			String detail="";
			detail=(String) objectobject.get("description");
			if (detail==null||detail.equals("")||detail.equals("null")) {
				detail=HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
			}
			System.out.println(detail);
			playtv.setBasic_info(detail);
			
			playtv.setClassnum(5);
			
			playtv.setSOURCE(5);
			
//			OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
			OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
		}
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

		for (int i = 0; i < 30; i++) {
			String mainUrl = "http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=" + i + "&s=1";
			mainurl(mainUrl);
		}
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
		}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < 30; i++) {
//			String mainUrl = "http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=" + i + "&s=1";
//			mainurl(mainUrl);
		
//		}
		TimingTime(23, 59, 59);
//		runstatic();
	}
	

}
