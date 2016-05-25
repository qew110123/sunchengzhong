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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.artsoft.bean.TEM_WEIBO_TOPIC_SCORE;
import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class WeiBoSearchTVplay {

	/**
	 * 0,火狐；1，
	 * 
	 * @param i
	 */
	static int i = 0;

	static WebDriver webDriver= null;
	
	public static void webDrivernull() {
		webDriver= null;
		
	}
	

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
	
	public static void WeiBoBranch1(String urlBranchurl,String DATA_ID,int DATA_TYPE){
		webDriver.get(urlBranchurl);
		
		System.out.println( webDriver.getTitle());
		
		WebElement elementS_line1 = webDriver.findElement(By.className("tb_counter"));
		WebElement elementtbody1 = elementS_line1.findElement(By.xpath("tbody"));
		WebElement elementtbody2 = elementtbody1.findElement(By.xpath("tr"));
		List<WebElement> liList;
		liList= elementtbody2.findElements(By.xpath("td[@class='S_line1']"));
		int webElementintnum=0;
		
		TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score=new TEM_WEIBO_TOPIC_SCORE();
		tem_weibo_topic_score.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
		tem_weibo_topic_score.setDataId(Integer.parseInt(DATA_ID));
		tem_weibo_topic_score.setDataUrl(urlBranchurl);
		tem_weibo_topic_score.setBigType(1);
		tem_weibo_topic_score.setDataType(DATA_TYPE);
		for (WebElement webElement : liList) {
			if (webElementintnum%3==0) {
				
				System.out.println(webElement);
				//div[@class='pl_gspage_r']
				System.out.println(webElement.getText());
				WebElement webElementtbody = webElement.findElement(By.xpath("strong"));
				System.out.println(webElementtbody.getText());
				int ReadingNum=Stringnum(webElementtbody.getText());
				tem_weibo_topic_score.setReadingNum(ReadingNum);
				
			}
			if (webElementintnum%3==1) {
				
				System.out.println(webElement);
				//div[@class='pl_gspage_r']
				System.out.println(webElement.getText());
				WebElement webElementtbody = webElement.findElement(By.xpath("strong"));
				System.out.println(webElementtbody.getText());
				int ReadingNum=Stringnum(webElementtbody.getText());
				tem_weibo_topic_score.setDiscussNum(ReadingNum);
			}
			if (webElementintnum%3==2) {
				
				System.out.println(webElement);
				//div[@class='pl_gspage_r']
				System.out.println(webElement.getText());
				WebElement webElementtbody = webElement.findElement(By.xpath("a/strong"));
				System.out.println(webElementtbody.getText());
				int ReadingNum=Stringnum(webElementtbody.getText());
				tem_weibo_topic_score.setFanNum(ReadingNum);
			}
			
			webElementintnum+=1;
		}
		
		Oracle.Inserttem_weibo_topic_scorePart(tem_weibo_topic_score);
	}
	
	static int shuaxin=0;
	public static  void shuaxin(){
		if (shuaxin%10==0) {
			webDriver.navigate().refresh();
			seleepTime(7);
		}
		
		
		shuaxin+=1;
	}
	
	
	
	public static void WeiBoBranch2(String urlBranchmoves,String DATA_ID,int DATA_TYPE){
		TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score1=new TEM_WEIBO_TOPIC_SCORE();
		tem_weibo_topic_score1.setDataDate(TimeTest.getNowTime("yyyyMMdd"));
		tem_weibo_topic_score1.setDataId(Integer.parseInt(DATA_ID));
		tem_weibo_topic_score1.setDataUrl(urlBranchmoves);
		tem_weibo_topic_score1.setBigType(2);
		tem_weibo_topic_score1.setDataType(DATA_TYPE);
		
		System.out.println(urlBranchmoves );
		webDriver.get(urlBranchmoves);
		
		System.out.println( webDriver.getTitle());
		WebElement urlBranchmoves1 = webDriver.findElement(By.className("tb_counter"));
		WebElement urlBranchmoves2 = urlBranchmoves1.findElement(By.xpath("tbody"));
		WebElement urlBranchmoves3 = urlBranchmoves2.findElement(By.xpath("tr"));
		List<WebElement> liList;
		liList= urlBranchmoves3.findElements(By.xpath("td[@class='S_line1']"));
		int urlBranchmovesintnum=0;
		for (WebElement webElement : liList) {
			if (urlBranchmovesintnum%3==0) {
				
				System.out.println(webElement);
				//div[@class='pl_gspage_r']
				System.out.println(webElement.getText());
				WebElement webElementtbody = webElement.findElement(By.xpath("strong"));
				System.out.println(webElementtbody.getText());
				
				double ReadingNum=Double.parseDouble(webElementtbody.getText());
				tem_weibo_topic_score1.setScore(ReadingNum);
			}
			if (urlBranchmovesintnum%3==1) {
				
				System.out.println(webElement);
				//div[@class='pl_gspage_r']
				System.out.println(webElement.getText());
				WebElement webElementtbody = webElement.findElement(By.xpath("strong"));
				System.out.println(webElementtbody.getText());
				int ReadingNum=Stringnum(webElementtbody.getText());
				tem_weibo_topic_score1.setFollowNum(ReadingNum);
			}
			if (urlBranchmovesintnum%3==2) {
				
				System.out.println(webElement);
				//div[@class='pl_gspage_r']
				System.out.println(webElement.getText());
				WebElement webElementtbody = webElement.findElement(By.xpath("strong"));
				System.out.println(webElementtbody.getText());
				
				int ReadingNum=Stringnum(webElementtbody.getText());
				tem_weibo_topic_score1.setReadingNum(ReadingNum);
			}
			
			urlBranchmovesintnum+=1;
		}
		
		Oracle.Inserttem_weibo_topic_scorePartBIG_TYPE2(tem_weibo_topic_score1);
	}
	/**
	 * 
	 * @param url
	 * @param DATA_ID 数据id
	 * @param DATA_TYPE类型  2 电视剧 3 电影
	 * @return
	 */
	public static Map WeiBoBranch(String url,String DATA_ID,int DATA_TYPE) {
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

		// ChromeOptions options = new ChromeOptions();
		// options.addArguments(“–user-data-dir=C:/Users/xxx/AppData/Local/Google/Chrome/User
		// Data/Default”);
		// WebDriver driver = new ChromeDriver(options);
		try {
			webDriver.get(url);
			// 获取标题元素值
			webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
			String title = webDriver.getTitle();
			System.out.println("标题: " + title);
			// 获取淘宝价格
			WebElement detail = webDriver.findElement(By.xpath("//*[@id=\"pl_weibo_directright\"]"));
//			System.out.println(detail.getText());
			WebElement atr = detail.findElement(By.xpath("div[@class='WB_cardwrap S_bg2 wbs_interest_wrap']"));
			WebElement atr1 = atr.findElement(By.xpath("div[@class='pl_gspage_r']"));
			WebElement atr2 = atr1.findElement(By.xpath("div[@class='card_scroll']"));
			WebElement atr3 = atr2.findElement(By.xpath("div[@class='wbs_relevant_interest']"));
			WebElement atr5 = atr3.findElement(By.xpath("div[@class='content_topic']"));
			WebElement atr4 = atr5.findElement(By.xpath("a"));
			String urlBranchurl = "";
//			System.out.println(atr4);
			System.out.println(urlBranchurl = atr4.getAttribute("href"));
			
			String urlBranchmoves = "";
			
				
			
			WebElement detailmoves=webDriver.findElement(By.xpath("//*[@id='pl_weibo_directright']/div[@class='WB_cardwrap S_bg2 wbs_interest_wrap']/div[@class='pl_gspage_r']/div[@class='card_scroll']/div[@class='wbs_relevant_interest']/div[@class='content_film']/a"));
			System.out.println(urlBranchmoves = detailmoves.getAttribute("href"));
			//获取cook
			// And now output all the available cookies for the current URL
//			Set<Cookie> allCookies = webDriver.manage().getCookies();
//			for (Cookie loadedCookie : allCookies) {
//				System.out.println(loadedCookie.getName());
//				System.out.println(loadedCookie.getValue());
//			   System.out.println(String.format("%s -> %s",loadedCookie.getName(), loadedCookie.getValue()));
//			}
			//截图
//			webDriver.save_screenshot("C:\\error.jpg");
			
			
			//driver = webdriver.Firefox()
//			driver.save_screenshot("C:\error.jpg")
			
			System.out.println(urlBranchurl );
			seleepTime(7);
			try {
				
				WeiBoBranch1(urlBranchurl, DATA_ID, DATA_TYPE);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			seleepTime(7);
			try {
				
				WeiBoBranch2(urlBranchmoves, DATA_ID, DATA_TYPE);
				shuaxin();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			
			
			
//			System.out.println(atrW_f14.getText());
			
			
//			WebElement element = driver.findElement(By.className("input_class"));
			//WebElement element = driver.findElement(By.xpath("//*[@id='index-page']/footer/div[3]/ul/li[2]/a/span"));
			
//			WebElement atrpstarnum;
//			System.out.println(atr2);
//			System.out.println(atrpstarnum = atr2.findElement(By.xpath(("div[@class='star_detail']"))));
//			WebElement atrpstarnum1;
//			System.out.println(atrpstarnum1 = atrpstarnum.findElement(By.xpath(("p[@class='star_num']"))));
//			List<WebElement> liList;
//			System.out.println(liList = atrpstarnum1.findElements(By.xpath(("span"))));
//			int i1 = 0;
//			// String =
//
//			String strfansCount = "";
//			String strvCount = "";
//			int fansCount = 0;
//			int vCount = 0;
//			for (WebElement webElement : liList) {
//				WebElement atrpstarnumaa;
//				System.out.println(atrpstarnumaa = webElement.findElement(By.xpath(("a"))));
//				System.out.println(atrpstarnumaa.getText());
//				if (i1 == 1) {
//					strfansCount = atrpstarnumaa.getText().replaceAll("万", "0000");
//				}
//				if (i1 == 2) {
//					strvCount = atrpstarnumaa.getText().replaceAll("万", "0000");
//				}
//				i1 += 1;
//			}
//
//			if (strfansCount != null && !"".equals(strfansCount) && strvCount != null && !"".equals(strvCount)) {
//				fansCount = Integer.parseInt(strfansCount);
//				vCount = Integer.parseInt(strvCount);
//				// OracleHaoSou.intoPeoPlewebo(tvplayId, strhtmlurl, fansCount,
//				// vCount, "", "", mainUrl, "1");
//				mapreturn.put("urlBranch", urlBranch);
//				// mapreturn.put("fansCount", fansCount);
//				mapreturn.put("fansCount", fansCount);
//				mapreturn.put("vCount", vCount);
//
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			//刷新
			if (i%10==0) {
				webDriver.navigate().refresh();
			}
			// 退出
			// if (i>10) {
			//// webDriver.quit();
			//// i=0;
			// }
			
//			System.out.println(i);
//			i += 1;

//			seleepTime(7);
		}

		return mapreturn;
	}

//	private static void mainweboPeoPle(int statnum, int endnum) {
//		// TODO Auto-generated method stub
//		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
//		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);
//
//		for (Object Objstring : listArray) {
//
//			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
//				String urlBranch = "";
//				try {
//					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode("#"+listTemp.get(1)+"#", "utf-8")
//							+ "&Refer=STopic_box";
//
////					hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
//					WeiBoBranch(urlBranch, listTemp.get(0), 2);
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
//			// "baidu.com", "0", "3", "2014-10-15 23:10:10");
//
//		}
//	}

	public static void seleepTime(int t) {
		t = (int) (t * Math.random());
		t = t + 20;
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

//	private static void hunanBranch(String mainUrl, String tvplayId, String tyPlayName, String DataType) {
//		// TODO Auto-generated method stub
//		// new WeiBo(1, mainUrl);
//
//		try {
//			Map mapmore = WeiBoBranch(mainUrl, DataType, i);
//			String strhtmlurl = "";
//
//			strhtmlurl = mapmore.get("urlBranch").toString();
//			String strfansCount = mapmore.get("fansCount") + "";
//			String strvCount = mapmore.get("vCount") + "";
//			int fansCount = 0;
//			int vCount = 0;
//			if (strfansCount != null && !"".equals(strfansCount) && strvCount != null && !"".equals(strvCount)) {
//				fansCount = Integer.parseInt(strfansCount);
//				vCount = Integer.parseInt(strvCount);
//				System.out.println(tvplayId + strhtmlurl + fansCount + vCount + mainUrl + "1");
//
//				OracleHaoSou.intoPeoPlewebo(tvplayId, strhtmlurl, fansCount, vCount, "", "", mainUrl, "1");
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("没有该人的数据");
//			seleepTime(10);
//		}
//		// Integer.parseInt( );
//		// Integer.parseInt( )
//		// if (condition) {
//		//
//		// OracleHaoSou.intoPeoPlewebo(tvplayId, , , , "", "", mainUrl, "1");
//		// }
//
//	}
	
	public static int  Stringnum( String numString){
		int numIn=0;
		if (numString==null||numString.equals("")) {
			numString="-1";
			numIn=0;
		}
		if (numString.contains("亿")) {
			numIn=(int) ((Double.parseDouble(numString.replace("亿", "")))*100000000);
		}else{
			
		
			if (numString.contains("万")) {
				numIn=(int) (Double.parseDouble(numString.replace("万", ""))*10000);
			}else{
				numIn=(int) Double.parseDouble(numString);
			}
		}
		
		
		return numIn;
		
	}
	
	
	
	
	public static void mainProgram(int statnum, int endnum,int TV_TYPE) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.select(Integer.toString(statnum), Integer.toString(endnum));
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				String urlBranch = "";
				try {
					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode("#"+listTemp.get(1)+"#", "utf-8")
							+ "&Refer=STopic_box";

//					hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
					try {
						seleepTime(10);
						WeiBoBranch(urlBranch, listTemp.get(0), TV_TYPE);
					} catch (Exception e) {
						// TODO: handle exception
						webDriver= null;
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
//		IpFilter.mainip("http://index.haosou.com/");
//		CommonUtil.setLog("ip代理时间" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		String returnNumTVle=OracleHaoSou.returnNumPeople("edw.dim_tvplay");
		System.out.println("需要采集的电视剧数为"+returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
			//电视剧
			int TV_TYPE=2;
			if (i>135) {
				mainProgram(i, i + 1000,TV_TYPE);
				seleepTime(5);
				
			}
		}

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
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					System.out.println("-------设定要指定任务--------");
					runstatic();
				}
			}, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new
		// WeiBo(1,"http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");
//		while (true) {
//			
//			WeiBoBranch("http://s.weibo.com/weibo/%2523%25E7%25BE%258E%25E5%259B%25BD%25E9%2598%259F%25E9%2595%25BF3%2523&Refer=STopic_box", "0", 0);
//		}
		TimingTime(1, 00, 00);
//		ConfigManager config = ConfigManager.getInstance();
//		// driver = config.getConfigValue("driver");
//		String xx = ConfigManager.getInstance().getConfigValue("IDwebopeople");
//		int xxnum = Integer.parseInt(xx);
//		for (int i = xxnum; i < 16871; i = i + 1000) {
//			// i=15780;
//			mainweboPeoPle(i, i + 1000);
//
//		}
	}
}
