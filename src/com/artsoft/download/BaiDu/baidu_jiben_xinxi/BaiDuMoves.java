package com.artsoft.download.BaiDu.baidu_jiben_xinxi;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.artsoft.bean.TEM_DIM_FILM;
import com.artsoft.bean.TvPlay;
import com.artsoft.download.BaiDu.BaiDuMovesDownload;
import com.artsoft.download.BaiDu.BaiDuTeleplayDownload;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class BaiDuMoves {
	
	
	
	public static void runnewMain(){
		List<String> listArray = OracleBaidu.selectmove();
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				String urlBranch = "";
				try {
					urlBranch = "http://baike.baidu.com/search?word="
							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
					BaiDuMovesDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void runnewMain_url(){
		List<String> listArray = OracleBaidu.selectbaidudianshiju_Moves_url();
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			System.out.println(id=listTemp.get(0));
			String strname="";
			System.out.println(strname=listTemp.get(1));
			String strUrl="";
			System.out.println(strUrl=listTemp.get(2));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
				String urlBranch = "";
				try {
					
//					urlBranch = "http://baike.baidu.com/search?word="
//							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
//					mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
//					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
					
					
//					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, strUrl, strname);
////					tvplay.setBaikefilmname(strUrlname);
//					tvplay.setType(2);
////					OracleHaoSou.InsertTVplay(tvplay);// 添加操作
////					OracleHaoSou.Insertwangluoju(tvplay);// 添加操作
//					OracleHaoSou.InWangLuoTVplay(tvplay);// 添加操作
					
					
//					TEM_DIM_FILM movesfilm = BaiDuTeleplayDownload.mainmore(id, strUrl, strname);
////					movesfilm.setBaikefilmname(strUrlname);
//					
//					OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// 添加操作
					
					
					TEM_DIM_FILM movesfilm = BaiDuMovesDownload.mainmore(id, strUrl, strname);
//					movesfilm.setBaikefilmname(strUrlname);
					
					OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// 添加操作
					
					
//				}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runnewMain();
//		String title="功夫熊猫";
//		String urlBranch="";
//		try {
//			urlBranch = "http://baike.baidu.com/search?word="
//					+ java.net.URLEncoder.encode(title, "utf-8") + "&pn=0&rn=0&enc=utf8";
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BaiDuTeleplayDownload.mainUrlall(urlBranch, "0", title);
		
		
		//名称搜索
//		runnewMain();
		
		//url搜索
		runnewMain_url();
	}

}
