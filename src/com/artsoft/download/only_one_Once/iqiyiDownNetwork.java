package com.artsoft.download.only_one_Once;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class iqiyiDownNetwork {
	
	
	
	private static void downIqiyi_all(String DATA_URL) {
		// TODO Auto-generated method stub
		String strHtmlDATA_URL = DownloadUtil.getHtmlText(DATA_URL, 1000 * 30, "UTF-8", null, null);

//		Document docDATA_URL = Jsoup.parse(strHtmlDATA_URL);
		
		Document doc = Jsoup.parse(strHtmlDATA_URL);
		Elements links = doc.select("div.wrapper-piclist ul li");
		for (Element element : links) {
//			System.out.println(element.toString());
			String name=element.select("a").first().attr("title");
			String url=element.select("a").first().attr("href");
			System.out.println(name);
			System.out.println(url);
			youkuIqiyishoushuo("",name,url);
		}
		
		
		
	}
	
	private static void youkuIqiyishoushuo(String id, String name, String DATA_URL) {
//		DEL_DIM_NETWORKPLAY NETWORKPLAY= new DEL_DIM_NETWORKPLAY();
//		NETWORKPLAY.setNetworkplayId(Integer.valueOf(id));
//		NETWORKPLAY.setNetworkplayName(name);
		
		TvPlay playtv = new TvPlay();
		playtv.setTvplay_id(id);
		playtv.setTvplay_url(DATA_URL);
		System.out.println(name);
		playtv.setTvplay_name(name);
//		System.out.println(DATA_URL = link.select("h3.result_title a").first().attr("href"));
		// System.out.println(DATA_URL=link.select("h3.result_title
		// a").first().attr("href"));
//		NETWORKPLAY.setDataUrl(DATA_URL);
		String strHtmlDATA_URL = DownloadUtil.getHtmlText(DATA_URL, 1000 * 30, "UTF-8", null, null);

		Document docDATA_URL = Jsoup.parse(strHtmlDATA_URL);
//		System.out.println(docDATA_URL.select("div.result_detail").toString());
		String SHOW_NAME = HtmlAnalyze.getTagText(docDATA_URL.select("div.result_detail").toString(), "title=\"", "\"");
//		NETWORKPLAY.setShowName(SHOW_NAME);
		
//		playtv.setTvplay_name(SHOW_NAME);
		
		String ACTORS = HtmlAnalyze.getTagText(strHtmlDATA_URL, "主演：", "；");
//		NETWORKPLAY.setActors(ACTORS);
		playtv.setMajor_actors(ACTORS);
		String DIRECTOR = HtmlAnalyze.getTagText(strHtmlDATA_URL, "导演：", "；");
//		NETWORKPLAY.setDirector(DIRECTOR);
		playtv.setDirector(DIRECTOR);
		// String SCREENWRITER=docDATA_URL.select("div.left_col
		// em.textOverflow a").attr("title");
		String LABEL_NAME = docDATA_URL.select("div.look_point a").text();
		if (!LABEL_NAME.equals("")) {
			LABEL_NAME=LABEL_NAME.replace(" ", ",");
//			NETWORKPLAY.setLabelName(LABEL_NAME);
			playtv.setLABEL_TYPE(LABEL_NAME);
		}
		String FIRST_DATE = HtmlAnalyze.getTagText(strHtmlDATA_URL, "首播时间：", "</em>");
//		NETWORKPLAY.setFirstDate(FIRST_DATE);
		playtv.setPremiere_time(FIRST_DATE);
		String SET_NUM = HtmlAnalyze.getTagText(strHtmlDATA_URL, "总集数：", "集</em>");
		if (!SET_NUM.equals("")) {
			
//			NETWORKPLAY.setSetNum(Integer.valueOf(SET_NUM));
			playtv.setPages(SET_NUM);
		}
//		String SCORE = HtmlAnalyze.getTagText(strHtmlDATA_URL, "score_font\">", "分");
//		if (!SCORE.equals("")) {
//			SCORE=SCORE.replace(" ", "").replace("\r\n", "");
//			System.out.println(SCORE);
//			try {
//				
//				NETWORKPLAY.setScore(Double.parseDouble(SCORE));
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
		
//		Oracle.InsertDEL_DIM_NETWORKPLAY(NETWORKPLAY);
		
		playtv.setSOURCE(2);
		
		
		playtv.setDATA_TYPE(0);
		
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM_LABEL_TYPE(playtv);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		youkuIqiyishoushuo("","好孕连连","http://www.iqiyi.com/lib/m_205880414.html?src=search");
//		downIqiyi_all("http://www.iqiyi.com/lib/dianshiju/%E5%96%9C%E5%89%A7,,_11_1.html");
		
		String url = "";
		String[] diqu = { "喜剧", "偶像", "家庭", "古装", "警匪", "言情", "军事", "武侠", "悬疑", "历史", "农村", "都市", "神话", "科幻", "少儿", "搞笑", "谍战", "战争", "年代", "犯罪", "恐怖", "惊悚", "爱情", "剧情", "奇幻" };
//		String[] leixing = { "古装", "武侠", "警匪", "军事", "神话", "科幻", "悬疑", "历史", "儿童", "农村", "都市", "家庭", "搞笑", "偶像", "言情", "时装", "优酷出品" };
		for (String diqutxt : diqu) {
//			for (String leixingtxt : leixing) {
//				System.out.println(diqutxt + leixingtxt);
				// http://www.youku.com/v_olist/c_96_g_%E6%81%90%E6%80%96_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_4.html
				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_3.html
				// http://www.youku.com/v_olist/c_96_g_%E6%AD%A6%E4%BE%A0_a_%E5%A4%A7%E9%99%86_sg__mt__lg__q__s_1_r_0_u_0_pt_0_av_0_ag_0_sg__pr__h__d_1_p_1.html
				// try {
				for (int i = 1; i < 100; i++) {
					try {
						
						//http://www.iqiyi.com/lib/dianshiju/%E5%96%9C%E5%89%A7%2C%2C_11_3.html
						url = "http://www.iqiyi.com/lib/dianshiju/" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "%2C%2C_11_" + i + ".html";
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(url);
					downIqiyi_all(url);
					
					
				}

				// } catch (UnsupportedEncodingException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
//			}

		}
		
	}

	

}
