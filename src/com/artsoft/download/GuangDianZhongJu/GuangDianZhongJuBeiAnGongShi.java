package com.artsoft.download.GuangDianZhongJu;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class GuangDianZhongJuBeiAnGongShi {

	/**
	 * 广电总局备案公示
	 * 备案公示列表
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note
		// 进行数据的详细的数据的采集
		// String mainUrl =
		// "http://dsj.sarft.gov.cn/tims/site/views/applications/note/noteTable.shanty?id=014fed5c0b0442bc4028e4a14fa22a0f";
		// mainurl(mainUrl, "", "", "", "");

		// String urlxiangxi=
		// "http://dsj.sarft.gov.cn/tims/site/views/applications/note/view.shanty?appName=note&id=0150d07ff83776bf4028819a4d2365a7";
		// urlmain(urlxiangxi);
		
		
//		String urls = "";
//		for (int i = 9; i <= 9; i++) {
//			urls = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note&pageIndex="+i;
//			urlmainallold(urls);
//		}
		
//		urlmainold("http://dsj.sarft.gov.cn/tims/site/views/applications/note/view.shanty?appName=note&id=011fd9e461e5059c402881f71fd9e2a5");
		
		
		
		
		
//		mainurl("http://dsj.sarft.gov.cn/tims/site/views/applications/note/viewSubArticle.shanty?appName=note&id=20080717151146890077", "四川省");
		
		
//		System.out.println(isDigit("111"));
		
		
//		mainurl("http://dsj.sarft.gov.cn/tims/site/views/applications/note/viewSubArticle.shanty?appName=note&id=20070922222153520276", "中央台");
		
		/**
		 * 老数据
		 */

		String urls = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note&pageIndex=1";
		for (int i = 1; i <= 9; i++) {
			urls = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note&pageIndex="+i;
			urlmainall(urls);
		}
		

	}
	
	
	public static String dateString(String stringdate){
//		System.out.println(stringdate);
		String  textdate="";
		try {
			Pattern pattern = Pattern.compile("[0-9]{4}[年][0-9]{1,2}[月]");
//		    Matcher matcher = pattern.matcher("报备机构：北京超炫文化传播有限公司     2009年02月            许可证号：（京）字第408号");
		    Matcher matcher = pattern.matcher(stringdate);
		    String dateStr = null;
		    if(matcher.find()){
		      dateStr = matcher.group(0);
		    }
		     
		    String[] arr = null;
		    if (null != dateStr) {
		        arr = dateStr.split("(年|月)");
		    }
		    for (String item : arr) {
		        System.out.println(item);
		    }
		    textdate=arr[0]+"年"+arr[1]+"月";
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	    
		    
		return textdate;
	}
	
	/**
	 * 进行数据的整体数据的
	 * 
	 * @param urlxiangxi
	 */
	private static void urlmainallold(String urls) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urls, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("td table TBODY tr td a");
		for (Element element : links) {
			// System.out.println(element);
			String bbstr = element.attr("target");
			String urlss = element.attr("href");
			// System.out.println(bbstr);
			if (bbstr != null && !"".equals(bbstr)) {
				if (!"#".equals(urlss)) {
					urlss = "http://dsj.sarft.gov.cn" + urlss;
					System.out.println(urlss);
					urlmainold(urlss);
				}
			}
		}

	}
	
	
	private static void urlmainold(String urlxiangxi) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlxiangxi, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("table table a");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		// System.out.println(links.size());
