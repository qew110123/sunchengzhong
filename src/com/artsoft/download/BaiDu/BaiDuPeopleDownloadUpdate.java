package com.artsoft.download.BaiDu;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.Persion;
import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class BaiDuPeopleDownloadUpdate {

	/**
	 * 基本信息的抓取
	 * 
	 * @param strId
	 * @param url
	 */
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

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.basic-info");
		for (Element link : links) {
			String[] sourceStrArray = link.toString().split("d>");
			for (int i = 0; i < sourceStrArray.length; i++) {
				String baseInfoName = "";
				String baseInfoValue = "";
				baseInfoName = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item name\">", "</");
				if ("主要成就".equals(baseInfoName)) {
					// System.out.println(sourceStrArray[i].toString());
					baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString() + "#",
							"basicInfo-item value\">", "</d#", true, 0);
				} else {
					baseInfoValue = HtmlAnalyze.getTagText(sourceStrArray[i].toString(), "basicInfo-item value\">",
							"</d");
				}
				if (baseInfoName != null && baseInfoValue != null) {
					baseInfoName = baseInfoName.replace("&nbsp;", "");
					baseInfoValue = baseInfoValue.replace(" ", "").replace("\n", "").replace("展开", "");
					if ("主要成就".equals(baseInfoName)) {
						String[] strlist = baseInfoValue.split("<br/>");
						for (int j = 0; j < strlist.length; j++) {
							baseInfoValue = baseInfoValue
									+ HtmlAnalyze.getTagText("##" + strlist[j].toString() + ";**", "##", "**");
							if (baseInfoValue.contains("主要成就")) {
								baseInfoValue = HtmlAnalyze.getTagText("##" + strlist[j].toString() + ";**", "主要成就",
										"**");
							}
						}
						baseInfoValue = baseInfoValue.replace("等收起;", "");
						baseInfoValue = baseInfoValue.replace("<br/>", ";");
					}
					person = BaiDuPeopleDownload.buildPerson(baseInfoName, baseInfoValue, person);
				}
			}

			Elements linksss = doc.select("div.lemma-summary div.para");
			String information = "";
			int u = 0;
			for (Element element : linksss) {
				if (u == 0) {
					information = element.text();
					u += 1;
				} else {
					information = information + "||" + element.text();
				}
			}
			if (information != null && !"".equals(information)) {
				person.setDescription_text(information);
			}
			person.print();
			person.setId(Integer.parseInt(strId));
			person.setUrl(url);
			// 进行数据的添加 操作
			// OracleHaoSou.InsertTemDimPerson(person);
			// 进行数据的修改操作
			OracleHaoSou.upTemDimPerson(person);

		}
	}

	/**
	 * 进行数据的开始和结束数据的分开
	 * 
	 * @param statnum
	 * @param endnum
	 */
	private static void mainbaiduPeoPle(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectBaiduiInformation(Integer.toString(statnum),
				Integer.toString(endnum));
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				mainmore(listTemp.get(0), listTemp.get(1));
			}
		}
	}

	
	/**
	 * 进行百度数据的部分数据的更新操作
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub

		/**
		 * 2015年11月14日16:36:11 进行补全数据的基本信息
		 */
		
//		mainmore("1230", "http://baike.baidu.com/view/1960160.htm");
//		String xx = ConfigManager.getInstance().getConfigValue("numBaidunum");
//		int xxnum = Integer.parseInt(xx);
//		for (int i = xxnum; i < 11503; i = i + 1000) {
//			mainbaiduPeoPle(i, i + 1000);
//		}
		String regex = "<[\\s\\S]*?>";
		//需要操作的内容
	String content = "<g>学</g><g>习</gfdfdfdfdfd><g>物</g><g>理</g>";
		//操作后的结果resu
	String resu = content.replaceAll(regex,"");
	System.out.println(resu);
	}

}
