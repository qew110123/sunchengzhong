package com.artsoft.oracle2;
/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		if(str == null) {
			return true;			
		}else {
			String strObj = str.toString().toLowerCase().trim();
			if(strObj.equals("") || strObj.equals("null") || strObj.equals("\"null\"") || strObj.equals("'null'")) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object str) {
		if(str == null) {
			return false;			
		}else {
			String strObj = str.toString();
			if(strObj.equals("") || strObj.equals("null") || strObj.equals(" ")) {
				return false;
			}
			return true;
		}
	}
	
	/**将对象转化为字符串，如果对象为null,返回""
	 * @param obj 对象
	 * @return  转化的字符串
	 */
	public static String nullToString(Object obj) {
		return nullToString(obj, "");
	}

	/**将对象转化为字符串，如果对象为null,返回tip
	 * @param obj 对象
	 * @param tip 提示信息
	 * @return 转化的字符串
	 */
	public static String nullToString(Object obj, String tip) {

		if (obj == null) {
			return tip;
		}

		String temp = obj.toString().trim();
		if (temp.equals("") || temp.equals("null")) {
			return tip;
		} else {
			return temp;
		}

	}
}
