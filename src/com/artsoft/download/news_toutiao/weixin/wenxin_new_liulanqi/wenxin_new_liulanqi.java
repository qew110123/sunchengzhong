package com.artsoft.download.news_toutiao.weixin.wenxin_new_liulanqi;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.artsoft.download.webo.weiboSearch.WeiBo;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.HtmlAnalyze;

public class wenxin_new_liulanqi {

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

			// Cookie cookie = new Cookie("gsid",
			// "4u9x26191nWvvJwthqgaobLTkeU");
			//
			// ((WebDriver) options).manage().addCookie(cookie);

			options.addArguments("–user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default");
			// C:\Users\Administrator\AppData\Local\Google\Chrome\User Data
			webDriver = new ChromeDriver(options);

			// return webDriver;
		} else {
			System.out.println(webDriver);

		}

	}

	static int iiii = 0;

	public static String weiXinBranch(String url) {
		iiii = +1;

		String num = "";

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
		WeiBo.seleepTime(30);

		// ChromeOptions options = new ChromeOptions();
		// options.addArguments(“–user-data-dir=C:/Users/xxx/AppData/Local/Google/Chrome/User
		// Data/Default”);
		// WebDriver driver = new ChromeDriver(options);

		
//		if (iiii % 3 == 1) {
			webDriver.get(url);
			
//		}
		// 获取标题元素值
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			String title = webDriver.getTitle();
			System.out.println("标题: " + title);
			// 获取淘宝价格
			// WebElement detail =
			// webDriver.findElement(By.xpath("//*[@id=\"pl_weibo_directtop\"]"));
			// System.out.println(detail.getText());
			// WebElement atr = detail.findElement(By.xpath("div"));
			// WebElement atr1 = atr.findElement(By.xpath("div"));
			// WebElement atr2 = atr1.findElement(By.xpath("div"));
			// WebElement atr3 = atr2.findElement(By.xpath("div"));
			// WebElement atr4 = atr3.findElement(By.xpath("a"));
			// String urlBranch = "";
			// System.out.println(atr4);
			// System.out.println(urlBranch = atr4.getAttribute("href"));
			// WebElement atrpstarnum;
			// System.out.println(atr2);
			// System.out.println(atrpstarnum =
			// atr2.findElement(By.xpath(("div[@class='star_detail']"))));
			// WebElement atrpstarnum1;
			// System.out.println(atrpstarnum1 =
			// atrpstarnum.findElement(By.xpath(("p[@class='star_num']"))));
			// List<WebElement> liList;
			// System.out.println(liList =
			// atrpstarnum1.findElements(By.xpath(("span"))));

			WebElement detail = webDriver.findElement(By.xpath("//*[@id=\"wrapper\"]"));
			System.out.println(detail.getText());

			WebElement atr = detail.findElement(By.xpath("div"));
			System.out.println(atr.getText());
			// WebElement atr1 = atr.findElement(By.xpath("div"));
			// System.out.println(atr1.getText());
			// WebElement atr2 = atr1.findElement(By.xpath("div"));
			// System.out.println(atr2.getText());
			// WebElement atr3 = atr2.findElement(By.xpath("div"));

			// WebElement atrpstarnum =
			// atr.findElement(By.xpath(("div[@class=\"mun\"]")));
			//
			// System.out.println(atrpstarnum.getText());
			num = HtmlAnalyze.getTagText(atr.getText(), "找到约", "条结果");
//			if (iiii % 3 == 0) {
				try {
					seleepTime(5);
					WebElement scrollheader = webDriver.findElement(By.xpath("//*[@id=\"scroll-header\"]"));
					System.out.println(scrollheader);
					System.out.println(scrollheader.getText());

					WebElement form = scrollheader.findElement(By.xpath("form"));
					System.out.println(form.getText());

					WebElement querybox = form.findElement(By.xpath("div"));
					System.out.println(querybox.getText());
					WebElement swz = querybox.findElement(By.xpath("input"));
					System.out.println(swz.getClass());
					swz.click();
					
					
					
					

					System.out.println("单击成功！");
					
					
					
					
					
					 title = webDriver.getTitle();
					System.out.println("标题: " + title);
					// 获取淘宝价格
					// WebElement detail =
					// webDriver.findElement(By.xpath("//*[@id=\"pl_weibo_directtop\"]"));
					// System.out.println(detail.getText());
					// WebElement atr = detail.findElement(By.xpath("div"));
					// WebElement atr1 = atr.findElement(By.xpath("div"));
					// WebElement atr2 = atr1.findElement(By.xpath("div"));
					// WebElement atr3 = atr2.findElement(By.xpath("div"));
					// WebElement atr4 = atr3.findElement(By.xpath("a"));
					// String urlBranch = "";
					// System.out.println(atr4);
					// System.out.println(urlBranch = atr4.getAttribute("href"));
					// WebElement atrpstarnum;
					// System.out.println(atr2);
					// System.out.println(atrpstarnum =
					// atr2.findElement(By.xpath(("div[@class='star_detail']"))));
					// WebElement atrpstarnum1;
					// System.out.println(atrpstarnum1 =
					// atrpstarnum.findElement(By.xpath(("p[@class='star_num']"))));
					// List<WebElement> liList;
					// System.out.println(liList =
					// atrpstarnum1.findElements(By.xpath(("span"))));


					detail = webDriver.findElement(By.xpath("//*[@id=\"wrapper\"]"));
					System.out.println(detail.getText());

					 atr = detail.findElement(By.xpath("div"));
					System.out.println(atr.getText());
					// WebElement atr1 = atr.findElement(By.xpath("div"));
					// System.out.println(atr1.getText());
					// WebElement atr2 = atr1.findElement(By.xpath("div"));
					// System.out.println(atr2.getText());
					// WebElement atr3 = atr2.findElement(By.xpath("div"));

					// WebElement atrpstarnum =
					// atr.findElement(By.xpath(("div[@class=\"mun\"]")));
					//
					// System.out.println(atrpstarnum.getText());
//					num = HtmlAnalyze.getTagText(atr.getText(), "找到约", "条结果");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("单击失败！");
				}

//			}
				
				
			if (iiii % 5 == 0) {
				try {

					seleepTime(5);

					WebElement sogou_next = webDriver.findElement(By.xpath("//*[@id=\"sogou_next\"]"));

					sogou_next.click();
					System.out.println("下一页");

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("下一页失败");
				}

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

			seleepTime(20);
		}
		return num;

		// return mapreturn;
	}

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

	public static void hunanBranch(String mainUrl, String tvplayId, String tyPlayName, String DataType,
			String data_date_jian) {
		// TODO Auto-generated method stub
		// new WeiBo(1, mainUrl);

		try {
			String num = weiXinBranch(mainUrl);

			if (!num.equals("")) {

				int intnum = Integer.valueOf(num);
				OracleBaidu.intotem_news_num(data_date_jian, tvplayId, intnum, mainUrl, 1, 2);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("没有该人的数据");
			WeiBo.seleepTime(150);
		}
		// Integer.parseInt( );
		// Integer.parseInt( )
		// if (condition) {
		//
		// OracleHaoSou.intoPeoPlewebo(tvplayId, , , , "", "", mainUrl, "1");
		// }

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {

			hunanBranch(
					"http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E8%B5%B5%E4%B8%BD%E9%A2%96&tsn=5&ft=2017-03-10&et=2017-03-10&interation=null&wxid=&usip=null&from=tool",
					"", "", "3", "20170310");
		}

	}

}
