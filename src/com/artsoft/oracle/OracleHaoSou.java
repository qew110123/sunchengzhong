package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.Persion;
import com.artsoft.bean.TvPlay;
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
			String palydate, String playUrl, String tvType, String DataType, String createTime) {
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
			list.add(Integer.parseInt(tvType));
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
				+ "t.opus,t.sub_path,t.evaluation,t.birthday_place ,t.social_activities,t.flowers,t.volk,t.brokers,t.hobby，t.basic_info) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
//		list.add(persion.getNation());
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
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 进行百度数据的数据的 修改操作
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
	 * 进行电视剧数据 的
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
				+ "t.recording_place,t.stills_url,t.BEFORE_TELEPLAY,t.NEXT_TELEPLAY,t.MAJOR_AWARDS) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?,?,?,?,?,?,?,?,?,?)";
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
		list.add(tvplay.getBefore_eleplay());
		list.add(tvplay.getNext_teleplay());
		list.add(tvplay.getMajor_awards());
		// list.add(persion.getPersonSocialActivitiesList());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 進入数据进行角色介绍添加
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
			String dubbingurl, String roleintro) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_tvplay_person t(t.tvplay_id,t.tvplay_name,t.tvplay_url,"
				+ "t.person_name,t.person_url,t.role_name,t.person_stills_url,t.dubbing_name,t.dubbing_url,"
				+ "t.role_intro)values(?,?,?,?,?,?,?,?,?,?)";

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
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * 添加ods.TEM_PERSON_STYLE 进行数据的添加
	 * 进行百度演员风格标签添加
	 * @param words
	 * @param wordsurl
	 * @param name
	 * 2015年11月14日12:27:19
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
	 * @param id
	 * @param basicInfo
	 * @param sex
	 */
	public static void updateiInformation(int id, String basicInfo, String sex) {
		System.out.println(id+basicInfo+sex);
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
	 * @return
	 */
	public static String returnMaxdianpeople() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String strSql = "select max(t.date_date) from ods.person_network_popularity t";
		String strMax = DBOperate218.getResultValue(conn, strSql);
		System.out.println(strMax);
		return strMax;
	}
	public static void main(String[] args) {
		returnMaxdata();
//		updateiInformation(2, "", null);
		// List<String> listArray = select("1", "10000");
		// for (Object Objstring : listArray) {
		// System.out.println(Objstring);
		// List<String> listTemp = (List<String>) Objstring;
		// System.out.println(listTemp.get(1));
		// }

		// intoPlayAmont("0", "电视剧", "222", "0", "2014-10-15 23:10:10",
		// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		// intoPeoPle("10","1000","2014-10-15","2014-10-15
		// 23:10:10","www.baiud.com","2");
		// intoPeoPlewebo("10", "http://weibo.com/yanyuankonglin", 2005, 666,
		// "2014-10-15", "2014-10-15 23:10:10",
		// "www.baiud.com", "2");

		// intoBaiDuPopularity("韩东君", "http://baike.baidu.com/view/6923013.htm",
		// "http://hiphotos.baidu.com/zhixin/abpic/item/d1e312f431adcbef60ef7675aeaf2edda2cc9fae.jpg");
//		selectphoto();
		// intoBaiDuPopularity("王俊凯", "http://baike.baidu.com/view/6923013.htm",
		// "http://hiphotos.baidu.com/zhixin/abpic/item/d1e312f431adcbef60ef7675aeaf2edda2cc9fae.jpg");
	}

}
