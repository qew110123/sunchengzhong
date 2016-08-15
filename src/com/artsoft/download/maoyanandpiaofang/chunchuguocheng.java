package com.artsoft.download.maoyanandpiaofang;

import java.sql.CallableStatement;

import com.artsoft.oracle2.DBManager;

public class chunchuguocheng {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBManager dbm = DBManager.instance();

		//尹海龙-研发部 2016/8/11 14:35:37
		//dbm.executeCall("{ call sp_f_tvplay0_delday(?, ?) }");
		
//		dbm.executeSQL("select PUBLIC_NUMBER,wechat_number from ODS.WEIXIN_NUMBER");
//		
//		
//		dbm.executeSQL("INSERT INTO ODS.WEIXIN_NUMBER (PUBLIC_NUMBER, wechat_number) VALUES ('11', '2222')");
//		System.out.println(dbm);
		dbm.executeCall("{  dwetl.sp_f_film_boxoffice_realtime(20160811) }");
//		dbm.execute();
		System.out.println(dbm);
		
//		dbm.executeCall("{ dwetl.MART_F_FILM_INDEX(temp_time);  }");
//		System.out.println(dbm);
//		
//		CallableStatement proc = dbm.getConnection().prepareCall("{ call sp_f_tvplay0_delday(?, ?) }");
////		proc.setString(1, tvstarttime);
////		proc.setString(2, tvendtime);
//		proc.execute();
		
//		CallableStatement proc = dbm.executeCall("{ call sp_f_tvplay0_delday(?, ?) }");
		
	}

}
