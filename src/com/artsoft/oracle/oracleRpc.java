package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class oracleRpc {
	
	/**
	 * 
	 * 2016��8��18��18:22:37
	 * ΢�������������ݣ�ÿ������
	 * @return
	 */
	
	
	/**
	ÿ�죺
	���Ӿ����������б�ǰ50 1
	��ӰʵʱƱ��           
	���������Ȳ�ǰ50
	���������Ȳ�ǰ50
	����  2 ���Ӿ� 3 ��Ӱ 4���� 5 ���� 
	*/
	
	public static List selectmaoyanshijuTmove() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = " with wurl as (  select   t.data_id,data_type,max(t.data_url) data_url from ods.tem_weibo_topic_score t  where t.BIG_TYPE=1  group by  t.data_id,data_type )    select t.ID,t.Name,t.data_type,wurl.data_url from  ( select t.tvplay_id ID,t.tvplay_name name,2 data_type,ROW_NUMBER() OVER(PARTITION BY 1 ORDER BY new_rate desc)  rank_no  from mart.f_tvplay_index t where t.data_date='29991231' and t.data_type=1   union  all  select t.film_id ID,t.film_name name,3 data_type,ROW_NUMBER() OVER(PARTITION BY 1 ORDER BY REAL_BOXOFFICES desc)  rank_no   from mart.f_film_index t where t.data_date='29991231' and t.REAL_BOXOFFICES>0   union all select t.variety_id ID,t.variety_name name,4 data_type,ROW_NUMBER() OVER(PARTITION BY 1 ORDER BY new_play_amount desc)  rank_no   from mart.f_variety_index t where t.data_date='29991231'    union all  select t.networkdrama_id ID,t.networkdrama_name name,5 data_type,ROW_NUMBER() OVER(PARTITION BY 1 ORDER BY new_play_amount desc)  rank_no   from mart.f_networkdrama_index t where t.data_date='29991231'    )t  left join wurl on t.ID=wurl.data_id and t.data_type=wurl.data_type  where t.rank_no<=50  order by t.data_type,rank_no";
		
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 4;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	
	/*
	�ۺ�������
	���Ӿ�ǰ500
	��Ӱǰ500
	����ǰ200
	����ǰ200
	*/
	
	public static List selectmaoyanshijuTmovebig() {
		Connection conn = DBOperate218.getInstance().getConnection();
		String sql = "with wurl as (  select   t.data_id,data_type,max(t.data_url) data_url from ods.tem_weibo_topic_score t   where t.BIG_TYPE=1  group by  t.data_id,data_type )    select t.ID,t.Name,t.data_type,wurl.data_url from  ( select t.tvplay_id ID,t.tvplay_name name,2 data_type,COMPLEX_RANK  rank_no  from mart.f_tvplay_index t where t.data_date='29991231'  and t.data_type=0 union  all  select t.film_id ID,t.film_name name,3 data_type,COMPLEX_RANK  rank_no   from mart.f_film_index t where t.data_date='29991231'   union all select t.variety_id ID,t.variety_name name,4 data_type,COMPLEX_RANK rank_no   from mart.f_variety_index t where t.data_date='29991231'    union all  select t.networkdrama_id ID,t.networkdrama_name name,5 data_type,COMPLEX_RANK  rank_no   from mart.f_networkdrama_index t where t.data_date='29991231'    )t  left join wurl on t.ID=wurl.data_id and t.data_type=wurl.data_type  where t.rank_no<=500  order by t.data_type,rank_no ";
		sql= "  select * from ( with wurl as (  select   t.data_id,data_type,max(t.data_url) data_url from ods.tem_weibo_topic_score t   where t.BIG_TYPE=1  group by  t.data_id,data_type )    select t.ID,t.Name,t.data_type,wurl.data_url from  ( select t.tvplay_id ID,t.tvplay_name name,2 data_type,COMPLEX_RANK  rank_no  from mart.f_tvplay_index t where t.data_date='29991231'  and t.data_type=0 union  all  select t.film_id ID,t.film_name name,3 data_type,COMPLEX_RANK  rank_no   from mart.f_film_index t where t.data_date='29991231'   union all select t.variety_id ID,t.variety_name name,4 data_type,COMPLEX_RANK rank_no   from mart.f_variety_index t where t.data_date='29991231'    union all  select t.networkdrama_id ID,t.networkdrama_name name,5 data_type,COMPLEX_RANK  rank_no   from mart.f_networkdrama_index t where t.data_date='29991231'    )t  left join wurl on t.ID=wurl.data_id and t.data_type=wurl.data_type  where t.rank_no<=2000  order by t.data_type,rank_no ) tableall where tableall.data_url is null";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 4;
		List<String> list = DBOperate218.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

}
