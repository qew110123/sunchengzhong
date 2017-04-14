package com.artsoft.demo;

import java.util.concurrent.*;

/** 
* Java�̣߳��з���ֵ���߳� 
* 
* @author Administrator 2009-11-5 0:41:50 
*/ 
public class Test { 
	static ExecutorService pool = Executors.newFixedThreadPool(2); 
        public static void main(String[] args) throws ExecutionException, InterruptedException { 
                //����һ���̳߳� 
                //���������з���ֵ������ 
                Callable c1 = new MyCallable("A"); 
                Callable c2 = new MyCallable("B"); 
                //ִ�����񲢻�ȡFuture���� 
                Future f1 = pool.submit(c1); 
                Future f2 = pool.submit(c2); 
                try {
					f1.get(3000, TimeUnit.SECONDS);
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //��Future�����ϻ�ȡ����ķ���ֵ�������������̨ 
                System.out.println(">>>"+f1.get().toString()); 
                System.out.println(">>>"+f2.get().toString()); 
                //�ر��̳߳� 
//                pool.shutdown(); 
        } 
        
        
        
} 

class MyCallable implements Callable{ 
        private String oid; 

        MyCallable(String oid) { 
                this.oid = oid; 
        } 

        @Override 
        public Object call() throws Exception { 
        		System.out.println("�ȴ�3s��");
        		Thread.sleep(3000);
        	
                return oid+"���񷵻ص�����"; 
        } 
}
