package com.artsoft.download.Interface;

import com.artsoft.download.BaiDu.baidu_jiben_xinxi.BaiduNetworkplayInfoByUrl;



/**
 * 网剧接口
 * 2017年3月30日18:21:52
 * @author Administrator
 *
 */
public class BaiduNetworkplayInfo {
	
	
	/**
	 * 网剧基本信息
	 * @param sql
	 */
	public static void infoSql(String sql){
		BaiduNetworkplayInfoByUrl.runnewMainurl(sql);
	}
	
}
