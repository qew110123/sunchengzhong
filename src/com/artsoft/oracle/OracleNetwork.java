package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

public class OracleNetwork {
	/**
	 * �������ݵ��ϰٶȰٿƲ�ѯ
	 * 2016��1��6��16:55:51
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
	 * �������ݵ�������������
	 * 2017��4��12��16:22:39
	 * @return
	 */
	public static List selectdouban() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_name from ODS.DEL_TVPLAY_NAME_TEM t where t.type = 3 group by t.tvplay_name";
		
		sql="select t.tvplay_name   from ODS.DEL_TVPLAY_NAME_TEM t  where t.type = 3    and (t.label_name like '%���%' or t.label_name like '%Ѱ��%')  group by t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * ������Ѷ��Ƶ���ݵ��ٴ���ϴ
	 * 2016��4��21��17:58:08
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

		String strSql = " select t.detail_url, t.tvplay_name, t.play_url   from ODS.TEM_NETWORK_REPUTATION t, (select tt1.tvplay_name           from (select t1.tvplay_name, t1.date_date, count(*)  from ODS.TEM_NETWORK_REPUTATION t1                  where t1.date_date = '"+nowTime+"'                    and t1.data_type = 0and t1.tv_type = 3                    and t1.source = 1                  group by t1.tvplay_name, t1.date_date) tt1           FULL JOIN (select t1.tvplay_name, t1.date_date, count(*)                       from ODS.TEM_NETWORK_REPUTATION t1                      where t1.date_date = '"+nowTime+"'                        and t1.data_type = 1and t1.tv_type = 3                        and t1.source = 1                      group by t1.tvplay_name, t1.date_date) tt2             ON tt1.tvplay_name = tt2.tvplay_name  group by tt1.tvplay_name ) tt3  where t.date_date = '"+nowTime+"'    and t.data_type = 0and t.tv_type = 3    and t.source = 1    and t.tvplay_name in tt3.tvplay_name  group by t.tvplay_name, t.play_url, t.detail_url";
//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, strSql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	public static List<String> selectIqiyiWangju() {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "select t.networkplay_id,t.networkplay_name from edw.dim_networkplay t";
//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, strSql, iNum);
		return (ArrayList<String>) list;
	}

	
	
	public static List<String> selectleshimovie(String nowTime) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = " select t.detail_url, t.tvplay_name, t.play_url   from ODS.TEM_NETWORK_REPUTATION t, (select tt1.tvplay_name           from (select t1.tvplay_name, t1.date_date, count(*)  from ODS.TEM_NETWORK_REPUTATION t1                  where t1.date_date = '"+nowTime+"'                    and t1.data_type = 0and t1.tv_type = 3                    and t1.source = 5                  group by t1.tvplay_name, t1.date_date) tt1           FULL JOIN (select t1.tvplay_name, t1.date_date, count(*)                       from ODS.TEM_NETWORK_REPUTATION t1                      where t1.date_date = '"+nowTime+"'                        and t1.data_type = 1and t1.tv_type = 3                        and t1.source = 5                      group by t1.tvplay_name, t1.date_date) tt2             ON tt1.tvplay_name = tt2.tvplay_name  group by tt1.tvplay_name ) tt3  where t.date_date = '"+nowTime+"'    and t.data_type = 0and t.tv_type = 3    and t.source = 5    and t.tvplay_name in tt3.tvplay_name  group by t.tvplay_name, t.play_url, t.detail_url";
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
		strSql = " select t.detail_url, t.tvplay_name, t.play_url   from ODS.TEM_NETWORK_REPUTATION t, (select tt1.tvplay_name           from (select t1.tvplay_name, t1.date_date, count(*)  from ODS.TEM_NETWORK_REPUTATION t1                  where t1.date_date >'"+nowTime+"'                   and t1.data_type = 0and t1.tv_type = 3                    and t1.source = 3                 group by t1.tvplay_name, t1.date_date) tt1           FULL JOIN (select t1.tvplay_name, t1.date_date, count(*)                       from ODS.TEM_NETWORK_REPUTATION t1                      where t1.date_date >'"+nowTime+"'                             and t1.data_type = 1and t1.tv_type = 3                        and t1.source = 3                      group by t1.tvplay_name, t1.date_date) tt2             ON tt1.tvplay_name = tt2.tvplay_name  group by tt1.tvplay_name ) tt3  where t.date_date >'"+nowTime+"'          and t.data_type = 0and t.tv_type = 3    and t.source = 3    and t.tvplay_name in tt3.tvplay_name  group by t.tvplay_name, t.play_url, t.detail_url";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, strSql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �����ſ����ݽ��ж������� 
	 * 2016��5��27��16:56:31
	 * @return
	 */
	public static List selectyoukuTVplay(String date_date) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source=1 and t.tv_type=0 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url";
		sql=" select t.tvplay_name, t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source=1 and t.tv_type=0 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url,t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * �����ſ����ݽ��ж�������  
	 * Ƭ��
	 *	2016��11��10��11:38:28
	 * @return
	 */
	public static List selectyoukuTVplay_pianhua() {
		Connection conn = DBOperate218.getInstance().getConnection();
//		String sql = "select t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source=1 and t.tv_type=0 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url";
		String sql=" select t.tvplay_id,t.tvplay_name from edw.f_tvplay_record t where t.project_date_back >='20160306' order by t.project_date_back desc";
		sql=" select to_char(t.tvplay_id),t.tvplay_name from edw.f_tvplay_record t where t.project_date_back >='20160306' order by t.project_date_back desc";
		sql="select to_char(t.tvplay_id),t.tvplay_name from edw.f_tvplay_record t where t.project_date_back >='20130306' order by t.project_date_back desc";
		sql="select to_char(t.tvplay_id),t.tvplay_name,t.tvplay_url from EDW.F_TVPLAY_RECORD t where t.project_date_back >='20160901' ";
		
		sql="select to_char(t.tvplay_id),t.tvplay_name,t.tvplay_url from EDW.F_TVPLAY_RECORD t where t.project_date_back >='20160901' ";
//		sql="select to_char(t.tvplay_id),t.tvplay_name from edw.f_tvplay_record t where t.project_date_back >='20130306' and t.tvplay_id not in(select t.tvplay_id from ODS.TEM_TVPLAY_TIDBITS t where data_date ='20161110' group by t.tvplay_id) order by t.project_date_back desc";
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.F_TVPLAY_RECORD t where t.tvplay_name = '���Ӵ���'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * �����ſ����ݽ��ж������� 
	 * 2016��5��30��17:43:14
	 * @return
	 */
	public static List selectyoukuvariety(String date_date, String source) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source="+source+" and t.tv_type=2 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	
	/**
	 * �������ݽ��ж������� 
	 * 2016��9��8��16:49:17
	 * @return
	 */
	public static List selectTVplayorder(String date_date,String source) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source=1 and t.tv_type=0 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url";
		sql=" select t.tvplay_name, t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source="+source+" and t.tv_type=0 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url,t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �������ݽ��ж������� 
	 * 2016��9��8��16:49:17
	 * @return
	 */
	public static List selectTVplayorder_tv_type(String date_date,String source) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source=1 and t.tv_type=0 and t.data_type=0 and t.date_date>'"+date_date+"' group by t.detail_url";
		sql=" select t.tvplay_name, t.detail_url from ODS.TEM_NETWORK_REPUTATION t where t.source="+source+" and t.tv_type=0 and t.data_type=1 and t.date_date>'"+date_date+"' group by t.detail_url,t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

}
