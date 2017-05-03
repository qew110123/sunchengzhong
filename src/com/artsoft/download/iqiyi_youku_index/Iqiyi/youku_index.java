package com.artsoft.download.iqiyi_youku_index.Iqiyi;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.artsoft.bean.TEM_CHINA_NETWORK_VIDEO_INDEX;
import com.artsoft.bean.TEM_IQIYI_AND_YOUKU_WORD_INDEX;
import com.artsoft.bean.TVPLAY_IQIYI_INDEX;
import com.artsoft.demo.DemoTime;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class youku_index {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String id="0";
		String name="琅琊榜";
		//使用优酷网进行搜索进行找id值
		//http://www.soku.com/search_video/q_%E7%90%85%E7%90%8A%E6%A6%9C
		String krywordutf8 = "";
		try {
			krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="http://www.soku.com/search_video/q_"+krywordutf8;
//		try {
			
			caxun(id,name,url);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		

	}
	
	
	
	private static void runstatic() {
		// TODO Auto-generated method stub
		List<String> listArray = Oracle.selec_tvplay();
		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				
				String name=listTemp.get(0);
				String id=listTemp.get(1);
				String krywordutf8 = "";
				try {
					krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				iqiyiIndex(name,krywordutf8);
				
				
				String url="http://www.soku.com/search_video/q_"+krywordutf8;
//				caxun(id,name,url);
//				try {
				youku_index.caxun(id, krywordutf8, url);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
				
			}
		}
	}
	
	static void caxun(String id, String name, String url) {
		// TODO Auto-generated method stub
		String strHtml = Htmlurl(url);
		Document doc = Jsoup.parse(strHtml);
		String  url_show = doc.select("span.num a").first().attr("href");
		//http://index.youku.com/vr_show/showid_zf65e90a030f811e59e2a?type=youku
		if (!url_show.contains("http")) {
			url_show="http:"+url_show;
			
		}
		
		String strHtml_show=Htmlurl(url_show);
		
		//播放指数
		
		bofangzishu_youku(id,name,url,strHtml_show);
		
		//性别
		
		xingbei(id,name,url,strHtml_show);
		
		//年龄
		
		nianling(id,name,url,strHtml_show);
		
		//职业
		
		zhiye(id,name,url,strHtml_show);
		
		//学历
		
		xueli(id,name,url,strHtml_show);
		
		//城市
		
		chengsi(id,name,url,strHtml_show);
		
		//播发网站
		
		bofangwangzhan(id,name,url,strHtml_show);
		
		
		//播发设备
		
		bofangshebei(id,name,url,strHtml_show);
		
		
		//剧情分析
		juqingfenxi(id,name,url,strHtml_show);
		
		
		
		
		//票房指数
		bofangzishu_tudou(id,name,url_show,strHtml_show);
		
		
		
		
		
		tudouwang(id, name, url_show, strHtml_show);
		
		
		//全网搜索指数
		alldata_wang(id, name, url_show, strHtml_show);
		
		//全网播放量
		alldata_bofangliang(id, name, url_show, strHtml_show);
		
		
	}

	private static void alldata_bofangliang(String id, String name, String url_show, String strHtml_show) {
		// TODO Auto-generated method stub
		
		if (!url_show.contains("youku")) {
			return;
			
		}
		url_show=url_show.replace("=youku", "=alldata");
		
		System.out.println(url_show);
		
		
		strHtml_show= Htmlurl(url_show);

		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "vv\":[\"", "\"]]}");
		System.out.println(youkuDatavvString);
		
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString+"#", "[\"", "#");
		String[] listkeyword = StringdataString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			String palydate = DemoTime.getBeforeAfterDate(youkuData, 0-i).toString();
			System.out.println(palydate);
			System.out.println(listkeyword[i]);
			
			
			 TVPLAY_IQIYI_INDEX tvplayindex= new TVPLAY_IQIYI_INDEX();
		        tvplayindex.setDataUrl(url_show);
		        tvplayindex.setTvplayName(name);
		        tvplayindex.setVCount(listkeyword[i]);
		        tvplayindex.setDataDate(palydate.replace("-", ""));
