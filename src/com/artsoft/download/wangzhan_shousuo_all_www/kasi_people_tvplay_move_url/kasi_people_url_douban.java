package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_tvplay_move_url;

import java.io.UnsupportedEncodingException;
import java.net.Proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_PERSON_URL_WORKS;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class kasi_people_url_douban {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id ="0";
		String name = "����";
		String zhuopin = "������,�����";
		shoushuo(id ,name, zhuopin);
	}
	
	
	public static void shoushuo(String id,String name, String zhuopin) {
		// TODO Auto-generated method stub
		// https://www.baidu.com/s?wd=%E5%88%98%E6%B6%9B&pn=10&oq=%E5%88%98%E6%B6%9B
		// http://www.baidu.com/s?wd=����&pn=10&oq=����

		// String utf8name="";
		String krywordutf8 = "";
		try {
			krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//http://movie.douban.com/subject_search?search_text=������
		//https://www.douban.com/search?cat=1002&q=%E5%88%98%E6%B6%9B
		String url = "https://www.douban.com/search?cat=1002&q=" + krywordutf8 + "" ;
		String strHtml = Htmlurl(url);

		Document doc = Jsoup.parse(strHtml);
		Elements linkli = doc.select("div.result div.title h3");
		for (Element element : linkli) {

			if (element.text().contains("��Ӱ")) {
				System.out.println(element);
				System.out.println(element.text());
				String urlxiangxi = "";
				System.out.println(urlxiangxi = element.select("a").attr("href"));

				xuanzhe(id,name, zhuopin, urlxiangxi,2);
			}
			
			if (element.text().contains("���Ӿ�")) {
				System.out.println(element);
				System.out.println(element.text());
				String urlxiangxi = "";
				System.out.println(urlxiangxi = element.select("a").attr("href"));

				xuanzhe(id,name, zhuopin, urlxiangxi,1);
			}

		}

	}
	
	
	/**
	 * 
	 * @param name
	 * @param zhuopin
	 * @param urltitle
	 */
	private static void xuanzhe(String id,String name, String zhuopin, String urltitle,int DATA_TYPE) {
		// TODO Auto-generated method stub

		String strHtml = Htmlurl(urltitle);

//		String[] zhuopinlist = zhuopin.split(",");
//		boolean bb = false;
//		int inti = 0;
//		for (int i = 0; i < zhuopinlist.length; i++) {
//			if (strHtml.contains(zhuopinlist[i])) {
//				System.out.println(urltitle);
//				inti += 1;
//			}
//		}
		
		if (strHtml.contains(name)) {
			System.out.println("�ɹ��ҵ�url");
			Branch(id,name,zhuopin,urltitle,strHtml,DATA_TYPE);
			
//			TEM_PERSON_URL personurl = new TEM_PERSON_URL();
//			personurl.setPersonId(id);
//			personurl.setPersonName(name);
//			personurl.setPersonUrl(urltitle);
//			personurl.setType(1);
////			personurl.setUpDate(upDate);
//			
//			 Oracle.InsertTEM_PERSON_URL(personurl);
		}

	}
	
	
	
	
	public static void Branch(String id,String name, String zhuopin, String urlBranch,String strHtml,int DATA_TYPE) {

		try {

//			String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
			Proxy proxy = DealProxy.getInstance().getPoxxy();
			int i = 0;
			while ((strHtml == null || strHtml.equals("")) && i < 15) {
				System.out.println("����" + i + "��");
				proxy = DealProxy.getInstance().getPoxxy();
				strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
				i += 1;
			}
			if (strHtml == null || strHtml.equals("")) {
				return;
			}

			// String name = HtmlAnalyze.getTagText(strHtml, "<meta
			// name=\"keywords\" content=\"", "\" /> ");

			System.out.println(name);
//			TvPlay playtv = new TvPlay();
			
			
			
			
//			playtv.setTvplay_url(urlBranch);

//			playtv.setTvplay_name(name);
			
			TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//			personwork.setPersonId(Integer.parseInt(strId));
			work.setPersonId(id);
//			personwork.setPersonUrl(url);
			work.setPersonName(name);
			
			work.setPersonUrl(urlBranch);
			
			work.setDateUrl(urlBranch);
			
			String daoyan = "";// ����
			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "����</span>: ", "</span>", true, 0);
			String[] daoyanlist = daoyanAll.split(" /");
			i = 0;
			for (String stringtext : daoyanlist) {
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
				if (!urlss.equals("")) {
					urlss = "http://movie.douban.com" + urlss;
				}
				daoyan = daoyan + textss + "|" + urlss;
				if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
					daoyan = daoyan + ",";
				}

				i += 1;
			}
			System.out.println(daoyan);
//			playtv.setDirector(daoyan);
			
			work.setDateDirector(daoyan);

//			String bianju = "";// ���
//			String bianjuAll = HtmlAnalyze.getTagText(strHtml, "���</span>: ", "</span>", true, 0);
//			String[] bianjulist = bianjuAll.split(" /");
//			i = 0;
//			for (String stringtext : bianjulist) {
//				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
//				if (!urlss.equals("")) {
//					urlss = "http://movie.douban.com" + urlss;
//				}
//				bianju = bianju + textss + "|" + urlss;
//				if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
//					bianju = bianju + ",";
//				}
//
//				i += 1;
//			}
//			System.out.println(bianju);
//			playtv.setScreenwriter(bianju);

			String yanyuan = "";// ��Ա
			String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "����</span>: ", "</span>", true, 0);
			String[] yanyuanlist = yanyuanAll.split(" /");
			i = 0;
			for (String stringtext : yanyuanlist) {
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
				if (!urlss.equals("")) {
					urlss = "http://movie.douban.com" + urlss;
				}
				yanyuan = yanyuan + textss + "|" + urlss;
				if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
					yanyuan = yanyuan + ",";
				}

				i += 1;
			}
			System.out.println(yanyuan);
