package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.bean.TEM_FILM_BOXOFFICE_REALTIME;
import com.artsoft.bean.TvPlay;
import com.artsoft.download.Movies.Details.DownCboooZhongGuoDetails;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class zhongguopiaofangwang_zhongpiaofang {
	static int i = 0;
	
	public static void xiangxiurl(TvPlay playtv,String url ){
		boolean bb = true;
		String strHtmllittle = "";
		while (bb) {
			strHtmllittle = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtmllittle != null && !"".equals(strHtmllittle)) {
				bb = false;
			}
		}
		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtmllittle, "类型：", "</p>");
		System.out.println(classstr = classstr.replaceAll(",", "/"));
		playtv.setSubject(classstr);

		String shichang = ""; // 时长
		shichang = HtmlAnalyze.getTagText(strHtmllittle, "片长", "</p>");
		shichang = shichang.replaceAll("：", "");
		playtv.setTime_length(shichang);

		String times = ""; // 上映:
		times = HtmlAnalyze.getTagText(strHtmllittle, "上映时间：", "</p>");
		// System.out.println(times=times.replaceAll("-", ""));
		playtv.setShow_date(times);

		String diqu = ""; // 地区
		diqu = HtmlAnalyze.getTagText(strHtmllittle, "地区：", "</p>");
		playtv.setProduction_area(diqu);
		
		String PRODUCE_FORMAT = ""; // 制作
		PRODUCE_FORMAT = HtmlAnalyze.getTagText(strHtmllittle, "制式：", "</p>");
		playtv.setPRODUCE_FORMAT(PRODUCE_FORMAT);

		String yanyuan = "";// 演员
		Object yanyuanAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>主演：</dt>", "</dd>", true, 0);
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
		playtv.setMajor_actors(yanyuan);

		String daoyan = "";// 导演
		String daoyanAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>导演：</dt>", "</dd>", true, 0);
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
		
		
		String PRODUCE_COMPANY = "";// 制作公司
		String PRODUCE_COMPANYAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>制作公司：</dt>", "</dd>", true, 0);
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
		
		
		String issuing_company = "";// 发行公司
		String issuing_companyAll = HtmlAnalyze.getTagText(strHtmllittle, "<dt>发行公司：</dt>", "</dd>", true, 0);
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
//		OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		
		TEM_FILM_BOXOFFICE_REALTIME  realitme=new TEM_FILM_BOXOFFICE_REALTIME();
//		realitme.setREAL_DATE(REAL_DATE);
		realitme.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
		
//		BoxOffice = (String) objectobject.get("BoxOffice");
//		realitme.setRealTimeBoxoffice(BoxOffice);
//		title = (String) objectobject.get("MovieName");
		realitme.setTitle(playtv.getTvplay_name());
//		field_num = (String) objectobject.get("num_pro");
//		boxPer = (String) objectobject.get("boxPer");
//		realitme.setBoxofficeRate(playtv.getBox_office());
//		mId = (String) objectobject.get("mId");
		realitme.setFid(String.valueOf(playtv.getTvplay_id()));
//		movieDay = (String) objectobject.get("movieDay");
//		realitme.setReleasedDays(movieDay);
//		sumBoxOffice = (String) objectobject.get("sumBoxOffice");
		realitme.setTotalBoxoffice(playtv.getBox_office());
		//http://www.cbooo.cn/m/642020
//		url="http://www.cbooo.cn/m/"+mId;
		realitme.setUrl(playtv.getTvplay_url());
		realitme.setCollectionUrl(playtv.getTvplay_url());
		
		realitme.setDATA_TYPE(1);;
		OracleMovePiaoFang.intoTEM_FILM_BOXOFFICE_REALTIME(realitme);
		
		
		
	}

	private static String cboooMaim(String mainUrl) {
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
				playtv.setTvplay_id( Integer.parseInt((String) objectobject.get("ID")));
				String url = (String) objectobject.get("ID");
				System.out.println(url = "http://www.cbooo.cn/m/" + url);
				playtv.setTvplay_url(url);

				String name = (String) objectobject.get("MovieName");
				System.out.println(name);
				playtv.setTvplay_name(name);

				String niandai = ""; // 年代
				niandai = (String) objectobject.get("releaseYear");
				System.out.println(niandai);
				playtv.setShoot_time(niandai);

				String bieming = "";// 别名
				bieming = (String) objectobject.get("MovieEnName");
				System.out.println(bieming = bieming.replace("	", ""));
				playtv.setAlias_en(bieming);

				String BoxOffice = ""; // 票房
				BoxOffice = (String) objectobject.get("BoxOffice");
				playtv.setBox_office(BoxOffice);
				
				xiangxiurl(playtv, url);

				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void openstatic() {
		String url = "";
		for (int i = 1; i < 255; i++) {
			url = "http://www.cbooo.cn/Mdata/getMdata_movie?area=50&type=0&year=0&initial=%E5%85%A8%E9%83%A8&pIndex="
					+ i + "";
			System.out.println(url);
			try {
				cboooMaim(url);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行
	}

	/**
	 * 中国票房 cbooo
	 * 中票房网，每日运行 总票房
	 * @param args
	 */
	///////////////////////
	public static void main(String[] args) {
		openstatic();
//		TimingTime(hh, mm, ss);
		 TimingTime(0, 59, 59);
//		TvPlay playtv = new TvPlay();
//			DownCboooZhongGuoDetails.xiangxiurl(playtv,"http://www.cbooo.cn/m/1008");
	}

}
