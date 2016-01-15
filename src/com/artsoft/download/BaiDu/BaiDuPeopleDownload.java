package com.artsoft.download.BaiDu;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.Persion;
import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

/**
 * �ٶ���������
 * 
 * @author Administrator
 *
 */

public class BaiDuPeopleDownload {

	public static Persion mainmore(String strId, String url) {
		Persion person = new Persion();
//		person.setId(Integer.valueOf(strId));
		person.setId(Integer.parseInt(strId));
		person.setUrl(url);
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		if (!strHtml.contains("��Ա")) {
			System.out.println();
			return null;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.basic-info");
		for (Element link : links) {

			String[] sourceStrArray = link.toString().split("d>");

			for (int i = 0; i < sourceStrArray.length; i++) {
				String baseInfoName = "";
				String baseInfoValue = "";
				baseInfoName = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item name\">", "</");
				if ("��Ҫ�ɾ�".equals(baseInfoName)) {
					baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString() + "#",
							"basicInfo-item value\">", "</d#", true, 0);
				} else {
					baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item value\">",
							"</d");
				}
				if (baseInfoName != null && baseInfoValue != null) {
					baseInfoName = baseInfoName.replace("&nbsp;", "");
					baseInfoValue = baseInfoValue.replace(" ", "").replace("\n", "");
					
					if ("��Ҫ�ɾ�".equals(baseInfoName)) {
						if (!"".equals(baseInfoValue)) {
							// System.out.println(baseInfoValue=);
							String[] strlist = baseInfoValue.replace("չ��", "").split("<br/>");
							for (int j = 0; j < strlist.length; j++) {
								if (baseInfoValue==null||baseInfoValue.equals("")||baseInfoValue.equals("null")) {
									baseInfoValue="";
								}
								System.out.println((HtmlAnalyze.getTagText("##" + baseInfoValue + ";**", "##", "**").replaceAll(";", "")).equals(HtmlAnalyze.getTagText("##" + strlist[j].toString() + ";**", "##", "**").replaceAll(";", "")));
								if (!(HtmlAnalyze.getTagText("##" + baseInfoValue + ";**", "##", "**").replaceAll(";", "")).equals(HtmlAnalyze.getTagText("##" + strlist[j].toString() + ";**", "##", "**").replaceAll(";", ""))) {
									
									baseInfoValue = baseInfoValue
											+ HtmlAnalyze.getTagText("##" + strlist[j].toString() + ";**", "##", "**");
									if (baseInfoValue.contains("��Ҫ�ɾ�")) {
										// baseInfoValue="";
										baseInfoValue = HtmlAnalyze.getTagText("##" + strlist[j].toString() + ";**", "��Ҫ�ɾ�",
												"**");
									}
								}
								
							}
						}
					}

//					System.out.println(baseInfoValue);
					person = buildPerson(baseInfoName, baseInfoValue, person);
				}
			}
			
