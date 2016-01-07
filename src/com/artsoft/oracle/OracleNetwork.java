package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OracleNetwork {
	/**
	 * 网络数据的上百度百科查询
	 * 2016年1月6日16:55:51
	 * @return
	 */
	public static List selectbaidudianshiju() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_name  from ODS.TEM_NETWORK_REPUTATION t where  t.tv_type=1 and t.date_date='20160106'  group by t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

}
