package com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class jinritoutiao {
	private static void jinritoutiao(String urlMain) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		JSONObject objects = new JSONObject();
		JSONArray list = new JSONArray();

		objects = JSONObject.fromObject(strHtml);
		System.out.println(objects);
		System.out.println(objects.get("data"));
//		JSONObject data = (JSONObject) objects.get("data");
//		System.out.println(data);
		list = (JSONArray) objects.get("data");
		
		int search_index = 0;
		String trend = "";
		String title="";
		String xiangxi_url="";
		for (Object object : list) {
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(title = (String) objectobject.get("title"));
//			System.out.println( xiangxi_url="http://www.toutiao.com"+objectobject.get("source_url"));
			String urlid="";
			urlid=HtmlAnalyze.getTagText(objectobject.get("source_url").toString()+"#", "group/", "#");
			System.out.println( xiangxi_url="http://www.toutiao.com/a"+urlid);
			String strHtml_xiangxi = DownloadUtil.getHtmlText(xiangxi_url, 1000 * 30, "UTF-8", null, null);
			if (strHtml_xiangxi==null) {
				continue;
			}
//			System.out.println(strHtml_xiangxi);
			Document docs = Jsoup.parse(strHtml_xiangxi);
			
			Elements  js_contentps=docs.select("article p");
			try {
				try {
				if (js_contentps.size()==0) {
					js_contentps=docs.getElementById("article-main").select("p");
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
				if (js_contentps.size()==0) {
					js_contentps=docs.getElementById("text").select("p");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String  js_contentStringp="";
			int ii=0;
			 for (Element element : js_contentps) {
//				if (ii>2) {
					String Stringelement="";
					
					if (element.toString().contains("<img")) {
						if (!element.toString().contains("iframe")) {
						Stringelement=element.toString();
						}
					}else{
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2=element.toString().replace("<strong>", "#1#strong#2#").replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother=docStringelement.text();
							Stringelement=Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#", "</strong>");
							
						}else{
							if (!element.toString().contains("iframe")) {
								Stringelement=element.text();
							}
						}
						 	
					}
					js_contentStringp=js_contentStringp+Stringelement+"||";
//				}
				ii+=1;
				
				
			}
			
//			System.out.println(search_index = (int) objectobject.get("power"));
//			System.out.println(trend = (String) objectobject.get("trend"));
//			OracleHaoSou.intotem_person_keyword_distrib(data_date, person_id, keyword, search_index, trend, urlMain,data_type);
			 System.out.println(js_contentStringp);
			 if (!js_contentStringp.equals("")) {
				 WECHAT_INFORMATION wechat=new WECHAT_INFORMATION();
				 wechat.setUrls(xiangxi_url);
				 wechat.setNames(title);
				 String DATES="";
				 System.out.println(objectobject.get("behot_time"));
				 String behot_time= String.valueOf( objectobject.get("behot_time"));
				 if (!behot_time.equals("")) {
					 DATES=stampToDate(behot_time+"000");
				}
				 wechat.setDates(DATES);
				 wechat.setContentAll(js_contentStringp);
				 wechat.setContentP(js_contentStringp);
				 wechat.setSOURCE(7);
				wechat.setPostUser(String.valueOf(objectobject.get("source")));
				
				
				wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				
				String imgurl=String.valueOf(objectobject.get("image_url"));
				System.out.println(imgurl);
				String imgname="";
				if (!imgurl.equals("")&&imgurl!=null) {
					String imgurls=imgurl.replace("\\/", "/");
					imgname=Image2.imagUrldownload_1(imgurls);
				}
				
				wechat.setIMG_BIG_NAME(imgname);
				
				
				Oracle.InsertWECHAT_INFORMATION(wechat);
				 
			}
		}
		
		
		
	}
	
	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    
    
    public static void runstaticshijian(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

//		if (TimeTest.getNowTime("HH").equals("07") || TimeTest.getNowTime("HH").equals("09") || TimeTest.getNowTime("HH").equals("13")) {
////			CountNum.runCount();
//			runstatic();
//		}
		try {
//			jinritoutiao("http://www.toutiao.com/api/pc/feed/?category=news_entertainment&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&as=A1A508930BCCE9B&cp=583B4CCE99DBDE1");
			String max_behot_time="";
			Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
//			 System.out.println(timeStamp);
			max_behot_time=String.valueOf(timeStamp).substring(0,10);
			jinritoutiao("http://www.toutiao.com/api/pc/feed/?category=news_entertainment&utm_source=toutiao&widen=1&max_behot_time="+max_behot_time+"&max_behot_time_tmp="+max_behot_time+"&as=A185A8A3DD43C2A&cp=583D937C827AFE1");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
    
    /**
	 * 测试时间
	 * 2016年8月17日17:30:00
	 * @param args
	 */
	public static void rundingshitime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
        Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                try {
					
                	runstaticshijian();
				} catch (Exception e) {
					// TODO: handle exception
				}
            } 
        }, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行  
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		 Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
//		 System.out.println(timeStamp);
//	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
//	        System.out.println(sd);
//		jinritoutiao("http://www.toutiao.com/api/pc/feed/?category=news_entertainment&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&as=A1A508930BCCE9B&cp=583B4CCE99DBDE1");
		            //http://www.toutiao.com/api/pc/feed/?category=news_entertainment&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&as=A17588738DF3BFB&cp=583D031BDF2B9E1
		//////////////http://www.toutiao.com/api/pc/feed/?category=news_entertainment&utm_source=toutiao&widen=1&max_behot_time=1480408030&max_behot_time_tmp=1480408030&as=A185A8A3DD43C2A&cp=583D937C827AFE1
//		runnewMain();
		
		rundingshitime(1, 00, 00);
	}

	

}
