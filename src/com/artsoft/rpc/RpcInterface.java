package com.artsoft.rpc;

public class RpcInterface {
	
	/**
	 * 发布服务提供的方法
	 * @param spact
	 * @return
	 */
	public String pp2(String spact){
		System.out.println("-pp2-");	
		System.out.println(spact);
		return "SUCCESS";
	}
	
	public String pp3(String spact){
		System.out.println("-pp3-");	
		System.out.println(spact);
		return "SUCCESS";
	}
	
	public String pp4(String spact){
		System.out.println("-pp4-");	
		System.out.println(spact);
		return "SUCCESS";
	}
	
	
}
