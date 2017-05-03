package com.artsoft.download.GuangDianZhongJu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class ImportantLixiang {

	/**
	 * �ش�������ʾ
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications/importantLixiang/view.shanty?appName=importantLixiang&id=0143d2a261a65a344028819a43aeb279";

		mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=importantLixiang";
		// mainurl(mainUrl);
		for (int i = 1; i < 7; i++) {
			mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=importantLixiang&pageIndex="
					+ i;
			urlmainall(mainUrl);
		}
//		 mainUrl =
//		 "http://dsj.sarft.gov.cn/tims/site/views/applications/importantLixiang/view.shanty?appName=importantLixiang&id=011fd9e4c4a70706402881f71fd9e2a5";
//		 mainurl(mainUrl);

	}
	
	public static void runstatic(){
		String mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications/importantLixiang/view.shanty?appName=importantLixiang&id=0143d2a261a65a344028819a43aeb279";

		mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=importantLixiang";
		// mainurl(mainUrl);
		for (int i = 1; i < 7; i++) {
			mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=importantLixiang&pageIndex="
					+ i;
			urlmainall(mainUrl);
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
					mainurl(urlss);
				}
			}
		}

	}

	/**
	 * ��ϸ����
	 * 
	 * @param mainUrl
	 */
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

		String tvplayname = ""; // ����
		String gdsubjectname = ""; // �������
		String setnumber = ""; // ����
		String reportcompany = ""; // �걨����
		String projectdate = ""; // ��������
		String shootinglicense = "";// �������֤
		String provinceopinion = "";// ʡ�����
		String adminopinion = ""; // �ܾ����
		String tvplaycontent = ""; // ������Ҫ
		String remark = ""; // ��ע

		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("table");
		for (Element element : links) {
			// try {

			// System.out.println(element);
			// System.out.println(element.attr("style"));
			// System.out.println(element.select("p").attr("style"));
			if ("margin:10px 0px 40px 0px;".equals(element.attr("style"))) {
				// System.out.println(element);
				// String tvplayname = ""; // ����
				// String gdsubjectname = ""; // �������
				// String setnumber = ""; // ����
				// String reportcompany = ""; // �걨����
				// String projectdate = ""; // ��������
				// String shootinglicense = "";// �������֤
				// String provinceopinion = "";// ʡ�����
				// String adminopinion = ""; // �ܾ����
				// String tvplaycontent = ""; // ������Ҫ
				// String remark = ""; // ��ע
				// String strlist=HtmlAnalyze.getTagText(strHtml, "��ע",
				// "������Ҫ",
				// false, 0);
				String strlist1 = HtmlAnalyze.getTagText(element.toString(), "��ע</th>", "<td colspan=\"5\">������Ҫ", true,
						0);
				// System.out.println(strlist);
				System.out.println(strlist1);
				String[] morelist = strlist1.split("/td>");
				int i = 0;
				for (String string : morelist) {
					if (string != null && !"".equals(string)) {
						// System.out.println(i);
						// System.out.println(string);
						String string2 = "";
						try {

							string2 = HtmlAnalyze.getTagText(string, "center\">", "<").replaceAll("&nbsp;", "")
									.replaceAll(" ", "");
							if (i == 1) {
								tvplayname = string2;
							}
							if (i == 2) {
								gdsubjectname = string2;
							}
							if (i == 3) {
								setnumber = string2;
							}
							if (i == 3) {
								remark = string2;
							}
						} catch (Exception e) {
							// TODO: handle exception
						}

						i += 1;
					}

				}

				reportcompany = HtmlAnalyze.getTagText(element.toString(), "�걨������", "</td>").replaceAll("&nbsp;", "");
				// System.out.println(reportcompany);

				shootinglicense = HtmlAnalyze.getTagText(element.toString(), "���֤�ţ�", "</td>");
				// System.out.println(shootinglicense);
				shootinglicense = shootinglicense.replace("null", "");

				projectdate = HtmlAnalyze.getTagText(element.toString(), reportcompany, "���֤�ţ�");
				// System.out.println(projectdate);
				projectdate = projectdate.replace("null", "");

				provinceopinion = HtmlAnalyze.getTagText(element.toString(), "ʡ�������ű������ ", "&nbsp;");
				if (provinceopinion!=null&&!"".equals(provinceopinion)) {
					provinceopinion=provinceopinion.replaceAll("&nbsp;", "");
				}
				
				System.out.println("111" + element.toString());
				adminopinion = HtmlAnalyze.getTagText(element.toString(), "�ܾ����</td>", "</td>");
				tvplaycontent = HtmlAnalyze.getTagText(element.toString(), "colspan=\"5\">������Ҫ��", "&nbsp;");
				OracleSarFtGov.intosubjectproject(tvplayname, gdsubjectname, setnumber, reportcompany, projectdate,
						shootinglicense, provinceopinion, adminopinion, tvplaycontent, remark, "", "1", "", "");

			}

			if ("margin: 0px; padding: 0px".equals(element.select("p").attr("style"))) {
				// System.out.println("========"+element.select("p"));
				// String reportcompany="";
				// String shootinglicense="";
				// String projectdate="";
				System.out.println(
						reportcompany = HtmlAnalyze.getTagText(element.select("p").toString(), "������", "&nbsp;"));
				System.out.println(
						shootinglicense = HtmlAnalyze.getTagText(element.select("p").toString(), "���֤�ţ�", "</p>"));
				projectdate = HtmlAnalyze.getTagText(element.select("p").toString(), reportcompany, "���֤�ţ�");
				if (projectdate!=null&&!"".equals(projectdate)) {
					
					if (projectdate.contains("&nbsp;")) {
						projectdate = projectdate.replaceAll("&nbsp;", "");
						System.out.println(projectdate);
					}
				}

			}

			if ("border-collapse: collapse; border: medium none".equals(element.attr("style"))) {
				System.out.println(element);
				// String tvplayname = ""; // ����
				// String gdsubjectname = ""; // �������
				// String setnumber = ""; // ����
				// String reportcompany = ""; // �걨����
				// String projectdate = ""; // ��������
				// String shootinglicense = "";// �������֤
				// String provinceopinion = "";// ʡ�����
				// String adminopinion = ""; // �ܾ����
				// String tvplaycontent = ""; // ������Ҫ
				// String remark = ""; // ��ע
				// String strlist=HtmlAnalyze.getTagText(strHtml, "��ע",
				// "������Ҫ",
				// false, 0);
				String strlist1 = HtmlAnalyze.getTagText(element.toString(), "��ע", "������Ҫ", true, 0);
				// System.out.println(strlist);
				System.out.println(strlist1);
				String[] morelist = strlist1.split("/span>");
				int i = 0;
				int x=0;
				for (String string : morelist) {
					if (string != null && !"".equals(string)) {
						// System.out.println(i);
						// System.out.println(string);
						String string2 = "";
						try {

							string2 = HtmlAnalyze.getTagText(string, "����\">", "<").replaceAll("&nbsp;", "")
									.replaceAll(" ", "");
							System.out.println(i);
							System.out.println(string2);
							if (i == 2) {
								tvplayname = string2;
							}
							if (i == 3) {
								gdsubjectname = string2;
							}
							if (i == 4+x) {
								setnumber = string2;
								try {
									Integer.parseInt(setnumber);
								} catch (Exception e) {
									// TODO: handle exception
									System.out.println("setnumber"+setnumber);
									x+=1;
								}
							}
//							if (i == 5+x) {
//								remark = string2;
//							}
						} catch (Exception e) {
							// TODO: handle exception
						}

						i += 1;
					}

				}
				
				
//				String strlist2 = HtmlAnalyze.getTagText(element.toString(), "���", "������Ҫ", true, 0);
//				// System.out.println(strlist);
//				System.out.println(strlist2);
//				String[] morelist1 = strlist2.split("/td>");
//				System.out.println(morelist1.length);
//				i=0;
//				for (String string : morelist1) {
////					if (string != null && !"".equals(string)) {
////						System.out.println("================"+string);
//						
//						String string2 = "";
//
//							string2 = HtmlAnalyze.getTagText(string, "\">", "</p>");
//							System.out.println(i);
//							System.out.println(string2);
//							i+=1;
////					}
//
//				}
				
				
				
				//
				// reportcompany =
				// HtmlAnalyze.getTagText(element.toString(),
				// "�걨������", "</span></span></span>");
				// System.out.println(reportcompany);
				// System.out.println(element.toString());
				// reportcompany = HtmlAnalyze.getTagText(element.toString(),
				// "�걨������", "</span></span></span>");
				// System.out.println(reportcompany);

				// shootinglicense = HtmlAnalyze.getTagText(element.toString(),
				// "���֤�ţ�", "���");
				// System.out.println(shootinglicense);
				// shootinglicense = shootinglicense.replace("null", "");

				// projectdate = HtmlAnalyze.getTagText(element.toString(),
				// reportcompany, "���֤�ţ�");
				// System.out.println(projectdate);
				// projectdate = projectdate.replace("null", "");

				provinceopinion = HtmlAnalyze.getTagText(element.toString(), "ʡ�������ű������", "�ܾ����");
				adminopinion = HtmlAnalyze.getTagText(element.toString(), "�ܾ����", "</tbody>");
				tvplaycontent = HtmlAnalyze.getTagText(element.toString(), "������Ҫ��", "ʡ�������ű������");
				try {
					
					OracleSarFtGov.intosubjectproject(tvplayname, gdsubjectname, setnumber, reportcompany, projectdate,
							shootinglicense, provinceopinion, adminopinion, tvplaycontent, remark, "", "1", "", "");
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			// } catch (Exception e) {
			// // TODO: handle exception
			// }
		}

	}

}
