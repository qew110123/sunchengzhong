package com.artsoft.download.Interface;

import com.artsoft.download.BaiDu.TemTvplayPersonMoves;



/**
 * 电影数据接口
 * 2017年4月14日17:03:07
 * @author Administrator
 *
 */
public class BaiduMovesInfo {
	
	
	/**
	 * 抓取电影百科所有角色的人物和饰演信息，配合数据清洗 流程化
	 * 2017年4月14日16:53:15
	 * @param sql
	 */
	public static void movesAsPersonSql(String sql){
//	BaiduZhongyiInfoByUrl.runnewMainurl(sql);
//		TemTvplayPersonTvplay.robotrun(sql);
		TemTvplayPersonMoves.runnewMainurl(sql);
	
	}	
	
	
}
