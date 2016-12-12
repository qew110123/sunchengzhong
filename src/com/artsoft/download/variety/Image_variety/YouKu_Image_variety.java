package com.artsoft.download.variety.Image_variety;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class YouKu_Image_variety {
	
	
	private static String youkuMaim(String urlMain) {
		try {
			String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}
			if (strHtml == null || strHtml.equals("")) {
				// return;
				strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			}

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.box-series div.p-thumb");
			for (Element link : links) {
				String name="";
				String strmainurl = "";
				String imgurl="";
				System.out.println(strmainurl = link.select("div.p-thumb a").attr("href"));
				System.out.println(name= link.select("div.p-thumb a").attr("title"));
				System.out.println(imgurl= link.select("img").attr("src"));
				DownloadImage.download(imgurl, name+".jpg", "D:\\Image\\variety\\youku\\");
				
				
			}

			// 进行下一页数据的判断

			String strnexturl = HtmlAnalyze.getTagText(strHtml, "<li class=\"next\" title=\"下一页\"><a href=\"",
					"\"  charset=");
			if (strnexturl != null || "".equals(strnexturl)) {
				strnexturl = "http://www.youku.com" + strnexturl;
				return strnexturl;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		String url = "";
		String[] diqu = { "大陆", "香港", "台湾", "韩国", "日本" , "美国", "澳大利亚" };
		String[] leixing = { "优酷出品", "优酷牛人", "脱口秀", "真人秀", "选秀", "美食", "旅游", "汽车", "访谈", "纪实", "搞笑", "时尚", "晚会", "理财", "演唱会", "曲艺", "益智", "音乐", "舞蹈", "体育娱乐", "游戏", "生活" };
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				// try {
				for (int i = 1; i < 30; i++) {
					try {
						url = "http://list.youku.com/category/show/c_85_g_" + java.net.URLEncoder.encode(leixingtxt, "utf-8")
								+ "_a_" + java.net.URLEncoder.encode(diqutxt, "utf-8") + "_s_1_d_" + i + ".html";
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(url);
					String urlnext = youkuMaim(url);
					if (urlnext.equals("") || urlnext == "" || urlnext == null) {
						break;
					}
				}

			}

		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
	}

}
