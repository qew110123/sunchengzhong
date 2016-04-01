package com.artsoft.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.base.Joiner;

public class mayi {
	public static void youkuDetailed(String url) {
		// 定义申请获得的appKey和appSecret
				String appkey = "11062879";
				String secret = "370923393aa61556607aa7a4580396b4";

				// 创建参数表
				Map paramMap = new HashMap();
				paramMap.put("app_key", appkey);
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				format.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 使用中国时间，以免时区不同导致认证错误
				paramMap.put("timestamp", format.format(new Date()));

				System.out.println(paramMap);
				
				// 对参数名进行排序
				String[] keyArray = (String[]) paramMap.keySet().toArray(new String[0]);
				Arrays.sort(keyArray);
				 
				// 拼接有序的参数名-值串
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(secret);
				for(String key : keyArray){
				    stringBuilder.append(key).append(paramMap.get(key));
				}
				     
				stringBuilder.append(secret);
				String codes = stringBuilder.toString();
				         
				// MD5编码并转为大写， 这里使用的是Apache codec
				String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(codes).toUpperCase();

				paramMap.put("sign", sign);

				// 拼装请求头Proxy-Authorization的值，这里使用 guava 进行map的拼接
				String authHeader = "MYH-AUTH-MD5 " + Joiner.on('&').withKeyValueSeparator("=").join(paramMap);

				System.out.println(authHeader);

				//接下来使用蚂蚁动态代理进行访问
				   
				//这里以jsoup为例，将authHeader放入请求头中即可
				//原版的jsoup设置代理不方便，这里使用E-lai提供的修改版(https://github.com/E-lai/jsoup-proxy)
				//注意authHeader必须在每次请求时都重新计算，要不然会因为时间误差而认证失败
				try {
					Document doc = Jsoup.connect("http://1212.ip138.com/ic.asp").proxy("182.92.1.222", 8123, null).header("Proxy-Authorization", authHeader).get();
					System.out.println(doc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				
				
				
				
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		youkuDetailed("");
		
		
	}

}
