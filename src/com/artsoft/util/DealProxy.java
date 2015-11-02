package com.artsoft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.artsoft.config.ConfigWip;


/**
 * �Զ�����������
 */
public class DealProxy {
	private static String Path; // �����ļ�����
	private static String FilePath; // �����ļ�·��
	static int iNum = 0;
	static ArrayList<Proxy> ProxyList = new ArrayList<Proxy>(); //����List ���������ļ���ȡ�Ĵ���
	Random rn =new Random();
	private static final DealProxy single = new DealProxy(); 
	 //��̬��������  
	 public static DealProxy getInstance() {
	 return single;
	 }
	 
	public  Proxy getPoxxy(){
		
		Proxy proxy = (Proxy) ProxyList.get(rn.nextInt(iNum));
		
		return proxy;
	}
	public DealProxy(){
		setProxy();
	}
	/**
	 * ��ȡ �����ļ� �д����ļ�·��
	 * */
	public static void setData() {
		Path = ConfigWip.getInstance().getConfigValue("ProxyPath");
		FilePath = System.getProperty("user.dir") + "/config/"+ Path;
	}
	
	/**
	 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
	 * @param fileName �ļ���
	 * @param strSeparator ����ʹ�÷ָ��� return map ip = �˿ں�
	 */
	public static HashMap<String, String> readFileByLines(String fileName, String strSeparator) {

		if (fileName == null || strSeparator == null) {
			System.out.println("��Ϣ��������");
			return null;
		}
		File file = new File(fileName);
		BufferedReader reader = null;
		HashMap<String, String> mpProxy = new HashMap<String, String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains(strSeparator)) {
					String[] Proxy = tempString.split(strSeparator);
					if (Proxy.length == 2) {
						mpProxy.put(Proxy[0], Proxy[1]);
					}
				}
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return mpProxy;
	}
	
	/**
	 * �������
	 */
	public static void setProxy() {
		setData();
		Map<?, ?> map = new HashMap();
		map = readFileByLines(FilePath, ":");
		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext()) {
			String strIp = (String) it.next();
			String strPort = (String) map.get(strIp);
			InetSocketAddress addr = new InetSocketAddress(strIp, Integer.valueOf(strPort));
			Proxy proxydata = new Proxy(Proxy.Type.HTTP, addr);
			ProxyList.add(proxydata);
		}
		iNum=ProxyList.size();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DealProxy aa = new DealProxy();
		Proxy a = DealProxy.getInstance().getPoxxy();
		DealProxy.setProxy();
	}
}
