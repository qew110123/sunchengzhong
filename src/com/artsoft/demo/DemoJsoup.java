package com.artsoft.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class DemoJsoup {
	
	
	public static void Weibo(String newUrl) throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************页面转向******************************");
		// String newUrl =
		// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
		HttpGet get = new HttpGet(newUrl);
		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
		// get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
		// rv:26.0) Gecko/20100101 Firefox/26.0");
		// get.addHeader("Host", "data.weibo.com");
		// get.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Remote Address", "122.226.183.189:80");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
//		get.addHeader(new BasicHeader("Cookie","Hm_lvt_645dcc265dc58142b6dbfea748247f02=1449663253,1449713980; Hm_lpvt_645dcc265dc58142b6dbfea748247f02=1449727658; PHPSESSID=j92m6qmogr3g19229tqh5cf906; CNZZDATA1000465408=199040908-1449668252-http%253A%252F%252Fmanhua.dmzj.com%252F%7C1449743854; CNZZDATA1253965819=1779078937-1449667597-http%253A%252F%252Fmanhua.dmzj.com%252F%7C1449743285"));
//		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		get.addHeader("Cookie","Hm_lvt_645dcc265dc58142b6dbfea748247f02=1449663253,1449713980; Hm_lpvt_645dcc265dc58142b6dbfea748247f02=1449727658; PHPSESSID=j92m6qmogr3g19229tqh5cf906; CNZZDATA1000465408=199040908-1449668252-http%253A%252F%252Fmanhua.dmzj.com%252F%7C1449743854; CNZZDATA1253965819=1779078937-1449667597-http%253A%252F%252Fmanhua.dmzj.com%252F%7C1449743285");
		get.addHeader("Host", "www.dmzj.com");
//		get.addHeader("Referer", "http://weibo.cn/search/?pos=search&vt=4");
		
		get.addHeader("Upgrade-Insecure-Requests", "1");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
//		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");

//		System.out.println(get);
//		System.out.println(URLEncoder.encode(newUrl,"UTF-8"));
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// 登录后首页的内容
//		String charset = "UTF-8";  
//        if (null != params && params.length >= 1) {  
//            charset = params[0];  
//        }  
//		System.out.println(EntityUtils.toString(httpResponse.getEntity(), "ISO-8859-1"));
//		System.out.println(httpResponse.getEntity().toString());
//		System.out.println(new StringEntity(httpResponse.getEntity().toString(), "GBK"));
		System.out.println(responseString);
		get.releaseConnection();

	}
	
	public static String executeGet(String url) throws Exception {  
        BufferedReader in = null;  
  
        String content = null;  
        try {  
            // 定义HttpClient  
            HttpClient client = new DefaultHttpClient();  
            // 实例化HTTP方法  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(url));  
            HttpResponse response = client.execute(request);  
  
            in = new BufferedReader(new InputStreamReader(response.getEntity()  
                    .getContent()));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
            in.close();  
            content = sb.toString(); 
//            content =(sb.toString(httpResponse.getEntity(), "ISO-8859-1"));
        } finally {  
            if (in != null) {  
                try {  
                    in.close();// 最后要关闭BufferedReader  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            return content;  
        }  
    }  
	
	 /**
     * 通过GET方式发起http请求
     */
    @Test
    public void requestByGetMethod(){
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //用get方法发送http请求
            HttpGet get = new HttpGet("http://www.dmzj.com/category/1-0-0-6-0-0-1.html");
            System.out.println("执行get请求:...."+get.getURI());
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("响应状态码:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");                    
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
 
    }
     
     
    /**
     * POST方式发起http请求
     */
    @Test
    public void requestByPostMethod(){
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost("http://www.dmzj.com/category/1-0-0-6-0-0-1.html");          //这里用上本机的某个工程做测试
            //创建参数列表
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("j_username", "admin"));
            list.add(new BasicNameValuePair("j_password", "admin"));
            //url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(uefEntity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }
             
        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
         
    }
     
    private CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
     
    private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		File input = new File("/tmp/input.html");
//		Document doc = null;
//		try {
////			doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
//			doc =Jsoup.connect("http://baike.baidu.com/link?url=aoe7rD8rFgF6w_1UD08-XlUAt5Uqr_6Sb7Aw0dfHt5reNDSWJ3k1BIQBjOn2MchyxuZ4XpFxQGWaoMOe_NNkda").get();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(doc.toString());
//
//		Element content = doc.getElementById("content");
//		Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//		  String linkHref = link.attr("href");
//		  String linkText = link.text();
//		}
//		String strHtml = DownloadUtil.getHtmlText("http://www.dmzj.com/category/1-0-0-6-0-0-1.html", 1000 * 30, "utf-8", null, null);
//		System.out.println(strHtml);
		
//		try {
//			System.out.println(executeGet("http://www.dmzj.com/category/1-0-0-6-0-0-1.html"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		DemoJsoup bb=new DemoJsoup();
//		bb.requestByPostMethod();
		bb.requestByGetMethod();

	}

}
