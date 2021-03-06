package com.artsoft.download;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.artsoft.config.ConfigManager;
import com.artsoft.demo.DemoTime;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.Stirngstr;
import com.artsoft.util.TimeTest;

public class HaoSouWordDownLoad {

	public static void HaosouBranch(String urlBranch, String tvplayId, String tyPlayName, String DataType) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		System.out.println(strHtml);

		String strtext = HtmlAnalyze.getTagText(strHtml, "\":\"", "\"}");
		String starttime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");
		String endtime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");

		System.out.println(strtext);
		System.out.println(starttime);

		String[] sourceStrArray = strtext.toString().split("\\|");
		System.out.println(sourceStrArray.length);
		if (starttime != null && !"".equals(starttime)) {

			for (int i = 0; i < sourceStrArray.length; i++) {
				System.out.println(sourceStrArray[i] + DemoTime.getBeforeAfterDate(starttime, i));
				OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName, sourceStrArray[i], "0",
						DemoTime.getBeforeAfterDate(starttime, i).toString(), urlBranch, "0", DataType, "");

			}
		}

	}

	public static void HaosouPeoPleBranch(String urlBranch, String tvplayId, String tyPlayName, String DataType) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		System.out.println(strHtml);

		String strtext = HtmlAnalyze.getTagText(strHtml, "\":\"", "\"}");
		String starttime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");
		String endtime = HtmlAnalyze.getTagText(strHtml, "from\":\"", "\"}");

		System.out.println(strtext);
		System.out.println(starttime);

		String[] sourceStrArray = strtext.toString().split("\\|");
		System.out.println(sourceStrArray.length);
		if (starttime != null && !"".equals(starttime)) {

			for (int i = 0; i < sourceStrArray.length; i++) {
				System.out.println(sourceStrArray[i] + DemoTime.getBeforeAfterDate(starttime, i));
				// OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName,
				// sourceStrArray[i], "0",
				// DemoTime.getBeforeAfterDate(starttime, i).toString(),
				// urlBranch, "0", DataType,"");
				OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i],
						DemoTime.getBeforeAfterDate(starttime, i).toString(), "", urlBranch, DataType);
			}
		}

	}

	public static void mainProgram(int statnum, int endnum) {

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + statnum);

		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.select(Integer.toString(statnum), Integer.toString(endnum));

		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				// 指数
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
					HaosouBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 媒体关注度
				try {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					HaosouBranch(urlBranch, listTemp.get(0), listTemp.get(1), "4");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}
	}

	/**
	 * 人的数据的采集
	 */

	private static void mainPeoPle(int statnum, int endnum) {
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
				// 指数
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
					HaosouPeoPleBranch(urlBranch, listTemp.get(0), listTemp.get(1), "2");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 媒体关注度
				try {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					HaosouPeoPleBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
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

		// try {
		// String message = java.net.URLEncoder.encode("赵丽颖","utf-8");
		// System.out.println(message);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// HaosouBranch("http://index.haosou.com/index.php?a=soMediaJson&q=%E8%B5%B5%E4%B8%BD%E9%A2%96");
		// mainProgram(0,1);

		/**
		 * 进行电视剧数据的下载
		 */
		//// ConfigManager config = ConfigManager.getInstance();
		//// driver = config.getConfigValue("driver");
		// String xx=ConfigManager.getInstance().getConfigValue("ID");
		//
		// int xxnum=Integer.parseInt(xx);
		// System.out.println(xxnum);
		// for (int i = xxnum; i < 15780; i=i+1000) {
		//// i=15780;
		// mainProgram(i,i+1000);
		// }

		/**
		 * 进行人的运行
		 */
		// mainPeoPle();
		ConfigManager config = ConfigManager.getInstance();
		// driver = config.getConfigValue("driver");
		String xx = ConfigManager.getInstance().getConfigValue("IDpeople");
//		mainPeoPle();
		int xxnum=Integer.parseInt(xx);
		for (int i = xxnum; i < 16871; i = i + 1000) {
			// i=15780;
			mainPeoPle(i, i + 1000);
		}
	}

}
