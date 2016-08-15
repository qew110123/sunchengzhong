package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.Company;
import com.artsoft.bean.TvPlay;
import com.artsoft.util.TimeTest;

public class OracleBaidu {
	
	/**
	 * 百度数据的中的
	 * @return
	 */
	public static List selecthuoqu() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name from ODS.DIM_PERSON t where t.profile is null and t.birth_date is null and t.birth_place is null and t.typical_works is null";
//		sql = "select t1.person_id,t1.person_name ,t1.person_url from ODS.TEM_TVPLAY_ACTORS t1,(select distinct t.person_url from ODS.TEM_TVPLAY_ACTORS t where t.person_id  >=16873 and t.person_url is not null) t2 where  t1.person_url = t2.person_url";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	/**
	 * 百度数据的中的
	 * @return
	 */
	public static List selecthuoqumingcheng() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name from ODS.DIM_PERSON t where t.profile is null and t.birth_date is null and t.birth_place is null and t.typical_works is null order by t.person_id";
		/**
		 * 名称数据的搜索进行添加
		 * 2016年1月21日16:59:32
		 * 
		 */
		//sql="select *  from edw.dim_tvplay t where t.years is null   and t.tvplay_name not in ('我在锡林郭勒等你', 'HEAVENS GARDEN天上花园','秀才遇到兵','GIRL MEETS WORLD蕾蕾看世界','无尽的爱第二部','无尽的爱第一部','守住你的秘密')";
		
		/**
		 * 2016年6月7日15:05:11
		 * 
		 */
		sql="select tt.person_id,tt.person_name from (select p.person_id , p.person_name, p.person_url,t.person_url person_urls  from ods.dim_person p  left join ODS.TEM_DIM_PERSON t    on p.person_name = t.person_name where p.person_id > 20234   and p.sex = 0 order by p.person_name desc) tt where tt.person_urls is null order by tt.person_id";
		
		/**
		 * 2016年8月9日18:30:05
		 * 
		 */
		sql="select  t.person_id,t.person_name,length(t.person_name),t.person_url from ods.dim_person t where t.person_id >172000 ";
		
		
		
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	/**
	 * 百度数据的中的
	 * 2016年6月6日16:35:35
	 * @return
	 */
	public static List selecthuoqumingchengurl() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "  select max(p.person_url) person_url,      max(t.person_id) person_id,max(t.person_name) person_name  from ods.tem_play_person p  left join ods.dim_person t on p.person_name = t.person_name  where t.sex = 0  and p.person_url is not null  group by p.person_name order by person_id";
		
		sql="select max(p.person_id) as person_id,t.person_name,max(t.person_url) as person_url,max(p.person_url) as person_urls  from ODS.DEL_DIM_PERSON t left join ods.dim_person p on t.person_name = p.person_name where p.person_url is null and t.person_name is not null and p.person_id is not null group by t.person_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	/**
	 * 百度数据的中的电视剧补充
	 * 2015年12月2日18:11:49
	 * @return
	 */
	public static List selectbaidudianshiju() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select * from ods.dim_tvplay_tmp s where s.tvplay_id >=23342 ";
		/**
		 * 2015年12月11日14:23:27
		 * 需要补充网络剧信息的
		 */
		sql = "select * from edw.dim_tvplay t   where  t.years is null ";
		/**
		 *l2015年12月18日14:43:54
		 * 数据的补充 
		 */
		sql="select * from edw.dim_tvplay p where p.tvplay_name in ('守住你的秘密','天上花园','我在锡林郭勒等你','秀才遇到兵','蕾蕾看世界','战鼓擂','无尽的爱第二部','家有爹娘第一部','无尽的爱第一部','摘下你的面具','我的宠物老公','极限追击','功夫婆婆','爸爸回家','为了一句话','子夜搏杀','爱情碟中谍','遍地惊雷','天使之恋一','正义联盟第一季天外来客','痴情为谁','草莽英雄传','杀戮','夺爱千金','蜂鸟','长城红','一个鬼子都不留之三威震太行','冲动的惩罚','映山红','刀剑出鞘','玉海棠','私房钱','不见鬼子不挂弦','实习天使','老人恋爱也疯狂','悲喜一家人','绝望的主妇第一季','猎魔行动','家和万事兴之花好月圆','狐影之枪花','错爱孽缘','纪晓岚','婚姻攻防战之爱要付出','喋血敌后','苦爱','邻里一家人','桃花运','温州两家人','野蛮寡妇','生死争夺','爱我你别走','长在面包树上的女人','文房四宝','孤军剿匪','秦时明月','遗产争夺战','绝密特工','芈月传','勇敢爱','豹龙斗')";
		/**
		 *2015年12月29日12:29:04
		 * 数据的补充 
		 */
		sql="select * from  edw.dim_tvplay t where t.years  is null";
		
		
		/**
		 * 2016年1月11日14:51:10
		 * 数据补充
		 * select * from edw.dim_tvplay t  where t.years is null;
		 */
		sql="select * from edw.dim_tvplay t  where t.years is null";
		
