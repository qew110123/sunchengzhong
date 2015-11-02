package com.artsoft.util;

public class Stirngstr {
	
	public static String  stringInt(String str){
		
//		String str = "love23next234csdn3423javaeye";
		str = str.trim();
		String str2 = "";
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2 += str.charAt(i);
				}
			}

		}
		System.out.println(str2);
		return str2;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
