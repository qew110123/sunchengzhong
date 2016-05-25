package com.artsoft.bean;


import java.io.Serializable;
import java.math.*;


/** ods.TEM_WEIBO_TOPIC_SCORE
	DATA_DATE	VARCHAR2(100)
	DATA_ID	NUMBER(18)
	READING_NUM	NUMBER(18)
	DISCUSS_NUM	NUMBER(18)
	FAN_NUM	NUMBER(18)
	SCORE	NUMBER(15,4)
	FOLLOW_NUM	NUMBER(18)
	INTO_DATE	DATE
	DATA_URL	VARCHAR2(600)
	BIG_TYPE	NUMBER(2)
	DATA_TYPE	NUMBER(2)
*/
public class TEM_WEIBO_TOPIC_SCORE implements Serializable {
//	private static final long serialVersionUID = 1L;
	private String dataDate="";
	private int dataId;
	private int readingNum;
	private int discussNum;
	private int fanNum;
	private double score;
	private int followNum;
	private String intoDate="";
	private String dataUrl="";
	private Integer bigType;
	private Integer dataType;
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public int getReadingNum() {
		return readingNum;
	}
	public void setReadingNum(int readingNum) {
		this.readingNum = readingNum;
	}
	public int getDiscussNum() {
		return discussNum;
	}
	public void setDiscussNum(int discussNum) {
		this.discussNum = discussNum;
	}
	public int getFanNum() {
		return fanNum;
	}
	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getFollowNum() {
		return followNum;
	}
	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}
	public String getIntoDate() {
		return intoDate;
	}
	public void setIntoDate(String intoDate) {
		this.intoDate = intoDate;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public Integer getBigType() {
		return bigType;
	}
	public void setBigType(Integer bigType) {
		this.bigType = bigType;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	
}

