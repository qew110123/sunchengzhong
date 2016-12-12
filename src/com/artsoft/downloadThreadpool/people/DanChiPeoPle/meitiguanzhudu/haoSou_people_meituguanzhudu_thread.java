package com.artsoft.downloadThreadpool.people.DanChiPeoPle.meitiguanzhudu;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;

public class haoSou_people_meituguanzhudu_thread implements Runnable{
	
	
	
	private String name;

	public haoSou_people_meituguanzhudu_thread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/**
		 * 
		 */
		if (name.equals("adminSou")) {
//			baidu_newsnum.TimingTime(1, 59, 59);
			meitiguanzhudu.TimingTime(9, 00, 01);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
	}
	
	

}
