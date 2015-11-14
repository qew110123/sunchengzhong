package com.artsoft.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class BaiDuWordTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mainUrl = "http://www.baidu.com/s?wd=%E7%94%9C%E7%BE%8E%20%E7%94%B7%E6%BC%94%E5%91%98&rsv_spt=1&rsv_iqid=0xc28c799200032724&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=0&oq=%E8%8B%B1%E6%B1%89&rsv_t=c159DcYDetvkWSJa4wbk87ZVyoZdnbRysIdlrT3aFhfKzXoO6rlhhdbWKD%2F6Ai1V8xeC&inputT=2838&rsv_pq=cb186f4f00005c54&rsv_n=2&rsv_sug3=14&rsv_sug4=2838&rsv_sug=1https://www.baidu.com/s?wd=%E7%94%9C%E7%BE%8E%20%E7%94%B7%E6%BC%94%E5%91%98&rsv_spt=1&rsv_iqid=0xc28c799200032724&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=0&oq=%E8%8B%B1%E6%B1%89&rsv_t=c159DcYDetvkWSJa4wbk87ZVyoZdnbRysIdlrT3aFhfKzXoO6rlhhdbWKD%2F6Ai1V8xeC&inputT=2838&rsv_pq=cb186f4f00005c54&rsv_n=2&rsv_sug3=14&rsv_sug4=2838&rsv_sug=1";
		mainurl(mainUrl);

	}

	private static void mainurl(String mainUrl) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String tyPlayName = "";
//		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml, "cname: \"", "\""));
		// System.out.println(strHtml);
//		System.out.println(strHtml);
		Document doc = Jsoup.parse(strHtml);
//		Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("div.c-gap-top div.c-span4");
		for (Element element : links) {
			System.out.println(element+"-----------");
		}

	}

}
