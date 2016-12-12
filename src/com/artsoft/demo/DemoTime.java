package com.artsoft.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.cglib.proxy.Proxy;

public class DemoTime {

	public static void iQiYiBranch(String urlBranch) {
		
		java.net.Proxy proxy = DealProxy.getInstance().getPoxxy();
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		System.out.println(strHtml);

		String strtext = HtmlAnalyze.getTagText(strHtml, "\":\"", "\"}");

		System.out.println(strtext);

		String[] sourceStrArray = strtext.toString().split("\\|");
		System.out.println(sourceStrArray.length);
		for (int i = 0; i < sourceStrArray.length; i++) {
			System.out.println(sourceStrArray[i]);
		}

	}

	/**
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param day
	 *            相对天数，为正数表示之后，为负数表示之前
	 * @return 指定日期字符串n天之前或者之后的日期
	 */
	public static java.sql.Date getBeforeAfterDate(String datestr, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date olddate = null;
		try {
			df.setLenient(false);
			olddate = new java.sql.Date(df.parse(datestr).getTime());
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误");
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewDay = Day + day;

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);

		return new java.sql.Date(cal.getTimeInMillis());
	}

	public static void main(String[] args) {
		System.out.println("获取两个日期之间间隔天数2008-12-1~2008-9.29:" + TimeTest.getTwoDay("2008-12-1", "2008-9-29"));

		System.out.println("获取两个日期之间间隔天数2013-01-11~2015-10-27:" + TimeTest.getTwoDay("2013-01-11", "2015-10-27"));

		// java.util.Calendar rightNow = java.util.Calendar.getInstance();
		// java.text.SimpleDateFormat sim = new
		// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// // 得到当前时间，+3天
		// rightNow.add(java.util.Calendar.DAY_OF_MONTH, 3);
		// // 如果是后退几天，就写 -天数 例如：
		// rightNow.add(java.util.Calendar.DAY_OF_MONTH, -3);
		// // 进行时间转换
		// String date = sim.format(rightNow.getTime());
		// System.out.println(date);

		System.out.println("指定日期字符串n天之前或者之后的日期  015-10-27:" + DemoTime.getBeforeAfterDate("2013-01-11", 1019));
//
//		iQiYiBranch(
//				"http://index.haosou.com/index.php?a=soIndexJson&q=%E8%B5%B5%E4%B8%BD%E9%A2%96&area=%E5%85%A8%E5%9B%BD");
//		iQiYiBranch(
//				"http://index.haosou.com/index.php?a=soMediaJson&q=%E8%B5%B5%E4%B8%BD%E9%A2%96");
		while (true) {
			iQiYiBranch(
					"http://t.cn/RVdBdaz");
			
		}
		
		

	}

}
