package com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.xunzhexunxing;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.DownCboooZhongGuoDetails;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONObject;

public class xunzhe_xiangxixinxi {
	
	
	public static void xunzheyunxing(String url){
		//http://www.cbooo.cn/m/644631
		TvPlay playtv = new TvPlay();
//		JSONObject objectobject = JSONObject.fromObject(object);
		String [] idlist=url.split("/");
		String id= idlist[idlist.length-1];
		playtv.setTvplay_id(id);
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
		
		

		String name =  HtmlAnalyze.getTagText(strHtmllittle, "<h2>", "<span>");
		System.out.println(name);
		playtv.setTvplay_name(name);
//<span>（
		String niandai = ""; // 年代
		niandai = HtmlAnalyze.getTagText(strHtmllittle, "<span>（", "）");
		System.out.println(niandai);
		playtv.setShoot_time(niandai);
		
		String bieming = "";// 别名
		bieming = HtmlAnalyze.getTagText(strHtmllittle, "</span><p>", "</p>");
		System.out.println(bieming = bieming.replace("	", ""));
		playtv.setAlias_en(bieming);

		String BoxOffice = ""; // 票房
		BoxOffice = HtmlAnalyze.getTagText(strHtmllittle, "累计票房<br />", "万</span>");
		playtv.setBox_office(BoxOffice);
		
		DownCboooZhongGuoDetails.xiangxiurl(playtv, url,"");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		xunzheyunxing("http://www.cbooo.cn/m/590216");
		
		

	}

}
