package com.artsoft.download.BaiDu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_DIM_ENTRYIMG;
import com.artsoft.demo.imag.Image1;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

/**
 * 百度词条数据图片
 * 
 * @author Administrator
 *
 */
public class BaiduImageEntry {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//id,url,name,sount
		baidumainurl("0","http://baike.baidu.com/view/13033889.htm","超体",0);
	}

	public static void baidumainurl(String id, String url, String name, int sount) {
		// TODO Auto-generated method stub
		String html=DownloadUtil.getHtmlText(url, 30000, "utf-8", null, null);
		if (html==null) {
			return;
		}
		String citiaourl=HtmlAnalyze.getTagText(html, "<a class=\"more-link\" href=\"", "\"");
		
		if (citiaourl==null||citiaourl.equals("")) {
			try {
				Document docss = Jsoup.connect(url).get();
				html=docss.toString();
				citiaourl=HtmlAnalyze.getTagText(html, "<a class=\"more-link\" href=\"", "\"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (citiaourl==null||citiaourl.equals("")) {
			return;
		}
		
		String citiaoallurl="http://baike.baidu.com"+citiaourl;
//		String htmlcitiao=DownloadUtil.getHtmlTextNew(citiaoallurl, 30000, "utf-8", null, null);
		
//		Element doc = Jsoup.parse(htmlcitiao);
		Document doc=null;
		try {
			doc = Jsoup.connect(citiaoallurl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String stills_title="";
		Elements docalbumitem=doc.select("div.album-item");
		for (Element iterable_element : docalbumitem) {
//			System.out.println(iterable_element);
//			System.out.println(iterable_element.text());
//			System.out.println(iterable_element.select("div.album-title"));
			stills_title= HtmlAnalyze.getTagText(iterable_element.select("div.album-title").toString(), "\"album-title yahei\">", "</div>");
			System.out.println(stills_title);
			
			
			Elements docimgs=iterable_element.select("a");
			
			String bigurl="";
			String smiimg="";
			int xx=1;
			for (Element element : docimgs) {
//				System.out.println(element);
//				System.out.println(element.text());
//				System.out.println(element.attr("title"));
				String bigallurl=element.attr("href");
				if (bigallurl!=null) {
					bigallurl="http://baike.baidu.com"+bigallurl;
				}
				String strHtmlPERSON_BIG_URLall=DownloadUtil.getHtmlTextNew(bigallurl, 30000, "UTF-8", null, null);
				Document docPERSON_BIG_URLall = Jsoup.parse(strHtmlPERSON_BIG_URLall);
				TEM_DIM_ENTRYIMG ENTRYIMG=new TEM_DIM_ENTRYIMG();
				bigurl=docPERSON_BIG_URLall.getElementById("imgPicture").attr("src");
				System.out.println(bigurl);
				ENTRYIMG.setBigUrl(bigurl);
				System.out.println(smiimg=element.select("img").first().attr("src"));
				ENTRYIMG.setSmallUrl(smiimg);
				ENTRYIMG.setDataUrl(url);
				ENTRYIMG.setDataTitle(name);
				ENTRYIMG.setDataType(sount);
				ENTRYIMG.setDataId(id);
				ENTRYIMG.setSTILLS_TITLE(stills_title);
				ENTRYIMG.setSTILLS_ORDERNO(xx);
				if (sount==0) {
					
					String sMALL_NAME="";
					sMALL_NAME=Image1.downloadimg_baidu_Tvplay_small(smiimg);
					
					ENTRYIMG.setSMALL_NAME(sMALL_NAME);
					String bIG_NAME="";
					bIG_NAME=Image1.downloadimg_baidu_Tvplay_big(bigurl);
					ENTRYIMG.setBIG_NAME(bIG_NAME);
				}
				OracleHaoSou.intoTEM_DIM_ENTRYIMG(ENTRYIMG);
				xx+=1;
			}
			
			
//			request.setCharacterEncoding(encoding);   
		}
		
		
		
		
//		Element docalbumlist=doc.getElementById("album-list");
//		Elements docimgs=docalbumlist.select("a");
//		
//		String bigurl="";
//		String smiimg="";
//		for (Element element : docimgs) {
////			System.out.println(element);
////			System.out.println(element.text());
////			System.out.println(element.attr("title"));
//			String bigallurl=element.attr("href");
//			if (bigallurl!=null) {
//				bigallurl="http://baike.baidu.com"+bigallurl;
//			}
//			String strHtmlPERSON_BIG_URLall=DownloadUtil.getHtmlTextNew(bigallurl, 30000, "UTF-8", null, null);
//			Document docPERSON_BIG_URLall = Jsoup.parse(strHtmlPERSON_BIG_URLall);
//			TEM_DIM_ENTRYIMG ENTRYIMG=new TEM_DIM_ENTRYIMG();
//			bigurl=docPERSON_BIG_URLall.getElementById("imgPicture").attr("src");
//			System.out.println(bigurl);
//			ENTRYIMG.setBigUrl(bigurl);
//			System.out.println(smiimg=element.select("img").first().attr("src"));
//			ENTRYIMG.setSmallUrl(smiimg);
//			ENTRYIMG.setDataUrl(url);
//			ENTRYIMG.setDataTitle(name);
//			ENTRYIMG.setDataType(sount);
//			ENTRYIMG.setDataId(id);
////			OracleHaoSou.intoTEM_DIM_ENTRYIMG(ENTRYIMG);
//		}
		
	}

}
