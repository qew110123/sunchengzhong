package com.artsoft.download.BaiDu.baidu_jiben_xinxi;

import java.util.List;

import com.artsoft.bean.Persion;
import com.artsoft.download.BaiDu.BaiDuPeopleDownload;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;

public class BaiduPersonMoreUrl {
	
	/**
	 * 进行url数据整体数据的更新
	 * 2016年6月6日16:35:03
	 * @param args
	 * @return 
	 */
	public  static void peopleurl(){
		// TODO Auto-generated method stub
		
//		BaiDuPeopleDownload.mainmore("", "http://baike.baidu.com/view/3919236.htm");
		List<String> listArray = OracleBaidu.selecthuoqumingchengurl();
		boolean bb=true;
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			String url="";
			String id ="";
			String name="";
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				System.out.println(id=listTemp.get(0));
				System.out.println(name=listTemp.get(1));
				System.out.println(url=listTemp.get(2));
//				BaiDuPeopleDownload.mainmore(listTemp.get(0), listTemp.get(2));
//				
//				System.out.println(listTemp.get(0));
//				System.out.println(listTemp.get(1));
				
					
					
					if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
						
						try {
							Persion person=BaiDuPeopleDownload.mainmore(id, url);
	//						person.setId(Integer.parseInt(id));
	//						person.setUrl(url);
							try {
								
								person.setName(name);
							} catch (Exception e) {
								// TODO: handle exception
							}
							OracleHaoSou.InsertTemDimPerson(person);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
	//					String urlBranch = "";
	//					try {
	//						urlBranch = "http://baike.baidu.com/search?word="
	//								+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
	//
	//						BaiDuPeopleDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
	//						CommonUtil.setLog(
	//								TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(1));
	//					} catch (UnsupportedEncodingException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//					TimeTest.seleepTime(5, 5);
	
					}
				
			}
		}
	}
	
	
	
	
	/**
	 * 进行url数据整体数据的更新
	 * 2017年3月27日16:09:10
	 * @param args
	 * @return 
	 */
	public  static void personUrlSql(String sql){
		// TODO Auto-generated method stub
		
//		BaiDuPeopleDownload.mainmore("", "http://baike.baidu.com/view/3919236.htm");
		List<String> listArray = OracleBaidu.selecthuoqumingchengurl(sql);
		boolean bb=true;
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			String url="";
			String id ="";
			String name="";
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				System.out.println(id=listTemp.get(0));
				System.out.println(name=listTemp.get(1));
				System.out.println(url=listTemp.get(2));
//				BaiDuPeopleDownload.mainmore(listTemp.get(0), listTemp.get(2));
//				
//				System.out.println(listTemp.get(0));
//				System.out.println(listTemp.get(1));
				
					
					
					if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
						
						try {
							Persion person=BaiDuPeopleDownload.mainmore(id, url);
	//						person.setId(Integer.parseInt(id));
	//						person.setUrl(url);
							try {
								
								person.setName(name);
							} catch (Exception e) {
								// TODO: handle exception
							}
							OracleHaoSou.InsertTemDimPerson(person);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
	//					String urlBranch = "";
	//					try {
	//						urlBranch = "http://baike.baidu.com/search?word="
	//								+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
	//
	//						BaiDuPeopleDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
	//						CommonUtil.setLog(
	//								TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(1));
	//					} catch (UnsupportedEncodingException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//					TimeTest.seleepTime(5, 5);
	
					}
				
			}
		}
	}

	public static void main(String[] args) {
		
		//卡斯基本信息数据
//		System.out.println(Integer.parseInt("104076"));
		peopleurl();
		
		
		
		
		//卡斯基本信息数据 sql
//		System.out.println(Integer.parseInt("104076"));
//		BaiduPersonMoreUrl.peopleurlSql("");
		
		
		BaiduPersonMoreUrl.personUrlSql("");
		
		
		
	}


}
