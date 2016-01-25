package com.artsoft.download.BaiDu;

import java.util.List;

import com.artsoft.oracle.OracleBaidu;

public class BaiDuPeoplemore {

	/**
	 * 进行百度信息数据人数的添加
	 * 2015年12月2日15:14:31
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BaiDuPeopleDownload.mainmore("", "http://baike.baidu.com/view/3919236.htm");
		List<String> listArray = OracleBaidu.selecthuoqu();
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(2));
				BaiDuPeopleDownload.mainmore(listTemp.get(0), listTemp.get(2));
			}
		}
	}

}
