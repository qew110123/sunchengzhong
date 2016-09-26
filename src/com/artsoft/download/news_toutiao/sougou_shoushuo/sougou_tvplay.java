package com.artsoft.download.news_toutiao.sougou_shoushuo;

import java.util.List;

import com.artsoft.oracle.Oracle;

import com.artsoft.download.news_toutiao.sougou_shoushuo.sougou_shoushuo;


public class sougou_tvplay {
	
	
	public static void runstatic( ) {
		// TODO Auto-generated method stub
		List<String> listArray = Oracle.selecttvplay();
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String name="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				try {
					
					
//					sougou_shoushuo.runnewMain(name, 2, id);
					
					TimeoutTest.runing(name, 2, id);
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
			}
				
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();
		

	}

}
