package com.artsoft.demo.imag;

import com.artsoft.util.TimeTest;
import com.artsoft.util.ftp.FavFTPUtil;

public class ftp_pathname {
	static String hostname = "192.168.1.18";
 	static int port = 21;
 	static String username = "shareuser";
 	static String password = "shareuser18";
	
	/**
 	 * ftp ���Ӿ� ����  
 	 * 2016��9��26��17:38:23
 	 */
	public static void FavFTPUtilbig(String filename) {
//		String hostname = "192.168.1.18";
//		int port = 21;
//		String username = "shareuser";
//		String password = "shareuser18";
		///tvplay/stills/
		String pathname = "/tvplay/stills/big";
//		String filename = "1.jpg";
		String originfilename = "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\tvplay\\stills\\big\\"+filename;
		FavFTPUtil.uploadFileFromProduction(hostname, port, username, password, pathname, filename, originfilename);
		System.out.println("contents�ϴ��ɹ�");
		
	}
	
	/**
 	 * ftp ���Ӿ� ����  
 	 * 2016��9��26��17:38:23
 	 * SMALL
 	 */
	public static void FavFTPUtilsmall(String filename) {
//		String hostname = "192.168.1.18";
//		int port = 21;
//		String username = "shareuser";
//		String password = "shareuser18";
		///tvplay/stills/
		String pathname = "/tvplay/stills/small";
//		String filename = "1.jpg";
		String originfilename = "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\tvplay\\stills\\small\\"+filename;
		FavFTPUtil.uploadFileFromProduction(hostname, port, username, password, pathname, filename, originfilename);
		System.out.println("contents�ϴ��ɹ�");
		
	}

}
