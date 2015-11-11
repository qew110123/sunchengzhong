package com.artsoft.download;

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

	private static void mainmore(String strId, String url) {
		Persion person = new Persion();
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// String strHtmlMore = "";
		// System.out.println(strHtmlMore = HtmlAnalyze.getTagText(strHtml,
		// "basic-info", "anchor-list"));
		//// System.out.println(strHtmlMore =
		// DownloadUtil.decodeUnicode(strHtmlMore));
		// System.out.println(HtmlAnalyze.getTagText(strHtmlMore, "������",
		// "anchor-list"));

		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("div.basic-info");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		// System.out.println(links.size());
		for (Element link : links) {
			// String idnum = "";
			// String strVolumes = "";
			// System.out.println(strVolumes =
			// link.select("span.inner-title").text());
			// System.out.println(idnum = link.select("a.video").attr("href"));
			// System.out.println(idnum = HtmlAnalyze.getTagText(idnum, "/f/",
			// ".html"));
			// System.out.println(link);
			// System.out.println(link);

			String[] sourceStrArray = link.toString().split("d>");

			for (int i = 0; i < sourceStrArray.length; i++) {
				String baseInfoName = "";
				String baseInfoValue = "";
				baseInfoName = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item name\">", "</");
				baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item value\">", "</d");
				if (baseInfoName != null && baseInfoValue != null) {
					baseInfoName = baseInfoName.replace("&nbsp;", "");
					// System.out.println(baseInfoValue =
					// baseInfoValue.replace(" ", "").replace("\n", "##")
					// .replace("##չ��##", "").replace("####", ""));
					baseInfoValue = baseInfoValue.replace(" ", "").replace("\n", "").replace("չ��", "");
					// System.out.println(baseInfoValue.replace("&nbsp;",
					// ""));##չ��##

					person = buildPerson(baseInfoName, baseInfoValue, person);
				}
			}
			person.setId(Integer.parseInt(strId));
			person.setUrl(url);
			OracleHaoSou.InsertTemDimPerson(person);

			// System.out.println();
			// String name="";
			// System.out.println(name=HtmlAnalyze.getTagText(link.toString(),
			// "������", "</dd>"));
			// String nationality="";
			// System.out.println(nationality=HtmlAnalyze.getTagText(link.toString(),
			// "�� ��", "</dd>"));
			// String blood="";
			// System.out.println(blood=HtmlAnalyze.getTagText(link.toString(),
			// "Ѫ ��", "</dd>"));
			// String height="";
			// System.out.println(height=HtmlAnalyze.getTagText(link.toString(),
			// "�� ��", "</dd>"));
			// String weight="";
			// System.out.println(weight=HtmlAnalyze.getTagText(link.toString(),
			// "�� ��", "</dd>"));
			// String occupation="";
			// System.out.println(occupation=HtmlAnalyze.getTagText(link.toString(),
			// "ְ ҵ", "</dd>"));
			// String coustellation="";
			// System.out.println(coustellation=HtmlAnalyze.getTagText(link.toString(),
			// "�� ��", "</dd>"));
			// String birthday="";
			// System.out.println(birthday=HtmlAnalyze.getTagText(link.toString(),
			// "��������", "</dd>"));

		}
	}

	public static Persion buildPerson(String baseInfoName, String baseInfoValue, Persion person) {
		if ("������".equals(baseInfoName)) {
			person.setName(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
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
		// if("ְҵ".equals(baseInfoName)){
		// person.setOccupation(baseInfoValue);
		// }
		if ("��ҵԺУ".equals(baseInfoName)) {
			person.setShcool(baseInfoValue);
		}
		if ("���͹�˾".equals(baseInfoName)) {
			person.setBrokerage_firm(baseInfoValue);
		}
		if ("������Ʒ".equals(baseInfoName)) {
			person.setOpus(baseInfoValue);
		}
		if ("��Ҫ�ɾ�".equals(baseInfoName)) {
			person.setMajor_awards(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setHomeplace(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			person.setBrokers(baseInfoValue);
		}
		if ("�Ա�".equals(baseInfoName)) {
			person.setGender(baseInfoValue);
		}
		// if("����".equals(baseInfoName)){
		//
		// }
		// if("��ż".equals(baseInfoName)){
		//
		// }
		return person;
	}

	private static void mainUrlall(String urlmain, String id, String strname) {
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
		if (links.size() == 10) {
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
		}
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

		ConfigManager config = ConfigManager.getInstance();
		// driver = config.getConfigValue("driver");
		String xx = ConfigManager.getInstance().getConfigValue("IDBaiDupeople");
		int xxnum = Integer.parseInt(xx);
		for (int i = xxnum; i < 16871; i = i + 1000) {
			// i=15780;
			mainweboPeoPle(i, i + 1000);

		}

	}
}
