package com.artsoft.bean;

public class tem_weibo_word_num {

	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private String dataId="";
	private String word="";
	private int newsNum;
	private String intoDate="";
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
	public void setNewsNum(int newsNum){
		this.newsNum=newsNum;
	}
	public int getNewsNum(){
		return newsNum;
	}
	public void setIntoDate(String intoDate){
		this.intoDate=intoDate;
	}
	public String getIntoDate(){
		return intoDate;
	}
	public void setUpdateDate(String  updateDate){
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
