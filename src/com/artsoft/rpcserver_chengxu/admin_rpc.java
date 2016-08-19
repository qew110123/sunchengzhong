package com.artsoft.rpcserver_chengxu;

import com.artsoft.download.webo.weiboSearch.WeiBoSearchTVplay;
import com.artsoft.download.webo.weiboSearch.weibo_rpcClient.Weibo_rpcclient;
import com.artsoft.rpc.RpcClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class admin_rpc {
	
	
	private static void runcilent() {
		// TODO Auto-generated method stub
		
		RpcClient client = new RpcClient();
		while (true) {
			try {
				String strTemp = client.turnweibo(LookIp.getIp(),"","1");
				if (strTemp!=null) {
//					Search.Analytical(strTemp);	
					System.out.println(strTemp);
					
					JSONArray arrayjsonname = JSONArray.fromObject(strTemp);
					for (Object object : arrayjsonname) {
						JSONObject objectobjects = JSONObject.fromObject(object);
						String id =(String) objectobjects.get("id");
						String name =(String) objectobjects.get("name");
						String data_type =(String) objectobjects.get("data_type");
						String data_url =(String) objectobjects.get("data_url");
						try {
							Weibo_rpcclient.run_rpc_weibo(id, name, data_url, Integer.valueOf(data_type));
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
					
				}else{
					System.out.println("服务器没有搭建完成，请等待1分钟");
					WeiBoSearchTVplay.seleepTime(10);
				}
			} catch (Exception e) {
				System.out.println("服务器出现问题，请调整");
				WeiBoSearchTVplay.seleepTime(10);
			} 
			System.out.println("本次运行完成");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			runcilent();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	

}
