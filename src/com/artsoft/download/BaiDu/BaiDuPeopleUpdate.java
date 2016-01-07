package com.artsoft.download.BaiDu;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.Persion;
import com.artsoft.config.ConfigManager;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class BaiDuPeopleUpdate {

	/**
	 * ������Ϣ��ץȡ
	 * 
	 * @param strId
	 * @param url
	 */
	private static void mainmore(String strId, String url) {
		Persion person= BaiDuPeopleDownload.mainmore(strId, url);
		OracleHaoSou.InsertTemDimPerson(person);
	}

	/**
	 * �������ݵĿ�ʼ�ͽ������ݵķֿ�
	 * 
	 * @param statnum
	 * @param endnum
	 */
	private static void mainbaiduPeoPle(int statnum, int endnum) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectBaiduiInformation(Integer.toString(statnum),
				Integer.toString(endnum));
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				mainmore(listTemp.get(0), listTemp.get(1));
			}
		}
	}

	
	/**
	 * ���аٶ����ݵĲ������ݵĸ��²���
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		mainbaiduPeoPle(0, 1);
		
	}

}
