package com.artsoft.mythread;

import java.util.LinkedList;
import java.util.List;

public class MyThread implements Runnable {
//	public int ticket = 1000;
	private  LinkedList linkedList = new LinkedList();
	
	
	public void MyThread1(LinkedList lists) 
	{ 
//		this.linkedList=lists;
		while (lists.size()>0) {
			this.linkedList.add(lists.removeFirst());
		}
		System.out.println(this.linkedList);
	} 
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// for (int i = 0; i < 20; i++) {
		// if (this.ticket > 0) {
		// System.out.println(Thread.currentThread().getName());
		// System.out.println("卖票：ticket" + this.ticket--);
		// }

		// }
//		while (this.ticket > 0) {
//			System.out.println(Thread.currentThread().getName());
//			System.out.println("卖票：ticket" + this.ticket--);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		if ("0".equals(Thread.currentThread().getName())) {
			this.linkedList.add("1");
//			linkedList.add("")
		}
		else{
//			System.out.println(this.linkedList.removeFirst());
			
			while (true) {
				if(this.linkedList.size()>0){
				System.out.println(Thread.currentThread().getName());
				System.out.println(this.linkedList.removeFirst());
				}
			}
			
		}
			

	}

	public static void bbstatic(String bb) {
		LinkedList listss=new LinkedList();
		listss.add("11111");
		listss.add("222222");
		listss.add("3333");
		listss.add("44444");
		listss.add("55555");
		
		MyThread mt = new MyThread();
		MyThread mt1 = new MyThread();
//		 new Thread(mt).start();//同一个mt，但是在Thread中就不可以，如果用同一
//		 new Thread(mt1).start();//个实例化对象mt，就会出现异常
		// new Thread(mt).start();
		mt.MyThread1(listss);
		LinkedList listsss=new LinkedList();
		listsss.add("11111");
		listsss.add("222222");
		listsss.add("3333");
		listsss.add("44444");
		listsss.add("5555566");
		mt1.MyThread1(listsss);
		Thread thread1 = new Thread(mt);
		Thread thread2 = new Thread(mt1);
////		thread2.setName("MyThread2");
		thread1.start();
//		thread1.start();
//		thread2.start();
		System.out.println("#####");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listsss);
		 listsss=new LinkedList();
		listsss.add("11111");
		listsss.add("222222");
		listsss.add("3333");
		listsss.add("44444");
		listsss.add("5555566");
		
		MyThread mt3 = new MyThread();
		mt3.MyThread1(listsss);
		// MyThread mt1=new MyThread();
		// new Thread(mt1).start();//同一个mt，但是在Thread中就不可以，如果用同一
		// new Thread(mt1).start();//个实例化对象mt，就会出现异常
		// new Thread(mt1).start();
		
//		MyThread thread = new MyThread(listss); 
////		thread.start(); 
//		new Thread(thread).start();
////		new Thread(thread).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1; i++) {
			bbstatic("");
			// System.out.println(i+"***********");
		}
	}
}
