package com.artsoft.config;

import java.util.Vector;

public class DownTask {
	
	static Vector<String> vTask = new Vector<String>();

	/**
	 * 获得任务列表大小
	 * @return
	 */
	public synchronized static int getVecSize() {
		int iSize = vTask.size();
		return iSize;
	}

	/**
	 * 添加任务
	 * @param strUrL
	 */
	public static void addTask(String strUrL) {
		vTask.add(strUrL);
	}

	/**
	 * 获得任务
	 * @return
	 */
	public synchronized static String getTask() {

		String strTask = vTask.firstElement();
		vTask.removeElementAt(0);
		return strTask;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DownTask.addTask("asdfadsf");
		String str = DownTask.getTask();
		System.out.println(str);
		System.out.println(DownTask.getVecSize());
	}

}
