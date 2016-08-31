package com.artsoft.downloadThreadpool.people;

import com.artsoft.download.news_num.baidu_newsnum;

public class haoSou_thread_admin implements Runnable{
	
	
	
	private String name;

	public haoSou_thread_admin(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/**
		 * °Ù¶ÈËÑË÷Á¿
		 */

		if (name.equals("adminSou")) {
//			baidu_newsnum.TimingTime(1, 59, 59);
			HaoSouWordAdmin.TimingTime(9, 00, 01);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
		
		if (name.equals("ip")) {
			
			HaoSouWordAdmin.TimingTimeip(1, 00, 01);
//			baidu_newsnum.TimingTime(1, 59, 59);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
	}
	
	

}
