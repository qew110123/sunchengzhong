package com.artsoft.download.news_toutiao.weixin.wenxin_new_liulanqi;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WenxinSouGouIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		TimingTime(1, 59, 59);
		runnewMainOracleTanchu();

	}
	
	
	private static void runnewMainOracleTanchu() {
		
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
				String urlmain = "";
				//urlmain="http://weixin.sogou.com/weixin?type=2&ie=utf8&query="+yingshis_utf_8+"&tsn=5&ft="+data_date+"&et="+data_date+"&interation=null&wxid=&usip=null&from=tool";
				
				String data_date_jian="";
//				wenxin_new_liulanqi.hunanBranch(urlmain, id, name, "1", data_date_jian);

			} catch (Exception e) {
				// TODO: handle exception
			}
			finally {
//				seleepTime(10);
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
					// runstatic();
					try {

//						runnewMain();
//						runnewMainOracleTanchu(TimeTest.getNowTime("yyyy-MM-dd"));
//						runnewMainOracleTanchu("2017-03-14");
//						wenxin_new.runnewMainOracle(TimeTest.getNowTime("yyyy-MM-dd"));
//						DBManager dbm = DBManager.instance();
//						dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
						
						runnewMain();
						
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
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


		private static void mainUrlall(String id, String name, String yingshis_utf_8, String data_date) {
			// TODO Auto-generated method stub
			
			//http://zhishu.sogou.com/index/media/wechat?kwdNamesStr=%E5%88%98%E6%96%B0&timePeriodType=MONTH&dataType=MEDIA_WECHAT&queryType=INPUT
			
			

			// TODO Auto-generated method stub
			       //http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E8%8C%83%E5%86%B0%E5%86%B0&tsn=5&ft=2017-03-08&et=2017-03-08&interation=null&wxid=&usip=null&from=tool
			       //http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E8%B5%B5%E4%B8%BD%E9%A2%96&tsn=5&ft=2017-03-09&et=2017-03-09&interation=null&wxid=&usip=null&from=tool
			String urlmain = "";
			urlmain="http://zhishu.sogou.com/index/media/wechat?kwdNamesStr=%E5%88%98%E6%96%B0&timePeriodType=MONTH&dataType=MEDIA_WECHAT&queryType=INPUT";
			String strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
			
//			System.out.println(urlmain);
//			strHtml=urlreturnHtml(urlmain);
			// System.out.println(strHtml);
			
			

			String Strjson = "";
			Strjson = HtmlAnalyze.getTagText(strHtml, "root.SG.data = ", ";");
			

			// 找到约
			String sunnumString = HtmlAnalyze.getTagText(strHtml, "SG.query = \"", "\"");

			if (!sunnumString.equals(name)) {
				return;
			}
			// 总数整体数据为
			System.out.println(sunnumString);


//			Document doc = Jsoup.parse(strHtml);
//
//			Elements links = doc.select("ul.news-list li");
//			for (Element element : links) {
//				System.out.println(element.select("h3").text());
//				String weixinurl="";
//				System.out.println(weixinurl=element.select("h3 a").attr("href"));
//				
//				
////				mainUrl(id, name, weixinurl, "2");
//				
//				
//				
//			}
//			
//			String data_date_jian=data_date.replace("-", "");
//			
//			int news_num = Integer.parseInt(sunnumString.replace(",", "").replace("约", ""));
			
			JSONObject objectobjects = JSONObject.fromObject(Strjson);

			JSONArray arrayjsonname = new JSONArray();
			arrayjsonname = (JSONArray) objectobjects.get("pvList");
//			int shuliang = 0;
			for (Object object : arrayjsonname) {
//				System.out.println(object);
				JSONArray arrayjson = new JSONArray();
				arrayjson= (JSONArray) object;
				for (Object object1 : arrayjson) {
					System.out.println(object1);
					JSONObject objectobjects2 = JSONObject.fromObject(object1);
					String news_num=objectobjects2.getString("pv");
					String data_date_jian=objectobjects2.getString("date");
//					OracleBaidu.intotem_news_num(data_date_jian, id, news_num, urlmain, 1, 2);
					OracleBaidu.intotem_news_num(data_date_jian, id, Integer.valueOf(news_num), urlmain, 1, 2);
					
				}
			}
			
			
//			OracleBaidu.intotem_news_num(data_date_jian, id, news_num, urlmain, 1, 2);

		
			
		}

}
