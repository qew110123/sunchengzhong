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
////					OracleHaoSou.InsertTVplay(tvplay);//�������Ӳ���
//					OracleHaoSou.UpdatPpartTVplay(tvplay);//������޸Ĳ���
					
				
					
					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url,name);
//					tvplay.setBaikefilmname(strUrlname);
					OracleHaoSou.InsertTVplay(tvplay);// ��Ӳ���
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
	 * 2017��3��15��11:08:05
	 * ���аٶȰٿƵ��Ӿ����ݵ�����
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
////					OracleHaoSou.InsertTVplay(tvplay);//�������Ӳ���
//					OracleHaoSou.UpdatPpartTVplay(tvplay);//������޸Ĳ���
					
				
					
					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url,name);
//					tvplay.setBaikefilmname(strUrlname);
					tvplay.setTvplay_name(name);
					OracleHaoSou.InsertTVplay(tvplay);// ��Ӳ���
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
		
		//�ֶ����е��Ӿ����ݵĸ���
//		runupdate();
		
		
		//���õ��Ӿ����ݵĸ���
		
		BaiDuTeleplaynextUrl.robotrun(" select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where t.tvplay_id='399'");
		
		
		
		
		
	}

	

}
