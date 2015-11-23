package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

public class OracleOpreater {

//	static Connection conn = DBOperate.getInstance().getConnection();

	/**
	 * 每一个电视剧的数据
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
		 Connection conn = DBOperate218.getInstance().getConnection();

		System.out.println(tyPlayName + source + dataAmount + vodeoType + upDatedate + playUrl + tvType + dataType);
		String strSql = "INSERT INTO ODS.TEM_NETWORK_REPUTATION t (t.TVPLAY_NAME,t.SOURCE ,t.DATA_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE"
				+ ",t.PLAY_URL,t.TV_TYPE,t.DATA_TYPE) VALUES (?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(dataAmount));
		list.add(Integer.parseInt(vodeoType));
//		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(Integer.parseInt(dataType));
		boolean bb=DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);

	}

	/**
	 * 每一集的数据读
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
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(
				tyPlayName + serNumber + source + playAmount + vodeoType + palydate + playUrl + tvType + realUrl);
		
		String strSql = "INSERT INTO ODS.TEM_NETWORK_PLAY_AMOUNT t (t.TVPLAY_NAME,t.SET_NUMBER "
				+ ",t.SOURCE,t.PLAY_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE,t.PLAY_URL,t.TV_TYPE,t.REAL_URL) "
				+ "VALUES (?,?,?,?,?,?"
				+ ",?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(serNumber));
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(playAmount));
		list.add(Integer.parseInt(vodeoType));
//		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(realUrl);
		boolean bb=DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 每个电视剧
	 * 主演 和类型
	 * @param TVPLAY_NAME
	 * @param SOURCE
	 * @param PLAY_AMOUNT
	 * @param DATA_DATE
	 * @param PLAY_URL
	 * @param PLAY_SUBJECT
	 * @param PLAY_ACTOR
	 * @param PLAY_COMMENT
	 */
	public static void intoTEM_NETWORK_TVPLAY_AMOUNT(String TVPLAY_NAME, int SOURCE, int PLAY_AMOUNT, String DATA_DATE,
			String PLAY_URL, String PLAY_SUBJECT, String PLAY_ACTOR, int PLAY_COMMENT) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(
				TVPLAY_NAME + DATA_DATE + PLAY_AMOUNT + PLAY_URL + PLAY_SUBJECT + PLAY_ACTOR + PLAY_COMMENT );
		
		String strSql = "insert into ods.TEM_NETWORK_TVPLAY_AMOUNT t values(null,?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(TVPLAY_NAME);// 这里是将对象加入到list中
		list.add(SOURCE);
		list.add(PLAY_AMOUNT);
		list.add(DATA_DATE);
		list.add(PLAY_URL);
//		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
//		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(PLAY_SUBJECT);
		list.add(PLAY_ACTOR);
		list.add(PLAY_COMMENT);
		boolean bb=DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

}
