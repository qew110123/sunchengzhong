package com.artsoft.config;

import java.util.Vector;

public class DownTask {
	
	static Vector<String> vTask = new Vector<String>();

	/**
	 * ��������б��С
	 * @return
	 */
	public synchronized static int getVecSize() {
		int iSize = vTask.size();
		return iSize;
	}

	/**
	 * �������
	 * @param strUrL
	 */
	public static void addTask(String strUrL) {
		vTask.add(strUrL);
	}

	/**
	 * �������
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
