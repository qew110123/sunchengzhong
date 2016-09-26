package com.artsoft.util;

import java.util.regex.Matcher;

import org.jaxen.pattern.Pattern;

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
	
	
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	public static boolean isNumericascii(String str){  
		   for(int i=str.length();--i>=0;){  
		      int chr=str.charAt(i);  
		      if(chr<48 || chr>57)  
		         return false;  
		   }  
		   return true;  
		}   
	
	public static boolean isNumeribaidu(String str){  
		 try {
	    	  //Integer num = Integer.valueOf(str);
	    	  Double num2 = Double.valueOf(str);
	    	  //System.out.println("Is Number!" + num);
	    	  System.out.println("Is Number!" + num2);
	    	  return true; 
	    	} catch (Exception e) {
	    	  System.out.println("Is not Number!");
	    	  return false;  
	    	}
//		   return true;  
		}  
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isNumeric("-687"));
		
		int xx=Integer.valueOf("-687");
		System.out.println(isNumericascii("6.87"));
		
		String str="-3333322221111Äê";
		 Boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
		    if(strResult == true) {
		      System.out.println("Is Number!");
		    } else {
		      System.out.println("Is not Number!");
		    }
		    
		    
		    try {
		    	  //Integer num = Integer.valueOf(str);
		    	  Double num2 = Double.valueOf(str);
		    	  //System.out.println("Is Number!" + num);
		    	  System.out.println("Is Number!" + num2);
		    	} catch (Exception e) {
		    	  System.out.println("Is not Number!");
		    	}
	}

}
