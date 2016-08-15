package com.artsoft.download.BaiDu;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		runupdate();
//		BaiDuTeleplayDownload.mainmore("0", "http://baike.baidu.com/link?url=UB4Ixeuqqi6TaUMlzQhQ_KHQ26JdvD72z1ft_bvQINbLweaglG7FM0ASdQxLMsZOyT0vmwYR1ps3M9KB6bv9l_paBRMC6nN23wQ9G_c9qEnTQbrkqLTA7mIQNsd-b3u0","");
		
//		TvPlay tvplay =BaiDuTeleplayDownload.mainmore("12803", "http://baike.baidu.com/view/940398.htm","青春出动");
////		OracleHaoSou.InsertTVplay(tvplay);//整体表添加操作
//		OracleHaoSou.UpdatPpartTVplay(tvplay);//整体表修改操作
	}

}
