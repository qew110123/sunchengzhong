package com.artsoft.util;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URL;  
import java.net.URLConnection;  

public class DownloadImage {
	
	public static void download(String urlString, String filename,String savePath) throws Exception {  
        // ����URL  
        URL url = new URL(urlString);  
        // ������  
        URLConnection con = url.openConnection();  
        //��������ʱΪ5s  
        con.setConnectTimeout(5*1000);  
        // ������  
        InputStream is = con.getInputStream();  
      
        // 1K�����ݻ���  
        byte[] bs = new byte[1024];  
        // ��ȡ�������ݳ���  
        int len;  
        // ������ļ���  
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
        // ��ʼ��ȡ  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        // ��ϣ��ر���������  
        os.close();  
        is.close();  
    }  
	
	public static void main(String[] args) {
		try {
			download("http://mmbiz.qpic.cn/mmbiz/ibgJia49E9UKgDutSyVAicsY5oKLH8flMETicEMEkmCmaElDGqCuE9tibxvF7YmV8qCb9micxdDoTT1YuBs1PGngmevQ/0?wx_fmt=jpeg", "ibgJia49E9UKgDutSyVAicsY5oKLH8flMETicEMEkmCmaElDGqCuE9tibxvF7YmV8qCb9micxdDoTT1YuBs1PGngmevQ.jpg", "D:\\Image\\��Ա��Ƹ��image20151211\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
