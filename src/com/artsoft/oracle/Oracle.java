package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.Tem_weibo_word_age_sex;
import com.artsoft.bean.Tem_weibo_word_area;
import com.artsoft.bean.Tem_weibo_word_tag;
import com.artsoft.bean.tem_weibo_word_num;
import com.artsoft.util.TimeTest;

public class Oracle {
	
	
	/**
	 * 2016年5月11日14:27:59
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(tem_weibo_word_num tem_weibo_word_num) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_weibo_word_num t(t.data_date ,t.data_id,t.word,t.news_num,t.into_date,t.url,t.dimension_type,t.data_type)values (?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_num.getDataDate());
		list.add(tem_weibo_word_num.getDataId());
		list.add(tem_weibo_word_num.getWord());
		list.add(tem_weibo_word_num.getNewsNum());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_num.getUrl());
		list.add(tem_weibo_word_num.getDimensionType());
		list.add(tem_weibo_word_num.getDataType());
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	/**
	 * 2016年5月11日14:27:59
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(Tem_weibo_word_area tem_weibo_word_area) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into  ods.tem_weibo_word_area t(t.data_date,t.data_id ,t.word ,t.area_num,t.area_rate,t.area_top,t.area_name_en,t.area_name_cn,t.into_date,t.url,t.dimension_type,t.data_type) values (?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_area.getDataDate());
		list.add(tem_weibo_word_area.getDataId());
		list.add(tem_weibo_word_area.getWord());
		list.add(tem_weibo_word_area.getAreaNum());
		list.add(tem_weibo_word_area.getAreaRate());
		list.add(tem_weibo_word_area.getAreaTop());
		list.add(tem_weibo_word_area.getAreaNameEn());
		list.add(tem_weibo_word_area.getAreaNameCn());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_area.getUrl());
		list.add(tem_weibo_word_area.getDimensionType());
		list.add(tem_weibo_word_area.getDataType());
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 *2016年5月11日16:46:27
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(Tem_weibo_word_age_sex tem_weibo_word_age_sex) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_weibo_word_age_sex t (t.data_date,t.data_id,t.word,t.man_rate,t.woman_rate,t.rate12,t.rate18,t.rate24,t.rate34,t.rate50,t.other_rate,t.into_date,t.url,t.data_type)values (?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_age_sex.getDataDate());
		list.add(tem_weibo_word_age_sex.getDataId());
		list.add(tem_weibo_word_age_sex.getWord());
		list.add(tem_weibo_word_age_sex.getManRate());
		list.add(tem_weibo_word_age_sex.getWomanRate());
		list.add(tem_weibo_word_age_sex.getRate12());
		list.add(tem_weibo_word_age_sex.getRate18v());
		list.add(tem_weibo_word_age_sex.getRate24());
		list.add(tem_weibo_word_age_sex.getRate34());
		list.add(tem_weibo_word_age_sex.getRate50());
		list.add(tem_weibo_word_age_sex.getOtherRate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_age_sex.getUrl());
//		list.add(tem_weibo_word_age_sex.getDimensionType());
		list.add(tem_weibo_word_age_sex.getDataType());
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}
	
	
	/**
	 *2016年5月11日16:47:19
	 * 
	 * @param company
	 * 
	 */
	public static void InsertCompany(Tem_weibo_word_tag tem_weibo_word_tag) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ods.tem_weibo_word_tag t (t.data_date,t.data_id,t.word,t.label_name,t.label_rate,t.into_date,t.url,t.dimension_type,t.data_type)values (?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?)";
		List<Comparable> list = new ArrayList();
		list.add(tem_weibo_word_tag.getDataDate());
		list.add(tem_weibo_word_tag.getDataId());
		list.add(tem_weibo_word_tag.getWord());
		list.add(tem_weibo_word_tag.getLabelName());
		if (tem_weibo_word_tag.getLabelRate().equals(null)||tem_weibo_word_tag.getLabelRate()==null) {
			tem_weibo_word_tag.setLabelRate("");
		}
		list.add(tem_weibo_word_tag.getLabelRate());
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(tem_weibo_word_tag.getUrl());
		list.add(tem_weibo_word_tag.getDimensionType());
//		list.add(tem_weibo_word_age_sex.getDimensionType());
		list.add(tem_weibo_word_tag.getDataType());
		System.out.println(list.toString());
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

}
