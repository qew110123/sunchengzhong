package com.artsoft.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Proxy;

import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;

public class Demo {
	public static String getKeyWordFromFile(String FileName) {
		// FileName : Config/keyword.txt
		StringBuilder strTemp = new StringBuilder();
		// String FilePath = System.getProperty("user.dir")+
		// "\\Config\\"+FileName;
		String FilePath = "config/" + FileName;
		File file = new File(FilePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				strTemp.append(tempString);
				strTemp.append("\n");
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return strTemp.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//// String cookstr="BAIDUID=3AADC6F35D3F3088991AA4460A697FE2:FG=1;
		//// BIDUPSID=3AADC6F35D3F3088991AA4460A697FE2; PSTM=1445389865;
		//// BDSFRCVID=YxIsJeCCxG3ICov459p-VZx1qNDZSXG-d5OR3J;
		//// H_BDCLCKID_SF=tRk8oItMJCvBfJuk-4QEbbQH-UnLqhcOW67Z0lOnMp05OqOv568heJOD3R6OJRow3NQghp0E5I5cVCO_e4bK-TrXDGuetx5;
		//// BDUSS=BnYVFaZEhifmlxYzhYaUg0cEdOUHpOcVYtU0NkZXZQc3R5bEZBT2JhVDVjMDVXQVFBQUFBJCQAAAAAAAAAAAEAAAChkbEONzY0Mjk1MzMzAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPnmJlb55iZWVl;
		//// locale=zh;
		//// H_PS_PSSID=12897_1430_17619_12826_17001_17470_17073_15669_12073_17421_17050";
		// String
		//// strUrl="http://baike.baidu.com/link?url=aoe7rD8rFgF6w_1UD08-XlUAt5Uqr_6Sb7Aw0dfHt5reNDSWJ3k1BIQBjOn2MchyxuZ4XpFxQGWaoMOe_NNkda";
		//// strUrl="http://index.youku.com/ProAction!getPromptAnswer.action?word=%E5%90%8D";
		// String strHtml = DownloadUtil.getHtmlText(strUrl, 1000 * 30,"UTF-8",
		//// null, null);
		//// String strHtml = DownloadUtil.readHtml(strUrl, 1000 * 30,"UTF-8",
		//// cookstr, null);
		//// StringBuffer strHtml = DownloadUtil.getContent(urlstr);
		//// System.out.println(strHtml);
		//
		// Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.basic-info");
		//// Element content = doc.getElementById("content");
		//// Elements links = content.getElementsByTag("a");
		// for (Element link : links) {
		// System.out.println(link);
		// System.out.println(link.text());
		//// String linkHref = link.attr("href");
		//// String linkText = link.text();
		// }
		// Integer.parseInt("2223830274");
		// double d = Double.parseDouble("2223830274");
		// System.out.println(d);
		// String s = "123.456 "; //要确保字符串为一个数值，否则会出异常
		// double d = Double.parseDouble(s);
		// float f = Float.parseFloat(s);
		// System.out.println(d);
		// System.out.println(f);

		// String str = "love23next234csdn3423javaeye";
		// str = str.trim();
		// String str2 = "";
		// if (str != null && !"".equals(str)) {
		// for (int i = 0; i < str.length(); i++) {
		// if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
		// str2 += str.charAt(i);
		// }
		// }
		//
		// }
		// System.out.println(str2);
		System.out.println(Integer.valueOf("118898"));
		// CommonUtil.setLog("32232323");
		// ConfigManager config = ConfigManager.getInstance();
		// String url=config.setConfigValue(strKey, strVal);

		// String[] keys=getKeyWordFromFile("keyword.txt").split("\n");
		// for (int i = 0; i < keys.length; i++) {
		//// System.out.println(i);
		//// System.out.println(keys[i]);
		// try {
		// String message = java.net.URLEncoder.encode(keys[i],"utf-8");
		// System.out.println(message);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// System.out.println(keys.toString());
		// System.out.println(getKeyWordFromFile("keyword.txt"));
		Proxy proxy = null;
		proxy = DealProxy.getInstance().getPoxxy();
		System.out.println(proxy);

		String url = "http://index.haosou.com/index.php?a=soIndexJson&q=%E8%B5%B5%E4%B8%BD%E9%A2%96&area=%E5%85%A8%E5%9B%BD";
		String strHtml = DownloadUtil.readHtml(url, 1000 * 30, "UTF-8", null, proxy);
		System.out.println(strHtml);

	}

}
