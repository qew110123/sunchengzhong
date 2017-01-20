package com.artsoft.bean;

public class TEM_PERSON_URL_WORKS {
	
	
	private static final long serialVersionUID = 1L;
	private String personId="";
	private String personName="";
	private String personUrl="";
	private String dateName="";
	private String dateUrl="";
	private String dateDirector="";
	private String dateMajorActors="";
	private String updateTime="";
	private Integer source=0;
	private Integer dataType=0;
	
	private String dateTime="";

	public void setPersonId(String personId){
		this.personId=personId;
	}
	public String getPersonId(){
		return personId;
	}
	public void setPersonName(String personName){
		this.personName=personName;
	}
	public String getPersonName(){
		return personName;
	}
	public void setPersonUrl(String personUrl){
		this.personUrl=personUrl;
	}
	public String getPersonUrl(){
		return personUrl;
	}
	public void setDateName(String dateName){
		this.dateName=dateName;
	}
	public String getDateName(){
		return dateName;
	}
	public void setDateUrl(String dateUrl){
		this.dateUrl=dateUrl;
	}
	public String getDateUrl(){
		return dateUrl;
	}
	public void setDateDirector(String dateDirector){
		this.dateDirector=dateDirector;
	}
	public String getDateDirector(){
		return dateDirector;
	}
	public void setDateMajorActors(String dateMajorActors){
		this.dateMajorActors=dateMajorActors;
	}
	public String getDateMajorActors(){
		return dateMajorActors;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setSource(Integer source){
		this.source=source;
	}
	public Integer getSource(){
		return source;
	}
	public void setDataType(Integer dataType){
		this.dataType=dataType;
	}
	public Integer getDataType(){
		return dataType;
	}
	
	public void setDateTime(String dateTime){
		this.dateTime=dateTime;
	}
	public String getDateTime(){
		return dateTime;
	}

}
