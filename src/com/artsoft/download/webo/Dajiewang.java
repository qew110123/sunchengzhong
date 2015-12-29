package com.artsoft.download.webo;

import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Dajiewang {

	public static void daJeWang() throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************ҳ��ת��******************************");
		String newUrl = "http://www.dajie.com/home";
		HttpGet get = new HttpGet(newUrl);
		get.addHeader(new BasicHeader("Cookie",
				"DJ_UVID=MTQ0Njc3NTY0MTU1Mzc3MDc2; DJ_RF=empty; DJ_EU=http%3A%2F%2Fwww.dajie.com%2Fhome; login_email=764295333%40qq.com; dj_auth_v3=MW_qOtlnwl_JWoggzLsiIygjegD07-zT0hRU1DpC7Nwrsyf3qxtw-s9uPFHeds4*; uchome_loginuser=23860580; dj_cap=623eefeadd1d35d8d524c3a4c11e428f; USER_ACTION=request^AProfessional^ANORMAL^A-^A-; login_email=764295333%40qq.comHost:www.dajie.com"));
		get.addHeader("Content-Type", "text/html;charset=UTF-8");
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		get.addHeader("Host", "www.dajie.com");
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// ��¼����ҳ������
		System.out.println(responseString);
		get.releaseConnection();

	}

	public static String Weibo(String newUrl) throws Exception {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		System.out.println("******************************ҳ��ת��******************************");
		// String newUrl =
		// "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1091324464527";
		HttpGet get = new HttpGet(newUrl);
		// get.addHeader("Content-Type", "text/html;charset=UTF-8");
		// get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64;
		// rv:26.0) Gecko/20100101 Firefox/26.0");
		// get.addHeader("Host", "data.weibo.com");
		// get.addHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.addHeader("Cache-Control", "max-age=0");
		get.addHeader("Connection", "keep-alive");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		get.addHeader(new BasicHeader("Cookie",
				"SINAGLOBAL=2972848266363.144.1448332311328; DATA=usrmdinst_14; PHPSESSID=jkeoolcth20l8c8bq3avh0dfc0; WBStore=5955be0e3d5411da|undefined; _s_tentry=-; Apache=817587687633.9316.1450254524241; ULV=1450254524257:2:1:1:817587687633.9316.1450254524241:1448332311344"));

		get.addHeader("Host", "data.weibo.com");
		get.addHeader("Referer", "http://data.weibo.com/index/hotword");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
		get.addHeader("X-Requested-With", "XMLHttpRequest");
		// get.addHeader("Accept-Language",
		// "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");

		HttpResponse httpResponse = client.execute(get);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		// ��¼����ҳ������
		System.out.println(responseString);
		get.releaseConnection();
		return responseString;
	}

	public static void people(String names) throws Exception {
		// String strHtml =
		// DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword?wname=������",
		// 1000 * 30, "UTF-8",
		// null, null);��
		// http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108

		// DownloadUtil.getHtmlText("http://data.weibo.com/index/ajax/contrast?key2=%25E8%258C%2583%25E5%2586%25B0%25E5%2586%25B0&key3=&key4=&key5=&key6=&_t=0&__rnd=1450261523108",
		// 1000 * 30, "UTF-8",
		// null, null);
		String strHtml = DownloadUtil.getHtmlText("http://data.weibo.com/index/hotword", 1000 * 30, "UTF-8", null,
				null);
		String timeDiff = HtmlAnalyze.getTagText(strHtml, "server_time': '", "'");
		System.out.println(new Date());
		// System.out.println(Weibo("http://data.weibo.com/index/ajax/contrast?key2=%25E6%259D%258E%25E6%2599%25A8&key3=&key4=&key5=&key6=&_t=0&__rnd=1450260534657"));
		System.out.println(timeDiff);

		Date date = new Date(System.currentTimeMillis());
		int s = 0;
		System.out.println(s = (int) (date.getTime() - Integer.parseInt(timeDiff)));

		// System.out.println(Integer.parseInt(timeDiff));
		// System.out.println(new Date()- new Date(Integer.parseInt(timeDiff));
		String hostnum = Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + s);
		// Weibo("http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=1449471097626");
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss").format(new Date(1446912627104l)));
		// Weibo("http://data.weibo.com/index/ajax/isexsitofinput?__rnd="+s );
		System.out.println(hostnum = DownloadUtil.decodeUnicode(hostnum));
		JSONObject objectobject = JSONObject.fromObject(hostnum);
		// objectobject=JSONObject.fromObject(object);
		// System.out.println( objectobject.get("keyword"));
		String keywordnames = HtmlAnalyze.getTagText(hostnum, "keyword\":[\"", "\"]}");

		if (!names.equals(keywordnames)) {
			return;
		}
		System.out.println(objectobject.get("data"));
		JSONArray authors = new JSONArray();
		authors = (JSONArray) objectobject.get("data");
		for (Object object : authors) {
			JSONObject objectobjects = JSONObject.fromObject(object);
			// objectobject=JSONObject.fromObject(object);
			// String ANIMATION_NAME="";
			// System.out.println(ANIMATION_NAME=(String)
			// objectobjects.get("name"));
			JSONArray arrayjsonname = new JSONArray();
			arrayjsonname = (JSONArray) objectobjects.get("zt");
			for (Object object2 : arrayjsonname) {
				System.out.println(object2);
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(objectobjects2.get("day_key"));
				System.out.println(objectobjects2.get("wid"));
				System.out.println(objectobjects2.get("value"));

			}

			JSONArray arrayjsonnameyd = new JSONArray();
			arrayjsonnameyd = (JSONArray) objectobjects.get("yd");
			for (Object object2 : arrayjsonnameyd) {
				System.out.println(object2);
				JSONObject objectobjects2 = JSONObject.fromObject(object2);
				System.out.println(objectobjects2.get("daykey"));
				System.out.println(objectobjects2.get("pc"));
				System.out.println(objectobjects2.get("mobile"));
			}

		}
		String wid = HtmlAnalyze.getTagText(hostnum, "\"wid\":\"", "\"");
		System.out.println("����λ��");
		String keywordzone = Weibo(
				"http://data.weibo.com/index/ajax/keywordzone?type=notdefault&wid=" + wid + "&__rnd=" + s);
		
		keywordzone = DownloadUtil.decodeUnicode(keywordzone);
		
