package com.artsoft.bean;

public class tem_person_works {
	private static final long serialVersionUID = 1L;
	private int personId;
	private String personUrl="";
	private String name="";
	private String producedTime="";
	private String roleName="";
	private String director="";
	private String majorActors="";
	private String singer="";
	private String remarks="";
	private String textValue="";
	private int dataType;
	private String updateTime="";
	private String name_URL="";

	public String getName_URL() {
		return name_URL;
	}
	public void setName_URL(String name_URL) {
		this.name_URL = name_URL;
	}
	public void setPersonId(int personId){
		this.personId=personId;
	}
	public int getPersonId(){
		return personId;
	}
	public void setPersonUrl(String personUrl){
		this.personUrl=personUrl;
	}
	public String getPersonUrl(){
		return personUrl;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setProducedTime(String producedTime){
		this.producedTime=producedTime;
	}
	public String getProducedTime(){
		return producedTime;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	public String getRoleName(){
		return roleName;
	}
	public void setDirector(String director){
		this.director=director;
	}
	public String getDirector(){
		return director;
	}
	public void setMajorActors(String majorActors){
		this.majorActors=majorActors;
	}
	public String getMajorActors(){
		return majorActors;
	}
	public void setSinger(String singer){
		this.singer=singer;
	}
	public String getSinger(){
		return singer;
	}
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	public String getRemarks(){
		return remarks;
	}
	public void setTextValue(String textValue){
		this.textValue=textValue;
	}
	public String getTextValue(){
		return textValue;
	}
	public void setDataType(int dataType){
		this.dataType=dataType;
	}
	public int getDataType(){
		return dataType;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
}
