package com.artsoft.util;

public class StringTool {
	
	
	public static String getStrVal(Object obj) {
		
		if(obj == null)
			return "";
		else
			return obj.toString().trim();
	}
	
	public static String turnSecToHms(long ms){
		long  s;//秒  
  		long  h;//小时  
  		long  m;//分钟  
  		long  d;//天
  		d=ms/1000/60/60/24;
  		h=(ms-d*60*60*1000*24)/1000/60/60;  
  		m=(ms-d*60*60*1000*24-h*60*60*1000)/1000/60;  
  		s=ms/1000-d*60*60*24-h*60*60-m*60;  
  		return  d+"天 "+h+"小时 "+m+"分钟 "+s+"秒";
	}
}

