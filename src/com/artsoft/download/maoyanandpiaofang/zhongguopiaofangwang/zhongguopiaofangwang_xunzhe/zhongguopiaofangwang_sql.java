package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_xunzhe;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.DownCboooZhongGuoDetails;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class zhongguopiaofangwang_sql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 金刚：骷髅岛
		 String sql = "";
		 zhongguopiaofangwang_sql.cbooo(sql);
		
		 zhongguopiaofangwang_sql.shoushuozhongguopiaofangwang("0", "金刚：骷髅岛",
		 "2017");
		
		
		
//		System.out.println(Stringtext("欢迎光临我的JAVA世纪网www.java123.net: ::: ："));

//		int count = 0;
//		String regEx = "[\\u4e00-\\u9fa5]";
//		// System.out.println(regEx);
//		String str = "Internet网络is真好 very  good ^_^!";
//		// System.out.println(str);
//		Pattern p = Pattern.compile(regEx);
//		Matcher m = p.matcher(str);
//		System.out.print("提取出来的中文有：");
//		System.out.println(m.groupCount());
//
//		while (m.find()) {
//			System.out.print(m.group());
//		}
//		System.out.println();
//
//		String str1 = "欢迎光临我的JAVA世纪网www.java123.net: ::: ：";
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < str1.length(); i++) {
//			if ((str1.charAt(i) + "").getBytes().length > 1) {
//				sb.append(str1.charAt(i));
//			}
//		}
//		System.out.println(sb);

	}
	
	
	public static String Stringtext(String texts){
		String str1 = texts;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str1.length(); i++) {
			if ((str1.charAt(i) + "").getBytes().length > 1) {
				sb.append(str1.charAt(i));
			}
		}
		System.out.println(sb);
		return sb.toString();
		
		
	}

	public  static void cbooo(String sql) {
		// TODO Auto-generated method stub
		robotrun(sql);

	}

	/**
	 * 2017年3月15日11:08:05 进行百度百科电视剧数据的数据
	 * 
	 * @param sql
	 */
	public static void robotrun(String sql) {
		List<String> listArray = OracleBaidu.selectmovesql(sql);

		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id = "";
			String name = "";
			String nian = "";
			System.out.println(id = listTemp.get(0));
			System.out.println(name = listTemp.get(1));
			System.out.println(nian = listTemp.get(2));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0)) && listTemp.get(1) != null
					&& !"".equals(listTemp.get(1)) && listTemp.get(2) != null && !"".equals(listTemp.get(2))) {

				// TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url,
				// name);
				// // tvplay.setBaikefilmname(strUrlname);
				// tvplay.setTvplay_name(name);
				// OracleHaoSou.InsertTVplay(tvplay);// 添加操作

				shoushuozhongguopiaofangwang(id, name, nian);

			}

		}
		System.out.println(listArray.size());
	}

	public static void xuanzheng(String id, String name, String url) {
		// TODO Auto-generated method stub
		// String url="";

		TvPlay playtv = new TvPlay();
		// JSONObject objectobject = JSONObject.fromObject(object);
		String[] idlist = url.split("/");
		String idString = idlist[idlist.length - 1];
		playtv.setId(id);

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

		// String name = "";
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

	public static void shoushuozhongguopiaofangwang(String id, String name, String nian) {
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

			// System.out.println(link.text());
			// System.out.println(TimeTest.getNowTime("yyyy"));

			// System.out.println(link.text().contains(TimeTest.getNowTime("yyyy")));
			// System.out.println(name.toLowerCase().equals(text.toLowerCase()));

			if (Stringtext(name.toLowerCase()).equals(Stringtext(text.toLowerCase())) && link.text().contains(nian)) {
				// System.out.println("11111");

				xuanzheng(id, name, urlstr);

			}
			// mainurl(urlstr);
		}

	}

}
