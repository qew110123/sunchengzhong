package com.artsoft.bean;

public class TEM_IQIYI_AND_YOUKU_WORD_INDEX {
	
//	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private String dataId="";
	private String word="";
	private String labelName="";
	private String labelRate="";
	private String  intoDate="";
	private Integer dimensionType=0;
	private Integer source=0;

	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setDataId(String dataId){
		this.dataId=dataId;
	}
	public String getDataId(){
		return dataId;
	}
	public void setWord(String word){
		this.word=word;
	}
	public String getWord(){
		return word;
	}
	public void setLabelName(String labelName){
		this.labelName=labelName;
	}
	public String getLabelName(){
		return labelName;
	}
	public void setLabelRate(String labelRate){
		this.labelRate=labelRate;
	}
	public String getLabelRate(){
		return labelRate;
	}
	public void setDimensionType(Integer dimensionType){
		this.dimensionType=dimensionType;
	}
	public Integer getDimensionType(){
		return dimensionType;
	}
	public void setSource(Integer source){
		this.source=source;
	}
	public Integer getSource(){
		return source;
	}

}
