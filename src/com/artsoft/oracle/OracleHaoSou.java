package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.Persion;
import com.artsoft.bean.TEM_DIM_ENTRYIMG;
import com.artsoft.bean.TEM_DIM_FILM;
import com.artsoft.bean.TEM_PLAY_PERSON;
import com.artsoft.bean.TvPlay;
import com.artsoft.bean.tem_person_works;
import com.artsoft.util.TimeTest;

public class OracleHaoSou {

	/**
	 * 拼写sql语句并获取开始和结束
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> select(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name 电视剧,t.years 制作年代,t.issuing_license from edw.dim_tvplay t order by t.tvplay_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	/**
	 * 拼写sql语句电影数据
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectdim_film(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name 电视剧,t.years 制作年代,t.issuing_license from edw.dim_tvplay t order by t.tvplay_id ";
		
		sql = "select t.film_id,t.film_name,t.FILM_URL from ods.dim_film t order by t.film_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		return (ArrayList<String>) list;

	}
	
	
	/**
	 * 拼写sql语句网络剧数据
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectdim_film_wangluoju(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name 电视剧,t.years 制作年代,t.issuing_license from edw.dim_tvplay t order by t.tvplay_id ";
		
		sql = " select t.tvplay_id,t.tvplay_name,t.tvplay_url from ODS.DIM_NETWORK_TVPLAY t order by t.tvplay_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		return (ArrayList<String>) list;

	}
	
	
	/**
	 * 拼写sql语句综艺数据
	 * 2016年6月17日17:12:11
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectdim_film_zhongyi(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name 电视剧,t.years 制作年代,t.issuing_license from edw.dim_tvplay t order by t.tvplay_id ";
		
		sql = " select t.tvplay_id,t.tvplay_name,t.tvplay_url from ODS.DIM_NETWORK_TVPLAY t order by t.tvplay_id";
		sql="select * from   ODS.DIM_NETWORK_VARIETY t order by t.tvplay_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		return (ArrayList<String>) list;

	}
	
	
	

	/**
	 * sql语句并获取开始和结束 dao用户列表中
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectname(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name from ODS.DIM_PERSON t order by t.person_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	


	/**
	 * 入电视剧搜索量的库
	 * 
	 * @param tvplayId
	 * @param tyPlayName
	 * @param dataAmount
	 * @param videoType
	 * @param palydate
	 * @param playUrl
	 * @param tvType
	 * @param DataType
	 * @param createTime
	 */
	public static void intoPlayAmont(String tvplayId, String tyPlayName, String dataAmount, String videoType,
			String palydate, String playUrl, int tvType, String DataType, String createTime) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(tvplayId + "tyPlayName" + tyPlayName + "dataAmount" + dataAmount + "videoType" + videoType
				+ "palydate" + palydate + "playUrl" + playUrl + tvType + DataType + createTime);

		String strSql = "insert into ods.TEM_NETWORK_REPUTATION t (t.tvplay_id,t.tvplay_name,t.data_Amount ,t.video_type,t.date_Date ,t.play_url, t.tv_type ,t.Data_type,t.CREATE_DATE) "
				+ "VALUES (?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";

