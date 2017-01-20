package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_tvplay_move_url;

import java.util.List;

import com.artsoft.oracle.Oracle;

public class main_kasi_people_url_renhezuopin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();

	}
	
private static void runstatic() {
		
//		kasi_people_url_baidu baidu=new kasi_people_url_baidu();
//		kasi_people_url_sougou sogou=new kasi_people_url_sougou();
//		kasi_people_url_douban douban= new kasi_people_url_douban();
		List<String> listArray = Oracle.selec_mart_dim_person_TEM_PERSON_URL();
		String id ="";
		String name ="";
		String url ="";
		
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
				System.out.println(id=listTemp.get(0));
				System.out.println(name=listTemp.get(1));
				System.out.println(url=listTemp.get(2));
//				BaiDuTeleplayDownload.selec_mart_dim_person(listTemp.get(0), listTemp.get(2));
				try {
					
//					baidu.shoushuo(id, name, zhuopin);
					kasi_people_url_baidu.mainmore(id, name, url);
					
//					sogou.shoushuo(id, name, zhuopin);
					
					
					
//					douban.shoushuo(id, name, zhuopin);
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
