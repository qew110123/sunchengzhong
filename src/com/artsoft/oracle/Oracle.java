package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.DEL_DIM_NETWORKPLAY;
import com.artsoft.bean.MAYAO_KEY;
import com.artsoft.bean.TEM_BAIDU_NEW;
import com.artsoft.bean.TEM_CHINA_NETWORK_VIDEO_INDEX;
import com.artsoft.bean.TEM_IQIYI_AND_YOUKU_WORD_INDEX;
import com.artsoft.bean.TEM_PERSON_URL;
import com.artsoft.bean.TEM_PERSON_URL_WORKS;
import com.artsoft.bean.TEM_TVPLAY_TIDBITS;
import com.artsoft.bean.TEM_WEIBO_TOPIC_SCORE;
import com.artsoft.bean.TVPLAY_IQIYI_INDEX;
import com.artsoft.bean.Tem_weibo_word_age_sex;
import com.artsoft.bean.Tem_weibo_word_area;
import com.artsoft.bean.Tem_weibo_word_tag;
import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.bean.tem_weibo_word_num;
import com.artsoft.util.Stirngstr;
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
		if (!Stirngstr.isNumeribaidu(tem_weibo_word_age_sex.getManRate())) {
			return;
			
		}
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

		String strSql = "INSERT INTO ods.WECHAT_INFORMATION t (t.data_id,t.names,t.dates,t.post_user,t.content_all,t.content_p,t.urls,t.weixinhao,t.data_date,t.weixin_ation,t.original,t.ranking,IMG_BIG_URL,IMG_BIG_NAME,SOURCE,DATA_TYPE,UPDATE_FLAG) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(INFORMATION.getDataId());
		list.add(INFORMATION.getNames());
		list.add(INFORMATION.getDates());
		list.add(INFORMATION.getPostUser());
		list.add(INFORMATION.getContentAll());
		list.add(INFORMATION.getContentP());
		list.add(INFORMATION.getUrls());
		list.add(INFORMATION.getWeixinhao());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(INFORMATION.getWeixinAtion());
		list.add(INFORMATION.getOriginal());
		list.add(INFORMATION.getRanking());
		list.add(INFORMATION.getIMG_BIG_URL());
		list.add(INFORMATION.getIMG_BIG_NAME());
		list.add(INFORMATION.getSOURCE());
		list.add(INFORMATION.getDATA_TYPE());
		list.add(TimeTest.getNowTime("yyyyMMddHH"));
//		list.add(INFORMATION.getUrls());
		
//		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 2016年11月25日17:37:24
	 * 
	 * @param company
	 * 
	 */
	public static void InsertDEL_DIM_NETWORKPLAY(DEL_DIM_NETWORKPLAY NETWORKPLAY) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "INSERT INTO ods.DEL_DIM_NETWORKPLAY t (t.NETWORKPLAY_ID,t.NETWORKPLAY_NAME,t.SHOW_NAME,t.ACTORS,t.DIRECTOR,t.SCREENWRITER, t.LABEL_NAME,t.FIRST_DATE,t.SET_NUM,t.SCORE,t.DATA_URL) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(NETWORKPLAY.getNetworkplayId());
		list.add(NETWORKPLAY.getNetworkplayName());
		list.add(NETWORKPLAY.getShowName());
		list.add(NETWORKPLAY.getActors());
		list.add(NETWORKPLAY.getDirector());
		list.add(NETWORKPLAY.getScreenwriter());
		list.add(NETWORKPLAY.getLabelName());
		list.add(NETWORKPLAY.getFirstDate());
//		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(NETWORKPLAY.getSetNum());
		list.add(NETWORKPLAY.getScore());
		list.add(NETWORKPLAY.getDataUrl());
		
//		list.add(INFORMATION.getUrls());
		
