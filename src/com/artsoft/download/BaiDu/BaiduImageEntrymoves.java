package com.artsoft.download.BaiDu;

import java.util.List;

import com.artsoft.oracle.OracleBaidu;

public class BaiduImageEntrymoves {
	
	
	public static void runupdate() {
		
//		List<String> listArray = OracleBaidu.selectbaiTVplay();
//
//		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			String id = "";
//			String name = "";
//			String url = "";
//			System.out.println(id = listTemp.get(0));
//			System.out.println(url = listTemp.get(2));
//			System.out.println(name = listTemp.get(1));
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0)) && listTemp.get(1) != null
//					&& !"".equals(listTemp.get(1)) && listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
////					BaiDuTeleplayDownload.mainmore(id, url, name);
//				try {
//					
////					BaiduImageEntry.baidumainurl(id, url, name, 0);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//
//		}
		List<String> listArray = OracleBaidu.selectbaimove();

		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id = "";
			String name = "";
			String url = "";
			System.out.println(id = listTemp.get(0));
			System.out.println(url = listTemp.get(2));
			System.out.println(name = listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0)) && listTemp.get(1) != null
					&& !"".equals(listTemp.get(1)) && listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
//				BaiDuTeleplayDownload.mainmore(id, url, name);
				try {
					
//					BaiduImageyanyuan.mainmore(id, url, name, 3);
					BaiduImageEntry.baidumainurl(id, url, name, 3);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		System.out.println(listArray.size());
	}
/**
 * 电影
 * 百度词条数据图片
 * 2016年6月8日15:55:02
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runupdate();
	}

}
