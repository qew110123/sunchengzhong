package com.artsoft.download.company;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.Company;
import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class BaiduCompany {
	
	private static Company mainUrl(String strId, String url, String strname) {
		Company company = new Company();
		// TODO Auto-generated method stub
		company.setPRODUCE_ID(Integer.parseInt(strId));
		company.setCOMPANY_NAME(strname);
		company.setUrl(url);
		
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.basic-info");
		for (Element link : links) {
			String[] sourceStrArray = link.toString().split("d>");

			for (int i = 0; i < sourceStrArray.length; i++) {
				String baseInfoName = "";
				String baseInfoValue = "";
				baseInfoName = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item name\">", "</");
				baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item value\">", "</d");

				if (baseInfoName != null && baseInfoValue != null) {
					baseInfoName = baseInfoName.replace("&nbsp;", "");
					System.out.println(baseInfoName);
				}
				company = companyTvPlay(baseInfoName, baseInfoValue, company);
			}
		}
		String stills_url="";
		Elements linkstills_url = doc.select("div.summary-pic img");
//			stills_url=HtmlAnalyze.getTagText(strHtml.toString(), " target=\"_blank\">\n<img src=\"", "\"");
		stills_url=HtmlAnalyze.getTagText(strHtml.toString(), "data-src=\"", "\"");
		if (stills_url==null||stills_url.equals(null)) {
			System.out.println(stills_url=HtmlAnalyze.getTagText(strHtml.toString(), "src=\"", "\""));
			if (stills_url==null||stills_url.equals(null)) {
				stills_url="";
			}
		}
		company.setLOGO_URL(stills_url);
//			company.setStills_url(stills_url);
		
		
		String information = "";
		information=HtmlAnalyze.getTagText(strHtml.toString(), "<div class=\"para\" label-module=\"para\">", "</div>");
		company.setDESCRIPTION(information);
		
		boolean IS_IPO=false;
		IS_IPO=strHtml.contains("上市");
		if (IS_IPO) {
			company.setIS_IPO(2);
		}else{
			company.setIS_IPO(3);
		}
		
		return company;
		
	}
	
	
	
	
	/**
	 * 进行判断是否有我需要的值
	 * 
	 * @param baseInfoName
	 * @param baseInfoValue
	 * @param tvplay
	 * @return
	 */
	public static Company companyTvPlay(String baseInfoName, String baseInfoValue, Company company) {
		if (baseInfoValue!=null&&!baseInfoValue.equals("")) {
			baseInfoValue=baseInfoValue.replace("	", "");
		}
		if ("中文名".equals(baseInfoName)) {
			company.setALIAS_NAME(baseInfoValue);
		}
		if ("公司名称".equals(baseInfoName)) {
			company.setALIAS_NAME(baseInfoValue);
		}
		if ("外文名称".equals(baseInfoName)) {
			company.setENGLISH_NAME(baseInfoValue);
		}
		if ("成立时间".equals(baseInfoName)) {
			company.setFOUNDED_DATE(baseInfoValue);
		}
		if ("总部地点".equals(baseInfoName)) {
			company.setHEADQUARTERS(baseInfoValue);
		}
		if ("经营范围".equals(baseInfoName)) {
			company.setBUSINESS_SCOPE(baseInfoValue);
		}
		if ("公司性质".equals(baseInfoName)) {
			company.setCOMPANY_NATURE(baseInfoValue);
		}
		if ("简介".equals(baseInfoName)) {
			company.setDESCRIPTION(baseInfoValue);
		}
		if ("作品".equals(baseInfoName)) {
			company.setWORKS_DESCRIPTION(baseInfoValue);
		}
		if ("代表作品".equals(baseInfoName)) {
			company.setWORKS_DESCRIPTION(baseInfoValue);
		}
		if ("作品简介".equals(baseInfoName)) {
			company.setWORKS_DESCRIPTION(baseInfoValue);
		}
		if ("logo的Url地址".equals(baseInfoName)) {
			company.setLOGO_URL(baseInfoValue);
		}
		
		if ("经营范围".equals(baseInfoName)) {
			company.setSERVICE_AREA(baseInfoValue);
		}
		if ("服务领域".equals(baseInfoName)) {
			company.setSERVICE_AREA(baseInfoValue);
		}
		if ("年营业额".equals(baseInfoName)) {
			company.setYEAR_MONEY(baseInfoValue);
		}
		if ("员工数".equals(baseInfoName)) {
			company.setSTAFF_NUM(baseInfoValue);
		}
		if ("创始人".equals(baseInfoName)) {
			company.setCEO_NAME(baseInfoValue);
		}
		if ("董事长".equals(baseInfoName)) {
			company.setCEO_NAME(baseInfoValue);
		}
		if ("公司总裁".equals(baseInfoName)) {
			company.setCEO_NAME(baseInfoValue);
		}
		
		return company;
	}
	
	/**
	 * 进行数据的搜索
	 * @param urlmain
	 * @param id
	 * @param strname
	 */
	public static void mainUrlall(String urlmain, String id, String strname) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("a.result-title");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		System.out.println(links.size());
		// for (Element link : links) {
		// String idnum = "";
		// String strVolumes = "";
		// System.out.println(strVolumes = link.text());
		// System.out.println(idnum = link.attr("href"));
		// }
		if (links.size()  >0) {
			String strUrl = "";
			String strUrlname = "";
			System.out.println(strUrlname=links.first().text());
			System.out.println(strUrl = links.attr("href"));
//			mainmore(id, strUrl,strUrlname);
			if (strUrl != null && !"".equals(strUrl)) {

				if (!strUrl.contains("http://baike.baidu.com")) {
					strUrl = "http://baike.baidu.com" + strUrl;
				}
				System.out.println(strUrl);
				System.out.println(strUrlname);
//				mainmore(id, strUrl);
				
				Company company=mainUrl(id,strUrl,strname);
				OracleBaidu.InsertCompany(company);
				
//				TvPlay tvplay =mainmore(id, strUrl,strUrlname);
//				
//				OracleHaoSou.InsertTVplay(tvplay);//添加操作
				
//				OracleHaoSou.UpdateTVplay(tvplay);//修改操作
			}
		}
	}
	/**
	 * 2016年4月14日15:14:27
	 * 百度公司名称，进行数据的抓取
	 * @param args
	 */
	
	public static void company(){
		List<String> listArray = OracleBaidu.selectDIM_PRODUCE();
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://baike.baidu.com/search?word="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
					mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		company();
//		mainUrl( "1","http://baike.baidu.com/subview/6233903/6325078.htm", "北京环球创影国际文化传媒有限公司");

	}

}
