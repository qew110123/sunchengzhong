package com.artsoft.admin;

import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadHunantv_DanJiBoFangLiang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;


public class Admin {
	
	public static void main(String[] args) {
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		/**
		 * 百度
		 */
		new Thread(new Thread_Main("newsnumthread")).start();
		
		/**
		 * 电影
		 */
		new Thread(new Thread_Main("DownYoukuMovie")).start();
		new Thread(new Thread_Main("DownIqiyiMovie")).start();
		new Thread(new Thread_Main("DownqqMovie")).start();
		new Thread(new Thread_Main("DownSohuMovie")).start();
		new Thread(new Thread_Main("DownLetvMovie")).start();
		new Thread(new Thread_Main("DownpptvMovie")).start();
		new Thread(new Thread_Main("DownkankanMovie")).start();
		new Thread(new Thread_Main("DownDoubanMovie")).start();
//		new Thread(new Thread_Main("DownqqMovie")).start();
//		new Thread(new Thread_Main("DownqqMovie")).start();
//		new Thread(new Thread_Main("DownqqMovie")).start();
		
		/**
		 * 网络剧 
		 * 每集播放量
		 * 2016年6月28日17:39:25
		 */
		
		new Thread(new Thread_Main("DownloadHunantv_DanJiBoFangLiang")).start();
		new Thread(new Thread_Main("DownloadSohu_DanJiBoFangLiang")).start();
		new Thread(new Thread_Main("DownloadYouku_always_DanJiBoFangLiang")).start();
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
		
	}

}