//		JSONArray authors = new JSONArray();
//		authors = (JSONArray) objectobject.get("data");
		JSONObject objectkeywordzones = JSONObject.fromObject(keywordzone);
		
//		System.out.println(objectkeywordzones.get("zone"));
		JSONObject objectkeywordzone=JSONObject.fromObject(objectkeywordzones.get("zone"));
//		System.out.println(objectkeywordzone.get("jiangsu"));
		keywordzone(objectkeywordzone);
		
		JSONObject objectkeyworduser=JSONObject.fromObject(objectkeywordzones.get("user"));
		keywordzoneuser(objectkeyworduser);
		String getdefaultattributealldata=Weibo("http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=" + s);
		getdefaultattributealldata = DownloadUtil.decodeUnicode(getdefaultattributealldata);
		System.out.println(getdefaultattributealldata);
		
		JSONObject objectkeyworduserda=JSONObject.fromObject(getdefaultattributealldata);
		JSONObject objectkeyworduserdata=JSONObject.fromObject(objectkeyworduserda.get("data"));
		String sexMan="";
		String sexwoman="";
		JSONObject objectkeyworduserdatasex=JSONObject.fromObject(objectkeyworduserdata.get("sex"));
		JSONObject objectkeyworduserdatasexkey2=JSONObject.fromObject(objectkeyworduserdatasex.get("key2"));
		System.out.println(objectkeyworduserdatasexkey2.get("man"));
//		JSONObject objectkeyworduserdatasexkey2man=JSONObject.fromObject(objectkeyworduserdatasexkey2.get("man"));
//		JSONObject objectkeyworduserdatasexkey2woman=JSONObject.fromObject(objectkeyworduserdatasexkey2.get("woman"));
//		JSONObject objectkeyworduserdatasexkey2word=JSONObject.fromObject(objectkeyworduserdatasexkey2.get("word"));
		sexMan=(String) objectkeyworduserdatasexkey2.get("man");
		sexwoman=(String) objectkeyworduserdatasexkey2.get("woman");
		String age0_12="";
		String age12_18="";
		String age19_24="";
		String age25_34="";
		String age35_50="";
		String ageother="";
		JSONObject objectkeyworduserdataage=JSONObject.fromObject(objectkeyworduserdata.get("age"));
		JSONObject objectkeyworduserdataagekey2=JSONObject.fromObject(objectkeyworduserdataage.get("key2"));
		JSONObject objectkeyworduserdataagekey2_0=JSONObject.fromObject(objectkeyworduserdataagekey2.get("0"));
//		JSONObject objectkeyworduserdataagekey2_0_0_12=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("0-12"));
//		JSONObject objectkeyworduserdataagekey2_0_12_18=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("12-18"));
//		JSONObject objectkeyworduserdataagekey2_0_19_24=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("19-24"));
//		JSONObject objectkeyworduserdataagekey2_0_25_34=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("25-34"));
//		JSONObject objectkeyworduserdataagekey2_0_35_50=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("35-50"));
//		JSONObject objectkeyworduserdataagekey2_0_ageother=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("other"));
		 age0_12=objectkeyworduserdataagekey2_0.get("0-12").toString();
		 age12_18=objectkeyworduserdataagekey2_0.get("12-18").toString();
		 age19_24=objectkeyworduserdataagekey2_0.get("19-24").toString();
		 age25_34=objectkeyworduserdataagekey2_0.get("25-34").toString();
		 age35_50=objectkeyworduserdataagekey2_0.get("35-50").toString();
		 ageother=objectkeyworduserdataagekey2_0.get("other").toString();
