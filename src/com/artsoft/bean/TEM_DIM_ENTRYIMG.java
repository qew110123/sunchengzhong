package com.artsoft.bean;

import java.io.Serializable;

public class TEM_DIM_ENTRYIMG implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dataId="";
	private String dataTitle="";
	private String smallUrl="";
	private String bigUrl="";
//	private Date intoDate;
	private String dataUrl="";
	private int dataType;

	public void setDataId(String dataId){
		this.dataId=dataId;
	}
	public String getDataId(){
		return dataId;
	}
	public void setDataTitle(String dataTitle){
		this.dataTitle=dataTitle;
	}
	public String getDataTitle(){
		return dataTitle;
	}
	public void setSmallUrl(String smallUrl){
		this.smallUrl=smallUrl;
	}
	public String getSmallUrl(){
		return smallUrl;
	}
	public void setBigUrl(String bigUrl){
		this.bigUrl=bigUrl;
	}
	public String getBigUrl(){
		return bigUrl;
	}
	public void setDataUrl(String dataUrl){
		this.dataUrl=dataUrl;
	}
	public String getDataUrl(){
		return dataUrl;
	}
	public void setDataType(Integer dataType){
		this.dataType=dataType;
	}
	public Integer getDataType(){
		return dataType;
	}
}