//		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 2016-11-25 17:37:28
	 * 
	 * @param company
	 * 
	 */
	public static void InsertTEM_BAIDU_NEW(TEM_BAIDU_NEW BAIDU_NEW) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "INSERT INTO ods.TEM_BAIDU_NEW t (t.TVPLAY_NAME,t.SHOW_NAME,t.SOURCES,t.CREATE_DATE,t.TVPLAY_URL) VALUES (?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(BAIDU_NEW.getTvplayName());
		list.add(BAIDU_NEW.getShowName());
		list.add(BAIDU_NEW.getSources());
		list.add(BAIDU_NEW.getCreateDate());
		list.add(BAIDU_NEW.getTvplayUrl());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 2016年5月11日14:27:59
	 * 
	 * @param company
	 * 
	 */
	public static void InsertWECHAT_INFORMATION_id(WECHAT_INFORMATION INFORMATION) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "INSERT INTO ods.WECHAT_INFORMATION t (t.data_id,t.names,t.dates,t.post_user,t.content_all,t.content_p,t.urls,t.weixinhao,t.data_date,t.weixin_ation,t.original,t.ranking,IMG_BIG_URL,IMG_BIG_NAME,SOURCE,DATA_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if (INFORMATION.getContentP()==null||INFORMATION.getContentP().equals("")) {
			return;
		}
		List<Comparable> list = new ArrayList();
		list.add(INFORMATION.getDataId());
		list.add(INFORMATION.getNames());
		list.add(INFORMATION.getDates());
		list.add(INFORMATION.getPostUser());
		list.add(INFORMATION.getContentAll());
		list.add(INFORMATION.getContentP());
		list.add(INFORMATION.getUrls());
		list.add(INFORMATION.getWeixinhao());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(INFORMATION.getWeixinAtion());
		list.add(INFORMATION.getOriginal());
		list.add(INFORMATION.getRanking());
		list.add(INFORMATION.getIMG_BIG_URL());
		list.add(INFORMATION.getIMG_BIG_NAME());
		list.add(INFORMATION.getSOURCE());
		list.add(INFORMATION.getDATA_TYPE());
		
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
//		sql=" select t.* from ODS.WEIXIN_NUMBER t where t.public_number='影投人'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * 微信数据
	 * 2017年3月8日18:46:35
	 * @return
	 */
	public static List selectWEIXIN_NUMBER_NEW() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name from mart.f_person_index t where t.data_date = '29991231' order by t.network_index desc,t.person_id";
