package com.artsoft.bean;

public class TEM_FILM_CINEMA_DATE {
	private static final long serialVersionUID = 1L;
	private String title="";
	private String url="";
	private String dataDate="";
	private String cycle="";
	private String totalBoxoffice="";
	private String fieldAveragePnum="";
	private String realTimeBoxoffice="";
	private int dataType;
//	private Date intoDate;
	private String collectionUrl="";
	private String fid="";
	private String CITY_NAME="";
	private int TYPE;
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
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
	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setCycle(String cycle){
		this.cycle=cycle;
	}
	public String getCycle(){
		return cycle;
	}
	public void setTotalBoxoffice(String totalBoxoffice){
		this.totalBoxoffice=totalBoxoffice;
	}
	public String getTotalBoxoffice(){
		return totalBoxoffice;
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
	public void setDataType(Integer dataType){
		this.dataType=dataType;
	}
	public Integer getDataType(){
		return dataType;
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
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}
}
