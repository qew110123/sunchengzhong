package com.artsoft.admin;

import com.artsoft.download.news_num.baidu_newsnum;

public class Thread implements Runnable {
	private String name;

	public Thread(String name) {
		this.name=name;
	}

	@Override
	public void run() {
//		  for (int i = 0; i < 5; i++) {
//	            System.out.println(name + "ÔËÐÐ  :  " + i);
//	            try {
//	            	Thread.sleep((int) Math.random() * 10);
//	            } catch (InterruptedException e) {
//	                e.printStackTrace();
//	            }
//	        }
		  
		  if (name.equals("peoplenews")) {
			  baidu_newsnum.peoplenews();
		}
		  if (name.equals("tvplaynews")) {
			  baidu_newsnum.tvplaynews();
		}
		
	}
	
}
 
