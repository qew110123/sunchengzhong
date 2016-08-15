package com.artsoft.download.BaiDu.chuangzuotuandui;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.CREATIVE_TEAM;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class chuangzuotuandui {
	
	
public static void mainmore(String strId, String url, String strUrlname,int sount) {
		
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String chuangzuotuandui=HtmlAnalyze.getTagText(strHtml.toString(), "�����Ŷ�</h2>", "</table>",false,0);
		
		String chuangzuotuanduihtml=HtmlAnalyze.getTagText(chuangzuotuandui.toString(), "<table", "</table>",false,0);

		System.out.println(chuangzuotuanduihtml);
		
		Document doc = Jsoup.parse(chuangzuotuanduihtml);
		
//		System.out.println(doc.toString());
		
		CREATIVE_TEAM CREATIVE = new CREATIVE_TEAM();
		CREATIVE.setDataId(strId);
		CREATIVE.setDataUrl(url);
		CREATIVE.setDataName(strUrlname);
		Elements linktd = doc.select("td");
//		for (Element element : linktd) {
//			System.out.println(element);
//			System.out.println(element.text());
//			
//		}
		for (int i = 0; i < linktd.size()-1; i++) {
			
			CREATIVExuanzhe(CREATIVE, linktd.get(i).text()+"", linktd.get(i+1).text()+"");
		}
		
		System.out.println(CREATIVE); 
		OracleHaoSou.intoCREATIVE_TEAM(CREATIVE);
		
	}

	public static CREATIVE_TEAM CREATIVExuanzhe(CREATIVE_TEAM CREATIVE,String String1,String String2){
		
		if ("�ܲ߻�".equals(String1)) {
//			baseInfoValue=delectbiaoqian(baseInfoValue);
//			baseInfoValue=baseInfoValue.replaceAll("&middot;", "��");
			CREATIVE.setMasterPlan(String2);
		}
//		System.out.println(String2);
		if ("��ͳ��".equals(String1)) {
//			baseInfoValue=delectbiaoqian(baseInfoValue);
//			baseInfoValue=baseInfoValue.replaceAll("&middot;", "��");
			CREATIVE.setPresidentChips(String2);
		}
		
		if ("����Ƭ��".equals(String1)) {
			CREATIVE.setChiefProducer(String2);
		}
		
		if ("�ܵ���".equals(String1)) {
			CREATIVE.setGeneralDirector(String2);
		}
		
		if ("�ܱ��".equals(String1)) {
			CREATIVE.setHeadWriter(String2);
		}
		
		if ("����ͳ��".equals(String1)) {
			CREATIVE.setArtistTc(String2);
		}
		
		if ("������".equals(String1)) {
			CREATIVE.setDirectorGroup(String2);
		}
		
		if ("�����".equals(String1)) {
			CREATIVE.setScreenwriterGroup(String2);
		}
		
		if ("����������".equals(String1)) {
			CREATIVE.setDirectorKoreaGroup(String2);
		}
		
		if ("���˹���".equals(String1)) {
			CREATIVE.setArtistConsultant(String2);
		}
		
		if ("����ͳ��".equals(String1)) {
			CREATIVE.setCameraTc(String2);
		}
		
		if ("������ͳ��".equals(String1)) {
			CREATIVE.setTechnologyBossTc(String2);
		}
		
		if ("����ͳ��".equals(String1)) {
			CREATIVE.setTechnologyTc(String2);
		}
		
		if ("������ͳ��".equals(String1)) {
			CREATIVE.setLaterBossTc(String2);
		}
		
		if ("�����ƹ�ͳ��".equals(String1)) {
			CREATIVE.setPropagandaTc(String2);
		}
		
		if ("����ܲ߻�".equals(String1)) {
			CREATIVE.setAdvertBossPlan(String2);
		}
		
		if ("���ͳ��".equals(String1)) {
			CREATIVE.setAdvertPlan(String2);
		}
		
		if ("��װͳ��".equals(String1)) {
			CREATIVE.setDressPlan(String2);
		}
		
		if ("��װʦ".equals(String1)) {
			CREATIVE.setDresser(String2);
		}
		
		if ("��װ�Ŷ�".equals(String1)) {
			CREATIVE.setDressTeam(String2);
		}
		
		if ("��ױ�Ŷ�".equals(String1)) {
			CREATIVE.setMakeupTeam(String2);
		}
		
		if ("��Ƭ��ͳ��".equals(String1)) {
			CREATIVE.setProducerBossTc(String2);
		}
		
		if ("��Ƭ����".equals(String1)) {
			CREATIVE.setProducerDirector(String2);
		}
		
		if ("��Ƭ����".equals(String1)) {
			CREATIVE.setProducerCooperation(String2);
		}
		
		if ("����".equals(String1)) {
			CREATIVE.setProducer(String2);
		}
		
		if ("�ܼ���".equals(String1)) {
			CREATIVE.setProducerBoss(String2);
		}
		
		if ("���ʵ�λ".equals(String1)) {
			CREATIVE.setAdvisoryUnit(String2);
		}
		
		if ("���ϳ�Ʒ��λ".equals(String1)||"���ϳ�Ʒ".equals(String1)) {
			CREATIVE.setProductionUnit(String2);
		}
		
		if ("���ڳ���".equals(String1)) {
			CREATIVE.setPostSystem(String2);
		}
		
		if ("����������˾".equals(String1)) {
			CREATIVE.setHanProductionCompany(String2);
		}
		
		if ("�ܲ߻�".equals(String1)) {
			CREATIVE.setPlanBoss(String2);
		}
		
		if ("�߻�".equals(String1)) {
			CREATIVE.setPlan(String2);
		}
		

		if ("�ർ".equals(String1)) {
			CREATIVE.setDirector(String2);
		}
		
		return CREATIVE;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainmore("0", "http://baike.baidu.com/link?url=z0vwyxV8E7JGJNLPYKVNYWggdXYOnDotXY3UB6aTuE3ChburMu_hBKfQYmX6LidAtUf6YwWzd4WSsfh_1zdh6NNpA8D7dB_Na94-XyhxM-QVWQqXLvrP12nVR5khCZWy5vwhlPtHooAnq92tXYpjqmRuVv5tAWwCWWbB3umsFZG", "���ܰ��ֵܵ��ļ�", 0);

	}

}
