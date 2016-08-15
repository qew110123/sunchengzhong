package com.artsoft.download.GuangDianZhongJu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class Changing {

	/**
	 * 	���ͨ���б�
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		mainurl1("http://dsj.sarft.gov.cn/tims/site/views/applications/changing/view.shanty?appName=changing&id=013f977998df4841402881a03e45415b");
		String mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications/importantLixiang/view.shanty?appName=importantLixiang&id=0143d2a261a65a344028819a43aeb279";
		for (int i = 0; i < 8; i++) {
			mainUrl = "http://dsj.sarft.gov.cn/tims/site/views/applications.shanty?appName=changing&pageIndex=" + i;
			urlmainall(mainUrl);
		}
		
		
		
//		 mainUrl =
//		 "http://dsj.sarft.gov.cn/tims/site/views/applications/changing/view.shanty?appName=changing&id=0145cc133e3325fe4028819a45743e51";
//		 mainurl(mainUrl);
		// System.out.println(textnull(" ս�ػƻ� �ѻ��� ����С����Ӱ�ӹ�˾"));

	}
	
	private static void mainurl1(String mainUrl) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		String biangejumingall=HtmlAnalyze.getTagText(strHtml.toString(), "��������ĵ��Ӿ�", "</table>",false,0);
		
		String biangejuming=HtmlAnalyze.getTagText(biangejumingall.toString(), "<table", "</table>",false,0);
		
		Document biangejumingdoc = Jsoup.parse(biangejuming);
		
		
		String OLD_VALUE = "";// ԭ����ֵ
		String NEW_VALUE = "";// ������ֵ
		String TVPLAY_NAME = "";// ����
		String PRODUCE_COMPANY = "";// ������˾
		int SET_NUMBER = 0; // ����
		
		Elements linktd = biangejumingdoc.select("tr");
		for (Element element : linktd) {
//			System.out.println(element);
			OLD_VALUE = "";// ԭ����ֵ
			NEW_VALUE = "";// ������ֵ
			TVPLAY_NAME = "";// ����
			PRODUCE_COMPANY = "";// ������˾
			int TYPE = 0;
			
			int x = 0;
			Elements linkstrtd = element.select("td");
			for (Element element3 : linkstrtd) {
				// System.out.println(element3.text());
				// System.out.println(x);
					TYPE = 1;
					if (x % 3 == 0) {
						TVPLAY_NAME = element3.text();
						OLD_VALUE = element3.text();
					}
					if (x % 3 == 1) {
						NEW_VALUE = element3.text();
					}
					if (x % 3 == 2) {
						PRODUCE_COMPANY = element3.text();
						System.out.println(
								OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
						if (!OLD_VALUE.equals("ԭ����")) {
						OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
								PRODUCE_COMPANY, 0, TYPE,mainUrl);
							
						}
					}
					x+=1;

			}
		}
		
		
		
		
		
		String biangezhizhuodanweiall=HtmlAnalyze.getTagText(strHtml.toString(), "���������λ�ĵ��Ӿ�", "</table>",false,0);
		
		String biangezhizhuodanwei=HtmlAnalyze.getTagText(biangezhizhuodanweiall.toString(), "<table", "</table>",false,0);
		
		Document biangezhizhuodanweidoc = Jsoup.parse(biangezhizhuodanwei);
		
		
//		String OLD_VALUE = "";// ԭ����ֵ
//		String NEW_VALUE = "";// ������ֵ
//		String TVPLAY_NAME = "";// ����
//		String PRODUCE_COMPANY = "";// ������˾
//		int SET_NUMBER = 0; // ����
		
		Elements linkbiangezhizhuodanwei = biangezhizhuodanweidoc.select("tr");
		for (Element element : linkbiangezhizhuodanwei) {
//			System.out.println(element);
			OLD_VALUE = "";// ԭ����ֵ
			NEW_VALUE = "";// ������ֵ
			TVPLAY_NAME = "";// ����
			PRODUCE_COMPANY = "";// ������˾
			int TYPE = 0;
			
			int x = 0;
			Elements linkstrtd = element.select("td");
			for (Element element3 : linkstrtd) {
				TYPE = 2;
				if (x % 3 == 0) {
					TVPLAY_NAME = element3.text();
				}
				if (x % 3 == 1) {
					OLD_VALUE = element3.text();
				}
				if (x % 3 == 2) {
					NEW_VALUE = element3.text();
					PRODUCE_COMPANY = element3.text();
					System.out.println(
							OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
					
					if (!OLD_VALUE.equals("ԭ������λ")) {
					OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
							PRODUCE_COMPANY, 0, TYPE,mainUrl);
					}
				}
				x+=1;

				// System.out.println(element3.text());
			}
		}
		
		
		
		String biangejishuall=HtmlAnalyze.getTagText(strHtml.toString(), "������Ӿ缯��", "</table>",false,0);
		
		String biangejishu=HtmlAnalyze.getTagText(biangejishuall.toString(), "<table", "</table>",false,0);
		
		Document biangejishudoc = Jsoup.parse(biangejishu);
		
		
//		String OLD_VALUE = "";// ԭ����ֵ
//		String NEW_VALUE = "";// ������ֵ
//		String TVPLAY_NAME = "";// ����
//		String PRODUCE_COMPANY = "";// ������˾
//		int SET_NUMBER = 0; // ����
		
		Elements linkbiangejishuwei = biangejishudoc.select("tr");
		for (Element element : linkbiangejishuwei) {
//			System.out.println(element);
			OLD_VALUE = "";// ԭ����ֵ
			NEW_VALUE = "";// ������ֵ
			TVPLAY_NAME = "";// ����
			PRODUCE_COMPANY = "";// ������˾
			int TYPE = 0;
			
			int x = 0;
			Elements linkstrtd = element.select("td");
			for (Element element3 : linkstrtd) {
					TYPE = 3;
					if (x % 3 == 0) {
						TVPLAY_NAME = element3.text();
					}
					if (x % 3 == 1) {
						OLD_VALUE = element3.text();
						NEW_VALUE = element3.text();
						try {
							SET_NUMBER = Integer
									.parseInt(HtmlAnalyze.getTagText(element3.text(), "���Ϊ", "��"));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (x % 3 == 2) {
						PRODUCE_COMPANY = element3.text();
						System.out.println(
								OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
						if (!OLD_VALUE.equals("��������")) {
							OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
									PRODUCE_COMPANY, SET_NUMBER, TYPE,mainUrl);
						}
					}

					// System.out.println(element3.text());
				x += 1;
			}
		}
		
		
		
	}

	private static void urlmainall(String mainUrl) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
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
					mainurl1(urlss);
				}
			}
		}

	}

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
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("table");
		String OLD_VALUE = "";// ԭ����ֵ
		String NEW_VALUE = "";// ������ֵ
		String TVPLAY_NAME = "";// ����
		String PRODUCE_COMPANY = "";// ������˾
		int SET_NUMBER = 0; // ����
		int TYPE = 0;
		int i = 0;
		int ii = 0;
		int x = 0;
		boolean bb1 = true;
		for (Element element : links) {
			ii = 0;
			if (element.attr("width").equals("100%") && !"".equals(element.attr("style"))
					&& !"font-size: 12px;".equals(element.attr("style"))) {
				// System.out.println(element);
				Elements linkstr = element.select("tr");
				for (Element element2 : linkstr) {
					if (i == 0) {
						OLD_VALUE = "";// ԭ����ֵ
						NEW_VALUE = "";// ������ֵ
						TVPLAY_NAME = "";// ����
						PRODUCE_COMPANY = "";// ������˾
						x = 0;
						Elements linkstrtd = element2.select("td");
						for (Element element3 : linkstrtd) {
							// System.out.println(element3.text());
							// System.out.println(x);
							if (ii >= 1) {
								TYPE = 1;
								if (x % 3 == 0) {
									TVPLAY_NAME = element3.text();
									OLD_VALUE = element3.text();
								}
								if (x % 3 == 1) {
									NEW_VALUE = element3.text();
								}
								if (x % 3 == 2) {
									PRODUCE_COMPANY = element3.text();
									System.out.println(
											OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
									OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
											PRODUCE_COMPANY, 0, TYPE,mainUrl);
								}

								// System.out.println(element3.text());
							}
//							ii+=1;
							x += 1;
						}
					}
					if (i == 1) {
						OLD_VALUE = "";// ԭ����ֵ
						NEW_VALUE = "";// ������ֵ
						TVPLAY_NAME = "";// ����
						PRODUCE_COMPANY = "";// ������˾
						x = 0;
						Elements linkstrtd = element2.select("td");
						for (Element element3 : linkstrtd) {
							// System.out.println(element3.text());
							// System.out.println(x);
							if (ii >= 1) {
								TYPE = 2;
								if (x % 3 == 0) {
									TVPLAY_NAME = element3.text();
								}
								if (x % 3 == 1) {
									OLD_VALUE = element3.text();
								}
								if (x % 3 == 2) {
									NEW_VALUE = element3.text();
									PRODUCE_COMPANY = element3.text();
									System.out.println(
											OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
									OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
											PRODUCE_COMPANY, 0, TYPE,mainUrl);
								}

								// System.out.println(element3.text());
							}
							x += 1;
						}
					}
					if (i == 2) {
						OLD_VALUE = "";// ԭ����ֵ
						NEW_VALUE = "";// ������ֵ
						TVPLAY_NAME = "";// ����
						PRODUCE_COMPANY = "";// ������˾
						x = 0;
						Elements linkstrtd = element2.select("td");
						for (Element element3 : linkstrtd) {
							// System.out.println(element3.text());
							// System.out.println(x);
							if (ii >= 1) {
								TYPE = 3;
								if (x % 3 == 0) {
									TVPLAY_NAME = element3.text();
								}
								if (x % 3 == 1) {
									OLD_VALUE = element3.text();
									NEW_VALUE = element3.text();
									try {
										SET_NUMBER = Integer
												.parseInt(HtmlAnalyze.getTagText(element3.text(), "���Ϊ", "��"));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
								if (x % 3 == 2) {
									PRODUCE_COMPANY = element3.text();
									System.out.println(
											OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
									OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
											PRODUCE_COMPANY, SET_NUMBER, TYPE,mainUrl);
								}

								// System.out.println(element3.text());
							}
							x += 1;
						}
					}
					ii += 1;
				}
				System.out.println(i);
				i += 1;
			}
			/*
			 * ���ݵ���������
			 */
//			// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//			if (!"".equals(element.attr("style")) && !"font-size: 12px;".equals(element.attr("style"))) {
//				// System.out.println(element);
//				Elements linkstr = element.select("tr");
//				for (Element element2 : linkstr) {
////					if (i == 0) {
////						OLD_VALUE = "";// ԭ����ֵ
////						NEW_VALUE = "";// ������ֵ
////						TVPLAY_NAME = "";// ����
////						PRODUCE_COMPANY = "";// ������˾
////						x = 0;
////						Elements linkstrtd = element2.select("td");
////						for (Element element3 : linkstrtd) {
////							// System.out.println(element3.text());
////							// System.out.println(x);
////							if (ii >= 1) {
////								TYPE = 1;
////								if (x % 3 == 0) {
////									TVPLAY_NAME = element3.text();
////									OLD_VALUE = element3.text();
////								}
////								if (x % 3 == 1) {
////									NEW_VALUE = element3.text();
////								}
////								if (x % 3 == 2) {
////									PRODUCE_COMPANY = element3.text();
////									System.out.println(
////											OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
////									OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
////											PRODUCE_COMPANY, 0, TYPE);
////								}
////
////								// System.out.println(element3.text());
////							}
////							x += 1;
////						}
////					}
//					if (i == 1) {
//						OLD_VALUE = "";// ԭ����ֵ
//						NEW_VALUE = "";// ������ֵ
//						TVPLAY_NAME = "";// ����
//						PRODUCE_COMPANY = "";// ������˾
//						x = 0;
//						Elements linkstrtd = element2.select("td");
//						for (Element element3 : linkstrtd) {
//							// System.out.println(element3.text());
//							// System.out.println(x);
//							if (ii >= 1) {
//								TYPE = 2;
//								if (x % 3 == 0) {
//									TVPLAY_NAME = element3.text();
//									//***************************************************
//									TVPLAY_NAME=mainUrl+TVPLAY_NAME;
//								}
//								if (x % 3 == 1) {
//									OLD_VALUE = element3.text();
//								}
//								if (x % 3 == 2) {
//									NEW_VALUE = element3.text();
//									PRODUCE_COMPANY = element3.text();
//									System.out.println(
//											OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
////									OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
////											PRODUCE_COMPANY, 0, TYPE);
//								}
//
//								// System.out.println(element3.text());
//							}
//							x += 1;
//						}
//					}
////					if (i == 2) {
////						OLD_VALUE = "";// ԭ����ֵ
////						NEW_VALUE = "";// ������ֵ
////						TVPLAY_NAME = "";// ����
////						PRODUCE_COMPANY = "";// ������˾
////						x = 0;
////						Elements linkstrtd = element2.select("td");
////						for (Element element3 : linkstrtd) {
////							// System.out.println(element3.text());
////							// System.out.println(x);
////							if (ii >= 1) {
////								TYPE = 3;
////								if (x % 3 == 0) {
////									TVPLAY_NAME = element3.text();
////								}
////								if (x % 3 == 1) {
////									OLD_VALUE = element3.text();
////									NEW_VALUE = element3.text();
////									try {
////										SET_NUMBER = Integer
////												.parseInt(HtmlAnalyze.getTagText(element3.text(), "���Ϊ", "��"));
////									} catch (Exception e) {
////										// TODO: handle exception
////									}
////								}
////								if (x % 3 == 2) {
////									PRODUCE_COMPANY = element3.text();
////									System.out.println(
////											OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY + SET_NUMBER + TYPE);
////									OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
////											PRODUCE_COMPANY, SET_NUMBER, TYPE);
////								}
////
////								// System.out.println(element3.text());
////							}
////							x += 1;
////						}
////					}
////					ii += 1;
//				}
//				System.out.println(i);
//				i += 1;
//			}

		}
		/**
		 * ��̬���ݵĲɼ�
		 */
		// try {

