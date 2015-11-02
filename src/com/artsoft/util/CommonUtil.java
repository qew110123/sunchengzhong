package com.artsoft.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * ������Ϣ���÷���
 * @author shiduo
 */
public class CommonUtil {

	/**
	 * ���ĳһʱ��� ,�����б�
	 * @param strStart ��ʼʱ�� 
	 * @param  strEnd ����ʱ�� 
	 * @return ArrayList
	 */
	public static ArrayList<?> getDateList(String strStart , String strEnd ) {
		
		ArrayList<String> List = new ArrayList<String>();
			
		// �����ʼ���� �� ��ֹ����
		//String strStart = Global.getInstance().getConfigValue("StartDate");
		//String strEnd = Global.getInstance().getConfigValue("EndDate");
		// �·���0-11
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Date startDate;
		Date endDate;
		try {
			startDate = format.parse(strStart);
			start.setTime(startDate);
			endDate = format.parse(strEnd);
			end.setTime(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (start.compareTo(end) <= 0) {

			List.add(format.format(start.getTime()).toString());
			start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
		}
		return List;
	}

	public static String getCookie(String strUrl, String strIp){
		
		String strJsId = "";
		HttpClient client = new HttpClient();
		if (strIp != null ){
			String [] strTemps = strIp.split(":");
			client.getHostConfiguration().setProxy(strTemps[0],Integer.parseInt(strTemps[1]));
		}
		
		client.setTimeout(1000*10);
		HttpMethod get = new GetMethod(strUrl);
		
		try {
			client.executeMethod(get);
		} catch (HttpException e) {
			e.printStackTrace();
			System.out.println("-----HttpException-----"+"�ػ��쳣1");
			return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------IOException-----"+"�ػ��쳣2");
			return "false";
		}
		Cookie[] cookies = client.getState().getCookies();
		if (cookies != null && cookies.length > 0) {
			String cook = cookies[0].getValue();
			for (int i = 1; i < cookies.length; i++) {
				cook += "; " + cookies[i].getName() + "="+ cookies[i].getValue();
			}
			strJsId = cook;
			System.out.println("--���cookie��--" + cook);
		}
		return strJsId;
		
	}
	
	/**
	 * ��ñ�ǩ����
	 * @param strText Դ���ַ���
	 * @param strStart ��ʼ���
	 * @param strEnd �������
	 * @return
	 */
	public static String getTagInfo(String strText, String strStart,String strEnd) {

		int iStart = strText.indexOf(strStart);
		if (iStart != -1) {
			int iEnd = strText.indexOf(strEnd, iStart);
			if (iEnd != -1) {
				strText = strText.substring(iStart + strStart.length(), iEnd);
				return strText;
			}
		}
		return "";
	}
	/**
	 * ��ñ�ǩ����
	 * @param strText Դ���ַ���
	 * @param strStart ��ʼ���
	 * @param strEnd �������
	 * @return
	 */
	public static String getTagInfoLast(String strText, String strStart,String strEnd) {

		int iStart = strText.indexOf(strStart);
		if (iStart != -1) {
			int iEnd = strText.lastIndexOf(strEnd, iStart);
			if (iEnd != -1) {
				strText = strText.substring(iEnd+strEnd.length(),iStart);
				return strText;
			}
		}
		return "";
	}
	
	/**
	 * ����������ʽ�����ַ���
	 * ͨ���޸�������ʽ��ɲ�ͬ������
	 * @param strContents
	 * @return
	 */
	public String dealCharacter (String strContents){
		if (strContents!=null){
			
			Pattern pattern = Pattern.compile("(\\(.*?\\))"); 
			Matcher matcher = pattern.matcher(strContents); 
			strContents =matcher.replaceAll("\n");
			System.out.println(matcher.replaceAll("\n")); 
		}
		return strContents;
	}
	/**
	 * ������ת��Ϊ ��/��/��
	 * ����19880101 -> 1/1/1988
	 * @param strDate
	 * @return
	 */
	 public static String turnDate (String strDate ){
		 
		 String newDate = "";
		 if(!"".equals(strDate)){
			 
		 SimpleDateFormat  sd = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat  sd2 = new SimpleDateFormat("MM/dd/yyyy");//Ȼ�����ת��
		 Date date;
		try {
			date = sd.parse(strDate);
			newDate = sd2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		return newDate;
	 } 
	 /**
	  * ����ת��  
	  * @param strDate
	  * @return
	  */
	 public static String turnDate2 (String strDate ){
		 
		 String newDate = "";
		 SimpleDateFormat  sd = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat  sd2 = new SimpleDateFormat("yyyy.MM.dd");//Ȼ�����ת��
		 Date date;
		try {
			date = sd.parse(strDate);
			newDate = sd2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	 } 
	 
	 
	 public static String turnDate3 (String strDate ){
		 String newDate = "";
		 if(!strDate.equals("")){
			 SimpleDateFormat  sd = new SimpleDateFormat("dd.MM.yyyy");
			 SimpleDateFormat  sd2 = new SimpleDateFormat("yyyy.MM.dd");//Ȼ�����ת��
			 Date date;
			 try {
				 date = sd.parse(strDate);
				 newDate = sd2.format(date);
			 } catch (ParseException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
		return newDate;
	 } 
	 
	/**
	 * �����ҳԴ���� ����������
	 * 
	 * @param strUrl
	 * @param strUrl
	 * @return strText
	 */
//	public static  String openUrl(String strUrl,String strEnCoding) {
//		
//		boolean bVal = true;
//		String strText = "";
//
//		while (bVal) {
//			strText = DownloadUtil.getHtmlText(strUrl, 1000 * 30, strEnCoding ,null, DealProxy.getInstance().getPoxxy());
////			strText = DownloadUtil.readHtml(strUrl,  1000*10, "utf-8", null, DealProxy.getInstance().getPoxxy());
////			strText = 	DownloadUtil.getHtmlTextNew(strUrl, 1000*10, null, DealProxy.getInstance().getPoxxy());
//			if (strText != null&&!"".equals(strText)) {
//				bVal = false;
//			}
//		}
//
//		return strText;
//	}
	
	
	
//	public static  Proxy getProxy(String strUrl,String strEnCoding) {
//		
//		boolean bVal = true;
//		String strText = "";
//		Proxy proxyWipo  = null ;
//
//		while (bVal) {
//			
//			 proxyWipo  = DealProxy.getInstance().getPoxxy();
//			strText = DownloadUtil.getHtmlText(strUrl, 1000 * 30, strEnCoding ,null,proxyWipo );
//			if (strText != null) {
//				
//				bVal = false;
//			}
//		}
//	}
	
	/**
	 * ��¼�ļ� ������Ϣ 
	 * @param fileName
	 * @param content
	 */

	public  static void  setLog(String content) {
		FileOutputStream out = null;
		try {
			Date date = new Date() ;
			String [] strDates = date.toLocaleString().split(" ");
			String strFileName = System.getProperty("user.dir").toString() + "/Log/" +strDates[0]+ "-ErrorURL.txt";
			File file = new File(System.getProperty("user.dir").toString() + "/Log");
			if (!file.exists()) {
				file.mkdir();
			}
			content=content+"\n";
			out = new FileOutputStream(strFileName,true);
            byte[] contentInBytes = content.getBytes();
            out.write(contentInBytes);
            out.flush();
            out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����ͬ��ǩ�ڵ���������
	 * @param strText ��ҳԴ���ַ���
	 * @param strStart ��ʼ��ǩ
	 * @param strEnd  ������ǩ
	 * @param strAdd ���������Ϊnull
	 * @return list ��������List
	 */
	public static ArrayList<String> getTags(String strText, String strStart,String strEnd, String strAdd) {

		ArrayList<String> list = new ArrayList<String>();
		boolean bVal = true;
		int iNum = 0;
		int iStart = 0;
		int iEnd = 0;
		String strTemp = "";
		
		while (bVal) {

			iStart = strText.indexOf(strStart, iNum);

			if (iStart != -1) {
				iEnd = strText.indexOf(strEnd, iStart);
				if (iEnd != -1) {
					iNum = iEnd;
					strTemp = strText.substring(iStart + strStart.length(),iEnd);
					if (strAdd != null) {
						list.add(strAdd + strTemp);
					} else {
						list.add(strTemp);
					}
				}
			} else {
				bVal = false;
			}
		}
		return list;
	}
	
	/**
	 * ���һ��֮������������
	 * @param year
	 * @return �����б�
	 */
	
	public static ArrayList<String> getSearchDate(String strStartTime, String strEndTime) {

		ArrayList<String> listDate = new ArrayList<String>();

		Calendar calendar = new GregorianCalendar();// ����һ��������������Ϊ���
		Calendar calendarEnd = new GregorianCalendar();// ����һ��������������Ϊ��ĩ

		String [] strStartTimes = strStartTime.split("-");
		String [] strEndTimes = strEndTime.split("-");
		
		calendar.set(Calendar.YEAR, Integer.parseInt(strStartTimes[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(strStartTimes[1])-1);
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(strStartTimes[2]));// �������������Ϊ1��1��
		
		calendarEnd.set(Calendar.YEAR, Integer.parseInt(strEndTimes[0]));
		calendarEnd.set(Calendar.MONTH, Integer.parseInt(strEndTimes[1])-1);
		calendarEnd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strEndTimes[2]));// ������ĩ������Ϊ12��31��

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

		while (calendar.getTime().getTime() <= calendarEnd.getTime().getTime()) {// ��һ���������ѭ��
			if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {// �ж����Ϊ������ʱ����ӡ
				listDate.add(sf.format(calendar.getTime()).toString());
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);// ����+1
		}
		return listDate;
	}  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String strNEW = CommonUtil.turnDate3("17.01.2013");
		
	//	String strHtml = CommonUtil.openUrl("http://www.ebnew.com/businessShow-v-id-421834359.html", "utf-8");
		
	//	System.out.println(strHtml);
//		Date date = new Date ();
//		String [] strDates = date.toLocaleString().split(" ");
//		
//		System.out.println(strDates[0]);
		String strMess="log";
		setLog(strMess);
		
	}

}
