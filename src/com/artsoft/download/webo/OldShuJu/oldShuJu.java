package com.artsoft.download.webo.OldShuJu;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.artsoft.bean.Tem_weibo_word_age_sex;
import com.artsoft.bean.Tem_weibo_word_tag;
import com.artsoft.bean.tem_weibo_word_num;
import com.artsoft.download.webo.WeBoDajiewang;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class oldShuJu {
	
	
	public static void people(String names, int data_type, String  data_id) throws Exception {
		// String strHtml =
		// DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword?wname=范冰冰",
		// 1000 * 30, "UTF-8",
		// null, null);、
		// http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108

		// DownloadUtil.getHtmlText("http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108",
		// 1000 * 30, "UTF-8",
		// null, null);
		String strHtml = DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword?wid=1091324101491&wname=%E4%BD%A0%E5%A5%BD", 1000 * 30, "UTF-8", null,
				null);
		String timeDiff = HtmlAnalyze.getTagText(strHtml, "server_time': '", "'");
		System.out.println(new Date());
		// System.out.println(Weibo("http://data.weibo.com/index/ajax/contrast?key2=%25E6%259D%258E%25E6%2599%25A8&key3=&key4=&key5=&key6=&_t=0&__rnd=1450260534657"));
		System.out.println(timeDiff);

		Date date = new Date(System.currentTimeMillis());
		int s = 0;
		System.out.println(s = (int) (date.getTime() - Integer.parseInt(timeDiff)));

		// System.out.println(Integer.parseInt(timeDiff));
		// System.out.println(new Date()- new Date(Integer.parseInt(timeDiff));
		String url="http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s;
//		String hostnum = WeBoDajiewang.Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s);
		
//		String hostnum = WeBoDajiewang.Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s);
		
		String widstring=WeBoDajiewang.Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s);
		
		String wid=HtmlAnalyze.getTagText(widstring, "\"wid\":\"", "\"");
		
//		System.out.println(widstring);
		//
//		String hostnum = WeBoDajiewang.Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s);
		String hostnum = WeBoDajiewang.Weibo("http://data.weibo.com/index/ajax/getchartdata?wid="+wid+"&sdate=2013-03-01&edate=2016-06-30&__rnd=" + s);
		
		// Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1449471097626");
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss").format(new Date(1446912627104l)));
		// Weibo("http://data.weibo.com/index/ajax/isexsitofinput?__rnd="+s );
//		System.out.println(hostnum = DownloadUtil.decodeUnicode(hostnum));
//		JSONObject objectobject = JSONObject.fromObject(hostnum);
//		// objectobject=JSONObject.fromObject(object);
//		// System.out.println( objectobject.get("keyword"));
//		String keywordnames = HtmlAnalyze.getTagText(hostnum, "keyword\":[\"", "\"]}");
//
//		if (!names.equals(keywordnames)) {
//			return;
//		}
//		System.out.println(objectobject.get("data"));
//		JSONArray authors = new JSONArray();
//		authors = (JSONArray) objectobject.get("data");
//		for (Object object : authors) {
			JSONObject objectobjects = JSONObject.fromObject(hostnum);
			// objectobject=JSONObject.fromObject(object);
			// String ANIMATION_NAME="";
			// System.out.println(ANIMATION_NAME=(String)
			// objectobjects.get("name"));
			JSONArray arrayjsonname = new JSONArray();
			arrayjsonname = (JSONArray) objectobjects.get("zt");
			String NEWS_NUM="";
			String DATA_DATE="";
			for (Object object2 : arrayjsonname) {
				tem_weibo_word_num tem_weibo_word_num= new tem_weibo_word_num();
				tem_weibo_word_num.setWord(names);
				tem_weibo_word_num.setDataId(data_id);
				System.out.println(object2);
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(DATA_DATE=(String) objectobjects2.get("day_key"));
				if (DATA_DATE!=null&&!DATA_DATE.equals("")) {
					DATA_DATE=DATA_DATE.replace("-", "");
					tem_weibo_word_num.setDataDate(DATA_DATE);
					System.out.println(objectobjects2.get("wid"));
					System.out.println(NEWS_NUM=(String) objectobjects2.get("value"));
					tem_weibo_word_num.setNewsNum(Integer.valueOf(NEWS_NUM));
					tem_weibo_word_num.setDimensionType(1);
					tem_weibo_word_num.setDataType(data_type);
					tem_weibo_word_num.setUrl(url);
					Oracle.InsertCompany(tem_weibo_word_num);
				}
				
			}

			JSONArray arrayjsonnameyd = new JSONArray();
			arrayjsonnameyd = (JSONArray) objectobjects.get("yd");
			for (Object object2 : arrayjsonnameyd) {
				tem_weibo_word_num tem_weibo_word_num= new tem_weibo_word_num();
				tem_weibo_word_num.setWord(names);
//				System.out.println(object2);
				String pc="";
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(DATA_DATE=(String)objectobjects2.get("daykey").toString().replace("-", ""));
				tem_weibo_word_num.setDataDate(DATA_DATE);
				System.out.println(pc=(String) objectobjects2.get("pc"));
				tem_weibo_word_num.setDataType(data_type);
//				tem_weibo_word_num.setDimensionType(1);
				tem_weibo_word_num.setDimensionType(2);
				tem_weibo_word_num.setNewsNum(Integer.valueOf(pc));
				tem_weibo_word_num.setDataId(data_id);
				tem_weibo_word_num.setUrl(url);
				Oracle.InsertCompany(tem_weibo_word_num);
				String mobile="";
				System.out.println(mobile=(String) objectobjects2.get("mobile"));
				tem_weibo_word_num.setDimensionType(3);
				tem_weibo_word_num.setNewsNum(Integer.valueOf(mobile));
				Oracle.InsertCompany(tem_weibo_word_num);
			}

//		}
		
	}
	
	
	public static void webopeople(String name ,int data_type,String  data_id){
//		String name = "范冰冰";
		String URLEncodername = "";
		String codeString ="";
		try {
			System.out.println(
					URLEncodername = java.net.URLEncoder.encode(java.net.URLEncoder.encode(name, "utf-8"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(codeString=WeBoDajiewang.Weibo("http://data.weibo.com/index/ajax/contrast?key2=" + URLEncodername
					+ "&key3=&key4=&key5=2013-03-01&key6=2016-06-30&_t=0&__rnd=1467358971915"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JSONObject objectobject = JSONObject.fromObject(codeString);
			String codeshuju=objectobject.getString("code");
			if (codeshuju.equals("100000")) {
				System.out.println("含有关键字");
				people(name, data_type,data_id);
			}else{
				if (codeshuju.equals("100001")) {
					System.out.println("没有关键词");
				}
				else{
					System.out.println("出现问题");
					CommonUtil.setLog( "出现问题"+TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + "name："+name+"id:"+data_id);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 人的数据的采集
	 */

	private static void mainPeoPle(int statnum, int endnum) {
		List<String> listArray = OracleHaoSou.selectname(Integer.toString(statnum), Integer.toString(endnum));
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**
			 * 名称  
			 * data_type 类型 1 人 2 电视剧 3 电影
			 * data_id  人物id
			 */
			webopeople(listTemp.get(1),1,listTemp.get(0));
		}

	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		String returnNumPeople = OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
		System.out.println("需要采集的人名字数为" + returnNumPeople);
		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			mainPeoPle(i, i + 1000);
		}
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runstatic();
		//人的
//		webopeople("欢乐颂",1,"0");
		runstatic();
	}

}
