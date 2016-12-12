package com.artsoft.bean;

public class DEL_DIM_NETWORKPLAY {
	

	private static final long serialVersionUID = 1L;
	private Integer networkplayId=0;
	private String networkplayName="";
	private String showName="";
	private String actors="";
	private String director="";
	private String screenwriter="";
	private String labelName="";
	private String firstDate="";
	private Integer setNum=0;
	private double score=0;
	private String dataUrl="";

	public void setNetworkplayId(Integer networkplayId){
		this.networkplayId=networkplayId;
	}
	public Integer getNetworkplayId(){
		return networkplayId;
	}
	public void setNetworkplayName(String networkplayName){
		this.networkplayName=networkplayName;
	}
	public String getNetworkplayName(){
		return networkplayName;
	}
	public void setShowName(String showName){
		this.showName=showName;
	}
	public String getShowName(){
		return showName;
	}
	public void setActors(String actors){
		this.actors=actors;
	}
	public String getActors(){
		return actors;
	}
	public void setDirector(String director){
		this.director=director;
	}
	public String getDirector(){
		return director;
	}
	public void setScreenwriter(String screenwriter){
		this.screenwriter=screenwriter;
	}
	public String getScreenwriter(){
		return screenwriter;
	}
	public void setLabelName(String labelName){
		this.labelName=labelName;
	}
	public String getLabelName(){
		return labelName;
	}
	public void setFirstDate(String firstDate){
		this.firstDate=firstDate;
	}
	public String getFirstDate(){
		return firstDate;
	}
	public void setSetNum(Integer setNum){
		this.setNum=setNum;
	}
	public Integer getSetNum(){
		return setNum;
	}
	public void setScore(double score){
		this.score=score;
	}
	public double getScore(){
		return score;
	}
	public void setDataUrl(String dataUrl){
		this.dataUrl=dataUrl;
	}
	public String getDataUrl(){
		return dataUrl;
	}

}
