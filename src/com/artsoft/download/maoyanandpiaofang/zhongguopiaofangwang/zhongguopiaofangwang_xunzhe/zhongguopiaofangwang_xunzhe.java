package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_xunzhe;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.DownCboooZhongGuoDetails;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONObject;

public class zhongguopiaofangwang_xunzhe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		xuanzheng("http://www.cbooo.cn/m/628324");
	}

	private static void xuanzheng(String url) {
		// TODO Auto-generated method stub
//		String url="";
		
		TvPlay playtv = new TvPlay();
//		JSONObject objectobject = JSONObject.fromObject(object);
		String [] idlist=url.split("/");
		String idString =idlist[idlist.length-1];
//		int id=
		
		playtv.setTvplay_id( Integer.parseInt(idString));
//		String url = (String) objectobject.get("ID");
//		System.out.println(url = "http://www.cbooo.cn/m/" + url);
		playtv.setTvplay_url(url);
		
		boolean bb = true;
		String strHtmllittle = "";
		while (bb) {
			strHtmllittle = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtmllittle != null && !"".equals(strHtmllittle)) {
				bb = false;
			}
		}

		
		String name = "";
		name= HtmlAnalyze.getTagText(strHtmllittle, "<title>", "_电影详情");
		System.out.println(name);
		playtv.setTvplay_name(name);

		String niandai = ""; // 年代
//		niandai = (String) objectobject.get("releaseYear");
		
		niandai= HtmlAnalyze.getTagText(strHtmllittle, "<span>（", "）");
		System.out.println(niandai);
		playtv.setShoot_time(niandai);

		String bieming = "";// 别名
//		bieming = (String) objectobject.get("MovieEnName");
		
		bieming= HtmlAnalyze.getTagText(strHtmllittle, "</span><p>", "</p>");
//		System.out.println(bieming = bieming.replace("	", ""));
		playtv.setAlias_en(bieming);

		String BoxOffice = ""; // 票房
//		BoxOffice = (String) objectobject.get("BoxOffice");
//		System.out.println(strHtmllittle);
		BoxOffice = HtmlAnalyze.getTagText(strHtmllittle, "累计票房<br />", "万</span>");
		playtv.setBox_office(BoxOffice);
		
		String area="";
		
		DownCboooZhongGuoDetails.xiangxiurl(playtv, url, area);
	}

}