//		JSONObject objectkeyworduserdataagekey2_0_0_12=JSONObject.fromObject(objectkeyworduserdataagekey2_0.get("0-12"));
		 
		String meishi="";
		String luyou="";
		String mingrenmingxing="";
		String lule="";
		String gaoxiaoyoumo="";
		JSONObject objectkeyworduserdatatag=JSONObject.fromObject(objectkeyworduserdata.get("tag"));
		JSONObject objectkeyworduserdatatagkey2=JSONObject.fromObject(objectkeyworduserdatatag.get("key2"));
		JSONObject objectkeyworduserdatatagkey2_0=JSONObject.fromObject(objectkeyworduserdatatagkey2.get("0"));
//		JSONObject objectkeyworduserdatatagkey2_0meishi=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("��ʳ"));
//		JSONObject objectkeyworduserdatatagkey2_0luyou=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("����"));
//		JSONObject objectkeyworduserdatatagkey2_0mingrenmingxing=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("��������"));
//		JSONObject objectkeyworduserdatatagkey2_0lule=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("����"));
//		JSONObject objectkeyworduserdatatagkey2_0gaoxiaoyoumo=JSONObject.fromObject(objectkeyworduserdatatagkey2_0.get("��Ц��Ĭ"));
		 meishi=(String) objectkeyworduserdatatagkey2_0.get("��ʳ");
		 luyou=(String) objectkeyworduserdatatagkey2_0.get("����");
		 mingrenmingxing=(String) objectkeyworduserdatatagkey2_0.get("��������");
		 lule=(String) objectkeyworduserdatatagkey2_0.get("����");
		 gaoxiaoyoumo=(String) objectkeyworduserdatatagkey2_0.get("��Ц��Ĭ");
		String moxie="";
		String shuiping="";
		String shuangyu="";
		String baiyang="";
		String jinniu="";
		String shuangzi="";
		String juxie="";
		String shizi="";
		String chunv="";
		String tianping="";
		String 	tianxie="";
		String sheshou="";
		JSONObject objectkeyworduserdatastar=JSONObject.fromObject(objectkeyworduserdata.get("star"));
		JSONObject objectkeyworduserdatastarkey2=JSONObject.fromObject(objectkeyworduserdatastar.get("key2"));
		JSONObject objectkeyworduserdatastarkey2_0=JSONObject.fromObject(objectkeyworduserdatastarkey2.get("0"));
