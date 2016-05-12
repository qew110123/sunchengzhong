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
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
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
		 * 2015��12��11��14:23:27
		 * ��Ҫ�����������Ϣ��
		 */
		sql = "select * from edw.dim_tvplay t   where  t.years is null ";
		/**
		 *l2015��12��18��14:43:54
		 * ���ݵĲ��� 
		 */
		sql="select * from edw.dim_tvplay p where p.tvplay_name in ('��ס�������','���ϻ�԰','�������ֹ��յ���','���������','���ٿ�����','ս����','�޾��İ��ڶ���','���е����һ��','�޾��İ���һ��','ժ��������','�ҵĳ����Ϲ�','����׷��','��������','�ְֻؼ�','Ϊ��һ�仰','��ҹ��ɱ','������е�','��ؾ���','��ʹ֮��һ','�������˵�һ����������','����Ϊ˭','��çӢ�۴�','ɱ¾','�ᰮǧ��','����','���Ǻ�','һ�����Ӷ�����֮������̫��','�嶯�ĳͷ�','ӳɽ��','��������','����','˽��Ǯ','�������Ӳ�����','ʵϰ��ʹ','��������Ҳ���','��ϲһ����','������������һ��','��ħ�ж�','�Һ�������֮������Բ','��Ӱ֮ǹ��','����Ե','�����','��������ս֮��Ҫ����','�Ѫ�к�','�మ','����һ����','�һ���','����������','Ұ���Ѹ�','��������','���������','����������ϵ�Ů��','�ķ��ı�','�¾��˷�','��ʱ����','�Ų�����ս','�����ع�','���´�','�¸Ұ�','������')";
		/**
		 *2015��12��29��12:29:04
		 * ���ݵĲ��� 
		 */
		sql="select * from  edw.dim_tvplay t where t.years  is null";
		
		
		/**
		 * 2016��1��11��14:51:10
		 * ���ݲ���
		 * select * from edw.dim_tvplay t  where t.years is null;
		 */
		sql="select * from edw.dim_tvplay t  where t.years is null";
		
		/**
		 *2016��1��7��11:15:30
		 *2016��1��14��18:39:31
		 * ���ݵĲ��� 
		 */
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.dim_tvplay t where t.tvplay_url is not null and t.tvplay_url !='��'";
		
		
		/**
		 * url���ݲ���
		 * 2016��1��14��18:47:35
		 * 
		 */
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from edw.dim_tvplay t where t.tvplay_id not in (select t.tvplay_id from ods.tem_tvplay t where t.tvplay_id in (select t.tvplay_id from edw.dim_tvplay t where t.tvplay_url is not null and t.tvplay_url !='��')) and t.tvplay_url is not null and t.tvplay_url !='��'";
		
		
		/**
		 *2016��1��21��17:04:07
		 * ���ݵĲ���  �����ƣ�
		 */
		sql="select * from edw.dim_tvplay t where t.years is null and t.tvplay_name not in ('�������ֹ��յ���','HEAVENS GARDEN���ϻ�԰','���������', 'GIRL MEETS WORLD���ٿ�����','�޾��İ��ڶ���','�޾��İ���һ��','��ס�������')";
		
		/**
		 * 2016��2��26��16��:2��:0
		 * ���ݵĲ���
		 */
		sql="select *  from edw.dim_tvplay t where t.years is null ";
		
		/**
		 * 2016��3��10��10:19:48
		 * ���Ӿ粹��
		 */
		sql="select * from edw.dim_tvplay t where t.years is null";
		
		
		/**
		 * 2016��3��17��13:18:12
		 * ���Ӿ粹��
		 */
		sql="select *  from edw.dim_tvplay t where t.years is null order by t.tvplay_name";
		
		/**
		 * 2016��3��23��13:14:11
		 * ���Ӿ粹��
		 */
		sql="select *  from edw.dim_tvplay t where t.years is null order by t.tvplay_name";
		
		/**
		  * 2016��3��29��17:59:17
		 * ���Ӿ粹��
		 */
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		
		/**
		 * 2016��4��20��11:16:01
		 * ���Ӿ粹��
		 */
		
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		
		/**
		 * 2016��4��21��11:36:45
		 * ���ݵĲ��� 
		 */
		sql="select t.tvplay_id,t.tvplay_name,t.tvplay_url from ods.tem_tvplay t";
		/**
		 * 2016��4��26��16:18:36
		 * ���ݵĲ��� 
		 */
		sql=" select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
		
		/**
		 * 2016��5��4��10:18:31
		 * ���ݵĲ��� 
		 */
		sql="select *   from edw.dim_tvplay t   where t.years is null order by t.tvplay_name";
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
