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
		// ���������õ�appKey��appSecret
				String appkey = "11062879";
				String secret = "370923393aa61556607aa7a4580396b4";

				// ����������
				Map paramMap = new HashMap();
				paramMap.put("app_key", appkey);
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				format.setTimeZone(TimeZone.getTimeZone("GMT+8"));// ʹ���й�ʱ�䣬����ʱ����ͬ������֤����
				paramMap.put("timestamp", format.format(new Date()));

				System.out.println(paramMap);
				
				// �Բ�������������
				String[] keyArray = (String[]) paramMap.keySet().toArray(new String[0]);
				Arrays.sort(keyArray);
				 
				// ƴ������Ĳ�����-ֵ��
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(secret);
				for(String key : keyArray){
				    stringBuilder.append(key).append(paramMap.get(key));
				}
				     
				stringBuilder.append(secret);
				String codes = stringBuilder.toString();
				         
				// MD5���벢תΪ��д�� ����ʹ�õ���Apache codec
				String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(codes).toUpperCase();

				paramMap.put("sign", sign);

				// ƴװ����ͷProxy-Authorization��ֵ������ʹ�� guava ����map��ƴ��
				String authHeader = "MYH-AUTH-MD5 " + Joiner.on('&').withKeyValueSeparator("=").join(paramMap);

				System.out.println(authHeader);

				//������ʹ�����϶�̬������з���
				   
				//������jsoupΪ������authHeader��������ͷ�м���
				//ԭ���jsoup���ô������㣬����ʹ��E-lai�ṩ���޸İ�(https://github.com/E-lai/jsoup-proxy)
				//ע��authHeader������ÿ������ʱ�����¼��㣬Ҫ��Ȼ����Ϊʱ��������֤ʧ��
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
