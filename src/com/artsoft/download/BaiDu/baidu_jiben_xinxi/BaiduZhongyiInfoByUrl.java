package com.artsoft.download.BaiDu.baidu_jiben_xinxi;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.download.BaiDu.BaiDuTeleplayDownload;
import com.artsoft.download.BaiDu.BaiduImageEntryTVplay;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

public class BaiduZhongyiInfoByUrl {
	
	/**
	 * 进行数据的搜索
	 * 
	 * @param urlmain
	 * @param id
	 * @param strname
	 */
	public static void mainUrlall(String urlmain, String id, String strname,int TYPE) {
		// TODO Auto-generated method stub
		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		try {
			
		
			Document doc = Jsoup.parse(strHtml);
			
			
			
			// Element linkmain = doc.getElementById("fluxes_static");
			Elements links = doc.select("a.result-title");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			System.out.println(links.size());
			// for (Element link : links) {
			// String idnum = "";
			// String strVolumes = "";
			// System.out.println(strVolumes = link.text());
			// System.out.println(idnum = link.attr("href"));
			// }
			if (links.size() > 0) {
				String strUrl = "";
				String strUrlname = "";
				System.out.println(strUrlname = links.first().text());
				System.out.println(strUrl = links.attr("href"));
				// mainmore(id, strUrl,strUrlname);
				if (strUrl != null && !"".equals(strUrl)) {
	
					if (!strUrl.contains("http://baike.baidu.com")) {
						strUrl = "http://baike.baidu.com" + strUrl;
					}
					System.out.println(strUrl);
//					// mainmore(id, strUrl);
//					TvPlay tvplay = mainmore(id, strUrl, strUrlname);
//					OracleHaoSou.InsertTVplay(tvplay);// 添加操作
//					// OracleHaoSou.UpdateTVplay(tvplay);//修改操作
					
					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, strUrl, strname);
					tvplay.setBaikefilmname(strUrlname);
					tvplay.setBAIKE_NAME(strUrlname);
					tvplay.setType(TYPE);
					OracleHaoSou.Insertzhongyi(tvplay);// 添加操作
				}
			}
			
			
			
			
			
			
//			try {
//				Elements links = doc.select("dl.search-list dd");
//				for (Element element : links) {
//					String strUrl = "";
//					String strUrlname = "";
//					String strallname="";
//					System.out.println(strUrlname = element.select("a.result-title").first().text());
//					System.out.println(strallname = element.select("p.result-summary").first().text());
//					System.out.println(strUrl = element.select("a.result-title").first().attr("href"));
//		//			if (strUrlname.contains("电视剧")||strallname.contains("电视剧")) {
//						if (strUrl!=null) {
//		//					System.out.println("成功");
//		//					TEM_DIM_FILM movesfilm = mainmore(id, strUrl, strname);
//		//					movesfilm.setBaikefilmname(strUrlname);
//		//					
//		//					OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// 添加操作
//							
//							
//							TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, strUrl, strname);
//							tvplay.setBaikefilmname(strUrlname);
//							tvplay.setType(TYPE);
//							OracleHaoSou.Insertzhongyi(tvplay);// 添加操作
//						}
//		//			}
//					
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	public static void runnewMainurl(){
		List<String> listArray = OracleBaidu.selectbaidudianshijuzhongyi();
//		for (Object Objstring : listArray) {
////			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
//				System.out.println(listTemp.get(0));
//				System.out.println(listTemp.get(2));
//				BaiDuTeleplayDownload.mainmore(listTemp.get(0), listTemp.get(2));
//			}
//		}
		
//		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
//			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
//				String urlBranch = "";
//				try {
//					
//					urlBranch = "http://baike.baidu.com/search?word="
//							+ java.net.URLEncoder.encode(listTemp.get(1), "utf-8") + "&pn=0&rn=0&enc=utf8";
////					if (Integer.parseInt(listTemp.get(0))>65605) {
//						mainUrlall(urlBranch, listTemp.get(0), listTemp.get(1),3);
//						CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
////					}
//					
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id = "";
			String name="";
			String url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			System.out.println(url=listTemp.get(2));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
				String urlBranch = "";
//				url="";
				if (BaiduImageEntryTVplay.isChineseChar(url)) {
					
					url=url.replace("#", "787878788");
					
					url=url.replace(":", "909090909090");
					
					String url_gai=url.replace("/", "11111111");
					String urlutf_8="";
					try {
						urlutf_8 = java.net.URLEncoder.encode(url_gai, "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					url=urlutf_8.replace("11111111", "/");
					url=url.replace("909090909090", ":");
					
					url=url.replace("787878788","#" );
					
				}
				TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url, name);
//					tvplay.setBaikefilmname(strUrlname);
//					tvplay.setBAIKE_NAME(strUrlname);
				tvplay.setType(3);
				OracleHaoSou.Insertzhongyi(tvplay);// 添加操作
				
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
//					}
			}
		}
	}

