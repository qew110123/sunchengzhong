package com.artsoft.download.BaiDu;

import java.util.List;

import com.artsoft.oracle.OracleBaidu;

public class BaiduImageEntryPersonName {
	
	
	public static void runupdate() {
		List<String> listArray = OracleBaidu.selectbaipeople();

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
					
					BaiduImageEntry.baidumainurl(id, url, name, 4);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		System.out.println(listArray.size());
	}
/**
 * ��
 * �ٶȴ�������ͼƬ
 * 2016��6��22��15:10:38
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runupdate();
//		BaiduImageEntry.baidumainurl("7432", "http://baike.baidu.com/subview/3412436/16380924.htm","�����ﴫ��" , 0);
	}

}
