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
	 * ����ַ�����ȡ�ڴ�й©����
	 * @param original ԭ�ַ���
	 * @param start ��ʼλ��
	 * @param end ����λ��
	 * @return �ַ���
	 */
	public static String subText(String original, int start, int end) {
		return new String(original.substring(start, end)); 
	}
	
	/**
	 *<br>
	 * ����˵�����ж��ַ����Ƿ��Ǵ����������ַ <br>
	 * ���������newText �ַ��� <br>
	 * �������ͣ�boolean
	 */
	public static String proxyValid(String newText) {
		if (newText == null || newText.length() == 0)
			return "������Ĵ����������ַ����Ϊ�գ�";
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
			return "������Ĵ����������ַ��ʽ����";
		int nPort = -1;
		try {
			nPort = Integer.parseInt(strPort);
			if (nPort == -1)
				return "������Ĵ����������ַ��ʽ����";
		} catch (java.lang.NumberFormatException e) {
			return "������Ĵ����������ַ��ʽ����";
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
		return "������Ĵ����������ַ��ʽ����";
	}
}
