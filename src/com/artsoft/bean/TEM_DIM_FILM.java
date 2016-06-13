package com.artsoft.bean;


import java.io.Serializable;
import java.math.*;


/** ods.TEM_DIM_FILM
	FILM_ID	NUMBER(18)
	FILM_NAME	VARCHAR2(150)
	FILM_URL	VARCHAR2(500)
	ENGLISH_NAME	VARCHAR2(150)
	ALIAS_NAME	VARCHAR2(150)
	YEARS	VARCHAR2(150)
	PRODUCER	VARCHAR2(600)
	PRODUCE_COMPANY	VARCHAR2(600)
	PRODUCE_COST	VARCHAR2(100)
	PRODUCE_AREA	VARCHAR2(600)
	PRODUCE_DATE	VARCHAR2(150)
	SHOW_DATE	VARCHAR2(150)
	PREMIERE_DATE	VARCHAR2(150)
	CREATE_TIME	TIMESTAMP
	UPDATE_TIME	TIMESTAMP
	PERFORMANCE_FORM	VARCHAR2(600)
	HISTORICAL_BACKGROUND	VARCHAR2(1500)
	DESCRIPTION	VARCHAR2(4000)
	ISSUE_ORGANIZATION	VARCHAR2(900)
	SUBJECT_NAME_ONE	VARCHAR2(600)
	ISSUING_LICENSE	VARCHAR2(600)
	DIRECTOR	VARCHAR2(600)
	ACTORS	VARCHAR2(4000)
	SCREENWRITER	VARCHAR2(1500)
	SUBJECT_NAME_TWO	VARCHAR2(300)
	SUBJECT_ID_ONE	NUMBER(18)
	SUBJECT_ID_TWO	NUMBER(18)
	AWARD	VARCHAR2(400)
	SHOOTING_LOCATION	VARCHAR2(500)
	SHOOTING_DATE	VARCHAR2(200)
	TIME_LONG	VARCHAR2(50)
	LANGUAGES	VARCHAR2(50)
	IMDB_CODE	VARCHAR2(80)
	FILM_IMG_URL	VARCHAR2(300)
	FILM_LEVEL	VARCHAR2(300)
	PLAY_PLATFORM	VARCHAR2(200)
	ORIGINAL	VARCHAR2(200)
	TV_NAME_UNIQ	VARCHAR2(200)
*/
public class TEM_DIM_FILM implements Serializable {
	private static final long serialVersionUID = 1L;
	private String filmId="";
	private String filmName="";
	private String filmUrl="";
	private String englishName="";
	private String aliasName="";
	private String years="";
	private String producer="";
	private String produceCompany="";
	private String produceCost="";
	private String produceArea="";
	private String produceDate="";
	private String showDate="";
	private String premiereDate="";
//	private Date createTime;
//	private Date updateTime;
	private String performanceForm="";
	private String historicalBackground="";
	private String description="";
	private String issueOrganization="";
	private String subjectNameOne="";
	private String issuingLicense="";
	private String director="";
	private String actors="";
	private String screenwriter="";
	private String subjectNameTwo="";
	private int subjectIdOne;
	private int subjectIdTwo;
	private String award="";
	private String shootingLocation="";
	private String shootingDate="";
	private String timeLong="";
	private String languages="";
	private String imdbCode="";
	private String filmImgUrl="";
	private String filmLevel="";
	private String playPlatform="";
	private String original="";
	private String tvNameUniq="";
	private String baikefilmname="";

