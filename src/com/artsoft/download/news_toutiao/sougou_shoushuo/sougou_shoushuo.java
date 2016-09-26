package com.artsoft.download.news_toutiao.sougou_shoushuo;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class sougou_shoushuo {

	private static void runnew_shouhu(String urlMain,int dATA_TYPE,String id) {
		// TODO Auto-generated method stub
		// ="http://ent.163.com/special/yyx/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);

		Document doc = Jsoup.parse(strHtml);
		//
		Elements links = doc.select("div.results div.vrwrap ");
		for (Element link : links) {
			try {
				// System.out.println(link);
				WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();

				// wechat.setWeixinhao(weixinhao);
				// wechat.setWeixinAtion(renzheng);
				// wechat.setRanking(paiming);
				// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				// String names=link.select("a").first().text();
				String url = link.select("a").first().attr("href");
				System.out.println(url);
				// url="http://ent.qq.com"+url;
				wechat.setUrls(url);
				// wechat.setUrls(urls);
				// wechat.setUrls(url);

				String names = link.select("a").first().text();
				System.out.println(names);
				wechat.setNames(names);

				String DATES = link.select("p.news-from").toString();// .text();
				DATES = HtmlAnalyze.getTagText(DATES, "&nbsp;", "\n");
				if (DATES.equals("") || DATES == null) {
					return;
				}
				wechat.setDates(DATES);

				String PostUser = link.select("p.news-from").toString();// .text();
				PostUser = HtmlAnalyze.getTagText(PostUser, "\">", "&nbsp;");
				wechat.setPostUser(PostUser);

				// String imgurl=link.select("img").first().attr("src");
				// System.out.println(imgurl);
				//
				// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				//
				// String imgname="";
				// if (!imgurl.equals("")&&imgurl!=null) {
				// String imgurls=imgurl.replace("\\/", "/");
				// imgname=Image2.imagUrldownload_1(imgurls);
				// }
				//
				// wechat.setIMG_BIG_NAME(imgname);

				String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "gbk", null, null);

				Document docs = Jsoup.parse(strHtmls);
				// String names=docs.getElementById("page").select("h1").text();
				// System.out.println(names);
				// wechat.setNames(names);

				// DATES=docs.select("span.time").text();
				// if (DATES.equals("")) {
				// DATES=docs.select("div.time").text();
				// }
				// wechat.setDates(DATES);

				String content_alls = "";

				Elements conts = docs.select("div");
				for (Element element : conts) {
					if (element.attr("itemprop").equals("articleBody")) {
						content_alls = element.toString();
					}

				}

				String CONTENT_ALL = content_alls.toString();

				wechat.setContentAll(CONTENT_ALL);
				// String CONTENT_P=docs.select("article-detail");

				while (CONTENT_ALL.contains("<script")) {

					String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
					CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
				}

				Element docs_contentps = Jsoup.parse(content_alls);

				Elements js_contentps = docs_contentps.select("p");
				String js_contentStringp = "";
				int ii = 0;
				int iii = 0;
				String leibiaoimg = "";
				for (Element element : js_contentps) {
					// if (ii>2) {
					String Stringelement = "";

					if (element.toString().contains("<img")) {
						// Stringelement=element.toString();
						try {
							String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">", false, 0);
							String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "src=\"", "\"");
							String newimgurl = Image2.imagUrldownload_allurl_baidu(imgurlhtml);
							if (iii == 0) {
								if ( !wechat.getIMG_BIG_NAME().equals("")) {
									
									leibiaoimg = newimgurl;
								}
							}
							iii += 1;
							Stringelement = element.toString().replace(imgotherhtml,
									"<img src=\"" + newimgurl + "\" >");

						} catch (Exception e) {
							// TODO: handle exception
							Stringelement = element.toString();
						}

					} else {
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
									.replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother = docStringelement.text();
							Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
									.replace("#1#/strong#2#", "</strong>");

						} else {
							Stringelement = element.text();
						}

					}
					js_contentStringp = js_contentStringp + Stringelement + "||";
					// }
					ii += 1;

				}
				// wechat1.setContentP(js_contentStringp);

				if (!leibiaoimg.equals("") || leibiaoimg == null) {
					// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/contents/"
							+ TimeTest.getNowTime("yyyyMMdd") + "/");
					String[] namelist = leibiaoimg.split("/");
					// try {
					String nameurl = namelist[namelist.length - 1];
					wechat.setIMG_BIG_NAME(nameurl);

				}

				System.out.println(js_contentStringp);

				// wechat.setContentP(js_contentStringp);
				wechat.setContentP(content_alls);

				wechat.setSOURCE(6);

				// wechat.setPostUser("À—∫¸”È¿÷");
				
				wechat.setDATA_TYPE(dATA_TYPE);
				
				wechat.setDataId(id);

				Oracle.InsertWECHAT_INFORMATION_id(wechat);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	private static void runnew_qq(String urlMain,int dATA_TYPE,String id) {
		// TODO Auto-generated method stub
		// ="http://ent.163.com/special/yyx/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);

		Document doc = Jsoup.parse(strHtml);
		//
		Elements links = doc.select("div.results div.vrwrap ");
		for (Element link : links) {
			try {
				// System.out.println(link);
				WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();

				// wechat.setWeixinhao(weixinhao);
				// wechat.setWeixinAtion(renzheng);
				// wechat.setRanking(paiming);
				// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				// String names=link.select("a").first().text();
				String url = link.select("a").first().attr("href");
				System.out.println(url);
				// url="http://ent.qq.com"+url;
				wechat.setUrls(url);
				// wechat.setUrls(urls);
				// wechat.setUrls(url);

				String names = link.select("a").first().text();
				System.out.println(names);
				wechat.setNames(names);

				String DATES = link.select("p.news-from").toString();// .text();
				DATES = HtmlAnalyze.getTagText(DATES, "&nbsp;", "\n");
				if (DATES.equals("") || DATES == null) {
					return;
				}
				wechat.setDates(DATES);

				String PostUser = link.select("p.news-from").toString();// .text();
				PostUser = HtmlAnalyze.getTagText(PostUser, "\">", "&nbsp;");
				wechat.setPostUser(PostUser);
				
				try {
					
				
					 String imgurl=link.select("img").first().attr("src");
					 if (imgurl.equals("")||imgurl==null) {
						 imgurl= HtmlAnalyze.getTagText(link.toString(), "encodeURIComponent(\"", "\"");
					}
					 System.out.println(imgurl);
					
					 wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					
					 String imgname="";
					 if (!imgurl.equals("")&&imgurl!=null) {
					 String imgurls=imgurl.replace("\\/", "/");
					 imgname=Image2.imagUrldownload_1(imgurls);
					 }
					
					 wechat.setIMG_BIG_NAME(imgname);
				 
				} catch (Exception e) {
					// TODO: handle exception
				}

				String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "gbk", null, null);

				Document docs = Jsoup.parse(strHtmls);
				// String names=docs.getElementById("page").select("h1").text();
				// System.out.println(names);
				// wechat.setNames(names);

				// DATES=docs.select("span.time").text();
				// if (DATES.equals("")) {
				// DATES=docs.select("div.time").text();
				// }
				// wechat.setDates(DATES);

//				String content_alls = "";
//
//				Elements conts = docs.select("div");
//				for (Element element : conts) {
//					if (element.attr("itemprop").equals("articleBody")) {
//						content_alls = element.toString();
//					}
//
//				}
				
				String content_alls = docs.getElementById("Cnt-Main-Article-QQ").toString();

				String CONTENT_ALL = content_alls.toString();

				wechat.setContentAll(CONTENT_ALL);
				// String CONTENT_P=docs.select("article-detail");

				while (CONTENT_ALL.contains("<script")) {

					String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
					CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
				}

				Element docs_contentps = Jsoup.parse(content_alls);

				Elements js_contentps = docs_contentps.select("p");
				String js_contentStringp = "";
				int ii = 0;
				int iii = 0;
				String leibiaoimg = "";
				for (Element element : js_contentps) {
					// if (ii>2) {
					String Stringelement = "";

					if (element.toString().contains("<img")) {
						// Stringelement=element.toString();
						try {
							String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">", false, 0);
							String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "src=\"", "\"");
							String newimgurl = Image2.imagUrldownload_allurl_baidu(imgurlhtml);
							if (iii == 0) {
								if ( !wechat.getIMG_BIG_NAME().equals("")) {
									
									leibiaoimg = newimgurl;
								}
							}
							iii += 1;
							Stringelement = element.toString().replace(imgotherhtml,
									"<img src=\"" + newimgurl + "\" >");

						} catch (Exception e) {
							// TODO: handle exception
							Stringelement = element.toString();
						}

					} else {
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
									.replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother = docStringelement.text();
							Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
									.replace("#1#/strong#2#", "</strong>");

						} else {
							Stringelement = element.text();
						}

					}
					js_contentStringp = js_contentStringp + Stringelement + "||";
					// }
					ii += 1;

				}
				// wechat1.setContentP(js_contentStringp);

				if (!leibiaoimg.equals("") || leibiaoimg == null) {
					// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/contents/"
							+ TimeTest.getNowTime("yyyyMMdd") + "/");
					String[] namelist = leibiaoimg.split("/");
					// try {
					String nameurl = namelist[namelist.length - 1];
					wechat.setIMG_BIG_NAME(nameurl);

				}

				System.out.println(js_contentStringp);

				// wechat.setContentP(js_contentStringp);
				wechat.setContentP(content_alls);

				wechat.setSOURCE(6);

				
				
				wechat.setDATA_TYPE(dATA_TYPE);
				
				wechat.setDataId(id);

				Oracle.InsertWECHAT_INFORMATION_id(wechat);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	private static void runnew_sina(String urlMain,int dATA_TYPE,String id) {
		// TODO Auto-generated method stub
		// ="http://ent.163.com/special/yyx/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);

		Document doc = Jsoup.parse(strHtml);
		//
		Elements links = doc.select("div.results div.vrwrap ");
		for (Element link : links) {
			try {
				// System.out.println(link);
				WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();

				// wechat.setWeixinhao(weixinhao);
				// wechat.setWeixinAtion(renzheng);
				// wechat.setRanking(paiming);
				// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				// String names=link.select("a").first().text();
				String url = link.select("a").first().attr("href");
				System.out.println(url);
				// url="http://ent.qq.com"+url;
				wechat.setUrls(url);
				// wechat.setUrls(urls);
				// wechat.setUrls(url);

				String names = link.select("a").first().text();
				System.out.println(names);
				wechat.setNames(names);

				String DATES = link.select("p.news-from").toString();// .text();
				DATES = HtmlAnalyze.getTagText(DATES, "&nbsp;", "\n");
				if (DATES.equals("") || DATES == null) {
					return;
				}
				wechat.setDates(DATES);

				String PostUser = link.select("p.news-from").toString();// .text();
				PostUser = HtmlAnalyze.getTagText(PostUser, "\">", "&nbsp;");
				wechat.setPostUser(PostUser);
				
				try {
					
				
					 String imgurl=link.select("img").first().attr("src");
					 if (imgurl.equals("")||imgurl==null) {
						 imgurl= HtmlAnalyze.getTagText(link.toString(), "encodeURIComponent(\"", "\"");
					}
					 System.out.println(imgurl);
					
					 wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					
					 String imgname="";
					 if (!imgurl.equals("")&&imgurl!=null) {
					 String imgurls=imgurl.replace("\\/", "/");
					 imgname=Image2.imagUrldownload_1(imgurls);
					 }
					
					 wechat.setIMG_BIG_NAME(imgname);
				 
				} catch (Exception e) {
					// TODO: handle exception
				}

				String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "utf-8", null, null);

				Document docs = Jsoup.parse(strHtmls);
				// String names=docs.getElementById("page").select("h1").text();
				// System.out.println(names);
				// wechat.setNames(names);

				// DATES=docs.select("span.time").text();
				// if (DATES.equals("")) {
				// DATES=docs.select("div.time").text();
				// }
				// wechat.setDates(DATES);

//				String content_alls = "";
//
//				Elements conts = docs.select("div");
//				for (Element element : conts) {
//					if (element.attr("itemprop").equals("articleBody")) {
//						content_alls = element.toString();
//					}
//
//				}
				
				String content_alls = docs.getElementById("artibody").toString();

				String CONTENT_ALL = content_alls.toString();

				wechat.setContentAll(CONTENT_ALL);
				// String CONTENT_P=docs.select("article-detail");

				while (CONTENT_ALL.contains("<script")) {

					String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
					CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
				}

				Element docs_contentps = Jsoup.parse(content_alls);

				Elements js_contentps = docs_contentps.select("p");
				String js_contentStringp = "";
				int ii = 0;
				int iii = 0;
				String leibiaoimg = "";
				for (Element element : js_contentps) {
					// if (ii>2) {
					String Stringelement = "";

					if (element.toString().contains("<img")) {
						// Stringelement=element.toString();
						try {
							String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">", false, 0);
							String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "src=\"", "\"");
							String newimgurl = Image2.imagUrldownload_allurl_baidu(imgurlhtml);
							if (iii == 0) {
								if ( !wechat.getIMG_BIG_NAME().equals("")) {
									
									leibiaoimg = newimgurl;
								}
							}
							iii += 1;
							Stringelement = element.toString().replace(imgotherhtml,
									"<img src=\"" + newimgurl + "\" >");

						} catch (Exception e) {
							// TODO: handle exception
							Stringelement = element.toString();
						}

					} else {
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
									.replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother = docStringelement.text();
							Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
									.replace("#1#/strong#2#", "</strong>");

						} else {
							Stringelement = element.text();
						}

					}
					js_contentStringp = js_contentStringp + Stringelement + "||";
					// }
					ii += 1;

				}
				// wechat1.setContentP(js_contentStringp);

				if (!leibiaoimg.equals("") || leibiaoimg == null) {
					// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/contents/"
							+ TimeTest.getNowTime("yyyyMMdd") + "/");
					String[] namelist = leibiaoimg.split("/");
					// try {
					String nameurl = namelist[namelist.length - 1];
					wechat.setIMG_BIG_NAME(nameurl);

				}

				System.out.println(js_contentStringp);

				// wechat.setContentP(js_contentStringp);
				wechat.setContentP(content_alls);

				wechat.setSOURCE(6);

				// wechat.setPostUser("À—∫¸”È¿÷");
				
				wechat.setDATA_TYPE(dATA_TYPE);
				
				wechat.setDataId(id);

				Oracle.InsertWECHAT_INFORMATION_id(wechat);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	private static void runnew_fenghuang(String urlMain,int dATA_TYPE,String id) {
		// TODO Auto-generated method stub
		// ="http://ent.163.com/special/yyx/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);

		Document doc = Jsoup.parse(strHtml);
		//
		Elements links = doc.select("div.results div.vrwrap ");
		for (Element link : links) {
			try {
				// System.out.println(link);
				WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();

				// wechat.setWeixinhao(weixinhao);
				// wechat.setWeixinAtion(renzheng);
				// wechat.setRanking(paiming);
				// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				// String names=link.select("a").first().text();
				String url = link.select("a").first().attr("href");
				System.out.println(url);
				// url="http://ent.qq.com"+url;
				wechat.setUrls(url);
				// wechat.setUrls(urls);
				// wechat.setUrls(url);

				String names = link.select("a").first().text();
				System.out.println(names);
				wechat.setNames(names);

				String DATES = link.select("p.news-from").toString();// .text();
				DATES = HtmlAnalyze.getTagText(DATES, "&nbsp;", "\n");
				if (DATES.equals("") || DATES == null) {
					return;
				}
				wechat.setDates(DATES);

				String PostUser = link.select("p.news-from").toString();// .text();
				PostUser = HtmlAnalyze.getTagText(PostUser, "\">", "&nbsp;");
				wechat.setPostUser(PostUser);
				
				try {
					
				
					 String imgurl=link.select("img").first().attr("src");
					 if (imgurl.equals("")||imgurl==null) {
						 imgurl= HtmlAnalyze.getTagText(link.toString(), "encodeURIComponent(\"", "\"");
					}
					 System.out.println(imgurl);
					
					 wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					
					 String imgname="";
					 if (!imgurl.equals("")&&imgurl!=null) {
					 String imgurls=imgurl.replace("\\/", "/");
					 imgname=Image2.imagUrldownload_1(imgurls);
					 }
					
					 wechat.setIMG_BIG_NAME(imgname);
				 
				} catch (Exception e) {
					// TODO: handle exception
				}

				String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "utf-8", null, null);

				Document docs = Jsoup.parse(strHtmls);
				// String names=docs.getElementById("page").select("h1").text();
				// System.out.println(names);
				// wechat.setNames(names);

				// DATES=docs.select("span.time").text();
				// if (DATES.equals("")) {
				// DATES=docs.select("div.time").text();
				// }
				// wechat.setDates(DATES);

//				String content_alls = "";
//
//				Elements conts = docs.select("div");
//				for (Element element : conts) {
//					if (element.attr("itemprop").equals("articleBody")) {
//						content_alls = element.toString();
//					}
//
//				}
				
				String content_alls = docs.getElementById("main_content").toString();

				String CONTENT_ALL = content_alls.toString();

				wechat.setContentAll(CONTENT_ALL);
				// String CONTENT_P=docs.select("article-detail");

				while (CONTENT_ALL.contains("<script")) {

					String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
					CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
				}

				Element docs_contentps = Jsoup.parse(content_alls);

				Elements js_contentps = docs_contentps.select("p");
				String js_contentStringp = "";
				int ii = 0;
				int iii = 0;
				String leibiaoimg = "";
				for (Element element : js_contentps) {
					// if (ii>2) {
					String Stringelement = "";

					if (element.toString().contains("<img")) {
						// Stringelement=element.toString();
						try {
							String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">", false, 0);
							String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "src=\"", "\"");
							String newimgurl = Image2.imagUrldownload_allurl_baidu(imgurlhtml);
							if (iii == 0) {
								if ( !wechat.getIMG_BIG_NAME().equals("")) {
									
									leibiaoimg = newimgurl;
								}
							}
							iii += 1;
							Stringelement = element.toString().replace(imgotherhtml,
									"<img src=\"" + newimgurl + "\" >");

						} catch (Exception e) {
							// TODO: handle exception
							Stringelement = element.toString();
						}

					} else {
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
									.replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother = docStringelement.text();
							Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
									.replace("#1#/strong#2#", "</strong>");

						} else {
							Stringelement = element.text();
						}

					}
					js_contentStringp = js_contentStringp + Stringelement + "||";
					// }
					ii += 1;

				}
				// wechat1.setContentP(js_contentStringp);

				if (!leibiaoimg.equals("") || leibiaoimg == null) {
					// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/contents/"
							+ TimeTest.getNowTime("yyyyMMdd") + "/");
					String[] namelist = leibiaoimg.split("/");
					// try {
					String nameurl = namelist[namelist.length - 1];
					wechat.setIMG_BIG_NAME(nameurl);

				}

				System.out.println(js_contentStringp);

				// wechat.setContentP(js_contentStringp);
				wechat.setContentP(content_alls);

				wechat.setSOURCE(6);

				// wechat.setPostUser("À—∫¸”È¿÷");
				
				wechat.setDATA_TYPE(dATA_TYPE);
				
				wechat.setDataId(id);

				Oracle.InsertWECHAT_INFORMATION_id(wechat);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	private static void runnew_163(String urlMain,int dATA_TYPE,String id) {
		// TODO Auto-generated method stub
		// ="http://ent.163.com/special/yyx/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);

		Document doc = Jsoup.parse(strHtml);
		//
		Elements links = doc.select("div.results div.vrwrap ");
		for (Element link : links) {
			try {
				// System.out.println(link);
				WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();

				// wechat.setWeixinhao(weixinhao);
				// wechat.setWeixinAtion(renzheng);
				// wechat.setRanking(paiming);
				// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				// String names=link.select("a").first().text();
				String url = link.select("a").first().attr("href");
				System.out.println(url);
				// url="http://ent.qq.com"+url;
				wechat.setUrls(url);
				// wechat.setUrls(urls);
				// wechat.setUrls(url);

				String names = link.select("a").first().text();
				System.out.println(names);
				wechat.setNames(names);

				String DATES = link.select("p.news-from").toString();// .text();
				DATES = HtmlAnalyze.getTagText(DATES, "&nbsp;", "\n");
				if (DATES.equals("") || DATES == null) {
					return;
				}
				wechat.setDates(DATES);

				String PostUser = link.select("p.news-from").toString();// .text();
				PostUser = HtmlAnalyze.getTagText(PostUser, "\">", "&nbsp;");
				wechat.setPostUser(PostUser);
				
				try {
					
				
					 String imgurl=link.select("img").first().attr("src");
					 if (imgurl.equals("")||imgurl==null) {
						 imgurl= HtmlAnalyze.getTagText(link.toString(), "encodeURIComponent(\"", "\"");
					}
					 System.out.println(imgurl);
					
					 wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					
					 String imgname="";
					 if (!imgurl.equals("")&&imgurl!=null) {
					 String imgurls=imgurl.replace("\\/", "/");
					 imgname=Image2.imagUrldownload_1(imgurls);
					 }
					
					 wechat.setIMG_BIG_NAME(imgname);
				 
				} catch (Exception e) {
					// TODO: handle exception
				}

				String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "GBK", null, null);

				Document docs = Jsoup.parse(strHtmls);
				// String names=docs.getElementById("page").select("h1").text();
				// System.out.println(names);
				// wechat.setNames(names);

				// DATES=docs.select("span.time").text();
				// if (DATES.equals("")) {
				// DATES=docs.select("div.time").text();
				// }
				// wechat.setDates(DATES);

//				String content_alls = "";
//
//				Elements conts = docs.select("div");
//				for (Element element : conts) {
//					if (element.attr("itemprop").equals("articleBody")) {
//						content_alls = element.toString();
//					}
//
//				}
				
				String content_alls = docs.getElementById("endText").toString();

				String CONTENT_ALL = content_alls.toString();

				wechat.setContentAll(CONTENT_ALL);
				// String CONTENT_P=docs.select("article-detail");

				while (CONTENT_ALL.contains("<script")) {

					String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
					CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
				}

				Element docs_contentps = Jsoup.parse(content_alls);

				Elements js_contentps = docs_contentps.select("p");
				String js_contentStringp = "";
				int ii = 0;
				int iii = 0;
				String leibiaoimg = "";
				for (Element element : js_contentps) {
					// if (ii>2) {
					String Stringelement = "";

					if (element.toString().contains("<img")) {
						// Stringelement=element.toString();
						try {
							String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">", false, 0);
							String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "src=\"", "\"");
							String newimgurl = Image2.imagUrldownload_allurl_baidu(imgurlhtml);
							if (iii == 0) {
								if ( !wechat.getIMG_BIG_NAME().equals("")) {
									
									leibiaoimg = newimgurl;
								}
							}
							iii += 1;
							Stringelement = element.toString().replace(imgotherhtml,
									"<img src=\"" + newimgurl + "\" >");

						} catch (Exception e) {
							// TODO: handle exception
							Stringelement = element.toString();
						}

					} else {
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
									.replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother = docStringelement.text();
							Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
									.replace("#1#/strong#2#", "</strong>");

						} else {
							Stringelement = element.text();
						}

					}
					js_contentStringp = js_contentStringp + Stringelement + "||";
					// }
					ii += 1;

				}
				// wechat1.setContentP(js_contentStringp);

				if (!leibiaoimg.equals("") || leibiaoimg == null) {
					// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/contents/"
							+ TimeTest.getNowTime("yyyyMMdd") + "/");
					String[] namelist = leibiaoimg.split("/");
					// try {
					String nameurl = namelist[namelist.length - 1];
					wechat.setIMG_BIG_NAME(nameurl);

				}

				System.out.println(js_contentStringp);

				// wechat.setContentP(js_contentStringp);
				wechat.setContentP(content_alls);

				wechat.setSOURCE(6);

				// wechat.setPostUser("À—∫¸”È¿÷");
				
				wechat.setDATA_TYPE(dATA_TYPE);
				
				wechat.setDataId(id);

				Oracle.InsertWECHAT_INFORMATION_id(wechat);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void runnewMain(String name,int dATA_TYPE,String id) {
		// TODO Auto-generated method stub
		//

		String nameutf8 = "";

		try {
			nameutf8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(nameutf8);
		String urlMain= "http://www.sogou.com/web?ie=utf8&query="+nameutf8;
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);
		
		String query= HtmlAnalyze.getTagText(strHtml, "'query':'", "'");
		
		System.out.println(query);
		
//		int dATA_TYPE=1;
		try {
			
			runnew_shouhu(
					"http://news.sogou.com/news?query=site%3Asohu.com+"+query+"&_ast=1474180736&_asf=news.sogou.com&time=0&w=03009900&sort=1&mode=2&manual=true",dATA_TYPE,id);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			//http://news.sogou.com/news?query=site:qq.com%20%D0%A1%B1%F0%C0%EB&manual=true&mode=1&sort=1&p=42230302
			runnew_qq(
					"http://news.sogou.com/news?query=site:qq.com%20"+query+"&manual=true&mode=1&sort=1&p=42230302",dATA_TYPE,id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			
			//http://news.sogou.com/news?query=site:sina.com.cn%20%D0%A1%B1%F0%C0%EB&manual=true&mode=1&sort=1&p=42230302
			runnew_sina(
					"http://news.sogou.com/news?query=site:sina.com.cn%20"+query+"&manual=true&mode=1&sort=1&p=42230302",dATA_TYPE,id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			//http://news.sogou.com/news?query=site:ifeng.com%20%D0%A1%B1%F0%C0%EB&manual=true&mode=1&sort=1&p=42230302
			runnew_fenghuang(
					"http://news.sogou.com/news?query=site:ifeng.com%20"+query+"&manual=true&mode=1&sort=1&p=42230302",dATA_TYPE,id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			//http://news.sogou.com/news?query=site:163.com%20%D0%A1%B1%F0%C0%EB&manual=true&mode=1&sort=1&p=42230302
			//endText
			runnew_163(
					"http://news.sogou.com/news?query=site:163.com%20"+query+"&manual=true&mode=1&sort=1&p=42230302",dATA_TYPE,id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  id="0";
		runnewMain("–°±¿Î",2,id);

	}

}
