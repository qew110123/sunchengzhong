package com.artsoft.download.GuangDianZhongJu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class GuangDianZhongJuBeiAnGongShi {

	/**
	 * ����ֱܾ�����ʾ
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note
		// �������ݵ���ϸ�����ݵĲɼ�
		// String mainUrl =
		// "http://dsj.sarft.gov.cn/tims/site/views/applications/note/noteTable.shanty?id=014fed5c0b0442bc4028e4a14fa22a0f";
		// mainurl(mainUrl, "", "", "", "");

		// String urlxiangxi=
		// "http://dsj.sarft.gov.cn/tims/site/views/applications/note/view.shanty?appName=note&id=0150d07ff83776bf4028819a4d2365a7";
		// urlmain(urlxiangxi);

		String urls = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note&pageIndex=1";
		for (int i = 1; i < 9; i++) {
			urls = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=note&pageIndex="+i;
			urlmainall(urls);
		}

	}

	/**
	 * �������ݵ��������ݵ�
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
	 * �������ݵ��������ݵ�
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
		String tvplayname = ""; // ����
		String gdsubjectname = ""; // �������
		String prduucecompany = ""; // ��������
		String tvpalyarea = ""; // ����
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
	 * �������ݵ���ϸ�ɼ�
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
		// style="FONT-SIZE: 16px;"> ", "\""); //������
		String strlistm = HtmlAnalyze.getTagText(strHtml, "<td height=\"30\" style=\"FONT-SIZE: 16px;\"> ", "</tr>");
		// System.out.println(strlistm);
		if (strlistm == null) {
			return;
		}
		String[] sourceStrArray = strlistm.split("\n");
		int i = 0;
		String genre = "";// ���
		String setnumber = "";// ��
		String producedate = "";// ��������
		String producecycle = "";// ��������
		for (String strtext : sourceStrArray) {
			// System.out.println(i+strtext);
			strtext = strtext.replaceAll(" ", "").replaceAll("\r", "");
			// System.out.println(i + strtext + i);
			if (i == 2 || i == 3 || i == 4) {
				if (!strtext.toString().contains("nbsp")) {
					switch (i) {
					case 2:
						genre = "һ��";
						break;
					case 3:
						genre = "ϲ��";
						break;
					case 4:
						genre = "Ϸ��";
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

		String reportcompany = HtmlAnalyze.getTagText(strHtml, "����������", "</td>");
		String projectdate = HtmlAnalyze.getTagText(strHtml,
				"<td width=\"231\" align=\"center\" style=\"FONT-SIZE: 16px;\"> ", "</td>");

		String shootinglicense = HtmlAnalyze.getTagText(strHtml, "���֤�ţ� ", "</td>");

		// <td height="200" colspan="4" align="left" style="FONT-SIZE: 16px;">
		// &nbsp;
		String provinceopinion = HtmlAnalyze.getTagText(strHtml,
				"<td height=\"200\" colspan=\"4\" align=\"left\" style=\"FONT-SIZE: 16px;\"> &nbsp; ", "</td>");
		String departmentopinion = HtmlAnalyze.getTagText(strHtml, "��ز������</td>", "</tr>");
		// ��ز������</td>
		System.out.println(departmentopinion = departmentopinion.replaceAll("&nbsp;", ""));
		String remark = HtmlAnalyze.getTagText(strHtml, "��ע</td>", "</tr>");
		// System.out.println(remark);
		System.out.println(remark = remark.replaceAll("&nbsp;", "").replaceAll("\r\n", "").replaceAll("\t", ""));
		String tvplaycontent = HtmlAnalyze.getTagText(strHtml, "������Ҫ��", "</td>");
		System.out.println(tvplaycontent);

		OracleSarFtGov.intoShanty(tvplayname, gdsubjectname, prduucecompany, producedate, producecycle, setnumber,
				reportcompany, projectdate, shootinglicense, genre, provinceopinion, departmentopinion, remark, mainUrl,
				tvplaycontent, tvpalyarea);
				// String gdsubjectname="";//�������
				// String producedate = "";// ��������
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
