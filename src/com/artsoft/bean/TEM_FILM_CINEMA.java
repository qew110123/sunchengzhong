package com.artsoft.bean;

public class TEM_FILM_CINEMA {
	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private String title="";
	private String url="";
	private String top="";
	private String totalBoxoffice="";
	private String fieldPnum="";
	private String fieldAveragePnum="";
	private String realTimeBoxoffice="";
//	private Date intoDate;
	private String collectionUrl="";
	private String CITY_NAME="";
	private int DATA_TYPE;
	public int getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(int dATA_TYPE) {
		DATA_TYPE = dATA_TYPE;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	private String fid="";

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
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setTop(String top){
		this.top=top;
	}
	public String getTop(){
		return top;
	}
	public void setTotalBoxoffice(String totalBoxoffice){
		this.totalBoxoffice=totalBoxoffice;
	}
	public String getTotalBoxoffice(){
		return totalBoxoffice;
	}
	public void setFieldPnum(String fieldPnum){
		this.fieldPnum=fieldPnum;
	}
	public String getFieldPnum(){
		return fieldPnum;
	}
	public void setFieldAveragePnum(String fieldAveragePnum){
		this.fieldAveragePnum=fieldAveragePnum;
	}
	public String getFieldAveragePnum(){
		return fieldAveragePnum;
	}
	public void setRealTimeBoxoffice(String realTimeBoxoffice){
		this.realTimeBoxoffice=realTimeBoxoffice;
	}
	public String getRealTimeBoxoffice(){
		return realTimeBoxoffice;
	}
	public void setCollectionUrl(String collectionUrl){
		this.collectionUrl=collectionUrl;
	}
	public String getCollectionUrl(){
		return collectionUrl;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}

}
