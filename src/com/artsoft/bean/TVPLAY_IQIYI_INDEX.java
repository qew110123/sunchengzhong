package com.artsoft.bean;

public class TVPLAY_IQIYI_INDEX {
	
	
//	private static final long serialVersionUID = 1L;
	private String tvplayId="";
	private String tvplayName="";
	private String vCount="";
	private String dataDate="";
	private String dataUrl="";
	private String createDate="";
	int SOURCE=1; 

	public void setTvplayId(String tvplayId){
		this.tvplayId=tvplayId;
	}
	public String getTvplayId(){
		return tvplayId;
	}
	public void setTvplayName(String tvplayName){
		this.tvplayName=tvplayName;
	}
	public String getTvplayName(){
		return tvplayName;
	}
	public void setVCount(String vCount){
		this.vCount=vCount;
	}
	public String getVCount(){
		return vCount;
	}
	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setDataUrl(String dataUrl){
		this.dataUrl=dataUrl;
	}
	public String getDataUrl(){
		return dataUrl;
	}
	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}
	public String getCreateDate(){
		return createDate;
	}
	public int getSOURCE() {
		return SOURCE;
	}
	public void setSOURCE(int sOURCE) {
		SOURCE = sOURCE;
	}

}
