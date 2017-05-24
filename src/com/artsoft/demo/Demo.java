package com.artsoft.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import WanfangBykeyWordMain.WanfangBykeyWordMain;
import oracle.security.o3logon.b;

public class Demo {
	public static String getKeyWordFromFile(String FileName) {
		// FileName : Config/keyword.txt
		StringBuilder strTemp = new StringBuilder();
		// String FilePath = System.getProperty("user.dir")+
		// "\\Config\\"+FileName;
		String FilePath = "config/" + FileName;
		File file = new File(FilePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				strTemp.append(tempString);
				strTemp.append("\n");
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
		return strTemp.toString();
	}
	
	
	public boolean convertHtmlToPdf(String inputFile, String outputFile)  
		    throws Exception {  
		        return true;  
		    }  
	
	
	
	public static void download(String urlString, String filename,String savePath) throws Exception {  
        // 构造URL  
        URL url = new URL(urlString);  
        // 打开连接  
        URLConnection con = url.openConnection();  
        //设置请求超时为5s  
        con.setConnectTimeout(5*1000);  
        
        
        con.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
        con.setRequestProperty("Referer", "http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=detail&fr=&sf=1&fmq=1449734028010_R&pv=&ic=0&nc=1&z=&se=&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E5%86%AF%E5%B7%A9%E5%A4%B4%E5%83%8F&f=3&oq=%E5%86%AF%E5%B7%A9tou&rsp=0");
        // 输入流  
        InputStream is = con.getInputStream();  
      
        // 1K的数据缓冲  
        byte[] bs = new byte[1024];  
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
	
	private static Proxy proxy(String strIp, String strPort) {
		InetSocketAddress addr = new InetSocketAddress(strIp, Integer.valueOf(strPort));
		Proxy proxydata = new Proxy(Proxy.Type.HTTP, addr);
		return proxydata;
	}
	
	public static int getvalue(int i){
		int result=0;
		switch (i) {
		case 1:
			result=result+i;
		case 2:
			result=result+i*2;
		case 3:
			result=result+i*3;
//			break;

//		default:
//			break;
		}
		return result;
	}
	
	String s;

	
	
	
	 private static int solve(int[] arr, int n) {
	        int left = 0;
	        int right = n - 1;
	        int ans = 0;
	        while (left < right) {
	            if (arr[left] < arr[right]) {
	                arr[left + 1] += arr[left];
	                ++left;
	                ++ans;
	            }else if (arr[left] > arr[right]){
	                arr[right - 1] += arr[right];
	                --right;
	                ++ans;
	            }else {
	                ++left;
	                --right;
	            }
	        }
	        return ans;
	    }
	void Mystery(){
		s=" constructor";
	}


	private static void change(String s, StringBuffer sb) {
		// TODO Auto-generated method stub
		s="aaaa";
		sb.setLength(0);
		sb.append("aaaa");
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(getvalue(2));
//		System.out.println(Double.parseDouble("-1"));
//		WanfangBykeyWordMain.Keywordbig_xuanze(Integer.valueOf(1));
		
//		String str1="abc";
//		String str2="abc";
//		System.out.println(str1==str2);
//		
//		String str3 = new String ("abd");
//		System.out.println(str1==str3);
//		
//		
//		str1.concat("def");
//		System.out.println(str1);
//		
//		StringBuffer sb= new StringBuffer("abc");
//		sb.append("deff");
//		System.out.println(sb);
//		
//		System.out.println(5<<2);
//		
//		String aa="/////";
//		System.out.println(aa.replace("//", "/"));
//		String d ="bookkeeper";
//		d.substring(1,7);
//		d="w"+d;
////		d.append("woo");
//		System.out.println(d);
		
//		String s="bbbb";
//		
//		StringBuffer sb=new StringBuffer("bbbb");
//		change(s,sb);
//		System.out.println(s+sb);
		
//		Mystery m =new Mystery();
//		 int x[]= new int[15];
//		System.out.println(x[5]);
//		float foo=0x0123;
		
		
//		int a =0;
//		int c=0;
//		do {
//			--c;
//			a=a-1;
//			System.out.println(a);
//			
//		} while (a>0);
//		System.out.println(c);
		
		
//		 Scanner scan = new Scanner(System.in);
//	        while (scan.hasNext()) {
//	            int n = scan.nextInt();
//	            int[] arr = new int[n];
//	            for (int i = 0; i < n; i++) {
//	                arr[i] = scan.nextInt();
//	            }
//	            System.out.println(solve(arr,n));
//	        }
//	        scan.close();
		
		
		String strHtml = DownloadUtil.getHtmlText("https://search.weixin.qq.com/cgi-bin/searchweb/clientjump?tag=wxindex_detail&q=%E8%8C%83%E5%86%B0%E5%86%B0&exportkey=AQf44876WvSdEDeiyxYvzYQ%3D&pass_ticket=nWMDGVxg0TigieEIiXKmdTZxLsZUnqFeIJPUXp6fCIo%3D", 1000 * 30, "UTF-8", null, null);
		
		System.out.println(strHtml);
		
		
	}

}
