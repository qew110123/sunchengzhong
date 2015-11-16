package com.artsoft.download;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;

public class BaiDuWordTable {

	/**
	 * 百度 关键字搜索 甜美 男演员
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String mainUrl =
		// "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E7%94%9C%E7%BE%8E%20%E7%94%B7%E6%BC%94%E5%91%98&rsv_pq=a07636340002943a&rsv_t=d0b7BFMXwAZEAdbFX9SkVYWLnqBsLC2feIlvmjBApe95Tfz3mZVswjDvWa0&rsv_enter=0&rsv_n=2&rsv_sug3=3&inputT=3690&rsv_sug4=3691";
		// mainurl(mainUrl,words);
//		wordurl("甜美");
		
		String strkey=ReadTxtFile.getKeyWordFromFile("keybaiduqinggan.txt");
		String[] keys=strkey.split("\n");
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]!=null&&!"".equals(keys[i])) {
				wordurl(keys[i]);
			}
		}
	}

	public static void wordurl(String keyword) {
		String urlkeyword="";
		try {
			 urlkeyword=java.net.URLEncoder.encode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String wordurl1 = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=monline_3_dg&wd=" + urlkeyword
				+ "%20%E7%94%B7%E6%BC%94%E5%91%98&rsv_pq=87778fee0004a681&rsv_t=9599FfTi5Ox7HVgmAW7oruJX7vQDjuGKdU7IiL4eG4acd30viiLMBgAgsdLGmWbaWvSS&rsv_enter=1&rsv_sug3=3&rsv_n=2";

		mainurl(wordurl1, keyword+" 男演员");
		wordurl1 = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=" + urlkeyword
				+ "%E7%9A%84%20%E7%94%B7%E6%BC%94%E5%91%98&rsv_pq=87778fee0004a681&rsv_t=9599FfTi5Ox7HVgmAW7oruJX7vQDjuGKdU7IiL4eG4acd30viiLMBgAgsdLGmWbaWvSS&rsv_enter=1&rsv_sug3=3&rsv_n=2&bs=%E7%9A%84";

		mainurl(wordurl1, keyword+"的 男演员");
		wordurl1 = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=" + urlkeyword
				+ "%20%E5%A5%B3%E6%BC%94%E5%91%98&oq=%E7%94%9C%E7%BE%8E%E7%9A%84%20%E5%A5%B3%E6%BC%94%E5%91%98&rsv_pq=ef606b6b0004ad15&rsv_t=249ayCGXChRBRL59%2BgrWS77gnSo6k%2BtSJFqemG3dZQhIQhBYqOq3i7LEJcY&rsv_enter=0&rsv_sug3=8&rsv_sug1=1&inputT=1558&rsv_sug4=2800&rsv_sug=1";

		mainurl(wordurl1, keyword+" 女演员");
		wordurl1 = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=" + urlkeyword
				+ "%E7%9A%84%20%E5%A5%B3%E6%BC%94%E5%91%98&oq=%E7%94%9C%E7%BE%8E%E7%9A%84%20%E5%A5%B3%E6%BC%94%E5%91%98&rsv_pq=f6cc517e00050548&rsv_t=451eltfRnOJuKmtwYtiEyzL6sxqDK1p8VM%2BvNVwHL4viOpXZkDZ3szar%2FTo&rsv_enter=0";

		mainurl(wordurl1, keyword+"的 女演员");
	}

	private static void mainurl(String mainUrl, String words) {
		System.out.println(mainUrl);
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		//
		// System.out.println(HtmlAnalyze.getTagText(strHtml,
		// "window.open;eval(", "script>"));
		String tyPlayName = "";
		// System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml,
		// "t12.baidu.com\":\"", "\""));
		// System.out.println(strHtml);
		// System.out.println(strHtml);
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("div.c-gap-top div.c-span4");
		for (Element element : links) {
			// System.out.println(element + "-----------");
			String name = "";
			String imgurl = "";
			System.out.println(name = HtmlAnalyze.getTagText(element.toString(), "rsv_re_ename':'", "'"));
			if (name != null && !"".equals(name)) {
				System.out.println(imgurl = HtmlAnalyze.getTagText(element.toString(), "data-src=\"", "\""));
				if (imgurl != null && !"".equals(imgurl)) {

					String imgurl1 = "";
					imgurl1 = HtmlAnalyze.getTagText(imgurl, "http", "/it");
					String imgurlhost = "";
					imgurlhost = "http" + imgurl1 + "/";
					/// it/u=926119202,865825779&amp;fm=58
					// System.out.println(HtmlAnalyze.getTagText(imgurl.toString(),
					// "http://t12.baidu.com/",
					// "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/"));
					System.out
							.println(imgurl = imgurl.replaceAll(imgurlhost, "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/")
									.replaceAll("amp;", ""));
				}
				OracleHaoSou.intotempersonstyle(words, imgurl, name);

			}

		}

	}

}
