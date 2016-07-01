package com.artsoft.admin;

import com.artsoft.download.Movies.DownDoubanMovie;
import com.artsoft.download.Movies.DownIqiyiMovie;
import com.artsoft.download.Movies.DownLetvMovie;
import com.artsoft.download.Movies.DownSohuMovie;
import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.Movies.DownkankanMovie;
import com.artsoft.download.Movies.DownpptvMovie;
import com.artsoft.download.Movies.DownqqMovie;
import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadHunantv_DanJiBoFangLiang;
import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadSohu_DanJiBoFangLiang;
import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadYouku_always_DanJiBoFangLiang;
import com.artsoft.download.news_num.baidu_newsnum;

public class Thread_Main implements Runnable {
	private String name;

	public Thread_Main(String name) {
		this.name=name;
	}

	@Override
	public void run() {
		
		/**
		 * 百度搜索量
		 */
		
		if (name.equals("newsnumthread")) {
			baidu_newsnum.TimingTime(1, 59, 59);
//			  DownYoukuMovie.TimingTime(01, 01, 01);
		}
		
		  /**
		   * 2016年6月22日10:48:25
		   * 电影每集播放量
		   */
		  if (name.equals("DownYoukuMovie")) {
			  DownYoukuMovie.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownIqiyiMovie")) {
			  DownIqiyiMovie.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownqqMovie")) {
			  DownqqMovie.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownSohuMovie")) {
			  DownSohuMovie.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownLetvMovie")) {
			  DownLetvMovie.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownpptvMovie")) {
			  DownpptvMovie.TimingTime(01, 01, 01);
		}
		  
		  if (name.equals("DownkankanMovie")) {
			  DownkankanMovie.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownDoubanMovie")) {
			  DownDoubanMovie.TimingTime(01, 01, 01);
		}
//		  if (name.equals("DownqqMovie")) {
//			  DownqqMovie.TimingTime(01, 01, 01);
//		}
		  
		  if (name.equals("DownloadHunantv_DanJiBoFangLiang")) {
			  DownloadHunantv_DanJiBoFangLiang.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownloadSohu_DanJiBoFangLiang")) {
			  DownloadSohu_DanJiBoFangLiang.TimingTime(01, 01, 01);
		}
		  if (name.equals("DownloadYouku_always_DanJiBoFangLiang")) {
			  DownloadYouku_always_DanJiBoFangLiang.TimingTime(01, 01, 01);
		}
		  
		
	}
	
}
 
