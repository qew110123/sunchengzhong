package com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_key;

import com.artsoft.bean.MAYAO_KEY;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class maoyan_key {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		openkey();
	}
	
	public static void openkey() {
		// TODO Auto-generated method stub
		String urlMain = "http://piaofang.maoyan.com/?date=2016-02-17";
		
		
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		while (strHtml.contains("抱歉，迷路了")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			
		}
		
//		System.out.println(strHtml);
		String keturlString ="";
		
		System.out.println(keturlString=HtmlAnalyze.getTagText(strHtml, "src: url(//", ");"));
		
		String KEY_URL_NEW="";
		System.out.println(KEY_URL_NEW=HtmlAnalyze.getTagText(strHtml, "src:url(", ")"));
		
		
		String numString1 ="";
		System.out.println(numString1=HtmlAnalyze.getTagText(strHtml, "<span id='ticket_count'><i class=\"cs gsBlur\">", "万"));
		
		System.out.println("22598.2");
		
		String [] numString1list= numString1.replace(".", "").split(";");
		
		
		String numString2 ="";
		System.out.println(numString2=HtmlAnalyze.getTagText(strHtml, "<li class=\"c2 \">", "</i>"));
		
		String [] numString2list= numString2.replace(".", "").split(";");
		
		System.out.println("12563.47");
		
		
		
		String numString3 ="";
		System.out.println(numString3=HtmlAnalyze.getTagText(strHtml, "上映10天", "亿"));
		
		String [] numString3list= numString3.replace(".", "").split(";");
		
		System.out.println("22.70");
		
		
		MAYAO_KEY maoyankey=new MAYAO_KEY();
		
		maoyankey.setKeyUrl(keturlString);
		maoyankey.setKeyUrl(KEY_URL_NEW);
		
		maoyankey.setKEY_URL_NEW(KEY_URL_NEW);
		
		
		try {
			
		
		
			System.out.println("1="+numString2list[0]);
			
			maoyankey.setOne(numString2list[0]+";");
			
			System.out.println("2="+numString1list[0]);
			
			maoyankey.setTwo(numString1list[0]+";");
			
			System.out.println("3="+numString2list[4]);
			
			maoyankey.setThree(numString2list[4]+";");
			
			System.out.println("4="+numString2list[5]);
			
			maoyankey.setFour(numString2list[5]+";");
			
			System.out.println("5="+numString2list[2]);
			
			maoyankey.setFive(numString2list[2]+";");
			
			System.out.println("6="+numString2list[3]);
			
			maoyankey.setSix(numString2list[3]+";");
			
			System.out.println("7="+numString2list[6]);
			
			maoyankey.setSeven(numString2list[6]+";");
			
			
			System.out.println("8="+numString1list[4]);
			
			maoyankey.setEight(numString1list[4]+";");
			
			System.out.println("9="+numString1list[3]);
			
			maoyankey.setNine(numString1list[3]+";");
			
			System.out.println("0="+numString3list[3]);
			
			maoyankey.setZero(numString3list[3]+";");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("12321");
		}
		try {
			if (maoyankey.getFive()!=null) {
				
				
//				maoyankey.setZero("0");
				Oracle.IntoMAYAO_KEY(maoyankey);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库 违反唯一约束条件！");
		}
		
	}

}
