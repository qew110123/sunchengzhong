package com.artsoft.download.only_one_Once;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.DEL_DIM_NETWORKPLAY;
import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class iqiyi_shoushuo_wangju {

	private static void runstatic() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		// String newtime = tt.getNowTime("yyyyMMdd");
		// String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray = OracleNetwork.selectIqiyiWangju();
		String id = "";
		String name = "";
		String urlMain = "";
		String utf8name = "";
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(id = listTemp.get(0));
			System.out.println(name = listTemp.get(1));
			try {
				utf8name = java.net.URLEncoder.encode(name, "utf-8");
				// http://so.iqiyi.com/so/q_%E6%9E%81%E5%93%81%E5%A5%B3%E5%A3%AB%E7%AC%AC1%E5%AD%A3_ctg_%E7%94%B5%E8%A7%86%E5%89%A7_t_0_page_1_p_1_qc_0_rd__site__m_1_bitrate_?refersource=lib&af=true
				urlMain = "http://so.iqiyi.com/so/q_" + utf8name
						+ "_ctg_%E7%94%B5%E8%A7%86%E5%89%A7_t_0_page_1_p_1_qc_0_rd__site__m_1_bitrate_?refersource=lib&af=true";
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			youkuIqiyishoushuo(id, name, urlMain);
		}

	}

	private static void youkuIqiyishoushuo(String id, String name, String urlMain) {
		// TODO Auto-generated method stub
		// http://so.iqiyi.com/so/q_%E6%9E%81%E5%93%81%E5%A5%B3%E5%A3%AB%E7%AC%AC1%E5%AD%A3_ctg_%E7%94%B5%E8%A7%86%E5%89%A7_t_0_page_1_p_1_qc_0_rd__site__m_1_bitrate_?refersource=lib&af=true
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		String DATA_URL = "";
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.mod_result_list li ");
		for (Element link : links) {
			if (link.toString().contains("主演:")) {
				DEL_DIM_NETWORKPLAY NETWORKPLAY= new DEL_DIM_NETWORKPLAY();
				NETWORKPLAY.setNetworkplayId(Integer.valueOf(id));
				NETWORKPLAY.setNetworkplayName(name);
				System.out.println(DATA_URL = link.select("h3.result_title a").first().attr("href"));
				// System.out.println(DATA_URL=link.select("h3.result_title
				// a").first().attr("href"));
				NETWORKPLAY.setDataUrl(DATA_URL);
				String strHtmlDATA_URL = DownloadUtil.getHtmlText(DATA_URL, 1000 * 30, "UTF-8", null, null);

				Document docDATA_URL = Jsoup.parse(strHtmlDATA_URL);
//				System.out.println(docDATA_URL.select("div.result_detail").toString());
				String SHOW_NAME = HtmlAnalyze.getTagText(docDATA_URL.select("div.result_detail").toString(), "title=\"", "\"");
				NETWORKPLAY.setShowName(SHOW_NAME);
				String ACTORS = HtmlAnalyze.getTagText(strHtmlDATA_URL, "主演：", "；");
				NETWORKPLAY.setActors(ACTORS);
				String DIRECTOR = HtmlAnalyze.getTagText(strHtmlDATA_URL, "导演：", "；");
				NETWORKPLAY.setDirector(DIRECTOR);
				// String SCREENWRITER=docDATA_URL.select("div.left_col
				// em.textOverflow a").attr("title");
				String LABEL_NAME = docDATA_URL.select("div.look_point a").text();
				if (!LABEL_NAME.equals("")) {
					LABEL_NAME=LABEL_NAME.replace(" ", ",");
					NETWORKPLAY.setLabelName(LABEL_NAME);
				}
				String FIRST_DATE = HtmlAnalyze.getTagText(strHtmlDATA_URL, "首播时间：", "</em>");
				NETWORKPLAY.setFirstDate(FIRST_DATE);
				String SET_NUM = HtmlAnalyze.getTagText(strHtmlDATA_URL, "总集数：", "集</em>");
				if (!SET_NUM.equals("")) {
					
					NETWORKPLAY.setSetNum(Integer.valueOf(SET_NUM));
				}
				String SCORE = HtmlAnalyze.getTagText(strHtmlDATA_URL, "score_font\">", "分");
				if (!SCORE.equals("")) {
					SCORE=SCORE.replace(" ", "").replace("\r\n", "");
					System.out.println(SCORE);
					try {
						
						NETWORKPLAY.setScore(Double.parseDouble(SCORE));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				Oracle.InsertDEL_DIM_NETWORKPLAY(NETWORKPLAY);
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();

	}

}
