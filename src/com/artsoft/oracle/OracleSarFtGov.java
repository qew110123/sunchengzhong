package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.Persion;
import com.artsoft.bean.TvPlay;
import com.artsoft.util.TimeTest;


/**
 * 进行数据的添加操作
 * 进行广电总局数据的添加
 * @author Administrator
 *
 */
public class OracleSarFtGov {

	public static void intoShanty(String tyPlayName, String gdsubjectname, String producecompany, String producedate,
			String producecycle, String setnumber, String reportcompany, String projectdate, String shootinglicense,
			String genre , String provinceopinion ,String departmentopinion ,String remark,String tvplayurl,String tvplaycontent ,String tvplayarea) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_TVPLAY_PROJECT t (t.tvplay_name,t.gd_subject_name,t.produce_company,"
				+ "t.produce_date,t.produce_cycle,t.set_number,t.report_company,t.project_date,t.shooting_license,"
				+ "t.genre,t.province_opinion,t.department_opinion,t.remark,t.create_date,t.tvplay_url,t.tvplay_content,t.tvplay_area)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(gdsubjectname);
		list.add(producecompany);
		list.add(producedate);
		list.add(Integer.parseInt(setnumber));
		list.add(reportcompany);
		list.add(projectdate);
		list.add(shootinglicense);
		list.add(genre);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(provinceopinion);
		list.add(departmentopinion);
		list.add(tvplayarea);
		boolean bb=DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	

}
