package com.artsoft.downloadThreadpool.tvplay;

import com.artsoft.download.news_num.baidu_newsnum;
import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;

public class haoSou_thread_tvtype implements Runnable{
	
	
	
	private String name;

	public haoSou_thread_tvtype(String name) {
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
			HaoSouTV_dianshiju.TimingTime(9, 00, 01);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
		
		if (name.equals("ip")) {
			
			HaoSouWordAdmin.TimingTimeip(1, 00, 01);
//			baidu_newsnum.TimingTime(1, 59, 59);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
	}
	
	

}
