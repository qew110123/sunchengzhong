package com.artsoft.demo;

import java.util.concurrent.Callable;  
import java.util.concurrent.ExecutionException;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.FutureTask;  
import java.util.concurrent.TimeUnit;  
import java.util.concurrent.TimeoutException;  

public class TestFurther {
	public static void rungood(){
		System.out.println("不告诉你。");  
        try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("8888888888");  
		System.out.println("22222222222222");
	}
	
	public static void runmain(){
		 ExecutorService executorService = Executors.newFixedThreadPool(1);  
	        FutureTask<Boolean> futureTask = new FutureTask<>(  
	                new Callable<Boolean>() {  
	                    @Override  
	                    public Boolean call() throws Exception {  
	                    		try {
									
	                    			rungood();
								} catch (Exception e) {
									// TODO: handle exception
									return false;  
								}
								
	                        return true;  
	                    }  
	                });  
	        try {  
	            executorService.submit(futureTask).get(4, TimeUnit.SECONDS);  
	            
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (TimeoutException e) {  
	            System.out.println("超时了吧~~~");  
	            e.printStackTrace();  
	            executorService.shutdownNow(); 
	        } 
//	        return true; 
	}
	  public static void main(String[] args) {  
		  for (int i = 0; i < 10; i++) {
			  runmain();
		  }
	   }  

}
