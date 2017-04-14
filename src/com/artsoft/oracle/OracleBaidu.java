package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.Company;
import com.artsoft.bean.TvPlay;
import com.artsoft.util.TimeTest;

public class OracleBaidu {
	
	/**
	 * �ٶ����ݵ��е�
	 * @return
	 */
	public static List selecthuoqu() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name from ODS.DIM_PERSON t where t.profile is null and t.birth_date is null and t.birth_place is null and t.typical_works is null";
		
		sql="select t.person_id,t.person_name from edw.dim_person t where t.person_url is null";
//		sql = "select t1.person_id,t1.person_name ,t1.person_url from ODS.TEM_TVPLAY_ACTORS t1,(select distinct t.person_url from ODS.TEM_TVPLAY_ACTORS t where t.person_id  >=16873 and t.person_url is not null) t2 where  t1.person_url = t2.person_url";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	/**
	 * �ٶ����ݵ��е�
	 * @return
	 */
	public static List selecthuoqumingcheng() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_name from ODS.DIM_PERSON t where t.profile is null and t.birth_date is null and t.birth_place is null and t.typical_works is null order by t.person_id";
		/**
		 * �������ݵ������������
		 * 2016��1��21��16:59:32
		 * 
		 */
		//sql="select *  from edw.dim_tvplay t where t.years is null   and t.tvplay_name not in ('�������ֹ��յ���', 'HEAVENS GARDEN���ϻ�԰','���������','GIRL MEETS WORLD���ٿ�����','�޾��İ��ڶ���','�޾��İ���һ��','��ס�������')";
		
		/**
		 * 2016��6��7��15:05:11
		 * 
		 */
		sql="select tt.person_id,tt.person_name from (select p.person_id , p.person_name, p.person_url,t.person_url person_urls  from ods.dim_person p  left join ODS.TEM_DIM_PERSON t    on p.person_name = t.person_name where p.person_id > 20234   and p.sex = 0 order by p.person_name desc) tt where tt.person_urls is null order by tt.person_id";
		
		/**
		 * 2016��8��9��18:30:05
		 * 
		 */
		sql="select  t.person_id,t.person_name,length(t.person_name),t.person_url from ods.dim_person t where t.person_id >172000 ";
		
		sql="select * from edw.dim_person t where t.person_url is null";
		
		
		
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	/**
	 * �ٶ����ݵ��е�
	 * 2016��6��6��16:35:35
	 * @return
	 */
	public static List selecthuoqumingchengurl() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "  select max(p.person_url) person_url,      max(t.person_id) person_id,max(t.person_name) person_name  from ods.tem_play_person p  left join ods.dim_person t on p.person_name = t.person_name  where t.sex = 0  and p.person_url is not null  group by p.person_name order by person_id";
		
		sql="select max(p.person_id) as person_id,t.person_name,max(t.person_url) as person_url,max(p.person_url) as person_urls  from ODS.DEL_DIM_PERSON t left join ods.dim_person p on t.person_name = p.person_name where p.person_url is null and t.person_name is not null and p.person_id is not null group by t.person_name";
		sql="select t.person_id,t.person_name,t.person_url from edw.dim_person t where t.person_url is not null and t.person_url !='��'";
		sql="select t.person_id,t.person_name,t.person_url from edw.dim_person t where t.person_url is not null and t.person_url !='��' and t.person_url not  like '%��%' ";
		sql="select p.person_id,p.person_name,p.person_url from edw.dim_person p where p.person_id not in(select t.person_id from ods.tem_dim_person t ) and  p.person_url is not null and p.person_url !='��' and p.person_url not  like '%��%'";
		
		sql="select t.person_id,t.person_name,t.person_url from EDW.Dim_Person t where t.update_date >='20161116'";
		sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t where t.update_date >='20161121' and t.person_url !='��'";
		
		sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t where t.update_date >='20161122' and t.person_url !='��'";
		
		sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t where t.update_date >='20161124' and t.person_url !='��'";
		sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t  where t.person_name in('��ΰ' ,'�����','�ܶ���','��˼��') ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	/**
	 * �ٶ����ݵ��е�
	 * 2017��3��27��16:07:29
	 * @return
	 */
	public static List selecthuoqumingchengurl(String sql) {
		Connection conn = DBOperate218.getInstance().getConnection();
//		String sql = "  select max(p.person_url) person_url,      max(t.person_id) person_id,max(t.person_name) person_name  from ods.tem_play_person p  left join ods.dim_person t on p.person_name = t.person_name  where t.sex = 0  and p.person_url is not null  group by p.person_name order by person_id";
		if (sql.equals("")) {
			
		
			sql="select max(p.person_id) as person_id,t.person_name,max(t.person_url) as person_url,max(p.person_url) as person_urls  from ODS.DEL_DIM_PERSON t left join ods.dim_person p on t.person_name = p.person_name where p.person_url is null and t.person_name is not null and p.person_id is not null group by t.person_name";
			sql="select t.person_id,t.person_name,t.person_url from edw.dim_person t where t.person_url is not null and t.person_url !='��'";
			sql="select t.person_id,t.person_name,t.person_url from edw.dim_person t where t.person_url is not null and t.person_url !='��' and t.person_url not  like '%��%' ";
			sql="select p.person_id,p.person_name,p.person_url from edw.dim_person p where p.person_id not in(select t.person_id from ods.tem_dim_person t ) and  p.person_url is not null and p.person_url !='��' and p.person_url not  like '%��%'";
			
			sql="select t.person_id,t.person_name,t.person_url from EDW.Dim_Person t where t.update_date >='20161116'";
			sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t where t.update_date >='20161121' and t.person_url !='��'";
			
			sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t where t.update_date >='20161122' and t.person_url !='��'";
			
			sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t where t.update_date >='20161124' and t.person_url !='��'";
			sql="select t.person_id,t.person_name,t.person_url from EDW.DIM_PERSON t  where t.person_name in('��ΰ' ,'�����','�ܶ���','��˼��') ";
		}
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;

	}
	
	
	
	/**
	 * �ٶ����ݵ��еĵ��Ӿ粹��
	 * 2015��12��2��18:11:49
	 * @return
	 */
	public static List selectbaidudianshiju() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select * from ods.dim_tvplay_tmp s where s.tvplay_id >=23342 ";
		
		/**
		 * 2016��8��17��15:34:37
		 */
		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') ����ʱ�� from edw.dim_tvplay t where t.years is null and t.create_time>to_date('20160731','yyyymmdd')";
		
		
		
		/**
		 * 2016��8��19��11:23:25
		 */
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') ����ʱ�� from edw.dim_tvplay t where t.years is null and t.create_time>to_date('20160807','yyyymmdd')";
		/**
		 * 2016��8��23��12:40:19
		 */
		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') ����ʱ�� from edw.dim_tvplay t where t.years is null and t.create_time>to_date('20160817','yyyymmdd')";
		
		/**
		 * 2016��8��30��15:09:00
		 */
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') ����ʱ�� from edw.dim_tvplay t where t.years is null and t.create_time>to_date('20160810','yyyymmdd')";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') ����ʱ�� from edw.dim_tvplay t where t.years is null and t.create_time>to_date('20160810','yyyymmdd')";


		sql="select t.tvplay_id,t.tvplay_name,to_char(create_time,'yyyymmdd') ����ʱ�� from edw.dim_tvplay t where t.years is null and t.create_time>to_date('20160810','yyyymmdd')";
		
		
		sql="select * from  edw.dim_tvplay t where t.tvplay_url is null  ";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
