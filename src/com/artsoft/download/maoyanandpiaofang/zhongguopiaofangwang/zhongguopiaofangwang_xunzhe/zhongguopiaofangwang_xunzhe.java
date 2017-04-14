package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_xunzhe;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.DownCboooZhongGuoDetails;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONObject;

public class zhongguopiaofangwang_xunzhe {

	public static void xuanzheng(String name,String url) {
		// TODO Auto-generated method stub
		// String url="";

		TvPlay playtv = new TvPlay();
		// JSONObject objectobject = JSONObject.fromObject(object);
		String[] idlist = url.split("/");
		String idString = idlist[idlist.length - 1];

		playtv.setTvplay_id(idString);
		// String url = (String) objectobject.get("ID");
		// System.out.println(url = "http://www.cbooo.cn/m/" + url);
		playtv.setTvplay_url(url);

		boolean bb = true;
		String strHtmllittle = "";
		while (bb) {
			strHtmllittle = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtmllittle != null && !"".equals(strHtmllittle)) {
				bb = false;
			}
		}

//		String name = "";
		if (name.equals("")) {
			
			name = HtmlAnalyze.getTagText(strHtmllittle, "<title>", "_电影详情");
		}
		System.out.println(name);
		playtv.setTvplay_name(name);

		String niandai = ""; // 年代
		// niandai = (String) objectobject.get("releaseYear");

		niandai = HtmlAnalyze.getTagText(strHtmllittle, "<span>（", "）");
		System.out.println(niandai);
		playtv.setShoot_time(niandai);

		String bieming = "";// 别名
		// bieming = (String) objectobject.get("MovieEnName");

		bieming = HtmlAnalyze.getTagText(strHtmllittle, "</span><p>", "</p>");
		// System.out.println(bieming = bieming.replace(" ", ""));
		playtv.setAlias_en(bieming);

		String BoxOffice = ""; // 票房
		// BoxOffice = (String) objectobject.get("BoxOffice");
		// System.out.println(strHtmllittle);
		BoxOffice = HtmlAnalyze.getTagText(strHtmllittle, "累计票房<br />", "万</span>");
		playtv.setBox_office(BoxOffice);

		String area = "";

		DownCboooZhongGuoDetails.xiangxiurl(playtv, url, area);
	}

	public static void shoushuozhongguopiaofangwang(String name) {
		String url = "";
		String urf8name = "";
		try {
			urf8name = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// http://www.cbooo.cn/search?k=%E8%A1%80%E6%88%98%E9%92%A2%E9%94%AF%E5%B2%AD
		url = "http://www.cbooo.cn/search?k=" + urf8name;
		String strHtmllittle = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);

		Document doc = Jsoup.parse(strHtmllittle);

		Elements links = doc.select("ul.ulzx03 li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		// System.out.println(links.size());
		for (Element link : links) {
			// System.out.println(link.select("a").text());
			String urlstr = "";
			System.out.println(urlstr = link.select("a").attr("href"));
			String text = "";
			System.out.println(text = link.select("a").attr("title"));

			System.out.println(link.text());
			System.out.println(TimeTest.getNowTime("yyyy"));
			
			System.out.println(link.text().contains(TimeTest.getNowTime("yyyy")));
			System.out.println(name.toLowerCase().equals(text.toLowerCase()));

			if (name.toLowerCase().equals(text.toLowerCase()) && link.text().contains(TimeTest.getNowTime("yyyy"))) {
//				 System.out.println("11111");

				xuanzheng(name,urlstr);

			}
			// mainurl(urlstr);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		shoushuozhongguopiaofangwang("少年");
		 xuanzheng("父子雄兵","http://www.cbooo.cn/m/657204");
	}

}
