package com.artsoft.bean;

public class Tem_weibo_word_tag {
	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private String dataId="";
	private String word="";
	private String labelName="";
	private String labelRate="";
	private String  intoDate="";
	private String updateDate="";
	private String url="";
	private Integer dimensionType;
	private Integer dataType;

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
	public void setIntoDate(String intoDate){
		this.intoDate=intoDate;
	}
	public String getIntoDate(){
		return intoDate;
	}
	public void setUpdateDate(String updateDate){
		this.updateDate=updateDate;
	}
	public String getUpdateDate(){
		return updateDate;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setDimensionType(Integer dimensionType){
		this.dimensionType=dimensionType;
	}
	public Integer getDimensionType(){
		return dimensionType;
	}
	public void setDataType(Integer dataType){
		this.dataType=dataType;
	}
	public Integer getDataType(){
		return dataType;
	}

}
