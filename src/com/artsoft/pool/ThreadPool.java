package com.artsoft.pool;

import java.util.LinkedList;
/**
 * �̳߳أ��̳�ThreadGroup��
 * ThreadGroup���ڴ���һ���̵߳��࣬����һ����״�ṹ,�����²�ڵ㻹������ThreadGroup����
 * @author lusd 
 * @Date   2011-4-18
 */
public class ThreadPool extends ThreadGroup {

	/**	��־�̳߳��Ƿ���	*/
	private boolean isAlive;
	/**	�̳߳��е��������	*/
	private LinkedList taskQueue;
	/**	�̳߳��е��߳�ID	*/
	private int threadID;
	/**	�̳߳�ID	*/
	private static int threadPoolID;
	/**	��ǰ�����̸߳���*/
	static int iRunNum = 0;

	/**
	 * @author lusd 
	 * @Date   2011-4-18
	 * @param numThreads
	 */
	public ThreadPool(int iThreads) {
		super("ThreadPool-" + (threadPoolID++));
		//����Ϊ���̳߳��ǵ�daemon����Ϊtrue��
		//��ʾ�����̳߳��������̶߳�������ʱ�����̳߳ػ��Զ�������
		super.setDaemon(true);
		this.isAlive = true;
		
		//�½�һ���������
		this.taskQueue = new LinkedList();
		
		//����numThreads�������߳�
		for (int i = 0; i < iThreads; i++) {
			PooledThread thread = new PooledThread();
			thread.setPriority(10);
			thread.start();
		}
	}
	
	/**
	 * �ж��Ƿ���ڴ���
	 * @author lusd 2011-4-18 
	 * @return
	 */
	public boolean isPoolEmpty(){
		if(taskQueue.size() == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * ��ȡ�̳߳����߳�����
	 * @return
	 */
	public int getPoolNum(){
		return taskQueue.size();
	}
	
	/**
	 * �ж��Ƿ����������е��߳�
	 * @author lusd 2011-4-18 
	 * @return
	 */
	public boolean isRunThread(){
		if(iRunNum == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * ���������
	 * @author lusd 2011-4-18 
	 * @param task
	 */
	public synchronized void performTask(TaskInterface task) {
		if (!this.isAlive) {
			//	�̱߳������׳�IllegalStateException�쳣
			throw new IllegalStateException();
		}
		if (task != null) {
			//������ŵ�������е�β��
			this.taskQueue.add(task);
			//֪ͨ�����߳�ȡ����
			notify();
		}
	}

	/**
	 * ��ȡ����
	 * @author lusd 2011-4-18 
	 * @return
	 * @throws InterruptedException
	 */
	protected synchronized TaskInterface getTask() throws InterruptedException {
		//��������б�Ϊ�գ������̳߳�û�б��رգ�������ȴ�����
		while (this.taskQueue.size() == 0) {
			if (!this.isAlive) {
				return null;
			}
			wait();
		}
		//ȡ�����б�ĵ�һ������
		return (TaskInterface) this.taskQueue.removeFirst();
	}

	/**
	 * �ر��̳߳أ������߳�ֹͣ������ִ������
	 * @author lusd 2011-4-18
	 */
	public synchronized void close() {
		if (isAlive) {
			this.isAlive = false;
			//�������
			this.taskQueue.clear();
			//��ֹ�̳߳��������߳�
			this.interrupt();
		}
	}

	/**
	 * �ر��̳߳أ����ȴ��̳߳��е��������������ꡣ
	 * ���ǲ��ܽ����µ�����
	 * @author lusd 2011-4-18
	 */
	public void join() {
		//֪ͨ�����ȴ��̡߳����̳߳��ѹرա�����Ϣ
		synchronized (this) {
			isAlive = false;
			notifyAll();
		}
		// �ȴ������߳����
		// ���Ƚ���һ���µ��߳����顣activeCount������ȡ�̳߳��л�̵߳Ĺ�����
		Thread[] threads = new Thread[this.activeCount()];
		// ���̳߳��еĻ�߳̿������´������߳�����threads�С�
		int count = this.enumerate(threads);
		for (int i = 0; i < count; i++) {
			try {
				// �ȴ��߳����н���
				threads[i].join();
			} catch (InterruptedException ex) {
			}
		}
	}

	/**
	 * �ڲ��࣬����ִ������Ĺ����߳�
	 * @author lusd 
	 * @Date   2011-4-18
	 */
	private class PooledThread extends Thread {

		//���췽��
		public PooledThread() {
			//��һ������Ϊ���߳����ڵ��߳�����󣬼���ǰ�̳߳ض���
			//�ڶ�������Ϊ�߳�����
			super(ThreadPool.this, "PooledThread-" + (threadID++));
		}

		public void run() {
			
			//������߳�û�б���ֹ
			while (!isInterrupted()) {
				// ��ȡ����
				TaskInterface task = null;
				try {
					task = getTask();
				} catch (InterruptedException ex) {
				}

				//ֻҪ�̳߳ص������б�Ϊ�գ�getTask�������ܵõ�һ������
				//��getTask()����null�����ʾ�̳߳����Ѿ�û�����񣬶����̳߳��ѱ��رա�
				if (task == null) {
					return;
				}

				// �������������쳣
				try {
					iRunNum++;
					task.perform();
					iRunNum--;
				} catch (Throwable t) {
					//���߳����е��߳���δ��������쳣����ʱ��JVM�ͻ�ȥ����uncaughtException������
					uncaughtException(this, t);
					iRunNum--;
				}
			}
		}
	}
}