		/**
		 *2016年1月7日11:15:30
		 *2016年1月14日18:39:31
		 * 数据的补充 
		 */
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.dim_tvplay t where t.tvplay_url is not null and t.tvplay_url !='无'";
		
		
		/**
		 * url数据补充
		 * 2016年1月14日18:47:35
		 * 
		 */
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.dim_tvplay t where t.tvplay_id not in (select t.tvplay_id from ods.tem_tvplay t where t.tvplay_id in (select t.tvplay_id from edw.dim_tvplay t where t.tvplay_url is not null and t.tvplay_url !='无')) and t.tvplay_url is not null and t.tvplay_url !='无'";
		
		
		/**
		 *2016年1月21日17:04:07
		 * 数据的补充  （名称）
		 */
		sql="select * from edw.dim_tvplay t where t.years is null and t.tvplay_name not in ('我在锡林郭勒等你','HEAVENS GARDEN天上花园','秀才遇到兵', 'GIRL MEETS WORLD蕾蕾看世界','无尽的爱第二部','无尽的爱第一部','守住你的秘密')";
		
		/**
		 * 2016年2月26日16：:2：:0
		 * 数据的补充
		 */
		sql="select *  from edw.dim_tvplay t where t.years is null ";
		
		/**
		 * 2016年3月10日10:19:48
		 * 电视剧补充
		 */
		sql="select * from edw.dim_tvplay t where t.years is null";
		
		
		/**
		 * 2016年3月17日13:18:12
		 * 电视剧补充
		 */
		sql="select *  from edw.dim_tvplay t where t.years is null order by t.tvplay_name";
		
		/**
		 * 2016年3月23日13:14:11
		 * 电视剧补充
		 */
		sql="select *  from edw.dim_tvplay t where t.years is null order by t.tvplay_name";
		
		/**
		  * 2016年3月29日17:59:17
		 * 电视剧补充
		 */
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		
		/**
		 * 2016年4月20日11:16:01
		 * 电视剧补充
		 */
		
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		
		/**
		 * 2016年4月21日11:36:45
		 * 数据的补充 
		 */
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		/**
		 * 2016年4月26日16:18:36
		 * 数据的补充 
		 */
		sql=" select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		/**
		 * 2016年5月4日10:18:31
		 * 数据的补充 
		 */
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		/**
		 * 2016年5月18日09:54:46
		 * 数据补充
		 */
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		/**
		 * 2016年6月7日16:31:27
		 * 完善电视剧基本信息抓取，按照电视剧关键字抓取百科搜索的当前的所有数据 
		 */
		
		/**
		 * 2016年8月4日09:48:16
		 * 电视剧
		 */
		sql="select t.tvplay_id, t.tvplay_name,to_char(t.create_time,'yyyymmdd') from edw.dim_tvplay t where t.years is null order by to_char(t.create_time,'yyyymmdd')desc";
		
