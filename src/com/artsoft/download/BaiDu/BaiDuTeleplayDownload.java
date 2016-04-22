package com.artsoft.download.BaiDu;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

/**
 * �ٶ��ҕ������
 * 
 * @author Administrator
 *
 */

public class BaiDuTeleplayDownload {

	public static TvPlay mainmore(String strId, String url, String strUrlname) {
		TvPlay tvplay = new TvPlay();
		// TODO Auto-generated method stub
		tvplay.setTvplay_id(Integer.parseInt(strId));
		tvplay.setTvplay_url(url);
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
					
					if (contains(baseInfoName)) {
						Document doclite = Jsoup.parse(sourceStrArray[i].toString());
						Elements linkss = doclite.select("a");
						// System.out.println(baseInfoValue.replace("&nbsp;",
						// ""));##չ��##
						// if (linkss.size()==0) {
						//
						// }
						// System.out.println(linkss);
						// System.out.println(linkss.size()==0);
						if (linkss.size() == 0) {
							// baseInfoValue = baseInfoValue.replace(" ",
							// "").replace("\n", "").replace("չ��", "");
							/**
							 * 2016��4��21��11:26:14 ����û������ʱ���� �ո� �ٺ���������ݵ�����up
							 */
							baseInfoValue = baseInfoValue.replace("չ��", "");
							System.out.println(baseInfoValue);
						} else {
							baseInfoValue = "";
							for (Element element : linkss) {
								// System.out.println(linkss.attr("href"));
								// System.out.println(linkss.text());
								if (!element.attr("class").equals("sup-anchor")) {
									baseInfoValue = baseInfoValue + element.text() + "|http://baike.baidu.com"
											+ element.attr("href") + ",";
								}

							}
							
							
							System.out.println(baseInfoValue);
						}
					} else {
						/**
						 * 2016��4��21��14:03:39
						 * ������Ƭ�˵����ó����������ݵ��滻
						 */
						if (baseInfoName.equals("��Ƭ��")) {
							
//							baseInfoValue = baseInfoValue.replace("չ��", "");
							System.out.println(baseInfoValue);
							
						}else{
							baseInfoValue = baseInfoValue.replace(" ", "").replace("\n", "").replace("չ��", "");
							System.out.println(baseInfoValue);
						}
					}
					
					
					

				}
				tvplay = buildTvPlay(baseInfoName, baseInfoValue, tvplay);

			}
			String stills_url = "";
			Elements linkstills_url = doc.select("div.summary-pic img");
			// stills_url=HtmlAnalyze.getTagText(strHtml.toString(), "
			// target=\"_blank\">\n<img src=\"", "\"");
			System.out.println(stills_url = HtmlAnalyze.getTagText(linkstills_url.toString(), "src=\"", "\""));
			if (stills_url == null || stills_url.equals(null)) {
				stills_url = "";
			}
			tvplay.setStills_url(stills_url);

			Elements linksss = doc.select("div.lemma-summary div.para");
			String information = "";
			int u = 0;
			for (Element element : linksss) {
				// System.out.println("111"+element);
				if (u == 0) {
					information = element.text();
					u += 1;
				} else {
					information = information + "||" + element.text();
				}
			}
			if (information != null && !"".equals(information)) {
				tvplay.setBasic_info(information);
			}

			// tvplay.setBasic_info("");
			tvplay.print();
			// OracleHaoSou.InsertTVplay(tvplay);

		}
		try {

			// �������ݵ���һ�������� �����
			Elements linkli = doc.select("li.roleIntroduction-item");
			String personname = "";
			String personurl = "";
			String rolename = "";
			String personstillsurl = "";
			String dubbingname = "";
			String dubbingurl = "";
			String roleintro = "";
			for (Element elementli : linkli) {
				System.out.println(personstillsurl = HtmlAnalyze.getTagText(elementli.toString(), "<img src=\"", "\""));
				System.out.println(
						rolename = HtmlAnalyze.getTagText(elementli.toString(), "class=\"item-value\">", "</span>"));
				System.out.println(personname = elementli.select("div.role-actor span.item-value").text());
				System.out.println(personurl = elementli.select("div.role-actor span.item-value a ").attr("href"));
				// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-actor
				// span.item-value").toString(), "href=\"", "\""));
				System.out.println(dubbingname = elementli.select("div.role-voice span.item-value").text());
				System.out.println(dubbingurl = elementli.select("div.role-voice span.item-value a").attr("href"));
				// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-voice
				// span.item-value").toString(), "href=\"", "\""));
				System.out.println(
						roleintro = HtmlAnalyze.getTagText(elementli.toString(), "role-description\">", "</dd>"));
				if (personurl != "") {
					personurl = "http://baike.baidu.com" + personurl;
				}
				if (dubbingurl != "") {
					dubbingurl = "http://baike.baidu.com" + dubbingurl;
				}
				OracleHaoSou.intotemtvplay(strId, strUrlname, url, "", personname, personurl, rolename, personstillsurl,
						dubbingname, dubbingurl, roleintro);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tvplay;

	}

	/**
	 * �ж������Ƿ����Ҫ�ֿ�
	 * 
	 * @param strtext
	 * @return
	 */
	public static boolean contains(String strtext) {
		List<String> list = new ArrayList<String>();
		list.add("����"); // ���б����������
		list.add("���"); // ���б����������
		list.add("����"); // ���б����������
//		list.add("��Ƭ��");
		if (list.contains(strtext)) {
			return true;
		}
		return false;

	}

	/**
	 * �����ж��Ƿ�������Ҫ��ֵ
	 * 
	 * @param baseInfoName
	 * @param baseInfoValue
	 * @param tvplay
	 * @return
	 */
	public static TvPlay buildTvPlay(String baseInfoName, String baseInfoValue, TvPlay tvplay) {
		if (baseInfoValue != null && !baseInfoValue.equals("")) {
			baseInfoValue = baseInfoValue.replace("	", "");
		}
		//
		if ("������".equals(baseInfoName)) {
			tvplay.setTvplay_name(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			tvplay.setAlias_en(baseInfoValue);
		}
		if ("����".equals(baseInfoName) || "��������".equals(baseInfoName)) {
			tvplay.setAlias_cn(baseInfoValue);
		}
		if ("��Ʒʱ��".equals(baseInfoName)) {
			tvplay.setProduced_time(baseInfoValue);
		}
		if ("��Ʒ��˾".equals(baseInfoName)) {
			tvplay.setProduced_company(baseInfoValue);
		}
		if ("��Ƭ����".equals(baseInfoName)) {
			tvplay.setProduction_area(baseInfoValue);
		}
		if ("����ص�".equals(baseInfoName)) {
			tvplay.setShoot_place(baseInfoValue);
		}
		if ("���й�˾".equals(baseInfoName)) {
			tvplay.setIssuing_company(baseInfoValue);
		}
		if ("�ײ�ʱ��".equals(baseInfoName)) {
			tvplay.setPremiere_time(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			tvplay.setDirector(baseInfoValue);
		}
		if ("���".equals(baseInfoName)) {
			tvplay.setScreenwriter(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			tvplay.setMajor_actors(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			tvplay.setPages(baseInfoValue);
		}
		if ("ÿ������".equals(baseInfoName)) {
			tvplay.setTime_length(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			tvplay.setSubject(baseInfoValue);
		}
		if ("��Ƭ��".equals(baseInfoName)) {
			tvplay.setProducer(baseInfoValue);
		}
		if ("���߲���ƽ̨".equals(baseInfoName)) {
			tvplay.setPlay_platform(baseInfoValue);
		}
		if ("����ʱ��".equals(baseInfoName)) {
			tvplay.setOpen_time(baseInfoValue);
		}
		if ("ɱ��ʱ��".equals(baseInfoName) || "�ػ�ʱ��".equals(baseInfoName)) {
			tvplay.setClose_time(baseInfoValue);
		}
		if ("�ײ�ƽ̨".equals(baseInfoName)) {
			tvplay.setPremiere_platform(baseInfoValue);
		}
		if ("�ײ�ƽ̨".equals(baseInfoName)) {
			tvplay.setPremiere_platform(baseInfoValue);
		}
		if ("�ӵ�".equals(baseInfoName)) {
			tvplay.setBefore_eleplay(baseInfoValue);
		}
		if ("���ӵ�".equals(baseInfoName)) {
			tvplay.setNext_teleplay(baseInfoValue);
		}
		if ("��Ҫ����".equals(baseInfoName)) {
			tvplay.setMajor_awards(baseInfoValue);
		}

		if ("��ӳ����".equals(baseInfoName) || "��ӳʱ��".equals(baseInfoName)) {
			tvplay.setShow_date(baseInfoValue);
		}

		return tvplay;
	}

	/**
	 * �������ݵ�����
	 * 
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
		if (links.size() > 0) {
			String strUrl = "";
			String strUrlname = "";
			System.out.println(strUrlname = links.first().text());
			System.out.println(strUrl = links.attr("href"));
			// mainmore(id, strUrl,strUrlname);
			if (strUrl != null && !"".equals(strUrl)) {

				if (!strUrl.contains("http://baike.baidu.com")) {
					strUrl = "http://baike.baidu.com" + strUrl;
				}
				System.out.println(strUrl);
				// mainmore(id, strUrl);
				TvPlay tvplay = mainmore(id, strUrl, strUrlname);
				OracleHaoSou.InsertTVplay(tvplay);// ��Ӳ���
				// OracleHaoSou.UpdateTVplay(tvplay);//�޸Ĳ���
			}
		}
	}

	/**
	 * �������ݿ�ʼ�ͽ������ݵĲɼ�
	 * 
	 * @param statnum
	 * @param endnum
	 */
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

	public static void mainProgram(int statnum, int endnum) {

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + statnum);

		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.select(Integer.toString(statnum), Integer.toString(endnum));

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

			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// String url = "http://baike.baidu.com/subview/24151/7972045.htm";
		// mainmore("1", url,"���");

		// String urlmain =
		// "http://baike.baidu.com/search?word=%E8%8C%83%E5%86%B0%E5%86%B0&pn=0&rn=0&enc=utf8";
		// mainUrlall(idurlmain);

		// TODO Auto-generated method stub
		// new
		// WeiBo(1,"http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");
		// WeiBoBranch("http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");

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

		// /**
		// * ���е��Ӿ����ݵ�����
		// */
		//// ConfigManager config = ConfigManager.getInstance();
		//// String driver = config.getConfigValue("driver");
		// String xx=ConfigManager.getInstance().getConfigValue("numBaidu");
		//
		// int xxnum=Integer.parseInt(xx);
		// System.out.println(xxnum);
		// for (int i = xxnum; i < 15780; i=i+1000) {
		// // i=15780;
		// mainProgram(i,i+1000);
		// }
		/**
		 * ����
		 */
		TvPlay tvplay = mainmore("40", "http://baike.baidu.com/view/13097674.htm", "����С����");
//		OracleHaoSou.InsertTVplay(tvplay);// ��Ӳ���
		OracleHaoSou.UpdatPpartTVplay(tvplay);//������޸Ĳ���
	}

}
