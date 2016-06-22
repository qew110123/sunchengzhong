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
	
	private String STILLS_TITLE="";
	
	private int STILLS_ORDERNO=0;
	
	

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
	public String getSTILLS_TITLE() {
		return STILLS_TITLE;
	}
	public void setSTILLS_TITLE(String sTILLS_TITLE) {
		STILLS_TITLE = sTILLS_TITLE;
	}
	public int getSTILLS_ORDERNO() {
		return STILLS_ORDERNO;
	}
	public void setSTILLS_ORDERNO(int sTILLS_ORDERNO) {
		STILLS_ORDERNO = sTILLS_ORDERNO;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
}

