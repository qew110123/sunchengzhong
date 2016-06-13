package com.artsoft.bean;

import java.io.Serializable;

public class TEM_FILM_WEEK_CINEMA implements Serializable {
	private static final long serialVersionUID = 1L;
	private String weekDataDate;
	private String title;
	private String weekBoxoffice;
	private String avgScreen;
	private String fieldAveragePnum;
	private String screenYield;
	private String scenesTime;
//	private Date intoDate;
	private String collectionUrl;

	public void setWeekDataDate(String weekDataDate){
		this.weekDataDate=weekDataDate;
	}
	public String getWeekDataDate(){
		return weekDataDate;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setWeekBoxoffice(String weekBoxoffice){
		this.weekBoxoffice=weekBoxoffice;
	}
	public String getWeekBoxoffice(){
		return weekBoxoffice;
	}
	public void setAvgScreen(String avgScreen){
		this.avgScreen=avgScreen;
	}
	public String getAvgScreen(){
		return avgScreen;
	}
	public void setFieldAveragePnum(String fieldAveragePnum){
		this.fieldAveragePnum=fieldAveragePnum;
	}
	public String getFieldAveragePnum(){
		return fieldAveragePnum;
	}
	public void setScreenYield(String screenYield){
		this.screenYield=screenYield;
	}
	public String getScreenYield(){
		return screenYield;
	}
	public void setScenesTime(String scenesTime){
		this.scenesTime=scenesTime;
	}
	public String getScenesTime(){
		return scenesTime;
	}
	public void setCollectionUrl(String collectionUrl){
		this.collectionUrl=collectionUrl;
	}
	public String getCollectionUrl(){
		return collectionUrl;
	}
}

