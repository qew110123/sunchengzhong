package com.artsoft.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.util.DownloadUtil;

public class WeBoCommentary {

	
	/**
	 * Î¢²©ÆÀÂÛ
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String mainUrl="http://weibo.cn/comment/D3qCQieAj?uid=5187664653&rl=0&vt=4&page=2";
		mainurl(mainUrl);
	}

	private static void mainurl(String mainUrl) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "utf-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
//		System.out.println(strHtml);
//		strHtml = DownloadUtil.decodeUnicode(strHtml);

		System.out.println(strHtml);
//		Document doc = Jsoup.parse(strHtml);
//		Elements links = doc.select("div.basic-info");
//		for (Element link : links) {
//			System.out.println(link);
//		}
	}

}