		/**
		 * 2016年8月9日13:09:30
		 * 电视剧
		 */
		sql="select t.* , to_char(create_time,'yyyymmdd')   from edw.dim_tvplay t where t.years is null  and t.create_time>to_date('20160723','yyyymmdd')";
		
		
		/**
		 * 2016年8月11日18:40:48
		 */
		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') 生成时间 from edw.dim_tvplay t where t.years is null ";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * 百度数据的中的电视剧补充
	 * 2016年5月23日17:25:17
	 * @return
	 */
	public static List selectbaidudianshijuTVplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name,a.tvplay_url from edw.dim_tvplay t left join ods.tem_tvplay a on t.tvplay_id = a.tvplay_id order by a.tvplay_id";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t where t.tvplay_url is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * 百度数据的中的网络剧补充
	 * 2016年6月17日16:37:43
	 * @return
	 */
	public static List selectbaidudianshijuWangluoju() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name,a.tvplay_url from edw.dim_tvplay t left join ods.tem_tvplay a on t.tvplay_id = a.tvplay_id order by a.tvplay_id";
		sql=" select t.tvplay_id,t.tvplay_name,t.tvplay_url from ODS.DIM_NETWORK_TVPLAY t order by t.tvplay_id";
		/**
		 * 2016年7月5日17:57:59
		 */
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from ODS.DIM_NETWORK_PLAY t";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	//
	/**
	 * 百度数据的中的综艺
	 * 2016年6月21日16:02:35
	 * @return
	 */
	public static List selectbaidudianshijuzhongyi() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select * from   ODS.DIM_NETWORK_VARIETY t order by t.tvplay_id";
		
		sql="select t.variety_id,t.variety_name from ODS.DIM_VARIETY t";
		
		/**
		 * 2016年7月26日10:53:18
		 * 跑一遍variety_name_back
		 */
		
		sql="select t.variety_id,t.variety_name_back from ods.dim_variety t where t.variety_name_back is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * 百度数据的中的人
	 * 百度词条数据图片
 * 2016年6月22日15:10:58
	 * @return
	 */
	public static List selectbaipeople() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select p.person_id,p.person_name, p.person_url from ods.dim_person p";
		
//		sql="select t.tvplay_id,max(t.tvplay_name),t.tvplay_url  from ods.tem_tvplay t left join ods.tem_play_stills s on s.data_id = t.tvplay_id where t.tvplay_url is not null and  s.small_url is null or s.big_url is null group by t.tvplay_id,t.tvplay_url order by t.tvplay_id";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * 百度数据的中的电视剧 
	 * 百度词条数据图片
 * 2016年6月8日15:55:02
	 * @return
	 */
	public static List selectbaiTVplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,max(t.tvplay_name),t.tvplay_url  from ods.tem_tvplay t  group by t.tvplay_id,t.tvplay_url order by t.tvplay_id";
		
//		sql="select t.tvplay_id,max(t.tvplay_name),t.tvplay_url  from ods.tem_tvplay t left join ods.tem_play_stills s on s.data_id = t.tvplay_id where t.tvplay_url is not null and  s.small_url is null or s.big_url is null group by t.tvplay_id,t.tvplay_url order by t.tvplay_id";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * 百度数据的中的电影
	 * 2016年6月12日17:58:50
	 * @return
	 */
	public static List selectbaimove() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = " select t.film_id,t.film_name,t.film_url  from  ods.dim_film t where t.film_url is not null order by t.film_id";
		sql="select * from edw.dim_film t where t.film_name = '美人鱼'";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	/**
	 * 百度数据的中的电视剧补充
	 * 2015年12月2日18:11:49
	 * @return
	 */
	public static List selectpeople() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_url,PERSON_NAME from ODS.DIM_PERSON t  where t.person_url is not null";
		/**
		 * 2016年5月10日11:33:58
		 */
		sql = "select t.person_id,t.person_url,PERSON_NAME from edw.dim_person t  where t.person_url is not null order by t.person_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * 百度数据的中的全部电影
	 * 2016-6-3 16:21:09
	 * @return
	 */
	public static List selectmove() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = " select t.film_id,t.film_name,t.FILM_URL from ods.dim_film t order by t.film_id ";
		
		sql="select * from edw.dim_film t where t.film_name = '美人鱼'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * 百度数据获取好友链接名称，和图片，主要是图片
	 * 2015年12月8日11:41:09
	 * @return
	 */
	
