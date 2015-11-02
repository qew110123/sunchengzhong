package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

public class OracleOpreater {

//	static Connection conn = DBOperate.getInstance().getConnection();

	/**
	 * 
	 * @param tyPlayName
	 * @param source
	 * @param dataAmount
	 * @param vodeoType
	 * @param upDatedate
	 * @param playUrl
	 * @param tvType
	 * @param dataType
	 */
	public static void intoReputation(String tyPlayName, String source, String dataAmount, String vodeoType,
			String upDatedate, String playUrl, String tvType, String dataType) {
		 Connection conn = DBOperate.getInstance().getConnection();

		System.out.println(tyPlayName + source + dataAmount + vodeoType + upDatedate + playUrl + tvType + dataType);
		String strSql = "INSERT INTO TEM_NETWORK_REPUTATION t (t.TVPLAY_NAME,t.SOURCE ,t.DATA_AMOUNT,t.VIDEO_TYPE,t.UPDATE_DATE"
				+ ",t.PLAY_URL,t.TV_TYPE,t.DATA_TYPE) VALUES (?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss') ,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(dataAmount));
		list.add(Integer.parseInt(vodeoType));
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(Integer.parseInt(dataType));
		boolean bb=DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);

	}

	/**
	 * 
	 * @param tyPlayName
	 * @param serNumber
	 * @param source
	 * @param playAmount
	 * @param vodeoType
	 * @param palydate
	 * @param playUrl
	 * @param tvType
	 * @param realUrl
	 */
	public static void intoPlayAmont(String tyPlayName, String serNumber, String source, String playAmount,
			String vodeoType, String palydate, String playUrl, String tvType, String realUrl) {
		Connection conn = DBOperate.getInstance().getConnection();
		System.out.println(
				tyPlayName + serNumber + source + playAmount + vodeoType + palydate + playUrl + tvType + realUrl);
		
		String strSql = "INSERT INTO TEM_NETWORK_PLAY_AMOUNT t (t.TVPLAY_NAME,t.SET_NUMBER "
				+ ",t.SOURCE,t.PLAY_AMOUNT,t.VIDEO_TYPE,t.PLAY_DATE,t.PLAY_URL,t.TV_TYPE,t.REAL_URL) "
				+ "VALUES (?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss') "
				+ ",?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(serNumber));
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(playAmount));
		list.add(Integer.parseInt(vodeoType));
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(realUrl);
		boolean bb=DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

}
