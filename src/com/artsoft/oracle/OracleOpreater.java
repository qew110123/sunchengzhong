package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.TvPlay;
import com.artsoft.util.TimeTest;

public class OracleOpreater {

	// static Connection conn = DBOperate.getInstance().getConnection();

	/**
	 * 每一个电视剧的数据
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
		Connection conn = DBOperate218.getInstance().getConnection();

		System.out.println(tyPlayName + source + dataAmount + vodeoType + upDatedate + playUrl + tvType + dataType);
		String strSql = "INSERT INTO ODS.TEM_NETWORK_REPUTATION t (t.TVPLAY_NAME,t.SOURCE ,t.DATA_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE"
				+ ",t.PLAY_URL,t.TV_TYPE,t.DATA_TYPE) VALUES (?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(dataAmount));
		list.add(Integer.parseInt(vodeoType));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(Integer.parseInt(dataType));
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);

	}
	
	
	/**
	 * 进行数据的查看豆瓣数据的查看
	 * @param times
	 * @return
	 */
	public static String returndouban(String times) {
		Connection conn = DBOperate218.getInstance().getConnection();
		// String strSql = "select count(*) from ods.person_network_popularity";
		String strSql = "select count(*) from ODS.TEM_NETWORK_REPUTATION t where t.source=9  and t.date_date='"+times+"' and t.tv_type=1 and t.data_type=1";
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}
	
	
	/**
	 * 进行数据的查看豆瓣数据的查看
	 * @param times
	 * @return
	 */
	public static String returndoubanwangluoju(String times) {
		Connection conn = DBOperate218.getInstance().getConnection();
		// String strSql = "select count(*) from ods.person_network_popularity";
		String strSql = "select count(*) from ODS.TEM_NETWORK_REPUTATION t where t.source=9  and t.date_date='"+times+"' and t.tv_type=1 and t.data_type=1";
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}
	
	
	

