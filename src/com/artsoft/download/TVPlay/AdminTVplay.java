package com.artsoft.download.TVPlay;

import com.artsoft.download.Movies.DownDoubanChaXunNetword;
import com.artsoft.download.Movies.DownDoubanNetword;
import com.artsoft.download.Movies.DownIqiyiMovie;
import com.artsoft.download.Movies.DownSohuNwtword;
import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.Movies.DownkankanNetwork;
import com.artsoft.download.Movies.DownqqMovie;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class AdminTVplay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":¿ª Ê¼");
		DownloadYouku2.runstatic();
		DownloadIqiyi.runstatic();
		Downloadqq.runstatic();
		DownloadSohu.runstatic();
		DownloadLetv.runstatic();
		Downloadpptv.runstatic();
		DownloadHunantv.runstatic();
//		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		Downloadkankan.runstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":½á Êø");

	}

}
