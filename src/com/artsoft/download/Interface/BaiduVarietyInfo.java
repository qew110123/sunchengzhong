package com.artsoft.download.Interface;

import com.artsoft.download.BaiDu.baidu_jiben_xinxi.BaiduZhongyiInfoByUrl;



/**
 * ���սӿ�
 * 2017��3��30��18:21:52
 * @author Administrator
 *
 */
public class BaiduVarietyInfo {
	
	
	/**
	 * ���ջ�����Ϣ
	 * @param sql
	 */
	public static void infoSql(String sql){
		BaiduZhongyiInfoByUrl.runnewMainurl(sql);
	}
	
	
}
