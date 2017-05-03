package com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.shouhuyule.shouhuyule_1;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.tengxunyule.tengxunyule_1;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.tengxunyule.tengxunyule_2;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.tengxunyule.tengxunyule_3;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.tengxunyule.tengxunyule_4;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.tengxunyule.tengxunyule_5;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.wangyiyule.wangyiyule_1;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.wangyiyule.wangyiyule_2;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.wangyiyule.wangyiyule_3;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.xinlangyule.xinlangyule_1;
import com.artsoft.oracle2.DBManager;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class news_admin {
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		try {
			jinritoutiao.runstaticshijian();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			baidubaijia.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			tengxunyule_1.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			tengxunyule_2.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			tengxunyule_3.runnewMain("http://ent.qq.com/tv/tv_2012/sjynd.htm",5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			tengxunyule_3.runnewMain("http://ent.qq.com/tv/tv_2012/zyxwsj.htm",5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			tengxunyule_4.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			tengxunyule_5.runnewMain("http://ent.qq.com/tv/",2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			tengxunyule_5.runnewMain("http://ent.qq.com/movie/",3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			tengxunyule_5.runnewMain("http://ent.qq.com/star/",1);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wangyiyule_1.runnewMain();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			wangyiyule_2.runnewMain("http://ent.163.com/special/ysl/",0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			wangyiyule_2.runnewMain("http://ent.163.com/special/00032VQS/zongyijiemu.html",5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//http://ent.163.com/tv/
		try {
			wangyiyule_3.runnewMain("http://ent.163.com/special/000381P3/newsdata_tv_index.js?callback=data_callback",2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//http://ent.163.com/movie/
		try {
			wangyiyule_3.runnewMain("http://ent.163.com/special/000381Q1/newsdata_movieidx.js?callback=data_callback",3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			wangyiyule_3.runnewMain("http://ent.163.com/star/",1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			shouhuyule_1.runnewMain("http://yule.sohu.com/tv.shtml",2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			shouhuyule_1.runnewMain("http://yule.sohu.com/movie.shtml",3);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		try {
//			shouhuyule_1.runnewMain("http://yule.sohu.com/movie.shtml");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		try {
			shouhuyule_1.runnewMain("http://yule.sohu.com/stars_news.shtml",1);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		try {
//			shouhuyule_1.runnewMain("http://yule.sohu.com/movie.shtml");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		//http://ent.sina.com.cn/tv/
		try {
			xinlangyule_1.runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=105&lid=1230&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480487757606",2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//http://ent.sina.com.cn/film/
		try {
			xinlangyule_1.runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=51&lid=740&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480491409744",3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//http://ent.sina.com.cn/zongyi/
		try {
			xinlangyule_1.runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=37&lid=531&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480491730297",5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//http://ent.sina.com.cn/star/
		try {
			xinlangyule_1.runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=107&lid=1244&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480491886807",1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		System.out.println("调用存储过程");
		
		
		
		
		DBManager dbm = DBManager.instance();
//		dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
		
		dbm.executeCall("call sp_dim_news('"+TimeTest.getNowTime("yyyyMMdd")+"') ");
		
		System.out.println("运行sp_dim_news完毕");
		
		
		
		dbm.executeCall("call sp_f_movies_news('"+TimeTest.getNowTime("yyyyMMdd")+"') ");
		
		System.out.println("运行sp_f_movies_news完毕");
		
		
		try {
			//等5分钟
			System.out.println("等5分钟");
			Thread.sleep(1000*60*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbm.executeCall("call mart_news('"+TimeTest.getNowTime("yyyyMMdd")+"') ");
		
		System.out.println("运行mart_news 可以 完毕");
		
		// DownloadHunantv.runstatic();

		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(04, 00, 00);
		// runstatic();
	}

}
