package com.artsoft.download.Interface;

import com.artsoft.download.BaiDu.TemTvplayPersonTvplay;



/**
 * 电视剧接口
 * 2017年4月14日16:43:21
 * @author Administrator
 *
 */
public class BaiduTvplayInfo {
	
	
//	/**
//	 * 综艺基本信息
//	 * @param sql
//	 */
//	public static void infoSql(String sql){
//		BaiduZhongyiInfoByUrl.runnewMainurl(sql);
//	}
//	
	
	
	/**
	 * 抓取电影百科所有角色的人物和饰演信息，配合数据清洗 流程化
	 * 
	 * @param sql
	 */
//	public static void PersonAsRoleSql(String sql){
////		BaiduZhongyiInfoByUrl.runnewMainurl(sql);
//		
//	}
	
	
	
	/**
	 * 抓取电视剧百科所有角色的人物和饰演信息，配合数据清洗 流程化
	 * 2017年4月14日16:53:15
	 * @param sql
	 */
	public static void tvplayAsPersonSql(String sql){
//	BaiduZhongyiInfoByUrl.runnewMainurl(sql);
		TemTvplayPersonTvplay.robotrun(sql);
	
	}	
	
	
}
