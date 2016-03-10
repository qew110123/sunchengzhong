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
    * @功能：手工构建一个简单格式的Excel 
    */  
//   private static List<Student> getStudent() throws Exception  
//   {  
//       List list = new ArrayList();  
//       SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
// 
//       Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));  
//       Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));  
//       Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));  
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
//	      Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));  
//	      Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));  
//	      Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));  
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
	       // 第一步，创建一个webbook，对应一个Excel文件  
	       HSSFWorkbook wb = new HSSFWorkbook();  
	       // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	       HSSFSheet sheet = wb.createSheet("排名");  
	       // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	       HSSFRow row = sheet.createRow((int) 0);  
	       // 第四步，创建单元格，并设置值表头 设置表头居中  
	       HSSFCellStyle style = wb.createCellStyle();  
	       style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	 
	       HSSFCell cell = row.createCell((short) 0);  
	       cell.setCellValue("名次");  
	       cell.setCellStyle(style);  
	       cell = row.createCell((short) 1);  
	       cell.setCellValue("电视剧");  
	       cell.setCellStyle(style);  
	       cell = row.createCell((short) 2);  
	       cell.setCellValue("播放量");  
	       cell.setCellStyle(style);  
//	       cell = row.createCell((short) 3);  
//	       cell.setCellValue("生日");  
//	       cell.setCellStyle(style);  
	 
	       // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
	       List list = getccc();
	 
	       for (int i = 0; i < list.size(); i++)  
	       {  
	           row = sheet.createRow((int) i + 1);  
	           ccc stu = (ccc) list.get(i);  
	           // 第四步，创建单元格，并设置值  
	           row.createCell((short) 0).setCellValue(stu.getMingci());  
	           row.createCell((short) 1).setCellValue(stu.getDianshiju());  
	           row.createCell((short) 2).setCellValue(stu.getBofangliang());  
	           cell = row.createCell((short) 3);  
//	           cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu  
//	                   .getBirth()));  
	       }  
	       
	       // 第六步，将文件存到指定位置  
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
 
   
	// 判断数据开始时间
		public static void TimingTime(int hh, int mm, int ss) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
			calendar.set(Calendar.MINUTE, mm); // 控制分
			calendar.set(Calendar.SECOND, ss); // 控制秒

			Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					System.out.println("-------设定要指定任务--------");
					try {
						runstatic();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
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
