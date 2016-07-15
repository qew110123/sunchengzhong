package com.artsoft.bean;

public class TEM_DIM_PLATFORM {
	private static final long serialVersionUID = 1L;
	private int tvplayId=0;
	public int getTvplayId() {
		return tvplayId;
	}
	public void setTvplayId(int tvplayId) {
		this.tvplayId = tvplayId;
	}
	public void setSource(int source) {
		this.source = source;
	}
	private String tvplayName="";
	private String tvplayUrl="";
	private String basicInfo="";
	private String playAmount="";
	private String timeInfo="";
	private String majorActors="";
	private int source=1;
//	private Date intoDate;
	
	private int dataType=1;
	private String produceArea="";
	private String subjectName="";
	private String channelName="";
	private String tvplayNameSmall="";
	private String url="";
	private String TVSTATION="";


	public void setTvplayName(String tvplayName){
		this.tvplayName=tvplayName;
	}
	public String getTvplayName(){
		return tvplayName;
	}
	public void setTvplayUrl(String tvplayUrl){
		this.tvplayUrl=tvplayUrl;
	}
	public String getTvplayUrl(){
		return tvplayUrl;
	}
	public void setBasicInfo(String basicInfo){
		this.basicInfo=basicInfo;
	}
	public String getBasicInfo(){
		return basicInfo;
	}
	public void setPlayAmount(String playAmount){
		this.playAmount=playAmount;
	}
	public String getPlayAmount(){
		return playAmount;
	}
	public void setTimeInfo(String timeInfo){
		this.timeInfo=timeInfo;
	}
	public String getTimeInfo(){
		return timeInfo;
	}
	public void setMajorActors(String majorActors){
		this.majorActors=majorActors;
	}
	public String getMajorActors(){
		return majorActors;
	}
	public void setSource(Integer source){
		this.source=source;
	}
	public Integer getSource(){
		return source;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public String getProduceArea() {
		return produceArea;
	}
	public void setProduceArea(String produceArea) {
		this.produceArea = produceArea;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getTvplayNameSmall() {
		return tvplayNameSmall;
	}
	public void setTvplayNameSmall(String tvplayNameSmall) {
		this.tvplayNameSmall = tvplayNameSmall;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTVSTATION() {
		return TVSTATION;
	}
	public void setTVSTATION(String tVSTATION) {
		TVSTATION = tVSTATION;
	}
}
