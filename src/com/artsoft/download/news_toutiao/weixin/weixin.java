package com.artsoft.download.news_toutiao.weixin;

import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.Oracle;
import com.artsoft.oracle.OracleBaidu;
import com.artsoft.oracle2.DBManager;
import com.artsoft.oracle2.DateUtil;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class weixin {
	private static Proxy proxy = null;
	static String strproxy = "";
	
	
	// �ж����ݿ�ʼʱ��
		public static void TimingTime(int hh, int mm, int ss) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
			calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
			calendar.set(Calendar.SECOND, ss); // ������

			Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					System.out.println("-------�趨Ҫָ������--------");
//					runstatic();
					try {
						
						runnewMain();
						
						DBManager dbm = DBManager.instance();
						dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}, time, 1000 * 60 * 60 * 8);// �����趨����ʱÿ��̶�ִ��
		}
	
	/**
	 * ΢��
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(1, 59, 59);
//		runnewMain();
		
//		mainUrl("http://mp.weixin.qq.com/profile?src=3&timestamp=1470797300&ver=1&signature=xNK5roZsfw0mDZ*FrmNUG*T-M8y5I2lDXhhW1e0OxFpUUxjiUaL8YnMAvjMRkHyoFx1p6utvLKX6qJgFgchaJw==", "1", "����", "����");
		
	}
	
	
	
	public static String urlreturnHtml(String urlBranch) {
		String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
		// null, proxy);
		boolean bb = true;
		int i = 0;
		while (bb) {
			proxy = DealProxy.getInstance().getPoxxy();
//			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 10, "UTF-8", null, proxy);
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 10, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
				System.out.println("�з���ֵ");
				if (strHtml.contains("���ķ��ʳ�����")||strHtml.contains("δ֪����")) {
					System.out.println("ip :" + proxy);
					System.out.println("���ķ��ʳ����ˣ�");
					String ip=HtmlAnalyze.getTagText(proxy.toString()+"##", "/", "##");
					deleteipfiler(ip);
					bb = true;
//					System.out.println(Thread.currentThread().getName());
				}else{
					System.out.println("ip :" + proxy);
					System.out.println("���ʳɹ���");
				}
			} else {
				System.out.println("�򿪳���" + i + "��,���ӣ�" + urlBranch);
				System.out.println("ip ��������" + proxy);
				String ip=HtmlAnalyze.getTagText(proxy.toString()+"##", "/", "##");
				deleteipfiler(ip);

			}
			if (i > 15) {
				bb = false;
			}

			i += 1;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strHtml;

	}
	
	private static void deleteipfiler(String ip) {
		
		if (strproxy.contains(ip)) {
			strproxy=strproxy.replace(ip+"\r\n", "");
			ReadTxtFile.wirterfile("proxy.txt", strproxy);
		}
		
		if (strproxy.split("\r\n").length<50) {
			ipfilerall("");
		}
	}
	
	
	
	private static void ipfilerall(String urlMain) {
		// TODO Auto-generated method stub
//		String strHtml = DownloadUtil.getHtmlText("http://dev.kuaidaili.com/api/getproxy?orderid=915195947631121&num=999&area=&area_ex=&port=&port_ex=&ipstart=&carrier=0&an_ha=1&an_an=1&protocol=1&method=2&quality=0&sort=0&b_pcchrome=1&b_pcie=1&b_pcff=1&showtype=1",
//				1000 * 30, "UTF-8", null, null);
		
//		String strproxy = "";
//		strproxy = "";
		strproxy = ReadTxtFile.getKeyWordFromFile("proxy.txt");
		String strHtml = DownloadUtil.getHtmlText("http://qsrdk.daili666api.com/ip/?tid=559375659838998&num=200&delay=1&category=2&sortby=speed",
				1000 * 30, "UTF-8", null, null);
		// System.out.println(strHtml);
		String[] iplist = strHtml.split("\r\n");
		// System.out.println(iplist.length);
		for (String stringip : iplist) {
//			String strIp = stringip.split(":")[0];
//			String strPort = stringip.split(":")[1];
			strproxy = strproxy + stringip + "\r\n";
			
//			Proxy proxy = proxy(strIp, strPort);
//			System.out.println(proxy);
//			// DownloadUtil.getHtmlText("http://vxer.daili666api.com/ip/?tid=559245058880483&num=1000",
//			// 1000 * 30, "UTF-8", null, proxy );
//			// System.out.println(urlreturnHtml(urlMain, proxy));
//			while (pool.getPoolNum() > 10) {
//				try {
//					System.out.println("�߳���������10���ȴ�5s");
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			System.out.println("��ǰ�����߳�thread:" + pool.getPoolNum());
//			pool.performTask(new IpFilter(urlMain, proxy, stringip));
			// if (returnboolean(urlMain, proxy)) {
			// System.out.println("����ʹ�õ�text"+strIp+":"+strPort);
			// }
		}
		
		
		ReadTxtFile.wirterfile("proxy.txt", strproxy);

	}
	
	

	private static void runnewMain() {
		// TODO Auto-generated method stub
		
		ipfilerall("");
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		
//		String[] yingshi = { "��һ��Ƭ��", "��Ӱͷ��", "��Ӱ��", "������", "���ʵս", "����״�", "������", "Ӱ��Ӫ������", "Ӱ������Ȧ��", "Ӱ�Ӷ���", "һ���ĵ�Ӱ", "����", "�����ư�", "���Ӿ�ӥ��", "�籦", "�Ļ�԰·����", "ӥ�ۻ�", "�����Ӱ�ֲ�", "�����Ե�Ӱ", "�²۵�ӰԺ", "�����Ӱ", "�������Ӱ", "��ý�ڲ�", "��Ӱ����", "��һ��ӰƱ��", "�����Ӱ", "�Ļ���ҵ����", "�й���ӰƱ����", "ͬ�������", "�����й�", "ȫ���Ӱ�۲�", "�й���Ӱ������", "ӰͶ��", "��һ���", "��ӳ�ֳ�", "���ͷ��", "��Ƭ��", "����", "�綺", "����", "����ѧԺ", "�й�Ӱ����Ƭ��", "ϲ����籾�۲�", "��Ӱ��������", "���Ӿ��������", "������Ѷ", "��͸������", "������", "�й���Ӱ������", "ȫ���Ӱ�۲�" };
		
		List<String> listArray = Oracle.selectWEIXIN_NUMBER();
		
		for (Object yingshis : listArray) {
			
			List<String> listTemp = (List<String>) yingshis;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			String yingshis_utf_8="";
			try {
				yingshis_utf_8=java.net.URLEncoder.encode(listTemp.get(0), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				mainUrlall(yingshis_utf_8,listTemp.get(1));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
				
	}
	
	private static void mainUrlall(String keyword_utf_8,String weixinhaoNo){
		String strHtml = "";
//		String urlmain="http://weixin.sogou.com/weixin?type=1&query=%E7%AC%AC%E4%B8%80%E5%88%B6%E7%89%87%E4%BA%BA&ie=utf8&_sug_=n&_sug_type_=";
		String urlmain="http://weixin.sogou.com/weixin?type=1&query="+keyword_utf_8+"&ie=utf8&_sug_=n&_sug_type_=";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(urlmain, 1000 * 30, "UTF-8", null, null);
			
			
//		strHtml =urlreturnHtml(urlmain);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
//		try {
			
		
		Document doc = Jsoup.parse(strHtml);
		
		Elements links = doc.getElementById("main").select("div.weixin-public");
		
//		System.out.println(links);
		
		String Stringlinks=links.toString();
		String[] Stringlist=Stringlinks.split("<!-- z -->");
		for (int i = 0; i < Stringlist.length; i++) {
//			System.out.println(Stringlist[i]);
			String link=HtmlAnalyze.getTagText(Stringlist[i].toString()+"##", "</script>", "##",true,0);
//			System.out.println(link);
			
			Document linkdoc = Jsoup.parse(link);
			//΢�źţ�
			String weixinhao=HtmlAnalyze.getTagText(link, "\"em_weixinhao\">", "</label>");
			//���ܽ��ܣ�
			String gongnengjieshao=HtmlAnalyze.getTagText(link, "���ܽ��ܣ�", "</span> </p>");
			//��֤��
			String renzheng=HtmlAnalyze.getTagText(link, "��֤��", "</span> </p>");
			//�������
			String zhuijinwenzhangtext=HtmlAnalyze.getTagText(link, "������£�", "</span> </p>");
			//img
//			String img=linkdoc.select("img").first().attr("src");
			String img= HtmlAnalyze.getTagText(link, "background-image:url(", ")");
			
//			String nameurl=Image2.imagUrldownload(img);
			
			
			String url=HtmlAnalyze.getTagText(link, "href=\"", "\"");
			url=url.replace("&amp;", "&");
			System.out.println(url);
					//
			
			try {
				//ֻҪǰ5������
//				if (i<=4) {
				if (weixinhaoNo.equals(weixinhao)) {
					mainUrl(url,(i+1)+"",renzheng,weixinhao);
				}
//				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	
	
	/**
	 * 
	 * @param url ��������
	 * @param paiming Ƭ��
	 * @param renzheng ��֤����
	 * @param weixinhao ΢�ź�
	 * @param imgnameurl  ͼƬ����
	 */
	private static void mainUrl(String url,String paiming,String renzheng,String weixinhao){
		String strHtml = "";
//		String urlmain="http://weixin.sogou.com/weixin?type=1&query=%E7%AC%AC%E4%B8%80%E5%88%B6%E7%89%87%E4%BA%BA&ie=utf8&_sug_=n&_sug_type_=";
//		boolean bb = true;
//		while (bb) {
//			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		strHtml =urlreturnHtml(url);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
//		try {
			
		
		Document doc = Jsoup.parse(strHtml);
		
//		System.out.println(doc);
//		
		String htmls="";
		htmls=StringEscapeUtils.unescapeHtml(strHtml);
//		
//		System.out.println("11112");
//		System.out.println(StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(strHtml)));
		
//		String htmls=StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(strHtml));
//		System.out.println(htmls);
		String msgList=HtmlAnalyze.getTagText(htmls, "msgList = '", "';");
		
//		System.out.println(msgList);
//		
//		System.out.println("1111");
		
		JSONObject objectobjects = JSONObject.fromObject(msgList);
		
		JSONArray arrayjsonname = new JSONArray();
		arrayjsonname = (JSONArray) objectobjects.get("list");
		int shuliang=0;
		for (Object object : arrayjsonname) {
//			if (shuliang==0) {
				WECHAT_INFORMATION wechat=new WECHAT_INFORMATION();
				
				wechat.setWeixinhao(weixinhao);
				wechat.setWeixinAtion(renzheng);
				wechat.setRanking(paiming);
				wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				
				JSONObject objectobjects2 = JSONObject.fromObject(object);
//				String app_msg_ext_info="";
//				System.out.println(app_msg_ext_info=(String) objectobjects2.get("app_msg_ext_info"));
				JSONObject app_msg_ext_infoobject=(JSONObject)objectobjects2.get("app_msg_ext_info");
				String title=(String) app_msg_ext_infoobject.get("title");
				
				String digest=(String) app_msg_ext_infoobject.get("digest");
				
				String imgnameurl=(String) app_msg_ext_infoobject.get("cover");
				String imgname="";
				if (!imgnameurl.equals("")&&imgnameurl!=null) {
					String imgurls=imgnameurl.replace("\\/", "/");
					imgname=Image2.imagUrldownload(imgurls);
				}
				
				
				wechat.setIMG_BIG_NAME(imgname);
//				wechat
				String content_url=(String) app_msg_ext_infoobject.get("content_url");
				content_url="http://mp.weixin.qq.com"+StringEscapeUtils.unescapeHtml(content_url).replace("\\/", "/");
//				System.out.println(1112);
				wechat.setUrls(content_url);
				
				String cover=(String) app_msg_ext_infoobject.get("cover");
				
//				String htmlss= DownloadUtil.getHtmlText(content_url, 1000 * 30, "UTF-8", null, null);
				
				String htmlss=urlreturnHtml(content_url);
				
				Document htmlssdoc = Jsoup.parse(htmlss);
				
				String activity_name=htmlssdoc.getElementById("activity-name").text();
				System.out.println(activity_name);
				wechat.setNames(activity_name);
				//�Ƿ���ԭ��
				String yuanchang="";
				try {
					htmlssdoc.getElementById("copyright_logo").text();
					yuanchang="0";
				} catch (Exception e) {
					// TODO: handle exception
					yuanchang="1";
				}
				
				wechat.setOriginal(yuanchang);
				String post_date=htmlssdoc.getElementById("post-date").text();
//				System.out.println(post_date);
				wechat.setDates(post_date);
				
				String post_user=htmlssdoc.getElementById("post-user").text();
//				System.out.println(post_user);
				wechat.setPostUser(post_user);
				
				Element js_content=htmlssdoc.getElementById("js_content");
				String js_contentString =js_content.toString();
//				System.out.println(js_contentString);
				wechat.setContentAll(js_contentString);
				
				
				Elements js_contentps=js_content.select("p");
				String js_contentStringp="";
				int ii=0;
				for (Element element : js_contentps) {
					if (ii>2) {
						String Stringelement="";
						
						if (element.toString().contains("<img")) {
							Stringelement=element.toString();
							
						}else{
							if (element.toString().contains("<strong>")) {
								String Stringelement_1_2=element.toString().replace("<strong>", "#1#strong#2#").replace("</strong>", "#1#/strong#2#");
								Document docStringelement = Jsoup.parse(Stringelement_1_2);
								String Stringelementother=docStringelement.text();
								Stringelement=Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#", "</strong>");
								
							}else{
								Stringelement=element.text();
							}
							 	
						}
						js_contentStringp=js_contentStringp+Stringelement+"||";
					}
					ii+=1;
					
					
				}
//				System.out.println(js_contentStringp);
				wechat.setContentP(js_contentStringp);
				
				wechat.setSOURCE(1);
				
				Oracle.InsertWECHAT_INFORMATION(wechat);
				
//				System.out.println(22222);
				
				JSONArray multi_app_msg_item_list=(JSONArray)app_msg_ext_infoobject.get("multi_app_msg_item_list");
				
				for (Object object2 : multi_app_msg_item_list) {
					
					if (shuliang<=5) {
						
					
					WECHAT_INFORMATION wechat1=new WECHAT_INFORMATION();
					
					wechat1.setWeixinhao(weixinhao);
					wechat1.setWeixinAtion(renzheng);
					wechat1.setRanking(paiming);
					
					 objectobjects2 = JSONObject.fromObject(object2);
//					String app_msg_ext_info="";
//					System.out.println(app_msg_ext_info=(String) objectobjects2.get("app_msg_ext_info"));
//					 app_msg_ext_infoobject=(JSONObject)objectobjects2.get("app_msg_ext_info");
					 app_msg_ext_infoobject=objectobjects2;
					 title=(String) app_msg_ext_infoobject.get("title");
					
					 digest=(String) app_msg_ext_infoobject.get("digest");
//					wechat
					 content_url=(String) app_msg_ext_infoobject.get("content_url");
					content_url="http://mp.weixin.qq.com"+StringEscapeUtils.unescapeHtml(content_url).replace("\\/", "/");
//					System.out.println(1112);
					wechat1.setUrls(content_url);
					
					 cover=(String) app_msg_ext_infoobject.get("cover");
					
//					 htmlss= DownloadUtil.getHtmlText(content_url, 1000 * 30, "UTF-8", null, null);
					 
					 htmlss=urlreturnHtml(content_url);
					
					 htmlssdoc = Jsoup.parse(htmlss);
					
					 activity_name=htmlssdoc.getElementById("activity-name").text();
					System.out.println(activity_name);
					wechat1.setNames(activity_name);
					//�Ƿ���ԭ��
					 yuanchang="";
					try {
						htmlssdoc.getElementById("copyright_logo").text();
						yuanchang="0";
					} catch (Exception e) {
						// TODO: handle exception
						yuanchang="1";
					}
					
					wechat1.setOriginal(yuanchang);
					 post_date=htmlssdoc.getElementById("post-date").text();
//					System.out.println(post_date);
					wechat1.setDates(post_date);
					
					 post_user=htmlssdoc.getElementById("post-user").text();
//					System.out.println(post_user);
					wechat1.setPostUser(post_user);
					
					 js_content=htmlssdoc.getElementById("js_content");
					 js_contentString =js_content.toString();
//					System.out.println(js_contentString);
					wechat1.setContentAll(js_contentString);
					
					
					 js_contentps=js_content.select("p strong");
					 js_contentps=js_content.select("p");
					 js_contentStringp="";
					 ii=0;
					 for (Element element : js_contentps) {
						if (ii>2) {
							String Stringelement="";
							
							if (element.toString().contains("<img")) {
								Stringelement=element.toString();
								
							}else{
								if (element.toString().contains("<strong>")) {
									String Stringelement_1_2=element.toString().replace("<strong>", "#1#strong#2#").replace("</strong>", "#1#/strong#2#");
									Document docStringelement = Jsoup.parse(Stringelement_1_2);
									String Stringelementother=docStringelement.text();
									Stringelement=Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#", "</strong>");
									
								}else{
									Stringelement=element.text();
								}
								 	
							}
							js_contentStringp=js_contentStringp+Stringelement+"||";
						}
						ii+=1;
						
						
					}
					wechat1.setContentP(js_contentStringp);
					
					wechat1.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
					
					 imgnameurl=(String) app_msg_ext_infoobject.get("cover");
					imgname="";
					if (!imgnameurl.equals("")&&imgnameurl!=null) {
						String imgurls=imgnameurl.replace("\\/", "/");
						imgname=Image2.imagUrldownload(imgurls);
					}
					
					
					wechat1.setIMG_BIG_NAME(imgname);
					
					wechat1.setSOURCE(1);
					
					Oracle.InsertWECHAT_INFORMATION(wechat1);
					}
					else{
						
					}
					shuliang+=1;
					
				}
				
			
//			}else{
//				shuliang+=1;
//			}
			}
		
		
	}

}