//			playtv.setMajor_actors(yanyuan);
			work.setDateMajorActors(yanyuan);
			
			
			String title ="";
			title = HtmlAnalyze.getTagText(strHtml, "v:itemreviewed\">", "</span>");
			work.setDateName(title);
			

//			String classstr = ""; // ����:
//			classstr = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			System.out.println(classstr = classstr.replaceAll(" 					", ""));
//			playtv.setSubject(classstr);
//
//			String diqu = ""; // ����
//			diqu = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			playtv.setProduction_area(diqu);
//
//			String yuyan = "";// ����
//
//			yuyan = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			playtv.setLgName(yuyan);

			String times = ""; // ��ӳ:
			times = HtmlAnalyze.getTagText(strHtml, "��ӳ����:", "<br/>");
			// System.out.println(times=times.replaceAll("-", ""));
//			playtv.setShow_date(times);
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "year\">(", ")");
			}
			work.setDateTime(times);
//			String shichang = ""; // ʱ��
//			shichang = HtmlAnalyze.getTagText(strHtml, "Ƭ��:", "<br/>");
////			playtv.setTime_length(shichang);
//			work.setDateTime(shichang);

//			String bieming = "";// ����
//
//			bieming = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			System.out.println(bieming = bieming.replace("	", ""));
//			playtv.setAlias_en(bieming);
//
//			String IMDb = ""; // IMDb
//			IMDb = HtmlAnalyze.getTagText(strHtml, "IMDb����:", "<br>");
//			System.out.println(IMDb);
//			playtv.setIMDb(IMDb);
//
//			String detail = "";
//			detail = HtmlAnalyze.getTagText(strHtml, "<span property=\"v:summary\" class=\"\">", "</span>");
//			System.out.println(detail);
//			if (detail == null || detail.equals("") || detail.equals("null")) {
//				detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
//			}
//			playtv.setBasic_info(detail);
//
//			playtv.setClassnum(9);
			work.setSource(4);
			work.setDataType(DATA_TYPE);
//			OracleHaoSou.intotem_person_works(personwork);
			
			Oracle.InsertTEM_PERSON_URL_WORKS(work);
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	/**
	 * 2017��1��5��14:58:15 ͳһ�򿪷�ʽ
	 * 
	 * @param urlMain
	 * @return
	 */
	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		int i = 0;
		while (i < 15  && (strHtml==null || strHtml.equals(""))) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;
		}
		return strHtml;
	}

}
