package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
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
}
