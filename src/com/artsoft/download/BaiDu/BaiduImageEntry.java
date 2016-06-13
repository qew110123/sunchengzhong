package com.artsoft.download.BaiDu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_DIM_ENTRYIMG;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

/**
 * �ٶȴ�������ͼƬ
 * 
 * @author Administrator
 *
 */
public class BaiduImageEntry {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//id,url,name,sount
		baidumainurl("0","http://baike.baidu.com/view/13033889.htm","����",0);
	}

	public static void baidumainurl(String id, String url, String name, int sount) {
		// TODO Auto-generated method stub
		String html=DownloadUtil.getHtmlText(url, 30000, "utf-8", null, null);
		if (html==null) {
			return;
		}
		String citiaourl=HtmlAnalyze.getTagText(html, "<a class=\"more-link\" href=\"", "\"");
		if (citiaourl==null) {
			return;
		}
		String citiaoallurl="http://baike.baidu.com"+citiaourl;
		String htmlcitiao=DownloadUtil.getHtmlTextNew(citiaoallurl, 30000, "UTF-8", null, null);
		Element doc = Jsoup.parse(htmlcitiao);
		Element docalbumlist=doc.getElementById("album-list");
		Elements docimgs=docalbumlist.select("a");
		
		String bigurl="";
		String smiimg="";
		for (Element element : docimgs) {
//			System.out.println(element);
//			System.out.println(element.text());
//			System.out.println(element.attr("title"));
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
			OracleHaoSou.intoTEM_DIM_ENTRYIMG(ENTRYIMG);
		}
		
	}

}