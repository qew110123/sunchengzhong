package com.artsoft.download.BaiDu.baidu_jiben_xinxi;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.artsoft.download.BaiDu.BaiDuPeopleDownload;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class BaiDuPeoplemoremingcheng {

	/**
	 * 进行百度信息数据人数的添加
	 * 通过名称进行baidu 数据的下载 
	 * 2015年12月10日13:29:22
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		BaiDuPeopleDownload.mainmore("", "http://baike.baidu.com/view/3919236.htm");
		List<String> listArray = OracleBaidu.selecthuoqumingcheng();
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
//				BaiDuPeopleDownload.mainmore(listTemp.get(0), listTemp.get(2));
//				
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
					String urlBranch = "";
					try {
						urlBranch = "http://baike.baidu.com/search?word="
								+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";

						BaiDuPeopleDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
						CommonUtil.setLog(
								TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(1));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					TimeTest.seleepTime(5, 5);

				}
			}
		}
	}

}
