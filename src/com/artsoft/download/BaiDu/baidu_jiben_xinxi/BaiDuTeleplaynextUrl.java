package com.artsoft.download.BaiDu.baidu_jiben_xinxi;

import java.util.List;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.BaiDu.BaiDuTeleplayDownload;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;

public class BaiDuTeleplaynextUrl {
	
	public static void runupdate(){
		List<String> listArray = OracleBaidu.selectbaidudianshijuTVplay() ;
//		for (Object Objstring : listArray) {
////			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
//				System.out.println(listTemp.get(0));
//				System.out.println(listTemp.get(2));
//				BaiDuTeleplayDownload.mainmore(listTemp.get(0), listTemp.get(2));
//			}
//		}
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String name="";
			String url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(url=listTemp.get(2));
			System.out.println(name=listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
//				String urlBranch = "";
//				try {
////					urlBranch = "http://baike.baidu.com/search?word="
////							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
//					BaiDuTeleplayDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
////					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
				
//					TvPlay tvplay =BaiDuTeleplayDownload.mainmore(id, url,name);
////					OracleHaoSou.InsertTVplay(tvplay);//整体表添加操作
//					OracleHaoSou.UpdatPpartTVplay(tvplay);//整体表修改操作
					
				
					
					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url,name);
//					tvplay.setBaikefilmname(strUrlname);
					OracleHaoSou.InsertTVplay(tvplay);// 添加操作
//					
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				

			}

		}
		System.out.println(listArray.size());
	}
	
	
	/**
	 * 2017年3月15日11:08:05
	 * 进行百度百科电视剧数据的数据
	 * @param sql
	 */
	public static void robotrun(String sql) {
		List<String> listArray = OracleBaidu.selectbaidudianshijuTVplay(sql) ;
//		for (Object Objstring : listArray) {
////			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
//				System.out.println(listTemp.get(0));
//				System.out.println(listTemp.get(2));
//				BaiDuTeleplayDownload.mainmore(listTemp.get(0), listTemp.get(2));
//			}
//		}
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id="";
			String name="";
			String url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(url=listTemp.get(2));
			System.out.println(name=listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
//				String urlBranch = "";
//				try {
////					urlBranch = "http://baike.baidu.com/search?word="
////							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
//					BaiDuTeleplayDownload.mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1));
////					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
				
//					TvPlay tvplay =BaiDuTeleplayDownload.mainmore(id, url,name);
////					OracleHaoSou.InsertTVplay(tvplay);//整体表添加操作
//					OracleHaoSou.UpdatPpartTVplay(tvplay);//整体表修改操作
					
				
					
					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url,name);
//					tvplay.setBaikefilmname(strUrlname);
					tvplay.setTvplay_name(name);
					OracleHaoSou.InsertTVplay(tvplay);// 添加操作
//					
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				

			}

		}
		System.out.println(listArray.size());
	}

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//手动进行电视剧数据的更新
//		runupdate();
		
		
		//调用电视剧数据的更新
		
		BaiDuTeleplaynextUrl.robotrun(" select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where t.tvplay_id='399'");
		
		
		
		
		
	}

	

}
