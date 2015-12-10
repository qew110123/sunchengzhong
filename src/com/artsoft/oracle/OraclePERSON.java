package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.bean.PERSON_ONTHER;
import com.artsoft.bean.Persion;

public class OraclePERSON {
	
	/**
	 * 
	 * @param persion
	 */
	public static void InsertTemDimPerson(PERSON_ONTHER persion) {
		Connection conn = DBOperate218.getInstance().getConnection();

		String strSql = "insert into ODS.TEM_DIM_PERSON_ONTHER t (PERSON_NAME,PERSON_URL,IMG_URL,SEX,BLOODTYPE,HEIGHT,WEIGHT,OCCUPATION,CONSTELLATION,BIRTHDAY,MAJOR_AWARDS,BROKERAGE_FIRM,VOLK,BROKERS,BASIC_INFO,HOBBY,SOURCE,BIG_IMG_URL,FONDNESS,PET_PHRASE,LIKE_COLOR,t.LIKE_FLOWER) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		List<Comparable> list = new ArrayList();
//		list.add(persion.getId());
		list.add(persion.getPERSON_NAME());
		list.add(persion.getPERSON_URL());
		list.add(persion.getIMG_URL());
		list.add(persion.getSEX());
		list.add(persion.getBLOODTYPE());
		list.add(persion.getHEIGHT());
		list.add(persion.getWEIGHT());
		list.add(persion.getOCCUPATION());
		list.add(persion.getCONSTELLATION());
		list.add(persion.getBIRTHDAY());
		list.add(persion.getMAJOR_AWARDS());
		list.add(persion.getBROKERAGE_FIRM());
		list.add(persion.getVOLK());
		list.add(persion.getBROKERS());
		list.add(persion.getBASIC_INFO());
		list.add(persion.getHOBBY());
		list.add(persion.getSOURCE());
		list.add(persion.getBIG_IMG_URL());
		list.add(persion.getFONDNESS());
		list.add(persion.getPET_PHRASE());
		list.add(persion.getLIKE_COLOR());
		list.add(persion.getLIKE_FLOWER());
		
		boolean bb = DBOperate218.insertRecord(conn, strSql, list);
		System.out.println(bb);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
