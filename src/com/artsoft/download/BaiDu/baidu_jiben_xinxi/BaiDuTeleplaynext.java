package com.artsoft.download.BaiDu.baidu_jiben_xinxi;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.artsoft.download.BaiDu.BaiDuTeleplayDownload;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class BaiDuTeleplaynext {
	
	
	public static void runnewMain(){
		List<String> listArray = OracleBaidu.selectbaidudianshiju();
//		for (Object Objstring : listArray) {
////			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
//				System.out.println(listTemp.get(0));
//				System.out.println(listTemp.get(2));
//				BaiDuTeleplayDownload.mainmore(listTemp.get(0), listTemp.get(2));
//			}
//		}
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://baike.baidu.com/search?word="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
					BaiDuTeleplayDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 电视剧基本信息采集
	 * 2016年11月2日14:39:05
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runnewMain();
	}

}