//		sql=" select t.* from ODS.WEIXIN_NUMBER t where t.public_number='影投人'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * 
	 * 2016年8月16日15:26:14
	 * 猫眼数据进行电影数据进行搜索
	 * @return
	 */
	public static List selectmaoyanshijuTmove() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.film_id,t.film_name from mart.dim_film t where t.box_offices = 0";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	
	
	/**
	 * 2016年9月5日17:16:02
	 * 
	 * @param company
	 * 
	 */
	public static void IntoTEM_TVPLAY_TIDBITS(TEM_TVPLAY_TIDBITS tidbits) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.TEM_TVPLAY_TIDBITS t (t.data_date,t.tvplay_name,t.title_name,t.detail_url,t.play_url,t.put_date,t.play_amount,t.play_platform,t.time_longs,t.order_no"
				+ ",t.img_small_url,t.img_small_name,t.into_date,t.data_type,t.SOURCE)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(TimeTest.getNowTime("yyyyMMdd"));
//		list.add(tidbits.getDataId());
		list.add(tidbits.getTvplayName());
		list.add(tidbits.getTitleName());
		list.add(tidbits.getDetailUrl());
		list.add(tidbits.getPlayUrl());
		list.add(tidbits.getPutDate());
		list.add(tidbits.getPlayAmount());
		list.add(tidbits.getPlayPlatform());
		list.add(tidbits.getTimeLongs());
		list.add(tidbits.getOrderNo());
		list.add(tidbits.getImgSmallUrl());
		list.add(tidbits.getImgSmallName());
//		list.add(tidbits.getIntoDate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tidbits.getDataType());
		list.add(tidbits.getSOURCE());
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	

	/**
	 * 2016年9月5日17:16:02
	 * 
	 * @param company
	 * 
	 */
	public static void IntoTEM_TVPLAY_TIDBITS_andid(TEM_TVPLAY_TIDBITS tidbits) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.TEM_TVPLAY_TIDBITS t (t.data_date,t.TVPLAY_ID,t.tvplay_name,t.title_name,t.detail_url,t.play_url,t.put_date,t.play_amount,t.play_platform,t.time_longs,t.order_no"
				+ ",t.img_small_url,t.img_small_name,t.into_date,t.data_type,t.SOURCE)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(Long.parseLong(tidbits.getTvplayId()));
		list.add(tidbits.getTvplayName());
		list.add(tidbits.getTitleName());
		list.add(tidbits.getDetailUrl());
		list.add(tidbits.getPlayUrl());
		list.add(tidbits.getPutDate());
		list.add(tidbits.getPlayAmount());
		list.add(tidbits.getPlayPlatform());
		list.add(tidbits.getTimeLongs());
		list.add(tidbits.getOrderNo());
		list.add(tidbits.getImgSmallUrl());
		list.add(tidbits.getImgSmallName());
//		list.add(tidbits.getIntoDate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tidbits.getDataType());
		list.add(tidbits.getSOURCE());
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	

	/**
	 * 2016年9月14日13:20:39
	 * 
	 * @param mayao_key
	 * 
	 */
	public static void IntoMAYAO_KEY(MAYAO_KEY mayao_key) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.Mayao_Key t (t.key_url,t.one,t.two,t.three,t.four,t.five,t.six,t.seven,t.eight,t.nine,t.zero)values(?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(mayao_key.getKeyUrl());
		list.add(mayao_key.getOne());
		list.add(mayao_key.getTwo());
		list.add(mayao_key.getThree());
		list.add(mayao_key.getFour());
		list.add(mayao_key.getFive());
		list.add(mayao_key.getSix());
		list.add(mayao_key.getSeven());
		list.add(mayao_key.getEight());
		list.add(mayao_key.getNine());
		list.add(mayao_key.getZero());
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 2017年3月3日14:29:33
	 * 
	 * @param mayao_key
	 * 
	 */
	public static void IntoMAYAO_KEY_new(MAYAO_KEY mayao_key) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.Mayao_Key t (t.key_url,t.one,t.two,t.three,t.four,t.five,t.six,t.seven,t.eight,t.nine,t.zero,t.KEY_URL_NEW)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(mayao_key.getKeyUrl());
		list.add(mayao_key.getOne());
		list.add(mayao_key.getTwo());
		list.add(mayao_key.getThree());
		list.add(mayao_key.getFour());
		list.add(mayao_key.getFive());
		list.add(mayao_key.getSix());
		list.add(mayao_key.getSeven());
		list.add(mayao_key.getEight());
		list.add(mayao_key.getNine());
		list.add(mayao_key.getZero());
		list.add(mayao_key.getKEY_URL_NEW());
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * mayao_key
	 * 2016年9月14日13:45:42
	 * @return
	 */
	public static List selectmayao_key (String key_url) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.key_url,t.one,t.two,t.three,t.four,t.five,t.six,t.seven,t.eight,t.nine,t.zero from ODS.Mayao_Key t  where key_url='"+key_url+"'";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 11;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	

	/**
	 * 拼写sql语句并获取开始和结束
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selecttvplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name 电视剧,t.years 制作年代,t.issuing_license from edw.dim_tvplay t where t.tvplay_id>114 order by t.tvplay_id ";
		
//		sql="select t1.tvplay_id,( case when t2.data_type=2  then t1.tvplay_name||'电视剧' else  t1.tvplay_name end  )as tvplatname,t2.* from (select t.tvplay_id,t.tvplay_name from         mart.f_tvplay_index t left join         (select nr.tvplay_id,count( distinct nr.data_type) as counts                 from ods.tem_network_reputation nr               where nr.data_type in (5,6)  and nr.date_date = to_char(sysdate-1,'yyyymmdd') and nr.tv_type = 0              group by nr.tvplay_id              ) n on t.tvplay_id = n.tvplay_id and n.counts = 2             where t.data_date = '29991231' and n.tvplay_id is null    order by nvl(t.complex_index,0) desc ) t1 left JOIN (select data_id,data_name,data_type from edw.del_data_flag where data_type=2 ) t2 on t1.tvplay_id =t2.data_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	/**
	 * 拼写sql语句并获取开始和结束
	 * 搜狗数据
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectshougoutvplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name from edw.f_tvplay_record t  where t.project_date_back >='20130601'";
		
//		sql="select t.tvplay_id,t.tvplay_name from EDW.F_TVPLAY_RECORD t where t.tvplay_name = '老子传奇'";
		
//		sql="select t.tvplay_id,t.tvplay_name from edw.f_tvplay_record t where t.tvplay_name = '龙珠传奇'";
		
//		sql="select t1.tvplay_id,( case when t2.data_type=2  then t1.tvplay_name||'电视剧' else  t1.tvplay_name end  )as tvplatname,t2.* from (select t.tvplay_id,t.tvplay_name from         mart.f_tvplay_index t left join         (select nr.tvplay_id,count( distinct nr.data_type) as counts                 from ods.tem_network_reputation nr               where nr.data_type in (5,6)  and nr.date_date = to_char(sysdate-1,'yyyymmdd') and nr.tv_type = 0              group by nr.tvplay_id              ) n on t.tvplay_id = n.tvplay_id and n.counts = 2             where t.data_date = '29991231' and n.tvplay_id is null    order by nvl(t.complex_index,0) desc ) t1 left JOIN (select data_id,data_name,data_type from edw.del_data_flag where data_type=2 ) t2 on t1.tvplay_id =t2.data_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	
	public static void main(String[] args) {
		TEM_WEIBO_TOPIC_SCORE tem_weibo_topic_score=new TEM_WEIBO_TOPIC_SCORE();
		Inserttem_weibo_topic_score(tem_weibo_topic_score);
	}

	public static void InsertTEM_IQIYI_AND_YOUKU_WORD_INDEX(TEM_IQIYI_AND_YOUKU_WORD_INDEX iqiyi) {
		// TODO Auto-generated method stub
		
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_IQIYI_AND_YOUKU_WORD_INDEX t (t.data_date,t.data_id,t.word,t.LABEL_NAME,t.LABEL_RATE,t.INTO_DATE,t.DIMENSION_TYPE,t.SOURCE)values (?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
		List<Comparable> list = new ArrayList();
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(iqiyi.getDataId());
		list.add(iqiyi.getWord());
		list.add(iqiyi.getLabelName());
		list.add(iqiyi.getLabelRate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(iqiyi.getDimensionType());
		list.add(iqiyi.getSource());

		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
		
	}
	
	
	public static ArrayList<String> selec_tvplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_name from ODS.DEL_TVPLAY_NAME_TEM t where t.type = 1 and (t.label_name like '%民国%' or t.label_name like '%寻宝%') group by t.tvplay_name";
		
//		sql="select t.tvplay_id,t.tvplay_name from EDW.F_TVPLAY_RECORD t where t.tvplay_name = '老子传奇'";
		
//		sql="select t.tvplay_id,t.tvplay_name from edw.f_tvplay_record t where t.tvplay_name = '龙珠传奇'";
		
//		sql="select t1.tvplay_id,( case when t2.data_type=2  then t1.tvplay_name||'电视剧' else  t1.tvplay_name end  )as tvplatname,t2.* from (select t.tvplay_id,t.tvplay_name from         mart.f_tvplay_index t left join         (select nr.tvplay_id,count( distinct nr.data_type) as counts                 from ods.tem_network_reputation nr               where nr.data_type in (5,6)  and nr.date_date = to_char(sysdate-1,'yyyymmdd') and nr.tv_type = 0              group by nr.tvplay_id              ) n on t.tvplay_id = n.tvplay_id and n.counts = 2             where t.data_date = '29991231' and n.tvplay_id is null    order by nvl(t.complex_index,0) desc ) t1 left JOIN (select data_id,data_name,data_type from edw.del_data_flag where data_type=2 ) t2 on t1.tvplay_id =t2.data_id ";
		
		
		
		sql="select t.tvplay_name,max(d.tvplay_id) as tvplay_id   from ODS.DEL_TVPLAY_NAME_TEM t left join mart.dim_tvplay d on t.tvplay_name = d.tvplay_name  where t.type = 3    and (t.label_name like '%民国%' or t.label_name like '%寻宝%')  group by t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}

	public static void InsertTVPLAY_IQIYI_INDEX(TVPLAY_IQIYI_INDEX tvplayindex) {
		// TODO Auto-generated method stub
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TVPLAY_IQIYI_INDEX t (t.TVPLAY_NAME,t.V_COUNT,t.data_date,t.DATA_URL,t.CREATE_DATE)values (?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tvplayindex.getTvplayName());
		list.add(tvplayindex.getVCount());
		list.add(tvplayindex.getDataDate());
		list.add(tvplayindex.getDataUrl());
//		list.add(tvplayindex.get);
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
		
	}
	
	
	public static void InsertTVPLAY_IQIYI_INDEX_SOURCE(TVPLAY_IQIYI_INDEX tvplayindex) {
		// TODO Auto-generated method stub
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TVPLAY_IQIYI_INDEX t (t.TVPLAY_NAME,t.V_COUNT,t.data_date,t.DATA_URL,t.CREATE_DATE,SOURCE)values (?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tvplayindex.getTvplayName());
		list.add(tvplayindex.getVCount());
		list.add(tvplayindex.getDataDate());
		list.add(tvplayindex.getDataUrl());
//		list.add(tvplayindex.get);
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(tvplayindex.getSOURCE());

		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
		
	}

	public static void InsertTEM_PERSON_URL(TEM_PERSON_URL personurl) {
		// TODO Auto-generated method stub
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_URL t (t.PERSON_ID,t.PERSON_NAME,t.PERSON_URL,t.TYPE,t.UP_DATE)values (?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(personurl.getPersonId());
		list.add(personurl.getPersonName());
		list.add(personurl.getPersonUrl());
		list.add(personurl.getType());
//		list.add(tvplayindex.get);
		list.add(TimeTest.getNowTime("yyyyMMdd"));

		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	

	public static void InsertTEM_PERSON_URL_WORKS(TEM_PERSON_URL_WORKS work) {
		// TODO Auto-generated method stub
		
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_URL_WORKS t (t.PERSON_ID,t.PERSON_NAME,t.PERSON_URL,t.DATE_NAME,t.DATE_URL,"
				+ "DATE_DIRECTOR,DATE_MAJOR_ACTORS,UPDATE_TIME,SOURCE,DATA_TYPE,DATE_TIME)values (?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(work.getPersonId());
		list.add(work.getPersonName());
		list.add(work.getPersonUrl());
		list.add(work.getDateName());
		
		list.add(work.getDateUrl());
		list.add(work.getDateDirector());
		list.add(work.getDateMajorActors());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(work.getSource());
		list.add(work.getDataType());
		list.add(work.getDateTime());
//		list.add(work.getDateName());
//		list.add(work.getDateName());
//		list.add(tvplayindex.get);

		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
	}
	
	
	
	public static ArrayList<String> selec_mart_dim_person() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name,t.actor_new_works from mart.dim_person t";
		
		sql="select t.person_id,t.person_name,t.actor_new_works from mart.dim_person t where t.actor_new_works is not null";
		
//		sql="select t.tvplay_id,t.tvplay_name from EDW.F_TVPLAY_RECORD t where t.tvplay_name = '老子传奇'";
		
//		sql="select t.tvplay_id,t.tvplay_name from edw.f_tvplay_record t where t.tvplay_name = '龙珠传奇'";
		
//		sql="select t1.tvplay_id,( case when t2.data_type=2  then t1.tvplay_name||'电视剧' else  t1.tvplay_name end  )as tvplatname,t2.* from (select t.tvplay_id,t.tvplay_name from         mart.f_tvplay_index t left join         (select nr.tvplay_id,count( distinct nr.data_type) as counts                 from ods.tem_network_reputation nr               where nr.data_type in (5,6)  and nr.date_date = to_char(sysdate-1,'yyyymmdd') and nr.tv_type = 0              group by nr.tvplay_id              ) n on t.tvplay_id = n.tvplay_id and n.counts = 2             where t.data_date = '29991231' and n.tvplay_id is null    order by nvl(t.complex_index,0) desc ) t1 left JOIN (select data_id,data_name,data_type from edw.del_data_flag where data_type=2 ) t2 on t1.tvplay_id =t2.data_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	public static ArrayList<String> selec_mart_dim_person_TEM_PERSON_URL() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name,t.actor_new_works from mart.dim_person t";
		
		sql="select max(person_id),max(person_name),max(person_url) from ods.TEM_PERSON_URL t where t.type=1 and t.up_date='20170111' group by t.person_url";
		
		
		sql=" select person_id,person_name,person_url from (select distinct * from ods.TEM_PERSON_URL t  where t.type = 1) tt1 where tt1.person_id not in (select person_id  from ODS.TEM_PERSON_URL_WORKS t where t.source = 1 group by t.person_id)";
		
//		sql="select t.tvplay_id,t.tvplay_name from EDW.F_TVPLAY_RECORD t where t.tvplay_name = '老子传奇'";
		
//		sql="select t.tvplay_id,t.tvplay_name from edw.f_tvplay_record t where t.tvplay_name = '龙珠传奇'";
		
//		sql="select t1.tvplay_id,( case when t2.data_type=2  then t1.tvplay_name||'电视剧' else  t1.tvplay_name end  )as tvplatname,t2.* from (select t.tvplay_id,t.tvplay_name from         mart.f_tvplay_index t left join         (select nr.tvplay_id,count( distinct nr.data_type) as counts                 from ods.tem_network_reputation nr               where nr.data_type in (5,6)  and nr.date_date = to_char(sysdate-1,'yyyymmdd') and nr.tv_type = 0              group by nr.tvplay_id              ) n on t.tvplay_id = n.tvplay_id and n.counts = 2             where t.data_date = '29991231' and n.tvplay_id is null    order by nvl(t.complex_index,0) desc ) t1 left JOIN (select data_id,data_name,data_type from edw.del_data_flag where data_type=2 ) t2 on t1.tvplay_id =t2.data_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}

	public static void InsertTEM_TEM_CHINA_NETWORK_VIDEO_INDEX(TEM_CHINA_NETWORK_VIDEO_INDEX index) {
		// TODO Auto-generated method stub
		
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_CHINA_NETWORK_VIDEO_INDEX t (t.DATA_DATE,t.DATA_ID,t.DATA_NAME,t.PLAY_TITLE,t.PLAY_NUM,"
				+ "PLAY_TOP,PLAY_STEP,COMMENTS,COLLECTION,DISPLAY_OUT,INTO_DATE,TYPE)values (?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(index.getDataId());
		list.add(index.getDataName());
		list.add(index.getPlayTitle());
		list.add(index.getPlayNum());
		
		list.add(index.getPlayTop());
		list.add(index.getPlayStep());
		list.add(index.getComments());
		list.add(index.getCollection());
		list.add(index.getDisplayOut());
//		list.add(index.getIntoDate());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(index.getType());

		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
	}

}
