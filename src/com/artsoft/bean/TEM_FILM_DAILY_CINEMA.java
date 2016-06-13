package com.artsoft.bean;

import java.io.Serializable;

public class TEM_FILM_DAILY_CINEMA implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dataDate;
	private String title;
	private String realTimeBoxoffice;
	private String fieldNum;
	private String fieldAveragePnum;
	private String averagePrice;
	private String attendanceRate;
//	private Date intoDate;
	private String collectionUrl;

	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setRealTimeBoxoffice(String realTimeBoxoffice){
		this.realTimeBoxoffice=realTimeBoxoffice;
	}
	public String getRealTimeBoxoffice(){
		return realTimeBoxoffice;
	}
	public void setFieldNum(String fieldNum){
		this.fieldNum=fieldNum;
	}
	public String getFieldNum(){
		return fieldNum;
	}
	public void setFieldAveragePnum(String fieldAveragePnum){
		this.fieldAveragePnum=fieldAveragePnum;
	}
	public String getFieldAveragePnum(){
		return fieldAveragePnum;
	}
	public void setAveragePrice(String averagePrice){
		this.averagePrice=averagePrice;
	}
	public String getAveragePrice(){
		return averagePrice;
	}
	public void setAttendanceRate(String attendanceRate){
		this.attendanceRate=attendanceRate;
	}
	public String getAttendanceRate(){
		return attendanceRate;
	}
	public void setCollectionUrl(String collectionUrl){
		this.collectionUrl=collectionUrl;
	}
	public String getCollectionUrl(){
		return collectionUrl;
	}
}

