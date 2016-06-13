package com.artsoft.bean;

import java.io.Serializable;

public class TEM_FILM_BOXOFFICE_REALTIME implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private String title="";
	private String url="";
	private String fid="";
	private String releasedDays="";
	private String realTimeBoxoffice="";
	private String boxofficeRate="";
	private String totalBoxoffice="";
//	private Date intoDate;
	private String collectionUrl="";
	private String REAL_DATE="";
	public int getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(int dATA_TYPE) {
		DATA_TYPE = dATA_TYPE;
	}
	private int DATA_TYPE=0;
	public String getREAL_DATE() {
		return REAL_DATE;
	}
	public void setREAL_DATE(String rEAL_DATE) {
		REAL_DATE = rEAL_DATE;
	}
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
	public void setFid(String fid){
		this.fid=fid;
	}
	public String getFid(){
		return fid;
	}
	public void setReleasedDays(String releasedDays){
		this.releasedDays=releasedDays;
	}
	public String getReleasedDays(){
		return releasedDays;
	}
	public void setRealTimeBoxoffice(String realTimeBoxoffice){
		this.realTimeBoxoffice=realTimeBoxoffice;
	}
	public String getRealTimeBoxoffice(){
		return realTimeBoxoffice;
	}
	public void setBoxofficeRate(String boxofficeRate){
		this.boxofficeRate=boxofficeRate;
	}
	public String getBoxofficeRate(){
		return boxofficeRate;
	}
	public void setTotalBoxoffice(String totalBoxoffice){
		this.totalBoxoffice=totalBoxoffice;
	}
	public String getTotalBoxoffice(){
		return totalBoxoffice;
	}
	public void setCollectionUrl(String collectionUrl){
		this.collectionUrl=collectionUrl;
	}
	public String getCollectionUrl(){
		return collectionUrl;
	}
}

