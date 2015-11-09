package com.artsoft.download;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownloadLetv {

	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
//		System.out.println(strHtml);
		String str = strHtml;
		String[] strarray = str.split("},\"ispay");
		for (int i = 0; i < strarray.length; i++) {
			String urlBranch = "";
			String Amount = "";
			String name = "";
			String score = "";
//			System.out.println(strarray[i]);
			System.out.println(Amount = HtmlAnalyze.getTagText(strarray[i], "playCount\":\"", "\""));
			System.out.println(score = HtmlAnalyze.getTagText(strarray[i], "rating\":\"", "\""));
			System.out.println(name = HtmlAnalyze.getTagText(strarray[i], "name\":\"", "\""));
			if (urlBranch != null || Amount != null || name != null || score != null || urlBranch != "" || Amount != ""
					|| name != "" || score != "") {
				try {

					OracleOpreater.intoReputation(name, "5", Amount, "0", "", mainUrl, "0", "0");
					OracleOpreater.intoReputation(name, "5", score, "0", "", mainUrl, "0", "1");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":¿ªÊ¼");
			for (int i = 0; i < 25; i++) {
				String mainUrl = "http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=" + i + "&s=1";
				mainurl(mainUrl);
			}
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":½áÊø");
			try {
				Thread.sleep(1000*60*60*23);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
