package com.artsoft.download.webo.weiboSearch;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;

public class WeiBo {

	/**
	 * 0,火狐；1，
	 * 
	 * @param i
	 */
	static int i = 0;

	static WebDriver webDriver;

	public static void WebDriverBranch() {
		if (webDriver == null) {

			// WebDriver webDriver;
			System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
			File file = new File(
					"C:\\Program Files (x86)\\Google\\Chrome\\Application\\39.0.2171.95\\default_apps\\youtube.crx");
			ChromeOptions options = new ChromeOptions();
			// options.addExtensions(file);
			
//			Cookie cookie = new Cookie("gsid", "4u9x26191nWvvJwthqgaobLTkeU");
//
//			((WebDriver) options).manage().addCookie(cookie);
			
			
			options.addArguments("–user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default");
			// C:\Users\Administrator\AppData\Local\Google\Chrome\User Data
			webDriver = new ChromeDriver(options);

			// return webDriver;
		} else {
			System.out.println(webDriver);

		}

	}

	public static Map WeiBoBranch(String url) {
		Map mapreturn = new HashMap();
		// if (i>=10) {
		//
		// WebDriver webDriver;
		// System.setProperty("webdriver.chrome.driver",
		// "D:\\chrome\\chromedriver.exe");
		// File file = new File ("C:\\Program Files
		// (x86)\\Google\\Chrome\\Application\\39.0.2171.95\\default_apps\\youtube.crx");
		// ChromeOptions options = new ChromeOptions();
		// // options.addExtensions(file);
		// options.addArguments("–user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User
		// Data/Default");
		// //C:\Users\Administrator\AppData\Local\Google\Chrome\User Data
		// webDriver = new ChromeDriver(options);
		// }

		WebDriverBranch();
		
		System.out.println("等待30秒");
		seleepTime(30);

		// ChromeOptions options = new ChromeOptions();
		// options.addArguments(“–user-data-dir=C:/Users/xxx/AppData/Local/Google/Chrome/User
		// Data/Default”);
		// WebDriver driver = new ChromeDriver(options);

		webDriver.get(url);
		// 获取标题元素值
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			String title = webDriver.getTitle();
			System.out.println("标题: " + title);
			// 获取淘宝价格
			WebElement detail = webDriver.findElement(By.xpath("//*[@id=\"pl_weibo_directtop\"]"));
			System.out.println(detail.getText());
			WebElement atr = detail.findElement(By.xpath("div"));
			WebElement atr1 = atr.findElement(By.xpath("div"));
			WebElement atr2 = atr1.findElement(By.xpath("div"));
			WebElement atr3 = atr2.findElement(By.xpath("div"));
			WebElement atr4 = atr3.findElement(By.xpath("a"));
			String urlBranch = "";
			System.out.println(atr4);
			System.out.println(urlBranch = atr4.getAttribute("href"));
			WebElement atrpstarnum;
			System.out.println(atr2);
			System.out.println(atrpstarnum = atr2.findElement(By.xpath(("div[@class='star_detail']"))));
			WebElement atrpstarnum1;
			System.out.println(atrpstarnum1 = atrpstarnum.findElement(By.xpath(("p[@class='star_num']"))));
			List<WebElement> liList;
			System.out.println(liList = atrpstarnum1.findElements(By.xpath(("span"))));
			int i1 = 0;
			// String =

			String strfansCount = "";
			String strvCount = "";
			int fansCount = 0;
			int vCount = 0;
			for (WebElement webElement : liList) {
				WebElement atrpstarnumaa;
				System.out.println(atrpstarnumaa = webElement.findElement(By.xpath(("a"))));
				System.out.println(atrpstarnumaa.getText());
				if (i1 == 1) {
					strfansCount = atrpstarnumaa.getText().replaceAll("万", "0000");
				}
				if (i1 == 2) {
					strvCount = atrpstarnumaa.getText().replaceAll("万", "0000");
				}
				i1 += 1;
			}

			if (strfansCount != null && !"".equals(strfansCount) && strvCount != null && !"".equals(strvCount)) {
				fansCount = Integer.parseInt(strfansCount);
				vCount = Integer.parseInt(strvCount);
				// OracleHaoSou.intoPeoPlewebo(tvplayId, strhtmlurl, fansCount,
				// vCount, "", "", mainUrl, "1");
				mapreturn.put("urlBranch", urlBranch);
				// mapreturn.put("fansCount", fansCount);
				mapreturn.put("fansCount", fansCount);
				mapreturn.put("vCount", vCount);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			// 退出
			// if (i>10) {
			//// webDriver.quit();
			//// i=0;
			// }
			System.out.println(i);
			i += 1;

			seleepTime(7);
		}

		return mapreturn;
	}

	private static void mainweboPeoPle(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);

		for (Object Objstring : listArray) {

			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode(listTemp.get(1), "utf-8")
							+ "&Refer=STopic_box";

					hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}
	}
	
	
	private static void mainweboPeoPleMeiRi(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectnameMeiRI(Integer.toString(statnum), Integer.toString(endnum));
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);

		for (Object Objstring : listArray) {

			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode(listTemp.get(1), "utf-8")
							+ "&Refer=STopic_box";

					hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		}
	}

	public static void seleepTime(int t) {
		t = (int) (t * Math.random());
		t = t + 10;
		// t = 2;
		try {
			System.out.println("当前等待" + t + "秒");
			// System.out.println("等待2秒,等待" + t + "秒");
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void hunanBranch(String mainUrl, String tvplayId, String tyPlayName, String DataType) {
		// TODO Auto-generated method stub
		// new WeiBo(1, mainUrl);

		try {
			Map mapmore = WeiBoBranch(mainUrl);
			String strhtmlurl = "";

			strhtmlurl = mapmore.get("urlBranch").toString();
			String strfansCount = mapmore.get("fansCount") + "";
			String strvCount = mapmore.get("vCount") + "";
			int fansCount = 0;
			int vCount = 0;
			if (strfansCount != null && !"".equals(strfansCount) && strvCount != null && !"".equals(strvCount)) {
				fansCount = Integer.parseInt(strfansCount);
				vCount = Integer.parseInt(strvCount);
				System.out.println(tvplayId + strhtmlurl + fansCount + vCount + mainUrl + "1");

				OracleHaoSou.intoPeoPlewebo(tvplayId, strhtmlurl, fansCount, vCount, "", "", mainUrl, "1");

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("没有该人的数据");
			seleepTime(10);
		}
		// Integer.parseInt( );
		// Integer.parseInt( )
		// if (condition) {
		//
		// OracleHaoSou.intoPeoPlewebo(tvplayId, , , , "", "", mainUrl, "1");
		// }

	}
	
	
	public static void runMeiRirun(){
		
		mainweboPeoPleMeiRi(0,  1000);
		
	}
	
	
	/*
	 * 测试时间 2016年8月17日17:30:00
	 * 
	 * @param args
	 */
	public static void rundingshitime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				try {

//					runstaticshijian();
					runMeiRirun();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new
		// WeiBo(1,"http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");
//		 hunanBranch("http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index", null, null, null);
//		ConfigManager config = ConfigManager.getInstance();
//		// driver = config.getConfigValue("driver");
//		String xx = ConfigManager.getInstance().getConfigValue("IDwebopeople");
//		int xxnum = Integer.parseInt(xx);
//		for (int i = xxnum; i < 16871; i = i + 1000) {
//			// i=15780;
//			mainweboPeoPle(i, i + 1000);
//		}
		
		
//		runMeiRirun();
		rundingshitime(1, 00, 00);
	}
}
