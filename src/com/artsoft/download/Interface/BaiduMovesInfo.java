package com.artsoft.download.Interface;

import com.artsoft.download.BaiDu.TemTvplayPersonMoves;



/**
 * ��Ӱ���ݽӿ�
 * 2017��4��14��17:03:07
 * @author Administrator
 *
 */
public class BaiduMovesInfo {
	
	
	/**
	 * ץȡ��Ӱ�ٿ����н�ɫ�������������Ϣ�����������ϴ ���̻�
	 * 2017��4��14��16:53:15
	 * @param sql
	 */
	public static void movesAsPersonSql(String sql){
//	BaiduZhongyiInfoByUrl.runnewMainurl(sql);
//		TemTvplayPersonTvplay.robotrun(sql);
		TemTvplayPersonMoves.runnewMainurl(sql);
	
	}	
	
	
}