		try {

			List<Comparable> list = new ArrayList();
			list.add(Integer.parseInt(tvplayId));// 这里是将对象加入到list中
			list.add(tyPlayName);
			list.add(Double.parseDouble(dataAmount));
			list.add(Integer.parseInt(videoType));
			list.add(palydate);
			list.add(playUrl);
			list.add(tvType);
			list.add(Integer.parseInt(DataType));
			list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			boolean bb = DBOperate218.insertRecord(conn, strSql, list);
			System.out.println(bb);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 人的添加
	 * 
	 * @param personId
	 * @param searchIndex
	 * @param updateDate
	 * @param createDate
	 * @param dataUrl
	 * @param dateType
	 */
	public static void intoPeoPle(String personId, String searchIndex, String updateDate, String createDate,
			String dataUrl, String dateType) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(personId + searchIndex + updateDate + createDate + dataUrl + dateType);

		String strSql = "insert into ods.person_network_popularity t (t.person_id,t.search_index,t.date_date,t.create_date,t.data_url,t.date_type) "
				+ "values(?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(personId));// 这里是将对象加入到list中
		list.add(Integer.parseInt(searchIndex));
		list.add(updateDate);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(dataUrl);
		list.add(dateType);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 添加人相关的微博信息 PERSON_ID NUMBER(18) Y 演职人员id WEIBO_UID VARCHAR2(200) Y 微博的uid
	 * FANS_COUNT NUMBER(18) Y 粉丝量 V_COUNT NUMBER(18) Y 微博总数 SEARCH_INDEX
	 * NUMBER(18) Y 搜索指数/媒体关注度 UPDATE_DATE TIMESTAMP(6) Y 数据抓取时间 CREATE_DATE
	 * TIMESTAMP(6) Y 数据入库时间 DATA_URL VARCHAR2(600) Y 数据采取url DATE_TYPE
	 * NUMBER(2) Y 数据类型，1 微博数据(搜索指数为空) 2 搜索指数 (微博相关数据为空) 3 媒体关注度
	 * 
	 * @return
	 */

	public static void intoPeoPlewebo(String personId, String weiboUid, int fansCount, int vCountNumber,
			String updateDate, String createDate, String dataUrl, String dateType) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out
				.println(personId + weiboUid + fansCount + vCountNumber + updateDate + createDate + dataUrl + dateType);

		String strSql = "insert into ods.person_network_popularity t (t.person_id,t.WEIBO_UID,t.FANS_COUNT ,t.V_COUNT,t.update_date,t.create_date,t.data_url,t.date_type) "
				+ "values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(personId));// 这里是将对象加入到list中
		list.add(weiboUid);
		list.add(fansCount);
		list.add(vCountNumber);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(dataUrl);
		list.add(dateType);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 
	 * @param personName
	 *            名字
	 * @param personUrl
	 *            该用户的详细链接
	 * @param photoUrl
	 *            图片地址
	 */
	public static void intoBaiDuPopularity(String personName, String personUrl, String photoUrl) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(personName + personUrl + photoUrl);

		String strSql = "insert into ODS.DIM_PERSON_PHOTO t(t.PERSON_NAME, t.PERSON_URL ,t.PHOTO_URL) values(?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(personName);// 这里是将对象加入到list中
		list.add(personUrl);
		list.add(photoUrl);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 进行百度人物的添加
	 * 
	 * @param persion
	 */
	public static void InsertTemDimPerson(Persion persion) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.TEM_DIM_PERSON t (t.person_id, t.person_name,t.img_url,t.person_url ,"
				+ "t.SEX ,t.NATIONALITY,t.bloodtype ,t.height,t.weight,t.occupation ,t.constellation ,t.birthday,"
				+ "t.deathday,t.alias_en,t.alias_cn,t.homeplace,t.nation,t.major_awards,t.shcool,t.brokerage_firm,"
				+ "t.opus,t.sub_path,t.evaluation,t.birthday_place ,t.social_activities,t.flowers,t.volk,t.brokers,t.hobby) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		strSql = "insert into ODS.TEM_DIM_PERSON t (t.person_id, t.person_name,t.img_url,t.person_url ,"
				+ "t.SEX ,t.NATIONALITY,t.bloodtype ,t.height,t.weight,t.occupation ,t.constellation ,t.birthday,"
				+ "t.deathday,t.alias_en,t.alias_cn,t.homeplace,t.major_awards,t.shcool,t.brokerage_firm,"
				+ "t.opus,t.sub_path,t.evaluation,t.birthday_place ,t.social_activities,t.flowers,t.volk,t.brokers,t.hobby，t.basic_info，t.UPDATE_TIME) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(persion.getId());
		list.add(persion.getName());
		list.add(persion.getImg_url());
		list.add(persion.getUrl());
		list.add(persion.getGender());
		list.add(persion.getNationality());
		list.add(persion.getBloodtype());
		list.add(persion.getHeight());
		list.add(persion.getWeight());
		list.add(persion.getOccupation());
		list.add(persion.getConstellation());
		list.add(persion.getBirthday());
		list.add(persion.getDeathday());
		list.add(persion.getAlias_en());
		list.add(persion.getAlias_cn());
		list.add(persion.getHomeplace());
		// list.add(persion.getNation());
		list.add(persion.getMajor_awards());
		list.add(persion.getShcool());
		list.add(persion.getBrokerage_firm());
		list.add(persion.getOpus());
		list.add(persion.getSub_path());
		list.add(persion.getEvaluation());
		list.add(persion.getBirthday_place());
		list.add(persion.getSocial_activities());
		list.add(persion.getFlowers());
		list.add(persion.getVolk());
		list.add(persion.getBrokers());
		list.add(persion.getHobby());
		list.add(persion.getDescription_text());
		// list.add(persion.getPersonSocialActivitiesList());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 进行百度数据的数据的 修改操作
	 * 
	 * @param persion
	 */
	public static void upTemDimPerson(Persion persion) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "update ods.tem_dim_person t set t.occupation=?,t.major_awards=?,t.basic_info=? where t.person_id=?";
		List<Comparable> list = new ArrayList();
		list.add(persion.getOccupation());
		list.add(persion.getMajor_awards());
		list.add(persion.getDescription_text());
		list.add(persion.getId());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 进行百度人物的添加
	 * 
	 * @param persion
	 */
	public static void UpdatePerson(Persion persion) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.TEM_DIM_PERSON t (t.person_id, t.person_name,t.img_url,t.person_url ,"
				+ "t.SEX ,t.NATIONALITY,t.bloodtype ,t.height,t.weight,t.occupation ,t.constellation ,t.birthday,"
				+ "t.deathday,t.alias_en,t.alias_cn,t.homeplace,t.nation,t.major_awards,t.shcool,t.brokerage_firm,"
				+ "t.opus,t.sub_path,t.evaluation,t.birthday_place ,t.social_activities,t.flowers,t.volk,t.brokers,t.hobby) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		/**
		 * 修改操作，整体修改
		 */
		strSql = "UPDATE ODS.TEM_DIM_PERSON t SET t.person_name =?, t.img_url = ?, t.person_url = ?,"
				+ " t.SEX = ?, t.NATIONALITY = ?, t.bloodtype = ?, t.height = ?, t.weight = ?,"
				+ " t.occupation = ?, t.constellation = ?, t.birthday = ?, t.deathday = ?,"
				+ " t.alias_en = ?, t.alias_cn = ?, t.homeplace = ?, t.major_awards = ?, t.shcool = ?,"
				+ " t.brokerage_firm = ?, t.opus = ?, t.sub_path = ?, t.evaluation = ?, t.birthday_place = ?,"
				+ " t.social_activities = ?, t.flowers = ?, t.volk = ?, t.brokers = ?, t.hobby = ?,"
				+ " t.basic_info = ? WHERE t.person_id = ?";
		List<Comparable> list = new ArrayList();
		list.add(persion.getName());
		list.add(persion.getImg_url());
		list.add(persion.getUrl());
		list.add(persion.getGender());
		list.add(persion.getNationality());
		list.add(persion.getBloodtype());
		list.add(persion.getHeight());
		list.add(persion.getWeight());
		list.add(persion.getOccupation());
		list.add(persion.getConstellation());
		list.add(persion.getBirthday());
		list.add(persion.getDeathday());
		list.add(persion.getAlias_en());
		list.add(persion.getAlias_cn());
		list.add(persion.getHomeplace());
		// list.add(persion.getNation());
		list.add(persion.getMajor_awards());
		list.add(persion.getShcool());
		list.add(persion.getBrokerage_firm());
		list.add(persion.getOpus());
		list.add(persion.getSub_path());
		list.add(persion.getEvaluation());
		list.add(persion.getBirthday_place());
		list.add(persion.getSocial_activities());
		list.add(persion.getFlowers());
		list.add(persion.getVolk());
		list.add(persion.getBrokers());
		list.add(persion.getHobby());
		list.add(persion.getDescription_text());
		// list.add(persion.getPersonSocialActivitiesList());
		list.add(persion.getId());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 进行电视剧数据 的添加
	 * 
	 * @param tvplay
	 */
	public static void InsertTVplay(TvPlay tvplay) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_tvplay t(t.tvplay_id,t.tvplay_name,t.tvplay_url,t.alias_en,t.alias_cn,"
				+ "t.major_actors,t.major_awards,t.director,t.screenwriTer,t.producer,t.production_company,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.produced_company,"
				+ "t.production_area,t.premiere_time,t.pages,t.time_length,t.play_platform ,t.premiere_platform,"
				+ "t.photography_director,t.total_production,t.production_chairman,t.production_cost,t.play_theater,"
				+ "t.before_teleplay,t.next_teleplay,t.open_time,t.close_time,t.total_planning,t.film_time,t.box_office,"
				+ "t.type,t.compere,t.total_sponsor,t.partners,t.special_support,t.social_platform,t.guest_program,t.season_number,"
				+ "t.recording_place,t.stills_url,t.UPDATE_TIME,t.BAIKE_FILM_NAME) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		strSql = "insert into ods.tem_tvplay t(t.tvplay_id,t.tvplay_name,t.tvplay_url,t.alias_en,t.alias_cn,"
				+ "t.major_actors,t.major_awards,t.director,t.screenwriTer,t.producer,t.production_company,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.produced_company,"
				+ "t.production_area,t.premiere_time,t.pages,t.time_length,t.play_platform ,t.premiere_platform,"
				+ "t.photography_director,t.total_production,t.production_chairman,t.production_cost,t.play_theater,"
				+ "t.before_teleplay,t.next_teleplay,t.open_time,t.close_time,t.total_planning,t.film_time,t.box_office,"
				+ "t.type,t.compere,t.total_sponsor,t.partners,t.special_support,t.social_platform,t.guest_program,t.season_number,"
				+ "t.recording_place,t.stills_url,t.UPDATE_TIME,t.BAIKE_FILM_NAME,t.PRESENTER,t.AIR_TIME) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		
		List<Comparable> list = new ArrayList();
		list.add(tvplay.getTvplay_id());
		list.add(tvplay.getTvplay_name());
		list.add(tvplay.getTvplay_url());
		list.add(tvplay.getAlias_en());
		list.add(tvplay.getAlias_cn());
		list.add(tvplay.getMajor_actors());
		list.add(tvplay.getMajor_awards());
		list.add(tvplay.getDirector());
		list.add(tvplay.getScreenwriter());
		list.add(tvplay.getProducer());
		list.add(tvplay.getProduction_company());
		list.add(tvplay.getIssuing_company());
		list.add(tvplay.getShoot_time());
		list.add(tvplay.getShoot_place());
		list.add(tvplay.getSubject());
		list.add(tvplay.getProduced_time());
		list.add(tvplay.getProduced_company());
		list.add(tvplay.getProduction_area());
		list.add(tvplay.getPremiere_time());
		list.add(tvplay.getPages());
		list.add(tvplay.getTime_length());
		list.add(tvplay.getPlay_platform());
		list.add(tvplay.getPremiere_platform());
		list.add(tvplay.getPhorogrphy_director());
		list.add(tvplay.getTotal_production());
		list.add(tvplay.getProduction_chairman());
		list.add(tvplay.getProduction_cost());
		list.add(tvplay.getPlay_theater());
		list.add(tvplay.getBefore_eleplay());
		list.add(tvplay.getNext_teleplay());
		list.add(tvplay.getOpen_time());
		list.add(tvplay.getClose_time());
		list.add(tvplay.getTotal_planning());
		list.add(tvplay.getFilm_time());
		list.add(tvplay.getBox_office());
		 list.add(tvplay.getType());
		list.add(tvplay.getCompere());
		list.add(tvplay.getTotal_sponsor());
		list.add(tvplay.getPartners());
		list.add(tvplay.getSpecial_support());
		list.add(tvplay.getSocial_platform());
		list.add(tvplay.getGuest_program());
		list.add(tvplay.getSeason_numbver());
		list.add(tvplay.getRecording_place());
		list.add(tvplay.getStills_url());
		// list.add(persion.getPersonSocialActivitiesList());
		// 增加添加时间 、、2016年2月26日17：:4：:1
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(tvplay.getBaikefilmname());
		list.add(tvplay.getPRESENTER());
		list.add(tvplay.getAIR_TIME());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	
	/**
	 * 进行网络剧数据 的添加
	 * 2016年6月30日16:04:15
	 * @param tvplay
	 */
	public static void Insertwangluoju(TvPlay tvplay) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_tvplay t(t.tvplay_id,t.tvplay_name,t.tvplay_url,t.alias_en,t.alias_cn,"
				+ "t.major_actors,t.major_awards,t.director,t.screenwriTer,t.producer,t.production_company,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.produced_company,"
				+ "t.production_area,t.premiere_time,t.pages,t.time_length,t.play_platform ,t.premiere_platform,"
				+ "t.photography_director,t.total_production,t.production_chairman,t.production_cost,t.play_theater,"
				+ "t.before_teleplay,t.next_teleplay,t.open_time,t.close_time,t.total_planning,t.film_time,t.box_office,"
				+ "t.type,t.compere,t.total_sponsor,t.partners,t.special_support,t.social_platform,t.guest_program,t.season_number,"
				+ "t.recording_place,t.stills_url,t.UPDATE_TIME,t.BAIKE_FILM_NAME) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		strSql = "insert into ods.tem_tvplay t(t.tvplay_id,t.tvplay_name,t.tvplay_url,t.alias_en,t.alias_cn,"
				+ "t.major_actors,t.major_awards,t.director,t.screenwriTer,t.producer,t.production_company,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.produced_company,"
				+ "t.production_area,t.premiere_time,t.pages,t.time_length,t.play_platform ,t.premiere_platform,"
				+ "t.photography_director,t.total_production,t.production_chairman,t.production_cost,t.play_theater,"
				+ "t.before_teleplay,t.next_teleplay,t.open_time,t.close_time,t.total_planning,t.film_time,t.box_office,"
				+ "t.type,t.compere,t.total_sponsor,t.partners,t.special_support,t.social_platform,t.guest_program,t.season_number,"
				+ "t.recording_place,t.stills_url,t.UPDATE_TIME,t.BAIKE_FILM_NAME,t.PRESENTER,t.AIR_TIME) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		strSql="insert into ods.TEM_DIM_NETWORK_PLAY t(t.tvplay_id,t.tvplay_name,t.tvplay_url,t.alias_en,t.alias_cn,"
				+ "t.major_actors,t.major_awards,t.director,t.screenwriTer,t.producer,t.production_company,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.produced_company,"
				+ "t.production_area,t.premiere_time,t.pages,t.time_length,t.play_platform ,t.premiere_platform,"
				+ "t.photography_director,t.total_production,t.production_chairman,t.production_cost,t.play_theater,"
				+ "t.open_time,t.close_time,t.total_planning,t.film_time,"
				+ "t.recording_place,t.stills_url,t.BASIC_INFO,t.UPDATE_TIME,t.BAIKE_NAME,t.SHOW_DATE) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tvplay.getTvplay_id());
		list.add(tvplay.getTvplay_name());
		list.add(tvplay.getTvplay_url());
		list.add(tvplay.getAlias_en());
		list.add(tvplay.getAlias_cn());
		list.add(tvplay.getMajor_actors());
		list.add(tvplay.getMajor_awards());
		list.add(tvplay.getDirector());
		list.add(tvplay.getScreenwriter());
		list.add(tvplay.getProducer());
		list.add(tvplay.getProduction_company());
		list.add(tvplay.getIssuing_company());
		list.add(tvplay.getShoot_time());
		list.add(tvplay.getShoot_place());
		list.add(tvplay.getSubject());
		list.add(tvplay.getProduced_time());
		list.add(tvplay.getProduced_company());
		list.add(tvplay.getProduction_area());
		list.add(tvplay.getPremiere_time());
		list.add(tvplay.getPages());
		list.add(tvplay.getTime_length());
		list.add(tvplay.getPlay_platform());
		list.add(tvplay.getPremiere_platform());
		list.add(tvplay.getPhorogrphy_director());
		list.add(tvplay.getTotal_production());
		list.add(tvplay.getProduction_chairman());
		list.add(tvplay.getProduction_cost());
		list.add(tvplay.getPlay_theater());
//		list.add(tvplay.getBefore_eleplay());
//		list.add(tvplay.getNext_teleplay());
		list.add(tvplay.getOpen_time());
		list.add(tvplay.getClose_time());
		list.add(tvplay.getTotal_planning());
		list.add(tvplay.getFilm_time());
//		list.add(tvplay.getBox_office());
//		 list.add(tvplay.getType());
//		list.add(tvplay.getCompere());
//		list.add(tvplay.getTotal_sponsor());
//		list.add(tvplay.getPartners());
//		list.add(tvplay.getSpecial_support());
//		list.add(tvplay.getSocial_platform());
//		list.add(tvplay.getGuest_program());
//		list.add(tvplay.getSeason_numbver());
		list.add(tvplay.getRecording_place());
		list.add(tvplay.getStills_url());
		// list.add(persion.getPersonSocialActivitiesList());
		// 增加添加时间 、、2016年2月26日17：:4：:1
		list.add(tvplay.getBasic_info());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(tvplay.getBaikefilmname());
//		list.add(tvplay.getPRESENTER());
		list.add(tvplay.getAIR_TIME());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	


	/**
	 * 进行综艺数据 的添加
	 * 2016年6月30日16:05:29
	 * @param tvplay
	 */
	public static void Insertzhongyi(TvPlay tvplay) {
		Connection conn = DBOperate218.getInstance().getConnection();
		
		
		String strSql = "insert into ods.TEM_DIM_VARIETY t(t.tvplay_id,t.tvplay_name,t.tvplay_url,t.alias_en,t.alias_cn,"
				+ "t.major_actors,t.major_awards,t.director,t.screenwriTer,t.producer,t.production_company,"
				+ "t.issuing_company,t.shoot_time,t.shoot_place,t.subject,t.produced_time,t.produced_company,"
				+ "t.production_area,t.premiere_time,t.pages,t.time_length,t.play_platform ,t.premiere_platform,"
				+ "t.photography_director,t.total_production,t.production_chairman,t.production_cost,t.play_theater,"
				+ "t.total_planning,t.film_time,t.total_sponsor,t.partners,t.special_support,t.social_platform,t.guest_program,t.season_number,"
				+ "t.recording_place,t.stills_url,t.BASIC_INFO,t.UPDATE_TIME,t.PRESENTER,t.AIR_TIME,ETL_NAME,BAIKE_NAME) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		
		List<Comparable> list = new ArrayList();
		list.add(tvplay.getTvplay_id());
		list.add(tvplay.getTvplay_name());
		list.add(tvplay.getTvplay_url());
		list.add(tvplay.getAlias_en());
		list.add(tvplay.getAlias_cn());
		list.add(tvplay.getMajor_actors());
		list.add(tvplay.getMajor_awards());
		list.add(tvplay.getDirector());
		list.add(tvplay.getScreenwriter());
		list.add(tvplay.getProducer());
		list.add(tvplay.getPRODUCE_COMPANY());
		list.add(tvplay.getIssuing_company());
		list.add(tvplay.getShoot_time());
		list.add(tvplay.getShoot_place());
		list.add(tvplay.getSubject());
		list.add(tvplay.getProduced_time());
		list.add(tvplay.getProduced_company());
		list.add(tvplay.getProduction_area());
		list.add(tvplay.getPremiere_time());
		list.add(tvplay.getPages());
		list.add(tvplay.getTime_length());
		list.add(tvplay.getPlay_platform());
		list.add(tvplay.getPremiere_platform());
		list.add(tvplay.getPhorogrphy_director());
		list.add(tvplay.getTotal_production());
		list.add(tvplay.getProduction_chairman());
		list.add(tvplay.getProduction_cost());
		list.add(tvplay.getPlay_theater());
//		list.add(tvplay.getBefore_eleplay());
//		list.add(tvplay.getNext_teleplay());
//		list.add(tvplay.getOpen_time());
//		list.add(tvplay.getClose_time());
		list.add(tvplay.getTotal_planning());
		list.add(tvplay.getFilm_time());
//		list.add(tvplay.getBox_office());
//		 list.add(tvplay.getType());
//		list.add(tvplay.getCompere());
		list.add(tvplay.getTotal_sponsor());
		list.add(tvplay.getPartners());
		list.add(tvplay.getSpecial_support());
		list.add(tvplay.getSocial_platform());
		list.add(tvplay.getGuest_program());
		list.add(tvplay.getSeason_numbver());
		list.add(tvplay.getRecording_place());
		list.add(tvplay.getStills_url());
		list.add(tvplay.getBasic_info());
		// list.add(persion.getPersonSocialActivitiesList());
		// 增加添加时间 、、2016年2月26日17：:4：:1
		list.add(TimeTest.getNowTime("yyyyMMdd"));
//		list.add(tvplay.getBaikefilmname());
		list.add(tvplay.getPRESENTER());
		list.add(tvplay.getAIR_TIME());
		list.add(tvplay.getETL_NAME());
		list.add(tvplay.getBAIKE_NAME());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * 进行电影
	 * 2016年6月6日14:13:14
	 * @param tvplay
	 */
	public static void InsertTEM_DIM_FILM(TEM_DIM_FILM movesfilm) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_dim_film t(t.film_id,t.film_name,t.film_url,t.english_name,t.alias_name,t.years,t.producer,t.produce_company,t.produce_cost,t.produce_area,t.produce_date,t.show_date,t.premiere_date,t.create_time,t.performance_form,t.historical_background,t.description,t.issue_organization,t.subject_name_one,t.issuing_license,t.director,t.actors,t.screenwriter,t.subject_name_two,t.award,t.shooting_location,t.shooting_date,t.time_long,t.languages,t.imdb_code,t.film_img_url,t.film_level,t.play_platform,t.original,t.tv_name_uniq,t.baike_film_name)  values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(movesfilm.getFilmId());
		list.add(movesfilm.getFilmName());
		list.add(movesfilm.getFilmUrl());
		list.add(movesfilm.getEnglishName());
		list.add(movesfilm.getAliasName());
		list.add(movesfilm.getYears());
		list.add(movesfilm.getProducer());
		list.add(movesfilm.getProduceCompany());
		list.add(movesfilm.getProduceCost());
		list.add(movesfilm.getProduceArea());
		list.add(movesfilm.getProduceDate());
		list.add(movesfilm.getShowDate());
		list.add(movesfilm.getPremiereDate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(movesfilm.getPerformanceForm());
		list.add(movesfilm.getHistoricalBackground());
		list.add(movesfilm.getDescription());
		list.add(movesfilm.getIssueOrganization());
		list.add(movesfilm.getSubjectNameOne());
		list.add(movesfilm.getIssuingLicense());
		list.add(movesfilm.getDirector());
		list.add(movesfilm.getActors());
		list.add(movesfilm.getScreenwriter());
		list.add(movesfilm.getSubjectNameTwo());
		list.add(movesfilm.getAward());
		list.add(movesfilm.getShootingLocation());
		list.add(movesfilm.getShootingDate());
		list.add(movesfilm.getTimeLong());
		list.add(movesfilm.getLanguages());
		list.add(movesfilm.getImdbCode());
		list.add(movesfilm.getFilmImgUrl());
		list.add(movesfilm.getFilmLevel());
		list.add(movesfilm.getPlayPlatform());
		list.add(movesfilm.getOriginal());
		list.add(movesfilm.getFilmName()+movesfilm.getYears()+movesfilm.getDirector());
		list.add(movesfilm.getBaikefilmname());
		
//		list.add(movesfilm.getBox_office());
//		// list.add(movesfilm.getType());
//		list.add(movesfilm.getCompere());
//		list.add(movesfilm.getTotal_sponsor());
//		list.add(movesfilm.getPartners());
//		list.add(movesfilm.getSpecial_support());
//		list.add(movesfilm.getSocial_platform());
//		list.add(movesfilm.getGuest_program());
//		list.add(movesfilm.getSeason_numbver());
//		list.add(movesfilm.getRecording_place());
//		list.add(movesfilm.getStills_url());
		// list.add(persion.getPersonSocialActivitiesList());
		// 增加添加时间 、、2016年2月26日17：:4：:1
//		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 进行电视剧数据 的修改
	 * 
	 * @param tvplay
	 */
	public static void UpdateTVplay(TvPlay tvplay) {
		Connection conn = DBOperate218.getInstance().getConnection();
		//
		String strSql = "update ods.tem_tvplay t set t.tvplay_name=?,t.tvplay_url=?,t.alias_en=?,t.alias_cn=?,"
				+ "t.major_actors=?,t.major_awards=?,t.director=?,t.screenwriTer=?,t.producer=?,t.production_company=?,"
				+ "t.issuing_company=?,t.shoot_time=?,t.shoot_place=?,t.subject=?,t.produced_time=?,t.produced_company=?,"
				+ "t.production_area=?,t.premiere_time=?,t.pages=?,t.time_length=?,t.play_platform =?,t.premiere_platform=?,"
				+ "t.photography_director=?,t.total_production=?,t.production_chairman=?,t.production_cost=?,"
				+ "t.play_theater=?,t.before_teleplay=?,t.next_teleplay=?,t.open_time=?,t.close_time=?,t.total_planning=?,"
				+ "t.film_time=?,t.box_office=?,t.type=1,t.compere=?,t.total_sponsor=?,t.partners=?,t.special_support=?,"
				+ "t.social_platform=?,t.guest_program=?,t.season_number=?,t.recording_place=?,t.stills_url=?"
				+ "where t.tvplay_id=?";
		List<Comparable> list = new ArrayList();
		list.add(tvplay.getTvplay_name());
		list.add(tvplay.getTvplay_url());
		list.add(tvplay.getAlias_en());
		list.add(tvplay.getAlias_cn());
		list.add(tvplay.getMajor_actors());
		list.add(tvplay.getMajor_awards());
		list.add(tvplay.getDirector());
		list.add(tvplay.getScreenwriter());
		list.add(tvplay.getProducer());
		list.add(tvplay.getProduction_company());
		list.add(tvplay.getIssuing_company());
		list.add(tvplay.getShoot_time());
		list.add(tvplay.getShoot_place());
		list.add(tvplay.getSubject());
		list.add(tvplay.getProduced_time());
		list.add(tvplay.getProduced_company());
		list.add(tvplay.getProduction_area());
		list.add(tvplay.getPremiere_time());
		list.add(tvplay.getPages());
		list.add(tvplay.getTime_length());
		list.add(tvplay.getPlay_platform());
		list.add(tvplay.getPremiere_platform());
		list.add(tvplay.getPhorogrphy_director());
		list.add(tvplay.getTotal_production());
		list.add(tvplay.getProduction_chairman());
		list.add(tvplay.getProduction_cost());
		list.add(tvplay.getPlay_theater());
		list.add(tvplay.getBefore_eleplay());
		list.add(tvplay.getNext_teleplay());
		list.add(tvplay.getOpen_time());
		list.add(tvplay.getClose_time());
		list.add(tvplay.getTotal_planning());
		list.add(tvplay.getFilm_time());
		list.add(tvplay.getBox_office());
		// list.add(tvplay.getType());
		list.add(tvplay.getCompere());
		list.add(tvplay.getTotal_sponsor());
		list.add(tvplay.getPartners());
		list.add(tvplay.getSpecial_support());
		list.add(tvplay.getSocial_platform());
		list.add(tvplay.getGuest_program());
		list.add(tvplay.getSeason_numbver());
		list.add(tvplay.getRecording_place());
		list.add(tvplay.getStills_url());
		list.add(tvplay.getTvplay_id());
		// list.add(persion.getPersonSocialActivitiesList());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	
	/**
	 * 进行电视剧数据 的部分修改修改
	 * 2016年4月21日11:32:36
	 * 
	 * @param tvplay
	 */
	public static void UpdatPpartTVplay(TvPlay tvplay) {
		Connection conn = DBOperate218.getInstance().getConnection();
		//
		String strSql = "update ods.tem_tvplay t set t.screenwriTer=?,t.producer=?"
				+ "where t.tvplay_id=?";
		List<Comparable> list = new ArrayList();

		list.add(tvplay.getScreenwriter());
		list.add(tvplay.getProducer());
		
		list.add(tvplay.getTvplay_id());
		// list.add(persion.getPersonSocialActivitiesList());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 进行电视剧数据 的添加
	 * 
	 * @param tvplay
	 */
	public static void InWangLuoTVplay(TvPlay tvplay) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.tem_NETWORK_TVPLAY t "
				+ "(t.netplay_name,t.english_name,t.alias_name,t.set_num,t.producer,t.produce_company,"
				+ "t.produce_area,t.produce_date,t.show_date, t.premiere_date,t.shot_start_date,t.SHOT_END_DATE,"
				+ "t.create_time,t.issue_organization,t.play_platform, t.netplay_subject,t.time_long,"
				+ "t.actors,t.director,t.screenwriter,t.basic_info,t.stills_url,t.NETPLAY_URL) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		// list.add(tvplay.getTvplay_id());
		list.add(tvplay.getTvplay_name());
		// list.add(tvplay.getTvplay_url());
		list.add(tvplay.getAlias_en());
		list.add(tvplay.getAlias_cn());
		if (tvplay.getPages() != null && !"".equals(tvplay.getPages())) {
			String jishu = tvplay.getPages().replace("集", "").replaceAll("\\D+", "");
			if (jishu != null && !"".equals(jishu)) {
				list.add(Integer.parseInt(jishu));
			} else {
				list.add(0);
			}
		} else {
			list.add(0);
		}
		//////////// 制片人
		list.add(tvplay.getProducer());
		// list.add(tvplay.getProduced_company());
		list.add(tvplay.getProduced_company());
		list.add(tvplay.getProduction_area());
		list.add(tvplay.getProduced_time());
		/////// 上映日期
		list.add(tvplay.getShow_date());
		list.add(tvplay.getPremiere_time());
		list.add(tvplay.getOpen_time());
		list.add(tvplay.getClose_time());
		////// 创建时间
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));

		///// 修改时间
		// list.add("");
		list.add(tvplay.getIssuing_company());
		list.add(tvplay.getPlay_platform());
		list.add(tvplay.getSubject());
		list.add(tvplay.getTime_length());
		list.add(tvplay.getMajor_actors());
		list.add(tvplay.getDirector());
		list.add(tvplay.getScreenwriter());
		list.add(tvplay.getBasic_info());
		list.add(tvplay.getStills_url());
		list.add(tvplay.getTvplay_url());

		// list.add(tvplay.getMajor_awards());
		// list.add(tvplay.getProducer());
		// list.add(tvplay.getProduction_company());
		// list.add(tvplay.getShoot_time());
		// list.add(tvplay.getShoot_place());
		// list.add(tvplay.getPremiere_platform());
		// list.add(tvplay.getPhorogrphy_director());
		// list.add(tvplay.getTotal_production());
		// list.add(tvplay.getProduction_chairman());
		// list.add(tvplay.getProduction_cost());
		// list.add(tvplay.getPlay_theater());
		// list.add(tvplay.getBefore_eleplay());
		// list.add(tvplay.getNext_teleplay());
		// list.add(tvplay.getTotal_planning());
		// list.add(tvplay.getFilm_time());
		// list.add(tvplay.getBox_office());
		// // list.add(tvplay.getType());
		// list.add(tvplay.getCompere());
		// list.add(tvplay.getTotal_sponsor());
		// list.add(tvplay.getPartners());
		// list.add(tvplay.getSpecial_support());
		// list.add(tvplay.getSocial_platform());
		// list.add(tvplay.getGuest_program());
		// list.add(tvplay.getSeason_numbver());
		// list.add(tvplay.getRecording_place());
		// // list.add(persion.getPersonSocialActivitiesList());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 進入数据进行角色介绍添加 和图片
	 * 
	 * @param tvplayid
	 * @param tvplayname
	 * @param tvplayurl
	 * @param personid
	 * @param personname
	 * @param personurl
	 * @param rolename
	 * @param personstillsurl
	 * @param dubbingname
	 * @param dubbingurl
	 * @param roleintro
	 */
	public static void intotemtvplay(String tvplayid, String tvplayname, String tvplayurl, String personid,
			String personname, String personurl, String rolename, String personstillsurl, String dubbingname,
			String dubbingurl, String roleintro,String PERSON_BIG_URL) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_tvplay_person t(t.tvplay_id,t.tvplay_name,t.tvplay_url,"
				+ "t.person_name,t.person_url,t.role_name,t.PERSON_SMALL_URL,t.dubbing_name,t.dubbing_url,"
				+ "t.role_intro,t.PERSON_BIG_URL,INTO_DATE)values(?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(tvplayname);
		list.add(tvplayurl);
		list.add(personname);
		list.add(personurl);
		list.add(rolename);
		list.add(personstillsurl);
		list.add(dubbingname);
		list.add(dubbingurl);
		list.add(roleintro);
		list.add(PERSON_BIG_URL);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	
	/**
	 * 進入数据进行角色介绍添加 和图片
	 * 
	 * @param tvplayid
	 * @param tvplayname
	 * @param tvplayurl
	 * @param personid
	 * @param personname
	 * @param personurl
	 * @param rolename
	 * @param personstillsurl
	 * @param dubbingname
	 * @param dubbingurl
	 * @param roleintro
	 * 
	 */
	public static void intotemtvplayall(String tvplayid, String tvplayname, String tvplayurl, String personid,
			String personname, String personurl, String rolename, String personstillsurl, String dubbingname,
			String dubbingurl, String roleintro,String PERSON_BIG_URL,int DATA_TYPE) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_tvplay_person t(t.tvplay_id,t.tvplay_name,t.tvplay_url,"
				+ "t.person_name,t.person_url,t.role_name,t.PERSON_SMALL_URL,t.dubbing_name,t.dubbing_url,"
				+ "t.role_intro,t.PERSON_BIG_URL,INTO_DATE,t.DATA_TYPE)values(?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		
		strSql = "insert into ods.tem_play_role_stills t(t.tvplay_id,t.tvplay_name,t.tvplay_url,"
				+ "t.person_name,t.person_url,t.role_name,t.PERSON_SMALL_URL,t.dubbing_name,t.dubbing_url,"
				+ "t.role_intro,t.PERSON_BIG_URL,INTO_DATE,t.DATA_TYPE)values(?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
		
		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(tvplayname);
		list.add(tvplayurl);
		list.add(personname);
		list.add(personurl);
		list.add(rolename);
		list.add(personstillsurl);
		list.add(dubbingname);
		list.add(dubbingurl);
		list.add(roleintro);
		list.add(PERSON_BIG_URL);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(DATA_TYPE);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	public static void intoTEM_PLAY_PERSON (TEM_PLAY_PERSON playpeoson){
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PLAY_PERSON t(t.DATA_ID ,t.DATA_NAME,t.DATA_URL,"
				+ "t.PERSON_NAME,t.PERSON_URL,t.ROLE_NAME,t.PERSON_SMALL_URL,t.DUBBING_NAME,t.ROLE_INTRO,"
				+ "t.INTO_DATE,t.SOURCE)values(?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		list.add(playpeoson.getDataId());// 这里是将对象加入到list中
		list.add(playpeoson.getDataName());
		list.add(playpeoson.getDataUrl());
		list.add(playpeoson.getPersonName());
		list.add(playpeoson.getPersonUrl());
		list.add(playpeoson.getRoleName());
		list.add(playpeoson.getPersonSmallUrl());
		list.add(playpeoson.getDubbingName());
		list.add(playpeoson.getRoleIntro());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(playpeoson.getSource());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
		
	}
	
	/**
	 * TEM_DIM_ENTRYIMG
	 * 2016年6月8日15:44:48
	 * 电影/电视剧剧照---词条 
	 * @param playpeoson
	 */
	
	public static void intoTEM_DIM_ENTRYIMG (TEM_DIM_ENTRYIMG ENTRYIMG){
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_DIM_ENTRYIMG t(t.data_id,t.data_title,t.small_url,t.big_url,t.into_date,t.data_url,t.data_type)values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
		//
		strSql="insert into ods.tem_play_stills t(t.data_id,t.data_title,t.small_url,t.big_url,t.into_date,t.data_url,t.data_type,t.STILLS_TITLE,t.STILLS_ORDERNO,t.UPDATE_DATE)values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(ENTRYIMG.getDataId());// 这里是将对象加入到list中
		list.add(ENTRYIMG.getDataTitle());
		list.add(ENTRYIMG.getSmallUrl());
		list.add(ENTRYIMG.getBigUrl());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(ENTRYIMG.getDataUrl());
		list.add(ENTRYIMG.getDataType());
		list.add(ENTRYIMG.getSTILLS_TITLE());
		list.add(ENTRYIMG.getSTILLS_ORDERNO());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
		
		
	}

	/**
	 * 添加ods.TEM_PERSON_STYLE 进行数据的添加 进行百度演员风格标签添加
	 * 
	 * @param words
	 * @param wordsurl
	 * @param name
	 *            2015年11月14日12:27:19
	 */
	public static void intotempersonstyle(String words, String wordsurl, String name) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_STYLE t VALUES(null,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		list.add(words);// 这里是将对象加入到list中
		list.add(wordsurl);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(name);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 查询图片数据
	 * 
	 * @return
	 */
	public static ArrayList<String> selectphoto() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_name,t.photo_url from ODS.DIM_PERSON_PHOTO t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}

	/**
	 * 查询图片数据
	 * 
	 * @return
	 */
	public static ArrayList<String> selectphoto1() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.img_name，t.img_url  from ods.TEM_DIM_PERSON_ONTHER t";
		sql = "select t.person_name，t.person_small_url,t.role_name,t.tvplay_name  from ODS.TEM_TVPLAY_PERSON t where t.into_date is not null group by t.person_name,t.person_small_url,t.role_name,t.tvplay_name";
		
		sql = "select t.person_big_url from ODS.TEM_TVPLAY_PERSON t where t.into_date is not null group by t.person_big_url";
		
		sql="select small_url from ods.tem_play_stills t where t.stills_orderno = 1 and t.stills_title = '词条图片' and t.data_type = 0";
		
		sql="select big_url from ods.tem_play_stills t where t.stills_orderno = 1 and t.stills_title = '词条图片' and t.data_type = 0";
		
		sql="select big_url from ods.tem_play_stills t where t.stills_orderno = 1 and t.stills_title = '词条图片' and t.data_type = 3";
		
		sql="select small_url from ods.tem_play_stills t where t.stills_orderno = 1 and t.stills_title = '词条图片' and t.data_type = 3";
		
		sql="select small_url,big_url from ods.tem_play_stills t where t.stills_orderno = 1 and t.stills_title = '词条图片' and t.data_type = 0 ";
		
		
		sql="select t.person_small_url as baike_url from ODS.DEL_TEM_PLAY_PERSON t where t.source = 0 and t.person_small_url is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}

	/**
	 * sql语句并获取开始和结束 dao用户列表中
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectBaiduiInformation(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_url,t.sex from  ODS.TEM_DIM_PERSON t order by t.person_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}

	/**
	 * 进行百度基本信息数据的更新
	 * 
	 * @param id
	 * @param basicInfo
	 * @param sex
	 */
	public static void updateiInformation(int id, String basicInfo, String sex) {
		System.out.println(id + basicInfo + sex);
		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "update ods.TEM_DIM_PERSON t set t.basic_info=?,t.sex=? where t.person_id=?";

		List<Comparable> list = new ArrayList();
		list.add(basicInfo);// 这里是将对象加入到list中
		list.add(sex);
		list.add(id);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 获取当360人的指数数据
	 * 
	 * @return
	 */
	public static String returnMaxdata() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "select max(t.date_date) from ods.person_network_popularity t";
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}

	/**
	 * 获取当360电视剧的指数数据
	 * 
	 * @return
	 */
	public static String returnMaxdianshijudata() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "select max(t.date_date) from ods.person_network_popularity t";
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}

	/**
	 * 获取当360人的指数数据
	 * 
	 * @return
	 */
	public static String returnMaxdianpeople() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "select max(t.date_date) from ods.person_network_popularity t";
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}

	/**
	 * 获取当360字数总需要采集的人数的个数
	 * 
	 * @return
	 */
	public static String returnNumPeople(String strdbname) {
		Connection conn = DBOperate218.getInstance().getConnection();
		// String strSql = "select count(*) from ods.person_network_popularity";
		String strSql = "select count(*) from " + strdbname;
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}

	/**
	 * tem_person_keyword_distrib 人物需求分布 2016年3月25日18:37:55
	 * 
	 * @return
	 */
	public static void intotem_person_keyword_distrib(String data_date, String person_id, String keyword,
			int search_index, String trend, String url ,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_KEYWORD_DISTRIB t ( t.data_date,t.data_id,t.keywords ,t.search_index,t.trend,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(keyword);
		list.add(search_index);
		list.add(trend);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * tem_person_relevant_keyword 人物相关搜索 2016年3月28日11:13:37
	 * 
	 * @return
	 */
	public static void intotem_person_relevant_keyword(String data_date, String person_id, String keyword,
			String keyword_url, String recreason, String trend, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_KEYWORD_RELEVANT_trend t ( t.data_date,t.data_id,t.keywords ,t.keyword_url,t.recreason,t.trend,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(keyword);
		list.add(keyword_url);
		list.add(recreason);
		list.add(trend);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * tem_person_relevant_news 人物相关新闻 
	 * 2016年3月28日11:18:43
	 * @return
	 */
	public static void intotem_person_relevant_news(String data_date, String person_id, String news_date,
			String news_sitename, String news_title, String news_url, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_KEYWORD_RELEVANT_NEWS t ( t.data_date,t.data_id,t.news_date ,t.news_sitename,t.news_title,t.news_url,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(news_date);
		list.add(news_sitename);
		list.add(news_title);
		list.add(news_url);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * tem_person_relevant_weibo 人物相关微博
	 * 2016年3月28日11:21:22
	 * @return
	 */
	public static void intotem_person_relevant_weibo(String data_date, String person_id, String comments_num,
			String forwards_num, String comments_url, String forwards_url, String text, String timestamp, String weibo_url, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_KEYWORD_RELEVANT_WEIBO t ( t.data_date,t.data_id,t.comments_num ,t.forwards_num,t.comments_url,t.forwards_url,"
				+ "t.text,t.timestamp,t.weibo_url,t.into_date,t.url,t.data_type)values(?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(comments_num);
		list.add(forwards_num);
		list.add(comments_url);
		list.add(forwards_url);
		list.add(text);
		list.add(timestamp);
		list.add(weibo_url);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * tem_person_keyword_up 人物需求分布 2016年3月25日18:37:55
	 * 
	 * @return
	 */
	public static void intotem_person_keyword_up(String data_date, String person_id, String keyword,
			String up_rate, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_KEYWORD_UP t ( t.data_date,t.data_id,t.keywords ,t.up_rate,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(keyword);
		list.add(up_rate);
//		list.add(trend);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * TEM_360_WORD_AREA
	 * 360指数地区分布
	 * 2016年7月5日10:40:14
	 * 
	 * @return
	 */
	public static void intoTEM_360_WORD_AREA(String data_date, String person_id, String keyword,
			String AREA_TOP,String AREA_NAME_CN,String AREA_RATE, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_360_WORD_AREA t ( t.data_date,t.data_id,t.WORD ,t.AREA_TOP,t.AREA_NAME_CN,AREA_RATE,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(keyword);
//		list.add(up_rate);
		list.add(AREA_TOP);
		list.add(AREA_NAME_CN);
		list.add(AREA_RATE);
//		list.add(trend);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * TEM_360_WORD_AGE_SEX
	 * 360关注人物性别比例年龄分布
	 * 2016年7月5日11:04:59
	 * 
	 * @return
	 */
	public static void intoTEM_360_WORD_AGE_SEX(String data_date, String person_id, String keyword,
			String MAN_RATE,String WOMAN_RATE,String RATE_19,String RATE_29,String RATE_39,String RATE_49,String RATE_50, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_360_WORD_AGE_SEX t ( t.data_date,t.data_id,t.WORD ,t.MAN_RATE,t.WOMAN_RATE,RATE19,RATE29,RATE39,RATE49,RATE50,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(keyword);
//		list.add(up_rate);
		list.add(MAN_RATE);
		list.add(WOMAN_RATE);
		list.add(RATE_19);
		list.add(RATE_29);
		list.add(RATE_39);
		list.add(RATE_49);
		list.add(RATE_50);
//		list.add(trend);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	
	/**
	 * TEM_360_WORD_TAG
	 * 360关键字标签
	 * 2016年7月5日11:22:18
	 * 
	 * @return
	 */
	public static void intoTEM_360_WORD_TAG(String data_date, String person_id, String keyword,
			String LABEL_NAME,String LABEL_NUM, String url,int data_type) {

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_360_WORD_TAG t ( t.data_date,t.data_id,t.WORD ,t.LABEL_NAME,t.LABEL_NUM,"
				+ "t.into_date,t.url,t.data_type)values(?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(data_date);
		list.add(person_id);
		list.add(keyword);
		list.add(LABEL_NAME);
		list.add(LABEL_NUM);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 *tem_person_works 百度人物主要作品 
	 *2016年5月6日18:10:50
	 * 
	 * @return
	 */
	public static void intotem_person_works( tem_person_works personwork ) {
		if (personwork.getTextValue().equals("")||personwork.getTextValue()==null) {
			return;
		}

		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "insert into ods.TEM_PERSON_WORKS t ( t.PERSON_ID,t.PERSON_URL,t.NAME ,t.PRODUCED_TIME,"
				+ "t.ROLE_NAME,t.DIRECTOR,t.MAJOR_ACTORS, t.SINGER,t.REMARKS,t.TEXT_VALUE,t.DATA_TYPE,t.UPDATE_TIME ,t.NAME_URL"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		// list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(personwork.getPersonId());
		list.add(personwork.getPersonUrl());
		list.add(personwork.getName());
		list.add(personwork.getProducedTime());
		list.add(personwork.getRoleName());
		list.add(personwork.getDirector());
		list.add(personwork.getMajorActors());
		list.add(personwork.getSinger());
		list.add(personwork.getRemarks());
		list.add(personwork.getTextValue());
		list.add(personwork.getDataType());
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(personwork.getName_URL());
		// list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	

	public static void main(String[] args) {
		// returnNumPeople("edw.dim_tvplay");
		returnMaxdata();
	}

}
