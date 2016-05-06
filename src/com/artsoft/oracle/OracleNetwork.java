package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

public class OracleNetwork {
	/**
	 * 网络数据的上百度百科查询
	 * 2016年1月6日16:55:51
	 * @return
	 */
	public static List selectbaidudianshiju(String timesday) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_name  from ODS.TEM_NETWORK_REPUTATION t where  t.tv_type=1 and t.date_date='"+timesday+"'  group by t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * 进行腾讯视频数据的再次清洗
	 * 2016年4月21日17:58:08
	 * @return
	 */
	
	public static List selectqqTVplay(String time) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = " select t.detail_url, t.tvplay_name, t.play_url    from ODS.TEM_NETWORK_REPUTATION t,        (select tt1.tvplay_name           from (select t1.tvplay_name, t1.date_date, count(*)                   from ODS.TEM_NETWORK_REPUTATION t1                  where t1.date_date = '"+time+"'                    and t1.data_type = 1and t1.tv_type = 0                    and t1.source = 3                  group by t1.tvplay_name, t1.date_date) tt1           FULL JOIN (select t1.tvplay_name, t1.date_date, count(*)                       from ODS.TEM_NETWORK_REPUTATION t1                      where t1.date_date = '"+time+"'                        and t1.data_type = 0and t1.tv_type = 0                        and t1.source = 3                      group by t1.tvplay_name, t1.date_date) tt2             ON tt1.tvplay_name = tt2.tvplay_name          where tt2.tvplay_name is null) tt3  where t.date_date = '"+time+"'    and t.data_type = 1and t.tv_type = 0    and t.source = 3    and t.tvplay_name in tt3.tvplay_name  group by t.tvplay_name, t.play_url, t.detail_url";

//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, strSql, iNum);
		return (ArrayList<String>) list;
	}



	public static List<String> selectyoukumovie(String nowTime) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = " select t.detail_url, t.tvplay_name, t.play_url   from ODS.TEM_NETWORK_REPUTATION t,"
				+ " (select tt1.tvplay_name           from (select t1.tvplay_name, t1.date_date, count(*)                  "
				+ " from ODS.TEM_NETWORK_REPUTATION t1                  where t1.date_date = '"+nowTime+"'                    and t1.data_type = 0and t1.tv_type = 3                    and t1.source = 1                  group by t1.tvplay_name, t1.date_date) tt1           FULL JOIN (select t1.tvplay_name, t1.date_date, count(*)                       from ODS.TEM_NETWORK_REPUTATION t1                      where t1.date_date = '"+nowTime+"'                        and t1.data_type = 1and t1.tv_type = 3                        and t1.source = 1                      group by t1.tvplay_name, t1.date_date) tt2             ON tt1.tvplay_name = tt2.tvplay_name          where tt2.tvplay_name is null) tt3  where t.date_date = '"+nowTime+"'    and t.data_type = 0and t.tv_type = 3    and t.source = 1    and t.tvplay_name in tt3.tvplay_name  group by t.tvplay_name, t.play_url, t.detail_url";
//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, strSql, iNum);
		return (ArrayList<String>) list;
	}



	public static List<String> selectqqMovie(String nowTime) {
		// TODO Auto-generated method stub
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "select t.detail_url, t.tvplay_name, t.play_url   from ODS.TEM_NETWORK_REPUTATION t,        (select tt1.tvplay_name           from (select t1.tvplay_name, t1.date_date, count(*)                   from ODS.TEM_NETWORK_REPUTATION t1                  where t1.date_date = '"+nowTime+"'                    and t1.data_type = 1and t1.tv_type = 3                    and t1.source = 3                  group by t1.tvplay_name, t1.date_date) tt1           FULL JOIN (select t1.tvplay_name, t1.date_date, count(*)                       from ODS.TEM_NETWORK_REPUTATION t1                      where t1.date_date = '"+nowTime+"'                        and t1.data_type = 0and t1.tv_type = 3                        and t1.source = 3                      group by t1.tvplay_name, t1.date_date) tt2             ON tt1.tvplay_name = tt2.tvplay_name          where tt2.tvplay_name is null) tt3  where t.date_date = '"+nowTime+"'    and t.data_type = 1and t.tv_type = 3    and t.source = 3    and t.tvplay_name in tt3.tvplay_name  group by t.tvplay_name, t.play_url, t.detail_url";
		//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, strSql, iNum);
		return (ArrayList<String>) list;
	}

}
