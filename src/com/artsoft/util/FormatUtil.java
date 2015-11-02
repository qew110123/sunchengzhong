package com.artsoft.util;


import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {
	public static String fromNCR(String strText) {
    	String strRegEx = "&#(\\d+);";
    	Pattern p= Pattern.compile(strRegEx);
    	Matcher m= p.matcher(strText);
    	/*if (!m.find())
    		return strText;*/
    	/*for(int i=0; i <= m.groupCount(); i++){*/
    	while(m.find()) {
    		String strTemp = m.group(0);
    		String newChar = getNcrChar(strTemp);
    		if (newChar != null) {
    			strText = strText.replace(strTemp, Matcher.quoteReplacement(newChar));
    			m= p.matcher(strText);
    		}
    	}
    	
    	return strText;
    }
    
    private static String getNcrChar(String strNcr) {
    	strNcr = strNcr.substring(2, strNcr.length() - 1);
    	char c = (char)Integer.parseInt(strNcr); 
    	return String.valueOf(c);
    }
    
    /**
	 * 解决字符串截取内存泄漏问题
	 * @param original 原字符串
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return 字符串
	 */
	public static String subText(String original, int start, int end) {
		return new String(original.substring(start, end)); 
	}
	
	/**
	 *<br>
	 * 方法说明：判断字符串是否是代理服务器地址 <br>
	 * 输入参数：newText 字符串 <br>
	 * 返回类型：boolean
	 */
	public static String proxyValid(String newText) {
		if (newText == null || newText.length() == 0)
			return "你输入的代理服务器地址不能为空！";
		StringTokenizer st = new StringTokenizer(newText, ":");
		String strAddress = null;
		if (st.hasMoreTokens()) {
			strAddress = st.nextToken();
		}
		String strPort = null;
		if (st.hasMoreTokens()) {
			strPort = st.nextToken();
		}
		if (strAddress == null || strPort == null)
			return "你输入的代理服务器地址格式错误";
		int nPort = -1;
		try {
			nPort = Integer.parseInt(strPort);
			if (nPort == -1)
				return "你输入的代理服务器地址格式错误";
		} catch (java.lang.NumberFormatException e) {
			return "你输入的代理服务器地址格式错误";
		}
		String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
		String regex1 = "1\\d{2}";
		String regex2 = "[1-9]\\d";
		String regex3 = "\\d";
		String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
				+ regex3 + ")";
		regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
				+ ")";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(strAddress);
		if (m.matches())
			return null;
		return "你输入的代理服务器地址格式错误";
	}
}