	/**
	 * 每一个电视剧的数据
	 * 
	 * @param tyPlayName
	 * @param source
	 * @param dataAmount
	 * @param vodeoType
	 * @param upDatedate
	 * @param playUrl
	 * @param tvType
	 * @param dataType
	 *            DETAIL_URL 电影详情基本信息url 2016年1月27日17:11:58 sunchengzhong
	 */
	public static void intoReputationAndDETAIL_URL(String tyPlayName, String source, String dataAmount,
			String vodeoType, String upDatedate, String playUrl, String tvType, String dataType, String DETAIL_URL) {
		Connection conn = DBOperate218.getInstance().getConnection();
		if (dataAmount.equals("暂无")) {
			dataAmount = "-1";
		}
//		if (condition) {
//			
//		}
		

		System.out.println(tyPlayName + source + dataAmount + vodeoType + upDatedate + playUrl + tvType + dataType);
		String strSql = "INSERT INTO ODS.TEM_NETWORK_REPUTATION t (t.TVPLAY_NAME,t.SOURCE ,t.DATA_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE"
				+ ",t.PLAY_URL,t.TV_TYPE,t.DATA_TYPE,t.DETAIL_URL) VALUES (?,?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(dataAmount));
		list.add(Integer.parseInt(vodeoType));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(Integer.parseInt(dataType));
		list.add(DETAIL_URL);
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);

	}

	/**
	 * 每一集的数据读
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
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(
				tyPlayName + serNumber + source + playAmount + vodeoType + palydate + playUrl + tvType + realUrl);

		String strSql = "INSERT INTO ODS.TEM_NETWORK_PLAY_AMOUNT t (t.TVPLAY_NAME,t.SET_NUMBER "
				+ ",t.SOURCE,t.PLAY_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE,t.PLAY_URL,t.TV_TYPE,t.REAL_URL) "
				+ "VALUES (?,?,?,?,?,?" + ",?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(serNumber));
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(playAmount));
		list.add(Integer.parseInt(vodeoType));
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(realUrl);
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	public static void intoPlayAmont(String tyPlayName, String source, String playAmount,
			String vodeoType, String palydate, String playUrl, String tvType, String realUrl) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(
				tyPlayName  + source + playAmount + vodeoType + palydate + playUrl + tvType + realUrl);

		String strSql = "INSERT INTO ODS.TEM_NETWORK_PLAY_AMOUNT t (t.TVPLAY_NAME "
				+ ",t.SOURCE,t.PLAY_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE,t.PLAY_URL,t.TV_TYPE,t.REAL_URL) "
				+ "VALUES (?,?,?,?,?" + ",?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
//		list.add(Integer.parseInt(serNumber));
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(playAmount));
		list.add(Integer.parseInt(vodeoType));
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(realUrl);
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 每个电视剧 主演 和类型
	 * 
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
		System.out.println(TVPLAY_NAME + DATA_DATE + PLAY_AMOUNT + PLAY_URL + PLAY_SUBJECT + PLAY_ACTOR + PLAY_COMMENT);

		String strSql = "insert into ods.TEM_NETWORK_TVPLAY_AMOUNT t values(null,?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(TVPLAY_NAME);// 这里是将对象加入到list中
		list.add(SOURCE);
		list.add(PLAY_AMOUNT);
		list.add(DATA_DATE);
		list.add(PLAY_URL);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(PLAY_SUBJECT);
		list.add(PLAY_ACTOR);
		list.add(PLAY_COMMENT);
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	public static void intoTEM_DIM_FILM_PLATFORM(TvPlay playtv) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_DIM_FILM_PLATFORM t(t.B_FILM_ID,t.film_name,t.film_url,t.english_name,"
				+ "t.alias_name,t.years,t.produce_area,t.show_date,t.create_time,t.update_time,t.source,t.description,"
				+ "t.subject_name_one,t.director,t.actors,t.screenwriter,t.subject_name_two,t.subject_id_one,t.subject_id_two,"
				+ "t.time_long,t.languages,t.imdb_code,t.film_level,t.original,t.BOX_OFFICES)values(TEM_DIM_FILM_PLATFORM_SEQ.nextval,?,?,?,?,?,?,?,to_date(?,"
				+ "'yyyy-mm-dd hh24:mi:ss'),null,?,?,?,?,?,?,?,null,null,?,?,?,?,?,?)";
		
		
		strSql = "insert into ods.TEM_DIM_FILM_PLATFORM t(t.B_FILM_ID,t.film_name,t.film_url,t.english_name,"
				+ "t.alias_name,t.years,t.produce_area,t.show_date,t.create_time,t.update_time,t.source,t.description,"
				+ "t.subject_name_one,t.director,t.actors,t.screenwriter,t.subject_name_two,t.subject_id_one,t.subject_id_two,"
				+ "t.time_long,t.languages,t.imdb_code,t.film_level,t.original,t.BOX_OFFICES,t.PRODUCE_COMPANY,t.ISSUE_ORGANIZATION,t.PRODUCE_FORMAT)values(?,?,?,?,?,?,?,?,to_date(?,"
				+ "'yyyy-mm-dd hh24:mi:ss'),null,?,?,?,?,?,?,?,null,null,?,?,?,?,?,?,?,?,?)";
		
		

		List<Comparable> list = new ArrayList();
		// 这里是将对象加入到list中
		
//		if (!playtv.getTvplay_id().equals("")) {
			list.add(playtv.getTvplay_id());
//		}else{
//			list.add("");
//		}

		list.add(playtv.getTvplay_name());
		list.add(playtv.getTvplay_url());
		list.add("");
		list.add(playtv.getAlias_en());
		list.add(playtv.getShoot_time());
		list.add(playtv.getProduction_area());
		list.add(playtv.getShow_date());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		// list.add(playtv.getSubject());
		list.add(playtv.getClassnum());
		list.add(playtv.getBasic_info());
		list.add(playtv.getSubject());
		list.add(playtv.getDirector());
		list.add(playtv.getMajor_actors());
		list.add(playtv.getScreenwriter());
		list.add("");
		list.add(playtv.getTime_length());
		list.add(playtv.getLgName());
		list.add(playtv.getIMDb());
		list.add("");
		list.add("");
		list.add(playtv.getBox_office());// 票房
		// list.add(playtv.get)
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playtv.getPRODUCE_COMPANY());
		list.add(playtv.getIssuing_company());// 票房
		list.add(playtv.getPRODUCE_FORMAT());// 票房
		

		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 添加电视剧数据 TEM_DIM_TVPLAY_PLATFORM 2016年3月30日18:13:06 2016年4月1日16:23:54
	 * 
	 * @param playtv
	 */
	public static void intoTEM_DIM_TVPLAY_PLATFORM(TvPlay playtv) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String info= playtv.getBasic_info();
		if (info!=null &&!info.equals("")&&!info.equals("null")) {
			info=info.replace("&gt", "");
		}

		String strSql = "insert into ods.TEM_DIM_TVPLAY_PLATFORM t ( t.tvplay_name,t.tvplay_url ,"
				+ "t.alias_en ,t.alias_cn,t.major_actors ,t.director,t.screenwriter,t.producer ,t.production_company ,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.PRODUCED_COMPANY,"
				+ "t.production_area,t.premiere_time,t.pages,t.photography_director,t.total_production,t.basic_info,"
				+ "t.update_time,t.source)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		// 这里是将对象加入到list中

		list.add(playtv.getTvplay_name());
		list.add(playtv.getTvplay_url());
		list.add(playtv.getAlias_en());
		list.add(playtv.getAlias_cn());
		list.add(playtv.getMajor_actors());
		// list.add(playtv.getProduction_area());
		list.add(playtv.getDirector());
		list.add(playtv.getScreenwriter());

		list.add(playtv.getProducer());

		list.add(playtv.getProduction_company());

		list.add(playtv.getIssuing_company());

		list.add(playtv.getShoot_time());

		list.add(playtv.getShoot_place());

		list.add(playtv.getSubject());

		// list.add("");
		list.add(playtv.getProduced_time());

		list.add(playtv.getProduced_company());

		list.add(playtv.getProduction_area());

		list.add(playtv.getPremiere_time());

		list.add(playtv.getPages());

		list.add(playtv.getPhorogrphy_director());

		list.add(playtv.getTotal_production());

		list.add(info);

		list.add(TimeTest.getNowTime("yyyyMMdd"));

		list.add(playtv.getSOURCE());

		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

}
