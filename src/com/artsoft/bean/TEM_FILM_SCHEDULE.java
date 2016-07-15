package com.artsoft.bean;

public class TEM_FILM_SCHEDULE {
	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private String fid="";
	private String url="";
	private String cityName="";
	private String title="";
	private String fieldNumRate="";
	private String fieldNum="";
//	private Date intoDate;
	private String collectionUrl="";

	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setFid(String fid){
		this.fid=fid;
	}
	public String getFid(){
		return fid;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setCityName(String cityName){
		this.cityName=cityName;
	}
	public String getCityName(){
		return cityName;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setFieldNumRate(String fieldNumRate){
		this.fieldNumRate=fieldNumRate;
	}
	public String getFieldNumRate(){
		return fieldNumRate;
	}
	public void setFieldNum(String fieldNum){
		this.fieldNum=fieldNum;
	}
	public String getFieldNum(){
		return fieldNum;
	}
	public void setCollectionUrl(String collectionUrl){
		this.collectionUrl=collectionUrl;
	}
	public String getCollectionUrl(){
		return collectionUrl;
	}
}
