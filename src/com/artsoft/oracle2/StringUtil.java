package com.artsoft.oracle2;
/**
 * �ַ�����ط���
 *
 */
public class StringUtil {

	/**
	 * ���Զ��ŷָ����ַ���ת�����ַ�������
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
	 * �ж��ַ����Ƿ�Ϊ��
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
	 * �ж��ַ�����Ϊ��
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
	
	/**������ת��Ϊ�ַ������������Ϊnull,����""
	 * @param obj ����
	 * @return  ת�����ַ���
	 */
	public static String nullToString(Object obj) {
		return nullToString(obj, "");
	}

	/**������ת��Ϊ�ַ������������Ϊnull,����tip
	 * @param obj ����
	 * @param tip ��ʾ��Ϣ
	 * @return ת�����ַ���
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
