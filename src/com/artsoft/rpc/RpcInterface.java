package com.artsoft.rpc;

import WanfangBykeyWordMain.WanfangBykeyWordMain;

public class RpcInterface {
	
	/**
	 * ���������ṩ�ķ���
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
	
	public String Keyword(String ip,String Keyword,String intNum){
//		System.out.println("----------�������е�"+ConfigManager.getInstance().getConfigValue("NO")+",���еĵ�"+ConfigManager.getInstance().getConfigValue("NEWNO")+"------------");
//		System.out.println("----------�������е�"+staticNumPag.readintNum()+",���еĵ�"+staticNumint.readintNum()+"------------");
		System.out.println("�û�ip:"+ip+"�����÷�������Keyword:");	
		String returnstringkey="";
		try {
			WanfangBykeyWordMain bb=new WanfangBykeyWordMain();
			returnstringkey=bb.Keywordbig_xuanze(Integer.valueOf(intNum));
//			returnstringkey=bb.Keywordbig(Integer.valueOf(intNum));
//		System.out.println("��ȡ�ؼ���Ϊ"+returnstringkey);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�׳��쳣");
		}
		return returnstringkey;
	}
	
	
	
	
}
