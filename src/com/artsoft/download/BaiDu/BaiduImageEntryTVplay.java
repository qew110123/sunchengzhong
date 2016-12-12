package com.artsoft.download.BaiDu;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.artsoft.oracle.OracleBaidu;

public class BaiduImageEntryTVplay {
	
	
	public static void runupdate() {
		List<String> listArray = OracleBaidu.selectbaiTVplay();

		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id = "";
			String name = "";
			String url = "";
			System.out.println(id = listTemp.get(0));
			System.out.println(url = listTemp.get(2));
			System.out.println(name = listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0)) && listTemp.get(1) != null
					&& !"".equals(listTemp.get(1)) && listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
//				BaiDuTeleplayDownload.mainmore(id, url, name);
				try {
					
//					url="http://baike.baidu.com/item/%E8%BF%B7%E5%AE%AB%E8%9B%9B/16794724";
//					url="http://baike.baidu.com/item/%E5%B0%91%E6%9E%97%E5%AF%BA%E4%BC%A0%E5%A5%87%E4%B9%8B%E4%B8%9C%E5%BD%92%E8%8B%B1%E9%9B%84";
					
					
					if (isChineseChar(url)) {
						
						url=url.replace("#", "787878788");
						
						url=url.replace(":", "909090909090");
						
						String url_gai=url.replace("/", "11111111");
						String urlutf_8=java.net.URLEncoder.encode(url_gai, "utf-8");
						url=urlutf_8.replace("11111111", "/");
						url=url.replace("909090909090", ":");
						
						url=url.replace("787878788","#" );
						
					}
					BaiduImageEntry.baidumainurl(id, url, name, 0);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		System.out.println(listArray.size());
	}
	
	
	public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
        Matcher m=p.matcher(str); 
        if(m.find()){ 
            temp =  true;
        }
        return temp;
    }
/**
 * 电视剧 
 * 百度词条数据图片
 * 2016年6月8日15:55:02
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BaiduImageEntry.baidumainurl("7432", "http://baike.baidu.com/subview/3412436/16380924.htm","武媚娘传奇" , 0);
		
//		BaiduImageEntry.baidumainurl("", "http://baike.baidu.com/item/%E9%93%81%E6%A0%B8%E6%A1%83%E4%B9%8B%E6%97%A0%E9%97%B4%E9%A3%8E%E4%BA%91", "", 0);
		
		runupdate();
	}

}
