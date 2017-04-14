package com.artsoft.download.Interface;

import com.artsoft.download.BaiDu.baidu_jiben_xinxi.BaiduZhongyiInfoByUrl;



/**
 * 综艺接口
 * 2017年3月30日18:21:52
 * @author Administrator
 *
 */
public class BaiduVarietyInfo {
	
	
	/**
	 * 综艺基本信息
	 * @param sql
	 */
	public static void infoSql(String sql){
		BaiduZhongyiInfoByUrl.runnewMainurl(sql);
	}
	
	
}
