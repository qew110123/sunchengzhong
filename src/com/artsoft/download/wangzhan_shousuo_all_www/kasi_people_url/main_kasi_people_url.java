package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_url;

import java.util.List;

import com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_tvplay_move_url.kasi_people_url_douban;
import com.artsoft.oracle.Oracle;

public class main_kasi_people_url {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runstatic();

	}

	private static void runstatic() {
		
		kasi_people_url_baidu baidu=new kasi_people_url_baidu();
		kasi_people_url_sougou sogou=new kasi_people_url_sougou();
		kasi_people_url_douban douban= new kasi_people_url_douban();
		
		kasi_people_url_360 baidu360=new kasi_people_url_360();
		List<String> listArray = Oracle.selec_mart_dim_person();
		String id ="";
		String name ="";
		String zhuopin ="";
		
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
				System.out.println(id=listTemp.get(0));
				System.out.println(name=listTemp.get(1));
				System.out.println(zhuopin=listTemp.get(2));
//				BaiDuTeleplayDownload.selec_mart_dim_person(listTemp.get(0), listTemp.get(2));
				try {
					
//					baidu.shoushuo(id, name, zhuopin);
					
					
					sogou.shoushuo(id, name, zhuopin);
					
					
					
//					douban.shoushuo(id, name, zhuopin);
					
					
					
//					baidu360.shoushuo(id, name, zhuopin);
					
				} catch (Exception e) {
					// TODO: handle exception
					seleepTime(4);
					
					
				}
			}
		}
	}
	
	
	public static void seleepTime(int t) {
		t = (int) (t * Math.random());
		t = t + 5;
		// t = 2;
		try {
			System.out.println("当前等待" + t + "秒");
			// System.out.println("等待2秒,等待" + t + "秒");
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
