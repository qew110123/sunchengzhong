package com.artsoft.download.BaiDu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_PLAY_PERSON;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class TemTvplayPerson {
	
	
	public static void  play_people(String id ,String name,String url ,int soutce){
		
		String strHtml="";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		Elements linkli = doc.select("li.pages ul.actorList li.listItem");
		for (Element elementli : linkli) {
			TEM_PLAY_PERSON playpeoson=new TEM_PLAY_PERSON();
			playpeoson.setDataId(id);
			playpeoson.setDataName(name);
			playpeoson.setDataUrl(url);
			playpeoson.setSource(soutce);
			String imgurl="";
			System.out.println(imgurl = HtmlAnalyze.getTagText(elementli.toString(), "<img src=\"", "\""));
			playpeoson.setPersonSmallUrl(imgurl);
			String PERSON_BIG_URLall="";
			try {
				
				PERSON_BIG_URLall = elementli.select("a").first().attr("href");
			} catch (Exception e) {
				// TODO: handle exception
				
			}
			
			if (PERSON_BIG_URLall != "") {
				PERSON_BIG_URLall = "http://baike.baidu.com" + PERSON_BIG_URLall;
				System.out.println(PERSON_BIG_URLall);
			}
			playpeoson.setPersonUrl(PERSON_BIG_URLall);
			
			String title="";
			try {
				title = elementli.select("dl.info dt a").first().text();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (title.equals("")) {
				title=HtmlAnalyze.getTagText(elementli.toString(), "<dt>", "&nbsp; Œ&nbsp");
			}
			System.out.println(title);
			playpeoson.setPersonName(title);
			
			
			String ROLE_NAME="";
			
			System.out.println(ROLE_NAME = HtmlAnalyze.getTagText(elementli.toString(), " Œ&nbsp;", "</dt>"));
			playpeoson.setRoleName(ROLE_NAME);
			
			String ROLE_INTRO="";
			System.out.println(ROLE_INTRO = HtmlAnalyze.getTagText(elementli.toString(), "<dd title=\"", "\">"));
			playpeoson.setRoleIntro(ROLE_INTRO);
			
			
			String DUBBING_NAME="";
			System.out.println(DUBBING_NAME=HtmlAnalyze.getTagText(elementli.toString(), "≈‰“Ù&nbsp;&nbsp;</em>", "</dd>"));
			playpeoson.setDubbingName(DUBBING_NAME);
			
//			String 
//			
			OracleHaoSou.intoTEM_PLAY_PERSON(playpeoson);
			
			// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-actor
			// span.item-value").toString(), "href=\"", "\""));
//			System.out.println(dubbingname = elementli.select("div.role-voice span.item-value").text());
//			System.out.println(dubbingurl = elementli.select("div.role-voice span.item-value a").attr("href"));
			// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-voice
			// span.item-value").toString(), "href=\"", "\""));
			
			
//			System.out.println(
//					roleintro = HtmlAnalyze.getTagText(elementli.toString(), "role-description\">", "</dd>"));
//			if (personurl != "") {
//				personurl = "http://baike.baidu.com" + personurl;
//			}
//			if (dubbingurl != "") {
//				dubbingurl = "http://baike.baidu.com" + dubbingurl;
//			}
//			
//			if (PERSON_BIG_URLall != "") {
//				PERSON_BIG_URLall = "http://baike.baidu.com" + PERSON_BIG_URLall;
//				String strHtmlPERSON_BIG_URLall = DownloadUtil.getHtmlText(PERSON_BIG_URLall, 1000 * 30, "UTF-8", null, null);
//				Document docPERSON_BIG_URLall = Jsoup.parse(strHtmlPERSON_BIG_URLall);
//				PERSON_BIG_URL=docPERSON_BIG_URLall.getElementById("imgPicture").attr("src");
//			}
			
//			OracleHaoSou.intotemtvplay(strId, strUrlname, url, "", personname, personurl, rolename, personstillsurl,
//					dubbingname, dubbingurl, roleintro,PERSON_BIG_URL);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		play_people("0", "ª∂¿÷ÀÃ", "http://baike.baidu.com/link?url=DyeQ2-aSHRhiQ4kpvaDbQXqZK7negVOH5MyhqLVxQQZc6lIuWN4RP5Z3AAuTPMsNooELgs01WtdoIEE-TB8dmnB8iwlBlIqFpip0FgALWhZ0cNSkDRjlZGc74UhRtFn1", 0);
		
		play_people("0", "111", "http://baike.baidu.com/view/9333778.htm", 3);
	}

}