//		String tvplayname = ""; // 剧名
//		String gdsubjectname = ""; // 题材名称
//		String prduucecompany = ""; // 报备机构
		String tvpalyarea = ""; // 地区
		String mainUrl = "";
		
		boolean bb11=false;
		for (Element link : links) {
			 System.out.println(link);
			 System.out.println(mainUrl = "http://dsj.sarft.gov.cn" + link.attr("href"));
			 System.out.println(tvpalyarea = link.text());
			 if (!"网站首页".equals(tvpalyarea)&&!"加入收藏".equals(tvpalyarea)) {
//				 System.out.println("11111111"+mainUrl);
//				 try {
//				 if (tvpalyarea.equals("江苏")) {
//					 bb11=true;
//					
//				}
//				 if (bb11) {
//					System.out.println(111);
					 mainurl(mainUrl, tvpalyarea);
//				}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
				 
			}

//			Elements linktd = link.select("td");
//			if (linktd.size() == 4) {
//				System.out.println(tvpalyarea = linktd.get(0).text());
//				System.out.println(tvplayname = linktd.get(1).text());
//				System.out.println(mainUrl = linktd.get(1).select("a").attr("href"));
//				System.out.println(gdsubjectname = linktd.get(2).text());
//				System.out.println(prduucecompany = linktd.get(3).text());
//				if (mainUrl != null || !"".equals(mainUrl)) {
//					if (!"".equals(mainUrl)) {
//						
//						mainUrl = "http://dsj.sarft.gov.cn" + mainUrl;
//						mainurl(mainUrl, tvplayname, gdsubjectname, prduucecompany, tvpalyarea);
//					}
//				}
//			}
			 
		}
	}
	
	
	
	public static boolean isDigit(String strNum) {  
	    Pattern pattern = Pattern.compile("[0-9]{1,}");  
	    Matcher matcher = pattern.matcher((CharSequence) strNum);  
	    return matcher.matches();  
	}
	
	

	private static void mainurl(String mainUrl, String tvpalyarea) {
		// TODO Auto-generated method stub

		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// System.out.println(strHtml);

		// String tvplayname=HtmlAnalyze.getTagText(strHtml,"<td height=\"30\"
		// style="FONT-SIZE: 16px;"> ", "\""); //新名称
		String strlistm = HtmlAnalyze.getTagText(strHtml, "<td height=\"12\" valign=\"top\"", "</td></tr></table>",false,0);
		// System.out.println(strlistm);
		if (strlistm == null) {
			return;
		}
		
		
//		strlistm = HtmlAnalyze.getTagText(strHtml, "<table width=\"800\" align=\"center\"><tr><td>", "</td></tr></table>",false,0);
		
//		System.out.println(strlistm);
		
//		String string=HtmlAnalyze.getTagText(strlistm, "</table>", "</p>",false,0);
//		
//		System.out.println(string);
//		
//		System.out.print(string);
		
		
		
		String[] listString= strlistm.split("<p style=\"margin: 0px; padding: 0px\">\r\n&nbsp;\r\n</p>");
		
		String tvplayname="";
		String gdsubjectname="";
		String prduucecompany="";
		
		
//		System.out.println(listString);
		
		for (String string : listString) {
			if (!string.toString().contains("报备机构")) {
				continue;
				
			}
			
//			System.out.println(string);
			Document doc = Jsoup.parse(string);

			String string2= HtmlAnalyze.getTagText(string, "制作周期", "内容提要");
			string2=string2.replace("\t\t\t\r\n", "").replace("\t", "").replace("\r", "");
			String[] sourceStrArray = string2.split("\n");
			int i = 0;
			String genre = "";// 体裁
			String setnumber = "";// 集
			String producedate = "";// 拍摄日期
			String producecycle = "";// 拍摄周期
			if (sourceStrArray[0].equals("一般")) {
				i = -5;
			}
			for (String strtext : sourceStrArray) {
				// System.out.println(i+strtext);
				strtext = strtext.replaceAll(" ", "").replaceAll("\r", "");
				// System.out.println(i + strtext + i);
				if (i == 5 || i == 3 || i == 4) {
					if (!strtext.toString().contains("nbsp")) {
						switch (i) {
						case 3:
							genre = "一般";
							break;
						case 4:
							genre = "喜剧";
							break;
						case 5:
							genre = "戏曲";
							break;
	
						default:
							genre = "";
							break;
						}
					}
				}			
				if (i == 6) {
					setnumber = strtext.replaceAll("&nbsp;", "");
	
				}
				if (i == 7) {
					producedate = strtext.replaceAll("&nbsp;", "");
					if (!producedate.contains(".")) {
						for (String string3 : sourceStrArray) {
							if (string3.contains(".")) {
								producedate = string3.replaceAll("&nbsp;", "");
							}
						}
						
					}
					
				}
				if (i == 8) {
					producecycle = strtext.replaceAll("&nbsp;", "");
					
					if (!producecycle.contains("个月")) {
						for (String string3 : sourceStrArray) {
							if (string3.contains("个月")) {
								producecycle = string3.replaceAll("&nbsp;", "");
							}
						}
						
					}
					
				}
				if (i == 1) {
					tvplayname = strtext.replaceAll("&nbsp;", "");
				}
				if (i == 2) {
					gdsubjectname = strtext.replaceAll("&nbsp;", "");
				}
	
				i += 1;
			}
			
			try {
				Integer.parseInt(setnumber);
			} catch (Exception e) {
				try {
				// TODO: handle exception
				String stringtext=HtmlAnalyze.getTagText(string, "序号", "<tr st",false,0);
				//<tr style="height: 30.05pt; page-break-inside: avoid">

				String statingtext=HtmlAnalyze.getTagText(string, "<tr style=\"height: 30.05pt; page-break-inside: avoid\">", "<tr st",false,0);
				
				if (statingtext.equals("")) {
					statingtext=HtmlAnalyze.getTagText(string, "<td width=\"31\" style=", "<tr st",false,0);
				}
				
				if (statingtext.equals("")) {
					statingtext=HtmlAnalyze.getTagText(string, "valign=\"top\"", "<tr st",false,0);
				}
				
				String[] listtext=stringtext.split("</p>");
				
				String[]listSting=statingtext.split("</p>");
				
				
				ArrayList<String> List = new ArrayList(); 
				
				
				for (String string3 : listSting) {
					Document docbbtext = Jsoup.parse(string3.replace("&nbsp; ", ""));
					System.out.println(docbbtext.text());
					List.add(docbbtext.text());
					
				}
				
				
				
				int iii=0;
				for (String string3 : listtext) {
//					System.out.println(string3);
					Document docbb = Jsoup.parse(string3.replace("&nbsp; ", ""));
					Document docbbtext = Jsoup.parse(listSting[iii].replace("&nbsp; ", ""));
					System.out.println(docbb.text());
					System.out.println(docbbtext.text());
					iii=iii+1;
					if (docbb.text().equals("剧名")) {
						tvplayname=docbbtext.text();
					}
					
					if (docbb.text().equals("集数")) {
						 docbbtext = Jsoup.parse(listSting[iii+2].replace("&nbsp; ", ""));
						System.out.println(docbbtext.text());
						setnumber=docbbtext.text();
						try {
							Integer.parseInt(setnumber);
						} catch (Exception e2) {
							// TODO: handle exception
							if (!isDigit(setnumber)) {
								for (String string4 : List) {
									if (isDigit(string4)) {
										setnumber = string4.replaceAll("&nbsp;", "");
									}
								}
								
							}
							
						}
						
					}
					
					if (docbb.text().equals("拍摄日期")) {
						docbbtext = Jsoup.parse(listSting[iii+2].replace("&nbsp; ", ""));
						producedate=docbbtext.text();
						
						if (!producedate.contains(".")) {
							for (String string4 : List) {
								if (string4.contains(".")) {
									producedate = string4.replaceAll("&nbsp;", "");
								}
							}
							
						}
						
						
					}
					
					if (docbb.text().equals("制作周期")) {
						docbbtext = Jsoup.parse(listSting[iii+2].replace("&nbsp; ", ""));
						producecycle=docbbtext.text();
						
						if (!producecycle.contains("个月")) {
							for (String string4 : List) {
								if (string4.contains("个月")) {
									producecycle = string4.replaceAll("&nbsp;", "");
								}
							}
							
						}
					}
					if (docbb.text().equals("题材")) {
//						docbbtext = Jsoup.parse(listSting[iii].replace("&nbsp; ", ""));
						gdsubjectname=docbbtext.text();
					}
					
					
					
				}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
			
			
			
			
			try {
				Integer.parseInt(setnumber);
			} catch (Exception e) {
				try {
				// TODO: handle exception
				String stringtext=HtmlAnalyze.getTagText(string, "序号", "<tr st",false,0);
				//<tr style="height: 30.05pt; page-break-inside: avoid">

				String statingtext=HtmlAnalyze.getTagText(string, "<tr style=\"height: 30.05pt; page-break-inside: avoid\">", "<tr st",false,0);
				
				if (statingtext.equals("")) {
					statingtext=HtmlAnalyze.getTagText(string, "<td width=\"31\" style=", "<tr st",false,0);
				}
				
				String[] listtext=stringtext.split("</p>");
				
				String[]listSting=statingtext.split("</p>");
				int iii=0;
				for (String string3 : listtext) {
//					System.out.println(string3);
					Document docbb = Jsoup.parse(string3.replace("&nbsp; ", ""));
					Document docbbtext = Jsoup.parse(listSting[iii].replace("&nbsp; ", ""));
					System.out.println(docbb.text());
					System.out.println(docbbtext.text());
					
					if (docbb.text().equals("剧名")) {
						tvplayname=docbbtext.text();
					}
					
					if (docbb.text().equals("集数")) {
						 docbbtext = Jsoup.parse(listSting[iii+2].replace("&nbsp; ", ""));
						System.out.println(docbbtext.text());
						setnumber=docbbtext.text();
					}
					
					if (docbb.text().equals("拍摄日期")) {
						docbbtext = Jsoup.parse(listSting[iii+2].replace("&nbsp; ", ""));
						producedate=docbbtext.text();
					}
					
					if (docbb.text().equals("制作周期")) {
						docbbtext = Jsoup.parse(listSting[iii+2].replace("&nbsp; ", ""));
						producecycle=docbbtext.text();
					}
					
					
					if (docbb.text().equals("题材")) {
						docbbtext = Jsoup.parse(listSting[iii].replace("&nbsp; ", ""));
						gdsubjectname=docbbtext.text();
					}
					iii=iii+1;
				}
				
				
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
				
			if (!isDigit(setnumber)) {
				setnumber="0";
			}
			
			
			
			// genre
			genre="";
			System.out.println(genre);
			System.out.println(setnumber);
			System.out.println(producedate);
	
			System.out.println(producecycle);
			
			
	
			String reportcompany = HtmlAnalyze.getTagText(string, "报备机构：", "<span>");
//			String projectdate = HtmlAnalyze.getTagText(string,
//					"<span><span>", "</td>");
			
			String projectdate =dateString(doc.text());
			
	
			String shootinglicense = HtmlAnalyze.getTagText(string, "许可证号：", "<table");
	
			// <td height="200" colspan="4" align="left" style="FONT-SIZE: 16px;">
			// &nbsp;
			String provinceopinion = HtmlAnalyze.getTagText(string,
					"省级管理部门备案意见", "相关部门意见");
			String departmentopinion = HtmlAnalyze.getTagText(string, "相关部门意见", "</tr>");
			// 相关部门意见</td>
			System.out.println(departmentopinion = departmentopinion.replaceAll("&nbsp;", ""));
			String remark = HtmlAnalyze.getTagText(string, "备注", "</tr>");
			// System.out.println(remark);
			System.out.println(remark = remark.replaceAll("&nbsp;", "").replaceAll("\r\n", "").replaceAll("\t", ""));
			String tvplaycontent = HtmlAnalyze.getTagText(string, "内容提要：", "</td>");
			System.out.println(tvplaycontent);
			
			
//			try {
				OracleSarFtGov.intoShanty(tvplayname, gdsubjectname, prduucecompany, producedate, producecycle, setnumber,
						reportcompany, projectdate, shootinglicense, genre, provinceopinion, departmentopinion, remark, mainUrl,
						tvplaycontent, tvpalyarea);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			
			
			
		}
		
//		Document doc = Jsoup.parse(strlistm);
		// Element linkmain = doc.getElementById("fluxes_static");
//		System.out.println(doc);
//		Elements links = doc.select("table table tr");
		
		
		
		
//		String[] sourceStrArray = strlistm.split("\n");
//		int i = 0;
//		String genre = "";// 体裁
//		String setnumber = "";// 集
//		String producedate = "";// 拍摄日期
//		String producecycle = "";// 拍摄周期
//		for (String strtext : sourceStrArray) {
//			// System.out.println(i+strtext);
//			strtext = strtext.replaceAll(" ", "").replaceAll("\r", "");
//			// System.out.println(i + strtext + i);
//			if (i == 2 || i == 3 || i == 4) {
//				if (!strtext.toString().contains("nbsp")) {
//					switch (i) {
//					case 2:
//						genre = "一般";
//						break;
//					case 3:
//						genre = "喜剧";
//						break;
//					case 4:
//						genre = "戏曲";
//						break;
//
//					default:
//						genre = "";
//						break;
//					}
//				}
//			}			if (i == 5) {
//				setnumber = strtext.replaceAll("&nbsp;", "");
//
//			}
//			if (i == 6) {
//				producedate = strtext.replaceAll("&nbsp;", "");
//			}
//			if (i == 7) {
//				producecycle = strtext.replaceAll("&nbsp;", "");
//			}
//
//			i += 1;
//		}
//		// genre
//		System.out.println(genre);
//		System.out.println(setnumber);
//		System.out.println(producedate);
//
//		System.out.println(producecycle);
//
//		String reportcompany = HtmlAnalyze.getTagText(strHtml, "报备机构：", "</td>");
//		String projectdate = HtmlAnalyze.getTagText(strHtml,
//				"<td width=\"231\" align=\"center\" style=\"FONT-SIZE: 16px;\"> ", "</td>");
//
//		String shootinglicense = HtmlAnalyze.getTagText(strHtml, "许可证号： ", "</td>");
//
//		// <td height="200" colspan="4" align="left" style="FONT-SIZE: 16px;">
//		// &nbsp;
//		String provinceopinion = HtmlAnalyze.getTagText(strHtml,
//				"<td height=\"200\" colspan=\"4\" align=\"left\" style=\"FONT-SIZE: 16px;\"> &nbsp; ", "</td>");
//		String departmentopinion = HtmlAnalyze.getTagText(strHtml, "相关部门意见</td>", "</tr>");
//		// 相关部门意见</td>
//		System.out.println(departmentopinion = departmentopinion.replaceAll("&nbsp;", ""));
//		String remark = HtmlAnalyze.getTagText(strHtml, "备注</td>", "</tr>");
//		// System.out.println(remark);
//		System.out.println(remark = remark.replaceAll("&nbsp;", "").replaceAll("\r\n", "").replaceAll("\t", ""));
//		String tvplaycontent = HtmlAnalyze.getTagText(strHtml, "内容提要：", "</td>");
//		System.out.println(tvplaycontent);
//
//		OracleSarFtGov.intoShanty(tvplayname, gdsubjectname, prduucecompany, producedate, producecycle, setnumber,
//				reportcompany, projectdate, shootinglicense, genre, provinceopinion, departmentopinion, remark, mainUrl,
//				tvplaycontent, tvpalyarea);
		
		
	}

	/**
	 * 进行数据的整体数据的
	 * 
	 * @param urlxiangxi
	 */
	private static void urlmainall(String urls) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urls, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("td table TBODY tr td a");
		for (Element element : links) {
			// System.out.println(element);
			String bbstr = element.attr("target");
			String urlss = element.attr("href");
			// System.out.println(bbstr);
			if (bbstr != null && !"".equals(bbstr)) {
				if (!"#".equals(urlss)) {
					urlss = "http://dsj.sarft.gov.cn" + urlss;
					System.out.println(urlss);
					urlmain(urlss);
				}
			}
		}

	}

	/**
	 * 进行数据的整体数据的
	 * 
	 * @param urlxiangxi
	 */
	private static void urlmain(String urlxiangxi) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlxiangxi, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("table table tr");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		// System.out.println(links.size());
		String tvplayname = ""; // 剧名
		String gdsubjectname = ""; // 题材名称
		String prduucecompany = ""; // 报备机构
		String tvpalyarea = ""; // 地区
		String mainUrl = "";
		for (Element link : links) {
			// System.out.println(link);

			Elements linktd = link.select("td");
			if (linktd.size() == 4) {
				System.out.println(tvpalyarea = linktd.get(0).text());
				System.out.println(tvplayname = linktd.get(1).text());
				System.out.println(mainUrl = linktd.get(1).select("a").attr("href"));
				System.out.println(gdsubjectname = linktd.get(2).text());
				System.out.println(prduucecompany = linktd.get(3).text());
				if (mainUrl != null || !"".equals(mainUrl)) {
					if (!"".equals(mainUrl)) {
						
						mainUrl = "http://dsj.sarft.gov.cn" + mainUrl;
						mainurl(mainUrl, tvplayname, gdsubjectname, prduucecompany, tvpalyarea);
					}
				}
			}
		}
	}

	/**
	 * 进行数据的详细采集
	 * 
	 * @param mainUrl
	 * @param tvplayname
	 * @param gdsubjectname
	 * @param prduucecompany
	 * @param tvpalyarea
	 */
	private static void mainurl(String mainUrl, String tvplayname, String gdsubjectname, String prduucecompany,
			String tvpalyarea) {
		// TODO Auto-generated method stub

		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// System.out.println(strHtml);

		// String tvplayname=HtmlAnalyze.getTagText(strHtml,"<td height=\"30\"
		// style="FONT-SIZE: 16px;"> ", "\""); //新名称
		String strlistm = HtmlAnalyze.getTagText(strHtml, "<td height=\"30\" style=\"FONT-SIZE: 16px;\"> ", "</tr>");
		// System.out.println(strlistm);
		if (strlistm == null) {
			return;
		}
		String[] sourceStrArray = strlistm.split("\n");
		int i = 0;
		String genre = "";// 体裁
		String setnumber = "";// 集
		String producedate = "";// 拍摄日期
		String producecycle = "";// 拍摄周期
		for (String strtext : sourceStrArray) {
			// System.out.println(i+strtext);
			strtext = strtext.replaceAll(" ", "").replaceAll("\r", "");
			// System.out.println(i + strtext + i);
			if (i == 2 || i == 3 || i == 4) {
				if (!strtext.toString().contains("nbsp")) {
					switch (i) {
					case 2:
						genre = "一般";
						break;
					case 3:
						genre = "喜剧";
						break;
					case 4:
						genre = "戏曲";
						break;

					default:
						genre = "";
						break;
					}
				}
			}			if (i == 5) {
				setnumber = strtext.replaceAll("&nbsp;", "");

			}
			if (i == 6) {
				producedate = strtext.replaceAll("&nbsp;", "");
			}
			if (i == 7) {
				producecycle = strtext.replaceAll("&nbsp;", "");
			}

			i += 1;
		}
		// genre
		System.out.println(genre);
		System.out.println(setnumber);
		System.out.println(producedate);

		System.out.println(producecycle);

		String reportcompany = HtmlAnalyze.getTagText(strHtml, "报备机构：", "</td>");
		String projectdate = HtmlAnalyze.getTagText(strHtml,
				"<td width=\"231\" align=\"center\" style=\"FONT-SIZE: 16px;\"> ", "</td>");

		String shootinglicense = HtmlAnalyze.getTagText(strHtml, "许可证号： ", "</td>");

		// <td height="200" colspan="4" align="left" style="FONT-SIZE: 16px;">
		// &nbsp;
		String provinceopinion = HtmlAnalyze.getTagText(strHtml,
				"<td height=\"200\" colspan=\"4\" align=\"left\" style=\"FONT-SIZE: 16px;\"> &nbsp; ", "</td>");
		String departmentopinion = HtmlAnalyze.getTagText(strHtml, "相关部门意见</td>", "</tr>");
		// 相关部门意见</td>
		System.out.println(departmentopinion = departmentopinion.replaceAll("&nbsp;", ""));
		String remark = HtmlAnalyze.getTagText(strHtml, "备注</td>", "</tr>");
		// System.out.println(remark);
		System.out.println(remark = remark.replaceAll("&nbsp;", "").replaceAll("\r\n", "").replaceAll("\t", ""));
		String tvplaycontent = HtmlAnalyze.getTagText(strHtml, "内容提要：", "</td>");
		System.out.println(tvplaycontent);
		try {
			OracleSarFtGov.intoShanty(tvplayname, gdsubjectname, prduucecompany, producedate, producecycle, setnumber,
					reportcompany, projectdate, shootinglicense, genre, provinceopinion, departmentopinion, remark, mainUrl,
					tvplaycontent, tvpalyarea);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
				// String gdsubjectname="";//题材名称
				// String producedate = "";// 拍摄日期
				// String tyPlayName = "";
				// System.out.println(tyPlayName =
				// HtmlAnalyze.getTagText(strHtml,
				// "cname: \"", "\""));

		// System.out.println(strHtml);
		// Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		// Elements links = linkmain.select("ul.v-list-inner li.v-list-unit");
		// Element content = doc.getElementById("content");
	}

}