	public String getBaikefilmname() {
		return baikefilmname;
	}
	public void setBaikefilmname(String baikefilmname) {
		this.baikefilmname = baikefilmname;
	}
	public void setFilmId(String filmId){
		this.filmId=filmId;
	}
	public String getFilmId(){
		return filmId;
	}
	public void setFilmName(String filmName){
		this.filmName=filmName;
	}
	public String getFilmName(){
		return filmName;
	}
	public void setFilmUrl(String filmUrl){
		this.filmUrl=filmUrl;
	}
	public String getFilmUrl(){
		return filmUrl;
	}
	public void setEnglishName(String englishName){
		this.englishName=englishName;
	}
	public String getEnglishName(){
		return englishName;
	}
	public void setAliasName(String aliasName){
		this.aliasName=aliasName;
	}
	public String getAliasName(){
		return aliasName;
	}
	public void setYears(String years){
		this.years=years;
	}
	public String getYears(){
		return years;
	}
	public void setProducer(String producer){
		this.producer=producer;
	}
	public String getProducer(){
		return producer;
	}
	public void setProduceCompany(String produceCompany){
		this.produceCompany=produceCompany;
	}
	public String getProduceCompany(){
		return produceCompany;
	}
	public void setProduceCost(String produceCost){
		this.produceCost=produceCost;
	}
	public String getProduceCost(){
		return produceCost;
	}
	public void setProduceArea(String produceArea){
		this.produceArea=produceArea;
	}
	public String getProduceArea(){
		return produceArea;
	}
	public void setProduceDate(String produceDate){
		this.produceDate=produceDate;
	}
	public String getProduceDate(){
		return produceDate;
	}
	public void setShowDate(String showDate){
		this.showDate=showDate;
	}
	public String getShowDate(){
		return showDate;
	}
	public void setPremiereDate(String premiereDate){
		this.premiereDate=premiereDate;
	}
	public String getPremiereDate(){
		return premiereDate;
	}
	public void setPerformanceForm(String performanceForm){
		this.performanceForm=performanceForm;
	}
	public String getPerformanceForm(){
		return performanceForm;
	}
	public void setHistoricalBackground(String historicalBackground){
		this.historicalBackground=historicalBackground;
	}
	public String getHistoricalBackground(){
		return historicalBackground;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setIssueOrganization(String issueOrganization){
		this.issueOrganization=issueOrganization;
	}
	public String getIssueOrganization(){
		return issueOrganization;
	}
	public void setSubjectNameOne(String subjectNameOne){
		this.subjectNameOne=subjectNameOne;
	}
	public String getSubjectNameOne(){
		return subjectNameOne;
	}
	public void setIssuingLicense(String issuingLicense){
		this.issuingLicense=issuingLicense;
	}
	public String getIssuingLicense(){
		return issuingLicense;
	}
	public void setDirector(String director){
		this.director=director;
	}
	public String getDirector(){
		return director;
	}
	public void setActors(String actors){
		this.actors=actors;
	}
	public String getActors(){
		return actors;
	}
	public void setScreenwriter(String screenwriter){
		this.screenwriter=screenwriter;
	}
	public String getScreenwriter(){
		return screenwriter;
	}
	public void setSubjectNameTwo(String subjectNameTwo){
		this.subjectNameTwo=subjectNameTwo;
	}
	public String getSubjectNameTwo(){
		return subjectNameTwo;
	}
	public void setSubjectIdOne(int subjectIdOne){
		this.subjectIdOne=subjectIdOne;
	}
	public int getSubjectIdOne(){
		return subjectIdOne;
	}
	public void setSubjectIdTwo(int subjectIdTwo){
		this.subjectIdTwo=subjectIdTwo;
	}
	public int getSubjectIdTwo(){
		return subjectIdTwo;
	}
	public void setAward(String award){
		this.award=award;
	}
	public String getAward(){
		return award;
	}
	public void setShootingLocation(String shootingLocation){
		this.shootingLocation=shootingLocation;
	}
	public String getShootingLocation(){
		return shootingLocation;
	}
	public void setShootingDate(String shootingDate){
		this.shootingDate=shootingDate;
	}
	public String getShootingDate(){
		return shootingDate;
	}
	public void setTimeLong(String timeLong){
		this.timeLong=timeLong;
	}
	public String getTimeLong(){
		return timeLong;
	}
	public void setLanguages(String languages){
		this.languages=languages;
	}
	public String getLanguages(){
		return languages;
	}
	public void setImdbCode(String imdbCode){
		this.imdbCode=imdbCode;
	}
	public String getImdbCode(){
		return imdbCode;
	}
	public void setFilmImgUrl(String filmImgUrl){
		this.filmImgUrl=filmImgUrl;
	}
	public String getFilmImgUrl(){
		return filmImgUrl;
	}
	public void setFilmLevel(String filmLevel){
		this.filmLevel=filmLevel;
	}
	public String getFilmLevel(){
		return filmLevel;
	}
	public void setPlayPlatform(String playPlatform){
		this.playPlatform=playPlatform;
	}
	public String getPlayPlatform(){
		return playPlatform;
	}
	public void setOriginal(String original){
		this.original=original;
	}
	public String getOriginal(){
		return original;
	}
	public void setTvNameUniq(String tvNameUniq){
		this.tvNameUniq=tvNameUniq;
	}
	public String getTvNameUniq(){
		return tvNameUniq;
	}
}

