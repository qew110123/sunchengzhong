package com.artsoft.download.TVPlay.platform;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.TVPlay.DownloadIqiyi;
import com.artsoft.download.TVPlay.DownloadLetv;
import com.artsoft.download.TVPlay.DownloadSohu;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.download.TVPlay.Downloadkankan;
import com.artsoft.download.TVPlay.Downloadpptv;
import com.artsoft.download.TVPlay.Downloadqq;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import oracle.net.aso.p;

public class DownYouKuplatform {
	static int i = 0;

	/**
	 * �����ſ��������ҳ�������ݶβ�ѯ
	 * 
	 * @param urlMain
	 *            ////////////////////////////////////
	 */
	public static String youkuMaim(String urlMain) {
		try {
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.box-series div.p-thumb");
			for (Element link : links) {
				System.out.println("�������ݵĲɼ�" + (++i));
				if (i <= 0) {
					continue;
				}
				String strmainurl = "";
				strmainurl = link.select("a").attr("href");
				if (!strmainurl.contains("http://")) {
					strmainurl=strmainurl.replace("//", "http://");
				}
				System.out.println(strmainurl);
				
				String strHtml1 = DownloadUtil.getHtmlText(strmainurl, 1000 * 30, "UTF-8", null, null);
				Document strmainurlHtmldoc = Jsoup.parse(strHtml1);
//				Document strmainurlHtmldoc = Jsoup.connect(strmainurl).get();
				
				String strmainxiangxiurl=strmainurlHtmldoc.select("h1.title a").attr("href");
				if (strmainxiangxiurl==null||strmainxiangxiurl.equals("")||strmainxiangxiurl.equals("http://tv.youku.com/")||strmainxiangxiurl.equals("//tv.youku.com/")) {
//					System.out.println(strmainurlHtmldoc);
					strmainxiangxiurl=HtmlAnalyze.getTagText(strmainurlHtmldoc.toString(), "desc-link\" href=\"","\"");
				}
				if (!strmainxiangxiurl.contains("http://")) {
					strmainxiangxiurl=strmainxiangxiurl.replace("//", "http://");
				}
				
				if (strmainxiangxiurl.equals("http://sports.youku.com/")||strmainxiangxiurl.equals("http://comic.youku.com/")||strmainxiangxiurl.equals("http://zy.youku.com/")||strmainxiangxiurl.equals("")||!strmainxiangxiurl.contains("html")) {
					
					Elements strmainxiangxiurls=strmainurlHtmldoc.select("a");
					for (Element element : strmainxiangxiurls) {
						if (element.text().equals("��Ŀ���")) {
							strmainxiangxiurl=element.attr("href");
							if (!strmainxiangxiurl.contains("http://")) {
								strmainxiangxiurl=strmainxiangxiurl.replace("//", "http://");
							}
						}
					}
				}
				DownYouKuplatform.youkuBranch("",strmainxiangxiurl);
				
				Thread.sleep(1000);
			}

			// ������һҳ���ݵ��ж�

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"��һҳ\"><a href=\"",
					"\"  charset=");
			if (strnexturl != null || "".equals(strnexturl)) {
				strnexturl = "http://www.youku.com" + strnexturl;
				return strnexturl;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * �����ſ�������ϸ���ҳ���ݵĲɼ�
	 * 
	 * @param urlBranch
	 *            ///////////////////////////////
	 */
	public static void youkuBranch(String name, String urlBranch) {
		TvPlay playtv = new TvPlay();
		playtv.setTvplay_url(urlBranch);
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.yk-col3");

		/**
		 * �ܲ���: ����: ��:
		 */

//		String name = "";// ����
		if (name.equals("")) {
			
		
			name = doc.select("span.name").text();
			
			if (name.equals("")) {
				name=HtmlAnalyze.getTagText(strHtml, "<a title=\"", "\"");
			}
			System.out.println(name);
			
			if (name.equals("ԭ��")) {
				return;
			}
		}
		playtv.setTvplay_name(name);
		String Amount = "";// ������
		Amount = doc.select("span.play").text();
		if (Amount.equals("")) {
			Amount=HtmlAnalyze.getTagText(strHtml, "�ܲ�������", "<");
		}
		System.out.println(Amount);
		Amount = Amount.replaceAll("�ܲ���:", "").replaceAll(",", "");

		String comment = ""; // ����
		comment = doc.select("span.comment").text();
		if (comment.equals("")) {
			comment=HtmlAnalyze.getTagText(strHtml, "���ۣ�", "<");
		}
		comment = comment.replaceAll("����:", "").replaceAll(",", "");
		System.out.println(comment);
		
		String answer = ""; // ��
		answer = doc.select("span.increm").text();
		answer = answer.replaceAll("��:", "").replaceAll(",", "");
		if (answer.equals("")) {
			answer=HtmlAnalyze.getTagText(strHtml, "����", "<");
		}
		System.out.println(answer);
		String score = ""; // ����

		score = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "<style type=\"text/css\">");
		// Pattern pattern = Pattern.compile("[0-9]*");
		// Matcher isNum = pattern.matcher(score);
		// score=doc.select("span.ratingstar").text();
		if (score.contains("\r")) {
			score = HtmlAnalyze.getTagText("#" + score, "#", "\r");
		}
		if (score.equals("")) {
			score=HtmlAnalyze.getTagText(strHtml, "class=\"star-num\">", "<");
		}
		// if (!isNum.matches()) {
		// score=doc.select("span.ratingstar").text().replace("����:", "");
		// }
		System.out.println(score);

		String bieming = "";// ����

		bieming = HtmlAnalyze.getTagText(strHtml, "<label>����:</label>", "</span>");
		bieming = bieming.replace("	", "");
		if (bieming.equals("")) {
			bieming = HtmlAnalyze.getTagText(strHtml, "������", "</li>");
		}
		System.out.println(bieming);
		playtv.setAlias_en(bieming);
		String shichang = ""; // ʱ��
		shichang = HtmlAnalyze.getTagText(strHtml, "ʱ��:", "</");
		playtv.setTime_length(shichang);

		String times = ""; // ��ӳ:
		times = HtmlAnalyze.getTagText(strHtml, "��ӳ��", "</span>");
		// System.out.println(times=times.replaceAll("-", ""));
		playtv.setShow_date(times);

		String niandai = ""; // ���
		niandai = HtmlAnalyze.getTagText(strHtml, "�����", "</span>");
		playtv.setShoot_time(niandai);

		String diqu = ""; // ����
		diqu = HtmlAnalyze.getTagText(strHtml, "������", "</li>");
		playtv.setProduction_area(diqu);

		String classstr = ""; // ����:
		classstr = HtmlAnalyze.getTagText(strHtml, "���ͣ�", "</li>");
		System.out.println(classstr = classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);

		String peopleste = ""; // ren:
		peopleste = HtmlAnalyze.getTagText(strHtml, "���ݣ�", "</li>");
		System.out.println(peopleste = peopleste.replaceAll(" 					", ""));

		String daoyan = "";// ����
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "����:", "</li>", true, 0);
		String[] daoyanlist = daoyanAll.split(" /");
		if (daoyanlist.length!=0) {
		
		int i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			daoyan = daoyan + textss + "|" + urlss;
			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
				daoyan = daoyan + ",";
			}

			i += 1;
		}
		}
		if (daoyan.equals("|")) {
			daoyan="";
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);

		String yanyuan = "";// ��Ա
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "���ݣ�", "</li>", true, 0);
		String[] yanyuanlist = yanyuanAll.split("/</i>");
		i = 0;
		for (String stringtext : yanyuanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			
			if (!urlss.contains("http://")) {
				urlss=urlss.replace("//", "http://");
			}
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			yanyuan = yanyuan + textss + "|" + urlss;
			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
				yanyuan = yanyuan + ",";
			}

			i += 1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "��飺", "</span>");
		System.out.println(detail);
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		playtv.setBasic_info(detail);

		// playtv.setClassnum(1);
		playtv.setSOURCE(1);
		// basic_info

		// OracleOpreater.intoTEM_NETWORK_TVPLAY_AMOUNT(name, 1,
		// Integer.parseInt(Amount), times, urlBranch, classstr, peopleste,
		// Integer.parseInt(comment));
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
	}

	public static void openstatic() {
		String url = "";
		String[] diqu = { "��½", "���", "̨��", "����", "����", "����", "Ӣ��", "�¹�", "�����", "���ô�", "ӡ��", "����˹", "̩��", "����" };
		String[] leixing = { "����", "����", "����", "�ƻ�", "ս��", "�ֲ�", "���", "��¼Ƭ", "����", "Ϸ��", "����", "���", "ð��", "����", "��ʷ",
				"����", "����", "����", "��ͯ", "ϲ��", "����", "����", "�˶�", "��Ƭ", "�ſ��Ʒ" };
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				for (int i = 1; i < 30; i++) {
					
					//http://list.youku.com/category/show/c_97_g_%E5%86%9B%E4%BA%8B_a_%E5%A4%A7%E9%99%86_s_1_d_1_p_2.html?spm=a2h1n.8251845.0.0
					
//						url = "http://www.youku.com/v_olist/c_97_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
//								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_1_p_" + i + ".html";
					   //http://list.youku.com/category/show/c_97_g_%E6%AD%A6%E4%BE%A0_a_%E9%A6%99%E6%B8%AF_s_1_d_1.html?spm=a2h1n.8251845.filterPanel.5!3~1~3!3~A
					url="http://list.youku.com/category/show/c_97_g_"+leixingtxt+"_a_"+diqutxt+"_s_1_d_1_p_"+i+".html?spm=a2h1n.8251845.0.0";
					
					System.out.println(url);
					String urlnext = DownYouKuplatform.youkuMaim(url);
					if (urlnext.equals("") || urlnext == "" || urlnext == null) {
						break;
					}
				}
			}

		}
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		openstatic();
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
	}
	
	/**
	 * 
	 * @param datestr
	 *            �����ַ���
	 * @param day
	 *            ���������Ϊ������ʾ֮��Ϊ������ʾ֮ǰ
	 * @return ָ�������ַ���n��֮ǰ����֮�������
	 */
	public static String getBeforeAfterDate(String datestr, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		java.sql.Date olddate = null;
		try {
			df.setLenient(false);
			olddate = new java.sql.Date(df.parse(datestr).getTime());
		} catch (ParseException e) {
			throw new RuntimeException("����ת������");
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewDay = Day + day;

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);
		// System.out.println(df.format(cal.getTimeInMillis()));

		return df.format(cal.getTimeInMillis());
	}
	
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuTVplay(date_date);
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				
//				DownloadYouku.youkuBranch(listTemp.get(1));
				DownYouKuplatform.youkuBranch(listTemp.get(0),listTemp.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
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
		// openstatic();
//		TimingTime(4, 00, 00);
//		runstatic();
		
		openordor();
	}

}