//		sql="select t.*, t.rowid from ODS.DEL_DIM_TVPLAY t";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
		sql=" select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'";
		
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'  ";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'  ";
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t   where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901'  ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �ٶ����ݵ��еĵ��Ӿ粹��
	 * 2016��5��23��17:25:17
	 * @return
	 */
	public static List selectbaidudianshijuTVplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name,a.tvplay_url from edw.dim_tvplay t left join ods.tem_tvplay a on t.tvplay_id = a.tvplay_id order by a.tvplay_id";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t where t.tvplay_url is not null";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t where t.tvplay_url is not null";
		
		sql=" select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where t.update_date >='20161116'";
		
		sql="select to_char(t.tvplay_id),t.tvplay_name,t.tvplay_url from EDW.F_TVPLAY_RECORD t where t.update_date >='20161121' and t.tvplay_url is not null";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where t.tvplay_name in('��������ɵ�����','С����','����־','���ʱ��')";
		
		sql=" select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where t.update_date >='20161206' and t.tvplay_url is not null";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where t.update_date >='20161206' and t.tvplay_url is not null ";
		
//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where   t.tvplay_name='���齭ɽ'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * �ٶ����ݵ��еĵ��Ӿ粹��
	 * 2016��5��23��17:25:17
	 * @return
	 */
	public static List selectbaidudianshijuTVplay(String sql) {
		Connection conn = DBOperate218.getInstance().getConnection();
		
//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where   t.tvplay_name='���齭ɽ'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * ��Ӱ��Ϣ���ݵ��������ݵ��油
	 * 2017��3��24��14:27:40
	 * @return
	 */
	public static List selectmovesql(String sql) {
		Connection conn = DBOperate218.getInstance().getConnection();
		
//		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.DIM_TVPLAY t where   t.tvplay_name='���齭ɽ'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * �ٶ����ݵ��е�����粹��
	 * 2016��6��17��16:37:43
	 * @return
	 */
	public static List selectbaidudianshijuWangluoju() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name,a.tvplay_url from edw.dim_tvplay t left join ods.tem_tvplay a on t.tvplay_id = a.tvplay_id order by a.tvplay_id";
		sql=" select t.tvplay_id,t.tvplay_name,t.tvplay_url from ODS.DIM_NETWORK_TVPLAY t order by t.tvplay_id";
		/**
		 * 2016��7��5��17:57:59
		 */
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from ODS.DIM_NETWORK_PLAY t";
		
		
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from edw.dim_networkplay t";
		
		
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �ٶ����ݵ��е�����粹�� url
	 * 2016��8��16��17:05:18
	 * @return
	 */
	public static List selectbaidudianshijuWangluojuurl() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name,a.tvplay_url from edw.dim_tvplay t left join ods.tem_tvplay a on t.tvplay_id = a.tvplay_id order by a.tvplay_id";
		/**
		 * 2016��7��5��17:57:59
		 */
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from edw.dim_networkplay t where t.networkplay_url is not null";
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url,t.img_big_name from edw.dim_networkplay t where t.networkplay_url is not null";
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from EDW.DIM_NETWORKPLAY t where t.update_date = '20161107' and t.networkplay_url is not null";
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from EDW.Dim_Networkplay t where t.update_date >='20161121' and t.networkplay_url is not null";
		
		sql=" select t.networkplay_id,t.networkplay_name,t.networkplay_url from EDW.Dim_Networkplay t where t.update_date >='20161122' and t.networkplay_url is not null";
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from EDW.Dim_Networkplay t where t.update_date >='20161124' and t.networkplay_url is not null";
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from EDW.Dim_Networkplay t where t.update_date >='20161125' and t.networkplay_url is not null";
		
		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from EDW.Dim_Networkplay t where t.update_date >='20161130' and t.networkplay_url is not null";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	
	/**
	 * è��Ʊ��
	 * 2017��4��6��15:54:58
	 * @return
	 */
	public static List selectmaoyanpiaofang() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql="select t.fid,max(t.title),max(url) from ODS.TEM_FILM_BOXOFFICE_REALTIME t where t.source=1 group by t.fid";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	
	/**
	 * �ٶ����ݵ��е�����粹�� url
	 * �����ű�����
	 * 2017��2��20��13:58:42
	 * @return
	 */
	public static List selectbaidudianshijuWangluojuurl_jiaoben() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.networkplay_id,t.networkplay_name,t.networkplay_url from edw.dim_networkplay t where t.actors is null and t.networkplay_url !='��' and t.networkplay_url is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	public static List selectbaidudianshijuWangluojuurl_jiaoben(String sql) {
		Connection conn = DBOperate218.getInstance().getConnection();
//		String sql = "select t.networkplay_id,t.networkplay_name,t.networkplay_url from edw.dim_networkplay t where t.actors is null and t.networkplay_url !='��' and t.networkplay_url is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �ٶ����ݵ��е�����粹�� url
	 * 2016��8��16��17:05:18
	 * @return
	 */
	public static List selectbaidudianshiju_Moves_url() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.film_id,t.film_name,t.film_url from EDW.DIM_FILM t where t.update_date >='20161206' and t.film_url is not null";
		
		sql="select t.film_id,t.film_name,t.film_url from EDW.DIM_FILM t where t.update_date >='20161206' and t.film_url is not null";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	//
	/**
	 * �ٶ����ݵ��е�����
	 * 2016��6��21��16:02:35
	 * @return
	 */
	public static List selectbaidudianshijuzhongyiall() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select * from   ODS.DIM_NETWORK_VARIETY t order by t.tvplay_id";
		
		sql="select t.variety_id,t.variety_name from ODS.DIM_VARIETY t";
		
		/**
		 * 2016��7��26��10:53:18
		 * ��һ��variety_name_back
		 */
		
		sql="select t.variety_id,t.variety_name_back from ods.dim_variety t where t.variety_name_back is not null";
		
		sql="select t.variety_id,t.variety_name,t.variety_url,t.img_big_name from edw.dim_variety t ";
		
//		sql="select t.networkplay_id,t.networkplay_name,t.networkplay_url from edw.dim_networkplay t";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	//
	/**
	 * �ٶ����ݵ��е�����
	 * 2016��6��21��16:02:35
	 * @return
	 */
	public static List selectbaidudianshijuzhongyi() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select * from   ODS.DIM_NETWORK_VARIETY t order by t.tvplay_id";
		
		sql="select t.variety_id,t.variety_name from ODS.DIM_VARIETY t";
		
		/**
		 * 2016��7��26��10:53:18
		 * ��һ��variety_name_back
		 */
		
		sql="select t.variety_id,t.variety_name_back from ods.dim_variety t where t.variety_name_back is not null";
		
		sql="select t.variety_id,t.variety_name,t.variety_url,t.img_big_name from edw.dim_variety t where t.variety_url is not null";
		
		sql="select t.variety_id,t.variety_name,t.variety_url from EDW.DIM_VARIETY t  where t.variety_url is not null and t.update_date='20161107'";
		
		sql="select t.variety_id,t.variety_name,t.variety_url from EDW.Dim_Variety t where t.update_date >='20161116' and t.variety_url is not null";
		
		sql="select t.variety_id,t.variety_name,t.variety_url from EDW.Dim_Variety t where t.update_date >='20161121' and t.variety_url is not null";
		
		sql="select t.variety_id,t.variety_name,t.variety_url from EDW.Dim_Variety t where t.update_date >='20161124' and t.variety_url is not null";
		
		sql="select t.variety_id,t.variety_name,t.variety_url from EDW.Dim_Variety t where t.update_date >='20161130' and t.variety_url is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	public static List selectbaidudianshijuzhongyi(String sql) {
		Connection conn = DBOperate218.getInstance().getConnection();
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �ٶ����ݵ��е�����
	 * 2016��6��21��16:02:35
	 * @return
	 */
	public static List selectbaidudianshijuzhongyiScropt() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.variety_id,t.variety_name,t.variety_url from edw.dim_variety t where t.actors is null and t.guest_program is null and t.presenter is null and t.variety_url !='��' and t.variety_url is not null and t.is_del !=-1 ";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �ٶ����ݵ��е���
	 * �ٶȴ�������ͼƬ
 * 2016��6��22��15:10:58
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
	 * �ٶ����ݵ��еĵ��Ӿ� 
	 * �ٶȴ�������ͼƬ
 * 2016��6��8��15:55:02
	 * @return
	 */
	public static List selectbaiTVplay() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,max(t.tvplay_name),t.tvplay_url  from ods.tem_tvplay t  group by t.tvplay_id,t.tvplay_url order by t.tvplay_id";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from mart.dim_tvplay t where t.tvplay_url is not null order by t.tvplay_id desc";
		
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t  where t.tvplay_url is not null order by t.tvplay_id desc";
		
		sql=" select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t  where t.tvplay_url is not null and ASCIISTR(t.tvplay_url) like '%\\%' order by t.tvplay_id desc";
		
		
		sql="select  to_char(t.tvplay_id),t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t where t.tvplay_name = '�ʳ��'";
		
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url  from EDW.F_TVPLAY_RECORD t where t.tvplay_id not in (select t.tvplay_id from edw.f_tvplay_stills t group by t.tvplay_id) and t.tvplay_url is not null order by t.project_date_back desc";
		
		sql="select t.tvplay_id,t.tvplay_name,to_char(t.create_time,'yyyymmdd')  from edw.dim_tvplay t  where t.years is null and to_char(t.create_time,'yyyymmdd') >='20160901' ";
		
		
		sql=" select  to_char(t.tvplay_id),t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t where t.tvplay_name = '�����´���֮����Ӣ��'";
//		sql=" select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.f_tvplay_record t where t.tvplay_url is not null and t.tvplay_name like  '%������֮�޼����%'";
//		sql="select t.tvplay_id,max(t.tvplay_name),t.tvplay_url  from ods.tem_tvplay t left join ods.tem_play_stills s on s.data_id = t.tvplay_id where t.tvplay_url is not null and  s.small_url is null or s.big_url is null group by t.tvplay_id,t.tvplay_url order by t.tvplay_id";
		
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from EDW.F_TVPLAY_RECORD t where t.update_date >='20161121' and t.tvplay_url is not null";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * �ٶ����ݵ��еĵ�Ӱ
	 * 2016��6��12��17:58:50
	 * @return
	 */
	public static List selectbaimove() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = " select t.film_id,t.film_name,t.film_url  from  ods.dim_film t where t.film_url is not null order by t.film_id";
		sql="select * from edw.dim_film t where t.film_name = '������'";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	/**
	 * �ٶ����ݵ��еĵ��Ӿ粹��
	 * 2015��12��2��18:11:49
	 * @return
	 */
	public static List selectpeople() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.person_id,t.person_url,PERSON_NAME from ODS.DIM_PERSON t  where t.person_url is not null";
		/**
		 * 2016��5��10��11:33:58
		 */
		sql = "select t.person_id,t.person_url,PERSON_NAME from edw.dim_person t  where t.person_url is not null order by t.person_id";
		
		sql=" select t.person_id,t.person_url,t.person_name from EDW.DIM_PERSON t where t.update_date >='20161205' and t.person_url is not null and t.person_url !='��'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * �ٶ����ݵ��еĵ��Ӿ粹��
	 * 2015��12��2��18:11:49
	 * @return
	 */
	public static List selectpeople(String sql) {
		Connection conn = DBOperate218.getInstance().getConnection();
//		String sql = "select t.person_id,t.person_url,PERSON_NAME from ODS.DIM_PERSON t  where t.person_url is not null";
		/**
		 * 2016��5��10��11:33:58
		 */
		if (sql.equals("")) {
			
			sql = "select t.person_id,t.person_url,PERSON_NAME from edw.dim_person t  where t.person_url is not null order by t.person_id";
			
			sql=" select t.person_id,t.person_url,t.person_name from EDW.DIM_PERSON t where t.update_date >='20161205' and t.person_url is not null and t.person_url !='��'";
		}
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * �ٶ����ݵ��е�ȫ����Ӱ
	 * 2016-6-3 16:21:09
	 * @return
	 */
	public static List selectmove() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = " select t.film_id,t.film_name,t.FILM_URL from ods.dim_film t order by t.film_id ";
		
		sql="select * from edw.dim_film t where t.film_name = '������'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/**
	 * �ٶ����ݵ��е�ȫ����Ӱ
	 * ��Ӱ�е����ݵ�ͼƬ����
	 * 2016��11��1��15:02:35
	 * @return
	 */
	public static List selectmove_zhuyanshujutupianxianzai() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = " select t.film_id,t.film_name,t.FILM_URL from ods.dim_film t order by t.film_id ";
		
		sql="select * from edw.dim_film t where t.film_name = '������'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	
	/**
	 * �ٶ����ݻ�ȡ�����������ƣ���ͼƬ����Ҫ��ͼƬ
	 * 2015��12��8��11:41:09
	 * @return
	 */
	
	public static void intoTEM_PERSON_IMG(String PERSON_NAME, String PERSON_URL	, String IMG_URL) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_IMG t( t.person_name ,t.person_url,t.img_url,t.create_date)values(?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";

		List<Comparable> list = new ArrayList();
//		list.add(Integer.parseInt(tvplayid));// �����ǽ�������뵽list��
		list.add(PERSON_NAME);
		list.add(PERSON_URL);
		list.add(IMG_URL);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * �ٶ�ͼƬ��ǰ4���������ݻ�ȡ�ƣ���ͼƬ����Ҫ��ͼƬ
	 * 2015��12��8��11:41:09
	 * @return
	 */
	
	public static void intoTEM_PERSON_IMG_baidu(String tvplayid,String PERSON_NAME, String PERSON_URL	, String IMG_URL ,String SOURCE) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.TEM_PERSON_IMG t(t.PERSON_ID ,t.person_name ,t.person_url,t.img_url,t.create_date,t.SOURCE)values(?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(tvplayid));// �����ǽ�������뵽list��
		list.add(PERSON_NAME);
		list.add(PERSON_URL);
		list.add(IMG_URL);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(SOURCE);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * sql��䲢��ȡ��ʼ�ͽ��� dao�û��б���
	 * 2015��12��8��11:54:27
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
	 * sql���caixuekehui,���аٶ�ͼƬ���ݵ�����
	 * 2015��12��10��17:06:55
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
	 * ��ѯͼƬ����,���аٶ�����ͼƬ��ϵ�����ݵ�����
	 * 2015��12��8��15:34:41
	 * @return
	 */
	public static ArrayList<String> selectImage() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select distinct img_url from ODS.TEM_PERSON_IMG t ";
		sql="select distinct img_url from ODS.TEM_PERSON_IMG t where t.source='�ٶ�ͼƬ'";
		//��Ա��Ƹ��
		sql="select t.img_url from ODS.TEM_DIM_PERSON_ONTHER t where t.source='��Ա��Ƹ����'";
		
		//��Ա��Ƹ����ͼƬ
//		sql="select t.big_img_url from ODS.TEM_DIM_PERSON_ONTHER t where t.source='��Ա��Ƹ����'";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 1;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		// List<String> list =DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
		
	}
	
	
	/**
	 * ���ݽ�����������
	 * 2016��4��7��15:04:53
	 * @return
	 */
	
	public static void intotem_news_num(String data_date,String bid, int news_num,String url, int data_type ,int  source) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_news_num t(t.data_date ,t.bid ,t.news_num,t.into_date,t.url,"
				+ "t.data_type,t.source)values(?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(data_date);// �����ǽ�������뵽list��
		list.add(bid);
		list.add(news_num);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(url);
		list.add(data_type);
		list.add(source);
		
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * �ٶ����ݵ��еĴ�����˾
	 * 2016��4��15��15:07:59
	 * @return
	 */
	public static List selectDIM_PRODUCE() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.produce_id,t.company_name from ODS.DIM_PRODUCE t ";
		
		/**
		 * 2016��4��20��10:13:14
		 * ���ı�ṹ
		 */
		sql=" select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * ���аٶȵ��Ӿ� ������Ϣ ��ץȡ
	 * 2016��4��15��14:42:41
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
