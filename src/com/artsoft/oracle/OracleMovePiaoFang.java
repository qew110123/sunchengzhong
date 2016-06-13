package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.TEM_FILM_BOXOFFICE_REALTIME;
import com.artsoft.bean.TEM_FILM_DAILY_CINEMA;
import com.artsoft.bean.TEM_FILM_WEEK_CINEMA;
import com.artsoft.util.TimeTest;

public class OracleMovePiaoFang {
	/**
	 * 猫眼数据中的 票房总体情况表 2016年3月14日15:54:41
	 * 
	 * @return
	 */
	public static void intotem_film_daily_boxoffice(String data_date, String title, String url, String uid,
			String released_days, String total_boxoffice, String real_time_boxoffice, String boxoffice_rate,
			String slice_rate, String attendance_rate, int source, String into_date, String collection_url,
			String BOXOFFICE_TYPE) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_daily_boxoffice t ( t.data_date,t.title,t.url ,t.fid ,t.released_days,t.total_boxoffice ,"
				+ "t.REAL_TIME_BOXOFFICE,t.boxoffice_rate,t.slice_rate ,t.attendance_rate ,t.source,t.into_date,t.collection_url,t.BOXOFFICE_TYPE)values(?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(title);
		list.add(url);
		list.add(uid);
		list.add(released_days);
		list.add(total_boxoffice);
		list.add(real_time_boxoffice);
		list.add(boxoffice_rate);
		list.add(slice_rate);
		list.add(attendance_rate);
		list.add(source);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(BOXOFFICE_TYPE);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 票房总体情况表TEM_FILM_DAILY_BOXOFFICE 2016年5月3日16:58:04
	 * 
	 * @return
	 */
	public static void intoTEM_FILM_DAILY_BOXOFFICE(String data_date, String title, String url, String uid,
			String released_days, String total_boxoffice, String real_time_boxoffice, String boxoffice_rate,
			String slice_rate, String attendance_rate, int source, String into_date, String collection_url,
			String BOXOFFICE_TYPE, String FIELD_AVERAGE_PRICE, String FIELD_AVERAGE_PNUM, String BOXOFFICE_UP,
			String WOMINDEX, String MOVIEIMG) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_daily_boxoffice t ( t.data_date,t.title,t.url ,t.fid ,t.released_days,t.total_boxoffice ,"
				+ "t.REAL_TIME_BOXOFFICE,t.boxoffice_rate,t.slice_rate ,t.attendance_rate ,t.source,t.into_date,t.collection_url,t.BOXOFFICE_TYPE,"
				+ "t.FIELD_AVERAGE_PRICE,t.FIELD_AVERAGE_PNUM,t.BOXOFFICE_UP,t.WOMINDEX,t.MOVIEIMG)values(?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(title);
		list.add(url);
		list.add(uid);
		list.add(released_days);
		list.add(total_boxoffice);
		list.add(real_time_boxoffice);
		list.add(boxoffice_rate);
		list.add(slice_rate);
		list.add(attendance_rate);
		list.add(source);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(BOXOFFICE_TYPE);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(FIELD_AVERAGE_PRICE);
		list.add(FIELD_AVERAGE_PNUM);
		list.add(BOXOFFICE_UP);
		list.add(WOMINDEX);
		list.add(MOVIEIMG);
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 票房总tem_dim_film_boxoffice 2016年3月14日17:25:51 影片基本信息表
	 * 
	 * @return
	 */

	public static void intotem_dim_film_boxoffice(String fid, String title, String score, String score_num,
			String want_see_num, String type, String duration, String standard, String released_date,
			String total_boxoffice, String first_week_boxoffice, String director, String actors, String produce_company,
			String issue_company, String union_issue_company, String times_length, String color, String aspect_ratio,
			String description, String collection_url, int source) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_DIM_FILM_BOXOFFICE t ( t.fid,t.title,t.score ,t.score_num ,t.want_see_num,t.type ,"
				+ "t.duration,t.standard,t.released_date ,t.total_boxoffice ,t.first_week_boxoffice,t.director,t.actors,"
				+ "t.produce_company,t.issue_company,t.union_issue_company,t.times_length,t.color,t.aspect_ratio,t.description,t.into_date,t.collection_url,t.source)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(fid);
		list.add(title);
		list.add(score);
		list.add(score_num);
		list.add(want_see_num);
		list.add(type);
		list.add(duration);
		list.add(standard);
		list.add(released_date);
		list.add(total_boxoffice);
		list.add(first_week_boxoffice);
		list.add(director);
		list.add(actors);
		list.add(produce_company);
		list.add(issue_company);
		list.add(union_issue_company);
		list.add(times_length);
		list.add(color);
		list.add(aspect_ratio);
		list.add(description);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(source);

		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 tem_daily_tilm_boxoffice 日票房明细 2016年3月14日17:51:16
	 * 
	 * @return
	 */

	public static void intotem_film_daily_boxoffice_other(String fid, String title, String data_date,
			String day_boxoffice, String boxoffice_rate, String slice_rate, String field_average_pnum,
			String collection_url, String BOXOFFICE_TYPE) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_daily_boxoffice_other t ( t.fid,t.title,t.data_date ,t.day_boxoffice ,t.boxoffice_rate,t.slice_rate ,"
				+ "t.field_average_pnum,t.into_date,t.collection_url,t.BOXOFFICE_TYPE)values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(fid);
		list.add(title);
		list.add(data_date);
		list.add(day_boxoffice);
		list.add(boxoffice_rate);
		list.add(slice_rate);
		list.add(field_average_pnum);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(BOXOFFICE_TYPE);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 日排片 tem_daily_film_slice 2016年3月14日17:59:00
	 * 
	 * @return
	 */
	public static void intotem_daily_film_slice(String fid, String title, String data_date, String field_num,
			String slice_rate, String seat_num, String seat_rate, String gold_field_rate, String collection_url) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_daily_film_slice t ( t.fid,t.title,t.data_date ,t.field_num ,t.slice_rate,t.seat_num ,"
				+ "t.seat_rate,t.gold_field_rate,t.into_date,t.collection_url)values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(fid);
		list.add(title);
		list.add(data_date);
		list.add(field_num);
		list.add(slice_rate);
		list.add(seat_num);
		list.add(seat_rate);
		list.add(gold_field_rate);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 城市明细 tem_film_city 2016年3月14日18:09:40
	 * 
	 * @return
	 */
	public static void intotem_film_city(String fid, String title, String data_date, String city_name,
			String daily_boxoffice, String boxoffice_rate, String slice_rate, String total_boxoffice, String seat_rate,
			String gold_field_rate, String field_average_pnum, String pnum, String field_num, String collection_url,
			String total_field_num, int source) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_city t ( t.fid,t.title,t.data_date ,t.city_name ,t.daily_boxoffice,t.boxoffice_rate ,"
				+ "t.slice_rate,t.total_boxoffice,t.seat_rate,t.gold_field_rate,t.field_average_pnum,t.pnum,t.field_num,t.into_date,t.collection_url,t.total_field_num,t.source"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(fid);
		list.add(title);
		list.add(data_date);
		list.add(city_name);
		list.add(daily_boxoffice);
		list.add(boxoffice_rate);
		list.add(slice_rate);
		list.add(total_boxoffice);
		list.add(seat_rate);
		list.add(gold_field_rate);
		list.add(field_average_pnum);
		list.add(pnum);
		list.add(field_num);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(total_field_num);
		list.add(source);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	
	
	
	
	/**
	 * 票房数据进行数据的实时数据
	 *TEM_FILM_BOXOFFICE_REALTIME
	 *2016年6月7日18:10:46
	 * 
	 * @return
	 */
	public static void intoTEM_FILM_BOXOFFICE_REALTIME(TEM_FILM_BOXOFFICE_REALTIME  realitme) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_FILM_BOXOFFICE_REALTIME t (t.data_date,t.title,t.url,t.fid,t.released_days,t.real_time_boxoffice,t.boxoffice_rate,t.total_boxoffice,t.into_date,t.collection_url,t.real_date,t.DATA_TYPE)values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(realitme.getDataDate());
		list.add(realitme.getTitle());
		list.add(realitme.getUrl());
		list.add(realitme.getFid());
		list.add(realitme.getReleasedDays());
		list.add(realitme.getRealTimeBoxoffice());
		list.add(realitme.getBoxofficeRate());
		list.add(realitme.getTotalBoxoffice());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(realitme.getCollectionUrl());
		list.add(realitme.getREAL_DATE());
		list.add(realitme.getDATA_TYPE());
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	
	
	
	/**
	 * 票房数据进行数据的实时数据
	 * 单日票房
	 *TEM_FILM_DAILY_CINEMA  dailycinema=new TEM_FILM_DAILY_CINEMA();
	 *
	 *2016年6月12日17:47:56
	 * 
	 * @return
	 */
	public static void intoTEM_FILM_WEEK_CINEMA(TEM_FILM_WEEK_CINEMA  dailycinema) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_FILM_WEEK_CINEMA t (t.week_data_date,t.title,t.week_boxoffice,t.avg_screen,t.field_average_pnum,t.screen_yield,t.scenes_time,t.into_date,t.collection_url)values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(dailycinema.getWeekDataDate());
		list.add(dailycinema.getTitle());
		list.add(dailycinema.getWeekBoxoffice());
		list.add(dailycinema.getAvgScreen());
		list.add(dailycinema.getFieldAveragePnum());
		list.add(dailycinema.getScreenYield());
		list.add(dailycinema.getScenesTime());
//		list.add(dailycinema.getTotalBoxoffice());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(dailycinema.getCollectionUrl());
//		list.add(realitme.getREAL_DATE());
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	
	/**
	 * 票房数据进行数据的实时数据
	 * 周票房
	 *TEM_FILM_DAILY_CINEMA  dailycinema=new TEM_FILM_DAILY_CINEMA();
	 *
	 *2016年6月12日18:58:04
	 * 
	 * @return
	 */
	public static void intoTEM_FILM_DAILY_CINEMA(TEM_FILM_DAILY_CINEMA  dailycinema) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_FILM_DAILY_CINEMA t (t.DATA_DATE,t.title,t.real_time_boxoffice,t.field_num,t.field_average_pnum,t.average_price,t.attendance_rate,t.into_date,t.collection_url)values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(dailycinema.getDataDate());
		list.add(dailycinema.getTitle());
		list.add(dailycinema.getRealTimeBoxoffice());
		list.add(dailycinema.getFieldNum());
		list.add(dailycinema.getFieldAveragePnum());
		list.add(dailycinema.getAveragePrice());
		list.add(dailycinema.getAttendanceRate());
//		list.add(dailycinema.getTotalBoxoffice());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(dailycinema.getCollectionUrl());
//		list.add(realitme.getREAL_DATE());
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	// tem_film_company 影投明细表

	/**
	 * 猫眼数据中的 影投明细表 tem_film_company 2016年3月15日11:14:21
	 * 
	 * @return
	 */
	public static void intotem_film_company(String fid, String title, String data_date, String film_company,
			String boxoffice, String boxoffice_rate, String slice_rate, String total_boxoffice, String seat_rate,
			String gold_field_rate, String field_average_pnum, String pnum, String field_num, String collection_url) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_company t ( t.fid,t.title,t.data_date ,t.film_company ,t.boxoffice,t.boxoffice_rate ,"
				+ "t.slice_rate,t.total_boxoffice,t.seat_rate,t.gold_field_rate,t.field_average_pnum,t.pnum,t.field_num,t.into_date,t.collection_url"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(fid);
		list.add(title);
		list.add(data_date);
		list.add(film_company);
		list.add(boxoffice);
		list.add(boxoffice_rate);
		list.add(slice_rate);
		list.add(total_boxoffice);
		list.add(seat_rate);
		list.add(gold_field_rate);
		list.add(field_average_pnum);
		list.add(pnum);
		list.add(field_num);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 受众人群 tem_audience_crowd 2016年3月15日11:19:25
	 * 
	 * @return
	 */

	public static void intotem_audience_crowd(String data_date, String film_id, String film_name, String age_name,
			String age_rate, String sex_name, String sex_rate, String collection_url, int DATA_TYPE) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_audience_crowd t ( t.data_date,t.film_id,t.film_name ,t.age_name ,t.age_rate,t.sex_name ,"
				+ "t.sex_rate,t.into_date,t.collection_url ,t.DATA_TYPE)values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(film_id);
		list.add(film_name);
		list.add(age_name);
		list.add(age_rate);
		list.add(sex_name);
		list.add(sex_rate);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(DATA_TYPE);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 猫眼数据中的 想看指数 tem_want_see_index 2016年3月15日14:46:21
	 * 
	 * @return
	 */
	public static void intotem_want_see_index(String data_date, String film_id, String film_name, String new_person_num,
			String collection_url, int source, String released_days, int index_type, int data_type,
			String insert_date) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_index t ( t.data_date,t.film_id,t.film_name ,t.data_index ,"
				+ "t.into_date,t.collection_url,t.source,t.released_days,t.index_type,t.data_type,t.insert_date)values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,? )";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(film_id);
		list.add(film_name);
		list.add(new_person_num);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);
		list.add(source);
		list.add(released_days);
		list.add(index_type);
		list.add(data_type);
		list.add(insert_date);
		// list.add(source);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * tem_want_see_city 想看人数城市排行 猫眼数据中的 想看人数城市排行 tem_want_see_city
	 * 2016年3月15日14:51:23
	 * 
	 * @return
	 */
	public static void intotem_want_see_city(String data_date, String film_id, String film_name, String city_name,
			String city_rate, String collection_url) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.tem_film_want_see_city t ( t.data_date,t.film_id,t.film_name ,t.city_name,t.city_rate,"
				+ "t.into_date,t.collection_url)values(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(film_id);
		list.add(film_name);
		list.add(city_name);
		list.add(city_rate);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(collection_url);

		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

}
