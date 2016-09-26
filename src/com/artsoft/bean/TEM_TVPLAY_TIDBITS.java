package com.artsoft.bean;

public class TEM_TVPLAY_TIDBITS {
	private String dataDate="";
	private int tvplayId;
	private String tvplayName="";
	private String titleName="";
	private String detailUrl="";
	private String playUrl="";
	private String putDate="";
	private int playAmount;
	private String playPlatform="";
	private String timeLongs="";
	private int orderNo;
	private String imgSmallUrl="";
	private String imgSmallName="";
	private String intoDate="";
	private Integer dataType=1;
	private Integer SOURCE=0;

	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setTvplayName(String tvplayName){
		this.tvplayName=tvplayName;
	}
	public String getTvplayName(){
		return tvplayName;
	}
	public void setTitleName(String titleName){
		this.titleName=titleName;
	}
	public String getTitleName(){
		return titleName;
	}
	public void setDetailUrl(String detailUrl){
		this.detailUrl=detailUrl;
	}
	public String getDetailUrl(){
		return detailUrl;
	}
	public void setPlayUrl(String playUrl){
		this.playUrl=playUrl;
	}
	public String getPlayUrl(){
		return playUrl;
	}
	public void setPutDate(String putDate){
		this.putDate=putDate;
	}
	public String getPutDate(){
		return putDate;
	}
	public void setPlayPlatform(String playPlatform){
		this.playPlatform=playPlatform;
	}
	public String getPlayPlatform(){
		return playPlatform;
	}
	public void setTimeLongs(String timeLongs){
		this.timeLongs=timeLongs;
	}
	public String getTimeLongs(){
		return timeLongs;
	}
	public void setImgSmallUrl(String imgSmallUrl){
		this.imgSmallUrl=imgSmallUrl;
	}
	public String getImgSmallUrl(){
		return imgSmallUrl;
	}
	public void setImgSmallName(String imgSmallName){
		this.imgSmallName=imgSmallName;
	}
	public String getImgSmallName(){
		return imgSmallName;
	}
	public void setIntoDate(String intoDate){
		this.intoDate=intoDate;
	}
	public String getIntoDate(){
		return intoDate;
	}
	public void setDataType(Integer dataType){
		this.dataType=dataType;
	}
	public Integer getDataType(){
		return dataType;
	}
	public int getTvplayId() {
		return tvplayId;
	}
	public void setTvplayId(int tvplayId) {
		this.tvplayId = tvplayId;
	}
	public int getPlayAmount() {
		return playAmount;
	}
	public void setPlayAmount(int playAmount) {
		this.playAmount = playAmount;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getSOURCE() {
		return SOURCE;
	}
	public void setSOURCE(Integer sOURCE) {
		SOURCE = sOURCE;
	}
}
