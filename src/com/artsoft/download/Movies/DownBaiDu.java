package com.artsoft.download.Movies;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.BaiDu.BaiDuTeleplayDownload;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

public class DownBaiDu {

	/**
	 * 进行数据的搜索
	 * 
	 * @param urlmain
	 * @param id
	 * @param strname
	 */
	public static void mainUrlall(String urlmain, String id, String strname) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("a.result-title");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		System.out.println(links.size());
		// for (Element link : links) {
		// String idnum = "";
		// String strVolumes = "";
		// System.out.println(strVolumes = link.text());
		// System.out.println(idnum = link.attr("href"));
		// }
		if (links.size() > 0) {
			String strUrl = "";
			String strUrlname = "";
			System.out.println(strUrlname = links.first().text());
			System.out.println(strUrl = links.attr("href"));
			// mainmore(id, strUrl,strUrlname);
			if (strUrl != null && !"".equals(strUrl)) {

				if (!strUrl.contains("http://baike.baidu.com")) {
					strUrl = "http://baike.baidu.com" + strUrl;
				}
				System.out.println(strUrl);
				// mainmore(id, strUrl);
				TvPlay tvplay = BaiDuTeleplayDownload.mainmore("1", strUrl, "");
				tvplay.setTvplay_name(strname);
				// OracleHaoSou.InsertTVplay(tvplay);//添加操作
				// OracleHaoSou.UpdateTVplay(tvplay);//修改操作
				OracleHaoSou.InWangLuoTVplay(tvplay);// 添加操作网络操作
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TimeTest tt = new TimeTest();
		String timesday=tt.getNowTime("yyyyMMdd");
		List<String> listArray = OracleNetwork.selectbaidudianshiju(timesday);

		for (Object Objstring : listArray) {
			// System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					String text=listTemp.get(0);
					text=text.replace(" ", "").replace("免费版","");
					urlBranch = "http://baike.baidu.com/search?word="
							+ java.net.URLEncoder.encode(text, "utf-8") + "&pn=0&rn=0&enc=utf8";
					mainUrlall(urlBranch, "", text);

					CommonUtil.setLog(
							TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + text + "," + listTemp.get(0));

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		// String text = "绝世撸神";
		// String urlBranch = "";
		// try {
		// urlBranch = "http://baike.baidu.com/search?word=" +
		// java.net.URLEncoder.encode(text, "utf-8") + "&pn=0&rn=0&enc=utf8";
		// mainUrlall(urlBranch,"", text);
		//
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// text + "," + text);
		//
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
