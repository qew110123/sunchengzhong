package com.artsoft.download.TVPlay.Downloadpool;

import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.pool.TaskInterface;

public class DownloadYoukupool implements TaskInterface {
	String strmainurl;
//	String 
	
	public DownloadYoukupool(String strmainurl) {
		// TODO Auto-generated constructor stub
		this.strmainurl=strmainurl;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
//		DownloadYouku.youkuBranch(strmainurl);
		DownloadYouku.youkuBranch(strmainurl);
		
	}
	
	
	
	

}
