package com.artsoft.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		String mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications/note/noteTable.shanty?id=014fed5c0b0442bc4028e4a14fa22a0f";
		mainurl(mainUrl, "", "", "", "");

	}

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
		System.out.println(strHtml);

		// String tvplayname=HtmlAnalyze.getTagText(strHtml,"<td height=\"30\"
		// style="FONT-SIZE: 16px;"> ", "\""); //������
		String strlistm = HtmlAnalyze.getTagText(strHtml, "<td height=\"30\" style=\"FONT-SIZE: 16px;\"> ", "</tr>");
		System.out.println(strlistm);
		String[] sourceStrArray = strlistm.split("\n");
		int i = 0;
		String genre = "";// ���
		String setnumber="";//��
		String producedate="";//��������
		String producecycle="";//��������
		for (String strtext : sourceStrArray) {
			// System.out.println(i+strtext);
			strtext = strtext.replaceAll(" ", "").replaceAll("\r", "");
			System.out.println(i + strtext + i);
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
			}
			if (i == 5) {
				setnumber=strtext.replaceAll("&nbsp;", "");

			}
			if (i == 6) {
				producedate=strtext.replaceAll("&nbsp;", "");
			}
			if (i == 7) {
				producecycle=strtext.replaceAll("&nbsp;", "");
			}

			i += 1;
		}
		// genre
		System.out.println(genre);
		System.out.println(setnumber);
		System.out.println(producedate);
		
		System.out.println(producecycle);
		
		String reportcompany=HtmlAnalyze.getTagText(strHtml, "����������", "</td>");
		String projectdate=HtmlAnalyze.getTagText(strHtml, "<td width=\"231\" align=\"center\" style=\"FONT-SIZE: 16px;\"> ", "</td>");
		
		String shootinglicense=HtmlAnalyze.getTagText(strHtml, "���֤�ţ� ", "</td>");
		
		//<td height="200" colspan="4" align="left" style="FONT-SIZE: 16px;"> &nbsp; 
		String provinceopinion=HtmlAnalyze.getTagText(strHtml, "<td height=\"200\" colspan=\"4\" align=\"left\" style=\"FONT-SIZE: 16px;\"> &nbsp; ", "</td>");
		String departmentopinion=HtmlAnalyze.getTagText(strHtml, "��ز������</td>", "</tr>");
				//��ز������</td>
		System.out.println(departmentopinion=departmentopinion.replaceAll("&nbsp;", ""));
		String remark=HtmlAnalyze.getTagText(strHtml, "��ע</td>", "</tr>");
//		System.out.println(remark);
		System.out.println(remark=remark.replaceAll("&nbsp;", ""));
		String tvplaycontent=HtmlAnalyze.getTagText(strHtml, "������Ҫ��", "</td>");
		// String gdsubjectname="";//�������
//		String producedate = "";// ��������
		// String tyPlayName = "";
		// System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml,
		// "cname: \"", "\""));

		// System.out.println(strHtml);
		// Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		// Elements links = linkmain.select("ul.v-list-inner li.v-list-unit");
		// Element content = doc.getElementById("content");
	}

}