			Elements linksss = doc.select("div.lemma-summary div.para");
			String information = "";
			int u = 0;
			for (Element element : linksss) {
				// System.out.println("111"+element);
				if (u == 0) {
					information = element.text();
					u+=1;
				} else {
					information = information + "||" + element.text();
				}
			}
			if (information!=null&&!"".equals(information)) {
				person.setDescription_text(information);
			}
			person.print();
			

		}
		//�������ݵ���� ����
		//OracleHaoSou.InsertTemDimPerson(person);
		return person;
	}

	public static Persion buildPerson(String baseInfoName, String baseInfoValue, Persion person) {
		baseInfoValue=baseInfoValue.replaceAll("&nbsp;", "");
		baseInfoValue=baseInfoValue.replaceAll("&middot;", "��");
		if ("������".equals(baseInfoName)) {
			baseInfoValue=delectbiaoqian(baseInfoValue);
			baseInfoValue=baseInfoValue.replaceAll("&middot;", "��");
			person.setName(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			baseInfoValue=delectbiaoqian(baseInfoValue);
			baseInfoValue=baseInfoValue.replaceAll("&middot;", "��");
			person.setAlias_en(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setAlias_cn(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setNationality(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setVolk(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			baseInfoValue=baseInfoValue.replaceAll("&nbsp;", "");
			person.setConstellation(baseInfoValue);
		}
		if ("Ѫ��".equals(baseInfoName)) {
			person.setBloodtype(baseInfoValue);
		}
		if ("���".equals(baseInfoName)) {
			person.setHeight(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setWeight(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			person.setBirthday_place(baseInfoValue);
		}
		if ("��������".equals(baseInfoName)) {
			person.setBirthday(baseInfoValue);
		}
		if ("��������".equals(baseInfoName) || "��������".equals(baseInfoName)) {
			person.setDeathday(baseInfoValue);
		}
		if ("ְҵ".equals(baseInfoName)) {
			person.setOccupation(baseInfoValue);
		}
		if ("��ҵԺУ".equals(baseInfoName)) {
			person.setShcool(baseInfoValue);
		}
		if ("���͹�˾".equals(baseInfoName)) {
			person.setBrokerage_firm(baseInfoValue);
		}
		if ("������Ʒ".equals(baseInfoName)) {
			baseInfoName=delectbiaoqian(baseInfoName);
			person.setOpus(baseInfoValue);
		}
		if ("��Ҫ�ɾ�".equals(baseInfoName)) {
			baseInfoValue=delectbiaoqian(baseInfoValue);
			person.setMajor_awards(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setHomeplace(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			person.setBrokers(baseInfoValue);
		}
		if ("�Ա�".equals(baseInfoName)) {
			baseInfoValue=baseInfoValue.replace("&nbsp;", "");
			person.setGender(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setHobby(baseInfoValue);
		}
		// if("����".equals(baseInfoName)){
		//
		// }
		// if("��ż".equals(baseInfoName)){
		//
		// }
		return person;
	}

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
//		if (links.size() == 10) {
			try {
				String strUrl = "";
				System.out.println(links.first().text());
				System.out.println(strUrl = links.attr("href"));
				// strUrl.contains("http://baike.baidu.com");
				if (strUrl != null && !"".equals(strUrl)) {

					if (!strUrl.contains("http://baike.baidu.com")) {
						strUrl = "http://baike.baidu.com" + strUrl;
					}
					System.out.println(strUrl);
					mainmore(id, strUrl);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}

	private static void mainweboPeoPle(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);

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
					CommonUtil.setLog(
							TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(1));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TimeTest.seleepTime(5, 5);

			}
			// intoPlayAmont("0", "���Ӿ�", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}
	}

	/**
	 * ������Ϣ���ݵ�ץȡ
	 * 
	 * @param mainUrl
	 */
	public static void maininformation(String id, String mainUrl, String sex) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}

		// System.out.println(strHtml);
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("div.lemma-summary div.para");
		String information = "";
		int u = 0;
		for (Element element : links) {
			// System.out.println("111"+element);
			if (u == 0) {
				information = element.text();
				u+=1;
			} else {
				System.out.println(information = information + "||" + element.text());
			}
		}
		String strsex = "";
		if (information != null && !"".equals(information)) {
			if (sex == null || "".equals(sex)) {
				if (information.contains("Ů���") || information.contains("Ů��Ա") || information.contains("Ů����")) {
					strsex = "Ů";
				}
				if (information.contains("�����") || information.contains("����Ա") || information.contains("������")) {
					strsex = "��";
				}
			}
			// OracleHaoSou.updateiInformation(Integer.parseInt(id),
			// information, strsex);
		}
	}

	/**
	 * �������ݵĿ�ʼ�ͽ������ݵķֿ�
	 * 
	 * @param statnum
	 * @param endnum
	 */
	private static void mainbaiduPeoPle(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectBaiduiInformation(Integer.toString(statnum),
				Integer.toString(endnum));
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);

		for (Object Objstring : listArray) {

			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			System.out.println(listTemp.get(2));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				maininformation(listTemp.get(0), listTemp.get(1), listTemp.get(2));
			}
		}
	}
	public static String delectbiaoqian(String major_awards){
		major_awards = major_awards.replaceAll("<[^>]+>", "");
		if(major_awards.indexOf("��") !=- -1){
			major_awards = major_awards.replaceAll("��", ";");
		}
		if(major_awards.indexOf("&nbsp;") !=- -1){
			major_awards = major_awards.replaceAll("&nbsp;", ";");
		}
		major_awards = major_awards.replaceAll("\\[\\d+\\]", "");
		major_awards = major_awards.replaceAll("'", "[������]");
		return major_awards;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// String url = "http://baike.baidu.com/view/9209.htm";
		// mainmore(url);

		// String urlmain =
		// "http://baike.baidu.com/search?word=%E8%8C%83%E5%86%B0%E5%86%B0&pn=0&rn=0&enc=utf8";
		// mainUrlall(idurlmain);

		// TODO Auto-generated method stub
		// new
		// WeiBo(1,"http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");
		// WeiBoBranch("http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");

		/*
		 * ���л�����Ϣ�Ĳɼ�������
		 */
		// ConfigManager config = ConfigManager.getInstance();
		// // driver = config.getConfigValue("driver");
		// String xx =
		// ConfigManager.getInstance().getConfigValue("IDBaiDupeople");
		// int xxnum = Integer.parseInt(xx);
		// for (int i = xxnum; i < 16871; i = i + 1000) {
		// // i=15780;
		// mainweboPeoPle(i, i + 1000);
		//
		// }
		// ********************************************************
		/**
		 * 2015��11��14��16:36:11 ���в�ȫ���ݵĻ�����Ϣ
		 */

		// String xx =
		// ConfigManager.getInstance().getConfigValue("numBaidunum");
		// int xxnum = Integer.parseInt(xx);
		// for (int i = xxnum; i < 11503; i = i + 1000) {
		// // i=15780;
		// mainbaiduPeoPle(i, i + 1000);
		//
		// }
//		mainmore("", "http://baike.baidu.com/view/1269111.htm?qq-pf-to=pcqq.c2c");
		

	}

}
