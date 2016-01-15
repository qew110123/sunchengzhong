package com.artsoft.admin;

import com.artsoft.download.TVPlay.Download;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

public class admin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ConfigManager config = ConfigManager.getInstance();
//		String url=config.getConfigValue("urls");
//		System.out.println(url);
//		while (true) {
//			String strurl=Download.youkuMaim(url);
//			System.out.println("strurl"+strurl);
//			if (strurl!=null&&!"".equals(strurl)) {
//				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+strurl);
//				url=strurl;
//			};
//			
//		}
		
		String strkey=ReadTxtFile.getKeyWordFromFile("keyword.txt");
		String[] keys=strkey.split("\n");
		for (int i = 0; i < keys.length; i++) {
			System.out.println(i);
			System.out.println(keys[i]);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+keys[i]);
//			ConfigManager config = ConfigManager.getInstance();
			String url=keys[i];
			System.out.println(url);
			boolean bb=true;
			while (bb) {
				String strurl=Download.youkuMaim(url);
				System.out.println("strurl"+strurl);
//				System.out.println(strurl!=null&&!"".equals(strurl));
				if (strurl!=null&&!"".equals(strurl)) {
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+strurl);
					url=strurl;
				}else{
					bb=false;
				}
				
			}
		}

	}

}
