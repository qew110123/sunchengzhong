package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class oracleRpc {
	
	/**
	 * 
	 * 2016年8月18日18:22:37
	 * 微博数据少量数据，每日数据
	 * @return
	 */
	
	
	/**
	每天：
	电视剧最新收视列表前50 1
	电影实时票房           
	综艺网络热播前50
	网剧网络热播前50
	类型  2 电视剧 3 电影 4综艺 5 网剧 
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
	综合排名：
	电视剧前500
	电影前500
	综艺前200
	网剧前200
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
