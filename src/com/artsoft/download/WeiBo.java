package com.artsoft.download;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;

public class WeiBo {

	/**
	 * 0,火狐；1，
	 * 
	 * @param i
	 */
	public static Map WeiBoBranch(String url) {
		Map mapreturn = new HashMap();
		WebDriver webDriver;
		System.setProperty("webdriver.chrome.driver",
				"D:\\chrome\\chromedriver.exe");
		webDriver = new ChromeDriver();
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
			webDriver.quit();
			seleepTime(7);
		}

		return mapreturn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new
		// WeiBo(1,"http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");
		// WeiBoBranch("http://s.weibo.com/weibo/%25E5%25AD%2599%25E4%25BF%25AA&Refer=focus_index");

		ConfigManager config = ConfigManager.getInstance();
		// driver = config.getConfigValue("driver");
		String xx = ConfigManager.getInstance().getConfigValue("IDwebopeople");
		int xxnum = Integer.parseInt(xx);
		for (int i = xxnum; i < 16871; i = i + 1000) {
			// i=15780;
			mainweboPeoPle(i, i + 1000);

		}
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
					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode(listTemp.get(1), "utf-8")+"&Refer=STopic_box";

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
		t=t+3;
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
			String strfansCount = mapmore.get("fansCount")+"";
			String strvCount =  mapmore.get("vCount")+"";
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

}
