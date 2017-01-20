package com.artsoft.bean;

public class TEM_PERSON_URL {
	private String personId;
	private String personName;
	private String personUrl;
	private Integer type;
	private String upDate;

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
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setUpDate(String upDate){
		this.upDate=upDate;
	}
	public String getUpDate(){
		return upDate;
	}

}
