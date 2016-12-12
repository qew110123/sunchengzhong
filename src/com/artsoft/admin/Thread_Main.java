package com.artsoft.admin;

import com.artsoft.download.Movies.DownDoubanMovie;
import com.artsoft.download.Movies.DownIqiyiMovie;
import com.artsoft.download.Movies.DownLetvMovie;
import com.artsoft.download.Movies.DownSohuMovie;
import com.artsoft.download.Movies.DownYoukuMovie;
import com.artsoft.download.Movies.DownkankanMovie;
import com.artsoft.download.Movies.DownpptvMovie;
import com.artsoft.download.Movies.DownqqMovie;
import com.artsoft.download.TVPlay.TVplayAdmin;
import com.artsoft.download.TVPlay.platform.Adminplatform;
import com.artsoft.download.downNetwork.DownAdmin;
import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadHunantv_DanJiBoFangLiang;
import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadSohu_DanJiBoFangLiang;
import com.artsoft.download.downNetwork.DanJiBoFangLiang.DownloadYouku_always_DanJiBoFangLiang;
import com.artsoft.download.maoyanandpiaofang.maoyan.adminmaoyan;
import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_jibenxinxi_zhongpiaofang;
import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_paipian;
import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_runother;
import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_shishipiaofang;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhonggoupiaofangwang_shisipiaofangwang;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_danri;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_yingyuandanripiaofang;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopiaofangwang_zhongpiaofang;
import com.artsoft.download.maoyanandpiaofang.zhongguopiaofangwang.zhongguopioafangwang_diqu;
import com.artsoft.download.news_num.baidu_newsnum;

public class Thread_Main implements Runnable {
	private String name;

	public Thread_Main(String name) {
		this.name = name;
	}

	@Override
	public void run() {

		/**
		 * 百度搜索量
		 */

		if (name.equals("newsnumthread")) {
			baidu_newsnum.TimingTime(1, 59, 59);
			// DownYoukuMovie.TimingTime(01, 01, 01);
		}

		/**
		 * 电视剧播放量
		 */

		if (name.equals("TVplayAdmin")) {
			TVplayAdmin.TimingTime(01, 01, 01);
		}

		/**
		 * 电视剧每集播放量
		 */

		if (name.equals("Adminplatform")) {
			Adminplatform.TimingTime(01, 01, 01);
		}

		/**
		 * 2016年6月22日10:48:25 电影每集播放量
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
		// if (name.equals("DownqqMovie")) {
		// DownqqMovie.TimingTime(01, 01, 01);
		// }

		if (name.equals("DownloadHunantv_DanJiBoFangLiang")) {
			DownloadHunantv_DanJiBoFangLiang.TimingTime(01, 01, 01);
		}
		if (name.equals("DownloadSohu_DanJiBoFangLiang")) {
			DownloadSohu_DanJiBoFangLiang.TimingTime(01, 01, 01);
		}
		if (name.equals("DownloadYouku_always_DanJiBoFangLiang")) {
			DownloadYouku_always_DanJiBoFangLiang.TimingTime(01, 01, 01);
		}
		
		/**
		 * 猫眼adminmaoyan
		 */
		
		if (name.equals("adminmaoyan")) {
			adminmaoyan.TimingTime(11, 59, 59);
		}
		
		if (name.equals("maoyan_paipian")) {
			maoyan_paipian.TimingTime(1, 59, 59);
		}
		
		if (name.equals("maoyan_runother")) {
			maoyan_runother.TimingTime(1, 59, 59);
		}
		
		if (name.equals("maoyan_jibenxinxi_zhongpiaofang")) {
			maoyan_jibenxinxi_zhongpiaofang.TimingTime(10, 59, 59);
		}
		
		if (name.equals("maoyan_shishipiaofang")) {
			maoyan_shishipiaofang.TimingTime(01, 00, 59);
		}
		
		/**
		 * 网络剧
		 * DownAdmin
		 */
		if (name.equals("DownAdmin")) {
			DownAdmin.main(null);
		}
		
		
		
		/**
		 * 中国票房网
		 */
		if (name.equals("zhonggoupiaofangwang_shisipiaofangwang")) {
			zhonggoupiaofangwang_shisipiaofangwang.TimingTime(0, 59, 59);
		}
		
		if (name.equals("zhongguopiaofangwang_danri")) {
			zhongguopiaofangwang_danri.TimingTime(11, 59, 59);
		}
		
		if (name.equals("zhongguopiaofangwang_yingyuandanripiaofang")) {
			zhongguopiaofangwang_yingyuandanripiaofang.TimingTime(0, 59, 59);
		}
		
		if (name.equals("zhongguopiaofangwang_zhongpiaofang")) {
			zhongguopiaofangwang_zhongpiaofang.TimingTime(0, 59, 59);
		}
		
		if (name.equals("zhongguopiaofangwang")) {
			zhongguopiaofangwang.TimingTime(0, 59, 59);
		}
		
		if (name.equals("zhongguopioafangwang_diqu")) {
			zhongguopioafangwang_diqu.TimingTime(6, 59, 59);
		}
		
		

	}

}
