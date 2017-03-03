package com.artsoft.demo;


//import com.alibaba.fastjson.JSONArray;  
//import com.alibaba.fastjson.JSONObject;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  


public class LoadImageClient {
	
	 CloseableHttpClient httpclient = HttpClients.createDefault();  
	  
	    public HttpGet doGet(String url) {  
	        HttpGet httpGet = new HttpGet(url);  
	        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
	        httpGet.addHeader("Connection", "Keep-Alive");  
	        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");  
	        httpGet.addHeader("Cookie", "");  
	        return httpGet;  
	  
	    }  
	  
	    public void loadImage(int tid) {  
	  
	        CloseableHttpResponse response = null;  
	        CloseableHttpResponse resImg = null;  
	        try {  
	  
	            HttpGet httpGet = doGet("http://www.xxx.com/tt?tid=" + tid);  
	  
	            response = execute(httpGet);  
	  
	            if (response == null){  
	                return;  
	            }  
	  
	  
	            String result = EntityUtils.toString(response.getEntity());  
	  
	            JSONObject json = new JSONObject();  
	            JSONObject jsonObject = (JSONObject) json.get(result.toString().trim());  
	            Object files = jsonObject.get("pictures");  
	  
	            if (files != null) {  
	                JSONArray jsonArray = (JSONArray) files;  
	                if (jsonArray.size() <= 0) return;  
	                JSONObject o = (JSONObject) jsonArray.get(jsonArray.size() - 1);  
	  
	                String url = (String) o.get("url");  
	               /* if(!url.contains("/sns/")){  
	                    return;  
	                }*/  
	  
	                String fileName = url.substring(url.lastIndexOf("/") + 1);  
	                System.out.println(Thread.currentThread().getName() + "--" + url);  
	  
	                File file = new File("e:\\pic\\" + fileName.trim());  
	  
	               /* if (!file.exists()) { 
	                    file.createNewFile(); 
	                }*/  
	  
	                FileOutputStream outputStream = new FileOutputStream(file);  
	  
	                HttpGet httpGetImg = doGet(url);  
	                resImg = execute(httpGetImg);  
	                if (resImg == null) return;  
	  
	                InputStream imgIs = resImg.getEntity().getContent();  
	                BufferedInputStream bis = new BufferedInputStream(imgIs);  
	                byte[] bytes = new byte[1024 * 1024];  
	                int length = 0;  
	                while ((length = bis.read(bytes)) > -1) {  
	                    outputStream.write(bytes, 0, length);  
	                }  
	  
	                outputStream.flush();  
	                outputStream.close();  
	            }else{  
	                System.out.println(Thread.currentThread().getName() + "--" + tid);  
	            }  
	  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            close(response);  
	            close(resImg);  
	        }  
	    }  
	  
	    private void close(CloseableHttpResponse response) {  
	        try {  
	            if (response != null) response.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    private CloseableHttpResponse execute(HttpGet httpGet) {  
	        CloseableHttpResponse response = null;  
	        try {  
	  
	            response = httpclient.execute(httpGet);  
	  
	            int status = response.getStatusLine().getStatusCode();  
	            if (status >= 200 && status < 300) {  
	                return response;  
	            }  
	  
	            response.close();  
	            return null;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	  
	    public static void main(String[] args) {  
	        final LoadImageClient loadImage = new LoadImageClient();  
	        List<Thread> threads = new ArrayList<Thread>();  
	  
	        for (int i = 10; i < 100; i++) {  
	            final int t = i * 10;  
	  
	            Thread thread = new Thread(new Runnable() {  
	                @Override  
	                public void run() {  
	                    try {  
	  
	                        int tid = 339000 + t;  
	                        for (int j = 10; j >=0 ; j--) {  
	                            loadImage.loadImage(tid + j);  
	                        }  
	                    } catch (Exception e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            });  
	            threads.add(thread);  
	  
	        }  
	  
	        for (Thread thread : threads) {  
	            thread.start();  
	        }  
	  
	    }  

}
