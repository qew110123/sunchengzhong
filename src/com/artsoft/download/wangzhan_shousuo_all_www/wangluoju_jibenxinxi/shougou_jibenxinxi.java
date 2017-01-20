package com.artsoft.download.wangzhan_shousuo_all_www.wangluoju_jibenxinxi;

import com.artsoft.bean.TEM_DIM_FILM;
import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class shougou_jibenxinxi {
	/**
	 *搜狗网剧和电影基本信息
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//电视剧
		tvplay();
		
		//电影
//		moves();
		

	}
	
	
	
	
	private static void moves() {
		// TODO Auto-generated method stub
		
//		String id ="";
//		String name = "刘涛";
//		String zhuopin = "欢乐颂";
		//电影
		int moves_id;
		String moves_name="从前有个套";
		String moves_url="http://tv.sogou.com/movie/wxt5hmbawtj4pmgt2c4pntgx.html?p=";
		int TYPE=2 ; //1百度 2 搜狗 3 360 4 豆瓣
		shoushuomoves(moves_name ,moves_url,TYPE);
		
	}
	
	
	
	private static void shoushuomoves(String moves_name, String moves_url, int tYPE) {
		// TODO Auto-generated method stub
		
		
		


		try {

//			String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//			Proxy proxy = DealProxy.getInstance().getPoxxy();
//			int i = 0;
//			while ((strHtml == null || strHtml.equals("")) && i < 15) {
//				System.out.println("访问" + i + "次");
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
//			int i = 0;
//			String[] daoyanlist = daoyanAll.split(" /");
			
			String daoyan = "";// 导演
			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "导演:", "</dd>", true, 0);
			String[] daoyanlist = daoyanAll.split("&nbsp;&nbsp;");
			int i = 0;
			for (String stringtext : daoyanlist) {
				if (stringtext.equals("")) {
					continue;
				}
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "</a>");
				if (!urlss.equals("")) {
					urlss = "http://tv.sogou.com" + urlss;
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

//			String bianju = "";// 编剧
//			String bianjuAll = HtmlAnalyze.getTagText(strHtml, "编剧</span>: ", "</span>", true, 0);
//			String[] bianjulist = bianjuAll.split(" /");
//			i = 0;
//			for (String stringtext : bianjulist) {
//				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
//				if (!urlss.equals("")) {
//					urlss = "http://movie.douban.com" + urlss;
//				}
//				bianju = bianju + textss + "|" + urlss;
//				if (bianjulist.length != 1 && i + 1 < bianjulist.length) {
//					bianju = bianju + ",";
//				}
//
//				i += 1;
//			}
//			System.out.println(bianju);
////			playtv.setScreenwriter(bianju);
////			tvplay.setScreenwriter(bianju);
//			movesfilm.setScreenwriter(bianju);
			

			String yanyuan = "";// 演员
			String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演:", "</dd>", true, 0);
			String[] yanyuanlist = yanyuanAll.split("&nbsp;&nbsp;");
			i = 0;
			for (String stringtext : yanyuanlist) {
				if (stringtext.equals("")) {
					continue;
				}
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "</a>");
				if (!urlss.equals("")) {
					urlss = "http://tv.sogou.com" + urlss;
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
			

			String classstr = ""; // 类型:
			classstr = HtmlAnalyze.getTagText(strHtml, "类型:", "</dd>");
			System.out.println(classstr = classstr.replaceAll("&nbsp;&nbsp;", ""));
//			playtv.setSubject(classstr);
//			tvplay.setSubject(classstr);
			movesfilm.setSubjectNameOne(classstr);
			String diqu = ""; // 地区
			diqu = HtmlAnalyze.getTagText(strHtml, "地区:", "</dd>").replace("&nbsp;&nbsp;", "");
//			playtv.setProduction_area(diqu);
//			tvplay.setProduction_area(diqu);
			movesfilm.setProduceArea(diqu);
			
//			String yuyan = "";// 语言
//
//			yuyan = HtmlAnalyze.getTagText(strHtml, "语言:", "<br/>");
////			playtv.setLgName(yuyan);
////			tvplay.setLgName(yuyan);
//			movesfilm.setLanguages(yuyan);

			String times = ""; // 上映:
			times = HtmlAnalyze.getTagText(strHtml, "上映:", "</dd>").replaceAll("&nbsp;&nbsp;", "");
			// System.out.println(times=times.replaceAll("-", ""));
//			playtv.setShow_date(times);
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "year\">(", ")").replaceAll("&nbsp;&nbsp;", "");
			}
			
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "首播:", "<br/>").replaceAll("&nbsp;&nbsp;", "");
			}
//			work.setDateTime(times);
//			tvplay.setPremiere_time(times);
			movesfilm.setShowDate(times);
			
//			String shichang = ""; // 时长
//			shichang = HtmlAnalyze.getTagText(strHtml, "片长:", "<br/>");
////			playtv.setTime_length(shichang);
////			work.setDateTime(shichang);
//			if (shichang.equals("")||shichang==null) {
//				shichang = HtmlAnalyze.getTagText(strHtml, "单集片长:", "<br/>");
//			}
////			tvplay.setFilm_time(shichang);
//			movesfilm.setTimeLong(shichang);
			

//			String bieming = "";// 别名
//
//			bieming = HtmlAnalyze.getTagText(strHtml, "又名:", "<br/>");
//			System.out.println(bieming = bieming.replace("	", ""));
////			playtv.setAlias_en(bieming);
////			tvplay.setAlias_en(bieming);
//			movesfilm.setAliasName(bieming);
//			
//			
////			String jishu = "";// 集数
////
////			jishu = HtmlAnalyze.getTagText(strHtml, "集数:", "<br/>");
////			System.out.println(jishu = jishu.replace("	", ""));
//////			playtv.setAlias_en(bieming);
////			tvplay.setPages(jishu);
//
//			String IMDb = ""; // IMDb
//			IMDb = HtmlAnalyze.getTagText(strHtml, "IMDb链接:", "<br>");
//			System.out.println(IMDb);
////			playtv.setIMDb(IMDb);
////			tvplay.setIMDb(IMDb);
//			movesfilm.setImdbCode(IMDb);

			String detail = "";
			detail = HtmlAnalyze.getTagText(strHtml, "剧情:", "</dd>");
			if (detail == null || detail.equals("") || detail.equals("null")) {
				detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
			}
			System.out.println(detail);
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
			
			OracleHaoSou.InsertTEM_DIM_FILM_SOURCE(movesfilm);// 添加操作
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
		
		
	}
	
	
	
	private static void tvplay() {
		// TODO Auto-generated method stub
		
//		String id ="";
//		String name = "刘涛";
//		String zhuopin = "欢乐颂";
		//电视剧名称
		int tvplay_id;
		String tvplay_name="一见钟情 中国大陆版";
		String tvplay_url="http://tv.sogou.com/movie/wxt5hmba2k53z66w2pd6sigw2c47vnhtyk63bzq.html?p=";
		int TYPE=4 ; //1百度 2 搜狗 3 360 4 豆瓣
		shoushuotvplay(tvplay_name ,tvplay_url,TYPE);
		
	}



	private static void shoushuotvplay(String tvplay_name, String tvplay_url, int tYPE) {

		try {

//			String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//			Proxy proxy = DealProxy.getInstance().getPoxxy();
//			int i = 0;
//			while ((strHtml == null || strHtml.equals("")) && i < 15) {
//				System.out.println("访问" + i + "次");
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
			
//			TvPlay tvplay = new TvPlay();
			
			
//			TEM_DIM_FILM movesfilm = new TEM_DIM_FILM();
			
			
			TvPlay tvplay = new TvPlay();
			
			
			// TODO Auto-generated method stub
//			tvplay.setTvplay_id(Integer.parseInt(strId));
//			tvplay.setTvplay_id(strId);
			
//			movesfilm.setFilmUrl(moves_url);
			
			tvplay.setTvplay_url(tvplay_url);
			
			tvplay.setTvplay_name(tvplay_name);
			
//			movesfilm.setFilmName(moves_name);
			
//			tvplay.setSource_class(tYPE);
//			movesfilm.setSOURCE(tYPE);
			tvplay.setSource_class(tYPE);
			
//			int i = 0;
//			String[] daoyanlist = daoyanAll.split(" /");
			
			String daoyan = "";// 导演
			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "导演:", "</dd>", true, 0);
			String[] daoyanlist = daoyanAll.split("&nbsp;&nbsp;");
			int i = 0;
			for (String stringtext : daoyanlist) {
				if (stringtext.equals("")) {
					continue;
				}
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "</a>");
				if (!urlss.equals("")) {
					urlss = "http://tv.sogou.com" + urlss;
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
//			movesfilm.setDirector(daoyan);
			tvplay.setDirector(daoyan);
//			work.setDateDirector(daoyan);

//			String bianju = "";// 编剧
//			String bianjuAll = HtmlAnalyze.getTagText(strHtml, "编剧</span>: ", "</span>", true, 0);
//			String[] bianjulist = bianjuAll.split(" /");
//			i = 0;
//			for (String stringtext : bianjulist) {
//				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "<");
//				if (!urlss.equals("")) {
//					urlss = "http://movie.douban.com" + urlss;
//				}
//				bianju = bianju + textss + "|" + urlss;
//				if (bianjulist.length != 1 && i + 1 < bianjulist.length) {
//					bianju = bianju + ",";
//				}
//
//				i += 1;
//			}
//			System.out.println(bianju);
////			playtv.setScreenwriter(bianju);
////			tvplay.setScreenwriter(bianju);
//			movesfilm.setScreenwriter(bianju);
			

			String yanyuan = "";// 演员
			String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演:", "</dd>", true, 0);
			String[] yanyuanlist = yanyuanAll.split("&nbsp;&nbsp;");
			i = 0;
			for (String stringtext : yanyuanlist) {
				if (stringtext.equals("")) {
					continue;
				}
				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "\">", "</a>");
				if (!urlss.equals("")) {
					urlss = "http://tv.sogou.com" + urlss;
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
//			movesfilm.setActors(yanyuan);
			tvplay.setMajor_actors(yanyuan);
			
			
			String title ="";
			title = HtmlAnalyze.getTagText(strHtml, "v:itemreviewed\">", "</span>");
//			work.setDateName(title);
			

			String classstr = ""; // 类型:
			classstr = HtmlAnalyze.getTagText(strHtml, "类型:", "</dd>");
			System.out.println(classstr = classstr.replaceAll("&nbsp;&nbsp;", ""));
//			playtv.setSubject(classstr);
			tvplay.setSubject(classstr);
//			movesfilm.setSubjectNameOne(classstr);
			String diqu = ""; // 地区
			diqu = HtmlAnalyze.getTagText(strHtml, "地区:", "</dd>").replace("&nbsp;&nbsp;", "");
//			playtv.setProduction_area(diqu);
			tvplay.setProduction_area(diqu);
//			movesfilm.setProduceArea(diqu);
			
//			String yuyan = "";// 语言
//
//			yuyan = HtmlAnalyze.getTagText(strHtml, "语言:", "<br/>");
////			playtv.setLgName(yuyan);
////			tvplay.setLgName(yuyan);
//			movesfilm.setLanguages(yuyan);

			String times = ""; // 上映:
			times = HtmlAnalyze.getTagText(strHtml, "上映:", "</dd>").replaceAll("&nbsp;&nbsp;", "");
			// System.out.println(times=times.replaceAll("-", ""));
//			playtv.setShow_date(times);
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "year\">(", ")").replaceAll("&nbsp;&nbsp;", "");
			}
			
			if (times.equals("")||times==null) {
				times = HtmlAnalyze.getTagText(strHtml, "首播:", "<br/>").replaceAll("&nbsp;&nbsp;", "");
			}
//			work.setDateTime(times);
			tvplay.setPremiere_time(times);
//			movesfilm.setShowDate(times);
			
//			String shichang = ""; // 时长
//			shichang = HtmlAnalyze.getTagText(strHtml, "片长:", "<br/>");
////			playtv.setTime_length(shichang);
////			work.setDateTime(shichang);
//			if (shichang.equals("")||shichang==null) {
//				shichang = HtmlAnalyze.getTagText(strHtml, "单集片长:", "<br/>");
//			}
////			tvplay.setFilm_time(shichang);
//			movesfilm.setTimeLong(shichang);
			

//			String bieming = "";// 别名
//
//			bieming = HtmlAnalyze.getTagText(strHtml, "又名:", "<br/>");
//			System.out.println(bieming = bieming.replace("	", ""));
////			playtv.setAlias_en(bieming);
////			tvplay.setAlias_en(bieming);
//			movesfilm.setAliasName(bieming);
//			
//			
////			String jishu = "";// 集数
////
////			jishu = HtmlAnalyze.getTagText(strHtml, "集数:", "<br/>");
////			System.out.println(jishu = jishu.replace("	", ""));
//////			playtv.setAlias_en(bieming);
////			tvplay.setPages(jishu);
//
//			String IMDb = ""; // IMDb
//			IMDb = HtmlAnalyze.getTagText(strHtml, "IMDb链接:", "<br>");
//			System.out.println(IMDb);
////			playtv.setIMDb(IMDb);
////			tvplay.setIMDb(IMDb);
//			movesfilm.setImdbCode(IMDb);

			String detail = "";
			detail = HtmlAnalyze.getTagText(strHtml, "剧情:", "</dd>");
			if (detail == null || detail.equals("") || detail.equals("null")) {
				detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
			}
			System.out.println(detail);
//			playtv.setBasic_info(detail);
//			tvplay.setBasic_info(detail);
//			movesfilm.setDescription(detail);

//			playtv.setClassnum(9);
//			work.setSource(4);
			
//			tvplay.setType(1);
			
//			movesfilm.setSOURCE(tYPE);
			
			tvplay.setType(1);
			
			tvplay.setSOURCE(tYPE);
			

//			OracleHaoSou.intotem_person_works(personwork);
			
//			Oracle.InsertTEM_PERSON_URL_WORKS(work);
			
//			OracleHaoSou.InsertTVplay_source(tvplay);
			
//			OracleHaoSou.InsertTEM_DIM_FILM_SOURCE(movesfilm);// 添加操作
			
			OracleHaoSou.InsertTVplay_source(tvplay);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	/**
	 * 2017年1月5日14:58:15 统一打开方式
	 * 
	 * @param urlMain
	 * @return
	 */
	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gb2312", null, null);
		int i = 0;
		while (i < 15  && (strHtml==null || strHtml.equals(""))) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;
		}
		return strHtml;
	}

}
