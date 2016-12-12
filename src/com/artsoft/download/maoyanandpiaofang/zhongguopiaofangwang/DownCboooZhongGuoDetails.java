package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_xunzhe.zhongguopiaofangwang_xunzhe;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DownCboooZhongGuoDetails {
	static int i = 0;
	
	public static void xiangxiurl(TvPlay playtv,String url ,String area){
		boolean bb = true;
		String strHtmllittle = "";
		while (bb) {
			strHtmllittle = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtmllittle != null && !"".equals(strHtmllittle)) {
				bb = false;
			}
		}
		String classstr = ""; // ����:
		classstr = HtmlAnalyze.getTagText(strHtmllittle, "���ͣ�", "</p>");
		System.out.println(classstr = classstr.replaceAll(",", "/"));
		playtv.setSubject(classstr);

		String shichang = ""; // ʱ��
		shichang = HtmlAnalyze.getTagText(strHtmllittle, "Ƭ��", "</p>");
		shichang = shichang.replaceAll("��", "");
		playtv.setTime_length(shichang);

		String times = ""; // ��ӳ:
		times = HtmlAnalyze.getTagText(strHtmllittle, "��ӳʱ�䣺", "</p>");
		// System.out.println(times=times.replaceAll("-", ""));
		playtv.setShow_date(times);

		String diqu = ""; // ����
		diqu = HtmlAnalyze.getTagText(strHtmllittle, "������", "</p>");
		playtv.setProduction_area(diqu);
		
		String PRODUCE_FORMAT = ""; // ����
		PRODUCE_FORMAT = HtmlAnalyze.getTagText(strHtmllittle, "��ʽ��", "</p>");
		playtv.setPRODUCE_FORMAT(PRODUCE_FORMAT);

		String yanyuan = "";// ��Ա
		Object yanyuanAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>���ݣ�</dt>", "</dd>", true, 0);
		String[] yanyuanlist = yanyuanAll.toString().split("</p>");
		int i = 0;
		for (String stringtext : yanyuanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\"");
			if (textss.equals("null")||textss.equals("")||textss==null) {
				textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			}
			if (textss != "") {
				yanyuan = yanyuan + textss + "|" + urlss;
				if (yanyuanlist.length != 1 && i + 2 < yanyuanlist.length) {
					yanyuan = yanyuan + ",";
				}
			}

			i += 1;
		}
		System.out.println(yanyuan);
		if (yanyuan.length()>2000) {
			yanyuan=yanyuan.substring(0,2000);
		}
		playtv.setMajor_actors(yanyuan);

		String daoyan = "";// ����
		String daoyanAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>���ݣ�</dt>", "</dd>", true, 0);
		String[] daoyanlist = daoyanAll.split("</p>");
		i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\"");
			if (textss.equals("null")||textss.equals("")||textss==null) {
				textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			}
			if (textss != "") {
				daoyan = daoyan + textss + "|" + urlss;
				if (daoyanlist.length != 1 && i + 2 < daoyanlist.length) {
					daoyan = daoyan + ",";
				}
			}

			i += 1;
		}
		System.out.println(daoyan);
		if (daoyan.length()>2000) {
			daoyan=daoyan.substring(0, 2000);
		}
		playtv.setDirector(daoyan);
		
		
		String PRODUCE_COMPANY = "";// ������˾
		String PRODUCE_COMPANYAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>������˾��</dt>", "</dd>", true, 0);
		String[] PRODUCElist = PRODUCE_COMPANYAll.split("</p>");
		i = 0;
		for (String stringtext : PRODUCElist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\"");
			if (textss.equals("null")||textss.equals("")||textss==null) {
				textss = HtmlAnalyze.getTagText(stringtext, "title=\"\">", "<");
			}
			if (textss != "") {
				PRODUCE_COMPANY = PRODUCE_COMPANY + textss + "|" + urlss;
				if (PRODUCElist.length != 1 && i + 2 < PRODUCElist.length) {
					PRODUCE_COMPANY = PRODUCE_COMPANY + ",";
				}
			}

			i += 1;
		}
		System.out.println(PRODUCE_COMPANY);
		playtv.setPRODUCE_COMPANY(PRODUCE_COMPANY);
		
		
		String issuing_company = "";// ���й�˾
		String issuing_companyAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>���й�˾��</dt>", "</dd>", true, 0);
		String[] issuing_companylist = issuing_companyAll.split("</p>");
		i = 0;
		for (String stringtext : issuing_companylist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\"");
			if (textss.equals("null")||textss.equals("")||textss==null) {
				textss = HtmlAnalyze.getTagText(stringtext, "title=\"\">", "<");
			}
			if (textss != "") {
				issuing_company = issuing_company + textss + "|" + urlss;
				if (daoyanlist.length != 1 && i + 2 < issuing_companylist.length) {
					issuing_company = issuing_company + ",";
				}
			}

			i += 1;
		}
		System.out.println(issuing_company);
		playtv.setIssuing_company(issuing_company);
		
		
		
		

		// String yuyan="";
		// yuyan= (String) objectobject.get("lgName");
		// playtv.setLgName(yuyan);

		playtv.setClassnum(10);
		
		playtv.setSELAREA(area);
		OracleOpreater.intoTEM_DIM_FILM_PLATFORM_SELAREA(playtv);
	}

	private static String cboooMaim(String mainUrl,String area) {
		// TODO Auto-generated method stub
		try {

			String strHtml = "";
			boolean bb = true;
			while (bb) {
				strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
				if (strHtml != null && !"".equals(strHtml)) {
					bb = false;
				}
			}

			JSONObject objectobjectmain = JSONObject.fromObject(strHtml);
			JSONArray album_lists = new JSONArray();
			album_lists = (JSONArray) objectobjectmain.get("pData");
			for (Object object : album_lists) {
				TvPlay playtv = new TvPlay();
				JSONObject objectobject = JSONObject.fromObject(object);
				playtv.setTvplay_id( (String) objectobject.get("ID"));
				String url = (String) objectobject.get("ID");
				System.out.println(url = "http://www.cbooo.cn/m/" + url);
				playtv.setTvplay_url(url);

				String name = (String) objectobject.get("MovieName");
				System.out.println(name);
				playtv.setTvplay_name(name);

				String niandai = ""; // ���
				niandai = (String) objectobject.get("releaseYear");
				System.out.println(niandai);
				playtv.setShoot_time(niandai);

				String bieming = "";// ����
				bieming = (String) objectobject.get("MovieEnName");
				System.out.println(bieming = bieming.replace("	", ""));
				playtv.setAlias_en(bieming);

				String BoxOffice = ""; // Ʊ��
				BoxOffice = (String) objectobject.get("BoxOffice");
				playtv.setBox_office(BoxOffice);
				
				xiangxiurl(playtv, url,area);

				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void openstatic(String ateaid,String area) {
		
		String url = "http://www.cbooo.cn/Mdata/getMdata_movie?area="+ateaid+"&type=0&year=0&initial=%E5%85%A8%E9%83%A8&pIndex=1";
		
//		while (bb) {
		String	strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		String tPage=HtmlAnalyze.getTagText(strHtml, "tPage\":", ",");
		
		int inttPage=Integer.parseInt(tPage);
		
		
//		String url = "";
		
		for (int i = 1; i < inttPage+1; i++) {
//			url = "http://www.cbooo.cn/Mdata/getMdata_movie?area=50&type=0&year=0&initial=%E5%85%A8%E9%83%A8&pIndex="
//					+ i + "";
			
			url = "http://www.cbooo.cn/Mdata/getMdata_movie?area="+ateaid+"&type=0&year=0&initial=%E5%85%A8%E9%83%A8&pIndex="
					+ i + "";
			System.out.println(url);
			try {
				DownCboooZhongGuoDetails.cboooMaim(url,area);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	public static void openstatic_other() {
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText("http://www.cbooo.cn/movies", 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		Element iddoc= Jsoup.parse(strHtml);
		
		Elements selArealist=iddoc.getElementById("selArea").select("option");
		String ateaid="";
		String area="";
		for (Element element : selArealist) {
//			System.out.println(element);
			System.out.println(ateaid=element.attr("value"));
			System.out.println(area=element.text());
//			if (!ateaid.equals("16")&&!ateaid.equals("25")&&!ateaid.equals("1")&&!ateaid.equals("40")&&!ateaid.equals("37")&&!ateaid.equals("50")) {
				try {
					openstatic(ateaid, area);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
//			}
		}
	}
	
	private static void openMain() {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText("http://www.cbooo.cn/", 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		Element iddoc= Jsoup.parse(strHtml);
		
		Elements selArealist=iddoc.getElementById("topdatatr").select("tr.trtop");
		String ateaid="";
		String area="";
		for (Element element : selArealist) {
//			System.out.println(element);
			System.out.println(ateaid=element.attr("id"));
//			System.out.println(area=element.text());
			if (!ateaid.equals("")&&ateaid!=null) {
				String url="http://www.cbooo.cn/m/"+ateaid;
				zhongguopiaofangwang_xunzhe.xuanzheng(url);
			}
		}
		
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		try {
			openMain();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			
			openstatic_other();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
//				runstatic();
				runstaticshijian();
			}
		}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
	}
	
	
	public static void runstaticshijian(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

		if (TimeTest.getNowTime("HH").equals("09") ) {
//			CountNum.runCount();
			runstatic();
		}
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}

	/**
	 * �й�Ʊ�� cbooo
	 * 
	 * @param args
	 */
	///////////////////////
	public static void main(String[] args) {
//		openstatic();
//		openstatic_other();
//		TimingTime(hh, mm, ss);
//		TvPlay playtv = new TvPlay();
//		DownCboooZhongGuoDetails.xiangxiurl(playtv,"http://www.cbooo.cn/m/3354","�й�");
//		 
		
//		openMain();
		
		TimingTime(2, 59, 59);
		 
	}

}