//		JSONObject objectkeyworduserdatastarkey2_0moxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("Ħ����"));
//		JSONObject objectkeyworduserdatastarkey2_0shuiping=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("ˮƿ��"));
//		JSONObject objectkeyworduserdatastarkey2_0shuangyu=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("˫����"));
//		JSONObject objectkeyworduserdatastarkey2_0baiyang=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("������"));
//		JSONObject objectkeyworduserdatastarkey2_0jinniu=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("��ţ��"));
//		JSONObject objectkeyworduserdatastarkey2_0shuangzi=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("˫����"));
//		JSONObject objectkeyworduserdatastarkey2_0juxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("��з��"));
//		JSONObject objectkeyworduserdatastarkey2_0shizi=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("ʨ����"));
//		JSONObject objectkeyworduserdatastarkey2_0chunv=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("��Ů��"));
//		JSONObject objectkeyworduserdatastarkey2_0tianping=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("�����"));
//		JSONObject objectkeyworduserdatastarkey2_0tianxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("��Ы��"));
//		JSONObject objectkeyworduserdatastarkey2_0sheshou=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("������"));
//		JSONObject objectkeyworduserdatastarkey2_0moxie=JSONObject.fromObject(objectkeyworduserdatastarkey2_0.get("Ħ����"));
		
		 moxie=objectkeyworduserdatastarkey2_0.get("Ħ����").toString();
		 shuiping=objectkeyworduserdatastarkey2_0.get("ˮƿ��").toString();
		 shuangyu=objectkeyworduserdatastarkey2_0.get("˫����").toString();
		 baiyang=objectkeyworduserdatastarkey2_0.get("������").toString();
		 jinniu=objectkeyworduserdatastarkey2_0.get("��ţ��").toString();
		 shuangzi=objectkeyworduserdatastarkey2_0.get("˫����").toString();
		 juxie=objectkeyworduserdatastarkey2_0.get("��з��").toString();
		 shizi=objectkeyworduserdatastarkey2_0.get("ʨ����").toString();
		 chunv=objectkeyworduserdatastarkey2_0.get("��Ů��").toString();
		 tianping=objectkeyworduserdatastarkey2_0.get("�����").toString();
		 tianxie=objectkeyworduserdatastarkey2_0.get("��Ы��").toString();
		 sheshou=objectkeyworduserdatastarkey2_0.get("������").toString();
		
		
		
	}
	
	public static void keywordzone(JSONObject objectkeywordzone){
		String pingyin="";
		String address="";
		String value="";
		String ct="";
		String index="";
		String wname="";
		String stateInitColor="";
		
		String[][] numsix={{"chongqing","����"},{"zhejiang","�㽭"},{"yunnan","����"},{"xinjiang","�½�"},{"hongkong","���"},{"xizang","����"},{"tianjin","���"},{"taiwan","̨��"},{"sichuan","�Ĵ�"},{"shanghai","�Ϻ�"},{"shaanxi","����"},{"shanxi","ɽ��"},{"shandong","ɽ��"},{"qinghai","�ຣ"},{"ningxia","����"},{"neimongol","���ɹ�"},{"liaoning","����"},{"jiangxi","����"},{"jiangsu","����"},{"jilin","����"},{"hunan","����"},{"hubei","����"},{"heilongjiang","������"},{"henan","����"},{"hebei","�ӱ�"},{"hainan","����"},{"guizhou","����"},{"guangxi","����"},{"guangdong","�㶫"},{"gansu","����"},{"fujian","����"},{"beijing","����"},{"macau","����"},{"anhui","����"}};
//		System.out.println(objectkeywordzone.get("jiangsu"));
		for (int i = 0; i < numsix.length; i++) {
			pingyin=numsix[i][0];
			address=numsix[i][1];
			JSONObject objectkeywordzones=JSONObject.fromObject(objectkeywordzone.get(pingyin));
			//{"value":"9.16%","ct":"7055","index":"2","wname":"�˳�","stateInitColor":"61B6FD"}
			value=(String) objectkeywordzones.get("value");
			ct=(String) objectkeywordzones.get("ct");
			index=(String) objectkeywordzones.get("index");
			wname=(String) objectkeywordzones.get("wname");
			stateInitColor=(String) objectkeywordzones.get("stateInitColor");
			System.out.println(value+ct+index+wname+stateInitColor);
		}
		
	}
	
	
	public static void keywordzoneuser(JSONObject objectkeywordzone){
		String pingyin="";
		String address="";
		String value="";
		String num="";
		String index="";
		String wname="";
		String stateInitColor="";
		
		String[][] numsix={{"chongqing","����"},{"zhejiang","�㽭"},{"yunnan","����"},{"xinjiang","�½�"},{"hongkong","���"},{"xizang","����"},{"tianjin","���"},{"taiwan","̨��"},{"sichuan","�Ĵ�"},{"shanghai","�Ϻ�"},{"shaanxi","����"},{"shanxi","ɽ��"},{"shandong","ɽ��"},{"qinghai","�ຣ"},{"ningxia","����"},{"neimongol","���ɹ�"},{"liaoning","����"},{"jiangxi","����"},{"jiangsu","����"},{"jilin","����"},{"hunan","����"},{"hubei","����"},{"heilongjiang","������"},{"henan","����"},{"hebei","�ӱ�"},{"hainan","����"},{"guizhou","����"},{"guangxi","����"},{"guangdong","�㶫"},{"gansu","����"},{"fujian","����"},{"beijing","����"},{"macau","����"},{"anhui","����"}};
//		System.out.println(objectkeywordzone.get("jiangsu"));
		for (int i = 0; i < numsix.length; i++) {
			pingyin=numsix[i][0];
			address=numsix[i][1];
			JSONObject objectkeywordzones=JSONObject.fromObject(objectkeywordzone.get(pingyin));
			//{"value":"9.16%","ct":"7055","index":"2","wname":"�˳�","stateInitColor":"61B6FD"}
			value=(String) objectkeywordzones.get("value");
			num=(String) objectkeywordzones.get("num");
			index=(String) objectkeywordzones.get("index");
			wname=(String) objectkeywordzones.get("wname");
			stateInitColor=(String) objectkeywordzones.get("stateInitColor");
			System.out.println(pingyin+pingyin+value+num+index+wname+stateInitColor);
		}
		
	}
	
	
	

	/**
	 * �˵Ĵ���
	 */

	public static void main(String[] args) throws Exception {
		String name = "����";
		String URLEncodername = "";
		System.out.println(
				URLEncodername = java.net.URLEncoder.encode(java.net.URLEncoder.encode(name, "utf-8"), "utf-8"));
		System.out.println(Weibo("http://data.weibo.com/index/ajax/contrast?key2=" + URLEncodername
				+ "&key3=&key4=&key5=&key6=&_t=0&__rnd=1450262484071"));
		people(name);
	}
}
