package com.artsoft.download;

import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.util.List;

import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownloadWeibo {
	public static void hunanBranch(String mainUrl, String tvplayId, String tyPlayName, String DataType) {
		String strHtml = "";
		String strhtmlurl = "";
		String strhtmllittle = "";
		boolean bb = true;
		while (bb) {
			Proxy proxy = null;
			proxy = DealProxy.getInstance().getPoxxy();
			System.out.println(proxy);
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "utf-8", null, null);
			
			try {
				int t=(int) (10*Math.random());
				Thread.sleep((t+3)*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			ConfigManager.getInstance().setConfigValue("mainUrl", String.valueOf(mainUrl));
			
			if (strHtml != null && !"".equals(strHtml)) {
				strHtml = DownloadUtil.decodeUnicode(strHtml);
				System.out.println(strHtml);

				System.out.println(strhtmlurl = HtmlAnalyze.getTagText(strHtml, "direct_user_url_nologin:_nologin\">",
						"person_card"));
				// String strhtmllittle = "";
				System.out.println(strhtmllittle = HtmlAnalyze.getTagText(strHtml, "关注", "person_newwb"));
				if (strhtmllittle != null && !"".equals(strhtmllittle)) {
					bb = false;
				}
			}
		}
		if (strHtml != null && !"".equals(strHtml) && strhtmllittle != null && !"".equals(strhtmllittle)) {
			String[] sourceStrArray = strhtmllittle.toString().replaceAll("\n", "").replaceAll("	", "")
					.replaceAll(" ", "").trim().split("\\|");
			System.out.println(sourceStrArray.length);
			String strfansCount = "";
			String strvCount = "";
			int fansCount = 0;
			int vCount = 0;
			System.out.println(sourceStrArray[0]);
			System.out.println(strfansCount = sourceStrArray[1].replaceAll("粉丝", "").replaceAll("万", "0000"));
			System.out.println(strvCount = sourceStrArray[2].replaceAll("微博", ""));
			if (strfansCount != null && !"".equals(strfansCount) && strvCount != null && !"".equals(strvCount)) {
				fansCount = Integer.parseInt(strfansCount);
				vCount = Integer.parseInt(strvCount);
				OracleHaoSou.intoPeoPlewebo(tvplayId, strhtmlurl, fansCount, vCount, "", "", mainUrl, "1");
			}
		}
	}

	private static void mainweboPeoPle(int statnum, int endnum) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);

		for (Object Objstring : listArray) {
			
			
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					
					hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigManager config = ConfigManager.getInstance();
		// driver = config.getConfigValue("driver");
		String xx = ConfigManager.getInstance().getConfigValue("IDwebopeople");
		int xxnum = Integer.parseInt(xx);
		for (int i = xxnum; i < 16871; i = i + 1000) {
			// i=15780;
			mainweboPeoPle(i, i + 1000);
			
		}

		// hunanBranch("http://s.weibo.com/weibo/%E5%AD%94%E7%90%B3");

	}

}
