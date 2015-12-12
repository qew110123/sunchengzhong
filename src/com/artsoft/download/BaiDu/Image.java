package com.artsoft.download.BaiDu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.artsoft.oracle.OracleBaidu;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.TimeTest;

public class Image {
	
	
	public static void download(String urlString, String filename,String savePath) throws Exception {  
        // 构造URL  
        URL url = new URL(urlString);  
        // 打开连接  
        URLConnection con = url.openConnection();  
        //设置请求超时为5s  
        con.setConnectTimeout(5*1000);  
        
        
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Connection", "keep-alive");
        
        con.setRequestProperty("Cookie", "__cfduid=d0be9c66cfcb2acf816d36b18a964d4441449813824; PHPSESSID=1omap3grcrofjfoe2kqd7tj0f6; Hm_lvt_a2e69a8741c0e16a5f1e2cbe733dedee=1449813896; Hm_lpvt_a2e69a8741c0e16a5f1e2cbe733dedee=1449813896; Hm_lvt_d8140bd5fd75af5af358ac7ea5754323=1449813827; Hm_lpvt_d8140bd5fd75af5af358ac7ea5754323=1449821990");
        con.setRequestProperty("Host", "www.yanyuanzhaopin.com");
        con.setRequestProperty("If-Modified-Since", "Sun, 12 Jan 2014 11:05:02 GMT");
        con.setRequestProperty("If-None-Match", "\"0968fb85fcf1:64c0\"");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
//        con.setRequestProperty("Referer", "http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=detail&fr=&sf=1&fmq=1449734028010_R&pv=&ic=0&nc=1&z=&se=&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E5%86%AF%E5%B7%A9%E5%A4%B4%E5%83%8F&f=3&oq=%E5%86%AF%E5%B7%A9tou&rsp=0");
        // 输入流  
        InputStream is = con.getInputStream();  
      
        // 1K的数据缓冲  
//        byte[] bs = new byte[1024];  
        byte[] bs = new byte[5120];  
        // 读取到的数据长度  
        int len;  
        // 输出的文件流  
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
        // 开始读取  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        // 完毕，关闭所有链接  
        os.close();  
        is.close();  
    } 

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// try {
		// DownloadImage.download("http://hiphotos.baidu.com/zhixin/abpic/item/ac754782b2b7d0a21ced714ac9ef76094a369a85.jpg",
		// "赵寅成.jpg","D:\\image\\");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

		List<String> listArray = OracleBaidu.selectImage();
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);
		int i=0;
		for (Object Objstring : listArray) {

			// System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				try {
					System.out.println(++i);
					if (i<1352) {
						continue;
					}
					String[] namelist=listTemp.get(0).split("/");
					System.out.println(namelist[namelist.length-1]);
//					DownloadImage.download(listTemp.get(0), namelist[namelist.length-1], "D:\\Image\\演员招聘网image20151211\\");
					download(listTemp.get(0), namelist[namelist.length-1], "D:\\Image\\演员招聘网image20151211\\");
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(listTemp.get(0));
//					System.out.println(listTemp.get(1));
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + listTemp.get(0));
				}
			}
		}

	}
}
