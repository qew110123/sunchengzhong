package com.artsoft.download.TVPlay.admin_thread;

import com.artsoft.download.TVPlay.DownloadIqiyi;
import com.artsoft.download.TVPlay.DownloadLetv;
import com.artsoft.download.TVPlay.DownloadSohu;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.download.TVPlay.Downloadkankan;
import com.artsoft.download.TVPlay.Downloadpptv;
import com.artsoft.download.TVPlay.Downloadqq;
import com.artsoft.download.news_num.baidu_newsnum;

public class Tvplay_thread_admin implements Runnable{
	
	
	
	private String name;

	public Tvplay_thread_admin(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/**
		 * tvplay
		 */

		if (name.equals("DownloadYouku")) {
			DownloadYouku.runstatic();
		}
		
		
		if (name.equals("DownloadIqiyi")) {
			DownloadIqiyi.runstatic();
		}
		
		
		if (name.equals("Downloadqq")) {
			
			Downloadqq.runstatic();
		}
		if (name.equals("DownloadSohu")) {
			DownloadSohu.runstatic();
		}
		
		
		if (name.equals("DownloadLetv")) {
			DownloadLetv.runstatic();
		}
		
		
		if (name.equals("Downloadkankan")) {
			Downloadkankan.runstatic();
		}
		
		
		if (name.equals("Downloadpptv")) {
			Downloadpptv.runstatic();
		}
		
		
	}
	
	

}
