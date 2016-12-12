package com.artsoft.downloadThreadpool.people.DanChiPeoPle.sousuozhishu;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;

public class haoSou_people_thread implements Runnable{
	
	
	
	private String name;

	public haoSou_people_thread(String name) {
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
			DanChiPeoPle.TimingTime(9, 00, 01);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
	}
	
	

}
