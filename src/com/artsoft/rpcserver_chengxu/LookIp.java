package com.artsoft.rpcserver_chengxu;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LookIp {
	
	public static  String  getIp(){
		String ip="127.0.0.1";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println(ip);
		return ip;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getIp();

	}

}