//		i = 0;
//		Elements strtd = links.select("td");
//		for (Element element : strtd) {
//			if (element.attr("style").equals("padding-left:10px;")) {
//				// System.out.println(element);
//				Elements strp = element.select("p");
//				for (Element element3 : strp) {
//					// System.out.println(element3);
//					String strtext = "";
//
//					if (i == 0) {
//						strtext = HtmlAnalyze.getTagText(element3.toString(), "������λ<br />", "</p>", true, 0);
//						if (strtext == null || !"".equals(strtext)) {
//							try {
//
//								String[] listbr = strtext.split("<br />");
//								for (String string : listbr) {
//									// System.out.println(string);
//									System.out.println(string = textnull(string));
//									String[] listbr1 = string.split("��");
//									x = 0;
//									for (String string1 : listbr1) {
//										if (!" ".equals(string1) && !"".equals(string1) && !"  ".equals(string1)) {
//											System.out.println(x + string1);
//											TYPE = 1;
//											if (x == 0) {
//												TVPLAY_NAME = string1;
//												OLD_VALUE = string1;
//											}
//											if (x == 1) {
//												NEW_VALUE = string1;
//											}
//											if (x == 2) {
//												PRODUCE_COMPANY = string1;
//												System.out.println(OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY
//														+ SET_NUMBER + TYPE);
//												OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
//														PRODUCE_COMPANY, 0, TYPE);
//											}
//											x += 1;
//										}
//									}
//								}
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//						}
//					}
//					if (i == 1) {
//						strtext = HtmlAnalyze.getTagText(element3.toString(), "������λ<br />", "</p>", true, 0);
//						if (strtext == null || !"".equals(strtext)) {
//							try {
//								String[] listbr = strtext.split("<br />");
//								for (String string : listbr) {
//									// System.out.println(string);
//									System.out.println(string = textnull(string));
//									String[] listbr1 = string.split("��");
//									x = 0;
//									for (String string1 : listbr1) {
//										if (!" ".equals(string1) && !"".equals(string1) && !"  ".equals(string1)) {
//											System.out.println(string1);
//
//											TYPE = 2;
//											if (x == 0) {
//												TVPLAY_NAME = string1;
//											}
//											if (x == 1) {
//												OLD_VALUE = string1;
//											}
//											if (x == 2) {
//												NEW_VALUE = string1;
//												PRODUCE_COMPANY = string1;
//												System.out.println(OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY
//														+ SET_NUMBER + TYPE);
//												OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
//														PRODUCE_COMPANY, 0, TYPE);
//											}
//
//											x += 1;
//										}
//									}
//								}
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//						}
//					}
//					if (i == 2) {
//						strtext = HtmlAnalyze.getTagText(element3.toString(), "������λ<br />", "<br /> <br /> ", true, 0);
//						if (strtext == null || !"".equals(strtext)) {
//							try {
//
//								String[] listbr = strtext.split("<br />");
//								for (String string : listbr) {
//									// System.out.println(string);
//									System.out.println(string = textnull(string));
//									String[] listbr1 = string.split("��");
//									x = 0;
//									for (String string1 : listbr1) {
//										if (!" ".equals(string1) && !"".equals(string1) && !"  ".equals(string1)) {
//											System.out.println(string1);
//
//											TYPE = 3;
//											if (x == 0) {
//												TVPLAY_NAME = string1;
//											}
//											if (x == 1) {
//												OLD_VALUE = string1;
//												NEW_VALUE = string1;
//												try {
//													SET_NUMBER = Integer
//															.parseInt(HtmlAnalyze.getTagText(string1, "���Ϊ", "��"));
//												} catch (Exception e) {
//													// TODO: handle exception
//												}
//											}
//											if (x == 2) {
//												PRODUCE_COMPANY = string1;
//												System.out.println(OLD_VALUE + NEW_VALUE + TVPLAY_NAME + PRODUCE_COMPANY
//														+ SET_NUMBER + TYPE);
//												OracleSarFtGov.intotemtvplaychange(OLD_VALUE, NEW_VALUE, TVPLAY_NAME,
//														PRODUCE_COMPANY, SET_NUMBER, TYPE);
//											}
//
//											// System.out.println(element3.text());
//
//											x += 1;
//										}
//									}
//								}
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//						}
//					}
//					i += 1;
//				}
//
//			}
//		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

	}

	private static String textnull(String text) {
		try {

			while (text.contains("����")) {
				text = text.replaceAll("����", "��");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return text;

	}

}