	public static void intoTEM_PERSON_IMG(String PERSON_NAME, String PERSON_URL	, String IMG_URL) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_IMG t( t.person_name ,t.person_url,t.img_url,t.create_date)values(?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";

		List<Comparable> list = new ArrayList();
//		list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(PERSON_NAME);
		list.add(PERSON_URL);
		list.add(IMG_URL);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 百度图片中前4条数据数据获取称，和图片，主要是图片
	 * 2015年12月8日11:41:09
	 * @return
	 */
	
	public static void intoTEM_PERSON_IMG_baidu(String tvplayid,String PERSON_NAME, String PERSON_URL	, String IMG_URL ,String SOURCE) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_IMG t(t.PERSON_ID ,t.person_name ,t.person_url,t.img_url,t.create_date,t.SOURCE)values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(tvplayid));// 这里是将对象加入到list中
		list.add(PERSON_NAME);
		list.add(PERSON_URL);
		list.add(IMG_URL);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(SOURCE);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * sql语句并获取开始和结束 dao用户列表中
	 * 2015年12月8日11:54:27
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectBaiduiInformation(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_url from  ODS.TEM_DIM_PERSON t order by t.person_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	/**
	 * sql语句caixuekehui,进行百度图片数据的下载
	 * 2015年12月10日17:06:55
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> selectBaiduitupian(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select p.person_id,p.person_name from ods.dim_person p where p.photo_name_small is null order by p.person_id";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	/**
	 * 查询图片数据,进行百度数据图片关系表数据的下载
	 * 2015年12月8日15:34:41
	 * @return
	 */
	public static ArrayList<String> selectImage() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select distinct img_url from ODS.TEM_PERSON_IMG t ";
		sql="select distinct img_url from ODS.TEM_PERSON_IMG t where t.source='百度图片'";
		//演员招聘网
		sql="select t.img_url from ODS.TEM_DIM_PERSON_ONTHER t where t.source='演员招聘网网'";
		
		//演员招聘网大图片
//		sql="select t.big_img_url from ODS.TEM_DIM_PERSON_ONTHER t where t.source='演员招聘网网'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
		
	}
	
	
	/**
	 * 数据进行搜索量数
	 * 2016年4月7日15:04:53
	 * @return
	 */
	
	public static void intotem_news_num(String data_date,String bid, int news_num,String url, int data_type ,int  source) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_news_num t(t.data_date ,t.bid ,t.news_num,t.into_date,t.url,"
				+ "t.data_type,t.source)values(?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(data_date);// 这里是将对象加入到list中
		list.add(bid);
		list.add(news_num);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		list.add(source);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * 百度数据的中的创作公司
	 * 2016年4月15日15:07:59
	 * @return
	 */
	public static List selectDIM_PRODUCE() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.produce_id,t.company_name from ODS.DIM_PRODUCE t ";
		
		/**
		 * 2016年4月20日10:13:14
		 * 更改表结构
		 */
		sql=" select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * 进行百度电视剧 进步信息 的抓取
	 * 2016年4月15日14:42:41
	 * 
	 * @param company
	 */
	public static void InsertCompany(Company company) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_dim_produce t (t.produce_id,t.company_name,t.english_name,t.alias_name,t.founded_date,t.headquarters,"
				+ "t.business_scope,t.company_nature,t.description,t.works_description,t.logo_url,t.service_area,t.year_money,t.staff_num,"
				+ "t.ceo_name,t.is_ipo,t.create_time,t.DATA_URL)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
		List<Comparable> list = new ArrayList();
		list.add(company.getPRODUCE_ID());
		list.add(company.getCOMPANY_NAME());
		list.add(company.getENGLISH_NAME());
		list.add(company.getALIAS_NAME());
		list.add(company.getFOUNDED_DATE());
		list.add(company.getHEADQUARTERS());
		list.add(company.getBUSINESS_SCOPE());
		list.add(company.getCOMPANY_NATURE());
		list.add(company.getDESCRIPTION());
		list.add(company.getWORKS_DESCRIPTION());
		list.add(company.getLOGO_URL());
		list.add(company.getSERVICE_AREA());
		list.add(company.getYEAR_MONEY());
		list.add(company.getSTAFF_NUM());
		list.add(company.getCEO_NAME());
		list.add(company.getIS_IPO());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(company.getUrl());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
}
