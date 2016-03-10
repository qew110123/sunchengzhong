package com.artsoft.download.ccc;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.ccc;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;  

public class cccc {

	 /** 
    * @���ܣ��ֹ�����һ���򵥸�ʽ��Excel 
    */  
//   private static List<Student> getStudent() throws Exception  
//   {  
//       List list = new ArrayList();  
//       SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
// 
//       Student user1 = new Student(1, "����", 16, df.parse("1997-03-12"));  
//       Student user2 = new Student(2, "����", 17, df.parse("1996-08-12"));  
//       Student user3 = new Student(3, "����", 26, df.parse("1985-11-12"));  
//       list.add(user1);  
//       list.add(user2);  
//       list.add(user3);  
// 
//       return list;  
//   }  
	
	
	private static List<ccc> getccc() throws Exception  {
	      List list = new ArrayList();  
//	      SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
	//
//	      Student user1 = new Student(1, "����", 16, df.parse("1997-03-12"));  
//	      Student user2 = new Student(2, "����", 17, df.parse("1996-08-12"));  
//	      Student user3 = new Student(3, "����", 26, df.parse("1985-11-12"));  
//	      list.add(user1);  
//	      list.add(user2);  
//	      list.add(user3);  
	      
	      String strHtml = "";
			boolean bb = true;
			while (bb) {
				strHtml = DownloadUtil.getHtmlText("http://www.xunyee.cn/rank-teleplay-play-0.html", 1000 * 30, "UTF-8", null, null);
				if (strHtml != null && !"".equals(strHtml)) {
					bb = false;
				}
			}
			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.yesterdaytab a.rank_left_lib");
			String paiming="";
			String dianshiju="";
			String bofangliang="";
			String time="";
			for (Element element : links) {
				
				System.out.println(paiming=element.select("span.rank_left_rank").text());
				System.out.println(dianshiju=element.select("span.rank_left_name").first().text());
				System.out.println(bofangliang=element.select("span.rank_left_value").first().text());
//				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
				System.out.println(time=TimeTest.getNowTime("yyyy-MM-dd"));
				CommonUtil.setLog(paiming+"	"+dianshiju+"	"+bofangliang+"	"+time);
				ccc c=new ccc(paiming,dianshiju,bofangliang);
				list.add(c);
				
			}
	//
	      return list;  
			
		}
	
	
	
	public static void runstatic() throws Exception  
	   {  
	       // ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
	       HSSFWorkbook wb = new HSSFWorkbook();  
	       // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
	       HSSFSheet sheet = wb.createSheet("����");  
	       // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
	       HSSFRow row = sheet.createRow((int) 0);  
	       // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
	       HSSFCellStyle style = wb.createCellStyle();  
	       style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ  
	 
	       HSSFCell cell = row.createCell((short) 0);  
	       cell.setCellValue("����");  
	       cell.setCellStyle(style);  
	       cell = row.createCell((short) 1);  
	       cell.setCellValue("���Ӿ�");  
	       cell.setCellStyle(style);  
	       cell = row.createCell((short) 2);  
	       cell.setCellValue("������");  
	       cell.setCellStyle(style);  
//	       cell = row.createCell((short) 3);  
//	       cell.setCellValue("����");  
//	       cell.setCellStyle(style);  
	 
	       // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���  
	       List list = getccc();
	 
	       for (int i = 0; i < list.size(); i++)  
	       {  
	           row = sheet.createRow((int) i + 1);  
	           ccc stu = (ccc) list.get(i);  
	           // ���Ĳ���������Ԫ�񣬲�����ֵ  
	           row.createCell((short) 0).setCellValue(stu.getMingci());  
	           row.createCell((short) 1).setCellValue(stu.getDianshiju());  
	           row.createCell((short) 2).setCellValue(stu.getBofangliang());  
	           cell = row.createCell((short) 3);  
//	           cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu  
//	                   .getBirth()));  
	       }  
	       
	       // �����������ļ��浽ָ��λ��  
	       try  
	       {  
//	           FileOutputStream fout = new FileOutputStream("D:/students.xls");  
	           //TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")
	           FileOutputStream fout = new FileOutputStream("D:/ccc/"+TimeTest.getNowTime("yyyy-MM-dd")+".xls");  
	           wb.write(fout);  
	           fout.close();  
	       }  
	       catch (Exception e)  
	       {  
	           e.printStackTrace();  
	       }  
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
					try {
						runstatic();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
//			for (int i = 0; i < 30; i++) {
//				String mainUrl = "http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=" + i + "&s=1";
//				mainurl(mainUrl);
			
//			}
//			TimingTime(23, 59, 59);
			TimingTime(11, 59, 59);
//			runstatic();
		}

}
