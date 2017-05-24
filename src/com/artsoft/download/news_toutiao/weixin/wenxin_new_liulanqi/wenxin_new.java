package com.artsoft.download.news_toutiao.weixin.wenxin_new_liulanqi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.download.webo.weiboSearch.WeiBo;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle2.DBManager;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 2017年3月3日17:26:00
 * 
 * 进行微信数据数据的
 * 
 * @author Administrator
 *
 */

public class wenxin_new {
	/**
	 * 微信数据的整体数据的
	 * 2017年3月14日15:50:17
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(1, 59, 59);

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
				// runstatic();
				try {

//					runnewMain();
					runnewMainOracleTanchu(TimeTest.getNowTime("yyyy-MM-dd"));
//					runnewMainOracleTanchu("2017-03-14");
//					wenxin_new.runnewMainOracle(TimeTest.getNowTime("yyyy-MM-dd"));
//					DBManager dbm = DBManager.instance();
//					dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行
	}
	
	
	
	private static void runnewMain() {
		// TODO Auto-generated method stub
		
		//select t.person_id,t.person_name from mart.f_person_index t where t.data_date = '29991231' order by t.network_index desc,t.person_id
		
		
		
		String id = "9100";
		String name = "刘新";
		String yingshis_utf_8 = "";
		try {
			yingshis_utf_8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data_date="2017-03-10";
		mainUrlall(id,name, yingshis_utf_8,data_date);
	}
	
	
	public static void runnewMainOracle(String data_date) {
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		List<String> listArray = Oracle.selectWEIXIN_NUMBER_NEW();
		String id="";
		String name="";
		for (Object yingshis : listArray) {

			List<String> listTemp = (List<String>) yingshis;
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			String yingshis_utf_8 = "";
			try {
				yingshis_utf_8 = java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
//				mainUrlall(yingshis_utf_8, listTemp.get(1));
				mainUrlall(id,name, yingshis_utf_8,data_date);

			} catch (Exception e) {
				// TODO: handle exception
			}
			finally {
				WeiBo.seleepTime(25);
			}

		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
		
	}
	
	
private static void runnewMainOracleTanchu(String data_date) {
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		List<String> listArray = Oracle.selectWEIXIN_NUMBER_NEW();
		String id="";
		String name="";
		for (Object yingshis : listArray) {
			

			List<String> listTemp = (List<String>) yingshis;
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			
			if (Integer.valueOf(id)<0) {
//				return;
				continue;
			}
			String yingshis_utf_8 = "";
			try {
				yingshis_utf_8 = java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
//				mainUrlall(yingshis_utf_8, listTemp.get(1));
//				mainUrlall(id,name, yingshis_utf_8,data_date);
				String urlmain = "http://weixin.sogou.com/weixin?type=2&s_from=input&query=%E8%8C%83%E5%86%B0%E5%86%B0&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=2747&sst0=1488525984844&lkt=1%2C1488525984699%2C1488525984699";
				urlmain="http://weixin.sogou.com/weixin?type=2&ie=utf8&query="+yingshis_utf_8+"&tsn=5&ft="+data_date+"&et="+data_date+"&interation=null&wxid=&usip=null&from=tool";
				//http://weixin.sogou.com/weixin?type=2&query=%E5%BC%A0%E4%B8%80%E5%87%A1&ie=utf8&s_from=input&_sug_=y&_sug_type_=&w=01019900&sut=3518&sst0=1494814568461&lkt=1%2C1494814568269%2C1494814568269
				//http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E5%BC%A0%E4%B8%80%E5%87%A1&tsn=5&ft=2017-05-15&et=2017-05-15&interation=&wxid=&usip=
				urlmain="http://weixin.sogou.com/weixin?type=2&query=111&ie=utf8&s_from=input&_sug_=y&_sug_type_=&w=01019900&sut=728&sst0=1494832346912&lkt=4%2C1494832346088%2C1494832346792";
				String data_date_jian=data_date.replace("-", "");
				wenxin_new_liulanqi.hunanBranch(urlmain, id, name, "1", data_date_jian,data_date);

			} catch (Exception e) {
				// TODO: handle exception
			}
			finally {
				seleepTime(10);
			}

		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
		
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

	private static void mainUrlall(String id,String name, String yingshis_utf_8,String data_date) {
		// TODO Auto-generated method stub
		       //http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E8%8C%83%E5%86%B0%E5%86%B0&tsn=5&ft=2017-03-08&et=2017-03-08&interation=null&wxid=&usip=null&from=tool
		       //http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E8%B5%B5%E4%B8%BD%E9%A2%96&tsn=5&ft=2017-03-09&et=2017-03-09&interation=null&wxid=&usip=null&from=tool
		String urlmain = "http://weixin.sogou.com/weixin?type=2&s_from=input&query=%E8%8C%83%E5%86%B0%E5%86%B0&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=2747&sst0=1488525984844&lkt=1%2C1488525984699%2C1488525984699";
		urlmain="http://weixin.sogou.com/weixin?type=2&ie=utf8&query="+yingshis_utf_8+"&tsn=5&ft="+data_date+"&et="+data_date+"&interation=null&wxid=&usip=null&from=tool";
		String strHtml ="";// DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
		
//		System.out.println(urlmain);
		strHtml=urlreturnHtml(urlmain);
		// System.out.println(strHtml);
		
		

		String StrUrl = "";
		StrUrl = HtmlAnalyze.getTagText(strHtml, "article_anti_url = \"", "\";");
		

		// 找到约
		String sunnumString = HtmlAnalyze.getTagText(strHtml, "找到约", "条结果");

		if (!sunnumString.equals("")) {
			sunnumString = sunnumString.replace(",", "");
		}
		// 总数整体数据为
		System.out.println(sunnumString);


		Document doc = Jsoup.parse(strHtml);

		Elements links = doc.select("ul.news-list li");
		for (Element element : links) {
			System.out.println(element.select("h3").text());
			String weixinurl="";
			System.out.println(weixinurl=element.select("h3 a").attr("href"));
			
			
			mainUrl(id, name, weixinurl, "2");
			
			
			
		}
		
		String data_date_jian=data_date.replace("-", "");
		
		int news_num = Integer.parseInt(sunnumString.replace(",", "").replace("约", ""));
		
		OracleBaidu.intotem_news_num(data_date_jian, id, news_num, urlmain, 1, 2);

	}
	
	
	private static void mainUrl(String id, String name, String content_url, String DATA_TYPE) {


		WECHAT_INFORMATION wechat1 = new WECHAT_INFORMATION();

		String htmlss = urlreturnHtml(content_url);
		
		
		Document htmlssdoc = Jsoup.parse(htmlss);
		
		wechat1.setDataId(id);
		
		
		wechat1.setUrls(content_url);

		String activity_name = htmlssdoc.getElementById("activity-name").text();
		System.out.println(activity_name);
		wechat1.setNames(activity_name);
		// 是否是原创
		String yuanchang = "";
		try {
			htmlssdoc.getElementById("copyright_logo").text();
			yuanchang = "0";
		} catch (Exception e) {
			// TODO: handle exception
			yuanchang = "1";
		}

		wechat1.setOriginal(yuanchang);
		String post_date = htmlssdoc.getElementById("post-date").text();
		// System.out.println(post_date);
		wechat1.setDates(post_date);

		String post_user = htmlssdoc.getElementById("post-user").text();
		// System.out.println(post_user);
		wechat1.setPostUser(post_user);

		Element js_content = htmlssdoc.getElementById("js_content");
		String js_contentString = js_content.toString();
		// System.out.println(js_contentString);
		wechat1.setContentAll(js_contentString);
		
		String js_content_new=js_content.toString();
		
		Elements js_contentps_h2 = js_content.select("h2");
		
		for (Element element : js_contentps_h2) {
			if (!element.toString().equals("")&&element.toString()!=null) {
//				String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<h2", "</h2>",
//						false, 0);
//				
//				String h2html=imgotherhtml;
//				String h2html
//				
//				js_content_new=js_content_new.replace(imgotherhtml, imgotherhtml+ "#h2#");
//				js_content_new=js_content_new.replace("</h2>", "*h2*</p>");
				
				String openp = HtmlAnalyze.getTagText(element.toString(), "<h2", ">",
						false, 0);
				
				
				String js_content_new_ever="<p>#h2#"+element.text().toString()+"#h2#</p>";
				
				js_content_new=js_content_new.replace(element.toString(), js_content_new_ever);
			}
		}
		
		js_content = Jsoup.parse(js_content_new);
		

//		Elements js_contentps = js_content.select("p strong");
		Elements js_contentps = js_content.select("p");
		String js_contentStringp = "";
		int ii = 0;

		int iii = 0;
		
		String leibiaoimg = "";
		for (Element element : js_contentps) {
			if (ii >= 0) {
				String Stringelement = "";

				if (element.toString().contains("<img")) {
					// Stringelement=element.toString();
					try {
						String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">",
								false, 0);
						
						String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "data-src=\"", "\"");
						
						String newimgurl = Image2.imagUrldownload_allurl(imgurlhtml);

						if (iii == 0) {
							leibiaoimg = newimgurl;
						}
						iii += 1;

						Stringelement = element.toString().replace(imgotherhtml,
								"<img src=\"" + newimgurl + "\" >");

					} catch (Exception e) {
						// TODO: handle exception
						Stringelement = element.toString();
					}

					// Stringelement=element.toString().replace(imgotherhtml,
					// "<img src=\""+newimgurl+"\" >");

				} else {
					if (element.toString().contains("<strong>")) {
						String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
								.replace("</strong>", "#1#/strong#2#");
						Document docStringelement = Jsoup.parse(Stringelement_1_2);
						String Stringelementother = docStringelement.text();
						Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
								.replace("#1#/strong#2#", "</strong>");

					} else {
						
						
						Stringelement = element.text();
					}

				}
				js_contentStringp = js_contentStringp + Stringelement + "||";
			}
			ii += 1;

		}
		if (js_contentStringp!=null&&!js_contentStringp.equals("")) {
			//			htmlss=htmlss.replace("<h2", "<p #1@#");
			//          htmlss=htmlss.replace("</h2>", "#1@#</p>");
			//<strong></strong>
			js_contentStringp=js_contentStringp.replace("#h2#", "<strong>");
			js_contentStringp=js_contentStringp.replace("*h2*", "</strong>");
		}
		
		
		wechat1.setContentP(js_contentStringp);


		String imgnameurl = "";
		String imgname = "";
		if (!imgnameurl.equals("") && imgnameurl != null) {
			String imgurls = imgnameurl.replace("\\/", "/");
			imgname = Image2.imagUrldownload(imgurls);
		}
		
		
//		if (!imgname.equals("") && imgname != null) {
//			wechat1.setIMG_BIG_NAME(imgname);
//			wechat1.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
//		}

		if (imgname.equals("") || imgname == null) {
			                      //http://img.art-d.com.cn:88/upload/img/news/contents/20161229/pm6tuNJM0NfGfgv1NyAbcbSMfoTvQuemeXIHNmn2kuv0poE6jGfGV0ygTWsicjzwXxaWUsTF4pmvSP4yEyR1blw.jpg
			wechat1.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/contents/"+TimeTest.getNowTime("yyyyMMdd")+"/");
			String[] namelist = leibiaoimg.split("/");
			// try {
			String nameurl = namelist[namelist.length - 1];
			wechat1.setIMG_BIG_NAME(nameurl);
		}
		wechat1.setSOURCE(1);
		wechat1.setDATA_TYPE(1);
		
//		wechat1.setUPDATE_FLAG(TimeTest.getNowTime("yyyyMMddHH"));
//		
//		wechat1.setDataDate(TimeTest.getNowTime("yyyyMMdd"));

		Oracle.InsertWECHAT_INFORMATION(wechat1);
		
		
//		return wechat1;


}
	
	
	private static Proxy proxy = null;

	private static String urlreturnHtml(String urlBranch) {
		String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
		// null, proxy);
		boolean bb = true;
		int i = 0;
		while (bb) {
			proxy = DealProxy.getInstance().getPoxxy();
			System.out.println(urlBranch);
//			 strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 10, "UTF-8",
//			 null, proxy);
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 10, "UTF-8", null, null);
//			 try {
////				strHtml=""+Jsoup.connect(urlBranch).get();
////				userAgent(userAgent).proxy(ip,port).execute()
//				 
//				 
////				strHtml = ""+Jsoup.connect(urlBranch).proxy("","").execute();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
				System.out.println("有返回值");
				if (strHtml.contains("您的访问出错了") || strHtml.contains("未知错误") || strHtml.contains("请输入验证码")) {
					System.out.println("ip :" + proxy);
					System.out.println("您的访问出错了！");
					String ip = HtmlAnalyze.getTagText(proxy.toString() + "##", "/", "##");
					deleteipfiler(ip);
					bb = true;
					// System.out.println(Thread.currentThread().getName());
				} else {
					System.out.println("ip :" + proxy);
					System.out.println("访问成功！");
				}
			} else {
				System.out.println("打开出错" + i + "次,链接：" + urlBranch);
				System.out.println("ip 代理出错" + proxy);
				String ip = HtmlAnalyze.getTagText(proxy.toString() + "##", "/", "##");
				deleteipfiler(ip);

			}
			if (i > 20) {
				bb = false;
			}

			i += 1;
			try {
				Thread.sleep(10000);
				
				WeiBo.seleepTime(17);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strHtml;

	}
	
	
	
	static String strproxy = "";
	
	private static void deleteipfiler(String ip) {

		if (strproxy.contains(ip)) {
			strproxy = strproxy.replace(ip + "\r\n", "");
			ReadTxtFile.wirterfile("proxy.txt", strproxy);
		}

		if (strproxy.split("\r\n").length < 50) {
			ipfilerall("");
		}
	}
	
	private static void ipfilerall(String urlMain) {
		// TODO Auto-generated method stub
		strproxy = ReadTxtFile.getKeyWordFromFile("proxy.txt");
		String strHtml = DownloadUtil.getHtmlText(
				"http://qsrdk.daili666api.com/ip/?tid=559375659838998&num=200&delay=1&category=2&sortby=speed",
				1000 * 30, "UTF-8", null, null);
		String[] iplist = strHtml.split("\r\n");
		for (String stringip : iplist) {
			strproxy = strproxy + stringip + "\r\n";
		}
		ReadTxtFile.wirterfile("proxy.txt", strproxy);

	}
	
}