//		        tvplayindex.set
		        
		        tvplayindex.setSOURCE(5);
		        Oracle.InsertTVPLAY_IQIYI_INDEX_SOURCE(tvplayindex);
			
			
		}
		
	
		
	}

	private static void alldata_wang(String id, String name, String url_show, String strHtml_show) {
		// TODO Auto-generated method stub
		
		if (!url_show.contains("youku")) {
			return;
			
		}
		url_show=url_show.replace("=youku", "=alldata");
		
		System.out.println(url_show);
		
		
		strHtml_show= Htmlurl(url_show);
		
		if (strHtml_show.equals("")) {
			return;
		}

		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "\"search\":[\"", "\"]],");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString+"#", "[\"", "#");
		String[] listkeyword = StringdataString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			String palydate = DemoTime.getBeforeAfterDate(youkuData, 0-i).toString();
			System.out.println(palydate);
			System.out.println(listkeyword[i]);
			
			
			 TVPLAY_IQIYI_INDEX tvplayindex= new TVPLAY_IQIYI_INDEX();
		        tvplayindex.setDataUrl(url_show);
		        tvplayindex.setTvplayName(name);
		        tvplayindex.setVCount(listkeyword[i]);
		        tvplayindex.setDataDate(palydate.replace("-", ""));
//		        tvplayindex.set
		        
		        tvplayindex.setSOURCE(2);
		        Oracle.InsertTVPLAY_IQIYI_INDEX_SOURCE(tvplayindex);
			
			
		}
		
	
		
		
		
		
		
		
		
	}

	private static void tudouwang(String id, String name, String url_show, String strHtml_show) {
		// TODO Auto-generated method stub
		
		if (!url_show.contains("youku")) {
			return;
			
		}
		
		
		url_show=url_show.replace("youku", "tudou");
		
		//土豆播放网站
		bofangwangzhan_tudou(id,name,url_show,strHtml_show);
		
		//土豆剧情分析
		juqingfenxi_tudou(id,name,url_show,strHtml_show);
		
		
//性别
		
		xingbei_tudou(id,name,url_show,strHtml_show);
		
		//年龄
		
		nianling_tudou(id,name,url_show,strHtml_show);
		
		//职业
		
		zhiye_tudou(id,name,url_show,strHtml_show);
		
		//学历
		
		xueli_tudou(id,name,url_show,strHtml_show);
		
		//城市
		
		chengsi_tudou(id,name,url_show,strHtml_show);
		
		
		bofangshebei_tudou(id,name,url_show,strHtml_show);
		
	}
	
	

	private static void bofangshebei_tudou(String id, String name, String url_show, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "device\":[\"", "\"]}");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String pc = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
		String shouji = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("pc");
		Iqiyi.setLabelRate(pc);
		Iqiyi.setDimensionType(7);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("手机");
		Iqiyi.setLabelRate(shouji);
		Iqiyi.setDimensionType(7);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
	}

	private static void chengsi_tudou(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "provinceMap\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
			String city="";
			String num="";
			System.out.println(city=HtmlAnalyze.getTagText("#"+listkeyword[i], "#", ":"));
			System.out.println(num=HtmlAnalyze.getTagText(listkeyword[i]+"#", ":" ,"#"));
			TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
			Iqiyi.setDataId(id);
			Iqiyi.setWord(name);
			Iqiyi.setLabelName(city);
			Iqiyi.setLabelRate(num);
			Iqiyi.setDimensionType(3);
			Iqiyi.setSource(3);
			Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		}
		
		
		
		
		
		
		
	}

	private static void xueli_tudou(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "edu\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
		}
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("硕士以上");
		Iqiyi.setLabelRate(listkeyword[0]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("本科");
		Iqiyi.setLabelRate(listkeyword[1]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("大专");
		Iqiyi.setLabelRate(listkeyword[2]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("高校/技校");
		Iqiyi.setLabelRate(listkeyword[3]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("初中以下");
		Iqiyi.setLabelRate(listkeyword[4]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
	}

	private static void zhiye_tudou(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "occupation\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
		}
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("学生");
		Iqiyi.setLabelRate(listkeyword[0]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("白领");
		Iqiyi.setLabelRate(listkeyword[1]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("非全职");
		Iqiyi.setLabelRate(listkeyword[2]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("公务员");
		Iqiyi.setLabelRate(listkeyword[3]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
	}

	private static void nianling_tudou(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "age\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
		}
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("21岁以下");
		Iqiyi.setLabelRate(listkeyword[0]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("20-29岁");
		Iqiyi.setLabelRate(listkeyword[1]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("30-39岁");
		Iqiyi.setLabelRate(listkeyword[2]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("40岁以上");
		Iqiyi.setLabelRate(listkeyword[3]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
	}

	private static void xingbei_tudou(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "sex\":[\"", "\"]}");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("男");
		Iqiyi.setLabelRate(nanxing);
		Iqiyi.setDimensionType(1);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("女");
		Iqiyi.setLabelRate(nvhai);
		Iqiyi.setDimensionType(1);
		Iqiyi.setSource(3);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		
		
	}

	private static void juqingfenxi_tudou(String id, String name, String url_show, String strHtml_show) {
		// TODO Auto-generated method stub
		String idurl=HtmlAnalyze.getTagText(strHtml_show, "_z", "\"");
		System.out.println(idurl);
		if (idurl.equals("")) {
			return;
		}
		//http://index.youku.com/ProAction!getProVideos.action?proId=d56886dc86fc11e3a705&pageIndex=0&pageSize=10&type=main&tag=youku
		//http://index.youku.com/ProAction!getProVideos.action?proId=d56886dc86fc11e3a705&pageIndex=1&pageSize=10&type=main&tag=youku
		//http://index.youku.com/ProAction!getProVideos.action?proId=d56886dc86fc11e3a705&pageIndex=2&pageSize=10&type=main&tag=tudou
		String url_json="http://index.youku.com/ProAction!getProVideos.action?proId="+idurl+"&pageIndex=0&pageSize=10&type=main&tag=tudou";
		
		String strHtml = Htmlurl(url_json);
		System.out.println(strHtml);
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		if (strHtml.equals("")) {
			return;
		}

		people = JSONObject.fromObject(strHtml);
		
		System.out.println(people.getString("totalPage"));
		int totalPage=Integer.valueOf(people.getString("totalPage"));
		
		list=people.getJSONArray("videos");
		
		System.out.println(list);
		
		for (int i = 0; i < totalPage; i++) {
			url_json="http://index.youku.com/ProAction!getProVideos.action?proId="+idurl+"&pageIndex="+i+"&pageSize=10&type=main&tag=tudou";
			
			
			 strHtml = Htmlurl(url_json);
			System.out.println(strHtml);
			
			 people = new JSONObject();
			 list = new JSONArray();
			if (strHtml.equals("")) {
				return;
			}

			people = JSONObject.fromObject(strHtml);
			
//			System.out.println(people.getString("totalPage"));
//			int totalPage=Integer.valueOf(people.getString("totalPage"));
			
			list=people.getJSONArray("videos");
			
//			System.out.println(list);
			for (Object object : list) {
				
				System.out.println(object.toString());
				
				
				TEM_CHINA_NETWORK_VIDEO_INDEX index= new TEM_CHINA_NETWORK_VIDEO_INDEX();
				
				index.setDataId(id);
				index.setDataName(name);
				
				String[] listString=object.toString().split("\\^");
				String PLAY_TITLE="";
				System.out.println(PLAY_TITLE=listString[1]);
				index.setPlayTitle(PLAY_TITLE);
				String PLAY_NUM="";
				System.out.println(PLAY_NUM=listString[2]);
				index.setPlayNum(PLAY_NUM);
				String PLAY_TOP="";
				System.out.println(PLAY_TOP=listString[3]);
				index.setPlayTop(PLAY_TOP);
				String PLAY_STEP="";
				System.out.println(PLAY_STEP=listString[4]);
				index.setPlayStep(PLAY_STEP);
				String COMMENTS="";
				System.out.println(COMMENTS=listString[5]);
				index.setComments(COMMENTS);
				String COLLECTION="";
				System.out.println(COLLECTION=listString[6]);
				index.setCollection(COLLECTION);	
				String DISPLAY_OUT="";	
				System.out.println(DISPLAY_OUT=listString[7]);
				index.setDisplayOut(DISPLAY_OUT);
				index.setType(2);
				Oracle.InsertTEM_TEM_CHINA_NETWORK_VIDEO_INDEX(index);
				
			}
			
			
			
			
		}
		
		
		
		
		
//		TEM_CHINA_NETWORK_VIDEO_INDEX
		
		
	}

	private static void bofangwangzhan_tudou(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "domain\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		
		
		String youkuDatavvString_num =HtmlAnalyze.getTagText(strHtml_show, "platform\":[\"", "\"]");
		
		String[] listkeyword_num = youkuDatavvString_num.toString().split("\",\"");
		
		
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
//			String city="";
//			String num="";
//			System.out.println(city=HtmlAnalyze.getTagText("#"+listkeyword[i], "#", ":"));
//			System.out.println(num=HtmlAnalyze.getTagText(listkeyword[i]+"#", ":" ,"#"));
			TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
			Iqiyi.setDataId(id);
			Iqiyi.setWord(name);
			Iqiyi.setLabelName(listkeyword[i]);
			Iqiyi.setLabelRate(listkeyword_num[i]);
			Iqiyi.setDimensionType(9);
			Iqiyi.setSource(3);
			Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		}
		
		
		
	}

	private static void bofangzishu_tudou(String id, String name, String url, String strHtml_show) {
		
		
		
		// TODO Auto-generated method stub
		if (!url.contains("youku")) {
			return;
			
		}
		
		
		 url=url.replace("youku", "tudou");
				
		
		strHtml_show =Htmlurl(url);
		
		if (strHtml_show==null) {
			return;
		}
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "vv\":[\"", ",\"name\":");
		System.out.println(youkuDatavvString);
		
		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		String[] listkeyword = StringdataString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			String palydate = DemoTime.getBeforeAfterDate(youkuData, 0-i).toString();
			System.out.println(palydate);
			System.out.println(listkeyword[i]);
			
			
			 TVPLAY_IQIYI_INDEX tvplayindex= new TVPLAY_IQIYI_INDEX();
		        tvplayindex.setDataUrl(url);
		        tvplayindex.setTvplayName(name);
		        tvplayindex.setVCount(listkeyword[i]);
		        tvplayindex.setDataDate(palydate.replace("-", ""));
//		        tvplayindex.set
		        
		        tvplayindex.setSOURCE(4);
		        Oracle.InsertTVPLAY_IQIYI_INDEX_SOURCE(tvplayindex);
			
			
		}
		
	}

	private static void bofangshebei(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "device\":[\"", "\"]}");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String pc = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
		String shouji = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("pc");
		Iqiyi.setLabelRate(pc);
		Iqiyi.setDimensionType(7);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("手机");
		Iqiyi.setLabelRate(shouji);
		Iqiyi.setDimensionType(7);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
	}

	private static void juqingfenxi(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		String idurl=HtmlAnalyze.getTagText(strHtml_show, "_z", "\"");
		System.out.println(idurl);
		if (idurl.equals("")) {
			return;
		}
		//http://index.youku.com/ProAction!getProVideos.action?proId=d56886dc86fc11e3a705&pageIndex=0&pageSize=10&type=main&tag=youku
		String url_json="http://index.youku.com/ProAction!getProVideos.action?proId="+idurl+"&pageIndex=0&pageSize=10&type=main&tag=youku";
		String strHtml = Htmlurl(url_json);
		System.out.println(strHtml);
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		if (strHtml.equals("")) {
			return;
		}

		people = JSONObject.fromObject(strHtml);
		
		System.out.println(people.getString("totalPage"));
		int totalPage=Integer.valueOf(people.getString("totalPage"));
		
		list=people.getJSONArray("videos");
		
		System.out.println(list);
		
		for (int i = 0; i < totalPage; i++) {
			url_json="http://index.youku.com/ProAction!getProVideos.action?proId="+idurl+"&pageIndex="+i+"&pageSize=10&type=main&tag=youku";
			
			
			 strHtml = Htmlurl(url_json);
			System.out.println(strHtml);
			
			 people = new JSONObject();
			 list = new JSONArray();
			if (strHtml.equals("")) {
				return;
			}

			people = JSONObject.fromObject(strHtml);
			
//			System.out.println(people.getString("totalPage"));
//			int totalPage=Integer.valueOf(people.getString("totalPage"));
			
			list=people.getJSONArray("videos");
			
//			System.out.println(list);
			for (Object object : list) {
				
				System.out.println(object.toString());
				
				
				TEM_CHINA_NETWORK_VIDEO_INDEX index= new TEM_CHINA_NETWORK_VIDEO_INDEX();
				
				index.setDataId(id);
				index.setDataName(name);
				
				String[] listString=object.toString().split("\\^");
				String PLAY_TITLE="";
				System.out.println(PLAY_TITLE=listString[1]);
				index.setPlayTitle(PLAY_TITLE);
				String PLAY_NUM="";
				System.out.println(PLAY_NUM=listString[2]);
				index.setPlayNum(PLAY_NUM);
				String PLAY_TOP="";
				System.out.println(PLAY_TOP=listString[3]);
				index.setPlayTop(PLAY_TOP);
				String PLAY_STEP="";
				System.out.println(PLAY_STEP=listString[4]);
				index.setPlayStep(PLAY_STEP);
				String COMMENTS="";
				System.out.println(COMMENTS=listString[5]);
				index.setComments(COMMENTS);
				String COLLECTION="";
				System.out.println(COLLECTION=listString[6]);
				index.setCollection(COLLECTION);	
				String DISPLAY_OUT="";	
				System.out.println(DISPLAY_OUT=listString[7]);
				index.setDisplayOut(DISPLAY_OUT);
				index.setType(1);
				Oracle.InsertTEM_TEM_CHINA_NETWORK_VIDEO_INDEX(index);
				
			}
			
			
			
			
		}
		
		
		
		
		
//		TEM_CHINA_NETWORK_VIDEO_INDEX
		
		
	}

	private static void bofangwangzhan(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "domain\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		
		
		String youkuDatavvString_num =HtmlAnalyze.getTagText(strHtml_show, "platform\":[\"", "\"]");
		
		String[] listkeyword_num = youkuDatavvString_num.toString().split("\",\"");
		
		
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
//			String city="";
//			String num="";
//			System.out.println(city=HtmlAnalyze.getTagText("#"+listkeyword[i], "#", ":"));
//			System.out.println(num=HtmlAnalyze.getTagText(listkeyword[i]+"#", ":" ,"#"));
			TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
			Iqiyi.setDataId(id);
			Iqiyi.setWord(name);
			Iqiyi.setLabelName(listkeyword[i]);
			Iqiyi.setLabelRate(listkeyword_num[i]);
			Iqiyi.setDimensionType(9);
			Iqiyi.setSource(2);
			Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		}
		
		
		
	}

	private static void chengsi(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "provinceMap\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
			String city="";
			String num="";
			System.out.println(city=HtmlAnalyze.getTagText("#"+listkeyword[i], "#", ":"));
			System.out.println(num=HtmlAnalyze.getTagText(listkeyword[i]+"#", ":" ,"#"));
			TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
			Iqiyi.setDataId(id);
			Iqiyi.setWord(name);
			Iqiyi.setLabelName(city);
			Iqiyi.setLabelRate(num);
			Iqiyi.setDimensionType(3);
			Iqiyi.setSource(2);
			Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		}
		
		
		
		
		
		
		
	}

	private static void xueli(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "edu\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
		}
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("硕士以上");
		Iqiyi.setLabelRate(listkeyword[0]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("本科");
		Iqiyi.setLabelRate(listkeyword[1]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("大专");
		Iqiyi.setLabelRate(listkeyword[2]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("高校/技校");
		Iqiyi.setLabelRate(listkeyword[3]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("初中以下");
		Iqiyi.setLabelRate(listkeyword[4]);
		Iqiyi.setDimensionType(5);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
	}

	private static void zhiye(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "occupation\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
		}
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("学生");
		Iqiyi.setLabelRate(listkeyword[0]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("白领");
		Iqiyi.setLabelRate(listkeyword[1]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("非全职");
		Iqiyi.setLabelRate(listkeyword[2]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("公务员");
		Iqiyi.setLabelRate(listkeyword[3]);
		Iqiyi.setDimensionType(8);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
	}

	private static void nianling(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "age\":[\"", "\"]");
		System.out.println(youkuDatavvString);
		if (youkuDatavvString.equals("")) {
			return;
		}
		
//		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		
//		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
//		
////		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
//		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		String[] listkeyword = youkuDatavvString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			System.out.println(listkeyword[i]);
		}
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("21岁以下");
		Iqiyi.setLabelRate(listkeyword[0]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("20-29岁");
		Iqiyi.setLabelRate(listkeyword[1]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("30-39岁");
		Iqiyi.setLabelRate(listkeyword[2]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("40岁以上");
		Iqiyi.setLabelRate(listkeyword[3]);
		Iqiyi.setDimensionType(2);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
	}

	private static void xingbei(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "sex\":[\"", "\"]}");
		System.out.println(youkuDatavvString);
		
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String nanxing = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
		String nvhai = HtmlAnalyze.getTagText(youkuDatavvString+"#", "\",\"", "#");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		
		
		TEM_IQIYI_AND_YOUKU_WORD_INDEX Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("男");
		Iqiyi.setLabelRate(nanxing);
		Iqiyi.setDimensionType(1);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		
		Iqiyi=new TEM_IQIYI_AND_YOUKU_WORD_INDEX();
		Iqiyi.setDataId(id);
		Iqiyi.setWord(name);
		Iqiyi.setLabelName("女");
		Iqiyi.setLabelRate(nvhai);
		Iqiyi.setDimensionType(1);
		Iqiyi.setSource(2);
		Oracle.InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(Iqiyi);
		
		
		
		
	}

	private static void bofangzishu_youku(String id, String name, String url, String strHtml_show) {
		// TODO Auto-generated method stub
		
		String youkuDatavvString =HtmlAnalyze.getTagText(strHtml_show, "vv\":[\"", ",\"name\":");
		System.out.println(youkuDatavvString);
		
		if (youkuDatavvString.equals("")) {
			return;
		}
		
		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		
//		String youkuData = HtmlAnalyze.getTagText("#"+youkuDatavvString, "#", "\",\"");
		String StringdataString=HtmlAnalyze.getTagText(youkuDatavvString, "[\"", "\"]");
		String[] listkeyword = StringdataString.toString().split("\",\"");
		for (int i = 0; i < listkeyword.length; i++) {
			String palydate = DemoTime.getBeforeAfterDate(youkuData, 0-i).toString();
			System.out.println(palydate);
			System.out.println(listkeyword[i]);
			
			
			 TVPLAY_IQIYI_INDEX tvplayindex= new TVPLAY_IQIYI_INDEX();
		        tvplayindex.setDataUrl(url);
		        tvplayindex.setTvplayName(name);
		        tvplayindex.setVCount(listkeyword[i]);
		        tvplayindex.setDataDate(palydate.replace("-", ""));
//		        tvplayindex.set
		        
		        tvplayindex.setSOURCE(3);
		        Oracle.InsertTVPLAY_IQIYI_INDEX_SOURCE(tvplayindex);
			
			
		}
		
	}

	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		int i = 0;
		while (i < 15  && (strHtml==null || strHtml.equals(""))) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;
		}
		return strHtml;
	}

}
