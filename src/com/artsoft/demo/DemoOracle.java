package com.artsoft.demo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.oracle.DBOperate;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.TimeTest;

public class DemoOracle {
	public void name() {
		Connection conn = DBOperate.getInstance().getConnection();
		String strSql="INSERT INTO TEM_NETWORK_REPUTATION t (t.TVPLAY_ID,t.TVPLAY_NAME,t.SOURCE ,t.DATA_AMOUNT,t.VIDEO_TYPE,t.UPDATE_DATE"
				+ ",t.PLAY_URL,t.TV_TYPE,t.DATA_TYPE) VALUES (?,?,1,10000,0,to_date('2009-12-25 14:23:31','yyyy-mm-dd hh24:mi:ss') ,'http://baidu.com',1,1)";
		
		List list=new ArrayList();
		list.add(1);//这里是将对象加入到list中
		list.add("封神榜");
		DBOperate.insertRecord(conn, strSql, list);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OracleOpreater.intoReputation("电视剧1", "1", "9.9", "0", "", "baidu.com", "0", "1");
//		OracleOpreater.intoPlayAmont("电视剧", "3", "1", "9.9", "0", "", "baidu.com", "0", "baiu.com");
//		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		
		

	}

}
