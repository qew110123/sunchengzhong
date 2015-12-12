package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

/**
 * 进行数据的添加操作 进行广电总局数据的添加
 * 
 * @author Administrator
 *
 */
public class OracleSarFtGov {

	public static void intoShanty(String tyPlayName, String gdsubjectname, String producecompany, String producedate,
			String producecycle, String setnumber, String reportcompany, String projectdate, String shootinglicense,
			String genre, String provinceopinion, String departmentopinion, String remark, String tvplayurl,
			String tvplaycontent, String tvplayarea) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(tyPlayName + gdsubjectname + producecompany + producedate + producecycle + setnumber
				+ reportcompany + projectdate + shootinglicense + genre + provinceopinion + departmentopinion + remark
				+ tvplayurl + tvplaycontent + tvplayarea);
		String strSql = "insert into ods.tem_TVPLAY_PROJECT t (t.tvplay_name,t.gd_subject_name,t.produce_company,"
				+ "t.produce_date,t.produce_cycle,t.set_number,t.report_company,t.project_date,t.shooting_license,"
				+ "t.genre,t.province_opinion,t.department_opinion,t.remark,t.create_date,t.tvplay_url,t.tvplay_content,t.tvplay_area)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(gdsubjectname);
		list.add(producecompany);
		list.add(producedate);
		list.add(producecycle);
		list.add(Integer.parseInt(setnumber));
		list.add(reportcompany);
		list.add(projectdate);
		list.add(shootinglicense);
		list.add(genre);
		list.add(provinceopinion);
		list.add(departmentopinion);
		list.add(remark);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tvplayurl);
		list.add(tvplaycontent);
		list.add(tvplayarea);
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 广电总局数据的采集 1 立项 2 摄制
	 * 
	 * @param tyPlayName
	 * @param gdsubjectname
	 * @param setnumber
	 * @param reportcompany
	 * @param projectdate
	 * @param shootinglicense
	 * @param provinceopinion
	 * @param adminopinion
	 * @param tvplaycontent
	 * @param remark
	 * @param createdate
	 * @param type
	 * @param producedate
	 * @param producecycle
	 */
	public static void intosubjectproject(String tyPlayName, String gdsubjectname, String setnumber,
			String reportcompany, String projectdate, String shootinglicense, String provinceopinion,
			String adminopinion, String tvplaycontent, String remark, String createdate, String type,
			String producedate, String producecycle) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(
				tyPlayName + gdsubjectname + setnumber + reportcompany + projectdate + shootinglicense + provinceopinion
						+ adminopinion + tvplaycontent + remark + createdate + type + producedate + producecycle);
		String strSql = "insert into ods.TEM_SUBJECT_PROJECT t values(null,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		// list.add(null);// 这里是将对象加入到list中
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(gdsubjectname);
		list.add(Integer.parseInt(setnumber));
		list.add(reportcompany);
		list.add(projectdate);
		list.add(shootinglicense);
		list.add(provinceopinion);
		list.add(adminopinion);
		list.add(tvplaycontent);
		list.add(remark);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(Integer.parseInt(type));
		list.add(producedate);
		list.add(producecycle);
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	public static void intotemtvplaychange(String old_value, String new_value, String tvplay_name,
			String produce_company, int set_number, int type) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(old_value + new_value + tvplay_name + produce_company + set_number + type );
		String strSql = "insert into ods.TEM_TVPLAY_CHANGE t (t.old_value,t.new_value,t.tvplay_name,t.produce_company,"
				+ "t.set_number,t.type,t.create_date)values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
		List<Comparable> list = new ArrayList();
		// list.add(null);// 这里是将对象加入到list中
		list.add(old_value);
		list.add(new_value);
		list.add(tvplay_name);
		list.add(produce_company);
		list.add(set_number);
		list.add(type);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 添加动漫
	 * @param ANIMATION_NAME
	 * @param ANIMATION_URL
	 * @param AUTHOR
	 * @param AREA
	 * @param STATE
	 * @param POPULARITY
	 * @param SUBJECT_NAME
	 * @param ANIMATION_CATEGORY
	 * @param IN_DATE
	 * @param SOURCE
	 */
	public static void intotem_animation(String ANIMATION_NAME, String ANIMATION_URL, String AUTHOR, String AREA,
			String STATE, String POPULARITY, String SUBJECT_NAME, String ANIMATION_CATEGORY, String IN_DATE,
			String SOURCE,String CLICK_NUM,String SCORE) {
		Connection conn = DBOperate218.getInstance().getConnection();
		// System.out.println(old_value + new_value + tvplay_name +
		// produce_company + set_number + type);
		String strSql = "insert into ods.tem_animation t (ANIMATION_NAME,ANIMATION_URL,AUTHOR,AREA,STATE,POPULARITY,SUBJECT_NAME,ANIMATION_CATEGORY,IN_DATE,SOURCE,CLICK_NUM,SCORE)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		// list.add(null);// 这里是将对象加入到list中
		list.add(ANIMATION_NAME);
		list.add(ANIMATION_URL);
		list.add(AUTHOR);
		list.add(AREA);
		list.add(STATE);
		list.add(Integer.parseInt(POPULARITY));
		list.add(SUBJECT_NAME);
		list.add(ANIMATION_CATEGORY);
		list.add(IN_DATE);
		list.add(SOURCE);
		list.add(CLICK_NUM);
		list.add(SCORE);

//		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	public static void main(String[] args) {
		intosubjectproject("你好", "你好", "33", "你好", "你好", "你好", "你好", "你好", "你好", "你好", "", "1", "你好", "你好");
	}

}
