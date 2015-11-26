package com.artsoft.download;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

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

		try {

			String[] sourceStrArray = strtext.toString().split("\\|");
			System.out.println(sourceStrArray.length);
			if (starttime != null && !"".equals(starttime)) {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < sourceStrArray.length; i++) {
					System.out.println(sourceStrArray[i]
							+ DemoTime.getBeforeAfterDate(starttime, i).toString().replaceAll("-", ""));
					// OracleHaoSou.intoPlayAmont(tvplayId, tyPlayName,
					// sourceStrArray[i], "0",
					// DemoTime.getBeforeAfterDate(starttime,
					// i).toString().replaceAll("-", ""), urlBranch, "0",
					// DataType, "");

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���ѵ��Ӿ������������ ����ⱨ��");
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

		try {
			String[] sourceStrArray = strtext.toString().split("\\|");
			System.out.println(sourceStrArray.length);
			if (starttime != null && !"".equals(starttime)) {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < sourceStrArray.length; i++) {
					System.out.println(sourceStrArray[i] + DemoTime.getBeforeAfterDate(starttime, i));
					// OracleHaoSou.intoPeoPle(tvplayId, sourceStrArray[i],
					// DemoTime.getBeforeAfterDate(starttime, i).toString(), "",
					// urlBranch, DataType);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�˷������ݺ���ⱨ��");
		}

	}

	public static void mainProgram(int statnum, int endnum) {

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + statnum);

		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.select(Integer.toString(statnum), Integer.toString(endnum));

		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				// ָ��
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8")
							+ "&area=%E5%85%A8%E5%9B%BD";
					HaosouBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					System.out.println("���б����ȴ�5����" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				// ý���ע��
				try {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1).replaceAll(",", ""), "utf-8");
					HaosouBranch(urlBranch, listTemp.get(0), listTemp.get(1), "4");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					System.out.println("���б����ȴ�5����" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "���Ӿ�", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}
	}

	/**
	 * �˵����ݵĲɼ�
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
				// ָ��
				try {
					// urlBranch =
					// "http://index.haosou.com/index.php?a=soMediaJson&q="+java.net.URLEncoder.encode(listTemp.get(1),"utf-8");
					urlBranch = "http://index.haosou.com/index.php?a=soIndexJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&area=%E5%85%A8%E5%9B%BD";
					HaosouPeoPleBranch(urlBranch, listTemp.get(0), listTemp.get(1), "2");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					System.out.println("���б����ȴ�5����" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();

				}
				// ý���ע��
				try {
					urlBranch = "http://index.haosou.com/index.php?a=soMediaJson&q="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					HaosouPeoPleBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					System.out.println("���б����ȴ�5����" + urlBranch);
					try {
						Thread.sleep(1000 * 60 * 5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "���Ӿ�", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// try {
		// String message = java.net.URLEncoder.encode("����ӱ","utf-8");
		// System.out.println(message);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// HaosouBranch("http://index.haosou.com/index.php?a=soMediaJson&q=%E8%B5%B5%E4%B8%BD%E9%A2%96");
		// mainProgram(0,1);
		Scanner in = new Scanner(System.in);
		System.out.println("������1:���е��Ӿ������  ,2:�����˵�����");
		String numi = in.next();

		/**
		 * ���е��Ӿ����ݵ�����
		 */
		// ConfigManager config = ConfigManager.getInstance();
		// driver = config.getConfigValue("driver");
		if ( "1".equals(numi)) {

			while (true) {
				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
				String xx = ConfigManager.getInstance().getConfigValue("ID");
				
				int xxnum = Integer.parseInt(xx);
				System.out.println(xxnum);
				for (int i = xxnum; i < 15780; i = i + 1000) {
					// i=15780;
					mainProgram(i, i + 1000);
				}
			}
		}
		
		/**
		 * �����˵�����
		 */
		if ("2".equals(numi)) {
			
			while (true) {
				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
				// ConfigManager config = ConfigManager.getInstance();
				// driver = config.getConfigValue("driver");
				String xx = ConfigManager.getInstance().getConfigValue("IDpeople");
				// mainPeoPle();
				int xxnum = Integer.parseInt(xx);
				for (int i = xxnum; i < 16871; i = i + 1000) {
					// i=15780;
					mainPeoPle(i, i + 1000);
				}
			}
		}
	}

}
