package com.artsoft.download.webo.weiboSearch.weibo_rpcClient;

import java.io.UnsupportedEncodingException;

import com.artsoft.download.webo.weiboSearch.WeiBoSearchTVplay;

public class Weibo_rpcclient {
	
	
	public static void run_rpc_weibo(String id,String name ,String url,int TV_TYPE){
		
		if (url.equals("")) {
			try {
				String urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode("#"+name+"#", "utf-8")
						+ "&Refer=STopic_box";

//				hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
				try {
					WeiBoSearchTVplay.seleepTime(10);
					WeiBoSearchTVplay.WeiBoBranch(urlBranch, id, TV_TYPE);
				} catch (Exception e) {
					// TODO: handle exception
//					webDriver= null;
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			
			try {
				System.out.println(url );
				if (!url.equals("")&&url!=null) {
					WeiBoSearchTVplay.seleepTime(7);
					try {
						
						WeiBoSearchTVplay.WeiBoBranch1(url, id, TV_TYPE);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
//					WeiBoSearchTVplay.seleepTime(7);
//					try {
//						
//						WeiBoSearchTVplay.WeiBoBranch2(url, id, TV_TYPE);
//						WeiBoSearchTVplay.shuaxin();
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	
	public static void main(String[] args) {
//		run_rpc_weibo("64", "快乐大本营", "", 5);
		
//		run_rpc_weibo("1", "邓伦", "", 1);
		
		run_rpc_weibo("6856695", "龙珠传奇", "", 2);
	}
	
	
}
