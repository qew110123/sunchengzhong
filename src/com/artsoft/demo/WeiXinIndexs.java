package com.artsoft.demo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class WeiXinIndexs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		runstatic();
		
		
	}
	
	// yunxing
		public static void runstatic() {

			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

//			mainurl("http://adm.meihua.info/actionpage/QuerySearch.aspx?keyStr=%E5%A7%9C%E6%98%86","姜昆");
			index("姜昆");

			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
		}
		
		
		private static void index(String name) {
			// TODO Auto-generated method stub
			String utf8name = "";
			try {
				utf8name = java.net.URLEncoder.encode(name, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//https://search.weixin.qq.com/cgi-bin/searchweb/getwxindex?query=%E5%A4%A9%E6%B4%A5&start_time=1486546021.033&end_time=1494235621.033&_=1494322020998
			
			System.out.println("获取系统毫秒数方法1："+Long.toString(new Date().getTime()));
			System.out.println(new Date());
			
//			Integer intnum=Integer.valueOf(Long.toString(new Date().getTime()));
			Double bb=Double.parseDouble(Long.toString(new Date().getTime()));
			System.out.println(bb/1000+7689600);
			System.out.println((bb/1000+7689600)+"");
			System.out.println(bb%1000);
			
			System.out.println(System.currentTimeMillis());
	        System.out.println("获取系统毫秒数方法2："+Long.toString(System.currentTimeMillis()));
			
	        String url="https://search.weixin.qq.com/cgi-bin/searchweb/getwxindex?query="+utf8name+"&start_time=1486546021.033&end_time=1494235621.033&_=1494322020998";
			try {
				
				long epoch = new java.text.SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").parse(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") ).getTime();
				
				System.out.println(epoch);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainurl(url, name);
			
		}

		private static void mainurl(String url, String name) {
			// TODO Auto-generated method stub
			
		}

}
