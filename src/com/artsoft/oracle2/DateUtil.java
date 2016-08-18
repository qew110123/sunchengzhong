package com.artsoft.oracle2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	private final static SimpleDateFormat sdfDaySpe = new SimpleDateFormat(
			"yyyy-M-d");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	/**
	 * 两个日期相隔多少年
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(int days) {
    	
    	Calendar canlendar = Calendar.getInstance(); // java.util包
    	canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
    	Date date = canlendar.getTime();
    	
    	SimpleDateFormat sdfd = new SimpleDateFormat("yyyyMMdd");
    	String dateStr = sdfd.format(date);
    	
    	return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    public static void main(String[] args) {
    	System.out.println(getDays());
    	System.out.println(getAfterDayWeek("3"));
    }
    
    /**
     * 
     * 描述：将源字符串中的汉字格式化为阿拉伯数字
     * @param sign 源字符串中的字符
     * @return
     */
   public static String formatDigit(String sign){
       if("零".equals(sign) || "０".equals(sign))
           sign = "0";
       if("一".equals(sign)|| "１".equals(sign))
           sign = "1";
       if("二".equals(sign)|| "２".equals(sign))
           sign = "2";
       if("三".equals(sign) || "３".equals(sign))
           sign = "3";
       if("四".equals(sign) || "４".equals(sign))
           sign = "4";
       if("五".equals(sign)|| "５".equals(sign))
           sign = "5";
       if("六".equals(sign)|| "６".equals(sign))
           sign = "6";
       if("七".equals(sign)|| "７".equals(sign))
           sign = "7";
       if("八".equals(sign)|| "８".equals(sign))
           sign = "8";
       if("九".equals(sign)|| "９".equals(sign))
           sign = "9";
       return sign;
   }
   /**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static Date getNextDay(Date nowdate, int delay) {
		try {
			long myTime = (nowdate.getTime() / 1000) + delay * 24
					* 60 * 60;
			nowdate.setTime(myTime * 1000);
			return nowdate;
		} catch (Exception e) {
			return nowdate;
		}
	}
	public static String getNextDayStr(Date nowdate, int delay) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long myTime = (nowdate.getTime() / 1000) + delay * 24
					* 60 * 60;
			nowdate.setTime(myTime * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdf.format(nowdate);
	}

	public static String[] getDates(Date date, String dateSel, String startDate, String endDate) {
		try {

			if(StringUtil.isEmpty(dateSel)) {
				return null;
			}
			
			if(dateSel.equals("999")) {
				if(StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)){
					return null;		
				}
			}else{
				
				Calendar cal = Calendar.getInstance();
				cal.setFirstDayOfWeek(Calendar.SUNDAY);
				cal.setTime(date);
				
				endDate = sdfDay.format(cal.getTime());
				int month = cal.get(Calendar.MONTH);

				if (dateSel.equals("0")) {//最近一天(从数据库中获取)
					return new String[] { endDate, endDate };
				} else if (dateSel.equals("1")) {//最近一周
					cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				} else if (dateSel.equals("2")) {//最近一月
					cal.set(Calendar.DAY_OF_MONTH, 1);
				} else if (dateSel.equals("3")) {//最近一季

					if (month <= Calendar.MARCH) {
						cal.set(Calendar.MONTH, Calendar.JANUARY);
						cal.set(Calendar.DAY_OF_MONTH, 1);
					} else if (month <= Calendar.JUNE) {
						cal.set(Calendar.MONTH, Calendar.APRIL);
						cal.set(Calendar.DAY_OF_MONTH, 1);
					} else if (month <= Calendar.SEPTEMBER) {
						cal.set(Calendar.MONTH, Calendar.JULY);
						cal.set(Calendar.DAY_OF_MONTH, 1);
					} else {
						cal.set(Calendar.MONTH, Calendar.OCTOBER);
						cal.set(Calendar.DAY_OF_MONTH, 1);
					}
				} else if (dateSel.equals("4")) {//最近半年

					if (month <= Calendar.JUNE) {
						cal.set(Calendar.MONTH, Calendar.JANUARY);
						cal.set(Calendar.DAY_OF_MONTH, 1);
					} else {
						cal.set(Calendar.MONTH, Calendar.JULY);
						cal.set(Calendar.DAY_OF_MONTH, 1);
					}
				} else if (dateSel.equals("5")) {//最近一年
					cal.set(Calendar.DAY_OF_YEAR, 1);
				}
				startDate = sdfDay.format(cal.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] { startDate, endDate };	
	}
	
	
	public static String[] getSpecialDates(Date date, String dateSel, String startDate, String endDate) {
		try {

			if(StringUtil.isEmpty(dateSel)) {
				return null;
			}
			
			if(dateSel.equals("999")) {
				if(StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)){
					return null;		
				}
			}else{
				
				Calendar cal = Calendar.getInstance();
				cal.setFirstDayOfWeek(Calendar.SUNDAY);
				cal.setTime(date);
				
				endDate = sdfDay.format(cal.getTime());
				int month = cal.get(Calendar.MONTH);
				if (dateSel.equals("0")) {//最近一天(从数据库中获取)
					return new String[] { endDate, endDate };
				} else if (dateSel.equals("1")) {//最近一周
					cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				} else if (dateSel.equals("2")) {//最近一月
					cal.add(Calendar.MONTH,1);
					cal.set(Calendar.DAY_OF_MONTH, 0);
					endDate = sdfDay.format(cal.getTime());
					cal.set(Calendar.DAY_OF_MONTH, 1);
				} else if (dateSel.equals("3")) {//最近一季
					cal.setTime(CalendarUtil.getThisSeasonFirstTime(date));
					endDate = sdfDay.format(CalendarUtil.getThisSeasonFinallyTime(date));
				} else if (dateSel.equals("4")) {//最近半年
					cal.setTime(CalendarUtil.getHalfYearStartTime(date));
					endDate = sdfDay.format(CalendarUtil.getHalfYearEndTime(date));
				} else if (dateSel.equals("5")) {//最近一年
					cal.set(Calendar.DAY_OF_YEAR, 1);
					endDate = CalendarUtil.getCurrentYearEnd(date);
				}
				startDate = sdfDay.format(cal.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] { startDate, endDate };	
	}
	
	/**
	 * 格式要求 2014-2-1
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static StringBuilder getDateStr(String startDate,String endDate){
		StringBuilder fileNames = new StringBuilder();
		Calendar cal = Calendar.getInstance();  
		try {
			Date dBegin = sdfDaySpe.parse(startDate);
			Date dEnd = sdfDaySpe.parse(endDate); 
	        List<Date> lDate = findDates(dBegin, dEnd);  
	        for (int j = 0; j < lDate.size(); j++) {
	        	if(fileNames.length() == 0){
					fileNames.append(sdfDaySpe.format(lDate.get(j)));
				}else{
					fileNames.append(",").append(sdfDaySpe.format(lDate.get(j)));
				}
//	            System.out.println(sdf.format(date));  
	        } 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileNames;
	}
	/**
	 * 格式要求 2014-02-01
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static StringBuilder getDateFullStr(String startDate,String endDate){
		StringBuilder fileNames = new StringBuilder();
		Calendar cal = Calendar.getInstance();  
		try {
			Date dBegin = sdfDay.parse(startDate);
			Date dEnd = sdfDay.parse(endDate); 
			List<Date> lDate = findDates(dBegin, dEnd);  
			for (int j = 0; j < lDate.size(); j++) {
				if(fileNames.length() == 0){
					fileNames.append(sdfDay.format(lDate.get(j)));
				}else{
					fileNames.append(",").append(sdfDay.format(lDate.get(j)));
				}
//	            System.out.println(sdf.format(date));  
			} 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileNames;
	}
	/**
	 * 格式要求 2014-02-01
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static StringBuilder getSpeDateStr(String startDate,String endDate){
		StringBuilder dateNames = new StringBuilder();
		Calendar cal = Calendar.getInstance();  
		try {
			Date dBegin = sdfDays.parse(startDate);
			Date dEnd = sdfDays.parse(endDate); 
			List<Date> lDate = findDates(dBegin, dEnd);  
			for (int j = 0; j < lDate.size(); j++) {
				if(dateNames.length() == 0){
					dateNames.append(sdfDays.format(lDate.get(j)));
				}else{
					dateNames.append(",").append(sdfDays.format(lDate.get(j)));
				}
//	            System.out.println(sdf.format(date));  
			} 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateNames;
	}
  
    public static List<Date> findDates(Date dBegin, Date dEnd) {  
        List lDate = new ArrayList();  
        lDate.add(dBegin);  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后    
        while (dEnd.after(calBegin.getTime())) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
            lDate.add(calBegin.getTime());  
        }  
        return lDate;  
    }
}
