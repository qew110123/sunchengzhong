package com.artsoft.download.wangzhan_shousuo_all_www.wangluoju_jibenxinxi;

import com.artsoft.bean.TEM_DIM_FILM;
import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class douban_jibenxinxi {
	/**
	 * ��������͵�Ӱ������Ϣ
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		//���Ӿ�
		tvplay();
		
		//��Ӱ
		moves();
	}
	
	

	private static void moves() {
		// TODO Auto-generated method stub
		
//		String id ="";
//		String name = "����";
//		String zhuopin = "������";
		//��Ӱ
		int moves_id;
		String moves_name="���ǽ���";
		String moves_url="https://movie.douban.com/subject/25754848/";
		int TYPE=4 ; //1�ٶ� 2 �ѹ� 3 360 4 ����
		shoushuomoves(moves_name ,moves_url,TYPE);
		
	}



	private static void shoushuomoves(String moves_name, String moves_url, int tYPE) {
		// TODO Auto-generated method stub
		
		
		


		try {

//			String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//			Proxy proxy = DealProxy.getInstance().getPoxxy();
//			int i = 0;
//			while ((strHtml == null || strHtml.equals("")) && i < 15) {
//				System.out.println("����" + i + "��");
//				proxy = DealProxy.getInstance().getPoxxy();
//				strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
//				i += 1;
//			}
//			if (strHtml == null || strHtml.equals("")) {
//				return;
//			}
//
//			// String name = HtmlAnalyze.getTagText(strHtml, "<meta
//			// name=\"keywords\" content=\"", "\" /> ");
//
//			System.out.println(name);
////			TvPlay playtv = new TvPlay();
			
			String strHtml=Htmlurl(moves_url);
			
			
//			playtv.setTvplay_url(urlBranch);

//			playtv.setTvplay_name(name);
			
//			TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//			personwork.setPersonId(Integer.parseInt(strId));
//			work.setPersonId(id);
////			personwork.setPersonUrl(url);
//			work.setPersonName(name);
//			
//			work.setPersonUrl(urlBranch);
//			
//			work.setDateUrl(urlBranch);
			
//			TvPlay tvplay = new TvPlay();
			
			TEM_DIM_FILM movesfilm = new TEM_DIM_FILM();
			
			// TODO Auto-generated method stub
//			tvplay.setTvplay_id(Integer.parseInt(strId));
//			tvplay.setTvplay_id(strId);
			
			movesfilm.setFilmUrl(moves_url);
			
			movesfilm.setFilmName(moves_name);
			
//			tvplay.setSource_class(tYPE);
			movesfilm.setSOURCE(tYPE);
			
			
			String daoyan = "";// ����
			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "����</span>: ", "</span>", true, 0);
			String[] daoyanlist = daoyanAll.split(" /");
			int i = 0;
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
//			tvplay.setDirector(daoyan);
			movesfilm.setDirector(daoyan);
//			work.setDateDirector(daoyan);

			String bianju = "";// ���
			String bianjuAll = HtmlAnalyze.getTagText(strHtml, "���</span>: ", "</span>", true, 0);
			String[] bianjulist = bianjuAll.split(" /");
			i = 0;
			for (String stringtext : bianjulist) {
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
				if (!urlss.equals("")) {
					urlss = "http://movie.douban.com" + urlss;
				}
				bianju = bianju + textss + "|" + urlss;
				if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
					bianju = bianju + ",";
				}

				i += 1;
			}
			System.out.println(bianju);
//			playtv.setScreenwriter(bianju);
//			tvplay.setScreenwriter(bianju);
			movesfilm.setScreenwriter(bianju);
			

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
//			work.setDateMajorActors(yanyuan);
//			tvplay.setMajor_actors(yanyuan);
			movesfilm.setActors(yanyuan);
			
			
			String title ="";
			title = HtmlAnalyze.getTagText(strHtml, "v:itemreviewed\">", "</span>");
//			work.setDateName(title);
			

			String classstr = ""; // ����:
			classstr = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
			System.out.println(classstr = classstr.replaceAll(" 					", ""));
//			playtv.setSubject(classstr);
//			tvplay.setSubject(classstr);
			movesfilm.setSubjectNameOne(classstr);
			String diqu = ""; // ����
			diqu = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			playtv.setProduction_area(diqu);
//			tvplay.setProduction_area(diqu);
			movesfilm.setProduceArea(diqu);
			
			String yuyan = "";// ����

			yuyan = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			playtv.setLgName(yuyan);
//			tvplay.setLgName(yuyan);
			movesfilm.setLanguages(yuyan);

			String times = ""; // ��ӳ:
			times = HtmlAnalyze.getTagText(strHtml, "��ӳ����:", "<br/>");
			// System.out.println(times=times.replaceAll("-", ""));
//			playtv.setShow_date(times);
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "year\">(", ")");
			}
			
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "�ײ�:", "<br/>");
			}
//			work.setDateTime(times);
//			tvplay.setPremiere_time(times);
			movesfilm.setShowDate(times);
			
			String shichang = ""; // ʱ��
			shichang = HtmlAnalyze.getTagText(strHtml, "Ƭ��:", "<br/>");
//			playtv.setTime_length(shichang);
//			work.setDateTime(shichang);
			if (shichang.equals("")||shichang==null) {
				shichang = HtmlAnalyze.getTagText(strHtml, "����Ƭ��:", "<br/>");
			}
//			tvplay.setFilm_time(shichang);
			movesfilm.setTimeLong(shichang);
			

			String bieming = "";// ����

			bieming = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
			System.out.println(bieming = bieming.replace("	", ""));
//			playtv.setAlias_en(bieming);
//			tvplay.setAlias_en(bieming);
			movesfilm.setAliasName(bieming);
			
			
//			String jishu = "";// ����
//
//			jishu = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			System.out.println(jishu = jishu.replace("	", ""));
////			playtv.setAlias_en(bieming);
//			tvplay.setPages(jishu);

			String IMDb = ""; // IMDb
			IMDb = HtmlAnalyze.getTagText(strHtml, "IMDb����:", "<br>");
			System.out.println(IMDb);
//			playtv.setIMDb(IMDb);
//			tvplay.setIMDb(IMDb);
			movesfilm.setImdbCode(IMDb);

			String detail = "";
			detail = HtmlAnalyze.getTagText(strHtml, "<span property=\"v:summary\" class=\"\">", "</span>");
			System.out.println(detail);
			if (detail == null || detail.equals("") || detail.equals("null")) {
				detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
			}
//			playtv.setBasic_info(detail);
//			tvplay.setBasic_info(detail);
			movesfilm.setDescription(detail);

//			playtv.setClassnum(9);
//			work.setSource(4);
			
//			tvplay.setType(1);
			
			movesfilm.setSOURCE(tYPE);
			

//			OracleHaoSou.intotem_person_works(personwork);
			
//			Oracle.InsertTEM_PERSON_URL_WORKS(work);
			
//			OracleHaoSou.InsertTVplay_source(tvplay);
			
			OracleHaoSou.InsertTEM_DIM_FILM_SOURCE(movesfilm);// ��Ӳ���
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
		
		
	}



	private static void tvplay() {
		// TODO Auto-generated method stub
		
		String id ="";
		String name = "����";
		String zhuopin = "������";
		//���Ӿ�����
		int tvplay_id;
		String tvplay_name="�����";
		String tvplay_url="https://movie.douban.com/subject/25754848/";
		int TYPE=4 ; //1�ٶ� 2 �ѹ� 3 360 4 ����
		shoushuo(tvplay_name ,tvplay_url,TYPE);
		
	}



	private static void shoushuo(String tvplay_name, String tvplay_url, int tYPE) {

		try {

//			String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//			Proxy proxy = DealProxy.getInstance().getPoxxy();
//			int i = 0;
//			while ((strHtml == null || strHtml.equals("")) && i < 15) {
//				System.out.println("����" + i + "��");
//				proxy = DealProxy.getInstance().getPoxxy();
//				strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
//				i += 1;
//			}
//			if (strHtml == null || strHtml.equals("")) {
//				return;
//			}
//
//			// String name = HtmlAnalyze.getTagText(strHtml, "<meta
//			// name=\"keywords\" content=\"", "\" /> ");
//
//			System.out.println(name);
////			TvPlay playtv = new TvPlay();
			
			String strHtml=Htmlurl(tvplay_url);
			
			
//			playtv.setTvplay_url(urlBranch);

//			playtv.setTvplay_name(name);
			
//			TEM_PERSON_URL_WORKS work = new TEM_PERSON_URL_WORKS();
//			personwork.setPersonId(Integer.parseInt(strId));
//			work.setPersonId(id);
////			personwork.setPersonUrl(url);
//			work.setPersonName(name);
//			
//			work.setPersonUrl(urlBranch);
//			
//			work.setDateUrl(urlBranch);
			
			TvPlay tvplay = new TvPlay();
			// TODO Auto-generated method stub
//			tvplay.setTvplay_id(Integer.parseInt(strId));
//			tvplay.setTvplay_id(strId);
			
			tvplay.setTvplay_url(tvplay_url);
			
			tvplay.setTvplay_name(tvplay_name);
			
			tvplay.setSource_class(4);
			
			
			String daoyan = "";// ����
			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "����</span>: ", "</span>", true, 0);
			String[] daoyanlist = daoyanAll.split(" /");
			int i = 0;
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
			tvplay.setDirector(daoyan);
//			work.setDateDirector(daoyan);

			String bianju = "";// ���
			String bianjuAll = HtmlAnalyze.getTagText(strHtml, "���</span>: ", "</span>", true, 0);
			String[] bianjulist = bianjuAll.split(" /");
			i = 0;
			for (String stringtext : bianjulist) {
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
				if (!urlss.equals("")) {
					urlss = "http://movie.douban.com" + urlss;
				}
				bianju = bianju + textss + "|" + urlss;
				if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
					bianju = bianju + ",";
				}

				i += 1;
			}
			System.out.println(bianju);
//			playtv.setScreenwriter(bianju);
			tvplay.setScreenwriter(bianju);
			

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
//			work.setDateMajorActors(yanyuan);
			tvplay.setMajor_actors(yanyuan);
			
			
			String title ="";
			title = HtmlAnalyze.getTagText(strHtml, "v:itemreviewed\">", "</span>");
//			work.setDateName(title);
			

			String classstr = ""; // ����:
			classstr = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
			System.out.println(classstr = classstr.replaceAll(" 					", ""));
//			playtv.setSubject(classstr);
			tvplay.setSubject(classstr);
			String diqu = ""; // ����
			diqu = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			playtv.setProduction_area(diqu);
			tvplay.setProduction_area(diqu);
			String yuyan = "";// ����

			yuyan = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
//			playtv.setLgName(yuyan);
			tvplay.setLgName(yuyan);

			String times = ""; // ��ӳ:
			times = HtmlAnalyze.getTagText(strHtml, "��ӳ����:", "<br/>");
			// System.out.println(times=times.replaceAll("-", ""));
//			playtv.setShow_date(times);
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "year\">(", ")");
			}
			
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "�ײ�:", "<br/>");
			}
//			work.setDateTime(times);
			tvplay.setPremiere_time(times);
			String shichang = ""; // ʱ��
			shichang = HtmlAnalyze.getTagText(strHtml, "Ƭ��:", "<br/>");
//			playtv.setTime_length(shichang);
//			work.setDateTime(shichang);
			if (shichang.equals("")||shichang==null) {
				shichang = HtmlAnalyze.getTagText(strHtml, "����Ƭ��:", "<br/>");
			}
			tvplay.setFilm_time(shichang);

			String bieming = "";// ����

			bieming = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
			System.out.println(bieming = bieming.replace("	", ""));
//			playtv.setAlias_en(bieming);
			tvplay.setAlias_en(bieming);
			
			
			String jishu = "";// ����

			jishu = HtmlAnalyze.getTagText(strHtml, "����:", "<br/>");
			System.out.println(jishu = jishu.replace("	", ""));
//			playtv.setAlias_en(bieming);
			tvplay.setPages(jishu);

			String IMDb = ""; // IMDb
			IMDb = HtmlAnalyze.getTagText(strHtml, "IMDb����:", "<br>");
			System.out.println(IMDb);
//			playtv.setIMDb(IMDb);
			tvplay.setIMDb(IMDb);

			String detail = "";
			detail = HtmlAnalyze.getTagText(strHtml, "<span property=\"v:summary\" class=\"\">", "</span>");
			System.out.println(detail);
			if (detail == null || detail.equals("") || detail.equals("null")) {
				detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
			}
//			playtv.setBasic_info(detail);
			tvplay.setBasic_info(detail);

//			playtv.setClassnum(9);
//			work.setSource(4);
			
			tvplay.setType(1);
			
			tvplay.setSOURCE(tYPE);

//			OracleHaoSou.intotem_person_works(personwork);
			
//			Oracle.InsertTEM_PERSON_URL_WORKS(work);
			
			OracleHaoSou.InsertTVplay_source(tvplay);
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
