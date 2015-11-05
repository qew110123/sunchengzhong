package com.artsoft.download;

import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class BaiDuPopularity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; i++) {
			String mainUrl = "http://baike.baidu.com/operation/api/starflowerstarlist?rankType=thisWeek&pg="+i;
			mainurl(mainUrl);
			
		}

	}

	private static void mainurl(String mainUrl) {
		// TODO Auto-generated method stub
		
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String strhtmlurl="";
		if (strHtml != null && !"".equals(strHtml)) {
			strHtml = DownloadUtil.decodeUnicode(strHtml);
//			System.out.println(strHtml);
		}
		
		String[] sourceStrArray = strHtml.split("rank");
//		System.out.println(sourceStrArray.length);
		String personName = "";
		String personUrl = "";
		String photoUrl="";
		
		int i=0;
		for (String strname : sourceStrArray) {
			 personName = HtmlAnalyze.getTagText(strname, "\"name\":\"", "\"");
			 personUrl = HtmlAnalyze.getTagText(strname, "url\":\"", "\"");
			 photoUrl = HtmlAnalyze.getTagText(strname, "picUrl\":\"", "\"");
			 if (personName!=null&&photoUrl!=null) {
				 System.out.println(++i);
				 System.out.println(personName);
				 if (personUrl!=null) {
					 System.out.println(personUrl="http://baike.baidu.com"+personUrl);					
				}else{
					personUrl="";
				}
				 System.out.println(photoUrl);
				 
				 OracleHaoSou.intoBaiDuPopularity(personName, personUrl, photoUrl);
			}
			
		}
//		System.out.println(sourceStrArray[0]);
//		System.out.println(strfansCount = sourceStrArray[1].replaceAll("·ÛË¿", "").replaceAll("Íò", "0000"));
//		System.out.println(strvCount = sourceStrArray[2].replaceAll("Î¢²©", ""));


	}

}
