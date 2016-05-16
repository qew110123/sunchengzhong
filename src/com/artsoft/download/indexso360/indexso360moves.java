package com.artsoft.download.indexso360;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class indexso360moves {
	
	static ThreadPool pool = new ThreadPool(30);
	
	public static void mainProgram(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectdim_film(Integer.toString(statnum), Integer.toString(endnum));
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				// if (Integer.parseInt(listTemp.get(0))>2961) {
				String person_id = listTemp.get(0);
				String keyword = listTemp.get(1);
				String data_date = TimeTest.getNowTime("yyyyMMdd");
				String krywordutf8 = "";
				try {
					krywordutf8 = java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				while (pool.getPoolNum() > 30) {
					try {
						System.out.println("线程数量大于30，等待5s");
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("当前启动线程thread:" + pool.getPoolNum());
				pool.performTask(new index360pool(person_id, data_date, keyword, krywordutf8,3));

				// }

			}
		}
	}
	
	
	public static void openstatic() {
		

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		IpFilter.mainip("http://index.haosou.com/");
		CommonUtil.setLog("ip代理时间" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		String returnNumTVle=OracleHaoSou.returnNumPeople("ods.dim_film");
		System.out.println("需要采集的人名字数为"+returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
//			int TV_TYPE=0;
			mainProgram(i, i + 1000);
		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");

	}
	
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}
	
	
	// 判断数据开始时间
		public static void TimingTime(int hh, int mm, int ss) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
			calendar.set(Calendar.MINUTE, mm); // 控制分
			calendar.set(Calendar.SECOND, ss); // 控制秒

			Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					System.out.println("-------设定要指定任务--------");
					runstatic();
				}
			}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// runstatic();
		// openstatic();
		TimingTime(00, 59, 59);
	}

}
