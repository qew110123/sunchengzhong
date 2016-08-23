package WanfangBykeyWordMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.artsoft.config.ConfigManager;
import com.artsoft.download.BaiDu.BaiDuTeleplayDownload;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.oracleRpc;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;

//import com.gesoft.MainSearch.staticNumPag;
//import com.gesoft.MainSearch.staticNumint;
//import com.gesoft.config.ConfigManager;

public class WanfangBykeyWordMain {
	/*
	 * ��ȡ�ı��ı�
	 */
//	String[] keys=getKeyWordFromFile("keyword.txt").split("\n");
	
//	LinkedList<String> keyList=new LinkedList<String>();
//	boolean bb=Collections.addAll(keyList, keys);
	
//	int numintn=Integer.valueOf(ConfigManager.getInstance().getConfigValue("NEWNO"));
	
	/**
	 * Сֵ
	 */
//	static int numintn=Integer.valueOf(ConfigManager.getInstance().getConfigValue("weiboxiaonum"));  //xml zhongd xiaoz 
	static int numintn=0;  //xml zhongd xiaoz 
	
	/**
	 * ��ֵ
	 */
	
	static int numintnbig=Integer.valueOf(ConfigManager.getInstance().getConfigValue("weibobagnum"));  //xml ��ֵ
	
//	Collections.addAll(keyList, keys);
	
	/*
	�ۺ�������
	���Ӿ�ǰ500
	��Ӱǰ500
	����ǰ200
	����ǰ200
	*/
	List<String> listArraybig = oracleRpc.selectmaoyanshijuTmovebig();
	
	
	/*
	ÿ�죺
	���Ӿ����������б�ǰ50 1
	��ӰʵʱƱ��           
	���������Ȳ�ǰ50
	���������Ȳ�ǰ50
	����  2 ���Ӿ� 3 ��Ӱ 4���� 5 ���� 
	*/
	List<String> listArray = oracleRpc.selectmaoyanshijuTmove();
	
	
	
	static boolean hhtimeTrue=true;// �жϽ���ʱ��������� true û�������� �� false �������
	
	
	public  String Keyword( int intnum ){
		// TODO Auto-generated method stub
		String stringbb="";		
		
		if (numintn+intnum>=listArray.size()) {
			intnum=listArray.size()-numintn;
			
			ConfigManager.getInstance().setConfigValue("weiboxiaonum","0");
			hhtimeTrue=false;
			numintn=0;
		}
		
		
		List<String> listArrayxiaoshuju = listArray.subList(numintn, numintn+intnum); 
		numintn=numintn+intnum;
		ConfigManager.getInstance().setConfigValue("weiboxiaonum",""+numintn);
		
		JSONArray arrayjsonname = new JSONArray();
		
		
		for (Object Objstring : listArrayxiaoshuju) {
			JSONObject datajson = new JSONObject();
			System.out.println(Objstring);
			if (Objstring==null) {
				return "";
			}
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String 	name="";
			String data_type="";
			String data_url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			System.out.println(data_type=listTemp.get(2));
			System.out.println(data_url=listTemp.get(3));
//			stringbb="#id#"+id+",#name#"+name+",#data_type#"+data_type+",#data_url#"+data_url+",##";
			
			try {
			datajson.put("id", id);
			datajson.put("name", name);
			datajson.put("data_type", data_type);
			if (data_url!=null) {
				
				datajson.put("data_url", data_url);
			}else{
				datajson.put("data_url", "");
			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			arrayjsonname.add(datajson.toString());
			System.out.println(arrayjsonname);
			
		}
		
		System.out.println(arrayjsonname);
		
		return String.valueOf(arrayjsonname);
	}
	
	
	

	int hhtimeint=Integer.valueOf(TimeTest.getNowTime("HH"));  //��ȡ��ǰСʱ 00 01 02..23
	
	
	public  String Keywordbig_xuanze( int intnum ){
		// TODO Auto-generated method stub
		String stringbb="";		
		
		if (numintnbig+intnum>=listArraybig.size()) {
			System.out.println("����������һ����ϣ��������У�");
			intnum=listArray.size()-numintnbig;
			numintnbig=0;
			ConfigManager.getInstance().setConfigValue("weibobagnum","0");
		}
		
		JSONArray arrayjsonname = new JSONArray();
		if (hhtimeTrue) {
			arrayjsonname=JSONArray.fromObject(Keyword(intnum));
		}else{
		
			if (hhtimeint>Integer.valueOf(TimeTest.getNowTime("HH"))) {
				hhtimeTrue=true;
			}
			
			
			List<String> listArrayxiaoshuju = listArraybig.subList(numintnbig, numintnbig+intnum); 
			numintnbig=numintnbig+intnum;
			System.out.println("΢��ȫ��ֵ��"+numintnbig);
			ConfigManager.getInstance().setConfigValue("weibobagnum",""+numintnbig);
			
			
			
			for (Object Objstring : listArrayxiaoshuju) {
				JSONObject datajson = new JSONObject();
				System.out.println(Objstring);
				if (Objstring==null) {
					return "";
				}
				List<String> listTemp = (List<String>) Objstring;
				System.out.println(listArrayxiaoshuju.size());
				System.out.println(listTemp);
				String id="";
				String 	name="";
				String data_type="";
				String data_url="";
				System.out.println(id=listTemp.get(0));
				System.out.println(name=listTemp.get(1));
				System.out.println(data_type=listTemp.get(2));
				System.out.println(data_url=listTemp.get(3));
	//			stringbb="#id#"+id+",#name#"+name+",#data_type#"+data_type+",#data_url#"+data_url+",##";
				
				try {
				datajson.put("id", id);
				datajson.put("name", name);
				datajson.put("data_type", data_type);
				if (data_url!=null) {
					
					datajson.put("data_url", data_url);
				}else{
					datajson.put("data_url", "");
				}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				arrayjsonname.add(datajson.toString());
				System.out.println(arrayjsonname);
				
			}
		}
		System.out.println(arrayjsonname);
		
		return String.valueOf(arrayjsonname);
	}
	
	
	public  String Keywordbig( int intnum ){
		// TODO Auto-generated method stub
		String stringbb="";		
		
		List<String> listArrayxiaoshuju = listArraybig.subList(numintnbig, numintnbig+intnum); 
		numintnbig=numintnbig+intnum;
		System.out.println("΢��ȫ��ֵ��"+numintnbig);
		ConfigManager.getInstance().setConfigValue("weibobagnum",""+numintnbig);
		
		JSONArray arrayjsonname = new JSONArray();
		
		
		for (Object Objstring : listArrayxiaoshuju) {
			JSONObject datajson = new JSONObject();
			System.out.println(Objstring);
			if (Objstring==null) {
				return "";
			}
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String 	name="";
			String data_type="";
			String data_url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			System.out.println(data_type=listTemp.get(2));
			System.out.println(data_url=listTemp.get(3));
//					stringbb="#id#"+id+",#name#"+name+",#data_type#"+data_type+",#data_url#"+data_url+",##";
			
			try {
			datajson.put("id", id);
			datajson.put("name", name);
			datajson.put("data_type", data_type);
			if (data_url!=null) {
				
				datajson.put("data_url", data_url);
			}else{
				datajson.put("data_url", "");
			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			arrayjsonname.add(datajson.toString());
			System.out.println(arrayjsonname);
			
		}
		System.out.println(arrayjsonname);
		return String.valueOf(arrayjsonname);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			WanfangBykeyWordMain bb=new WanfangBykeyWordMain();
			bb.Keywordbig(1);
		}
		
	}
}