	private static void runnewMain() {
		List<String> listArray = OracleBaidu.selectbaidudianshijuzhongyiall();
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
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			if (id != null && !"".equals(id)&&name != null && !"".equals(name)) {
				String urlBranch = "";
				try {
					name=BaiduWangluojuInfoByUrl.deletenum(name);
					urlBranch = "http://baike.baidu.com/search?word="
							+ java.net.URLEncoder.encode(name, "utf-8") + "&pn=0&rn=0&enc=utf8";
					try {
						System.out.println(name);
						mainUrlall(urlBranch, id, name);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 进行数据的搜索
	 * 
	 * @param urlmain
	 * @param id
	 * @param strname
	 */
	public static void mainUrlall(String urlmain, String id, String strname) {
		// TODO Auto-generated method stub
		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		Document doc = Jsoup.parse(strHtml);
		
		
		
//		// Element linkmain = doc.getElementById("fluxes_static");
//		Elements links = doc.select("a.result-title");
//		// Element content = doc.getElementById("content");
//		// Elements links = content.getElementsByTag("a");
//		System.out.println(links.size());
//		// for (Element link : links) {
//		// String idnum = "";
//		// String strVolumes = "";
//		// System.out.println(strVolumes = link.text());
//		// System.out.println(idnum = link.attr("href"));
//		// }
//		if (links.size() > 0) {
//			String strUrl = "";
//			String strUrlname = "";
//			System.out.println(strUrlname = links.first().text());
//			System.out.println(strUrl = links.attr("href"));
//			// mainmore(id, strUrl,strUrlname);
//			if (strUrl != null && !"".equals(strUrl)) {
//
//				if (!strUrl.contains("http://baike.baidu.com")) {
//					strUrl = "http://baike.baidu.com" + strUrl;
//				}
//				System.out.println(strUrl);
//				// mainmore(id, strUrl);
//				TvPlay tvplay = mainmore(id, strUrl, strUrlname);
//				OracleHaoSou.InsertTVplay(tvplay);// 添加操作
//				// OracleHaoSou.UpdateTVplay(tvplay);//修改操作
//			}
//		}
		try {
		Elements links = doc.select("dl.search-list dd");
		for (Element element : links) {
			String strUrl = "";
			String strUrlname = "";
			String strallname="";
			System.out.println(strUrlname = element.select("a.result-title").first().text());
			System.out.println(strallname = element.select("p.result-summary").first().text());
			System.out.println(strUrl = element.select("a.result-title").first().attr("href"));
//			if (strUrlname.contains("电视剧")||strallname.contains("电视剧")) {
				if (strUrl!=null) {
//					System.out.println("成功");
//					TEM_DIM_FILM movesfilm = mainmore(id, strUrl, strname);
//					movesfilm.setBaikefilmname(strUrlname);
//					
//					OracleHaoSou.InsertTEM_DIM_FILM(movesfilm);// 添加操作
//					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, strUrl, strname);
//					tvplay.setBaikefilmname(strUrlname);
//					tvplay.setType(2);
////					OracleHaoSou.InsertTVplay(tvplay);// 添加操作
//					OracleHaoSou.Insertwangluoju(tvplay);// 添加操作
					
					
					TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, strUrl, strname);
//					tvplay.setBaikefilmname(strUrlname);
//					tvplay.setBAIKE_NAME(strUrlname);
					tvplay.setType(3);
					OracleHaoSou.Insertzhongyi(tvplay);// 添加操作
				}
//			}
			
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	
	
	
	
	public static void runnewMainurl(String sql){
		List<String> listArray = OracleBaidu.selectbaidudianshijuzhongyi(sql);
		
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id = "";
			String name="";
			String url="";
			System.out.println(id=listTemp.get(0));
			System.out.println(name=listTemp.get(1));
			System.out.println(url=listTemp.get(2));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))&&listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
				String urlBranch = "";
//				url="";
				if (BaiduImageEntryTVplay.isChineseChar(url)) {
					
					url=url.replace("#", "787878788");
					
					url=url.replace(":", "909090909090");
					
					String url_gai=url.replace("/", "11111111");
					String urlutf_8="";
					try {
						urlutf_8 = java.net.URLEncoder.encode(url_gai, "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					url=urlutf_8.replace("11111111", "/");
					url=url.replace("909090909090", ":");
					
					url=url.replace("787878788","#" );
					
				}
				TvPlay tvplay = BaiDuTeleplayDownload.mainmore(id, url, name);
//					tvplay.setBaikefilmname(strUrlname);
//					tvplay.setBAIKE_NAME(strUrlname);
				tvplay.setType(3);
				OracleHaoSou.Insertzhongyi(tvplay);// 添加操作
				
					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0)+","+listTemp.get(1));
//					}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//没有url
//		runnewMain();
		//有url
		runnewMainurl();
		
		
		runnewMainurl("sql");
	}


}
