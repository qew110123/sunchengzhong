package com.artsoft.download.doubanDuShu;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.Movies.DownDoubanMovie;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class doubanDuShu {
	
	public static void openstatic() {
		
//		String[] list = { "С˵", "�����ѧ", "��ѧ", "���", "�й���ѧ", "����", "ɢ��", "�ձ���ѧ", "���ϴ���", "ͯ��", "ʫ��", "����", "��С��", "��ͯ��ѧ", "�ŵ���ѧ", "�Ű���", "�໪", "����", "������ѧ", "Ǯ����", "³Ѹ", "�������", "ʫ��", "������", "������������", "����˹", "��̨", "����", "�汾", "����", "�ഺ", "����", "�ƻ�", "��Ұ����", "����", "����", "����", "���", "�ձ�����", "����", "����", "��ë", "���ݱ���", "����С˵", "������", "����С˵", "��Խ", "��ӹ", "��С˵", "����ɯ������˹��", "����", "��С�", "����", "ħ��", "�ഺ��ѧ", "�ƻ�С˵", "J.K.����", "��ľֱ��", "����", "����", "����", "����Ȼ", "�̿���", "��ʷ", "����ѧ", "��ѧ", "����", "�Ļ�", "���ѧ", "����", "���", "����", "���", "����", "�ڽ�", "��Ӱ", "��ѧ", "����ѧ", "����¼", "˼��", "�й���ʷ", "��ѧ", "����", "����", "Ϸ��", "���ﴫ��", "�滭", "����ʷ", "���", "����", "������ѧ", "����ʷ", "��ս", "��������", "����", "����", "����", "����", "����", "��־", "�ɳ�", "����", "��Ӱ", "Ů��", "ְ��", "��ʳ", "����", "�μ�", "����", "����", "���", "�ֹ�", "����", "����", "�˼ʹ�ϵ", "�Ҿ�", "������", "����ѧ", "����", "����", "����", "��ҵ", "Ͷ��", "Ӫ��", "��ҵ", "���", "���", "��Ʊ", "��ҵʷ", "�߻�", "����", "������", "���", "��ѧ", "�������", "�û�����", "�㷨", "web", "�Ƽ�", "UE", "ͨ��", "����", "UCD", "������", "����" };
//		for (String stringtext : list) {
//			for (int i = 0; i <= 500; i = i + 15) {
//				try {
//					System.out.println("https://www.douban.com/tag/" + stringtext
//							+ "/book?start=" + i);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//
//		}
		
			
			mainurl("https://www.douban.com/tag/%E5%B0%8F%E8%AF%B4/book?start=15");
		
	}
	
	private static boolean mainurl(String urlMain) {
		// TODO Auto-generated method stub
		
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

		Elements links = doc.select("a.title");
		for (Element element : links) {
			String url="";
			String title="";
			String id="";
			System.out.println(url=element.attr("href"));
			System.out.println(title=element.text());
			System.out.println(id = HtmlAnalyze.getTagText(url, "/subject/", "/?"));
			doubanurl(id,title,url);
		}
		
		
		return false;
		
		
	}

	private static void doubanurl(String id, String title, String urlMain) {
		// TODO Auto-generated method stub
		urlMain=urlMain.replace("https:", "http:");
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);
		
		String score ="";
		System.out.println(score=doc.select("strong.rating_num").text());
		
		String score_num="";
		System.out.println(score_num=doc.select("a.rating_people").text().replace("������", ""));
		
		String author="";
		System.out.println(author=HtmlAnalyze.getTagText(strHtml, "����</span>:", "<br/>"));
		
		String press="";
		System.out.println(press=HtmlAnalyze.getTagText(strHtml, "������:</span>", "<br/>"));
		
		String original_name="";
		System.out.println(original_name=HtmlAnalyze.getTagText(strHtml, "ԭ����:</span>", "<br/>"));
		
		String translator="";
		System.out.println(translator=HtmlAnalyze.getTagText(strHtml, "����</span>:", "<br/>"));
		
		String publication_year="";
		System.out.println(publication_year=HtmlAnalyze.getTagText(strHtml, "������:</span>", "<br/>"));
		
		String pages="";
		System.out.println(pages=HtmlAnalyze.getTagText(strHtml, "ҳ��:</span>", "<br/>"));
		
		
		String price="";
		System.out.println(price=HtmlAnalyze.getTagText(strHtml, "����:</span>", "<br/>"));
		
		String binding="";
		System.out.println(binding=HtmlAnalyze.getTagText(strHtml, "װ֡:</span>", "<br/>"));
		
		String series="";
		System.out.println(series=HtmlAnalyze.getTagText(strHtml, "����:</span>", "<br/>"));
		
		String ISBN="";
		System.out.println(ISBN=HtmlAnalyze.getTagText(strHtml, "ISBN:</span>", "<br/>"));
		
		String Abstract="";
		System.out.println(Abstract=HtmlAnalyze.getTagText(strHtml, "<span class=\"all hidden\">", "</span>").replace(".intro p{text-indent:2em;}", ""));
		
		String Biography="";
		System.out.println(Biography=HtmlAnalyze.getTagText(strHtml, "<div class=\"indent \">", "</p></div>").replace(".intro p{text-indent:2em;}", ""));
		
		String catalog="";
		System.out.println(catalog=HtmlAnalyze.getTagText(strHtml, "Ŀ¼</span>", "</div>").replace("&nbsp;", ""));
		
		String label="";
		Elements cataloglinks = doc.select("a.tag");
		for (Element element : cataloglinks) {
			label=label+element.text();
		}
		System.out.println(label);
		
		
		
		
		
	}


	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
	}

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
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// runstatic();
		openstatic();
//		 TimingTime(11, 59, 59);

	}

}
