package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.TEM_WEIBO_TOPIC_SCORE;
import com.artsoft.bean.Tem_weibo_word_age_sex;
import com.artsoft.bean.Tem_weibo_word_area;
import com.artsoft.bean.Tem_weibo_word_tag;
import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.bean.tem_weibo_word_num;
import com.artsoft.util.TimeTest;

public class Oracle {
	
	
	/**
	 * 2016年5月11日14:27:59
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(tem_weibo_word_num tem_weibo_word_num) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_weibo_word_num t(t.data_date ,t.data_id,t.word,t.news_num,t.into_date,t.url,t.dimension_type,t.data_type)values (?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_num.getDataDate());
		list.add(tem_weibo_word_num.getDataId());
		list.add(tem_weibo_word_num.getWord());
		list.add(tem_weibo_word_num.getNewsNum());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_num.getUrl());
		list.add(tem_weibo_word_num.getDimensionType());
		list.add(tem_weibo_word_num.getDataType());
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * 2016年5月11日14:27:59
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(Tem_weibo_word_area tem_weibo_word_area) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into  ods.tem_weibo_word_area t(t.data_date,t.data_id ,t.word ,t.area_num,t.area_rate,t.area_top,t.area_name_en,t.area_name_cn,t.into_date,t.url,t.dimension_type,t.data_type) values (?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_area.getDataDate());
		list.add(tem_weibo_word_area.getDataId());
		list.add(tem_weibo_word_area.getWord());
		list.add(tem_weibo_word_area.getAreaNum());
		list.add(tem_weibo_word_area.getAreaRate());
		list.add(tem_weibo_word_area.getAreaTop());
		list.add(tem_weibo_word_area.getAreaNameEn());
		list.add(tem_weibo_word_area.getAreaNameCn());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_area.getUrl());
		list.add(tem_weibo_word_area.getDimensionType());
		list.add(tem_weibo_word_area.getDataType());
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 *2016年5月11日16:46:27
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(Tem_weibo_word_age_sex tem_weibo_word_age_sex) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_weibo_word_age_sex t (t.data_date,t.data_id,t.word,t.man_rate,t.woman_rate,t.rate12,t.rate18,t.rate24,t.rate34,t.rate50,t.other_rate,t.into_date,t.url,t.data_type)values (?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_age_sex.getDataDate());
		list.add(tem_weibo_word_age_sex.getDataId());
		list.add(tem_weibo_word_age_sex.getWord());
		list.add(tem_weibo_word_age_sex.getManRate());
		list.add(tem_weibo_word_age_sex.getWomanRate());
		list.add(tem_weibo_word_age_sex.getRate12());
		list.add(tem_weibo_word_age_sex.getRate18v());
		list.add(tem_weibo_word_age_sex.getRate24());
		list.add(tem_weibo_word_age_sex.getRate34());
		list.add(tem_weibo_word_age_sex.getRate50());
		list.add(tem_weibo_word_age_sex.getOtherRate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_age_sex.getUrl());
//		list.add(tem_weibo_word_age_sex.getDimensionType());
		list.add(tem_weibo_word_age_sex.getDataType());
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 *2016年5月11日16:47:19
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(Tem_weibo_word_tag tem_weibo_word_tag) {
		try {
			
		
			Connection conn = DBOperate218.getInstance().getConnection();
	
			String strSql = "insert into ods.tem_weibo_word_tag t (t.data_date,t.data_id,t.word,t.label_name,t.label_rate,t.into_date,t.url,t.dimension_type,t.data_type)values (?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
			List<Comparable> list = new ArrayList();
			list.add(tem_weibo_word_tag.getDataDate());
			list.add(tem_weibo_word_tag.getDataId());
			list.add(tem_weibo_word_tag.getWord());
			list.add(tem_weibo_word_tag.getLabelName());
			if (tem_weibo_word_tag.getLabelRate().equals(null)||tem_weibo_word_tag.getLabelRate()==null) {
				tem_weibo_word_tag.setLabelRate("");
			}
			list.add(tem_weibo_word_tag.getLabelRate());
			list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			list.add(tem_weibo_word_tag.getUrl());
			list.add(tem_weibo_word_tag.getDimensionType());
	//		list.add(tem_weibo_word_age_sex.getDimensionType());
			list.add(tem_weibo_word_tag.getDataType());
			System.out.println(list.toString());
			boolean bb = DBOperate218.insertRecord(conn, strSql, list);
			System.out.println(bb);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库出错");
		}
	}
	
	
	/**
	 *2016年5月11日16:47:19
	 * 
	 * @param company
	 * 
	 */
	public static void Inserttem_weibo_topic_score(TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score) {
		try {
			
		
			Connection conn = DBOperate218.getInstance().getConnection();
	
			String strSql = "insert into ods.TEM_WEIBO_TOPIC_SCORE t (t.data_date,t.data_id ,t.reading_num,t.discuss_num,t.fan_num,"
					+ "t.score,t.follow_num,t.into_date,t.data_url,t.big_type,t.data_type) values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
			List<Comparable> list = new ArrayList();
//			list.add(tem_weibo_topic_score.getDataDate());
//			list.add(tem_weibo_topic_score.getDataId());
//			list.add(tem_weibo_topic_score.getReadingNum());
//			list.add(tem_weibo_topic_score.getDiscussNum());
//			list.add(tem_weibo_topic_score.getFanNum());
//			list.add(tem_weibo_topic_score.getScore());
//			list.add(tem_weibo_topic_score.getFanNum());
//			list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
//			list.add(tem_weibo_topic_score.getDataUrl());
//			list.add(tem_weibo_topic_score.getBigType());
//			list.add(tem_weibo_topic_score.getDataType());
//			System.out.println(list.toString());
			
			
			list.add(null);
			list.add(null);
			list.add(null);
			list.add(null);
			list.add(null);
			list.add(null);
			list.add(null);
			list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			list.add(null);
			list.add(null);
			list.add(null);
			System.out.println(list.toString());
			
			boolean bb = DBOperate218.insertRecord(conn, strSql, list);
			System.out.println(bb);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库出错");
		}
	}
	
	
	
	
	/**
	 *2016年5月19日10:57:03
	 * 
	 * @param company
	 * 添加微博微博电视剧/电影微博阅读量、讨论量、粉丝量、微博评分、关注、阅读数   
	 * 前数据
	 * 
	 */
	public static void Inserttem_weibo_topic_scorePart(TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score) {
		try {
			
		
			Connection conn = DBOperate218.getInstance().getConnection();
	
			String strSql = "insert into ods.TEM_WEIBO_TOPIC_SCORE t (t.data_date,t.data_id ,t.reading_num,t.discuss_num,t.fan_num,"
					+ "t.into_date,t.data_url,t.big_type,t.data_type) values(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
			List<Comparable> list = new ArrayList();
			list.add(tem_weibo_topic_score.getDataDate());
			list.add(tem_weibo_topic_score.getDataId());
			list.add(tem_weibo_topic_score.getReadingNum());
			list.add(tem_weibo_topic_score.getDiscussNum());
			list.add(tem_weibo_topic_score.getFanNum());
//			list.add(tem_weibo_topic_score.getScore());
//			list.add(tem_weibo_topic_score.getFanNum());
			list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			list.add(tem_weibo_topic_score.getDataUrl());
			list.add(tem_weibo_topic_score.getBigType());
			list.add(tem_weibo_topic_score.getDataType());
			System.out.println(list.toString());
			
			boolean bb = DBOperate218.insertRecord(conn, strSql, list);
			System.out.println(bb);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库出错");
		}
	}
	
	
	/**
	 *2016年5月19日15:54:45
	 * 
	 * @param company
	 * 添加微博微博电视剧/电影微博阅读量、讨论量、粉丝量、微博评分、关注、阅读数   
	 * 后数据
	 * 
	 */
	public static void Inserttem_weibo_topic_scorePartBIG_TYPE2(TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score) {
		try {
			
		
			Connection conn = DBOperate218.getInstance().getConnection();
	
			String strSql = "insert into ods.TEM_WEIBO_TOPIC_SCORE t (t.data_date,t.data_id ,t.reading_num,t.score,t.follow_num,"
					+ "t.into_date,t.data_url,t.big_type,t.data_type) values(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
			List<Comparable> list = new ArrayList();
			list.add(tem_weibo_topic_score.getDataDate());
			list.add(tem_weibo_topic_score.getDataId());
			list.add(tem_weibo_topic_score.getReadingNum());
//			list.add(tem_weibo_topic_score.getDiscussNum());
//			list.add(tem_weibo_topic_score.getFanNum());
			list.add(tem_weibo_topic_score.getScore());
			list.add(tem_weibo_topic_score.getFanNum());
			list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			list.add(tem_weibo_topic_score.getDataUrl());
			list.add(tem_weibo_topic_score.getBigType());
			list.add(tem_weibo_topic_score.getDataType());
			System.out.println(list.toString());
			
			boolean bb = DBOperate218.insertRecord(conn, strSql, list);
			System.out.println(bb);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库出错");
		}
	}
	
	
	/**
	 * 2016年5月11日14:27:59
	 * 
	 * @param company
	 * 
	 */
	public static void InsertWECHAT_INFORMATION(WECHAT_INFORMATION INFORMATION) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "INSERT INTO ods.WECHAT_INFORMATION t (t.data_id,t.names,t.dates,t.post_user,t.content_all,t.content_p,t.urls,t.weixinhao,t.data_date,t.weixin_ation,t.original,t.ranking,IMG_BIG_URL,IMG_BIG_NAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(INFORMATION.getDataId());
		list.add(INFORMATION.getNames());
		list.add(INFORMATION.getDates());
		list.add(INFORMATION.getPostUser());
		list.add(INFORMATION.getContentAll());
		list.add(INFORMATION.getContentP());
		list.add(INFORMATION.getUrls());
		list.add(INFORMATION.getWeixinhao());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(INFORMATION.getWeixinAtion());
		list.add(INFORMATION.getOriginal());
		list.add(INFORMATION.getRanking());
		list.add(INFORMATION.getIMG_BIG_URL());
		list.add(INFORMATION.getIMG_BIG_NAME());
//		list.add(INFORMATION.getUrls());
		
//		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * 百度数据的中的网络剧补充
	 * 2016年6月17日16:37:43
	 * @return
	 */
	public static List selectWEIXIN_NUMBER() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.* from ODS.WEIXIN_NUMBER t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	public static void main(String[] args) {
		TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score=new TEM_WEIBO_TOPIC_SCORE();
		Inserttem_weibo_topic_score(tem_weibo_topic_score);
	}

}
