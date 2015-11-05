package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

public class OracleHaoSou {

	/**
	 * ƴдsql��䲢��ȡ��ʼ�ͽ���
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public static ArrayList<String> select(String startRow, String endRow) {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "select t.tvplay_id,t.tvplay_name ���Ӿ�,t.years �������,t.issuing_license from edw.dim_tvplay t order by t.tvplay_id ";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate218.selectStartTOEnd(conn, sql, startRow, endRow, iNum);
		return (ArrayList<String>) list;

	}

	/**
	 * sql��䲢��ȡ��ʼ�ͽ��� dao�û��б���
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
	 * ����Ӿ��������Ŀ�
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
		System.out.println(
				tvplayId + tyPlayName + dataAmount + videoType + palydate + playUrl + tvType + DataType + createTime);

		String strSql = "insert into ods.TEM_NETWORK_REPUTATION t (t.tvplay_id,t.tvplay_name,t.data_Amount ,t.video_type,t.Update_Date ,t.play_url, t.tv_type ,t.Data_type,t.CREATE_DATE) "
				+ "VALUES (?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(tvplayId));// �����ǽ�������뵽list��
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
	}

	/**
	 * �˵����
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

		String strSql = "insert into ods.person_network_popularity t (t.person_id,t.search_index,t.update_date,t.create_date,t.data_url,t.date_type) "
				+ "values(?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";

		List<Comparable> list = new ArrayList();
		list.add(Integer.parseInt(personId));// �����ǽ�������뵽list��
		list.add(Integer.parseInt(searchIndex));
		list.add(updateDate);
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(dataUrl);
		list.add(dateType);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	/**
	 * �������ص�΢����Ϣ PERSON_ID NUMBER(18) Y ��ְ��Աid WEIBO_UID VARCHAR2(200) Y ΢����uid
	 * FANS_COUNT NUMBER(18) Y ��˿�� V_COUNT NUMBER(18) Y ΢������ SEARCH_INDEX
	 * NUMBER(18) Y ����ָ��/ý���ע�� UPDATE_DATE TIMESTAMP(6) Y ����ץȡʱ�� CREATE_DATE
	 * TIMESTAMP(6) Y �������ʱ�� DATA_URL VARCHAR2(600) Y ���ݲ�ȡurl DATE_TYPE
	 * NUMBER(2) Y �������ͣ�1 ΢������(����ָ��Ϊ��) 2 ����ָ�� (΢���������Ϊ��) 3 ý���ע��
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
		list.add(Integer.parseInt(personId));// �����ǽ�������뵽list��
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
	 *            ����
	 * @param personUrl
	 *            ���û�����ϸ����
	 * @param photoUrl
	 *            ͼƬ��ַ
	 */
	public static void intoBaiDuPopularity(String personName, String personUrl, String photoUrl) {
		Connection conn = DBOperate218.getInstance().getConnection();
		System.out.println(personName + personUrl + photoUrl);

		String strSql = "insert into ODS.DIM_PERSON_PHOTO t(t.PERSON_NAME, t.PERSON_URL ,t.PHOTO_URL) values(?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(personName);// �����ǽ�������뵽list��
		list.add(personUrl);
		list.add(photoUrl);
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 * ��ѯͼƬ����
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

	public static void main(String[] args) {
		// List<String> listArray = select("1", "10000");
		// for (Object Objstring : listArray) {
		// System.out.println(Objstring);
		// List<String> listTemp = (List<String>) Objstring;
		// System.out.println(listTemp.get(1));
		// }

		// intoPlayAmont("0", "���Ӿ�", "222", "0", "2014-10-15 23:10:10",
		// "baidu.com", "0", "3", "2014-10-15 23:10:10");

		// intoPeoPle("10","1000","2014-10-15","2014-10-15
		// 23:10:10","www.baiud.com","2");
		// intoPeoPlewebo("10", "http://weibo.com/yanyuankonglin", 2005, 666,
		// "2014-10-15", "2014-10-15 23:10:10",
		// "www.baiud.com", "2");

		// intoBaiDuPopularity("������", "http://baike.baidu.com/view/6923013.htm",
		// "http://hiphotos.baidu.com/zhixin/abpic/item/d1e312f431adcbef60ef7675aeaf2edda2cc9fae.jpg");
		selectphoto();
//		intoBaiDuPopularity("������", "http://baike.baidu.com/view/6923013.htm",
//				"http://hiphotos.baidu.com/zhixin/abpic/item/d1e312f431adcbef60ef7675aeaf2edda2cc9fae.jpg");
	}

}
